package store;

import store.order.OrderController;
import store.order.OrderResultDto;
import store.presentation.UserResponse;
import store.presentation.view.InputHandler;
import store.product.ProductController;
import store.receipt.ReceiptController;

public class MainController {
    private final ReceiptController receiptController;
    private final ProductController productController;
    private final OrderController orderController;
    private final InputHandler inputHandler;

    public MainController(ReceiptController receiptController, ProductController productController,
                          OrderController orderController, InputHandler inputHandler) {
        this.receiptController = receiptController;
        this.productController = productController;
        this.orderController = orderController;
        this.inputHandler = inputHandler;
    }

    public void run() {
        productController.displayProducts();
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
