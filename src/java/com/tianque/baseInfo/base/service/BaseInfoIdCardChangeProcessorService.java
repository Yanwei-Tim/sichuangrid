package com.tianque.baseInfo.base.service;

import com.tianque.baseInfo.base.domain.Countrymen;

/**
 * @Description:修改身份证号码处理接口（户籍人口、未落户、流动人口三个实现）
 * @author zhangyouwei@hztian.com
 * @date: 2014-7-2 上午01:19:25
 */
public interface BaseInfoIdCardChangeProcessorService {

	/**
	 * 修改身份证号码
	 * 
	 * @param countrymen
	 * @param idCardNo
	 * @return
	 */
	public Countrymen updateBaseInfoIdCardNo(Countrymen countrymen,
			String idCardNo);

	/**
	 * 验证是否存在重复数据
	 * 
	 * @param actualPopulationType
	 * @param idCardNo
	 * @param orgId
	 * @return
	 */
	public Countrymen existBaseInfo(String actualPopulationType,
			String idCardNo, Long orgId);
}
