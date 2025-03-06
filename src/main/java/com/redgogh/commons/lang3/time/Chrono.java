package com.redgogh.commons.lang3.time;

import java.time.*;
import java.util.Date;

/**
 * @author Red Gogh
 */
public class Chrono implements ChronoTemporal {

    private final LocalDateTime _core_local_time;

    private final ZoneId zoneId;

    private final Date date;

    private final LocalDate localDate;

    private final long timestamp;

    private static final ZoneId SYSTEM_DEFAULT_ZONE_ID = ZoneId.systemDefault();

    // non-required zone id

    public Chrono(int year, int month) {
        this(year, month, 1, 0, 0, 0, 0);
    }

    public Chrono(int year, int month, int day) {
        this(year, month, day, 0, 0, 0);
    }

    public Chrono(int year, int month, int day, int hour) {
        this(year, month, day, hour, 0, 0, 0);
    }

    public Chrono(int year, int month, int day, int hour, int minute) {
        this(year, month, day, hour, minute, 0, 0);
    }

    public Chrono(int year, int month, int day, int hour, int minute, int second) {
        this(year, month, day, hour, minute, second, 0);
    }

    public Chrono(int year, int month, int day, int hour, int minute, int second, int millis) {
        this(LocalDateTime.of(year, month, day, hour, minute, second, millis));
    }

    public Chrono(LocalDateTime localDateTime) {
        this(localDateTime.atZone(SYSTEM_DEFAULT_ZONE_ID).toInstant().toEpochMilli());
    }

    public Chrono(long timestamp) {
        this(timestamp, SYSTEM_DEFAULT_ZONE_ID);
    }

    // required zone id

    public Chrono(int year, int month, ZoneId zoneId) {
        this(year, month, 1, 0, 0, 0, 0, zoneId);
    }

    public Chrono(int year, int month, int day, ZoneId zoneId) {
        this(year, month, day, 0, 0, 0, zoneId);
    }

    public Chrono(int year, int month, int day, int hour, ZoneId zoneId) {
        this(year, month, day, hour, 0, 0, 0, zoneId);
    }

    public Chrono(int year, int month, int day, int hour, int minute, ZoneId zoneId) {
        this(year, month, day, hour, minute, 0, 0, zoneId);
    }

    public Chrono(int year, int month, int day, int hour, int minute, int second, ZoneId zoneId) {
        this(year, month, day, hour, minute, second, 0, zoneId);
    }

    public Chrono(int year, int month, int day, int hour, int minute, int second, int millis, ZoneId zoneId) {
        this(LocalDateTime.of(year, month, day, hour, minute, second, millis), zoneId);
    }

    public Chrono(LocalDateTime localDateTime, ZoneId zoneId) {
        this(localDateTime.atZone(zoneId).toInstant().toEpochMilli(), zoneId);
    }

    public Chrono(long timestamp, ZoneId zoneId) {
        // serialize to _local_time
        _core_local_time = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), zoneId);

        // initialize
        this.zoneId = zoneId;
        this.timestamp = _core_local_time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        this.date = new Date(timestamp);
        this.localDate = _core_local_time.toLocalDate();
    }

    public static void main(String[] args) {
        Chrono chrono = new Chrono(2025, 3, 6);

        System.out.printf("plus Weeks 1: %s\n", chrono.plusWeeks(1));

        System.out.printf("Day of week: %s\n", chrono.getDayOfWeek());
        System.out.printf("Day of month: %s\n", chrono.getDayOfMonth());
        System.out.printf("Day of year: %s\n", chrono.getDayOfYear());
    }

    @Override
    public ZonedDateTime toZoneDateTime(String zoneId) {
        return toZoneDateTime(ZoneId.of(zoneId));
    }

    @Override
    public ZonedDateTime toZoneDateTime(ZoneId zoneId) {
        return _core_local_time.atZone(zoneId);
    }

    @Override
    public ZonedDateTime toZonedDateTime() {
        return _core_local_time.atZone(zoneId);
    }

    @Override
    public ZoneId getZoneId() {
        return zoneId;
    }

    @Override
    public long getTime() {
        return timestamp;
    }

    @Override
    public Date toDate() {
        return date;
    }

    @Override
    public LocalDate toLocalDate() {
        return localDate;
    }

    @Override
    public LocalDateTime toLocalDateTime() {
        return _core_local_time;
    }

    @Override
    public Chrono plusNanos(int value) {
        return new Chrono(_core_local_time.plusNanos(value));
    }

    @Override
    public Chrono plusMillis(int value) {
        return new Chrono(_core_local_time.plusNanos((long) value * 1000));
    }

    @Override
    public Chrono plusSeconds(int value) {
        return new Chrono(_core_local_time.plusSeconds(value));
    }

    @Override
    public Chrono plusMinutes(int value) {
        return new Chrono(_core_local_time.plusMinutes(value));
    }

    @Override
    public Chrono plusHours(int value) {
        return new Chrono(_core_local_time.plusHours(value));
    }

    @Override
    public Chrono plusDays(int value) {
        return new Chrono(_core_local_time.plusDays(value));
    }

    @Override
    public Chrono plusWeeks(int value) {
        return new Chrono(_core_local_time.plusWeeks(value));
    }

    @Override
    public Chrono plusMonths(int value) {
        return new Chrono(_core_local_time.plusMonths(value));
    }

    @Override
    public Chrono plusYears(int value) {
        return new Chrono(_core_local_time.plusYears(value));
    }

    @Override
    public Chrono minusNanos(int value) {
        return new Chrono(_core_local_time.minusNanos(value));
    }

    @Override
    public Chrono minusMillis(int value) {
        return new Chrono(_core_local_time.minusNanos((long) value * 1000));
    }

    @Override
    public Chrono minusSeconds(int value) {
        return new Chrono(_core_local_time.minusSeconds(value));
    }

    @Override
    public Chrono minusMinutes(int value) {
        return new Chrono(_core_local_time.minusMinutes(value));
    }

    @Override
    public Chrono minusHours(int value) {
        return new Chrono(_core_local_time.minusHours(value));
    }

    @Override
    public Chrono minusDays(int value) {
        return new Chrono(_core_local_time.minusDays(value));
    }

    @Override
    public Chrono minusWeeks(int value) {
        return new Chrono(_core_local_time.minusWeeks(value));
    }

    @Override
    public Chrono minusMonths(int value) {
        return new Chrono(_core_local_time.minusMonths(value));
    }

    @Override
    public Chrono minusYears(int value) {
        return new Chrono(_core_local_time.minusYears(value));
    }

    @Override
    public long betweenNanos(Chrono chrono) {
        return 0;
    }

    @Override
    public long betweenMillis(Chrono chrono) {
        return 0;
    }

    @Override
    public long betweenSeconds(Chrono chrono) {
        return 0;
    }

    @Override
    public long betweenMinutes(Chrono chrono) {
        return 0;
    }

    @Override
    public long betweenHours(Chrono chrono) {
        return 0;
    }

    @Override
    public long betweenDays(Chrono chrono) {
        return 0;
    }

    @Override
    public long betweenWeeks(Chrono chrono) {
        return 0;
    }

    @Override
    public long betweenMonths(Chrono chrono) {
        return 0;
    }

    @Override
    public long betweenYears(Chrono chrono) {
        return 0;
    }

    @Override
    public long betweenNanos(Date date) {
        return 0;
    }

    @Override
    public long betweenMillis(Date date) {
        return 0;
    }

    @Override
    public long betweenSeconds(Date date) {
        return 0;
    }

    @Override
    public long betweenMinutes(Date date) {
        return 0;
    }

    @Override
    public long betweenHours(Date date) {
        return 0;
    }

    @Override
    public long betweenDays(Date date) {
        return 0;
    }

    @Override
    public long betweenWeeks(Date date) {
        return 0;
    }

    @Override
    public long betweenMonths(Date date) {
        return 0;
    }

    @Override
    public long betweenYears(Date date) {
        return 0;
    }

    @Override
    public int getMillis() {
        return getSeconds() * 1000;
    }

    @Override
    public int getSeconds() {
        return _core_local_time.getSecond();
    }

    @Override
    public int getMinutes() {
        return _core_local_time.getMinute();
    }

    @Override
    public int getHours() {
        return _core_local_time.getHour();
    }

    @Override
    public int getDayOfWeek() {
        return _core_local_time.getDayOfWeek().getValue();
    }

    @Override
    public int getDayOfMonth() {
        return _core_local_time.getDayOfMonth();
    }

    @Override
    public int getDayOfYear() {
        return _core_local_time.getDayOfYear();
    }

    @Override
    public int getWeekOfMonth() {
        return 0;
    }

    @Override
    public int getWeekOfYear() {
        return 0;
    }

    @Override
    public int getMonths() {
        return 0;
    }

    @Override
    public int getYears() {
        return 0;
    }

    @Override
    public String format() {
        return format("yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public String format(String pattern) {
        return DateFormatter.format(toDate(), pattern);
    }

    @Override
    public String toString() {
        return format();
    }
}
