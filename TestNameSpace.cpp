#include <string>;
#include <iostream>;
#include <cstdlib>;
#include <cstring> ;
#include <iostream>;
#include <time.h>;
#include <chrono>;
#include <iomanip>;
#include <sstream>;
using namespace std;
void test11() {
	string sArray[10] = {
		"0",
		"1",
		"22",
		"333",
		"4444",
		"55555",
		"666666",
		"7777777",
		"88888888",
		"999999999",
	};
	for (int i = 0; i < 10; i++)
	{
		cout << sArray[i] << endl;
	}
	sArray->erase(0, 1);
	int pos = 0;
	for (int i = 0; i < 10; i++)
	{
		cout << sArray[i] << endl;
		if (sArray[i] == "333") {
			pos = i;
		}
	}
	cout << "Found 333 at position: " << pos << endl;
}
struct Date
{
	int year;
	int month;
	int day;
};
	void init(Date & d)
	{
		cout << "year,month,day:" << endl;
		cin >> d.year >> d.month >> d.day;
	}
	void print(Date & d)
	{
		cout << "year month day" << endl;
		cout << d.year << ":" << d.month << ":" << d.day << endl;
	}
	bool isLeapYear(Date & d)
	{
		if ((d.year % 4 == 0 && d.year % 100 != 0) || d.year % 400 == 0)
			return true;
		else
			return false;
	}
struct Student
{
	char* name;
	int age;
};
void test12() {
	Student stu;
	stu.name = (char*)malloc(100);
	strcpy_s(stu.name, 100,"hehe");
	stu.age = 100;
	free(stu.name);
	Student* ps = new Student;
	ps->name = (char*)malloc(100);
	strcpy_s(ps->name, 100,"bob");
	ps->age = 23;
	free(ps->name);
	free(ps);
}
class AA {
private:
	std::unique_ptr<int> ab;
public:
	AA(int val) :ab(std::make_unique<int>(val)) {}
	AA(const AA& other):ab(std::make_unique<int>(*other.ab)) {}
	AA& operator=(const AA& other) {
		if (this != &other) {
			ab = std::make_unique<int>(*other.ab);  // ����ָ�룬ȷ�����
		}
		return *this;
	}
	// ���з�����
	int getValue() const { return *ab; }
	void setValue(int val) { *ab = val; }
};
void testTime() {
	time_t t = time(NULL);
	struct tm local;

	// ʹ�� localtime_s() ��� localtime()
	localtime_s(&local, &t);  // Windows ��ȫ�汾

	int hour = local.tm_hour;
	int minute = local.tm_min;
	int second = local.tm_sec;
	int year = local.tm_year;
	int month = local.tm_mon;
	int day = local.tm_mday;
	std::cout << "��ǰʱ��: "
		<< 1900+year<<":"
		<< month + 1 << ":"  // tm_mon ��0��ʼ��������Ҫ��1
		<< day << " "
		<< hour << ":"
		<< minute << ":"
		<< second << std::endl;
	char buf[15];
	strftime(buf, sizeof(buf), "%Y-%m-%d", &local);
	string buff = string(buf);
	std::cout << "��ǰʱ��2: "<< buff << std::endl;

	std::ostringstream oss;
	oss << std::setfill('0')
		<< std::setw(4) << year << "-"
		<< std::setw(2) << month + 1 << "-"  // tm_mon ��Χ�� 0-11���� +1
		<< std::setw(2) << day;
	string tmp= oss.str();
	std::cout << "��ǰʱ��3: " << buff << std::endl;

	snprintf(buf, sizeof(buf), "%04d-%02d-%02d",
		year, month + 1, day);
	string prinf= string(buf);
	std::cout << "��ǰʱ��4: " << buff << std::endl;
}


//int main() {
//	//test11();
//	//test12();
//	//AA a(33);
//	//AA b = a; // ���ÿ������캯��
//	//b.setValue(99);
//	//std::cout << a.getValue() << ", " << b.getValue() << std::endl;  // ʹ�ù���������ȡֵ
//	testTime();
//	return 0;
//}

