package com.tianque.baseInfo.primaryOrg.primaryMembers.service;

import java.util.List;

import com.tianque.baseInfo.primaryOrg.primaryMembers.domain.PrimaryMembers;
import com.tianque.baseInfo.primaryOrg.primaryMembers.domain.PrimaryMembersOrgType;

public interface PrimaryMembersOrgTypeService {

	public PrimaryMembersOrgType findPrimaryMembersOrgTypeByMemberAndPrimaryOrg(
			Long memberId, Long primaryOrgId);

	public List<PrimaryMembersOrgType> findPrimaryMembersOrgTypeByMember(
			Long memberId);

	public Long addPrimaryMembersOrgType(
			PrimaryMembersOrgType primaryMembersOrgType);

	public void deltePrimaryMembersOrgType(Long primaryMembersId,
			Long primaryOrgId);

	public void updatePrimaryMembersOrgType(
			PrimaryMembersOrgType primaryMembersOrgType);

	public void updatePrimaryMembers(PrimaryMembersOrgType primaryMembersOrgType);

	public void deltePrimaryMembersOrgTypeByPrimaryMembersId(
			long primaryMembersId);

	public void deltePrimaryMembersOrgTypeByPrimaryMembersIds(List<Long> ids);

	public void deletePrimaryMembersOrgType(
			PrimaryMembersOrgType primaryMembersOrgType);

	public PrimaryMembers getprimaryOrgName(PrimaryMembers primaryMembers);

	/**
	 * 查询组织成员数
	 * 
	 * @param primaryOrgMemberVo
	 * @return
	 */
	public int countFindPrimaryOrgMembers(
			PrimaryMembersOrgType primaryMembersOrgType);

	/**
	 * 判断成员是否是四级平台
	 * 
	 * @param memberId
	 * @return
	 */
	public boolean isFourLevelPlat(Long memberId);

	/**************** 迁移合并方法 ********************/
	/**
	 * 
	 * @Title: updatePrimarymembersorgtypeOrgIdForOrgChange
	 * @Description: TODO党委组织修改成员关联关系表
	 * @param @param newId
	 * @param @param oldId 设定文件
	 * @return void 返回类型
	 * @author wanggz
	 * @date 2014-10-17 上午10:09:34
	 */
	public void updatePrimarymembersorgtypeOrgIdForOrgChange(Long newId,
			Long oldId);
}
