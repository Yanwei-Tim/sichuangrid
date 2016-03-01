package com.tianque.baseInfo.superiorVisit.service;

import java.util.List;

import com.tianque.baseInfo.base.service.BaseInfoPopulationTemplateService;
import com.tianque.baseInfo.superiorVisit.domain.SuperiorVisit;
import com.tianque.core.vo.PageInfo;

public interface SuperiorVisitService extends BaseInfoPopulationTemplateService {

	/**
	 * 添加重点上访人员
	 * 
	 * @param superiorVisit
	 *            重点上访人员对象
	 * @return superiorVisit 重点上访人员对象
	 */
	public SuperiorVisit addSuperiorVisit(SuperiorVisit superiorVisit,
			List<Long> visitTypes);

	public SuperiorVisit addSuperiorVisit(SuperiorVisit superiorVisit);

	/**
	 * 
	 * @Title: addSuperiorVisitForMobile
	 * @Description: TODO提取新方法，添加重点上访人员
	 * @param @param superiorVisit
	 * @param @param visitTypes
	 * @param @return
	 * @param @ 设定文件
	 * @return SuperiorVisit 返回类型
	 * @author wanggz
	 * @date 2014-6-19 下午02:49:00
	 */
	public SuperiorVisit addSuperiorVisitForMobile(SuperiorVisit superiorVisit,
			List<Long> visitTypes);

	/**
	 * 根据ID获取重点上访人员
	 * 
	 * @param id
	 *            重点上访人员ID
	 * @return superiorVisit 重点上访人员对象
	 */
	public SuperiorVisit getSuperiorVisitById(Long id);

	/**
	 * 修改重点上访人员信息
	 * 
	 * @param superiorVisit
	 *            重点上访人员对象
	 * @return superiorVisit 重点上访人员对象
	 */
	public SuperiorVisit updateSuperiorVisit(SuperiorVisit superiorVisit,
			List<Long> visitTypes);

	public SuperiorVisit updateSuperiorVisit(SuperiorVisit superiorVisit);

	/**
	 * 根据ID删除重点上访人员
	 * 
	 * @param id
	 *            重点上访人员ID
	 */
	public void deleteSuperiorVisitByIds(Long[] id);

	/**
	 * 根据网格内置编码分页查询重点上访人员
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<SuperiorVisit> findSuperiorVisitForPageByOrgInternalCode(
			Long orgId, Integer pageNum, Integer pageSize, String sidx,
			String sord, Long isEmphasis);

	public boolean hasDuplicateSuperiorVisit(Long orgId, String idCardNo,
			Long exceptedId);

	public List<SuperiorVisit> updateDeathByIds(Long[] populationIds,
			Boolean death);

	public SuperiorVisit updateSuperiorVisitBaseInfo(SuperiorVisit superiorVisit);

	public SuperiorVisit updateSuperiorVisitBusiness(SuperiorVisit domain,
			List<Long> visitTypes);

	public SuperiorVisit updateSuperiorVisitBusiness(SuperiorVisit domain);

	public SuperiorVisit getSuperiorVistByIdCardNoAndOrgId(String idCardNo,
			Long orgId);

	SuperiorVisit hasDuplicateSuperiorVisit(Long orgId, String idCardNo);

	public SuperiorVisit addSuperiorVisitBaseInfo(SuperiorVisit population);

	public void moveTempByIds(String[] moveids, Long targetOrgId);
}
