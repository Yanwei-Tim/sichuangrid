package com.tianque.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.SmsSendBox;
import com.tianque.domain.vo.SearchSmsSendBoxVo;

public interface SearchSmsSendBoxDao {

	public PageInfo<SmsSendBox> searchSmsSendBox(
			SearchSmsSendBoxVo searchSmsSendBoxVo, int pageNum, int pageSize,
			String sortField, String order);

	public PageInfo<SmsSendBox> searchSmsSendBox(
			SearchSmsSendBoxVo searchSmsSendBoxVo, String date, int pageNum,
			int pageSize, String sortField, String order);

}
