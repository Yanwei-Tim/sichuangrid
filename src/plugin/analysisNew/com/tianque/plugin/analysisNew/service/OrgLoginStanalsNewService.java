package com.tianque.plugin.analysisNew.service;

import java.util.List;

import com.tianque.plugin.analysisNew.domain.OrgLoginStanals;
import com.tianque.ticket.service.TicketService;

public interface OrgLoginStanalsNewService {

	/**
	 * 增加一条部门的月度登录统计信息
	 * 
	 * @param orgLoginStanals
	 * @return
	 */
	public void addOrgLoginStanals(OrgLoginStanals orgLoginStanals);

	/**
	 * JOB自动生成每个月的登录统计信息
	 * 
	 * @param year
	 * @param month
	 */
	public void addMonthOrgLoginStanalssJob(int year, int month);

	/**
	 * 根据部门、年度、月份 自动重新生成该部门下的统计信息
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public void reCreateOrgLoginStanals(int year, int month);

	/**
	 * 根据部门、查询日期 获得部门统计信息列表
	 * 
	 * @param orgId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<OrgLoginStanals> getOrgLoginStanalsForList(int year, int month,
			Long orgId, int internalId);

	void setTicketService(TicketService ticketService, String ticketId);

	/**
	 * 服务办事类别月报统计
	 */
	public void addMonthOrgLoginStanals();

	void deleteOrgLoginStanalsByOrgId(Long orgId);

}
