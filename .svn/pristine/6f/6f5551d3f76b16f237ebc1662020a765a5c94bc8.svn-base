package com.tianque.issueAbutmentJoint.validator;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.issueAbutmentJoint.constants.IssueJointTypeInternalId;
import com.tianque.issueAbutmentJoint.domain.IssueJoint;
import com.tianque.plugin.dataManage.validate.AbstactDataManageValidator;

@Component("issueJointValidator")
public class IssueJointValidatorImpl extends
		AbstactDataManageValidator<IssueJoint> {

	@Autowired
	private ValidateHelper validateHelper;

	@Override
	public ValidateResult validate(IssueJoint domain) {
		ValidateResult result = new ValidateResult();

		if (domain.getOccurOrg() != null
				&& domain.getOccurOrg().getId() != null
				&& ThreadVariable.getUser().getOrganization().getId() != null) {
			if (!validateHelper.isUbordinateRelationship(domain.getOccurOrg()
					.getId(), ThreadVariable.getUser().getOrganization()
					.getId())) {
				result.addErrorMessage(getColumNo("occurOrg")
						+ "事件发生单位或网格超出您管辖范围");
			}

		}
		if (domain.getIssueJointType() == null
				|| domain.getIssueJointType().getId() == null) {
			result.addErrorMessage(getColumNo("issueJointType") + "事件大类不能为空");
		} else if (validateHelper.illegalPropertyDictDisplayName(
				PropertyTypes.ISSUE_JOINT_TYPE, domain.getIssueJointType()
						.getDisplayName())) {
			result.addErrorMessage(getColumNo("issueJointType") + "事件大类输入不正确");
		}
		if (domain.getIssueJointTypeSub() == null
				|| domain.getIssueJointTypeSub().getId() == null) {
			result.addErrorMessage(getColumNo("issueJointTypeSub") + "事件子类不能为空");
		} else if (validateHelper.illegalPropertyDictDisplayName(
				PropertyTypes.ISSUE_JOINT_TYPE_SUB, domain
						.getIssueJointTypeSub().getDisplayName())) {
			result.addErrorMessage(getColumNo("issueJointTypeSub")
					+ "事件子类输入不正确");
		} else if (domain.getIssueJointType() != null
				&& domain.getIssueJointType().getInternalId() != domain
						.getIssueJointTypeSub().getInternalId()) {
			result.addErrorMessage(getColumNo("issueJointTypeSub")
					+ "事件子类必须和事件大类对应");
		}

		if (validateHelper.emptyString(domain.getSubject())) {
			result.addErrorMessage(getColumNo("subject") + "事件名称不能为空");
		} else if (validateHelper.illegalStringLength(0, 50,
				domain.getSubject())) {
			result.addErrorMessage(getColumNo("subject") + "事件名称不能输入大于50字符");
		} else if (!validateHelper.isConSpeCharacters(domain.getSubject())) {
			result.addErrorMessage(getColumNo("subject") + "事件名称不能含有特殊字符");
		}

		if (domain.getOccurDate() == null) {
			result.addErrorMessage(getColumNo("occurDate") + "事件发生时间不能为空");
		} else if (domain.getOccurDate().after(new Date())) {
			result.addErrorMessage(getColumNo("occurDate") + "事件发生时间不能大于今天");
		}

		if (!validateHelper.emptyString(domain.getHours())
				&& validateHelper.illegalHour(domain.getHours())) {
			result.addErrorMessage(getColumNo("hours") + "事件发生时间小时不正确");
		}

		if (!validateHelper.emptyString(domain.getMinute())
				&& validateHelper.illegalMinute(domain.getMinute())) {
			result.addErrorMessage(getColumNo("minute") + "事件发生时间分不正确");
		}

		if (!validateHelper.emptyString(domain.getOccurLocation())
				&& validateHelper.illegalStringLength(0, 50,
						domain.getOccurLocation())) {
			result.addErrorMessage(getColumNo("occurLocation")
					+ "发生地点不能输入大于50字符");
		}

		if (validateHelper.emptyString(domain.getMaincharacters())) {
			result.addErrorMessage(getColumNo("maincharacters") + "主要当事人姓名不能为空");
		} else if (validateHelper.illegalStringLength(0, 20,
				domain.getMaincharacters())) {
			result.addErrorMessage(getColumNo("maincharacters")
					+ "主要当事人姓名不能输入大于20字符");
		} else if (!validateHelper.isConSpeCharacters(domain
				.getMaincharacters())) {
			result.addErrorMessage(getColumNo("maincharacters")
					+ "主要当事人姓名不能含有特殊字符");
		}

		if (!validateHelper.emptyString(domain.getTelephone())
				&& validateHelper.illegalStringLength(0, 20,
						domain.getTelephone())) {
			result.addErrorMessage(getColumNo("telephone")
					+ "主要当事人固话不能输入大于20字符");
		} else if (!validateHelper.emptyString(domain.getTelephone())
				&& validateHelper.illegalTelephone(domain.getTelephone())) {
			result.addErrorMessage(getColumNo("telephone") + "主要当事人固话错误");
		}

		if (!validateHelper.emptyString(domain.getMobile())
				&& validateHelper
						.illegalStringLength(0, 20, domain.getMobile())) {
			result.addErrorMessage(getColumNo("mobile") + "主要当事人联系手机不能输入大于20字符");
		} else if (!validateHelper.emptyString(domain.getMobile())
				&& validateHelper.illegalMobilePhone(domain.getMobile())) {
			result.addErrorMessage(getColumNo("mobile") + "主要当事人联系手机错误");
		}

		/** 突发性事件报告 矛盾劝解是必填其他情况冗余 */
		if (domain.getIssueKind() == null
				|| domain.getIssueKind().getId() == null) {
			if (domain.getIssueJointType() != null
					&& IssueJointTypeInternalId.issueJointTypeInternalIds
							.contains(domain.getIssueJointType()
									.getInternalId())) {
				result.addErrorMessage(getColumNo("issueKind") + "事件规模不能为空");
			}

		} else if (validateHelper.illegalPropertyDictDisplayName(
				PropertyTypes.ISSUE_KIND, domain.getIssueKind()
						.getDisplayName())) {
			result.addErrorMessage(getColumNo("issueKind") + "事件规模输入不正确");
		}

		/** 突发性事件报告 矛盾劝解是必填其他情况冗余 */
		if (domain.getRelatePeopleCount() == null) {
			if (domain.getIssueJointType() != null
					&& IssueJointTypeInternalId.issueJointTypeInternalIds
							.contains(domain.getIssueJointType()
									.getInternalId())) {
				result.addErrorMessage(getColumNo("relatePeopleCount")
						+ "涉及人数不能为空");
			}

		}

		if (validateHelper.emptyString(domain.getIssueContent())) {
			result.addErrorMessage(getColumNo("issueContent") + "事件简述不能为空");
		} else if (validateHelper.illegalStringLength(0, 600,
				domain.getIssueContent())) {
			result.addErrorMessage(getColumNo("issueContent")
					+ "事件简述不能输入大于600字符");
		}

		if (domain.getFeedbackTime() == null) {
			result.addErrorMessage(getColumNo("feedbackTime") + "反馈时间不能为空");
		} else if (domain.getFeedbackTime().after(new Date())) {
			result.addErrorMessage(getColumNo("feedbackTime") + "反馈时间不能大于今天");
		} else if (domain.getOccurDate() != null
				&& domain.getFeedbackTime().before(domain.getOccurDate())) {
			result.addErrorMessage(getColumNo("feedbackTime") + "反馈时间不能小于发生时间");
		}

		if (domain.getLastDealTime() == null) {
			result.addErrorMessage(getColumNo("lastDealTime") + "最后处理时间不能为空");
		} else if (domain.getLastDealTime().after(new Date())) {
			result.addErrorMessage(getColumNo("lastDealTime") + "最后处理时间不能大于今天");
		} else if (domain.getFeedbackTime().after(domain.getLastDealTime())) {
			result.addErrorMessage(getColumNo("lastDealTime")
					+ "反馈时间不能大于最后处理时间");
		}

		if (validateHelper.emptyString(domain.getDealUserName())) {
			result.addErrorMessage(getColumNo("dealUserName") + "承办人不能为空");
		} else if (validateHelper.illegalStringLength(0, 20,
				domain.getDealUserName())) {
			result.addErrorMessage(getColumNo("dealUserName") + "承办人不能输入大于20字符");
		}
		if (!validateHelper.isConSpeCharacters(domain.getDealUserName())) {
			result.addErrorMessage(getColumNo("dealUserName") + "承办人不能含有特殊字符");
		}

		if (validateHelper.emptyString(domain.getDealMobile())) {
			result.addErrorMessage(getColumNo("dealMobile") + "承办人手机不能为空");
		} else if (validateHelper.illegalStringLength(0, 20,
				domain.getDealMobile())) {
			result.addErrorMessage(getColumNo("dealMobile") + "承办人手机不能输入大于20字符");
		} else if (validateHelper.illegalMobilePhone(domain.getDealMobile())) {
			result.addErrorMessage(getColumNo("dealMobile") + "承办人手机错误");
		}

		if (validateHelper.emptyString(domain.getDealContent())) {
			result.addErrorMessage(getColumNo("dealContent") + "处理意见不能为空");
		} else if (validateHelper.illegalStringLength(0, 600,
				domain.getDealContent())) {
			result.addErrorMessage(getColumNo("dealContent")
					+ "处理意见不能输入大于600字符");
		}

		return result;
	}

	@Override
	public ValidateResult validateSpecializedInfo(IssueJoint domain) {
		return null;
	}

}
