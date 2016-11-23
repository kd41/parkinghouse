package ee.home.parkinghouse.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
@PropertySource("classpath:parkinghouse.properties")
public class ParkinghouseProperties {

    // let it be here for example
    // @Value("#{new java.text.SimpleDateFormat(\"HH:mm\").parse(\"${day.fee.start.time}\")}")
    // private Date dayFeeStartTime;

    @Value("${day.fee.start.time}")
    private String dayFeeStartTime;
    private LocalTime dayFeeStartLocalTime;

    @Value("${day.fee.end.time}")
    private String dayFeeEndTime;
    private LocalTime dayFeeEndLocalTime;

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

    public LocalTime getDayFeeStartTime() {
        if (dayFeeStartLocalTime == null) {
            dayFeeStartLocalTime = LocalTime.parse(dayFeeStartTime, DateTimeFormatter.ISO_LOCAL_TIME);
        }
        return dayFeeStartLocalTime;
    }

    public LocalTime getDayFeeEndTime() {
        if (dayFeeEndLocalTime == null) {
            dayFeeEndLocalTime = LocalTime.parse(dayFeeEndTime, DateTimeFormatter.ISO_LOCAL_TIME);
        }
        return dayFeeEndLocalTime;
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
