package com.tianque.service.impl;

import com.tianque.domain.School;
import com.tianque.exception.base.BusinessValidationException;

public class SchoolServiceHelper {

	public static void checkMentalPatient(School school) {
		if (school == null || school.getChineseName() == null
				|| "".equals(school.getChineseName().trim())) {
			throw new BusinessValidationException("请输入中文名称！");
		}
		if (school.getOrganization() == null
				|| school.getOrganization().getId() == null) {
			throw new BusinessValidationException("请输入所属网格！");
		}
		if (school.getKind() == null || school.getKind().getId() == null) {
			throw new BusinessValidationException("请输入学校性质！");
		}
		if (school.getType() == null || school.getType().getId() == null) {
			throw new BusinessValidationException("请输入学校类型！");
		}
		if (school.getAddress() == null
				|| "".equals(school.getAddress().trim())) {
			throw new BusinessValidationException("请输入学校地址！");
		}
		if (school.getPresident() == null
				|| "".equals(school.getPresident().trim())) {
			throw new BusinessValidationException("请输入学长名称!");
		}
	}
}