package com.mt.zt.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;

import com.mt.zt.exception.ImDataAccessException;

public abstract class BaseImHandler {

	private static final Logger logger = LoggerFactory.getLogger(BaseImHandler.class);
	
	//nickfeng
	//public static String token = "YWMtUzNp_MewEeS2NyfEIZORqwAAAU08UYXQLi6HYQAc2hf0qC2mPw-qwT2Fn24";
	//sazwdk
	public static String token = "YWMtrlrpBMY0EeSdQ8tNmCXmyAAAAU0ymXz_u-FbkJ2A_-62al_SuloeXbk3DI8";
	
	
	public abstract String getBaseUrl();
	
	protected HttpHeaders bulidHttpHeader() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		//headers.set("Content-Type","application/json");
		headers.set("Authorization", "Bearer "+token);
        return headers;
    }
	
	protected MultiValueMap<String, String> bulidHttpHeaderMap() {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("Content-Type", "application/json");
		map.add("Authorization", "Bearer "+token);
        return map;
    }
	
	protected void handlerImDataAccessException(RestClientException ex,String request,String url,HttpMethod method){
		StringBuilder sb = new StringBuilder();
		sb.append("Error! ");
		sb.append(" url: " + url);
		sb.append(" method: " + method);
		sb.append(" request: " + request);
		sb.append(" errorMsg: " + ex.getMessage());
		logger.error(sb.toString());
		throw new ImDataAccessException(sb.toString(), ex);
	}
	
}
