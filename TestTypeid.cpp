#include <iostream>
#include <typeinfo>
using namespace std;
typedef void (*Func)();
class Base
{
public:
	virtual ~Base() {}
};
class Derive :public Base
{
};
//int main()
//{
//	cout << typeid(int).name() << endl;
//	cout << typeid(double).name() << endl;
//	cout << typeid(char*).name() << endl;
//	cout << typeid(char**).name() << endl;
//	cout << typeid(const char*).name() << endl;
//	cout << typeid(const char* const).name() << endl;
//	cout << typeid(Func).name() << endl;
//	cout << typeid(Base).name() << endl;
//	cout << typeid(Derive).name() << endl << endl;
//	Derive d;
//	Base& b = d; //Base 中没有虚函数时，有时？
//	cout << typeid(b).name() << endl;
//	cout << typeid(d).name() << endl;
//	Base* p = &d;
//	cout << typeid(p).name() << endl; //判断指针是，其实是看不出其类型信息的cout<<typeid(*p).name()<<endl;
//	cout << typeid(d).name() << endl;
//	cout << boolalpha << (typeid(*p) == typeid(d)) << endl;
//	return 0;
//}