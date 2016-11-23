package ee.home.parkinghouse.calculation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

import ee.home.parkinghouse.application.ParkinghouseProperties;
import ee.home.parkinghouse.model.Fee;
import ee.home.parkinghouse.model.User.CustomerType;
import ee.home.parkinghouse.util.DateUtil;

/**
 * getFees() method is simplified: 1) cost calculates by startDate; 2) cost not change during parking period.
 */
@Component
public class FeeCostCalculation {

    @Autowired
    private ParkinghouseProperties properties;

    public Fee getFees(final Date start, final Date end, final CustomerType customerType) {
        if (DateUtil.isFirstAfter(start, end)) {
            throw new IllegalArgumentException("Start may not be after end.");
        }
        Fee fee = new Fee();
        fee.setStart(start);
        fee.setEnd(end);
        LocalDateTime startLocal = DateUtil.dateToLocalDateTime(start);
        LocalDateTime endLocal = DateUtil.dateToLocalDateTime(end);
        Duration duration = Duration.between(startLocal, endLocal);
        long durationMinutes = duration.toMinutes();
        if (durationMinutes == 0) {
            addPartToFee(fee, start, end, 0, 0);
            return fee;
        }
        LocalDateTime startDayTime = DateUtil.getLocalDateTimeWithTime(start, properties.getDayFeeStartTime());
        LocalDateTime endDayTime = DateUtil.getLocalDateTimeWithTime(end, properties.getDayFeeEndTime());
        boolean isDayFee = false;
        if (DateUtil.isFirstAfter(startLocal, startDayTime) && DateUtil.isFirstBefore(startLocal, endDayTime)) {
            isDayFee = true;
        }
        addPartToFee(fee, start, end, getPartCost(customerType, !isDayFee), (int) durationMinutes / 30 + 1);

        // calculate total cost
        fee.getParts().forEach(part -> fee.setTotalCost(fee.getTotalCost() + part.getTotalCost()));
        return fee;
    }

    private void addPartToFee(Fee fee, Date partStart, Date partEnd, long partCost, int partCount) {
        Fee.Part part = new Fee.Part();
        part.setStart(partStart);
        part.setEnd(partEnd);
        part.setCost(partCost);
        part.setCount(partCount);
        part.setTotalCost(partCost * partCount);
        fee.getParts().add(part);
    }

    private long getPartCost(CustomerType type, boolean isNight) {
        long partCost;
        if (CustomerType.REGULAR == type && isNight) {
            partCost = properties.getRegularFeeNightCost();
        } else if (CustomerType.PREMIUM == type && isNight) {
            partCost = properties.getPremiumFeeNightCost();
        } else if (CustomerType.REGULAR == type && !isNight) {
            partCost = properties.getRegularFeeDayCost();
        } else if (CustomerType.PREMIUM == type && !isNight) {
            partCost = properties.getPremiumFeeDayCost();
        } else {
            throw new UnsupportedOperationException("unknow state");
        }
        return partCost;
    }

}
