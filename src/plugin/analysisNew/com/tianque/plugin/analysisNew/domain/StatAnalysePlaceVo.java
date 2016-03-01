package com.tianque.plugin.analysisNew.domain;

import java.io.Serializable;

import com.tianque.domain.Organization;

public class StatAnalysePlaceVo implements Serializable {

	private int total;
	// private int practicalPlace;
	// private int noPracticalPlace;
	private Organization organization;
	// 百分比
	private double percentage;

	private long helped;// 已帮教数量
	private long noHelp; // 未帮教数量

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public long getHelped() {
		return helped;
	}

	public void setHelped(long helped) {
		this.helped = helped;
	}

	public long getNoHelp() {
		return noHelp;
	}

	public void setNoHelp(long noHelp) {
		this.noHelp = noHelp;
	}

}
