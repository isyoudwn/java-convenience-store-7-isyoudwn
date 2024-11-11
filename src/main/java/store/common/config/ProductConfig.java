package store.common.config;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import store.product.Product;
import store.promotion.Promotion;
import store.promotion.Promotions;
import store.product.Stock;

public class ProductConfig {
    private final Promotions promotions;

    public ProductConfig(Promotions promotions) {
        this.promotions = promotions;
    }

    public List<Product> productFileHandle() {
        Map<String, Product> products = new LinkedHashMap<>();
        List<String> lines = readFile("src/main/resources/products.md");
        processLines(lines, products);
        return new ArrayList<>(products.values());
    }

    private List<String> readFile(String filePath) {
        try {
            Path path = Paths.get(filePath);
            return Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    private void processLines(List<String> lines, Map<String, Product> products) {
        lines.stream().skip(1).forEach(line -> {
            String[] values = line.split(",");
            String name = values[0].trim();
            int price = Integer.parseInt(values[1].trim());
            int quantity = Integer.parseInt(values[2].trim());
            Promotion promotion = getPromotion(values[3].trim());

            Product product = getOrCreateProduct(products, name, price, promotion);
            addStockToProduct(product.getStock(), quantity, promotion);
        });
    }

    private Product getOrCreateProduct(Map<String, Product> products, String name, int price, Promotion promotion) {
        return products.computeIfAbsent(name, key -> new Product(name, price, new Stock(0, 0), promotion));
    }

    private void addStockToProduct(Stock stock, int quantity, Promotion promotion) {
        if (promotion == null) {
            addGeneralStock(stock, quantity);
        } else {
            addPromotionStock(stock, quantity);
        }
    }

    private void addGeneralStock(Stock stock, int quantity) {
        stock.addGeneralStock(quantity);
    }

    private void addPromotionStock(Stock stock, int quantity) {
        stock.addPromotionStock(quantity);
    }

    private Promotion getPromotion(String input) {
        if (input == null || input.trim().equals("null")) {
            return null;
        }
        return promotions.findByName(input);
    }
}
