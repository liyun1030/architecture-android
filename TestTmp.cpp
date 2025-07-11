#include "TestTmp.h"
#include <cassert>
#include <iostream>

using namespace std;
//元编程

template<int N>
struct Factorial {
    static constexpr int value = N * Factorial<N - 1>::value;
};

// 特化终止递归
template<>
struct Factorial<0> {
    static constexpr int value = 1;
};

//斐波那契数列
template<int N>
struct Fibonacci {
    static constexpr int value = Fibonacci<N - 1>::value + Fibonacci<N - 2>::value;
};

template<>
struct Fibonacci<0> { static constexpr int value = 0; };

template<>
struct Fibonacci<1> { static constexpr int value = 1; };


// 类型列表基类
template<typename... Ts>
struct TypeList {};

// 检查类型是否在列表中
template<typename T, typename List>
struct Contains;

// 特化：空列表
template<typename T>
struct Contains<T, TypeList<>> : std::false_type {};

// 特化：匹配第一个元素
template<typename T, typename... Ts>
struct Contains<T, TypeList<T, Ts...>> : std::true_type {};

// 特化：递归检查后续元素
template<typename T, typename U, typename... Ts>
struct Contains<T, TypeList<U, Ts...>> : Contains<T, TypeList<Ts...>> {};


void accessElement(int arr[], int index) {
    assert(index >= 0 && index < 10);  // 确保索引在有效范围内
    arr[index] = 42;
}
//int main() {
    //int arr[10] = {0};  // 初始化数组
    //// 测试访问元素
    //accessElement(arr, 15);  // 正确访问
    //cout << "arr[5]: " << arr[5] << endl;  // 输出：42
    // accessElement(arr, 10);  // 错误访问，触发断言
    // 使用元编程计算阶乘和斐波那契数列
 //   cout << "Factorial of 5: " << Factorial<5>::value << endl;  // 输出：120
	//cout << "Fibonacci of 10: " << Fibonacci<10>::value << endl;  // 输出：55
 //   // 使用：
 //   constexpr int result = Factorial<5>::value;  // 编译期计算 5! = 120
	//cout << result << endl; // 输出结果

 //   // 使用：
 //   constexpr int fib_10 = Fibonacci<10>::value;  // 编译期计算斐波那契第10项
 //   cout << fib_10 << endl; // 输出结果


    // 使用：
    //static_assert(Contains<int, TypeList<char, int, double>>::value, "类型存在");
    //return 0;
//}