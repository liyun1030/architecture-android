#include <iostream>
using namespace std;
class Widget
{
public:
	Widget()
	{
		fptr[0] = &Widget::f;
		fptr[1] = &Widget::g;
		fptr[2] = &Widget::h;
		fptr[3] = &Widget::i;
	}
	void select(int idx, int val)
	{
		if (idx<0 || idx>cnt) return;
		(this->*fptr[idx])(val);
	}
	int count()
	{
		return cnt;
	}
private:
	void f(int val) { cout << "void f() " << val << endl; }
	void g(int val) { cout << "void g() " << val << endl; }
	void h(int val) { cout << "void h() " << val << endl; }
	void i(int val) { cout << "void i() " << val << endl; }
	enum { cnt = 4 };
	void (Widget::* fptr[cnt])(int);
};


class A
{
public:
	static void dis(int num);
	static int data;
};
void A::dis(int num)
{
	cout << data<<'\x20'<<string(5,' ') << num << endl;
}
int A::data = 100;

void testW() {
	Widget w;
	for (int i = 0; i < w.count(); i++)
	{
		w.select(i, i);
	}
}
void testAA() {
	int* p = &A::data;
	cout << *p << endl;
	void (*pfunc)(int) = &A::dis;
	pfunc(54);
}
//int main()
//{	
//	testAA();
//	//testW();
//	return 0;
//}