package store.common.config;

import store.MainController;
import store.receipt.ReceiptController;
import store.order.OrderController;
import store.order.OrderCreator;
import store.presentation.view.InputHandler;
import store.presentation.view.OrderInputView;
import store.order.OrderParser;
import store.order.OrderProcessor;
import store.presentation.view.ReceiptView;
import store.presentation.view.StoreView;
import store.product.ProductController;
import store.product.Products;
import store.promotion.Promotions;

public class StoreConfig {

    public MainController mainController() {
        return new MainController(receiptController(), productController(), orderController(), inputHandler());
    }

    private OrderController orderController() {
        return new OrderController(inputHandler(), orderProcessor());
    }

    private ProductController productController() {
        return new ProductController(new StoreView(), products());
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
        return new OrderCreator(products());
    }

    private Products products() {
        Promotions promotions = promotions();
        return new Products(new ProductLoader(promotions).productFileHandle());
    }

    private Promotions promotions() {
        return new Promotions(PromotionLoader.loadPromotionsFromFile());
    }
}
