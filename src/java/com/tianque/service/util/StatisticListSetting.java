package com.tianque.service.util;

import java.util.HashMap;
import java.util.Map;

public class StatisticListSetting {
	private String tableName;
	private String domainName;
	private String propertyField;
	private Map<String, Object> countMap;
	private String searchField;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getPropertyField() {
		return propertyField;
	}

	public void setPropertyField(String propertyField) {
		this.propertyField = propertyField;
	}

	public Map<String, Object> getCountMap() {
		return countMap;
	}

	public void setCountMap(Map<String, Object> countMap) {
		this.countMap = countMap;
	}

	public StatisticListSetting(String tableName, String domainName, String propertyField,
			String searchField, String[] keys, Object[] values) {
		this.tableName = tableName;
		this.domainName = domainName;
		this.propertyField = propertyField;
		this.searchField = searchField;
		if (keys != null && values != null && keys.length > 0 && values.length > 0) {
			this.countMap = new HashMap<String, Object>();
			for (int i = 0; i < keys.length; i++) {
				this.countMap.put(keys[i], values[i]);
			}
		} else {
			this.countMap = null;
		}

	}

	public StatisticListSetting(String tableName) {
		this(tableName, null, null, null, null, null);

	}

	public StatisticListSetting(String tableName, String searchField) {
		this(tableName, null, null, searchField, null, null);

	}

	public StatisticListSetting(String tableName, String domainName, String propertyField) {
		this(tableName, domainName, propertyField, null, null, null);

	}

	public StatisticListSetting(String tableName, String domainName, String propertyField,
			String searchField) {
		this(tableName, domainName, propertyField, searchField, null, null);

	}

	public StatisticListSetting(String string, String houseType, String[] strings, Object[] objects) {
		this(string, houseType, null, null, strings, objects);
	}

	public String getSearchField() {
		return searchField;
	}

	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}

}
