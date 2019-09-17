package com.zicen.myandroiddemoproject.utils;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Nick on 05/09/2017.
 */
public final class DateConvertUtils {
    private static final DateFormat FORMAT_ISO_8601;
    static {
        FORMAT_ISO_8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
        FORMAT_ISO_8601.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public static final String DATA_FORMAT_PATTEN_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATA_FORMAT_PATTEN_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String DATA_FORMAT_PATTEN_MM_DD_HH_MM = "MM-dd HH:mm";
    private static final String DATA_FORMAT_PATTEN_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String DATA_FORMAT_PATTEN_YYYY_DOT_MM_DOT_DD = "yyyy.MM.dd";
    public static final String DATA_FORMAT_PATTEN_HH_MM = "HH:mm";
    public static final String DATA_FORMAT_PATTEN_HH_MM_SS = "HH:mm:ss";

    private final static long minute = 60 * 1000;// 1分钟
    private final static long hour = 60 * minute;// 1小时
    private final static long day = 24 * hour;// 1天
    private final static long month = 31 * day;// 月
    private final static long year = 12 * month;// 年

    public static String getUTCTime() {
        return FORMAT_ISO_8601.format(new Date());
    }

    /**
     * 将时间转换为时间戳
     *
     * @param data             待转换的日期
     * @param dataFormatPatten 待转换日期格式
     */
    public static long dateToTimeStamp(String data, String dataFormatPatten) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dataFormatPatten, Locale.CHINA);
        Date date = null;
        try {
            date = simpleDateFormat.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert date != null;
        return date.getTime();
    }

    /**
     * 将时间戳转换为日期
     *
     * @param time             待转换的时间戳
     * @param dataFormatPatten 转换出的日期格式
     */
    public static String timeStampToDate(long time, String dataFormatPatten) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dataFormatPatten, Locale.CHINA);
        Date date = new Date(time);
        return simpleDateFormat.format(date);
    }

    /**
     * 日期转星期
     *
     * @param dateString 日期
     * @return 周一 周二 周三 ...
     */
    public static String convertDataToWeek(String dateString) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATA_FORMAT_PATTEN_YYYY_MM_DD, Locale.CHINA);
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (isNow(date))
            return "今天";

        String[] weekDaysName = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDaysName[intWeek];
    }

    /**
     * 日期转换
     *
     * @return 08.07
     */
    public static String convertDataToString(String dateString) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATA_FORMAT_PATTEN_YYYY_MM_DD, Locale.CHINA);
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date == null)
            return "";
        return (String.valueOf(date.getMonth()).length() == 1 ? "0" + date.getMonth() : String.valueOf(date.getMonth()))
                + "." + (String.valueOf(date.getDay()).length() == 1 ? "0" + date.getDay() : String.valueOf(date.getDay()));
    }

    /**
     * 判断时间是不是今天
     *
     * @return 是返回true，不是返回false
     */
    public static boolean isNow(Date date) {
        //当前时间
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATA_FORMAT_PATTEN_YYYY_MM_DD, Locale.CHINA);
        //获取今天的日期
        String nowDay = simpleDateFormat.format(now);
        //对比的时间
        String day = simpleDateFormat.format(date);
        return day.equals(nowDay);
    }

    /**
     * 将时长由毫秒数转化成时间
     *
     * @param duration 毫秒数
     * @return HH:mm
     */
    public static String timeParse(long duration) {
        String time = "";
        long minute = duration / 60000;
        long seconds = duration % 60000;
        long second = Math.round((float) seconds / 1000);

        if (minute < 10) {
            time += "0";
        }
        time += minute + ":";
        if (second < 10) {
            time += "0";
        }
        time += second;
        return time;
    }

    //显示00：00：00格式的时长
    public static String getTime(long time) {
        StringBuilder sb = new StringBuilder();
        long timeInSeconds = time / 1000;
        int hours = (int) timeInSeconds / 3600;
        if (hours >= 10) {
            sb.append(hours);
            sb.append(":");

        } else if (hours > 0 && hours < 10) {
            sb.append(0).append(hours);
            sb.append(":");
        }

        long minutes = (int) ((timeInSeconds % 3600) / 60);
        if (minutes >= 10) {
            sb.append(minutes);
        } else if (minutes > 0 && minutes < 10) {
            sb.append(0).append(minutes);
        } else {
            sb.append("00");
        }
        sb.append(":");

        int seconds = (int) (timeInSeconds % 60);
        if (seconds >= 10) {
            sb.append(seconds);
        } else if (seconds > 0 && seconds < 10) {
            sb.append(0).append(seconds);
        } else {
            sb.append("00");
        }
        return sb.toString();
    }

    //时间戳转规定时间格式
    /*
    * 一小时内：XX分钟前
	* 当      天：XX：XX（如20:30）
	* 昨      天：昨天XX:XX
	* 当      年：XX月XX日
	* 去      年：XXXX年XX月XX日
	* */
    public static String formateLongTime(long time) {

        long currentTime = System.currentTimeMillis();
        long deta = currentTime - time * 1000;

        Calendar calendar = Calendar.getInstance();
        //当前时间
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        int currentHour = calendar.get(Calendar.HOUR);
        int currentMinute = calendar.get(Calendar.MINUTE);
        //评论时间
        calendar.setTimeInMillis(time * 1000);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);

        if (deta <= 60 * 1000) {//刚刚
            return "1 分钟前";
        } else if (deta > 60 * 1000 && deta < 3600 * 1000) {//一小时以内
            if (currentHour == hour) {
                return (currentMinute - minute) + " 分钟前";
            } else {
                return (60 + currentMinute - minute) + "分钟前";
            }
        } else if (deta >= 3600 * 1000 && currentDay == day && month == currentMonth && year == currentYear) {//同一天
            return time2Str(String.valueOf(time), 0);
        } else if (currentYear == year) {//同年
            if (currentMonth == month && currentDay - day == 1) {//昨天
                return "昨天 " + time2Str(String.valueOf(time), 0);
            } else if (currentMonth == month && currentDay - day == 2) {//前天
                return "前天 " + time2Str(String.valueOf(time), 0);
            } else {
                return time2Str(String.valueOf(time), 2);
            }
        } else {//不同年份
            return time2Str(String.valueOf(time), 1);
        }
    }

    //时间戳转字符串
    public static String time2Str(String cc_time, int type) {
        String str = null;
        SimpleDateFormat sdf;
        if (type == 1) {
            sdf = new SimpleDateFormat("yyyy 年 MM 月 dd 日 HH:mm");
        } else if (type == 2) {
            sdf = new SimpleDateFormat("MM 月 dd 日 HH:mm");
        } else {
            sdf = new SimpleDateFormat("HH:mm");
        }
        long lcc_time = Long.valueOf(cc_time);
        str = sdf.format(new Date(lcc_time * 1000L));
        return str;
    }

    // 创建时间format
    public static String TimeStramp2Date(int type, long timestamp) {
        long newTimeStamp = timestamp * 1000;
        String timeFormat = "";
        if (type == 1) {
            timeFormat = "yyyy-MM-dd HH:mm";
        } else if (type == 0) {
            timeFormat = "yyyy-MM-dd";
        } else if (type == 2) {
            timeFormat = "HH:mm";
        } else if (type == 3) {
            timeFormat = "yyyy.MM.dd";
        } else if (type == 4) {
            timeFormat = "yyyy 年 MM 月 dd 日";
        } else if (type == 5) {
            timeFormat = "yyyy-MM-dd HH:mm:ss";
        } else if (type == 6) {
            timeFormat = "yyyy.MM.dd HH:mm";
        }
        return new SimpleDateFormat(timeFormat)
                .format(new java.util.Date(newTimeStamp));
    }


    /**
     * @return 获取格式为上午 小时：分钟格式字符穿
     */
    public static String getWeekDay() {
        final Calendar c = Calendar.getInstance();
        int i = c.get(Calendar.DAY_OF_WEEK);
        return String.valueOf(i);
    }

    public static String getYearMonth(long time) {
        time = time * 1000;

        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月");
        long current = System.currentTimeMillis();
        String res = format.format(time);
        String c = format.format(current);
        if (res.equals(c)) {
            res = "本月";
        }
        return res;
    }


    /**
     * 用于屏蔽直播间消息，2018-09-01只后不处理非来自服务器消息
     *
     * @return
     */
    public static boolean dateOver91(long time) {
        try {
            @SuppressLint("SimpleDateFormat")
            DateFormat df = new SimpleDateFormat(DATA_FORMAT_PATTEN_YYYY_MM_DD);
            Date date = df.parse("2018-09-01");
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            long eTime = cal.getTimeInMillis();
            return time >= eTime;
        } catch (Exception e) {
            return false;
        }

    }


    public static String getTimeFormatText(long time) {
        Date date = new Date(time * 1000);

        long diff = new Date().getTime() - date.getTime();
        long r = 0;
        if (diff > year) {
            r = (diff / year);
            return r + "  年前";
        }
        if (diff > month) {
            r = (diff / month);
            return r + " 个月前";
        }
        if (diff > day) {
            r = (diff / day);
            return r + " 天前";
        }
        if (diff > hour) {
            r = (diff / hour);
            return r + " 个小时前";
        }
        if (diff > minute) {
            r = (diff / minute);
            return r + " 分钟前";
        }
        return "刚刚";
    }

}
