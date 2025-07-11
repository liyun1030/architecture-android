#include <iostream>
#include <cmath>
using namespace std;

class Point {
private:
    double x, y;
public:
    Point(double x = 0, double y = 0) : x(x), y(y) {}

    // 声明友元函数
    friend double distance(const Point& p1, const Point& p2);
};

// 友元函数实现（注意：不是类的成员函数）
double distance(const Point& p1, const Point& p2) {
    return sqrt(pow(p1.x - p2.x, 2) + pow(p1.y - p2.y, 2));
}
class Account {
private:
    string owner;
    double balance;
public:
    Account(string name, double bal) : owner(name), balance(bal) {}

    // 声明友元类
    friend class BankSystem;
};

class BankSystem {
public:
    // 可以访问 Account 的私有成员
    static void transfer(Account& from, Account& to, double amount) {
        from.balance -= amount;
        to.balance += amount;
    }

    static void showBalance(const Account& acc) {
        cout << acc.owner << " 的余额: " << acc.balance << endl;
    }
};
void testFriend() {
    Account a1("Alice", 1000);
    Account a2("Bob", 500);
	

    BankSystem::transfer(a1, a2, 300);  // 转账
    BankSystem::showBalance(a1);  // 输出: Alice 的余额: 700
    BankSystem::showBalance(a2);  // 输出: Bob 的余额: 800
	BankSystem::transfer(a2, a1, 200);  // 再转账
}
//int main() {
//    testFriend();
//    //Point p1(1, 2), p2(4, 6);
//    //cout << "两点距离: " << distance(p1, p2) << endl;  // 输出: 5
//    return 0;
//}