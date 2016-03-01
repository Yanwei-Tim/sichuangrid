package com.tianque.platformMessage.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.platformMessage.domain.PlatformMessage;
import com.tianque.platformMessage.domain.SearchPlatformMessageVo;

public interface SearchPlatformMessageService {

	/**
	 * 发件箱查询
	 */
	public PageInfo<PlatformMessage> searchOutboxPlatformMessage(
			SearchPlatformMessageVo searchPlatformMessageVo, Integer pageNum, Integer pageSize,
			String sortField, String order);

	/**
	 * 收件箱查询
	 */
	public PageInfo<PlatformMessage> searchInboxPlatformMessage(
			SearchPlatformMessageVo searchPlatformMessageVo, Integer page, Integer rows,
			String sidx, String sord);
}
