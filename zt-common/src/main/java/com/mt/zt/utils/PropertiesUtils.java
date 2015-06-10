package com.mt.zt.utils;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class PropertiesUtils {
	
	private static final PropertiesUtils p = new PropertiesUtils();
	
	private static final Map<String,String> maps = new HashMap<>();
	
	private PropertiesUtils(){}
	
	public static void addPropertiesFile(Resource resource){
		try {
			Properties props = PropertiesLoaderUtils.loadProperties(resource);
			if (!props.isEmpty()) {
				Enumeration<?> e = props.propertyNames();
				while (e.hasMoreElements()) {
					String key = (String) e.nextElement();
					String value = props.getProperty(key);
					if (key != null) {
						maps.put(key, value);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void addPropertiesFile(Resource[] resource){
		for (int i = 0; i < resource.length; i++) {
			addPropertiesFile(resource[i]);
		}
	}
	
	public static String getPropertie(String key){
		return maps.get(key);
	}
	
}
