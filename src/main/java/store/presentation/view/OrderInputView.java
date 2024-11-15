package store.presentation.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import store.presentation.UserResponse;
import store.order.Order;
import store.order.OrderCreator;
import store.order.OrderParser;
import store.order.OrderRequestDto;

public class OrderInputView {
    private final OrderParser orderParser;
    private final OrderCreator orderCreator;

    public OrderInputView(OrderParser orderParser, OrderCreator orderCreator) {
        this.orderParser = orderParser;
        this.orderCreator = orderCreator;
    }

    public List<Order> readItem() {
        System.out.println();
        System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
        String input = Console.readLine();
        List<OrderRequestDto> orderRequests = orderParser.parseOrder(input);
        return orderCreator.createOrders(orderRequests);
    }

    public UserResponse notifyIgnoredPromotion(String name, Integer stock) {
        System.out.println();
        System.out.println("현재 " + name + " " + stock + "개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)");
        return UserResponse.of(Console.readLine());
    }

    public UserResponse notifyExtraFree(String name, Integer stock) {
        System.out.println();
        System.out.println("현재 " + name + "은(는) " + stock + "개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)");
        return UserResponse.of(Console.readLine());
    }

    public UserResponse askMembershipDiscount() {
        System.out.println();
        System.out.println("멤버십 할인을 받으시겠습니까? (Y/N)");
        return UserResponse.of(Console.readLine());
    }

    public UserResponse askContinue() {
        System.out.println();
        System.out.println("감사합니다. 구매하고 싶은 다른 상품이 있나요?(Y/N)");
        UserResponse response = UserResponse.of(Console.readLine());
        System.out.println();
        return response;
    }
}
