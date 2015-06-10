package com.mt.zt.impl;

import java.net.URI;

import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.mt.zt.im.model.HttpEntityEnclosingDeleteRequest;

public class CustomRestTemplate extends RestTemplate {
	
	public void setRequestFactory(ClientHttpRequestFactory requestFactory) {
		
	}
	
	public HttpComponentsClientHttpRequestFactory bulidHttpRequestFactory(){
		HttpComponentsClientHttpRequestFactory rf = new HttpComponentsClientHttpRequestFactory() {
	        @Override
	        protected HttpUriRequest createHttpUriRequest(HttpMethod httpMethod, URI uri) {
	            if (HttpMethod.DELETE == httpMethod) {
	                return new HttpEntityEnclosingDeleteRequest(uri);
	            }
	            /**
	            if (HttpMethod.GET ==  httpMethod) {
	            	return new HttpEntityEnclosingGetRequest(uri);
	            }
	            **/
	            return super.createHttpUriRequest(httpMethod, uri);
	        }
	    };
		return rf;
	}

}
