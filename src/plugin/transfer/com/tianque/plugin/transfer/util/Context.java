package com.tianque.plugin.transfer.util;

import java.util.HashMap;
import java.util.Map;

public class Context {

	private Map<String, Object> map;
	private Map<String, Object> oldMap;
	private Map<String, Object> newMap;
	private boolean isExistedToOrgId;

	public Context() {
		map = new HashMap<String, Object>();
		oldMap = new HashMap<String, Object>();
		newMap = new HashMap<String, Object>();
		isExistedToOrgId = false;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public Map<String, Object> getOldMap() {
		return oldMap;
	}

	public void setOldMap(Map<String, Object> oldMap) {
		this.oldMap = oldMap;
	}

	public Map<String, Object> getNewMap() {
		return newMap;
	}

	public void setNewMap(Map<String, Object> newMap) {
		this.newMap = newMap;
	}

	public boolean isExistedToOrgId() {
		return isExistedToOrgId;
	}

	public void setExistedToOrgId(boolean isExistedToOrgId) {
		this.isExistedToOrgId = isExistedToOrgId;
	}

}
