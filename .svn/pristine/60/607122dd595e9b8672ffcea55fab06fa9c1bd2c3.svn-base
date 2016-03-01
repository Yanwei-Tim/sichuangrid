package com.tianque.baseInfo.aidNeedPopulation.service;

import com.tianque.baseInfo.aidNeedPopulation.domain.AidNeedPopulation;
import com.tianque.baseInfo.base.service.BaseInfoPopulationTemplateService;
import com.tianque.core.vo.PageInfo;

public interface AidNeedPopulationService extends
		BaseInfoPopulationTemplateService {

	/**
	 * 添加 一条 需救助人员信息
	 * 
	 * @param aidNeedPopulation
	 * @return
	 */
	public AidNeedPopulation addAidNeedPopulation(
			AidNeedPopulation aidNeedPopulation);

	/**
	 * 修改 一条 需救助人员信息
	 * 
	 * @param aidNeedPopulation
	 * @return
	 */
	public AidNeedPopulation updateAidNeedPopulation(
			AidNeedPopulation aidNeedPopulation);

	/**
	 * 分页 查询 闲散青少年
	 * 
	 * @param query
	 * @param aidNeedPopulation
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<AidNeedPopulation> findAidNeedPopulationServiceForPageByOrganizationId(
			Long organizationId, Integer pageNum, Integer pageSize,
			String sidx, String sord, Long isEmphasis);

	/**
	 * 得到 一条 需救助人员信息
	 * 
	 * @param id
	 * @return
	 */
	public AidNeedPopulation getAidNeedPopulationById(Long id);

	/**
	 * 删除 一条需救助人员信息
	 * 
	 * @param id
	 */
	public boolean deleteAidNeedPopulation(Long id);

	public AidNeedPopulation getAidNeedPopulationByIdCardNoAndOrganizationId(
			String idCardNo, Long organizationId);

	public AidNeedPopulation updateAidNeedPopulationByIdCardNoAndOrgId(
			String name, Long id, AidNeedPopulation domain);

	public boolean hasDuplicateAidNeedPopulation(Long orgId, String idCardNo,
			Long exceptedId);

	/**
	 * 更新需救助人员的业务信息
	 * 
	 * @param population
	 * @return
	 */
	public AidNeedPopulation updateAidNeedPopulationBusiness(
			AidNeedPopulation population);

	/**
	 * 更新需救助人员的基础信息
	 * 
	 * @param population
	 * @return
	 */

	public AidNeedPopulation updateAidNeedPopulationBaseInfo(
			AidNeedPopulation population);

	public void deleteAidNeedPopulationByIds(Long[] analyzePopulationIds);

	public void updateDeathByIds(Long[] analyzePopulationIds, boolean death);

	AidNeedPopulation hasDuplicateAidNeedPopulation(Long orgId, String idCardNo);

	/**
	 * 获取需救助人员信息
	 * 
	 * @param idcardNo
	 * @param claimOrgId
	 * @return
	 */
	public AidNeedPopulation getAidNeedPopulationByIdCardNo(String idcardNo,
			Long claimOrgId);

	public AidNeedPopulation addAidNeedPopulationBaseInfo(
			AidNeedPopulation population);

	public void moveTempByIds(String[] moveids, Long targetOrgId);
}
