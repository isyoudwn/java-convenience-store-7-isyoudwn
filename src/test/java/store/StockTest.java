package store;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import store.product.Stock;

class StockTest {

    @Test
    void 프로모션_기간이_아닐_경우_일반재고_먼저_감소한다() {
        Stock stock = new Stock(10, 10);

        stock.reduceInNoPromotion(10);

        assertEquals(0, stock.getGeneralStock());
        assertEquals(10, stock.getPromotionStock());
    }

    @Test
    void 프로모션_기간일_경우_프로모션_재고_먼저_감소한다() {
        Stock stock = new Stock(10, 10);

        stock.reduceInPromotion(10);

        Assertions.assertEquals(10, stock.getGeneralStock());
        Assertions.assertEquals(0, stock.getPromotionStock());
    }

    @Test
    void 프로모션_기간_중_프로모션_재고가_부족하면_일반_재고에서_추가로_감소한다() {
        Stock stock = new Stock(10, 10);

        stock.reduceInPromotion(12);

        Assertions.assertEquals(8, stock.getGeneralStock());
        Assertions.assertEquals(0, stock.getPromotionStock());
    }

    @Test
    void 프로모션_기간이_아닌데_일반재고가_부족할_경우_추가로_프로모션_재고에서_감소한다() {
        Stock stock = new Stock(10, 10);

        stock.reduceInNoPromotion(12);

        Assertions.assertEquals(0, stock.getGeneralStock());
        Assertions.assertEquals(8, stock.getPromotionStock());
    }
}
