#include <iostream>
using namespace std;
class Shape {
public:
    void draw() { cout << "Drawing shape" << endl; }
    void rotate() { cout << "Rotating shape" << endl; }
};

//int main() {
//    Shape s;
//    void (Shape:: * action)() = &Shape::draw;  // ��̬ѡ�񷽷�
//    (s.*action)();  // �����Drawing shape
//
//    action = &Shape::rotate;  // �л�����
//    (s.*action)();  // �����Rotating shape
//    return 0;
//}