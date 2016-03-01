package com.tianque.peopleLog.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.peopleLog.domain.CommentLog;
import com.tianque.peopleLog.domain.PeopleLog;

public interface SubLogDao {

	public PageInfo<PeopleLog> findPeopleLogForPageByOrgInternalCode(String orgCode,
			Integer pageNum, Integer pageSize, String sortField, String sord);

	public PageInfo<PeopleLog> findPeopleLogForPage(String orgCode, Integer pageNum,
			Integer pageSize, String sortField, String sord);

	public PeopleLog findLogByLogId(Long logId);

	public CommentLog addComment(CommentLog comment);

	public CommentLog findCommentLogById(Long id);

	/**
	 * 工作台
	 * 根据网格编码分页查找下辖日志本级列表
	 * 
	 * @param orgInternalCode
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<PeopleLog> findSubLogListByOrgInternalCode4Me(String orgInternalCode,
			Integer page, Integer rows, String sidx, String sord);

	/**
	 * 工作台
	 * 根据网格编码分页查找下辖日志列表全部
	 * 
	 * @param orgInternalCode
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<PeopleLog> findSubLogListByOrgInternalCode4All(String orgInternalCode,
			Integer page, Integer rows, String sidx, String sord);

}
