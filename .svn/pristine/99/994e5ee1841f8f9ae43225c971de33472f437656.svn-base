package com.tianque.plugin.analyzing.service;

import java.util.List;

import com.tianque.plugin.analyzing.domain.HighchartColumnVo;
import com.tianque.plugin.analyzing.domain.StatAnalysePlaceVo;

public interface StatAnalysePlaceService {
	/**
	 * 列表信息,实时获取
	 * 
	 * @param tableName
	 * @param orgId
	 * @param keyType
	 * @return
	 */
	public List<StatAnalysePlaceVo> findStatAnalysePlace(String tableName, Long orgId,
			String keyType);

	public List<StatAnalysePlaceVo> findStatAnalysePlaceFromHistory(String tableName, Long orgId,
			String keyType, int year, int month);

	/**
	 * 类型分布图，实时获取
	 * 
	 * @param tableName
	 * @param orgId
	 * @param year
	 * @param month
	 * @return
	 */
	public HighchartColumnVo getStatAnalysePlace(String tableName, Long orgId, String keyType);

	/**
	 * 类型分布图，历史数据
	 * 
	 * @param orgId
	 * @param typeTableName
	 * @param year
	 * @param month
	 * @return
	 */
	public HighchartColumnVo getStatAnalysePlaceFromHistory(Long orgId, String typeTableName,
			int year, int month);

}
