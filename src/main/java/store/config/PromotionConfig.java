package store.config;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import store.Promotion;

public class PromotionConfig {
    public static List<Promotion> loadPromotionsFromFile() {
        FileLoader<Promotion> loader = new FileLoader<>("promotions.md", Promotion.class);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return new ArrayList<>(loader.loadFileData(formatter));
    }
}
