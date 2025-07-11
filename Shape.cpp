#include <iostream>
using namespace std;
class Shape {
public:
    void draw() { cout << "Drawing shape" << endl; }
    void rotate() { cout << "Rotating shape" << endl; }
};

//int main() {
//    Shape s;
//    void (Shape:: * action)() = &Shape::draw;  // 动态选择方法
//    (s.*action)();  // 输出：Drawing shape
//
//    action = &Shape::rotate;  // 切换方法
//    (s.*action)();  // 输出：Rotating shape
//    return 0;
//}