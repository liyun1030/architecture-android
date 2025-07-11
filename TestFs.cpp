#include <fstream>
#include <iostream>
#include <string>
using namespace std;


void testMethod1() {
    //ifstream ifs("readme.txt");
    //string line;  // ʹ�� string ��̬�洢һ������

    //// ��ȷд����ֱ�Ӵ��� string ��������ָ������
    //while (getline(ifs, line)) {  // ע��������ȫ�ֺ��� getline������ ifs.getline
    //    cout << "Read: " << line << endl;  // ��� line������δ����� buf
    //}

    ifstream ifs("readme.txt");
    char buf[100];  // �̶����ȵ� char ����

    // ʹ�� ifs.getline ��ȡ�� char ���飬��ָ������
    while (ifs.getline(buf, sizeof(buf))) {
        cout << "Read: " << buf << endl;  // ��� buf
    }
}
void testMethod2() {
    ifstream ifs("readme.txt");
    if (!ifs.is_open()) {
        cerr << "�ļ���ʧ��" << endl;
        return ;
    }

    char buf[100];
    //bool /*isFirstLine*/ = true;  // ����Ƿ�Ϊ��һ��

    while (true) {
        // ��ȡ��ǰ�е�һ���֣����99�ַ���
        ifs.getline(buf, sizeof(buf));

        // �����ȡ�����ݣ���һ��ǰ���ӻ��У�������ǰ�ӻ��зָ���
        //if (!isFirstLine) {
        //    cout << endl;  // �ڶ��м��Ժ�������������������
        //}
        cout << buf;
        /*isFirstLine = false;*/  // ��һ�д��������Ϊfalse

        // �ж϶�ȡ״̬
        if (ifs.eof()) {  // �����ļ�β���������ݶ��꣩
            cout << endl;  // ���һ�н�������
            break;
        }
        else if (ifs.fail()) {  // �򻺳��������¶�ȡʧ�ܣ�δ���굱ǰ�У�
            ifs.clear();  // �������״̬��������ȡ��ǰ��ʣ�ಿ��
            //cout << " [�ض�]";  // ��ʾ�ض�
        }
        else {  // ��������һ�У�δ���ļ�β��
            // ��break������ѭ����ȡ��һ��
            cout << endl;  // ���У�׼����ȡ��һ��
        }
    }

    ifs.close();
}
//int main() {
   
    //testMethod1();
	 //testMethod2();   
    // ��ȡ�ļ�
    //ifstream ifs("readme.txt", ios::in);
    //char buf[100];

    ////// ѭ����ȡ������
    //while (true) {
    //    // ��ȡһ�л򲿷�����
    //    ifs.getline(buf, sizeof(buf));

    //    // �����ǰ��ȡ�����ݣ������У���Ϊ����δ����һ�У�
    //    cout << buf;

    //    // �ж��Ƿ��򻺳��������¶�ȡʧ�ܣ���δ���ļ�β��
    //    if (ifs.fail() && !ifs.eof()) {
    //        ifs.clear();  // �������״̬
    //        cout << " [�ض�]";  // ��ʾ�û����б��ض�
    //    }
    //    else {
    //        cout << endl;  // ������ȡһ�к���
    //        break;
    //    }
    //}
   
    //ifs.getline(buf, sizeof(buf));  // ʹ�� getline() ��ȡһ��
    //cout << "Read: " << buf << endl;
    //ifs.close();

    // ��д���
    //fstream fs("readme.txt", ios::in | ios::out);
    //fs.seekg(0, ios::beg);  // �ƶ���ָ�뵽��ͷ
    //fs >> buf;  // ��ȡ��һ������
    //cout << "First word: " << buf << endl;

    //fs.seekp(0, ios::end);  // �ƶ�дָ�뵽ĩβ
    //fs << "Appended text.";  // ׷������
    //fs.close();

    //return 0;
//}