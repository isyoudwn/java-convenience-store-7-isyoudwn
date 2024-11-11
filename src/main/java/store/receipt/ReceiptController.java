package store.receipt;

import store.order.OrderResultDto;
import store.presentation.view.ReceiptView;

public class ReceiptController {
    private final ReceiptView receiptView;

    public ReceiptController(ReceiptView receiptView) {
        this.receiptView = receiptView;
    }

    public void displayReceipt(OrderResultDto orderResult) {
        Receipt receipt = new Receipt(orderResult.orders(), orderResult.discountMembership());
        receiptView.print(receipt);
    }
}
