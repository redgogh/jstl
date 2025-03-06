package com.redgogh.commons.lang3.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public interface ChronoTemporal {

    ZonedDateTime toZoneDateTime(String zoneId);

    ZonedDateTime toZoneDateTime(ZoneId zoneId);

    ZonedDateTime toZonedDateTime();

    ZoneId getZoneId();

    long getTime();

    Date toDate();

    LocalDate toLocalDate();

    LocalDateTime toLocalDateTime();

    Chrono plusNanos(int value);

    Chrono plusMillis(int value);

    Chrono plusSeconds(int value);

    Chrono plusMinutes(int value);

    Chrono plusHours(int value);

    Chrono plusDays(int value);

    Chrono plusWeeks(int value);

    Chrono plusMonths(int value);

    Chrono plusYears(int value);

    Chrono minusNanos(int value);

    Chrono minusMillis(int value);

    Chrono minusSeconds(int value);

    Chrono minusMinutes(int value);

    Chrono minusHours(int value);

    Chrono minusDays(int value);

    Chrono minusWeeks(int value);

    Chrono minusMonths(int value);

    Chrono minusYears(int value);

    long betweenNanos(Chrono temporal);

    long betweenMillis(Chrono temporal);

    long betweenSeconds(Chrono temporal);

    long betweenMinutes(Chrono temporal);

    long betweenHours(Chrono temporal);

    long betweenDays(Chrono temporal);

    long betweenWeeks(Chrono temporal);

    long betweenMonths(Chrono temporal);

    long betweenYears(Chrono temporal);

    long betweenNanos(Date date);

    long betweenMillis(Date date);

    long betweenSeconds(Date date);

    long betweenMinutes(Date date);

    long betweenHours(Date date);

    long betweenDays(Date date);

    long betweenWeeks(Date date);

    long betweenMonths(Date date);

    long betweenYears(Date date);

    int getMillis();

    int getSeconds();

    int getMinutes();

    int getHours();

    int getDayOfWeek();

    int getDayOfMonth();

    int getDayOfYear();

    int getWeekOfMonth();

    int getWeekOfYear();

    int getMonths();

    int getYears();

    String format();

    String format(String pattern);

}
