#include "TestPointer.h"
#include <iostream>
#include <memory>

//int main() {
//    // ����һ��unique_ptr��ָ��һ����̬�����int����
//    std::unique_ptr<int> ptr(new int(42));
//
//    // ʹ��ָ��������ͽ����ò�����������ָ������ֵ
//    std::cout << *ptr << std::endl;  // ���: 42
//
//    // ͨ���ƶ����캯��������Ȩת�Ƹ���һ��unique_ptr
//    std::unique_ptr<int> ptr2 = std::move(ptr);
//
//    // ע�⣬��ʱptr�Ѿ�Ϊ��ָ�룬����ӵ�ж��������Ȩ
//    std::cout << *ptr2 << std::endl;  // ���: 42
//
//    // ʹ���Զ���ɾ����
//    struct Deleter {
//        void operator()(int* p) {
//            std::cout << "Custom deleter called" << std::endl;
//            delete p;
//        }
//    };
//
//    std::unique_ptr<int, Deleter> ptr3(new int(100), Deleter());
//
//    // unique_ptr����������ʱ���Զ��ͷ��ڴ棬ͬʱ�����Զ���ɾ����
//    return 0;
//}