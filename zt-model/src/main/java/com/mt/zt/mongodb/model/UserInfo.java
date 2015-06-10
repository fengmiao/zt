package com.mt.zt.mongodb.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="user_info")
public class UserInfo {

	private String userId;
	
	private String identification;
	
	private String nickName;
	
	private String linkWifiSsid;
	
	private String linkWifiMac;

	private String imAccount;
	
	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getLinkWifiSsid() {
		return linkWifiSsid;
	}

	public void setLinkWifiSsid(String linkWifiSsid) {
		this.linkWifiSsid = linkWifiSsid;
	}

	public String getLinkWifiMac() {
		return linkWifiMac;
	}

	public void setLinkWifiMac(String linkWifiMac) {
		this.linkWifiMac = linkWifiMac;
	}

	public String getImAccount() {
		return imAccount;
	}

	public void setImAccount(String imAccount) {
		this.imAccount = imAccount;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	

}
