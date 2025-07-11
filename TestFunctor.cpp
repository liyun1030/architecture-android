#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
class Pow
{
public:
	int operator()(int i)
	{
		return i * i;
	}
	double operator ()(double d)
	{
		return d * d;
	}
};
void testFunc()
{
	Pow pow;
	int i = pow(4); //pow.opreator()(4);
	double d = pow(5.5);
	cout << i << endl;
	cout << d << endl;
}
struct Counter {
	int target;
	int count;

	Counter(int t) : target(t), count(0) {}

	void operator()(int x) {
		if (x == target) ++count;
	}
};
void print(int n) {
	std::cout << n << " ";
}

//int main() {
//	std::vector<int> v = { 1, 2, 3, 2, 4, 2 };
//	std::for_each(v.begin(), v.end(), print);  // Êä³ö£º1 2 3 4 5
//	std::cout << endl;
//	Counter counter = std::for_each(v.begin(), v.end(), Counter(2));
//	std::cout << "Number of 2s: " << counter.count << std::endl; // Êä³ö 3
//	return 0;
//}
