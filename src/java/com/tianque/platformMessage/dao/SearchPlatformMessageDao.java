package com.tianque.platformMessage.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.platformMessage.domain.PlatformMessage;
import com.tianque.platformMessage.domain.SearchPlatformMessageVo;

public interface SearchPlatformMessageDao {

	public PageInfo<PlatformMessage> searchOutboxPlatformMessage(
			SearchPlatformMessageVo searchPlatformMessageVo, Integer pageNum, Integer pageSize,
			String sortField, String order);

	public PageInfo<PlatformMessage> searchInboxPlatformMessage(
			SearchPlatformMessageVo searchPlatformMessageVo,Integer page, Integer rows,
			String sidx, String sord);
}
