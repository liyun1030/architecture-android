#include <iostream>
#include "TestExtern.h"
using namespace std;	

extern int var;

extern "C" {
#include "TestExtern.h"  // C 语言头文件
}

//int main() {
//    std::cout << "初始值: " << getCounter() << std::endl;  // 输出: 0
//
//    incrementCounter();
//    std::cout << "增加后: " << getCounter() << std::endl;  // 输出: 1
//
//    // 直接访问全局变量（不推荐，优先使用封装的函数）
//    globalCounter += 10;
//    std::cout << "直接修改后: " << getCounter() << std::endl;  // 输出: 11
//
//    var = 123;
//    cout << "Hello, World!" << endl;
//    return 0;
//}

