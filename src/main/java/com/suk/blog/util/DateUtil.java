//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.suk.blog.util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    private static Logger logger = LoggerFactory.getLogger(DateUtil.class);
    public static String DATE_FORMAT = "yyyy-MM-dd";
    public static String YEAR_MONTH_FORMAT = "yyyy-MM";
    public static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public DateUtil() {
    }

    public static Date string2date(String value) {
        if (value == null) {
            return null;
        } else {
            SimpleDateFormat sdf = null;
            if (value.length() == 7) {
                sdf = new SimpleDateFormat(YEAR_MONTH_FORMAT);
            } else {
                sdf = new SimpleDateFormat(DATE_FORMAT);
            }

            try {
                return sdf.parse(value);
            } catch (ParseException var3) {
                logger.error(var3.getMessage(), var3);
                throw new RuntimeException(var3);
            }
        }
    }

    public static Timestamp string2timestamp(String dateStr) {
        if (dateStr == null) {
            return null;
        } else {
            String[] ds = dateStr.split("-");
            String[] dh = dateStr.split(" ");
            if (ds.length == 3) {
                return dh.length == 2 ? Timestamp.valueOf(dateStr) : Timestamp.valueOf(dateStr + " 00:00:00");
            } else {
                return null;
            }
        }
    }

    public static Date string2date(String value, String format) {
        if (value == null) {
            return null;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(format);

            try {
                return sdf.parse(value);
            } catch (ParseException var4) {
                logger.error(var4.getMessage(), var4);
                throw new RuntimeException(var4);
            }
        }
    }

    /**
     * 字符串自动转日期
     *
     * @param value
     * @return
     */
    public static Date string2date2(String value) {
        if (value == null) {
            return null;
        } else {
            String substring = DATE_TIME_FORMAT.substring(0, value.length());
            SimpleDateFormat sdf = new SimpleDateFormat(substring);
            try {
                return sdf.parse(value);
            } catch (ParseException var3) {
                logger.error(var3.getMessage(), var3);
                throw new RuntimeException(var3);
            }
        }
    }

    /**
     * 获取某年第一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 获取某年最后一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearLast(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
        return currYearLast;
    }

    public static String date2string(Date date) {
        if (date == null) {
            return null;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            return sdf.format(date);
        }
    }

    public static String date2string(Date date, String format) {
        if (date == null) {
            return null;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        }
    }

    public static String getNowDate(String format) {
        String dateTime = "";

        try {
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            dateTime = sdf.format(now);
        } catch (Exception var4) {
            logger.error(var4.getMessage(), var4);
        }

        return dateTime;
    }

    public static String getNowDate() {
        return getNowDate(DATE_FORMAT);
    }

    public static String getNowDateTime() {
        return getNowDate(DATE_TIME_FORMAT);
    }

    public static long getDaysInterval(Date date, Date date2) {
        return (date2.getTime() - date.getTime()) / 86400000L;
    }

    public static double getDueDay(Date dueDate) {
        return (double) (((new Date()).getTime() - dueDate.getTime()) * 100L / 86400000L) / 100.0D;
    }

    public static Date getDate(int days) {
        Date date = new Date();
        date.setTime(date.getTime() + 86400000L * (long) days);
        return date;
    }

    public static Date getDate(Date date, int days) {
        Date date2Use = new Date(date.getTime() + 86400000L * (long) days);
        return date2Use;
    }

    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(1);
    }

    public static int getNowYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return calendar.get(1);
    }

    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(2) + 1;
    }

    public static int getNowMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return calendar.get(2) + 1;
    }

    public static int getDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(7);
    }

    public static int getNowDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return calendar.get(7);
    }

    public static int getDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(5);
    }

    public static int getNowDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return calendar.get(5);
    }

    public static Timestamp getNowTimestamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp;
    }

    public static String getNowYearMonthDayWeek() {
        return getNowDate("yyyy年M月d日 H:m:s") + " 星期" + "日一二三四五六".charAt(getNowDayOfWeek() - 1);
    }

    public static String getMonthStatDay(String year, String month) {
        return month != null && year != null ? year + "-" + month + "-01" : null;
    }

    public static String getMonthStatDay(Date date) {
        return date == null ? null : date2string(date, "yyyy-MM-01");
    }

    public static String getMonthEndDay(Date date) {
        if (date == null) {
            return null;
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            return date2string(date, "yyyy-MM-") + c.getActualMaximum(5);
        }
    }

    public static String getMonthEndDay(String year, String month) {
        if (month != null && year != null) {
            Calendar c = Calendar.getInstance();
            c.set(1, Integer.valueOf(year));
            c.set(2, Integer.valueOf(month) - 1);
            return year + "-" + month + "-" + c.getActualMaximum(5);
        } else {
            return null;
        }
    }

/*    public static Long wdhms2s(String wdhms) {
        if (StringUtils.isEmpty(wdhms)) {
            return null;
        } else {
            Object[][] wdhm4s = new Object[][]{{"w", 144000L}, {"d", 28800L}, {"h", 3600L}, {"m", 60L}, {"s", 1L}};
            String[] ts = wdhms.toLowerCase().replace("  ", " ").split(",| ");
            Long s = 0L;
            String[] arr$ = ts;
            int len$ = ts.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                String t = arr$[i$];
                Object[][] arr$ = wdhm4s;
                int len$ = wdhm4s.length;

                for(int j$ = 0; j$ < len$; ++j$) {
                    Object[] arr = arr$[i$];
                    if (t.endsWith(arr[0].toString()) && t.length() > 1) {
                        String str = t.substring(0, t.length() - 1);
                        s = s + (Long)arr[1] * Long.valueOf(str);
                    }
                }
            }

            return s;
        }
    }*/

    public static String s2wdhms(Long s) {
        if (s == null) {
            return null;
        } else {
            Object[][] wdhm4s = new Object[][]{{"w", 144000L}, {"d", 28800L}, {"h", 3600L}, {"m", 60L}, {"s", 1L}};
            String wdhms = "";
            Object[][] arr$ = wdhm4s;
            int len$ = wdhm4s.length;

            for (int i$ = 0; i$ < len$; ++i$) {
                Object[] arr = arr$[i$];
                Long w = s / (Long) arr[1];
                if (w > 0L) {
                    wdhms = wdhms + (wdhms.length() > 0 ? " " : "") + w + arr[0].toString();
                    s = s % (Long) arr[1];
                }
            }

            return wdhms;
        }
    }

    public static String getWdhmsInterval(Date date, Date date2) {
        return date != null && date2 != null ? s2wdhms((date2.getTime() - date.getTime()) / 1000L) : null;
    }

    public static Date firstDayOfLastMonth() {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.add(2, -1);
        aCalendar.set(5, 1);
        Date date = aCalendar.getTime();
        return date;
    }

    public static Date lastDayOfLastMonth() {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.add(2, -1);
        aCalendar.set(5, aCalendar.getActualMaximum(5));
        Date date = aCalendar.getTime();
        return date;
    }

    public static Date firstDayOfLastWeek() {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.add(3, -1);
        c.set(7, 2);
        return c.getTime();
    }

    public static Date lastDayOfLastWeek() {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.set(7, 1);
        c.add(5, 0);
        return c.getTime();
    }

    public static int getCurrentDate() {
        Date date = new Date();
        String dateStr = (new SimpleDateFormat("yyyyMMdd")).format(date);
        return Integer.parseInt(dateStr);
    }

    public static Timestamp getTodayBegin() {
        Calendar cal = Calendar.getInstance();
        cal.set(11, 0);
        cal.set(13, 0);
        cal.set(12, 0);
        cal.set(14, 0);
        return new Timestamp(cal.getTimeInMillis());
    }

    public static Timestamp getTodayEnd() {
        Calendar cal = Calendar.getInstance();
        cal.set(11, 23);
        cal.set(13, 59);
        cal.set(12, 59);
        cal.set(14, 999);
        return new Timestamp(cal.getTimeInMillis());
    }

    public static Timestamp getCurrMonthBegin() {
        Calendar cal = Calendar.getInstance();
        cal.set(5, 1);
        cal.set(11, 0);
        cal.set(13, 0);
        cal.set(12, 0);
        cal.set(14, 0);
        return new Timestamp(cal.getTimeInMillis());
    }

    public static Timestamp getCurrMonthEnd() {
        Calendar cal = Calendar.getInstance();
        cal.set(5, cal.getActualMaximum(5));
        cal.set(11, 23);
        cal.set(13, 59);
        cal.set(12, 59);
        cal.set(14, 999);
        return new Timestamp(cal.getTimeInMillis());
    }

    /***
     *
     * @param date
     * @param dateFormat : e.g:yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String formatDateByPattern(Date date,String dateFormat){
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String formatTimeStr = null;
        if (date != null) {
            formatTimeStr = sdf.format(date);
        }
        return formatTimeStr;
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static final String datePath()
    {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * 日期路径 即年/月/日 如20180808
     */
    public static final String dateTime()
    {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyyMMdd");
    }
    /***
     * convert Date to cron ,eg.  "0 06 10 15 1 ? 2014"
     * @param date  : 时间点
     * @return
     */
    public static String getCron(Date  date){
        String dateFormat="ss mm HH dd MM ? yyyy";
        return formatDateByPattern(date, dateFormat);
    }


    public static Date addSeconds(Date date1, long secs) {
        return new Date(date1.getTime() + (secs * 1000));
    }
}
