#include <iostream>

using namespace std;

class circle {
	private:
		double radius;
		double height;	
		static double pi;

	public:
		circle(double r, double h) : radius(r), height(h) {
			//构造函数
		}
		void printZhouchang() {
			//周长
			cout <<"周长:"<< 2 * pi * radius << " ";
		}
		void printMianji() {
			//面积
			cout << "面积:" << pi * radius * radius << " ";
		}
		void printBiaoMianJi() {  
			// 圆球表面积
			cout << "圆球表面积:" << 4 * pi * radius * radius << " ";
		}

		void printTiJi() {  
			// 圆柱体积
			cout << "圆柱体积:" << pi * radius * radius * height << endl;
		}
};
double circle::pi = 3.14;  // 静态成员变量初始化
//int main() {
//	double r;
//	double h;
//	cin >> r >> h;  // 输入半径和高度
//	circle c(r,h);
//	void (circle:: * pp)() = &circle::printZhouchang;  // 指向成员函数的指针
//	(c.*pp)();  // 调用成员函数
//	pp = &circle::printMianji;  // 改变指针指向
//	(c.*pp)();  // 调用成员函数
//	pp = &circle::printBiaoMianJi;  // 改变指针指向
//	(c.*pp)();  // 调用成员函数
//	pp = &circle::printTiJi;  // 改变指针指向
//	(c.*pp)();  // 调用成员函数
//	return 0;
//}