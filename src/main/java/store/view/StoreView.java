package store.view;

import java.text.DecimalFormat;
import store.Product;
import store.Products;

public class StoreView {
    private static final DecimalFormat priceFormat = new DecimalFormat("#,###");

    public void printProducts(Products products) {
        System.out.println("안녕하세요. W편의점입니다.");
        System.out.println("현재 보유하고 있는 상품입니다.\n");

        for (Product product : products.getProducts()) {
            printProduct(product);
        }
    }

    private void printProduct(Product product) {
        printProductWithoutPromotion(product);
        if (product.getPromotion() != null) {
            printProductWithPromotion(product);
        }
    }

    private void printProductWithPromotion(Product product) {
        String output = "- " + product.getName()
                + " " + formatPrice(product.getPrice()) + "원 ";
        output = appendPromotionStockInfo(product, output);
        output = appendPromotionInfo(product, output);
        System.out.println(output);
    }

    private void printProductWithoutPromotion(Product product) {
        String output = "- " + product.getName()
                + " " + formatPrice(product.getPrice()) + "원 ";
        output = appendGeneralStockInfo(product, output);
        System.out.println(output);
    }

    private String formatPrice(int price) {
        return priceFormat.format(price);
    }

    private String appendGeneralStockInfo(Product product, String output) {
        if (product.getStock().getGeneralStock() > 0) {
            output += product.getStock().getGeneralStock() + "개 ";
        }
        if (product.getStock().getGeneralStock() <= 0) {
            output += "재고 없음";
        }
        return output;
    }

    private String appendPromotionStockInfo(Product product, String output) {
        if (product.getStock().getPromotionStock() > 0) {
            output += product.getStock().getPromotionStock() + "개 ";
        }
        if (product.getStock().getPromotionStock() <= 0) {
            output += "재고 없음 ";
        }
        return output;
    }

    private String appendPromotionInfo(Product product, String output) {
        if (product.getPromotion() != null) {
            output += product.getPromotion().getName();
        }
        return output;
    }
}
