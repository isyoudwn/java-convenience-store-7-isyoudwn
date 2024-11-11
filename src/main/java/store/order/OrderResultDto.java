package store.order;

import java.util.List;

public record OrderResultDto(List<Order> orders, Integer discountMembership) {
    public static OrderResultDto of(List<Order> orders, Integer discountMembership) {
        return new OrderResultDto(orders, discountMembership);
    }
}
