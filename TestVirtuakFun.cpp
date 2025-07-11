#include <iostream>

using namespace std;

class Shape {
public:
	Shape() : _x(0), _y(0) {}
	Shape(int x, int y)
		: _x(x), _y(y) {
	}
	virtual void draw()
	{
		cout << "draw Shap ";
		cout << "start (" << _x << "," << _y << ") " << endl;
	}
	virtual ~Shape() {  // 基类析构函数应声明为virtual
		cout << "Shape destructor called" << endl;
	}
private:
	int _x, _y; //坐标
};
class Circle : public Shape {
public:
	Circle(int x, int y, int r)
		: _x(x), _y(y), _r(r) {
	}
	void draw() override {  // 重写虚函数
		cout << "draw Circle ";
		cout << "start (" << _x << "," << _y << ") ";
		cout << "radius r = " << _r << endl;
	}
	
private:
	int _x, _y; //坐标
	int _r; //半径
};

class Rect : public Shape {
public:
	Rect(int x, int y, int len, int wid)
		: _x(x), _y(y), _len(len), _wid(wid) {
	}
	void draw() override {  // 重写虚函数
		cout << "draw Rect ";
		cout << "start (" << _x << "," << _y << ") ";
		cout << "len = " << _len << " wid = " << _wid << endl;
	}
private:
	int _x, _y; //坐标
	int _len, _wid; //长和宽
};

//int main()
//{
//	Circle c(1, 2, 4);
//	c.draw();
//	Rect r(2, 3, 4, 5);
//	r.draw();
//	Shape* ps;
//	int choice;
//	while (1) //真正的实现了动多态，在运行阶段决定。
//	{
//		if (scanf_s("%d", &choice) != 1) {
//			cin.clear();  // 清除错误标志
//			cin.ignore(); // 忽略缓冲区中的字符
//			continue;
//		}
//		switch (choice)
//		{
//		case 1:
//			ps = &c;
//			ps->draw();
//			break;
//		case 2:
//			ps = &r;
//			ps->draw();
//			break;
//		}
//	}
//
//	return 0;
//}