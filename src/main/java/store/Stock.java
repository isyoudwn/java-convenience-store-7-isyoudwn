package store;

public class Stock {
    private int generalStock;
    private int promotionStock;

    public Stock(int generalStock, int promotionStock) {
        this.generalStock = generalStock;
        this.promotionStock = promotionStock;
    }

    public void reduceInNoPromotion(int quantity) {
        int remainingQuantity = reduceGeneralStock(quantity);

        if (remainingQuantity > 0) {
            reduceInPromotion(remainingQuantity);
        }
    }

    public void reduceInPromotion(int quantity) {
        int remainingQuantity = reducePromotionStock(quantity);

        if (remainingQuantity > 0) {
            reduceGeneralStock(remainingQuantity);
        }
    }

    private int reduceGeneralStock(int quantity) {
        if (generalStock >= quantity) {
            generalStock -= quantity;
            return 0;
        }

        int remainingQuantity = quantity - generalStock;
        generalStock = 0;
        return remainingQuantity;
    }

    private int reducePromotionStock(int quantity) {
        if (promotionStock >= quantity) {
            promotionStock -= quantity;
            return 0;
        }

        int remainingQuantity = quantity - promotionStock;
        promotionStock = 0;
        return remainingQuantity;
    }

    public void addGeneralStock(Integer quantity) {
        generalStock += quantity;
    }

    public void addPromotionStock(Integer quantity) {
        promotionStock += quantity;
    }

    public int getTotalStock() {
        return generalStock + promotionStock;
    }

    public int getGeneralStock() {
        return generalStock;
    }

    public int getPromotionStock() {
        return promotionStock;
    }
}
