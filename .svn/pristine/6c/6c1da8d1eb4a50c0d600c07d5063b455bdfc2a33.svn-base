package com.tianque.gis.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.gis.domain.vo.PeripheryVo;

public interface SearchPeripheryService {

	public PageInfo<PeripheryVo> findPeripherysByTypeAndRange(PeripheryVo peripheryVo,
			Integer page, Integer rows, String sidx, String sord);

	public List<PeripheryVo> findPeripherysByTypeAndRange(PeripheryVo peripheryVo, String sidx,
			String sord);

	public List<PeripheryVo> getPeripheryList(PeripheryVo peripheryVo);
}