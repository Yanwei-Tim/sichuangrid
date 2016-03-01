package com.tianque.working.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.working.dao.ServiceManagementDao;
import com.tianque.working.domain.ServiceManagement;

@Repository("serviceManagementDao")
public class ServiceManagementDaoImpl extends AbstractBaseDao implements ServiceManagementDao {

	@Override
	public ServiceManagement getServiceManagementById(Long id) {
		return (ServiceManagement) getSqlMapClientTemplate().queryForObject(
				"serviceManagement.getServiceManagement", id);
	}

	@Override
	public ServiceManagement addServiceManagement(ServiceManagement serviceManagement) {
		Long id = (Long) getSqlMapClientTemplate().insert("serviceManagement.addServiceManagement",
				serviceManagement);
		return getServiceManagementById(id);
	}

	@Override
	public void deleteServiceManagementById(Long id) {
		getSqlMapClientTemplate().delete("serviceManagement.deleteServiceManagement", id);
	}

	@Override
	public ServiceManagement updateServiceManagement(ServiceManagement serviceManagement) {
		getSqlMapClientTemplate().update("serviceManagement.updateServiceManagement",
				serviceManagement);
		return getServiceManagementById(serviceManagement.getId());
	}

	@Override
	public PageInfo<ServiceManagement> findServiceManagementsForPageByOrgIdAndDailyDirectoryId(
			String dailyDirectoryIds, Long orgId, Integer page, Integer rows, String sidx,
			String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dailyDirectoryId", dailyDirectoryIds);
		map.put("sortField", sidx);
		map.put("order", sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"serviceManagement.countForPageByOrgId", map);

		List<ServiceManagement> resultList = getSqlMapClientTemplate().queryForList(
				"serviceManagement.findForPageByOrgId", map, (page - 1) * rows, rows);

		return new PageInfo<ServiceManagement>(page, rows, countNum, resultList);
	}

}
