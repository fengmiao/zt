package com.mt.zt.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.mt.zt.exception.ImDataAccessException;
import com.mt.zt.im.IUserHandler;
import com.mt.zt.im.model.ImUserModel;
import com.mt.zt.utils.JsonUtils;

@Service("userHandler")
public class UserHandlerImpl extends BaseImHandler implements IUserHandler {

	private static final Logger logger = LoggerFactory.getLogger(BaseImHandler.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ImApiUrl apiUrl;

	public String getBaseUrl(){
		return apiUrl.SERVER_HOST_URL+"/users";
	}
	
	@Override
	public void addUser(ImUserModel user) {
		String url = getBaseUrl();
		HttpEntity<ImUserModel> request=new HttpEntity<ImUserModel>(user, bulidHttpHeader());
		try {
			restTemplate.postForObject(url, request, String.class);
		} catch (RestClientException e) {
			e.printStackTrace();
			handlerImDataAccessException(e, request.toString(), url, HttpMethod.POST);
		} 
	}

	@Override
	public void updateUserPassword(String userName, String password) {
		String url = getBaseUrl()+"/{username}/password";
		String r = "{\"newpassword\":\""+password+"\"}";
		HttpEntity<String> request=new HttpEntity<String>(r, bulidHttpHeader());
		try {
			restTemplate.put(url, request, userName);
		} catch (RestClientException e) {
			e.printStackTrace();
			url = url.replace("{username}", userName);
			handlerImDataAccessException(e, request.toString(), url, HttpMethod.PUT);
		}
	}

	@Override
	public void updateUserNickName(String userName, String nickName) {
		String url = getBaseUrl()+"/{username}";
		String r = "{\"nickname\":\""+nickName+"\"}";
		HttpEntity<String> request=new HttpEntity<String>(r, bulidHttpHeader());
		try {
			restTemplate.put(url, request, userName);
		} catch (RestClientException e) {
			e.printStackTrace();
			url = url.replace("{username}", userName);
			handlerImDataAccessException(e, request.toString(), url, HttpMethod.PUT);
		}
	}

	@Override
	public void addFriend(String userName, String friendName) {
		String url = getBaseUrl()+"/{owner_username}/contacts/users/{friend_username}";
		HttpEntity<String> request=new HttpEntity<String>(bulidHttpHeader());
		try {
			restTemplate.postForLocation(url, request, userName,friendName);
		} catch (RestClientException e) {
			e.printStackTrace();
			url = url.replace("{username}", userName).replace("{friend_username}", friendName);
			handlerImDataAccessException(e, request.toString(), url, HttpMethod.POST);
		}
	}

	@Override
	public void deleteFriend(String userName, String friendName) {
		String url = getBaseUrl()+"/{owner_username}/contacts/users/{friend_username}";
		HttpEntity<String> request=new HttpEntity<String>(bulidHttpHeader());
		try {
			ResponseEntity<String> exchange = restTemplate.exchange(
					url,
		            HttpMethod.DELETE,
		            request,
		            String.class,
		            userName,friendName);
		} catch (RestClientException e) {
			e.printStackTrace();
			url = url.replace("{username}", userName).replace("{friend_username}", friendName);
			handlerImDataAccessException(e, request.toString(), url, HttpMethod.DELETE);
		}
	}

	@Override
	public void addBlocks(String userName, String[] friendNames) {
		String url = getBaseUrl()+"/{owner_username}/blocks/users";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("usernames", friendNames);
		String r = JsonUtils.mapToJson(map);
		HttpEntity<String> request=new HttpEntity<String>(r,bulidHttpHeader());
		try {
			ResponseEntity<String> exchange = restTemplate.exchange(
					url,
		            HttpMethod.POST,
		            request,
		            String.class,
		            userName);
		} catch (RestClientException e) {
			e.printStackTrace();
			url = url.replace("{owner_username}", userName);
			handlerImDataAccessException(e, request.toString(), url, HttpMethod.POST);
		}
	}
	
	
	@Override
	public void deleteBlocks(String userName, String friendName) {
		String url = getBaseUrl()+"/{owner_username}/blocks/users/{blocked_username}";
		HttpEntity<String> request=new HttpEntity<String>(bulidHttpHeader());
		try {
			ResponseEntity<String> exchange = restTemplate.exchange(
					url,
		            HttpMethod.POST,
		            request,
		            String.class,
		            userName,friendName);
		} catch (RestClientException e) {
			e.printStackTrace();
			url = url.replace("{owner_username}", userName).replace("{blocked_username}", friendName);
			handlerImDataAccessException(e, request.toString(), url, HttpMethod.POST);
		}
	}


	@Override
	public ImUserModel getUser(String imAccount) {
		String url = getBaseUrl()+"/"+imAccount;
		HttpGet get = new HttpGet(url);
		get.addHeader("Content-Type","application/json");
		get.addHeader("Authorization", "Bearer "+token);
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		CloseableHttpClient client = httpClientBuilder.build();
		ImUserModel model = null;
		try {
			//HttpContext localContext = new BasicHttpContext();
			HttpClientContext context = HttpClientContext.create();
			HttpResponse response = client.execute(get,context);
			if(response.getStatusLine().getStatusCode() == 200){
				org.apache.http.HttpEntity entity = response.getEntity();
				String responseContent = EntityUtils.toString(entity, "UTF-8");
				Map<String, Map<String, Object>> maps =  JsonUtils.jsonToMap(responseContent);
				List<Map<String, Object>> list = (List<Map<String, Object>>) maps.get("entities");
				Map<String, Object> r = list.get(0);
				model = new ImUserModel();
				model.setUsername(String.valueOf(r.get("username")));
				model.setNickname(String.valueOf(r.get("nickname")));
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append("Error! ");
			sb.append(" url: " + url);
			sb.append(" method: " + HttpMethod.GET);
			sb.append(" request: " + get);
			sb.append(" errorMsg: " + e.getMessage());
			logger.error(sb.toString());
			throw new ImDataAccessException(sb.toString(), e);
		}
		return model;
	}


	@Override
	public void deleteUser(String imAccount) {
		String url = getBaseUrl()+"/"+imAccount;
		HttpEntity<String> request=new HttpEntity<String>(bulidHttpHeader());
		try {
			ResponseEntity<String> exchange = restTemplate.exchange(
					url,
		            HttpMethod.DELETE,
		            request,
		            String.class);
		} catch (RestClientException e) {
			e.printStackTrace();
			handlerImDataAccessException(e, request.toString(), url, HttpMethod.DELETE);
		}
	}


}
