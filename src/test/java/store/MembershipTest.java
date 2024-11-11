package store;

import org.junit.jupiter.api.Test;
import store.promotion.Membership;

import static org.junit.jupiter.api.Assertions.*;

public class MembershipTest {

    @Test
    public void 멤버십_적용_할인을_한다() {
        Membership member = Membership.MEMBER;
        int totalAmount = 10000;

        int discount = member.calculateDiscount(totalAmount);

        assertEquals(3000, discount);
    }

    @Test
    public void 멤버십을_적용하지_않는다() {
        Membership normal = Membership.NORMAL;
        int totalAmount = 10000;

        int discount = normal.calculateDiscount(totalAmount);

        assertEquals(0, discount);
    }

    @Test
    public void 최대_한도는_8000원이다() {
        Membership member = Membership.MEMBER;
        int totalAmount = 50000;

        int discount = member.calculateDiscount(totalAmount);

        assertEquals(8000, discount);
    }
}
