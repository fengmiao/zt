package com.mt.zt.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class ListUtils {

	/**
     * 用于把List&lt;Object>转换成Map&lt;String,Object>形式，便于存入缓存
     * @author zhang_bo
     * @param keyName 主键属性
     * @param list 集合
     * @return 返回对象
     */
    public static <T> Map<String, T> listToMap(String keyName, List<T> list){
        Map<String, T> m = new HashMap<String, T>();
        try {
            for (T t : list) {
                PropertyDescriptor pd = new PropertyDescriptor(keyName,t.getClass());
                Method getMethod = pd.getReadMethod();// 获得get方法
                Object o = getMethod.invoke(t);// 执行get方法返回一个Object
                m.put(o.toString(), t);
            }
            return m;
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return null;
    }
    
    
    public static <T> T[] toArray(List<T> list,T[] result){
		list.toArray(result);
		return result;
    }
	
}
