package com.tianque.baseInfo.companyPlace.dao;

import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.companyPlace.domain.ContaminationInfo;

@DynamicIbatisDAO(value = "ContaminationInfoDAO", sqlMapClientTemplate = "sqlMapClientTemplate")
@Repository("ContaminationInfoDAO")
public interface ContaminationInfoDAO {
	public Long addContaminationInfo(ContaminationInfo ContaminationInfo);

	public int deleteContaminationInfo(Long baseId);

	public int deleteContaminationInfoByBusinessId(Long businessId);

	public void updateContaminationInfo(ContaminationInfo ContaminationInfo);

	public ContaminationInfo getContaminationInfoById(Long id);

	public ContaminationInfo getContaminationInfoByBusinessId(Long businessId);
}
