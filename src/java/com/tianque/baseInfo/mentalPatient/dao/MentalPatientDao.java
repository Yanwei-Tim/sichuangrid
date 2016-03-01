package com.tianque.baseInfo.mentalPatient.dao;

import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDao;
import com.tianque.baseInfo.mentalPatient.domain.MentalPatient;
import com.tianque.baseInfo.mentalPatient.domain.ServiceSupervisionMeasures;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchMentalPatientVo;

public interface MentalPatientDao extends
		BaseInfoPopulationBaseDao<MentalPatient, SearchMentalPatientVo> {

	public PageInfo<MentalPatient> findMentalPatientsForPageByOrgInternalCode(
			String orgInternalCode, int pageNum, int pageSize, String sortField, String order,
			Long isEmphasis);

	public void updateEmphasiseById(Long long1, Long isEmphasis);

	public void updateDeathAndLogoutStateById(Long id, Boolean death, Long logoutState);

	public PageInfo findServiceSupervisionMeasuresPatientForList(
			Integer pageNum, Integer pageSize, String sortField, String order,
			ServiceSupervisionMeasures serviceSupervisionMeasures);

	public ServiceSupervisionMeasures getServiceSupervisionMeasuresById( Long id);

	public ServiceSupervisionMeasures addServiceSupervisionMeasures(
			ServiceSupervisionMeasures serviceSupervisionMeasures);

	public ServiceSupervisionMeasures updateServiceSupervisionMeasures(
			ServiceSupervisionMeasures serviceSupervisionMeasures);

	public void deleteServiceSupervisionMeasuresById(Long id );

	public void deleteServiceSupervisionMeasuresByIdAndType(Long id,
			String upperCase);
	
	public void updateTableUpdateDateById(Long id);
}