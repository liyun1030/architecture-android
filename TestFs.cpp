#include <fstream>
#include <iostream>
#include <string>
using namespace std;


void testMethod1() {
    //ifstream ifs("readme.txt");
    //string line;  // 使用 string 动态存储一行内容

    //// 正确写法：直接传入 string 对象，无需指定长度
    //while (getline(ifs, line)) {  // 注意这里是全局函数 getline，不是 ifs.getline
    //    cout << "Read: " << line << endl;  // 输出 line，而非未定义的 buf
    //}

    ifstream ifs("readme.txt");
    char buf[100];  // 固定长度的 char 数组

    // 使用 ifs.getline 读取到 char 数组，并指定长度
    while (ifs.getline(buf, sizeof(buf))) {
        cout << "Read: " << buf << endl;  // 输出 buf
    }
}
void testMethod2() {
    ifstream ifs("readme.txt");
    if (!ifs.is_open()) {
        cerr << "文件打开失败" << endl;
        return ;
    }

    char buf[100];
    //bool /*isFirstLine*/ = true;  // 标记是否为第一行

    while (true) {
        // 读取当前行的一部分（最多99字符）
        ifs.getline(buf, sizeof(buf));

        // 输出读取的内容（第一行前不加换行，后续行前加换行分隔）
        //if (!isFirstLine) {
        //    cout << endl;  // 第二行及以后，先输出换行再输出内容
        //}
        cout << buf;
        /*isFirstLine = false;*/  // 第一行处理完后标记为false

        // 判断读取状态
        if (ifs.eof()) {  // 到达文件尾（所有内容读完）
            cout << endl;  // 最后一行结束后换行
            break;
        }
        else if (ifs.fail()) {  // 因缓冲区满导致读取失败（未读完当前行）
            ifs.clear();  // 清除错误状态，继续读取当前行剩余部分
            //cout << " [截断]";  // 提示截断
        }
        else {  // 正常读完一行（未到文件尾）
            // 不break，继续循环读取下一行
            cout << endl;  // 换行，准备读取下一行
        }
    }

    ifs.close();
}
//int main() {
   
    //testMethod1();
	 //testMethod2();   
    // 读取文件
    //ifstream ifs("readme.txt", ios::in);
    //char buf[100];

    ////// 循环读取所有行
    //while (true) {
    //    // 读取一行或部分内容
    //    ifs.getline(buf, sizeof(buf));

    //    // 输出当前读取的内容（不换行，因为可能未读完一行）
    //    cout << buf;

    //    // 判断是否因缓冲区满导致读取失败（且未到文件尾）
    //    if (ifs.fail() && !ifs.eof()) {
    //        ifs.clear();  // 清除错误状态
    //        cout << " [截断]";  // 提示用户此行被截断
    //    }
    //    else {
    //        cout << endl;  // 完整读取一行后换行
    //        break;
    //    }
    //}
   
    //ifs.getline(buf, sizeof(buf));  // 使用 getline() 读取一行
    //cout << "Read: " << buf << endl;
    //ifs.close();

    // 读写混合
    //fstream fs("readme.txt", ios::in | ios::out);
    //fs.seekg(0, ios::beg);  // 移动读指针到开头
    //fs >> buf;  // 读取第一个单词
    //cout << "First word: " << buf << endl;

    //fs.seekp(0, ios::end);  // 移动写指针到末尾
    //fs << "Appended text.";  // 追加内容
    //fs.close();

    //return 0;
//}