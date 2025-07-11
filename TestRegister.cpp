#include <iostream>
#include <memory>
#include <string>
#include <unordered_map>
#include <functional>
#include <stdexcept>

using namespace std;
// 支付方式抽象接口
class PaymentMethod {
public:
    virtual void pay(double amount) = 0;
    virtual ~PaymentMethod() = default;
};

// 具体支付方式实现
class Alipay : public PaymentMethod {
public:
    void pay(double amount) override {
        std::cout << "支付宝支付: " << amount << "元" << std::endl;
    }
};

class WechatPay : public PaymentMethod {
public:
    void pay(double amount) override {
        std::cout << "微信支付: " << amount << "元" << std::endl;
    }
};

class UnionPay : public PaymentMethod {
public:
    void pay(double amount) override {
        std::cout << "银联支付: " << amount << "元" << std::endl;
    }
};

// 订单服务类
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
            throw std::runtime_error("未设置支付方式");
        }
        paymentMethod->pay(amount);
    }
};

// 支付管理器
class PaymentManager {
private:
    std::unordered_map<std::string, std::function<std::unique_ptr<PaymentMethod>()>> factories;
    std::string currentPaymentType;

public:
    // 注册支付方式
    void registerPaymentType(const string& type,
        function<unique_ptr<PaymentMethod>()> factory) {// 工厂函数
        factories[type] = factory;// 存入哈希表
    }

    // 设置当前支付方式
    void setCurrentPaymentType(const std::string& type) {
        if (factories.find(type) == factories.end()) {
            throw std::invalid_argument("不支持的支付方式: " + type);
        }
        currentPaymentType = type;
    }

    // 创建支付方式实例
    std::unique_ptr<PaymentMethod> createPaymentMethod() const {
        auto it = factories.find(currentPaymentType);
        if (it != factories.end()) {
            return it->second();
        }
        throw std::invalid_argument("未设置有效的支付方式");
    }

    // 获取所有支持的支付方式
    std::vector<std::string> getSupportedPaymentTypes() const {
        std::vector<std::string> types;
        for (const auto& pair : factories) {
            types.push_back(pair.first);
        }
        return types;
    }
};

//int main() {
//    // 创建支付管理器
//    PaymentManager paymentManager;
//
//    // 注册所有支持的支付方式
//    paymentManager.registerPaymentType("alipay", [] { return std::make_unique<Alipay>(); });
//    paymentManager.registerPaymentType("wechat", [] { return std::make_unique<WechatPay>(); });
//    paymentManager.registerPaymentType("unionpay", [] { return std::make_unique<UnionPay>(); });
//
//    // 打印支持的支付方式
//    std::cout << "支持的支付方式: ";
//    for (const auto& type : paymentManager.getSupportedPaymentTypes()) {
//        std::cout << type << " ";
//    }
//    std::cout << std::endl;
//
//    // 创建订单服务，初始使用支付宝
//    paymentManager.setCurrentPaymentType("alipay");
//    OrderService orderService(paymentManager.createPaymentMethod());
//
//    // 处理订单
//    orderService.processOrder(99.9);  // 支付宝支付
//
//    // 动态切换为微信支付
//    paymentManager.setCurrentPaymentType("wechat");
//    orderService.setPaymentMethod(paymentManager.createPaymentMethod());
//    orderService.processOrder(199.9);  // 微信支付
//
//    // 动态切换为银联支付
//    paymentManager.setCurrentPaymentType("unionpay");
//    orderService.setPaymentMethod(paymentManager.createPaymentMethod());
//    orderService.processOrder(299.9);  // 银联支付
//
//    return 0;
//}