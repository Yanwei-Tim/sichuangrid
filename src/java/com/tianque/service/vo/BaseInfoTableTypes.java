package com.tianque.service.vo;

import java.util.HashMap;
import java.util.Map;

public class BaseInfoTableTypes {

	private final static Map<String, String> keyTableTypes = new HashMap<String, String>();

	static {
		keyTableTypes.put("ENTERPRISEKEY", "enterpriseKey");// 规上企业
		keyTableTypes.put("PROTECTIONKEY", "protectionKey");// 重点保障
		keyTableTypes.put("SAFETYPRODUCTIONKEY", "safetyProductionKey");// 安全重点
		keyTableTypes.put("FIRESAFETYKEY", "fireSafetyKey");// 消防安全重点
		keyTableTypes.put("SECURITYKEY", "securityKey");// 治安重点
		keyTableTypes.put("ENTERPRISEDOWNKEY", "enterpriseDownKey");// 规上企业
	}

	public static String toString(String key) {
		if (key == null)
			return "enterpriseKey";
		else
			return keyTableTypes.get(key);
	}

	public static Map<String, String> backTablesMap() {
		return keyTableTypes;
	}
}
