package com.tianque.plugin.tqSearch.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class TqSearchResults implements Serializable {
	private static final long serialVersionUID = 1L;

	private int pageNum;

	private int pageSize;

	private List<Map<String, Object>> result;

	private int countNum;

	public TqSearchResults() {
	}

	public TqSearchResults(int pageNum, int pageSize, Integer countNum,
			List<Map<String, Object>> list) {
		setResult(list);
		setCountNum(countNum);
		setPageNum(pageNum);
		setPageSize(pageSize);

	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<Map<String, Object>> getResult() {
		return result;
	}

	public void setResult(List<Map<String, Object>> result) {
		this.result = result;
	}

	public int getCountNum() {
		return countNum;
	}

	public void setCountNum(int countNum) {
		this.countNum = countNum;
	}
}
