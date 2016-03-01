package com.tianque.working.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.resourcePool.domain.MyProfile;
import com.tianque.working.domain.Document;
import com.tianque.working.domain.DocumentSignVo;
import com.tianque.working.domain.DocumentsHasAttachFiles;
import com.tianque.working.domain.DocumentsHasOrg;
import com.tianque.working.domain.DocumentsHasUser;
import com.tianque.working.domain.SearchDocumentVo;

public interface DispatchDocumentsService {
	public PageInfo finddispatchDocumentsList(Document document, Integer page,
			Integer rows, String sidx, String sord);

	// public Document adddispatchDocuments(Document document,
	// DocumentsHasAttachFiles[] files, String sendObjCacheId,
	// String copySendObjCacheId);
	/***
	 * 公文管理历史数据查询
	 */
	public PageInfo finddispatchDocumentsListForHistory(Document document,
			Integer page, Integer rows, String sidx, String sord);

	/** 新公文管理新增信息 */
	public void addDispathDocumentInfo(String userIdList,
			String copyUserIdList, Document document,
			DocumentsHasAttachFiles[] files);

	public void deleteDispatchDocById(Long[] ids);

	public Document getDocumentsById(Long id);

	public List<DocumentsHasAttachFiles> getDocfilesByDocumentId(Long id);

	/**
	 * 根据文件本身的id查找附件，实现下载附件的功能
	 * 
	 * @param id
	 * @return
	 */
	public DocumentsHasAttachFiles getDocfilesByAttachFileId(Long id);

	public PageInfo<Document> searchDispatchDocuments(
			SearchDocumentVo searchDocumentVo, Integer page, Integer rows,
			String sidx, String sord);

	public PageInfo<Document> searchAllDocuments(
			SearchDocumentVo searchDocumentVo, Integer page, Integer rows,
			String sidx, String sord);

	public void sendDocuments(Long[] ids, String mode);

	public PageInfo findSignerInfoList(DocumentsHasOrg documentsHasOrg,
			Integer page, Integer rows, String sidx, String sord);

	public Document updateDocument(String userIds, String userIdsCopy,
			Document document, DocumentsHasAttachFiles[] files);

	public PageInfo findReaderList(DocumentsHasUser documentsHasUser,
			Integer page, Integer rows, String sidx, String sord);

	public Map<String, Object> calculateDocumentPercent(Long documentId);

	public List<Document> getDocumentsByIds(String ids);

	public List<MyProfile> synchToMyProfile(String ids, Long sendMessage,
			Long resourcePoolTypeId, String setPermissionCacheId);

	/**
	 * 公文催收
	 * 
	 * @param organizationIds
	 *            要催收的部门id
	 * @param document
	 *            要催收的公文
	 */
	public void urgeDocuments(Long[] organizationIds, Document document);

	public Document searchEarliestDocument();

	/**
	 * 公文管理中删除附件
	 * 
	 * @param id
	 */
	public void deleteMyDocumentsAttachFile(Long id);

	/***
	 * 修改公文阅读状态
	 */
	public void updateDocumentReadState(Long documentId, Long userId,
			Integer readState, Date readDate);

	/***
	 * 转发直接绑定现有公文与转发给的用户关系
	 */
	public void bindingDocWithUser(Document document, String[] userId,
			String[] copyUserId);

	/***
	 * 查询公文签收信息
	 * 
	 * @param userId
	 * @return
	 */
	public List<DocumentSignVo> findReminderInfoByDocumentId(Long documentId);

	/***
	 * 历史公文数据查询
	 * 
	 * @param ids
	 */
	public void deleteDispatchDocForHistroy(Long[] ids);

	/***
	 * 查看转发公文数据
	 */
	public Document getDocumentsByIdAndUserId(Long documentId, Long userId);

}
