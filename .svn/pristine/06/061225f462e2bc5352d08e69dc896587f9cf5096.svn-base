package com.tianque.jms.msg;

import java.util.List;
import java.util.Map;

import com.tianque.jms.constants.JmsMessageType;

public class SolrIndexMsg extends BaseMsg {
	private static final long serialVersionUID = 1L;

	private String searchType;
	private Map<String, String> msgMap;
	private List<String> msgList;

	public SolrIndexMsg(){
		this.msgType = "solrIndex";
		setJmsMessageType(JmsMessageType.SOLR_JMS_TYPE);
	}
	
	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public Map<String, String> getMsgMap() {
		return msgMap;
	}

	public void setMsgMap(Map<String, String> msgMap) {
		this.msgMap = msgMap;
	}

	public List<String> getMsgList() {
		return msgList;
	}

	public void setMsgList(List<String> msgList) {
		this.msgList = msgList;
	}

}
