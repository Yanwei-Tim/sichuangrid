package com.tianque.baseInfo.companyPlace.service;

import java.util.List;

import com.tianque.plugin.transfer.vo.ErrorMessageVo;

public interface CompanyPlaceTransferService {
	/**
	 * 转移
	 * 
	 * @param type
	 * @param ids
	 * @param toOrgId
	 *            目标网格
	 * @return
	 */
	List<ErrorMessageVo> transfer(String type, List<Long> ids, Long toOrgId,
			String modulKey);
}
