package com.tianque.domain.vo;

public class Points {
	public static final double NORMAL = 0;
	public static final double YELLOW_CARD = 5.0;
	public static final double RED_CARD = 10.0;

	public static String toString(int points) {
		switch (points) {
		case 0:
			return "普通督办";
		case 5:
			return "黄牌督办";
		case 10:
			return "红牌督办";
		default:
			return "";
		}
	}

	public static String doublePointToString(Double points) {
		if (points.equals(new Double(0.0))) {
			return "普通督办";
		} else if (points.equals(new Double(5.0))) {
			return "黄牌督办";
		} else if (points.equals(new Double(10.0))) {
			return "红牌督办";
		} else {
			return "";
		}
	}
}
