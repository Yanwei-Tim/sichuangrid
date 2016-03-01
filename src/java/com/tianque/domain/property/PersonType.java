package com.tianque.domain.property;

public enum PersonType {

	RESIDENT("0"),

	FLOATINGPOPULATION("1"),

	UNSETTLEDPOPULATION("2"),

	OVERSEAPERSONNEL("3");

	private String code;

	private PersonType(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}
}
