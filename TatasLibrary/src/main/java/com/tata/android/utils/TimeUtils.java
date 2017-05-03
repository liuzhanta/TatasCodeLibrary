package com.tata.android.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Description:
 * Creator:Terry
 * Date:2017/5/3 下午5:15
 */

public class TimeUtils {

    public static final String DEFAULT_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private TimeUtils() {
        //no instance
    }

    public static long toTime(String timeString) {
        return toTime(timeString, DEFAULT_TIME_PATTERN);
    }

    public static long toTime(String timeString, String pattern) {
        if (TextUtils.isEmpty(timeString)) {
            return 0;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            Date date = sdf.parse(timeString);
            return date.getTime() > 0 ? date.getTime() : 0;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getNowTime(String format) {
        if (TextUtils.isEmpty(format)) return null;
        Calendar rightNow = Calendar.getInstance();
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        String sysDatetime = fmt.format(rightNow.getTime());
        return sysDatetime;
    }

    public static String toPattern(long time, String pattern) {
        final Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static String toPattern(long time) {
        final Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_TIME_PATTERN);
        return sdf.format(date);
    }
}
