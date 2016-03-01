package com.tianque.core.struts.interceptor;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.util.FormTokenHelper;

/**
 * @ClassName: FormTokenInterceptor
 * @Description: (表单防重复提交拦截器)
 * @author yumeng
 * @date 2013-5-1 下午01:39:13
 */
@SuppressWarnings("serial")
public class FormTokenInterceptor extends AbstractInterceptor {

	public static final String INVALID_TOKEN_CODE = "invalid.token";

	@Autowired
	private CacheService cacheService;

	@SuppressWarnings("unchecked")
	public String intercept(ActionInvocation invocation) throws Exception {
		String tokenValue = FormTokenHelper.getToken(FormTokenHelper.TOKEN_NAME);

		if (tokenValue != null && !FormTokenHelper.validToken(cacheService)) {
			return handleInvalidToken(invocation);
		}
		return invocation.invoke();
	}

	private String handleInvalidToken(ActionInvocation invocation) throws Exception {
		return INVALID_TOKEN_CODE;
	}
}
