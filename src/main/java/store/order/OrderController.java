package store.order;

import java.util.List;

import store.Membership;
import store.Receipt;
import store.UserResponse;
import store.view.InputHandler;
import store.view.ReceiptView;

public class OrderController {
    private final InputHandler inputHandler;
    private final OrderProcessor orderProcessor;
    private final ReceiptView receiptView;

    public OrderController(InputHandler inputHandler, OrderProcessor orderProcessor, ReceiptView receiptView) {
        this.inputHandler = inputHandler;
        this.orderProcessor = orderProcessor;
        this.receiptView = receiptView;
    }

    public void order() {
        List<Order> orders = inputHandler.readItem();
        applyPromotion(orders);
        Integer discountMembership = applyMembership(orders);
        Receipt receipt = new Receipt(orders, discountMembership);
        receiptView.print(receipt);
        retry();
    }

    private void retry() {
        UserResponse response = inputHandler.askContinue();
        if (UserResponse.YES == response) {
            order();
        }
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
