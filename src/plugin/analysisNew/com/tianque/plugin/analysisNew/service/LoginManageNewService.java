package com.tianque.plugin.analysisNew.service;

import java.util.List;

import com.tianque.plugin.analysisNew.domain.LoginManage;

public interface LoginManageNewService {
	/**
	 * 统计用户的登录情况-list 查询直属下辖（区域）
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public List<LoginManage> queryLoginManageForList(LoginManage loginManage);

	/**
	 * 统计用户的登录情况-list 查询各层级
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public List<LoginManage> queryLoginManageByOrgIdForList(
			LoginManage loginManage);

	/**
	 * 新增JOB
	 * 
	 * @param year
	 * @param month
	 */
	public void addLoginManageJob(int year, int month);

}
