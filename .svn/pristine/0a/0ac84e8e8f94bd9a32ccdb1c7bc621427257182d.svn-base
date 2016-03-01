package com.tianque.partyBuilding.baseInfos.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseServiceImpl;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.partyBuilding.baseInfos.dao.BasicCaseDao;
import com.tianque.partyBuilding.baseInfos.domain.BasicCase;
import com.tianque.partyBuilding.baseInfos.domain.vo.SearchBasicCaseVo;
import com.tianque.partyBuilding.baseInfos.service.BasicCaseService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 基本情况表:业务逻辑层
 * 
 * @author
 * @date 2014-01-14 15:32:52
 */
@Transactional
@Service("basicCaseService")
public class BasicCaseServiceImpl extends
		BaseServiceImpl<BasicCase, SearchBasicCaseVo> implements
		BasicCaseService {

	private static Logger logger = LoggerFactory
			.getLogger(BasicCaseServiceImpl.class);

	@Autowired
	private BasicCaseDao basicCaseDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Autowired
	@Qualifier("basicCaseValidator")
	private DomainValidator<BasicCase> domainValidator;

	@Resource(name = "basicCaseDao")
	public void setBaseDao(BasicCaseDao baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public BasicCase add(BasicCase basicCase) {
		basicCaseValidator(basicCase);
		autoFillOrganizationInternalCode(basicCase);
		try {
			basicCase = getBaseDao().add(basicCase);
			return basicCase;
		} catch (Exception e) {
			return dealException("BasicCaseServiceImpl", "add",
					"新增基本情况表信息出现错误", e);
		}
	}

	@Override
	public BasicCase update(BasicCase basicCase) {
		basicCaseValidator(basicCase);
		try {
			basicCase = getBaseDao().update(basicCase);
			return basicCase;
		} catch (Exception e) {
			return dealException("BasicCaseServiceImpl", "update",
					"更新基本情况表信息出现错误", e);
		}
	}

	private void basicCaseValidator(BasicCase basicCase) {
		ValidateResult baseDataValidator = domainValidator.validate(basicCase);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

	@Override
	public BasicCase findBasicCaseByIdAndOrgId(Long id, Long orgId) {
		if (orgId == null) {
			throw new BusinessValidationException("组织机构不能为空");
		}
		return basicCaseDao.findBasicCaseByIdAndOrgId(id, orgId);
	}

	private void autoFillOrganizationInternalCode(BasicCase basicCase) {
		if (basicCase.getOrganization() == null) {
			throw new BusinessValidationException("找不到指定网格");
		} else {
			Organization organization = organizationDubboService
					.getSimpleOrgById(basicCase.getOrganization().getId());
			basicCase.setOrgInternalCode(organization.getOrgInternalCode());
		}
	}

	@Override
	public BasicCase getByIdAndOrgIdAndType(Long id, Long orgId, String type) {
		if (orgId == null) {
			throw new BusinessValidationException("组织机构不能为空");
		}
		return basicCaseDao.getByIdAndOrgIdAndType(id, orgId, type);
	}

}
