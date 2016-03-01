package com.tianque.datatransfer.dataconvert;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.DataConvertionHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.systemLog.service.SystemLogService;
import com.tianque.core.systemLog.util.ModelType;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.domain.User;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.vladium.logging.Logger;

@Component("mobileUserDataConverter")
@Scope("prototype")
public class MobileUserDataConverter extends AbstractDataConverter<User> {
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	DataConvertionHelper convertHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	// 1不检查cell[1]是否为组织机构 2检查并验证存在与否

	public ValidateResult validate(String[] cellValues, int realRow,
			String[][] beanDatas, Organization headerOrg) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		ValidateResult result = new ValidateResult();
		if (validateHelper.emptyString(cellValues[2])) {
			result.addErrorMessage(realRow, 3, "用户姓名必须输入");
		} else if (validateHelper.illegalStringLength(2, 10, cellValues[2])) {
			result.addErrorMessage(realRow, 3, "用户姓名只能输入2-10个字符");
		}
		if (validateHelper.emptyString(cellValues[3])) {
			result.addErrorMessage(realRow, 4, "工作电话必须输入");
		}
		if (!validateHelper.emptyString(cellValues[3])) {
			if (validateHelper.illegalTelephone(cellValues[3])) {
				result.addErrorMessage(realRow, 4, "工作电话只能输入数字和-");
			}
			if (validateHelper.illegalStringLength(0, 13, cellValues[3])) {
				result.addErrorMessage(realRow, 4, "工作电话不能输入大于13字符");
			}
		}
		if (validateHelper.emptyString(cellValues[4])) {
			result.addErrorMessage(realRow, 5, "手机号码必须输入");
		}
		if (!validateHelper.emptyString(cellValues[4])) {
			if (validateHelper.illegalMobilePhone(cellValues[4])) {
				result.addErrorMessage(realRow, 5, "手机号码只能输入1开头的11位数字");
			}
		}
		if (validateHelper.emptyString(cellValues[9])
				&& validateHelper.emptyString(cellValues[10])) {
			result.addErrorMessage(realRow, 10, "是否手机可用和是否pc可用至少选择一个");
		}
		return result;
	}

	public User convertToDomain(String[] cellValues, ValidateResult result,
			String[][] beanDatas, Organization headerOrg) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		User mobileUser = new User();
		ExcelImportHelper.newProcImportDate(beanDatas, cellValues,
				getUploadOrganization(), mobileUser, result);

		mobileUser.setUserName(cellValues[0]);
		mobileUser.setPassword(cellValues[1]);
		mobileUser.setName(cellValues[2]);
		mobileUser.setMobile(cellValues[3]);

		mobileUser.setWorkPhone(cellValues[4]);
		mobileUser.setHomePhone(cellValues[5]);
		mobileUser.setWorkCompany(cellValues[6]);
		mobileUser.setImsi(cellValues[7]);
		mobileUser.setValidatorImsi(convertHelper
				.convertToBoolean(cellValues[8]));
		mobileUser.setChangePassword(convertHelper
				.convertToBoolean(cellValues[9]));
		mobileUser.setConnectVpdn(convertHelper
				.convertToBoolean(cellValues[10]));
		mobileUser.setPcusable(convertHelper.convertToBoolean(cellValues[11]));
		mobileUser.setAdmin(convertHelper.convertToBoolean(cellValues[12]));
		mobileUser.setFourthAccount(convertHelper
				.convertToBoolean(cellValues[13]));
		mobileUser.setGps(convertHelper.convertToBoolean(cellValues[14]));
		mobileUser.setFourTeams(convertHelper.convertToBoolean(cellValues[15]));
		// 默认值
		mobileUser.setOrganization(headerOrg);
		mobileUser.setOrgInternalCode(headerOrg.getOrgInternalCode());
		mobileUser.setIsFristWorkBench(true);
		mobileUser.setLock(true);
		mobileUser.setMobileusable(true);
		return mobileUser;
	}

	@Autowired
	private SystemLogService systemLogService;

	public SystemLogService getSystemLogService() {
		return systemLogService;
	}

	public void setSystemLogService(SystemLogService systemLogService) {
		this.systemLogService = systemLogService;
	}

	@Override
	public User persistenceDomain(User domain) {
		// domain.setChangePassword(true);
		User user = permissionService.addMobileUser(domain, true);
		systemLogService.log(
				Logger.INFO,
				ModelType.USER,
				OperatorType.IPORT,
				ThreadVariable.getSession().getUserName() + "导入手机账号用户"
						+ user.getUserName(), ObjectToJSON.convertJSON(domain));
		return user;
	}

	@Override
	public User updateDomain(User domain) {
		// User user = permissionService.updateUser(domain);
		// Long[] roleIds = new Long[getRoles().size()];
		// for (int i = 0; i < getRoles().size(); i++) {
		// roleIds[i] = getRoles().get(0).getId();
		// }
		// permissionService.updateUserRoleRela(user.getId(), roleIds);
		return null;
	}

	@Override
	public ValidateResult validate(User domain, int realRow) {
		ValidateResult result = new ValidateResult();
		if (validateHelper.emptyString(domain.getUserName())) {
			result.addErrorMessage(getColumNo("userName") + "用户名必须输入");
		} else if (validateHelper.illegalUserName(domain.getUserName())) {
			result.addErrorMessage(getColumNo("userName") + "用户名必须是数字,字母或下划线组成");
		}
		if (!validateHelper.emptyString(domain.getUserName())
				&& validateHelper.illegalMobileUserName(domain.getUserName())) {
			result.addErrorMessage(getColumNo("userName")
					+ "用户名格式错误，必须类似@gasg结尾！");
		}

		if (!validateHelper.emptyString(domain.getUserName())
				&& permissionService.findUserByMobileUserName(domain
						.getUserName()) != null) {
			result.addErrorMessage(getColumNo("userName") + "用户名已存在！");
		}
		if (validateHelper.emptyString(domain.getPassword())) {
			result.addErrorMessage(getColumNo("password") + "密码不能为空");
		} else if (validateHelper.illegalStringLength(6, 16,
				domain.getPassword())) {
			result.addErrorMessage(getColumNo("password") + "密码要在6-16为");
		}

		if (validateHelper.emptyString(domain.getName())) {
			result.addErrorMessage(getColumNo("name") + "用户姓名必须输入");
		} else if (validateHelper.illegalStringLength(2, 10, domain.getName())) {
			result.addErrorMessage(getColumNo("name") + "用户姓名只能输入2-10个字符");
		} else if (!validateHelper.isConSpeCharacters(domain.getName())) {
			result.addErrorMessage(getColumNo("name") + "用户姓名不能输入特殊字符");
		}
		if (validateHelper.emptyString(domain.getMobile())) {
			result.addErrorMessage(getColumNo("mobile") + "手机号码必须输入");
		}
		if (!validateHelper.emptyString(domain.getMobile())) {
			if (validateHelper.illegalMobilePhone(domain.getMobile())) {
				result.addErrorMessage(getColumNo("mobile")
						+ "手机号码只能输入1开头的11位数字");
			}
		}
		if (validateHelper.emptyString(domain.getWorkPhone())) {
			result.addErrorMessage(getColumNo("workPhone") + "工作电话必须输入");
		}
		if (!validateHelper.emptyString(domain.getWorkPhone())) {
			if (validateHelper.illegalTelephone(domain.getWorkPhone())) {
				result.addErrorMessage(getColumNo("workPhone") + "工作电话只能输入数字和-");
			}
			if (validateHelper
					.illegalStringLength(0, 13, domain.getWorkPhone())) {
				result.addErrorMessage(getColumNo("workPhone")
						+ "工作电话不能输入大于13字符");
			}
		}
		if (!validateHelper.emptyString(domain.getHomePhone())) {
			if (validateHelper.illegalTelephone(domain.getHomePhone())) {
				result.addErrorMessage(getColumNo("homePhone") + "住宅电话只能输入数字和-");
			}
			if (validateHelper
					.illegalStringLength(0, 13, domain.getHomePhone())) {
				result.addErrorMessage(getColumNo("homePhone")
						+ "住宅电话不能输入大于13字符");
			}
		}
		if (!validateHelper.emptyString(domain.getWorkCompany())) {
			if (validateHelper.illegalStringLength(0, 30,
					domain.getWorkCompany())) {
				result.addErrorMessage(getColumNo("workCompany")
						+ "工作单位不能输入大于13字符");
			}
		}
		if (domain.isValidatorImsi()) {
			if (validateHelper.emptyString(domain.getImsi())) {
				result.addErrorMessage(getColumNo("imsi")
						+ "是否验证IMSI码选择为是时 IMSI码必须输入");
			} else if (validateHelper.illegalMobileUserIMSI(domain.getImsi())) {
				result.addErrorMessage(getColumNo("imsi") + "IMSI码必须输入纯属数字");
			} else if (permissionService.findUserByImsi(domain.getImsi()) != null) {
				result.addErrorMessage(getColumNo("imsi") + "该IMSI码已存在");
			}
		}
		return result;
	}

	public String getColumNo(String key) {
		StringBuffer bf = new StringBuffer();
		if (!StringUtils.isEmpty(ExcelImportHelper.getDataColumMap(key))) {
			bf.append("第[").append(ExcelImportHelper.realRow.get())
					.append("]行");
			bf.append("第[")
					.append(Integer.valueOf(ExcelImportHelper
							.getDataColumMap(key)) + 1).append("]列");
		} else {
			bf.append("");
		}
		return bf.toString();
	}

	@Override
	public User convertToDomain(String[] cellValues, ValidateResult result) {
		// TODO Auto-generated method stub
		return null;
	}
}
