package com.tianque.issue.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.utils.CustomDateUtil;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.issue.dao.IssueExtractionDao;
import com.tianque.issue.service.IssueExtractionService;
import com.tianque.issue.uitl.ListUtil;

/**
 * @ClassName: IssueExtractionServiceImpl
 * @Description: 事件分离服务
 */
@Transactional
@Service("issueExtractionService")
public class IssueExtractionServiceImpl implements IssueExtractionService {
	public final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private IssueExtractionDao issueExtractionDao;

	@Override
	public void exectueExtractionIssue() {
		// 查询符合要求的数据
		List<Long> ids = queryCompletedHistoryIssuesIds();
		logger.error("总共迁移数据量:" + ids.size());
		// 每500条做一次迁移操作。
		int count = 0;
		List<List<Long>> idList = ListUtil.splitList(ids, 500);
		for (List<Long> list : idList) {
			handleData(list);
			count += list.size();
			logger.error("处理的数据:" + count);
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void handleData(List list) {
		migrationDatas(list);
		deleteDatasByIdList(list);
	}

	@Override
	public List<Long> queryCompletedHistoryIssuesIds() {
		// 获取三十天前的日期
		Date date = CustomDateUtil
				.dateBeforeNowDateByDays(CustomDateUtil.DAYS_CHANGE);
		return issueExtractionDao.queryCompletedHistoryIssuesIds(date);
	}

	@Override
	public void migrationDatas(List<Long> issueIds) {
		try {
			// 迁移事件主表
			issueExtractionDao.migrationIssuesDatas(issueIds);
			// 迁移事件步骤表
			issueExtractionDao.migrationIssueStepsDatas(issueIds);
			// 迁移事件日志表
			issueExtractionDao.migrationIssueLogsDatas(issueIds);
			// 迁移事件步骤组表
			issueExtractionDao.migrationIssueStepsGroupDatas(issueIds);
			// 迁移办结表
			// issueExtractionDao.migrationCompletedissueDatas(issueIds);
			// 迁移打标
			issueExtractionDao.updateMarkCompletedissueDatas(issueIds);
		} catch (Exception e) {
			logger.error("迁移事件时出错:", e);
			throw new BusinessValidationException("迁移事件时出错。");
		}
	}

	@Override
	public void deleteDatasByIdList(List<Long> issueIds) {
		try {
			// 删除事件主表
			issueExtractionDao.deleteIssuesDatasByIdList(issueIds);
			// 删除事件步骤
			issueExtractionDao.deleteIssueStepsDatasByIdList(issueIds);
			// 删除事件日志
			issueExtractionDao.deleteIssueLogsDatasByIdList(issueIds);
			// 删除事件步骤组
			issueExtractionDao.deleteIssueStepsGroupDatasByIdList(issueIds);
			// 删除已办结
			// issueExtractionDao.deleteCompletedissueDatasByIdList(issueIds);
		} catch (Exception e) {
			logger.error("迁移历史事件后，根据事件id删除事件出错:", e);
			throw new BusinessValidationException("迁移历史事件后，根据事件id删除事件出错。");
		}
	}
}
