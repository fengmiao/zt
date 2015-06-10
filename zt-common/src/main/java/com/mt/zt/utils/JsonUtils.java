package com.mt.zt.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;



public class JsonUtils {

	@SuppressWarnings("unchecked")
	public static Map<String, Map<String, Object>> jsonToMap(String json) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Map<String, Object>> map = null;
		try {
			map = mapper.readValue(json, Map.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public static String mapToJson(Map<String, Object> map) {
		ObjectMapper mapper = new ObjectMapper();
		String r = null;
		try {
			r = mapper.writeValueAsString(map);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	
	
	public static String beanToJson(Object objet){
		ObjectMapper mapper = new ObjectMapper();
		String value = null;
		try {
			value = mapper.writeValueAsString(objet);
			//System.out.println(value);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public static void sysout(Object objet){
		System.out.println(beanToJson(objet));
	}
	

	public static void main(String[] args) {
		/**
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"action\" : \"post\",");
		sb.append("\"application\" : \"4d7e4ba0-dc4a-11e3-90d5-e1ffbaacdaf5\",");
		sb.append("\"params\" : { },");
		sb.append("\"uri\" : \"https://a1.easemob.com/easemob-demo/chatdemoui\",");
		sb.append("\"entities\" : [ ],");
		sb.append("\"data\" : {");
		sb.append("\"groupid\" : \"1411527886490154\"");
		sb.append("},");
		sb.append("\"timestamp\" : 1411527886457,");
		sb.append("\"duration\" : 125,");
		sb.append("\"organization\" : \"easemob-demo\",");
		sb.append("\"applicationName\" : \"chatdemoui\"");
		sb.append("}");
		Map<String, Map<String, Object>> maps = JsonUtils.jsonToMap(sb
				.toString());
		System.out.println(maps.size());
		System.out.println(maps.get("applicationName"));
		**/
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "json");
		map.put("bool", Boolean.TRUE);
		map.put("int", new Integer(1));
		map.put("arr", new String[] { "a", "b" });
		JsonUtils.mapToJson(map);
	}

}
