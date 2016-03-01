package com.tianque.openLayersMap.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tianque.core.vo.PageInfo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.domian.vo.SearchInfoVo;
import com.tianque.openLayersMap.util.GisProperties;
import com.tianque.plugin.tqSearch.service.TqQueryService;
import com.tianque.shard.util.ShardTables;

/**
 * dao抽象类
 * 
 * @author N73
 * 
 * @param <T>
 */

public class AbstractDao<T> extends BaseDao<T> {
	@Autowired
	private TqQueryService tqQueryService;

	protected Integer updateForBind(String statementName, Object parameters) {
		int num = getSqlMapClientTemplate().update(statementName, parameters);
		if (num > 0 && GisProperties.ISUSE_TQSEARCH) {
			tqQueryService.updateIndex(statementName, parameters);
		}
		return num;
	}

	protected Object queryForObject(String statementName,
			Map<String, Object> parameters) {
		if (GisProperties.ISUSE_TQSEARCH) {
			return tqQueryService.queryForObject(statementName, parameters);
		} else {
			return getSqlMapClientTemplate().queryForObject(statementName,
					parameters);
		}
	}

	protected List queryForList(String statementName,
			Map<String, Object> parameters, Integer pageNum, Integer pageSize) {
		if (GisProperties.ISUSE_TQSEARCH) {
			pageNum = (pageNum < 1) ? 1 : pageNum;
			parameters.put("startRow", (pageNum - 1) * pageSize);
			parameters.put("endRow", pageNum * pageSize);
			return tqQueryService.queryForList(statementName, parameters);
		} else {
			return getSqlMapClientTemplate().queryForList(statementName,
					parameters, (pageNum - 1) * pageSize, pageSize);
		}
	}

	protected List queryForList(String statementName,
			Map<String, Object> parameters) {
		if (GisProperties.ISUSE_TQSEARCH) {
			return tqQueryService.queryForList(statementName, parameters);
		} else {
			return getSqlMapClientTemplate().queryForList(statementName,
					parameters);
		}
	}

	protected Object queryForCount(String statementName,
			Map<String, Object> parameters) {
		if (GisProperties.ISUSE_TQSEARCH) {
			Object count = tqQueryService.queryForCount(statementName,
					parameters);
			return (count != null) ? Integer.parseInt(count + "") : 0;
		} else {
			return getSqlMapClientTemplate().queryForObject(statementName,
					parameters);
		}
	}

	@SuppressWarnings("unchecked")
	protected PageInfo<T> queryForPageInfo(String countListStatementName,
			String getListStatementName, int pageNum, int pageSize,
			Map<String, Object> paramObject) {
		Object total = queryForCount(countListStatementName, paramObject);
		List<T> result = queryForList(getListStatementName, paramObject,
				pageNum, pageSize);
		return new PageInfo<T>(pageNum, pageSize, Integer.parseInt(total + ""),
				result);
	}

	@SuppressWarnings("unchecked")
	protected List<T> queryForList(String countListStatementName,
			String getListStatementName, int pageNum, int pageSize,
			Map<String, Object> paramObject) {
		Object total = queryForCount(countListStatementName, paramObject);
		List<T> result = queryForList(getListStatementName, paramObject,
				pageNum, pageSize);
		return result;
	}

	@SuppressWarnings("unchecked")
	protected PageInfo<T> getPageInfo(String countListStatementName,
			String getListStatementName, int pageNum, int pageSize,
			Map<String, Object> paramObject) {
		pageNum = (pageNum < 1) ? 1 : pageNum;
		paramObject.put("startRow", (pageNum - 1) * pageSize);
		paramObject.put("endRow", pageNum * pageSize);

		Integer total = (Integer) getSqlMapClientTemplate().queryForObject(
				countListStatementName, paramObject);
		List<T> result = getSqlMapClientTemplate().queryForList(
				getListStatementName, paramObject);
		return new PageInfo<T>(pageNum, pageSize, total, result);
	}

	// 得到参数
	protected Map<String, Object> getParamMap(String orgInternalCode,
			String searchValue, String tableName, String personType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("searchValue", searchValue);
		map.put("tableName", tableName);
		map.put("personType", personType);
		return map;
	}

	// 得到参数(统计绑定数、总数)
	protected Map<String, Object> getStatisticParamMap(String orgInternalCode,
			String typeName, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		if (ShardTables.isShardTables(typeName) && shardCode != null) {
			map.put("typeName", typeName + "_" + shardCode);
		} else {
			map.put("typeName", typeName);
		}
		if(shardCode != null){
			map.put("shardCode", shardCode);
		}
		return map;
	}

	// 得到查询Map
	protected Map<String, Object> getMap(String orgInternalCode,
			String typeName, String sidx, String sord,
			ScreenCoordinateVo screenCoordinateVo, String searchValue,
			Long buildDataId, String isBound, String shardCode) {
		Map<String, Object> map = getStatisticParamMap(orgInternalCode,
				typeName, shardCode);
		map.put("isBound", isBound);// 查询未绑定的数据
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("searchValue", searchValue);
		map.put("screenCoordinateVo", screenCoordinateVo);
		map.put("buildDataId", buildDataId);
		SearchInfoVo searchInfoVo = (screenCoordinateVo != null) ? screenCoordinateVo
				.getSearchInfoVo() : null;
		map.put("searchInfoVo", searchInfoVo);
		return map;
	}
}
