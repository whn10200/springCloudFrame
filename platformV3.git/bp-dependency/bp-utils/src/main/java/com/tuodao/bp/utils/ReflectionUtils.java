package com.tuodao.bp.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 利用反射，将实体转成Map
 * 
 * @author hc
 *
 * @created on 2017年6月15日
 * 
 * @version 1.0.0
 */
public class ReflectionUtils {
	
	public static Map<String, Object> convertBean(Object bean)
			throws IntrospectionException, IllegalAccessException, InvocationTargetException {
		Map<String, Object> returnMap = Maps.newHashMap();
		if(null == bean) return returnMap;
		Class<?> type = bean.getClass();
		BeanInfo beanInfo = Introspector.getBeanInfo(type);

		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			boolean equals = descriptor.getPropertyType().equals(Integer.class);
			if (!propertyName.equals("class")) {
				Method readMethod = descriptor.getReadMethod();
				Object result = readMethod.invoke(bean, new Object[0]);
				if (result != null) {
					returnMap.put(propertyName, result);
				} else {
					if(equals) {
						returnMap.put(propertyName, 0);
					}else {
						returnMap.put(propertyName, "");
					}
					
				}
			}
		}
		return returnMap;
	}
}
