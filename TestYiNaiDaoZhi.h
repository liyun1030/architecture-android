#pragma once
#include <iostream>
using namespace std;
class PaymentMethod {
public:
    virtual void pay(double amount) = 0;
    virtual ~PaymentMethod() = default;
};
// 低层模块：具体支付方式实现抽象接口
class Alipay : public PaymentMethod {
public:
    void pay(double amount) override {
        cout << "使用支付宝支付: " << amount << "元" << endl;
    }
};

class WechatPay : public PaymentMethod {
public:
    void pay(double amount) override {
        cout << "使用微信支付: " << amount << "元" << endl;
    }
};

