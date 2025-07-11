/**
* ÒþÊ½Á´±í²Ù×÷
*/

#include <iostream>
#include <string>
using namespace std;
class Student1
{
public:
	Student1(string n)
		:name(n)
	{
		if (head == NULL)
		{
			head = this;
			this->next = NULL;
		}
		else {
			this->next = head;
			head = this;
		}
	}
	static void printStudentList();
	static void deleteStudentList();
private:
	string name;
	Student1* next;
	static Student1* head;
};
void Student1::printStudentList()
{
	Student1* p = head;
	while (p != NULL)
	{
		cout << p->name << endl;
		p = p->next;
	}
}
void Student1::deleteStudentList()
{
	Student1* p = head;

	while (head)
	{
		head = head->next;
		delete p;
		p = head;
	}
}
Student1* Student1::head = NULL;
//int main()
//{
//	string name;
//	string postName;
//	char buf[1024];
//	for (int i = 0; i < 10; i++)
//	{
//		name = "stu";
//		postName = to_string(i);
//		name += postName;
//		new Student1(name);
//	}
//	Student1::printStudentList();
//	//Student1::deleteStudentList();
//	return 0;
//}
