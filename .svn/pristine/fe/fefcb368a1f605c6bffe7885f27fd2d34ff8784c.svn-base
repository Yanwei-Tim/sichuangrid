package com.tianque.baseInfo.primaryOrg.dao;

import java.util.List;

import com.tianque.baseInfo.primaryOrg.domain.PrimaryOrgVo;
import com.tianque.baseInfo.primaryOrg.domain.PrimaryOrganizations;
import com.tianque.core.vo.PageInfo;

/**
 * 综合组织信息数据访问接口
 */
public interface PrimaryOrganizationsDao {

	/**
	 * 分页查询组织信息
	 * 
	 * @param primaryOrgVo
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<PrimaryOrganizations> findPrimaryOrgs(
			PrimaryOrgVo primaryOrgVo, Integer pageNum, Integer pageSize);

	/**
	 * 搜索组织信息
	 * 
	 * @param primaryOrgVo
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<PrimaryOrganizations> findPrimaryOrgsBySearch(
			PrimaryOrgVo primaryOrgVo, Integer pageNum, Integer pageSize);

	/** 成员库组织选择时 组织信息查询 */
	public PageInfo<PrimaryOrgVo> findPrimaryOrgsForOrgOption(
			PrimaryOrgVo primaryOrgVo, Integer pageNum, Integer pageSize);

	/**
	 * 按查询组织数目
	 */
	public int countPrimaryOrgsWhenAdd(PrimaryOrganizations primaryOrg);

	/**
	 * 根据id获取综合组织
	 * 
	 * @param id
	 * @return PrimaryOrgVo 综合组织vo
	 */

	public PrimaryOrgVo getPrimaryOrgById(Long id);

	/**
	 * 删除综合组织
	 * 
	 * @param id
	 * @return int 删除条数
	 */
	public int deletePrimaryOrg(Long id);

	/**
	 * 新增综合组织
	 * 
	 * @param PrimaryOrg
	 * @return PrimaryOrgVo 服务团队vo
	 */
	public PrimaryOrgVo addPrimaryOrg(PrimaryOrganizations primaryOrg);

	/**
	 * 更新综合组织
	 * 
	 * @param primaryOrg
	 * @return PrimaryOrgVo
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
	public int synchronizePrimaryOrgMembersToMasseduty(Long selectedId);

	/**
	 * 通过成员id获取组织组织名称
	 * 
	 * @param primaryMembersId
	 * @return
	 */
	public List<PrimaryOrganizations> getPrimaryOrganizationByprimaryMemberId(
			Long primaryMembersId);

	/**
	 * 根据组织名称模糊搜索组织 返回前段组件属性 【label为名称，value为组织id】
	 * 
	 * @param primaryOrgVo
	 * @return
	 */
	public List<PrimaryOrganizations> getPrimaryOrgaInfonByDetailName(
			PrimaryOrgVo primaryOrgVo);

	public List<PrimaryOrganizations> getFourTeamsPrimaryOrgaInfonByDetailName(
			PrimaryOrgVo primaryOrgVo);

	/******************** 迁移合并方法 ********************/
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
	 * @Description: TODO查询出目标部门的重复数据对象
	 * @param @param primaryOrgVo
	 * @param @return 设定文件
	 * @return List<PrimaryOrganizations> 返回类型
	 * @author wanggz
	 * @date 2014-10-17 上午08:45:38
	 */
	public List<PrimaryOrganizations> findPrimaryOrgByOrgIdAndDetailnameForOrgChange(
			PrimaryOrgVo primaryOrgVo, Long newOrgId);

}
