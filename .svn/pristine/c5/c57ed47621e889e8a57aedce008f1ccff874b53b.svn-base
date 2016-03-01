package com.tianque.baseInfo.qPersonnel.service;

import java.util.List;

import com.tianque.baseInfo.base.service.BaseInfoPopulationTemplateService;
import com.tianque.baseInfo.qPersonnel.domain.QPersonnel;
import com.tianque.core.vo.PageInfo;

public interface QPersonnelService extends BaseInfoPopulationTemplateService {

	PageInfo<QPersonnel> findQPersonnelsForPageByOrgId(Long organizationId,
			Integer page, Integer rows, String sidx, String sord,
			Long isEmphasis);

	Boolean existQPersonnel(Long organizationId, String idCardNo, Long id);

	QPersonnel updateQPersonnelBaseInfo(QPersonnel population);

	QPersonnel addQPersonnelBaseInfo(QPersonnel population);

	QPersonnel updateQPersonnelBusiness(QPersonnel population);

	List<Long> deleteQPersonnelByIds(List<Long> asList);

	QPersonnel getSimpleQPersonnelById(Long id);

	QPersonnel getQPersonnelById(Long id);

	List<QPersonnel> updateDeathByIds(List<Long> asList, boolean death);

	QPersonnel updateQPersonnel(QPersonnel qPersonnel);

	QPersonnel addQPersonnel(QPersonnel qPersonnel);

	QPersonnel getQPersonnelByIdCardNo(String idCardNo, Long id);

	QPersonnel updateQPersonnelByName(String idCardNo, Long id,
			QPersonnel domain);

	/** 数据认领判断是否有重复数据 */
	public QPersonnel hasDuplicateQPersonnel(Long orgId, String idCardNo);

	/** 数据认领撤销认领时调用的删除方法 */
	public void deleteQPersonnelByIds(Long[] ids);

}
