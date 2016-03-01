package com.tianque.plugin.judgmentAnalysis.constants;

public enum DimensionCombinationMode {
	HIS("历史", "h"), RealTime("准实时", "rt");

	public String name;
	public String value;

	private DimensionCombinationMode() {
	}

	private DimensionCombinationMode(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
