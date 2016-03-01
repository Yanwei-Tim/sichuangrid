package com.tianque.search.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuickSearchType {

	public static QuickSearchType BY_NAME = new QuickSearchType(1, "姓名");
	public static QuickSearchType BY_IDCARD = new QuickSearchType(2, "身份证");
	public static QuickSearchType BY_CERTIFICATE = new QuickSearchType(4, "证件号码");
	public static List<QuickSearchType> DEFAULT_PERSON_QUICKSEARCH_TYPE = new ArrayList<QuickSearchType>();
	public static List<QuickSearchType> OVERSEA_PERSON_QUICKSEARCH_TYPE = new ArrayList<QuickSearchType>();

	private static Map<Integer, QuickSearchType> allTypes = new HashMap<Integer, QuickSearchType>();

	static {
		allTypes.put(BY_NAME.getCode(), BY_NAME);
		allTypes.put(BY_IDCARD.getCode(), BY_IDCARD);
		allTypes.put(BY_CERTIFICATE.getCode(), BY_CERTIFICATE);

		DEFAULT_PERSON_QUICKSEARCH_TYPE.add(BY_IDCARD);
		DEFAULT_PERSON_QUICKSEARCH_TYPE.add(BY_NAME);

		OVERSEA_PERSON_QUICKSEARCH_TYPE.add(BY_CERTIFICATE);
		OVERSEA_PERSON_QUICKSEARCH_TYPE.add(BY_NAME);
	}

	public static QuickSearchType parseQuickSearchType(int code) {
		if (!allTypes.containsKey(code)) {
			throw new IllegalArgumentException("未知的查询方式");
		}
		return allTypes.get(code);
	}

	private int code;
	private String displayName;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	private QuickSearchType(int code, String displayName) {
		this.code = code;
		this.displayName = displayName;
	}

	public String toString() {
		return getDisplayName();
	}

}
