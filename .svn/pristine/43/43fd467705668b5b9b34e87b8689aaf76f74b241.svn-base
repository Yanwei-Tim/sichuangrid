package com.tianque.partyBuilding.doubleRegActivities.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.partyBuilding.doubleRegActivities.dao.ReportHasProjectDao;
import com.tianque.partyBuilding.doubleRegActivities.domain.ReportHasProject;

/**
 * 党组织报到和服务项目关联表:数据操作层
 * 
 * @author N73
 * 
 */
@Repository("reportHasProjectDao")
public class ReportHasProjectDaoImpl extends AbstractBaseDao implements
		ReportHasProjectDao {

	@Override
	public void addReportHasProject(ReportHasProject reportHasProject) {
		getSqlMapClientTemplate().insert(
				"reportHasProject.addReportHasProject", reportHasProject);
	}

	@Override
	public void deleteReportHasProjectByReportId(Long reportId) {
		getSqlMapClientTemplate().delete(
				"reportHasProject.deleteReportHasProjectByReportId", reportId);

	}

	@Override
	public void deleteReportHasProjectByReportIds(Long[] reportIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportIds", reportIds);
		getSqlMapClientTemplate().delete(
				"reportHasProject.deleteReportHasProjectByReportIds", map);

	}

	@Override
	public List<ReportHasProject> getReportHasProjectByReportId(Long reportId) {
		return getSqlMapClientTemplate().queryForList(
				"reportHasProject.getReportHasProjectByReportId", reportId);
	}

	@Override
	public void deleteReportHasProjectByProjectId(Long projectId) {
		getSqlMapClientTemplate()
				.delete("reportHasProject.deleteReportHasProjectByProjectId",
						projectId);

	}

	@Override
	public void deleteReportHasProjectByProjectIds(Long[] projectIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("projectIds", projectIds);
		getSqlMapClientTemplate().delete(
				"reportHasProject.deleteReportHasProjectByProjectIds", map);

	}

}
