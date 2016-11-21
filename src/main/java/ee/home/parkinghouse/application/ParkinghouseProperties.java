package ee.home.parkinghouse.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Date;

@Component
@PropertySource("classpath:parkinghouse.properties")
public class ParkinghouseProperties {

    @Value("#{new java.text.SimpleDateFormat(\"HH:mm\").parse(\"${day.fee.start.time}\")}")
    private Date dayFeeStartTime;

    @Value("#{new java.text.SimpleDateFormat(\"HH:mm\").parse(\"${day.fee.end.time}\")}")
    private Date dayFeeEndTime;

    @Value("${regular.fee.day.cost}")
    private long regularFeeDayCost;

    @Value("${premium.fee.day.cost}")
    private long premiumFeeDayCost;

    @Value("${regular.fee.night.cost}")
    private long regularFeeNightCost;

    @Value("${premium.fee.night.cost}")
    private long premiumFeeNightCost;

    @Value("${regular.fee.max.month.cost.limit}")
    private long regularMaxMonthLimit;

    @Value("${premium.fee.max.month.cost.limit}")
    private long premiumMaxMonthLimit;

    @SuppressWarnings("deprecation")
    public LocalTime getDayFeeStartTime() {
        return LocalTime.of(dayFeeStartTime.getHours(), dayFeeStartTime.getMinutes());
    }

    @SuppressWarnings("deprecation")
    public LocalTime getDayFeeEndTime() {
        return LocalTime.of(dayFeeEndTime.getHours(), dayFeeEndTime.getMinutes());
    }

    public long getRegularFeeDayCost() {
        return regularFeeDayCost;
    }

    public long getPremiumFeeDayCost() {
        return premiumFeeDayCost;
    }

    public long getRegularFeeNightCost() {
        return regularFeeNightCost;
    }

    public long getPremiumFeeNightCost() {
        return premiumFeeNightCost;
    }

    public long getRegularMaxMonthLimit() {
        return regularMaxMonthLimit;
    }

    public long getPremiumMaxMonthLimit() {
        return premiumMaxMonthLimit;
    }

}
