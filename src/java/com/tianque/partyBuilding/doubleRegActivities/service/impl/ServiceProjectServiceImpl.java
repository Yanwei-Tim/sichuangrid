package com.tianque.partyBuilding.doubleRegActivities.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseServiceImpl;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.partyBuilding.doubleRegActivities.dao.ServiceProjectDao;
import com.tianque.partyBuilding.doubleRegActivities.domain.ServiceProject;
import com.tianque.partyBuilding.doubleRegActivities.domain.Vo.SearchServiceProjectVo;
import com.tianque.partyBuilding.doubleRegActivities.service.ReportHasProjectService;
import com.tianque.partyBuilding.doubleRegActivities.service.ServicePojectService;
import com.tianque.sysadmin.service.PermissionService;

/**
 * 服务项目表:业务逻辑层
 * 
 * @author
 * @date 2014-02-26 10:02:48
 */
@Transactional
@Service("serviceProjectService")
public class ServiceProjectServiceImpl extends
		BaseServiceImpl<ServiceProject, SearchServiceProjectVo> implements
		ServicePojectService {

	private static Logger logger = LoggerFactory
			.getLogger(ServiceProjectServiceImpl.class);

	@Autowired
	private ServiceProjectDao serviceProjectDao;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private ReportHasProjectService reportHasProjectService;

	@Autowired
	@Qualifier("serviceProjectValidator")
	private DomainValidator<ServiceProject> domainValidator;

	@Resource(name = "serviceProjectDao")
	public void setBaseDao(ServiceProjectDao baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public ServiceProject add(ServiceProject serviceproject) {
		serviceprojectValidator(serviceproject);
		try {
			serviceproject = getBaseDao().add(serviceproject);
			return serviceproject;
		} catch (Exception e) {
			return dealException("ServiceProjectServiceImpl", "add",
					"新增服务项目表信息出现错误", e);
		}
	}

	@Override
	public ServiceProject update(ServiceProject serviceproject) {
		serviceprojectValidator(serviceproject);
		try {
			serviceproject = getBaseDao().update(serviceproject);
			return serviceproject;
		} catch (Exception e) {
			return dealException("ServiceProjectServiceImpl", "update",
					"更新服务项目表信息出现错误", e);
		}
	}

	private void serviceprojectValidator(ServiceProject serviceproject) {
		ValidateResult baseDataValidator = domainValidator
				.validate(serviceproject);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

	@Override
	public String hasDuplicateProjectName(Long id, Long orgId,
			String projectName) {
		if (orgId == null || projectName == null) {
			throw new BusinessValidationException("参数错误");
		}
		ServiceProject getServiceProject = serviceProjectDao
				.getServiceProjectByProjectNameAndOrgId(projectName, orgId);
		if (getServiceProject != null && getServiceProject.getId() != null
				&& !getServiceProject.getId().equals(id)) {
			String userName = ThreadVariable.getUser().getUserName()
					.equals(getServiceProject.getCreateUser()) ? "您"
					: permissionService.getFullUserByUerName(getServiceProject
							.getCreateUser()) == null ? "" : permissionService
							.getFullUserByUerName(
									getServiceProject.getCreateUser())
							.getName();
			return "当前组织机构(" + userName + ")下已存在相同的编号,请重新输入";
		} else {
			return null;
		}
	}

	@Override
	public Integer countClaimedByIdAndOrgId(Long id, Long orgId) {
		if (id == null || orgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		return serviceProjectDao.countClaimedByIdAndOrgId(id, orgId);
	}

	@Override
	public void deleteServiceProjectByIds(Long[] ids) {
		try {
			if (ids == null || ids.length == 0) {
				throw new BusinessValidationException("Id不能为空");
			}
			reportHasProjectService.deleteReportHasProjectByProjectIds(ids);// 根据服务项目的id删除与党组织报到关联的信息
			serviceProjectDao.deleteServiceProjectByIds(ids);
		} catch (Exception e) {
			logger.error(
					"类ServiceProjectServiceImpl的deleteServiceProjectByIds方法出现异常，原因：",
					e);
		}

	}
}
