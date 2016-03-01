package com.tianque.qinYangIssue.validator;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.IssueType;
import com.tianque.domain.IssueTypeDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.issue.controller.strategy.impl.DefaultIssueManageStrategy;
import com.tianque.issue.vo.IssueTypeViewDefine;
import com.tianque.issueAbutmentJoint.constants.IssueJointTypeInternalId;
import com.tianque.plugin.dataManage.validate.AbstactDataManageValidator;
import com.tianque.qinYangIssue.domain.IssueJointTemp;
import com.tianque.service.IssueTypeDomainService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.sysadmin.service.PropertyDictService;

/**
 * @Description:青羊对接事件临时表验证类
 * @author zhangyouwei@hztianque.com
 * @date: 2015-1-20 下午10:19:09
 */
@Component("issueJointTempValidator")
public class IssueJointTempValidatorImpl extends
		AbstactDataManageValidator<IssueJointTemp> {

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private DefaultIssueManageStrategy strategy;
	@Autowired
	private IssueTypeDomainService issueTypeDomainService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private PropertyDictService propertyDictService;

	/**
	 * 临时事件表中各字段的验证
	 * 
	 * @param issueJointTemp
	 * @param vr
	 *            如果验证不通过，返回验证信息
	 * @return
	 */
	@Override
	public ValidateResult validate(IssueJointTemp issueJointTemp) {
		ValidateResult vr = new ValidateResult();
		/** 事件类型的验证 */
		validateIssueTypes(issueJointTemp, vr);
		/** 事件服务单号 */
		// validateIssueSerialnumber(issueJointTemp, vr);
		/** 事件主题 */
		validateIssueSubject(issueJointTemp, vr);
		/** 事件发生地点 */
		validateIssueOccurLocation(issueJointTemp, vr);
		/** 事件发生时间和创建时间 */
		validateIssueDate(issueJointTemp, vr);
		/** 事件当事人 */
		validateIssueMainCharacters(issueJointTemp, vr);
		/** 事件规模 */
		validateIssueKind(issueJointTemp, vr);
		/** 事件规模涉及人数 */
		validateIssueRelatePeopleCount(issueJointTemp, vr);
		/** 事件简述 */
		validateIssueContent(issueJointTemp, vr);
		/** 事件发生网格和创建网格 */
		validateIssueOccurOrgAndCreateorg(issueJointTemp, vr);
		/** 创建者 */
		validateCreateuser(issueJointTemp, vr);

		return vr;
	}

	private void validateCreateuser(IssueJointTemp issueJointTemp,
			ValidateResult vr) {
		if (!StringUtil.isStringAvaliable(vr.getErrorMessages())) {
			if (!StringUtil.isStringAvaliable(issueJointTemp.getCreateUser())) {
				vr.addErrorMessage("请为该事件填写创建者");
			}
			// else {
			// User user = permissionService.findUserByUserName(issueJointTemp
			// .getCreateUser());
			// if (user == null || user.getId() == null) {
			// vr.addErrorMessage("填写的事件创建者不存在!");
			// }
			// }
		}
	}

	private void validateIssueSubject(IssueJointTemp issueJointTemp,
			ValidateResult vr) {
		if (!StringUtil.isStringAvaliable(vr.getErrorMessages())) {
			if (!StringUtil.isStringAvaliable(issueJointTemp.getSubject())) {
				vr.addErrorMessage("请为该事件填写一个不小于2-50个字的主题!");
			} else if (issueJointTemp.getSubject().length() < 2
					|| issueJointTemp.getSubject().length() > 50) {
				vr.addErrorMessage("事件主题不能小于2个或大于50个字符!");
			}
			if (StringUtil.isStringAvaliable(issueJointTemp.getSubject())
					&& !legalTitleString(issueJointTemp.getSubject())) {
				vr.addErrorMessage("事件主题只能输入中英文、数字、引号、括号、空格、书名号、减号等字符!");
			}
		}
	}

	private void validateIssueOccurLocation(IssueJointTemp issueJointTemp,
			ValidateResult vr) {
		if (!StringUtil.isStringAvaliable(vr.getErrorMessages())) {
			if (!StringUtil
					.isStringAvaliable(issueJointTemp.getOccurLocation())) {
				vr.addErrorMessage("请为该事件填写一个事发地点!");
			} else if (issueJointTemp.getOccurLocation().length() > 50) {
				vr.addErrorMessage("事发地点不能大于50个字符!");
			}
			if (StringUtil.isStringAvaliable(issueJointTemp.getOccurLocation())
					&& !legalAddressString(issueJointTemp.getOccurLocation())) {
				vr.addErrorMessage("事发地点只能输入中英文、数字、括号、空格、减号、#号等字符!");
			}
		}
	}

	private void validateIssueDate(IssueJointTemp issueJointTemp,
			ValidateResult vr) {
		if (!StringUtil.isStringAvaliable(vr.getErrorMessages())) {
			if (issueJointTemp.getOccurDate() == null) {
				vr.addErrorMessage("请输该事件的发生时间!");
			} else if (CalendarUtil.now().before(issueJointTemp.getOccurDate())) {
				vr.addErrorMessage("事件发生时间不能大于当前时间!");
			}
			if (issueJointTemp.getCreateDate() == null) {
				vr.addErrorMessage("请输该事件的发生时间!");
			} else if (CalendarUtil.now()
					.before(issueJointTemp.getCreateDate())) {
				vr.addErrorMessage("事件发生时间不能大于当前时间!");
			}
		}
	}

	private void validateIssueMainCharacters(IssueJointTemp issueJointTemp,
			ValidateResult vr) {
		if (!StringUtil.isStringAvaliable(vr.getErrorMessages())) {
			if (issueJointTemp.getMaincharacters() != null) {
				if (issueJointTemp.getMaincharacters().length() > 30) {
					vr.addErrorMessage("主要当事人不能大于30个字符!");
				}
				if (!legalNamesString(issueJointTemp.getMaincharacters())) {
					vr.addErrorMessage("主要当事人中只能输入中英文、数字、逗号、顿号等字符!");
				}
			}
		}
	}

	private void validateIssueRelatePeopleCount(IssueJointTemp issueJointTemp,
			ValidateResult vr) {
		if (!StringUtil.isStringAvaliable(vr.getErrorMessages())) {
			if (issueJointTemp.getRelatePeopleCount() == null) {
				if (issueJointTemp.getIssueJointType() != null
						&& IssueJointTypeInternalId.issueJointTypeInternalIds
								.contains(propertyDictService
										.getPropertyDictByDomainName(
												issueJointTemp
														.getIssueJointType()
														.getDomainName())
										.getInternalId())) {
					vr.addErrorMessage("涉及人数不能为空");
				}

			} else {
				if (issueJointTemp.getRelatePeopleCount() < 0
						|| issueJointTemp.getRelatePeopleCount() > 999999) {
					vr.addErrorMessage("涉及人数只能输入1到999999之间的整数!");
				}
			}
		}
	}

	private void validateIssueContent(IssueJointTemp issueJointTemp,
			ValidateResult vr) {
		if (!StringUtil.isStringAvaliable(vr.getErrorMessages())) {
			if (!StringUtil.isStringAvaliable(issueJointTemp.getIssueContent())) {
				vr.addErrorMessage("请填写该事件的具体情况!");
			}
		}
	}

	// 判断事件类型，是否需要选择事件规模
	private void validateIssueKind(IssueJointTemp issueJointTemp,
			ValidateResult vr) {

		/** 突发性事件报告 矛盾劝解是必填其他情况冗余 */
		if (!StringUtil.isStringAvaliable(vr.getErrorMessages())) {
			if (issueJointTemp.getIssueKind() == null
					|| issueJointTemp.getIssueKind().getId() == null) {
				if (issueJointTemp.getIssueJointType() != null
						&& IssueJointTypeInternalId.issueJointTypeInternalIds
								.contains(propertyDictService
										.getPropertyDictByDomainName(
												issueJointTemp
														.getIssueJointType()
														.getDomainName())
										.getInternalId())) {
					vr.addErrorMessage("事件规模不能为空");
				}

			} else {
				PropertyDict issueKind = propertyDictService
						.getPropertyDictById(issueJointTemp.getIssueKind()
								.getId());
				if (validateHelper.illegalPropertyDictDisplayName(

				PropertyTypes.ISSUE_KIND, issueKind.getDisplayName())) {
					vr.addErrorMessage("事件规模输入不正确");
				}
			}
		}
	}

	// 发生网格和创建网格的验证
	private void validateIssueOccurOrgAndCreateorg(
			IssueJointTemp issueJointTemp, ValidateResult vr) {
		if (!StringUtil.isStringAvaliable(vr.getErrorMessages())) {
			// 创建网格的验证
			validateIssueOrg(issueJointTemp.getCreateOrg(),
					issueJointTemp.getCreateOrgInternalCode(), vr);
		}
		if (!StringUtil.isStringAvaliable(vr.getErrorMessages())) {
			// 发生网格的验证
			validateIssueOccueOrg(issueJointTemp,
					issueJointTemp.getOccurOrgInternalCode(), vr);
		}

	}

	private void validateIssueOccueOrg(IssueJointTemp issueJointTemp,
			String orgCode, ValidateResult vr) {
		if (!StringUtil.isStringAvaliable(vr.getErrorMessages())) {
			if (issueJointTemp.getOccurOrg() == null
					|| issueJointTemp.getOccurOrg().getId() == null) {
				vr.addErrorMessage("请为该事件填写一个正确的所属部门!");
			} else {
				Organization org = organizationDubboService
						.getFullOrgById(issueJointTemp.getOccurOrg().getId());
				Organization creatOrg = organizationDubboService
						.getFullOrgById(issueJointTemp.getCreateOrg().getId());
				if (OrganizationLevel.levelCompare(org.getOrgLevel()
						.getInternalId(), OrganizationLevel.TOWN) > 0
						|| OrganizationLevel.levelCompare(org.getOrgLevel()
								.getInternalId(), creatOrg.getOrgLevel()
								.getInternalId()) > 0) {
					vr.addErrorMessage("请为该事件填写一个正确的所属部门!");
				}
				if (!StringUtil.isStringAvaliable(orgCode)) {
					vr.addErrorMessage("请为该事件填写一个正确的所属部门的orgCode!");
				} else {
					if (org.getOrgInternalCode() == null
							|| !(orgCode.equals(org.getOrgInternalCode()))) {
						vr.addErrorMessage("请为该事件填写正确的orgCode!");
					}
				}
			}
		}
	}

	private void validateIssueOrg(Organization org, String orgCode,
			ValidateResult vr) {
		if (!StringUtil.isStringAvaliable(vr.getErrorMessages())) {
			if (org == null || org.getId() == null) {
				vr.addErrorMessage("请为该事件填写一个创建网格的所属部门!");
			} else {
				org = organizationDubboService.getFullOrgById(org.getId());
				if (!StringUtil.isStringAvaliable(orgCode)) {
					vr.addErrorMessage("请为该事件填写一个创建网格的所属部门的orgCode!");
				} else {
					if (org == null || org.getOrgInternalCode() == null
							|| !(orgCode.equals(org.getOrgInternalCode()))) {
						vr.addErrorMessage("请为该事件填写正确的orgCode!");
					}
				}
			}
		}
	}

	// 事件大小类型的验证
	private void validateIssueTypes(IssueJointTemp issueJointTemp,
			ValidateResult vr) {
		// 验证事件类型大类
		if (!StringUtil.isStringAvaliable(vr.getErrorMessages())) {
			List<IssueTypeViewDefine> issueTypeNames = strategy
					.getIssueTypeNames();
			if (issueJointTemp.getIssueJointType() == null
					|| issueJointTemp.getIssueJointType().getId() == null) {
				vr.addErrorMessage("事件大类不能为空");
			} else {
				IssueTypeDomain issueTypeDomain = issueTypeDomainService
						.getIssueTypeDoaminById(issueJointTemp
								.getIssueJointType().getId());
				if (!illegalPropertyDictDisplayName(issueTypeNames,
						issueTypeDomain.getDomainName())) {
					vr.addErrorMessage("事件大类填写不正确");
				} else {
					// 验证事件类型小类
					if (issueJointTemp.getIssueJointTypeSub() == null
							|| issueJointTemp.getIssueJointTypeSub().getId() == null) {
						vr.addErrorMessage("事件子类不能为空");
					} else {
						Map<String, List<IssueType>> issueTypes = strategy
								.loadEnabledIssueTypes(issueTypeNames);
						if (!illegalPropertyDictIssueJointTypeSub(issueTypes,
								issueJointTemp, issueTypeDomain.getDomainName())) {
							vr.addErrorMessage("事件子类输入不正确");
						}
					}
				}
			}
		}

	}

	private boolean legalTitleString(String value) {
		return value
				.replaceAll(
						"[\u4e00-\u9fa5]*\\w*-*_*\\s*）*”*“*’*‘*\"*'*《*》*（*\\(*\\)*",
						"").length() == 0;
	}

	private boolean legalAddressString(String value) {
		return value.replaceAll("[\u4e00-\u9fa5]*\\w*-*\\s*）*#*（*\\(*\\)*", "")
				.length() == 0;
	}

	private boolean legalNamesString(String value) {
		return value.replaceAll("[\u4e00-\u9fa5]*\\w*,*\\s*，*、*", "").length() == 0;
	}

	private boolean illegalPropertyDictDisplayName(
			List<IssueTypeViewDefine> issueTypeNames,
			String issueJointTempDomainName) {
		for (IssueTypeViewDefine issueTypeViewDefine : issueTypeNames) {
			if (issueTypeViewDefine.getTypeName().equals(
					issueJointTempDomainName)) {
				return true;
			}
			continue;
		}
		return false;
	}

	private boolean illegalPropertyDictIssueJointTypeSub(
			Map<String, List<IssueType>> issueTypes,
			IssueJointTemp issueJointTemp, String domainName) {
		List<IssueType> issueTypeList = issueTypes.get(domainName);
		for (IssueType IssueType : issueTypeList) {
			if (IssueType.getId().equals(
					issueJointTemp.getIssueJointTypeSub().getId())) {
				return true;
			}
			continue;
		}
		return false;
	}

	private void validateIssueSerialnumber(IssueJointTemp issueJointTemp,
			ValidateResult vr) {
		if (!StringUtil.isStringAvaliable(vr.getErrorMessages())) {
			if (!StringUtil.isStringAvaliable(issueJointTemp.getSerialNumber())) {
				vr.addErrorMessage("请为该事件填写服务单号!");
			} else if (issueJointTemp.getSerialNumber().length() > 30) {
				vr.addErrorMessage("事件服务单号不能大于30个字符!");
			}
			if (StringUtil.isStringAvaliable(issueJointTemp.getSerialNumber())
					&& !legalTitleString(issueJointTemp.getSerialNumber())) {
				vr.addErrorMessage("事件服务单号只能输入中英文、数字、引号、括号、空格、书名号、减号等字符!");
			}
		}
	}

	@Override
	public ValidateResult validateSpecializedInfo(IssueJointTemp domain) {
		// TODO Auto-generated method stub
		return null;
	}

}
