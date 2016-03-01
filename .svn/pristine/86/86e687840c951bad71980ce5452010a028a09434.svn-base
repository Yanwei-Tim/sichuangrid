package com.tianque.baseInfo.positiveInfo.service;

import java.util.List;

import com.tianque.baseInfo.base.service.BaseInfoPopulationTemplateService;
import com.tianque.baseInfo.positiveInfo.domain.PositiveInfo;
import com.tianque.core.vo.PageInfo;

public interface PositiveInfoService extends BaseInfoPopulationTemplateService {
	/**
	 * 添加规正人员信息
	 */
	public PositiveInfo addPositiveInfo(PositiveInfo positiveInfos);

	/**
	 * 查看规正人员信息
	 * 
	 * @param positiveInfos
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<PositiveInfo> findPositiveInfoForPage(
			PositiveInfo positiveInfos, int pageNum, int pageSize,
			String sortField, String order, Long isEmphasis);

	/**
	 * 查看某一规正人员的详细信息
	 */
	public PositiveInfo getPositiveInfoById(Long id);

	/**
	 * 修改一条规正人员信息
	 * 
	 * @param positiveInfos
	 * @return
	 */
	public PositiveInfo updatePositiveInfoBaseInfo(PositiveInfo positiveInfos);

	/**
	 * 判断用户是否存在
	 * 
	 * @return
	 */
	public boolean hasDuplicatePosiviteInfos(Long orgId, String IdCardNo,
			Long exceptedId);

	public PositiveInfo updatePositiveInfoByName(String name, Long id,
			PositiveInfo domain);

	public void deletePositiveInfoByIds(Long[] personIds);

	public PositiveInfo updatePositiveInfoBusiness(PositiveInfo population);

	/**
	 * 根据id修改注销状态
	 * 
	 * @param analyzePopulationIds
	 * @param isEmphasis
	 */
	public List<PositiveInfo> updateEmphasiseByIds(Long[] analyzePopulationIds,
			Long isEmphasis);

	public List<PositiveInfo> changeDeathByIds(Long[] populationIds,
			Boolean death);

	public PositiveInfo findPositiveInfoByIdCardNoAndOrgId(String idCardNo,
			Long orgId);

	public List<PositiveInfo> findPositiveInfoByDate(String positiveType,
			long time);

	public PositiveInfo getPositiveInfoByIdCardNo(String idCardNo, Long orgId);

	/**
	 * 修改刑释解教人员
	 * 
	 * @param positiveInfo
	 * @return 刑释解教人员对象
	 */
	public PositiveInfo updatePositiveInfo(PositiveInfo positiveInfo);

	/**
	 * 根据网格和身份证号查找数据
	 * 
	 * @param orgId
	 * @param idCardNo
	 * @return PositiveInfo
	 */
	public PositiveInfo hasDuplicatePositiveInfo(Long orgId, String idCardNo);

	/**
	 * 新增刑释解教人员
	 * 
	 * @param orgId
	 * @param idCardNo
	 * @return PositiveInfo
	 */
	public PositiveInfo addPositiveInfoBaseInfo(PositiveInfo population);

	public PositiveInfo fromRectificativePerson(Long id);

	/**
	 * 释解教人员 移动数据操作
	 * 
	 * @param moveids
	 * @param targetOrgId
	 */
	public void moveTempByIds(String[] moveids, Long targetOrgId);

	/**
	 * 更具orgcode 查询人员
	 * 
	 * @param organizationId
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @param isEmphasis
	 * @return
	 */
	public PageInfo<PositiveInfo> findPositiveInfosForPageByOrgInternalCode(
			Long organizationId, Integer page, Integer rows, String sidx,
			String sord, Long isEmphasis);

	public PositiveInfo findPositiveInfoByIdCardNoAndOrgIdAndEmphasis(
			String idCardNo, Long orgId);

	public PositiveInfo rectificativeToPositiveInfo(PositiveInfo population);

	public boolean hasDuplicatePosiviteInfosForRectificative(Long orgId,
			String idCardNo);

	public boolean hasDuplicatePosiviteInfosForRectificative(Long orgId,
			String idCardNo, Long exceptedId);

}
