package com.tianque.plugin.analyzing.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.cache.service.CacheService;
import com.tianque.domain.OrgLoginLog;
import com.tianque.domain.Organization;
import com.tianque.plugin.analyzing.domain.OrgLoginStanals;

@Repository("orgLoginStanalsDao")
@SuppressWarnings("unchecked")
public class OrgLoginStanalsDaoImpl extends AbstractBaseDao implements
		OrgLoginStanalsDao {
	@Autowired
	private CacheService cacheService;

	@Override
	public void addOrgLoginStanals(OrgLoginStanals orgLoginStanals) {
		try {
			getSqlMapClientTemplate().insert(
					"orgLoginStanals.addOrgLoginStanals", orgLoginStanals);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void clearPeriodOrgLoginStanalsByDate(int year, int month) {
		Map condition = new HashMap();
		condition.put("year", year);
		condition.put("month", month);
		getSqlMapClientTemplate().delete(
				"orgLoginStanals.deleteOrgLoginStanals", condition);
	}

	@Override
	public List<OrgLoginStanals> getOrgLoginStanalsForList(int year, int month,
			List<Long> organizationId) {
		Map condition = new HashMap();
		condition.put("organizationId", organizationId);
		condition.put("year", year);
		condition.put("month", month);
		return getSqlMapClientTemplate().queryForList(
				"orgLoginStanals.getOrgLoginStanalsForList", condition);
	}

	@Override
	public OrgLoginStanals getRootOrgLoginStanalsForList(int year, int month,
			Long orgId, int orgTypeId) {
		Map condition = new HashMap();
		condition.put("orgId", orgId);
		condition.put("orgType", orgTypeId);
		condition.put("year", year);
		condition.put("month", month);
		return (OrgLoginStanals) getSqlMapClientTemplate().queryForObject(
				"orgLoginStanals.getRootOrgLoginStanalsForList", condition);
	}

	@Override
	public List<OrgLoginLog> getOrgLoginLogListByStartAndEndWorkDay(
			String orgInternalCode, int orgTypeId, Date startworkdate,
			Date endworkdate) {
		Map map = new HashMap();
		map.put("orgInternalCode", orgInternalCode);
		map.put("startworkdate", startworkdate);
		map.put("endworkdate", endworkdate);
		map.put("orgType", orgTypeId);
		return getSqlMapClientTemplate().queryForList(
				"orgLoginStanals.getOrgLoginLogListByStartAndEndWorkDay", map);
	}

	@Override
	public List<Organization> findOrganizationsByOrgInternalCode(
			String orgInternalCode, int orgTypeId) {
		Map query = new HashMap();
		query.put("orgInternalCode", orgInternalCode);
		query.put("orgType", orgTypeId);
		return getSqlMapClientTemplate().queryForList(
				"orgLoginStanals.findOrganizationsByOrgInternalCode", query);
	}

	@Override
	public void deleteOrgLoginStanalsByOrgId(Long orgId) {

		getSqlMapClientTemplate().delete(
				"orgLoginStanals.deleteOrgLoginStanalsByOrgId", orgId);
	}

	@Override
	public List<OrgLoginStanals> findUsersByCreateDate(Integer date) {
		return getSqlMapClientTemplate().queryForList(
				"orgLoginStanals.findUsersByCreateDate", date);
	}

	@Override
	public List<Map<String, Object>> getWorkDay(List<String> dateList,
			Map dataMap, String firstTableName, String secondeTableName) {
		String name = "";
		for (int i = 0; i < dateList.size(); i++) {
			name += dateList.get(i);
		}
		name = "getWorkDay" + name;
		if (dataMap == null) {
			dataMap = new HashMap();
		}
		if (dataMap.get(name) != null) {
			return (List<Map<String, Object>>) dataMap.get(name);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", dateList);
		map.put("firstTableName", firstTableName);
		map.put("secondeTableName", secondeTableName);
		List<Map<String, Object>> resltList = getSqlMapClientTemplate()
				.queryForList("orgLoginStanals.getWorkDay", map);
		dataMap.put(name, resltList);
		return resltList;
	}
}
