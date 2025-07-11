// include/shared.h
#pragma once

// 共享变量声明（不分配内存）
extern int globalCounter;

// 共享函数声明（extern 可省略）
extern void incrementCounter();
extern int getCounter();