package store.promotion;

import static store.common.Exceptions.PROMOTION_NOT_FOUND;

import java.util.List;
import store.promotion.Promotion;

public class Promotions {
    private final List<Promotion> promotions;

    public Promotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public Promotion findByName(String name) {
        return promotions.stream()
                .filter(promotion -> promotion.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(PROMOTION_NOT_FOUND.getMessage()));
    }
}
