package com.tianque.baseInfo.mentalPatient.service;

import java.util.List;

import com.tianque.baseInfo.base.service.BaseInfoPopulationTemplateService;
import com.tianque.baseInfo.mentalPatient.domain.MentalPatient;
import com.tianque.baseInfo.mentalPatient.domain.ServiceSupervisionMeasures;
import com.tianque.core.vo.PageInfo;

public interface MentalPatientService extends BaseInfoPopulationTemplateService {

	public MentalPatient addMentalPatient(MentalPatient mentalPatient);

	public MentalPatient updateMentalPatient(MentalPatient mentalPatient);

	public MentalPatient getMentalPatientById(Long id);

	public void deleteMentalPatientById(Long id);

	public PageInfo<MentalPatient> findMentalPatientsForPageByOrgInternalCode(Long orgId,
			int pageNum, int pageSize, String sortField, String order, Long isEmphasis);

	public boolean hasDuplicateMentalPatient(Long orgId, String idCardNo, Long exceptedId);

	public MentalPatient updateMentalPatientByName(String name, Long id, MentalPatient domain);

	public List<MentalPatient> updateDeathByIds(Long[] populationIds, Boolean death);

	public void deleteMentalPatientByIds(Long[] analyzePopulationIds);

	public MentalPatient updateMentalPatientBaseInfo(MentalPatient population);

	public MentalPatient updateMentalPatientBusinessInfo(MentalPatient population);

	public MentalPatient findMentalPatientByIdCardNoAndOrgId(String idCardNo, Long orgId);

	MentalPatient hasDuplicateMentalPatient(Long orgId, String idCardNo);

	public MentalPatient addMentalPatientBaseInfo(MentalPatient population);

	public void moveTempByIds(String[] moveids, Long targetOrgId);

	public PageInfo findServiceSupervisionMeasuresPatientForList(Integer page,
			Integer rows, String sidx, String sord, ServiceSupervisionMeasures serviceSupervisionMeasures);

	public ServiceSupervisionMeasures getServiceSupervisionMeasuresById( Long id);

	public ServiceSupervisionMeasures updateServiceSupervisionMeasures(
			ServiceSupervisionMeasures serviceSupervisionMeasures);

	public ServiceSupervisionMeasures addServiceSupervisionMeasures(
			ServiceSupervisionMeasures serviceSupervisionMeasures);

	public void deleteServiceSupervisionMeasuresById(Long id);

}