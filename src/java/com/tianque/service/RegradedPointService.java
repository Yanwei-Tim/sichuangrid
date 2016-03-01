package com.tianque.service;

import java.util.Date;
import java.util.List;

import org.apache.struts2.json.JSONException;

import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.RegradedPoints;
import com.tianque.domain.vo.RegradedReason;

public interface RegradedPointService {

	RegradedPoints deductPoints(Organization targeOrg, String regradedType,
			RegradedReason regradedReason, Double points, Date applicationDate)
			throws JSONException;

	RegradedPoints bonusPoints(Organization targeOrg, String regradedType,
			RegradedReason regradedReason, Double points, Date applicationDate)
			throws JSONException;

	PageInfo<RegradedPoints> findRegradedPointsByOrgIdAndStatDate(Long orgId,
			String statDate, Integer pageNum, Integer pageSize,
			String sortField, String order);

	PageInfo<RegradedPoints> findRegradedPointsByOrgIdAndStatDate(Long orgId,
			PropertyDict reoprtDateType, Integer year, Integer month,
			Integer pageNum, Integer pageSize, String sortField, String order);

	RegradedPoints bonusPoints(Organization simpleOrgById,
			String regradedbyperson, RegradedReason regradedReason,
			Double double1, Date now, Long long1) throws JSONException;

	RegradedPoints deductPoints(Organization simpleOrgById,
			String regradedbyperson, RegradedReason regradedReason,
			Double double1, Date now, Long long1) throws JSONException;

	List<RegradedPoints> queryRegradedPointsForListByIds(List<Long> idList);

	void deleteRegradedPointByLogId(Long long1);

	/**
	 * 获取已办结事件的评分详情
	 * 
	 * @param isHistory
	 *            （该事件是否是历史事件）
	 * @param keyId
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	GridPage queryIssueGradeHistoryForPageResultByIssueId(boolean isHistory,
			Long keyId, Integer page, Integer rows, String sidx, String sord);

	String issueGradeIsVisabled(Long keyId);

	/***
	 * 事件考核评估，添加打分记录，手动打分 备注:以免影响其他方法调用，单独写
	 * 
	 * @param targeOrg
	 * @param regradedType
	 * @param regradedReason
	 * @param points
	 * @param applicationDate
	 * @return
	 */
	RegradedPoints ManualDeductPoints(Organization targeOrg,
			String regradedType, RegradedReason regradedReason, Double points,
			Date applicationDate) throws JSONException;

	RegradedPoints ManualBonusPoints(Organization targeOrg,
			String regradedType, RegradedReason regradedReason, Double points,
			Date applicationDate) throws JSONException;
}
