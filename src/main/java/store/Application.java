package store;

import store.common.config.ProductLoader;
import store.common.config.PromotionLoader;
import store.common.config.StoreConfig;
import store.product.Products;
import store.promotion.Promotions;

public class Application {
    public static void main(String[] args) {
        Promotions promotions = new Promotions(PromotionLoader.loadPromotionsFromFile());
        ProductLoader productLoader = new ProductLoader(promotions);
        Products products = new Products(productLoader.productFileHandle());
        StoreConfig storeConfig = new StoreConfig(products);
        MainController mainController = storeConfig.mainController();
        mainController.run();
    }
}
