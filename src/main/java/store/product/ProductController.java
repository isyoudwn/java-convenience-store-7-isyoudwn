package store.product;

import store.presentation.view.StoreView;

public class ProductController {
    private final StoreView storeView;

    private final Products products;

    public ProductController(StoreView storeView, Products products) {
        this.storeView = storeView;
        this.products = products;
    }

    public void displayProducts() {
        storeView.printProducts(products);
    }
}
