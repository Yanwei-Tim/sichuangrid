package com.tianque.plugin.serviceTeam.serviceRecord.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
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
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.SearchBaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.FileUtil;
import com.tianque.core.vo.AutoCompleteData;
import com.tianque.core.vo.GridPage;
import com.tianque.datatransfer.ExcelExportHelper;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.plugin.serviceTeam.serviceRecord.domain.ServiceRecord;
import com.tianque.plugin.serviceTeam.serviceRecord.domain.ServiceRecordAttachment;
import com.tianque.plugin.serviceTeam.serviceRecord.service.ServiceRecordService;
import com.tianque.plugin.serviceTeam.serviceRecord.vo.ServiceRecordVo;
import com.tianque.plugin.serviceTeam.serviceTeamMember.service.ServiceTeamMemberService;
import com.tianque.plugin.serviceTeam.serviceTeamMember.vo.ServiceTeamMemberVo;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 服务记录控制类
 * 
 * @author lvliujie
 */
@Namespace("/plugin/serviceTeam/serviceRecord")
@Scope("request")
@Controller("serviceRecordController")
@Transactional
public class ServiceRecordController extends SearchBaseAction {
	private static Logger logger = LoggerFactory
			.getLogger(ServiceRecordController.class);
	@Autowired
	private ServiceRecordService serviceRecordService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private ServiceTeamMemberService serviceTeamMemberService;
	/** 存储记录 */
	private ServiceRecord serviceRecord;
	/** 显示记录 */
	private ServiceRecordVo serviceRecordVo;
	/** 记录所属人搜索条件vo */
	private ServiceTeamMemberVo serviceTeamMemberVo;
	/** 附件对象 */
	private ServiceRecordAttachment attachment;
	/** 组织机构ID */
	private Long orgId;
	/** 对象id */
	private String objectIds;
	/** 对象姓名 */
	private String objectNames;
	/** 所属人id */
	private String memberIds;
	/** 所属人姓名 */
	private String memberNames;
	/** 对象人员类型 */
	private String populationType;
	/** 对象类型 */
	private String objectType;
	/** 记录id */
	private String recordIds;
	/** 是否需要显示记录类型 */
	private String showRecordType;
	/** 删除的行数 */
	private int deleteCount;
	/** 附件名字 */
	private String[] attachFileNames;
	/** 这个字段是为手机接口用的，附件名字 **/
	private String[] attachFiles;
	private String[] attachFile;

	/** 附件Id */
	private Long attachmentId;
	/** 年份List */
	private List<Map<String, String>> yearList;
	/** 记录所属人查询List */
	private List<AutoCompleteData> serviceTeamMembers;
	/** 导出时是否为当前页 */
	private boolean pageOnly;
	/**服务对象ID*/
	private Long serviceObjectId;

	/** id加密业务跳转 */
	@EncryptAnnotation
	@Action(value = "dispatchByEncrypt", results = {
			@Result(name = "maintain", location = "/template/serviceTeam/serviceRecord/serviceRecordMaintainDlg.ftl"),
			@Result(name = "view", location = "/template/serviceTeam/serviceRecord/serviceRecordViewDlg.ftl"),
			@Result(name = "developPeopleLog", location = "/template/serviceTeam/serviceRecord/developPeopleLog.ftl") })
	public String dispatchByEncrypt() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			return "maintain";
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			getServiceRecordById();
			return "maintain";
		} else if (DialogMode.SEARCH_MODE.equals(mode)) {
			return "search";
		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			serviceRecordVo = serviceRecordService
					.getServiceRecordById(serviceRecord.getId());
			return "view";
		} else if ("selectObject".equals(mode)) {
			return "selectObject";
		} else if ("developPeopleLog".equals(mode)) {
			getServiceRecordById();
			return "developPeopleLog";
		}
		return ERROR;
	}

	/** 业务跳转 */
	@Action(value = "dispatch", results = {
			@Result(name = "maintain", location = "/template/serviceTeam/serviceRecord/serviceRecordMaintainDlg.ftl"),
			@Result(name = "search", location = "/template/serviceTeam/serviceRecord/serviceRecordSearchDlg.ftl"),
			@Result(name = "selectObject", location = "/template/serviceTeam/serviceRecord/objectForServiceRecordMaintainDlg.ftl"),
			@Result(name = "view", location = "/template/serviceTeam/serviceRecord/serviceRecordViewDlg.ftl"),
			@Result(name = "developPeopleLog", location = "/template/serviceTeam/serviceRecord/developPeopleLog.ftl") })
	public String dispatch() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			return "maintain";
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			getServiceRecordById();
			return "maintain";
		} else if (DialogMode.SEARCH_MODE.equals(mode)) {
			return "search";
		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			serviceRecordVo = serviceRecordService
					.getServiceRecordById(serviceRecord.getId());
			return "view";
		} else if ("selectObject".equals(mode)) {
			return "selectObject";
		} else if ("developPeopleLog".equals(mode)) {
			getServiceRecordById();
			return "developPeopleLog";
		}
		return ERROR;
	}

	/** 新增 */
	@PermissionFilter(ename = "addServiceRecord")
	@Action(value = "addServiceRecord", results = {
			@Result(name = "success", type = "json", params = { "root",
					"serviceRecordVo", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addServiceRecord() throws Exception {
		getServiceRecordInfo(serviceRecord);
		serviceRecordVo = serviceRecordService.addServiceRecord(serviceRecord);
		return SUCCESS;
	}

	public String addServiceRecordForMobileForPeople() throws Exception {
		getServiceRecordInfo(serviceRecord);
		serviceRecordVo = serviceRecordService.addServiceRecord(serviceRecord);
		return SUCCESS;
	}

	@Action(value = "addServiceRecordForMobile", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addServiceRecordForMobile() throws Exception {
		// 只有一个参数，在前面加逗号
		if (attachFiles != null && attachFiles.length != 0) {
			attachFiles[0] = "," + attachFiles[0];
			attachFileNames = attachFiles;
		}
		// 多个参数
		if (attachFile != null && attachFile.length != 0) {
			String[] strTmp = attachFile[0].split(",");
			for (int i = 0; i < strTmp.length; i++) {
				strTmp[i] = "," + strTmp[i];
			}
			attachFileNames = strTmp;
		}

		getServiceRecordInfo(serviceRecord);
		serviceRecordVo = serviceRecordService.addServiceRecord(serviceRecord);
		return SUCCESS;
	}

	/**
	 * 修改服务记录
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "updateServiceRecord")
	@Actions({
			@Action(value = "editServiceRecord", results = {
					@Result(name = "success", type = "json", params = { "root",
							"serviceRecordVo", "ignoreHierarchy", "false" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "editServiceRecordForMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String updateServiceRecord() throws Exception {
		getServiceRecordInfo(serviceRecord);
		serviceRecordVo = serviceRecordService
				.updateServiceRecord(serviceRecord,serviceObjectId);
		return SUCCESS;
	}

	public String updateServiceRecordForMobile() throws Exception {
		getServiceRecordInfo(serviceRecord);
		serviceRecordVo = serviceRecordService
				.updateServiceRecord(serviceRecord,null);
		return SUCCESS;
	}

	/**
	 * id加密删除服务记录
	 * 
	 * @return 被删除记录数
	 */
	@EncryptAnnotation
	@PermissionFilter(ename = "deleteServiceRecord")
	@Action(value = "deleteServiceRecordsByEncrypt", results = {
			@Result(type = "json", name = "success", params = { "root",
					"deleteCount", "ignoreHierarchy", "false" }),
			@Result(type = "json", name = "error", params = { "root",
					"deleteCount", "ignoreHierarchy", "false" }) })
	public String deleteServiceRecordsByEncrypt() throws Exception {
		deleteCount = serviceRecordService
				.deleteServiceRecords(analyzeIds(recordIds));
		return SUCCESS;
	}

	/**
	 * 删除服务记录
	 * 
	 * @return 被删除记录数
	 */
	@PermissionFilter(ename = "deleteServiceRecord")
	@Action(value = "deleteServiceRecords", results = {
			@Result(type = "json", name = "success", params = { "root",
					"deleteCount", "ignoreHierarchy", "false" }),
			@Result(type = "json", name = "error", params = { "root",
					"deleteCount", "ignoreHierarchy", "false" }) })
	public String deleteServiceRecords() throws Exception {
		deleteCount = serviceRecordService
				.deleteServiceRecords(analyzeIds(recordIds));
		return SUCCESS;
	}

	public String deleteServiceRecordsForMobile() throws Exception {
		deleteCount = serviceRecordService
				.deleteServiceRecords(analyzeIds(recordIds));
		return SUCCESS;
	}

	/**
	 * 查看记录信息
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "viewServiceRecord")
	@Action(value = "viewSeriviceRecord", results = { @Result(name = "serviceRecord", location = "/template/serviceTeam/serviceRecord/serviceRecordViewTab.ftl") })
	public String viewServiceTeam() throws Exception {
		serviceRecordVo = serviceRecordService
				.getServiceRecordById(serviceRecord.getId());
		return "serviceRecord";
	}

	public String viewServiceTeamForMobile() throws Exception {
		serviceRecordVo = serviceRecordService
				.getServiceRecordById(serviceRecord.getId());
		return "serviceRecord";
	}

	/**
	 * 删除附件
	 * 
	 * @return
	 */
	@Action(value = "deleteAttachment", results = {
			@Result(type = "json", name = "success", params = { "root",
					"deleteCount", "ignoreHierarchy", "false" }),
			@Result(type = "json", name = "error", params = { "root",
					"deleteCount", "ignoreHierarchy", "false" }) })
	public String deleteAttachment() throws Exception {
		deleteCount = serviceRecordService
				.deleteAttachmentByFileId(attachmentId);
		return SUCCESS;
	}

	/** 根据等级，年份，组织分页查询服务记录 */
	@Action(value = "findServiceRecords", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findServiceRecords() throws Exception {
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				serviceRecordService.findServiceRecords(serviceRecordVo,
						analyzeIds(objectIds), populationType, page, rows,
						sidx, sord), organizationDubboService,
				new String[] { "organization" }, orgId));
		return SUCCESS;
	}

	/** 附件下载 */
	@Action(value = "downloadServiceRecordAttachment")
	public String downloadServiceRecordAttachment() throws Exception {
		if (null == attachmentId) {
			errorMessage = "参数不正确";
			return ERROR;
		}
		attachment = serviceRecordService
				.getServiceRecordAttachmentById(attachmentId);
		if (null == attachment) {
			errorMessage = "服务记录附件不存在";
			return ERROR;
		}
		try {
			inputStream = new java.io.FileInputStream(FileUtil.getWebRoot()
					+ File.separator + attachment.getFileActualUrl());
			downloadFileName = new String(attachment.getFileName().getBytes(
					"gbk"), "ISO8859-1");
		} catch (FileNotFoundException e) {
			errorMessage = "附件文件不存在";
			return ERROR;
		} catch (UnsupportedEncodingException uee) {
			errorMessage = "文件编码格式不正确";
			return ERROR;
		}
		return STREAM_SUCCESS;
	}

	/** 服务记录导出Excel表格下载 */
	@Action(value = "downloadServiceRecord")
	public String downloadServiceRecord() throws Exception {
		if (serviceRecordVo == null) {
			serviceRecordVo = new ServiceRecordVo();
		}
		List<ServiceRecordVo> records = serviceRecordService
				.getNeedExportDatas(pageOnly, serviceRecordVo, page, rows,
						sidx, sord);
		inputStream = ExcelExportHelper.exportDateToExcel(
				SpecialGroupsContext.getServiceRecordPropertyArray(), records);
		downloadFileName = new String("服务记录清单".getBytes("gbk"), "ISO8859-1")
				+ ".xls";

		return STREAM_SUCCESS;
	}

	/** 服务记录导出Excel表格下载 */
	@Action(value = "downloadServiceRecordAll")
	public void downloadServiceRecordAll() throws Exception {
		if (serviceRecordVo == null) {
			serviceRecordVo = new ServiceRecordVo();
		}
		if (!pageOnly) {
			pageOnly = true;
			Integer count = serviceRecordService.getCount(serviceRecordVo);
			String[][] excelDefines = SpecialGroupsContext
					.getServiceRecordPropertyArray();
			exportDataAll(count, excelDefines, "服务记录清单");
		}
		return;
	}

	@Override
	public List<ServiceRecordVo> getNeedExportDatas(int page) {
		return serviceRecordService.getNeedExportDatas(pageOnly,
				serviceRecordVo, page, rows, sidx, sord);
	}

	/** 获取成员列表 */
	@Action(value = "findServiceMembers", results = {
			@Result(type = "json", name = "success", params = { "root",
					"serviceTeamMembers", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findServiceMembers() throws Exception {
		serviceTeamMembers = serviceTeamMemberService
				.findServiceMembersForServiceRecord(serviceTeamMemberVo);
		return SUCCESS;
	}

	/**
	 * 查找快速检索下拉年份
	 */
	@Action(value = "findDisplayYear", results = {
			@Result(type = "json", name = "success", params = { "root",
					"yearList", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findDisplayYear() throws Exception {
		yearList = serviceRecordService.getDisplayYear();
		return SUCCESS;
	}

	/**
	 * 根据id获取服务记录信息
	 */
	private void getServiceRecordById() {
		serviceRecordVo = serviceRecordService
				.getServiceRecordById(serviceRecord.getId());
		serviceRecordVo.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(serviceRecordVo
						.getOrganization().getId(), organizationDubboService));

	}

	/**
	 * 将id字符串转换为Long数组
	 */
	private Long[] analyzeIds(String ids) {
		if (ids == null) {
			return null;
		}
		String[] infoIds = ids.split(",");
		List<Long> idList;
		if (infoIds[0].equals("")) {
			idList = initTargetId(infoIds, 1);
		} else {
			idList = initTargetId(infoIds, 0);
		}
		Long[] idLongs = new Long[idList.size()];
		for (int i = 0; i < idLongs.length; i++) {
			idLongs[i] = idList.get(i);
		}
		return idLongs;
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

	// 获得服务记录关于对象的相关信息
	private void getServiceRecordInfo(ServiceRecord record) {
		if (null != record.getServiceObjects()
				&& record.getServiceObjects().length() != 0) {
			String[] objectStrs = record.getServiceObjects().split(",");
			objectIds = "";
			objectNames = "";
			for (int i = 0; i < objectStrs.length; i++) {
				String[] info = objectStrs[i].split("-");
				objectIds += "," + info[0];
				objectType = info[info.length - 1];
				objectNames += ",";
				String name = "";
				for (int j = 1; j < info.length - 1; j++) {
					name += "-" + info[j];
				}
				name = name.substring(1);
				objectNames += name;
			}
			objectIds = objectIds.substring(1);
			objectNames = objectNames.substring(1);
			serviceRecord.setServiceObjects(objectNames);
			serviceRecord.setObjectIds(analyzeIds(objectIds));
		}
		if (null != record.getServiceMembers()
				&& record.getServiceMembers().length() != 0) {
			String[] memberStrs = record.getServiceMembers().split(",");
			memberIds = "";
			memberNames = "";
			for (int i = 0; i < memberStrs.length; i++) {
				String[] info = memberStrs[i].split("-");
				memberIds += "," + info[0];
				memberNames += "," + info[1];
				// String name = "";
				// for (int j = 1; j < info.length; j++) {
				// name += "-" + info[1];
				// }
				// memberNames = memberNames.substring(1);
				// memberNames += name;
			}
			// 去掉第一个逗号
			memberNames = memberNames.substring(1);
			serviceRecord.setServiceMembers(memberNames);
			serviceRecord.setMemberIds(analyzeIds(memberIds));
		}
		serviceRecord.setAttachFileNames(attachFileNames);
		serviceRecord.setObjectType(objectType);
	}

	public List<Map<String, String>> getYearList() {
		return yearList;
	}

	public void setYearList(List<Map<String, String>> yearList) {
		this.yearList = yearList;
	}

	public ServiceRecord getServiceRecord() {
		return serviceRecord;
	}

	public void setServiceRecord(ServiceRecord serviceRecord) {
		this.serviceRecord = serviceRecord;
	}

	public ServiceRecordVo getServiceRecordVo() {
		return serviceRecordVo;
	}

	public void setServiceRecordVo(ServiceRecordVo serviceRecordVo) {
		this.serviceRecordVo = serviceRecordVo;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		ServiceRecordController.logger = logger;
	}

	public ServiceRecordService getServiceRecordService() {
		return serviceRecordService;
	}

	public void setServiceRecordService(
			ServiceRecordService serviceRecordService) {
		this.serviceRecordService = serviceRecordService;
	}

	public OrganizationDubboService getorganizationDubboService() {
		return organizationDubboService;
	}

	public void setorganizationDubboService(
			OrganizationDubboService organizationDubboService) {
		this.organizationDubboService = organizationDubboService;
	}

	public String[] getAttachFileNames() {
		return attachFileNames;
	}

	public void setAttachFileNames(String[] attachFileNames) {
		this.attachFileNames = attachFileNames;
	}

	public String getObjectIds() {
		return objectIds;
	}

	public void setObjectIds(String objectIds) {
		this.objectIds = objectIds;
	}

	public String getPopulationType() {
		return populationType;
	}

	public void setPopulationType(String populationType) {
		this.populationType = populationType;
	}

	public String getObjectNames() {
		return objectNames;
	}

	public void setObjectNames(String objectNames) {
		this.objectNames = objectNames;
	}

	public String getRecordIds() {
		return recordIds;
	}

	public void setRecordIds(String recordIds) {
		this.recordIds = recordIds;
	}

	public int getDeleteCount() {
		return deleteCount;
	}

	public void setDeleteCount(int deleteCount) {
		this.deleteCount = deleteCount;
	}

	public String getMemberIds() {
		return memberIds;
	}

	public void setMemberIds(String memberIds) {
		this.memberIds = memberIds;
	}

	public String getMemberNames() {
		return memberNames;
	}

	public void setMemberNames(String memberNames) {
		this.memberNames = memberNames;
	}

	public Long getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(Long attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public ServiceTeamMemberService getServiceTeamMemberService() {
		return serviceTeamMemberService;
	}

	public void setServiceTeamMemberService(
			ServiceTeamMemberService serviceTeamMemberService) {
		this.serviceTeamMemberService = serviceTeamMemberService;
	}

	public ServiceTeamMemberVo getServiceTeamMemberVo() {
		return serviceTeamMemberVo;
	}

	public void setServiceTeamMemberVo(ServiceTeamMemberVo serviceTeamMemberVo) {
		this.serviceTeamMemberVo = serviceTeamMemberVo;
	}

	public List<AutoCompleteData> getServiceTeamMembers() {
		return serviceTeamMembers;
	}

	public void setServiceTeamMembers(List<AutoCompleteData> serviceTeamMembers) {
		this.serviceTeamMembers = serviceTeamMembers;
	}

	public ServiceRecordAttachment getAttachment() {
		return attachment;
	}

	public void setAttachment(ServiceRecordAttachment attachment) {
		this.attachment = attachment;
	}

	public String getShowRecordType() {
		return showRecordType;
	}

	public void setShowRecordType(String showRecordType) {
		this.showRecordType = showRecordType;
	}

	public boolean isPageOnly() {
		return pageOnly;
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

	public String[] getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(String[] attachFiles) {
		this.attachFiles = attachFiles;
	}

	public String[] getAttachFile() {
		return attachFile;
	}

	public void setAttachFile(String[] attachFile) {
		this.attachFile = attachFile;
	}

	public Long getServiceObjectId() {
		return serviceObjectId;
	}

	public void setServiceObjectId(Long serviceObjectId) {
		this.serviceObjectId = serviceObjectId;
	}
	

}
