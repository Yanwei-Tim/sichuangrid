package com.tianque.plugin.analysisNew.domain;

import java.io.Serializable;

public class IssueLineDataColumnVo implements Serializable {
	private String name;
	private Object[] data;
	private String stack;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStack() {
		return stack;
	}

	public void setStack(String stack) {
		this.stack = stack;
	}

	public void setData(Object[] data) {
		this.data = data;
	}

	public Object[] getData() {
		return data;
	}
}
