package com.tianque.working.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.MyGroup;
import com.tianque.domain.Organization;
import com.tianque.domain.WorkContacter;
import com.tianque.service.TemporaryPopulationService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.userAuth.api.ContacterDubboService;
import com.tianque.viewObject.service.ViewObjectService;
import com.tianque.viewObject.vo.ViewObjectVo;
import com.tianque.working.constants.DocumentsConstants;
import com.tianque.working.domain.Document;
import com.tianque.working.domain.DocumentSignVo;
import com.tianque.working.domain.DocumentState;
import com.tianque.working.domain.DocumentsHasAttachFiles;
import com.tianque.working.domain.DocumentsHasOrg;
import com.tianque.working.domain.DocumentsHasUser;
import com.tianque.working.domain.MyDepartmentGrop;
import com.tianque.working.domain.OptionalObj;
import com.tianque.working.domain.SearchDocumentVo;
import com.tianque.working.service.DispatchDocumentsService;
import com.tianque.working.service.MyDepartmentGropService;

@Controller("dispatchDocumentsController")
@Scope("prototype")
@Namespaces({ @Namespace("/documents/dispatchDocumentsManage"),
		@Namespace("/hotModuel/documents/dispatchDocumentsManage") })
public class DispatchDocumentsController extends BaseAction {
	@Autowired
	private DispatchDocumentsService dispatchDocumentsService;
	@Autowired
	private MyDepartmentGropService myDepartmentGropService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private ViewObjectService viewObjectService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private TemporaryPopulationService temporaryPopulationService;
	@Autowired
	private ContacterDubboService contacterDubboService;

	private Document document;
	private SearchDocumentVo searchDocumentVo;
	private String[] attachFiles;
	private Long fileId;
	private String selectedIds;
	private String organizationIds;
	private String sendObjCacheId;
	private String userOrgName;
	private ViewObjectVo sendViewObjectVo;
	private ViewObjectVo copyViewObjectVo;
	private DocumentsHasOrg documentsHasOrg;
	private DocumentsHasUser documentsHasUser;
	// 同不资料库公用的ids
	private List<Document> documents;
	private String sendMessage;
	private Long resourcePoolTypeId;
	private String setPermissionCacheId;
	private List<Organization> organizations;
	private String sendOptrionalObjIds;
	private String sendDepartmentGroupIds;
	private String copySendOptrionalObjIds;
	private String copySendDepartmentGroupIds;
	private Map<String, Object> resultMap;
	private DocumentsHasAttachFiles myDocumentsAttachFile;
	private List<MyDepartmentGrop> myDepartmentGrops = new ArrayList<MyDepartmentGrop>();
	/* 附件 */
	private List<DocumentsHasAttachFiles> docfiles = new ArrayList<DocumentsHasAttachFiles>();
	Map<String, Object> jsonMap = new HashMap<String, Object>();
	/** 公文管理改造新增字段 */
	/** 当前登录用户的群组List */
	private List<MyGroup> myGroups;
	private String userReceivers;
	private String orgReceivers;
	private String userReceiversCopy;
	private String orgReceiversCopy;
	private String receiversNames;
	private String receiversNamesCopy;
	private String userIds;
	private String organizationIdsCopy;
	private String userIdsCopy;
	private List<DocumentSignVo> reminderList;
	// 以下四个参数用户后台做参数处理所用
	private String identification;
	private String orgUserIdStr;
	private String userIdStr;
	private String orgUserIdCopyStr;
	private String userIdCopyStr;

	/***
	 * 发公文历史数据查询
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "histroyDocumentsList", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String histroyDocumentsList() {
		gridPage = new GridPage(
				dispatchDocumentsService.finddispatchDocumentsListForHistory(
						document, page, rows, sidx, sord));
		return SUCCESS;
	}

	/***
	 * 历史公文数据删除
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "deleteHistoryDocumentForHistory", results = {
			@Result(name = "success", type = "json", params = { "root",
					"success" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteHistoryDocumentForHistory() {
		if (!StringUtil.isStringAvaliable(selectedIds)) {
			errorMessage = "历史数据删除失败，未获得数据信息";
			return ERROR;
		}
		dispatchDocumentsService
				.deleteDispatchDocForHistroy(analyze(selectedIds));
		return SUCCESS;
	}

	/*
	 * 发文列表
	 */
	@Action(value = "dispatchDocumentsList", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String dispatchDocumentsList() throws Exception {
		if (null == document) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		} else {
			gridPage = new GridPage(
					dispatchDocumentsService.finddispatchDocumentsList(
							document, page, rows, sidx, sord));
		}
		return SUCCESS;
	}

	@Action(value = "dispatchOperateDocuments", results = {
			@Result(name = "success", location = "/daily/documentsManage/dispatchDocumentsManage/maintainDispatchDocuments.jsp"),
			@Result(name = "view", location = "/daily/documentsManage/dispatchDocumentsManage/dispatchDocumentsView.jsp"),
			@Result(name = "historyView", location = "/daily/documentsManage/dispatchDocumentsManage/hisetoryDocumentView.jsp"),
			@Result(name = "search", location = "/daily/documentsManage/dispatchDocumentsManage/searchDispatchDocuments.jsp"),
			@Result(name = "sign", location = "/daily/documentsManage/dispatchDocumentsManage/signStateDlg.jsp"),
			@Result(name = "read", location = "/daily/documentsManage/receiveDocumentsManage/readStateDlg.jsp"),
			@Result(name = "allSearch", location = "/daily/documentsManage/searchAllDocuments/searchDocumentsDlg.jsp"),
			@Result(name = "sharing", location = "/knowledgeSharing/myProfile/synchFileDlg.jsp"),
			@Result(name = "documentSelectCopy", location = "/daily/documentsManage/dispatchDocumentsManage/documentSelectPersonCopy.jsp"),
			@Result(name = "documentSelect", location = "/daily/documentsManage/dispatchDocumentsManage/documentSelectPersion.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatchOperateDocuments() throws Exception {
		if (DialogMode.HISTORY_VIEW.equals(mode)) {
			if (document == null || document.getId() == null) {
				errorMessage = "未获得正确的参数";
				return ERROR;
			}
			document = dispatchDocumentsService.getDocumentsById(document
					.getId());

			if (document != null && null != document.getSendObjId()
					&& document.getSendObjId() != 0) {
				sendViewObjectVo = viewObjectService.getViewObjectById(document
						.getSendObjId());
			}
			if (document != null && null != document.getCopySendObjId()
					&& document.getCopySendObjId() != 0) {
				copyViewObjectVo = viewObjectService.getViewObjectById(document
						.getCopySendObjId());
			}

			docfiles = dispatchDocumentsService
					.getDocfilesByDocumentId(document.getId());
			getResultMapForUpdate(document.getId());
			return mode;
		}
		if (DialogMode.VIEW_MODE.equals(mode)) {
			if (document == null || document.getId() == null) {
				errorMessage = "未获得正确的参数";
				return ERROR;
			}
			document = dispatchDocumentsService.getDocumentsById(document
					.getId());
			docfiles = dispatchDocumentsService
					.getDocfilesByDocumentId(document.getId());
			getResultMapForUpdate(document);
			return DialogMode.VIEW_MODE;
		}
		if (DialogMode.SEARCH_MODE.equals(mode)
				|| DialogMode.SEARCH_DOCUMENTS.equals(mode)) {
			return DialogMode.SEARCH_MODE;
		}
		if (DialogMode.ALLSEARCH_MODE.equals(mode)) {// 暂时未使用，等唐杰需求确认
			return DialogMode.ALLSEARCH_MODE;
		}
		if (DialogMode.SIGN.equals(mode)) {
			if (document == null || document.getId() == null) {
				errorMessage = "公文签收信息查询出错";
				return ERROR;
			}
			reminderList = dispatchDocumentsService
					.findReminderInfoByDocumentId(document.getId());
			return DialogMode.SIGN;
		}
		if (DialogMode.READ.equals(mode)) {
			return DialogMode.READ;
		}
		if (DialogMode.EDIT_MODE.equals(mode)) {
			if (document == null || document.getId() == null) {
				errorMessage = "未获得正确的参数";
				return ERROR;
			}
			document = dispatchDocumentsService.getDocumentsById(document
					.getId());
			docfiles = dispatchDocumentsService
					.getDocfilesByDocumentId(document.getId());
		}
		if (DialogMode.TRANSMIT.equals(mode)) {
			if (document == null || document.getId() == null) {
				errorMessage = "未获得正确的参数";
				return ERROR;
			}
			document = dispatchDocumentsService.getDocumentsByIdAndUserId(
					document.getId(), ThreadVariable.getUser().getId());
			docfiles = dispatchDocumentsService
					.getDocfilesByDocumentId(document.getId());
		}

		if (DialogMode.SHAREING.equalsIgnoreCase(mode)) {
			documents = dispatchDocumentsService.getDocumentsByIds(selectedIds);
			return DialogMode.SHAREING;
		}
		if (DialogMode.ADD_MODE.equals(mode)) {
			userOrgName = organizationDubboService.getSimpleOrgById(
					ThreadVariable.getUser().getOrganization().getId())
					.getOrgName();
		}
		if (DocumentsConstants.DCOUMENT_SELECT.equals(mode)
				|| DocumentsConstants.DOCUMENT_COPY_SELECT.equals(mode)) {
			myGroups = contacterDubboService
					.findMyGroupByOwnerId(ThreadVariable.getUser().getId());
			return mode;
		}
		return SUCCESS;
	}

	// 新增发文
	@Action(value = "addDispatchDocuments", results = {
			@Result(type = "json", name = "success", params = { "root",
					"document", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addDispatchDocuments() throws Exception {
		dealUserIds();
		receiversNames = dealreceiverNames(receiversNames, userReceivers,
				orgReceivers);
		receiversNamesCopy = dealreceiverNames(receiversNamesCopy,
				userReceiversCopy, orgReceiversCopy);

		document.setReceiversNames(receiversNames);
		document.setReceiversNamesCopy(receiversNamesCopy);
		DocumentsHasAttachFiles[] files = convertToFileInfos(attachFiles);
		dispatchDocumentsService.addDispathDocumentInfo(userIdStr,
				userIdCopyStr, document, files);
		return SUCCESS;
	}

	// 修改发文
	@Action(value = "updateDispatchDocuments", results = {
			@Result(type = "json", name = "success", params = { "root",
					"document", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateDispatchDocuments() throws Exception {
		dealUserIds();
		receiversNames = dealreceiverNames(receiversNames, userReceivers,
				orgReceivers);
		receiversNamesCopy = dealreceiverNames(receiversNamesCopy,
				userReceiversCopy, orgReceiversCopy);

		document.setReceiversNames(receiversNames);
		document.setReceiversNamesCopy(receiversNamesCopy);
		DocumentsHasAttachFiles[] files = convertToFileInfos(attachFiles);
		document = dispatchDocumentsService.updateDocument(userIdStr,
				userIdCopyStr, document, files);
		return SUCCESS;
	}

	// 发文
	@Action(value = "sendDocuments", results = {
			@Result(type = "json", name = "success", params = { "root",
					"document", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String sendDocuments() throws Exception {
		dispatchDocumentsService.sendDocuments(analyze(selectedIds), mode);
		return SUCCESS;
	}

	// 转发
	@Action(value = "transmitDocuments", results = {
			@Result(type = "json", name = "success", params = { "root",
					"document", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String transmitDocuments() throws Exception {
		dealUserIds();
		String[] userIds = null;
		String[] copyUserIds = null;
		if (StringUtil.isStringAvaliable(userIdStr)) {
			userIds = userIdStr.split(",");
		}
		if (StringUtil.isStringAvaliable(userIdCopyStr)) {
			copyUserIds = userIdStr.split(",");
		}
		dispatchDocumentsService.bindingDocWithUser(document, userIds,
				copyUserIds);
		return SUCCESS;
	}

	// 查询、快速过滤
	@Action(value = "searchDispatchDocuments", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String searchDispatchDocuments() throws Exception {
		if (null == searchDocumentVo) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		}
		gridPage = new GridPage(
				dispatchDocumentsService.searchDispatchDocuments(
						searchDocumentVo, page, rows, sidx, sord));
		return SUCCESS;
	}

	// 删除功能
	@Action(value = "deleteDispatchDocById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"success" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteDispatchDocById() throws Exception {
		dispatchDocumentsService.deleteDispatchDocById(analyze(selectedIds));
		return SUCCESS;
	}

	@Action(value = "deleteMyDocumentsAttachFile", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteMyDocumentsAttachFile() throws Exception {
		if (myDocumentsAttachFile == null
				|| myDocumentsAttachFile.getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		dispatchDocumentsService
				.deleteMyDocumentsAttachFile(myDocumentsAttachFile.getId());
		return SUCCESS;
	}

	// 附件下载
	@Action(value = "downLoadDocfiles", results = { @Result(name = "error", type = "json", params = {
			"root", "errorMessage" }) })
	public String downLoadDocfiles() throws Exception {
		DocumentsHasAttachFiles file = dispatchDocumentsService
				.getDocfilesByAttachFileId(fileId);
		if (null == file) {
			this.errorMessage = "您下载的文件不存在!";
			return ERROR;
		}

		String downFilePath = FileUtil.getWebRoot() + File.separator
				+ file.getFileActualUrl();
		downloadFileName = new String(file.getFileName().getBytes("gbk"),
				"ISO8859-1");
		inputStream = new java.io.FileInputStream(new File(downFilePath));
		return STREAM_SUCCESS;
	}

	// 同步资料
	@Action(value = "synchToMyProfile", results = {
			@Result(name = "success", type = "json", params = { "root",
					"myProfiles", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String synchToMyProfile() throws Exception {
		dispatchDocumentsService.synchToMyProfile(selectedIds,
				Long.valueOf(sendMessage), resourcePoolTypeId,
				setPermissionCacheId);
		return SUCCESS;

	}

	private String getOrgNames(String orgIds) {
		organizations = myDepartmentGropService.findOrganizationByIds(orgIds);
		String orgNames = "";
		if (organizations != null) {
			for (Organization organization : organizations) {
				orgNames += organization.getOrgName() + ",";
			}
		}
		return orgNames;
	}

	private String getGroupNames(String groupIds) {
		myDepartmentGrops = myDepartmentGropService
				.findMyDepartmentsGropByIds(groupIds);
		String groupNames = "";
		if (myDepartmentGrops != null) {
			for (MyDepartmentGrop myDepartmentGrop : myDepartmentGrops) {
				groupNames += myDepartmentGrop.getName() + ",";
			}
		}
		return groupNames;
	}

	private DocumentsHasAttachFiles[] convertToFileInfos(String[] fileInfos) {
		if (null == fileInfos || fileInfos.length == 0) {
			return null;
		} else {
			DocumentsHasAttachFiles[] result = new DocumentsHasAttachFiles[fileInfos.length];
			for (int index = 0; index < fileInfos.length; index++) {
				result[index] = convertToFileInfo(fileInfos[index]);
			}
			return result;
		}
	}

	private DocumentsHasAttachFiles convertToFileInfo(String file) {
		DocumentsHasAttachFiles info = new DocumentsHasAttachFiles();
		// id是否存在，存在则获取
		String idStr = file.substring(0, file.indexOf(","));
		 if (StringUtil.isStringAvaliable(idStr)) {
			 info.setFileId(Long.valueOf(idStr));
		}
		info.setFileName(file.substring(file.indexOf(",") + 1));
		return info;
	}

	private PageInfo<Document> emptyPage(int pageSize) {
		PageInfo<Document> pageInfo = new PageInfo<Document>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<Document>());
		return pageInfo;
	}

	/** 获取所有站内联系人id */
	private String getUserReceiverIds(String userReceiverId) {
		String[] userReceiverIds = userReceiverId.split(",");
		Set<Long> list = new HashSet<Long>();
		for (String id : userReceiverIds) {
			if (id == null || "".equals(id) || id.endsWith("-levelList")) {
				continue;
			}
			List<WorkContacter> workContacters = contacterDubboService
					.findWorkContacterById(Long.parseLong(id));
			if (workContacters != null && workContacters.size() > 0) {
				for (WorkContacter workContacter : workContacters) {
					list.add(workContacter.getFromUser().getId());
				}
			}
		}
		List<Long> userReciverIds = new ArrayList<Long>();
		userReciverIds.addAll(list);
		StringBuffer sb = new StringBuffer();
		if (userReciverIds != null && userReciverIds.size() != 0) {
			for (Long id : userReciverIds) {
				sb.append(id + ",");
			}
		}
		return sb.toString();
	}

	/** 获取部门收件人id */

	private String getOrgReceiverIds(String orgId_orgLevel_orgType_str) {
		String[] orgIdAndOrgLevelAndOrgTypes = orgId_orgLevel_orgType_str
				.split(",");
		List<Long> orgReceiverIds = new ArrayList<Long>();

		for (String orgIdAndOrgLevelAndOrgType : orgIdAndOrgLevelAndOrgTypes) {

			if (StringUtil.isStringAvaliable(orgIdAndOrgLevelAndOrgType)) {

				String[] orgId_orgLevel_orgType = orgIdAndOrgLevelAndOrgType
						.split("_");

				Long orgId = Long.valueOf(orgId_orgLevel_orgType[0]);

				Integer orgLevel = Integer.valueOf(orgId_orgLevel_orgType[1]);

				Integer orgType = Integer.valueOf(orgId_orgLevel_orgType[2]);

				List<Long> userIds = permissionService
						.findUserIdsByByOrgTypeAndOrgLevelAndOrgParentId(
								orgType, orgLevel, orgId);

				orgReceiverIds.addAll(userIds);

			}

		}
		// 用HashSet去重
		HashSet h = new HashSet(orgReceiverIds);

		orgReceiverIds.clear();

		orgReceiverIds.addAll(h);
		StringBuffer sb = new StringBuffer();
		if (orgReceiverIds != null && orgReceiverIds.size() != 0) {
			for (Long id : orgReceiverIds) {
				sb.append(id + ",");
			}
		}

		return sb.toString();
	}

	/***
	 * 数据处理
	 */
	private void dealUserIds() {
		if (StringUtil.isStringAvaliable(userReceivers)) {
			userIdStr = getUserReceiverIds(userReceivers);
		}
		if (StringUtil.isStringAvaliable(orgReceivers)) {
			orgUserIdStr = getOrgReceiverIds(orgReceivers);
		}
		if (StringUtil.isStringAvaliable(userReceiversCopy)) {
			userIdCopyStr = getUserReceiverIds(userReceiversCopy);
		}
		if (StringUtil.isStringAvaliable(orgReceiversCopy)) {
			orgUserIdCopyStr = getOrgReceiverIds(orgReceiversCopy);
		}
		if (orgUserIdStr != null && orgUserIdStr.length() != 0) {
			userIdStr += orgUserIdStr;
		}
		if (orgUserIdCopyStr != null && orgUserIdCopyStr.length() != 0) {
			userIdCopyStr += orgUserIdCopyStr;
		}
	}

	/***
	 * 历史数据查看时参数处理
	 * 
	 * @param documentId
	 */
	private void getResultMapForUpdate(Long documentId) {
		sendOptrionalObjIds = getOptrionalObjIds(documentId, DocumentState.SEND);
		copySendOptrionalObjIds = getOptrionalObjIds(documentId,
				DocumentState.COPYSEND);
		sendDepartmentGroupIds = getDepartmentGroupIds(documentId,
				DocumentState.SEND);
		copySendDepartmentGroupIds = getDepartmentGroupIds(documentId,
				DocumentState.COPYSEND);

		String groupNames = getGroupNames(sendDepartmentGroupIds);
		String orgNames = getOrgNames(sendOptrionalObjIds);
		String copygroupNames = getGroupNames(copySendDepartmentGroupIds);
		String copyorgNames = getOrgNames(copySendOptrionalObjIds);
		resultMap = new HashMap<String, Object>();
		getResultMaps(groupNames, orgNames, sendDepartmentGroupIds,
				sendOptrionalObjIds, "send", resultMap);
		getResultMaps(copygroupNames, copyorgNames, copySendDepartmentGroupIds,
				copySendOptrionalObjIds, "copy", resultMap);
	}

	private String getOptrionalObjIds(Long documentId, String postObj) {
		OptionalObj optionalObj = myDepartmentGropService
				.findOptionalObjByDocumentIdandPostObj(documentId, postObj);
		if (optionalObj != null) {
			return optionalObj.getOptionalOrgIds();
		}
		return "";
	}

	private String getDepartmentGroupIds(Long documentId, String postObj) {
		return myDepartmentGropService.findGroupObjsByDocumentIdandPostObj(
				documentId, postObj);
	}

	private void getResultMaps(String groupNames, String orgNames,
			String groupIds, String orgIds, String flag,
			Map<String, Object> resultMap) {
		Map<String, String> orgIdsMap = new HashMap<String, String>();
		orgIdsMap.put("orgIds", orgIds);
		orgIdsMap.put("groupIds", groupIds);
		Map<String, String> cachedMap = temporaryPopulationService
				.addObjectToTemporary(orgIdsMap);

		resultMap.put(flag + "cacheId", cachedMap.get("id"));
		if ("".equals(orgNames)) {
			if (!"".equals(groupNames)) {
				groupNames = groupNames.substring(0, groupNames.length() - 1);
				resultMap.put(flag + "selectOrgNames", groupNames + "部门群");
			}

		} else {
			orgNames = orgNames.substring(0, orgNames.length() - 1);
			if ("".equals(groupNames)) {
				resultMap.put(flag + "selectOrgNames", orgNames);
			} else {
				groupNames = groupNames.substring(0, groupNames.length() - 1);
				resultMap.put(flag + "selectOrgNames", orgNames + "和"
						+ groupNames + "部门群");
			}

		}
		String orgIdsAndGroupIds = groupIds;
		orgIdsAndGroupIds += orgIds;
		if (orgIdsAndGroupIds != null && !"".equals(orgIdsAndGroupIds)) {
			resultMap.put(flag + "AllOrgIds", orgIdsAndGroupIds);
		}

	}

	public static boolean isNum(String str) {
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}

	private void getResultMapForUpdate(Document document) {
		StringBuffer nameSb = new StringBuffer();
		StringBuffer nameCopySb = new StringBuffer();
		String receviesName = document.getReceiversNames();
		String receviesNameCopy = document.getReceiversNamesCopy();
		if (StringUtil.isStringAvaliable(receviesName)) {
			if (receviesName.split("\\|").length > 1) {
				String[] names = receviesName.split("\\|")[1].split(",");
				if (names != null && names.length != 0) {
					for (String name : names) {
						String[] realName = name.split("=");
						if (realName != null && realName.length > 1) {
							nameSb.append(realName[1] + ",");
						}
					}
				}
			}
		}

		if (StringUtil.isStringAvaliable(receviesNameCopy)) {
			if (receviesNameCopy.split("\\|").length > 1) {
				String[] names = receviesNameCopy.split("\\|")[1].split(",");
				if (names != null && names.length != 0) {
					for (String name : names) {
						String[] realName = name.split("=");
						if (realName != null && realName.length > 1) {
							nameCopySb.append(realName[1] + ",");
						}
					}
				}
			}
		}

		resultMap = new HashMap<String, Object>();
		resultMap.put("sendName", nameSb.toString());
		resultMap.put("copyName", nameCopySb.toString());
	}

	/***
	 * 处理收件人信息，将收件人信息和ID做冗余
	 * 
	 * @param receiverNames
	 * @param userReceiver
	 * @param orgReceiver
	 * @return
	 */
	private String dealreceiverNames(String receiverNames, String userReceiver,
			String orgReceiver) {
		StringBuffer sb = new StringBuffer();
		if (StringUtil.isStringAvaliable(userReceiver)) {
			sb.append(userReceiver);
		}
		sb.append("&");
		if (StringUtil.isStringAvaliable(orgReceiver)) {
			sb.append(orgReceiver);
		}
		sb.append("|");
		if (StringUtil.isStringAvaliable(receiverNames)) {
			sb.append(receiverNames);
		}
		return sb.toString();
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public String[] getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(String[] attachFiles) {
		this.attachFiles = attachFiles;
	}

	public List<DocumentsHasAttachFiles> getDocfiles() {
		return docfiles;
	}

	public void setDocfiles(List<DocumentsHasAttachFiles> docfiles) {
		this.docfiles = docfiles;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public SearchDocumentVo getSearchDocumentVo() {
		return searchDocumentVo;
	}

	public void setSearchDocumentVo(SearchDocumentVo searchDocumentVo) {
		this.searchDocumentVo = searchDocumentVo;
	}

	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}

	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}

	public String getSendObjCacheId() {
		return sendObjCacheId;
	}

	public void setSendObjCacheId(String sendObjCacheId) {
		this.sendObjCacheId = sendObjCacheId;
	}

	public String getSelectedIds() {
		return selectedIds;
	}

	public void setSelectedIds(String selectedIds) {
		this.selectedIds = selectedIds;
	}

	public ViewObjectVo getSendViewObjectVo() {
		return sendViewObjectVo;
	}

	public void setSendViewObjectVo(ViewObjectVo sendViewObjectVo) {
		this.sendViewObjectVo = sendViewObjectVo;
	}

	public ViewObjectVo getCopyViewObjectVo() {
		return copyViewObjectVo;
	}

	public void setCopyViewObjectVo(ViewObjectVo copyViewObjectVo) {
		this.copyViewObjectVo = copyViewObjectVo;
	}

	public DocumentsHasOrg getDocumentsHasOrg() {
		return documentsHasOrg;
	}

	public void setDocumentsHasOrg(DocumentsHasOrg documentsHasOrg) {
		this.documentsHasOrg = documentsHasOrg;
	}

	public DocumentsHasUser getDocumentsHasUser() {
		return documentsHasUser;
	}

	public void setDocumentsHasUser(DocumentsHasUser documentsHasUser) {
		this.documentsHasUser = documentsHasUser;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public String getSendMessage() {
		return sendMessage;
	}

	public void setSendMessage(String sendMessage) {
		this.sendMessage = sendMessage;
	}

	public Long getResourcePoolTypeId() {
		return resourcePoolTypeId;
	}

	public void setResourcePoolTypeId(Long resourcePoolTypeId) {
		this.resourcePoolTypeId = resourcePoolTypeId;
	}

	public String getSetPermissionCacheId() {
		return setPermissionCacheId;
	}

	public void setSetPermissionCacheId(String setPermissionCacheId) {
		this.setPermissionCacheId = setPermissionCacheId;
	}

	public String getOrganizationIds() {
		return organizationIds;
	}

	public void setOrganizationIds(String organizationIds) {
		this.organizationIds = organizationIds;
	}

	public List<Organization> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(List<Organization> organizations) {
		this.organizations = organizations;
	}

	public List<MyDepartmentGrop> getMyDepartmentGrops() {
		return myDepartmentGrops;
	}

	public void setMyDepartmentGrops(List<MyDepartmentGrop> myDepartmentGrops) {
		this.myDepartmentGrops = myDepartmentGrops;
	}

	public String getSendOptrionalObjIds() {
		return sendOptrionalObjIds;
	}

	public void setSendOptrionalObjIds(String sendOptrionalObjIds) {
		this.sendOptrionalObjIds = sendOptrionalObjIds;
	}

	public String getSendDepartmentGroupIds() {
		return sendDepartmentGroupIds;
	}

	public void setSendDepartmentGroupIds(String sendDepartmentGroupIds) {
		this.sendDepartmentGroupIds = sendDepartmentGroupIds;
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public String getUserOrgName() {
		return userOrgName;
	}

	public void setUserOrgName(String userOrgName) {
		this.userOrgName = userOrgName;
	}

	public String getCopySendOptrionalObjIds() {
		return copySendOptrionalObjIds;
	}

	public void setCopySendOptrionalObjIds(String copySendOptrionalObjIds) {
		this.copySendOptrionalObjIds = copySendOptrionalObjIds;
	}

	public String getCopySendDepartmentGroupIds() {
		return copySendDepartmentGroupIds;
	}

	public void setCopySendDepartmentGroupIds(String copySendDepartmentGroupIds) {
		this.copySendDepartmentGroupIds = copySendDepartmentGroupIds;
	}

	public DocumentsHasAttachFiles getMyDocumentsAttachFile() {
		return myDocumentsAttachFile;
	}

	public void setMyDocumentsAttachFile(
			DocumentsHasAttachFiles myDocumentsAttachFile) {
		this.myDocumentsAttachFile = myDocumentsAttachFile;
	}

	public List<MyGroup> getMyGroups() {
		return myGroups;
	}

	public void setMyGroups(List<MyGroup> myGroups) {
		this.myGroups = myGroups;
	}

	public String getUserReceivers() {
		return userReceivers;
	}

	public void setUserReceivers(String userReceivers) {
		this.userReceivers = userReceivers;
	}

	public String getOrgReceivers() {
		return orgReceivers;
	}

	public void setOrgReceivers(String orgReceivers) {
		this.orgReceivers = orgReceivers;
	}

	public String getUserReceiversCopy() {
		return userReceiversCopy;
	}

	public void setUserReceiversCopy(String userReceiversCopy) {
		this.userReceiversCopy = userReceiversCopy;
	}

	public String getOrgReceiversCopy() {
		return orgReceiversCopy;
	}

	public void setOrgReceiversCopy(String orgReceiversCopy) {
		this.orgReceiversCopy = orgReceiversCopy;
	}

	public String getReceiversNames() {
		return receiversNames;
	}

	public void setReceiversNames(String receiversNames) {
		this.receiversNames = receiversNames;
	}

	public String getReceiversNamesCopy() {
		return receiversNamesCopy;
	}

	public void setReceiversNamesCopy(String receiversNamesCopy) {
		this.receiversNamesCopy = receiversNamesCopy;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public String getOrganizationIdsCopy() {
		return organizationIdsCopy;
	}

	public void setOrganizationIdsCopy(String organizationIdsCopy) {
		this.organizationIdsCopy = organizationIdsCopy;
	}

	public String getUserIdsCopy() {
		return userIdsCopy;
	}

	public String getOrgUserIdStr() {
		return orgUserIdStr;
	}

	public void setOrgUserIdStr(String orgUserIdStr) {
		this.orgUserIdStr = orgUserIdStr;
	}

	public String getUserIdStr() {
		return userIdStr;
	}

	public void setUserIdStr(String userIdStr) {
		this.userIdStr = userIdStr;
	}

	public String getOrgUserIdCopyStr() {
		return orgUserIdCopyStr;
	}

	public void setOrgUserIdCopyStr(String orgUserIdCopyStr) {
		this.orgUserIdCopyStr = orgUserIdCopyStr;
	}

	public String getUserIdCopyStr() {
		return userIdCopyStr;
	}

	public void setUserIdCopyStr(String userIdCopyStr) {
		this.userIdCopyStr = userIdCopyStr;
	}

	public void setUserIdsCopy(String userIdsCopy) {
		this.userIdsCopy = userIdsCopy;
	}

	public List<DocumentSignVo> getReminderList() {
		return reminderList;
	}

	public void setReminderList(List<DocumentSignVo> reminderList) {
		this.reminderList = reminderList;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}


}
