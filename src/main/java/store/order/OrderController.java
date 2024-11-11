package store.order;

import java.util.List;

import store.Membership;
import store.UserResponse;
import store.view.InputHandler;

public class OrderController {
    private final InputHandler inputHandler;
    private final OrderProcessor orderProcessor;

    public OrderController(InputHandler inputHandler, OrderProcessor orderProcessor) {
        this.inputHandler = inputHandler;
        this.orderProcessor = orderProcessor;

    }

    public void order() {
        List<Order> orders = inputHandler.readItem();
        applyPromotion(orders);
        Integer discountMembership = applyMembership(orders);
    }

    public void applyPromotion(List<Order> orders) {
        for (Order order : orders) {
            if (order.getPromotionStatus() == PromotionStatus.ACTIVE) {
                orderProcessor.applyPromotion(order);
            }
            order.reduceStock();
        }
    }

    public Integer applyMembership(List<Order> orders) {
        UserResponse response = inputHandler.askMembershipDiscount();
        Membership membership = Membership.of(response);
        return orderProcessor.applyMembership(orders, membership);
    }
}
