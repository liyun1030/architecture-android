#include <iostream>
#include <memory>
#include <string>
#include <unordered_map>
#include <functional>
#include <stdexcept>

using namespace std;
// ֧����ʽ����ӿ�
class PaymentMethod {
public:
    virtual void pay(double amount) = 0;
    virtual ~PaymentMethod() = default;
};

// ����֧����ʽʵ��
class Alipay : public PaymentMethod {
public:
    void pay(double amount) override {
        std::cout << "֧����֧��: " << amount << "Ԫ" << std::endl;
    }
};

class WechatPay : public PaymentMethod {
public:
    void pay(double amount) override {
        std::cout << "΢��֧��: " << amount << "Ԫ" << std::endl;
    }
};

class UnionPay : public PaymentMethod {
public:
    void pay(double amount) override {
        std::cout << "����֧��: " << amount << "Ԫ" << std::endl;
    }
};

// ����������
class OrderService {
private:
    std::unique_ptr<PaymentMethod> paymentMethod;

public:
    explicit OrderService(std::unique_ptr<PaymentMethod> method)
        : paymentMethod(std::move(method)) {
    }

    void setPaymentMethod(std::unique_ptr<PaymentMethod> method) {
        paymentMethod = std::move(method);
    }

    void processOrder(double amount) {
        if (!paymentMethod) {
            throw std::runtime_error("δ����֧����ʽ");
        }
        paymentMethod->pay(amount);
    }
};

// ֧��������
class PaymentManager {
private:
    std::unordered_map<std::string, std::function<std::unique_ptr<PaymentMethod>()>> factories;
    std::string currentPaymentType;

public:
    // ע��֧����ʽ
    void registerPaymentType(const string& type,
        function<unique_ptr<PaymentMethod>()> factory) {// ��������
        factories[type] = factory;// �����ϣ��
    }

    // ���õ�ǰ֧����ʽ
    void setCurrentPaymentType(const std::string& type) {
        if (factories.find(type) == factories.end()) {
            throw std::invalid_argument("��֧�ֵ�֧����ʽ: " + type);
        }
        currentPaymentType = type;
    }

    // ����֧����ʽʵ��
    std::unique_ptr<PaymentMethod> createPaymentMethod() const {
        auto it = factories.find(currentPaymentType);
        if (it != factories.end()) {
            return it->second();
        }
        throw std::invalid_argument("δ������Ч��֧����ʽ");
    }

    // ��ȡ����֧�ֵ�֧����ʽ
    std::vector<std::string> getSupportedPaymentTypes() const {
        std::vector<std::string> types;
        for (const auto& pair : factories) {
            types.push_back(pair.first);
        }
        return types;
    }
};

//int main() {
//    // ����֧��������
//    PaymentManager paymentManager;
//
//    // ע������֧�ֵ�֧����ʽ
//    paymentManager.registerPaymentType("alipay", [] { return std::make_unique<Alipay>(); });
//    paymentManager.registerPaymentType("wechat", [] { return std::make_unique<WechatPay>(); });
//    paymentManager.registerPaymentType("unionpay", [] { return std::make_unique<UnionPay>(); });
//
//    // ��ӡ֧�ֵ�֧����ʽ
//    std::cout << "֧�ֵ�֧����ʽ: ";
//    for (const auto& type : paymentManager.getSupportedPaymentTypes()) {
//        std::cout << type << " ";
//    }
//    std::cout << std::endl;
//
//    // �����������񣬳�ʼʹ��֧����
//    paymentManager.setCurrentPaymentType("alipay");
//    OrderService orderService(paymentManager.createPaymentMethod());
//
//    // ������
//    orderService.processOrder(99.9);  // ֧����֧��
//
//    // ��̬�л�Ϊ΢��֧��
//    paymentManager.setCurrentPaymentType("wechat");
//    orderService.setPaymentMethod(paymentManager.createPaymentMethod());
//    orderService.processOrder(199.9);  // ΢��֧��
//
//    // ��̬�л�Ϊ����֧��
//    paymentManager.setCurrentPaymentType("unionpay");
//    orderService.setPaymentMethod(paymentManager.createPaymentMethod());
//    orderService.processOrder(299.9);  // ����֧��
//
//    return 0;
//}