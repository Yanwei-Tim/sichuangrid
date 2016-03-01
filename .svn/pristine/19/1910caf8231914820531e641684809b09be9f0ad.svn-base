package com.tianque.datatransfer.dataconvert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.UserImportHelper;
import com.tianque.core.datatransfer.dataconvert.DataConvertionHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.systemLog.service.SystemLogService;
import com.tianque.core.systemLog.util.ModelType;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.domain.Role;
import com.tianque.domain.User;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.vladium.logging.Logger;

@Component("userDataConverter")
@Scope("prototype")
public class UserDataConverter extends AbstractDataConverter<User> {

	@Autowired
	private PermissionService permissionService;
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	DataConvertionHelper convertHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	
	/***
	 * 区县以下用户导入验证
	 */
	public ValidateResult validateForDisprit(String[] cellValues, int realRow) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		ValidateResult result = new ValidateResult();
		Organization currentOrg = getUploadOrganization();
		
		String discript = cellValues[1];
		String village = cellValues[2];
		String grid = cellValues[3];
		if(StringUtil.isStringAvaliable(grid)){
			if(!StringUtil.isStringAvaliable(village) || !StringUtil.isStringAvaliable(discript)){
				result.addErrorMessage(realRow, 3, "网格不为空时必须填写社区和乡镇");
			}else{
				List<Organization> orgList= organizationDubboService.findOrganizationsByOrgNameAndParentId(null, discript, currentOrg.getId());
				Organization discriptOrg = (orgList != null && orgList.size() == 1) ? orgList.get(0) : null;
				if(discriptOrg==null){
					result.addErrorMessage(realRow, 3, "乡镇组织机构未找到,请确认后重新填写");
				}
				orgList= organizationDubboService.findOrganizationsByOrgNameAndParentId(null, village, discriptOrg.getId());
				Organization villageOrg =  (orgList != null && orgList.size() == 1) ? orgList.get(0) : null;
				if(villageOrg==null){
					result.addErrorMessage(realRow, 3, "社区(村)组织机构未找到,请确认后重新填写");
				}
				orgList= organizationDubboService.findOrganizationsByOrgNameAndParentId(null, grid, villageOrg.getId());
				Organization gridOrg = (orgList != null && orgList.size() == 1) ? orgList.get(0) : null;
				if(gridOrg==null){
					result.addErrorMessage(realRow, 3, "网格组织机构未找到,请确认后重新填写");
				}
				//通过网格、社区、乡镇名称获得组织机构线
				currentOrg = gridOrg;
			}
		}else if(StringUtil.isStringAvaliable(village)){
			if(!StringUtil.isStringAvaliable(discript)){
				result.addErrorMessage(realRow, 3, "社区村不为空时，必须输入乡镇");
			}else{
				//通过社区、乡镇名称获得组织机构线
				List<Organization> orgList= organizationDubboService.findOrganizationsByOrgNameAndParentId(null, discript, currentOrg.getId());
				Organization discriptOrg = (orgList != null && orgList.size() == 1) ? orgList.get(0) : null;
				if(discriptOrg==null){
					result.addErrorMessage(realRow, 3, "乡镇组织机构未找到,请确认后重新填写");
				}
				orgList= organizationDubboService.findOrganizationsByOrgNameAndParentId(null, village, discriptOrg.getId());
				Organization villageOrg =  (orgList != null && orgList.size() == 1) ? orgList.get(0) : null;
				if(villageOrg==null){
					result.addErrorMessage(realRow, 3, "社区(村)组织机构未找到,请确认后重新填写");
				}
				currentOrg = villageOrg;
			}
		}else if(StringUtil.isStringAvaliable(discript)){
			//通过乡镇名称和currentOrg获取组织机构信息
			List<Organization> orgList= organizationDubboService.findOrganizationsByOrgNameAndParentId(null, discript, currentOrg.getId());
			Organization discriptOrg = (orgList != null && orgList.size() == 1) ? orgList.get(0) : null;
			if(discriptOrg==null){
				result.addErrorMessage(realRow, 3, "乡镇组织机构未找到,请确认后重新填写");
			}
			currentOrg = discriptOrg;
		}
		
		if(currentOrg==null || currentOrg.getId()==null){
			result.addErrorMessage(realRow, 1, "组织机构信息错误");
		}
		if (null != currentOrg) {
			Role role = permissionService.findRoleByRoleNameAndUserInLevel(
					cellValues[0], currentOrg.getOrgLevel().getId());
			if (role == null) {
				result.addErrorMessage(realRow, 2, "系统中不存在此岗位");
			}
		}
		
		if (validateHelper.emptyString(cellValues[4])) {
			result.addErrorMessage(realRow, 4, "用户姓名必须输入");
		} else if (validateHelper.illegalStringLength(2, 10, cellValues[4])) {
			result.addErrorMessage(realRow, 4, "用户姓名只能输入2-10个字符");
		}
		
		if (validateHelper.emptyString(cellValues[5])) {
			result.addErrorMessage(realRow, 5, "工作电话必须输入");
		}
		if (!validateHelper.emptyString(cellValues[5])) {
			if (validateHelper.illegalTelephone(cellValues[5])) {
				result.addErrorMessage(realRow, 5, "工作电话只能输入数字和-");
			}
			if (validateHelper.illegalStringLength(0, 13, cellValues[5])) {
				result.addErrorMessage(realRow, 5, "工作电话不能输入大于13字符");
			}
		}
		if (validateHelper.emptyString(cellValues[6])) {
			result.addErrorMessage(realRow, 6, "手机号码必须输入");
		}
		if (!validateHelper.emptyString(cellValues[6])) {
			if (validateHelper.illegalMobilePhone(cellValues[6])) {
				result.addErrorMessage(realRow, 6, "手机号码只能输入1开头的11位数字");
			}
		}
		String userName = cellValues[7];
		if (validateHelper.emptyString(userName)) {
			result.addErrorMessage(realRow, 7, "用户名不能为空");
		} else {
			if (validateHelper.illegalUserName(userName)) {
				result.addErrorMessage(realRow, 7, "用户名必须是数字,字母或下划线组成");
			}
			if (validateHelper.illegalStringLength(1, 20, userName)) {
				result.addErrorMessage(realRow, 7, "用户名长度最大20位");
			}
		}
		if (validateHelper.emptyString(cellValues[8])
				&& validateHelper.emptyString(cellValues[9])) {
			result.addErrorMessage(realRow, 8, "是否手机可用和是否pc可用至少选择一个");
		}
		if ("是".equals(cellValues[12])) {
			if (validateHelper.emptyString(cellValues[10])) {
				result.addErrorMessage(realRow, 10, "是否验证IMSI码选择为是时 IMSI码必须输入");
			} else if (validateHelper.illegalMobileUserIMSI(cellValues[10])) {
				result.addErrorMessage(realRow, 10, "IMSI码必须输入纯属数字");
			} else if (permissionService.findUserByImsi(cellValues[10]) != null) {
				result.addErrorMessage(realRow, 10, "该IMSI码已存在");
			}
		}
		return result;
	}

	// 1不检查cell[1]是否为组织机构 2检查并验证存在与否

	@Override
	public ValidateResult validate(String[] cellValues, int realRow) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		ValidateResult result = new ValidateResult();
		Organization currentOrg = null;
		if (getCheckOrgOrNot() == UserImportHelper.OTHER
				&& validateHelper.emptyString(cellValues[1])) {
			result.addErrorMessage(realRow, 2, "单位或村（社区）名称必须输入");
		} else if (getCheckOrgOrNot() == UserImportHelper.OTHER
				&& !validateHelper.illegalOrganizationName(
						getUploadOrganization(), cellValues[1])) {
			currentOrg = convertHelper.convertToOrganization(
					getUploadOrganization(), cellValues[1]);
		}
		if (getCheckOrgOrNot() != UserImportHelper.OTHER) {
			currentOrg = getUploadOrganization();
		}
		if (getCheckOrgOrNot() == UserImportHelper.OTHER
				&& !validateHelper.emptyString(cellValues[1])) {
			if (null == currentOrg) {
				result.addErrorMessage(realRow, 2,
						"单位或村（社区）名称输入不正确。请确保系统中有该单位或村（社区）");
			}
		}
		if (null != currentOrg) {
			Role role = permissionService.findRoleByRoleNameAndUserInLevel(
					cellValues[0], currentOrg.getOrgLevel().getId());
			if (role == null) {
				result.addErrorMessage(realRow, 1, "系统中不存在此岗位");
			}
		}

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
		String userName = cellValues[5];
		if (validateHelper.emptyString(userName)) {
			result.addErrorMessage(realRow, 6, "用户名不能为空");
		} else {
			if (validateHelper.illegalUserName(userName)) {
				result.addErrorMessage(realRow, 6, "用户名必须是数字,字母或下划线组成");
			}
			if (validateHelper.illegalStringLength(1, 20, userName)) {
				result.addErrorMessage(realRow, 6, "用户名长度最大20位");
			}
		}
		if (validateHelper.emptyString(cellValues[6])) {
			result.addErrorMessage(realRow, 7, "密码不能为空");
		} else if (validateHelper.illegalStringLength(6, 16, cellValues[6])) {
			result.addErrorMessage(realRow, 7, "密码要在6-16为");
		}
		if (validateHelper.emptyString(cellValues[9])
				&& validateHelper.emptyString(cellValues[10])) {
			result.addErrorMessage(realRow, 10, "是否手机可用和是否pc可用至少选择一个");
		}
		if ("是".equals(cellValues[12])) {
			if (validateHelper.emptyString(cellValues[11])) {
				result.addErrorMessage(realRow, 12, "是否验证IMSI码选择为是时 IMSI码必须输入");
			} else if (validateHelper.illegalMobileUserIMSI(cellValues[11])) {
				result.addErrorMessage(realRow, 12, "IMSI码必须输入纯属数字");
			} else if (permissionService.findUserByImsi(cellValues[11]) != null) {
				result.addErrorMessage(realRow, 12, "该IMSI码已存在");
			}
		}
		return result;
	}

	@Override
	public User convertToDomain(String[] cellValues) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		Organization org = getUploadOrganization();
		List<Role> roles = new ArrayList<Role>();
		User result = new User();
		result.setName(cellValues[2]);
		result.setWorkPhone(cellValues[3]);
		result.setMobile(cellValues[4]);
		result.setUserName(cellValues[5]);
		result.setPassword(cellValues[6]);
		result.setPcusable(convertHelper.convertToBoolean(cellValues[9]));
		result.setMobileusable(convertHelper.convertToBoolean(cellValues[10]));
		// 新增的三个字段
		result.setImsi(cellValues[11]);
		result.setValidatorImsi(convertHelper.convertToBoolean(cellValues[12]));
		result.setFourthAccount(convertHelper.convertToBoolean(cellValues[13]));
		result.setGps(convertHelper.convertToBoolean(cellValues[14]));
		result.setFourTeams(convertHelper.convertToBoolean(cellValues[15]));

		if (getCheckOrgOrNot() == 2) {
			org = convertHelper.convertToOrganization(getUploadOrganization(),
					cellValues[1]);
		}
		roles.add(permissionService.findRoleByRoleNameAndUserInLevel(
				cellValues[0], org.getOrgLevel().getId()));
		result.setOrganization(org);
		result.setRoles(roles);
		return result;
	}
	
	public User convertToDomainForNewUser(String[] cellValues) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		Organization org = getUploadOrganization();
		String discript = cellValues[1];
		String village = cellValues[2];
		String grid = cellValues[3];
		if(StringUtil.isStringAvaliable(grid)){
				List<Organization> orgList= organizationDubboService.findOrganizationsByOrgNameAndParentId(null, discript, org.getId());
				Organization discriptOrg = (orgList != null && orgList.size() == 1) ? orgList.get(0) : null;
				orgList= organizationDubboService.findOrganizationsByOrgNameAndParentId(null, village, discriptOrg.getId());
				Organization villageOrg =  (orgList != null && orgList.size() == 1) ? orgList.get(0) : null;
				orgList= organizationDubboService.findOrganizationsByOrgNameAndParentId(null, grid, villageOrg.getId());
				Organization gridOrg = (orgList != null && orgList.size() == 1) ? orgList.get(0) : null;
				//通过网格、社区、乡镇名称获得组织机构线
				org = gridOrg;
			
		}else if(StringUtil.isStringAvaliable(village)){
				//通过社区、乡镇名称获得组织机构线
				List<Organization> orgList= organizationDubboService.findOrganizationsByOrgNameAndParentId(null, discript, org.getId());
				Organization discriptOrg = (orgList != null && orgList.size() == 1) ? orgList.get(0) : null;
				orgList= organizationDubboService.findOrganizationsByOrgNameAndParentId(null, village, discriptOrg.getId());
				Organization villageOrg =  (orgList != null && orgList.size() == 1) ? orgList.get(0) : null;
				org = villageOrg;
		}else if(StringUtil.isStringAvaliable(discript)){
			//通过乡镇名称和currentOrg获取组织机构信息
			List<Organization> orgList= organizationDubboService.findOrganizationsByOrgNameAndParentId(null, discript, org.getId());
			Organization discriptOrg = (orgList != null && orgList.size() == 1) ? orgList.get(0) : null;
			org = discriptOrg;
		}
		
		List<Role> roles = new ArrayList<Role>();
		User result = new User();
		result.setName(cellValues[4]);
		result.setWorkPhone(cellValues[5]);
		result.setMobile(cellValues[6]);
		result.setUserName(cellValues[7]);
		result.setPassword(UserImportHelper.DEFAULT_PASSWORD);
		result.setPcusable(convertHelper.convertToBoolean(cellValues[8]));
		result.setMobileusable(convertHelper.convertToBoolean(cellValues[9]));
		// 新增的三个字段
		result.setImsi(cellValues[10]);
		result.setValidatorImsi(convertHelper.convertToBoolean(cellValues[11]));
		result.setFourthAccount(convertHelper.convertToBoolean(cellValues[12]));
		result.setGps(convertHelper.convertToBoolean(cellValues[13]));
		result.setFourTeams(convertHelper.convertToBoolean(cellValues[14]));

		if (getCheckOrgOrNot() == 2) {
			org = convertHelper.convertToOrganization(getUploadOrganization(),
					cellValues[1]);
		}
		roles.add(permissionService.findRoleByRoleNameAndUserInLevel(
				cellValues[0], org.getOrgLevel().getId()));
		result.setOrganization(org);
		result.setRoles(roles);
		return result;
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
		domain.setChangePassword(true);
		if(!StringUtil.isStringAvaliable(domain.getPassword())){//设置默认密码
			domain.setPassword(UserImportHelper.DEFAULT_PASSWORD);
		}
		User user = permissionService.addUser(domain);
		permissionService.addUserRoleRela(user.getId(), domain.getRoles()
				.get(0).getId());
		systemLogService
				.log(Logger.INFO,
						ModelType.USER,
						OperatorType.IPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												user.getOrganization().getId(),
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + "导入用户"
								+ user.getUserName(), ObjectToJSON
								.convertJSON(domain));
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
		return null;
	}

	@Override
	public User convertToDomain(String[] cellValues, ValidateResult result) {
		// TODO Auto-generated method stub
		return null;
	}
}
