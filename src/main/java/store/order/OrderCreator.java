package store.order;

import static store.common.Exceptions.INSUFFICIENT_STOCK;

import java.util.ArrayList;
import java.util.List;
import store.product.Product;
import store.product.Products;

public class OrderCreator {
    private final Products products;

    public OrderCreator(Products products) {
        this.products = products;
    }

    public List<Order> createOrders(List<OrderRequestDto> orderRequests) {
        List<Order> orders = new ArrayList<>();
        for (OrderRequestDto orderRequest : orderRequests) {
            Product product = products.findProduct(orderRequest.name());
            validateStock(product, orderRequest.quantity());
            Order order = createOrder(product, orderRequest.quantity());
            orders.add(order);
        }
        return orders;
    }

    public Order createOrder(Product product, Integer quantity) {
        return new Order(product, quantity);
    }

    public void validateStock(Product product, Integer quantity) {
        if (product.totalStock() < quantity) {
            throw new IllegalArgumentException(INSUFFICIENT_STOCK.getMessage());
        }
    }
}
