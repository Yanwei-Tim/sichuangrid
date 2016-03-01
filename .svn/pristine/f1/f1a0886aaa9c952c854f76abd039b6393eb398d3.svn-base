package com.tianque.peopleLog.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.peopleLog.domain.CommentLog;
import com.tianque.peopleLog.domain.PeopleLog;
import com.tianque.peopleLog.domain.SearchPeopleLogVo;
import com.tianque.sysadmin.service.PropertyDictService;

@Repository("searchCommentLogDao")
public class SearchCommentLogDaoImpl extends AbstractBaseDao implements SearchCommentLogDao {
	@Autowired
	private PropertyDictService propertyDictService;

	/**
	 * 根据查询条件分页查询我的评论
	 * 
	 * @param searchPeopleLogVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	@Override
	public PageInfo<CommentLog> findCommentLogByCondition(SearchPeopleLogVo searchPeopleLogVo,
			Integer pageNum, Integer pageSize, String sortField, String sord) {
		if (sortField.equals("title")) {
			sortField = "p.title";
		}
		if (sortField.equals("logType")) {
			sortField = "p.logType";
		}
		if (sortField.equals("id")) {
			sortField = "c.id";
		}
		if (sortField.equals("commentTime")) {
			sortField = "c.commentTime";
		}
		if (sortField.equals("comments")) {
			sortField = "c.comments";
		}
		searchPeopleLogVo.setSortField(sortField);
		searchPeopleLogVo.setOrder(sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchCommentLog.countSearchComment", searchPeopleLogVo);
		List<Map<String, Object>> list = getSqlMapClientTemplate().queryForList(
				"searchCommentLog.searchComment", searchPeopleLogVo, (pageNum - 1) * pageSize,
				pageSize);
		List commentLogs = new ArrayList<CommentLog>();
		for (int i = 0; i < list.size(); i++) {
			CommentLog commentLog = new CommentLog();
			Map<String, Object> commentMap = list.get(i);
			commentLog.setCommentTime((Date) commentMap.get("COMMENTTIME"));
			commentLog.setComments((String) commentMap.get("COMMENTS"));
			commentLog.setId(((BigDecimal) commentMap.get("ID")).longValue());
			commentLog.setLogId(((BigDecimal) commentMap.get("LOGID")).longValue());
			commentLog.setUserId(((BigDecimal) commentMap.get("USERID")).longValue());
			commentLog.getPeopleLog();
			PeopleLog peopleLog = new PeopleLog();
			peopleLog.setTitle((String) commentMap.get("PEOPLELOGTITLE"));
			peopleLog.setLogType(propertyDictService.getPropertyDictById(((BigDecimal) commentMap
					.get("PEOPLELOGTYPE")).longValue()));
			Organization org = (Organization) getSqlMapClientTemplate().queryForObject(
					"organization.getOrganizationByOrgInternalCode",
					ThreadVariable.getOrganization().getOrgInternalCode());
			peopleLog.setOrganization(org);
			commentLog.setPeopleLog(peopleLog);
			commentLogs.add(commentLog);
		}

		return createPageInfo(pageNum, pageSize, countNum, commentLogs);
	}

	private PageInfo<CommentLog> createPageInfo(int pageNum, int pageSize, Integer countNum,
			List list) {
		PageInfo<CommentLog> pageInfo = new PageInfo<CommentLog>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	/**
	 * 根据查询条件分页查询下辖日志
	 */

	/**
	 * 同级下查询下辖日志
	 */
	@Override
	public PageInfo<PeopleLog> findSubLogByQueryConditionIsPeer(String orgInternalCode,
			SearchPeopleLogVo searchPeopleLogVo, Integer pageNum, Integer pageSize, String sidx,
			String sord) {
		searchPeopleLogVo.setSortField(sidx);
		searchPeopleLogVo.setOrder(sord);
		searchPeopleLogVo.setOrgInternalCode(orgInternalCode);
		if (searchPeopleLogVo.getCommentLog() != null
				&& "".equals(searchPeopleLogVo.getCommentLog().get("comments"))
				&& "".equals(searchPeopleLogVo.getCommentLog().get("commentUser"))) {
			searchPeopleLogVo.setCommentLog(null);
		}
		List<PeopleLog> list = getSqlMapClientTemplate().queryForList(
				"searchCommentLog.searchSubLogIsPeer", searchPeopleLogVo, (pageNum - 1) * pageSize,
				pageSize);
//		Integer countNum = list.size();
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject("searchCommentLog.countSearchSubLogIsPeer", searchPeopleLogVo);
		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		return createPage(pageNum, pageSize, countNum, list);
	}

	private PageInfo<PeopleLog> createPage(int pageNum, int pageSize, Integer countNum, List list) {
		PageInfo<PeopleLog> pageInfo = new PageInfo<PeopleLog>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	/**
	 * 全部下查询下辖日志
	 */
	@Override
	public PageInfo<PeopleLog> findSubLogByQueryConditionAll(String orgInternalCode,
			SearchPeopleLogVo searchPeopleLogVo, Integer pageNum, Integer pageSize, String sidx,
			String sord) {
		searchPeopleLogVo.setSortField(sidx);
		searchPeopleLogVo.setOrder(sord);
		searchPeopleLogVo.setOrgInternalCode(orgInternalCode);
		if (searchPeopleLogVo.getCommentLog() != null
				&& "".equals(searchPeopleLogVo.getCommentLog().get("comments"))
				&& "".equals(searchPeopleLogVo.getCommentLog().get("commentUser"))) {
			searchPeopleLogVo.setCommentLog(null);
		}
		List<PeopleLog> list = getSqlMapClientTemplate().queryForList(
				"searchCommentLog.searchSubLogAll", searchPeopleLogVo, (pageNum - 1) * pageSize,
				pageSize);
//		Integer countNum = list.size();
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject("searchCommentLog.countSearchSubLogAll", searchPeopleLogVo);
		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		return createPage(pageNum, pageSize, countNum, list);
	}

}
