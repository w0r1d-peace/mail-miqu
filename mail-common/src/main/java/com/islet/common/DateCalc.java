package com.islet.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期计算工具类
 *
 * @author tangJM.
 * @date 2018/4/26
 */
public class DateCalc {

    /**
     * 格式化当前日期
     *
     * @param timestamp
     * @return
     */
    public static String formatDate(Date timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("y-MM-dd");
        return sdf.format(timestamp);
    }

    /**
     * 格式化当前时间
     *
     * @param timestamp
     * @return
     */
    public static String formatDateTime(Date timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("y-MM-dd HH:mm:ss");
        return sdf.format(timestamp);
    }

    /**
     * 获取当前时间的前N个星期的星期天日期
     *
     * @param sub
     * @return
     */
    public static Date beforeWeeksDate(int sub) {
        return beforeWeeksDate(new Date(), sub);
    }

    /**
     * 获取某一天的前N个星期的星期天日期
     *
     * @param timestamp
     * @param sub
     * @return
     */
    public static Date beforeWeeksDate(Date timestamp, int sub) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(timestamp);
        instance.add(Calendar.WEEK_OF_YEAR, -sub);
        instance.set(Calendar.DAY_OF_WEEK, 1);
        instance.set(Calendar.HOUR_OF_DAY, 0);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.SECOND, 0);
        instance.set(Calendar.MILLISECOND, 0);
        return instance.getTime();
    }

    /**
     * 获取昨天的开始时间
     *
     * @return
     */
    public static Date convert2LastBeginDate() {
        return convert2LastBeginDate(new Date());
    }

    /**
     * 获取某天的前一天开始时间
     *
     * @param timestamp
     * @return
     */
    public static Date convert2LastBeginDate(Date timestamp) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(timestamp);
        instance.set(Calendar.HOUR_OF_DAY, 0);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.SECOND, 0);
        instance.set(Calendar.MILLISECOND, 0);
        instance.set(Calendar.DATE, instance.get(Calendar.DATE) - 1);
        return instance.getTime();
    }

    /**
     * 获取当天的结束时间
     *
     * @return
     */
    public static Date convert2EndDate() {
        return convert2EndDate(new Date());
    }

    /**
     * 获取当天的开始时间
     *
     * @return
     */
    public static Date convert2BeginDate() {
        return convert2BeginDate(new Date());
    }

    /**
     * 获取一天的开始时间
     *
     * @param timestamp
     * @return
     */
    public static Date convert2BeginDate(Date timestamp) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(timestamp);
        instance.set(Calendar.HOUR_OF_DAY, 0);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.SECOND, 0);
        instance.set(Calendar.MILLISECOND, 0);
        return instance.getTime();
    }

    /**
     * 获取一天的结束时间
     *
     * @param timestamp
     * @return
     */
    public static Date convert2EndDate(Date timestamp) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(timestamp);
        instance.set(Calendar.HOUR_OF_DAY, 23);
        instance.set(Calendar.MINUTE, 59);
        instance.set(Calendar.SECOND, 59);
        instance.set(Calendar.MILLISECOND, 999);
        return instance.getTime();
    }

    /**
     * 获取某天的前N天开始时间
     *
     * @param timestamp
     * @param sub       N
     * @return
     */
    public static Date beforeDate(Date timestamp, int sub) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(timestamp);
        instance.set(Calendar.HOUR_OF_DAY, 0);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.SECOND, 0);
        instance.set(Calendar.MILLISECOND, 0);
        instance.add(Calendar.DATE, -sub);
        return instance.getTime();
    }

    /**
     * 获取今天的前N天开始时间
     *
     * @param sub N
     * @return
     */
    public static Date beforeDate(int sub) {
        return beforeDate(new Date(), sub);
    }

    /**
     * 是否是星期六
     *
     * @param timestamp
     * @return
     */
    public static boolean isSunday(Date timestamp) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(timestamp);
        return instance.get(Calendar.DAY_OF_WEEK) == 1;
    }

    /**
     * 获取当前时间前N个月的第一天
     *
     * @param sub
     * @return
     */
    public static Date beforeMonthsDate(int sub) {
        return beforeMonthsDate(new Date(), sub);
    }

    /**
     * 获取某个时间前N个月的第一天
     *
     * @param timestamp
     * @param sub
     * @return
     */
    public static Date beforeMonthsDate(Date timestamp, int sub) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(timestamp);
        instance.set(Calendar.MONTH, -sub);
        instance.set(Calendar.DAY_OF_MONTH, 0);
        instance.set(Calendar.HOUR_OF_DAY, 0);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.SECOND, 0);
        instance.set(Calendar.MILLISECOND, 0);
        return instance.getTime();
    }

    public static boolean isMonthStartDay(Date timestamp) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(timestamp);
        return instance.get(Calendar.DAY_OF_MONTH) == instance.getActualMinimum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当前前i天的开始时间的日期格式
     *
     * @param i
     * @return
     */
    public static String beforeDateAsString(int i) {
        return DateCalc.formatDate(DateCalc.beforeDate(i));
    }

    /**
     * 将字符串转换成Date类型
     *
     * @param dateStr
     * @param pattern
     * @return
     */
    public static Date str2Date(String dateStr, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        try {
            Date parse = simpleDateFormat.parse(dateStr);
            return parse;
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    /**
     * 日期转换
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String formatTimestamp(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }
}
