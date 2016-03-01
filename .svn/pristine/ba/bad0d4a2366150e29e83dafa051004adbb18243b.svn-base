package com.tianque.xichang.working.parameterEvaluation.timeLimit.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseServiceImpl;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.xichang.working.parameterEvaluation.timeLimit.constant.UseLevel;
import com.tianque.xichang.working.parameterEvaluation.timeLimit.dao.ParametertimelimitDao;
import com.tianque.xichang.working.parameterEvaluation.timeLimit.domain.Parametertimelimit;
import com.tianque.xichang.working.parameterEvaluation.timeLimit.domain.vo.SearchParametertimelimitVo;
import com.tianque.xichang.working.parameterEvaluation.timeLimit.service.ParametertimelimitService;

/**
 * 三本台账时限标准表:业务逻辑层
 * 
 * @author
 * @date 2014-03-04 10:34:33
 */
@Repository("parametertimelimitService")
public class ParametertimelimitServiceImpl extends
		BaseServiceImpl<Parametertimelimit, SearchParametertimelimitVo> implements
		ParametertimelimitService {

	private static Logger logger = LoggerFactory.getLogger(ParametertimelimitServiceImpl.class);

	@Autowired
	@Qualifier("parametertimelimitValidator")
	private DomainValidator<Parametertimelimit> domainValidator;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private ParametertimelimitDao parametertimelimitDao;

	@Resource(name = "parametertimelimitDao")
	public void setBaseDao(ParametertimelimitDao baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public Parametertimelimit add(Parametertimelimit parametertimelimit) {
		if (null == parametertimelimit) {
			throw new BusinessValidationException("参数错误");
		}
		if (null != parametertimelimitDao.getParametertimelimitByDepartment(parametertimelimit
				.getDepartmentlevel().getId())) {
			throw new BusinessValidationException("该层级已经设置时限，不能重复设置");
		}
		try {
			fillOrgTypeAndOrgLevel(parametertimelimit);
			parametertimelimit = getBaseDao().add(parametertimelimit);
			return parametertimelimit;
		} catch (Exception e) {
			throw new ServiceValidationException("类ParametertimelimitServiceImpl的add方法出现异常，原因：",
					"新增三本台账时限标准表信息出现错误", e);
		}
	}

	@Override
	public Parametertimelimit update(Parametertimelimit parametertimelimit) {
		// parametertimelimitValidator(parametertimelimit);
		try {
			fillOrgTypeAndOrgLevel(parametertimelimit);
			parametertimelimit = getBaseDao().update(parametertimelimit);
			return parametertimelimit;
		} catch (Exception e) {
			throw new ServiceValidationException("类ParametertimelimitServiceImpl的update方法出现异常，原因：",
					"更新三本台账时限标准表信息出现错误", e);
		}
	}

	private void parametertimelimitValidator(Parametertimelimit parametertimelimit) {
		ValidateResult baseDataValidator = domainValidator.validate(parametertimelimit);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(baseDataValidator.getErrorMessages());
		}
	}

	private void fillOrgTypeAndOrgLevel(Parametertimelimit parametertimelimit) {
		PropertyDict departmentlevel = propertyDictService.getPropertyDictById(parametertimelimit
				.getDepartmentlevel().getId());
		parametertimelimit.setUselevel(propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(PropertyTypes.ORGANIZATION_LEVEL,
						UseLevel.getOrgLevelDisplayName(departmentlevel.getDisplayName())));
		parametertimelimit.setOrgtype(propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(PropertyTypes.ORGANIZATION_TYPE,
						UseLevel.getOrgTypeDisplayName(departmentlevel.getDisplayName())));

	}

	@Override
	public Parametertimelimit getParametertimelimitByOrgId(Long orgId) {
		if (null == orgId) {
			throw new BusinessValidationException("参数错误");
		}
		Organization org = organizationDubboService.getFullOrgById(orgId);
		Parametertimelimit parametertimelimit = parametertimelimitDao
				.getParametertimelimitByOrgTypeAndOrgLevel(org.getOrgType().getId(), org
						.getOrgLevel().getId());
		if (null == parametertimelimit) {
			parametertimelimit = parametertimelimitDao.getDefaultParametertimelimit();
		}
		return parametertimelimit;
	}
}
