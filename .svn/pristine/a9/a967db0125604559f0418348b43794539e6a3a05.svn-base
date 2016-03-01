package com.tianque.dao;

import java.util.List;
import java.util.Map;

import com.tianque.baseInfo.buildDatas.domain.vo.LayoutTagVo;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.KeyPlace;

public interface KeyPlaceDao {
	public KeyPlace addKeyPlace(KeyPlace keyPlace);

	public KeyPlace getKeyPlaceByIdKey(Long id_key);

	public KeyPlace updateKeyPlace(KeyPlace keyPlace);

	public void updateKeyPlaceByIdAndOrgInType(Long id, Long orgId,
			String orgInternalCode, String types);

	public void deleteKeyPlaceByIdAndType(KeyPlace keyPlace);

	public void deleteKeyPlaceByIdAndInType(Long id, String types);

	public void deleteKeyPlaceByIdsAndInType(List<Long> ids, String types);

	public List<KeyPlace> searchKeyPlaceByName(Map<String, String> map);

	public List<KeyPlace> getKeyPlaceByIdInType(Long id, String types);

	public KeyPlace getKeyPlaceByIdAndType(Long id, String type);

	/**
	 * 根据楼宇id查询已绑定的重点场所
	 * 
	 * @param builddatasid
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	PageInfo<KeyPlace> findBoundKeyPlaceByBuilddatasIdForPage(
			Long builddatasid, int page, int rows, String sidx, String sord);

	/**
	 * 根据组织机构id查询未绑定的重点场所
	 * 
	 * @param orgid
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @param queryStr
	 * @return
	 */
	PageInfo<KeyPlace> findUnBoundKeyPlaceByOrgId(String orgInternalCode,
			int page, int rows, String sidx, String sord, String queryStr);

	/**
	 * 绑定重点场所
	 * 
	 * @param houseIds
	 * @param builddatasId
	 */
	void boundKeyPlace(List<Long> keyPlaceIds, Long builddatasId);

	/**
	 * 解除绑定重点场所
	 * 
	 * @param houseIds
	 * @param builddatasId
	 */
	void unboundKeyPlace(List<Long> keyPlaceIds);

	List<LayoutTagVo> searchAllKeyPlaceName4LayoutTag(String orgInternalCode,
			Long buildDatasId);

	List<KeyPlace> findKeyPlaceListByBuilddatasId(Long buildDatasId);

	Integer countKeyPlaceByBuilddatasIdAndKeyPlaceType(long buildDatasId,
			String keyPlaceType);

	String getKeyPlaceNameByIdAndType(Long id, String type);

	PageInfo<KeyPlace> searchKeyPlaceByName(String name,
			String orgInternalCode, Integer pageNum, Integer pageSize);

	void emphasisAndNotEmphasis(KeyPlace keyPlace);

	/**
	 * 根据编号和类型批量注销
	 * 
	 * @param ids
	 * @param types
	 * @param emphasis
	 */
	void emphasisKeyPlacesByIdsAndTypes(List<Long> ids, String types,
			Long emphasis);

	void updateDomainByKeyplaces(Map<String, Object> map);

}
