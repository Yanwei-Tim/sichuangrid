package com.tianque.peopleLog.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.peopleLog.domain.CommentLog;
import com.tianque.peopleLog.domain.PeopleLog;
import com.tianque.peopleLog.domain.SearchPeopleLogVo;

public interface SearchCommentLogDao {

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
	public PageInfo<CommentLog> findCommentLogByCondition(SearchPeopleLogVo searchPeopleLogVo,
			Integer page, Integer rows, String sidx, String sord);

	/**
	 * 根据查询条件分页查询下辖日志(同级)
	 * 
	 * @param searchPeopleLogVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<PeopleLog> findSubLogByQueryConditionIsPeer(String orgInternalCode,
			SearchPeopleLogVo searchPeopleLogVo, Integer page, Integer rows, String sidx,
			String sord);

	/**
	 * 根据查询条件分页查询下辖日志(全部)
	 * 
	 * @param searchPeopleLogVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<PeopleLog> findSubLogByQueryConditionAll(String orgInternalCode,
			SearchPeopleLogVo searchPeopleLogVo, Integer page, Integer rows, String sidx,
			String sord);
}
