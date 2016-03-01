package com.tianque.baseInfo.primaryOrg.primaryMembers.dao;

import java.util.List;

import com.tianque.baseInfo.primaryOrg.primaryMembers.domain.PrimaryMemberVo;
import com.tianque.baseInfo.primaryOrg.primaryMembers.domain.PrimaryMembers;
import com.tianque.baseInfo.primaryOrg.primaryMembers.domain.PrimaryMembersOrgType;
import com.tianque.core.vo.PageInfo;

/**
 * 成员数据访问层接口
 * 
 */
public interface PrimaryMembersDao {
	/**
	 * 新增成员信息
	 * 
	 */
	public PrimaryMembers addPrimaryMembers(PrimaryMembers primaryMembers);

	/**
	 * 分页查询组织成员
	 * 
	 * @param primaryOrgMemberVo
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<PrimaryMembers> findPrimaryMembers(
			PrimaryMemberVo primaryMember, Integer pageNum, Integer pageSize);

	/**
	 * 根据id获取组织成员
	 * 
	 * @param id
	 * @return PrimaryMemberVo 组织成员vo
	 */

	public PrimaryMemberVo getPrimaryMemberById(Long id);

	/**
	 * 根据ID单个查询
	 * 
	 * @param id
	 * @return
	 */
	public PrimaryMembers getPrimaryMembersById(Long id);

	/**
	 * 根据姓名或者电话进行查询相同的信息
	 * 
	 * @param primaryMembers
	 * @param page
	 * @param rows
	 * @return @
	 */
	public PageInfo<PrimaryMembers> findPrimaryMembersByNameOrMobile(
			PrimaryMembers primaryMembers, Integer page, Integer rows);

	/**
	 * 获取成员ID
	 */
	public List<PrimaryMembersOrgType> findPrimarymembersorgtypeByorgId(
			List orgIds);

	/**
	 * 根据选中的成员列表查询对应的集合
	 * 
	 * @return
	 */
	public List<PrimaryMembers> findPrimaryMembersByIds(List<Long> ids);

	public PageInfo<PrimaryMembers> findPrimaryMembers(Long orgId,
			Long isHaveJob, Integer page, Integer rows, String sidx, String sord);

	public void deletePrimaryMembersById(Long id);

	public void deletePrimaryMembersByIds(List<Long> ids);

	public PrimaryMembers updatePrimaryMembers(PrimaryMembers primaryMembers);

	public PageInfo<PrimaryMembers> searchPrimaryMembersByName(Long orgId,
			Long isHaveJob, String name, Integer page, Integer rows,
			String sidx, String sord);

	/**
	 * 组织维护成员列表显示
	 * 
	 * @param primaryMemberVo
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<PrimaryMembers> findPrimaryMembersByOrg(
			PrimaryMemberVo primaryMemberVo, Integer pageNum, Integer pageSize);

	/**
	 * 设置排序
	 * 
	 * @param id
	 * @param seq
	 */
	public void setPrimaryOrgSeq(Long id, Integer seq);
}
