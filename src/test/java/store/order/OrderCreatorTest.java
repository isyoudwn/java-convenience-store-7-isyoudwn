package store.order;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.product.Product;
import store.product.Products;
import store.promotion.Promotion;
import store.product.Stock;

class OrderCreatorTest {
    private List<Product> items = new ArrayList<>();
    private OrderCreator orderCreator;
    private Products products;

    @BeforeEach
    void setUp() {
        items.add(new Product("사과", 1000, new Stock(50, 0), null));
        items.add(new Product("컵라면", 500, new Stock(30, 0), null));
        items.add(new Product("마라탕", 2000, new Stock(20, 10),
                new Promotion("Summer Sale", 2, 1,
                        LocalDate.of(2024, 6, 1),
                        LocalDate.of(2025, 6, 30)
                )));
        products = new Products(items);
        orderCreator = new OrderCreator(products);
    }

    @Test
    void 주문을_생성한다() {
        // given
        List<OrderRequestDto> orderRequests = List.of(
                new OrderRequestDto("사과", 5),
                new OrderRequestDto("마라탕", 2));

        // when
        List<Order> orders = orderCreator.createOrders(orderRequests);

        // then
        assertEquals(2, orders.size());
        assertEquals("사과", orders.get(0).getProductName());
        assertEquals("마라탕", orders.get(1).getProductName());
    }

    @Test
    void 재고가_부족할_경우_오류를_발생한다() {
        // given
        List<OrderRequestDto> orderRequests = List.of(
                new OrderRequestDto("사과", 52),
                new OrderRequestDto("마라탕", 2));

        // when
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            orderCreator.createOrders(orderRequests);
        });

        // then
        assertEquals("[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.", thrown.getMessage());
    }

    @Test
    void 프로모션_대상일_경우_주문의_상태는_promtion이다() {
        // given
        List<OrderRequestDto> orderRequests = List.of(
                new OrderRequestDto("사과", 5));

        // when
        List<Order> orders = orderCreator.createOrders(orderRequests);

        // then
        assertEquals(1, orders.size());
        assertEquals(PromotionStatus.NONE, orders.get(0).getPromotionStatus());
    }

    @Test
    void 프로모션_대상이_아닐경우_주문의_상태는_normal이다() {
        // given
        List<OrderRequestDto> orderRequests = List.of(
                new OrderRequestDto("마라탕", 2));

        // when
        List<Order> orders = orderCreator.createOrders(orderRequests);

        // then
        assertEquals(1, orders.size());
        assertEquals(PromotionStatus.ACTIVE, orders.get(0).getPromotionStatus());
    }
}
