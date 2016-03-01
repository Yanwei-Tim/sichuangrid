package com.tianque.baseInfo.primaryOrg.primaryMembers.service;

import com.tianque.baseInfo.primaryOrg.primaryMembers.domain.PrimaryMemberVo;
import com.tianque.baseInfo.primaryOrg.primaryMembers.domain.PrimaryMembers;
import com.tianque.baseInfo.primaryOrg.primaryMembers.domain.PrimaryOrgOption;
import com.tianque.core.vo.PageInfo;

/**
 * 成员业务处理接口
 * 
 */
public interface PrimaryMembersService {
	/**
	 * 新增成员业务
	 * 
	 */
	public PrimaryMembers addPrimaryMembers(PrimaryMembers primaryMembers,
			String optionOrgIds);

	/**
	 * 获取组织成员信息页面
	 * 
	 * @param primaryOrgMember
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return @
	 */
	public PageInfo<PrimaryMembers> findPrimaryMembers(
			PrimaryMemberVo primaryMember, Integer pageNum, Integer pageSize,
			String sidx, String sord);

	/**
	 * 根据ID单个查询
	 * 
	 */
	public PrimaryMembers getPrimaryMembersById(Long id);

	/**
	 * 根据姓名或者电话进行查询相同的信息
	 * 
	 * @param primaryMembers
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return @
	 */
	public PageInfo<PrimaryMembers> findPrimaryMembersByNameOrMobile(
			PrimaryMembers primaryMembers, Integer page, Integer rows,
			String sidx, String sord);

	/**
	 * 成员合并
	 * 
	 * @param primaryMembers
	 * @param selectedIds
	 */
	public PrimaryMembers combinePrimaryMembers(PrimaryMembers primaryMembers,
			String selectedIds);

	/**
	 * 是否任职
	 * 
	 * @param isHaveJob
	 * @param selectedIds
	 */
	public PrimaryMembers havajobPrimaryMember(Long isHaveJob, Long id);

	public PageInfo<PrimaryOrgOption> findPrimaryOrganizations(Long orgId,
			String displayLevel, String orgTeamClazz, int internalId, int page,
			int rows, String sidx, String sord,String teamTypeDomainName);

	public void deletePrimaryMembersById(Long[] id);

	public PageInfo<PrimaryMembers> findPrimaryMembers(Long orgId,
			Long isHaveJob, Integer page, Integer rows, String sidx, String sord);

	public PrimaryMembers updatePrimaryMembers(PrimaryMembers primaryMembers,
			String optionOrgIds);

	/**
	 * 组织维护成员显示成员
	 * 
	 * @param primaryMemberVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo findPrimaryMembersByOrg(PrimaryMemberVo primaryMemberVo,
			Integer page, Integer rows, String sidx, String sord);

	/**
	 * 修改排序字字段
	 * 
	 * @param id
	 */
	public void setMemberSeq(Long id, Integer seq);

}
