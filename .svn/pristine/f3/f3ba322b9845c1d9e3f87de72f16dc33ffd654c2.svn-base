package com.tianque.jms.solr;

import com.tianque.jms.OperateMode;

public class SolrMessage {
	private Long id;
	private String type;
	private String mode;
	private String idCardNo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public SolrMessage(String idCardNo, String type) {
		this.idCardNo = idCardNo;
		this.mode = OperateMode.DELETE.toString();
		this.type = type;
	}

	public SolrMessage(Long id, String type, String mode) {
		this.id = id;
		this.type = type;
		this.mode = mode;
	}

	public SolrMessage() {

	}
}
