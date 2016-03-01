package com.tianque.issue.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.exolab.castor.mapping.xml.PropertyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseServiceImpl;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.PropertyDomain;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.issue.constants.IssueSkipRuleStatus;
import com.tianque.issue.dao.IssueSkipruleDao;
import com.tianque.issue.domain.IssueMessageRemind;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.domain.IssueSkiprule;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.domain.vo.SearchIssueSkipruleVo;
import com.tianque.issue.service.IssueMessageRemindService;
import com.tianque.issue.service.IssueSkipruleService;
import com.tianque.service.IssueTypeService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.userAuth.api.PropertyDomainDubboService;

/**
 * 越级规则设置:业务逻辑层
 * 
 * @author
 * @date 2013-11-22 14:51:42
 */
@Repository("issueSkipruleService")
public class IssueSkipruleServiceImpl extends
		BaseServiceImpl<IssueSkiprule, SearchIssueSkipruleVo> implements
		IssueSkipruleService {

	@Autowired
	@Qualifier("IssueSkipruleValidator")
	private DomainValidator<IssueSkiprule> domainValidator;

	@Autowired
	private IssueMessageRemindService issueMessageRemindService;

	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Autowired
	private IssueTypeService issueTypeService;

	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private PropertyDomainDubboService domainDubboService;

	@Resource(name = "issueSkipruleDao")
	public void setBaseDao(IssueSkipruleDao baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public IssueSkiprule add(IssueSkiprule issueSkiprule) {
		issueSkipruleValidator(issueSkiprule);
		try {
			issueSkiprule = getBaseDao().add(issueSkiprule);
			return issueSkiprule;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类IssueSkipruleServiceImpl的add方法出现异常，原因：",
					"新增越级规则设置信息出现错误", e);
		}
	}

	@Override
	public IssueSkiprule addIssueSkiprule(IssueSkiprule issueSkiprule,
			List<IssueMessageRemind> issueMessageRemindList) {
		validateSkiprule(issueSkiprule);
		// 先把状态提出来，去查询规则是否存在
		Integer status = issueSkiprule.getStatus();
		issueSkiprule.setStatus(null);

		IssueSkiprule exsistIssueSkiprule = getIssueSkipruleByRules(issueSkiprule);
		if (exsistIssueSkiprule != null) {
			throw new BusinessValidationException("该越级规则设置已经存在");
		}
		// 设置保存状态
		issueSkiprule.setStatus(status);
		IssueSkiprule is = add(issueSkiprule);
		if (issueMessageRemindList != null) {
			for (IssueMessageRemind issueMessageRemind : issueMessageRemindList) {
				if (issueMessageRemind.getContactsName() != null
						&& !"".equals(issueMessageRemind.getContactsName())
						&& issueMessageRemind.getContactsMobile() != null
						&& !"".equals(issueMessageRemind.getContactsMobile())) {
					issueMessageRemind.setIssueSkipruleId(is.getId());
					issueMessageRemindService.add(issueMessageRemind);
				}

			}
		}
		return is;
	}

	@Override
	public IssueSkiprule update(IssueSkiprule issueSkiprule) {
		issueSkipruleValidator(issueSkiprule);
		try {
			issueSkiprule = getBaseDao().update(issueSkiprule);
			return issueSkiprule;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类IssueSkipruleServiceImpl的update方法出现异常，原因：",
					"更新越级规则设置信息出现错误", e);
		}
	}

	@Override
	public IssueSkiprule updateIssueSkiprule(IssueSkiprule issueSkiprule,
			List<IssueMessageRemind> issueMessageRemindList) {
		IssueSkiprule is = update(issueSkiprule);
		issueMessageRemindService.deleteIssueMessageRemindBySkipRuleId(is
				.getId());
		for (IssueMessageRemind issueMessageRemind : issueMessageRemindList) {
			if (issueMessageRemind.getContactsName() != null
					&& !"".equals(issueMessageRemind.getContactsName())
					&& issueMessageRemind.getContactsMobile() != null
					&& !"".equals(issueMessageRemind.getContactsMobile())) {
				issueMessageRemind.setIssueSkipruleId(is.getId());
				issueMessageRemindService.add(issueMessageRemind);
			}

		}
		return is;
	}

	/** 批量启用 */
	@Override
	public void startIssueSkipRule(List<Long> idList) {
		if (idList == null || idList.size() == 0)
			throw new BusinessValidationException("越级规则ID不能为空");
		((IssueSkipruleDao) getBaseDao()).updateIssueSkipRuleStatus(idList,
				IssueSkipRuleStatus.STATUS_ENABLE);
	}

	/** 批量停用 */
	@Override
	public void stopIssueSkipRule(List<Long> idList) {
		if (idList == null || idList.size() == 0)
			throw new BusinessValidationException("越级规则ID不能为空");
		((IssueSkipruleDao) getBaseDao()).updateIssueSkipRuleStatus(idList,
				IssueSkipRuleStatus.STATUS_DISABLE);
	}

	private void issueSkipruleValidator(IssueSkiprule issueSkiprule) {
		ValidateResult baseDataValidator = domainValidator
				.validate(issueSkiprule);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

	@Override
	public IssueSkiprule getIssueSkipruleByRules(IssueSkiprule issueSkiprule) {
		if (issueSkiprule == null || issueSkiprule.getOrgId() == null
				|| issueSkiprule.getIssueTypeDomainId() == null
				|| issueSkiprule.getIssueTypeId() == null
				|| issueSkiprule.getIssueUrgentLevel() == null) {
			throw new BusinessValidationException("查询参数无效!");
		}

		return ((IssueSkipruleDao) getBaseDao())
				.getIssueSkipruleByRules(issueSkiprule);
	}

	@Override
	public IssueSkiprule getIssueSkipruleByRules(IssueSkiprule issueSkiprule,
			Long orgId) {
		if (issueSkiprule == null || issueSkiprule.getOrgId() == null
				|| issueSkiprule.getIssueTypeDomainId() == null
				|| issueSkiprule.getIssueTypeId() == null
				|| issueSkiprule.getIssueUrgentLevel() == null) {
			throw new BusinessValidationException("查询参数无效!");
		}
		IssueSkiprule is = ((IssueSkipruleDao) getBaseDao())
				.getIssueSkipruleByRules(issueSkiprule);
		if (is != null) {
			Organization organization = organizationDubboService
					.getFullOrgById(ThreadVariable.getOrganization().getId());
			PropertyDict propertyDict = propertyDictService
					.getPropertyDictById(is.getSubmitLevel().getId());
			is.setSubmitLevel(propertyDict);
			if (propertyDict.getInternalId() > organization.getOrgLevel()
					.getInternalId()) {
				Organization submitOrganization = organizationDubboService
						.getParentOrgByOrgTypeAndChildOrgId(orgId,
								propertyDict.getInternalId());
				is.setSubmitOrgId(submitOrganization.getId());
				is.setSubmitOrgName(submitOrganization.getOrgName());
				is.setSubmitOrgLevel(organization.getOrgLevel().getId());
			} else if (propertyDict.getInternalId() == organization
					.getOrgLevel().getInternalId()) {
				is.setSubmitOrgId(organization.getParentOrg().getId());
				is.setSubmitOrgName(organization.getParentOrg().getOrgName());
				is.setSubmitOrgLevel(organization.getOrgLevel().getId());
			}
			// else {
			// return null;
			// }
			is.setCcOrgNames(getCcOrgNamesByOrgIds(is.getCcOrgIds()));
		}
		return is;
	}

	@Override
	public IssueSkiprule getIssueSkipruleByIssue(IssueNew issueNew,
			IssueStep step) {
		if (step.getEmergencyLevel() != null) {
			PropertyDomain domain=domainDubboService.getPropertyDomainByDomainName(PropertyTypes.URGENT_LEVEL);
			List<PropertyDict> dicts=propertyDictService.findPropertyDictByPropertyDomainId(domain.getId());
			long highLevelId=0;
			long middleLevelId=0;
			for(int i=0;i<dicts.size();i++){
				if(dicts.get(i).getDisplayName().equals("高")){
					highLevelId=dicts.get(i).getId();
				}else if(dicts.get(i).getDisplayName().equals("中")){
					middleLevelId=dicts.get(i).getId();
				}
			}
			Organization organization=null;
			if(step.getEmergencyLevel().getId()==middleLevelId){
				organization = organizationDubboService
					.getParentOrgByOrgTypeAndChildOrgId(step.getTarget()
							.getId(), OrganizationLevel.TOWN);
			}else if(step.getEmergencyLevel().getId()==highLevelId){
				organization = organizationDubboService
						.getParentOrgByOrgTypeAndChildOrgId(step.getTarget()
								.getId(), OrganizationLevel.DISTRICT);
			}
			// IssueType issueType = issueTypeService
			// .getIssueTypeByIssueId(issueNew.getId());
			IssueSkiprule searchIssueSkiprule = new IssueSkiprule();
			searchIssueSkiprule.setOrgId(organization.getId());
			searchIssueSkiprule.setIssueTypeDomainId(issueNew.getIssueType()
					.getIssueTypeDomain().getId());
			searchIssueSkiprule.setIssueTypeId(issueNew.getIssueType().getId());
			searchIssueSkiprule.setIssueUrgentLevel(step.getEmergencyLevel());
			searchIssueSkiprule.setStatus(IssueSkipRuleStatus.STATUS_ENABLE);
			return getIssueSkipruleByRules(searchIssueSkiprule, step
					.getTarget().getId());
		} else
			return null;
	}

	@Override
	public List<IssueSkiprule> findIssueSkiprulesByRules(
			IssueSkiprule issueSkiprule) {
		if (issueSkiprule == null || issueSkiprule.getOrgId() == null
				|| issueSkiprule.getIssueTypeDomainId() == null
				|| issueSkiprule.getIssueTypeId() == null) {
			throw new BusinessValidationException("查询参数无效!");
		}
		return ((IssueSkipruleDao) getBaseDao())
				.findIssueSkiprulesByRules(issueSkiprule);
	}

	@Override
	public IssueSkiprule getIssueSkipruleById(Long id) {
		IssueSkiprule issueSkiprule = get(id);
		issueSkiprule.setCcOrgNames(getCcOrgNamesByOrgIds(issueSkiprule
				.getCcOrgIds()));
		return issueSkiprule;
	}

	private String getCcOrgNamesByOrgIds(String ccOrgIds) {
		String ccOrgNames = "";
		if (ccOrgIds != null && !"".equals(ccOrgIds)) {
			String ccOrgids[] = ccOrgIds.split(",");
			for (String ccOrgid : ccOrgids) {
				Organization organization = organizationDubboService
						.getSimpleOrgById(Long.valueOf(ccOrgid));
				ccOrgNames += organization.getOrgName() + ",";
			}
		}
		return ccOrgNames;
	}

	@Override
	public List<PropertyDict> getUrgentLevelList(IssueSkiprule issueSkiprule) {
		if (issueSkiprule == null) {
			throw new BusinessValidationException("参数无效!");
		}	
		List<PropertyDict> propertyDicts = null;
		if(!(ThreadVariable.getUser().getOrganization().getOrgLevel().getDisplayName().equals("县（区）"))&&
		   !(ThreadVariable.getUser().getOrganization().getOrgLevel().getDisplayName().equals("乡镇（街道）"))){
		Organization organization = organizationDubboService
				.getParentOrgByOrgTypeAndChildOrgId(issueSkiprule.getOrgId(),
						OrganizationLevel.TOWN);
		issueSkiprule.setOrgId(organization.getId());
		issueSkiprule.setStatus(IssueSkipRuleStatus.STATUS_ENABLE);
		List<IssueSkiprule> isList = findIssueSkiprulesByRules(issueSkiprule);
		organization = organizationDubboService
				.getParentOrgByOrgTypeAndChildOrgId(issueSkiprule.getOrgId(),
						OrganizationLevel.DISTRICT);
		issueSkiprule.setOrgId(organization.getId());
		issueSkiprule.setStatus(IssueSkipRuleStatus.STATUS_ENABLE);
		List<IssueSkiprule> isListNew = findIssueSkiprulesByRules(issueSkiprule);
		propertyDicts = new ArrayList<PropertyDict>();
		if (isList != null) {
			for (IssueSkiprule is : isList) {
				if(!ThreadVariable.getUser().getOrganization().getOrgLevel().getDisplayName().equals("村（社区）")){
					propertyDicts.add(propertyDictService.getPropertyDictById(is
							.getIssueUrgentLevel().getId()));
				}
			}
		}
		if (isListNew != null) {
			for (IssueSkiprule is : isListNew) {
				if(!ThreadVariable.getUser().getOrganization().getOrgLevel().getDisplayName().equals("乡镇（街道）")){
				propertyDicts.add(propertyDictService.getPropertyDictById(is
						.getIssueUrgentLevel().getId()));
				}
			}
		}
		}
		return propertyDicts;
	}

	private void validateSkiprule(IssueSkiprule issueSkiprule) {
		if (issueSkiprule == null || issueSkiprule.getOrgId() == null
				|| issueSkiprule.getIssueTypeDomainId() == null
				|| issueSkiprule.getIssueTypeId() == null
				|| issueSkiprule.getIssueUrgentLevel() == null
				|| issueSkiprule.getStatus() == null) {
			throw new BusinessValidationException("参数错误");
		}
	}
}
