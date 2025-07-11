#include <iostream>
using namespace std;
class X
{
public:
	X(int d)
		:_data(d) {
	}
	void setData(int i)
	{
		_data = i;
	}
	int _data;
};
class Y
{
public:
	Y(int d)
		:_data(d) {
	}
	int getData()
	{
		return _data;
	}
	int _data;
};
class Z :public X, public Y
{
public:
	Z() :X(2), Y(3)
	{
	}
	void dis()
	{
		cout << X::_data << endl;
		cout << Y::_data << endl;
	}
};


class Base { public: int data; };
				   class Derived1 : virtual public Base {};  // 继承一份 Base::data
				   class Derived2 : virtual public Base {};  // 继承另一份 Base::data
				   class Final : public Derived1, public Derived2 {};  // 两份 Base::data


				   void testVal() {
					   testVal();
					   Z z;
					   z.dis();
					   z.setData(2000);
					   cout << z.getData() << endl;
				   }
				   void tstVal() {
					   // 使用时产生歧义
					   Final f;
					   f.data = 10;// 唯一一份 Base::data
					   f.Derived1::data = 10;  // 必须显式指定路径
					   f.Derived2::data = 20;  // 导致数据不一致
				   }
//int main()
//{
//	return 0;
//}