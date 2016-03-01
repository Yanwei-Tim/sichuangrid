package com.tianque.baseInfo.publicPlace.dao;

import java.util.List;

import com.tianque.baseInfo.publicPlace.domain.PublicPlace;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchPublicPlaceVo;

public interface SearchPublicPlaceDao {

	PageInfo findPublicPlacesByQueryCondition(
			SearchPublicPlaceVo searchPlaceVo, Integer page, Integer rows,
			String sidx, String sord);

	List<PublicPlace> searchPublicPlaceForExport(
			SearchPublicPlaceVo searchPublicPlaceVo, Integer page,
			Integer rows, String sidx, String sord);

	public Integer getCount(SearchPublicPlaceVo searchPublicPlaceVo);
}
