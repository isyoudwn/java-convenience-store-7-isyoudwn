package store;

public class Product {
    private final String name;
    private final Integer price;
    private final Stock stock;
    private final Promotion promotion;

    public Product(String name, Integer price, Stock stock, Promotion promotion) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.promotion = promotion;
    }
}
