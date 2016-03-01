package com.tianque.working.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.working.constants.DocumentsConstants;
import com.tianque.working.dao.ReceiveDocumentsDao;
import com.tianque.working.domain.Document;
import com.tianque.working.domain.DocumentState;
import com.tianque.working.domain.SearchDocumentVo;
import com.tianque.working.domain.UserHasDocuments;

@Repository("receiveDocumentsDao")
public class ReceiveDocumentsDaompl extends AbstractBaseDao implements
		ReceiveDocumentsDao {

	@Override
	public void receiveDocuments(Long documentId, Long userId,
			UserHasDocuments userHasDocuments) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("documentId", documentId);
		map.put("userId", userId);
		map.put("isSign", DocumentsConstants.isSign);
		map.put("signDate", userHasDocuments.getSignDate());
		map.put("signContent", userHasDocuments.getSignContent());
		map.put("signer", userHasDocuments.getSigner());
		map.put("signUserid", userId);
		getSqlMapClientTemplate().update("document.signDocumentsById", map);
	}

	// 收文列表
	@Override
	public PageInfo findReceiveDocumentsList(Document document,
			Long currentOrgId, Integer page, Integer rows, String sidx,
			String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", ThreadVariable.getUser().getId());
		map.put("signState", document.getSignState());
		if (StringUtil.isStringAvaliable(sidx)) {
			map.put("sortField", sidx);
		}
		map.put("order", sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"document.countReceiveDocuments", map);
		if (countNum == null) {
			countNum = 0;
			return createDocumentsPageInfo(page, rows, countNum,
					new ArrayList<Document>());
		}
		List<Document> list = getSqlMapClientTemplate().queryForList(
				"document.findReceiveDocuments", map, (page - 1) * rows, rows);
		return createDocumentsPageInfo(page, rows, countNum, list);
	}

	@Override
	public void addDocumentsHasUsers(Long documentId, Long documentsHasOrgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("documentId", documentId);
		map.put("userId", ThreadVariable.getUser().getId());
		map.put("readState", DocumentState.READ);
		map.put("readDate", new Date());
		map.put("documentsHasOrgId", documentsHasOrgId);
		getSqlMapClientTemplate().insert("document.addDocumentsHasUsers", map);

	}

	@Override
	public Document getDocumentsHasUsersByDocIdAndUserId(Long documentId,
			Long documentsHasOrgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("documentId", documentId);
		map.put("userId", ThreadVariable.getUser().getId());
		map.put("documentsHasOrgId", documentsHasOrgId);
		return (Document) getSqlMapClientTemplate().queryForObject(
				"document.getDocumentsHasUsersByDocIdAndUserId", map);
	}

	@Override
	public int updateDocHasUsersDeleteStatByDocHasUserId(Long documentsHasOgrId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("documentsHasOgrId", documentsHasOgrId);
		map.put("readState", DocumentState.DELETE);
		map.put("userId", ThreadVariable.getUser().getId());
		return getSqlMapClientTemplate().update(
				"document.updateDocHasUsersByDocIdAndUserId", map);
	}

	@Override
	public PageInfo<Document> searchReceiveDocuments(
			SearchDocumentVo searchDocumentVo, Integer page, Integer rows,
			String sidx, String sord) {
		if (StringUtil.isStringAvaliable(sidx)) {
			searchDocumentVo.setSortField(sidx);
		}
		searchDocumentVo.setUserId(ThreadVariable.getUser().getId());
		searchDocumentVo.setOrder(sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"document.searchDocumentsByConditionCount", searchDocumentVo);
		List<Document> list = getSqlMapClientTemplate().queryForList(
				"document.searchDocumentsByCondition", searchDocumentVo,
				(page - 1) * rows, rows);
		if (countNum == null) {
			countNum = 0;
		}
		return createDocumentsPageInfo(page, rows, countNum, list);
	}

	@Override
	public Map<String, Object> caculateDocuments() {
		Long userId = ThreadVariable.getUser().getId();
		Integer notsignDocs = (Integer) getSqlMapClientTemplate()
				.queryForObject("document.countNotSignDoc", userId);
		Integer notReadDocs = (Integer) getSqlMapClientTemplate()
				.queryForObject("document.countNotReadDoc", userId);
		if (null == notsignDocs) {
			notsignDocs = 0;
		}
		if (null == notReadDocs) {
			notReadDocs = 0;
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("notsignDocs", notsignDocs);
		jsonMap.put("notReadDocs", notReadDocs);
		return jsonMap;
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

	@Override
	public Document getDocumentHasUserBydocumentsHasOrgId(
			Long documentsHasOrgId, Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("documentsHasOrgId", documentsHasOrgId);
		return (Document) getSqlMapClientTemplate().queryForObject(
				"document.getDocumentsHasUsersByDocIdAndUserId", map);
	}

	@Override
	public void deleteReceiveDocByDocIdAndUserId(Map<String, Object> map) {
		getSqlMapClientTemplate().update(
				"document.updateDocHasUsersDeleteByDocIdAndUserId", map);
	}

	@Override
	public PageInfo findReceiveDocumentsListByIds(String ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		List<Document> list = getSqlMapClientTemplate().queryForList(
				"document.findReceiveDocumentsListByIds", map);
		PageInfo<Document> pageInfo = new PageInfo<Document>();
		pageInfo.setResult(list);
		return pageInfo;
	}

	/*** 新增加的方法 ***/
	@Override
	public void deleteUserHasDocuments(Long documentId, Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("documentId", documentId);
		map.put("userId", userId);
		getSqlMapClientTemplate()
				.delete("document.deleteUserHasDocuments", map);
	}

	@Override
	public PageInfo findReceiveDocumentsListForHistory(Document document,
			Long currentOrgId, Integer page, Integer rows, String sidx,
			String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("organizationId", currentOrgId);
		map.put("userId", ThreadVariable.getUser().getId());
		map.put("historyDate", CalendarUtil.parseDate(
				DocumentsConstants.HISTORY_DATE, "yyyy-MM-dd"));
		if (document != null) {
			map.put("documentTitle", document.getTitle());
		}
		if (StringUtil.isStringAvaliable(sidx)) {
			map.put("sortField", sidx);
		}
		map.put("order", sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"document.countReceiveDocumentsForHistory", map);

		List<Document> list = getSqlMapClientTemplate().queryForList(
				"document.findReceiveDocumentsForHistory", map,
				(page - 1) * rows, rows);
		return createDocumentsPageInfo(page, rows, countNum, list);
	}
}
