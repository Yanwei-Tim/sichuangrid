package com.tianque.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.SmsMessageMark;

public interface SmsMessageMarkDao {
	public SmsMessageMark getSimpleSmsMessageMarkById(Long id);

	public SmsMessageMark getSimpleSmsMessageMarkByDealType(int dealType);

	public SmsMessageMark getSimpleSmsMessageMarkByOperationtType(
			int operationtType);

	public SmsMessageMark addSmsMessageMark(SmsMessageMark smsMessageMark);

	/**
	 * 更新model
	 * 
	 * @param id
	 */
	public SmsMessageMark updateSmsMessageMark(SmsMessageMark smsMessageMark);

	public void deleteSmsMessageMarkById(Long id);

	public PageInfo<SmsMessageMark> findSmsMessageMarks(Integer page,
			Integer rows, String sidx, String sord);
}
