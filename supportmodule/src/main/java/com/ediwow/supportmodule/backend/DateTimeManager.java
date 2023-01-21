package com.ediwow.supportmodule.backend;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class DateTimeManager {
    private static final SimpleDateFormat SDF_DATE_SQL = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH), SDF_DATE_READABLE = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH), SDF_TIMESTAMP_SQL = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH), SDF_TIMESTAMP_READABLE = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.ENGLISH), SDF_DATE_STRAIGHT = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH), SDF_TIMESTAMP_STRAIGHT = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH), SDF_TIME_SQL_READABLE = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH), SDF_TIME_STRAIGHT = new SimpleDateFormat("HHmmss", Locale.ENGLISH), SDF_BILLMONTH_SQL = new SimpleDateFormat("yyyyMM", Locale.ENGLISH), SDF_BILLMONTH_READABLE = new SimpleDateFormat("MMM yyyy", Locale.ENGLISH);

    public static class ToDateObject {
        public static Date fromSQL(String sDate) throws Exception {
            return (sDate == null) ? null : SDF_DATE_SQL.parse(sDate);
        }

        public static Date fromReadable(String sDate) throws Exception {
            return (sDate == null) ? null : SDF_DATE_READABLE.parse(sDate);
        }

        public static Date fromStraight(String sDate) throws Exception {
            return (sDate == null) ? null : SDF_DATE_STRAIGHT.parse(sDate);
        }
    }

    public static class BillMonth {
        public static String fromSQLtoReadable(int billMonth) throws Exception {
            return SDF_BILLMONTH_READABLE.format(Objects.requireNonNull(SDF_BILLMONTH_SQL.parse(String.valueOf(billMonth))));
        }
    }

    public static class ToTimeObject {
        public static Date fromReadableSQL(String sTime) throws Exception {
            return (sTime == null) ? null : SDF_TIME_SQL_READABLE.parse(sTime);
        }

        public static Date fromStraight(String sTime) throws Exception {
            return (sTime == null) ? null : SDF_TIME_STRAIGHT.parse(sTime);
        }
    }

    public static class ToTimestampObject {
        public static Date fromSQL(String sTimestamp) throws Exception {
            return (sTimestamp == null) ? null : SDF_TIMESTAMP_SQL.parse(sTimestamp);
        }

        public static Date fromReadable(String sTimestamp) throws Exception {
            return (sTimestamp == null) ? null : SDF_TIMESTAMP_READABLE.parse(sTimestamp);
        }

        public static Date fromStraight(String sTimestamp) throws Exception {
            return (sTimestamp == null) ? null : SDF_TIMESTAMP_STRAIGHT.parse(sTimestamp);
        }
    }

    public static class ToFormat {
        public static String toSQLDate(Date date) {
            return (date == null) ? null : SDF_DATE_SQL.format(date);
        }

        public static String toSQLTimestamp(Date timestamp) {
            return (timestamp == null) ? null : SDF_TIMESTAMP_SQL.format(timestamp);
        }


        public static String toReadableDate(Date date) {
            return (date == null) ? null : SDF_DATE_READABLE.format(date);
        }

        public static String toReadableTime(Date time) {
            return (time == null) ? null : SDF_TIME_SQL_READABLE.format(time);
        }

        public static String toReadableTimestamp(Date timestamp) {
            return (timestamp == null) ? null : SDF_TIMESTAMP_READABLE.format(timestamp);
        }

        public static String toStraightDate(Date date) {
            return (date == null) ? null : SDF_DATE_STRAIGHT.format(date);
        }

        public static String toStraightTime(Date time) {
            return (time == null) ? null : SDF_TIME_STRAIGHT.format(time);
        }

        public static String toStraightTimestamp(Date timestamp) {
            return (timestamp == null) ? null : SDF_TIMESTAMP_STRAIGHT.format(timestamp);
        }
    }
}
