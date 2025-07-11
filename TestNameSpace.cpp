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
			ab = std::make_unique<int>(*other.ab);  // 重置指针，确保深拷贝
		}
		return *this;
	}
	// 公有访问器
	int getValue() const { return *ab; }
	void setValue(int val) { *ab = val; }
};
void testTime() {
	time_t t = time(NULL);
	struct tm local;

	// 使用 localtime_s() 替代 localtime()
	localtime_s(&local, &t);  // Windows 安全版本

	int hour = local.tm_hour;
	int minute = local.tm_min;
	int second = local.tm_sec;
	int year = local.tm_year;
	int month = local.tm_mon;
	int day = local.tm_mday;
	std::cout << "当前时间: "
		<< 1900+year<<":"
		<< month + 1 << ":"  // tm_mon 从0开始，所以需要加1
		<< day << " "
		<< hour << ":"
		<< minute << ":"
		<< second << std::endl;
	char buf[15];
	strftime(buf, sizeof(buf), "%Y-%m-%d", &local);
	string buff = string(buf);
	std::cout << "当前时间2: "<< buff << std::endl;

	std::ostringstream oss;
	oss << std::setfill('0')
		<< std::setw(4) << year << "-"
		<< std::setw(2) << month + 1 << "-"  // tm_mon 范围是 0-11，需 +1
		<< std::setw(2) << day;
	string tmp= oss.str();
	std::cout << "当前时间3: " << buff << std::endl;

	snprintf(buf, sizeof(buf), "%04d-%02d-%02d",
		year, month + 1, day);
	string prinf= string(buf);
	std::cout << "当前时间4: " << buff << std::endl;
}


//int main() {
//	//test11();
//	//test12();
//	//AA a(33);
//	//AA b = a; // 调用拷贝构造函数
//	//b.setValue(99);
//	//std::cout << a.getValue() << ", " << b.getValue() << std::endl;  // 使用公共方法获取值
//	testTime();
//	return 0;
//}

