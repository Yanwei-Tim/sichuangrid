package com.tianque.plugin.tqSearch.syncSolrIndex;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.core.util.DateUtil;
import com.tianque.jms.OperateMode;
import com.tianque.jms.msg.PopulationSolrIndexMsg;
import com.tianque.jms.sender.ActiveMQMessageSender;

@Component("syncPopulationSolrIndexForMQ")
public class SyncPopulationSolrIndexForMQ {
	
	private static final Logger		logger				= LoggerFactory.getLogger(SyncPopulationSolrIndexForMQ.class);
	
	@Autowired
	private ActiveMQMessageSender activeMQMessageSender;
	
	public void sendPopulationSolrIndexForMQ(Object obj, String type,
			OperateMode mode) {
		try {
			if(OperateMode.DELETE.equals(mode)){
				PopulationSolrIndexMsg populationSolrIndexMsg = new PopulationSolrIndexMsg();
				populationSolrIndexMsg.setMode(mode);
				populationSolrIndexMsg.setObjectType(type);
				if(obj instanceof Long){
					populationSolrIndexMsg.setObjectId((Long) obj);
					activeMQMessageSender.send(populationSolrIndexMsg);
				}
				return;
			}
			Map<String, String> objMap = new HashMap<String, String>();
			objMap.put("dataType", type);
			ActualPopulation actualPopulation = (ActualPopulation) obj;
			objMap.put("dataId", String.valueOf(actualPopulation.getId()));
			objMap.put("baseinfoId", String.valueOf(actualPopulation.getBaseInfoId()));
			objMap.put("addressinfoId", String.valueOf(actualPopulation.getAddressInfoId()));
			objMap.put("logOut", String.valueOf(actualPopulation.getLogOut()));
			objMap.put("idCardNo", actualPopulation.getIdCardNo());
			objMap.put("name", actualPopulation.getName());
			objMap.put("simplepinyin", actualPopulation.getSimplePinyin());
			objMap.put("fullpinyin", actualPopulation.getFullPinyin());
			objMap.put("updateDate", DateUtil.toString(
					actualPopulation.getUpdateDate(), "yyyy-MM-dd HH:mm:ss"));
			objMap.put("orgId", String.valueOf(actualPopulation.getOrganization().getId()));
			objMap.put("orgInternalCode", actualPopulation.getOrgInternalCode());
			PopulationSolrIndexMsg populationSolrIndexMsg = new PopulationSolrIndexMsg();
			populationSolrIndexMsg.setMode(mode);
			populationSolrIndexMsg.setObjectType(type);
			populationSolrIndexMsg.setPopulationMsgMap(objMap);
			activeMQMessageSender.send(populationSolrIndexMsg);
		} catch (Exception e) {
			logger.error("同步统一搜索索引时出错：" + e);
		}
	}
}
