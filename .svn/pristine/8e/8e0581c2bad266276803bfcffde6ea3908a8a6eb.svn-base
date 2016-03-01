package com.tianque.peopleLog.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.peopleLog.domain.CommentLog;
import com.tianque.peopleLog.domain.PeopleLog;
import com.tianque.peopleLog.domain.Statistics;

public interface CommentLogDao {
	/**
	 * 查找数据用于显示评论
	 * 
	 * @param userId
	 * @param pageNum
	 * @param pageSize
	 * @param sortField
	 * @param sord
	 * @return PageInfo<CommentLog>
	 */
	public PageInfo<CommentLog> getCommentLogByUserId(Long userId, Integer pageNum,
			Integer pageSize, String sortField, String sord);

	/**
	 * 查找数据用于查看日志
	 * 
	 * @param id
	 * @return PeopleLog
	 */
	public PeopleLog getPeopleLogById(long id);

	/**
	 * 统计新增
	 * 
	 * @param userId
	 * @return Statistics
	 */
	public Statistics findLogStatistics(Long userId);

	/**
	 * 工作台统计
	 * 
	 * @param orgCode
	 * @return Statistics
	 */
	public Statistics findLogStatisticsByOrgCode(String orgCode);
}
