package com.tianque.plugin.statistics.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.StringUtil;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.statistics.dao.PacketTaskListStatisticsDao;
import com.tianque.plugin.statistics.service.PacketTaskListStatisticsService;
@Service("packetTaskListStatisticsService")
@Transactional
public class PacketTaskListStatisticsServiceImpl implements PacketTaskListStatisticsService{

	@Autowired
	private PacketTaskListStatisticsDao packetTaskListStatisticsDao;
	@Override
	public void generalsituationStatistics(String tableName) {
		if(!StringUtil.isStringAvaliable(tableName)){
			throw new BusinessValidationException("数据清洗失败，未获得正确表名");
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("tableName", tableName);
		try{
			for (int i = 1; i <= 6; i++) {
				map.put("orgLevel", i);
				packetTaskListStatisticsDao.generalsituationStatistics(map);
			}
				packetTaskListStatisticsDao.fullOrgIdByOrgCode(map);
		}catch(Exception e){
			throw new ServiceValidationException("数据清洗失败",e);
		}
		
	}
}
