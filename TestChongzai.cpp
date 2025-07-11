#include <iostream>

using namespace std;
class A
{
public:
	A()
	{
		cout << "A constructor" << endl;
	}
	~A()
	{
		cout << "A destructor" << endl;
	}
	void* operator new (size_t size)
	{
		cout << "new " << size << endl;
		void* p = malloc(size); // ((A*)p)->a = 100;
		return p;
	}
	void operator delete(void* p)
	{
		cout << "delete" << endl;
		free(p);
	}
	void* operator new[](size_t size)
	{
		cout << "new[] " << size << endl;
		return malloc(size);
	}
	void operator delete[](void* p)
	{
		cout << "delete[] " << endl;
		free(p);
	}
private:
	int a;
};
//int main()
//{
//	// int *p = new int;
//	// delete p;
//	// int *pa = new int[20];
//	// delete []pa;
//	A* cp = new A;
//	delete cp;
//	A* cpa = new A[20];
//	delete[]cpa;
//	return 0;
//}