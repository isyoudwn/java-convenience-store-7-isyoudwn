package store;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class PromotionTest {
    @Test
    public void 프로모션이_활성화_됐는지_확인한다() {
        Promotion promotion = new Promotion(
                "마라탕",
                2,
                1,
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2040, 12, 31));

        assertTrue(promotion.isPromotionActive());
    }

    @Test
    public void 프로모션_기간이_아니라면_프로모션이_활성화되지_않는다() {
        Promotion promotion = new Promotion(
                "마라탕",
                2,
                1,
                LocalDate.of(2020, 1, 1),
                LocalDate.of(2022, 12, 31));

        assertFalse(promotion.isPromotionActive());
    }t
}
