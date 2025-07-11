#include <iostream>
using namespace std;

class StudentH
{
public:
	StudentH(string sn, int n, char s);
	StudentH(const StudentH& another);
	~StudentH();
	void dis();
private:
	string name;
	int num;
	char sex;
};

class GraduateH :public StudentH
{
public:
	GraduateH(string sn, int in, char cs, float fs);
	~GraduateH();
	GraduateH(const GraduateH& another);
	void dump()
	{
		dis();
		cout << salary << endl;
	}
private:
	float salary;
};