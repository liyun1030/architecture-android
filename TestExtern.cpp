#include <iostream>
#include "TestExtern.h"
using namespace std;	

extern int var;

extern "C" {
#include "TestExtern.h"  // C ����ͷ�ļ�
}

//int main() {
//    std::cout << "��ʼֵ: " << getCounter() << std::endl;  // ���: 0
//
//    incrementCounter();
//    std::cout << "���Ӻ�: " << getCounter() << std::endl;  // ���: 1
//
//    // ֱ�ӷ���ȫ�ֱ��������Ƽ�������ʹ�÷�װ�ĺ�����
//    globalCounter += 10;
//    std::cout << "ֱ���޸ĺ�: " << getCounter() << std::endl;  // ���: 11
//
//    var = 123;
//    cout << "Hello, World!" << endl;
//    return 0;
//}

