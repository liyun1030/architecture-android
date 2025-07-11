#include <iostream>
#include <typeinfo>
using namespace std;
class A
{
public:
	virtual ~A() {}
};
class B :public A
{
};
class C :public A
{
};
class D
{
};
// Ŀ�꺯�����޲��������� void��
void sayHello() {
	std::cout << "Hello!" << std::endl;
}
typedef void (*Func)();
// ��������ָ����������ַ�ʽ��
Func ptr1 = &sayHello;  // ȡ��ַ�� & ��ʡ��
Func ptr2 = sayHello;   // ֱ�Ӹ�ֵ���������Զ�ת��Ϊָ�룩

#include <functional>

// ���� std::function ����
void execute(std::function<void()> callback) {
	callback();
}


void testPtr() {
	// ���ú��������ַ�ʽ��
	ptr1();  // ���: Hello!
	(*ptr2)();  // �ȼ�д������ʽ�����ã�
	execute(ptr1);  // ʹ�� std::function
}
template <typename T>
void Swap(T& a, T& b)
{
	T t = a;
	a = b;
	b = t;
}

//int main()
//{
//	//testPtr();
//	int ia = 10; int ib = 20;
//	Swap<int>(ia,ib);
//	//B b;
//	//A* pa = &b;
//	//B* pb = dynamic_cast<B*>(pa); //�ɹ�
//	//cout << "b:" << &b << endl;
//	//cout << "pa:" << pa << endl;
//	//cout <<"pb:"<< pb << endl;
//	//C* pc = dynamic_cast<C*>(pa); //���ɹ� ��ȫ
//	//cout << "pc:" << pc << endl;
//	//D* pd = dynamic_cast<D*>(pa); //���ɹ� ��ȫ
//	//cout << "pd:" << pd << endl;
//	//pb = static_cast<B*>(pa); //�ɹ�
//	//cout << "pb2:" << pb << endl;
//	//pc = static_cast<C*>(pa); //�ɹ� ����ȫ
//	//cout << "pc2:" << pc << endl;
//	//// pd = static_cast<D*>(pa); //���� ���ɹ�
//	//// cout<<pd<<endl;
//	//pb = reinterpret_cast<B*>(pa); //�ɹ� ����ȫ
//	//cout << "pb3:" << pb << endl;
//	//pc = reinterpret_cast<C*>(pa); //�ɹ� ����ȫ
//	//cout << "pc3:" << pc << endl;
//	//pd = reinterpret_cast<D*>(pa); //�ɹ� ����ȫ
//	//cout << "pd3:" << pd << endl;
//	return 0;
//}