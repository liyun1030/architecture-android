#include <iostream> 
using namespace std;    

void divide(int a, int b) {
    if (b == 0) {
        throw runtime_error("��������Ϊ��");  // �׳���׼�쳣
    }
    cout << "���: " << a / b << endl;
}
class MyException : public exception {
public:
    const char* what() const noexcept override {
        return "�Զ����쳣";
    }
};

// ʹ���Զ����쳣
void func() {
    throw MyException();
}

void func1() {
    throw runtime_error("�쳣");
}

void func2() {
    func1();  // �������쳣���������ϴ���
}
void funcc() noexcept {
    // �˺��������׳��쳣
}
//int main() {
//    try {
//        func2();
//    }
//    catch (const runtime_error& e) {
//        cout << e.what() << endl;  // �� main �в���
//    }
//
//
//    try {
//        divide(10, 0);  // ���ÿ����׳��쳣�ĺ���
//    }
//    catch (...) {//const runtime_error& e
//		cout << "���������쳣" << endl;  // ���������쳣
//        //cout << "����: " << e.what() << endl;  // ���������Ϣ
//    }
//
//    // ����
//    try {
//        func();
//    }
//    catch (const MyException& e) {
//        cout << e.what() << endl;  // ���: "�Զ����쳣"
//    }
//
//    try {
//        throw runtime_error("����");
//    }
//    catch (const runtime_error& e) {  // ��Զ���ᱻִ��
//        cout << "���������쳣" << endl;
//    }
//    catch (const exception& e) {  // �Ȳ�����࣬��������� catch ��Զ����ִ��
//        cout << "��������쳣" << endl;
//    }
//
//    try {
//        // �����׳��κ��쳣�Ĵ���
//    }
//    catch (...) {
//        cout << "����δ֪�쳣" << endl;
//        // �޷���ȡ�쳣����ľ�����Ϣ
//    }
//    //try {
//    //    vector<int> vec(5);
//    //    vec.at(10) = 100;  // Խ����ʣ��׳� out_of_range
//    //}
//    //catch (constt out_of_range& e) {
//    //    cout << e.what() << endl;  // ���: "vector::_M_range_check: __n (which is 10) >= this->size() (which is 5)"
//    //}
//
//	return 0;
//}