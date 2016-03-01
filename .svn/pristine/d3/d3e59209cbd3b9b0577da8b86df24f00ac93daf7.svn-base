package com.tianque.baseInfo.youths.service;

import com.tianque.baseInfo.youths.domain.Youths;
import com.tianque.baseInfo.youths.vo.SearchYouthsVo;
import com.tianque.core.vo.PageInfo;

public interface YouthsService {

	public PageInfo<Youths> findYouthsForPage(Integer pageNum,
			Integer pageSize, String sortField, String order,
			SearchYouthsVo searchYouthsVo);

	public Integer getYouthsCount(SearchYouthsVo searchYouthsVo);
}
