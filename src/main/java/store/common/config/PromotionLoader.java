package store.common.config;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import store.promotion.Promotion;

public class PromotionLoader {
    public static List<Promotion> loadPromotionsFromFile() {
        FileLoader<Promotion> loader = new FileLoader<>("promotions.md", Promotion.class);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return new ArrayList<>(loader.loadFileData(formatter));
    }
}
