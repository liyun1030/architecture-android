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
				   class Derived1 : virtual public Base {};  // �̳�һ�� Base::data
				   class Derived2 : virtual public Base {};  // �̳���һ�� Base::data
				   class Final : public Derived1, public Derived2 {};  // ���� Base::data


				   void testVal() {
					   testVal();
					   Z z;
					   z.dis();
					   z.setData(2000);
					   cout << z.getData() << endl;
				   }
				   void tstVal() {
					   // ʹ��ʱ��������
					   Final f;
					   f.data = 10;// Ψһһ�� Base::data
					   f.Derived1::data = 10;  // ������ʽָ��·��
					   f.Derived2::data = 20;  // �������ݲ�һ��
				   }
//int main()
//{
//	return 0;
//}