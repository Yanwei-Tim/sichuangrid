package com.tianque.gis.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tianque.core.vo.PageInfo;
import com.tianque.gis.domain.vo.PeripheryVo;
import com.tianque.gis.service.SearchPeripheryService;

@Service("searchPeripheryService")
public class SearchPeripheryServiceImpl implements SearchPeripheryService {

	@Override
	public PageInfo<PeripheryVo> findPeripherysByTypeAndRange(PeripheryVo peripheryVo,
			Integer page, Integer rows, String sidx, String sord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PeripheryVo> findPeripherysByTypeAndRange(PeripheryVo peripheryVo, String sidx,
			String sord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PeripheryVo> getPeripheryList(PeripheryVo peripheryVo) {
		// TODO Auto-generated method stub
		return null;
	}

}
