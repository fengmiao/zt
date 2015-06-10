package com.mt.zt.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ImApiUrl {
	
	@Value("${im.server.host.url}")
	public String SERVER_HOST_URL;
	
	@Value("${im.user.base.url}")
	public String USER_BASE_URL;
	
	@Value("${im.chatgroup.base.url}")
	public String CHATGROUP_BASE_URL;
	
	@Value("${im.app.client.id}")
	public String APP_CLIENT_ID;
	
	@Value("${im.app.client.secret}")
	public String APP_CLIENT_SECRET;
	
}
