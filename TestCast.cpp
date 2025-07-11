#include <iostream>
#include <typeinfo>
using namespace std;
class A
{
public:
	virtual ~A() {}
};
class B :public A
{
};
class C :public A
{
};
class D
{
};
// 目标函数（无参数、返回 void）
void sayHello() {
	std::cout << "Hello!" << std::endl;
}
typedef void (*Func)();
// 声明函数指针变量（两种方式）
Func ptr1 = &sayHello;  // 取地址符 & 可省略
Func ptr2 = sayHello;   // 直接赋值函数名（自动转换为指针）

#include <functional>

// 接受 std::function 参数
void execute(std::function<void()> callback) {
	callback();
}


void testPtr() {
	// 调用函数（两种方式）
	ptr1();  // 输出: Hello!
	(*ptr2)();  // 等价写法（显式解引用）
	execute(ptr1);  // 使用 std::function
}
template <typename T>
void Swap(T& a, T& b)
{
	T t = a;
	a = b;
	b = t;
}

//int main()
//{
//	//testPtr();
//	int ia = 10; int ib = 20;
//	Swap<int>(ia,ib);
//	//B b;
//	//A* pa = &b;
//	//B* pb = dynamic_cast<B*>(pa); //成功
//	//cout << "b:" << &b << endl;
//	//cout << "pa:" << pa << endl;
//	//cout <<"pb:"<< pb << endl;
//	//C* pc = dynamic_cast<C*>(pa); //不成功 安全
//	//cout << "pc:" << pc << endl;
//	//D* pd = dynamic_cast<D*>(pa); //不成功 安全
//	//cout << "pd:" << pd << endl;
//	//pb = static_cast<B*>(pa); //成功
//	//cout << "pb2:" << pb << endl;
//	//pc = static_cast<C*>(pa); //成功 不安全
//	//cout << "pc2:" << pc << endl;
//	//// pd = static_cast<D*>(pa); //编译 不成功
//	//// cout<<pd<<endl;
//	//pb = reinterpret_cast<B*>(pa); //成功 不安全
//	//cout << "pb3:" << pb << endl;
//	//pc = reinterpret_cast<C*>(pa); //成功 不安全
//	//cout << "pc3:" << pc << endl;
//	//pd = reinterpret_cast<D*>(pa); //成功 不安全
//	//cout << "pd3:" << pd << endl;
//	return 0;
//}