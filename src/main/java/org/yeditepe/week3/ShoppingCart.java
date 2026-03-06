package org.yeditepe.week3;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private final Map<String, Integer> items = new HashMap<>();
    private final Map<String, Double> prices = new HashMap<>();

    public void addItem(String item, int quantity, double price) {
        if (quantity <= 0 || price <= 0) {
            throw new IllegalArgumentException("Quantity and price must be positive.");
        }
        items.put(item, items.getOrDefault(item, 0) + quantity);
        prices.put(item, price);
    }

    public void removeItem(String item, int quantity) {
        if (!items.containsKey(item) || quantity <= 0) {
            throw new IllegalArgumentException("Invalid item or quantity.");
        }
        int remaining = items.get(item) - quantity;
        if (remaining < 0) {
            throw new IllegalArgumentException("Not enough quantity to remove.");
        }
        if (remaining == 0) {
            items.remove(item);
            prices.remove(item);
        } else {
            items.put(item, remaining);
        }
    }

    public double getTotalCost() {
        return items.entrySet().stream()
                .mapToDouble(entry -> entry.getValue() * prices.get(entry.getKey()))
                .sum();
    }

    public void applyDiscount(double percentage) {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("Invalid discount percentage.");
        }
        for (String item : prices.keySet()) {
            prices.put(item, prices.get(item) * (1 - percentage / 100));
        }
    }

    public int getItemCount() {
        return items.values().stream().mapToInt(Integer::intValue).sum();
    }
}