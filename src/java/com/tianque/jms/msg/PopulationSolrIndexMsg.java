package com.tianque.jms.msg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tianque.jms.OperateMode;
import com.tianque.jms.constants.JmsMessageType;

public class PopulationSolrIndexMsg extends BaseMsg  {
	private static final long serialVersionUID = 1L;
	
	private Long baseinfoId;
	private List<Long> deleteIds;
	private Long importStartDate;
	private Map<String,HashMap<String,Long>> importIdRange;
	private Map<String,String> populationMsgMap;

	public PopulationSolrIndexMsg(){
		this.msgType = "populatonSolrIndex";
		setJmsMessageType(JmsMessageType.POPULATIONSOLRSEARCH_JMS_TYPE);
	}
	
	public PopulationSolrIndexMsg(String objectType,Long objectId,OperateMode mode){
		this.msgType = "populatonSolrIndex";
		this.objectType = objectType;
		this.objectId = objectId;
		this.mode = mode;
	}
	
	public Long getBaseinfoId() {
		return baseinfoId;
	}

	public void setBaseinfoId(Long baseinfoId) {
		this.baseinfoId = baseinfoId;
	}

	public List<Long> getDeleteIds() {
		return deleteIds;
	}

	public void setDeleteIds(List<Long> deleteIds) {
		this.deleteIds = deleteIds;
	}

	public Long getImportStartDate() {
		return importStartDate;
	}

	public void setImportStartDate(Long importStartDate) {
		this.importStartDate = importStartDate;
	}

	public Map<String, HashMap<String, Long>> getImportIdRange() {
		return importIdRange;
	}

	public void setImportIdRange(Map<String, HashMap<String, Long>> importIdRange) {
		this.importIdRange = importIdRange;
	}

	public Map<String, String> getPopulationMsgMap() {
		return populationMsgMap;
	}

	public void setPopulationMsgMap(Map<String, String> populationMsgMap) {
		this.populationMsgMap = populationMsgMap;
	}
	
}
