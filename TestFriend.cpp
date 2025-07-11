#include <iostream>
#include <cmath>
using namespace std;

class Point {
private:
    double x, y;
public:
    Point(double x = 0, double y = 0) : x(x), y(y) {}

    // ������Ԫ����
    friend double distance(const Point& p1, const Point& p2);
};

// ��Ԫ����ʵ�֣�ע�⣺������ĳ�Ա������
double distance(const Point& p1, const Point& p2) {
    return sqrt(pow(p1.x - p2.x, 2) + pow(p1.y - p2.y, 2));
}
class Account {
private:
    string owner;
    double balance;
public:
    Account(string name, double bal) : owner(name), balance(bal) {}

    // ������Ԫ��
    friend class BankSystem;
};

class BankSystem {
public:
    // ���Է��� Account ��˽�г�Ա
    static void transfer(Account& from, Account& to, double amount) {
        from.balance -= amount;
        to.balance += amount;
    }

    static void showBalance(const Account& acc) {
        cout << acc.owner << " �����: " << acc.balance << endl;
    }
};
void testFriend() {
    Account a1("Alice", 1000);
    Account a2("Bob", 500);
	

    BankSystem::transfer(a1, a2, 300);  // ת��
    BankSystem::showBalance(a1);  // ���: Alice �����: 700
    BankSystem::showBalance(a2);  // ���: Bob �����: 800
	BankSystem::transfer(a2, a1, 200);  // ��ת��
}
//int main() {
//    testFriend();
//    //Point p1(1, 2), p2(4, 6);
//    //cout << "�������: " << distance(p1, p2) << endl;  // ���: 5
//    return 0;
//}