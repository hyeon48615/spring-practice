package net.fullstack10.util;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class DateUtil {
    // java.sql.Timestamp -> java.time.LocalDateTime
    public static LocalDateTime toLocalDateTime(Timestamp date) {
        return date != null ? date.toLocalDateTime() : null;
    }

    // LocalDateTime -> 문자열
    public static String localDateTimeToString(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    // LocalDate -> 문자열
    public static String localDateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    // 문자열 -> LocalDateTime
    public static LocalDateTime toLocalDateTime(String date) {
        try {
            return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    // 문자열 -> LocalDate
    public static LocalDate toLocalDate(String date) {
        try {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static String toString(LocalDateTime date) {
        if (date == null) {
            return null;
        }

        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(date, now);

        long minutes = duration.toMinutes();
        long hours = duration.toHours();

        if (minutes < 60) {
            return minutes + "분 전";
        } else if (hours < 24) {
          return hours + "시간 전";
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return date.format(formatter);
        }
    }
}
