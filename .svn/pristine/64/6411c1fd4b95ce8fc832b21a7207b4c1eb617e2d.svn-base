package com.tianque.partyBuilding.doubleRegActivities.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.partyBuilding.doubleRegActivities.dao.ReportHasProjectDao;
import com.tianque.partyBuilding.doubleRegActivities.domain.ReportHasProject;
import com.tianque.partyBuilding.doubleRegActivities.service.ReportHasProjectService;

/**
 * 党组织报到和服务项目关联:业务逻辑层
 * 
 * @author N73
 * 
 */
@Transactional
@Service("reportHasProjectService")
public class ReportHasProjectServiceImpl extends AbstractBaseService implements
		ReportHasProjectService {
	@Autowired
	private ReportHasProjectDao reportHasProjectDao;

	@Override
	public void addReportHasProject(ReportHasProject reportHasProject) {
		try {
			reportHasProjectDao.addReportHasProject(reportHasProject);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ReportHasProjectServiceImpl的add方法出现异常，原因：",
					"新增党组织报到和服务项目关联表信息出现错误", e);
		}

	}

	@Override
	public void deleteReportHasProjectByReportId(Long reportId) {
		if (reportId == null) {
			throw new BusinessValidationException("参数错误");
		}
		reportHasProjectDao.deleteReportHasProjectByReportId(reportId);

	}

	@Override
	public void deleteReportHasProjectByReportIds(Long[] reportIds) {
		if (reportIds == null) {
			throw new BusinessValidationException("参数错误");
		}
		reportHasProjectDao.deleteReportHasProjectByReportIds(reportIds);

	}

	@Override
	public List<ReportHasProject> getReportHasProjectByReportId(Long reportId) {
		try {
			return this.reportHasProjectDao
					.getReportHasProjectByReportId(reportId);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ReportHasProjectServiceImpl的getReportHasProjectByReportId方法出现异常，原因：",
					"获取党组织报到和服务项目关联关系信息出现错误", e);
		}
	}

	@Override
	public void deleteReportHasProjectByProjectId(Long projectId) {
		try {
			if (projectId == null) {
				throw new BusinessValidationException("参数错误");
			}
			reportHasProjectDao.deleteReportHasProjectByProjectId(projectId);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ReportHasProjectServiceImpl的deleteReportHasProjectByProjectId方法出现异常，原因：",
					"根据projectId删除党组织报到和服务项目关联关系信息出现错误", e);
		}
	}

	@Override
	public void deleteReportHasProjectByProjectIds(Long[] projectIds) {
		try {
			if (projectIds == null) {
				throw new BusinessValidationException("参数错误");
			}
			reportHasProjectDao.deleteReportHasProjectByProjectIds(projectIds);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ReportHasProjectServiceImpl的deleteReportHasProjectByProjectIds方法出现异常，原因：",
					"根据projectId删除党组织报到和服务项目关联关系信息出现错误", e);
		}
	}

}
