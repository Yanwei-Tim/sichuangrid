package com.tianque.plugin.tqSearch.syncSolrIndex;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.jms.OperateMode;
import com.tianque.jms.msg.SolrIndexMsg;
import com.tianque.jms.sender.ActiveMQMessageSender;
import com.tianque.plugin.tqSearch.sqlMap.SolrSqlMap;

@Component("syncSolrIndexForMQ")
public class SyncSolrIndexForMQ {
	@Autowired
	private ActiveMQMessageSender activeMQMessageSender;

	public void sendMessage(Object send, OperateMode mode) {
		sendMessage(send, mode,"");
	}
	
	public void sendMessage(Object send, OperateMode mode,String key) {
		SolrIndexMsg solrIndexMsg = new SolrIndexMsg();
		solrIndexMsg.setMode(mode);
		if(OperateMode.EDIT.equals(mode)){
			convertEditMapMessage(solrIndexMsg,send,key);
		}else if(OperateMode.DELETE.equals(mode)){
			convertDeleteMapMessage(solrIndexMsg,send,key);
		}
		activeMQMessageSender.send(solrIndexMsg);
	}
	
	private void convertDeleteMapMessage(SolrIndexMsg solrIndexMsg,Object sendObject,String key){
		SolrSqlMap sqlMap = SolrSqlMap.getDeleteEnum(key);
		if(sqlMap!=null){
			solrIndexMsg.setSearchType(sqlMap.getSearchType());
			List<String> deleteVo = SolrSqlMap.getDeleteVo(sqlMap.getKey(), sendObject);
			solrIndexMsg.setMsgList(deleteVo);
		}
	}
	
	private void convertEditMapMessage(SolrIndexMsg solrIndexMsg,Object sendObject,String key){
		SolrSqlMap sqlMap = SolrSqlMap.getUpdateEnum(sendObject,key);
		if(sqlMap!=null){
			solrIndexMsg.setSearchType(sqlMap.getSearchType());
			Map<String,String> sendMap = SolrSqlMap.getUpdateVo(sqlMap.getKey(), sendObject);
			solrIndexMsg.setMsgMap(sendMap);
		}
	}
}
