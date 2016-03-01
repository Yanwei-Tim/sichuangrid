package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;
import com.tianque.issue.constants.IssueTypes;

/** 矛盾纠纷事件类型 */
public class Contradiction extends BaseProperty {
	public final static String CONTRADICTION_KEY = IssueTypes.CONTRADICTION;

	public final static int NATIONAL_RELIGION = 1;
	public final static int DEMOBILIZE_RELOCATE = 2;
	public final static int RELOCATE = 3;
	public final static int ESTATE_MANAGEMENT = 4;
	public final static int BOUNDARY_LINE = 5;
	public final static int ECONOMIES = 6;
	public final static int LABOUR_TROUBLE = 7;
	public final static int RESTRUCTURING_OF_ENTERPRISE = 8;
	public final static int ENVIRONMENTAL_POLLUTION = 9;
	public final static int JUDICIAL_ACTIVITY = 10;
	public final static int EXECUTIVE_LAW_ENFORCEMENT = 11;
	public final static int ACADEMIC_DISCIPLINES = 12;
	public final static int MARITIME_AFFAIRS = 13;
	public final static int WORK_STYLE_OF_CADRE = 14;
	public final static int VILLAGE_MANAGE = 15;
	public final static int THREE_CAPITAL = 16;
	public final static int MATRIMONY = 17;
	public final static int MEDICAL_TREATMENT = 18;
	public final static int ORTHER = 19;
	/** 矛盾纠纷类型下面事件子类型 */
	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(NATIONAL_RELIGION, "民族宗教矛盾纠纷"));
		properties.add(getInternalProperty(DEMOBILIZE_RELOCATE, "军转干部、复员退伍军人安置引发的矛盾纠纷"));
		properties.add(getInternalProperty(RELOCATE, "征地拆迁安置引发的矛盾纠纷"));
		properties.add(getInternalProperty(ESTATE_MANAGEMENT, "建筑工程质量、物业管理等问题引发的矛盾纠纷"));
		properties.add(getInternalProperty(BOUNDARY_LINE, "土地矿产山林水利界线权属纠纷"));
		properties.add(getInternalProperty(ECONOMIES, "经济活动引发的矛盾纠纷"));
		properties.add(getInternalProperty(LABOUR_TROUBLE, "劳资纠纷"));
		properties.add(getInternalProperty(RESTRUCTURING_OF_ENTERPRISE, "企业改制引发的矛盾纠纷"));
		properties.add(getInternalProperty(ENVIRONMENTAL_POLLUTION, "环境污染、生态破坏引发的矛盾纠纷"));
		properties.add(getInternalProperty(JUDICIAL_ACTIVITY, "司法活动引发的矛盾纠纷"));
		properties.add(getInternalProperty(EXECUTIVE_LAW_ENFORCEMENT, "行政执法活动引发的矛盾纠纷"));
		properties.add(getInternalProperty(ACADEMIC_DISCIPLINES, "大中专院校、中小学校引发的矛盾纠纷"));
		properties.add(getInternalProperty(MARITIME_AFFAIRS, "海事渔事纠纷"));
		properties.add(getInternalProperty(WORK_STYLE_OF_CADRE, "干部作风等问题引发的矛盾纠纷"));
		properties.add(getInternalProperty(VILLAGE_MANAGE, "村（社区、居）务管理引发的矛盾纠纷"));
		properties.add(getInternalProperty(THREE_CAPITAL, "农村土地承包、农村集体“三资”及农民负担等问题引发的矛盾纠纷"));
		properties.add(getInternalProperty(MATRIMONY, "婚姻家庭邻里等各类民间纠纷"));
		properties.add(getInternalProperty(MEDICAL_TREATMENT, "医患纠纷"));
		properties.add(getInternalProperty(ORTHER, "其他"));
		return properties;
	}

}
