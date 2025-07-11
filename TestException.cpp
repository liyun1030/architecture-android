#include <iostream> 
using namespace std;    

void divide(int a, int b) {
    if (b == 0) {
        throw runtime_error("除数不能为零");  // 抛出标准异常
    }
    cout << "结果: " << a / b << endl;
}
class MyException : public exception {
public:
    const char* what() const noexcept override {
        return "自定义异常";
    }
};

// 使用自定义异常
void func() {
    throw MyException();
}

void func1() {
    throw runtime_error("异常");
}

void func2() {
    func1();  // 不处理异常，继续向上传递
}
void funcc() noexcept {
    // 此函数不会抛出异常
}
//int main() {
//    try {
//        func2();
//    }
//    catch (const runtime_error& e) {
//        cout << e.what() << endl;  // 在 main 中捕获
//    }
//
//
//    try {
//        divide(10, 0);  // 调用可能抛出异常的函数
//    }
//    catch (...) {//const runtime_error& e
//		cout << "捕获到所有异常" << endl;  // 捕获所有异常
//        //cout << "错误: " << e.what() << endl;  // 输出错误信息
//    }
//
//    // 捕获
//    try {
//        func();
//    }
//    catch (const MyException& e) {
//        cout << e.what() << endl;  // 输出: "自定义异常"
//    }
//
//    try {
//        throw runtime_error("错误");
//    }
//    catch (const runtime_error& e) {  // 永远不会被执行
//        cout << "捕获子类异常" << endl;
//    }
//    catch (const exception& e) {  // 先捕获基类，导致下面的 catch 永远不会执行
//        cout << "捕获基类异常" << endl;
//    }
//
//    try {
//        // 可能抛出任何异常的代码
//    }
//    catch (...) {
//        cout << "捕获到未知异常" << endl;
//        // 无法获取异常对象的具体信息
//    }
//    //try {
//    //    vector<int> vec(5);
//    //    vec.at(10) = 100;  // 越界访问，抛出 out_of_range
//    //}
//    //catch (constt out_of_range& e) {
//    //    cout << e.what() << endl;  // 输出: "vector::_M_range_check: __n (which is 10) >= this->size() (which is 5)"
//    //}
//
//	return 0;
//}