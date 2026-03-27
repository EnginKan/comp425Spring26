package org.yeditepe.week6;

public class DiscountCalculator {

    public double applyDiscount(double price, double discountRate) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }

        if (discountRate < 0 || discountRate > 1) {
            throw new IllegalArgumentException("Invalid discount rate");
        }

        return price - (price * discountRate);
    }
}
