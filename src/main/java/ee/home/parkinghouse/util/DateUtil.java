package ee.home.parkinghouse.util;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public final class DateUtil {
    public static final String DATE_FORMAT = "dd.MM.yyyy_HH:mm";

    private DateUtil() {
        // empty
    }

    public static Date now() {
        return new Date();
    }

    public static LocalDateTime nowLocalDateTime() {
        return LocalDateTime.now();
    }

    public static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static boolean isFirstBetween(Date dateToCheck, Date before, Date after) {
        return isFirstBefore(before, dateToCheck) && isFirstAfter(after, dateToCheck);
    }

    public static boolean isFirstBefore(Date first, Date second) {
        return first.before(second);
    }

    public static boolean isFirstBefore(LocalDateTime first, LocalDateTime second) {
        return first.isBefore(second);
    }

    public static boolean isFirstAfter(Date first, Date second) {
        return first.after(second);
    }

    public static boolean isFirstAfter(LocalDateTime first, LocalDateTime second) {
        return first.isAfter(second);
    }

    public static LocalDateTime getLocalDateTimeWithTime(Date date, LocalTime time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, time.getHour());
        calendar.set(Calendar.MINUTE, time.getMinute());
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return dateToLocalDateTime(calendar.getTime());
    }
}
