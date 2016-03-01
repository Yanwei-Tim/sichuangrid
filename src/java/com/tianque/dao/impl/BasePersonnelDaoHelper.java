package com.tianque.dao.impl;

import com.tianque.core.util.StringUtil;
import com.tianque.exception.base.BusinessValidationException;

public class BasePersonnelDaoHelper {
	public static void checkUpdateNameAndGenderByIdCardNo(String idCardNo,
			String name, Long gender) {
		if (!StringUtil.isStringAvaliable(idCardNo)
				|| !StringUtil.isStringAvaliable(name) || gender == null) {
			throw new BusinessValidationException("参数错误");
		}
	}
}
