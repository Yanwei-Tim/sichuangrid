package com.tianque.datatransfer.dataconvert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.core.systemLog.service.SystemLogService;
import com.tianque.core.systemLog.util.ModelType;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.vladium.logging.Logger;

@Component("organizationDataConverter")
@Scope("prototype")
public class OrganizationDataConverter extends
		AbstractDataConverter<Organization> {

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private SystemLogService systemLogService;

	public Organization convertToOrganization(String[] cellValues) {

		Organization result = new Organization();

		result.setOrgType(propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						OrganizationType.ORG_TYPE_KEY, cellValues[0]));
		if (result.getOrgType().getInternalId() == OrganizationType.FUNCTIONAL_ORG) {
			result.setFunctionalOrgType(propertyDictService
					.findPropertyDictByDomainNameAndDictDisplayName(
							PropertyTypes.FUNCTIONAL_ORG_TYPE, cellValues[1]));
		}
		result.setOrgLevel(propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						OrganizationLevel.ORG_LEVEL_KEY, cellValues[2]));
		Organization parentOrg = organizationDubboService.getOrgByDepartmentNo(cellValues[3]);
		result.setParentOrg(parentOrg);
		result.setOrgName(cellValues[4]);
		result.setDepartmentNo(cellValues[5]);
		return result;
	}

//	private int getMarginNum(Organization result) {
//		int internalId = result.getOrgLevel().getInternalId();
//		switch (internalId) {
//		case OrganizationLevel.PROVINCE:
//			return 11;
//		case OrganizationLevel.CITY:
//			return 9;
//		case OrganizationLevel.DISTRICT:
//			return 7;
//		case OrganizationLevel.TOWN:
//			return 5;
//		case OrganizationLevel.VILLAGE:
//			return 3;
//		case OrganizationLevel.GRID:
//			return 1;
//		}
//		return 0;
//	}

	@Override
	public boolean isRepeatData(Organization domain) {
		return organizationDubboService.isHasThisOrganization(domain.getId());
	}

	@Override
	public Organization persistenceDomain(Organization domain) {
		Organization organization = organizationDubboService.addOrganization(domain);
		systemLogService
				.log(Logger.INFO,
						ModelType.DEPT,
						OperatorType.IPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												organization.getId(),
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + "导入组织机构"
								+ organization.getOrgName(), ObjectToJSON
								.convertJSON(domain));
		return domain;
	}

	@Override
	public Organization updateDomain(Organization domain) {
		if (null != domain.getParentOrg()) {
			domain.setParentOrg(organizationDubboService.getOrgByDepartmentNo(domain
					.getParentOrg().getDepartmentNo()));
		}
		organizationDubboService.updateOrganizationByName(domain.getOrgName(),
				domain.getId(), domain);
		return domain;
	}

	@Override
	public ValidateResult validate(Organization organization, int realRow) {

		return new ValidateResult();
	}
	
	public ValidateResult validateForOrganization(String[] cellValues, int realRow){
		cellValues = validateHelper.cellValueTrim(cellValues);
		ValidateResult result = new ValidateResult();
		String orgType = cellValues[0];
		String functionOrgType = cellValues[1];
		String orgLevel = cellValues[2];
		String parentDeparentNo = cellValues[3];
		String importOrgName = cellValues[4];
		String importOrgDeparentNo = cellValues[5];
		if(!StringUtil.isStringAvaliable(orgType)){
			result.addErrorMessage(realRow, 0, "组织机构类型必须选择");
		}else{
			PropertyDict orgTypeDict = propertyDictService.findPropertyDictByDomainNameAndDictDisplayName(
					OrganizationType.ORG_TYPE_KEY, orgType);
			if(orgTypeDict==null || orgTypeDict.getId()==null){
				result.addErrorMessage(realRow, 0, "组织机构类型错误，未找到正确类型");
			}else if(orgTypeDict.getInternalId()==OrganizationType.FUNCTIONAL_ORG){//导入为职能部门
				if(!StringUtil.isStringAvaliable(functionOrgType)){
					result.addErrorMessage(realRow, 1, "组织机构类型为职能部门时，职能部门类型必须选择");
				}
			}
		}
		if(!StringUtil.isStringAvaliable(orgLevel)){
			result.addErrorMessage(realRow, 2, "组织机构层级必须选择");
		}else{
			PropertyDict orgLevelDict = propertyDictService.findPropertyDictByDomainNameAndDictDisplayName(
					PropertyTypes.ORGANIZATION_LEVEL, orgLevel);
			if(orgLevelDict==null || orgLevelDict.getId()==null){
				result.addErrorMessage(realRow, 2, "组织机构层级数据错误");
			}
		}
		if(!StringUtil.isStringAvaliable(parentDeparentNo)){
			result.addErrorMessage(realRow, 3, "上级部门编号必须填写");
		}else{
			//根据上级部门编号查询组织机构信息
			Organization organization = organizationDubboService.getOrgByDepartmentNo(parentDeparentNo);
			if(organization==null){
				result.addErrorMessage(realRow, 3, "上级部门编号填写错误，未找到该编号所属机构");
			}
		}
		if(!StringUtil.isStringAvaliable(importOrgName)){
			result.addErrorMessage(realRow, 4, "导入组织机构名称必须填写");
		}else if(validateHelper.illegalStringLength(0, 20,
				importOrgName)){
			result.addErrorMessage(realRow, 4, "导入组织机构名称输入错误，最多只能输入20个字符");
		}
		
		if(!StringUtil.isStringAvaliable(importOrgName)){
			result.addErrorMessage(realRow, 5, "导入组织机构编号必须填写");
		}else{
			//根据上级部门编号查询组织机构信息
			Organization organization = organizationDubboService.getOrgByDepartmentNo(importOrgDeparentNo);
			if(organization!=null){
				result.addErrorMessage(realRow, 5, "导入的组织机构编码填写错误，系统中已存在相同编码");
			}
		}
		
		return result;
	}

	@Override
	public Organization convertToDomain(String[] cellValues,
			ValidateResult result) {
		return null;
	}

}