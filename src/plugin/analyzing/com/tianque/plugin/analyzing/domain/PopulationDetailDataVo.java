package com.tianque.plugin.analyzing.domain;

import java.io.Serializable;

public class PopulationDetailDataVo implements Serializable{

	private String name;
	private Integer amount;
	/** 百分比 */
	private double amountPercentage;
	private boolean showLink = true;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public double getAmountPercentage() {
		return amountPercentage;
	}

	public void setAmountPercentage(double amountPercentage) {
		this.amountPercentage = amountPercentage;
	}

	public boolean isShowLink() {
		if (amount == 0) {
			return false;
		}
		return showLink;
	}

	public void setShowLink(boolean showLink) {
		this.showLink = showLink;
	}

}
