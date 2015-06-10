package com.mt.zt.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import com.mt.zt.exception.AllztException;

public class DateUtils {

	public static int getAge(Date date){
		DateTime end = new DateTime(); 
		DateTime begin = new DateTime(date);
		Period p = new Period(begin, end, PeriodType.years());
		return p.getYears();
	}
	
	public static String formatyyyyMMdd(Date date){
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		return sf.format(date);
	}
	
	public static Date parseyyyyMMdd(String source){
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sf.parse(source);
		} catch (ParseException e) {
			throw new IllegalArgumentException("生日格式错误");
		}
	}
	
	public static Date parseTime(String str){
		String patter = "yyyy-MM-dd HH:mm:ss.SSS";
		SimpleDateFormat sf=new SimpleDateFormat(patter);
		try {
			return sf.parse(str);
		} catch (ParseException e) {
			throw new IllegalArgumentException("时间格式错误");
		}
	}
	
	public static String formatTime(Date date){
		String patter = "yyyy-MM-dd HH:mm:ss.SSS";
		SimpleDateFormat sf=new SimpleDateFormat(patter);
		return sf.format(date);
	}
	
	public static String getIntervalTime(Date date){
		String result = "";
		DateTime end = new DateTime(); 
		DateTime begin = new DateTime(date);
		Duration d = new Duration(begin, end);
		int[] arr = new int[]{1,24,7*24,4*7*24,365*24};
		int index = 0;
		for (int i = 0; i < arr.length; i++) {
			if(d.getStandardHours() >= arr[i]){
				index = index + 1;
			}
		}
		switch (index) {
		case 0:
			result = d.getStandardMinutes()+"分前";
			break;
		case 1:
			result = d.getStandardHours()+"小时前";
			break;
		case 2:
			result = d.getStandardDays()+"天前";
			break;
		case 3:
			Period pw = new Period(begin, end, PeriodType.weeks());
			result = pw.getWeeks()+"周前";
			break;
		case 4:
			Period pm = new Period(begin, end, PeriodType.months());
			if(pm.getMonths() > 0){
				result = pm.getMonths()+"月前";
			}else{
				result = "4周前";
			}
			break;	
		case 5:
			Period py = new Period(begin, end, PeriodType.years());
			result = py.getYears()+"年前";
			break;
		default:
			break;
		}
		return result;
	}
	
	public static void main(String[] args) {
		DateTime begin = new DateTime(2014,10,18,10,10,30);
		DateTime end = new DateTime();
		Period pw = new Period(begin, end, PeriodType.weeks());
		System.out.println(pw.getWeeks());
		
		String aa = "2014-07-05";
		//Date date = DateUtils.parseBirthday(aa);
		//String r = DateUtils.getIntervalTime(date);
		//System.out.println(r);
	}
	
}
