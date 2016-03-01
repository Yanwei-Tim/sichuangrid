package com.tianque.plugin.transfer.dao;

import java.util.Map;

import com.tianque.domain.PopulationTypeBean;

public interface TransferOtherInfoDao {
	/**
	 * 修改ORG
	 * @param tableName
	 * @param map
	 */
	public void updateOrg(String tableName, Map<String, Object> map);
	
	public void updateAttentionPopulationExisted(String tableName,Long newId, Map<String, Object> map);
	public void deleteAttentionPopulationExisted(String tableName,Long id);

	/**
	 * 修改新的关联关系
	 * @param populationTypeBean
	 * @param newActualId
	 */
	public void updatePopulationTypeBean(PopulationTypeBean populationTypeBean, Long newActualId);
	
	public void updatePopulationAddressInfoId(String tableName,Long newAddressInfoId,Long populationId);

}
