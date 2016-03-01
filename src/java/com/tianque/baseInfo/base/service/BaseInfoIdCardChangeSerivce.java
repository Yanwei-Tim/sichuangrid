package com.tianque.baseInfo.base.service;

import com.tianque.baseInfo.base.domain.Countrymen;

/**
 * 
 * @Description:户籍、流动、未落户修改身份证号码service接口
 * @author zhangyouwei@hztian.com
 * @date: 2014-7-1 上午12:25:40
 */
public interface BaseInfoIdCardChangeSerivce {

	/**
	 * 根据身份证号码判断baseinfo是否存在
	 * 
	 * @param idCardNo
	 * @param orgId
	 * @param actualPopulationType
	 * @return
	 */
	public Countrymen existBaseInfo(String actualPopulationType,
			String idCardNo, Long orgId);

	/**
	 * 根据身份证号修改基础表信息（身份证号、地址、出生日期等）
	 * 
	 * @param countrymen
	 * @param idCardNo
	 * @return
	 */
	public Countrymen updateBaseInfoIdCardNo(Countrymen countrymen,
			String idCardNo);

}
