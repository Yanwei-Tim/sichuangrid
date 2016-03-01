package com.tianque.partyBuilding.doubleRegActivities.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseDaoImpl;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.partyBuilding.doubleRegActivities.dao.ServiceProjectDao;
import com.tianque.partyBuilding.doubleRegActivities.domain.ServiceProject;
import com.tianque.partyBuilding.doubleRegActivities.domain.Vo.SearchServiceProjectVo;

/**
 * 服务项目表:数据操作层
 * 
 * @author
 * @date 2014-02-26 10:02:48
 */
@Repository("serviceProjectDao")
public class ServiceProjectDaoImpl extends
		BaseDaoImpl<ServiceProject, SearchServiceProjectVo> implements
		ServiceProjectDao {

	@Override
	public ServiceProject getServiceProjectByProjectNameAndOrgId(
			String projectName, Long orgId) {
		if (projectName == null || orgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("projectName", projectName);
		map.put("orgId", orgId);
		return (ServiceProject) getSqlMapClientTemplate().queryForObject(
				"serviceProject.getServiceProjectByProjectNameAndOrgId", map);
	}

	@Override
	public Integer countClaimedByIdAndOrgId(Long id, Long orgId) {
		if (id == null || orgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("orgId", orgId);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"serviceProject.countClaimedByIdAndOrgId", map);
	}

	@Override
	public void deleteServiceProjectByIds(Long[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idList", ids);
		getSqlMapClientTemplate().delete(
				"serviceProject.deleteServiceProjectByIds", map);

	}
}
