/*   
 * Copyright (c) 2014-2020 TianQue Ltd. All Rights Reserved.   
 *   
 * This software is the confidential and proprietary information of   
 * TianQue. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with TianQue.
 *   
 */
package com.tianque.core.dubbo.filter;

import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.tianque.core.util.GlobalValue;
import com.tianque.core.util.ThreadVariable;
import com.tianque.exception.base.IllegalOperationException;

/**
 * @ClassName: DubboRPCRequestFilter
 * @Description: dubboRPC请求拦截器，设置用户sessionID
 * @author wangxiaohu wsmalltiger@163.com
 * @date 2015年3月6日 下午4:38:16
 */
public class DubboRPCRequestFilter implements Filter {
	private static final String INIT_APP_COOKIE = "INIT_APP";

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation)
			throws RpcException {
		String url = invoker.getInterface().getName() + "."
				+ invocation.getMethodName();
		if (isNeedFilter(url)) {
			if (ThreadVariable.getSession() != null
					&& ThreadVariable.getSession().getSessionId() != null) {
				RpcContext.getContext().setAttachment("cookie",
						ThreadVariable.getSession().getSessionId());
			}
			return getResult(invoker, invocation);
		}
		if (ThreadVariable.getSession() == null) {
			throw new IllegalOperationException("非法操作，cookie 不存在！");
		}
		String sid = INIT_APP_COOKIE;
		if (!GlobalValue.isInitApp) {
			sid = ThreadVariable.getSession().getSessionId();
		}
		RpcContext.getContext().setAttachment("cookie", sid);
		return getResult(invoker, invocation);
	}

	private Result getResult(Invoker<?> invoker, Invocation invocation) {
		Result result = invoker.invoke(invocation);
		//		logger.error("result=====" + result);
		return result;
	}

	//查看返回数据占用多少字节
	//	public static byte[] ObjectToByte(java.lang.Object obj) {
	//		byte[] bytes = null;
	//		try {
	//			// object to bytearray  
	//			ByteArrayOutputStream bo = new ByteArrayOutputStream();
	//			ObjectOutputStream oo = new ObjectOutputStream(bo);
	//			oo.writeObject(obj);
	//
	//			bytes = bo.toByteArray();
	//
	//			bo.close();
	//			oo.close();
	//		} catch (Exception e) {
	//			System.out.println("translation" + e.getMessage());
	//			e.printStackTrace();
	//		}
	//		return bytes;
	//	}

	private boolean isNeedFilter(String url) {
		for (String witeName : DubboRPCFilter.whiteList) {
			if (url.equals(witeName)) {
				return true;
			}
		}
		return false;
	}
}
