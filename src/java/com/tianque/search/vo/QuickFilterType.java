package com.tianque.search.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuickFilterType implements Serializable {

	public static QuickFilterType ALL = new QuickFilterType(-1, "全部");
	public static QuickFilterType INTERESTING_NOW = new QuickFilterType(1, "现在关注");
	public static QuickFilterType INTERESTING_BEFOR = new QuickFilterType(2, "曾经关注");
	public static QuickFilterType LIVING = new QuickFilterType(51, "正常");
	public static QuickFilterType DEADED = new QuickFilterType(52, "死亡");
	public static QuickFilterType REGISTED = new QuickFilterType(101, "正常");
	public static QuickFilterType UN_REGISTER = new QuickFilterType(102, "已注销");
	public static QuickFilterType TRUE = new QuickFilterType(201, "是");
	public static QuickFilterType FALSE = new QuickFilterType(202, "否");
	public static List<QuickFilterType> DEFAULT_IMPORTANTPERSON_FILTER_TYPES = new ArrayList<QuickFilterType>();
	public static List<QuickFilterType> DEFAULT_ACTUAL_POPULATION_FILTER_TYPES = new ArrayList<QuickFilterType>();
	public static List<QuickFilterType> DEFAULT_DEATH_STATE_FILTER_TYPES = new ArrayList<QuickFilterType>();
	public static List<QuickFilterType> DEFAULT_BOOLEAN_STATE_FILTER_TYPES = new ArrayList<QuickFilterType>();

	private static Map<Integer, QuickFilterType> allTypes = new HashMap<Integer, QuickFilterType>();

	private static void register(QuickFilterType type) {
		allTypes.put(type.getCode(), type);
	}

	static {
		register(ALL);
		register(INTERESTING_NOW);
		register(INTERESTING_BEFOR);

		DEFAULT_IMPORTANTPERSON_FILTER_TYPES.add(ALL);
		DEFAULT_IMPORTANTPERSON_FILTER_TYPES.add(INTERESTING_NOW);
		DEFAULT_IMPORTANTPERSON_FILTER_TYPES.add(INTERESTING_BEFOR);

		DEFAULT_ACTUAL_POPULATION_FILTER_TYPES.add(ALL);
		DEFAULT_ACTUAL_POPULATION_FILTER_TYPES.add(REGISTED);
		DEFAULT_ACTUAL_POPULATION_FILTER_TYPES.add(UN_REGISTER);

		DEFAULT_DEATH_STATE_FILTER_TYPES.add(ALL);
		DEFAULT_DEATH_STATE_FILTER_TYPES.add(LIVING);
		DEFAULT_DEATH_STATE_FILTER_TYPES.add(DEADED);

		DEFAULT_BOOLEAN_STATE_FILTER_TYPES.add(ALL);
		DEFAULT_BOOLEAN_STATE_FILTER_TYPES.add(TRUE);
		DEFAULT_BOOLEAN_STATE_FILTER_TYPES.add(FALSE);
	}

	public static QuickFilterType parseFilterType(int code) {
		if (!allTypes.containsKey(code)) {
			throw new IllegalArgumentException("未知的过滤方式");
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

	public QuickFilterType() {
		this(-100, "");
	}

	private QuickFilterType(int code, String displayName) {
		this.code = code;
		this.displayName = displayName;
	}

	public String toString() {
		return getDisplayName();
	}

	public Boolean toBoolean() {
		switch (code) {
		case -1:
			return null;
		case 1:
			return Boolean.TRUE;
		case 2:
			return Boolean.FALSE;
		case 51:
			return Boolean.FALSE;
		case 52:
			return Boolean.TRUE;
		case 101:
			return Boolean.FALSE;
		case 102:
			return Boolean.TRUE;
		case 201:
			return Boolean.TRUE;
		case 202:
			return Boolean.FALSE;
		default:
			return Boolean.FALSE;
		}
	}

}
