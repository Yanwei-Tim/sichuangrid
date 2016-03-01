package com.tianque.domain;

import java.util.Map;

public class AmchartBean implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String color;// 该软件在amchart报表中显示的颜色
	private Map<String, Integer> dataMap;// 存放统计信息
	private Map<String, String> hrefMap;// 存放统计信息

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Map<String, Integer> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Integer> dataMap) {
		this.dataMap = dataMap;
	}

	public Map<String, String> getHrefMap() {
		return hrefMap;
	}

	public void setHrefMap(Map<String, String> hrefMap) {
		this.hrefMap = hrefMap;
	}
}
