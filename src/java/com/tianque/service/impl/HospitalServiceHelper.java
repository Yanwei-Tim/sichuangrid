package com.tianque.service.impl;

import com.tianque.domain.Hospital;
import com.tianque.exception.base.BusinessValidationException;

public class HospitalServiceHelper {

	public static void checkMentalPatient(Hospital hospital) {
		if (hospital == null || hospital.getHospitalName() == null
				|| "".equals(hospital.getHospitalName().trim())) {
			throw new BusinessValidationException("请医院名称！");
		}
		if (hospital.getOrganization() == null
				|| hospital.getOrganization().getId() == null) {
			throw new BusinessValidationException("请输入所属网格！");
		}
		if (hospital.getAddress() == null
				|| "".equals(hospital.getAddress().trim())) {
			throw new BusinessValidationException("请输入医院地址！");
		}
	}
}