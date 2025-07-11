#include "TestYiNaiDaoZhi.h"
// �߲�ģ�飺ͨ���ӿ�����֧����ʽ
class OrderService {
private:
    std::unique_ptr<PaymentMethod> paymentMethod;  // ��������ӿ�

public:
    // ͨ�����캯��ע������������ע�룩
    explicit OrderService(std::unique_ptr<PaymentMethod> method)
        : paymentMethod(std::move(method)) {
    }

    void processOrder(double amount) {
        paymentMethod->pay(amount);  // ͨ���ӿڵ��ã������ľ���ʵ��
    }

    void setPaymentMethod(std::unique_ptr<PaymentMethod> method) {
        paymentMethod = std::move(method);  // ����������ʱ�л�֧����ʽ
	}
};

//// ʹ��ʾ��
//int main() {
//    // �ͻ��˾�������ʵ��
//    auto payment = std::make_unique<Alipay>();
//    OrderService orderService(std::move(payment));
//
//    orderService.processOrder(99.9);  // �����ʹ��֧����֧��: 99.9Ԫ
//
//    // ��Ҫ�л�֧����ʽ��ֻ���޸Ŀͻ��˴���
//    auto wechatPayment = std::make_unique<WechatPay>();
//    orderService.setPaymentMethod(std::move(wechatPayment));
//
//    orderService.processOrder(199.9);  // �����ʹ��΢��֧��: 199.9Ԫ
//
//
//   
//    return 0;
//}