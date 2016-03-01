package com.tianque.plugin.analysisNew.service;

import com.tianque.sysadmin.vo.PacketStatisticsVo;

/**
 * @Description:新的
 * @author zhangyouwei@hztianque.com
 * @date: 2015-5-13 上午10:53:18
 */
public interface BuilddataStatNewService {
	/**
	 * 定时统计
	 * 
	 * @param month
	 * @param year
	 */
	public void addBuilddataStat(int year, int month);

	/**
	 * 补全历史数据
	 * 
	 * @param packetStatisticsVo
	 */
	public void manualBuilddataCompletion(PacketStatisticsVo packetStatisticsVo);
}
