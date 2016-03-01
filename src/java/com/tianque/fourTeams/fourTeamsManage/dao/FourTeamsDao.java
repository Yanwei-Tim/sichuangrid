package com.tianque.fourTeams.fourTeamsManage.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.fourTeams.fourTeamsManage.domain.FourTeamMembers;
import com.tianque.fourTeams.fourTeamsManage.domain.FourTeams;

public interface FourTeamsDao {

	// 队伍一览
	public PageInfo<FourTeams> findFourTeams(FourTeams fourTeams, Integer page,
			Integer rows, String sidx, String sord);

	// 队伍高级检索
	public PageInfo<FourTeams> findSearchFourTeams(FourTeams fourTeams,
			Integer page, Integer rows, String sidx, String sord);

	// 维护队伍检索
	public PageInfo<FourTeams> findSearchTeamName(FourTeams fourTeams,
			Integer page, Integer rows, String sidx, String sord);

	// 维护成员检索
	public PageInfo<FourTeamMembers> searchTeamMemberName(Long id, Long orgId,
			String names, Integer page, Integer rows, String sidx, String sord);

	// 四支队伍高级检索
	public PageInfo<FourTeams> findSearchFourTeamMembers(FourTeams fourTeams,
			String screeningLevel, Integer page, Integer rows, String sidx,
			String sord);

	// 子队伍一览
	public PageInfo<FourTeams> findSubFourTeams(FourTeams fourTeams,
			Integer page, Integer rows, String sidx, String sord);

	// 维护队伍新增
	public FourTeams addTeam(FourTeams fourTeams);

	// indexId的最大值
	public Integer indexIdNumber();

	// 队伍一览中变更当前队伍数+1
	public void updateFourTeamsSubTeamNumber(Long id);

	public FourTeams getFourTeams(Long id);

	public FourTeams getFourTeam(Long id);

	// 查看维护队伍信息
	public FourTeams findFourTeamView(Long id);

	// 维护队伍批量删除
	public int deleteFourTeams(String[] id);

	// 维护队伍批量删除成功队伍数-1
	public void updateTeamByDeleteFourTeams(Long id, Integer number);

	// 维护队伍修改
	public FourTeams editTeam(FourTeams fourTeams);

	// 四支队伍一览
	public PageInfo<FourTeams> findserviceFourTeams(FourTeams fourTeams,
			String screeningLevel, Integer page, Integer rows, String sidx,
			String sord);

	// 维护成员一览
	public PageInfo<FourTeamMembers> findMemberFourTeams(Long id, Integer page,
			Integer rows, String sidx, String sord);

	// 队伍一览中变更当前队伍成员数+1
	public void updateFourTeamsSubTeamMemberNumber(Long id);

	// 维护成员新增
	public FourTeamMembers addTeamMember(FourTeamMembers fourTeamMembers);

	// 维护成员批量删除
	public void deleteFourTeamMembers(String[] ids);

	// 维护队伍批量删除成功队伍成员数-1
	public void updateTeamByDeleteFourTeamMembers(Long id, Integer length);

	public FourTeamMembers findFourTeamMemberEdit(Long id);

	// 维护队伍成员修改
	public FourTeamMembers editTeamMember(FourTeamMembers fourTeamMembers);

	/**
	 * 通过四支队伍的类别查询队伍信息
	 */
	public FourTeams getFourTeamMembersByType(String type);

	/**
	 * 查询队伍下是否存在维护人员
	 */
	public Integer findTeamHasMember(String id);

	/**
	 * 事件中办理，办结选择队伍的信息
	 */
	public PageInfo<FourTeams> findTeamsByConditionForIssue(
			FourTeams fourTeams, Integer page, Integer rows, String sidx,
			String sord);

	/**
	 * 根据队伍名称和所在网格查询是否存在相同名称的队伍
	 */
	public FourTeams repeatTeamName(String names, Long orgId);

	/***
	 * 筛选队伍信息
	 */
	public PageInfo<FourTeams> findScreeningFourteamsForPageInfo(
			FourTeams fourTeams, String screeningLevel, Integer page,
			Integer rows, String sidx, String sord);

	/************************ 组织机构迁移合并方法 ***************************/
	/***
	 * 批量修改四支队伍的organization信息
	 */
	public Integer updateFourteamsOrganizationByIds(Long orgId, String orgCode,
			List<String> ids);

	/***
	 * 修改四支队伍的名称信息
	 */
	public void updateFourTeamsNameByIds(String fourTeamsName, Long id);

	/***
	 * 根据网格ID和code查询队伍数据信息
	 */
	public List<FourTeams> findteamsByOrgIdAndOrgCode(Long orgId, String orgCode);
}
