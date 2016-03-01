package com.tianque.fourTeams.fourTeamsIssue.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseServiceImpl;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.IssueType;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.fourTeams.fourTeamsIssue.dao.FourTeamsIssueSkipruleDao;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueMessageRemind;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueSkiprule;
import com.tianque.fourTeams.fourTeamsIssue.domain.vo.SearchFourTeamsIssueSkipruleVo;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsIssueMessageRemindService;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsIssueSkipruleService;
import com.tianque.service.IssueTypeService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

/**
 * 四支队伍越级规则设置:业务逻辑层
 * 
 * @author
 * 
 */
@Repository("fourTeamsIssueSkipruleService")
public class FourTeamsIssueSkipruleServiceImpl extends
		BaseServiceImpl<FourTeamsIssueSkiprule, SearchFourTeamsIssueSkipruleVo>
		implements FourTeamsIssueSkipruleService {

	@Autowired
	@Qualifier("FourTeamsIssueSkipruleValidator")
	private DomainValidator<FourTeamsIssueSkiprule> domainValidator;

	@Autowired
	private FourTeamsIssueMessageRemindService fourTeamsIssueMessageRemindService;

	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Autowired
	private IssueTypeService issueTypeService;
	@Autowired
	private PropertyDictService propertyDictService;

	@Resource(name = "fourTeamsIssueSkipruleDao")
	public void setBaseDao(FourTeamsIssueSkipruleDao baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public FourTeamsIssueSkiprule add(
			FourTeamsIssueSkiprule fourTeamsIssueSkiprule) {
		fourTeamsIssueSkipruleValidator(fourTeamsIssueSkiprule);
		try {
			fourTeamsIssueSkiprule = getBaseDao().add(fourTeamsIssueSkiprule);
			return fourTeamsIssueSkiprule;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类FourTeamsIssueSkipruleServiceImpl的add方法出现异常，原因：",
					"新增越级规则设置信息出现错误", e);
		}
	}

	@Override
	public FourTeamsIssueSkiprule addFourTeamsIssueSkiprule(
			FourTeamsIssueSkiprule fourTeamsIssueSkiprule,
			List<FourTeamsIssueMessageRemind> fourTeamsIssueMessageRemindList) {
		FourTeamsIssueSkiprule exsistfourTeamsIssueSkiprule = getFourTeamsIssueSkipruleByRules(fourTeamsIssueSkiprule);
		if (exsistfourTeamsIssueSkiprule != null) {
			throw new BusinessValidationException("该越级规则设置已经存在");
		}
		FourTeamsIssueSkiprule is = add(fourTeamsIssueSkiprule);
		if (fourTeamsIssueMessageRemindList != null) {
			for (FourTeamsIssueMessageRemind fourTeamsIssueMessageRemind : fourTeamsIssueMessageRemindList) {
				if (fourTeamsIssueMessageRemind.getContactsName() != null
						&& !"".equals(fourTeamsIssueMessageRemind
								.getContactsName())
						&& fourTeamsIssueMessageRemind.getContactsMobile() != null
						&& !"".equals(fourTeamsIssueMessageRemind
								.getContactsMobile())) {
					fourTeamsIssueMessageRemind.setIssueSkipruleId(is.getId());
					fourTeamsIssueMessageRemindService
							.add(fourTeamsIssueMessageRemind);
				}
			}
		}
		return is;
	}

	@Override
	public FourTeamsIssueSkiprule update(
			FourTeamsIssueSkiprule fourTeamsIssueSkiprule) {
		fourTeamsIssueSkipruleValidator(fourTeamsIssueSkiprule);
		try {
			fourTeamsIssueSkiprule = getBaseDao()
					.update(fourTeamsIssueSkiprule);
			return fourTeamsIssueSkiprule;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类FourTeamsIssueSkipruleServiceImpl的update方法出现异常，原因：",
					"更新越级规则设置信息出现错误", e);
		}
	}

	@Override
	public FourTeamsIssueSkiprule updateFourTeamsIssueSkiprule(
			FourTeamsIssueSkiprule fourTeamsIssueSkiprule,
			List<FourTeamsIssueMessageRemind> fourTeamsIssueMessageRemindList) {

		FourTeamsIssueSkiprule is = update(fourTeamsIssueSkiprule);
		fourTeamsIssueMessageRemindService
				.deleteFourTeamsIssueMessageRemindBySkipRuleId(is.getId());
		for (FourTeamsIssueMessageRemind fourTeamsIssueMessageRemind : fourTeamsIssueMessageRemindList) {
			if (fourTeamsIssueMessageRemind.getContactsName() != null
					&& !"".equals(fourTeamsIssueMessageRemind.getContactsName())
					&& fourTeamsIssueMessageRemind.getContactsMobile() != null
					&& !"".equals(fourTeamsIssueMessageRemind
							.getContactsMobile())) {
				fourTeamsIssueMessageRemind.setIssueSkipruleId(is.getId());
				fourTeamsIssueMessageRemindService
						.add(fourTeamsIssueMessageRemind);
			}

		}
		return is;
	}

	private void fourTeamsIssueSkipruleValidator(
			FourTeamsIssueSkiprule fourTeamsIssueSkiprule) {
		ValidateResult baseDataValidator = domainValidator
				.validate(fourTeamsIssueSkiprule);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

	@Override
	public FourTeamsIssueSkiprule getFourTeamsIssueSkipruleByRules(
			FourTeamsIssueSkiprule fourTeamsIssueSkiprule) {
		if (fourTeamsIssueSkiprule == null
				|| fourTeamsIssueSkiprule.getOrgId() == null
				|| fourTeamsIssueSkiprule.getIssueTypeDomainId() == null
				|| fourTeamsIssueSkiprule.getIssueTypeId() == null
				|| fourTeamsIssueSkiprule.getIssueUrgentLevel() == null) {
			throw new BusinessValidationException("查询参数无效!");
		}

		return ((FourTeamsIssueSkipruleDao) getBaseDao())
				.getFourTeamsIssueSkipruleByRules(fourTeamsIssueSkiprule);
	}

	@Override
	public FourTeamsIssueSkiprule getFourTeamsIssueSkipruleByRules(
			FourTeamsIssueSkiprule fourTeamsIssueSkiprule, Long orgId) {
		if (fourTeamsIssueSkiprule == null
				|| fourTeamsIssueSkiprule.getOrgId() == null
				|| fourTeamsIssueSkiprule.getIssueTypeDomainId() == null
				|| fourTeamsIssueSkiprule.getIssueTypeId() == null
				|| fourTeamsIssueSkiprule.getIssueUrgentLevel() == null) {
			throw new BusinessValidationException("查询参数无效!");
		}
		FourTeamsIssueSkiprule is = ((FourTeamsIssueSkipruleDao) getBaseDao())
				.getFourTeamsIssueSkipruleByRules(fourTeamsIssueSkiprule);
		if (is != null) {
			Organization organization = organizationDubboService
					.getFullOrgById(ThreadVariable.getOrganization().getId());
			PropertyDict propertyDict = propertyDictService
					.getPropertyDictById(is.getSubmitLevel().getId());
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
			} else {
				return null;
			}
			is.setCcOrgNames(getCcOrgNamesByOrgIds(is.getCcOrgIds()));
		}
		return is;
	}

	@Override
	public FourTeamsIssueSkiprule getFourTeamsIssueSkipruleByIssue(
			FourTeamsIssueNew issueNew) {
		if (issueNew.getEmergencyLevel() != null) {
			Organization organization = organizationDubboService
					.getParentOrgByOrgTypeAndChildOrgId(issueNew.getOccurOrg()
							.getId(), OrganizationLevel.DISTRICT);
			IssueType issueType = issueTypeService
					.getIssueTypeByIssueId(issueNew.getId());
			FourTeamsIssueSkiprule searchFourTeamsIssueSkiprule = new FourTeamsIssueSkiprule();
			searchFourTeamsIssueSkiprule.setOrgId(organization.getId());
			searchFourTeamsIssueSkiprule.setIssueTypeDomainId(issueType
					.getIssueTypeDomain().getId());
			searchFourTeamsIssueSkiprule.setIssueTypeId(issueType.getId());
			searchFourTeamsIssueSkiprule.setIssueUrgentLevel(issueNew
					.getEmergencyLevel());
			return getFourTeamsIssueSkipruleByRules(
					searchFourTeamsIssueSkiprule, issueNew.getOccurOrg()
							.getId());
		} else
			return null;
	}

	@Override
	public List<FourTeamsIssueSkiprule> findFourTeamsIssueSkiprulesByRules(
			FourTeamsIssueSkiprule fourTeamsissueSkiprule) {
		if (fourTeamsissueSkiprule == null
				|| fourTeamsissueSkiprule.getOrgId() == null
				|| fourTeamsissueSkiprule.getIssueTypeDomainId() == null
				|| fourTeamsissueSkiprule.getIssueTypeId() == null) {
			throw new BusinessValidationException("查询参数无效!");
		}
		return ((FourTeamsIssueSkipruleDao) getBaseDao())
				.findFourTeamsIssueSkiprulesByRules(fourTeamsissueSkiprule);
	}

	@Override
	public FourTeamsIssueSkiprule getFourTeamsIssueSkipruleById(Long id) {
		FourTeamsIssueSkiprule fourTeamsIssueSkiprule = get(id);
		fourTeamsIssueSkiprule
				.setCcOrgNames(getCcOrgNamesByOrgIds(fourTeamsIssueSkiprule
						.getCcOrgIds()));
		return fourTeamsIssueSkiprule;
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
}
