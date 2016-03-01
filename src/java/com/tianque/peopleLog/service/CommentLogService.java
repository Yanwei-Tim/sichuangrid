package com.tianque.peopleLog.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.peopleLog.domain.CommentLog;
import com.tianque.peopleLog.domain.PeopleLog;
import com.tianque.peopleLog.domain.Statistics;

public interface CommentLogService {

	/**
	 * 查找数据用于显示我的点评
	 * 
	 * @param userId
	 * @param pageNum
	 * @param pageSize
	 * @param sortField
	 * @param sord
	 * @return PageInfo<CommentLog>
	 */
	public PageInfo<CommentLog> findCommentLogForPageByUserId(Long userId, Integer pageNum,
			Integer pageSize, String sortField, String sord);

	/**
	 * 查找数据用于显示日志
	 * 
	 * @param id
	 * @return PeopleLog
	 */
	public PeopleLog findPeopleLogById(long id);

	/**
	 * 统计新增
	 * 
	 * @param orgId
	 * @return Statistics
	 */
	public Statistics findLogStatistics(Long userId);

	/**
	 * 工作台统计
	 * 
	 * @param orgCode
	 * @return
	 */
	public Statistics findLogStatisticsByOrgCode(String orgCode);
}
