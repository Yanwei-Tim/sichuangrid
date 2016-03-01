package com.tianque.baseInfo.base.dao;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.shard.util.IdConversionShardUtil;

@Repository("sourcesStateDao")
public class SourcesStateDaoImpl extends AbstractBaseDao implements
		SourcesStateDao {
	private static final String HOUSEHOLDSTAFFS = "householdStaffs";
	private static final String HOUSEINFO = "houseinfo";

	public void updateSourcesStateById(String table, Long id, Long sourcesState) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("table", table);
		if (HOUSEHOLDSTAFFS.equals(table) || HOUSEINFO.equals(table)) {
			map.put("table",
					table + "_" + IdConversionShardUtil.getShardCodeById(id));
		}
		map.put("id", id);
		map.put("sourcesState", sourcesState);
		getSqlMapClientTemplate().update("baseInfoStanal.updateSourcesState",
				map);
	}
}
