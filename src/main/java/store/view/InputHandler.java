package store.view;

import java.util.List;
import java.util.function.Supplier;
import store.UserResponse;
import store.order.Order;


public class InputHandler {
    private final OrderInputView orderInputView;

    public InputHandler(OrderInputView orderInputView) {
        this.orderInputView = orderInputView;
    }

    public List<Order> readItem() {
        return handle(orderInputView::readItem);
    }

    public UserResponse notifyIgnoredPromotion(String name, Integer stock) {
        return handle(() -> orderInputView.notifyIgnoredPromotion(name, stock));
    }

    public UserResponse notifyExtraFree(String name, Integer stock) {
        return handle(() -> orderInputView.notifyExtraFree(name, stock));
    }

    public UserResponse askMembershipDiscount() {
        return handle(orderInputView::askMembershipDiscount);
    }

    private <T> T handle(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
