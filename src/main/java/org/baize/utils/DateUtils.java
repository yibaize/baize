package org.baize.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 作者：泡泡大湿
 * 时间： 2017/9/7.
 * 描述：
 */
public class DateUtils {
    public static long currentTime(){
        Calendar c = Calendar.getInstance();
        return c.getTimeInMillis();
    }
    public static int currentDay(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date tem = new Date();
        String to = format.format(tem);
        return Integer.parseInt(to);
    }
    private static  Calendar dayEndCalendar(){
        Calendar c= Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY,24);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        return c;
    }
    public static int day(){
        Date date = dayEndCalendar().getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String from = format.format(date);
        int to = Integer.parseInt(from);
        return to;
    }
    public static long dayEndMillis(){
        return dayEndCalendar().getTimeInMillis();
    }
    /*
    *当前天+7天
     */
    private static Calendar weekEndCalendar(){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_YEAR,c.get(Calendar.DAY_OF_YEAR)+7);
        return c;
    }
    /**
     * 今天是星期几
     * @return
     */
    public static int getTodayOnWeek(){
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.DAY_OF_WEEK)-1;
    }
    public static int week(){
        Date date = weekEndCalendar().getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String from = format.format(date);
        int to = Integer.parseInt(from);
        return to;
    }
    public static long weekMillis(){
        return weekEndCalendar().getTimeInMillis();
    }
    private static Calendar monthEndCalendar(){
        Calendar c = Calendar.getInstance();
        c.set(c.get(Calendar.YEAR),c.get(Calendar.MONDAY),c.get(Calendar.DAY_OF_MONTH),0,0,0);
        c.set(Calendar.DAY_OF_MONTH,c.getActualMaximum(Calendar.DAY_OF_MONTH));
        c.set(Calendar.HOUR_OF_DAY,24);
        return  c;
    }
    public static int month(){
        Date date = monthEndCalendar().getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String from = format.format(date);
        int to = Integer.parseInt(from);
        return to;
    }
    public static long monthEndMllis(){
        return monthEndCalendar().getTimeInMillis();
    }
    public static int getTime(String date){
        int time = 0;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat to = new SimpleDateFormat("yyyyMMdd");
            Date date1 = format.parse(date);
            String timeStr = to.format(date1);
            time = Integer.parseInt(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }
    public static long getTimeMillis(String date){
        long time = 0;
        try {
            time = org.apache.commons.lang3.time.DateUtils.parseDate(date,"yyyy-MM-dd").getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     * 30秒之后
     * @return
     */
    public static long getFutureTimeMillis(){
        Calendar c=Calendar.getInstance();
        c.add(Calendar.SECOND,30);
        return c.getTimeInMillis();
    }

    public static void main(String[] args) {
        System.out.println("xxx");
    }
}
