package net.fullstack10.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DateUtil {
    // java.sql.Timestamp -> java.time.LocalDateTime
    public static LocalDateTime toLocalDateTime(Timestamp date) {
        return date != null ? date.toLocalDateTime() : null;
    }
}
