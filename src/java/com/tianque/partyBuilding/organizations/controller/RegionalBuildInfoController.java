package com.tianque.partyBuilding.organizations.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.FileUtil;
import com.tianque.core.vo.GridPage;
import com.tianque.partyBuilding.organizations.constant.RegionalPartyType;
import com.tianque.partyBuilding.organizations.domain.ClaimRegionalBuildInfo;
import com.tianque.partyBuilding.organizations.domain.RegionalBuildInfo;
import com.tianque.partyBuilding.organizations.domain.RegionalBuildInfoAttachFile;
import com.tianque.partyBuilding.organizations.domain.vo.RegionalBuildInfoVo;
import com.tianque.partyBuilding.organizations.service.RegionalBuildInfoService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 区域联建情况控制类
 * */
@Namespace("/partyBuilding/regionalBuildInfoManage")
@Scope("request")
@Controller("regionalBuildInfoController")
public class RegionalBuildInfoController extends BaseAction {
	@Autowired
	private RegionalBuildInfoService regionalBuildInfoService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private RegionalBuildInfoVo regionalBuildInfoVo;
	private RegionalBuildInfo regionalBuildInfo;
	private RegionalBuildInfoAttachFile regionalBuildInfoAttachFile;
	private String deleteIds;
	private Long regionalBuildInfoId;
	private ClaimRegionalBuildInfo claimRegionalBuildInfo;
	private String claimMode;
	private String[] attachFiles;// 附件

	/**
	 * 根据条件查询区域联建情况同时根据传的参数不同可以用于list显示
	 * */
	@Action(value = "regionalBuildInfoList", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String regionalBuildInfoList() throws Exception {
		if (null == regionalBuildInfoVo
				|| regionalBuildInfoVo.getOrganization().getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		} else {
			gridPage = new GridPage(
					ControllerHelper.processAllOrgRelativeName(
							this.regionalBuildInfoService
									.findRegionalBuildInfoBySearchVo(
											regionalBuildInfoVo, page, rows,
											sidx, sord), organizationDubboService,
							new String[] { "organization" },
							regionalBuildInfoVo.getOrganization().getId()));
		}

		return SUCCESS;
	}

	/** 得到一条记录 */
	@Action(value = "getRegionalBuildInfoById", results = { @Result(type = "json", name = "success", params = {
			"root", "regionalBuildInfo", "ignoreHierarchy", "false" }) })
	public String getRegionalBuildInfoById() throws Exception {
		if (regionalBuildInfo == null || regionalBuildInfo.getId() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		regionalBuildInfo = regionalBuildInfoService
				.getRegionalBuildInfoById(regionalBuildInfo.getId());
		return "success";
	}

	/** id加密控制跳转 */
	@EncryptAnnotation
	@Action(value = "dispatchOperateByEncrypt", results = {
			@Result(name = "success", location = "/partyBuilding/organizations/regionalBuildInfoManage/regionalBuildInfoMaintainDlg.jsp"),
			@Result(name = "view", location = "/partyBuilding/organizations/regionalBuildInfoManage/regionalBuildInfoView.jsp"),
			@Result(name = "claim", location = "/partyBuilding/organizations/regionalBuildInfoManage/claimRegionalBuildInfoMaintainDlg.jsp"),
			@Result(name = "list", location = "/partyBuilding/organizations/regionalBuildInfoManage/claimRegionalBuildInfoList.jsp"),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String dispatchOperateByEncrypt() throws Exception {
		if (RegionalPartyType.REGIONALPARTY_ADD.equals(mode)) {
			regionalBuildInfo.setOrganization(ControllerHelper
					.proccessRelativeOrgNameByOrgId(regionalBuildInfo
							.getOrganization().getId(), organizationDubboService));
		} else if (RegionalPartyType.REGIONALPARTY_EDIT.equals(mode)) {
			regionalBuildInfo = regionalBuildInfoService
					.getRegionalBuildInfoById(id);
			regionalBuildInfo.setOrganization(ControllerHelper
					.proccessRelativeOrgNameByOrgId(regionalBuildInfo
							.getOrganization().getId(), organizationDubboService));
		} else if (RegionalPartyType.REGIONALPARTY_VIEW.equals(mode)) {
			regionalBuildInfo = regionalBuildInfoService
					.getRegionalBuildInfoById(id);
			regionalBuildInfo.setOrganization(ControllerHelper
					.proccessRelativeOrgNameByOrgId(regionalBuildInfo
							.getOrganization().getId(), organizationDubboService));
			return mode;
		} else if (RegionalPartyType.REGIONALPARTY_SEARCH.equals(mode)) {
			regionalBuildInfoVo.setOrganization(ControllerHelper
					.proccessRelativeOrgNameByOrgId(regionalBuildInfoVo
							.getOrganization().getId(), organizationDubboService));
			return mode;
		} else if (RegionalPartyType.REGIONALPARTY_CLAIM.equals(mode)) {
			if (RegionalPartyType.REGIONALPARTY_ADD.equals(claimMode)) {
				if (regionalBuildInfoId == null) {
					errorMessage = "参数错误";
					return ERROR;
				}
				claimRegionalBuildInfo = new ClaimRegionalBuildInfo();
				claimRegionalBuildInfo
						.setRegionalBuildInfoId(regionalBuildInfoId);
			}
			if (RegionalPartyType.REGIONALPARTY_EDIT.equals(claimMode)) {
				if (id == null) {
					errorMessage = "参数错误";
					return ERROR;
				}
				claimRegionalBuildInfo = regionalBuildInfoService
						.getClaimRegionalBuildInfoById(id);
			}

			return mode;
		} else if (RegionalPartyType.REGIONALPARTY_LIST.equals(mode)) {
			if (regionalBuildInfoId == null) {
				errorMessage = "参数错误";
				return ERROR;
			}
			return mode;
		}

		return SUCCESS;
	}

	/** 控制跳转 */
	@Action(value = "dispatchOperate", results = {
			@Result(name = "success", location = "/partyBuilding/organizations/regionalBuildInfoManage/regionalBuildInfoMaintainDlg.jsp"),
			@Result(name = "view", location = "/partyBuilding/organizations/regionalBuildInfoManage/regionalBuildInfoView.jsp"),
			@Result(name = "search", location = "/partyBuilding/organizations/regionalBuildInfoManage/searchRegionalBuildInfoDlg.jsp"),
			@Result(name = "claim", location = "/partyBuilding/organizations/regionalBuildInfoManage/claimRegionalBuildInfoMaintainDlg.jsp"),
			@Result(name = "list", location = "/partyBuilding/organizations/regionalBuildInfoManage/claimRegionalBuildInfoList.jsp"),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String dispatchOperate() throws Exception {
		if (RegionalPartyType.REGIONALPARTY_ADD.equals(mode)) {
			regionalBuildInfo.setOrganization(ControllerHelper
					.proccessRelativeOrgNameByOrgId(regionalBuildInfo
							.getOrganization().getId(), organizationDubboService));
		} else if (RegionalPartyType.REGIONALPARTY_EDIT.equals(mode)) {
			regionalBuildInfo = regionalBuildInfoService
					.getRegionalBuildInfoById(id);
			regionalBuildInfo.setOrganization(ControllerHelper
					.proccessRelativeOrgNameByOrgId(regionalBuildInfo
							.getOrganization().getId(), organizationDubboService));
		} else if (RegionalPartyType.REGIONALPARTY_VIEW.equals(mode)) {
			regionalBuildInfo = regionalBuildInfoService
					.getRegionalBuildInfoById(id);
			regionalBuildInfo.setOrganization(ControllerHelper
					.proccessRelativeOrgNameByOrgId(regionalBuildInfo
							.getOrganization().getId(), organizationDubboService));
			return mode;
		} else if (RegionalPartyType.REGIONALPARTY_SEARCH.equals(mode)) {
			regionalBuildInfoVo.setOrganization(ControllerHelper
					.proccessRelativeOrgNameByOrgId(regionalBuildInfoVo
							.getOrganization().getId(), organizationDubboService));
			return mode;
		} else if (RegionalPartyType.REGIONALPARTY_CLAIM.equals(mode)) {
			if (RegionalPartyType.REGIONALPARTY_ADD.equals(claimMode)) {
				if (regionalBuildInfoId == null) {
					errorMessage = "参数错误";
					return ERROR;
				}
				claimRegionalBuildInfo = new ClaimRegionalBuildInfo();
				claimRegionalBuildInfo
						.setRegionalBuildInfoId(regionalBuildInfoId);
			}
			if (RegionalPartyType.REGIONALPARTY_EDIT.equals(claimMode)) {
				if (id == null) {
					errorMessage = "参数错误";
					return ERROR;
				}
				claimRegionalBuildInfo = regionalBuildInfoService
						.getClaimRegionalBuildInfoById(id);
			}

			return mode;
		} else if (RegionalPartyType.REGIONALPARTY_LIST.equals(mode)) {
			if (regionalBuildInfoId == null) {
				errorMessage = "参数错误";
				return ERROR;
			}
			return mode;
		}

		return SUCCESS;
	}

	/** 对认领记录的操作（新增、修改） */
	@Action(value = "claimRegionalBuildInfo", results = { @Result(type = "json", name = "success", params = {
			"root", "claimRegionalBuildInfo", "ignoreHierarchy", "false" }) })
	public String claimRegionalBuildInfo() throws Exception {
		if (claimRegionalBuildInfo == null
				|| claimRegionalBuildInfo.getRegionalBuildInfoId() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		if (RegionalPartyType.REGIONALPARTY_ADD.equals(claimMode)) {
			claimRegionalBuildInfo = regionalBuildInfoService
					.addClaimRegionalBuildInfo(claimRegionalBuildInfo);
		} else if (RegionalPartyType.REGIONALPARTY_EDIT.equals(claimMode)) {
			claimRegionalBuildInfo = regionalBuildInfoService
					.updateClaimRegionalBuildInfo(claimRegionalBuildInfo);
		}
		return SUCCESS;
	}

	/** 附件 */
	@Action(value = "downloadRegionalBuildInfoAttachFiles")
	public String downloadRegionalBuildInfoAttachFiles() throws Exception {
		if (null == regionalBuildInfoAttachFile
				|| null == regionalBuildInfoAttachFile.getId()) {
			errorMessage = "参数不正确";
			return ERROR;
		}
		regionalBuildInfoAttachFile = regionalBuildInfoService
				.getRegionalBuildInfoAttachFileById(regionalBuildInfoAttachFile
						.getId());
		if (null == regionalBuildInfoAttachFile) {
			errorMessage = "附件不存在";
			return ERROR;
		}
		inputStream = new java.io.FileInputStream(FileUtil.getWebRoot()
				+ File.separator
				+ regionalBuildInfoAttachFile.getFileActualUrl());
		downloadFileName = new String(regionalBuildInfoAttachFile.getFileName()
				.getBytes("gbk"), "ISO8859-1");
		return STREAM_SUCCESS;
	}

	/** 对区域联建情况的操作（新增，修改） */
	@Action(value = "maintainRegionalBuildInfo", results = { @Result(type = "json", name = "success", params = {
			"root", "regionalBuildInfo", "ignoreHierarchy", "false" }) })
	public String maintainRegionalBuildInfo() throws Exception {

		if (RegionalPartyType.REGIONALPARTY_ADD.equals(mode)) {
			regionalBuildInfo.setOrganization(ControllerHelper
					.proccessRelativeOrgNameByOrg(
							regionalBuildInfo.getOrganization(),
							organizationDubboService));
			regionalBuildInfo = regionalBuildInfoService
					.addRegionalBuildInfo(regionalBuildInfo);
			if (regionalBuildInfo.getIsAttachment()) {
				regionalBuildInfo
						.setRegionalBuildInfoAttachFiles(regionalBuildInfoService
								.addAttachFileByRegionalBuildInfoId(
										regionalBuildInfo.getId(), attachFiles));
			}
		} else if (RegionalPartyType.REGIONALPARTY_EDIT.equals(mode)) {
			regionalBuildInfo = regionalBuildInfoService
					.updateRegionalBuildInfo(regionalBuildInfo, attachFiles);
		}
		return SUCCESS;
	}

	/** 区域联建认领信息列表显示 */
	@Action(value = "claimRegionalBuildInfoList", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String claimRegionalBuildInfoList() throws Exception {
		if (regionalBuildInfoId == null) {
			errorMessage = "参数不正确";
			return ERROR;
		} else {
			gridPage = new GridPage(
					this.regionalBuildInfoService
							.findClaimRegionalBuildInfoByRegionalBuildInfoId(
									regionalBuildInfoId, page, rows, sidx, sord));
		}
		return SUCCESS;
	}

	/** 删除 */
	@Action(value = "deleteClaimRegionalBuildInfo", results = {
			@Result(name = "success", type = "json", params = { "root", "id" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteClaimRegionalBuildInfo() throws Exception {
		if (id == null) {
			errorMessage = "参数不正确";
			return ERROR;
		}
		regionalBuildInfoService.deleteClaimRegionalBuildInfoById(id);
		return SUCCESS;
	}

	/** id加密删除 */
	@Action(value = "deleteRegionalBuildInfoByEncrypt", results = {
			@Result(name = "success", type = "json", params = { "root",
					"deleteIds" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@EncryptAnnotation
	public String deleteRegionalBuildInfoByEncrypt() throws Exception {
		regionalBuildInfoService
				.deleteRegionalBuildInfoByIds(analyzeDeleteIds());
		return SUCCESS;
	}

	/** 删除 */
	@Action(value = "deleteRegionalBuildInfo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"deleteIds" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteRegionalBuildInfo() throws Exception {
		regionalBuildInfoService
				.deleteRegionalBuildInfoByIds(analyzeDeleteIds());
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

	/** 删除附件 */
	@Action(value = "deleteRegionalBuildInfoAttachFile", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteRegionalBuildInfoAttachFile() throws Exception {
		if (null == regionalBuildInfoAttachFile
				|| null == regionalBuildInfoAttachFile.getId()) {
			errorMessage = "所传参数不正确";
			return ERROR;
		}
		regionalBuildInfoService
				.deleteRegionalBuildInfoAttachFileById(regionalBuildInfoAttachFile
						.getId());
		return SUCCESS;
	}

	public RegionalBuildInfoVo getRegionalBuildInfoVo() {
		return regionalBuildInfoVo;
	}

	public void setRegionalBuildInfoVo(RegionalBuildInfoVo regionalBuildInfoVo) {
		this.regionalBuildInfoVo = regionalBuildInfoVo;
	}

	public RegionalBuildInfo getRegionalBuildInfo() {
		return regionalBuildInfo;
	}

	public void setRegionalBuildInfo(RegionalBuildInfo regionalBuildInfo) {
		this.regionalBuildInfo = regionalBuildInfo;
	}

	public RegionalBuildInfoAttachFile getRegionalBuildInfoAttachFile() {
		return regionalBuildInfoAttachFile;
	}

	public void setRegionalBuildInfoAttachFile(
			RegionalBuildInfoAttachFile regionalBuildInfoAttachFile) {
		this.regionalBuildInfoAttachFile = regionalBuildInfoAttachFile;
	}

	public String[] getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(String[] attachFiles) {
		this.attachFiles = attachFiles;
	}

	public String getDeleteIds() {
		return deleteIds;
	}

	public void setDeleteIds(String deleteIds) {
		this.deleteIds = deleteIds;
	}

	public Long getRegionalBuildInfoId() {
		return regionalBuildInfoId;
	}

	public void setRegionalBuildInfoId(Long regionalBuildInfoId) {
		this.regionalBuildInfoId = regionalBuildInfoId;
	}

	public ClaimRegionalBuildInfo getClaimRegionalBuildInfo() {
		return claimRegionalBuildInfo;
	}

	public void setClaimRegionalBuildInfo(
			ClaimRegionalBuildInfo claimRegionalBuildInfo) {
		this.claimRegionalBuildInfo = claimRegionalBuildInfo;
	}

	public String getClaimMode() {
		return claimMode;
	}

	public void setClaimMode(String claimMode) {
		this.claimMode = claimMode;
	}

}
