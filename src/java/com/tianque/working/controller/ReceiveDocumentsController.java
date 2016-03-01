package com.tianque.working.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.working.constants.DocumentsConstants;
import com.tianque.working.domain.Document;
import com.tianque.working.domain.DocumentsHasAttachFiles;
import com.tianque.working.domain.DocumentsHasOrg;
import com.tianque.working.domain.SearchDocumentVo;
import com.tianque.working.domain.UserHasDocuments;
import com.tianque.working.service.DispatchDocumentsService;
import com.tianque.working.service.ReceiveDocumentsService;

@Controller("receiveDocumentsController")
@Scope("prototype")
@Transactional
@Namespace("/documents/receiveDocumentsManage")
public class ReceiveDocumentsController extends BaseAction {
	private Document document;
	private DocumentsHasOrg documentsHasOrg;
	// 选中的文件id
	private String selectedIds;
	private SearchDocumentVo searchDocumentVo;
	@Autowired
	private ReceiveDocumentsService receiveDocumentsService;
	@Autowired
	private DispatchDocumentsService dispatchDocumentsService;
	/* 附件 */
	private List<DocumentsHasAttachFiles> docfiles = new ArrayList<DocumentsHasAttachFiles>();
	private Boolean readState;
	private String flag;
	private String ids;

	/** 改造后增加参数 */
	private UserHasDocuments userHasDocuments;
	private Map<String, Object> resultMap;

	@Action(value = "operateReceiveDocuments", results = {
			@Result(name = "receiveState", location = "/daily/documentsManage/receiveDocumentsManage/receiveStateDlg.jsp"),
			@Result(name = "view", location = "/daily/documentsManage/dispatchDocumentsManage/dispatchDocumentsView.jsp"),
			@Result(name = "search", location = "/daily/documentsManage/dispatchDocumentsManage/searchReceiveDocuments.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }),
			@Result(name = "readResult", type = "json", params = { "root",
					"readState" }) })
	public String operateReceiveDocuments() throws Exception {
		if (DialogMode.RECEIVESTATE_MODE.equals(mode)) {
			userHasDocuments = new UserHasDocuments();
			userHasDocuments.setSignDate(new Date());
			return mode;
		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			// 建立文件和用户的关系
			document = dispatchDocumentsService.getDocumentsByIdAndUserId(
					document.getId(), ThreadVariable.getUser().getId());
			if (document == null) {
				errorMessage = "查看公文信息失败";
				return ERROR;
			}
			document.setApprovalOpinion(dealApprovalOpinion(document
					.getApprovalOpinion()));
			docfiles = dispatchDocumentsService
					.getDocfilesByDocumentId(document.getId());
			getResultMapForUpdate(document);
			// 如果公文没阅读过，更改该公文阅读状态
			if (!readState) {
				dispatchDocumentsService.updateDocumentReadState(
						document.getId(), ThreadVariable.getUser().getId(),
						DocumentsConstants.isRead, CalendarUtil.now());
			}
			return DialogMode.VIEW_MODE;
		} else if (DialogMode.SEARCH_MODE.equals(mode)) {

			return DialogMode.SEARCH_MODE;
		}

		return SUCCESS;
	}

	private String dealApprovalOpinion(String approvalOpinion) {
		if (StringUtil.isStringAvaliable(approvalOpinion)) {
			approvalOpinion = approvalOpinion.replace(
					DocumentsConstants.TEXTAREA_WRAP, "<br/>");
		}
		return approvalOpinion;

	}

	/***
	 * 判断字符串中是否包含数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNum(String str) {
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}

	/***
	 * 获取收公文人的名称
	 * 
	 * @param document
	 */
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
	 * 收公文历史数据列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "receiveDocumentsListHistory", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String receiveDocumentsListHistory() throws Exception {
		Long currentOrgId = ThreadVariable.getOrganization().getId();
		gridPage = new GridPage(
				receiveDocumentsService.findReceiveDocumentsListForHistory(
						document, currentOrgId, page, rows, sidx, sord));
		return SUCCESS;
	}

	// 历史数据删除
	@Action(value = "deleteReceiveDocForHistory", results = {
			@Result(name = "success", type = "json", params = { "root",
					"success" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteReceiveDocForHistory() throws Exception {
		if (!StringUtil.isStringAvaliable(selectedIds)) {
			errorMessage = "数据删除失败，未获得需要删除的数据信息";
			return ERROR;
		}
		receiveDocumentsService
				.deleteReceiveDocForHistory(analyze(selectedIds));
		return SUCCESS;
	}

	/*
	 * 收文列表
	 */
	@Action(value = "receiveDocumentsList", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String receiveDocumentsList() throws Exception {
		if (document == null) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		} else {
			Long currentOrgId = ThreadVariable.getOrganization().getId();
			gridPage = new GridPage(
					receiveDocumentsService.findReceiveDocumentsList(document,
							currentOrgId, page, rows, sidx, sord));
		}
		return SUCCESS;
	}

	/***
	 * 根据ID查询公文信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "receiveDocumentsListByIds", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String receiveDocumentsListByIds() throws Exception {
		if (!StringUtil.isStringAvaliable(ids)) {
			errorMessage = "操作失败，查询参数未获得";
			return ERROR;
		}
		gridPage = new GridPage(
				receiveDocumentsService.findReceiveDocumentsListByIds(ids));
		return SUCCESS;
	}

	/*
	 * 签收文件
	 */
	@Action(value = "receiveDocuments", results = {
			@Result(type = "json", name = "success", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String receiveDocuments() throws Exception {
		if (!StringUtil.isStringAvaliable(selectedIds)) {
			errorMessage = "请至少选择一条数据进行签收";
			return ERROR;
		}
		if (userHasDocuments == null) {
			errorMessage = "签收失败，未获得签收回执等数据";
			return ERROR;
		}
		receiveDocumentsService.receiveDocuments(selectedIds.split(","),
				userHasDocuments);
		return SUCCESS;
	}

	// 收文删除功能
	@Action(value = "deleteReceiveDoc", results = {
			@Result(name = "success", type = "json", params = { "root",
					"success" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteReceiveDoc() throws Exception {
		if (!StringUtil.isStringAvaliable(selectedIds)) {
			errorMessage = "未获得需要删除的公文信息";
			return ERROR;
		}
		receiveDocumentsService.deleteReceiveDocByDocIdAndUserId(
				analyze(selectedIds), ThreadVariable.getUser().getId());
		return SUCCESS;
	}

	// 收文查询、快速过滤
	@Action(value = "searchReceiveDocuments", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String searchReceiveDocuments() throws Exception {
		if (null == searchDocumentVo) {
			gridPage = new GridPage(emptyPage(rows));
		} else {
			gridPage = new GridPage(
					receiveDocumentsService.searchReceiveDocuments(
							searchDocumentVo, page, rows, sidx, sord));
		}
		return SUCCESS;
	}

	private PageInfo<Document> emptyPage(int pageSize) {
		PageInfo<Document> pageInfo = new PageInfo<Document>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<Document>());
		return pageInfo;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public DocumentsHasOrg getDocumentsHasOrg() {
		return documentsHasOrg;
	}

	public void setDocumentsHasOrg(DocumentsHasOrg documentsHasOrg) {
		this.documentsHasOrg = documentsHasOrg;
	}

	public String getSelectedIds() {
		return selectedIds;
	}

	public void setSelectedIds(String selectedIds) {
		this.selectedIds = selectedIds;
	}

	public List<DocumentsHasAttachFiles> getDocfiles() {
		return docfiles;
	}

	public void setDocfiles(List<DocumentsHasAttachFiles> docfiles) {
		this.docfiles = docfiles;
	}

	public SearchDocumentVo getSearchDocumentVo() {
		return searchDocumentVo;
	}

	public void setSearchDocumentVo(SearchDocumentVo searchDocumentVo) {
		this.searchDocumentVo = searchDocumentVo;
	}

	public Boolean getReadState() {
		return readState;
	}

	public void setReadState(Boolean readState) {
		this.readState = readState;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public UserHasDocuments getUserHasDocuments() {
		return userHasDocuments;
	}

	public void setUserHasDocuments(UserHasDocuments userHasDocuments) {
		this.userHasDocuments = userHasDocuments;
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

}
