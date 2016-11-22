package ee.home.parkinghouse.util;

import java.time.LocalDateTime;
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

    public static boolean isFirstBetween(Date dateToCheck, Date before, Date after) {
        return isFirstBefore(before, dateToCheck) && isFirstAfter(after, dateToCheck);
    }

    public static boolean isFirstBefore(Date first, Date second) {
        return first.before(second);
    }

    public static boolean isFirstAfter(Date first, Date second) {
        return first.after(second);
    }
}
