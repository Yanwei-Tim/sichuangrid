package com.tianque.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.SmsMessageMark;

public interface SmsMessageMarkService {
	public SmsMessageMark getSimpleSmsMessageMarkById(Long id);

	public SmsMessageMark addSmsMessageMark(SmsMessageMark smsMessageMark);

	/**
	 * 根据处理类型（上报，交办，越级，验证等）获取短信模板
	 * 
	 * @param dealType
	 * @return
	 */
	public SmsMessageMark getSimpleSmsMessageMarkByDealType(int dealType);

	/**
	 * 根据操作类型（办理，领导批示，受理等）获取短信模板
	 * 
	 * @param dealType
	 * @return
	 */
	public SmsMessageMark getSimpleSmsMessageMarkByOperationtType(
			int operationtType);

	/**
	 * 更新model
	 * 
	 * @param id
	 */
	public SmsMessageMark updateSmsMessageMark(SmsMessageMark smsMessageMark);

	public boolean deleteSmsMessageMarkById(Long id);

	public PageInfo<SmsMessageMark> findSmsMessageMarks(Integer page,
			Integer rows, String sidx, String sord);
}
