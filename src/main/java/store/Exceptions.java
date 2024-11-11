package store;

public enum Exceptions {
    PRODUCT_NOT_FOUND("[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요."),
    INSUFFICIENT_STOCK("[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");

    private final String message;

    Exceptions (String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
