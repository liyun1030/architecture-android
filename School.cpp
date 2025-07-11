#include <iostream>
using namespace std;
class School
{
public:
	static void addLibBooks(string book)
	{
		lib += book;
	}
public:
	string tower;
	string lake;
	static string lib;
};
//类外实始化
string School::lib = "Beijing lib:";


//int main()
//{
//	School a, b, c, d;
//	//static 数据成员，属于类，并不属于对象。
//	//存储于 data 区的 rw 段
//	cout << sizeof(a) << sizeof(b) << sizeof(c) << sizeof(d) << endl;
//	//类的方式访问,编译有问题，必须要初始化。
//	//在无对象生成的时候，亦可以访问。
//	School::lib = "China lib:";
//	cout << School::lib << endl;
//	//lib 虽然属于类，但是目的是为了实现类对象间的共享
//	//故对象也是可以访问的。
//	cout << a.lib << endl;
//	cout << b.lib << endl;
//	//为了搞好图书馆的建设，提设 static 接口
//	School::addLibBooks("mao xuan");
//	cout << School::lib << endl;
//	return 0;
//}
