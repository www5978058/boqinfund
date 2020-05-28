package com.shyb.boqinfund.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author wzh
 * @date 2019/6/19 - 11:40
 */
@Slf4j
public class DateUtils {
    private static final String DEFAULT_FORMAT = "YYYY-MM-dd HH:mm:ss";
    public static final String CN_FORMAT = "YYYY年MM月dd日HH时mm分ss秒";

    /**
     * 默认解析时间
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        return formatDate(date, DEFAULT_FORMAT);
    }

    /**
     * 指定格式解析时间
     *
     * @param date
     * @param format
     * @return
     */
    public static String formatDate(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 在日期上增加数个整月
     *
     * @param date 日期
     * @param n    要增加的月数
     * @return 日期
     */
    public static Date addMonth(Date date, int n) {
        return addTime(date,n,Calendar.MONTH);
    }

    /**
     * 在日期上增加天数
     *
     * @param date 日期
     * @param n    要增加的天数
     * @return
     */
    public static Date addDay(Date date, int n) {
        return addTime(date,n,Calendar.DATE);
    }

    /**
     * 在当前日期上添加小时数
     *
     * @param date
     * @param hour
     * @return
     */
    public static Date addHour(Date date, double hour) {
        Long time = date.getTime();
        Double times = time + hour * 60 * 60 * 1000;
        return new Date(Math.round(times));
    }

    /**
     * 在当前日期上添加分钟数
     *
     * @param date
     * @param mins
     * @return
     */
    public static Date addMinute(Date date, double mins) {
        Long time = date.getTime();
        Double times = time + mins * 60 * 1000;
        return new Date(Math.round(times));
    }

    private static Date addTime(Date date,int n, int calendarType) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(calendarType, n);
        return cal.getTime();
    }

    /**
     * 计算两个日期的间隔天数
     *
     * @return
     */
    public static int countDays(Date start, Date end) {
        long t1 = start.getTime();
        long t2 = end.getTime();
        return (int) ((t2 / 1000 - t1 / 1000) / 3600 / 24);
    }

    /**
     * 计算间隔小时数 -带半小时(不足半小时按照半小时 超过半小时按照一个小时算)
     *
     * @return
     */
    public static double countHour(Date start, Date end) {
        long t1 = start.getTime();
        long t2 = end.getTime();
        int hour = (int) ((t2 / 1000 - t1 / 1000) / 3600);
        double halfHour = 0;
        if ((countMins(start, end) - hour * 60) > 30) {
            halfHour = 1;
        } else if ((countMins(start, end) - hour * 60) <= 30 && (countMins(start, end) - hour * 60) > 0) {
            halfHour = 0.5;
        }
        return hour + halfHour;
    }

    /**
     * 计算两个日期的间隔分钟数
     *
     * @return
     */
    public static int countMins(Date start, Date end) {
        start = parse(format(start, "yyyy-MM-dd HH:mm") + ":00", DEFAULT_FORMAT);
        end = parse(format(end, "yyyy-MM-dd HH:mm") + ":00", DEFAULT_FORMAT);
        long t1 = start.getTime();
        long t2 = end.getTime();
        return (int) ((t2 - t1) / 1000 / 60);
    }

    /**
     * 使用用户格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @param pattern 日期格式
     * @return
     */
    public static Date parse(String strDate, String pattern) {
        if (StringUtils.isBlank(strDate)) {
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            return null;
        }
    }
    /**
     * 使用用户格式格式化日期
     *
     * @param date    日期
     * @param pattern 日期格式
     * @return
     */

    public static String format(Date date, String pattern) {
        String returnValue = "";
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            returnValue = df.format(date);
        }
        return (returnValue);
    }

    /**
     * String 转  Date
     *
     * @param dateTime
     * @return
     * @author hanzhonghua
     */
    public static Date parseDate(String dateTime) {
        try {
            return org.apache.commons.lang3.time.
                    DateUtils.parseDate(dateTime, new String[]{
                    "yyyy-MM-dd HH:mm:ss",
                    "yyyy/MM/dd HH:mm:ss",
                    "yyyy-MM-dd HH:mm",
                    "yyyy/MM/dd HH:mm",
                    "yyyy-MM-dd",
                    "yyyyMMdd",
                    "yyyy年MM月dd日",
                    "yyyy/MM/dd"});
        } catch (Exception e1) {
            log.error("日期参数错误，错误信息为：{}", e1.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(countDays(parseDate("2019-6-24 12:00:00"), parseDate("2019-6-25 12:00:00")));
    }

}
