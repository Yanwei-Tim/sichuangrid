package com.tianque.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.RegradedPointsDao;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.RegradedPoints;
import com.tianque.domain.property.ReportDateType;
import com.tianque.domain.vo.RegradedReason;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.service.IssueService;
import com.tianque.service.RegradedPointService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Service("regradedPointService")
public class RegradedPointServiceImpl implements RegradedPointService {
	@Autowired
	private RegradedPointsDao regradedPointsDao;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private IssueService issueService;

	@Override
	public RegradedPoints bonusPoints(Organization targeOrg,
			String regradedType, RegradedReason regradedReason, Double points,
			Date applicationDate) throws JSONException {
		return bonusPoints(targeOrg, regradedType, regradedReason, points,
				applicationDate, null);
	}

	@Override
	public RegradedPoints deductPoints(Organization targeOrg,
			String regradedType, RegradedReason regradedReason, Double points,
			Date applicationDate) throws JSONException {
		return deductPoints(targeOrg, regradedType, regradedReason, points,
				applicationDate, null);
	}

	@Override
	public PageInfo<RegradedPoints> findRegradedPointsByOrgIdAndStatDate(
			Long orgId, String statDate, Integer pageNum, Integer pageSize,
			String sortField, String order) {
		return regradedPointsDao.findRegradedPointsByOrgIdAndStatDate(orgId,
				statDate, pageNum, pageSize, sortField, order);
	}

	@Override
	public PageInfo<RegradedPoints> findRegradedPointsByOrgIdAndStatDate(
			Long orgId, PropertyDict reoprtDateType, Integer year,
			Integer month, Integer pageNum, Integer pageSize, String sortField,
			String order) {
		PropertyDict dict = propertyDictService
				.getPropertyDictById(reoprtDateType.getId());
		Date startDate = null;
		Date endDate = null;
		Calendar defaultDate = ReportDateType.getDefaultCalendar();
		defaultDate.set(Calendar.YEAR, year);
		defaultDate.set(Calendar.MONTH, month - 1);
		startDate = defaultDate.getTime();
		if (ReportDateType.GROUPBYYEAR_KEY.equals(dict.getDisplayName())) {
			defaultDate.add(Calendar.MARCH, 12);
			endDate = defaultDate.getTime();
		} else if (ReportDateType.GROUPBYQUARTER_KEY.equals(dict
				.getDisplayName())) {
			defaultDate.add(Calendar.MARCH, 3);
			endDate = defaultDate.getTime();
		} else {
			defaultDate.add(Calendar.MARCH, 1);
			endDate = defaultDate.getTime();
		}
		return regradedPointsDao.findRegradedPointsByOrgIdAndStatDate(orgId,
				startDate, endDate, pageNum, pageSize, sortField, order);
	}

	private RegradedPoints createRegradedPoints(Organization targeOrg,
			String regradedType, RegradedReason regradedReason, Double points,
			Integer pointType, Date applicationDate, Long logId)
			throws JSONException {
		if (regradedReason == null || regradedReason.getIssueNumber() == null) {
			return null;
		}
		IssueNew issue = issueService.getIssueBySerialNumber(regradedReason
				.getIssueNumber());
		if (issue == null || issue.getCreateOrg() == null
				|| issue.getCreateOrg().getId() == null
				|| issue.getCreateUser() == null) {
			return null;
		}
		/*
		 * User user = userDao.getFullUserByUserName(issue.getCreateUser()); if
		 * (user == null || user.getName() == null) { return null; }
		 */
		RegradedPoints regradedPoints = new RegradedPoints();
		regradedPoints.setContent(regradedReason.getRegradedResonDescription());
		regradedPoints.setPoints(points);
		regradedPoints.setPointType(pointType);
		regradedPoints.setRegradedDate(CalendarUtil.now("yyyy-MM-dd HH:mm:ss"));
		// regradedPoints.setRegradedOrg(issue.getCreateOrg());
		regradedPoints.setRegradedOrg(ThreadVariable.getSession()
				.getOrganization());
		regradedPoints.setRegradedOrgInternalCode(ThreadVariable.getSession()
				.getOrgInternalCode());
		regradedPoints.setRegradedType(regradedType);
		// regradedPoints.setRegradedUser(issue.getCreateUser());
		// regradedPoints.setRegradedUserRealName(user.getName());
		// 打分者应该是当前登录的用户
		regradedPoints.setRegradedUser(ThreadVariable.getSession()
				.getUserName());
		regradedPoints.setRegradedUserRealName(ThreadVariable.getSession()
				.getUserRealName());
		regradedPoints.setRegradeReason(JSONUtil.serialize(regradedReason));
		regradedPoints.setTargeOrg(targeOrg);
		regradedPoints.setTargeOrgInternalCode(targeOrg.getOrgInternalCode());
		regradedPoints.setKeyString(regradedReason.getKeyString());
		regradedPoints.setApplicationDate(applicationDate);
		regradedPoints.setLogId(logId);
		return regradedPoints;
	}

	@Override
	public RegradedPoints bonusPoints(Organization targeOrg,
			String regradedType, RegradedReason regradedReason, Double points,
			Date applicationDate, Long logId) throws JSONException {
		if (regradedReason.getRegradedResonDescription() == null)
			throw new BusinessValidationException("加分内容描述不能为空!");
		if (applicationDate == null)
			throw new BusinessValidationException("打分应用时间不能为空!");
		if (points == null)
			throw new BusinessValidationException("打分分值不能为空!");
		return regradedPointsDao.addRegradedPoints(createRegradedPoints(
				targeOrg, regradedType, regradedReason, points, 1,
				applicationDate, logId));
	}

	@Override
	public RegradedPoints deductPoints(Organization targeOrg,
			String regradedType, RegradedReason regradedReason, Double points,
			Date applicationDate, Long logId) throws JSONException {
		if (regradedReason.getRegradedResonDescription() == null)
			throw new BusinessValidationException("扣分内容描述不能为空!");
		if (applicationDate == null)
			throw new BusinessValidationException("打分应用时间不能为空!");
		if (points == null)
			throw new BusinessValidationException("打分分值不能为空!");
		return regradedPointsDao.addRegradedPoints(createRegradedPoints(
				targeOrg, regradedType, regradedReason, points, -1,
				applicationDate, logId));
	}

	@Override
	public List<RegradedPoints> queryRegradedPointsForListByIds(
			List<Long> idList) {
		if (idList == null) {
			throw new BusinessValidationException("参数错误");
		}
		return regradedPointsDao.queryRegradedPointsForListByIds(idList);
	}

	@Override
	public void deleteRegradedPointByLogId(Long logId) {
		regradedPointsDao.deleteRegradedPointByLogId(logId);
	}

	@Override
	public GridPage queryIssueGradeHistoryForPageResultByIssueId(
			boolean isHistory, Long keyId, Integer page, Integer rows,
			String sidx, String sord) {
		GridPage gridPage = new GridPage();
		if (isHistory) {
			gridPage = regradedPointsDao
					.queryIssueGradeHistoryForPageResultByHistoryIssueId(keyId,
							page, rows, sidx, sord);
		} else {
			gridPage = regradedPointsDao
					.queryIssueGradeHistoryForPageResultByIssueId(keyId, page,
							rows, sidx, sord);
		}
		for (Object obj : gridPage.getRows()) {
			RegradedPoints rp = (RegradedPoints) obj;
			if (rp.getRegradedOrg() != null
					&& rp.getRegradedOrg().getId() != null) {
				rp.setRegradedOrg(organizationDubboService.getSimpleOrgById(rp
						.getRegradedOrg().getId()));
			}
			if (rp.getTargeOrg() != null && rp.getTargeOrg().getId() != null) {
				rp.setTargeOrg(organizationDubboService.getSimpleOrgById(rp
						.getTargeOrg().getId()));
			}
		}
		return gridPage;
	}

	@Override
	public String issueGradeIsVisabled(Long keyId) {
		if (keyId == null) {
			throw new BusinessValidationException("参数错误");
		}
		return regradedPointsDao.issueGradeIsVisabled(keyId);
	}

	@Override
	public RegradedPoints ManualDeductPoints(Organization targeOrg,
			String regradedType, RegradedReason regradedReason, Double points,
			Date applicationDate) throws JSONException {
		if (regradedReason.getRegradedResonDescription() == null) {
			throw new BusinessValidationException("扣分内容描述不能为空!");
		}
		if (applicationDate == null) {
			throw new BusinessValidationException("打分应用时间不能为空!");
		}
		if (points == null) {
			throw new BusinessValidationException("打分分值不能为空!");
		}
		RegradedPoints regradedPoints = createRegradedPointsManual(targeOrg,
				regradedType, regradedReason, points, -1, applicationDate, null);
		return regradedPointsDao.addRegradedPoints(regradedPoints);

	}

	@Override
	public RegradedPoints ManualBonusPoints(Organization targeOrg,
			String regradedType, RegradedReason regradedReason, Double points,
			Date applicationDate) throws JSONException {
		if (regradedReason.getRegradedResonDescription() == null) {
			throw new BusinessValidationException("加分内容描述不能为空!");
		}
		if (applicationDate == null) {
			throw new BusinessValidationException("打分应用时间不能为空!");
		}
		if (points == null) {
			throw new BusinessValidationException("打分分值不能为空!");
		}
		RegradedPoints regradedPoints = createRegradedPointsManual(targeOrg,
				regradedType, regradedReason, points, 1, applicationDate, null);
		return regradedPointsDao.addRegradedPoints(regradedPoints);
	}

	private RegradedPoints createRegradedPointsManual(Organization targeOrg,
			String regradedType, RegradedReason regradedReason, Double points,
			Integer pointType, Date applicationDate, Long logId)
			throws JSONException {
		RegradedPoints regradedPoints = new RegradedPoints();
		if (regradedReason == null) {
			return null;
		}
		regradedPoints.setContent(regradedReason.getRegradedResonDescription());
		regradedPoints.setPoints(points);
		regradedPoints.setPointType(pointType);
		regradedPoints.setRegradedDate(CalendarUtil.now("yyyy-MM-dd HH:mm:ss"));
		regradedPoints.setRegradedOrg(ThreadVariable.getSession()
				.getOrganization());
		regradedPoints.setRegradedOrgInternalCode(ThreadVariable.getSession()
				.getOrgInternalCode());
		regradedPoints.setRegradedType(regradedType);
		regradedPoints.setRegradedUser(ThreadVariable.getSession()
				.getUserName());
		regradedPoints.setRegradedUserRealName(ThreadVariable.getSession()
				.getUserRealName());
		regradedPoints.setRegradeReason(JSONUtil.serialize(regradedReason));
		regradedPoints.setTargeOrg(targeOrg);
		regradedPoints.setTargeOrgInternalCode(targeOrg.getOrgInternalCode());
		regradedPoints.setKeyString(regradedReason.getKeyString());
		regradedPoints.setApplicationDate(applicationDate);
		regradedPoints.setLogId(logId);
		return regradedPoints;
	}

}
