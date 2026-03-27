package org.yeditepe.week6;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


public class DiscountCalculatorTest {

    @Test
    void shouldApplyDiscountCorrectly() {
        DiscountCalculator calculator = new DiscountCalculator();
        double result = calculator.applyDiscount(100, 0.2);
        assertEquals(80, result);
    }

    @Test
    void shouldThrowExceptionForNegativePrice() {
        DiscountCalculator calculator = new DiscountCalculator();
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.applyDiscount(-50, 0.2);
        });
    }

    @Test
    void shouldThrowExceptionForInvalidDiscountRate() {
        DiscountCalculator calculator = new DiscountCalculator();
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.applyDiscount(100, 1.5);
        });
    }
}