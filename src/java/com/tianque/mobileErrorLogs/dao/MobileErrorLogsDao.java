package com.tianque.mobileErrorLogs.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.mobileErrorLogs.domain.MobileErrorLogs;

/**
 * @author weiminglong 2016年1月7日下午2:43:30
 */
public interface MobileErrorLogsDao {

	public MobileErrorLogs addMobileErrorLogs(MobileErrorLogs mobileErrorLogs);

	public PageInfo<MobileErrorLogs> findMobileErrorLogs(
			MobileErrorLogs mobileErrorLogs, Integer pageNum, Integer pageSize,
			String sidx, String sord);

	public PageInfo<MobileErrorLogs> advancedSearchMobileErrorLogs(
			MobileErrorLogs mobileErrorLogs, Integer page, Integer rows,
			String sidx, String sord);
}
