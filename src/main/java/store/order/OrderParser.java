package store.order;

import static store.common.Exceptions.INVALID_FORMAT;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderParser {
    private static final String ORDER_REGEX = "\\[([a-zA-Z0-9가-힣]+)-(\\d+)\\]";

    public List<OrderRequestDto> parseOrder(String input) {
        input = input.replaceAll("\\s+", "");

        List<OrderRequestDto> orders = new ArrayList<>();
        String[] orderItems = input.split(",");

        for (String item : orderItems) {
            parseOrderItem(item, orders);
        }

        validateOrders(orders);
        return orders;
    }

    private void parseOrderItem(String item, List<OrderRequestDto> orders) {
        Pattern pattern = Pattern.compile(ORDER_REGEX);
        Matcher matcher = pattern.matcher(item);

        if (matcher.matches()) {
            String productName = matcher.group(1);
            int quantity = Integer.parseInt(matcher.group(2));
            addOrUpdateOrder(productName, quantity, orders);
            return;
        }
        throw new IllegalArgumentException(INVALID_FORMAT.getMessage());
    }

    private void addOrUpdateOrder(String productName, int quantity, List<OrderRequestDto> orders) {
        for (OrderRequestDto order : orders) {
            if (order.name().equals(productName)) {
                int updatedQuantity = order.quantity() + quantity;
                orders.remove(order);
                orders.add(new OrderRequestDto(productName, updatedQuantity));
                return;
            }
        }
        orders.add(new OrderRequestDto(productName, quantity));
    }

    private void validateOrders(List<OrderRequestDto> orders) {
        if (orders.isEmpty()) {
            throw new IllegalArgumentException(INVALID_FORMAT.getMessage());
        }
    }
}
