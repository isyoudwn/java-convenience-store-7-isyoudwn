package store.order;

public record OrderRequestDto(String name, Integer quantity) {
    static OrderRequestDto of(String name, Integer quantity) {
        return new OrderRequestDto(name, quantity);
    }
}
