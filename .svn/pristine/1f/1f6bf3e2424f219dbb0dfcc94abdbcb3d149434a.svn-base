package com.tianque.openLayersMap.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.openLayersMap.domian.vo.PersonInfoVo;

/**
 * 二维地图 人口信息个性化的接口
 * 
 * @author zhanghuafei
 * 
 */
public interface PersonService {
	/**
	 * 通过组织机构code和表名统计人口信息数据（主要应用于辖区分布）
	 * 
	 * @param orgInternalCode
	 * @param tableName
	 *            表名
	 * @return Integer
	 */
	public Integer countTwoDimensionMapPageInfoByOrgInternalCodeAndTableName(
			String orgInternalCode, String tableName);

	/**
	 * 根据住房ID,人员类型，获得该住房的人员类型数量
	 */
	public Integer countHouseHasPopulation(Long houseId, String type);

	/**
	 * 通过featureId,类型分页查询已绑定的人员数据
	 * 
	 * @param typeName
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return PageInfo<PersonInfoVo>
	 */
	public PageInfo<PersonInfoVo> findPersonByOrgIdAndTypeName(Long orgId,
			Long buildDataId, String typeName, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	/**
	 * 根据楼宇id和人口类型查询人口数量
	 * 
	 * @param buildDataId
	 * @param type
	 * @return
	 */
	public Integer countPopulationByBuildDataId(Long buildDataId, String type,
			Long orgId);

}
