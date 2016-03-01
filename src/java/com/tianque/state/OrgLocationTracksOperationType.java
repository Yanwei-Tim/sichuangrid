package com.tianque.state;

public class OrgLocationTracksOperationType {
	public static final Integer ADDT = 1;// 录入
	public static final Integer MOVE = 2; // 转移
	public static final Integer TO = 3; // 迁入
	public static final Integer FROM = 4; // 迁出
	public static final Integer CANCEL_ATTENTION = 5; // 取消关注
	public static final Integer AGAIN_ATTENTION = 6; // 重新关注
	public static final Integer DELETE = 7;// 删除

	public static String toString(int typeValue) {
		switch (typeValue) {
		case 1:
			return "录入";
		case 2:
			return "转移";
		case 3:
			return "迁入";
		case 4:
			return "迁出";
		case 5:
			return "取消关注";
		case 6:
			return "重新关注";
		case 7:
			return "删除";
		default:
			return "";
		}
	}
}
