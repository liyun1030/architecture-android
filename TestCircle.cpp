#include <iostream>

using namespace std;

class circle {
	private:
		double radius;
		double height;	
		static double pi;

	public:
		circle(double r, double h) : radius(r), height(h) {
			//���캯��
		}
		void printZhouchang() {
			//�ܳ�
			cout <<"�ܳ�:"<< 2 * pi * radius << " ";
		}
		void printMianji() {
			//���
			cout << "���:" << pi * radius * radius << " ";
		}
		void printBiaoMianJi() {  
			// Բ������
			cout << "Բ������:" << 4 * pi * radius * radius << " ";
		}

		void printTiJi() {  
			// Բ�����
			cout << "Բ�����:" << pi * radius * radius * height << endl;
		}
};
double circle::pi = 3.14;  // ��̬��Ա������ʼ��
//int main() {
//	double r;
//	double h;
//	cin >> r >> h;  // ����뾶�͸߶�
//	circle c(r,h);
//	void (circle:: * pp)() = &circle::printZhouchang;  // ָ���Ա������ָ��
//	(c.*pp)();  // ���ó�Ա����
//	pp = &circle::printMianji;  // �ı�ָ��ָ��
//	(c.*pp)();  // ���ó�Ա����
//	pp = &circle::printBiaoMianJi;  // �ı�ָ��ָ��
//	(c.*pp)();  // ���ó�Ա����
//	pp = &circle::printTiJi;  // �ı�ָ��ָ��
//	(c.*pp)();  // ���ó�Ա����
//	return 0;
//}