package com.classAttendance.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author soldier
 * @title: DateUtil
 * @projectName class_attendance
 * @date 19-7-9上午9:58
 * @Email： 583403411@qq.com
 * @description:
 */
public class DateUtil {

    private static final String TAG = "DateUtil(时间转换)";

    /**
     * 字符串转换成日期
     *
     * @param strDate
     *            时间串
     * @param format
     *            时间字符串的格式，如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date string2Date(String strDate, String format) {
        DateFormat df = new SimpleDateFormat(format);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new Date();
        }
    }

    public String showDate(int oldTime){
        int nowTime=(int)(System.currentTimeMillis()/1000);
        int cha=nowTime-oldTime;
        long oldTime1=((long)oldTime)*1000;
        if(cha<86400){
            //是一天
            return date2String(long2Date(oldTime1), "HH:mm");
        }else if(cha<15552000){
            //半年
            return date2String(long2Date(oldTime1), "MM-dd");
        }else{
            return date2String(long2Date(oldTime1), "yyyy-MM-dd");
        }


    }

    /**
     * 更改日
     * @param date 需要更改的时间
     * @param num 需要增加或减少月份的数值，1、往后一天；-1、往前一天
     * @return 修改后的日期
     */
    public Date addDay(Date date,int num){
        Calendar calendar   =   new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH,num);//把日期往后增加一天.整数往后推,负数往前移动
        date=calendar.getTime();   //这个时间就是日期往后推一天的结果
        return date;
    }
    /**
     * 更改月份
     * @param date 需要更改的时间
     * @param num 需要增加或减少月份的数值，1、往后一个月；-1、往前一个月
     * @return 修改后的日期
     */
    public Date addMonth(Date date,int num){
        Calendar   calendar   =   new   GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH,num);//把日期往后增加一天.整数往后推,负数往前移动
        date=calendar.getTime();   //这个时间就是日期往后推一天的结果
        return date;
    }
    /**
     * 更改年份
     * @param date 需要更改的时间
     * @param num 需要增加或减少年份的数值，1、往后一年；-1、往前一年
     * @return 修改后的日期
     */
    public Date addYear(Date date,int num){
        Calendar   calendar   =   new   GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR,num);//把日期往后增加一天.整数往后推,负数往前移动
        date=calendar.getTime();   //这个时间就是日期往后推一天的结果
        return date;
    }
    /**
     * Date转时间戳
     *
     * @param date
     * @return 时间戳
     */
    public Long date2Long(Date date) {
        return date.getTime();
    }

    /**
     * 貌似没多大意义，date转精确到秒的时间戳
     *
     * @param date
     * @return 时间戳
     */
    public int dateToInt(Date date) {
        return (int) (date.getTime() / 1000);
    }

    /**
     * 时间戳转date
     *
     * @param times
     * @return date
     */
    public Date long2Date(long times) {
        return new Date(times);
    }

    /**
     * 字符串的时间戳转date
     *
     * @param str
     * @return date
     */
    public Date strLongToDate(String str) {
        long times = Long.valueOf(str).longValue();
        return new Date(times);
    }

    /**
     * date转string
     *
     * @param nowTime
     *            date对象
     * @param mFormat
     *            时间字符串的格式，如：yyyy-MM-dd HH:mm:ss
     * @return 时间字符串
     */
    public static String date2String(Date nowTime, String mFormat) {
        SimpleDateFormat time = new SimpleDateFormat(mFormat);
        return time.format(nowTime);
    }
    public static String date2StringSimple(Date nowTime) {
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return time.format(nowTime);
    }
    /**
     * 时间戳转String
     * @param times date对象
     * @param mFormat 时间字符串的格式，如：yyyy-MM-dd HH:mm:ss
     * @return 时间字符串
     */
    public String longToString(long times, String mFormat) {
        Date nowTime = long2Date(times);
        SimpleDateFormat time = new SimpleDateFormat(mFormat);
        return time.format(nowTime);
    }

    /**
     * timestamp转String
     *
     * @param time
     * @param mFormat
     *            mFormat="yyyy MM dd HH mm ss";
     * @return
     */
    public String timestamp2String(Timestamp time, String mFormat) {
        return date2String(new Date(time.getTime()), mFormat);
    }

    /**
     * 1-->01
     */
    public String int2Str2(int num) {
        String str = "";
        if (num < 10) {
            str = "0" + num;
        } else {
            str = "" + num;
        }
        return str;
    }

    /**
     * 1-->001
     */
    public String int2Str3(int num) {
        String str = "";
        if (num < 10) {
            str = "00" + num;
        } else {
            if (num < 100) {
                str = "0" + num;
            } else {
                str = "" + num;
            }
        }
        return str;
    }

}
