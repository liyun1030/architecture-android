#include <iostream>
using namespace std;
class Complex
{
public:
	Complex(float x = 0, float y = 0):_x(x), _y(y) {}
	void dis()
	{
		cout << "(" << _x << "," << _y << ")" << endl;
	}
	
	Complex& operator+=(const Complex& c)
	{
		this->_x += c._x;
		this->_y += c._y;
		return *this;
	}
	friend const Complex operator+(const Complex& c1, const Complex& c2);
private:
	float _x;
	float _y;
};
const Complex operator+(const Complex& c1, const Complex& c2)
{
	return Complex(c1._x + c2._x, c1._y + c2._y);
}



//int main()
//{
//	Complex c1(2, 3);
//	Complex c2(3, 4);
//	c1.dis();
//	c2.dis();
//	// Complex c3 = c1+c2;
//	Complex c3 = operator+(c1, c2);
//	c3.dis();
//
//	int a = 10, b = 20, c = 30;
//	(a += b) += c;
//	cout << "a = " << a << endl;
//	cout << "b = " << b << endl;
//	cout << "c = " << c << endl;
//	Complex a1(10, 0), b1(20, 0);
//
//	return 0;
//}