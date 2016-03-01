package com.tianque.peopleLog.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.peopleLog.domain.CommentLog;
import com.tianque.peopleLog.domain.PeopleLog;
import com.tianque.peopleLog.domain.Statistics;
import com.tianque.sysadmin.service.PropertyDictService;

@Repository("commentLogDao")
public class CommentLogDaoImpl extends AbstractBaseDao implements CommentLogDao {
	@Autowired
	private PropertyDictService propertyDictService;

	/**
	 * 查找数据用于显示评论
	 * 
	 * @param orgId
	 * @param pageNum
	 * @param pageSize
	 * @param sortField
	 * @param sord
	 * @return PageInfo<CommentLog>
	 */
	@Override
	public PageInfo<CommentLog> getCommentLogByUserId(Long userId,
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
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("sortField", sortField);
		map.put("sord", sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"commentLog.countCommentLog", map);
		List<Map<String, Object>> list = getSqlMapClientTemplate()
				.queryForList("commentLog.getCommentLog", map,
						(pageNum - 1) * pageSize, pageSize);
		List commentLogs = new ArrayList<CommentLog>();
		for (int i = 0; i < list.size(); i++) {
			CommentLog commentLog = new CommentLog();
			Map<String, Object> commentMap = list.get(i);
			commentLog.setCommentTime((Date) commentMap.get("COMMENTTIME"));
			commentLog.setComments((String) commentMap.get("COMMENTS"));
			commentLog.setId(((BigDecimal) commentMap.get("ID")).longValue());
			commentLog.setLogId(((BigDecimal) commentMap.get("LOGID"))
					.longValue());
			commentLog.setUserId(((BigDecimal) commentMap.get("USERID"))
					.longValue());
			commentLog.getPeopleLog();
			PeopleLog peopleLog = new PeopleLog();
			peopleLog.setTitle((String) commentMap.get("PEOPLELOGTITLE"));
			String orginternalcode = (String) commentMap.get("ORGINTERNALCODE");
			Organization org = (Organization) getSqlMapClientTemplate()
					.queryForObject(
							"organization.getOrganizationByOrgInternalCode",
							orginternalcode);
			peopleLog.setOrganization(org);
			peopleLog.setLogType(propertyDictService
					.getPropertyDictById(((BigDecimal) commentMap
							.get("PEOPLELOGTYPE")).longValue()));
			commentLog.setPeopleLog(peopleLog);
			commentLogs.add(commentLog);
		}

		return createPageInfo(pageNum, pageSize, countNum, commentLogs);
	}

	private PageInfo<CommentLog> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List list) {
		PageInfo<CommentLog> pageInfo = new PageInfo<CommentLog>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	/**
	 * 查找数据用于显示日志
	 * 
	 * @param id
	 * @return PeopleLog
	 */
	public PeopleLog getPeopleLogById(long id) {
		PeopleLog peopleLog = (PeopleLog) getSqlMapClientTemplate()
				.queryForObject("peopleLog.getPeopleLogById", id);
		List<CommentLog> comments = getSqlMapClientTemplate().queryForList(
				"commentLog.getCommentLogByLogId", id);
		peopleLog.setComments(comments);
		return peopleLog;
	}

	/**
	 * 统计新增
	 * 
	 * @param userId
	 * @return Statistics
	 */
	@Override
	public Statistics findLogStatistics(Long userId) {
		Statistics statistics = new Statistics();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("today", CalendarUtil.format(new Date()));
		String dateFormat = "yyyy-MM";
		map.put("yearMonth", CalendarUtil.format(dateFormat, new Date()));
		map.put("weekMonday", CalendarUtil.getWeekMonday());
		map.put("nextWeekMonday", CalendarUtil.getNextWeekMonday());
		long todayNum = (Long) getSqlMapClientTemplate().queryForObject(
				"peopleLog.getTodayNum", map);
		long weekNum = (Long) getSqlMapClientTemplate().queryForObject(
				"peopleLog.getWeekNum", map);
		long mouthNum = (Long) getSqlMapClientTemplate().queryForObject(
				"peopleLog.getMouthNum", map);
		long mouthCommentNum = (Long) getSqlMapClientTemplate().queryForObject(
				"peopleLog.getMouthCommentNum", map);
		long countNum = (Long) getSqlMapClientTemplate().queryForObject(
				"peopleLog.getCountNum", userId);
		long commentNum = (Long) getSqlMapClientTemplate().queryForObject(
				"peopleLog.getCommentNum", userId);
		statistics.setTodayNum(todayNum);
		statistics.setWeekNum(weekNum);
		statistics.setMouthNum(mouthNum);
		statistics.setMouthCommentNum(mouthCommentNum);
		statistics.setCountNum(countNum);
		statistics.setCommentNum(commentNum);
		return statistics;
	}

	@Override
	public Statistics findLogStatisticsByOrgCode(String orgCode) {
		Statistics statistics = new Statistics();
		long countSubMeNum = (Long) getSqlMapClientTemplate().queryForObject(
				"peopleLog.getCountSubMeNum", orgCode);
		long commentSubMeNum = (Long) getSqlMapClientTemplate().queryForObject(
				"peopleLog.getCommentSubMeNum", orgCode);
		long countSubAllNum = (Long) getSqlMapClientTemplate().queryForObject(
				"peopleLog.getCountSubAllNum", orgCode);
		long commentSubAllNum = (Long) getSqlMapClientTemplate()
				.queryForObject("peopleLog.getCommentSubAllNum", orgCode);
		statistics.setCountSubMeNum(countSubMeNum);
		statistics.setCommentSubMeNum(commentSubMeNum);
		statistics.setCountSubAllNum(countSubAllNum);
		statistics.setCommentSubAllNum(commentSubAllNum);
		return statistics;
	}

}
