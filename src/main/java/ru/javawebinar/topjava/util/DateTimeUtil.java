package ru.javawebinar.topjava.util;

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

    public static boolean isBetweenDate(LocalDate ld, LocalDate startDate, LocalDate endDate) {
        return ld.compareTo(startDate) >= 0 && ld.compareTo(endDate) <= 0;
    }

    // generic date-time utill
    public static boolean isBetweenDateOrTime(Temporal dt, Temporal startDt, Temporal endDt) {
        boolean found;
        if (startDt instanceof LocalDate && endDt instanceof LocalDate) {
            LocalDate date = LocalDate.from(dt);
            LocalDate startDate = LocalDate.from(startDt);
            LocalDate endDate = LocalDate.from(endDt);
            found = date.compareTo(startDate) >= 0 && date.compareTo(endDate) <= 0;
        } else {
            LocalTime time = LocalTime.from(dt);
            LocalTime startTime = LocalTime.from(startDt);
            LocalTime endTime = LocalTime.from(endDt);
            found = time.compareTo(startTime) >= 0 && time.compareTo(endTime) <= 0;
        }
        return found;
    }

    public static boolean isBetweenDateAndTime() {
        return false;
    }


    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }
}
