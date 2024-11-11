package store;

import store.config.ProductConfig;
import store.config.PromotionConfig;
import store.config.StoreConfig;
import store.order.OrderController;

public class Application {
    public static void main(String[] args) {
        Promotions promotions = new Promotions(PromotionConfig.loadPromotionsFromFile());
        ProductConfig productConfig = new ProductConfig(promotions);
        Products products = new Products(productConfig.productFileHandle());
        StoreConfig storeConfig = new StoreConfig(products);
        OrderController orderController = storeConfig.orderController();
        orderController.order();
    }
}
