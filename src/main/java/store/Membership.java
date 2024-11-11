package store;

public enum Membership {
    MEMBER(30),
    NORMAL(0);

    private final Integer discountRate;

    Membership(Integer discountRate) {
        this.discountRate = discountRate;
    }

    public static Membership of(UserResponse response) {
        if (response == UserResponse.YES) {
            return MEMBER;
        }
        return NORMAL;
    }

    public Integer calculateDiscount(int totalAmount) {
        double discount = totalAmount * discountRate / 100.0;
        Integer totalDiscount = (int) Math.round(discount);

        if (totalDiscount > 8000) {
            totalDiscount = 8000;
        }

        return totalDiscount;
    }
}
