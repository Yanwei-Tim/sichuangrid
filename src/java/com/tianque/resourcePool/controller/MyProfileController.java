package com.tianque.resourcePool.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
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

import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.resourcePool.domain.MyProfile;
import com.tianque.resourcePool.domain.MyProfileAttachFile;
import com.tianque.resourcePool.service.MyProfileAttachFileService;
import com.tianque.resourcePool.service.MyProfileService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.viewObject.vo.ViewObjectVo;

@Namespace("/resourcePool/myProfileManage")
@Scope("request")
@Transactional
@Controller("myProfileController")
public class MyProfileController extends BaseAction {
	private static Logger logger = LoggerFactory
			.getLogger(MyProfileController.class);
	private MyProfile myProfile;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private MyProfileService myProfileService;
	@Autowired
	private MyProfileAttachFileService myProfileAttachFileService;
	private String[] attachFiles;
	private String orgIds;
	private Long internalId;
	private int orgLevelInternalId;
	private Long resourcePoolTypeId;
	private List<MyProfileAttachFile> attachFileList = new ArrayList<MyProfileAttachFile>();
	private MyProfileAttachFile myProfileAttachFile;
	private String ids;
	private List<MyProfile> myProfiles;
	private String sendMessage;
	private Long id;
	private Date startReleaseTime;
	private Date startCreateTime;
	private int searchType;
	private String searchText;
	private String setPermissionCacheId;
	private ViewObjectVo viewObjectVo;

	private String identification;
	private Long attached;
	private Integer levelType;
	private Long userOrgId;

	private String attachFile;

	@Action(value = "dispatch", results = {
			@Result(name = "file", location = "/knowledgeSharing/myProfile/maintainMyProfileDlg.jsp"),
			@Result(name = "viewfile", location = "/knowledgeSharing/myProfile/viewMyProfile.jsp"),
			@Result(name = "viewPersonfing", location = "/knowledgeSharing/myProfile/viewPersonMyprofile.jsp"),
			@Result(name = "share", location = "/knowledgeSharing/myProfile/fileSharingDlg.jsp"),
			@Result(name = "briefing", location = "/knowledgeSharing/myProfile/maintainMyProfilePersonDlg.jsp"),
			@Result(name = "search", location = "/knowledgeSharing/myProfile/searchMyProfileDlg.jsp") })
	public String dispatch() throws Exception {

		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			myProfile.setOrganization(ThreadVariable.getOrganization());
			orgLevelInternalId = organizationDubboService
					.getFullOrgById(myProfile.getOrganization().getId())
					.getOrgLevel().getInternalId();
			return forPage();
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
			myProfile = myProfileService.getMyProfileById(id);
			myProfile.setMyProfileAttachFile(myProfileAttachFileService
					.getMyProfileAttachFileByMyProfileId(id));
			return forPage(myProfile.getResourcePoolType().getId());
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			myProfile = myProfileService.getMyProfileById(id);
			attachFileList = myProfileAttachFileService
					.getMyProfileAttachFileByMyProfileId(id);
			return forPageview(myProfile.getResourcePoolType().getId());
		} else if (DialogMode.SEARCH_MODE.equalsIgnoreCase(mode)) {

			return "search";
		} else if ("sharing".equalsIgnoreCase(mode)) {

			myProfiles = myProfileService.getMyProfileByIds(ids);

			return "share";
		}

		return ERROR;
	}

	/**
	 * 共享资料请求页面
	 * 
	 * @return
	 */
	@Action(value = "getMyProfileByIds", results = {
			@Result(name = "success", type = "json", params = { "root",
					"myProfiles", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getMyProfileByIds() throws Exception {
		myProfiles = myProfileService.getMyProfileByIds(ids);
		return SUCCESS;

	}

	private String forPageview(Long fileTypeId) {

		if (Integer.parseInt(fileTypeId.toString()) < 4) {
			return "viewfile";
		} else if (Integer.parseInt(fileTypeId.toString()) >= 4) {
			return "viewPersonfing";
		}
		return null;

	}

	// 4指两种请求页面的界限
	private String forPage() {
		if (internalId < 4) {
			return "file";
		} else if (internalId >= 4) {
			return "briefing";
		}
		return null;
	}

	private String forPage(Long fileTypeId) {
		if (Integer.parseInt(fileTypeId.toString()) < 4) {
			return "file";
		} else if (Integer.parseInt(fileTypeId.toString()) >= 4) {
			return "briefing";
		}
		return null;
	}

	@PermissionFilter(ename = "addMyProfile")
	@Action(value = "addMyProfile", results = {
			@Result(name = "success", type = "json", params = { "root",
					"myProfile", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addMyProfile() throws Exception {
		myProfileService.savaMyProfile(myProfile, attachFiles);
		return SUCCESS;

	}

	@Action(value = "addMyProfileForMobile", results = {
			@Result(name = "success", type = "json", params = { "root",
					"myProfile", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addMyProfileForMobile() throws Exception {
		createAttachFilesForMobile();
		myProfileService.savaMyProfile(myProfile, attachFiles);
		return SUCCESS;

	}

	private void createAttachFilesForMobile() throws Exception {
		if (null != attachFile && !"".equals(attachFile)) {
			String[] arrStr = attachFile.split(",");
			attachFiles = new String[arrStr.length];
			for (int i = 0; i < arrStr.length; i++) {
				if (null != arrStr[i] && !"".equals(arrStr[i])) {
					attachFiles[i] = ","
							+ URLDecoder.decode(arrStr[i], "UTF-8");
				}
			}
		}
		if (null != attachFiles && attachFiles.length == 1
				&& !attachFiles[0].startsWith(",")) {
			attachFiles[0] = "," + URLDecoder.decode(attachFiles[0], "UTF-8");
		}
	}

	/**
	 * 列表
	 */
	@Action(value = "findMyProfileForList", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findMyProfileForList() throws Exception {
		gridPage = new GridPage(
				ControllerHelper.proccessRelativeOrgNameByPageInfo(
						myProfileService.findMyProfileForList(
								resourcePoolTypeId, searchType, page, rows,
								sidx, sord), organizationDubboService));
		return SUCCESS;

	}

	@Action(value = "findMyProfileAttachFileByMyProfileId", results = { @Result(name = "success", type = "json", params = {
			"root", "attachFileList", "ignoreHierarchy", "false" }) })
	public String findMyProfileAttachFileByMyProfileId() throws Exception {
		attachFileList = myProfileAttachFileService
				.getMyProfileAttachFileByMyProfileId(resourcePoolTypeId);
		return SUCCESS;
	}

	public String downloadMyProfileAttachFile() {
		if (myProfileAttachFile == null || myProfileAttachFile.getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		myProfileAttachFile = myProfileAttachFileService
				.getSimpleMyProfileAttachFileById(myProfileAttachFile.getId());
		if (myProfileAttachFile == null) {
			this.errorMessage = "未找到对应的附件";
			return ERROR;
		}
		try {
			inputStream = new java.io.FileInputStream(FileUtil.getWebRoot()
					+ File.separator + myProfileAttachFile.getFileActualUrl());
			downloadFileName = new String(myProfileAttachFile.getFileName()
					.getBytes("gbk"), "ISO8859-1");
		} catch (FileNotFoundException e) {
			logger.error("downloadMyProfileAttachFile 错误", e);
			throw new RuntimeException("文件打开失败!");
		} catch (UnsupportedEncodingException uee) {
			logger.error("downloadMyProfileAttachFile 错误", uee);
			throw new RuntimeException("文件打开失败!");
		}

		return SUCCESS;
	}

	@Action(value = "deleteMyProfileAttachFile", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteMyProfileAttachFile() throws Exception {
		if (myProfileAttachFile == null || myProfileAttachFile.getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		myProfileAttachFileService
				.deleteMyProfileAttachFileById(myProfileAttachFile.getId());
		return SUCCESS;
	}

	@Action(value = "findMyProfileById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"myProfile", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findMyProfileById() throws Exception {
		if (myProfile == null || myProfile.getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		myProfileService.getMyProfileById(myProfile.getId());
		return SUCCESS;

	}

	/**
	 * 共享资料
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "profileSharing")
	@Action(value = "addUserMyProfilePermission", results = {
			@Result(name = "success", type = "json", params = { "root",
					"myProfile", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addUserMyProfilePermission() throws Exception {
		myProfileService.addUserMyProfilePermission(ids,
				Long.valueOf(sendMessage), resourcePoolTypeId,
				setPermissionCacheId);
		return SUCCESS;

	}

	/**
	 * 共享资料手机版
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "profileSharing")
	@Action(value = "addUserMyProfilePermissionForMobile", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false", "excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addUserMyProfilePermissionForMobile() throws Exception {
		myProfileService.addUserMyProfilePermissionForMobile(ids,
				Long.valueOf(sendMessage), resourcePoolTypeId, levelType,
				userOrgId);
		return SUCCESS;

	}

	@PermissionFilter(ename = "updateMyProfile")
	@Action(value = "updateMyProfile", results = {
			@Result(name = "success", type = "json", params = { "root",
					"myProfile", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateMyProfile() throws Exception {

		myProfile = myProfileService.updateMyprofile(myProfile, attachFiles);
		return SUCCESS;

	}

	@PermissionFilter(ename = "deleteMyProfile")
	@Action(value = "deleteMyProfile", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteMyProfile() throws Exception {
		myProfileService.deleteMyProfileById(ids);
		return SUCCESS;

	}

	/**
	 * 取消共享
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "profileCancelSharing")
	@Action(value = "cancelSharingMyProfile", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String cancelSharingMyProfile() throws Exception {
		myProfileService.cancelSharingMyProfile(ids);
		return SUCCESS;

	}

	@Action(value = "searchMyProfile", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String searchMyProfile() throws Exception {
		gridPage = new GridPage(
				ControllerHelper.proccessRelativeOrgNameByPageInfo(
						myProfileService
								.searchMyProfile(myProfile, searchType,
										startReleaseTime, resourcePoolTypeId,
										startCreateTime, attached, page, 20,
										sidx, sord), organizationDubboService));
		return SUCCESS;

	}

	@Action(value = "fastSearchMyProfile", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String fastSearchMyProfile() throws Exception {
		gridPage = new GridPage(
				ControllerHelper.proccessRelativeOrgNameByPageInfo(
						myProfileService.fastSearchMyProfile(
								resourcePoolTypeId, searchType, searchText,
								page, rows, sidx, sord), organizationDubboService));
		return SUCCESS;

	}

	public MyProfile getMyProfile() {
		return myProfile;
	}

	public void setMyProfile(MyProfile myProfile) {
		this.myProfile = myProfile;
	}

	public String[] getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(String[] attachFiles) {
		this.attachFiles = attachFiles;
	}

	public String getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}

	public Long getInternalId() {
		return internalId;
	}

	public void setInternalId(Long internalId) {
		this.internalId = internalId;
	}

	public int getOrgLevelInternalId() {
		return orgLevelInternalId;
	}

	public void setOrgLevelInternalId(int orgLevelInternalId) {
		this.orgLevelInternalId = orgLevelInternalId;
	}

	public Long getResourcePoolTypeId() {
		return resourcePoolTypeId;
	}

	public void setResourcePoolTypeId(Long resourcePoolTypeId) {
		this.resourcePoolTypeId = resourcePoolTypeId;
	}

	public List<MyProfileAttachFile> getAttachFileList() {
		return attachFileList;
	}

	public void setAttachFileList(List<MyProfileAttachFile> attachFileList) {
		this.attachFileList = attachFileList;
	}

	public MyProfileAttachFile getMyProfileAttachFile() {
		return myProfileAttachFile;
	}

	public void setMyProfileAttachFile(MyProfileAttachFile myProfileAttachFile) {
		this.myProfileAttachFile = myProfileAttachFile;
	}

	public List<MyProfile> getMyProfiles() {
		return myProfiles;
	}

	public void setMyProfiles(List<MyProfile> myProfiles) {
		this.myProfiles = myProfiles;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getSendMessage() {
		return sendMessage;
	}

	public void setSendMessage(String sendMessage) {
		this.sendMessage = sendMessage;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getSetPermissionCacheId() {
		return setPermissionCacheId;
	}

	public void setSetPermissionCacheId(String setPermissionCacheId) {
		this.setPermissionCacheId = setPermissionCacheId;
	}

	public ViewObjectVo getViewObjectVo() {
		return viewObjectVo;
	}

	public void setViewObjectVo(ViewObjectVo viewObjectVo) {
		this.viewObjectVo = viewObjectVo;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public Long getAttached() {
		return attached;
	}

	public void setAttached(Long attached) {
		this.attached = attached;
	}

	public Integer getLevelType() {
		return levelType;
	}

	public void setLevelType(Integer levelType) {
		this.levelType = levelType;
	}

	public Long getUserOrgId() {
		return userOrgId;
	}

	public void setUserOrgId(Long userOrgId) {
		this.userOrgId = userOrgId;
	}

	public String getAttachFile() {
		return attachFile;
	}

	public void setAttachFile(String attachFile) {
		this.attachFile = attachFile;
	}

}
