package com.tianque.dao;

import java.util.List;

import com.tianque.domain.PopulationTypeBean;

/**
 * 实有人口与业务信息关联关系数据库操作接口。
 */
public interface PopulationTypeDao {

	/**
	 * 新增实有人口与业务信息关联关系
	 * 
	 * @param populationType
	 *            关系对象
	 */
	public void addPopulationType(PopulationTypeBean populationType);

	/**
	 * 根据实有人口主键和类型删除关联关系
	 * 
	 * @param actualId
	 *            实有人口主键
	 * @param actualType
	 *            实有人口类型
	 */
	public void deletePopulationTypeByActualIdAndType(Long actualId,
			String actualType);

	/**
	 * 根据业务人员主键和类型删除关联关系
	 * 
	 * @param actualId
	 *            业务人员主键
	 * @param actualType
	 *            业务人员类型
	 */
	public void deletePopulationTypeByPopulationIdAndType(Long populationId,
			String populationType);

	/**
	 * 根据实有人口主键和类型获取关联关系
	 * 
	 * @param actualId
	 *            实有人口主键
	 * @param actualType
	 *            实有人口类型
	 * @return List<PopulationType> 关联关系集合
	 */
	public List<PopulationTypeBean> getPopulationTypeByActualIdAndType(
			Long actualId, String actualType);

	/**
	 * 根据业务人员主键和类型获取关联关系
	 * 
	 * @param actualId
	 *            业务人员主键
	 * @param actualType
	 *            业务人员类型
	 */
	public PopulationTypeBean getPopulationTypeByPopulationIdAndType(
			Long populationId, String populationType);

	/**
	 * 根据条件删除业务与实口关联关系
	 * 
	 * @param actualId
	 *            实有人口主键
	 * @param actualType
	 *            实有人口类型
	 * @param actualType
	 *            业务人员类型
	 * @return @
	 */
	public void deletePopulationTypeByActualIdAndTypeAndPopulationType(
			Long actualId, String actualType, String populationType);

	/**
	 * 通过orgId和idCardNo获取该人口的实口类型(从户籍和流动的表中查询符合的记录) 其中actualId和actualType有值
	 * 
	 * @param orgId
	 * @param idCardNo
	 * @return
	 */
	public PopulationTypeBean getActualPopulationTypeBeanByOrgIdAndIdCardNo(
			Long orgId, Long baseInfoId, String shardCode);
}
