package com.tianque.peopleLog.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.peopleLog.domain.CommentLog;
import com.tianque.peopleLog.domain.PeopleLog;

@Repository("SubLogDao")
public class SubLogDaoImpl extends AbstractBaseDao implements SubLogDao {
	/**
	 * 仅本级日志展示
	 */
	@Override
	public PageInfo<PeopleLog> findPeopleLogForPageByOrgInternalCode(String orgCode,
			Integer pageNum, Integer pageSize, String sortField, String sord) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgCode);
		map.put("sortField", sortField);
		map.put("sord", sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"peopleLog.countSubLogList", map);
		map.put("countNum", countNum);
		List<PeopleLog> list = getSqlMapClientTemplate().queryForList("peopleLog.findSubLogList",
				map, (pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<PeopleLog> createPageInfo(int pageNum, int pageSize, Integer countNum,
			List list) {
		PageInfo<PeopleLog> pageInfo = new PageInfo<PeopleLog>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	/**
	 * 本级及以下全部日志展示
	 */
	@Override
	public PageInfo<PeopleLog> findPeopleLogForPage(String orgCode, Integer pageNum,
			Integer pageSize, String sortField, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgCode);
		map.put("sortField", sortField);
		map.put("sord", sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"peopleLog.countAllSubLogList", map);
		map.put("countNum", countNum);
		List<PeopleLog> list = getSqlMapClientTemplate().queryForList(
				"peopleLog.findAllSubLogList", map, (pageNum - 1) * pageSize, pageSize);

		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	/**
	 * 显示需点评的日志
	 */
	@Override
	public PeopleLog findLogByLogId(Long logId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("logId", logId);
		return (PeopleLog) getSqlMapClientTemplate().queryForObject("peopleLog.findLog", map);
	}

	/**
	 * 保存日志点评
	 */
	@Override
	public CommentLog addComment(CommentLog commentLog) {

		Long id = (Long) getSqlMapClientTemplate().insert("commentLog.saveComment", commentLog);

		return findCommentLogById(id);
	}

	/*
	 * 查找评论内容
	 */
	public CommentLog findCommentLogById(Long id) {
		return (CommentLog) getSqlMapClientTemplate().queryForObject(
				"commentLog.getCommentLogById", id);
	}

	@Override
	public PageInfo<PeopleLog> findSubLogListByOrgInternalCode4Me(String orgInternalCode,
			Integer pageNum, Integer pageSize, String sortField, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("sortField", sortField);
		map.put("sord", sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"peopleLog.countSubLogList4Me", map);
		map.put("countNum", countNum);
		List<PeopleLog> list = getSqlMapClientTemplate().queryForList(
				"peopleLog.findSubLogList4Me", map, (pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	@Override
	public PageInfo<PeopleLog> findSubLogListByOrgInternalCode4All(String orgInternalCode,
			Integer pageNum, Integer pageSize, String sortField, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("sortField", sortField);
		map.put("sord", sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"peopleLog.countSubLogList4All", map);
		map.put("countNum", countNum);
		List<PeopleLog> list = getSqlMapClientTemplate().queryForList(
				"peopleLog.findSubLogList4All", map, (pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

}
