package com.tianque.peopleLog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.peopleLog.dao.CommentLogDao;
import com.tianque.peopleLog.domain.CommentLog;
import com.tianque.peopleLog.domain.PeopleLog;
import com.tianque.peopleLog.domain.Statistics;

@Service("commentLogService")
@Transactional
public class CommentLogServiceImpl implements CommentLogService {

	@Autowired
	private CommentLogDao commentLogDao;

	/**
	 * 查找数据用于显示我的点评列表
	 * 
	 * @param orgId
	 * @param pageNum
	 * @param pageSize
	 * @param sortField
	 * @param sord
	 * @return PageInfo<CommentLog>
	 */
	@Override
	public PageInfo<CommentLog> findCommentLogForPageByUserId(Long userId,
			Integer pageNum, Integer pageSize, String sortField, String sord) {
		try {
			return commentLogDao.getCommentLogByUserId(userId, pageNum,
					pageSize, sortField, sord);

		} catch (Exception e) {
			throw new ServiceValidationException("查找数据错误", e);
		}
	}

	/**
	 * 查找数据用于查看日志
	 * 
	 * @param id
	 * @return PeopleLog
	 */
	@Override
	public PeopleLog findPeopleLogById(long id) {
		return commentLogDao.getPeopleLogById(id);
	}

	/**
	 * 统计新增
	 * 
	 * @param orgId
	 * @return Statistics
	 */
	@Override
	public Statistics findLogStatistics(Long userId) {
		try {
			return commentLogDao.findLogStatistics(userId);
		} catch (Exception e) {
			throw new ServiceValidationException("统计新增错误", e);
		}
	}

	/**
	 * 工作台统计
	 * 
	 * @param orgCode
	 * @return
	 */
	@Override
	public Statistics findLogStatisticsByOrgCode(String orgCode) {
		try {
			return commentLogDao.findLogStatisticsByOrgCode(orgCode);
		} catch (Exception e) {
			throw new ServiceValidationException("工作台统计错误", e);

		}
	}

}
