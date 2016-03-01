package com.tianque.plugin.transfer.dao;

import java.util.List;

import com.tianque.domain.PopulationTypeBean;

public interface HouseDao {
	// 通过业务人员的id和type来查询实口Id和实口类型
	public Object getByPopulationIdAndPopulationType(Long populationId, String populationType);

	// 通过业务人员id和type来获取其关联的houseid
	public Object getByImportantIdAndImportantType(Long populationId, String populationType);

	// 通过实口id和type来获取其关联的houseid
	public Object getByActualIdAndActualType(Long populationId, String populationType);

	// 更新业务人员的人房关联
	public void updateImportantHouseId(Long populationId, String populationType, Long oldHouseId,
			Long newHouseId);

	// 删除各类人员的人房关联
	public void deleteRelationByPopulationTypeAndPopulationId(String str, Long populationId,
			String populationType);

	// 根据实口id和type来获取其对应的所有业务人员
	public List<PopulationTypeBean> getAllImportantByActualIdAndType(Long actualId,
			String actualType);

	// 给业务人员增加人房关联关系
	public void addHouseRelation(String personType, String populationType, Long houseId,
			Long populationId, boolean defaultLivingHouse);
}
