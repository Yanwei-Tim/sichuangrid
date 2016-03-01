package com.tianque.working.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.PageInfoUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.User;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.file.domain.AttachFile;
import com.tianque.util.ParametersConvertUtil;
import com.tianque.working.constants.DocumentsConstants;
import com.tianque.working.dao.DispatchDocumentsDao;
import com.tianque.working.domain.Document;
import com.tianque.working.domain.DocumentSignVo;
import com.tianque.working.domain.DocumentState;
import com.tianque.working.domain.DocumentsHasAttachFiles;
import com.tianque.working.domain.DocumentsHasOrg;
import com.tianque.working.domain.DocumentsHasUser;
import com.tianque.working.domain.SearchDocumentVo;
import com.tianque.working.domain.UserHasDocuments;

@Repository("dispatchDocumentsDao")
public class DispatchDocumentsDaoImpl extends AbstractBaseDao implements
		DispatchDocumentsDao {
	@Override
	public PageInfo<Document> finddispatchDocumentsList(Document document,
			Integer page, Integer rows, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != document) {
			map.put("dispatchState", document.getDispatchState());
			map.put("orgId", document.getOrgId());
			map.put("userId", document.getUserId());
		}
		if (StringUtil.isStringAvaliable(sidx)) {
			map.put("sortField", sidx);
		}
		map.put("order", sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"document.countDispatchDocuments", map);

		List<Document> list = getSqlMapClientTemplate().queryForList(
				"document.findDispatchDocuments", map, (page - 1) * rows, rows);
		return createDocumentsPageInfo(page, rows, countNum, list);
	}

	@Override
	public Document adddispatchDocuments(Document document) {
		validateDocument(document);
		Long id = (Long) getSqlMapClientTemplate().insert(
				"document.adddispatchDocuments", document);
		return getDocumentsById(id);
	}

	private void validateDocument(Document document) {
		if (document.getTitle() == null || document.getTitle().trim().isEmpty()) {
			throw new BusinessValidationException("文件标题必须输入");
		}
		if (document.getDispatchUnit() == null
				|| document.getDispatchUnit().trim().isEmpty()) {
			throw new BusinessValidationException("发文单位必须输入");
		}
	}

	// 建立文件和部门的关联关系
	@Override
	public void addDocumentsHasOrg(Long documentId, List<Long> orgIds,
			String status) {
		List<Long> newList = new ArrayList<Long>();

		for (Long organizationId : orgIds) {
			if (newList.contains(organizationId)) {
				continue;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("documentId", documentId);
			map.put("organizationId", organizationId);
			map.put("signState", DocumentState.NOTSIGN);
			map.put("status", status);
			getSqlMapClientTemplate()
					.insert("document.addDocumentsHasOrg", map);

			newList.add(organizationId);
		}
	}

	@Override
	public void updateDocDispatchState(Document document) {
		getSqlMapClientTemplate().update("document.updateDocDispatchState",
				document);
	}

	@Override
	public void updateDocSynchroDocs(Long id, Boolean synchroDocs) {
		Document document = new Document();
		document.setId(id);
		document.setSynchroDocs(synchroDocs);
		getSqlMapClientTemplate().update("document.updateDocSynchroDocs",
				document);
	}

	// 删除发文
	@Override
	public void deleteDispatchDocById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("id是空的");
		} else {

			getSqlMapClientTemplate().delete("document.deleteDispatchDocById",
					id);
		}
	}

	// 删除发文和部门的关系
	@Override
	public void deleteDocumentsHasOrgByDocId(Long id) {
		if (id == null) {
			throw new BusinessValidationException("id是空的");
		} else {

			getSqlMapClientTemplate().delete(
					"document.deleteDocumentsHasOrgByDocId", id);
		}
	}

	// 删除发文和附件的关联关系
	@Override
	public void deleteDocumentsHasAttachFiles(Long documentId) {

		getSqlMapClientTemplate().delete(
				"document.deleteDocumentsHasAttachFiles", documentId);
	}

	// 删除附件
	@Override
	public void deleteAttachFiles(Long id) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("moduleKey", DocumentState.DOCUMENTS_FILE);
		getSqlMapClientTemplate().delete("document.deleteAttachFiles", map);
	}

	@Override
	public Document getDocumentsById(Long id) {
		return (Document) getSqlMapClientTemplate().queryForObject(
				"document.getDocumentsById", id);
	}

	@Override
	public List<DocumentsHasOrg> getDocumentsHasOrgByDocId(Long documentId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("documentId", documentId);
		return getSqlMapClientTemplate().queryForList(
				"document.getDocumentsHasOrgByDocId", map);
	}

	// 根据发文id查找附件
	@Override
	public List<DocumentsHasAttachFiles> getDocfilesByDocumentId(Long id) {
		List<DocumentsHasAttachFiles> docHasAttachFiles = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("moduleKey", DocumentState.DOCUMENTS_FILE);

		docHasAttachFiles = getSqlMapClientTemplate().queryForList(
				"document.getDocfilesByDocumentId", map);
		return docHasAttachFiles;
	}

	// 根据文件本省的id查找附件实现下载附件的功能
	@Override
	public DocumentsHasAttachFiles getDocfilesById(Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fileId", id);
		map.put("moduleKey", DocumentState.DOCUMENTS_FILE);
		return (DocumentsHasAttachFiles) getSqlMapClientTemplate()
				.queryForObject("document.getDocfilesById", map);
	}

	@Override
	public void addDocAttachFiles(DocumentsHasAttachFiles[] attachFiles) {
		if (attachFiles != null) {
			Map<String, Object> params = new HashMap<String, Object>();
			for (DocumentsHasAttachFiles file : attachFiles) {
				if (file == null)
					continue;
				fillAttachFileParams(params, file);
				Long id = (Long) getSqlMapClientTemplate().insert(
						"document.addDocAttachFiles", params);
				getSqlMapClientTemplate().insert("attachFiles.addAttachFile",
						constractAttachFileFromDocAttachFile(id, file));
			}
		}
	}

	@Override
	public PageInfo<Document> searchDispatchDocuments(
			SearchDocumentVo searchDocumentVo, Integer page, Integer rows,
			String sidx, String sord) {
		if (StringUtil.isStringAvaliable(sidx)) {
			searchDocumentVo.setSortField(sidx);
		}
		searchDocumentVo.setOrder(sord);
		searchDocumentVo.setOrganizationId(ThreadVariable.getOrganization()
				.getId());
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"document.countSearchDispatchDocuments", searchDocumentVo);

		List<Document> list = getSqlMapClientTemplate().queryForList(
				"document.searchDispatchDocuments", searchDocumentVo,
				(page - 1) * rows, rows);
		return createDocumentsPageInfo(page, rows, countNum, list);
	}

	@Override
	public PageInfo<Document> searchAllDocuments(
			SearchDocumentVo searchDocumentVo, Integer page, Integer rows,
			String sidx, String sord) {
		if (StringUtil.isStringAvaliable(sidx)) {
			searchDocumentVo.setSortField(sidx);
		}
		searchDocumentVo.setOrder(sord);
		searchDocumentVo.setOrganizationId(ThreadVariable.getOrganization()
				.getId());
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"document.countSearchAllDocuments", searchDocumentVo);

		List<Document> list = getSqlMapClientTemplate().queryForList(
				"document.searchAllDocuments", searchDocumentVo,
				(page - 1) * rows, rows);
		return createDocumentsPageInfo(page, rows, countNum, list);
	}

	@Override
	public PageInfo<DocumentsHasOrg> findSignerInfoList(
			DocumentsHasOrg documentsHasOrg, Long orgLevel, Long orgType,
			Integer page, Integer rows, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("documentId", documentsHasOrg.getDocumentId());
		map.put("signState", documentsHasOrg.getSignState());
		map.put("status", documentsHasOrg.getStatus());
		if (StringUtil.isStringAvaliable(sidx)) {
			map.put("sortField", sidx);
		}
		map.put("order", sord);
		if (documentsHasOrg.getOrgIdsList() != null
				&& documentsHasOrg.getOrgIdsList().size() > 0) {
			map.put("orgIdsList", documentsHasOrg.getOrgIdsList());
		}
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"document.countSignerInfoList", map);

		List<DocumentsHasOrg> list = getSqlMapClientTemplate().queryForList(
				"document.findSignerInfoList", map, (page - 1) * rows, rows);
		return createDocumentsHasOrgPageInfo(page, rows, countNum, list);
	}

	// 阅读人列表
	@Override
	public PageInfo<DocumentsHasUser> findReaderList(List<User> users,
			DocumentsHasUser documentsHasUser, Integer page, Integer rows,
			String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> userIds = ParametersConvertUtil.convertToListString(users);
		if (userIds == null) {
			return PageInfoUtil.emptyPage(rows);
		}
		map.put("userIds", userIds);
		map.put("documentId", documentsHasUser.getDocumentId());
		// Integer countNum = (Integer)
		// getSqlMapClientTemplate().queryForObject(
		// "document.countReaderList", map);
		List<DocumentsHasUser> list = getSqlMapClientTemplate().queryForList(
				"document.findReaderList", map, (page - 1) * rows, rows);
		return createDocumentsHasUserPageInfo(page, rows, users, list,
				documentsHasUser.getOrgName());
	}

	@Override
	public Map<String, Object> calculateDocumentPercent(Long documentId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("documentId", documentId);
		Integer sendDocs = (Integer) getSqlMapClientTemplate().queryForObject(
				"document.countSendDocs", map);
		Integer sendDocsOfSign = (Integer) getSqlMapClientTemplate()
				.queryForObject("document.countSendDocsOfSign", map);
		Integer copySendDocs = (Integer) getSqlMapClientTemplate()
				.queryForObject("document.countCopySendDocs", map);
		Integer copySendDocsOfSign = (Integer) getSqlMapClientTemplate()
				.queryForObject("document.countCopySendDocsOfSign", map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("sendDocs", null == sendDocs ? 0 : sendDocs);
		jsonMap.put("sendDocsOfSign", null == sendDocsOfSign ? 0
				: sendDocsOfSign);
		jsonMap.put("sendDocsOfNotSign", (sendDocs - sendDocsOfSign));
		jsonMap.put("sendDocsOfSignPercent", 0 == sendDocs ? 0
				: ((sendDocsOfSign * 100) / sendDocs));
		jsonMap.put("sendDocsOfNotSignPercent", 0 == sendDocs ? 0
				: (100 - ((sendDocsOfSign * 100) / sendDocs)));

		jsonMap.put("copySendDocs", null == copySendDocs ? 0 : copySendDocs);
		jsonMap.put("copySendDocsOfSign", null == copySendDocsOfSign ? 0
				: copySendDocsOfSign);
		jsonMap.put("copySendDocsOfNotSign",
				(copySendDocs - copySendDocsOfSign));
		jsonMap.put("copySendDocsOfSignPercent", 0 == copySendDocs ? 0
				: (((copySendDocsOfSign * 100) / copySendDocs)));
		jsonMap.put("copySendDocsOfNotSignPercent", 0 == copySendDocs ? 0
				: (100 - ((copySendDocsOfSign * 100) / copySendDocs)));

		jsonMap.put("totalDocs", (sendDocs + copySendDocs));
		jsonMap.put("totalDocsSign", (sendDocsOfSign + copySendDocsOfSign));
		jsonMap.put(
				"totalDocsNotSign",
				((sendDocs + copySendDocs) - (sendDocsOfSign + copySendDocsOfSign)));
		jsonMap.put(
				"totalDocsSignPercent",
				0 == (sendDocs + copySendDocs) ? 0
						: (((sendDocsOfSign + copySendDocsOfSign) * 100) / (sendDocs + copySendDocs)));
		jsonMap.put("totalDocsNotSignPercent",
				0 == (sendDocs + copySendDocs) ? 0
						: (100 - ((sendDocsOfSign + copySendDocsOfSign) * 100)
								/ (sendDocs + copySendDocs)));

		return jsonMap;
	}

	private void fillAttachFileParams(Map<String, Object> params,
			DocumentsHasAttachFiles file) {
		params.put("documentId", file.getDocumentId());
		params.put("fileType", DocumentState.DOCUMENTS_FILE);
	}

	private AttachFile constractAttachFileFromDocAttachFile(Long objId,
			DocumentsHasAttachFiles file) {
		AttachFile afile = new AttachFile();
		afile.setModuleKey(DocumentState.DOCUMENTS_FILE);
		afile.setModuleObjectId(objId.toString());
		afile.setName(file.getFileName());
		afile.setPhysicsFullFileName(file.getFileActualUrl());
		afile.setName(file.getFileName());
		return afile;
	}

	private PageInfo<Document> createDocumentsPageInfo(int page, int rows,
			Integer countNum, List<Document> list) {
		PageInfo<Document> pageInfo = new PageInfo<Document>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	private PageInfo<DocumentsHasOrg> createDocumentsHasOrgPageInfo(int page,
			int rows, Integer countNum, List<DocumentsHasOrg> list) {
		PageInfo<DocumentsHasOrg> pageInfo = new PageInfo<DocumentsHasOrg>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	@Override
	public Document updateDocument(Document document) {
		getSqlMapClientTemplate().update("document.updateDocument", document);
		return getDocumentsById(document.getId());
	}

	@Override
	public void deleteDocumentsHasAttachFileByFileId(Long fileId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("fileId", fileId);
		params.put("moduleKey", DocumentState.DOCUMENTS_FILE);
		getSqlMapClientTemplate().delete(
				"document.deleteDocumentsHasAttachFileByFileId", fileId);
		getSqlMapClientTemplate().delete("document.deleteAttachFilesByFileId",
				params);

	}

	private PageInfo<DocumentsHasUser> createDocumentsHasUserPageInfo(int page,
			int rows, List<User> users, List<DocumentsHasUser> lists,
			String orgName) {
		PageInfo<DocumentsHasUser> pageInfo = new PageInfo<DocumentsHasUser>();
		List<DocumentsHasUser> documentsHasUsers = new ArrayList<DocumentsHasUser>();
		if (lists == null) {
			lists = new ArrayList<DocumentsHasUser>();
		}
		for (User user : users) {
			DocumentsHasUser documentsHasUser = new DocumentsHasUser();
			for (DocumentsHasUser list : lists) {
				if (list.getUserId().equals(user.getId())) {
					documentsHasUser.setReadDate(list.getReadDate());
					documentsHasUser.setReadState(list.getReadState());
				}
			}
			documentsHasUser.setMobile(user.getMobile());
			documentsHasUser.setWorkPhone(user.getWorkPhone());
			documentsHasUser.setName(user.getName());
			documentsHasUser.setOrgName(orgName);
			documentsHasUsers.add(documentsHasUser);
		}
		pageInfo.setResult(documentsHasUsers);
		pageInfo.setTotalRowSize(users.size());
		pageInfo.setCurrentPage(page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	@Override
	public Document searchEarliestDocument() {
		return (Document) getSqlMapClientTemplate().queryForObject(
				"document.searchEarliestDocument");
	}

	@Override
	public void addUserHasDocument(UserHasDocuments userHasDocuments) {
		getSqlMapClientTemplate().insert("document.addUserHasDocuments",
				userHasDocuments);
	}

	@Override
	public void deleteUserHasDocument(Long documenId) {
		getSqlMapClientTemplate().delete("document.deleteUserHasDocument",
				documenId);
	}

	@Override
	public List<UserHasDocuments> getUserByDocumentsId(Long documentId) {
		return getSqlMapClientTemplate().queryForList(
				"document.getUserByDocumentsId", documentId);
	}

	@Override
	public void updateDocumentReadState(Long documentId, Long userId,
			Integer readState, Date readDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("documentId", documentId);
		map.put("userId", userId);
		map.put("readState", readState);
		map.put("readDate", readDate);
		getSqlMapClientTemplate().update("document.updateDocumentReadState",
				map);
	}

	@Override
	public List<DocumentSignVo> findReminderInfoByDocumentId(Long documentId) {
		return getSqlMapClientTemplate().queryForList(
				"document.findReminderInfoByDocumentId", documentId);
	}

	@Override
	public PageInfo finddispatchDocumentsListForHistory(Document document,
			Integer page, Integer rows, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != document) {
			map.put("orgId", document.getOrgId());
			map.put("documentTitle", document.getTitle());
		}
		if (StringUtil.isStringAvaliable(sidx)) {
			map.put("sortField", sidx);
		}
		map.put("historyDate", CalendarUtil.parseDate(
				DocumentsConstants.HISTORY_DATE, "yyyy-MM-dd"));
		map.put("order", sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"document.countDispatchDocumentsForHistory", map);

		List<Document> list = getSqlMapClientTemplate().queryForList(
				"document.findDispatchDocumentsForHistory", map,
				(page - 1) * rows, rows);
		return createDocumentsPageInfo(page, rows, countNum, list);
	}

	@Override
	public Integer countUserHasDocument(Long documentId, Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("documentId", documentId);
		map.put("userId", userId);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"document.countUserHasDocument", map);
	}

	@Override
	public Document getDocumentsByIdAndUserId(Long documentId, Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("documentId", documentId);
		map.put("userId", userId);
		return (Document) getSqlMapClientTemplate().queryForObject(
				"document.getDocumentsByIdAndUserId", map);
	}
}
