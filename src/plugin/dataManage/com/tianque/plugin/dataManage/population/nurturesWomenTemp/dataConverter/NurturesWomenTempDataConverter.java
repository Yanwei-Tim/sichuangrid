package com.tianque.plugin.dataManage.population.nurturesWomenTemp.dataConverter;

import javax.annotation.Resource;

import ognl.Ognl;
import ognl.OgnlException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tianque.core.util.SpringBeanUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.domain.property.CurrentAddressType;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.facade.organization.OrganizationServiceFacade;
import com.tianque.plugin.dataManage.base.ReflectionUtil;
import com.tianque.plugin.dataManage.base.dao.AbstractDataManageDao;
import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.population.nurturesWomenTemp.domain.NurturesWomenTemp;
import com.tianque.plugin.dataManage.validate.DomainValidatorTemp;
import com.tianque.plugin.datatransfer.dataconvert.AbstractTempDataConverter;

@Component("nurturesWomenTemp")
public class NurturesWomenTempDataConverter extends AbstractTempDataConverter<NurturesWomenTemp> {
	private final Logger logger = Logger.getLogger(getClass());
	@Autowired
	@Qualifier("abstractDataManageDao")
	private AbstractDataManageDao abstractDataManageDao;

	@Autowired
	@Resource(name = "businessPopulationDataManageService")
	public void setDataManageService(AbstractDataManageService dataManageService) {
		super.setDataManageService(dataManageService);
	}

	@Autowired
	@Resource(name = "nurturesWomenTempValidator")
	public void setValidator(DomainValidatorTemp validators) {
		super.setValidator(validators);
	}

	@Override
	public NurturesWomenTemp persistenceDomain(NurturesWomenTemp domain) {
		try {
			Organization org = ReflectionUtil.getTargetOrganization(domain);
			if (null == org) {
				throw new BusinessValidationException("所属网格不能为空!");
			}
			if (org.getOrgLevel().getInternalId() > OrganizationLevel.DISTRICT) {
				throw new BusinessValidationException("所属网格应该在区县以下!");
			}

			OrganizationServiceFacade facade = (OrganizationServiceFacade) SpringBeanUtil
					.getBeanFromSpringByBeanNameForFacade("organizationServiceFacade");
			Ognl.setValue("claimDetail.districtOrgId", domain, facade.getDistrictorgId(org.getId()));
			Ognl.setValue("orgInternalCode", domain, org.getOrgInternalCode());
		} catch (Exception e) {
			logger.error("获取对象的组织机构organization错误", e);
		}
		try {
			ReflectionUtil.getTargetClaimDetail(domain).setImportDepartment(
					ThreadVariable.getUser().getOrganization());

		} catch (Exception e) {
			throw new ServiceValidationException("获取对象的认领状态ClaimDetail错误", e);
		}
		try {
			Ognl.setValue("claimDetail.importDepartment", domain, ThreadVariable.getUser()
					.getOrganization());

			Ognl.setValue("claimDetail.importDate", domain, ThreadVariable.getSession()
					.getAccessTime());
		} catch (OgnlException e) {
			logger.error("导入的数据设置属性值出错", e);
		}

		if (null != domain.getManCurrentAddressType()
				&& domain.getManCurrentAddressType().getInternalId() != CurrentAddressType.OTHER) {
			domain.setManCurrentAddress(domain.getManCommunity() + "小区" + domain.getManBlock()
					+ "幢" + domain.getManUnit() + "单元" + domain.getManRoom() + "室");
		} else {
			domain.setManCurrentAddress(domain.getManCommunity());
		}

		abstractDataManageDao.addTemp(domain);
		return domain;
	}

	public void setAbstractDataManageDao(AbstractDataManageDao abstractDataManageDao) {
		this.abstractDataManageDao = abstractDataManageDao;
	}
}
