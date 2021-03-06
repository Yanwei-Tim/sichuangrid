package com.tianque.issue.vo;

import java.io.Serializable;

public class IssueTypeViewDefine implements Serializable {

	private String typeName;

	private int titleWidth;

	private int column;

	private int width;

	public IssueTypeViewDefine() {
		this("");
	}

	public IssueTypeViewDefine(String typename) {
		this(typename, 4);
	}

	public IssueTypeViewDefine(String typename, int titleWidth) {
		this(typename, titleWidth, 2, 300);
	}

	public IssueTypeViewDefine(String typename, int titleWidth, int column, int width) {
		this.typeName = typename;
		this.titleWidth = titleWidth;
		this.column = column;
		this.width = width;
	}

	public int getTitleWidth() {
		return titleWidth;
	}

	public void setTitleWidth(int titleWidth) {
		this.titleWidth = titleWidth;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
