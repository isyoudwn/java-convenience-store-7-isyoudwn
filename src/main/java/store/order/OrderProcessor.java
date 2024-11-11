package store.order;

import java.util.List;
import store.Membership;
import store.UserResponse;
import store.view.InputHandler;

public class OrderProcessor {
    private final InputHandler inputHandler;

    public OrderProcessor(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    public void applyPromotion(Order order) {
        checkIgnoredQuantity(order);
        checkExtraFreeItem(order);
    }

    public void checkIgnoredQuantity(Order order) {
        Integer ignored = order.calculateIgnoredQuantity();
        if (ignored == 0) {
            return;
        }
        UserResponse response = inputHandler.notifyIgnoredPromotion(order.getProductName(), ignored);
        if (response == UserResponse.NO) {
            order.reduceQuantity(ignored);
        }
    }

    public void checkExtraFreeItem(Order order) {
        Integer missedFreeItems = order.calculateMissedFreeItems();
        if (missedFreeItems == 0) {
            return;
        }
        UserResponse response = inputHandler.notifyExtraFree(order.getProductName(), missedFreeItems);
        if (response == UserResponse.YES) {
            order.addQuantity(missedFreeItems);
            order.addFreeQuantity(missedFreeItems);
        }
    }

    public Integer applyMembership(List<Order> orders, Membership membership) {
        int total = orders.stream()
                .filter(order -> order.getPromotionStatus() == PromotionStatus.NONE)
                .mapToInt(Order::calculateTotalPrice)
                .sum();

        return membership.calculateDiscount(total);
    }
}
