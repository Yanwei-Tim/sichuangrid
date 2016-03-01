package com.tianque.issue.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.IssueType;
import com.tianque.domain.IssueTypeDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.vo.IssueTypeVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.issue.constants.IssueTypes;
import com.tianque.issue.domain.AdministrativeOrgTimeLimitStandard;
import com.tianque.issue.service.AdministrativeOrgTimeLimitStandardService;
import com.tianque.service.IssueTypeDomainService;
import com.tianque.service.IssueTypeService;
import com.tianque.sysadmin.service.PropertyDictService;

/**
 * @说明：行政部门时限标准控制类
 * @author 龙振东
 * 
 */
@Transactional
@Controller("administrativeOrgTimeLimitStandardController")
@Scope("prototype")
@Namespace("/administrativeOrgTimeLimitStandardManage")
public class AdministrativeOrgTimeLimitStandardController extends BaseAction {
	private static Logger logger = LoggerFactory
			.getLogger(AdministrativeOrgTimeLimitStandardController.class);

	@Autowired
	private AdministrativeOrgTimeLimitStandardService administrativeOrgTimeLimitStandardService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private IssueTypeDomainService issueTypeDomainService;
	@Autowired
	private IssueTypeService issueTypeService;

	private Organization userOrganization;
	private AdministrativeOrgTimeLimitStandard administrativeOrgTimeLimitStandard;
	private String selectedIds;
	private Boolean deleteState;

	private List<Object> assembleValues;// 组装前台需要的事件类型数据

	@Action(value = "findAdministrativeOrgTimeLimitStandardList", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findAdministrativeOrgTimeLimitStandardList() throws Exception {
		gridPage = new GridPage(
				administrativeOrgTimeLimitStandardService
						.findAdministrativeOrgTimeLimitStandardByUserOrganization(
								userOrganization, page, rows, sidx, sord));
		return SUCCESS;
	}

	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/issue/issueAccessConfig/maintainAdministrativeOrgTimeLimitStandardDlg.jsp"),
			@Result(name = "edit", location = "/issue/issueAccessConfig/maintainAdministrativeOrgTimeLimitStandardDlgForEdit.jsp"),
			@Result(name = "editDefault", location = "/issue/issueAccessConfig/maintainAdministrativeOrgTimeLimitStandardDlgForEditDefault.jsp") })
	public String dispatch() throws Exception {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			prepareAdd();
			assembleValuesMothed();
			return SUCCESS;
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)
				|| DialogMode.VIEW1_MODE.equalsIgnoreCase(mode)) {
			administrativeOrgTimeLimitStandard = administrativeOrgTimeLimitStandardService
					.getAdministrativeOrgTimeLimitStandardById(administrativeOrgTimeLimitStandard
							.getId());
			return DialogMode.VIEW1_MODE.equalsIgnoreCase(mode) ? "editDefault"
					: DialogMode.EDIT_MODE;
		}
		return SUCCESS;
	}

	private void assembleValuesMothed() {
		String bigType[] = { IssueTypes.PEOPLELIVE_SERVICE,
				IssueTypes.RESOLVETHECONTRADICTIONS,
				IssueTypes.SECURITYPRECAUTIONS, IssueTypes.SPECIALPOPULATIONS,
				IssueTypes.SOCIALCONDITIONS, IssueTypes.POLICIESANDLAWS,
				IssueTypes.EMERGENCIES, IssueTypes.OTHERMANAGE };

		assembleValues = new ArrayList<Object>();
		for (int i = 0; i < bigType.length; i++) {
			IssueTypeVo itv = new IssueTypeVo();
			IssueTypeDomain itd = new IssueTypeDomain();
			itd = issueTypeDomainService
					.getIssueTypeDoaminByDomainName(bigType[i]);
			if (itd != null && itd.getId() != null) {
				List<IssueType> itsLists = issueTypeService
						.findIssueTypesByDomainId(itd.getId());
				for (IssueType issueType : itsLists) {
					issueType.setFullPinYin(String.valueOf(itd.getId()) + "_"
							+ String.valueOf(issueType.getId()));
				}
				itv.setIssueTypeDomain(itd);
				itv.setIssueTypes(itsLists);
			}
			assembleValues.add(itv);
		}
	}

	private void prepareAdd() {
		administrativeOrgTimeLimitStandard = new AdministrativeOrgTimeLimitStandard();
		Organization organization = ThreadVariable.getOrganization();
		PropertyDict propertyDict = propertyDictService
				.getPropertyDictById(organization.getOrgLevel().getId());
		administrativeOrgTimeLimitStandard.setCreateOrg(organization);
		List<PropertyDict> list = propertyDictService
				.findPropertyDictByDomainNameAndInternalIds(
						PropertyTypes.ORGANIZATION_LEVEL,
						new int[] { propertyDict.getInternalId() - 10 });
		if (list.size() > 0) {
			administrativeOrgTimeLimitStandard.setUseLevel(list.get(0));
		} else {
			throw new BusinessValidationException("网格层级用户，不能进行新增操作");
		}
	}

	@PermissionFilter(ename = "updateAdministrativeOrgTimeLimitStandard", actionName = "updateAdministrativeOrgTimeLimitStandard")
	@Action(value = "updateAdministrativeOrgTimeLimitStandard", results = {
			@Result(name = "success", type = "json", params = { "root",
					"administrativeOrgTimeLimitStandard", "ignoreHierarchy",
					"false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainAdministrativeOrgTimeLimitStandard() throws Exception {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			administrativeOrgTimeLimitStandard = administrativeOrgTimeLimitStandardService
					.addAdministrativeOrgTimeLimitStandard(administrativeOrgTimeLimitStandard);
			return SUCCESS;
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)
				|| DialogMode.VIEW1_MODE.equalsIgnoreCase(mode)) {
			administrativeOrgTimeLimitStandard = administrativeOrgTimeLimitStandardService
					.updateAdministrativeOrgTimeLimitStandard(administrativeOrgTimeLimitStandard);
			return SUCCESS;
		}
		return ERROR;
	}

	@PermissionFilter(ename = "addAdministrativeOrgTimeLimitStandard", actionName = "addAdministrativeOrgTimeLimitStandard")
	@Action(value = "addAdministrativeOrgTimeLimitStandard", results = {
			@Result(name = "success", type = "json", params = { "root",
					"administrativeOrgTimeLimitStandard", "ignoreHierarchy",
					"false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainAdministrativeOrgTimeLimitStandardWithMoreIds()
			throws Exception {
		Long internalId = -1l;
		if (null != administrativeOrgTimeLimitStandard.getUseLevel()
				&& null != administrativeOrgTimeLimitStandard.getUseLevel()
						.getId()) {
			PropertyDict propertyDict = propertyDictService
					.getPropertyDictById(administrativeOrgTimeLimitStandard
							.getUseLevel().getId());
			internalId = Long.valueOf("" + propertyDict.getInternalId());
		}
		Organization org = ThreadVariable.getUser().getOrganization();
		List<AdministrativeOrgTimeLimitStandard> list = administrativeOrgTimeLimitStandardService
				.getAdministrativeOrgTimeLimitStandardByOrginternalid(
						org.getId(), internalId);
		List<Long> issueTypeIds = new ArrayList<Long>();
		for (AdministrativeOrgTimeLimitStandard administrativeOrgTimeLimitStandard : list) {
			issueTypeIds.add(administrativeOrgTimeLimitStandard
					.getIssueTypeId());
		}
		String checkedValues[] = administrativeOrgTimeLimitStandard
				.getItemIds().trim().split(",");
		checkedValues = getDistinct(checkedValues);
		for (int i = 0; i < checkedValues.length; i++) {
			if (issueTypeIds.size() > 0
					&& checkedValues[i] != ""
					&& !(checkedValues[i].split("_")[1]).equals("")
					&& issueTypeIds.contains(Long.parseLong(checkedValues[i]
							.split("_")[1]))) {
				continue;
			} else {
				if (!("").equals(checkedValues[i])) {
					AdministrativeOrgTimeLimitStandard newats = administrativeOrgTimeLimitStandard;
					newats.setId(null);
					newats.setIssueTypeId(Long.parseLong(checkedValues[i]
							.split("_")[1]));
					newats.setIssueDomainId(Long.parseLong(checkedValues[i]
							.split("_")[0]));
					administrativeOrgTimeLimitStandard = administrativeOrgTimeLimitStandardService
							.addAdministrativeOrgTimeLimitStandard(newats);
				}
			}
		}
		if (list.size() > 0) {
			administrativeOrgTimeLimitStandard = list.get(0);
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "deleteAdministrativeOrgTimeLimitStandard")
	@Action(value = "deleteAdministrativeOrgTimeLimitStandardByIds", results = {
			@Result(type = "json", name = "success", params = { "root",
					"deleteState", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteAdministrativeOrgTimeLimitStandardByIds()
			throws Exception {
		if (null == selectedIds) {
			deleteState = false;
			errorMessage = "请选择一条记录再删除!";
			return ERROR;
		}
		deleteState = administrativeOrgTimeLimitStandardService
				.deleteAdministrativeOrgTimeLimitStandardByIds(analyze(selectedIds));

		return SUCCESS;
	}

	@Action(value = "validateRepeatByUseLevelAndIssueDomainIdAndIssueTypeId", results = {
			@Result(type = "json", name = "success", params = { "root",
					"result", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String validateRepeatByUseLevelAndIssueDomainIdAndIssueTypeId()
			throws Exception {
		if (administrativeOrgTimeLimitStandard == null) {
			errorMessage = "操作失败，请联系管理员！";
			return ERROR;
		}
		result = administrativeOrgTimeLimitStandardService
				.validateRepeatByUseLevelAndIssueDomainIdAndIssueTypeId(administrativeOrgTimeLimitStandard);
		return SUCCESS;
	}

	@Action(value = "validateRepeatByUseLevelAndIssueDomainIdAndIssueTypeIdWithMoreId", results = {
			@Result(type = "json", name = "success", params = { "root",
					"result", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String validateRepeatByUseLevelAndIssueDomainIdAndIssueTypeIdWithMoreId() {
		String checkedValues[] = administrativeOrgTimeLimitStandard
				.getItemIds().split(",");
		checkedValues = getDistinct(checkedValues);
		Long internalId = -1l;
		if (null != administrativeOrgTimeLimitStandard.getUseLevel()
				&& null != administrativeOrgTimeLimitStandard.getUseLevel()
						.getId()) {
			PropertyDict propertyDict = propertyDictService
					.getPropertyDictById(administrativeOrgTimeLimitStandard
							.getUseLevel().getId());
			internalId = Long.valueOf("" + propertyDict.getInternalId());
		}
		Organization org = ThreadVariable.getUser().getOrganization();
		List<AdministrativeOrgTimeLimitStandard> list = administrativeOrgTimeLimitStandardService
				.getAdministrativeOrgTimeLimitStandardByOrginternalid(
						org.getId(), internalId);
		List<Long> issueTypeIds = new ArrayList<Long>();
		for (AdministrativeOrgTimeLimitStandard administrativeOrgTimeLimitStandard : list) {
			issueTypeIds.add(administrativeOrgTimeLimitStandard
					.getIssueTypeId());
		}
		result = false;
		for (int i = 0; i < checkedValues.length; i++) {
			if (issueTypeIds.size() > 0
					&& checkedValues[i] != ""
					&& !(checkedValues[i].split("_")[1]).equals("")
					&& issueTypeIds.contains(Long.parseLong(checkedValues[i]
							.split("_")[1]))) {
				result = true;
				break;
			}
		}
		return SUCCESS;
	}

	public static String[] getDistinct(String num[]) {
		List<String> list = new java.util.ArrayList<String>();
		for (int i = 0; i < num.length; i++) {
			if (!list.contains(num[i]) && !num[i].trim().equals("")) {
				list.add(num[i]);
			}
		}
		return list.toArray(new String[0]);
	}

	public Organization getUserOrganization() {
		return userOrganization;
	}

	public void setUserOrganization(Organization userOrganization) {
		this.userOrganization = userOrganization;
	}

	public void setAdministrativeOrgTimeLimitStandard(
			AdministrativeOrgTimeLimitStandard administrativeOrgTimeLimitStandard) {
		this.administrativeOrgTimeLimitStandard = administrativeOrgTimeLimitStandard;
	}

	public AdministrativeOrgTimeLimitStandard getAdministrativeOrgTimeLimitStandard() {
		return administrativeOrgTimeLimitStandard;
	}

	public void setSelectedIds(String selectedIds) {
		this.selectedIds = selectedIds;
	}

	public String getSelectedIds() {
		return selectedIds;
	}

	public void setDeleteState(Boolean deleteState) {
		this.deleteState = deleteState;
	}

	public Boolean getDeleteState() {
		return deleteState;
	}

	public List<Object> getAssembleValues() {
		return assembleValues;
	}

	public void setAssembleValues(List<Object> assembleValues) {
		this.assembleValues = assembleValues;
	}

}
