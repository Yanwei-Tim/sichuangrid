package com.tianque.domain.vo;

public class MentalPatientDetailCounts {
	private String name;
	private Long amount;
	private Long helped;
	private Long noHelp;

	public Long getNoHelp() {
		return noHelp;
	}

	public void setNoHelp(Long noHelp) {
		this.noHelp = noHelp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getHelped() {
		return helped;
	}

	public void setHelped(Long helped) {
		this.helped = helped;
	}
}
