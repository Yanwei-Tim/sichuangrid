package com.tianque.openLayersMap.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.openLayersMap.domian.vo.IssueInfoVo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;

public interface IssueTwoDimensionMapDao {
	/**
	 * 通过组织机构ID和屏幕坐标集分页查询二维地图数据（主要应用于图层）
	 * 
	 * @param orgInternalCode
	 * @param screenCoordinateVo
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<IssueInfoVo> findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVo(
			Long orgId, String orgInternalCode,
			ScreenCoordinateVo screenCoordinateVo, Integer pageNum,
			Integer pageSize, String sidx, String sord, Long dealState,
			String type);

	/**
	 * 通过组织机构ID、屏幕坐标集和搜索条件分页查询二维地图数据（主要应用于搜索）
	 * 
	 * @param orgInternalCode
	 * @param screenCoordinateVo
	 * @param searchValue
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<IssueInfoVo> findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndSearchValue(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo,
			String searchValue, Integer pageNum, Integer pageSize, String sidx,
			String sord, String mainTableName);

	/**
	 * 通过组织机构Code统计二维地图绑定数（主要应用于图层）
	 * 
	 * @param orgInternalCode
	 * @return
	 */
	public Integer statisticBoundedTwoDimensionMapInfoByOrgInternalCode(
			String orgInternalCode, String type);

	/**
	 * 通过组织机构ID、屏幕坐标集和搜索条件统计二维地图绑定数（主要应用于搜索）
	 * 
	 * @param orgInternalCode
	 * @param screenCoordinateVo
	 * @param searchValue
	 * @return
	 */
	public Integer statisticBoundedTwoDimensionMapInfoByOrgInternalCodeAndSearchValue(
			String orgInternalCode, String searchValue);

	/**
	 * 二维地图绑定、解除绑定
	 * 
	 * @param keyPlaceInfoVo
	 * 
	 * @return boolean
	 */
	public boolean updateTwoDimensionMap(IssueInfoVo infoVo);

	/**
	 * 通过组织机构ID和状态类型分页查询二维地图数据（主要应用于辖区分布：个人待办）
	 * 
	 * @param orgId
	 * @param typeName
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<IssueInfoVo> findMyNeedDoTwoDimensionMapPageInfoByOrgIdAndTypeName(
			Long orgId, Long typeName, Integer pageNum, Integer pageSize,
			String sidx, String sord);

	/**
	 * 通过组织机构ID和状态类型分页查询二维地图数据（主要应用于辖区分布：个人已办(历史事件)）
	 * 
	 * @param orgId
	 * @param typeName
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<IssueInfoVo> findMyDoneHistoryTwoDimensionMapPageInfoByOrgIdAndDealStateList(
			Long orgId, List<Long> dealStateList, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	/**
	 * 通过组织机构ID和状态类型分页查询二维地图数据（主要应用于辖区分布：个人已办（近期已办））
	 * 
	 * @param orgId
	 * @param typeName
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<IssueInfoVo> findMyDoneTwoDimensionMapPageInfoByOrgIdAndDealStateList(
			Long orgId, List<Long> dealStateList, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	/**
	 * 通过组织机构ID和状态类型分页查询二维地图数据（主要应用于辖区分布：下辖待办）
	 * 
	 * @param orgId
	 * @param orgInternalCode
	 * @param dealState
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<IssueInfoVo> findMyJurisdictionsNeedDoTwoDimensionMapPageInfoByOrgIdAndDealState(
			Long orgId, String orgInternalCode, Long dealState,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	/**
	 * 通过组织机构ID和状态类型分页查询二维地图数据（主要应用于辖区分布：下辖已办）
	 * 
	 * @param orgId
	 * @param orgInternalCode
	 * @param dealState
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<IssueInfoVo> findMyJurisdictionsDoneTwoDimensionMapPageInfoByOrgIdAndDealState(
			Long orgId, String orgInternalCode, Long dealState,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	/**
	 * 通过组织机构ID和状态类型分页查询二维地图数据（主要应用于辖区分布：下辖已办）
	 * 
	 * @param orgId
	 * @param orgInternalCode
	 * @param dealState
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<IssueInfoVo> findMyJurisdictionsHistoryDoneTwoDimensionMapPageInfoByOrgIdAndDealState(
			Long orgId, String orgInternalCode, Long dealState,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	/**
	 * 通过组织机构ID和状态类型分页查询二维地图数据（主要应用于辖区分布：我的近期已办办结）
	 * 
	 * @param orgId
	 * @param orgInternalCode
	 * @param dealState
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<IssueInfoVo> findMyFinishTwoDimensionMapPageInfoByOrgIdAndDealState(
			Long orgId, String orgInternalCode, Long dealState,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	/**
	 * 通过组织机构ID和状态类型分页查询二维地图数据（主要应用于辖区分布：我的历史已办办结）
	 * 
	 * @param orgId
	 * @param orgInternalCode
	 * @param dealState
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<IssueInfoVo> findMyFinishHistoryTwoDimensionMapPageInfoByOrgIdAndDealState(
			Long orgId, String orgInternalCode, Long dealState,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	/**
	 * 通过组织机构ID和是否待办状态统计待办事件数据（主要应用于辖区分布）
	 * 
	 * @param orgId
	 * @param typeName
	 * @return Integer
	 */
	public Integer countMyNeedDoTwoDimensionMapPageInfoByOrgIdAndTypeName(
			Long orgId, Long typeName);

	/**
	 * 通过组织机构ID和是否待办状态统计已办事件数据（主要应用于辖区分布）
	 * 
	 * @param orgId
	 * @param dealStateList
	 * @return Integer
	 */
	public Integer countMyDoneTwoDimensionMapPageInfoByOrgIdAndDealStateList(
			Long orgId, List<Long> dealStateList);

	/**
	 * 通过组织机构ID和组织机构CODE和是否待办状态统计下辖待办事件数据（主要应用于辖区分布）
	 * 
	 * @param orgId
	 * @param orgInternalCode
	 * @param dealState
	 * @return Integer
	 */
	public Integer countMyJurisdictionsNeedDoTwoDimensionMapPageInfoByOrgIdAndDealState(
			Long orgId, String orgInternalCode, Long dealState);

	/**
	 * 通过组织机构ID和组织机构CODE和是否待办状态统计下辖已办事件数据（主要应用于辖区分布）
	 * 
	 * @param orgId
	 * @param orgInternalCode
	 * @param dealState
	 * @return Integer
	 */
	public Integer countMyJurisdictionsDoneTwoDimensionMapPageInfoByOrgIdAndDealState(
			Long orgId, String orgInternalCode, Long dealState);

	/**
	 * 通过组织机构ID和组织机构CODE和是否待办状态统计我的已办结事件数据（主要应用于辖区分布）
	 * 
	 * @param orgId
	 * @param orgInternalCode
	 * @param dealState
	 * @return Integer
	 */
	public Integer countMyDoneTwoDimensionMapPageInfoByOrgIdAndDealState(
			Long orgId, String orgInternalCode, Long dealState);

	/**
	 * 通过组织机构编码模糊查询某地图区域范围内的事件集合对象
	 * 
	 * @param orgInternalCode
	 *            组织机构编码
	 * @param screenCoordinateVo
	 *            地图某区域最大和最小经纬度
	 * @return List<IssueInfoVo>
	 */
	public List<IssueInfoVo> findIssueInfoVosByOrgCodeAndScreenCoordinateVo(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo);

	/**
	 * 通过组织机构Code获取二维地图数据总数（主要应用于画区域统计）
	 * 
	 * @param orgInternalCode
	 * @return
	 */
	public Integer statisticTwoDimensionMapInfoSumByOrgInternalCode(
			String orgInternalCode);

	/**
	 * 根据Id得到详情查看popup页面(个人待办、下辖待办)
	 * 
	 * @param id
	 * @return
	 */
	public IssueInfoVo getViewPopupInfoById(Long id);

	/**
	 * 根据事件id得到该事件类型
	 * 
	 * @param id
	 * @return List<IssueInfoVo>
	 */
	public List<IssueInfoVo> getIssueTypeByIssueId(Long id);

	/**
	 * 根据Id得到详情查看popup页面(已办)
	 * 
	 * @param id
	 * @return
	 */
	public IssueInfoVo getAlreadyViewPopupInfoById(Long id);

	/**
	 * 根据Id得到详情查看popup页面(已办结)
	 * 
	 * @param id
	 * @return
	 */
	public IssueInfoVo getAlreadyThingViewPopupInfoById(Long id);
}
