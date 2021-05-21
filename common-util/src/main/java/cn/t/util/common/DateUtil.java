package cn.t.util.common;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

public class DateUtil {

    private static final String YYYY_MM_DD = "yyyy-MM-dd";
    private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    private static final String YYYY_MM_DD_HH_MM_SS_Z = "yyyy-MM-dd HH:mm:ssZ";

    public static final SimpleDateFormat YYYY_MM_DD_SDF = new SimpleDateFormat(YYYY_MM_DD);
    public static final SimpleDateFormat YYYY_MM_DD_HH_MM_SS_SDF = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
    public static final SimpleDateFormat YYYY_MM_DD_HH_MM_SS_Z_SDF = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS_Z);

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(YYYY_MM_DD);

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS);

    public static String nowDateString() {
        return LocalDate.now().format(dateFormatter);
    }

    public static String nowDateTimeString() {
        return LocalDateTime.now().format(dateTimeFormatter);
    }

    public static LocalDate parseLocalDate(String str) {
        return LocalDate.parse(str, dateFormatter);
    }

    public static String formatLocalDate(LocalDate localDate) {
        return localDate.format(dateFormatter);
    }

    public static LocalDateTime parseLocalDateTime(String str) {
        return LocalDateTime.parse(str, dateTimeFormatter);
    }

    public static String formatLocalDateTime(LocalDateTime localDateTime) {
        return localDateTime.format(dateTimeFormatter);
    }

    public static long localDateTimeToMills(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime).getTime();
    }

    public static LocalDate getBeginDateOfMonth() {
        return LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
    }

    public static LocalDateTime getBeginDateTimeOfMonth() {
        return LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay();
    }

    public static LocalDateTime getBeginDateTimeOfToday() {
        return LocalDate.now().atStartOfDay();
    }

    public static LocalDateTime getBeginDateTimeOfYesterday() {
        return LocalDate.now().minusDays(1).atStartOfDay();
    }

    public static long getBeginTimestampOfToday() {
        return Timestamp.from(getBeginDateTimeOfToday().atOffset(ZoneOffset.UTC).toInstant()).getTime();
    }

    public static long getBeginTimestampOfYesterday() {
        return Timestamp.from(getBeginDateTimeOfYesterday().atOffset(ZoneOffset.UTC).toInstant()).getTime();
    }

    public static LocalDateTime getBeginDateTimeOfToday(ZoneId zoneId) {
        return LocalDate.now(zoneId).atStartOfDay();
    }

    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
    }
    public static LocalDate convertToLocalDateViaMillSecond(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
    }

    public static LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

    public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime();
    }

    public static LocalDateTime convertToLocalDateTimeViaMillSecond(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime();
    }

    public static LocalDateTime convertToLocalDateTimeViaSqlTimestamp(Date dateToConvert) {
        return new java.sql.Timestamp(
            dateToConvert.getTime()).toLocalDateTime();
    }


    public static Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }


    public static Date convertToDateViaInstant(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
            .atZone(ZoneId.systemDefault())
            .toInstant());
    }


    public static Date convertToDateViaSqlTimestamp(LocalDateTime dateToConvert) {
        return java.sql.Timestamp.valueOf(dateToConvert);
    }

    public static Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return java.util.Date
            .from(dateToConvert.atZone(ZoneId.systemDefault())
                .toInstant());
    }


    public static LocalDateTime convertToDateTime(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    public static long convertToTimestamp(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return instant.toEpochMilli();
    }

    public static String convertToDate(Date date) {
        synchronized (YYYY_MM_DD_SDF) {
            return YYYY_MM_DD_SDF.format(date);
        }
    }

    public static String convertToDateTimeString(Date date) {
        synchronized (YYYY_MM_DD_HH_MM_SS_SDF) {
            return YYYY_MM_DD_HH_MM_SS_SDF.format(date);
        }
    }

    public static String convertToZonedDateTimeString(Date date) {
        synchronized (YYYY_MM_DD_HH_MM_SS_Z_SDF) {
            return YYYY_MM_DD_HH_MM_SS_Z_SDF.format(date);
        }
    }


}
