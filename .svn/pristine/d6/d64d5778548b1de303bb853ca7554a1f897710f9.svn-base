package com.tianque.plugin.transfer.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.CalendarUtil;
import com.tianque.domain.PopulationTypeBean;
import com.tianque.shard.util.IdConversionShardUtil;
import com.tianque.shard.util.ShardTables;

@Repository("transferOtherInfoDao")
public class TransferOtherInfoDaoImpl extends AbstractBaseDao implements
		TransferOtherInfoDao {

	@Override
	public void updateOrg(String tableName, Map<String, Object> map) {
		if(ShardTables.isShardTables(tableName)){
			String shardCode = IdConversionShardUtil
					.getShardCodeById((Long)map.get("id"));
			tableName = tableName + "_" + shardCode;
		}
		map.put("tableName", tableName);
		map.put("nowDate", CalendarUtil.now());
		getSqlMapClientTemplate().update("transferOtherInfo.updateOrg", map);
	}

	@Override
	public void updatePopulationTypeBean(PopulationTypeBean populationTypeBean,
			Long newActualId) {
		Map<String, Object> map = new HashMap();
		map.put("newActualId", newActualId);
		map.put("actualId", populationTypeBean.getActualId());
		map.put("actualType", populationTypeBean.getActualType());
		map.put("populationId", populationTypeBean.getPopulationId());
		map.put("populationType", populationTypeBean.getPopulationType());
		getSqlMapClientTemplate().update(
				"transferOtherInfo.updatePopulationTypeBean", map);
	}

	@Override
	public void updateAttentionPopulationExisted(String tableName, Long newId,
			Map<String, Object> map) {
		if(ShardTables.isShardTables(tableName)){
			String shardCode = IdConversionShardUtil
					.getShardCodeById((Long)map.get("id"));
			tableName = tableName + "_" + shardCode;
		}
		map.put("tableName", tableName);
		map.put("newId", newId);
		getSqlMapClientTemplate().update(
				"transferOtherInfo.updateAttentionPopulationExisted", map);

	}

	@Override
	public void deleteAttentionPopulationExisted(String tableName, Long id) {
		if(ShardTables.isShardTables(tableName)){
			String shardCode = IdConversionShardUtil
					.getShardCodeById(id);
			tableName = tableName + "_" + shardCode;
		}
		Map<String, Object> map = new HashMap();
		map.put("tableName", tableName);
		map.put("id", id);
		getSqlMapClientTemplate().delete(
				"transferOtherInfo.deleteAttentionPopulationExisted", map);

	}

	@Override
	public void updatePopulationAddressInfoId(String tableName,
			Long newAddressInfoId, Long populationId) {
		if(ShardTables.isShardTables(tableName)){
			String shardCode = IdConversionShardUtil
					.getShardCodeById(populationId);
			tableName = tableName + "_" + shardCode;
		}
		Map<String, Object> map = new HashMap();
		map.put("tableName", tableName);
		map.put("newAddressInfoId", newAddressInfoId);
		map.put("populationId", populationId);
		getSqlMapClientTemplate().delete(
				"transferOtherInfo.updatePopulationAddressInfoId", map);

	}

}
