#include <iostream>
#include <cmath>
using namespace std;

class Point {
private:
    double x, y;

public:
    Point(double x = 0, double y = 0) : x(x), y(y) {}

    // ��������֮��ľ���
    double distanceTo(const Point& other) const {
        return sqrt(pow(x - other.x, 2) + pow(y - other.y, 2));
    }

    // ��ȡ����
    double getX() const { return x; }
    double getY() const { return y; }
};

class Circle {
private:
    Point center;  // Բ��
    double radius; // �뾶

public:
    Circle(const Point& c, double r) : center(c), radius(r) {}

    // �ж���Բ�Ƿ��ཻ
    bool isIntersecting(const Circle& other) const {
        double centersDist = center.distanceTo(other.center);
        double sumRadii = radius + other.radius;
        double diffRadii = abs(radius - other.radius);

        // �ཻ������|r1-r2| �� d �� r1+r2
        return (diffRadii <= centersDist) && (centersDist <= sumRadii);
    }

    // ��ȡԲ�ĺͰ뾶
    Point getCenter() const { return center; }
    double getRadius() const { return radius; }
};

//int main() {
//    double x, y, r;
//
//    // �����һ��Բ
//    cout << "�������һ��Բ��Բ������Ͱ뾶����ʽ��x y r����";
//    cin >> x >> y >> r;
//    Circle circle1(Point(x, y), r);
//
//    // ����ڶ���Բ
//    cout << "������ڶ���Բ��Բ������Ͱ뾶����ʽ��x y r����";
//    cin >> x >> y >> r;
//    Circle circle2(Point(x, y), r);
//
//    // �жϲ�������
//    if (circle1.isIntersecting(circle2)) {
//        cout << "��Բ�ཻ" << endl;
//    }
//    else {
//        cout << "��Բ���ཻ" << endl;
//    }
//
//    return 0;
//}