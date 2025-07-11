#include "TestHerit2.h"
StudentH::StudentH(string sn, int n, char s)
	:name(sn), num(n), sex(s)
{
}
StudentH::~StudentH()
{
}
void StudentH::dis()
{
	
	cout << name << endl;
	cout << num << endl;
	cout << sex << endl;
}
StudentH::StudentH(const StudentH& another)
{
	name = another.name;
	num = another.num;
	sex = another.sex;
}
GraduateH::GraduateH(string sn, int in, char cs, float fs)
	:StudentH(sn, in, cs), salary(fs)
{
}
GraduateH::~GraduateH()
{
}
GraduateH::GraduateH(const GraduateH& another)
	:StudentH(another), salary(another.salary)
{
}

//int main()
//{
//	GraduateH g("liuneng", 2001, 'x', 2000);
//	g.dump();
//	GraduateH gg = g;
//	gg.dump();
//	cout << typeid(123).name() << endl;
//	cout << typeid(g).name() << endl;
//	return 0;
//}