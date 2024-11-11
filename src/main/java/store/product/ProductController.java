package store.product;

import store.presentation.view.StoreView;

public class ProductController {
    private final Products products;
    private final StoreView storeView;

    public ProductController(Products products, StoreView storeView) {
        this.products = products;
        this.storeView = storeView;
    }

    public void displayStore() {
        storeView.printProducts(products);
    }
}
