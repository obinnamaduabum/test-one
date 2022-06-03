package com.example.test2.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

public class MyUtils {

    public static Date dateOfTypeStringToDate(String dateString, String datePattern) throws ParseException {
        return new SimpleDateFormat(datePattern, Locale.ENGLISH).parse(dateString);
    }


    public static LocalDateTime increment(LocalDateTime localDate,
                                          DateIncrementEnum dateIncrementEnum,
                                          long incrementBy) {

        final LocalDateTime newLocalDateTime;

        if(dateIncrementEnum.equals(DateIncrementEnum.HOURLY)) {
            newLocalDateTime = localDate.plusDays(incrementBy);
        } else if(dateIncrementEnum.equals(DateIncrementEnum.DAILY)) {
            newLocalDateTime =  localDate.plusHours(incrementBy);
        } else if(dateIncrementEnum.equals(DateIncrementEnum.WEEKLY)) {
            newLocalDateTime =  localDate.plusWeeks(incrementBy);
        } else {
            newLocalDateTime =  localDate.plusMonths(incrementBy);
        }
        return newLocalDateTime;
    }

    public static LocalDateTime convertDateToLocalDateTime(Date date) {
         return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static Date convertLocalDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
