#include "TestYiNaiDaoZhi.h"
// 高层模块：通过接口依赖支付方式
class OrderService {
private:
    std::unique_ptr<PaymentMethod> paymentMethod;  // 依赖抽象接口

public:
    // 通过构造函数注入依赖（依赖注入）
    explicit OrderService(std::unique_ptr<PaymentMethod> method)
        : paymentMethod(std::move(method)) {
    }

    void processOrder(double amount) {
        paymentMethod->pay(amount);  // 通过接口调用，不关心具体实现
    }

    void setPaymentMethod(std::unique_ptr<PaymentMethod> method) {
        paymentMethod = std::move(method);  // 可以在运行时切换支付方式
	}
};

//// 使用示例
//int main() {
//    // 客户端决定具体实现
//    auto payment = std::make_unique<Alipay>();
//    OrderService orderService(std::move(payment));
//
//    orderService.processOrder(99.9);  // 输出：使用支付宝支付: 99.9元
//
//    // 若要切换支付方式，只需修改客户端代码
//    auto wechatPayment = std::make_unique<WechatPay>();
//    orderService.setPaymentMethod(std::move(wechatPayment));
//
//    orderService.processOrder(199.9);  // 输出：使用微信支付: 199.9元
//
//
//   
//    return 0;
//}