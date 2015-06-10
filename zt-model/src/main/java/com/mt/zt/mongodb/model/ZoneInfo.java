package com.mt.zt.mongodb.model;


import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="zone_info")
public class ZoneInfo {

	@Id
	private String zoneId;
	
	private String name;
	
	private String address;
	
	private List<String> wifis;
	
	private String imGroup;

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<String> getWifis() {
		return wifis;
	}

	public void setWifis(List<String> wifis) {
		this.wifis = wifis;
	}

	public String getImGroup() {
		return imGroup;
	}

	public void setImGroup(String imGroup) {
		this.imGroup = imGroup;
	}
	
}
