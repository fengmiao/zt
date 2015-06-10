package com.mt.zt.utils;

import java.math.BigDecimal;

public class DoubleUtils {

	public static double getRandomDouble(double min,double max,double x){
		double r = 0;
		do {
			r = x*Math.random() + min;
		} while (r > max);
		return r;
	}
	
	public static double getScale(int sacle,double d){
		return new BigDecimal(d).setScale(sacle, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			double lng = DoubleUtils.getScale(6,DoubleUtils.getRandomDouble(113.932, 113.999, 0.01));
			System.out.println(lng);
		}
	}
	
}
