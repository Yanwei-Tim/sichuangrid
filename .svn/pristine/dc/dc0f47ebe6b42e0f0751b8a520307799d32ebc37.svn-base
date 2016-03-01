package com.tianque.baseInfo.primaryOrg.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.primaryOrg.domain.PrimaryOrgVo;
import com.tianque.baseInfo.primaryOrg.domain.PrimaryOrganizations;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;

@Repository("primaryOrganizationsDao")
public class PrimaryOrganizationsDaoImpl extends AbstractBaseDao implements
		PrimaryOrganizationsDao {

	/*
	 * @Override public PageInfo<PrimaryOrganizations> findPrimaryOrgs(
	 * PrimaryOrgVo primaryOrgVo, Integer pageNum, Integer pageSize) { Integer
	 * countNum = (Integer) getSqlMapClientTemplate().queryForObject(
	 * "primaryOrganizations.countFindPrimaryOrgs", primaryOrgVo); if (countNum
	 * == 0) { return new PageInfo<PrimaryOrganizations>(); }
	 * List<PrimaryOrganizations> list = getSqlMapClientTemplate()
	 * .queryForList("primaryOrganizations.findPrimaryOrgs", primaryOrgVo,
	 * (pageNum - 1) * pageSize, pageSize); return new
	 * PageInfo<PrimaryOrganizations>(pageNum, pageSize, countNum, list); }
	 */
	@Override
	public PageInfo<PrimaryOrganizations> findPrimaryOrgs(
			PrimaryOrgVo primaryOrgVo, Integer pageNum, Integer pageSize) {
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"primaryOrganizations.countFindPrimaryOrgs", primaryOrgVo);
		if (countNum == 0) {
			return new PageInfo<PrimaryOrganizations>();
		}
		List<PrimaryOrganizations> list = getSqlMapClientTemplate()
				.queryForList("primaryOrganizations.findPrimaryOrgs",
						primaryOrgVo, (pageNum - 1) * pageSize, pageSize);
		return new PageInfo<PrimaryOrganizations>(pageNum, pageSize, countNum,
				list);
	}

	@Override
	public PageInfo<PrimaryOrgVo> findPrimaryOrgsForOrgOption(
			PrimaryOrgVo primaryOrgVo, Integer pageNum, Integer pageSize) {
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"primaryOrganizations.countFindPrimaryOrgs", primaryOrgVo);
		if (countNum == 0) {
			return new PageInfo<PrimaryOrgVo>();
		}
		List<PrimaryOrgVo> list = getSqlMapClientTemplate().queryForList(
				"primaryOrganizations.findPrimaryOrgs", primaryOrgVo,
				(pageNum - 1) * pageSize, pageSize);
		return new PageInfo<PrimaryOrgVo>(pageNum, pageSize, countNum, list);
	}

	@Override
	public PageInfo<PrimaryOrganizations> findPrimaryOrgsBySearch(
			PrimaryOrgVo primaryOrgVo, Integer pageNum, Integer pageSize) {
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"primaryOrganizations.countFindPrimaryOrgsBySearch",
				primaryOrgVo);
		if (countNum == 0) {
			return new PageInfo<PrimaryOrganizations>();
		}
		List<PrimaryOrganizations> list = getSqlMapClientTemplate()
				.queryForList("primaryOrganizations.findPrimaryOrgsBySearch",
						primaryOrgVo, (pageNum - 1) * pageSize, pageSize);
		return new PageInfo<PrimaryOrganizations>(pageNum, pageSize, countNum,
				list);
	}

	@Override
	public int countPrimaryOrgsWhenAdd(PrimaryOrganizations primaryOrg) {
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"primaryOrganizations.countPrimaryOrgsWhenAdd", primaryOrg);
		return countNum;
	}

	@Override
	public PrimaryOrgVo getPrimaryOrgById(Long id) {
		return (PrimaryOrgVo) getSqlMapClientTemplate().queryForObject(
				"primaryOrganizations.getPrimaryOrgById", id);
	}

	@Override
	public int deletePrimaryOrg(Long id) {
		return getSqlMapClientTemplate().delete(
				"primaryOrganizations.deletePrimaryOrg", id);
	}

	@Override
	public PrimaryOrgVo addPrimaryOrg(PrimaryOrganizations primaryOrg) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"primaryOrganizations.addPrimaryOrg", primaryOrg);
		return getPrimaryOrgById(id);
	}

	@Override
	public PrimaryOrgVo updatePrimaryOrg(PrimaryOrganizations primaryOrg) {
		getSqlMapClientTemplate().update(
				"primaryOrganizations.updatePrimaryOrg", primaryOrg);
		return getPrimaryOrgById(primaryOrg.getId());
	}

	@Override
	public Integer getCount(PrimaryOrgVo primaryOrgVo) {
		if (primaryOrgVo.getMemberCountMin() != null
				|| primaryOrgVo.getMemberCountMin() != null) {
			return (Integer) getSqlMapClientTemplate().queryForObject(
					"primaryOrganizations.countFindPrimaryOrgsBySearch",
					primaryOrgVo);
		} else {
			return (Integer) getSqlMapClientTemplate().queryForObject(
					"primaryOrganizations.countFindPrimaryOrgs", primaryOrgVo);
		}
	}

	@Override
	public PrimaryOrgVo setPrimaryOrgSeq(Long id, Integer seq) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("seq", seq);
		getSqlMapClientTemplate().update(
				"primaryOrganizations.setPrimaryOrgSeq", map);
		return this.getPrimaryOrgById(id);
	}

	@Override
	public int synchronizePrimaryOrgMembersToMasseduty(Long selectedId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", selectedId);
		map.put("isSynchronize", true);
		return getSqlMapClientTemplate().update(
				"primaryOrganizations.synchronizePrimaryOrgMembersToMasseduty",
				map);
	}

	@Override
	public List<PrimaryOrganizations> getPrimaryOrganizationByprimaryMemberId(
			Long primaryMembersId) {
		return (List<PrimaryOrganizations>) getSqlMapClientTemplate()
				.queryForList(
						"primaryOrganizations.getPrimaryOrganizationByPrimaryMemberId",
						primaryMembersId);
	}

	@Override
	public List<PrimaryOrganizations> getPrimaryOrgaInfonByDetailName(
			PrimaryOrgVo primaryOrgVo) {
		return (List<PrimaryOrganizations>) getSqlMapClientTemplate()
				.queryForList(
						"primaryOrganizations.getPrimaryOrgInfoBydetailName",
						primaryOrgVo);
	}

	public List<PrimaryOrganizations> getFourTeamsPrimaryOrgaInfonByDetailName(
			PrimaryOrgVo primaryOrgVo) {
		return (List<PrimaryOrganizations>) getSqlMapClientTemplate()
				.queryForList(
						"primaryOrganizations.getFourTeamPrimaryOrgInfoBydetailName",
						primaryOrgVo);
	}

	/***************************** 迁移合并方法 *******************************/
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
	@Override
	public List<PrimaryOrganizations> findAllPrimaryOrgForOrgChange(
			Long oldOrgId, Long newOrgId, String deptMinValue) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("oldOrgId", oldOrgId);
		map.put("newOrgId", newOrgId);
		map.put("deptMinValue", deptMinValue);
		return getSqlMapClientTemplate().queryForList(
				"primaryOrganizations.findAllPrimaryOrgForOrgChange", map);
	}

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
			PrimaryOrgVo primaryOrgVo, Long newOrgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("newOrgId", newOrgId);
		if (primaryOrgVo != null) {
			if (primaryOrgVo.getTeamClazz() != null
					&& primaryOrgVo.getTeamClazz().getId() != null) {
				map.put("teamclazz", primaryOrgVo.getTeamClazz().getId());
			}
			if (primaryOrgVo.getTeamType() != null
					&& primaryOrgVo.getTeamType().getId() != null) {
				map.put("teamtype", primaryOrgVo.getTeamType().getId());
			}
			if (primaryOrgVo.getDepartmentType() != null
					&& primaryOrgVo.getDepartmentType().getId() != null) {
				map.put("departmenttype", primaryOrgVo.getDepartmentType()
						.getId());
			}
			map.put("detailname", primaryOrgVo.getDetailName());
		}
		return getSqlMapClientTemplate()
				.queryForList(
						"primaryOrganizations.findPrimaryOrgByOrgIdAndDetailnameForOrgChange",
						map);
	}

}
