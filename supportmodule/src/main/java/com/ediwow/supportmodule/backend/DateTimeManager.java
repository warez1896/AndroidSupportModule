package com.ediwow.supportmodule.backend;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeManager {
    private static final SimpleDateFormat SDF_DATE_SQL = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH), SDF_DATE_READABLE = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH), SDF_TIMESTAMP_SQL = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH), SDF_TIMESTAMP_READABLE = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.ENGLISH), SDF_DATE_STRAIGHT = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH), SDF_TIMESTAMP_STRAIGHT = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH);

    public static class ToDateObject {
        public static Date fromSQL(String sDate) throws Exception {
            if (sDate == null)
                return null;
            return SDF_DATE_SQL.parse(sDate);
        }

        public static Date fromReadable(String sDate) throws Exception {
            if (sDate == null)
                return null;
            return SDF_DATE_READABLE.parse(sDate);
        }

        public static Date fromStraight(String sDate) throws Exception {
            if (sDate == null)
                return null;
            return SDF_DATE_STRAIGHT.parse(sDate);
        }
    }

    public static class ToTimestampObject {
        public static Date fromSQL(String sTimestamp) throws Exception {
            if (sTimestamp == null)
                return null;
            return SDF_TIMESTAMP_SQL.parse(sTimestamp);
        }

        public static Date fromReadable(String sTimestamp) throws Exception {
            if (sTimestamp == null)
                return null;
            return SDF_TIMESTAMP_READABLE.parse(sTimestamp);
        }

        public static Date fromStraight(String sTimestamp) throws Exception {
            if (sTimestamp == null)
                return null;
            return SDF_TIMESTAMP_STRAIGHT.parse(sTimestamp);
        }
    }

    public static class ToFormat {
        public static String toSQLDate(Date date) {
            if (date == null)
                return null;
            return SDF_DATE_SQL.format(date);
        }

        public static String toSQLTimestamp(Date timestamp) {
            if (timestamp == null)
                return null;
            return SDF_TIMESTAMP_SQL.format(timestamp);
        }

        public static String toReadableDate(Date date) {
            if (date == null)
                return null;
            return SDF_DATE_READABLE.format(date);
        }

        public static String toReadableTimestamp(Date timestamp) {
            if (timestamp == null)
                return null;
            return SDF_TIMESTAMP_READABLE.format(timestamp);
        }

        public static String toStraightDate(Date date) {
            if (date == null)
                return null;
            return SDF_DATE_STRAIGHT.format(date);
        }

        public static String toStraightTimestamp(Date timestamp) {
            if (timestamp == null)
                return null;
            return SDF_TIMESTAMP_STRAIGHT.format(timestamp);
        }
    }
}
