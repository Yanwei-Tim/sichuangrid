package com.tianque.working.service;

import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.working.domain.Document;
import com.tianque.working.domain.SearchDocumentVo;
import com.tianque.working.domain.UserHasDocuments;

public interface ReceiveDocumentsService {
	public void receiveDocuments(String[] ids, UserHasDocuments userHasDocuments);

	public PageInfo findReceiveDocumentsList(Document document,
			Long currentOrgId, Integer page, Integer rows, String sidx,
			String sord);

	/***
	 * 历史数据查询
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

	/***
	 * 通过ID查询公文信息
	 * 
	 */
	public PageInfo findReceiveDocumentsListByIds(String ids);

	public void addDocumentsHasUsers(Long documentId, Long documentsHasOrgId);

	public void updateDocHasUsersByDocIdAndUserId(Long[] ids);

	/*
	 * 查询、快速过滤
	 */
	public PageInfo<Document> searchReceiveDocuments(
			SearchDocumentVo searchDocumentVo, Integer page, Integer rows,
			String sidx, String sord);

	public Map<String, Object> caculateDocuments();

	/**
	 * 通过documentsHasOrg的主键id，判断用户是否读了文件，有记录读，且是读，没有记录未读
	 * 
	 * @param documentsHasOrgId
	 * @return
	 */
	public Boolean isUserReadDocumentByDocumentHasOrgId(Long documentsHasOrgId);

	public void deleteReceiveDocByDocIdAndUserId(Long[] analyze, Long userId);

	/***
	 * 历史收文删除
	 */
	public void deleteReceiveDocForHistory(Long[] analyze);
}
