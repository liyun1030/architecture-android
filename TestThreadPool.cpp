#include "TestThreadPool.h"
#include <iostream>
#include <vector>
#include <queue>
#include <memory>
#include <thread>
#include <mutex>
#include <condition_variable>
#include <future>
#include <functional>
#include <stdexcept>

class ThreadPool {
public:
    // 构造函数：创建指定数量的工作线程
    ThreadPool(size_t threads) : stop(false) {
        for (size_t i = 0; i < threads; ++i) {
            workers.emplace_back([this] {//构建对象
                while (true) {
                    std::function<void()> task;
                    { // 临界区开始
                        // 加锁等待任务
                        std::unique_lock<std::mutex> lock(this->queue_mutex);
                        this->condition.wait(lock, [this] {
                            return this->stop || !this->tasks.empty();
                            });

                        // 如果线程池停止且没有任务，退出线程
                        if (this->stop && this->tasks.empty())
                            return;

                        // 取出任务
                        task = std::move(this->tasks.front());
                        this->tasks.pop();
                    }// 临界区结束，锁自动释放

                    // 执行任务
                    task();
                }
                });
        }
    }

    // 析构函数：清理线程池资源
    ~ThreadPool() {
        {
            std::unique_lock<std::mutex> lock(queue_mutex);
            stop = true;
        }

        // 通知所有线程停止
        condition.notify_all();

        // 等待所有线程完成
        for (std::thread& worker : workers) {
            worker.join();
        }
    }

    // 向线程池添加任务
    template<class F, class... Args>
    auto enqueue(F&& f, Args&&... args)
        -> std::future<typename std::result_of<F(Args...)>::type> {
        using return_type = typename std::result_of<F(Args...)>::type;

        // 包装任务为 shared_ptr 以便于传递和存储
        auto task = std::make_shared< std::packaged_task<return_type()> >(
            std::bind(std::forward<F>(f), std::forward<Args>(args)...)
        );

        // 获取任务的 future
        std::future<return_type> res = task->get_future();

        {
            // 加锁保护任务队列
            std::unique_lock<std::mutex> lock(queue_mutex);

            // 线程池停止后不能再添加任务
            if (stop)
                throw std::runtime_error("enqueue on stopped ThreadPool");

            // 将任务添加到队列
            tasks.emplace([task]() { (*task)(); });
        }

        // 通知一个等待的线程有新任务
        condition.notify_one();

        return res;
    }

private:
    // 工作线程集合
    std::vector<std::thread> workers;

    // 任务队列
    std::queue<std::function<void()>> tasks;

    // 同步原语
    std::mutex queue_mutex;
    std::condition_variable condition;
    bool stop;
};

void testThreadPool() {
    // 创建一个包含 4 个线程的线程池
    ThreadPool pool(4);

    // 存储任务的 future
    std::vector< std::future<int> > results;

    // 提交 8 个任务到线程池
    for (int i = 0; i < 8; ++i) {
        results.emplace_back(
            pool.enqueue([i] {
                std::cout << "Task " << i << " started" << std::endl;
                std::this_thread::sleep_for(std::chrono::seconds(1));
                std::cout << "Task " << i << " finished" << std::endl;
                return i * i;
                })
        );
    }

    // 获取并打印任务结果
    for (auto&& result : results) {
        std::cout << "Result: " << result.get() << std::endl;
    }

}
void worker(std::promise<int>& p) {
    // 模拟耗时操作
    std::this_thread::sleep_for(std::chrono::seconds(1));
    p.set_value(42);  // 设置结果
}

int testPromise() {
    std::promise<int> prom;
    std::future<int> fut = prom.get_future();  // 获取与 promise 关联的 future

    std::thread t(worker, std::ref(prom));
    t.detach();

    std::cout << "Result: " << fut.get() << std::endl;  // 等待并获取结果
    return 0;
}
int main() {
    testThreadPool();
    //testPromise();
    return 0;
}