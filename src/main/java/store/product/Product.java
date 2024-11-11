package store.product;

import store.promotion.Promotion;

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

    public Boolean isInPromotion() {
        return promotion != null && promotion.isPromotionActive() && stock.getPromotionStock() > 0;
    }

    public Integer calculateFreeItems(Integer quantity) {
        return promotion.calculateFreeItems(quantity, stock.getPromotionStock());
    }

    public Integer calculateMissedFreeItems(Integer orderQuantity) {
        return promotion.calculateMissedFreeItems(orderQuantity, stock.getPromotionStock());
    }

    public Integer calculateMaxApplied(Integer orderQuantity) {
        Integer maxApplied = promotion.calculateMaxApplied(stock.getPromotionStock());
        return Math.min(maxApplied, orderQuantity);
    }

    public void reduceStock(Integer quantity) {
        if (isInPromotion()) {
            stock.reduceInPromotion(quantity);
            return;
        }
        stock.reduceInNoPromotion(quantity);
    }

    public Integer totalStock() {
        return stock.getTotalStock();
    }

    public String getName() {
        return name;
    }

    public Integer getPromotionStock() {
        return stock.getPromotionStock();
    }

    public Integer getGeneralStock() {
        return stock.getGeneralStock();
    }

    public Integer calculatePrice(Integer quantity) {
        return price * quantity;
    }

    public Stock getStock() {
        return stock;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public Integer getPrice() {
        return price;
    }
}
