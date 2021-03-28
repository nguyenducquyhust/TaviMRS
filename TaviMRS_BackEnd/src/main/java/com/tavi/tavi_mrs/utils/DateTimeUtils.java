package com.tavi.tavi_mrs.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeUtils {

    public static Date asDate(LocalDate localDate) {
        return localDate == null ? null : Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date asDate(LocalDateTime localDateTime) {
        return localDateTime == null ? null : Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate asLocalDate(Date date) {
        return date == null ? null : Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime asLocalDateTime(Date date) {
        return date == null ? null : Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static String toDateString(Date date) {
        return date == null ? null : asLocalDateTime(date).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static String toDateTimeString(Date date) {
        return date == null ? null : asLocalDateTime(date).format(DateTimeFormatter.ofPattern("HH:mm:ss, dd/MM/yyyy"));
    }
}
