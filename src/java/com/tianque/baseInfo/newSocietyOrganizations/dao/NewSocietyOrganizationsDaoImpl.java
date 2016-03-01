package com.tianque.baseInfo.newSocietyOrganizations.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.newSocietyOrganizations.domain.NewSocietyOrganizations;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;

@Repository("newSocietyOrganizationsDao")
public class NewSocietyOrganizationsDaoImpl extends AbstractBaseDao implements
		NewSocietyOrganizationsDao {

	@Override
	public NewSocietyOrganizations getNewSocietyOrganizationsByName(
			String name, Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("id", id);
		NewSocietyOrganizations domain = (NewSocietyOrganizations) getSqlMapClientTemplate()
				.queryForObject(
						"newSocietyOrganizations.getNewSocietyOrganizationsByName",
						map);
		return domain;
	}

	@Override
	public PageInfo<NewSocietyOrganizations> findNewSocietyOrganizationsForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord, Boolean isEmphasis) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("isEmphasis", isEmphasis);
		map.put("objectType", "NEWSOCIETYORGANIZATIONS");

		Integer countNewSocietyOrganizations = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"newSocietyOrganizations.countNewSocietyOrganizationsForListPage",
						map);

		List<NewSocietyOrganizations> list = getSqlMapClientTemplate()
				.queryForList(
						"newSocietyOrganizations.findNewSocietyOrganizationsForListPage",
						map, (pageNum - 1) * pageSize, pageSize);

		return createPageInfo(pageNum, pageSize, countNewSocietyOrganizations,
				list);
	}

	@Override
	public NewSocietyOrganizations addNewSocietyOrganizations(
			NewSocietyOrganizations newSocietyOrganizations) {
		if (!validate(newSocietyOrganizations))
			throw new BusinessValidationException("参数错误");
		Long id = (Long) getSqlMapClientTemplate().insert(
				"newSocietyOrganizations.addNewSocietyOrganizations",
				newSocietyOrganizations);
		return getSimpleNewSocietyOrganizations(id);
	}

	@Override
	public NewSocietyOrganizations getSimpleNewSocietyOrganizations(Long id) {
		NewSocietyOrganizations result = (NewSocietyOrganizations) getSqlMapClientTemplate()
				.queryForObject(
						"newSocietyOrganizations.getNewSocietyOrganizationsById",
						id);
		return result;
	}

	@Override
	public NewSocietyOrganizations updateNewSocietyOrganizations(
			NewSocietyOrganizations newSocietyOrganizations) {
		if (newSocietyOrganizations == null
				|| newSocietyOrganizations.getId() == null)
			throw new BusinessValidationException("参数错误");

		getSqlMapClientTemplate().update(
				"newSocietyOrganizations.updateNewSocietyOrganizations",
				newSocietyOrganizations);
		return getSimpleNewSocietyOrganizations(newSocietyOrganizations.getId());
	}

	@Override
	public void deleteNewSocietyOrganizationsById(Long id) {
		getSqlMapClientTemplate()
				.delete("newSocietyOrganizations.deleteNewSocietyOrganizationsById",
						id);
	}

	@Override
	public void updateEmphasiseById(Long id, Boolean isEmphasis,
			String logOutReason) {
		Map map = new HashMap();
		map.put("id", id);
		map.put("isEmphasis", isEmphasis);
		map.put("logOutReason", logOutReason);
		map.put("logOutTime", new Date());
		getSqlMapClientTemplate().update(
				"newSocietyOrganizations.updateEmphasiseById", map);
	}

	private boolean validate(NewSocietyOrganizations newSocietyFederation) {
		if (newSocietyFederation == null
				|| newSocietyFederation.getName() == null
				|| "".equalsIgnoreCase(newSocietyFederation.getName().trim())) {
			return false;
		}
		if (newSocietyFederation.getLegalPerson() == null
				|| "".equalsIgnoreCase(newSocietyFederation.getLegalPerson()
						.trim())) {
			return false;
		}
		if (newSocietyFederation.getType() == null) {
			return false;
		}
		if (newSocietyFederation.getOrganization() == null) {
			return false;
		}
		return true;
	}

	private PageInfo<NewSocietyOrganizations> createPageInfo(int pageNum,
			int pageSize, Integer countNum, List list) {
		PageInfo<NewSocietyOrganizations> pageInfo = new PageInfo<NewSocietyOrganizations>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public int getNewSocietyOrganizationsCountByOrgId(Long orgId) {
		int count = 0;
		count = (Integer) (getSqlMapClientTemplate()
				.queryForObject(
						"newSocietyOrganizations.getNewSocietyOrganizationsCountByOrgId",
						orgId));
		return count;
	}

	@Override
	public int getNewSocietyOrganizationsCountByOrgCode(String orgCode) {
		int count = 0;
		count = (Integer) (getSqlMapClientTemplate()
				.queryForObject(
						"newSocietyOrganizations.getNewSocietyOrganizationsCountByOrgCode",
						orgCode));
		return count;
	}

	/*************************** 组织机构迁移合并 **************************************/
	/**
	 * 
	 * @Title: findNewSocietyOrganizationsListForOrgChange
	 * @Description: TODO查询出源部门与目标部门重复的社会组织数据
	 * @param @param newOrgId
	 * @param @param oldOrgId
	 * @param @return 设定文件
	 * @return List<NewSocietyOrganizations> 返回类型
	 * @author wanggz
	 * @date 2014-10-18 下午02:18:05
	 */
	@Override
	public List<NewSocietyOrganizations> findNewSocietyOrganizationsListForOrgChange(
			Long newOrgId, Long oldOrgId) {
		Map map = new HashMap();
		map.put("newOrgId", newOrgId);
		map.put("oldOrgId", oldOrgId);
		return getSqlMapClientTemplate()
				.queryForList(
						"newSocietyOrganizations.findNewSocietyOrganizationsListForOrgChange",
						map);
	}

	/**
	 * 
	 * @Title: findTargetNewSocietyOrganizationsListForOrgChange
	 * @Description: TODO根据名字类型，组织机构id查询出目标部门的重复数据
	 * @param @param name
	 * @param @param type
	 * @param @param newOrgId
	 * @param @return 设定文件
	 * @return List<NewSocietyOrganizations> 返回类型
	 * @author wanggz
	 * @date 2014-10-18 下午02:35:59
	 */
	@Override
	public List<NewSocietyOrganizations> findTargetNewSocietyOrganizationsListForOrgChange(
			String name, Long newOrgId) {
		Map map = new HashMap();
		map.put("newOrgId", newOrgId);
		map.put("name", name);
		return getSqlMapClientTemplate()
				.queryForList(
						"newSocietyOrganizations.findTargetNewSocietyOrganizationsListForOrgChange",
						map);
	}

}
