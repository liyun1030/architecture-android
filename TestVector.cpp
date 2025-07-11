#ifndef MYVECTOR_HPP
#define MYVECTOR_HPP
#include <iostream>
using namespace std;
template <typename T> class MyVector;
template <typename T> ostream& operator<<(ostream& out, const MyVector<T>& obj);
template <typename T>
class MyVector
{
public:
	MyVector(int size = 0);
	MyVector(const MyVector<T>& obj);
	MyVector<T>& operator=(MyVector<T>& obj);
	~MyVector();
	T& operator[] (int index);
	int getSize();
	friend ostream& operator<< <T>(ostream& out, const MyVector<T>& obj);
protected:
	T* m_space;
	int m_len;
};
template <typename T>
int MyVector<T>::getSize()
{
	return m_len;
}
template <typename T>
ostream& operator<< (ostream& out, const MyVector<T>& obj)
{
	for (int i = 0; i < obj.m_len; i++)
	{
		out << obj.m_space[i] << " ";
	}
	out << endl;
	return out;
}
template <typename T>
MyVector<T>::MyVector(int size)
{
	m_space = new T[size];
	m_len = size;
}
template <typename T>
MyVector<T>::MyVector(const MyVector& obj)
{
	m_len = obj.m_len;
	m_space = new T[m_len];
	for (int i = 0; i < m_len; i++)
	{
		m_space[i] = obj.m_space[i];
	}
}
template <typename T>
MyVector<T>::~MyVector() //Îö¹¹º¯Êý
{
	if (m_space != NULL)
	{
		delete[] m_space;
		m_space = NULL;
		m_len = 0;
	}
}
template <typename T>
T& MyVector<T>::operator[] (int index)
{
	return m_space[index];
}
template <typename T>
MyVector<T>& MyVector<T>::operator=(MyVector<T>& obj)
{
	delete[] m_space;
	m_space = NULL;
	m_len = 0;
	m_len = obj.m_len;
	m_space = new T[m_len];
	for (int i = 0; i < m_len; i++)
	{
		m_space[i] = obj[i];
	}
	return *this;
}
#endif


//int main()
//{
//	MyVector<int> a(10);
//	for (int i = 0; i < 10; i++)
//	{
//		a[i] = i + 100;
//	}
//	MyVector<int> b(a);
//	cout << b[2] << b[1] << endl;
//	MyVector<int> c;
//	c = a;
//	cout << b[2] << b[1] << endl;
//	cout << a;
//	return 0;
//}