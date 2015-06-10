package com.mt.zt.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.mt.zt.im.IChatGroupHandler;
import com.mt.zt.im.model.ImChatGroupModel;
import com.mt.zt.utils.JsonUtils;

@Service("chatGroupHandler")
public class ChatGroupHandlerImpl extends BaseImHandler implements IChatGroupHandler {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ImApiUrl apiUrl;
	
	public String getBaseUrl(){
		return apiUrl.SERVER_HOST_URL+ "/chatgroups";
	}
	
	@Override
	public String addChatGroup(ImChatGroupModel group) {
		String url = getBaseUrl();
		HttpEntity<ImChatGroupModel> request=new HttpEntity<ImChatGroupModel>(group, bulidHttpHeader());
		String groupId = null;
		try {
			String body = restTemplate.postForObject(url, request, String.class);
			Map<String, Map<String, Object>> maps =  JsonUtils.jsonToMap(body);
			groupId = (String) maps.get("data").get("groupid");
		} catch (RestClientException e) {
			e.printStackTrace();
			handlerImDataAccessException(e, request.toString(), url, HttpMethod.POST);
		}
		return groupId;
	}

	@Override
	public void addMember(String groupId, String userName) {
		String url = getBaseUrl()+"/{group_id}/users/{user_primary_key}";
		HttpEntity<String> request=new HttpEntity<String>(bulidHttpHeader());
		try {
			restTemplate.postForObject(url, request, String.class,groupId,userName);
		} catch (RestClientException e) {
			e.printStackTrace();
			url = url.replace("{group_id}", groupId).replace("{user_primary_key}", userName);
			handlerImDataAccessException(e, request.toString(), url, HttpMethod.POST);
		}
	}

	@Override
	public void deleteMember(String groupId, String userName) {
		String url = getBaseUrl()+"/{group_id}/users/{user_primary_key}";
		HttpEntity<String> request=new HttpEntity<String>(bulidHttpHeader());
		try {
			ResponseEntity<String> exchange = restTemplate.exchange(
					url,
		            HttpMethod.DELETE,
		            request,
		            String.class,
		            groupId,
		            userName);
		} catch (RestClientException e) {
			e.printStackTrace();
			url = url.replace("{group_id}", groupId).replace("{user_primary_key}", userName);
			handlerImDataAccessException(e, request.toString(), url, HttpMethod.DELETE);
		}
	}

	@Override
	public void addChatGroupMembers(String groupId, String[] userIds) {
		URI uri;
		try {
			uri = new URI(getBaseUrl()+String.format("/%s/users/", groupId));
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("usernames", userIds);
			String body = JsonUtils.mapToJson(map);
			RequestEntity<String> request = new RequestEntity<String>(body,bulidHttpHeaderMap(), HttpMethod.POST, uri);
			try {
				ResponseEntity<String> result = restTemplate.exchange(request, String.class);
			} catch (RestClientException e) {
				e.printStackTrace();
				handlerImDataAccessException(e, request.toString(), uri.toString(), HttpMethod.POST);
			}
		} catch (URISyntaxException ex) {
			throw new IllegalStateException("Could not create URI object: " + ex.getMessage(), ex);
		}
	}
	
	public static void main(String[] args) {
		String url = "/%s/users/";
		System.out.println(String.format(url, "aa"));
		System.out.println(StringUtils.join(new String[]{"aa","bb"}, ","));
	}

	@Override
	public void updateChatGroupName(String groupId, String name) {
		String url = getBaseUrl()+"/{group_id}";
		if (StringUtils.isNotEmpty(name) ) {
			String r = "{\"groupname\":\""+name+"\",\"desc\":\""+name+"\"}";
			System.out.println(r);
			HttpEntity<String> request=new HttpEntity<String>(r, bulidHttpHeader());
			try {
				restTemplate.put(url, request, groupId);
			} catch (RestClientException e) {
				e.printStackTrace();
				url = url.replace("{group_id}", groupId);
				handlerImDataAccessException(e, request.toString(), url, HttpMethod.PUT);
			}
		}
	}

	@Override
	public void updateChatGroupMaxusers(String groupId, Integer maxusers) {
		String url = getBaseUrl()+"/{group_id}";
		if (maxusers != null) {
			String r = "{\"maxusers\":\""+maxusers+"\"}";
			System.out.println(r);
			HttpEntity<String> request=new HttpEntity<String>(r, bulidHttpHeader());
			try {
				restTemplate.put(url, request, groupId);
			} catch (RestClientException e) {
				e.printStackTrace();
				url = url.replace("{group_id}", groupId);
				handlerImDataAccessException(e, request.toString(), url, HttpMethod.PUT);
			}
		}
		
	}

}
