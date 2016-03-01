package com.tianque.baseInfo.base.helper;

import java.util.ArrayList;
import java.util.List;

import com.tianque.util.IdCardUtil;

public class CardNoHelper {

	public static List<String> createIdCardNo(String idCardNo) {
		List<String> list = new ArrayList<String>();
		list.add(idCardNo);
		if (idCardNo.length() == 18) {
			list.add(IdCardUtil.idCardNo18to15(idCardNo));
		} else if (idCardNo.length() == 15) {
			list.add(IdCardUtil.idCardNo15to18(idCardNo, "19"));
			list.add(IdCardUtil.idCardNo15to18(idCardNo, "20"));
		}
		return list;
	}

}
