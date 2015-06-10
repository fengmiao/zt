package com.mt.zt.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mt.zt.im.IManageHandler;
import com.mt.zt.utils.JsonUtils;

@Service("managerHandler")
public class ManagerHandlerImpl implements IManageHandler {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ImApiUrl apiUrl;	
	
	@Override
	public String queryManagerToken() {
		String clientId = apiUrl.APP_CLIENT_ID;
		String clientSecret = apiUrl.APP_CLIENT_SECRET;
		String grantType = "client_credentials";
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"grant_type\":\""+grantType+"\",");
		sb.append("\"client_id\":\""+clientId+"\",");
		sb.append("\"client_secret\":\""+clientSecret+"\"");
		sb.append("}");
		String url = getBaseUrl()+"/token";
		HttpEntity<String> request=new HttpEntity<String>(sb.toString(), bulidHttpHeader());
		String body = restTemplate.postForObject(url, request, String.class);
		Map<String, Map<String, Object>> maps = JsonUtils.jsonToMap(body);
		String token = String.valueOf(maps.get("access_token"));
		return token;
	}
	
	private String getBaseUrl(){
		return "http://a1.easemob.com/nickfeng/jdim";
	}
	
	private HttpHeaders bulidHttpHeader() {  
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;  
    }

}
