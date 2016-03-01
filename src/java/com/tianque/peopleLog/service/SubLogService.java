package com.tianque.peopleLog.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.peopleLog.domain.CommentLog;
import com.tianque.peopleLog.domain.PeopleLog;

public interface SubLogService {
	/**
	 * 根据网格显示下辖日志内容
	 * 
	 * @param orgId
	 * @param pageNum
	 * @param pageSize
	 * @param sord
	 * @param isPeer
	 * @return
	 */
	public PageInfo<PeopleLog> findSubLogListByOrgInternalCode(Long orgId, Integer pageNum,
			Integer pageSize, String sortField, String sord, boolean isPeer);

	/**
	 * 根据日志id查找日志内容（用于点评）
	 * 
	 * @param logId
	 * @return
	 */
	public PeopleLog findLogByLogId(Long logId);

	/**
	 * 保存评论
	 * 
	 * @return
	 */
	public CommentLog addComments(CommentLog comment);

	/**
	 * 查找记录用于subLogLostMe的jqGrid显示
	 * 
	 * @param orgInternalCode
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @param isPeer
	 * @return
	 */
	public PageInfo<PeopleLog> findSubLogListByOrgInternalCode4Bench(String orgInternalCode,
			Integer page, Integer rows, String sidx, String sord, boolean isPeer);
}
