package com.tianque.plugin.transfer.service;

import java.util.List;

import com.tianque.plugin.transfer.vo.ErrorMessageVo;

public interface TransferService {

	public List<ErrorMessageVo> validate(String type, List<Long> ids,
			Long toOrgId);

	public List<ErrorMessageVo> validate(String type, List<Long> ids,
			Long toOrgId, Long fromOrgId);

}
