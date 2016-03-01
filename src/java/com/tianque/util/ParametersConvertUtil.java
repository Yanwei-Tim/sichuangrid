package com.tianque.util;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.base.BaseDomain;

public class ParametersConvertUtil {

	public static List<String> convertToListString(List parameters) {
		if(parameters == null || parameters.size() < 1){
			return null;
		}
		List<String> results =  new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		int i = 0;
		for(Object parameter : parameters){
			String p;
			if(parameter instanceof BaseDomain){
				p = ((BaseDomain) parameter).getId().toString();
			}else{
				p = parameter.toString();
			}
			i++;
			if(sb.length() > 0){
				sb.append(",").append(p);
			}else{
				sb.append(p);
			}
			if(i % 1000 == 0){
				results.add(sb.toString());
				sb = new StringBuffer();
			}
			
		}
		if(sb.length() > 0){
			results.add(sb.toString());
		}
		return results;
	}
	
	public static List<String> nullStringList() {
		List<String> list = new ArrayList<String>();
		list.add("null");
		return list;
	}
}
