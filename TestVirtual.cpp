#include "TestVirtual.h"
#include "iostream"
#include <stdio.h>
#include <string.h>
#include <typeinfo>
using namespace std;

#define add(a,b) ((a) + (b)) //宏定义
void swap(int &a,int &b) {
	int temp = a;
	a = b;
	b = temp;
	cout << "a = " << a << ", b = " << b << endl;
}
void swap2(int *a, int *b) {
	int temp = *a;
	*a = *b;
	*b = temp;
	cout << "a = " << *a << ", b = " << *b << endl;
}
void test() {
	//int a, b;
	//int& r = a;
	//cout << &a << &r << endl; //变量与引用具有相同的地址。
	//int& ra = r; //可对引用更次引用，表示 a 变量有两个别名，分别是r 和ra
	//int a = 3, b = 5;
	//cout << "a = " << a << ", b = " << b << endl;
	/*swap(a, b);
	cout << "After swap: a = " << a << ", b = " << b << endl;*/
	//swap2(&a, &b);
}
void test1() {
	int* p = new int;
	int* a = new int(3);
	int* b = new int[100] {0};
	int** P = new int* [5] {NULL};
}
void test2() {
		//int* p = new int(5);
		//cout << *p << endl;
		//delete p;
		//char* pp = new char[10];
		//strcpy(pp, "china");
		//cout << pp << endl;
		//delete[]pp;
		//string* ps = new string("china");
		//cout << *ps << endl; //cout<<ps<<endl;
		//delete ps;
		//char** pa = new char* [5];
		//memset(pa, 0, sizeof(char* [5]));
		
}
void test3() {
	const int x = 200;
	int& a = const_cast<int&>(x); // int &a = x;
	a = 300;
	cout << a << x << endl;
	//cout << &a << "---" << &x << endl;
	//int* p = const_cast<int*>(&x); // int *p = &x;
	//*p = 400;
	//cout << a << *p << endl;
	//cout << p << "---" << &x << endl;
}
namespace MySpace
{
	int val = 5;
	int x, y, z;
}
void test4() {
	using namespace MySpace;
	x = 1;
	y = 2;
	z = 3;
	cout << "x = " << x << ", y = " << y << ", z = " << z << endl;
	cout << "val = " << val << endl;
}
void test5(){
	using MySpace::x;
	MySpace::y = 10;
}
//int main() {
//	test3();
//	return 0;
//}