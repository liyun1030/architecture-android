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
    // ���캯��������ָ�������Ĺ����߳�
    ThreadPool(size_t threads) : stop(false) {
        for (size_t i = 0; i < threads; ++i) {
            workers.emplace_back([this] {//��������
                while (true) {
                    std::function<void()> task;
                    { // �ٽ�����ʼ
                        // �����ȴ�����
                        std::unique_lock<std::mutex> lock(this->queue_mutex);
                        this->condition.wait(lock, [this] {
                            return this->stop || !this->tasks.empty();
                            });

                        // ����̳߳�ֹͣ��û�������˳��߳�
                        if (this->stop && this->tasks.empty())
                            return;

                        // ȡ������
                        task = std::move(this->tasks.front());
                        this->tasks.pop();
                    }// �ٽ������������Զ��ͷ�

                    // ִ������
                    task();
                }
                });
        }
    }

    // ���������������̳߳���Դ
    ~ThreadPool() {
        {
            std::unique_lock<std::mutex> lock(queue_mutex);
            stop = true;
        }

        // ֪ͨ�����߳�ֹͣ
        condition.notify_all();

        // �ȴ������߳����
        for (std::thread& worker : workers) {
            worker.join();
        }
    }

    // ���̳߳��������
    template<class F, class... Args>
    auto enqueue(F&& f, Args&&... args)
        -> std::future<typename std::result_of<F(Args...)>::type> {
        using return_type = typename std::result_of<F(Args...)>::type;

        // ��װ����Ϊ shared_ptr �Ա��ڴ��ݺʹ洢
        auto task = std::make_shared< std::packaged_task<return_type()> >(
            std::bind(std::forward<F>(f), std::forward<Args>(args)...)
        );

        // ��ȡ����� future
        std::future<return_type> res = task->get_future();

        {
            // ���������������
            std::unique_lock<std::mutex> lock(queue_mutex);

            // �̳߳�ֹͣ�������������
            if (stop)
                throw std::runtime_error("enqueue on stopped ThreadPool");

            // ��������ӵ�����
            tasks.emplace([task]() { (*task)(); });
        }

        // ֪ͨһ���ȴ����߳���������
        condition.notify_one();

        return res;
    }

private:
    // �����̼߳���
    std::vector<std::thread> workers;

    // �������
    std::queue<std::function<void()>> tasks;

    // ͬ��ԭ��
    std::mutex queue_mutex;
    std::condition_variable condition;
    bool stop;
};

void testThreadPool() {
    // ����һ������ 4 ���̵߳��̳߳�
    ThreadPool pool(4);

    // �洢����� future
    std::vector< std::future<int> > results;

    // �ύ 8 �������̳߳�
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

    // ��ȡ����ӡ������
    for (auto&& result : results) {
        std::cout << "Result: " << result.get() << std::endl;
    }

}
void worker(std::promise<int>& p) {
    // ģ���ʱ����
    std::this_thread::sleep_for(std::chrono::seconds(1));
    p.set_value(42);  // ���ý��
}

int testPromise() {
    std::promise<int> prom;
    std::future<int> fut = prom.get_future();  // ��ȡ�� promise ������ future

    std::thread t(worker, std::ref(prom));
    t.detach();

    std::cout << "Result: " << fut.get() << std::endl;  // �ȴ�����ȡ���
    return 0;
}
int main() {
    testThreadPool();
    //testPromise();
    return 0;
}