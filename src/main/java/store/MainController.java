package store;

import store.order.OrderController;
import store.order.OrderResultDto;
import store.presentation.UserResponse;
import store.presentation.view.InputHandler;
import store.product.ProductController;
import store.receipt.ReceiptController;

public class MainController {
    private final OrderController orderController;
    private final InputHandler inputHandler;
    private final ReceiptController receiptController;
    private final ProductController productController;


    public MainController(ReceiptController receiptController,
                          OrderController orderController, InputHandler inputHandler, ProductController productController) {
        this.receiptController = receiptController;
        this.orderController = orderController;
        this.inputHandler = inputHandler;
        this.productController = productController;
    }

    public void run() {
        productController.displayStore();
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
