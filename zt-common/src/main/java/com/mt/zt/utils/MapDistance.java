package com.mt.zt.utils;

import java.math.BigDecimal;

public class MapDistance {

	private static double EARTH_RADIUS = 6378.137;

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	public static double getDistance(double lat1, double lng1, double lat2,
			double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		BigDecimal d = new BigDecimal(s);
		double f = d.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f;
	}
	
	public static String getDistanceStr(double lat1, double lng1, double lat2,
			double lng2) {
		return MapDistance.getDistance(lat1, lng1, lat2, lng2) + "km";
	}

	public static String formatDistanceStr(double distance) {
		BigDecimal d = new BigDecimal(distance);
		double f = d.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f + "km";
	}
	
	public static void main(String[] args) {
		double d = MapDistance.getDistance(32.0, 119.0, 31.956929, 118.985266);
		System.out.println(d);
	}

}
