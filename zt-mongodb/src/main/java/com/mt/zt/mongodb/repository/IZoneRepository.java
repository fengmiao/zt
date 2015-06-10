package com.mt.zt.mongodb.repository;

import com.mt.zt.mongodb.model.ZoneInfo;

public interface IZoneRepository{

	public ZoneInfo findByWifiMac(String linkWifiMac);

	public void saveZoneInfo(ZoneInfo zi);

	public void addZoneWifi(String zoneId,String wifi);

}
