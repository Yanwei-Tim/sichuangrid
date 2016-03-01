package com.tianque.baseInfo.fPersonnel.service;

import java.util.List;

import com.tianque.baseInfo.base.service.BaseInfoPopulationTemplateService;
import com.tianque.baseInfo.fPersonnel.domain.FPersonnel;
import com.tianque.core.vo.PageInfo;

public interface FPersonnelService extends BaseInfoPopulationTemplateService {

	PageInfo<FPersonnel> findFPersonnelsForPageByOrgId(Long organizationId,
			Integer page, Integer rows, String sidx, String sord,
			Long isEmphasis);

	Boolean existFPersonnel(Long organizationId, String idCardNo, Long id);

	FPersonnel updateFPersonnelBaseInfo(FPersonnel population);

	FPersonnel addFPersonnelBaseInfo(FPersonnel population);

	FPersonnel updateFPersonnelBusiness(FPersonnel population);

	List<Long> deleteFPersonnelByIds(List<Long> asList);

	FPersonnel getSimpleFPersonnelById(Long id);

	FPersonnel getFPersonnelById(Long id);

	List<FPersonnel> updateDeathByIds(List<Long> asList, boolean death);

	FPersonnel updateFPersonnel(FPersonnel fPersonnel);

	FPersonnel addFPersonnel(FPersonnel fPersonnel);

	FPersonnel getFPersonnelByIdCardNo(String idCardNo, Long id);

	FPersonnel updateFPersonnelByName(String idCardNo, Long id,
			FPersonnel domain);

	/** 数据认领判断是否有重复数据 */
	public FPersonnel hasDuplicateFPersonnel(Long orgId, String idCardNo);

	/** 数据认领撤销认领时调用的删除方法 */
	public void deleteFPersonnelByIds(Long[] ids);

}
