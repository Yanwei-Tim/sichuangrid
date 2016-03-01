package com.tianque.publicSecurity.service;

import java.util.List;

import com.tianque.plugin.transfer.vo.ErrorMessageVo;

public interface PublicSecurityTransferService {

	/**
	 * 转移
	 * 
	 * @param type
	 * @param ids
	 * @param toOrgId
	 *            目标网格
	 * @return
	 */
	List<ErrorMessageVo> transfer(String type, List<Long> ids, Long toOrgId);

}
