package com.tsystems.si.aviation.imf.tomorrowPlan.utils;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeHandle {
	    private static final Logger     logger               = LoggerFactory.getLogger(TimeHandle.class);
	    static SimpleDateFormat fmtDateTime = new SimpleDateFormat("yyyyMMddHHmmss");
	    static SimpleDateFormat fmtDateTimewithT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	    static SimpleDateFormat fmtDateTimeReadable = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    static SimpleDateFormat showFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    static SimpleDateFormat fmtDate = new SimpleDateFormat("yyyy-MM-dd");
	    static SimpleDateFormat fmtDateNoZX = new SimpleDateFormat("yyyyMMdd");
    // 得到日期格式对象  
    // Date date = fmtDateTime.parse(strDateMake);  
         public static Calendar getMessageTimeByName(String fileName){
        	 String[] nameSpilt = fileName.split("_");
             int length = nameSpilt.length;
        	 String timeString = nameSpilt[length-2];
        	 Date date =null;
        	 try {
				 date = fmtDateTime.parse(timeString);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
        	 Calendar calendar = Calendar.getInstance();
        	 
        	 calendar.setTime(date);
        	 return calendar;
         }
         public static Date getMessageDateTimeByName(String fileName){
        	 String[] nameSpilt = fileName.split("_");
             int length = nameSpilt.length;
        	 String timeString = nameSpilt[length-2];
        	 Date date =null;
        	 try {
				 date = fmtDateTime.parse(timeString);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
        	 return date;
         }
         public static Calendar getMessageTimeByFile(File file){
        	 Calendar calendar  = Calendar.getInstance();
             calendar.setTimeInMillis(file.lastModified());
        	 
        	 return calendar;
         }
         

         
         public static Calendar getMessageUTCTimeByFile(File file){
        	 Calendar calendar  = Calendar.getInstance();
             calendar.setTimeInMillis(file.lastModified());        	 
        	 return calendar;
         }
         
         public static String getCalendarString(Calendar ca){
        	 String dateString = showFmt.format(ca.getTime());
        	 return dateString;
        	 
         }
         
         public static Calendar getCalendarByString(String timeString){
        	 Date date =null;
        	 try {
				 date = fmtDateTimeReadable.parse(timeString);
			
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
        	 Calendar calendar = Calendar.getInstance();
        	 
        	 calendar.setTime(date);
        	 return calendar;
         }
         public static String getDateString(Date date){
        	 String dateString = fmtDate.format(date.getTime());
        	 return dateString;
        	 
         }
         public static String getDateStringNoZX(Date date){
        	 String dateString = fmtDateNoZX.format(date.getTime());
        	 return dateString;
        	 
         }
         public static String getDateTimeString(Date date){
        	 String dateString =null;
        	 if(date!=null){
        		 dateString = showFmt.format(date.getTime());
        	 }
        	 
        	 return dateString;
        	 
         }
         
         public static String getDateTimeWithTString(Date date){
        	 String dateString = null;
        	 if(date!=null){
        		 dateString = fmtDateTimewithT.format(date.getTime());
        	 }
        	 return dateString;
        	 
         }
         
         public static Map<String, Date>  getFlightDateTimePeriod(Date dateTime,int beforeMin, int afterMin){
        	 Map<String,Date> dateTimeHashMap = new HashMap<String,Date>();
        	 Calendar calendarBefore = Calendar.getInstance();
        	 calendarBefore.setTime(dateTime);
        	 Calendar calendarAfter = Calendar.getInstance();
        	 calendarAfter.setTime(dateTime);
        	 int tmp=0-beforeMin;
        	 calendarBefore.add(Calendar.MINUTE, tmp);
        	 calendarAfter.add(Calendar.MINUTE, afterMin);
        	 Date before = calendarBefore.getTime();
        	 Date after = calendarAfter.getTime();

        	 dateTimeHashMap.put("beforeDateTime", before);
        	 dateTimeHashMap.put("afterDateTime", after);
        	 return dateTimeHashMap;
         }
         
         
         public static Date dateAddMinutes(Date date,int minutes){
        	 
        	 Calendar calendar = Calendar.getInstance();
        	 calendar.setTime(date);
        	 calendar.add(Calendar.MINUTE, minutes);
        	 return calendar.getTime();
         }
         
         public static Date dateSubMinutes(Date date,int minutes){
        	 
        	 Calendar calendar = Calendar.getInstance();
        	 calendar.setTime(date);
        	 calendar.add(Calendar.MINUTE, -minutes);
        	 return calendar.getTime();
         }
         

         
         public static int getFlyMinitesByString(String flyTime){
        	// logger.info("FlyTim    :{}",flyTime);
        	 int minutes =0;
        	 if(flyTime!=null){
	        	 String hourS = flyTime.substring(0, 2);
	        	 int hour = Integer.parseInt(hourS);
	        	 String minuteS = flyTime.substring(2, 4);
	        	 int minute = Integer.parseInt(minuteS);
	        	 
	        	  minutes = hour*60+minute;
	        	// logger.info("FlyMinutes:{}",minutes);
        	 }
        	 return minutes;
         }
         
         

         
         
         public static int getMounthIntByShortMonth(String shortMonth){
        	 //calendar 中月份从0开始
        	 int n=0;
        	 if(shortMonth.equalsIgnoreCase("JAN")){
        		 n=0;
        	 }else if(shortMonth.equalsIgnoreCase("FEB")){
        		 n=1;
        	 }else if(shortMonth.equalsIgnoreCase("MAR")){
        		 n=2;
        	 }else if(shortMonth.equalsIgnoreCase("APR")){
        		 n=3;
        	 }else if(shortMonth.equalsIgnoreCase("MAY")){
        		 n=4;
        	 }else if(shortMonth.equalsIgnoreCase("JUN")){
        		 n=5;
        	 }else if(shortMonth.equalsIgnoreCase("JUL")){
        		 n=6;
        	 }else if(shortMonth.equalsIgnoreCase("AUG")){
        		 n=7;
        	 }else if(shortMonth.equalsIgnoreCase("SEP")){
        		 n=8;
        	 }else if(shortMonth.equalsIgnoreCase("OCT")){
        		 n=9;
        	 }else if(shortMonth.equalsIgnoreCase("NOV")){
        		 n=10;
        	 }else if(shortMonth.equalsIgnoreCase("DEC")){
        		 n=11;
        	 }
        	 return n;
         }
         
         public static String getShortMonthByMounthInt(String monthInt){
        	 //calendar 中月份从0开始
        	 String month=null;
        	 if(monthInt.equalsIgnoreCase("01")){
        		 month="JAN";
        	 }else if(monthInt.equalsIgnoreCase("02")){
        		 month="FEB";
        	 }else if(monthInt.equalsIgnoreCase("03")){
        		 month="MAR";
        	 }else if(monthInt.equalsIgnoreCase("04")){
        		 month="APR";
        	 }else if(monthInt.equalsIgnoreCase("05")){
        		 month="MAY";
        	 }else if(monthInt.equalsIgnoreCase("06")){
        		 month="JUN";
        	 }else if(monthInt.equalsIgnoreCase("07")){
        		 month="JUL";
        	 }else if(monthInt.equalsIgnoreCase("08")){
        		 month="AUG";
        	 }else if(monthInt.equalsIgnoreCase("09")){
        		 month="SEP";
        	 }else if(monthInt.equalsIgnoreCase("10")){
        		 month="OCT";
        	 }else if(monthInt.equalsIgnoreCase("11")){
        		 month="NOV";
        	 }else if(monthInt.equalsIgnoreCase("12")){
        		 month="DEC";
        	 }
        	 return month;
         }
         
         public static Date dateAddDays(Date date,int days){
        	 
        	 Calendar calendar = Calendar.getInstance();
        	 calendar.setTime(date);
        	 calendar.add(Calendar.DAY_OF_MONTH, days);
        	 return calendar.getTime();
         }
         
         public static String formatTomorrowPlanDateTime(String dateTimeString){
        	 
        	 String dt = dateTimeString.replace("/", "-");
        	 String[] dat = dt.split("[\\s]+");
        	 String d = dat[0];
        	 String t = dat[1];
        	 String[] ymd = d.split("-");
        	 String[] hm = t.split(":");
        	 int year = Integer.parseInt(ymd[0]);
        	 int month = Integer.parseInt(ymd[1]);
        	 int day = Integer.parseInt(ymd[2]);
        	 //in case 12/3/15 17:05
        	 if(year<100){
        		 year = Integer.parseInt("20"+ymd[2]);
        		 month = Integer.parseInt(ymd[0]);
        		 day = Integer.parseInt(ymd[1]);
        	 }
        	 int hour = Integer.parseInt(hm[0]);
        	 int minute = Integer.parseInt(hm[1]);
        	 Calendar calendar = Calendar.getInstance();
        	 
        	 calendar.set(Calendar.YEAR, year);
        	 calendar.set(Calendar.MONTH, month-1);
        	 calendar.set(Calendar.DAY_OF_MONTH, day);
        	 calendar.set(Calendar.HOUR_OF_DAY, hour);
        	 calendar.set(Calendar.MINUTE, minute);
        	 calendar.set(Calendar.SECOND, 0);
        	 
        	 String formatedDateTimeString = fmtDateTimewithT.format(calendar.getTime());
        	 return formatedDateTimeString;

         }
}
