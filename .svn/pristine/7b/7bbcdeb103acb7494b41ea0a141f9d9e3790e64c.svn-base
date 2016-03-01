package com.tianque.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

public class BasisStat {

	private Long id;
	private String statisticType;
	private Long countNum;
	private String scale;
	private Date startDate;
	private Date endDate;

	public BasisStat() {
	}

	public BasisStat(Long id, String statisticType, Long countNum, String scale, Date startDate,
			Date endDate) {
		this.id = id;
		this.statisticType = statisticType;
		this.countNum = countNum;
		this.scale = scale;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatisticType() {
		return statisticType;
	}

	public void setStatisticType(String statisticType) {
		this.statisticType = statisticType;
	}

	public Long getCountNum() {
		return countNum;
	}

	public void setCountNum(Long countNum) {
		this.countNum = countNum;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
