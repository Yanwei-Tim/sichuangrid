package com.tianque.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.KeyPlace;
import com.tianque.openLayersMap.domian.vo.KeyPlaceInfoVo;
import com.tianque.solr.domain.DocumentType;

public interface KeyPlaceService {
	public KeyPlace addKeyPlace(KeyPlace keyPlace);

	public KeyPlace updateKeyPlace(KeyPlace keyPlace);

	public KeyPlace deleteKeyPlace(KeyPlace keyPlace);

	public void deleteKeyPlaceByIdAndInType(Long id, String types);

	public void deleteKeyPlaceByIdsAndInType(List<Long> ids, String types);

	public KeyPlace execute(DocumentType type, String mode, Object object);

	public List<KeyPlace> searchKeyPlaceByName(String name,
			String orgInternalCode);

	public PageInfo<KeyPlace> searchKeyPlacePageByName(String name,
			String orgInternalCode, Integer pageNum, Integer pageSize);

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
	PageInfo<KeyPlace> findUnBoundKeyPlaceByOrgId(Long orgid, int page,
			int rows, String sidx, String sord, String queryStr);

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

	List<KeyPlace> findKeyPlaceListByBuilddatasId(Long buildDatasId);

	void emphasisAndNotEmphasis(Long id, String type, Long emphasis);

	/**
	 * 根据编号和类型批量注销
	 * 
	 * @param ids
	 * @param types
	 * @param emphasis
	 */
	void emphasisKeyPlacesByIdsAndTypes(List<Long> ids, String types,
			Long emphasis);

	/**
	 * 单位场所转移修改
	 * */
	public void updateKeyPlaceByIdAndOrgInType(Long id, Long orgId,
			String orgInternalCode, String types);

	/**
	 * 修改地图经纬度值
	 * 
	 * @param keyPlaceInfoVo
	 */
	public void updateDomainByKeyplaces(KeyPlaceInfoVo keyPlaceInfoVo);
}
