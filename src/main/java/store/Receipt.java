package store;

import java.util.List;
import store.order.Order;

public class Receipt {
    private final List<Order> orders;
    private final Integer totalQuantity;
    private final Integer totalAmount;
    private final Integer discountAmount;
    private final Integer discountMembership;

    public Receipt(List<Order> orders, Integer discountMembership) {
        this.orders = orders;
        this.totalQuantity = calculateTotalQuantity();
        this.totalAmount = calculateTotalAmount();
        this.discountAmount = calculateDiscountAmount();
        this.discountMembership = discountMembership;
    }

    private Integer calculateTotalQuantity() {
        return orders.stream()
                .mapToInt(Order::getTotalQuantity)
                .sum();
    }

    private Integer calculateTotalAmount() {
        return orders.stream()
                .mapToInt(Order::calculateTotalPrice)
                .sum();
    }

    private Integer calculateDiscountAmount() {
        return orders.stream()
                .mapToInt(Order::calculateDiscountPrice)
                .sum();
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public Integer getDiscountAmount() {
        return discountAmount;
    }

    public Integer getDiscountMembership() {
        return discountMembership;
    }
}
