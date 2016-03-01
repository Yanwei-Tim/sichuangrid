package com.tianque.cache;

public enum CacheNameSpaceEnum {
	PEOPLE_DEFAULTPAGE("PEOPLE_DEFAULTPAGE"), DEFAULT("DEFAULT_CACHE_KEY");

	private final String key;

	private CacheNameSpaceEnum(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer("TQ_SCGRID_");
		stringBuffer.append(this.key);
		stringBuffer.append("_");
		return stringBuffer.toString();
	}
}
