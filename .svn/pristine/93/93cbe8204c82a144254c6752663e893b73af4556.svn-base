package com.tianque.plugin.analysisNew.dao;

import java.util.Map;

public interface AreaOrgStatNewDao {

	/**
	 * 根据orgCode,以及各个层级对应的数据字典id取得该组织下面的组织情况的统计
	 * 
	 * @param orgCode
	 *            （组织机构的code）
	 * @param provincePropertyDomainId
	 *            （省级数据字典id）
	 * @param cityPropertyDomainId
	 *            （市级数据字典id）
	 * @param districtPropertyDomainId
	 *            （县级数据字典id）
	 * @param townPropertyDomainId
	 *            （乡镇级数据字典id）
	 * @param villagePropertyDomainId
	 *            （社区级数据字典id）
	 * @param gridPropertyDomainId
	 *            （网格级数据字典id）
	 * @param gridPropertyDomainIdByType
	 *            （网格）
	 * 
	 */
	Map<String, Object> getAreaOrgStatDate(String orgCode,
			Long provincePropertyDomainId, Long cityPropertyDomainId,
			Long districtPropertyDomainId, Long townPropertyDomainId,
			Long villagePropertyDomainId, Long gridPropertyDomainId,
			Long gridPropertyDomainIdByType);
}
