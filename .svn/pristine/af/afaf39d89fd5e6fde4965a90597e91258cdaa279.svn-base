package com.tianque.plugin.dataManage.location.companyPlaceTemp.dataConverter;

import javax.annotation.Resource;

import ognl.Ognl;
import ognl.OgnlException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.util.SpringBeanUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.facade.organization.OrganizationServiceFacade;
import com.tianque.plugin.dataManage.base.ReflectionUtil;
import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.location.companyPlaceTemp.domain.CompanyPlaceTemp;
import com.tianque.plugin.dataManage.validate.DomainValidatorTemp;
import com.tianque.plugin.datatransfer.dataconvert.AbstractTempDataConverter;

@Component("companyPlaceTempDataConverter")
public class CompanyPlaceTempDataConverter extends AbstractTempDataConverter<CompanyPlaceTemp> {
	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	@Resource(name = "companyPlaceTempService")
	public void setDataManageService(AbstractDataManageService dataManageService) {
		super.setDataManageService(dataManageService);
	}

	@Autowired
	@Resource(name = "companyPlaceTempValidator")
	public void setValidator(DomainValidatorTemp validators) {
		super.setValidator(validators);
	}

	@Override
	public CompanyPlaceTemp persistenceDomain(CompanyPlaceTemp domain) {
		try {
			Organization org = ReflectionUtil.getTargetOrganization(domain);
			if (null == org) {
				throw new BusinessValidationException("所属网格不能为空!");
			}
			if (org.getOrgLevel().getInternalId() > OrganizationLevel.DISTRICT) {
				throw new BusinessValidationException("所属网格应该在区县以下!");
			}
			// 对场所中的组织机构的添加时的特殊处理
			Ognl.setValue("orgInternalCode", domain, org.getOrgInternalCode());

			OrganizationServiceFacade facade = (OrganizationServiceFacade) SpringBeanUtil
					.getBeanFromSpringByBeanNameForFacade("organizationServiceFacade");
			Ognl.setValue("claimDetail.districtOrgId", domain, facade.getDistrictorgId(org.getId()));
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
		return (CompanyPlaceTemp) dataManageService.addTemp(CompanyPlaceTemp.class, domain);
	}

}
