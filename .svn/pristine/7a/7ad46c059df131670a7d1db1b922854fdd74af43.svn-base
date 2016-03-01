package com.tianque.core.struts.interceptor;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.FormTokenHelper;
import com.tianque.core.util.NotNeedInterceptMethod;

/***
 * @说明：拦截提交的表单数据，过滤掉其中的一些js
 * @author 龙振东
 * 
 */

@SuppressWarnings("serial")
public class CrossAttackInterceptor extends AbstractInterceptor {

	public static final String ILLEGAL_ATTACK = "illegal_attack";
	public static final String NOT_NEED_INTERCEPT_METHOD="enclosureUpload";

	@Autowired
	private ValidateHelper validateHelper;

	@SuppressWarnings("unchecked")
	public String intercept(ActionInvocation invocation) throws Exception {
		String methodName = invocation.getInvocationContext().getName();
		if(NotNeedInterceptMethod.methodNames.get(methodName)!=null){
			return invocation.invoke();
		}
		Map params = ActionContext.getContext().getParameters();
		String formString = FormTokenHelper.requestParameters(params);
		if (validateHelper.illegalScript(formString)) {
			return ILLEGAL_ATTACK;
		}
		return invocation.invoke();
	}
}
