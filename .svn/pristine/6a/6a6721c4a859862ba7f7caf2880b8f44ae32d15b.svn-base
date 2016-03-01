package com.tianque.working.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.User;
import com.tianque.working.domain.Document;
import com.tianque.working.domain.DocumentSignVo;
import com.tianque.working.domain.DocumentsHasAttachFiles;
import com.tianque.working.domain.DocumentsHasOrg;
import com.tianque.working.domain.DocumentsHasUser;
import com.tianque.working.domain.SearchDocumentVo;
import com.tianque.working.domain.UserHasDocuments;

public interface DispatchDocumentsDao {
	PageInfo finddispatchDocumentsList(Document document, Integer page,
			Integer rows, String sidx, String sord);

	public Document adddispatchDocuments(Document document);

	public void addDocumentsHasOrg(Long documentId, List<Long> orgIds,
			String status);

	public void addDocAttachFiles(DocumentsHasAttachFiles[] attachFiles);

	public void deleteDispatchDocById(Long id);

	public void deleteDocumentsHasOrgByDocId(Long id);

	public void deleteDocumentsHasAttachFiles(Long documentId);

	public void deleteAttachFiles(Long id);

	public Document getDocumentsById(Long id);

	public List<DocumentsHasOrg> getDocumentsHasOrgByDocId(Long documentId);

	public List<DocumentsHasAttachFiles> getDocfilesByDocumentId(Long id);

	public DocumentsHasAttachFiles getDocfilesById(Long id);

	public PageInfo<Document> searchDispatchDocuments(
			SearchDocumentVo searchDocumentVo, Integer page, Integer rows,
			String sidx, String sord);

	public PageInfo<Document> searchAllDocuments(
			SearchDocumentVo searchDocumentVo, Integer page, Integer rows,
			String sidx, String sord);

	public void updateDocDispatchState(Document document);

	// 把公文的同步状态改为true
	public void updateDocSynchroDocs(Long id, Boolean synchroDocs);

	public PageInfo<DocumentsHasOrg> findSignerInfoList(
			DocumentsHasOrg documentsHasOrg, Long orgLevel, Long orgType,
			Integer page, Integer rows, String sidx, String sord);

	public Document updateDocument(Document document);

	public void deleteDocumentsHasAttachFileByFileId(Long fileId);

	public PageInfo<DocumentsHasUser> findReaderList(List<User> users,
			DocumentsHasUser documentsHasUser, Integer page, Integer rows,
			String sidx, String sord);

	public Map<String, Object> calculateDocumentPercent(Long documentId);

	public Document searchEarliestDocument();

	/***
	 * 保存公文与人员关系
	 */
	public void addUserHasDocument(UserHasDocuments userHasDocuments);

	/***
	 * 通过公文ID删除公文与用户关系
	 */
	public void deleteUserHasDocument(Long documenId);

	/***
	 * 根据公文ID查询收取公文用户信息
	 */
	public List<UserHasDocuments> getUserByDocumentsId(Long documentId);

	/***
	 * 更改公文阅读状态
	 */
	public void updateDocumentReadState(Long documentId, Long userId,
			Integer readState, Date readDate);

	/***
	 * 查询公文签收信息
	 */
	public List<DocumentSignVo> findReminderInfoByDocumentId(Long documentId);

	/***
	 * 公文管理历史数据查询
	 */
	public PageInfo finddispatchDocumentsListForHistory(Document document,
			Integer page, Integer rows, String sidx, String sord);

	/***
	 * 根据公文ID和用户ID查询用户是否已经拥有该公文
	 */
	public Integer countUserHasDocument(Long documentId, Long userId);

	/***
	 * 查看和转发时查询公文信息
	 */
	public Document getDocumentsByIdAndUserId(Long documentId, Long userId);
}
