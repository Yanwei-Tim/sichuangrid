package com.tianque.partyBuilding.doubleRegActivities.service;

import java.util.List;

import com.tianque.partyBuilding.doubleRegActivities.domain.ReportHasProject;

/**
 * 党组织报到和服务项目关联表:业务逻辑层接口
 * 
 * @author
 */
public interface ReportHasProjectService {

	/**
	 * 新增党组织报到和服务项目关联信息
	 * 
	 * @param reportHasProject
	 */
	void addReportHasProject(ReportHasProject reportHasProject);

	/**
	 * 根据reportId删除党组织报到和服务项目关联信息
	 * 
	 * @param reportId
	 */
	void deleteReportHasProjectByReportId(Long reportId);

	/**
	 * 根据reportIds删除党组织报到和服务项目关联信息
	 * 
	 * @param reportId
	 */
	void deleteReportHasProjectByReportIds(Long[] reportIds);

	/**
	 * 根据reportId获取党组织报到和服务项目关联信息
	 * 
	 * @param projectId
	 * @return
	 */
	List<ReportHasProject> getReportHasProjectByReportId(Long reportId);

	/**
	 * 根据projectId删除党组织报到和服务项目关联信息
	 * 
	 * @param projectId
	 */
	void deleteReportHasProjectByProjectId(Long projectId);

	/**
	 * 根据projectIds删除党组织报到和服务项目关联信息
	 * 
	 * @param projectId
	 */
	void deleteReportHasProjectByProjectIds(Long[] projectIds);

}
