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
//	Base& b = d; //Base ��û���麯��ʱ����ʱ��
//	cout << typeid(b).name() << endl;
//	cout << typeid(d).name() << endl;
//	Base* p = &d;
//	cout << typeid(p).name() << endl; //�ж�ָ���ǣ���ʵ�ǿ�������������Ϣ��cout<<typeid(*p).name()<<endl;
//	cout << typeid(d).name() << endl;
//	cout << boolalpha << (typeid(*p) == typeid(d)) << endl;
//	return 0;
//}