package com.tianque.fourTeams.fourTeamsManage.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.ValidateResult;
import com.tianque.fourTeams.fourTeamsManage.domain.FourTeamMembers;
import com.tianque.fourTeams.fourTeamsManage.service.FourTeamsService;
import com.tianque.plugin.dataManage.validate.AbstactDataManageValidator;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("fourTeamMemberValidatorImpl")
public class FourTeamMemberValidatorImpl extends
		AbstactDataManageValidator<FourTeamMembers> {

	@Autowired
	private ValidateHelper validateHelper;

	@Autowired
	private FourTeamsService fourTeamsService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public ValidateResult validate(FourTeamMembers domain) {
		ValidateResult result = new ValidateResult();

		/***
		 * 验证出生日期格式
		 */
		if (domain.getBirthday() != null
				&& domain.getBirthday().after(new Date())) {
			result.addErrorMessage(getColumNo("birthday") + "出生日期不能大于今天");
		}

		/** 验证服务网格名称 */
		if (domain.getServiceName() == null
				|| domain.getServiceName().trim().length() == 0) {
			result.addErrorMessage(getColumNo("serviceName") + "成员服务网格不能为空");
		} else if (validateHelper.illegalStringLength(2, 30,
				domain.getServiceName())) {
			result.addErrorMessage(getColumNo("serviceName")
					+ "成员服务网格不能小于2个字符，不能大于30个字符");

		}

		/** 验证成员名称 **/
		if (validateHelper.emptyString(domain.getNames())) {
			result.addErrorMessage(getColumNo("names") + "成员名称必填");
		} else if (validateHelper.illegalStringLength(2, 10, domain.getNames())) {
			result.addErrorMessage(getColumNo("names")
					+ "成员名称不能小于2个字符，不能大于32个字符!");
		}
		/** 验证成员性别 **/
		if (validateHelper.nullObj(domain.getGender())) {
			result.addErrorMessage(getColumNo("gender") + "成员性别必填");
		}
		/** 验证成员政治面貌 **/
		if (validateHelper.nullObj(domain.getPolitics())) {
			result.addErrorMessage(getColumNo("politics") + "成员政治面貌必填");
		}
		/** 验证成员职务 **/
		if (!validateHelper.emptyString(domain.getDuty())) {
			if (validateHelper.illegalStringLength(0, 32, domain.getDuty())) {
				result.addErrorMessage(getColumNo("duty") + "成员职务不能大于32个字符!");
			}
		}
		/** 验证成员特长 **/
		if (!validateHelper.emptyString(domain.getSpecialty())) {
			if (validateHelper
					.illegalStringLength(0, 20, domain.getSpecialty())) {
				result.addErrorMessage("成员特长不能大于20个字符!");
				result.addErrorMessage(getColumNo("specialty") + "特长不能大于20个字符!");
			}
		}
		/** 验证成员联系手机 **/
		if (!validateHelper.emptyString(domain.getMobile())) {
			if (validateHelper.illegalMobilePhone(domain.getMobile())) {
				result.addErrorMessage(getColumNo("mobile")
						+ "成员联系手机格式有误，只能输入1开头的11位数字");
			}
		}
		/** 验证成员联系电话 **/
		if (!validateHelper.emptyString(domain.getTelephone())) {
			if (validateHelper.illegalTelephone(domain.getTelephone())) {
				result.addErrorMessage(getColumNo("telephone")
						+ "成员联系电话不合法，只能输数字和横杠(-)");
			}
			// 长度验证
			if (validateHelper
					.illegalStringLength(0, 20, domain.getTelephone())) {
				result.addErrorMessage(getColumNo("telephone")
						+ "电话号码不能大于20个字符");
			}
		}
		/** 验证成员网格管理员姓名 **/
		if (!validateHelper.emptyString(domain.getOrgAdminName())) {
			if (validateHelper.illegalStringLength(0, 6,
					domain.getOrgAdminName())) {
				result.addErrorMessage(getColumNo("orgAdminName")
						+ "网格管理员姓名不能超过6个字符!");
			}
		}
		/** 网格管理员联系电话 **/
		if (!validateHelper.emptyString(domain.getOrgAdminTelephone())) {
			if (validateHelper.illegalTelephone(domain.getOrgAdminTelephone())) {
				result.addErrorMessage(getColumNo("orgAdminTelephone")
						+ "成员联系电话不合法，只能输数字和横杠(-)");
			}
			// 长度验证
			if (validateHelper.illegalStringLength(0, 20,
					domain.getOrgAdminTelephone())) {
				result.addErrorMessage(getColumNo("orgAdminTelephone")
						+ "电话号码不能大于20个字符");
			}
		}
		/** 备注 **/
		if (!validateHelper.emptyString(domain.getComments())) {
			if (validateHelper
					.illegalStringLength(0, 100, domain.getComments())) {
				result.addErrorMessage(getColumNo("comments") + "备注不能超过300个字符!");
			}
		}

		/** 所在单位验证 */
		if (!validateHelper.emptyString(domain.getMemberDepartement())) {
			if (validateHelper.illegalStringLength(0, 100,
					domain.getMemberDepartement())) {
				result.addErrorMessage(getColumNo("memberDepartement")
						+ "备注不能超过300个字符!");
			}
		}

		return result;
	}

	@Override
	public ValidateResult validateSpecializedInfo(FourTeamMembers domain) {
		return null;
	}
}
