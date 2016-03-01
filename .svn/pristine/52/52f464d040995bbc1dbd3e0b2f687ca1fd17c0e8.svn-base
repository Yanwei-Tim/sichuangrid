package com.tianque.incident.dao.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.incident.dao.IncidentSystemManagerDao;
import com.tianque.incident.domain.IncidentType;
import com.tianque.incident.domain.component.IncidentDegreeRule;

@Repository("incidentSystemManagerDao")
public class IncidentSystemManagerDaoImpl extends AbstractBaseDao implements
		IncidentSystemManagerDao {

	/**
	 * 获取树的二级节点内容
	 */
	@Override
	public List<IncidentType> getIncidentsBysubjectionTypeId(
			Long subjectionTypeId) {
		return getSqlMapClientTemplate()
				.queryForList("incidentType.getIncidentsBysubjectionTypeId",
						subjectionTypeId);
	}

	/**
	 * 查询
	 */
	@Override
	public IncidentType getIncidentTypeById(Long id) {
		return (IncidentType) getSqlMapClientTemplate().queryForObject(
				"incidentType.getIncidentTypeById", id);
	}

	// public IncidentDegreeRule getIncidentDegreeRuleby
	/**
	 * 新增预警信息
	 */
	@Override
	public IncidentType addIncidentType(IncidentType incidentType) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"incidentType.addIncidentType", incidentType);
		return getIncidentTypeById(id);
	}

	/**
	 * 新增分级详细
	 */
	@Override
	public IncidentDegreeRule addIncidentDegreeRule(
			IncidentDegreeRule incidentDegreeRule) {
		return (IncidentDegreeRule) getSqlMapClientTemplate().insert(
				"incidentType.addIncidentDegreeRule", incidentDegreeRule);
	}

	/**
	 * 修改
	 */
	@Override
	public IncidentType updateIncidentType(IncidentType incidentType) {
		getSqlMapClientTemplate().update("incidentType.updateIncidentType",
				incidentType);
		return getIncidentTypeById(incidentType.getId());
	}

	@Override
	public void deleteIncidentDegreeRuleByIncidentTypeId(long incidentTypeId) {
		getSqlMapClientTemplate().delete(
				"incidentType.deleteIncidentDegreeRule", incidentTypeId);
	}

	@Override
	public void deleteIncidentTypeById(Long subjectTypeId) {
		getSqlMapClientTemplate().delete("incidentType.deleteIncidentTypeById",
				subjectTypeId);
	}

	@Override
	public IncidentType getIncidentTypeByNameAndSubjectTypeId(
			Long subjectTypeId, String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("subjectTypeId", subjectTypeId);
		map.put("name", name);
		return (IncidentType) getSqlMapClientTemplate().queryForObject(
				"incidentType.getIncidentTypeByNameAndSubjectTypeId", map);
	}

}
