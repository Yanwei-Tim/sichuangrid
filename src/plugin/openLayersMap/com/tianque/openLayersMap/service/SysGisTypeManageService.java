package com.tianque.openLayersMap.service;


import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.openLayersMap.domian.GisTypeManage;
import com.tianque.openLayersMap.domian.vo.StatisticInfoVo;

/**
 * 地图子类类别管理
 * 
 * @date 2013-3-15
 */
public interface SysGisTypeManageService {

	/** 新增 */
	public GisTypeManage addGisTypeManage(GisTypeManage domain);

	/** 修改 */
	public GisTypeManage updateGisTypeManage(GisTypeManage domain);

	/**
	 * 修改子类对象在地图中显示还是隐藏的状态
	 * 
	 * @param domain
	 *            子类对象
	 * @return GisTypeManage
	 */
	public GisTypeManage updateGisTypeManageStatus(GisTypeManage domain);

	/** 删除 */
	public void deleteGisTypeManageById(Long id);

	/** 检索 */
	public GisTypeManage getGisTypeManageById(Long id);

	/** 获取所有的数据 */
	public PageInfo<GisTypeManage> findGisTypesForPageByInnerType(int pageNum, int pageSize, GisTypeManage domain);

	/** 根据父类型 获取选中状态的数据 */
	public List<GisTypeManage> getNeedGisTypeManagesByInnerType(GisTypeManage domain);

	/**
	 * 根据父类型和网格id 获取选中状态的数据
	 * 
	 * @param domain
	 *            GisTypeManage
	 * @param OrgId
	 *            网格id
	 * @return List<StatisticInfoVo>
	 */
	public List<StatisticInfoVo> getNeedGisTypeManagesByInnerTypeAndOrgId(GisTypeManage domain, Long OrgId);

	/** 是否存在相同的类型名称和key */
	public Boolean isHasDuplicateGisTypeManage(GisTypeManage gisTypeManage);

	/** 根据获取数据 */
	public GisTypeManage getGisTypeManagesByKey(GisTypeManage domain);

	/**
	 * 根据表名获取数据
	 * 
	 * @param domain
	 * @return GisTypeManage
	 */
	public GisTypeManage getGisTypeManagesByTableName(GisTypeManage domain);

	/**
	 * (innerType)内置类型为null
	 * 
	 * @param pageSize
	 * @param pageNum
	 * @return PageInfo<GisTypeManage>
	 */
	public PageInfo<GisTypeManage> findGisTypesByInnerTypeOfNull(Integer pageSize, Integer pageNum);

	/**
	 * 删除gisTypeManage表中为空的innerKey的数据
	 * 
	 */
	public void deleteGisTypeManageByInnerKeyOfNull();

	/**
	 * 通过tableName和keyType查询匹配的子类配置
	 * 
	 */
	public GisTypeManage getGisTypeManageByTableNameAndKeyType(String tableName, String keyType);
}
