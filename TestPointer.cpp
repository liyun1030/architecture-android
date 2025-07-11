#include "TestPointer.h"
#include <iostream>
#include <memory>

//int main() {
//    // 创建一个unique_ptr，指向一个动态分配的int对象
//    std::unique_ptr<int> ptr(new int(42));
//
//    // 使用指针操作符和解引用操作符访问所指向对象的值
//    std::cout << *ptr << std::endl;  // 输出: 42
//
//    // 通过移动构造函数将所有权转移给另一个unique_ptr
//    std::unique_ptr<int> ptr2 = std::move(ptr);
//
//    // 注意，此时ptr已经为空指针，不再拥有对象的所有权
//    std::cout << *ptr2 << std::endl;  // 输出: 42
//
//    // 使用自定义删除器
//    struct Deleter {
//        void operator()(int* p) {
//            std::cout << "Custom deleter called" << std::endl;
//            delete p;
//        }
//    };
//
//    std::unique_ptr<int, Deleter> ptr3(new int(100), Deleter());
//
//    // unique_ptr超出作用域时会自动释放内存，同时调用自定义删除器
//    return 0;
//}