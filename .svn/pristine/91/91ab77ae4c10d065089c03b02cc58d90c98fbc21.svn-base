package com.tianque.fourTeams.fourTeamsIssue.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.property.BasicOrgType;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.vo.SearchIssueVoNew;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.fourTeams.fourTeamsIssue.controller.FourTeamsIssueOvertimeHelper;
import com.tianque.fourTeams.fourTeamsIssue.dao.SearchFourTeamsIssueByLevelDao;
import com.tianque.fourTeams.fourTeamsIssue.service.SearchFourTeamsIssueByLevelService;
import com.tianque.fourTeams.fourTeamsIssue.vo.FourTeamsIssueViewObject;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Transactional
@Repository("SearchFourTeamsIssueByLevelService")
public class SearchFourTeamsIssueByLevelServiceImpl implements
		SearchFourTeamsIssueByLevelService {
	@Autowired
	protected OrganizationDubboService organizationDubboService;
	@Autowired
	protected SearchFourTeamsIssueByLevelDao searchIssueByLevelDao;
	@Autowired
	private FourTeamsIssueOvertimeHelper issueOvertimeHelper;

	@Override
	public PageInfo<FourTeamsIssueViewObject> findJurisdictionsNeedDo(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows,
			String sidx, String sord) {
		try {
			if (searchIssueVo == null || searchIssueVo.getTargeOrgId() == null) {
				return createEmptyIssueVoPageInfo(rows, 0);
			}
			Organization org = setOrgCode(searchIssueVo);
			searchIssueVo.setSeachValue(setseachValue(
					searchIssueVo.getSeachValue(), org));
			setOrderField(searchIssueVo, sidx, sord);
			PageInfo<FourTeamsIssueViewObject> pageInfo = searchIssueByLevelDao
					.findJurisdictionsNeedDo(searchIssueVo, page, rows);
			for (FourTeamsIssueViewObject issueViewObject : pageInfo
					.getResult()) {
				issueOvertimeHelper.fillEaringWarnField(issueViewObject);
			}
			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException("待办事件查询出错", e);
		}
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findJurisdictionAuditDelay(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows,
			String sidx, String sord) {
		if (searchIssueVo == null || searchIssueVo.getTargeOrgId() == null) {
			return createEmptyIssueVoPageInfo(rows, 0);
		}
		setOrderField(searchIssueVo, sidx, sord);
		searchIssueVo.setTargeOrgId(ThreadVariable.getOrganization().getId());
		PageInfo<FourTeamsIssueViewObject> pageInfo = searchIssueByLevelDao
				.findJurisdictionAuditDelay(searchIssueVo, page, rows);
		for (FourTeamsIssueViewObject issueViewObject : pageInfo.getResult()) {
			issueOvertimeHelper.fillEaringWarnField(issueViewObject);
		}
		return pageInfo;
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findJurisdictionsSkipIssue(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows,
			String sidx, String sord) {
		if (searchIssueVo == null || searchIssueVo.getTargeOrgId() == null) {
			return createEmptyIssueVoPageInfo(rows, 0);
		}
		Organization org = setOrgCode(searchIssueVo);
		searchIssueVo.setSeachValue(setseachValue(
				searchIssueVo.getSeachValue(), org));
		setOrderField(searchIssueVo, sidx, sord);
		PageInfo<FourTeamsIssueViewObject> pageInfo = searchIssueByLevelDao
				.findJurisdictionsSkipIssue(searchIssueVo, page, rows);
		for (FourTeamsIssueViewObject issueViewObject : pageInfo.getResult()) {
			issueOvertimeHelper.fillEaringWarnField(issueViewObject);
		}
		return pageInfo;
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findJurisdictionsDone(
			String statusType, SearchIssueVoNew searchIssueVo, Integer page,
			Integer rows, String sidx, String sord) {
		try {
			if (searchIssueVo == null || searchIssueVo.getTargeOrgId() == null) {
				return createEmptyIssueVoPageInfo(rows, 0);
			}
			Organization org = setOrgCode(searchIssueVo);
			searchIssueVo.setSeachValue(setseachValue(
					searchIssueVo.getSeachValue(), org));
			setOrderField(searchIssueVo, sidx, sord);
			PageInfo<FourTeamsIssueViewObject> pageInfo = searchIssueByLevelDao
					.findJurisdictionsDone(statusType, searchIssueVo, page,
							rows);
			for (FourTeamsIssueViewObject issueViewObject : pageInfo
					.getResult()) {
				issueOvertimeHelper.fillEaringWarnField(issueViewObject);
			}
			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException("已办事件查询出错", e);
		}
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findJurisdictionsCompleted(
			String statusType, SearchIssueVoNew searchIssueVo, Integer page,
			Integer rows, String sidx, String sord) {
		try {
			if (searchIssueVo == null || searchIssueVo.getTargeOrgId() == null) {
				return createEmptyIssueVoPageInfo(rows, 0);
			}
			Organization org = setOrgCode(searchIssueVo);
			searchIssueVo.setSeachValue(setseachValue(
					searchIssueVo.getSeachValue(), org));
			setOrderField(searchIssueVo, sidx, sord);
			PageInfo<FourTeamsIssueViewObject> pageInfo = searchIssueByLevelDao
					.findJurisdictionsCompleted(statusType, searchIssueVo,
							page, rows);
			for (FourTeamsIssueViewObject issueViewObject : pageInfo
					.getResult()) {
				issueOvertimeHelper.fillEaringWarnField(issueViewObject);
			}
			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException("已办结事件查询出错", e);
		}
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findJurisdictionsSubmit(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows,
			String sidx, String sord) {
		if (searchIssueVo == null || searchIssueVo.getTargeOrgId() == null) {
			return createEmptyIssueVoPageInfo(rows, 0);
		}
		Organization org = setOrgCode(searchIssueVo);
		searchIssueVo.setSeachValue(setseachValue(
				searchIssueVo.getSeachValue(), org));
		setOrderField(searchIssueVo, sidx, sord);
		PageInfo<FourTeamsIssueViewObject> pageInfo = searchIssueByLevelDao
				.findJurisdictionsSubmit(searchIssueVo, page, rows);
		for (FourTeamsIssueViewObject issueViewObject : pageInfo.getResult()) {
			issueOvertimeHelper.fillEaringWarnField(issueViewObject);
		}
		return pageInfo;
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findJurisdictionsAssgin(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows,
			String sidx, String sord) {
		if (searchIssueVo == null || searchIssueVo.getTargeOrgId() == null) {
			return createEmptyIssueVoPageInfo(rows, 0);
		}
		Organization org = setOrgCode(searchIssueVo);
		searchIssueVo.setSeachValue(setseachValue(
				searchIssueVo.getSeachValue(), org));
		setOrderField(searchIssueVo, sidx, sord);
		PageInfo<FourTeamsIssueViewObject> pageInfo = searchIssueByLevelDao
				.findJurisdictionsAssgin(searchIssueVo, page, rows);
		for (FourTeamsIssueViewObject issueViewObject : pageInfo.getResult()) {
			issueOvertimeHelper.fillEaringWarnField(issueViewObject);
		}
		return pageInfo;
	}

	private PageInfo<FourTeamsIssueViewObject> createEmptyIssueVoPageInfo(
			int pageSize, int pageIndex) {
		PageInfo<FourTeamsIssueViewObject> result = new PageInfo<FourTeamsIssueViewObject>();
		result.setTotalRowSize(0);
		result.setCurrentPage(pageIndex);
		result.setPerPageSize(pageSize);
		return result;
	}

	private void setOrderField(SearchIssueVoNew searchIssueVo, String sidx,
			String sord) {
		searchIssueVo.setSortField(sidx);
		searchIssueVo.setOrder(sord);
	}

	private Organization setOrgCode(SearchIssueVoNew searchIssueVo) {
		Organization org = organizationDubboService.getSimpleOrgById(searchIssueVo
				.getTargeOrgId());
		if (org != null && org.getOrgInternalCode() != null) {
			org = setQueryOrg(org);
			searchIssueVo.setTargeOrgInternalCode(org.getOrgInternalCode());
			searchIssueVo.setOrgId(org.getId());
			searchIssueVo.setOrgCode(org.getOrgInternalCode());
			searchIssueVo.setOrgLevel(org.getOrgLevel().getId());
		}
		return org;

	}

	// 如果当前登录的是职能部门，那么只显示本层级的
	private String setseachValue(String seachValue, Organization userOrg) {
		if (OrgIsFunctional(userOrg)) {
			// 单独为职能部门设置查询条件
			return BasicOrgType.ORGSCOPE_FUNCTIONAL;
		}
		return seachValue;
	}

	// 判断当前登录用户是否是职能部门
	private boolean OrgIsFunctional(Organization userOrg) {
		boolean flg = false;
		if (userOrg.getOrgType().getInternalId() == OrganizationType.FUNCTIONAL_ORG) {
			flg = true;
		}
		return flg;
	}

	// 根据登陆用户的组织机构设定内置编码
	// 如果是职能部门，需要判断
	private Organization setQueryOrg(Organization selectOrg) {
		Organization userOrg = ThreadVariable.getUser().getOrganization();
		if (OrgIsFunctional(userOrg)) {
			// 登陆的用户是职能部门
			Long userParentOrgId = userOrg.getParentOrg().getId();
			if (selectOrg.getId().equals(userParentOrgId)) {
				// 如果选择的组织机构是当前职能部门的父类节点，则采用职能部门的节点,并且只有自己本级
				return userOrg;
			} else {
				return selectOrg;
			}
		} else {
			return selectOrg;
		}
	}
}
