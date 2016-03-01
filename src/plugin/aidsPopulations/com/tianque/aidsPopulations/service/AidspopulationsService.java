package com.tianque.aidsPopulations.service;

import java.util.List;

import com.tianque.aidsPopulations.domain.Aidspopulations;
import com.tianque.baseInfo.base.service.BaseInfoPopulationTemplateService;
import com.tianque.core.vo.PageInfo;

/**
 * :业务逻辑层接口
 * 
 * @author liaoshanshan
 * @date 2013-06-09 16:34:04
 */
public interface AidspopulationsService extends
		BaseInfoPopulationTemplateService {
	/**
	 * 添加艾滋病人员
	 * 
	 * @param Aidspopulations
	 *            艾滋病人员对象
	 * @return Aidspopulations 艾滋病人员对象
	 */
	public Aidspopulations addAidspopulations(Aidspopulations aidspopulations);

	/**
	 * 添加艾滋病人员基本信息
	 * 
	 * @param Aidspopulations
	 *            艾滋病人员对象
	 * @return Aidspopulations 艾滋病人员对象
	 */
	public Aidspopulations addAidspopulationBaseInfo(
			Aidspopulations aidspopulations);

	/**
	 * 根据ID获取艾滋病人员
	 * 
	 * @param id
	 *            艾滋病人员ID
	 * @return Aidspopulations 艾滋病人员对象
	 */
	public Aidspopulations getAidspopulationsById(Long id);

	public Aidspopulations getAidspopulationById(Long id);

	public Aidspopulations getAidspopulationsByIdCardNo(String idcardNo,
			Long claimOrgId);

	/**
	 * 修改艾滋病人员信息
	 * 
	 * @param Aidspopulations
	 *            艾滋病人员对象
	 * @return Aidspopulations 艾滋病人员对象
	 */
	public Aidspopulations updateAidspopulations(Aidspopulations aidspopulations);

	/**
	 * 判断艾滋病人员是否存在
	 * 
	 * @param orgId
	 *            组织机构id
	 * @param idCardNo
	 *            身份证号
	 * @param exceptedId
	 *            当前id
	 * @return @
	 */
	public boolean hasDuplicateAidspopulations(Long orgId, String idCardNo,
			Long exceptedId);

	/**
	 * 判断艾滋病人员身份证号是否存在
	 * 
	 * @param orgId
	 *            组织机构id
	 * @param idCardNo
	 *            身份证号
	 * @return Aidspopulations 艾滋病人员对象 @
	 */
	public Aidspopulations hasDuplicateAidspopulations(Long orgId,
			String idCardNo);

	/**
	 * 修改艾滋病人员基本信息
	 * 
	 * @param Aidspopulations
	 *            艾滋病人员对象
	 * @return @
	 */
	public Aidspopulations updateAidspopulationsBaseInfo(
			Aidspopulations Aidspopulations);

	/**
	 * 根据网格、身份证识别艾滋病人员是否存在
	 */
	public Aidspopulations updateAidspopulationsByIdCardNoAndOrgId(
			String idCardNo, Long id, Aidspopulations domain);

	/**
	 * 修改艾滋病人员业务信息
	 * 
	 * @param Aidspopulations
	 *            艾滋病人员对象
	 * @return Aidspopulations 艾滋病人员对象
	 */
	public Aidspopulations updateAidspopulationsBusiness(
			Aidspopulations aidspopulations);

	/**
	 * 根据ID删除艾滋病人员
	 * 
	 * @param id
	 *            艾滋病人员ID
	 */
	public void deleteAidspopulationByIds(Long[] id);

	/**
	 * 根据网格内置编码分页查询艾滋病人员
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<Aidspopulations> findAidspopulationsForPage(Long orgId,
			Integer pageNum, Integer pageSize, String sidx, String sord,
			Long isEmphasis);

	/**
	 * 取消死亡
	 * 
	 * @param populationIds
	 * @param death
	 * @return @
	 */
	public List<Aidspopulations> updateDeathByIds(Long[] populationIds,
			Boolean death);
}
