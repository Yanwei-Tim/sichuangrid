package com.tianque.partyBuilding.activityRecords.controller;

import java.io.File;
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

import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.FileUtil;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PartyOrgInfo;
import com.tianque.partyBuilding.activityRecords.constant.ActivityRecordsType;
import com.tianque.partyBuilding.activityRecords.constant.OrganizationType;
import com.tianque.partyBuilding.activityRecords.domain.ActivityRecords;
import com.tianque.partyBuilding.activityRecords.domain.ActivityRecordsAttachFiles;
import com.tianque.partyBuilding.activityRecords.domain.vo.ActivityRecordsVo;
import com.tianque.partyBuilding.activityRecords.domain.vo.SearchOrganizationVo;
import com.tianque.partyBuilding.activityRecords.service.ActivityRecordsService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 组织活动记录控制类
 * */

@Namespace("/partyBuilding/activityRecordManage")
@Scope("request")
@Controller("activityRecordsController")
@Transactional
public class ActivityRecordsController extends BaseAction {
	private static Logger logger = LoggerFactory
			.getLogger(ActivityRecordsController.class);

	@Autowired
	private ActivityRecordsService activityRecordsService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private String deleteIds;
	private ActivityRecordsVo activityRecordsVo;
	private ActivityRecords activityRecords;
	private String partyOrganizationType;
	private Long orgId;
	private String mode;
	private ActivityRecordsAttachFiles activityRecordsAttachFiles;

	private String[] attachFiles;// 附件

	// 搜索组织的时候的组织名称
	private String partyOrganizationName;
	// 组织id
	private Long partyOrganizationId;

	// 权限名
	private String partyModule;
	private List<SearchOrganizationVo> searchOrganizationVos;

	/** list 显示 */
	@Action(value = "activityRecordList", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String activityRecordList() throws Exception {
		if (orgId == null || partyOrganizationType == null
				|| "".equals(partyOrganizationType)) {
			errorMessage = "参数错误";
			return ERROR;
		} else {
			gridPage = new GridPage(
					ControllerHelper.processAllOrgRelativeName(
							this.activityRecordsService
									.findActivityRecordsForPageByOrgId(
											orgId,
											organizationDubboService
													.getOrgInternalCodeById(orgId),
											partyOrganizationType,
											partyOrganizationId, page, rows,
											sidx, sord), organizationDubboService,
							new String[] { "organization" }, orgId));
		}
		return SUCCESS;
	}

	/** 得到一条记录 */
	@Action(value = "getActivityRecordById", results = { @Result(type = "json", name = "success", params = {
			"root", "activityRecords", "ignoreHierarchy", "false" }) })
	public String getActivityRecordById() throws Exception {
		try {
			if ("".equals(partyOrganizationType.trim())
					|| activityRecords == null
					|| activityRecords.getId() == null) {
				errorMessage = "参数错误";
				return ERROR;
			}
			activityRecords = activityRecordsService.getActivityRecordById(
					activityRecords.getId(), partyOrganizationType);
		} catch (Exception e) {
			this.errorMessage = "操作出错，请联系管理员";
			logger.error("getActivityRecordById: ", e);
			return ERROR;
		}
		return "success";
	}

	/** id加密删除 */
	@EncryptAnnotation
	@Action(value = "deleteActivityRecordByEncrypt", results = {
			@Result(name = "success", type = "json", params = { "root",
					"deleteIds" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteActivityRecordByEncrypt() throws Exception {
		activityRecordsService.deleteActivityRecordByIds(analyzeDeleteIds());
		return SUCCESS;
	}

	/** 删除 */
	@Action(value = "deleteActivityRecord", results = {
			@Result(name = "success", type = "json", params = { "root",
					"deleteIds" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteActivityRecord() throws Exception {
		activityRecordsService.deleteActivityRecordByIds(analyzeDeleteIds());
		return SUCCESS;
	}

	private List<Long> analyzeDeleteIds() {
		String[] deleteId = deleteIds.split(",");
		List<Long> idList;
		if (deleteId[0].equals("")) {
			idList = initTargetId(deleteId, 1);
		} else {
			idList = initTargetId(deleteId, 0);
		}
		return idList;
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

	/** 选取党组织 */
	@Action(value = "findOrganizationByType", results = { @Result(type = "json", name = "success", params = {
			"root", "searchOrganizationVos", "ignoreHierarchy", "false" }) })
	public String findOrganizationByType() throws Exception {
		searchOrganizationVos = activityRecordsService.findOrganizationByType(
				orgId, organizationDubboService.getOrgInternalCodeById(orgId),
				partyOrganizationType, partyOrganizationName);
		return SUCCESS;
	}

	/** id加密 控制跳转 */
	@EncryptAnnotation
	@Action(value = "dispatchOperateByEncrypt", results = {
			@Result(name = "list", location = "/partyBuilding/activityRecords/activityRecordsList.jsp"),
			@Result(name = "success", location = "/partyBuilding/activityRecords/activityRecordsMaintainDlg.jsp"),
			@Result(name = "view", location = "/partyBuilding/activityRecords/activityRecordView.jsp"),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String dispatchOperateByEncrypt() throws Exception {
		if (ActivityRecordsType.ACTIVITYMODE_ADD.equals(mode)) {
			activityRecords = new ActivityRecords();
			activityRecords
					.setOrganization(ControllerHelper
							.proccessRelativeOrgNameByOrgId(orgId,
									organizationDubboService));
			activityRecords.setOrganizationType(partyOrganizationType);
			activityRecords.setOrganizationId(partyOrganizationId);
			activityRecords.setOrganizationName(partyOrganizationName);
		} else if (ActivityRecordsType.ACTIVITYMODE_EDIT.equals(mode)) {
			activityRecords = activityRecordsService.getActivityRecordById(id,
					partyOrganizationType);

		} else if (ActivityRecordsType.ACTIVITYMODE_VIEW.equals(mode)) {
			activityRecords = activityRecordsService.getActivityRecordById(id,
					partyOrganizationType);
			return ActivityRecordsType.ACTIVITYMODE_VIEW;
		} else if (ActivityRecordsType.ACTIVITYMODE_SEARCH.equals(mode)) {
			activityRecordsVo = new ActivityRecordsVo();
			activityRecordsVo
					.setOrganization(ControllerHelper
							.proccessRelativeOrgNameByOrgId(orgId,
									organizationDubboService));
			activityRecordsVo.setOrganizationType(partyOrganizationType);
			activityRecordsVo.setOrganizationId(partyOrganizationId);
			activityRecordsVo.setOrganizationName(partyOrganizationName);
			return ActivityRecordsType.ACTIVITYMODE_SEARCH;
		} else if (ActivityRecordsType.ACTIVITYMODE_LIST.equals(mode)) {
			if (null != partyOrganizationId) {
			}
			return ActivityRecordsType.ACTIVITYMODE_LIST;
		}

		return SUCCESS;
	}

	/** 控制跳转 */
	@Action(value = "dispatchOperate", results = {
			@Result(name = "list", location = "/partyBuilding/activityRecords/activityRecordsList.jsp"),
			@Result(name = "success", location = "/partyBuilding/activityRecords/activityRecordsMaintainDlg.jsp"),
			@Result(name = "view", location = "/partyBuilding/activityRecords/activityRecordView.jsp"),
			@Result(name = "search", location = "/partyBuilding/activityRecords/searchActivityRecordsDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String dispatchOperate() throws Exception {
		if (ActivityRecordsType.ACTIVITYMODE_ADD.equals(mode)) {
			activityRecords = new ActivityRecords();
			activityRecords
					.setOrganization(ControllerHelper
							.proccessRelativeOrgNameByOrgId(orgId,
									organizationDubboService));
			activityRecords.setOrganizationType(partyOrganizationType);
			activityRecords.setOrganizationId(partyOrganizationId);
			activityRecords.setOrganizationName(partyOrganizationName);
		} else if (ActivityRecordsType.ACTIVITYMODE_EDIT.equals(mode)) {
			activityRecords = activityRecordsService.getActivityRecordById(id,
					partyOrganizationType);

		} else if (ActivityRecordsType.ACTIVITYMODE_VIEW.equals(mode)) {
			activityRecords = activityRecordsService.getActivityRecordById(id,
					partyOrganizationType);
			return ActivityRecordsType.ACTIVITYMODE_VIEW;
		} else if (ActivityRecordsType.ACTIVITYMODE_SEARCH.equals(mode)) {
			activityRecordsVo = new ActivityRecordsVo();
			activityRecordsVo
					.setOrganization(ControllerHelper
							.proccessRelativeOrgNameByOrgId(orgId,
									organizationDubboService));
			activityRecordsVo.setOrganizationType(partyOrganizationType);
			activityRecordsVo.setOrganizationId(partyOrganizationId);
			activityRecordsVo.setOrganizationName(partyOrganizationName);
			return ActivityRecordsType.ACTIVITYMODE_SEARCH;
		} else if (ActivityRecordsType.ACTIVITYMODE_LIST.equals(mode)) {
			if (null != partyOrganizationId) {
			}
			return ActivityRecordsType.ACTIVITYMODE_LIST;
		}
		return SUCCESS;
	}

	/** 对组织活动的操作（新增，修改） */
	@Action(value = "maintainActivityRecord", results = { @Result(type = "json", name = "success", params = {
			"root", "activityRecords", "ignoreHierarchy", "false" }) })
	public String maintainActivityRecord() throws Exception {
		if (ActivityRecordsType.ACTIVITYMODE_ADD.equals(mode)) {
			activityRecords.setOrganization(ControllerHelper
					.proccessRelativeOrgNameByOrg(
							activityRecords.getOrganization(),
							organizationDubboService));
			activityRecords = activityRecordsService
					.addActivityRecords(activityRecords);
			if (activityRecords.getIsAttachment()) {
				activityRecords.setActivityRecordFiles(activityRecordsService
						.addAttachFileByActivityRecordsId(
								activityRecords.getId(), attachFiles));
			}
		} else if (ActivityRecordsType.ACTIVITYMODE_EDIT.equals(mode)) {
			activityRecords = activityRecordsService.updateActivityRecord(
					activityRecords, attachFiles);
		}
		return SUCCESS;
	}

	/** 根据条件查询 */
	@Action(value = "searchActivityRecord", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String searchActivityRecord() throws Exception {

		if (null == orgId) {
			gridPage = new GridPage(new PageInfo<ActivityRecords>());
			return SUCCESS;
		}
		Organization organization = organizationDubboService.getSimpleOrgById(orgId);
		activityRecordsVo.setOrganization(organization);
		activityRecordsVo.setName(OrganizationType
				.getOrganizationNameByType(activityRecordsVo
						.getOrganizationType()));
		activityRecordsVo.setTable(activityRecordsVo.getOrganizationType());
		activityRecordsVo.setSortField(sidx);
		activityRecordsVo.setOrder(sord);
		PageInfo<PartyOrgInfo> pageInfo = ControllerHelper
				.processAllOrgRelativeName(activityRecordsService
						.searchActivityRecordsBySearchVo(page, rows,
								activityRecordsVo), organizationDubboService,
						new String[] { "organization" }, orgId);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	/** 附件 */
	@Action(value = "downloadActivityRecordsAttachFiles")
	public String downloadActivityRecordsAttachFiles() throws Exception {
		if (null == activityRecordsAttachFiles
				|| null == activityRecordsAttachFiles.getId()) {
			errorMessage = "参数不正确";
			return ERROR;
		}
		activityRecordsAttachFiles = activityRecordsService
				.getActivityRecordsAttachFilesById(activityRecordsAttachFiles
						.getId());
		if (null == activityRecordsAttachFiles) {
			errorMessage = "组织活动附件不存在";
			return ERROR;
		}
		inputStream = new java.io.FileInputStream(FileUtil.getWebRoot()
				+ File.separator
				+ activityRecordsAttachFiles.getFileActualUrl());
		downloadFileName = new String(activityRecordsAttachFiles.getFileName()
				.getBytes("gbk"), "ISO8859-1");
		return STREAM_SUCCESS;
	}

	/** 删除附件 */
	@Action(value = "deleteActivityRecordsAttachFiles", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteActivityRecordsAttachFiles() throws Exception {
		if (null == activityRecordsAttachFiles
				|| null == activityRecordsAttachFiles.getId()) {
			errorMessage = "所传参数不正确";
			return ERROR;
		}
		activityRecordsService
				.deleteActivityRecordsAttachFilesById(activityRecordsAttachFiles
						.getId());
		return SUCCESS;
	}

	public ActivityRecordsVo getActivityRecordsVo() {
		return activityRecordsVo;
	}

	public void setActivityRecordsVo(ActivityRecordsVo activityRecordsVo) {
		this.activityRecordsVo = activityRecordsVo;
	}

	public ActivityRecords getActivityRecords() {
		return activityRecords;
	}

	public void setActivityRecords(ActivityRecords activityRecords) {
		this.activityRecords = activityRecords;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String[] getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(String[] attachFiles) {
		this.attachFiles = attachFiles;
	}

	public List<SearchOrganizationVo> getSearchOrganizationVos() {
		return searchOrganizationVos;
	}

	public void setSearchOrganizationVos(
			List<SearchOrganizationVo> searchOrganizationVos) {
		this.searchOrganizationVos = searchOrganizationVos;
	}

	public ActivityRecordsAttachFiles getActivityRecordsAttachFiles() {
		return activityRecordsAttachFiles;
	}

	public void setActivityRecordsAttachFiles(
			ActivityRecordsAttachFiles activityRecordsAttachFiles) {
		this.activityRecordsAttachFiles = activityRecordsAttachFiles;
	}

	public String getDeleteIds() {
		return deleteIds;
	}

	public void setDeleteIds(String deleteIds) {
		this.deleteIds = deleteIds;
	}

	public String getPartyOrganizationType() {
		return partyOrganizationType;
	}

	public void setPartyOrganizationType(String partyOrganizationType) {
		this.partyOrganizationType = partyOrganizationType;
	}

	public String getPartyOrganizationName() {
		return partyOrganizationName;
	}

	public void setPartyOrganizationName(String partyOrganizationName) {
		this.partyOrganizationName = partyOrganizationName;
	}

	public Long getPartyOrganizationId() {
		return partyOrganizationId;
	}

	public void setPartyOrganizationId(Long partyOrganizationId) {
		this.partyOrganizationId = partyOrganizationId;
	}

	public String getPartyModule() {
		return partyModule;
	}

	public void setPartyModule(String partyModule) {
		this.partyModule = partyModule;
	}

}
