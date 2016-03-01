package com.tianque.openLayersMap.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.openLayersMap.domian.vo.PersonInfoVo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;

/**
 * 二维地图 人口信息的dao
 * 
 * @author jiangjinling
 * 
 */
public interface PersonTwoDimensionMapDao {

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
	 * @param string
	 * @return PageInfo<PersonInfoVo>
	 */
	public PageInfo<PersonInfoVo> findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndSearchValue(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo,
			String searchValue, Integer pageNum, Integer pageSize, String sidx,
			String sord, String tableName, List list, String shardCode);

	/**
	 * 通过组织机构ID、屏幕坐标集和搜索条件统计二维地图总数（主要应用于辖区搜索）
	 * 
	 * @param orgInternalCode
	 * @param searchValue
	 * @return Integer
	 */
	public Integer statisticTwoDimensionMapInfoByOrgInternalCodeAndSearchValue(
			String orgInternalCode, String searchValue);

	/**
	 * 通过组织机构ID、屏幕坐标集和搜索条件统计二维地图绑定数量（主要应用于辖区搜索）
	 * 
	 * @param orgInternalCode
	 * @param searchValue
	 * @param string
	 * @return Integer
	 */
	public Integer statisticBoundedTwoDimensionMapInfoByOrgInternalCodeAndSearchValue(
			String orgInternalCode, String searchValue, String tableName,
			String personType, String shardCode);

	/**
	 * 根据Id和表名得到详情查看popup页面
	 * 
	 * @param id
	 * @param tableName
	 * @return PersonInfoVo
	 */
	public PersonInfoVo getViewPopupInfoByIdAndTableName(Long id,
			String tableName, String personType, String shardCode);

	/**
	 * 通过组织机构id统计人口二维地图数据（主要应用于辖区分布）
	 * 
	 * @param orgId
	 * @param tableName
	 *            子类表名
	 * @return Integer
	 */
	public Integer countTwoDimensionMapPageInfoByOrgId(String orgInternalCode,
			String tableName, String shardCode);

	/**
	 * 根据住房ID,人员类型，获得该住房的人员类型数量
	 */
	public Integer countHouseHasPopulation(Long houseId, String type);

	/**
	 * 通过组织机构Code、屏幕坐标集、类型分页查询二维地图数据
	 * 
	 * @param orgInternalCode
	 *            组织机构code
	 * @param screenCoordinateVo
	 *            当前屏幕的对象
	 * @param typeName
	 *            区分下面的子类
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return PageInfo<PersonInfoVo>
	 */
	public PageInfo<PersonInfoVo> findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeName(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo,
			String typeName, String personTypeName, Integer pageNum,
			Integer pageSize, String sidx, String sord, String shardCode);

	public Integer statisticBoundedTwoDimensionMapInfoByOrgInternalCodeAndTypeName(
			String orgInternalCode, String typeName, String shardCode);

	/**
	 * 通过组织机构Code和类型分页查询二维地图数据（主要应用于辖区分布）
	 * 
	 * @param orgInternalCode
	 * @param typeName
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @param personTypeName
	 * @return
	 */
	public PageInfo<PersonInfoVo> findTwoDimensionMapPageInfoByOrgInternalCodeAndTypeName(
			String orgInternalCode, String typeName, Integer pageNum,
			Integer pageSize, String sidx, String sord, String personTypeName,
			String shardCode);

	/**
	 * 通过房屋ID获取人员信息
	 * 
	 * @param houseIds
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<PersonInfoVo> findPersonsByHouseId(Long houseId,
			Integer pageNum, Integer pageSize, String sidx, String sord,
			String shardCode);

	/**
	 * 通过房屋Id和类型获取各类人员信息
	 * 
	 * @param houseId
	 * @param personType
	 * @param personTypeName
	 * @param typeName
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<PersonInfoVo> findPersonsByHouseIdAndType(Long houseId,
			String personType, String personTypeName, String typeName,
			Integer pageNum, Integer pageSize, String sidx, String sord,
			String shardCode);

	/**
	 * 根据楼宇id和人口类型查询人口数量
	 * 
	 * @param buildDataId
	 * @param type
	 * @return
	 */
	public Integer countPopulationByBuildDataId(Long buildDataId, String type,
			String shardCode);

}
