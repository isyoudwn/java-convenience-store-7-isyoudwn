package store.product;

import static store.common.Exceptions.PRODUCT_NOT_FOUND;

import java.util.List;

public class Products {
    private final List<Product> products;

    public Products(List<Product> products) {
        this.products = products;
    }

    public Product findProduct(String name) {
        return products.stream()
                .filter(product -> product.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(PRODUCT_NOT_FOUND.getMessage()));
    }

    public List<Product> getProducts() {
        return products;
    }
}
