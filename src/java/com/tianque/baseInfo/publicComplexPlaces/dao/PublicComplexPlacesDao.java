package com.tianque.baseInfo.publicComplexPlaces.dao;

import java.util.Date;

import com.tianque.baseInfo.publicComplexPlaces.domain.PublicComplexPlaces;
import com.tianque.core.vo.PageInfo;

/**
 * 公共复杂场所数据库操作接口
 */
public interface PublicComplexPlacesDao {
	/**
	 * 增加公共复杂场所 数据
	 * 
	 * @param publicComplexPlaces
	 * @return publicComplexPlaces
	 */
	public PublicComplexPlaces addPlace(PublicComplexPlaces place);

	/**
	 * 根据id获得 公共复杂场所数据
	 * 
	 * @param id
	 * @return PublicComplexPlaces
	 */
	public PublicComplexPlaces getPlaceById(Long id);

	/**
	 * 根据id删除公场所数据
	 * 
	 * @param id
	 */
	public void deletePlaceById(Long id);

	/**
	 * 修改公共复杂场所数据
	 * 
	 * @param publicComplexPlaces
	 * @return PublicComplexPlaces
	 */
	public PublicComplexPlaces updatePlace(PublicComplexPlaces place);

	public PageInfo<PublicComplexPlaces> findPublicComplexPlacesForPageByOrgInternalCode(String orgInternalCode,
			Integer page, Integer rows, String sidx, String sord, Boolean isEmphasis);

	public void updateEmphasiseById(Long id, Boolean isEmphasis, String logoutReason,
			Date logoutDate);

	public PublicComplexPlaces getPlaceByPlaceAddress(String placeName, Long orgId);
}
