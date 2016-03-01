package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

public class BasicOrgType extends BaseProperty {
	public final static int PERMARY_ORGANIZATION = 0;
	public final static int PERMARY_PARTY = 1;
	public final static int AUTONOMY_ORG = 2;
	public final static int MASS_TREAT_TEAM = 3;
	public final static int VOLUNTARY_TEAM = 4;
	public final static int LEADER_GROUP = 5;
	public final static int GRIDMANAGEMENT_TEAM = 6;
	public final static int OTHER = 7;
	public final static int BASICLEVEL_PARTY = 8;
	public final static int DEPARTMENT_PARTY = 9;
	public final static int GOVERNMENT_DEPARTMENT = 10;
	public final static int MASS_ORGANIZATION = 11;

	/** 新增社会组织 */
	public final static int SOCIALORGANIZATION = 12;
	/** 新增非公有制经济组织 */
	public final static int NEWECONOMICORGANIZATIONS = 13;

	/** 群防群治小类 平安志愿者 */
	public final static String POSTULANTDUTY = "平安志愿者";
	public final static String ORGSCOPE_SAMEGRADE = "sameGrade";
	public final static String ORGSCOPE_ALLURISDICTION = "allJurisdiction";
	public final static String ORGSCOPE_DIRECTJURISDICTION = "directJurisdiction";
	public final static String ORGSCOPE_FUNCTIONAL = "functional";// 职能部门
	/** 综治办 */
	public final static String COMPREHENSIVE_KEY = "comprehensive";
	/** 综治委 */
	public final static String COMMISSION_KEY = "commission";
	/** 综治成员单位 */
	public final static String COMMISSIONMEMBER_KEY = "commissionMember";

	private static Map<Integer, String> positionRelation = new HashMap<Integer, String>();

	public final static String ZOGNZHIBAN = "综治办";// 综治办
	public final static String ZOGNZHIWEI = "综治委";// 综治委
	public final static String ZONGZHICHENGYUANDANWEI = "综治成员单位";// 综治成员单位
	public final static String ZHUANXIANGGONGZUOLINGDAOXIAOZU = "专项工作领导小组";// 专项工作领导小组

	public final static String DEPARTMENTPARTY = "部门党委";// 部门党委
	public final static String BASICLEVELPARTY = "基层党委";// 基层党组织
	public final static String AUTONOMYORG = "基层自治组织";// 基层自治组织
	public final static String MASSTREATTEAM = "群防群治队伍";// 群防群治队伍
	public final static String LEADERGROUP = "社会志愿者队伍";// 专项工作领导小组

	static {
		positionRelation.put(PERMARY_ORGANIZATION, PropertyTypes.COMPOSITEDUTY);
		positionRelation.put(PERMARY_PARTY, PropertyTypes.PARTYDUTY);
		positionRelation.put(AUTONOMY_ORG, PropertyTypes.COMPOSITEDUTY);
		positionRelation.put(MASS_TREAT_TEAM, PropertyTypes.MASSESDUTY);
		positionRelation.put(VOLUNTARY_TEAM, PropertyTypes.POSTULANTDUTY);
		positionRelation.put(LEADER_GROUP, PropertyTypes.LEADERGROUPDUTY);
		positionRelation.put(GRIDMANAGEMENT_TEAM,
				PropertyTypes.GRIDMANAGEMENTTEAMDUTY);
		positionRelation.put(OTHER, PropertyTypes.LEADERGROUPDUTY);

		positionRelation.put(BASICLEVEL_PARTY,
				PropertyTypes.BASICLEVELPARTYDUTY);
		positionRelation.put(DEPARTMENT_PARTY,
				PropertyTypes.DEPARTMENTPARTYDUTY);
		positionRelation.put(GOVERNMENT_DEPARTMENT,
				PropertyTypes.GOVERNMENTDEPARTMENTDUTY);
		positionRelation.put(MASS_ORGANIZATION, PropertyTypes.MASSESDUTY);

		positionRelation.put(SOCIALORGANIZATION,
				PropertyTypes.SOCIALORGANIZATION);
		positionRelation.put(NEWECONOMICORGANIZATIONS,
				PropertyTypes.NEWECONOMICORGANIZATIONS);

	}

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(PERMARY_ORGANIZATION, "综治组织"));
		properties.add(getInternalProperty(PERMARY_PARTY, "基层党组织"));
		properties.add(getInternalProperty(AUTONOMY_ORG, "基层自治组织"));
		properties.add(getInternalProperty(MASS_TREAT_TEAM, "群防群治队伍"));
		properties.add(getInternalProperty(VOLUNTARY_TEAM, "社会志愿者队伍"));
		properties.add(getInternalProperty(LEADER_GROUP, "专项工作领导小组"));
		properties.add(getInternalProperty(GRIDMANAGEMENT_TEAM, "网格化管理服务团队"));
		properties.add(getInternalProperty(OTHER, "其他"));
		properties.add(getInternalProperty(BASICLEVEL_PARTY, "基层党委"));
		properties.add(getInternalProperty(DEPARTMENT_PARTY, "部门党委"));
		properties.add(getInternalProperty(GOVERNMENT_DEPARTMENT, "政府部门"));
		properties.add(getInternalProperty(MASS_ORGANIZATION, "群团组织"));
		properties.add(getInternalProperty(SOCIALORGANIZATION, "社会组织"));
		properties
				.add(getInternalProperty(NEWECONOMICORGANIZATIONS, "非公有制经济组织"));
		return properties;
	}

	public static String getPositionDemainName(int internalId) {
		return positionRelation.get(internalId);
	}
}
