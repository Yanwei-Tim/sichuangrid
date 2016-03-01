package com.tianque.baseInfo.druggy.service;

import java.util.List;

import com.tianque.baseInfo.base.service.BaseInfoPopulationTemplateService;
import com.tianque.baseInfo.druggy.domain.Druggy;
import com.tianque.core.vo.PageInfo;

/**
 * 吸毒人员数据库操作接口。
 */
public interface DruggyService extends BaseInfoPopulationTemplateService {

	/**
	 * 添加吸毒人员
	 * 
	 * @param druggy
	 *            吸毒人员对象
	 * @return druggy 吸毒人员对象
	 */
	public Druggy addDruggy(Druggy druggy);

	/**
	 * 添加吸毒人员
	 * 
	 * @param druggy
	 *            吸毒人员对象
	 * @return druggy 吸毒人员对象
	 */
	public Druggy addDruggyForMobile(Druggy druggy);

	/**
	 * 添加吸毒人员基本信息
	 * 
	 * @param druggy
	 *            吸毒人员对象
	 * @return druggy 吸毒人员对象
	 */
	public Druggy addDruggyBaseInfo(Druggy druggy);

	/**
	 * 根据ID获取吸毒人员
	 * 
	 * @param id
	 *            吸毒人员ID
	 * @return druggy 吸毒人员对象
	 */
	public Druggy getDruggyById(Long id);

	/**
	 * 修改吸毒人员信息
	 * 
	 * @param druggy
	 *            吸毒人员对象
	 * @return druggy 吸毒人员对象
	 */
	public Druggy updateDruggy(Druggy druggy);

	/**
	 * 根据ID删除吸毒人员
	 * 
	 * @param id
	 *            吸毒人员ID
	 */
	public void deleteDruggyByIds(Long[] id);

	/**
	 * 根据网格内置编码分页查询吸毒人员
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<Druggy> findDruggyForPageByOrgInternalCode(Long orgId,
			Integer pageNum, Integer pageSize, String sidx, String sord,
			Long isEmphasis);

	/**
	 * 根据网格、身份证识别吸毒人员是否存在
	 */
	public Druggy updateDruggyByIdCardNoAndOrgId(String idCardNo, Long id,
			Druggy domain);

	public boolean hasDuplicateDruggy(Long orgId, String idCardNo,
			Long exceptedId);

	public List<Druggy> updateDeathByIds(Long[] populationIds, Boolean death);

	public Druggy updateDruggyBaseInfo(Druggy druggy);

	public Druggy updateDruggyBusiness(Druggy domain);

	public Druggy getDruggyByIdCardNo(String idcardNo, Long claimOrgId);

	Druggy hasDuplicateDruggy(Long orgId, String idCardNo);

	public void moveTempByIds(String[] moveids, Long targetOrgId);

}
