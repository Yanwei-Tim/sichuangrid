package com.tianque.baseInfo.primaryOrg.primaryMembers.dao;

import java.util.List;

import com.tianque.baseInfo.primaryOrg.primaryMembers.domain.PrimaryMembersOrgType;

public interface PrimaryMembersOrgTypeDao {

	public Long addPrimaryMembersOrgType(
			PrimaryMembersOrgType primaryMembersOrgType);

	public List<PrimaryMembersOrgType> findPrimaryMembersOrgTypeByMember(
			Long memberId);

	public PrimaryMembersOrgType findPrimaryMembersOrgTypeByMemberAndPrimaryOrg(
			Long memberId, Long primaryOrgId);

	public void deltePrimaryMembersOrgType(Long primaryMembersId,
			Long primaryOrgId);

	public void deletePrimaryMembersOrgType(
			PrimaryMembersOrgType primaryMembersOrgType);

	public void updatePrimaryMembersOrgType(
			PrimaryMembersOrgType primaryMembersOrgType);

	public void updatePrimaryMembers(PrimaryMembersOrgType primaryMembersOrgType);

	public void deltePrimaryMembersOrgTypeByPrimaryMembersId(
			Long primaryMembersId);

	public void deltePrimaryMembersOrgTypeByPrimaryMembersIds(List<Long> ids);

	public List<PrimaryMembersOrgType> getprimaryOrgName(Long primaryMembersId);

	/**
	 * 查询组织成员数
	 * 
	 * @param primaryOrgMemberVo
	 * @return
	 */
	public int countFindPrimaryOrgMembers(
			PrimaryMembersOrgType primaryMembersOrgType);

	/************************ 迁移合并方法 ***************************/
	/**
	 * 
	 * @Title: updatePrimarymembersorgtypeOrgIdForOrgChange
	 * @Description: TODO修改成员关联表信息 党委组织
	 * @param @param newId
	 * @param @param oldId 设定文件
	 * @return void 返回类型
	 * @author wanggz
	 * @date 2014-10-17 上午10:03:40
	 */
	public void updatePrimarymembersorgtypeOrgIdForOrgChange(Long newId,
			Long oldId);

}
