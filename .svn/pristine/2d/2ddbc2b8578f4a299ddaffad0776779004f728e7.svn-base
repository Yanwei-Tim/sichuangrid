package com.tianque.excel.definition;

import com.tianque.core.datatransfer.DataType;
import com.tianque.domain.property.PropertyTypes;

public class InternetBarContext {
	/**
	 * 网吧导出
	 * 序号|对象属性|中文名|属性类型|类型|是否采用默认样式|合并行|合并列|
	 * 
	 * @return
	 */
	public static String[][] getInternetBarInfoArray() {
		String[][] excelColumArray = {
				{ "0", "", "网吧", "", "", "true", "0", "22" },
				{ "0", "placeName", "场所名称", "", "", "true" },
				{ "1", "organization", "所属网格", DataType.DATA_TYPE_ORG, "", "true" },
				{ "2", "placeAddress", "场所地址", "", "", "true" },
				{ "3", "manager", "负责人", "", "", "true" },
				{ "4", "mobile", "联系电话", "", "", "true" },
				{ "5", "netAccessProviders", "宽带接入服务商", "", "", "true" },
				{ "6", "internetAccessType", "接入方式", "", "", "true" },
				{ "7", "barCode", "网吧编号", "", "", "true" },
				{ "8", "operationArea", "营业面积", "", "", "true" },
				{ "9", "useIP", "使用IP", "", "", "true" },
				{ "10", "stationPolice", "所在派出所名称", "", "", "true" },
				{ "11", "tempNetCardNum", "临时上网卡数量", "", "", "true" },
				{ "12", "totalComputerNum", "电脑总数", "", "", "true" },
				{ "13", "netSecurityAuditNo", "网络安全审核意见书号", "", "", "true" },
				{ "14", "netCultureLicenceNo", "网络文化经营许可证号", "", "", "true" },
				{ "15", "fireAuditDocumentNo", "消防审核意见书号", "", "", "true" },
				{ "16", "attentionExtent", "关注程度", DataType.DATA_TYPE_DICT,
						PropertyTypes.ATTENTION_EXTENT, "true" },
				{ "17", "hasServiceTeamMember", "有无治安负责人", DataType.DATA_TYPE_LONG, "", "true" },
				{ "18", "lastRecordTime", "最新巡场记录", DataType.DATA_TYPE_DATE, "", "true" },
				{ "19", "remark", "备注", "", "", "true" },
				{ "20", "isEmphasis", "是否注销", DataType.DATA_TYPE_BOOLEAN, "", "true" },
				{ "21", "logOutTime", "注销时间", DataType.DATA_TYPE_DATE, "", "true" },
				{ "22", "logOutReason", "注销原因", "", "", "true" }};
		return excelColumArray;
	}

	/**
	 * 重点场所-网吧导入
	 * 
	 * @return
	 */
	public static String[][] getInternetBarImportArray() {
		String[][] excelColumArray = {
				{ "0", "placeName", "场所名称", "", "", "true" },
				{ "1", "organization", "所属网格", DataType.DATA_TYPE_ORG, "", "true" },
				{ "2", "manager", "负责人", "", "", "true" },
				{ "3", "mobile", "联系方式", "", "", "true" },
				{ "4", "placeAddress", "场所地址", "", "", "true" },
				{ "5", "stationPolice", "所在地派出所名称", "", "", "true" },
				{ "6", "barCode", "网吧编号", "", "", "true" },
				{ "7", "operationArea", "营业面积", DataType.DATA_TYPE_DOUBLE, "", "true" },
				{ "8", "tempNetCardNum", "临时上网卡数量", DataType.DATA_TYPE_INTEGER, "", "true" },
				{ "9", "totalComputerNum", "电脑总数", DataType.DATA_TYPE_INTEGER, "", "true" },
				{ "10", "netAccessProviders", "宽带接入服务商", "", "", "true" },
				{ "11", "internetAccessType", "宽带接入方式", "", "", "true" },
				{ "12", "useIP", "现使用IP", "", "", "true" },
				{ "13", "netCultureLicenceNo", "网络文化经营许可证号", "", "", "true" },
				{ "14", "netSecurityAuditNo", "网络安全审核意见书号", "", "", "true" },
				{ "15", "fireAuditDocumentNo", "消防审核意见书号", "", "", "true" },
				{ "16", "attentionExtent", "关注程度", DataType.DATA_TYPE_DICT,
						PropertyTypes.ATTENTION_EXTENT, "true" },
				{ "17", "remark", "备注", "", "", "true" }

		};
		return excelColumArray;
	}
}
