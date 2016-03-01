package com.tianque.partyBuilding.baseInfos.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.Organization;
import com.tianque.partyBuilding.baseInfos.domain.BasicCase;
import com.tianque.partyBuilding.baseInfos.domain.PartyworkMembers;
import com.tianque.partyBuilding.baseInfos.domain.vo.SearchBasicCaseVo;
import com.tianque.partyBuilding.baseInfos.service.BasicCaseService;
import com.tianque.partyBuilding.baseInfos.service.CaseImageUploadService;
import com.tianque.partyBuilding.baseInfos.service.PartyworkMembersService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 基本情况表:服务前端控制类
 * 
 * @author
 * @date 2014-01-14 15:32:52
 */
@Namespace("/basicCaseManage")
@Scope("request")
@Controller("basicCaseController")
public class BasicCaseController extends BaseAction {

	private static Logger logger = LoggerFactory
			.getLogger(BasicCaseController.class);

	@Autowired
	private BasicCaseService basicCaseService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PartyworkMembersService partyworkMembersService;
	@Autowired
	private CaseImageUploadService caseImageUploadService;

	private BasicCase basicCase = new BasicCase();
	private SearchBasicCaseVo searchbasicCaseVo;
	private Organization organization;
	private PartyworkMembers partyworkMembers;
	private List<PartyworkMembers> partyworkMembersList;
	private String baseInfoType;

	/**
	 * 根据id和组织机构Id查询基本情况信息
	 * 
	 * @return
	 */
	@Action(value = "getBasicCaseByOrgIdAndId", results = {
			@Result(name = "upGrids", type = "json", params = { "root",
					"basicCase", "ignoreHierarchy", "false" }),
			@Result(name = "editBasicCase", location = "/partyBuilding/baseInfos/basicCaseDlg.jsp"),
			@Result(name = "zoomin", location = "/partyBuilding/baseInfos/basicCasePhotosList.jsp"),
			@Result(name = "editPhotos", location = "/partyBuilding/baseInfos/maintainPhotos.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getBasicCaseByOrgIdAndId() throws Exception {
		if (organization == null || organization.getId() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		organization = organizationDubboService.getSimpleOrgById(organization
				.getId());
		basicCase = basicCaseService.getByIdAndOrgIdAndType(basicCase.getId(),
				organization.getId(), baseInfoType);
		if (basicCase == null) {
			basicCase = new BasicCase();
		}
		basicCase.setOrganization(organization);
		basicCase.setCaseImageUploads(caseImageUploadService
				.findByOrgIdAndType(organization.getId(), baseInfoType));
		return mode;
	}

	/**
	 * 新增、修改基本情况信息
	 * 
	 * @return
	 */
	@Action(value = "addOrUpdateBasicCase", results = {
			@Result(name = "success", type = "json", params = { "root",
					"basicCase", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "editCase")
	public String addOrUpdateBasicCase() throws Exception {
		if (basicCase == null
				|| !checkOrganization(basicCase.getOrganization())) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		if (basicCase.getId() == null) {
			basicCase = basicCaseService.add(basicCase);
		} else {
			if (basicCase.getId() == null) {
				this.errorMessage = "参数无效!";
				return ERROR;
			}
			basicCase = basicCaseService.update(basicCase);
		}

		return SUCCESS;

	}

	/**
	 * 党工委成员页面跳转
	 * 
	 * @return
	 */
	@Action(value = "dispatchPartyworkMembersOperate", results = {
			@Result(name = "success", location = "/partyBuilding/baseInfos/partyworkMembersDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatchPartyworkMembersOperate() throws Exception {
		if (mode.equals(DialogMode.ADD_MODE)) {

		} else if (mode.equals(DialogMode.EDIT_MODE)) {
			if (null == partyworkMembers || null == partyworkMembers.getId()) {
				this.errorMessage = "参数无效!";
				return ERROR;
			}
			partyworkMembers = partyworkMembersService.get(partyworkMembers
					.getId());
		}
		return SUCCESS;
	}

	/**
	 * 新增成员信息
	 * 
	 * @return
	 */
	@Action(value = "addPartyWorkMembers", results = { @Result(name = "success", type = "json", params = {
			"root", "partyworkMembers", "ignoreHierarchy", "false" }) })
	@PermissionFilter(ename = "addPartyWorkMembers")
	public String addPartyWorkMembers() throws Exception {
		if (partyworkMembers == null
				|| !checkOrganization(partyworkMembers.getOrganization())) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		partyworkMembers = partyworkMembersService.add(partyworkMembers);
		return SUCCESS;
	}

	/**
	 * 删除成员信息
	 * 
	 * @return
	 */
	@Action(value = "deletePartyWorkMembers", results = { @Result(name = "success", type = "json", params = {
			"root", "partyworkMembers" }) })
	@PermissionFilter(ename = "deletePartyWorkMembers")
	public String deletePartyWorkMembers() throws Exception {
		if (partyworkMembers == null || partyworkMembers.getId() == null) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		partyworkMembersService.delete(partyworkMembers.getId());
		return SUCCESS;
	}

	/**
	 * 修改成员信息
	 * 
	 * @return
	 */
	@Action(value = "updatePartyWorkMembers", results = { @Result(name = "success", type = "json", params = {
			"root", "partyworkMembers", "ignoreHierarchy", "false" }) })
	@PermissionFilter(ename = "updatePartyWorkMembers")
	public String updatePartyWorkMembers() throws Exception {
		if (partyworkMembers == null || partyworkMembers.getId() == null
				|| !checkOrganization(partyworkMembers.getOrganization())) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		partyworkMembers = partyworkMembersService.update(partyworkMembers);
		return SUCCESS;
	}

	/**
	 * 成员列表
	 * 
	 * @return
	 */
	@Action(value = "membersList", results = { @Result(name = "success", location = "/partyBuilding/baseInfos/partyworkMembersList.jsp") })
	public String membersList() throws Exception {
		if (partyworkMembers == null
				|| !checkOrganization(partyworkMembers.getOrganization())) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		partyworkMembersList = partyworkMembersService
				.getPartyWorkMembersListByOrgId(partyworkMembers);
		return SUCCESS;

	}

	/**
	 * 成员排序
	 * 
	 * @return
	 */
	@Action(value = "membersSort", results = { @Result(name = "success", type = "json", params = {
			"root", "true" }) })
	@PermissionFilter(ename = "sortPartyWorkMembers")
	public String membersSort() throws Exception {
		partyworkMembersService.sortLeaderById(partyworkMembersList);
		return SUCCESS;
	}

	/**
	 * 根据SearchVo进行查询(提供分页、查找、排序功能)
	 * 
	 * @param pager
	 *            Pager对象
	 * 
	 * @return Pager对象
	 */
	@Action(value = "findBasicCasePagerBySearchVo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "basicCaseManagement")
	public String findBasicCasePagerBySearchVo() throws Exception {
		if (searchbasicCaseVo == null || organization.getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		Organization org = organizationDubboService.getSimpleOrgById(organization
				.getId());
		if (org == null) {
			this.errorMessage = "网格不存在";
			return ERROR;
		}
		searchbasicCaseVo.setOrgInternalCode(org.getOrgInternalCode());
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				basicCaseService.findPagerBySearchVo(searchbasicCaseVo, page,
						rows, sidx, sord), organizationDubboService,
				new String[] { "organization" }, org.getId()));
		return SUCCESS;
	}

	private boolean checkOrganization(Organization org) {
		return (org != null && org.getId() != null);
	}

	public BasicCase getBasicCase() {
		return basicCase;
	}

	public void setBasicCase(BasicCase basicCase) {
		this.basicCase = basicCase;
	}

	public SearchBasicCaseVo getSearchBasicCaseVo() {
		return searchbasicCaseVo;
	}

	public void setSearchBasicCaseVo(SearchBasicCaseVo searchbasicCaseVo) {
		this.searchbasicCaseVo = searchbasicCaseVo;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public PartyworkMembers getPartyworkMembers() {
		return partyworkMembers;
	}

	public void setPartyworkMembers(PartyworkMembers partyworkMembers) {
		this.partyworkMembers = partyworkMembers;
	}

	public List<PartyworkMembers> getPartyworkMembersList() {
		return partyworkMembersList;
	}

	public void setPartyworkMembersList(
			List<PartyworkMembers> partyworkMembersList) {
		this.partyworkMembersList = partyworkMembersList;
	}

	public String getBaseInfoType() {
		return baseInfoType;
	}

	public void setBaseInfoType(String baseInfoType) {
		this.baseInfoType = baseInfoType;
	}

}
