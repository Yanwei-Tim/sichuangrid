package com.tianque.baseInfo.publicPlace.dao;

import java.util.Date;

import com.tianque.baseInfo.publicPlace.domain.PublicPlace;
import com.tianque.core.vo.PageInfo;

/**
 * 公共场所数据库操作接口
 */
public interface PublicPlaceDao {
	/**
	 * 增加公共场所 数据
	 * 
	 * @param publicPlace
	 * @return publicPlace
	 */
	public PublicPlace addPlace(PublicPlace place);

	/**
	 * 根据id获得 公共场所数据
	 * 
	 * @param id
	 * @return PublicPlace
	 */
	public PublicPlace getPlaceById(Long id);

	/**
	 * 根据id删除公场所数据
	 * 
	 * @param id
	 */
	public void deletePlaceById(Long id);

	/**
	 * 修改公共产所数据
	 * 
	 * @param publicPlace
	 * @return PublicPlace
	 */
	public PublicPlace updatePlace(PublicPlace place);

	public PageInfo<PublicPlace> findPublicPlaceForPageByOrgInternalCode(String orgInternalCode,
			Integer page, Integer rows, String sidx, String sord, Boolean isEmphasis);

	public void updateEmphasiseById(Long id, Boolean isEmphasis, String logoutReason,
			Date logoutDate);

	public PublicPlace getPlaceByPlaceAddress(String placeName, Long orgId);
}
