package com.tianque.baseInfo.publicComplexPlaces.dao;

import java.util.List;

import com.tianque.baseInfo.publicComplexPlaces.domain.PublicComplexPlaces;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchPublicComplexPlacesVo;

public interface SearchPublicComplexPlacesDao {

	PageInfo findPublicComplexPlacessByQueryCondition(
			SearchPublicComplexPlacesVo searchPlaceVo, Integer page, Integer rows,
			String sidx, String sord);

	List<PublicComplexPlaces> searchPublicComplexPlacesForExport(
			SearchPublicComplexPlacesVo searchPublicComplexPlacesVo, Integer page,
			Integer rows, String sidx, String sord);

	public Integer getCount(SearchPublicComplexPlacesVo searchPublicComplexPlacesVo);
}
