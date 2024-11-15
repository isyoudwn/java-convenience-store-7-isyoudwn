package store.order;

import store.product.Product;

public class Order {
    private final Product product;
    private Integer totalQuantity;
    private final PromotionStatus promotionStatus;
    private Integer freeQuantity;
    private Integer ignoredQuantity;

    public Order(Product product, Integer totalQuantity) {
        this.product = product;
        this.totalQuantity = totalQuantity;
        this.promotionStatus = promotionStatus();
        this.freeQuantity = calculateFreeItems();
        this.ignoredQuantity = calculateIgnoredQuantity();
    }

    private PromotionStatus promotionStatus() {
        if (product.isInPromotion()) {
            return PromotionStatus.ACTIVE;
        }
        return PromotionStatus.NONE;
    }

    private Integer calculateFreeItems() {
        if (product.isInPromotion()) {
            return product.calculateFreeItems(totalQuantity);
        }
        return 0;
    }

    public Integer calculateMissedFreeItems() {
        return product.calculateMissedFreeItems(totalQuantity);
    }

    public Integer calculateIgnoredQuantity() {
        if (promotionStatus == PromotionStatus.NONE) {
            return 0;
        }

        Integer maxApplied = product.calculateMaxApplied(totalQuantity);

        if (maxApplied < totalQuantity) {
            return totalQuantity - maxApplied;
        }
        return 0;
    }

    public void reduceStock() {
        product.reduceStock(totalQuantity);
    }

    public void reduceQuantity(Integer quantity) {
        this.totalQuantity -= quantity;
    }

    public void addQuantity(Integer quantity) {
        this.totalQuantity += quantity;
    }

    public void addFreeQuantity(Integer quantity) {
        this.freeQuantity += quantity;
    }

    public PromotionStatus getPromotionStatus() {
        return promotionStatus;
    }

    public Integer getFreeQuantity() {
        return freeQuantity;
    }

    public String getProductName() {
        return product.getName();
    }

    public Integer calculateTotalPrice() {
        return product.calculatePrice(totalQuantity);
    }

    public Integer calculateDiscountPrice() {
        return product.calculatePrice(freeQuantity);
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public Integer getIgnoredQuantity() {
        return ignoredQuantity;
    }

    public void reduceIgnoredQuantity(Integer quantity) {
        ignoredQuantity -= quantity;
    }

    public Integer calculateIgnoredPrice() {
        return ignoredQuantity * product.getPrice();
    }
}
