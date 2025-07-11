#include <iostream>

using namespace std;
class Student
{
public:
	Student(string sn, int n, char s);
	~Student();
	void dis();
private:
	string name;
	int num;
	char sex;
};


class Graduate :public Student
{
public:
	Graduate(string sn, int in, char cs, float fs);
	~Graduate();
	void dump()
	{
		dis();
		cout << salary << endl;
	}
private:
	float salary;
};

class Birthday
{
public:
	Birthday(int y, int m, int d);
	~Birthday();
	void print();
private:
	int year;
	int month;
	int day;
};

class Doctor :public Graduate
{
public:
	Doctor(string sn, int in, char cs, float fs, string st, int iy, int im, int id);
	~Doctor();
	void disdump();
private:
	string title; //调用的默认构造器，初始化为”” Birthday birth; //类中声明的类对象
	Birthday birth; //类中声明的类对象
};