package store;

import store.common.config.ProductConfig;
import store.common.config.PromotionConfig;
import store.common.config.StoreConfig;
import store.order.OrderController;
import store.product.Products;
import store.promotion.Promotions;

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
