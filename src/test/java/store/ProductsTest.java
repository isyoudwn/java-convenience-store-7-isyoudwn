package store;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class ProductsTest {
    @Test
    void 상품을_찾는다() {
        Product maratang = new Product("마라탕", 8000, null, null);
        Product hamburger = new Product("햄버거", 5000, null, null);
        Products products = new Products(List.of(maratang, hamburger));

        Product foundProduct = products.findProduct("마라탕");

        assertEquals("마라탕", foundProduct.getName());
    }

    @Test
    public void 상품을_찾을_수_없으면_예외를_반환한다() {
        Product maratang = new Product("마라탕", 8000, null, null);
        Product hamburger = new Product("햄버거", 5000, null, null);
        Products products = new Products(List.of(maratang, hamburger));

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            products.findProduct("피자");
        });

        assertEquals("[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.", thrown.getMessage());
    }
}
