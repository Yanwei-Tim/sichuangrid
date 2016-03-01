package com.tianque.web.api.vo;

import java.io.Serializable;

/**
 * @ClassName: Response
 * @Description: 返回对象
 * @author wangxiaohu wsmalltiger@163.com
 * @date 2014年10月22日 下午3:51:13
 */
public class Response implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String RESULT_CODE_SUCCESS = "0";
	public static final String RESULT_CODE_FAIL = "1";

	private Head head;
	private Body body;

	public Response(Head head, Body body) {
		super();
		this.head = head;
		this.body = body;
	}

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

}
