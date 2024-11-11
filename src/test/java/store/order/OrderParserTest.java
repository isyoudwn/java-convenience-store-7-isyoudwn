package store.order;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class OrderParserTest {
    OrderParser orderParser = new OrderParser();

    @Test
    public void 유효한_입력인지_확인한다_한개() {
        String input = "[사이다-5]";

        List<OrderRequestDto> orders = orderParser.parseOrder(input);

        assertEquals(1, orders.size());
        assertEquals("사이다", orders.get(0).name());
        assertEquals(5, orders.get(0).quantity());
    }

    @Test
    public void 유효한_입력인지_확인한다_여러개() {
        String input = "[사이다-2],[감자칩-3],[콜라-1]";

        List<OrderRequestDto> orders = orderParser.parseOrder(input);

        assertEquals(3, orders.size());
        assertEquals("사이다", orders.get(0).name());
        assertEquals(2, orders.get(0).quantity());

        assertEquals("감자칩", orders.get(1).name());
        assertEquals(3, orders.get(1).quantity());

        assertEquals("콜라", orders.get(2).name());
        assertEquals(1, orders.get(2).quantity());
    }

    @ParameterizedTest
    @MethodSource("invalidInput")
    public void 입력이_잘못될_경우_예외를_반환한다() {
        String input = "[사이다-2], 사이다-3";

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            orderParser.parseOrder(input);
        });

        assertEquals("[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.", thrown.getMessage());
    }

    private static Stream<String> invalidInput() {
        return Stream.of(
                "[사이다-2], 사이다-3",
                "",
                "[사이다-2], 사이다",
                "[사이다-abc], [콜라-3]",
                "[사이다-2, [콜라-3]"
        );
    }
}
