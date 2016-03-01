package com.tianque.baseInfo.rectificativePerson.service;

import java.util.List;

import com.tianque.baseInfo.base.service.BaseInfoPopulationTemplateService;
import com.tianque.baseInfo.rectificativePerson.domain.RectificativePerson;
import com.tianque.core.vo.PageInfo;

public interface RectificativePersonService extends
		BaseInfoPopulationTemplateService {

	public RectificativePerson addRectificativePerson(
			RectificativePerson rectificativePerson);

	public RectificativePerson updateRectificativePerson(
			RectificativePerson rectificativePerson);

	public RectificativePerson getRectificativePersonById(Long id);

	public PageInfo<RectificativePerson> findRectificativePersonsForPageByOrgInternalCode(
			Long orgId, int pageNum, int pageSize, String sortField,
			String order, Long isEmphasis);

	public boolean hasDuplicateRectificativePerson(Long orgId, String idCardNo,
			Long exceptedId);

	public RectificativePerson getRectificativePersonByIdCardNo(
			String idCardNo, Long orgId);

	public RectificativePerson updateRectificativePersonByName(String name,
			Long id, RectificativePerson domain);

	public RectificativePerson updateRectificativePersonBusiness(
			RectificativePerson population);

	public RectificativePerson updateRectificativePersonBaseInfo(
			RectificativePerson population);

	public List<RectificativePerson> updateDeathByIds(Long[] populationIds,
			Boolean death);

	public void deleteRectificativePersonByIds(Long[] populationIds);

	public void updateEmphasiseById(Long id, Long isEmphasis);

	public RectificativePerson findRectificativePersonByIdCardNoAndOrgId(
			String idCardNo, Long orgId);

	public List<RectificativePerson> findRectificativePersonByRectifyDate();

	RectificativePerson hasDuplicateRectificativePerson(Long orgId,
			String idCardNo);

	public RectificativePerson addRectificativePersonBaseInfo(
			RectificativePerson population);

	/**
	 * 矫正人员 转移功能接口
	 * 
	 * @param moveids
	 * @param targetOrgId
	 */
	public void moveTempByIds(String[] moveids, Long targetOrgId);

	public RectificativePerson findRectificativePersonByIdCardNoAndOrgIdAndEmphasis(
			String idCardNo, Long orgId);

	public boolean hasDuplicateRectificativePersonForPositiveInfo(Long orgId,
			String idCardNo, Long exceptedId);

	public List<RectificativePerson> remindByRectifyDate();

	public RectificativePerson updateIsRemindByid(Long id);

}
