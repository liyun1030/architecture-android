#include "util.h"
// src/utils.cpp
#include "TestExtern.h"

// ���岢��ʼ���������
int globalCounter = 0;

// ʵ�ֹ�����
void incrementCounter() {
    ++globalCounter;
}

int getCounter() {
    return globalCounter;
}