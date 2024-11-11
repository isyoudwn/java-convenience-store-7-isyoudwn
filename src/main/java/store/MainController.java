package store;

import store.order.OrderController;
import store.order.OrderResultDto;
import store.presentation.UserResponse;
import store.presentation.view.InputHandler;
import store.product.StoreController;
import store.receipt.ReceiptController;

public class MainController {
    private final OrderController orderController;
    private final InputHandler inputHandler;
    private final ReceiptController receiptController;
    private final StoreController storeController;


    public MainController(ReceiptController receiptController,
                          OrderController orderController, InputHandler inputHandler, StoreController storeController) {
        this.receiptController = receiptController;
        this.orderController = orderController;
        this.inputHandler = inputHandler;
        this.storeController = storeController;
    }

    public void run() {
        storeController.displayStore();
        OrderResultDto orderResult = orderController.order();
        receiptController.displayReceipt(orderResult);
        retry();
    }

    private void retry() {
        UserResponse response = inputHandler.askContinue();
        if (UserResponse.YES == response) {
            run();
        }
    }
}
