package com.tianque.web.api.vo;

import java.io.Serializable;

/**
 * @ClassName: Head
 * @Description: 返回头
 * @author wangxiaohu wsmalltiger@163.com
 * @date 2014年10月22日 下午3:51:00
 */
public class Head implements Serializable {
	private static final long serialVersionUID = 1L;

	private String resultCode;
	private String resultMsg;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public static Head createHead(boolean requestError, String errorMessage) {
		Head head = new Head();
		head.setResultCode(requestError ? Response.RESULT_CODE_FAIL
				: Response.RESULT_CODE_SUCCESS);
		head.setResultMsg(errorMessageConvert(requestError, errorMessage));
		return head;
	}

	private static String errorMessageConvert(boolean requestError,
			String errorMessage) {
		if (requestError) {
			if (errorMessage == null || errorMessage.trim().equals("")) {
				return "请求失败!";
			}
			return errorMessage;
		}
		return null;
	}

}
