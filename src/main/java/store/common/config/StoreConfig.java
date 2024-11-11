package store.common.config;

import store.MainController;
import store.product.ProductController;
import store.product.Products;
import store.receipt.ReceiptController;
import store.order.OrderController;
import store.order.OrderCreator;
import store.presentation.view.InputHandler;
import store.presentation.view.OrderInputView;
import store.order.OrderParser;
import store.order.OrderProcessor;
import store.presentation.view.ReceiptView;
import store.presentation.view.StoreView;

public class StoreConfig {

    private final Products products;

    public StoreConfig(Products products) {
        this.products = products;
    }

    public MainController mainController() {
        return new MainController(receiptController(), orderController(), inputHandler(), productController());
    }

    private ProductController productController() {
        return new ProductController(products, storeView());
    }

    private OrderController orderController() {
        return new OrderController(inputHandler(), orderProcessor());
    }

    private StoreView storeView() {
        return new StoreView();
    }

    private ReceiptController receiptController() {
        return new ReceiptController(new ReceiptView());
    }

    private OrderProcessor orderProcessor() {
        return new OrderProcessor(inputHandler());
    }

    private InputHandler inputHandler() {
        return new InputHandler(orderInputView());
    }

    private OrderInputView orderInputView() {
        return new OrderInputView(orderParser(), orderCreator());
    }

    private OrderParser orderParser() {
        return new OrderParser();
    }

    private OrderCreator orderCreator() {
        return new OrderCreator(products);
    }
}
