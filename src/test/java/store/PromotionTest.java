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
    }

    @Test
    public void 가져오지_않은_무료아이템_개수를_계산한다() {
        Promotion promotion = new Promotion(
                "마라탕",
                2,  // buy
                1,  // get
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2040, 12, 31)
        );
        Integer quantity = 6;  // 6개를 구매
        Integer promotionStock = 2;  // 재고가 2개만 있음
        Integer expectedMissedFreeItems = 0;  // 재고가 부족하여 무료 아이템을 제공하지 않음

        assertEquals(expectedMissedFreeItems, promotion.calculateMissedFreeItems(quantity, promotionStock));
    }

    @Test
    public void 가져오지_않은_무료아이템_개수를_계산한다_구매수량이_충족하는데_혜택을_안_가져왔을때() {
        Promotion promotion = new Promotion(
                "마라탕",
                1,
                1,
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2040, 12, 31));
        Integer quantity = 3;
        Integer promotionStock = 9;
        Integer expectedMissedFreeItems = 1;

        assertEquals(expectedMissedFreeItems, promotion.calculateMissedFreeItems(quantity, promotionStock));
    }

    @Test
    public void 가져오지_않은_무료아이템_개수를_계산한다_프로모션_재고가_부족할때_혜택을_줄_수_없다() {
        Promotion promotion = new Promotion(
                "마라탕",
                1,
                1,
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2040, 12, 31));
        Integer quantity = 9;
        Integer promotionStock = 8;
        Integer expectedMissedFreeItems = 0;

        assertEquals(expectedMissedFreeItems, promotion.calculateMissedFreeItems(quantity, promotionStock));
    }

    @Test
    public void 재고가_충분할때_최대_무료_아이템을_계산한다() {
        Promotion promotion = new Promotion(
                "마라탕",
                3,
                1,
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2040, 12, 31));
        Integer quantity = 10;
        Integer promotionStock = 5;
        Integer expectedMissedFreeItems = 0;

        assertEquals(expectedMissedFreeItems, promotion.calculateMissedFreeItems(quantity, promotionStock));
    }

    @Test
    public void 그룹_당_혜택이_2개일_때_정확히_계산한다() {
        Promotion promotion = new Promotion(
                "마라탕",
                2,
                2,
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2040, 12, 31));
        Integer quantity = 5;
        Integer promotionStock = 4;
        Integer expectedMissedFreeItems = 0;

        assertEquals(expectedMissedFreeItems, promotion.calculateMissedFreeItems(quantity, promotionStock));
    }

    @Test
    public void 구매량이_프로모션_기준보다_적을때_무료아이템이_없다() {
        Promotion promotion = new Promotion(
                "마라탕",
                5,
                2,
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2040, 12, 31));
        Integer quantity = 4;
        Integer promotionStock = 10;
        Integer expectedMissedFreeItems = 0;

        assertEquals(expectedMissedFreeItems, promotion.calculateMissedFreeItems(quantity, promotionStock));
    }
}
