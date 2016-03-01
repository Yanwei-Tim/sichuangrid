/**
 *
 */
package com.tianque.resourcePool.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.resourcePool.domain.MyProfile;
import com.tianque.resourcePool.domain.MyProfileAttachFile;
import com.tianque.resourcePool.service.MyProfileAttachFileService;
import com.tianque.resourcePool.service.MyProfileService;
import com.tianque.resourcePool.service.SharingFilesService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Namespace("/resourcePool/sharingFilesManage")
@Scope("request")
@Controller("sharingFilesController")
public class SharingFilesController extends BaseAction {
	private MyProfile myProfile;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SharingFilesService sharingFilesService;
	@Autowired
	private MyProfileService myProfileService;
	@Autowired
	private MyProfileAttachFileService myProfileAttachFileService;

	private int searchType;
	private String searchText;
	private Long resourcePoolTypeId;
	private Long id;
	private Long internalId;
	private Date startReleaseTime;
	private Date startCreateTime;
	private Date startShareDate;
	private String ids;
	private List<MyProfileAttachFile> attachFileList = new ArrayList<MyProfileAttachFile>();

	@Actions({
			@Action(value = "dispatch", results = {
					@Result(name = "viewfile", location = "/knowledgeSharing/myProfile/viewMyProfile.jsp"),
					@Result(name = "viewPersonfing", location = "/knowledgeSharing/myProfile/viewPersonMyprofile.jsp"),
					@Result(name = "search", location = "/knowledgeSharing/sharingFiles/searchSharingFileDlg.jsp") }),
			@Action(value = "sharingFileViewforMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"myProfile", "ignoreHierarchy", "false",
							"excludeNullProperties", "true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String dispatch() throws Exception {
		if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			myProfile = myProfileService.getMyProfileById(id);
			attachFileList = myProfileAttachFileService
					.getMyProfileAttachFileByMyProfileId(id);
			return forPageview(myProfile.getResourcePoolType().getId());
		} else if (DialogMode.SEARCH_MODE.equalsIgnoreCase(mode)) {

			return "search";
		} else {
			myProfile = myProfileService.getMyProfileById(id);
			attachFileList = myProfileAttachFileService
					.getMyProfileAttachFileByMyProfileId(id);
			myProfile.setMyProfileAttachFile(attachFileList);
			return SUCCESS;
		}

	}

	private String forPageview(Long fileTypeId) {

		if (Integer.parseInt(fileTypeId.toString()) < 4) {
			return "viewfile";
		} else if (Integer.parseInt(fileTypeId.toString()) >= 4) {
			return "viewPersonfing";
		}
		return null;

	}

	/**
	 * 列表
	 * 
	 * @return
	 */
	@Action(value = "findSharingFilesList", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findSharingFilesList() throws Exception {
		gridPage = new GridPage(
				ControllerHelper.proccessRelativeOrgNameByPageInfo(
						sharingFilesService.findSharingFilesForList(
								ThreadVariable.getUser().getOrganization()
										.getId(), resourcePoolTypeId,
								searchType, page, rows, sidx, sord),
						organizationDubboService));
		return SUCCESS;

	}

	/**
	 * 通过ID查询共享资料信息
	 * 
	 * @return
	 */
	@Action(value = "findSharingFilesListByIds", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findSharingFilesListByIds() throws Exception {
		if (!StringUtil.isStringAvaliable(ids)) {
			errorMessage = "未获得文件资料数据参数";
			return ERROR;
		}
		gridPage = new GridPage(
				ControllerHelper.proccessRelativeOrgNameByPageInfo(
						sharingFilesService.findSharingFilesForListByIds(ids
								.split(",")), organizationDubboService));
		return SUCCESS;

	}

	/**
	 * 搜索
	 * 
	 * @return
	 */
	@Action(value = "fastSearchSharingFiles", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String fastSearchSharingFiles() throws Exception {
		gridPage = new GridPage(
				ControllerHelper.proccessRelativeOrgNameByPageInfo(
						sharingFilesService.fastSearchSharingFiles(
								resourcePoolTypeId, searchType, searchText,
								page, rows, sidx, sord),
						organizationDubboService));
		return SUCCESS;

	}

	/**
	 * 搜索手机端
	 * 
	 * @return
	 */
	@Action(value = "fastSearchSharingFilesforMobile", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String fastSearchSharingFilesforMobile() throws Exception {
		gridPage = new GridPage(
				ControllerHelper.proccessRelativeOrgNameByPageInfo(
						sharingFilesService.fastSearchSharingFilesforMobile(
								resourcePoolTypeId, searchType, searchText,
								page, rows, sidx, sord),
						organizationDubboService));
		return SUCCESS;

	}

	@Action(value = "searchSharingFiles", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String searchSharingFiles() throws Exception {
		gridPage = new GridPage(
				ControllerHelper.proccessRelativeOrgNameByPageInfo(
						sharingFilesService.searchSharingFiles(myProfile,
								searchType, startReleaseTime,
								resourcePoolTypeId, startCreateTime,
								startShareDate, page, rows, sidx, sord),
						organizationDubboService));
		return SUCCESS;

	}

	public MyProfile getMyProfile() {
		return myProfile;
	}

	public void setMyProfile(MyProfile myProfile) {
		this.myProfile = myProfile;
	}

	public int getSearchType() {
		return searchType;
	}

	public void setSearchType(int searchType) {
		this.searchType = searchType;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public Long getResourcePoolTypeId() {
		return resourcePoolTypeId;
	}

	public void setResourcePoolTypeId(Long resourcePoolTypeId) {
		this.resourcePoolTypeId = resourcePoolTypeId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getInternalId() {
		return internalId;
	}

	public void setInternalId(Long internalId) {
		this.internalId = internalId;
	}

	public Date getStartReleaseTime() {
		return startReleaseTime;
	}

	public void setStartReleaseTime(Date startReleaseTime) {
		this.startReleaseTime = startReleaseTime;
	}

	public Date getStartCreateTime() {
		return startCreateTime;
	}

	public void setStartCreateTime(Date startCreateTime) {
		this.startCreateTime = startCreateTime;
	}

	public Date getStartShareDate() {
		return startShareDate;
	}

	public void setStartShareDate(Date startShareDate) {
		this.startShareDate = startShareDate;
	}

	public List<MyProfileAttachFile> getAttachFileList() {
		return attachFileList;
	}

	public void setAttachFileList(List<MyProfileAttachFile> attachFileList) {
		this.attachFileList = attachFileList;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}
