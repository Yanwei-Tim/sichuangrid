package com.tianque.baseInfo.mPersonnel.service;

import java.util.List;

import com.tianque.baseInfo.base.service.BaseInfoPopulationTemplateService;
import com.tianque.baseInfo.mPersonnel.domain.MPersonnel;
import com.tianque.core.vo.PageInfo;

public interface MPersonnelService extends BaseInfoPopulationTemplateService {

	PageInfo<MPersonnel> findMPersonnelsForPageByOrgId(Long organizationId,
			Integer page, Integer rows, String sidx, String sord,
			Long isEmphasis);

	Boolean existMPersonnel(Long organizationId, String idCardNo, Long id);

	MPersonnel updateMPersonnelBaseInfo(MPersonnel population);

	MPersonnel addMPersonnelBaseInfo(MPersonnel population);

	MPersonnel updateMPersonnelBusiness(MPersonnel population);

	List<Long> deleteMPersonnelByIds(List<Long> asList);

	MPersonnel getSimpleMPersonnelById(Long id);

	MPersonnel getMPersonnelById(Long id);

	List<MPersonnel> updateDeathByIds(List<Long> asList, boolean death);

	MPersonnel updateMPersonnel(MPersonnel mPersonnel);

	MPersonnel addMPersonnel(MPersonnel mPersonnel);

	MPersonnel getMPersonnelByIdCardNo(String idCardNo, Long id);

	MPersonnel updateMPersonnelByName(String idCardNo, Long id,
			MPersonnel domain);

	/** 数据认领判断是否有重复数据 */
	public MPersonnel hasDuplicateMPersonnel(Long orgId, String idCardNo);

	/** 数据认领撤销认领时调用的删除方法 */
	public void deleteMPersonnelByIds(Long[] ids);

}
