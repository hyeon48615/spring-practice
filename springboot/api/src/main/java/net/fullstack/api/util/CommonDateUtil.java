package net.fullstack.api.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class CommonDateUtil {
    public CommonDateUtil() {}

    public static LocalDate getLocalDateFromString(String date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(date, formatter);
    }
    public static LocalDateTime getLocalDateTimeFromString(String date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(date, formatter);
    }
}
