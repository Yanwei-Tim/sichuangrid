package com.tianque.baseInfo.internetBar.dao;

import java.util.List;

import com.tianque.baseInfo.publicPlace.domain.PublicPlace;
import com.tianque.domain.vo.SearchPublicPlaceVo;

public interface SearchInternetBarDao {

	// PageInfo findInternetBarByQueryCondition(SearchInternetBarVo searchPlaceVo,
	// Integer page, Integer rows, String sidx, String sord);

	List<PublicPlace> searchDruggysForExport(SearchPublicPlaceVo searchPublicPlaceVo, Integer page,
			Integer rows, String sidx, String sord);

}
