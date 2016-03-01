package com.tianque.working.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.DateUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.working.dao.ReceiveDocumentsDao;
import com.tianque.working.domain.Document;
import com.tianque.working.domain.DocumentState;
import com.tianque.working.domain.SearchDocumentVo;
import com.tianque.working.domain.UserHasDocuments;
import com.tianque.working.service.ReceiveDocumentsService;

@Service("receiveDocumentsService")
public class ReceiveDocumentsServiceImpl implements ReceiveDocumentsService {
	public final static Logger logger = LoggerFactory
			.getLogger(AbstractBaseService.class);

	@Autowired
	private ReceiveDocumentsDao receiveDocumentsDao;

	@Override
	public PageInfo findReceiveDocumentsList(Document document,
			Long currentOrgId, Integer page, Integer rows, String sidx,
			String sord) {
		PageInfo pageInfo = null;
		try {
			pageInfo = receiveDocumentsDao.findReceiveDocumentsList(document,
					currentOrgId, page, rows, sidx, sord);
		} catch (Exception e) {
			throw new ServiceValidationException("收文列表出错", e);
		}
		return pageInfo;
	}

	@Override
	public void receiveDocuments(String[] ids, UserHasDocuments userHasDocuments) {
		if (ids == null || ids.length == 0) {
			throw new BusinessValidationException("请至少选择一条公文进行签收");
		}
		try {
			for (String id : ids) {
				receiveDocumentsDao.receiveDocuments(Long.parseLong(id),
						ThreadVariable.getUser().getId(), userHasDocuments);
			}
		} catch (Exception e) {
			throw new ServiceValidationException("收文功能出错了", e);
		}
	}

	@Override
	public void addDocumentsHasUsers(Long documentId, Long documentsHasOrgId) {
		Document doc = receiveDocumentsDao
				.getDocumentsHasUsersByDocIdAndUserId(documentId,
						documentsHasOrgId);
		if (doc == null) {
			receiveDocumentsDao.addDocumentsHasUsers(documentId,
					documentsHasOrgId);
		}
	}

	@Override
	public void updateDocHasUsersByDocIdAndUserId(Long[] ids) {
		for (Long id : ids) {
			receiveDocumentsDao.updateDocHasUsersDeleteStatByDocHasUserId(id);
		}
	}

	@Override
	public PageInfo<Document> searchReceiveDocuments(
			SearchDocumentVo searchDocumentVo, Integer page, Integer rows,
			String sidx, String sord) {
		PageInfo<Document> pageInfo = null;
		try {
			setProperties(searchDocumentVo);
			pageInfo = receiveDocumentsDao.searchReceiveDocuments(
					searchDocumentVo, page, rows, sidx, sord);
		} catch (Exception e) {
			throw new ServiceValidationException("异常信息", e);
		}
		return pageInfo;
	}

	@Override
	public Map<String, Object> caculateDocuments() {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			jsonMap = receiveDocumentsDao.caculateDocuments();
		} catch (Exception e) {
			throw new ServiceValidationException("异常信息", e);
		}
		return jsonMap;
	}

	@Override
	public Boolean isUserReadDocumentByDocumentHasOrgId(Long documentsHasOrgId) {
		Document doc = receiveDocumentsDao
				.getDocumentsHasUsersByDocIdAndUserId(null, documentsHasOrgId);
		return null != doc;
	}

	private void setProperties(SearchDocumentVo searchDocumentVo) {
		searchDocumentVo.setOrganizationId(ThreadVariable.getOrganization()
				.getId());
		if (null != searchDocumentVo.getSignDateEnd()) {
			searchDocumentVo.setSignDateEnd(DateUtil
					.getEndTime(searchDocumentVo.getSignDateEnd()));
		}
		if (null != searchDocumentVo.getTransmitDateEnd()) {
			searchDocumentVo.setTransmitDateEnd(DateUtil
					.getEndTime(searchDocumentVo.getTransmitDateEnd()));
		}
	}

	@Override
	public void deleteReceiveDocByDocIdAndUserId(Long[] analyze, Long userId) {
		if (analyze == null || analyze.length == 0) {
			throw new BusinessValidationException("删除失败，未获得需要删除的数据");
		}
		try {
			for (Long documentId : analyze) {
				// 先删除公文相关的用户信息
				receiveDocumentsDao.deleteUserHasDocuments(documentId, userId);
			}
		} catch (Exception e) {
			throw new ServiceValidationException("收公文删除失败", e);
		}

	}

	@Override
	public PageInfo findReceiveDocumentsListByIds(String ids) {
		if (!StringUtil.isStringAvaliable(ids)) {
			throw new BusinessValidationException("信息查询失败，未获得参数");
		}
		return receiveDocumentsDao.findReceiveDocumentsListByIds(ids);
	}

	@Override
	public PageInfo findReceiveDocumentsListForHistory(Document document,
			Long currentOrgId, Integer page, Integer rows, String sidx,
			String sord) {
		PageInfo pageInfo = null;
		try {
			pageInfo = receiveDocumentsDao.findReceiveDocumentsListForHistory(
					document, currentOrgId, page, rows, sidx, sord);
		} catch (Exception e) {
			throw new ServiceValidationException("收文列表出错", e);
		}
		return pageInfo;
	}

	@Override
	public void deleteReceiveDocForHistory(Long[] analyze) {
		Map<String, Object> map;
		for (Long id : analyze) {
			map = new HashMap<String, Object>();
			map.put("documentId", id);
			map.put("readState", DocumentState.DELETE);
			map.put("userId", ThreadVariable.getUser().getId());
			receiveDocumentsDao.deleteReceiveDocByDocIdAndUserId(map);
		}

	}
}
