package com.tianque.excel.definition;

import com.tianque.core.datatransfer.DataType;
import com.tianque.domain.property.PropertyTypes;

public class HospitalContext {
	/**
	 * 医院导出
	 * 序号|对象属性|中文名|属性类型|类型|是否采用默认样式|合并行|合并列|
	 * 
	 * @return
	 */
	public static String[][] getHospitalInfoArray() {
		String[][] excelColumArray = {
				{ "0", "", "医院", "", "", "true", "0", "22" },
				{ "0", "hospitalName", "医院名称", "", "", "true" },
				{ "1", "organization", "所属网格", DataType.DATA_TYPE_ORG, "", "true" },
				{ "2", "kind", "医院性质", DataType.DATA_TYPE_DICT,
					PropertyTypes.HOSPITALS_KIND, "true" },
				{ "3", "address", "医院地址", "", "", "true" },
				{ "4", "type", "医院类型", DataType.DATA_TYPE_DICT,
					PropertyTypes.HOSPITALS_TYPE, "true" },
				{ "5", "director", "医院院长", "", "", "true" },
				{ "6", "affiliatedUnit", "所属单位", "", "", "true" },
				{ "7", "telephone", "联系电话", "", "", "true" },
				{ "8", "mobileNumber", "联系手机", "", "", "true" },
				{ "9", "email", "电子邮箱", "", "", "true" },
				{ "10", "fax", "传真", "", "", "true" },
				{ "11", "attentionExtent", "关注程度", DataType.DATA_TYPE_DICT,
						PropertyTypes.ATTENTION_EXTENT, "true" },
				{ "12", "hasServiceTeamMember", "有无治安负责人", DataType.DATA_TYPE_LONG, "", "true" },
				{ "13", "lastRecordTime", "最新巡场记录", DataType.DATA_TYPE_DATE, "", "true" },
				{ "14", "remark", "备注", "", "", "true" },
				{ "15", "isEmphasis", "是否注销", DataType.DATA_TYPE_BOOLEAN, "", "true" },
				{ "16", "logOutTime", "注销时间", DataType.DATA_TYPE_DATE, "", "true" },
				{ "17", "logOutReason", "注销原因", "", "", "true" }};
		return excelColumArray;
	}
}
