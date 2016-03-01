package com.tianque.report.vo;

public class IssueInvestigationMediateItemVo {
	private String name;
	private Long city;
	private Long district;
	private Long town;
	private Long grid;
	private Long subtotal;
	private Long annualTotal;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCity() {
		return city;
	}

	public void setCity(Long city) {
		this.city = city;
	}

	public Long getDistrict() {
		return district;
	}

	public void setDistrict(Long district) {
		this.district = district;
	}

	public Long getTown() {
		return town;
	}

	public void setTown(Long town) {
		this.town = town;
	}

	public Long getGrid() {
		return grid;
	}

	public void setGrid(Long grid) {
		this.grid = grid;
	}

	public Long getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Long subtotal) {
		this.subtotal = subtotal;
	}

	public Long getAnnualTotal() {
		return annualTotal;
	}

	public void setAnnualTotal(Long annualTotal) {
		this.annualTotal = annualTotal;
	}

}
