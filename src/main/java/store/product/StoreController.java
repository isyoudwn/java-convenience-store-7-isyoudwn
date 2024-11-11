package store.product;

import store.presentation.view.StoreView;

public class StoreController {
    private final Products products;
    private final StoreView storeView;

    public StoreController(Products products, StoreView storeView) {
        this.products = products;
        this.storeView = storeView;
    }

    public void displayStore() {
        storeView.printProducts(products);
    }
}
