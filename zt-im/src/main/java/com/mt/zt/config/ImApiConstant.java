package com.mt.zt.config;

import java.net.URI;



import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.mt.zt.im.model.HttpEntityEnclosingDeleteRequest;



@Configuration
// @PropertySource(name="imconfig", value =
// {"classpath:business/im_api.properties"})
@ComponentScan(basePackages = { "com.feixun.jd.im" })
@PropertySource("classpath:business/im_api.properties")
public class ImApiConstant {

	@Autowired
	private Environment env;
	
	private final boolean IGNORE_UNRESOLVABLE_PLACEHOLDERS = true;

	@Bean
	public RestTemplate getRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory() {
	        @Override
	        protected HttpUriRequest createHttpUriRequest(HttpMethod httpMethod, URI uri) {
	            if (HttpMethod.DELETE == httpMethod) {
	                return new HttpEntityEnclosingDeleteRequest(uri);
	            }
	            return super.createHttpUriRequest(httpMethod, uri);
	        }
	    });
		return restTemplate;
	}

	@Bean
	public PropertySourcesPlaceholderConfigurer getProperties() {
		//String profile = System.getProperty("spring.profiles.active", "default");
		PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
		Resource[] resources = new Resource[] {
				new ClassPathResource("business/im_api.properties")};
		pspc.setLocations(resources);
		//pspc.setIgnoreUnresolvablePlaceholders(IGNORE_UNRESOLVABLE_PLACEHOLDERS);
		//pspc.setIgnoreResourceNotFound(true);
		return pspc;
	}
}
