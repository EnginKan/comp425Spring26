package org.yeditepe.week9;

public class FakePaymentGateway implements PaymentGateway {

    private double balance = 500;

    @Override
    public boolean pay(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}
