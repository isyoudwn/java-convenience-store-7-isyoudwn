package store;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Promotion {
    private final String name;
    private final Integer buy;
    private final Integer get;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Promotion(String name, Integer buy, Integer get, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.buy = buy;
        this.get = get;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Boolean isPromotionActive() {
        LocalDateTime now = DateTimes.now();
        LocalDateTime start = TimeUtil.toLocalDateTime(startDate);
        LocalDateTime end = TimeUtil.toLocalDateTime(endDate);
        return now.isAfter(start) && now.isBefore(end);
    }
}
