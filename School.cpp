#include <iostream>
using namespace std;
class School
{
public:
	static void addLibBooks(string book)
	{
		lib += book;
	}
public:
	string tower;
	string lake;
	static string lib;
};
//����ʵʼ��
string School::lib = "Beijing lib:";


//int main()
//{
//	School a, b, c, d;
//	//static ���ݳ�Ա�������࣬�������ڶ���
//	//�洢�� data ���� rw ��
//	cout << sizeof(a) << sizeof(b) << sizeof(c) << sizeof(d) << endl;
//	//��ķ�ʽ����,���������⣬����Ҫ��ʼ����
//	//���޶������ɵ�ʱ������Է��ʡ�
//	School::lib = "China lib:";
//	cout << School::lib << endl;
//	//lib ��Ȼ�����࣬����Ŀ����Ϊ��ʵ��������Ĺ���
//	//�ʶ���Ҳ�ǿ��Է��ʵġ�
//	cout << a.lib << endl;
//	cout << b.lib << endl;
//	//Ϊ�˸��ͼ��ݵĽ��裬���� static �ӿ�
//	School::addLibBooks("mao xuan");
//	cout << School::lib << endl;
//	return 0;
//}
