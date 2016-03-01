package com.tianque.baseInfo.publicPlace.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.publicPlace.domain.PublicPlace;
import com.tianque.baseInfo.publicPlace.service.PublicPlaceService;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 公共场所控制层
 */
@Namespace("/baseinfo/publicPlaceManage")
@Transactional
@Scope("request")
@Controller("publicPlaceController")
public class PublicPlaceController extends BaseAction {
	private Long organizationId;
	private Boolean hasDuplicateLocation;
	private String locationIds;

	private PublicPlace location;

	private String supervisorName; // 治安负责员名称
	private String locationType; // 场所类型名称
	private String superviceRecordName; // 巡场情况

	@Autowired
	private PublicPlaceService publicPlaceServic;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Actions({
			@Action(value = "dispatchOperate", results = {
					@Result(name = "success", location = "/baseinfo/siteinfo/publicPlace/publicPlaceDlg.jsp"),
					@Result(name = "search", location = "/baseinfo/siteinfo/publicPlace/publicPlaceSearchDlg.jsp"),
					// @Result(name = "print", location =
					// "/baseinfo/siteinfo/publicPlace/publicPlacePrintDlg.jsp"),
					@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
					@Result(name = "view", location = "/baseinfo/siteinfo/publicPlace/publicPlaceViewDlg.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "dispatchOperateForGis", results = {
					@Result(name = "view", location = "/baseinfo/siteinfo/publicPlace/publicPlaceViewDlg_gis.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String dispatchOperate() throws Exception {
		ajaxUrl = "hasDuplicatePublicPlaceLocation";
		if (null != id) {
			location = publicPlaceServic.getPlaceById(id);
			location.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(location
							.getOrganization().getId(), organizationDubboService));
		}
		if (DialogMode.SEARCH_MODE.equals(mode)) {
			return "search";
		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			return "view";
		} else if (DialogMode.PRINT_MODE.equals(mode)) {
			return "print";
		}
		return SUCCESS;
	}

	/**
	 * 解密调用
	 * 
	 * @return
	 */

	@Action(value = "dispatchOperateByEncrypt", results = {
			@Result(name = "success", location = "/baseinfo/siteinfo/publicPlace/publicPlaceDlg.jsp"),
			@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
			@Result(name = "view", location = "/baseinfo/siteinfo/publicPlace/publicPlaceViewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@EncryptAnnotation
	public String dispatchOperateByEncrypt() throws Exception {
		ajaxUrl = "hasDuplicatePublicPlaceLocation";
		if (null != id) {
			location = publicPlaceServic.getPlaceById(id);
			location.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(location
							.getOrganization().getId(), organizationDubboService));
		} else if (location != null && location.getId() != null) {
			location = publicPlaceServic.getPlaceById(location.getId());
			location.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(location
							.getOrganization().getId(), organizationDubboService));
		}
		if (DialogMode.VIEW_MODE.equals(mode)) {
			return "view";
		} else if (DialogMode.PRINT_MODE.equals(mode)) {
			return "print";
		}
		return SUCCESS;
	}

	@Action(value = "hasDuplicatePublicPlaceLocation", results = {
			@Result(name = "success", type = "json", params = { "root",
					"hasDuplicateLocation" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String hasDuplicateLocation() throws Exception {
		if (null == organizationId || null == location) {
			errorMessage = "组织机构ID,或location为空";
			return ERROR;
		}
		hasDuplicateLocation = publicPlaceServic.hasDuplicatePlace(
				organizationId, location.getPlaceName(), location.getId());
		return SUCCESS;
	}

	@Action(value = "publicPlaceList", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findGridPageByOrgIdAndIsEmphasis() throws Exception {
		Boolean isEmphasis = null;
		if (location != null) {
			isEmphasis = location.getIsEmphasis();
		}
		if (null == organizationId) {
			gridPage = new GridPage(emptyPage(rows));
		} else {
			gridPage = new GridPage(
					ControllerHelper.processAllOrgRelativeName(
							publicPlaceServic
									.findPublicPlaceForPageByOrgInternalCode(
											organizationId, page, rows, sidx,
											sord, isEmphasis),
							organizationDubboService,
							new String[] { "organization" }, organizationId));
		}
		return SUCCESS;
	}

	@EncryptAnnotation
	@PermissionFilter(ename = "deletePublicPlace")
	@Action(value = "deletePublicPlaceByIds", results = {
			@Result(name = "success", type = "json", params = { "root",
					"deleteIds" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteByIds() throws Exception {
		publicPlaceServic.deletePublicPlaceByIds(analyzeLocationIds());
		return SUCCESS;

	}

	private Long[] analyzeLocationIds() {
		String[] deleteIds = locationIds.split(",");
		List<Long> idList;
		if (deleteIds[0].equals("")) {
			idList = initTargetId(deleteIds, 1);
		} else {
			idList = initTargetId(deleteIds, 0);
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

	@PermissionFilter(ename = "addPublicPlace")
	@Action(value = "maintainPublicPlace", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainBaseInfo() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			location = publicPlaceServic.addPublicPlace(location);
		}
		if (DialogMode.EDIT_MODE.equals(mode)) {
			if (null == location) {
				return ERROR;
			}
			location = publicPlaceServic.updatePublicPlace(location);
			location.setOrganization(ControllerHelper
					.proccessRelativeOrgNameByOrg(location.getOrganization(),
							organizationDubboService));
		}
		return SUCCESS;
	}

	@Action(value = "updateEmphasiseById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"locationIds", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateEmphasiseById() throws Exception {
		location = publicPlaceServic.updateEmphasiseByIds(analyzeLocationIds(),
				location);
		return SUCCESS;
	}

	@Actions({
			@Action(value = "viewPublicPlace", results = { @Result(name = "success", location = "/baseinfo/siteinfo/publicPlace/publicPlaceView.jsp") }),
			@Action(value = "viewPublicPlaceForMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"location", "ignoreHierarchy", "false" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String viewInfo() throws Exception {
		if (null != location || null != location.getId()) {
			location = publicPlaceServic.getPlaceById(location.getId());
			location.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(location
							.getOrganization().getId(), organizationDubboService));
		}
		return SUCCESS;
	}

	@Action(value = "viewPublicPlaceByEncrypt", results = { @Result(name = "success", location = "/baseinfo/siteinfo/publicPlace/publicPlaceView.jsp") })
	@EncryptAnnotation
	public String viewInfoByEncrypt() throws Exception {
		if (null != location || null != location.getId()) {
			location = publicPlaceServic.getPlaceById(location.getId());
			location.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(location
							.getOrganization().getId(), organizationDubboService));
		}
		return SUCCESS;
	}

	private PageInfo<PublicPlace> emptyPage(int pageSize) {
		PageInfo<PublicPlace> pageInfo = new PageInfo<PublicPlace>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<PublicPlace>());
		return pageInfo;
	}

	public PublicPlace getLocation() {
		return location;
	}

	public void setLocation(PublicPlace location) {
		this.location = location;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public String getLocationType() {
		return locationType;
	}

	public String getSuperviceRecordName() {
		return superviceRecordName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public void setSuperviceRecordName(String superviceRecordName) {
		this.superviceRecordName = superviceRecordName;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Boolean getHasDuplicateLocation() {
		return hasDuplicateLocation;
	}

	public void setHasDuplicateLocation(Boolean hasDuplicateLocation) {
		this.hasDuplicateLocation = hasDuplicateLocation;
	}

	public String getLocationIds() {
		return locationIds;
	}

	public void setLocationIds(String locationIds) {
		this.locationIds = locationIds;
	}

}
