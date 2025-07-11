#include <iostream>
#include <cmath>
using namespace std;

class Point {
private:
    double x, y;

public:
    Point(double x = 0, double y = 0) : x(x), y(y) {}

    // 计算两点之间的距离
    double distanceTo(const Point& other) const {
        return sqrt(pow(x - other.x, 2) + pow(y - other.y, 2));
    }

    // 获取坐标
    double getX() const { return x; }
    double getY() const { return y; }
};

class Circle {
private:
    Point center;  // 圆心
    double radius; // 半径

public:
    Circle(const Point& c, double r) : center(c), radius(r) {}

    // 判断两圆是否相交
    bool isIntersecting(const Circle& other) const {
        double centersDist = center.distanceTo(other.center);
        double sumRadii = radius + other.radius;
        double diffRadii = abs(radius - other.radius);

        // 相交条件：|r1-r2| ≤ d ≤ r1+r2
        return (diffRadii <= centersDist) && (centersDist <= sumRadii);
    }

    // 获取圆心和半径
    Point getCenter() const { return center; }
    double getRadius() const { return radius; }
};

//int main() {
//    double x, y, r;
//
//    // 输入第一个圆
//    cout << "请输入第一个圆的圆心坐标和半径（格式：x y r）：";
//    cin >> x >> y >> r;
//    Circle circle1(Point(x, y), r);
//
//    // 输入第二个圆
//    cout << "请输入第二个圆的圆心坐标和半径（格式：x y r）：";
//    cin >> x >> y >> r;
//    Circle circle2(Point(x, y), r);
//
//    // 判断并输出结果
//    if (circle1.isIntersecting(circle2)) {
//        cout << "两圆相交" << endl;
//    }
//    else {
//        cout << "两圆不相交" << endl;
//    }
//
//    return 0;
//}