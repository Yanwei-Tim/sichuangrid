package com.tianque.working.service;

import com.tianque.domain.workingRecord.DisputEsexamine;
import com.tianque.working.domain.DailyDirectory;

public interface DisputEsexamineService {
	/**
	 * 添加日常工作中矛盾纠纷排查信息
	 * 
	 * @param DisputEsexamine
	 *        上报信息对象
	 * @return DisputEsexamine 上报信息对象
	 * @throws Exception
	 */
	public DisputEsexamine addDisputEsexamine(DisputEsexamine disputEsexamine) throws Exception;

	public DisputEsexamine getdDisputEsexamineId(Long id);

	/**
	 * 将年度累计数据换为实时的数据
	 */
	public DisputEsexamine getRealTimeYearData(Long reportTime, DisputEsexamine disputEsexamine,
			DailyDirectory dailyDirectorie);

	/**
	 * 修改信息上报状态
	 * 
	 * @throws Exception
	 */
	public DisputEsexamine updateSubmitState(Long id, Long submitStateId, Long expiredEntering);

	public DisputEsexamine backWorkingRecord(Long id, Long submitStateId);

	/**
	 * 修改上报信息
	 * 
	 * @throws Exception
	 */
	public void deleteDisputEsexamine(Long id);

	/**
	 * 修改上报信息
	 * 
	 * @throws Exception
	 */
	public DisputEsexamine updateDisputEsexamine(DisputEsexamine disputEsexamine) throws Exception;

	/**
	 * 根据reprTime,dailyDirectoryId,orgid判断是否汇总，并返回相应的月，季，年报
	 */
	public DisputEsexamine reportSummarizing(Long reportTime, Long dailyDirectoryId, Long orgid);

	/**
	 * 根据reprTime,dailyDirectoryId,orgid子辖区是否全部上报
	 */
	public boolean sunReportJudge(Long reportTime, Long dailyDirectoryId, Long orgid);
}
