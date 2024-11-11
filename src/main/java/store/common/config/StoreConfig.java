package store.common.config;

import store.product.Products;
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

    public OrderController orderController() {
        return new OrderController(inputHandler(), orderProcessor(), receiptView(), storeView(), products);
    }

    public OrderProcessor orderProcessor() {
        return new OrderProcessor(inputHandler());
    }

    public InputHandler inputHandler() {
        return new InputHandler(orderInputView());
    }

    public ReceiptView receiptView() {
        return new ReceiptView();
    }

    public OrderInputView orderInputView() {
        return new OrderInputView(orderParser(), orderCreator());
    }

    public OrderParser orderParser() {
        return new OrderParser();
    }

    public OrderCreator orderCreator() {
        return new OrderCreator(products);
    }

    public StoreView storeView() {
        return new StoreView();
    }
}
