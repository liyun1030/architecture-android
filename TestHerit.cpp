#include <iostream>
#include "TestHerit.h"
using namespace std;
//class Person
//{
//public:
//	void eat(string food)
//	{
//		cout << "i am eating " << food << endl;
//	}
//};
//class Student :public Person
//{
//public:void study(string course)
//{
//	cout << "i am a student i study " << course << endl;
//}
//};
//class Teacher :public Person
//{
//public:
//	void teach(string course)
//	{
//		cout << "i am a teacher i teach " << course << endl;
//	}
//};
//int main()
//{
//	Student s;
//	s.study("C++");
//	s.eat("»ÆìË¼¦");
//	Teacher t;
//	t.teach("Java");
//	t.eat("Â¿Èâ»ðÉÕ");
//	return 0;
//}

Student::Student(string sn, int n, char s)
	:name(sn), num(n), sex(s)
{
}
Student::~Student()
{
}
void Student::dis()
{
	cout << name << endl;
	cout << num << endl;
	cout << sex << endl;
}
Graduate::Graduate(string sn, int in, char cs, float fs)
	:Student(sn, in, cs), salary(fs)
{
}
Graduate::~Graduate()
{
}

Birthday::Birthday(int y, int m, int d)
	:year(y), month(m), day(d)
{
}
Birthday::~Birthday()
{
}
void Birthday::print()
{
		cout << year << month << day << endl;
}

Doctor::Doctor(string sn, int in, char cs, float fs, string st, int iy, int im, int id)
	:Graduate(sn, in, cs, fs), birth(iy, im, id), title(st)
{
}
Doctor::~Doctor()
{
}
void Doctor::disdump()
{
	dump();
	cout << title << endl;
	birth.print();
}

//int main()
//{
//	Student s("zhaosi", 2001, 'm');
//	s.dis();
//	cout << "----------------" << endl;
//	Graduate g("liuneng", 2001, 'x', 2000);
//	g.dump();
//	cout << "----------------" << endl;
//	Doctor d("qiuxiang", 2001, 'y', 3000, "doctor", 2001, 8, 16);
//	d.disdump();
//	return 0;
//}











