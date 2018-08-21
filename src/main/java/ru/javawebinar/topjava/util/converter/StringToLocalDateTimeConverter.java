package ru.javawebinar.topjava.util.converter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

/**
 * Created by Joanna Pakosh on Авг., 2018
 */
public class StringToLocalDateTimeConverter {

    public static class LocaleDateFormatter implements Formatter<LocalDate> {
        @Override
        public LocalDate parse(String text, Locale locale) throws ParseException {
            return parseLocalDate(text);
        }

        @Override
        public String print(LocalDate localDate, Locale locale) {
            return DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(localDate);
        }
    }

    public static class LocalTimeFormatter implements Formatter<LocalTime> {
        @Override
        public LocalTime parse(String text, Locale locale) throws ParseException {
            return parseLocalTime(text);
        }

        @Override
        public String print(LocalTime localTime, Locale locale) {
            return DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(localTime);
        }
    }
}