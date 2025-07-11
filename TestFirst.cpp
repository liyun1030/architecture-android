#include <iostream>
#include <thread>
#include <mutex>

std::mutex mtx;  // 定义互斥锁
int shared_data = 0;  // 共享资源

void increment() {
    for (int i = 0; i < 100000; ++i) {
        std::lock_guard<std::mutex> lock(mtx);  // 自动加锁
        ++shared_data;  // 临界区
    }  // 离开作用域自动解锁
}
void unsafe_increment() {
    for (int i = 0; i < 100000; ++i) {
        mtx.lock();  // 手动加锁
        ++shared_data;
        mtx.unlock();  // 手动解锁（必须确保执行，否则死锁）
    }
}
//int main() {
//    std::thread t1(unsafe_increment);
//    std::thread t2(unsafe_increment);
//
//    t1.join();
//    t2.join();
//
//    std::cout << "Final value: " << shared_data << std::endl;  // 输出应为200000
//    return 0;
//}