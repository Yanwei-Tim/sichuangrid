package com.tianque.baseInfo.primaryOrg.service;

import java.util.List;

import com.tianque.baseInfo.primaryOrg.domain.PrimaryOrgVo;
import com.tianque.baseInfo.primaryOrg.domain.PrimaryOrganizations;
import com.tianque.core.vo.PageInfo;

public interface PrimaryOrgService {

	/**
	 * 获取综治组织信息页面
	 * 
	 * @param primaryOrg
	 * @param internalId
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return @
	 */
	public PageInfo<PrimaryOrganizations> findPrimaryOrgs(
			PrimaryOrgVo primaryOrg, Integer internalId, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	/** 成员库组织选择时 组织信息查询 */

	public PageInfo<PrimaryOrgVo> findPrimaryOrgsForOrgOption(
			PrimaryOrgVo primaryOrg, Integer internalId, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	/**
	 * 根据ID删除组织
	 * 
	 * @param selectedIds
	 *            准备删除的记录ID
	 * @return int 删除条数
	 */
	public int deletePrimaryOrg(String selectedIds);

	/**
	 * 根据id获取单个综治组织信息
	 * 
	 * @param id
	 * @return PrimaryOrgVo @
	 */
	public PrimaryOrgVo getPrimaryOrgById(Long id);

	/**
	 * 新增综治组织业务
	 * 
	 * @param PrimaryOrgVo
	 * @return PrimaryOrgVo 综治组织vo
	 */
	public PrimaryOrgVo addPrimaryOrg(PrimaryOrganizations primaryOrg);

	/**
	 * 修改综治组织信息
	 * 
	 * @param PrimaryOrg
	 * @return PrimaryOrgVo @
	 */
	public PrimaryOrgVo updatePrimaryOrg(PrimaryOrganizations primaryOrg);

	public Integer getCount(PrimaryOrgVo primaryOrg);

	/**
	 * 根据id修改排序字段
	 * 
	 * @param id
	 * @param seq
	 * */
	public PrimaryOrgVo setPrimaryOrgSeq(Long id, Integer seq);

	/**
	 * 同步群防群治
	 * 
	 * @param selectedIds
	 * 
	 * */
	public int synchronizePrimaryOrgMembersToMasseduty(String selectedIds);

	/**
	 * 通过成员id获取组织组织名称信息
	 * 
	 * @param getPrimaryOrganizationByprimaryMemberId
	 * @return @
	 */
	public List<PrimaryOrganizations> getPrimaryOrganizationByprimaryMemberId(
			Long primaryMembersId);

	/**
	 * 通过组织名称查询组织信息
	 * 
	 * @param getPrimaryOrganizationByprimaryMemberId
	 * @return @
	 */
	public List<PrimaryOrganizations> getPrimaryOrgaInfonByDetailName(
			PrimaryOrgVo primaryOrg);

	/************************** 迁移合并方法 *****************************/
	/**
	 * 
	 * @Title: findAllPrimaryOrgForOrgChange
	 * @Description: TODO根据orgId查询党委部门
	 * @param @param oldOrgId
	 * @param @param newOrgId
	 * @param @return 设定文件
	 * @return List<PrimaryOrganizations> 返回类型
	 * @author wanggz
	 * @date 2014-10-16 下午06:06:17
	 */
	public List<PrimaryOrganizations> findAllPrimaryOrgForOrgChange(
			Long oldOrgId, Long newOrgId, String deptMinValue);

	/**
	 * 
	 * @Title: findPrimaryOrgByOrgIdAndDetailnameForOrgChange
	 * @Description: TODO查询目标部门的重复党委部门数据
	 * @param @param primaryOrgVo
	 * @param @param newOrgId
	 * @param @return 设定文件
	 * @return List<PrimaryOrganizations> 返回类型
	 * @author wanggz
	 * @date 2014-10-17 上午09:23:37
	 */
	public List<PrimaryOrganizations> findPrimaryOrgByOrgIdAndDetailnameForOrgChange(
			PrimaryOrgVo primaryOrgVo, Long newOrgId);

}
