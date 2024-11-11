package store.order;

import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.Product;
import store.Promotion;
import store.Stock;

class OrderTest {

    private Product promoProduct;
    private Product nonPromoProduct;

    @BeforeEach
    public void setUp() {

        nonPromoProduct = new Product("사과", 1000, new Stock(50, 5), null);

        promoProduct = new Product("마라탕", 2000, new Stock(20, 10),
                new Promotion("세일이오!", 2, 1,
                        LocalDate.of(2024, 6, 1),
                        LocalDate.of(2025, 6, 30)
                ));
    }

    @Test
    void 주문을_생성하면_product를_기준으로_초기_freeItem이_설정된다_no_promotion() {
        Order order = new Order(nonPromoProduct, 10);

        Assertions.assertEquals(order.getPromotionStatus(), PromotionStatus.NONE);
        Assertions.assertEquals(order.getFreeQuantity(), 0);
    }

    @Test
    void 주문을_생성하면_product를_기준으로_초기_freeItem이_설정된다_promotion() {
        Order order = new Order(promoProduct, 10);

        Assertions.assertEquals(PromotionStatus.ACTIVE, order.getPromotionStatus());
        Assertions.assertEquals(3, order.getFreeQuantity());
    }
}