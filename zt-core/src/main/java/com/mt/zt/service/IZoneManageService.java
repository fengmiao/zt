package com.mt.zt.service;

import java.util.Map;

import com.mt.zt.vo.entry.ZoneInfoEo;

public interface IZoneManageService {

		public void addZoneInfo(ZoneInfoEo zone);
		
		public void addZoneWifi(String zoneId,String wifiMac);
		
		public Map<String,Boolean> queryMarkWifis(String[] wifimacs);
	
}
