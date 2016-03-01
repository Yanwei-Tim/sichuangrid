package com.tianque.core.util;

import java.util.HashMap;
import java.util.Map;

public class NotNeedInterceptMethod {

	/**不需要form表单拦截的方法名称集合*/
	public final static Map<String,String> methodNames = new HashMap<String,String>();
	
	static{
		methodNames.put("enclosureUpload", "enclosureUpload");
		methodNames.put("editAccountReport", "editAccountReport");
		methodNames.put("copyFile", "copyFile");
	}

}
