package ru.javawebinar.topjava.util;

import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static boolean isBetween(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) <= 0;
    }
    // generic date-time utill
    public static boolean isBetweenDateOrTime(Temporal dt, Temporal startDt, Temporal endDt) {
        if (startDt instanceof LocalDate && endDt instanceof LocalDate) {
            LocalDate date = LocalDate.from(dt);
            LocalDate sDate = LocalDate.from(startDt);
            LocalDate eDate = LocalDate.from(endDt);
            return date.compareTo(sDate) >= 0 && date.compareTo(eDate) <= 0;
        } else {
            LocalTime time = LocalTime.from(dt);
            LocalTime sTime = LocalTime.from(startDt);
            LocalTime eTime = LocalTime.from(endDt);
            return time.compareTo(sTime) >= 0 && time.compareTo(eTime) <= 0;
        }
    }

    public static LocalDate parseLocalDate(String str) {
        return StringUtils.isEmpty(str) ? null : LocalDate.parse(str);
    }

    public static LocalTime parseLocalTime(String str) {
        return StringUtils.isEmpty(str) ? null : LocalTime.parse(str);
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }
}
