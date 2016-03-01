package com.tianque.excel.definition;

import com.tianque.core.datatransfer.DataType;
import com.tianque.domain.property.PropertyTypes;

public class DangerousChemicalUnitContext {

	// 危险化学品导出
	public static String[][] getDangerousChemicalsUnitArray() {
		String[][] excelColumArray = {
				{ "0", "", "危险化学品单位表", "", "", "true", "0", "18" },
				{ "0", "unitName", "单位名称", "", "", "true" },
				{ "1", "organization", "所属网格", DataType.DATA_TYPE_ORG, "", "true" },
				{ "2", "unitAddress", "单位地址", "", "", "true" },
				{ "3", "superintendent", "负责人", "", "", "true" },
				{ "4", "telephone", "联系电话", "", "", "true" },
				{ "5", "unitType", "单位类别", "", "", "true" },
				{ "6", "commodityType", "货物类别", "", "", "true" },
				{ "7", "copyScope", "副本许可范围", "", "", "true" },
				{ "8", "storageDevice", "存储设备", "", "", "true" },
				{ "9", "isEmphasis", "是否注销", DataType.DATA_TYPE_BOOLEAN, "", "true" },
				{ "10", "logOutReason", "注销原因", "", "", "true" },
				{ "11", "logOutTime", "注销时间", DataType.DATA_TYPE_DATE, "", "true" },
				{ "12", "attentionExtent", "关注程度", DataType.DATA_TYPE_DICT,
						PropertyTypes.ATTENTION_EXTENT, "true" },
				{ "13", "hasServiceTeamMember", "有无治安负责人", DataType.DATA_TYPE_LONG, "", "true" },
				{ "14", "lastRecordTime", "最新巡场记录", DataType.DATA_TYPE_DATE, "", "true" },
				{ "15", "remark", "备注", "", "", "true" },
				{ "16", "isEmphasis", "是否注销", DataType.DATA_TYPE_BOOLEAN, "", "true" },
				{ "17", "logOutTime", "注销时间", DataType.DATA_TYPE_DATE, "", "true" },
				{ "18", "logOutReason", "注销原因", "", "", "true" }

		};
		return excelColumArray;
	}

	public static String[][] getDangerousChemicalsImportArray() {
		String[][] excelColumArray = {
				{ "0", "unitName", "单位名称", "", "", "true" },
				{ "1", "organization", "所属网格", DataType.DATA_TYPE_ORG, "", "true" },
				{ "2", "unitAddress", "单位地址", "", "", "true" },
				{ "3", "superintendent", "负责人", "", "", "true" },
				{ "4", "telephone", "联系电话", "", "", "true" },
				{ "5", "unitType", "单位类别", "", "", "true" },
				{ "6", "commodityType", "货物类别", "", "", "true" },
				{ "7", "copyScope", "副本许可范围", "", "", "true" },
				{ "8", "storageDevice", "存储设备", "", "", "true" },
				{ "9", "isEmphasis", "是否注销", DataType.DATA_TYPE_BOOLEAN, "", "true" },
				{ "10", "logOutReason", "注销原因", "", "", "true" },
				{ "11", "logOutTime", "注销时间", DataType.DATA_TYPE_DATE, "", "true" },
				{ "12", "attentionExtent", "关注程度", DataType.DATA_TYPE_DICT,
						PropertyTypes.ATTENTION_EXTENT, "true" },
				{ "13", "remark", "备注", "", "", "true" }

		};
		return excelColumArray;
	}

}
