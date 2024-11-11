package store.order;

import java.util.List;
import store.promotion.Membership;
import store.presentation.UserResponse;
import store.presentation.view.InputHandler;

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
        Integer ignored = order.getIgnoredQuantity();
        if (ignored == 0) {
            return;
        }
        UserResponse response = inputHandler.notifyIgnoredPromotion(order.getProductName(), ignored);
        if (response == UserResponse.NO) {
            order.reduceQuantity(ignored);
            order.reduceIgnoredQuantity(ignored);
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
        int total = 0;
        for (Order order : orders) {
            if (order.getIgnoredQuantity() != 0) {
                total += order.calculateIgnoredPrice();
            }
            if (order.getPromotionStatus() == PromotionStatus.NONE) {
                total += order.getTotalQuantity();
            }
        }
        return membership.calculateDiscount(total);
    }
}
