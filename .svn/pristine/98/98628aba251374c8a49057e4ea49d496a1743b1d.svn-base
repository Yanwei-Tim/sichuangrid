package com.tianque.baseInfo.otherAttentionPersonnel.service;

import java.util.List;

import com.tianque.baseInfo.base.service.BaseInfoPopulationTemplateService;
import com.tianque.baseInfo.otherAttentionPersonnel.domain.OtherAttentionPersonnel;
import com.tianque.core.vo.PageInfo;

public interface OtherAttentionPersonnelService extends
		BaseInfoPopulationTemplateService {

	PageInfo<OtherAttentionPersonnel> findOtherAttentionPersonnelsForPageByOrgId(
			Long organizationId, Integer page, Integer rows, String sidx,
			String sord, Long isEmphasis);

	Boolean existOtherAttentionPersonnel(Long organizationId, String idCardNo,
			Long id);

	OtherAttentionPersonnel updateOtherAttentionPersonnelBaseInfo(
			OtherAttentionPersonnel population);

	OtherAttentionPersonnel addOtherAttentionPersonnelBaseInfo(
			OtherAttentionPersonnel population);

	OtherAttentionPersonnel updateOtherAttentionPersonnelBusiness(
			OtherAttentionPersonnel population);

	List<Long> deleteOtherAttentionPersonnelByIds(List<Long> asList);

	OtherAttentionPersonnel getSimpleOtherAttentionPersonnelById(Long id);

	OtherAttentionPersonnel getOtherAttentionPersonnelById(Long id);

	List<OtherAttentionPersonnel> updateDeathByIds(List<Long> asList,
			boolean death);

	OtherAttentionPersonnel updateOtherAttentionPersonnel(
			OtherAttentionPersonnel otherAttentionPersonnel);

	OtherAttentionPersonnel addOtherAttentionPersonnel(
			OtherAttentionPersonnel otherAttentionPersonnel);

	OtherAttentionPersonnel getOtherAttentionPersonnelByIdCardNo(
			String idCardNo, Long id);

	OtherAttentionPersonnel updateOtherAttentionPersonnelByName(
			String idCardNo, Long id, OtherAttentionPersonnel domain);

	/** 数据认领判断是否有重复数据 */
	public OtherAttentionPersonnel hasDuplicateOtherAttentionPersonnel(
			Long orgId, String idCardNo);

	/** 数据认领撤销认领时调用的删除方法 */
	public void deleteOtherAttentionPersonnelByIds(Long[] ids);

}
