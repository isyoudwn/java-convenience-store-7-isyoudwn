package store.presentation.view;

import java.text.DecimalFormat;
import java.util.List;
import store.receipt.Receipt;
import store.order.Order;

public class ReceiptView {
    private static final DecimalFormat priceFormat = new DecimalFormat("#,###");

    public void print(Receipt receipt) {
        System.out.println();
        printStoreName();
        printHeader();
        printOrderDetails(receipt.getOrders());
        printFreeItems(receipt.getOrders());
        printFooter(receipt);
    }

    private void printStoreName() {
        System.out.println("============== W 편의점 ==============");
    }

    private void printHeader() {
        System.out.printf("%-12s\t%-5s\t%-6s\n", "상품명", "수량", "금액");
    }

    private void printOrderDetails(List<Order> orders) {
        for (Order order : orders) {
            String productName = order.getProductName();
            int quantity = order.getTotalQuantity();
            int totalPrice = order.calculateTotalPrice();
            System.out.printf("%-12s\t%-5d\t%-6s\n", productName, quantity, formatPrice(totalPrice));
        }
    }

    private void printFreeItems(List<Order> orders) {
        System.out.println("============= 증 정 ================");
        for (Order order : orders) {
            if (order.getFreeQuantity() > 0) {
                String productName = order.getProductName();
                System.out.printf("%-12s\t%-5d\n", productName, order.getFreeQuantity());
            }
        }
    }

    private void printFooter(Receipt receipt) {
        System.out.println("====================================");
        System.out.printf("%-15s\t%-5d\t%-6s\n", "총구매액", receipt.getTotalQuantity(),
                formatPrice(receipt.getTotalAmount()));
        System.out.printf("%-15s\t%-5s\t%-6s\n", "행사할인", "-", formatPrice(receipt.getDiscountAmount()));
        System.out.printf("%-15s\t%-5s\t%-6s\n", "멤버십할인", "-", formatPrice(receipt.getDiscountMembership()));
        System.out.printf("%-15s\t%-5s\t%-6s\n", "내실돈", "",
                formatPrice(receipt.getTotalAmount() - receipt.getDiscountAmount() - receipt.getDiscountMembership()));
    }

    private String formatPrice(int price) {
        return priceFormat.format(price);
    }
}