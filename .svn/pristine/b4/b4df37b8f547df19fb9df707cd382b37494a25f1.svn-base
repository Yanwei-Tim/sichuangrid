package com.tianque.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.NewEconomicOrganizationsDao;
import com.tianque.domain.NewEconomicOrganizations;
import com.tianque.domain.vo.SearchNewEconomicOrganizationsVo;
import com.tianque.exception.base.BusinessValidationException;

@Repository("newEconomicOrganizationsDao")
public class NewEconomicOrganizationsDaoImpl extends AbstractBaseDao implements
		NewEconomicOrganizationsDao {

	@Override
	public NewEconomicOrganizations addNewEconomicOrganizations(
			NewEconomicOrganizations newEconomicOrganizations) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"newEconomicOrganizations.addNewEconomicOrganizations",
				newEconomicOrganizations);
		newEconomicOrganizations = getNewEconomicOrganizationsById(id);
		return newEconomicOrganizations;
	}

	@Override
	public PageInfo<NewEconomicOrganizations> findNewEconomicOrganizationsForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord, Long isEmphasis) {
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("isEmphasis", isEmphasis);
		query.put("orgInternalCode", orgInternalCode);

		Integer countNum = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"newEconomicOrganizations.countNewEconomicOrganizations",
						query);
		if (isStrotFieldAvaliable(sidx)) {
			query.put("sortField", sidx);
		}
		query.put("order", sord);
		query.put("objectType", "NEWECONOMICORGANIZATIONS");

		List<NewEconomicOrganizations> list = getSqlMapClientTemplate()
				.queryForList(
						"newEconomicOrganizations.findNewEconomicOrganizations",
						query, (pageNum - 1) * pageSize, pageSize);

		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<NewEconomicOrganizations> createPageInfo(int pageNum,
			int pageSize, Integer countNum, List list) {
		PageInfo<NewEconomicOrganizations> pageInfo = new PageInfo<NewEconomicOrganizations>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public NewEconomicOrganizations updateNewEconomicOrganizations(
			NewEconomicOrganizations newEconomicOrganizations) {
		getSqlMapClientTemplate().update(
				"newEconomicOrganizations.updateNewEconomicOrganizations",
				newEconomicOrganizations);
		newEconomicOrganizations = getNewEconomicOrganizationsById(newEconomicOrganizations
				.getId());
		return newEconomicOrganizations;
	}

	@Override
	public NewEconomicOrganizations getNewEconomicOrganizationsById(Long id) {
		return (NewEconomicOrganizations) getSqlMapClientTemplate()
				.queryForObject(
						"newEconomicOrganizations.getNewEconomicOrganizationsById",
						id);
	}

	@Override
	public void deleteNewEconomicOrganizations(Long id) {
		getSqlMapClientTemplate().delete(
				"newEconomicOrganizations.deleteNewEconomicOrganizations", id);

	}

	@Override
	public void deleteNewEconomicOrganizationsForMore(Long[] ids) {
		if (ids == null || ids.length < 0) {
			throw new BusinessValidationException("参数错误");
		}
		getSqlMapClientTemplate()
				.delete("newEconomicOrganizations.deleteNewEconomicOrganizationsForMore",
						ids);
	}

	@Override
	public PageInfo<NewEconomicOrganizations> searchNewEconomicOrganizationss(
			Integer pageNum, Integer pageSize,
			SearchNewEconomicOrganizationsVo searchNewEconomicOrganizationsVo) {
		if (searchNewEconomicOrganizationsVo == null)
			return emptyNewEconomicOrganizationsPage(pageSize);
		PageInfo<NewEconomicOrganizations> pageInfo = new PageInfo<NewEconomicOrganizations>();
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"newEconomicOrganizations.countSearchNewEconomicOrganizations",
				searchNewEconomicOrganizationsVo);
		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		if (countNum > 0) {
			List<NewEconomicOrganizations> list = getSqlMapClientTemplate()
					.queryForList(
							"newEconomicOrganizations.searchNewEconomicOrganizations",
							searchNewEconomicOrganizationsVo,
							(pageNum - 1) * pageSize, pageSize);
			pageInfo.setResult(list);
		} else {
			pageInfo.setResult(new ArrayList<NewEconomicOrganizations>());
		}
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	private PageInfo<NewEconomicOrganizations> emptyNewEconomicOrganizationsPage(
			int pageSize) {
		PageInfo<NewEconomicOrganizations> pageInfo = new PageInfo<NewEconomicOrganizations>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<NewEconomicOrganizations>());
		return pageInfo;
	}

	@Override
	public List<NewEconomicOrganizations> searchAllNewEconomicOrganizationss(
			SearchNewEconomicOrganizationsVo searchNewEconomicOrganizationsVo) {
		return getSqlMapClientTemplate().queryForList(
				"newEconomicOrganizations.searchNewEconomicOrganizations",
				searchNewEconomicOrganizationsVo);
	}

	private boolean isStrotFieldAvaliable(String sortField) {
		return sortField != null && !"".equals(sortField.trim());
	}

	@Override
	public List<NewEconomicOrganizations> getNewEconomicOrganizationsByNameAndLicenseNumberAndOrgId(
			String name, String licenseNumber, Long orgId) {
		Map map = new HashMap();
		map.put("orgId", orgId);
		map.put("licenseNumber", licenseNumber);
		map.put("name", name);
		return getSqlMapClientTemplate()
				.queryForList(
						"newEconomicOrganizations.findNewEconomicOrganizationsByOrgIdAndLicenseNumberAndName",
						map);
	}

	@Override
	public NewEconomicOrganizations getNewEconomicOrganizationsByNameAndOrgId(
			String name, Long orgId) {
		Map map = new HashMap();
		map.put("orgId", orgId);
		map.put("name", name);
		return (NewEconomicOrganizations) getSqlMapClientTemplate()
				.queryForObject(
						"newEconomicOrganizations.findNewEconomicOrganizationsByOrgIdAndName",
						map);
	}

	@Override
	public NewEconomicOrganizations getNewEconomicOrganizationsByLicenseNumberAndOrgId(
			String licenseNumber, Long orgId) {
		Map map = new HashMap();
		map.put("orgId", orgId);
		map.put("licenseNumber", licenseNumber);
		return (NewEconomicOrganizations) getSqlMapClientTemplate()
				.queryForObject(
						"newEconomicOrganizations.findNewEconomicOrganizationsByOrgIdAndLicenseNumber",
						map);
	}

	@Override
	public void updateEmphasiseById(Long id, Long isEmphasis,
			String logOutReason) {
		Map map = new HashMap();
		map.put("id", id);
		map.put("isEmphasis", isEmphasis);
		map.put("logOutReason", logOutReason);
		map.put("logOutTime", new Date());
		getSqlMapClientTemplate().update(
				"newEconomicOrganizations.updateEmphasiseById", map);
	}

	@Override
	public Integer getCount(
			SearchNewEconomicOrganizationsVo searchNewEconomicOrganizationsVo) {
		// TODO Auto-generated method stub
		if (null == searchNewEconomicOrganizationsVo) {
			return 0;
		}
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"newEconomicOrganizations.countSearchNewEconomicOrganizations",
				searchNewEconomicOrganizationsVo);
	}

	/********************************** 组织机构迁移合并方法 ***************************************/
	/**
	 * 
	 * @Title: findRepeatNewEconomicOrganizationsForOrgChange
	 * @Description: TODO查询出源部门和目标部门重复的非公有制经济组织数据
	 * @param @param newOrgId
	 * @param @param oldOrgId
	 * @param @return 设定文件
	 * @return PageInfo<NewEconomicOrganizations> 返回类型
	 * @author wanggz
	 * @date 2014-10-18 下午05:04:16
	 */
	@Override
	public List<NewEconomicOrganizations> findRepeatNewEconomicOrganizationsForOrgChange(
			Long newOrgId, Long oldOrgId) {
		Map map = new HashMap();
		map.put("newOrgId", newOrgId);
		map.put("oldOrgId", oldOrgId);

		return getSqlMapClientTemplate()
				.queryForList(
						"newEconomicOrganizations.findRepeatNewEconomicOrganizationsForOrgChange",
						map);
	}

	/**
	 * 
	 * @Title: findRepeatNewEconomicOrganizationsByNameForOrgChange
	 * @Description: TODO查询出源部门和目标部门名称重复的非公有制经济组织数据
	 * @param @param newOrgId
	 * @param @param oldOrgId
	 * @param @return 设定文件
	 * @return List<NewEconomicOrganizations> 返回类型
	 * @author wanggz
	 * @date 2014-10-18 下午05:35:43
	 */
	@Override
	public List<NewEconomicOrganizations> findRepeatNewEconomicOrganizationsByNameForOrgChange(
			Long newOrgId, Long oldOrgId) {
		Map map = new HashMap();
		map.put("newOrgId", newOrgId);
		map.put("oldOrgId", oldOrgId);

		return getSqlMapClientTemplate()
				.queryForList(
						"newEconomicOrganizations.findRepeatNewEconomicOrganizationsByNameForOrgChange",
						map);
	}
}
