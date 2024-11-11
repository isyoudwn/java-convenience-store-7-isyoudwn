package store;

import store.order.Receipt;
import store.presentation.view.ReceiptView;

public class ReceiptController {
    private final ReceiptView receiptView;

    public ReceiptController(ReceiptView receiptView) {
        this.receiptView = receiptView;
    }

    public void displayReceipt(Receipt receipt) {
        receiptView.print(receipt);
    }
}
