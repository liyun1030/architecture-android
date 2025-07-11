#pragma once
#include <iostream>
using namespace std;
class PaymentMethod {
public:
    virtual void pay(double amount) = 0;
    virtual ~PaymentMethod() = default;
};
// �Ͳ�ģ�飺����֧����ʽʵ�ֳ���ӿ�
class Alipay : public PaymentMethod {
public:
    void pay(double amount) override {
        cout << "ʹ��֧����֧��: " << amount << "Ԫ" << endl;
    }
};

class WechatPay : public PaymentMethod {
public:
    void pay(double amount) override {
        cout << "ʹ��΢��֧��: " << amount << "Ԫ" << endl;
    }
};

