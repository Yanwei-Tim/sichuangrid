package com.tianque.baseInfo.base.dao;

public interface PopulationRelationDao {

	/**
	 * 绑定实口和业务人员
	 * 
	 * @param actualId
	 * @param actualType
	 * @param populationId
	 * @param populationType
	 */
	public void addPopulationRelation(Long actualId, String actualType,
			Long populationId, String populationType);

	/**
	 * 从业务人员删除实口和业务人员关系
	 * 
	 * @param populationId
	 * @param populationType
	 */
	public void businessDeletePopulationRelation(Long populationId,
			String populationType);

	/**
	 * 从实口删除实口和业务人员关系
	 * 
	 * @param actualId
	 * @param actualType
	 */
	public void actualDeletePopulationRelation(Long actualId, String actualType);
}
