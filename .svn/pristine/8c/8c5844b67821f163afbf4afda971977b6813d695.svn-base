package com.tianque.fourTeams.fourTeamsManage.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.fourTeams.fourTeamsManage.dao.FourTeamsDao;
import com.tianque.fourTeams.fourTeamsManage.domain.FourTeamMembers;
import com.tianque.fourTeams.fourTeamsManage.domain.FourTeams;

@Repository("fourTeamsDao")
public class FourTeamsDaoImpl extends AbstractBaseDao implements FourTeamsDao {

	@Override
	public PageInfo<FourTeams> findFourTeams(FourTeams fourTeams, Integer page,
			Integer rows, String sidx, String sord) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("fourTeams", fourTeams);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"fourTeams.countTeamsByCondition", map);

		List<FourTeams> list = getSqlMapClientTemplate().queryForList(
				"fourTeams.findTeamsByCondition", map, (page - 1) * rows, rows);
		PageInfo<FourTeams> pageInfo = new PageInfo<FourTeams>(page, rows,
				countNum, list);
		return pageInfo;
	}

	@Override
	public PageInfo<FourTeams> findSearchFourTeams(FourTeams fourTeams,
			Integer page, Integer rows, String sidx, String sord) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("fourTeams", fourTeams);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"fourTeams.countSearchTeamsByCondition", map);

		List<FourTeams> list = getSqlMapClientTemplate().queryForList(
				"fourTeams.findSearchTeamsByCondition", map, (page - 1) * rows,
				rows);
		PageInfo<FourTeams> pageInfo = new PageInfo<FourTeams>(page, rows,
				countNum, list);
		return pageInfo;
	}

	@Override
	public PageInfo<FourTeams> findSearchTeamName(FourTeams fourTeams,
			Integer page, Integer rows, String sidx, String sord) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("fourTeams", fourTeams);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"fourTeams.countSearchTeamsByConditionNames", map);

		List<FourTeams> list = getSqlMapClientTemplate().queryForList(
				"fourTeams.findSearchTeamsByConditionNames", map,
				(page - 1) * rows, rows);
		PageInfo<FourTeams> pageInfo = new PageInfo<FourTeams>(page, rows,
				countNum, list);
		return pageInfo;
	}

	@Override
	public PageInfo<FourTeamMembers> searchTeamMemberName(Long id, Long orgId,
			String names, Integer page, Integer rows, String sidx, String sord) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("id", id);
		map.put("names", names);
		map.put("orgId", orgId);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"fourTeams.countSearchTeamMembersByConditionNames", map);

		List<FourTeamMembers> list = getSqlMapClientTemplate().queryForList(
				"fourTeams.findSearchTeamMembersByConditionNames", map,
				(page - 1) * rows, rows);
		PageInfo<FourTeamMembers> pageInfo = new PageInfo<FourTeamMembers>(
				page, rows, countNum, list);
		return pageInfo;
	}

	@Override
	public PageInfo<FourTeams> findSearchFourTeamMembers(FourTeams fourTeams,
			String screeningLevel, Integer page, Integer rows, String sidx,
			String sord) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("fourTeams", fourTeams);
		map.put("screeningLevel", screeningLevel);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"fourTeams.countSearchTeamMembersByCondition", map);

		List<FourTeams> list = getSqlMapClientTemplate().queryForList(
				"fourTeams.findSearchTeamMembersByCondition", map,
				(page - 1) * rows, rows);
		PageInfo<FourTeams> pageInfo = new PageInfo<FourTeams>(page, rows,
				countNum, list);
		return pageInfo;
	}

	@Override
	public PageInfo<FourTeams> findSubFourTeams(FourTeams fourTeams,
			Integer page, Integer rows, String sidx, String sord) {
		if (fourTeams == null || fourTeams.getOrgIdsList() == null
				|| fourTeams.getOrgIdsList().size() == 0) {
			return PageInfo.emptyPage(rows);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("fourTeams", fourTeams);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"fourTeams.countFourTeamsByCondition", map);

		List<FourTeams> list = getSqlMapClientTemplate().queryForList(
				"fourTeams.findFourTeamsByCondition", map, (page - 1) * rows,
				rows);
		return new PageInfo<FourTeams>(page, rows, countNum, list);
	}

	@Override
	public FourTeams addTeam(FourTeams fourTeams) {
		getSqlMapClientTemplate().insert("fourTeams.insertTeam", fourTeams);
		FourTeams result = (FourTeams) getSqlMapClientTemplate()
				.queryForObject("fourTeams.getFourTeams", fourTeams.getId());

		return result;
	}

	@Override
	public Integer indexIdNumber() {
		Integer indexId = (Integer) getSqlMapClientTemplate().queryForObject(
				"fourTeams.maxIndexId");
		indexId = indexId + 1;
		return indexId;
	}

	@Override
	public void updateFourTeamsSubTeamNumber(Long id) {
		getSqlMapClientTemplate().update(
				"fourTeams.updateFourTeamsSubTeamNumber", id);
	}

	@Override
	public FourTeams getFourTeams(Long id) {
		FourTeams result = (FourTeams) getSqlMapClientTemplate()
				.queryForObject("fourTeams.getFourTeams", id);
		return result;
	}

	@Override
	public FourTeams getFourTeam(Long id) {
		FourTeams result = (FourTeams) getSqlMapClientTemplate()
				.queryForObject("fourTeams.getFourTeam", id);
		return result;
	}

	@Override
	public FourTeams findFourTeamView(Long id) {
		FourTeams result = (FourTeams) getSqlMapClientTemplate()
				.queryForObject("fourTeams.getFourTeamView", id);
		return result;
	}

	@Override
	public int deleteFourTeams(String[] id) {
		return getSqlMapClientTemplate()
				.delete("fourTeams.deleteFourTeams", id);
	}

	@Override
	public void updateTeamByDeleteFourTeams(Long id, Integer number) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("number", number);
		getSqlMapClientTemplate().update(
				"fourTeams.updateTeamByDeleteFourTeams", map);
	}

	@Override
	public FourTeams editTeam(FourTeams fourTeams) {
		getSqlMapClientTemplate().update("fourTeams.updateTeam", fourTeams);
		FourTeams result = (FourTeams) getSqlMapClientTemplate()
				.queryForObject("fourTeams.getFourTeams", fourTeams.getId());

		return result;
	}

	@Override
	public PageInfo<FourTeams> findserviceFourTeams(FourTeams fourTeams,
			String screeningLevel, Integer page, Integer rows, String sidx,
			String sord) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("fourTeams", fourTeams);
		map.put("orgCode", fourTeams.getOrganization().getOrgInternalCode());
		map.put("screeningLevel", screeningLevel);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"fourTeams.countServiceTeamsByCondition", map);

		List<FourTeams> list = getSqlMapClientTemplate().queryForList(
				"fourTeams.findServiceTeamsByCondition", map,
				(page - 1) * rows, rows);
		PageInfo<FourTeams> pageInfo = new PageInfo<FourTeams>(page, rows,
				countNum, list);
		return pageInfo;
	}

	@Override
	public PageInfo<FourTeamMembers> findMemberFourTeams(Long id, Integer page,
			Integer rows, String sidx, String sord) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("id", id);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"fourTeams.countMemberFourTeamsByCondition", map);

		List<FourTeamMembers> list = getSqlMapClientTemplate().queryForList(
				"fourTeams.findMemberFourTeamsByCondition", map,
				(page - 1) * rows, rows);
		PageInfo<FourTeamMembers> pageInfo = new PageInfo<FourTeamMembers>(
				page, rows, countNum, list);
		return pageInfo;
	}

	@Override
	public void updateFourTeamsSubTeamMemberNumber(Long id) {
		getSqlMapClientTemplate().update(
				"fourTeams.updateFourTeamsSubTeamMemberNumber", id);
	}

	@Override
	public FourTeamMembers addTeamMember(FourTeamMembers fourTeamMembers) {
		getSqlMapClientTemplate().insert("fourTeams.insertTeamMember",
				fourTeamMembers);
		FourTeamMembers result = (FourTeamMembers) getSqlMapClientTemplate()
				.queryForObject("fourTeams.getFourTeamMembers",
						fourTeamMembers.getId());

		return result;
	}

	@Override
	public void deleteFourTeamMembers(String[] id) {
		getSqlMapClientTemplate().delete("fourTeams.deleteFourTeamMembers", id);
	}

	@Override
	public void updateTeamByDeleteFourTeamMembers(Long id, Integer number) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("number", number);
		getSqlMapClientTemplate().update(
				"fourTeams.updateTeamByDeleteFourTeamMembers", map);
	}

	@Override
	public FourTeamMembers findFourTeamMemberEdit(Long id) {
		FourTeamMembers result = (FourTeamMembers) getSqlMapClientTemplate()
				.queryForObject("fourTeams.getFourTeamMembers", id);
		return result;
	}

	@Override
	public FourTeamMembers editTeamMember(FourTeamMembers fourTeamMembers) {
		getSqlMapClientTemplate().update("fourTeams.updateTeamMember",
				fourTeamMembers);
		FourTeamMembers result = (FourTeamMembers) getSqlMapClientTemplate()
				.queryForObject("fourTeams.getFourTeamMembers",
						fourTeamMembers.getId());

		return result;
	}

	@Override
	public FourTeams getFourTeamMembersByType(String type) {
		return (FourTeams) getSqlMapClientTemplate().queryForObject(
				"fourTeams.getFourTeamMembersByType", type);
	}

	@Override
	public Integer findTeamHasMember(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"fourTeams.countMemberFourTeamsByCondition", map);
		return countNum;
	}

	@Override
	public PageInfo<FourTeams> findTeamsByConditionForIssue(
			FourTeams fourTeams, Integer page, Integer rows, String sidx,
			String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("fourTeams", fourTeams);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"fourTeams.countTeamsByConditionForIssue", map);

		List<FourTeams> list = getSqlMapClientTemplate().queryForList(
				"fourTeams.findTeamsByConditionForIssue", map,
				(page - 1) * rows, rows);
		PageInfo<FourTeams> pageInfo = new PageInfo<FourTeams>(page, rows,
				countNum, list);
		return pageInfo;
	}

	@Override
	public FourTeams repeatTeamName(String names, Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("names", names);
		map.put("orgId", orgId);
		return (FourTeams) getSqlMapClientTemplate().queryForObject(
				"fourTeams.repeatTeamName", map);
	}

	@Override
	public PageInfo<FourTeams> findScreeningFourteamsForPageInfo(
			FourTeams fourTeams, String screeningLevel, Integer page,
			Integer rows, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fourteamsType", fourTeams.getTeamType());
		map.put("orgId", fourTeams.getOrganization().getId());
		map.put("orgCode", fourTeams.getOrganization().getOrgInternalCode());
		map.put("orgIdsList", fourTeams.getOrgIdsList());
		map.put("screeningLevel", screeningLevel);

		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"fourTeams.countfindScreeningFourteamsForPageInfo", map);

		List<FourTeams> list = getSqlMapClientTemplate().queryForList(
				"fourTeams.findScreeningFourteamsForPageInfo", map,
				(page - 1) * rows, rows);
		PageInfo<FourTeams> pageInfo = new PageInfo<FourTeams>(page, rows,
				countNum, list);
		return pageInfo;
	}

	/************************** 组织机构迁移合并方法 **************************/
	@Override
	public Integer updateFourteamsOrganizationByIds(Long orgId, String orgCode,
			List<String> ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("orgCode", orgCode);
		map.put("ids", ids);
		return getSqlMapClientTemplate().update(
				"fourTeams.updateFourteamsOrganizationByIds", map);
	}

	@Override
	public void updateFourTeamsNameByIds(String fourTeamsName, Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fourTeamsName", fourTeamsName);
		map.put("id", id);
		getSqlMapClientTemplate().update("fourTeams.updateFourTeamsNameByIds",
				map);
	}

	@Override
	public List<FourTeams> findteamsByOrgIdAndOrgCode(Long orgId, String orgCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("orgCode", orgCode);
		return getSqlMapClientTemplate().queryForList(
				"fourTeams.findteamsByOrgIdAndOrgCode", map);
	}
}
