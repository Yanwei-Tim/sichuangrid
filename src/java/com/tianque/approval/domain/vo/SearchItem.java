package com.tianque.approval.domain.vo;

import java.io.Serializable;

public class SearchItem implements Serializable {

	/**
	 * 事项编号
	 */
	private String itemNumber;
	/**
	 * 事项名称
	 */
	private String itemName;
	/** 快速检索关键字 */
	private String fastSearchKeyWords;

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getFastSearchKeyWords() {
		return fastSearchKeyWords;
	}

	public void setFastSearchKeyWords(String fastSearchKeyWords) {
		this.fastSearchKeyWords = fastSearchKeyWords;
	}

}
