package store.promotion;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.LocalDateTime;
import store.common.TimeUtil;

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

    public Integer calculateMissedFreeItems(Integer quantity, Integer promotionStock) {
        if (quantity < buy) {
            return 0;
        }

        Integer group = buy + get;
        Integer rest = quantity % group;

        if (rest != 0 && rest % get == 0 && promotionStock > 0) {
            Integer needed = 1 + quantity;
            if (needed > promotionStock) {
                return 0;
            }
            return 1;
        }
        return 0;
    }

    public Integer calculateFreeItems(Integer quantity, Integer promotionStock) {
        if (quantity < buy) {
            return 0;
        }
        Integer maxQuantity = Math.min(quantity, promotionStock);
        Integer group = maxQuantity / (buy + get);
        return group * get;
    }

    public Integer calculateMaxApplied(Integer promotionStock) {
        Integer groupSize = buy + get;
        return (promotionStock / groupSize) * groupSize;
    }

    public String getName() {
        return name;
    }
}
