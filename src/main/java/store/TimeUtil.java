package store;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TimeUtil {
    public static LocalDateTime toLocalDateTime(LocalDate localDate) {
        return localDate.atStartOfDay();
    }
}
