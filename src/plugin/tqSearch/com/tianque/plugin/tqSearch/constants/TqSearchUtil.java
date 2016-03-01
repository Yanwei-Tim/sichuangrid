package com.tianque.plugin.tqSearch.constants;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.tianque.core.util.StringUtil;
import com.tianque.exception.base.SystemUtilException;

public class TqSearchUtil {
	 /** 
     * 将一个 Map 对象转化为一个 JavaBean 
     * @param type 要转化的类型 
     * @param map 包含属性值的 map 
     * @return 转化出来的 JavaBean 对象 
     */ 
    public static Object toBean(Class beanClazz, Map map) { 
    	try {
			BeanInfo beanInfo = Introspector.getBeanInfo(beanClazz); // 获取类属性 
			Object obj = beanClazz.newInstance(); // 创建 JavaBean 对象 
			
			PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors(); 
			for (int i = 0; i< propertyDescriptors.length; i++) { 
			    PropertyDescriptor descriptor = propertyDescriptors[i]; 
			    String propertyName = descriptor.getName(); 
			    Class clazz = descriptor.getPropertyType();
			    Object value = map.get(propertyName);
			    if (value!=null && StringUtil.isStringAvaliable(value+"")) {
			        if("Long".equals(clazz.getSimpleName())){
			        	value = Long.parseLong(value+"");
			        }else if("Integer".equals(clazz.getSimpleName())){
			        	value = Integer.parseInt(value+"");
			        }else if("Double".equals(clazz.getSimpleName())){
			        	value = Double.parseDouble(value+"");
			        }else if("String".equals(clazz.getSimpleName())){
			        	value = value+"";
			        }
			        descriptor.getWriteMethod().invoke(obj, new Object[]{value}); 
			    } 
			} 
			return obj;
		} catch (Exception e) {
			throw new SystemUtilException("将一个 Map 对象转化为一个 JavaBean报错", e);
		}
    }
    
    /** 
     * 将一个 JavaBean 对象转化为一个  Map 
     * @param bean 要转化的JavaBean 对象 
     * @return 转化出来的  Map 对象 
     * @throws IntrospectionException 如果分析类属性失败 
     * @throws IllegalAccessException 如果实例化 JavaBean 失败 
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败 
     */ 
    public static Map toMap(Object bean) { 
        try {
			Class type = bean.getClass(); 
			Map returnMap = new HashMap(); 
			BeanInfo beanInfo = Introspector.getBeanInfo(type); 

			PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors(); 
			for (int i = 0; i< propertyDescriptors.length; i++) { 
			    PropertyDescriptor descriptor = propertyDescriptors[i]; 
			    String propertyName = descriptor.getName(); 
			    if (!propertyName.equals("class")) { 
			        Method readMethod = descriptor.getReadMethod(); 
			        Object result = readMethod.invoke(bean, new Object[0]); 
			        if (result != null) { 
			            returnMap.put(propertyName, result); 
			        }
			    }
			}
			return returnMap;
		} catch (Exception e) {
			throw new SystemUtilException("将一个 JavaBean 对象转化为一个  Map", e);
		}
    }
    
    public static Map toMap(Map<String,String> formatMap, Object bean) {
    	try {
    		Map<String,Object> result = new HashMap<String, Object>();
    		Class beanClazz = bean.getClass(); 
			BeanInfo beanInfo = Introspector.getBeanInfo(beanClazz); // 获取类属性 
			PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors(); 
			for (int i = 0; i< propertyDescriptors.length; i++) { 
			    PropertyDescriptor descriptor = propertyDescriptors[i]; 
			    String propertyName = descriptor.getName(); 
			    if (formatMap.get(propertyName)!=null) { 
			        Method readMethod = descriptor.getReadMethod(); 
			        Object value = readMethod.invoke(bean, new Object[0]); 
			        result.put(propertyName, value);
			    }
			} 
			return result;
		} catch (Exception e) {
			throw new SystemUtilException("将一个对象转化为一个 规定字段的Map集合报错", e);
		}
    }
}
