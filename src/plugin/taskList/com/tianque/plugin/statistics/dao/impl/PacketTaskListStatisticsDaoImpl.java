package com.tianque.plugin.statistics.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.dao.AbstractBaseDao;
import com.tianque.plugin.statistics.dao.PacketTaskListStatisticsDao;

@Repository("packetTaskListStatisticsDao")
public class PacketTaskListStatisticsDaoImpl extends AbstractBaseDao implements PacketTaskListStatisticsDao{

	@Override
	public void generalsituationStatistics(Map<String, Object> map) {
		getSqlMapClientTemplate().insert("packetTaskListStatistics.generalsituationStatistics",map);
	}

	@Override
	public void fullOrgIdByOrgCode(Map<String, Object> map) {
		getSqlMapClientTemplate().insert("packetTaskListStatistics.fullOrgIdByOrgCode",map);
		getSqlMapClientTemplate().delete("packetTaskListStatistics.deleteOrgIdIsNull",map);
//		getSqlMapClientTemplate().delete("packetTaskListStatistics.deleteSubTypeIsNull",map);
	}
	
}
