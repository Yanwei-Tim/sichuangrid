package com.tianque.working.dao;

import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.working.domain.Document;
import com.tianque.working.domain.SearchDocumentVo;
import com.tianque.working.domain.UserHasDocuments;

public interface ReceiveDocumentsDao {
	/***
	 * 签收用户所选择的公文信息
	 * 
	 * @param documentId
	 *            公文ID
	 * @param userId
	 *            用户ID
	 * @param userHasDocuments
	 *            签收内容
	 */
	public void receiveDocuments(Long documentId, Long userId,
			UserHasDocuments userHasDocuments);

	public PageInfo findReceiveDocumentsList(Document document,
			Long currentOrgId, Integer page, Integer rows, String sidx,
			String sord);

	/***
	 * 收公文历史数据列表
	 * 
	 * @param document
	 * @param currentOrgId
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo findReceiveDocumentsListForHistory(Document document,
			Long currentOrgId, Integer page, Integer rows, String sidx,
			String sord);

	public void addDocumentsHasUsers(Long documentId, Long userId);

	public Document getDocumentsHasUsersByDocIdAndUserId(Long documentId,
			Long documentsHasOrgId);

	public Document getDocumentHasUserBydocumentsHasOrgId(
			Long documentsHasOrgId, Long userId);

	public int updateDocHasUsersDeleteStatByDocHasUserId(Long documentId);

	public PageInfo<Document> searchReceiveDocuments(
			SearchDocumentVo searchDocumentVo, Integer page, Integer rows,
			String sidx, String sord);

	public Map<String, Object> caculateDocuments();

	public void deleteReceiveDocByDocIdAndUserId(Map<String, Object> map);

	/***
	 * 通过ID查询公文信息
	 * 
	 * @param ids
	 * @return
	 */
	public PageInfo findReceiveDocumentsListByIds(String ids);

	/***
	 * 删除公文与用户关系
	 */
	public void deleteUserHasDocuments(Long documentId, Long userId);

}
