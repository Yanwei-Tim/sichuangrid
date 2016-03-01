package com.tianque.baseInfo.primaryOrg.primaryMembers.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.primaryOrg.primaryMembers.dao.PrimaryMembersDao;
import com.tianque.baseInfo.primaryOrg.primaryMembers.domain.PrimaryMemberVo;
import com.tianque.baseInfo.primaryOrg.primaryMembers.domain.PrimaryMembers;
import com.tianque.baseInfo.primaryOrg.primaryMembers.domain.PrimaryMembersOrgType;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.serviceTeam.serviceTeamMember.vo.ServiceTeamMemberVo;

/**
 * 成员数据访问层实现类
 * 
 */
@Repository("primaryMembersDao")
public class PrimaryMembersDaoImpl extends AbstractBaseDao implements
		PrimaryMembersDao {

	@Override
	public void deletePrimaryMembersById(Long id) {
		getSqlMapClientTemplate().delete(
				"primaryMembers.deletePrimaryMembersById", id);
	}

	@Override
	public void deletePrimaryMembersByIds(List<Long> ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		getSqlMapClientTemplate().delete(
				"primaryMembers.deletePrimaryMembersByIds", map);
	}

	@Override
	public PageInfo<PrimaryMembers> findPrimaryMembers(Long orgId,
			Long isHaveJob, Integer page, Integer rows, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("isHaveJob", isHaveJob);
		if (isStrotFieldAvaliable(sidx)) {
			map.put("sortField", sidx);
		}
		map.put("order", sord);
		List<PrimaryMembers> list = getSqlMapClientTemplate().queryForList(
				"primaryMembers.findPrimaryMembers", map, (page - 1) * rows,
				rows);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"primaryMembers.countFindPrimaryMembers", map);
		return createPage(rows, page, countNum, list);

	}

	@Override
	public PrimaryMembers updatePrimaryMembers(PrimaryMembers primaryMembers) {
		getSqlMapClientTemplate().update("primaryMembers.updatePrimaryMembers",
				primaryMembers);
		return getPrimaryMembersById(primaryMembers.getId());

	}

	@Override
	public PageInfo<PrimaryMembers> searchPrimaryMembersByName(Long orgId,
			Long isHaveJob, String name, Integer page, Integer rows,
			String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("isHaveJob", isHaveJob);
		map.put("name", name);
		if (isStrotFieldAvaliable(sidx)) {
			map.put("sortField", sidx);
		}
		map.put("order", sord);
		List<PrimaryMembers> list = getSqlMapClientTemplate().queryForList(
				"primaryMembers.searchPrimaryMembersByName", map,
				(page - 1) * rows, rows);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"primaryMembers.countSearchPrimaryMembersByName", map);
		return createPage(rows, page, countNum, list);
	}

	@Override
	public PrimaryMembers addPrimaryMembers(PrimaryMembers primaryMembers) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"primaryMembers.addPrimaryMembers", primaryMembers);
		return getPrimaryMembersById(id);
	}

	@Override
	public PrimaryMembers getPrimaryMembersById(Long value) {
		PrimaryMembers p = (PrimaryMembers) getSqlMapClientTemplate()
				.queryForObject("primaryMembers.getPrimaryMembersById", value);
		if (p != null) {
			return p;
		}
		return null;
	}

	@Override
	public PageInfo<PrimaryMembers> findPrimaryMembers(
			PrimaryMemberVo primaryMemberVo, Integer pageNum, Integer pageSize) {
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"primaryMembers.countFindPrimaryMembers", primaryMemberVo);
		if (countNum == 0) {
			return new PageInfo<PrimaryMembers>();
		}
		List<PrimaryMembers> list = getSqlMapClientTemplate().queryForList(
				"primaryMembers.findPrimaryMembers", primaryMemberVo,
				(pageNum - 1) * pageSize, pageSize);

		return new PageInfo<PrimaryMembers>(pageNum, pageSize, countNum, list);
	}

	@Override
	public PageInfo<PrimaryMembers> findPrimaryMembersByOrg(
			PrimaryMemberVo primaryMemberVo, Integer pageNum, Integer pageSize) {

		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"primaryMembers.countFindPrimaryMembersByOrg", primaryMemberVo);
		if (countNum == 0) {
			return new PageInfo<PrimaryMembers>();
		}
		List<PrimaryMembers> list = getSqlMapClientTemplate().queryForList(
				"primaryMembers.findPrimaryMembersByOrg", primaryMemberVo,
				(pageNum - 1) * pageSize, pageSize);

		return new PageInfo<PrimaryMembers>(pageNum, pageSize, countNum, list);
	}

	@Override
	public PrimaryMemberVo getPrimaryMemberById(Long id) {
		return (PrimaryMemberVo) getSqlMapClientTemplate().queryForObject(
				"primaryMembers.getPrimaryMemberById", id);
	}

	@Override
	public List<PrimaryMembersOrgType> findPrimarymembersorgtypeByorgId(
			List orgIds) {
		return (List<PrimaryMembersOrgType>) getSqlMapClientTemplate()
				.queryForList(
						"primaryMembers.findPrimarymembersorgtypeByorgId",
						orgIds);
	}

	@Override
	public PageInfo<PrimaryMembers> findPrimaryMembersByNameOrMobile(
			PrimaryMembers primaryMembers, Integer page, Integer rows) {
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"primaryMembers.countFindPrimaryMembersByNameOrMobile",
				primaryMembers);
		List<ServiceTeamMemberVo> list = new ArrayList();
		if (page == null) {
			list = getSqlMapClientTemplate().queryForList(
					"primaryMembers.findPrimaryMembersByNameOrMobile",
					primaryMembers);
			return new PageInfo(1, countNum, countNum, list);
		} else {
			list = getSqlMapClientTemplate().queryForList(
					"primaryMembers.findPrimaryMembersByNameOrMobile",
					primaryMembers, (page - 1) * rows, rows);
			return new PageInfo(page, rows, countNum, list);
		}
	}

	@Override
	public List<PrimaryMembers> findPrimaryMembersByIds(List<Long> ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		return getSqlMapClientTemplate().queryForList(
				"primaryMembers.findPrimaryMembersByIds", map);
	}

	private PageInfo<PrimaryMembers> createPage(int rows, int page,
			Integer countNum, List list) {
		PageInfo<PrimaryMembers> pageInfo = new PageInfo<PrimaryMembers>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	private boolean isStrotFieldAvaliable(String sortField) {
		return sortField != null && !"".equals(sortField.trim());
	}

	@Override
	public void setPrimaryOrgSeq(Long id, Integer seq) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("seq", seq);
		getSqlMapClientTemplate()
				.update("primaryMembers.setPrimaryOrgSeq", map);

	}
}