package com.tianque.baseInfo.companyPlace.constant;

import com.tianque.baseInfo.companyPlace.constacts.ModulTypes;

public class KeyPlaceType {
	public static String mapTypes;
	public static String businessTypes;
	static {
		StringBuffer strTypesBuffer = new StringBuffer();
		for (String value : ModulTypes.mapKey.values()) {
			strTypesBuffer.append("'" + value + "',");
		}
		mapTypes = new String(strTypesBuffer.substring(0,
				strTypesBuffer.length() - 1));

		businessTypes = ModulTypes.businessTypes;
	}
}
