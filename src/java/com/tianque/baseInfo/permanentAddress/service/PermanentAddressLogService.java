package com.tianque.baseInfo.permanentAddress.service;

import java.util.List;

import com.tianque.baseInfo.permanentAddress.domain.PermanentAddressLog;
import com.tianque.core.vo.PageInfo;

public interface PermanentAddressLogService {

	public PermanentAddressLog addPermanentAddressLog(
			PermanentAddressLog permanentAddressLog);

	public boolean deletePermanentAddressLog(Long id);

	public PermanentAddressLog updatePermanentAddressLog(
			PermanentAddressLog permanentAddressLog);

	public PermanentAddressLog getPermanentAddressLogById(Long id);

	public List<PermanentAddressLog> findPermanentAddressLogByJobstatus(
			Integer jobState);

	public void updatePermanentAddressLogJobstatus(Long id, Integer jobState);

	/***
	 * 查询所有的户籍地修改job日志
	 * 
	 * @return
	 */
	public PageInfo<PermanentAddressLog> findAllPermanentAddressLog(
			Integer page, Integer rows, String sidx, String sord);

	/***
	 * 根据ID删除日志记录
	 */
	public void deleteLogByIds(String[] ids);

	/***
	 * 根据ID数组查询日志信息
	 */
	public List<PermanentAddressLog> findPermanentAddressLogByJobstatusAndIds(
			String[] ids);

}
