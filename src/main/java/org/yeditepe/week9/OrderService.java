package org.yeditepe.week9;

public class OrderService {

    private PaymentGateway paymentGateway;
    private NotificationService notificationService;

    public OrderService(PaymentGateway paymentGateway,
                        NotificationService notificationService) {
        this.paymentGateway = paymentGateway;
        this.notificationService = notificationService;
    }

    public boolean placeOrder(String user, double amount) {
        boolean paid = paymentGateway.pay(amount);

        if (paid) {
            notificationService.send(user, "Order successful");
            return true;
        }

        return false;
    }
}