package com.tianque.openLayersMap.dao;


import java.util.List;

import com.tianque.openLayersMap.domian.GisTypeManage;

/**
 * 子类类别管理dao接口
 * 
 * @author yubin
 *
 */
public interface SysGisTypeManageDao {
	/** 修改 */
	public GisTypeManage updateGisTypeManage(GisTypeManage domain);

	/** 检索 */
	public GisTypeManage findGisTypeManageById(Long id);

	/** 获取数量 */
	public Integer countGisTypesByInnerType(GisTypeManage domain);

	/**
	 * 获取列表
	 * 
	 * @param pageSize
	 * @param pageNum
	 */
	public List<GisTypeManage> findGisTypesByInnerType(GisTypeManage domain,
			int pageNum, int pageSize);

	/** 新增 */
	public GisTypeManage addGisTypeManage(GisTypeManage domain);

	/** 删除 */
	public void deleteGisTypeManageById(Long id);

	/** 根据父类型 获取选中状态的数据 */
	public List<GisTypeManage> getNeedGisTypeManagesByInnerType(
			GisTypeManage domain);

	/** 是否存在相同的类型名称和key */
	public Boolean isHasDuplicateGisTypeManage(GisTypeManage domain);

	/** 根据key获取 */
	public GisTypeManage getGisTypeManagesByKey(GisTypeManage domain);

	/**
	 * 根据表名获取数据
	 * 
	 * @param domain
	 * @return GisTypeManage
	 */
	public GisTypeManage getGisTypeManagesByTableName(GisTypeManage domain);

	public Integer countGisTypesByInnerTypeOfNull();

	public List<GisTypeManage> findGisTypesByInnerTypeOfNull(Integer pageSize,
			Integer pageNum);
	
	public void deleteGisTypeManageByInnerKeyOfNull();

	/**
	 * 根据表名和类型得到子类对象
	 * 
	 * @param tableName 表名
	 * @param keyType 类型
	 * @return GisTypeManage
	 */
	public GisTypeManage getGisTypeManageByTableNameAndKeyType(
			String tableName, String keyType);
}
