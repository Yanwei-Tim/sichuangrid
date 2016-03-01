package com.tianque.state;

public class IssueDealType {
	public static final Long Add = 0L; // 新增
	public static final Long CONCEPT = 1L;// 受理/
	public static final Long COMPLETE = 2L;// 完成
	public static final Long SEND_BACK = 3L; // 回退/
	public static final Long SUBMIT_FORWARD = 4L; // 上报
	public static final Long ASSIGN = 5L; // 交办
	public static final Long COMMENT = 6L;// 处理中/
	public static final Long DISPATCH = 7L;
	public static final Long TELL = 8L; // 抄告/

	public static final Long URGENT = 9L;// 加急

	public static final Long NORMAL = 10L;// 督办
	public static final Long YELLOW_CARD = 11L;// 黄牌督办
	public static final Long RED_CARD = 12L;// 红牌督办

	public static final Long READ = 13L;// 阅读
	public static final Long HISTORICALISSUE = 14L;// 历史遗留
	public static final Long CANCELSUPERVISE = 15L;// 取消督办
	public static final Long COMMAND = 16L; // 领导批示
	public static final Long PUBLICLTYCASS = 17L;// 宣传案列
	public static final Long CANCELURGENT = 18L;// 取消加急
	public static final Long CANCELHISTORICALISSUE = 19L;// 取消历史遗留
	public static final Long CANCELPUBLICLTYCASS = 20L;// 取消宣传案例

	public static final Long ASSIGN_TO_ADMIN = 1001L;
	public static final Long ASSIGN_TO_FUNCTION = 1002L;

	public static final Long SUBMIT_FORWARD_ADMIN_TO_ADMIN = 1011L;
	public static final Long SUBMIT_FORWARD_ADMIN_TO_FUNCTION = 1012L;
	public static final Long SUBMIT_FORWARD_FUNCTION_TO_FUNCTION = 1013L;
	public static final Long SUBMIT_FORWARD_FUNCTION_TO_ADMIN = 1014L;
	public static final Long TELL_SUBMIT_FORWARD_ADMIN_TO_FUNCTION = 1021L; // 综治上报职能部门时伴随的抄告
	public static final Long TELL_SUBMIT_FORWARD_ADMIN_TO_ADMIN = 1022L; // 综治上报综治时伴随的抄告
	public static final Long TELL_ASSIGN_TO_FUNCTION = 1023L; // 交办时伴随的抄告

	public static String toString(int typeValue) {
		switch (typeValue) {
		case 0:
			return "新增";
		case 1:
			return "受理";
		case 2:
			return "完成";
		case 3:
			return "回退";
		case 4:
			return "上报";
		case 5:
			return "交办";
		case 6:
			return "意见";
		case 7:
			return "派单";
		case 8:
			return "抄告";
		case 9:
			return "加急";
		case 10:
			return "普通督办";
		case 11:
			return "黄牌督办";
		case 12:
			return "红牌督办";
		case 13:
			return "阅读";
		case 14:
			return "历史遗留";
		case 15:
			return "取消督办";
		case 16:
			return "领导批示";
		case 17:
			return "宣传案例";
		case 18:
			return "取消加急";
		case 19:
			return "取消历史遗留";
		case 20:
			return "取消宣传案例";
		default:
			return "";
		}
	}
}
