package com.tianque.mobileErrorLogs.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.mobileErrorLogs.domain.MobileErrorLogs;

/**
 * @author weiminglong 2016年1月7日下午2:42:29
 */
public interface MobileErrorLogsService {
	/**
	 * 保存由手机客户端上传的数据到服务端
	 * 
	 * @param mobileErrorLogs
	 * @return
	 */
	public MobileErrorLogs addMobileErrorLogs(MobileErrorLogs mobileErrorLogs);

	/***
	 * 获取数据列表
	 * 
	 * @param mobileErrorLogs
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<MobileErrorLogs> findMobileErrorLogs(
			MobileErrorLogs mobileErrorLogs, Integer pageNum, Integer pageSize,
			String sidx, String sord);

	/**
	 * 高级搜索功能
	 * 
	 * @param mobileErrorLogs
	 * @param page
	 * @param rows
	 * @return
	 */
	public PageInfo<MobileErrorLogs> advancedSearchMobileErrorLogs(
			MobileErrorLogs mobileErrorLogs, Integer page, Integer rows,
			String sidx, String sord);
}
