package com.tianque.domain;

import com.tianque.core.base.BaseDomain;

public class PlantFormMessageConfig extends BaseDomain {
	private String tableName;
	private String condition1;
	private String condition2;
	private String condition3;
	private String condition4;
	private String condition5;
	private String condition6;
	private String msgTitle;
	private String msgContent;

	public String getCondition1() {
		return condition1;
	}

	public void setCondition1(String condition1) {
		this.condition1 = condition1;
	}

	public String getCondition2() {
		return condition2;
	}

	public void setCondition2(String condition2) {
		this.condition2 = condition2;
	}

	public String getCondition3() {
		return condition3;
	}

	public void setCondition3(String condition3) {
		this.condition3 = condition3;
	}

	public String getCondition4() {
		return condition4;
	}

	public void setCondition4(String condition4) {
		this.condition4 = condition4;
	}

	public String getCondition5() {
		return condition5;
	}

	public void setCondition5(String condition5) {
		this.condition5 = condition5;
	}

	public String getCondition6() {
		return condition6;
	}

	public void setCondition6(String condition6) {
		this.condition6 = condition6;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getMsgTitle() {
		return msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

}
