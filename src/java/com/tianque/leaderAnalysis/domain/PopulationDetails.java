package com.tianque.leaderAnalysis.domain;

import java.io.Serializable;

public class PopulationDetails implements Serializable {
	private String type;
	private int count;
	private int monthCreate;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getMonthCreate() {
		return monthCreate;
	}

	public void setMonthCreate(int monthCreate) {
		this.monthCreate = monthCreate;
	}

}
