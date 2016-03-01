package com.tianque.workBench.myVisitRecord.domain;

import com.tianque.domain.PropertyDict;

public class MyVisitRecord {
	private String type;
	private Integer amount;
	private Integer recordCount;
	private PropertyDict typeName;
	private String eName;
	private String cName;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Integer recordCount) {
		this.recordCount = recordCount;
	}

	public PropertyDict getTypeName() {
		return typeName;
	}

	public void setTypeName(PropertyDict typeName) {
		this.typeName = typeName;
	}

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

}
