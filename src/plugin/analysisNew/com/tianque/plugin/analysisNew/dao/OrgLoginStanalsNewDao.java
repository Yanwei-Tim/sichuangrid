package com.tianque.plugin.analysisNew.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tianque.domain.OrgLoginLog;
import com.tianque.domain.Organization;
import com.tianque.plugin.analysisNew.domain.OrgLoginStanals;

public interface OrgLoginStanalsNewDao {
	/**
	 * 添加日志
	 * 
	 * @param orgLoginStanals
	 */
	public void addOrgLoginStanals(OrgLoginStanals orgLoginStanals);

	/**
	 * 清理数据
	 * 
	 * @param year
	 * @param month
	 * @param orgCode
	 */
	public void clearPeriodOrgLoginStanalsByDate(int year, int month);

	/**
	 * 获得列表
	 * 
	 * @param year
	 * @param month
	 * @param orgId
	 * @param orgTypeId
	 * @return
	 */
	public List<OrgLoginStanals> getOrgLoginStanalsForList(int year, int month,
			List<Long> organizationId);

	/**
	 * 根据每个月的起始时间和结束时间 获得这个月内所有的登录日志信息
	 * 
	 * @param startworkdate
	 * @param endworkdate
	 * @return
	 */
	public List<OrgLoginLog> getOrgLoginLogListByStartAndEndWorkDay(
			String orgInternalCode, int orgTypeId, Date startworkdate,
			Date endworkdate);

	/**
	 * 查找所有组织结构信息 只查处orgId和orgInternalCode
	 * 
	 * @param orgInternalCode
	 * @return
	 */
	public List<Organization> findOrganizationsByOrgInternalCode(
			String orgInternalCode, int orgTypeId);

	OrgLoginStanals getRootOrgLoginStanalsForList(int year, int month,
			Long orgId, int orgTypeId);

	public void deleteOrgLoginStanalsByOrgId(Long orgId);

	public List<OrgLoginStanals> findUsersByCreateDate(Integer date);

	public List<Map<String, Object>> getWorkDay(List<String> dateList,
			Map dataMap, String firstTableName, String secondeTableName);
}
