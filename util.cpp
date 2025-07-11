#include "util.h"
// src/utils.cpp
#include "TestExtern.h"

// 定义并初始化共享变量
int globalCounter = 0;

// 实现共享函数
void incrementCounter() {
    ++globalCounter;
}

int getCounter() {
    return globalCounter;
}