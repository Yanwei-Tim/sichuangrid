package com.tianque.baseInfo.primaryOrg.primaryMembers.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.baseInfo.primaryOrg.domain.PrimaryOrgVo;
import com.tianque.baseInfo.primaryOrg.domain.PrimaryOrganizations;
import com.tianque.baseInfo.primaryOrg.primaryMembers.domain.IsFourLevelPlatform;
import com.tianque.baseInfo.primaryOrg.primaryMembers.domain.PrimaryMemberVo;
import com.tianque.baseInfo.primaryOrg.primaryMembers.domain.PrimaryMembers;
import com.tianque.baseInfo.primaryOrg.primaryMembers.domain.PrimaryMembersOrgType;
import com.tianque.baseInfo.primaryOrg.primaryMembers.domain.PrimaryOrgOption;
import com.tianque.baseInfo.primaryOrg.primaryMembers.service.PrimaryMembersOrgTypeService;
import com.tianque.baseInfo.primaryOrg.primaryMembers.service.PrimaryMembersService;
import com.tianque.baseInfo.primaryOrg.service.PrimaryOrgService;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.Organization;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 成员控制类
 * 
 */
@Namespace("/primaryOrg/primaryMembersManage")
@Controller("PrimaryMembersController")
@Scope("prototype")
public class PrimaryMembersController extends BaseAction {

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PrimaryMembersService primaryMembersService;
	@Autowired
	private PrimaryMembersOrgTypeService primaryMembersOrgTypeService;
	@Autowired
	private OrganizationDubboService orgService;
	@Autowired
	private PrimaryOrgService primaryOrgService;

	// 组织信息
	private PrimaryOrgVo primaryOrgVo;
	/** 成员vo对象 **/
	private PrimaryMemberVo primaryMemberVo;
	private PrimaryMembers primaryMembers;
	private PrimaryOrgOption primaryOrgOption;

	private PrimaryMembersOrgType primaryMembersOrgType;

	/** 组织机构大类名称 */
	private String name;
	/** 组织机构编号 */
	private Long orgId;

	/** 仅显示本级、所有下辖、直属下辖 */
	private String displayLevel;
	// 用于区分组织类别
	private Integer internalId;

	private String optionOrgIds;

	private String orgTeamClazz;

	/** 是否任职 */
	private Long isHaveJob;
	private String ids;
	private Long selectedId;
	private String selectedIds;
	private Integer isFourLevelPlatform;
	private Long primaryOrgId;
	private String primaryOrgDetailName;

	// 组织信息（用于高级搜索）
	private List<PrimaryOrganizations> primaryOrganizations;
	private Integer seq;
	private String dailogName;
	private String teamTypeDomainName;

	/** id加密业务跳转 */
	@EncryptAnnotation
	@Action(value = "dispatchByEncrypt", results = {
			@Result(name = "maintain", location = "/baseinfo/primaryMembersManage/primaryMembersMaintainDlg.jsp"),
			@Result(name = "maintainPrimaryOrgMembers", location = "/baseinfo/primaryMembersManage/primaryOrgMemersDlg.jsp"),
			@Result(name = "combine", location = "/baseinfo/primaryMembersManage/primaryMemberCombineDlg.jsp") })
	public String dispatchByEncrypt() throws Exception {
		if ("primaryMembersList".equals(mode)) {
			return getMode();
		} else if ("primaryMembersOrgOption".equals(mode)) {
			return getMode();
		} else if ("maintainPrimaryOrgMembers".equals(mode)) {
			if (null == name) {
				errorMessage = "参数有误";
				return ERROR;
			}
			return getMode();
		} else if (DialogMode.COMBINE.equals(mode)) {
			if (id == null) {
				this.errorMessage = "没有选中任何信息!";
				return ERROR;
			}
			primaryMembers = primaryMembersService.getPrimaryMembersById(id);
			return DialogMode.COMBINE;
		} else if ("primaryOrgMemberList".equals(mode)) {
			if (null == name) {
				errorMessage = "参数有误";
				return ERROR;
			}
			return getMode();
		} else if (DialogMode.SEARCH_MODE.equals(mode)) {
			return getMode();
		}
		if (DialogMode.ADD_MODE.equals(mode)) {
			if (isFourLevelPlatform != null && primaryOrgId != null
					&& primaryOrgDetailName != null) {
				primaryMembers.setPrimaryOrgIds(isFourLevelPlatform + "-"
						+ primaryOrgId);
				primaryMembers.setPrimaryOrgNames(primaryOrgDetailName);
			}
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			primaryMembers.setOrg(org);
			return DialogMode.OGRANIZATIONS_MAINTAIN;
		}
		if (DialogMode.EDIT_MODE.equals(mode)
				|| DialogMode.VIEW_MODE.equals(mode)) {
			primaryMembers = primaryMembersService
					.getPrimaryMembersById(selectedId);
			if (primaryMembers == null) {
				errorMessage = "没有查到任何数据!";
				return ERROR;
			}
			// 根据成员ID 关联 关系表，查询组织ID 然后查询组织name set赋值
			primaryMembers = primaryMembersOrgTypeService
					.getprimaryOrgName(primaryMembers);
			return DialogMode.OGRANIZATIONS_MAINTAIN;
		}
		return ERROR;
	}

	/** 业务跳转 */
	@Action(value = "dispatch", results = {
			@Result(name = "add", location = "/baseinfo/primaryMembersManage/primaryMembersMaintainDlg.jsp"),
			@Result(name = "edit", location = "/baseinfo/primaryMembersManage/primaryMembersMaintainDlg.jsp"),
			@Result(name = "primaryMembersList", location = "/baseinfo/primaryMembersManage/primaryMembersList.jsp"),
			@Result(name = "combine", location = "/baseinfo/primaryMembersManage/primaryMemberCombineDlg.jsp"),
			@Result(name = "primaryMembersOrgOption", location = "/baseinfo/primaryMembersManage/primaryMembersOrgOptionDlg.jsp"),

			@Result(name = "maintainPrimaryOrgMembers", location = "/baseinfo/primaryMembersManage/primaryOrgMemersDlg.jsp"),
			@Result(name = "primaryOrgMemberList", location = "/baseinfo/primaryMembersManage/primaryOrgMemberList.jsp"),
			@Result(name = "search", location = "/baseinfo/primaryMembersManage/primaryMembersSearchDlg.jsp") })
	public String dispatch() throws Exception {
		if (DialogMode.PRIMARYMEMBERSLIST.equals(mode)) {
			return DialogMode.PRIMARYMEMBERSLIST;
		} else if (DialogMode.PRIMARYMEMBERSORGOPTION.equals(mode)) {
			return DialogMode.PRIMARYMEMBERSORGOPTION;
		} else if (DialogMode.MAINTAINPRIMARYORGMEMBERS.equals(mode)) {
			if (null == name) {
				errorMessage = "参数有误";
				return ERROR;
			}
			return DialogMode.MAINTAINPRIMARYORGMEMBERS;
		} else if (DialogMode.COMBINE.equals(mode)) {
			if (id == null) {
				this.errorMessage = "没有选中任何信息!";
				return ERROR;
			}
			primaryMembers = primaryMembersService.getPrimaryMembersById(id);
			return DialogMode.COMBINE;
		} else if (DialogMode.PRIMARYORGMEMBERLIST.equals(mode)) {
			if (null == name) {
				errorMessage = "参数有误";
				return ERROR;
			}
			return DialogMode.PRIMARYORGMEMBERLIST;
		} else if (DialogMode.SEARCH_MODE.equals(mode)) {
			return DialogMode.SEARCH_MODE;
		} else if (DialogMode.ADD_MODE.equals(mode)) {
			if (isFourLevelPlatform != null && primaryOrgId != null
					&& primaryOrgDetailName != null) {
				primaryMembers.setPrimaryOrgIds(isFourLevelPlatform + "-"
						+ primaryOrgId);
				primaryMembers.setPrimaryOrgNames(primaryOrgDetailName);
			}
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			org.setOrgName(ControllerHelper.getOrganizationRelativeName(orgId,
					organizationDubboService));
			primaryMembers.setOrg(org);
			return DialogMode.ADD_MODE;
		} else if (DialogMode.EDIT_MODE.equals(mode)
				|| DialogMode.VIEW_MODE.equals(mode)) {
			primaryMembers = primaryMembersService.getPrimaryMembersById(id);
			boolean isFourLevelPlat = primaryMembersOrgTypeService
					.isFourLevelPlat(primaryMembers.getId());
			if (isFourLevelPlat) {
				setIsFourLevelPlatform(IsFourLevelPlatform.FourLevelPlat_IS);
			} else {
				setIsFourLevelPlatform(IsFourLevelPlatform.FourLevelPlat_NO);
			}
			if (primaryMembers == null) {
				errorMessage = "没有查到任何数据!";
				return ERROR;
			}
			// 根据成员ID 关联 关系表，查询组织ID 然后查询组织name set赋值
			primaryMembers = primaryMembersOrgTypeService
					.getprimaryOrgName(primaryMembers);
			return DialogMode.EDIT_MODE;
		}
		return ERROR;
	}

	/**
	 * 新增成员
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "addPrimaryMembers")
	@Action(value = "addPrimaryMembers", results = {
			@Result(name = "success", type = "json", params = { "root",
					"primaryMembers", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addPrimaryMembers() throws Exception {
		if (null == primaryMembers) {
			errorMessage = "新增出错";
			return ERROR;
		}
		primaryMembers = primaryMembersService.addPrimaryMembers(
				primaryMembers, optionOrgIds);
		return SUCCESS;
	}

	/**
	 * 显示成员列表信息 :findPrimaryMembers
	 * 
	 * @return SUCCESS
	 */

	@Action(value = "findPrimaryMembers", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findPrimaryMembers() throws Exception {
		if (null == primaryMemberVo) {
			errorMessage = "参数错误";
			return ERROR;
		}
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				primaryMembersService.findPrimaryMembers(primaryMemberVo, page,
						rows, sidx, sord), organizationDubboService,
				new String[] { "org" }, orgId));
		return SUCCESS;
	}

	/**
	 * 组织维护成员显示成员列表信息 :findPrimaryMembersByOrg
	 * 
	 * @return SUCCESS
	 */

	@Action(value = "findPrimaryMembersByOrg", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findPrimaryMembersByOrg() throws Exception {
		if (null == primaryMemberVo) {
			errorMessage = "参数错误";
			return ERROR;
		}
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				primaryMembersService.findPrimaryMembersByOrg(primaryMemberVo,
						page, rows, sidx, sord), organizationDubboService,
				new String[] { "org" }, orgId));
		return SUCCESS;
	}

	/**
	 * 所在组织选择组织列表 :findPrimaryOrganizations
	 * 
	 * @return SUCCESS
	 */
	@Action(value = "findPrimaryOrganizations", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findPrimaryOrganizations() throws Exception {
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				primaryMembersService.findPrimaryOrganizations(orgId,
						displayLevel, orgTeamClazz, internalId, page, rows,
						sidx, sord ,teamTypeDomainName), organizationDubboService,
				new String[] { "org" }, orgId));
		return SUCCESS;
	}

	/** 根据组织名称获取组织列表 */
	@Action(value = "getPrimaryOrgInfoByDetailName", results = {
			@Result(type = "json", name = "success", params = { "root",
					"primaryOrganizations", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getPrimaryOrgInfoByDetailName() throws Exception {
		primaryOrganizations = primaryOrgService
				.getPrimaryOrgaInfonByDetailName(primaryOrgVo);
		return SUCCESS;
	}

	/**
	 * 修改成员信息
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "updatePrimaryMembers")
	@Action(value = "editPrimaryMembers", results = {
			@Result(name = "success", type = "json", params = { "root",
					"primaryMembers", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String editPrimaryMembers() throws Exception {
		if (null == primaryMembers) {
			errorMessage = "修改出错";
			return ERROR;
		}
		primaryMembers = primaryMembersService.updatePrimaryMembers(
				primaryMembers, optionOrgIds);
		return SUCCESS;
	}

	/**
	 * 根据id删除数据(批量删除)
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "deletePrimaryMembers")
	@Action(value = "deletePrimaryMembersByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deletePrimaryMembersByIds() throws Exception {
		if (ids == null || analyzePopulationIds(ids) == null) {
			errorMessage = "删除出错";
			return ERROR;
		}
		primaryMembersService
				.deletePrimaryMembersById(analyzePopulationIds(ids));
		return SUCCESS;
	}

	/**
	 * id加密根据id删除数据(批量删除)
	 * 
	 * @return
	 */
	@EncryptAnnotation
	@Action(value = "deletePrimaryMembersByIdsByEncrypt", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deletePrimaryMembersByIdsByEncrypt() throws Exception {
		if (ids == null || analyzePopulationIds(ids) == null) {
			errorMessage = "删除出错";
			return ERROR;
		}
		primaryMembersService
				.deletePrimaryMembersById(analyzePopulationIds(ids));
		return SUCCESS;
	}

	/**
	 * 组织维护成员删除成员信息 关联表
	 * 
	 * @return SUCCESS
	 */
	@Action(value = "deletePrimaryMembersOrgType", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deletePrimaryMembersOrgType() throws Exception {
		if (primaryMembersOrgType == null) {
			errorMessage = "删除出错";
			return ERROR;
		}
		primaryMembersOrgTypeService
				.deletePrimaryMembersOrgType(primaryMembersOrgType);
		return SUCCESS;
	}

	/**
	 * 根据姓名或手机查寻重复数据列表（基础表）
	 * 
	 * @return SUCCESS
	 */
	@Action(value = "findPrimaryMembersByNameOrMobile", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findPrimaryMembersByNameOrMobile() throws Exception {
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				primaryMembersService.findPrimaryMembersByNameOrMobile(
						primaryMembers, page, rows, sidx, sord),
				organizationDubboService, new String[] { "org" }, orgId));
		return SUCCESS;
	}

	/**
	 * 合并成员库成员
	 * 
	 * @return
	 */
	@Action(value = "combinePrimaryMembers", results = {
			@Result(name = "success", type = "json", params = { "root",
					"primaryMembers", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String combinePrimaryMembers() throws Exception {
		if (null == primaryMembers || null == primaryMembers.getOrg().getId()) {
			errorMessage = "合并出错,请联系管理员!";
			return ERROR;
		}
		primaryMembers = primaryMembersService.combinePrimaryMembers(
				primaryMembers, ids);
		return SUCCESS;
	}

	/** id加密任职卸任跳转 */
	@EncryptAnnotation
	@Action(value = "havajobPrimaryMemberByEncrypt", results = {
			@Result(name = "success", type = "json", params = { "root",
					"primaryMembers", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String havajobPrimaryMemberByEncrypt() throws Exception {
		if (id == null) {
			errorMessage = "操作出错，没有选中任何信息!";
			return ERROR;
		}
		primaryMembers = primaryMembersService.havajobPrimaryMember(isHaveJob,
				id);
		return SUCCESS;
	}

	@Action(value = "havajobPrimaryMember", results = {
			@Result(name = "success", type = "json", params = { "root",
					"primaryMembers", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String havajobPrimaryMember() throws Exception {
		if (id == null) {
			errorMessage = "操作出错，没有选中任何信息!";
			return ERROR;
		}
		primaryMembers = primaryMembersService.havajobPrimaryMember(isHaveJob,
				id);
		return SUCCESS;
	}

	@Action(value = "setMemberSeq", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String setMemberSeq() throws Exception {
		if (id == null) {
			errorMessage = "操作出错，没有选中任何信息!";
			return ERROR;
		}
		primaryMembersService.setMemberSeq(id, seq);
		return SUCCESS;
	}

	private Long[] analyzePopulationIds(String value) {
		String[] selectId = value.split(",");
		List<Long> idList;
		if (selectId[0].equals("")) {
			idList = initTargetId(selectId, 1);
		} else {
			idList = initTargetId(selectId, 0);
		}
		Long[] ids = new Long[idList.size()];
		for (int i = 0; i < ids.length; i++) {
			ids[i] = idList.get(i);
		}
		return ids;
	}

	private List<Long> initTargetId(String[] targetIds, int size) {
		List<Long> idLongs = new ArrayList<Long>();
		for (int i = size; i < targetIds.length; i++) {
			String tempId = targetIds[i];
			if (size == 0) {
				idLongs.add(Long.parseLong(targetIds[i]));
			} else {
				idLongs.add(Long.parseLong(tempId));
			}
		}
		return idLongs;
	}

	// 获得组织的name
	public List<String> getPrimaryOrgNames(Long[] ids) {
		List<String> names = new ArrayList<String>();
		for (Long id : ids) {
			String orgname = primaryOrgService.getPrimaryOrgById(id)
					.getDetailName();
			names.add(orgname);
		}
		return names;
	}

	public PrimaryMembers getPrimaryMembers() {
		return primaryMembers;
	}

	public void setPrimaryMembers(PrimaryMembers primaryMembers) {
		this.primaryMembers = primaryMembers;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getIsHaveJob() {
		return isHaveJob;
	}

	public void setIsHaveJob(Long isHaveJob) {
		this.isHaveJob = isHaveJob;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Long getSelectedId() {
		return selectedId;
	}

	public void setSelectedId(Long selectedId) {
		this.selectedId = selectedId;
	}

	public Integer getInternalId() {
		return internalId;
	}

	public void setInternalId(Integer internalId) {
		this.internalId = internalId;
	}

	public String getOptionOrgIds() {
		return optionOrgIds;
	}

	public void setOptionOrgIds(String optionOrgIds) {
		this.optionOrgIds = optionOrgIds;
	}

	public String getDisplayLevel() {
		return displayLevel;
	}

	public void setDisplayLevel(String displayLevel) {
		this.displayLevel = displayLevel;
	}

	public String getOrgTeamClazz() {
		return orgTeamClazz;
	}

	public void setOrgTeamClazz(String orgTeamClazz) {
		this.orgTeamClazz = orgTeamClazz;
	}

	public PrimaryOrgOption getPrimaryOrgOption() {
		return primaryOrgOption;
	}

	public void setPrimaryOrgOption(PrimaryOrgOption primaryOrgOption) {
		this.primaryOrgOption = primaryOrgOption;
	}

	public PrimaryOrgVo getPrimaryOrgVo() {
		return primaryOrgVo;
	}

	public void setPrimaryOrgVo(PrimaryOrgVo primaryOrgVo) {
		this.primaryOrgVo = primaryOrgVo;
	}

	public PrimaryMemberVo getPrimaryMemberVo() {
		return primaryMemberVo;
	}

	public void setPrimaryMemberVo(PrimaryMemberVo primaryMemberVo) {
		this.primaryMemberVo = primaryMemberVo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSelectedIds() {
		return selectedIds;
	}

	public void setSelectedIds(String selectedIds) {
		this.selectedIds = selectedIds;
	}

	public List<PrimaryOrganizations> getPrimaryOrganizations() {
		return primaryOrganizations;
	}

	public void setPrimaryOrganizations(
			List<PrimaryOrganizations> primaryOrganizations) {
		this.primaryOrganizations = primaryOrganizations;
	}

	public Integer getIsFourLevelPlatform() {
		return isFourLevelPlatform;
	}

	public void setIsFourLevelPlatform(Integer isFourLevelPlatform) {
		this.isFourLevelPlatform = isFourLevelPlatform;
	}

	public Long getPrimaryOrgId() {
		return primaryOrgId;
	}

	public void setPrimaryOrgId(Long primaryOrgId) {
		this.primaryOrgId = primaryOrgId;
	}

	public String getPrimaryOrgDetailName() {
		return primaryOrgDetailName;
	}

	public void setPrimaryOrgDetailName(String primaryOrgDetailName) {
		this.primaryOrgDetailName = primaryOrgDetailName;
	}

	public PrimaryMembersOrgType getPrimaryMembersOrgType() {
		return primaryMembersOrgType;
	}

	public void setPrimaryMembersOrgType(
			PrimaryMembersOrgType primaryMembersOrgType) {
		this.primaryMembersOrgType = primaryMembersOrgType;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getDailogName() {
		return dailogName;
	}

	public void setDailogName(String dailogName) {
		this.dailogName = dailogName;
	}

	public String getTeamTypeDomainName() {
		return teamTypeDomainName;
	}

	public void setTeamTypeDomainName(String teamTypeDomainName) {
		this.teamTypeDomainName = teamTypeDomainName;
	}

}
