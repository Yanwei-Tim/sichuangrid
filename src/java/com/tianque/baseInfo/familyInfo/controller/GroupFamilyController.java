package com.tianque.baseInfo.familyInfo.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.base.service.CountrymenService;
import com.tianque.baseInfo.familyInfo.domain.GroupFamily;
import com.tianque.baseInfo.familyInfo.domain.GroupFamilyHasPopulation;
import com.tianque.baseInfo.familyInfo.domain.SavePeople;
import com.tianque.baseInfo.familyInfo.service.GroupFamilyService;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Scope("prototype")
@Namespace("/baseinfo/familyInfo")
@Controller("groupFamilyController")
@Transactional
public class GroupFamilyController extends BaseAction {
	private Countrymen population;
	private String populationType;
	private Long populationId;
	private String dailogName;
	private Long orgId;
	private GroupFamily groupFamily;
	private GroupFamilyHasPopulation groupFamilyHasPopulation;
	private Boolean whetherFamilyMaster;
	private Boolean whetherExistOtherFamily; // 是否存在其他家庭中
	private String ids;
	private int count;
	private String previousFamilyAccount;
	private String newFamilyAccount;
	private Long groupFamilyId;
	private String previousFamilyMaster; // 原家长
	private String newFamilyMaster; // 新家长
	private Long familyRelationId; // 与家长关系id
	private String idCardNo;
	private String prompt; // 提示
	private SavePeople savePeople;
	private Long organizationId;

	private List<GroupFamilyHasPopulation> groupFamilyHasPopulations = new ArrayList<GroupFamilyHasPopulation>();
	@Autowired
	private GroupFamilyService groupFamilyService;
	@Autowired
	private CountrymenService countrymenService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Action(value = "getGroupFamilyByPopulationId", results = {
			@Result(name = "success", location = "/baseinfo/familyInfo/groupFamily/groupFamilyDialog.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getGroupFamilyByPopulationId() throws Exception {
		if (null == id && null == organizationId) {
			errorMessage = "人口信息ID不存在或组织机构ID传递出错";
			return ERROR;
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		population = countrymenService
				.getCountrymenByPopulationIdAndPopulationType(id,
						populationType, organization.getOrgInternalCode());
		population.setGroupFamily(groupFamilyService
				.getGroupFamilyByPopulation(population));
		population.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(population
						.getOrganization().getId(), organizationDubboService));
		return SUCCESS;
	}

	@Action(value = "proccessGroupFamily", results = { @Result(name = "success", type = "json", params = {
			"root", "population", "ignoreHierarchy", "false" }) })
	public String proccessGroupFamily() throws Exception {
		groupFamily.setOrgInternalCode(population.getOrgInternalCode());
		groupFamilyService.practiseGroupFamilyForSynchro(groupFamily,
				population.getId(), population.getActualPopulationType());
		population.setGroupFamily(groupFamily);
		return SUCCESS;
	}

	@Action(value = "pageGroupFamiliesByOrgId", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage" }) })
	public String pageGroupFamiliesByOrgId() throws Exception {
		if (orgId == null) {
			gridPage = new GridPage(emptyPage(rows));
		} else {
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					groupFamilyService.pageGroupFamiliesByOrgId(orgId, page,
							rows, sidx, sord), organizationDubboService,
					new String[] { "organization" }, orgId));
		}
		return SUCCESS;
	}

	private PageInfo<GroupFamily> emptyPage(int pageSize) {
		PageInfo<GroupFamily> pageInfo = new PageInfo<GroupFamily>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<GroupFamily>());
		return pageInfo;
	}

	@Action(value = "getGroupFamilyByFamilyId", results = {
			@Result(name = "success", location = "/baseinfo/familyInfo/groupFamily/viewGroupFamilyDlg.jsp"),
			@Result(name = "print", location = "/baseinfo/familyInfo/groupFamily/viewGroupFamilyPrintDlg.jsp") })
	public String getGroupFamilyByFamilyId() throws Exception {
		groupFamily = groupFamilyService
				.getGroupFamilyById(groupFamily.getId());
		groupFamily.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(groupFamily
						.getOrganization().getId(), organizationDubboService));
		groupFamily.setCurrentAddress(countrymenService
				.getCountrymenByIdCardNoAndOrgInternalCode(
						groupFamily.getMasterCardNo(),
						groupFamily.getOrgInternalCode()).getCurrentAddress());
		if (DialogMode.PRINT_MODE.equals(mode)) {
			return "print";
		}
		return SUCCESS;
	}

	/**
	 * ID加密 查看
	 * 
	 * @return
	 */
	@EncryptAnnotation
	@Action(value = "getGroupFamilyByFamilyEncryptId", results = {
			@Result(name = "success", location = "/baseinfo/familyInfo/groupFamily/viewGroupFamilyDlg.jsp"),
			@Result(name = "print", location = "/baseinfo/familyInfo/groupFamily/viewGroupFamilyPrintDlg.jsp") })
	public String getGroupFamilyByFamilyEncryptId() throws Exception {
		groupFamily = groupFamilyService
				.getGroupFamilyById(groupFamily.getId());
		groupFamily.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(groupFamily
						.getOrganization().getId(), organizationDubboService));
		groupFamily.setCurrentAddress(countrymenService
				.getCountrymenByIdCardNoAndOrgInternalCode(
						groupFamily.getMasterCardNo(),
						groupFamily.getOrgInternalCode()).getCurrentAddress());
		if (DialogMode.PRINT_MODE.equals(mode)) {
			return "print";
		}
		return SUCCESS;
	}

	@Action(value = "judgeGroupFamilyMember", results = { @Result(name = "success", type = "json", params = {
			"root", "count", "ignoreHierarchy", "false" }) })
	public String judgeGroupFamilyMember() throws Exception {
		count = groupFamilyService.getFamilyMembersByFamilyId(ids);
		return SUCCESS;
	}

	/**
	 * ID加密 根据家庭ID查询该家庭下是否存在家庭成员
	 * 
	 * @return
	 */
	@Action(value = "judgeGroupFamilyMemberByEncryptId", results = { @Result(name = "success", type = "json", params = {
			"root", "count", "ignoreHierarchy", "false" }) })
	@EncryptAnnotation
	public String judgeGroupFamilyMemberByEncryptId() throws Exception {
		count = groupFamilyService.getFamilyMembersByFamilyId(ids);
		return SUCCESS;
	}

	@EncryptAnnotation
	@PermissionFilter(ename = "deleteGroupFamilyInfo")
	@Action(value = "deleteGroupFamilyByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteGroupFamilyByIds() throws Exception {
		groupFamilyService.deleteGroupFamily(ids);

		return SUCCESS;
	}

	@PermissionFilter(ename = "resetFamilyAccount")
	@Action(value = "resetFamilyAccount", results = {
			@Result(name = "success", type = "json", params = { "root", "" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String resetFamilyAccount() throws Exception {
		groupFamilyService.resetFamilyAccount(groupFamilyId,
				previousFamilyAccount, newFamilyAccount);

		return SUCCESS;

	}

	@PermissionFilter(ename = "resetFamilyAccount")
	@Action(value = "resetFamilyAccountByEncrypt", results = {
			@Result(name = "success", type = "json", params = { "root", "" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@EncryptAnnotation
	public String resetFamilyAccountByEncrypt() throws Exception {
		groupFamilyService.resetFamilyAccount(groupFamilyId,
				previousFamilyAccount, newFamilyAccount);

		return SUCCESS;

	}

	// @PermissionFilter(ename = "changeFamilyMaster")
	@Action(value = "dispatch", results = {
			@Result(name = "change", location = "/baseinfo/familyInfo/groupFamily/changeFamilyMasterDlg.jsp"),
			@Result(name = "reset", location = "/baseinfo/familyInfo/groupFamily/resetFamilyAccountDlg.jsp"),
			@Result(name = "manage", location = "/baseinfo/familyInfo/groupFamily/manageFamilyMembersDlg.jsp") })
	public String dispatch() throws Exception {
		if ("change".equals(mode)) {
			groupFamilyHasPopulations = groupFamilyService
					.findFamilyMembersByFamilyIdNoMaster(groupFamilyId);
			previousFamilyMaster = groupFamilyService.getGroupFamilyById(
					groupFamilyId).getFamilyMaster();
		}
		if ("reset".equals(mode)) {
			groupFamilyHasPopulations = groupFamilyService
					.findFamilyMembersByFamilyIdNoMaster(groupFamilyId);
			groupFamily = groupFamilyService.getGroupFamilyById(groupFamilyId);
			return "reset";
		}
		if ("manage".equals(mode)) {
			if (groupFamilyId == null) {
				this.errorMessage = "参数错误";
				return ERROR;
			}
			groupFamily = groupFamilyService.getGroupFamilyById(groupFamilyId);

			return "manage";
		}
		return "change";

	}

	/**
	 * ID加密 重置家庭编号
	 * 
	 * @return
	 * 
	 */
	@EncryptAnnotation
	@Action(value = "dispatchByEncryptId", results = {
			@Result(name = "reset", location = "/baseinfo/familyInfo/groupFamily/resetFamilyAccountDlg.jsp"),
			@Result(name = "change", location = "/baseinfo/familyInfo/groupFamily/changeFamilyMasterDlg.jsp"),
			@Result(name = "manage", location = "/baseinfo/familyInfo/groupFamily/manageFamilyMembersDlg.jsp") })
	public String dispatchByEncryptId() throws Exception {
		if ("change".equals(mode)) {
			groupFamilyHasPopulations = groupFamilyService
					.findFamilyMembersByFamilyIdNoMaster(groupFamilyId);
			previousFamilyMaster = groupFamilyService.getGroupFamilyById(
					groupFamilyId).getFamilyMaster();
			return "change";
		}
		if ("manage".equals(mode)) {
			if (groupFamilyId == null) {
				this.errorMessage = "参数错误";
				return ERROR;
			}
			groupFamily = groupFamilyService.getGroupFamilyById(groupFamilyId);

			return "manage";
		}

		if ("reset".equals(mode)) {
			groupFamilyHasPopulations = groupFamilyService
					.findFamilyMembersByFamilyIdNoMaster(groupFamilyId);
			groupFamily = groupFamilyService.getGroupFamilyById(groupFamilyId);
		}
		return "reset";
	}

	@Action(value = "addFamilyMember", results = {
			@Result(name = "success", type = "json", params = { "root", "" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addFamilyMember() throws Exception {
		groupFamilyService.addFamilyMember(groupFamilyId, familyRelationId,
				savePeople, whetherExistOtherFamily);

		return SUCCESS;
	}

	@Action(value = "judgeExistAtOrgCodeOfFamily", results = {
			@Result(name = "success", type = "json", params = { "root",
					"savePeople" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String judgeExistAtOrgCodeOfFamily() throws Exception {
		savePeople = groupFamilyService.judgeExistAtOrgCodeOfFamily(
				groupFamilyId, idCardNo, familyRelationId);

		return SUCCESS;
	}

	@Action(value = "deleteGroupFamilyMember", results = {
			@Result(name = "success", type = "json", params = { "root", "" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteGroupFamilyMember() throws Exception {
		groupFamilyService.deleteGroupFamilyMember(groupFamilyId, populationId,
				populationType);
		return SUCCESS;
	}

	@Action(value = "findFamilyMembersByFamilyIdNoMasterAndIncludeRelation", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findFamilyMembersByFamilyIdNoMasterAndIncludeRelation()
			throws Exception {
		if (groupFamilyId == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		gridPage = new GridPage<GroupFamilyHasPopulation>(
				groupFamilyService
						.findFamilyMembersByFamilyIdNoMasterAndIncludeRelation(
								groupFamilyId, page, rows, sidx, sord));

		return SUCCESS;
	}

	@PermissionFilter(ename = "changeFamilyMaster")
	@Action(value = "changeFamilyMaster", results = {
			@Result(name = "success", type = "json", params = { "root", "" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String changeFamilyMaster() throws Exception {
		groupFamilyService.changeFamilyMaster(groupFamilyId, familyRelationId,
				newFamilyMaster);

		return SUCCESS;
	}

	@Action(value = "findFamilyMembersByFamilyId", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage" }) })
	public String findFamilyMembersByFamilyId() throws Exception {
		if (groupFamily.getId() != null) {
			gridPage = new GridPage<GroupFamilyHasPopulation>(
					groupFamilyService.pageFamilyMembersByFamilyId(
							groupFamily.getId(), page, rows, sidx, sord));
		}
		return SUCCESS;
	}

	@Action(value = "whetherFamilyMaster", results = { @Result(name = "success", type = "json", params = {
			"root", "whetherFamilyMaster" }) })
	public String whetherFamilyMaster() throws Exception {
		if (null != groupFamily && null != groupFamily.getFamilyRelation()
				&& null != groupFamily.getFamilyRelation().getId()) {
			whetherFamilyMaster = groupFamilyService
					.whetherFamilyMaster(groupFamily.getFamilyRelation()
							.getId());
		}
		return SUCCESS;
	}

	@Action(value = "findGroupFamilyByOrgIdAndFamilyAccount", results = { @Result(name = "success", type = "json", params = {
			"root", "groupFamily" }) })
	public String findGroupFamilyByOrgIdAndFamilyAccount() throws Exception {
		if (null != orgId && null != groupFamily
				&& null != groupFamily.getFamilyAccount()) {
			groupFamily = groupFamilyService
					.findGroupFamilyByOrgIdAndFamilyAccount(orgId,
							groupFamily.getFamilyAccount());
		}
		return SUCCESS;
	}

	@EncryptAnnotation
	@Action(value = "detailGroupFamily", results = {
			@Result(name = "success", location = "/baseinfo/familyInfo/groupFamily/detailGroupFamilyDlg.jsp"),
			@Result(name = "print", location = "/baseinfo/familyInfo/groupFamily/detailGroupFamilyPrintDlg.jsp") })
	public String detailGroupFamily() throws Exception {
		population = countrymenService
				.getCountrymenByPopulationIdAndPopulationType(
						groupFamilyHasPopulation.getPopulationId(),
						groupFamilyHasPopulation.getPopulationType(),
						groupFamilyHasPopulation.getPopulation()
								.getOrgInternalCode());
		groupFamily = groupFamilyService.getGroupFamilyByPopulation(population);
		if (null != population.getOrganization()) {
			population.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(population
							.getOrganization().getId(), organizationDubboService));
		}
		population.setGroupFamily(groupFamily);
		if (DialogMode.PRINT_MODE.equals(mode)) {
			return "print";
		}
		return SUCCESS;
	}

	public Countrymen getPopulation() {
		return population;
	}

	public String getPopulationType() {
		return populationType;
	}

	public String getDailogName() {
		return dailogName;
	}

	public void setPopulation(Countrymen population) {
		this.population = population;
	}

	public void setPopulationType(String populationType) {
		this.populationType = populationType;
	}

	public void setDailogName(String dailogName) {
		this.dailogName = dailogName;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setGroupFamily(GroupFamily groupFamily) {
		this.groupFamily = groupFamily;
	}

	public GroupFamily getGroupFamily() {
		return groupFamily;
	}

	public void setWhetherFamilyMaster(Boolean whetherFamilyMaster) {
		this.whetherFamilyMaster = whetherFamilyMaster;
	}

	public Boolean getWhetherFamilyMaster() {
		return whetherFamilyMaster;
	}

	public void setGroupFamilyHasPopulation(
			GroupFamilyHasPopulation groupFamilyHasPopulation) {
		this.groupFamilyHasPopulation = groupFamilyHasPopulation;
	}

	public GroupFamilyHasPopulation getGroupFamilyHasPopulation() {
		return groupFamilyHasPopulation;
	}

	public List<GroupFamilyHasPopulation> getGroupFamilyHasPopulations() {
		return groupFamilyHasPopulations;
	}

	public void setGroupFamilyHasPopulations(
			List<GroupFamilyHasPopulation> groupFamilyHasPopulations) {
		this.groupFamilyHasPopulations = groupFamilyHasPopulations;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getPreviousFamilyAccount() {
		return previousFamilyAccount;
	}

	public void setPreviousFamilyAccount(String previousFamilyAccount) {
		this.previousFamilyAccount = previousFamilyAccount;
	}

	public String getNewFamilyAccount() {
		return newFamilyAccount;
	}

	public void setNewFamilyAccount(String newFamilyAccount) {
		this.newFamilyAccount = newFamilyAccount;
	}

	public Long getGroupFamilyId() {
		return groupFamilyId;
	}

	public void setGroupFamilyId(Long groupFamilyId) {
		this.groupFamilyId = groupFamilyId;
	}

	public String getPreviousFamilyMaster() {
		return previousFamilyMaster;
	}

	public void setPreviousFamilyMaster(String previousFamilyMaster) {
		this.previousFamilyMaster = previousFamilyMaster;
	}

	public String getNewFamilyMaster() {
		return newFamilyMaster;
	}

	public void setNewFamilyMaster(String newFamilyMaster) {
		this.newFamilyMaster = newFamilyMaster;
	}

	public Long getFamilyRelationId() {
		return familyRelationId;
	}

	public void setFamilyRelationId(Long familyRelationId) {
		this.familyRelationId = familyRelationId;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public Long getPopulationId() {
		return populationId;
	}

	public void setPopulationId(Long populationId) {
		this.populationId = populationId;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public SavePeople getSavePeople() {
		return savePeople;
	}

	public void setSavePeople(SavePeople savePeople) {
		this.savePeople = savePeople;
	}

	public Boolean getWhetherExistOtherFamily() {
		return whetherExistOtherFamily;
	}

	public void setWhetherExistOtherFamily(Boolean whetherExistOtherFamily) {
		this.whetherExistOtherFamily = whetherExistOtherFamily;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
}
