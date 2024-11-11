package store.order;

import java.util.List;

import store.promotion.Membership;
import store.presentation.UserResponse;
import store.presentation.view.InputHandler;

public class OrderController {
    private final InputHandler inputHandler;
    private final OrderProcessor orderProcessor;


    public OrderController(InputHandler inputHandler, OrderProcessor orderProcessor) {
        this.inputHandler = inputHandler;
        this.orderProcessor = orderProcessor;
    }

    public OrderResultDto order() {
        List<Order> orders = inputHandler.readItem();
        applyPromotion(orders);
        Integer discountMembership = applyMembership(orders);
        return OrderResultDto.of(orders, discountMembership);
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
