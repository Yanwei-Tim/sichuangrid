package com.tianque.partyBuilding.baseInfos.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.BaseFileUploadAction;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.Organization;
import com.tianque.partyBuilding.baseInfos.domain.DistrictBasiccase;
import com.tianque.partyBuilding.baseInfos.domain.vo.SearchDistrictBasiccaseVo;
import com.tianque.partyBuilding.baseInfos.service.CaseImageUploadService;
import com.tianque.partyBuilding.baseInfos.service.DistrictBasiccaseService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 社区基本情况表:服务前端控制类
 * 
 * @author
 * @date 2014-01-14 15:24:54
 */
@Namespace("/districtBasiccaseManage")
@Scope("request")
@Controller("districtBasiccaseController")
public class DistrictBasiccaseController extends BaseFileUploadAction {

	private static Logger logger = LoggerFactory
			.getLogger(DistrictBasiccaseController.class);

	@Autowired
	private DistrictBasiccaseService districtBasiccaseService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private CaseImageUploadService caseImageUploadService;

	private DistrictBasiccase districtBasiccase = new DistrictBasiccase();
	private SearchDistrictBasiccaseVo searchdistrictBasiccaseVo;
	private Organization organization;

	/**
	 * 根据id和组织机构Id查询基本情况信息
	 * 
	 * @return
	 */
	@Action(value = "getDistrictBasiccaseByIdAndOrgId", results = {
			@Result(name = "districtCase", type = "json", params = { "root",
					"districtBasiccase", "ignoreHierarchy", "false" }),
			@Result(name = "editPartyOrgsAndMembersCase", location = "/partyBuilding/baseInfos/districtDlg/partyOrgsAndMembersCaseDlg.jsp"),
			@Result(name = "editAdministrativeCases", location = "/partyBuilding/baseInfos/districtDlg/administrativeCasesDlg.jsp"),
			@Result(name = "editStreetPartySituation", location = "/partyBuilding/baseInfos/districtDlg/streetPartySituationDlg.jsp"),
			@Result(name = "editSystemConstruction", location = "/partyBuilding/baseInfos/districtDlg/systemConstructionDlg.jsp"),
			@Result(name = "editRegionalPartySituation", location = "/partyBuilding/baseInfos/districtDlg/regionalPartySituationDlg.jsp"),
			@Result(name = "editVolunteersSituation", location = "/partyBuilding/baseInfos/districtDlg/volunteersSituationDlg.jsp"),
			@Result(name = "eidtDoubleRegistrationSituation", location = "/partyBuilding/baseInfos/districtDlg/doubleRegistrationSituationDlg.jsp"),
			@Result(name = "zoomin", location = "/partyBuilding/baseInfos/distinctPhotosList.jsp"),
			@Result(name = "editPhotos", location = "/partyBuilding/baseInfos/maintainPhotosByDistrict.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getDistrictBasiccaseByIdAndOrgId() throws Exception {
		if (organization == null || organization.getId() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		organization = organizationDubboService.getSimpleOrgById(organization
				.getId());
		districtBasiccase.setOrganization(organization);
		districtBasiccase = districtBasiccaseService
				.findDistrictBasiccaseByIdAndOrgId(districtBasiccase.getId(),
						organization.getId());
		if (districtBasiccase == null) {
			districtBasiccase = new DistrictBasiccase();
			districtBasiccase.setOrganization(organization);
			districtBasiccase.setCaseImageUploads(caseImageUploadService
					.getCaseImageUploadsByOrgId(organization.getId()));
		} else {
			districtBasiccase.setCaseImageUploads(caseImageUploadService
					.getCaseImageUploadsByOrgId(organization.getId()));
		}
		return mode;
	}

	/**
	 * 新增、修改基本情况信息
	 * 
	 * @return
	 */
	@Action(value = "addOrUpdateDistrictBasiccase", results = {
			@Result(name = "success", type = "json", params = { "root",
					"districtBasiccase", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "editCase")
	public String addOrUpdateDistrictBasiccase() throws Exception {
		if (districtBasiccase == null
				|| !checkOrganization(districtBasiccase.getOrganization())) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		if (districtBasiccase.getId() == null) {
			districtBasiccase = districtBasiccaseService.add(districtBasiccase);
		} else {
			if (districtBasiccase.getId() == null) {
				this.errorMessage = "参数无效!";
				return ERROR;
			}
			districtBasiccase = districtBasiccaseService
					.update(districtBasiccase);
		}

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
	@Action(value = "findDistrictBasiccasePagerBySearchVo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "districtBasiccaseManagement")
	public String findDistrictBasiccasePagerBySearchVo() throws Exception {
		if (searchdistrictBasiccaseVo == null || organization.getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		Organization org = organizationDubboService.getSimpleOrgById(organization
				.getId());
		if (org == null) {
			this.errorMessage = "网格不存在";
			return ERROR;
		}
		searchdistrictBasiccaseVo.setOrgInternalCode(org.getOrgInternalCode());
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				districtBasiccaseService.findPagerBySearchVo(
						searchdistrictBasiccaseVo, page, rows, sidx, sord),
				organizationDubboService, new String[] { "organization" }, org
						.getId()));
		return SUCCESS;
	}

	private boolean checkOrganization(Organization org) {
		return (org != null && org.getId() != null);
	}

	public DistrictBasiccase getDistrictBasiccase() {
		return districtBasiccase;
	}

	public void setDistrictBasiccase(DistrictBasiccase districtBasiccase) {
		this.districtBasiccase = districtBasiccase;
	}

	public SearchDistrictBasiccaseVo getSearchDistrictBasiccaseVo() {
		return searchdistrictBasiccaseVo;
	}

	public void setSearchDistrictBasiccaseVo(
			SearchDistrictBasiccaseVo searchdistrictBasiccaseVo) {
		this.searchdistrictBasiccaseVo = searchdistrictBasiccaseVo;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

}
