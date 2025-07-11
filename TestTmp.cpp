#include "TestTmp.h"
#include <cassert>
#include <iostream>

using namespace std;
//Ԫ���

template<int N>
struct Factorial {
    static constexpr int value = N * Factorial<N - 1>::value;
};

// �ػ���ֹ�ݹ�
template<>
struct Factorial<0> {
    static constexpr int value = 1;
};

//쳲���������
template<int N>
struct Fibonacci {
    static constexpr int value = Fibonacci<N - 1>::value + Fibonacci<N - 2>::value;
};

template<>
struct Fibonacci<0> { static constexpr int value = 0; };

template<>
struct Fibonacci<1> { static constexpr int value = 1; };


// �����б����
template<typename... Ts>
struct TypeList {};

// ��������Ƿ����б���
template<typename T, typename List>
struct Contains;

// �ػ������б�
template<typename T>
struct Contains<T, TypeList<>> : std::false_type {};

// �ػ���ƥ���һ��Ԫ��
template<typename T, typename... Ts>
struct Contains<T, TypeList<T, Ts...>> : std::true_type {};

// �ػ����ݹ������Ԫ��
template<typename T, typename U, typename... Ts>
struct Contains<T, TypeList<U, Ts...>> : Contains<T, TypeList<Ts...>> {};


void accessElement(int arr[], int index) {
    assert(index >= 0 && index < 10);  // ȷ����������Ч��Χ��
    arr[index] = 42;
}
//int main() {
    //int arr[10] = {0};  // ��ʼ������
    //// ���Է���Ԫ��
    //accessElement(arr, 15);  // ��ȷ����
    //cout << "arr[5]: " << arr[5] << endl;  // �����42
    // accessElement(arr, 10);  // ������ʣ���������
    // ʹ��Ԫ��̼���׳˺�쳲���������
 //   cout << "Factorial of 5: " << Factorial<5>::value << endl;  // �����120
	//cout << "Fibonacci of 10: " << Fibonacci<10>::value << endl;  // �����55
 //   // ʹ�ã�
 //   constexpr int result = Factorial<5>::value;  // �����ڼ��� 5! = 120
	//cout << result << endl; // ������

 //   // ʹ�ã�
 //   constexpr int fib_10 = Fibonacci<10>::value;  // �����ڼ���쳲�������10��
 //   cout << fib_10 << endl; // ������


    // ʹ�ã�
    //static_assert(Contains<int, TypeList<char, int, double>>::value, "���ʹ���");
    //return 0;
//}