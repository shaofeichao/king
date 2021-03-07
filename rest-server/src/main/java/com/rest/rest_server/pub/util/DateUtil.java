package com.rest.rest_server.pub.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期工具
 */
public class DateUtil {


    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYMMDD = "yyMMdd";

    public static String YYYYMMDD = "yyyyMMdd";

    public static String YYYYMM = "yyyyMM";

    public static String YYYY_MM_DDHHMMSS = "yyyy-MM-dd HH:mm:ss";

    public static String YYYYMMDDHHMMSS2 = "yyyyMMdd HH:mm:ss";

    // yyyy-MM-dd HH:mm
    public static String YYYY_MM_DDHHMM = "yyyy-MM-dd HH:mm";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    /*HH:mm:ss*/
    public static String HHMMSS = "HH:mm:ss";
    /*HHmmssSSS*/
    public static String HHMMSSSSS = "HHmmssSSS";
    /*MMddHHmmssSSS*/
    public static String MMddHHmmssSSS = "MMddHHmmssSSS";
    public static String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

    /**
     * 缺省月格式
     */
    public static final String DEFAULT_MONTH = "MONTH";

    /**
     * 缺省年格式
     */
    public static final String DEFAULT_YEAR = "YEAR";

    /**
     * 缺省日格式
     */
    public static final String DEFAULT_DATE = "DAY";

    /**
     * 缺省小时格式
     */
    public static final String DEFAULT_HOUR = "HOUR";

    /**
     * 缺省分钟格式
     */
    public static final String DEFAULT_MINUTE = "MINUTE";

    /**
     * 缺省秒格式
     */
    public static final String DEFAULT_SECOND = "SECOND";

    /**
     * 格式化当前日期
     *
     * @return
     */
    public static String format2String(String dateFormat) {
        return new SimpleDateFormat(dateFormat).format(new Date());
    }

    /**
     * 格式化制定日期
     *
     * @return
     */
    public static String format2String(String dateFormat, Date theDate) {
        if (theDate == null)
            return null;
        return new SimpleDateFormat(dateFormat).format(theDate);
    }

    /**
     * 字符串转日期格式；
     *
     * @return
     */
    public static Date getDateByString(String dateString, String dateFormat) throws ParseException {
        return new SimpleDateFormat(dateFormat).parse(dateString);
    }

    public static Calendar date2Calendar(Date date, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, i);
        return calendar;
    }

    public static String formatDateString(String strDate) {
        String date = null;
        if (strDate != null && strDate.length() >= 19) {
            date = strDate.substring(0, 19);
        }
        return date;
    }

    public static String getDateString(String strDate) {
        String date = null;
        if (strDate != null && strDate.length() >= 10) {
            date = strDate.substring(0, 10);
        }
        return date;
    }

    /**
     * 当前时间添加N月
     *
     * @return
     */
    public static String getAddMonth(int month, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.MONTH, month);

        return sdf.format(ca.getTime());
    }

    public static String getAddHourTime(int addHour) {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DDHHMMSS);

        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.HOUR_OF_DAY, addHour);

        return sdf.format(ca.getTime());
    }

    public static String getAddHourTimes(int addHour) {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDDHHMMSS2);

        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.HOUR_OF_DAY, addHour);

        return sdf.format(ca.getTime());
    }

    public static String addMinutes(int amount) {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DDHHMMSS);
        Calendar calendar = Calendar.getInstance();
        //calendar.setTime(date);
        calendar.add(Calendar.MINUTE, amount);
        return sdf.format(calendar.getTime());
    }

    /**
     * 功能：对于给定的时间增加天数/月数/年数后的日期,按指定格式输出
     *
     * @param date      String 要改变的日期
     * @param field     int 日期改变的字段，YEAR,MONTH,DAY
     * @param amount    int 改变量
     * @param strFormat 日期返回格式
     * @return
     * @throws ParseException
     */
    public static String getAddTime(String date, String field, int amount, String strFormat) {
        if (strFormat == null) {
            strFormat = YYYY_MM_DDHHMMSS;
        }
        Calendar rightNow = Calendar.getInstance();
        try {
            if (date != null && !"".equals(date.trim())) {
                rightNow.setTime(getDateByString(date, strFormat));
            }
            if (field == null) {
                return toString(rightNow.getTime(), strFormat);
            }
            rightNow.add(getInterval(field), amount);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return toString(rightNow.getTime(), strFormat);
    }

    /**
     * 将java.util.date型按照指定格式转为字符串
     *
     * @param date   源对象
     * @param format 想得到的格式字符串
     * @return 如：2010-05-28
     */
    public static String toString(Date date, String format) {
        return getSimpleDateFormat(format).format(date);
    }

    /**
     * 获取格式化对象
     *
     * @param strFormat 格式化的格式 如YYYY_MM_DDHHMMSS
     * @return 格式化对象
     */
    public static SimpleDateFormat getSimpleDateFormat(String strFormat) {
        if (strFormat != null && !"".equals(strFormat.trim())) {
            return new SimpleDateFormat(strFormat);
        } else {
            return new SimpleDateFormat();
        }
    }

    /**
     * 获取时间间隔类型
     *
     * @param field 时间间隔类型
     * @return 日历的时间间隔
     */
    protected static int getInterval(String field) {
        String tmpField = field.toUpperCase();
        if (tmpField.equals(DEFAULT_YEAR)) {
            return Calendar.YEAR;
        } else if (tmpField.equals(DEFAULT_MONTH)) {
            return Calendar.MONTH;
        } else if (tmpField.equals(DEFAULT_DATE)) {
            return Calendar.DATE;
        } else if (DEFAULT_HOUR.equals(tmpField)) {
            return Calendar.HOUR;
        } else if (DEFAULT_MINUTE.equals(tmpField)) {
            return Calendar.MINUTE;
        } else {
            return Calendar.SECOND;
        }
    }


    /**
     * 转换指定格式的字符串为时间
     *
     * @return
     */
    public static Date formatStr2Date(String str, String pattern) {
        Date d = null;
        if (null != str && !str.trim().equals("")) {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            try {
                d = sdf.parse(str);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return d;
    }

    /**
     * 转换时间为指定格式的字符串
     *
     * @param date
     * @return
     */
    public static String formatDate2Str(Date date, String pattern) {
        if (null == date) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String s = sdf.format(date);
        return s;
    }

    /**
     * Calendar转换为Date
     *
     * @param calendar
     * @return
     */
    public static Date calendar2Date(Calendar calendar) {
        if (null == calendar) {
            return null;
        }
        return calendar.getTime();
    }

    /**
     * Date转换为Calendar
     *
     * @param date
     * @return
     */
    public static Calendar date2Calendar(Date date) {
        if (null == date) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 计算指定日期是该年中的第几周
     *
     * @param date
     * @return
     */
    public static Integer getWeekOfYear(Date date) {
        if (null == date) {
            return 0;
        }
        return date2Calendar(date).get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 计算指定日期是该年中的第几天
     *
     * @param date
     * @return
     */
    public static Integer getDayOfYear(Date date) {
        if (null == date) {
            return 0;
        }
        return date2Calendar(date).get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 计算指定日期是该月中的第几天
     *
     * @param date
     * @return
     */
    public static Integer getDayOfMonth(Date date) {
        if (null == date) {
            return 0;
        }
        return date2Calendar(date).get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 计算指定日期是月中的第几个星期
     *
     * @param date
     * @return
     */
    public static Integer getDayOfWeekInMonth(Date date) {
        if (null == date) {
            return 0;
        }
        return date2Calendar(date).get(Calendar.DAY_OF_WEEK_IN_MONTH);
    }

    /**
     * 计算指定日期是该周中的第几天(星期天1~~~星期六7)
     *
     * @param date
     * @return
     */
    public static Integer getDayOfWeek(Date date) {
        if (null == date) {
            return 0;
        }
        return date2Calendar(date).get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 计算指定日期月份的最大天数
     *
     * @param date
     * @return
     */
    public static int getMaxDayInMonth(Date date) {
        if (date == null) {
            return 0;
        }
        return date2Calendar(date).getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 给指定日期添加或减去指定年
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addYear(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, amount);
        return calendar.getTime();
    }

    /**
     * 指定日期的年份
     *
     * @param date
     * @param year
     * @return
     */
    public static Date setYear(Date date, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR, year);
        return calendar.getTime();
    }

    /**
     * 给指定日期添加或减去指定月
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addMonth(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, amount);
        return calendar.getTime();
    }

    /**
     * 指定日期月份
     *
     * @param date
     * @param month
     * @return
     */
    public static Date setMonth(Date date, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * 返回下月
     */
    public static Date getNextMonth(Date d) {
        return add(d, Calendar.MONTH, 1);
    }

    /**
     * 按Calendar的field 增加，返回对应日期。
     */
    public static Date add(Date d, int field, int amount) {
        Date result = null;
        try {
            //log.debug("date="+d);
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            cal.add(field, amount);
            result = cal.getTime();
        } catch (Exception e) {
            // log.error("DateUtil_error:"+e);
        }
        return result;
    }

    /**
     * 给指定日期添加或减去指定周
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addWeek(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.WEEK_OF_MONTH, amount);
        return calendar.getTime();
    }

    /**
     * 给指定日期添加或减去指定天
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addDay(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, amount);
        return calendar.getTime();
    }

    /**
     * 指定日期的天数
     *
     * @param date
     * @param day
     * @return
     */
    public static Date setDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, day);
        return calendar.getTime();
    }

    /**
     * 给指定日期添加或减去指定小时
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addHour(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, amount);
        return calendar.getTime();
    }

    /**
     * 指定日期的小时
     *
     * @param date
     * @param hour
     * @return
     */
    public static Date setHour(Date date, int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        return calendar.getTime();
    }

    /**
     * 给指定日期添加或减去指定分钟
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addMinute(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, amount);
        return calendar.getTime();
    }

    /**
     * 指定日期的分钟
     *
     * @param date
     * @param minute
     * @return
     */
    public static Date setMinute(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    /**
     * 给指定日期添加或减去指定秒
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addSecond(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, amount);
        return calendar.getTime();
    }

    /**
     * 指定日期的秒
     *
     * @param date
     * @param second
     * @return
     */
    public static Date setSecond(Date date, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 给指定日期添加或减去指定毫秒
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addMillisecond(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MILLISECOND, amount);
        return calendar.getTime();
    }

    /**
     * 指定日期的毫秒
     *
     * @param date
     * @param millisecond
     * @return
     */
    public static Date setMillisecond(Date date, int millisecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND, millisecond);
        return calendar.getTime();
    }

    /**
     * 计算两个日期之间差的多少毫秒，如果日期firstDate在日期secondDate的后面则返回一个正数、如果日期firstDate在日期secondDate的前面则返回一个负数、两个日期相同返回0
     *
     * @param firstDate
     * @param secondDate
     * @return
     */
    public static long compareMillisecond(Date firstDate, Date secondDate) {
        long time1 = firstDate.getTime();
        long time2 = secondDate.getTime();
        return (time1 - time2);
    }

    /**
     * 计算两个日期之间差的多少秒，如果日期firstDate在日期secondDate的后面则返回一个正数、如果日期firstDate在日期secondDate的前面则返回一个负数、两个日期相同返回0
     *
     * @param firstDate
     * @param secondDate
     * @return
     */
    public static long compareSecond(Date firstDate, Date secondDate) {
        long time1 = firstDate.getTime();
        long time2 = secondDate.getTime();
        return (time1 - time2) / (1000);
    }

    /**
     * 计算两个日期之间差的多少分钟，如果日期firstDate在日期secondDate的后面则返回一个正数、如果日期firstDate在日期secondDate的前面则返回一个负数、两个日期相同返回0
     *
     * @param firstDate
     * @param secondDate
     * @return
     */
    public static long compareMinute(Date firstDate, Date secondDate) {
        long time1 = firstDate.getTime();
        long time2 = secondDate.getTime();
        return (time1 - time2) / (1000 * 60);
    }

    /**
     * 计算两个日期之间差的多少小时，如果日期firstDate在日期secondDate的后面则返回一个正数、如果日期firstDate在日期secondDate的前面则返回一个负数、两个日期相同返回0
     *
     * @param firstDate
     * @param secondDate
     * @return
     */
    public static long compareHour(Date firstDate, Date secondDate) {
        long time1 = firstDate.getTime();
        long time2 = secondDate.getTime();
        return (time1 - time2) / (1000 * 60 * 60);
    }

    /**
     * 计算两个日期之间差的多少天，如果日期firstDate在日期secondDate的后面则返回一个正数、如果日期firstDate在日期secondDate的前面则返回一个负数、两个日期相同返回0
     *
     * @param firstDate
     * @param secondDate
     * @return
     */
    public static long compareDay(Date firstDate, Date secondDate) {
        long time1 = firstDate.getTime();
        long time2 = secondDate.getTime();
        return (time1 - time2) / (1000 * 60 * 60 * 24);
    }

    //比较两个日期 ，以月为单位，如果时间1大于时间2 ，则返回true ,否则返回false
    public static boolean compareMonth(Date start, Date end, String type) {
        String s = format2String(YYYYMM, start);
        //System.out.print(s);
        String e = format2String(YYYYMM, end);
        // System.out.print(e);
        Long sl = Long.valueOf(s);
        Long el = Long.valueOf(e);
        if (sl <= el)
            return false;

        return true;


    }

    // /**
    // *
    // 计算两个日期之间差的多少月,返回绝对值
    // *
    // * @param firstDate
    // * @param secondDate
    // * @return
    // */
    public static long compareMonth(Date start, Date end) {
        if (start.after(end)) {
            Date t = start;
            start = end;
            end = t;
        }
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        Calendar temp = Calendar.getInstance();
        temp.setTime(end);
        temp.add(Calendar.DATE, 1);

        int year = endCalendar.get(Calendar.YEAR)
                - startCalendar.get(Calendar.YEAR);
        int month = endCalendar.get(Calendar.MONTH)
                - startCalendar.get(Calendar.MONTH);

        if ((startCalendar.get(Calendar.DATE) == 1)
                && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month + 1;
        } else if ((startCalendar.get(Calendar.DATE) != 1)
                && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month;
        } else if ((startCalendar.get(Calendar.DATE) == 1)
                && (temp.get(Calendar.DATE) != 1)) {
            return year * 12 + month;
        } else {
            return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
        }
    }


    /**
     * 获取指定日期对应的年份的第一天的日期
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, 1);// 设为日期为今年的第1天
        return calendar.getTime();
    }

    /**
     * 获取指定日期对应的年份的最后一天的日期
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, 1);// 设为日期为今年的第1天
        calendar.add(Calendar.YEAR, 1);// 加上一年，到第二年的第一天
        calendar.add(Calendar.DATE, -1);// 减一天，今年的最后一天
        return calendar.getTime();
    }

    /**
     * 获取指定日期对应的季度的第一天的日期
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        if (0 == month || 1 == month || 2 == month) {
            calendar.set(Calendar.MONDAY, 0);
        } else if (3 == month || 4 == month || 5 == month) {
            calendar.set(Calendar.MONDAY, 3);
        } else if (6 == month || 7 == month || 8 == month) {
            calendar.set(Calendar.MONDAY, 6);
        } else {
            calendar.set(Calendar.MONDAY, 9);
        }
        return getFirstDayOfMonth(calendar.getTime());
    }

    /**
     * 获取指定日期对应的季度的最后一天的日期
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        if (0 == month || 1 == month || 2 == month) {
            calendar.set(Calendar.MONDAY, 2);
        } else if (3 == month || 4 == month || 5 == month) {
            calendar.set(Calendar.MONDAY, 5);
        } else if (6 == month || 7 == month || 8 == month) {
            calendar.set(Calendar.MONDAY, 8);
        } else {
            calendar.set(Calendar.MONDAY, 11);
        }
        return getLastDayOfMonth(calendar.getTime());
    }

    /**
     * 获取指定日期对应的月份的第一天的日期
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);// 设为当前月的1号
        return calendar.getTime();
    }

    /**
     * 获取指定日期对应的月份的最后一天的日期
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);// 设为当前月的1号
        calendar.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
        calendar.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天
        return calendar.getTime();
    }

    /**
     * 获取指定日期对应的周的第一天的日期(按中国习惯星期一作为一周的第一天)
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        int dayOfWeek = getDayOfWeek(date);
        if (2 == dayOfWeek) {
            return date;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        while (true) {
            calendar.add(Calendar.DATE, -1);
            if (2 == getDayOfWeek(calendar.getTime())) {
                break;
            }
        }
        return calendar.getTime();
    }

    /**
     * 获取指定日期对应的周的最后一天的日期(按中国习惯星期天作为一周的最后一天)
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek(Date date) {
        int dayOfWeek = getDayOfWeek(date);
        if (1 == dayOfWeek) {
            return date;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        while (true) {
            calendar.add(Calendar.DATE, 1);
            if (1 == getDayOfWeek(calendar.getTime())) {
                break;
            }
        }
        return calendar.getTime();
    }

    /**
     * 获取指定日期对应的周的指定星期几的日期(weekIndex : 1 星期天  7 星期六)
     *
     * @param date
     * @return
     */
    public static Date getDayOfWeek(Date date, int weekIndex) {
        int dayOfWeek = getDayOfWeek(date);
        if (weekIndex == dayOfWeek) {
            return date;
        }
        int i = 1;
        if (dayOfWeek > weekIndex) {
            i = -1;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        while (true) {
            calendar.add(Calendar.DATE, i);
            if (weekIndex == getDayOfWeek(calendar.getTime())) {
                break;
            }
        }
        return calendar.getTime();
    }

    /**
     * 获得日期字符串数组
     *
     * @param calendarType 日期跨度的类型，
     */

    public static Date[] getDateArrays(Date start, Date end, int calendarType) {
        ArrayList<Date> ret = new ArrayList<Date>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        Date tmpDate = calendar.getTime();
        long endTime = end.getTime();
        while (tmpDate.before(end) || tmpDate.getTime() == endTime) {
            ret.add(calendar.getTime());
            calendar.add(calendarType, 1);
            tmpDate = calendar.getTime();
        }
        Date[] dates = new Date[ret.size()];
        return ret.toArray(dates);
    }

    /**
     * 获取指定两个日期之间的每一天
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List getDayList(String startDate, String endDate, String dateType) {
        Date date = null;
        Date date2 = null;
        List dayLst = new ArrayList();
        try {
            date = getDateByString(startDate, dateType); //这里类型可以修改，
            date2 = getDateByString(endDate, dateType);
            Date[] strArray = getDateArrays(date, date2, Calendar.DAY_OF_YEAR);
            for (Date string : strArray) {
                dayLst.add(toString(string, dateType));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dayLst;
    }

    /**
     * 格式当期日期的月和日
     *
     * @param month 月
     * @param day   日
     * @return String
     */
    public static String setDateByDayAndMonth(int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        Date date = calendar.getTime();
        return new SimpleDateFormat(DateUtil.YYYY_MM_DD).format(date);
    }

    public static String getDateStr4Msg() {
        String year = DateUtil.format2String("yyyy");
        String month = DateUtil.format2String("MM");
        String dd = DateUtil.format2String("dd");
        String dateStr = year + "年" + month + "月" + dd + "日";
        return dateStr;
    }


    /**
     * 格式化 日期字符串格式
     *
     * @return
     */
    public static String getStringDateFormat(String dateString, String format) {
        try {
            dateString = format2String(format, getDateByString(dateString, format));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateString;
    }
}
