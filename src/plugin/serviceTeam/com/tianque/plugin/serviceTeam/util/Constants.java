package com.tianque.plugin.serviceTeam.util;

import com.tianque.core.property.BaseProperty;

public interface Constants {

	/**
	 * 服务团队的类型
	 */
	class ServiceTeamPropertyTypes extends BaseProperty {
		public final static String TEAM = "服务团队类型-插件";
		public final static String ENTERTAINMENT = "文化娱乐";
		public final static String LEGAL_SERVICES = "法律服务";
		public final static String SAFE_CONSTRUCTION = "安全建设";
		public final static String PEOPLES_LIVELIHOOD = "民生服务";
		public final static String CONFLICT_MEDIATION = "矛盾调解";
		public final static String SECURITY_GUARD = "治安巡防";
		public final static String MEDICAL_SERVICES = "医疗服务";
		public final static String MASS_TREAT_TEAM = "群防群治队伍";
		public final static String OTHER = "其他";
		public final static Integer ENTERTAINMENT_ID = 0;
		public final static Integer LEGAL_SERVICES_ID = 1;
		public final static Integer SAFE_CONSTRUCTION_ID = 2;
		public final static Integer PEOPLES_LIVELIHOOD_ID = 3;
		public final static Integer CONFLICT_MEDIATION_ID = 4;
		public final static Integer SECURITY_GUARD_ID = 5;
		public final static Integer MEDICAL_SERVICES_ID = 6;
		public final static Integer MASS_TREAT_TEAM_ID = 7;
		public final static Integer OTHERN = 8;
	}

	/**
	 * 团队职务类型
	 */
	class ServiceTeamPositionPropertyTypes extends BaseProperty {
		public final static String TEAMPOSITION = "服务团队职务类型-插件";
		public final static String TEAMLEADER = "队长";
		public final static String DEPUTYCAPTAIN = "副队长";
		public final static String TEAMMEMBER = "队员";
		public final static Integer TEAMLEADERID = 0;
		public final static Integer DEPUTYCAPTAINID = 1;
		public final static Integer TEAMMEMBERID = 2;
	}

	class TeamMember {
		public final static Long serviceMember = 1l;// 团队成员
		public final static Long guarderMember = 0l;// 监护人
	}

	class OnDudy {
		public final static Long ONDUDY = 1l;// 关注
		public final static Long UNDUDY = 0l;// 非关注（注销）
	}

	class TeamLogout {
		public final static Long LOGOUT = 1l;// 注销
		public final static Long NOT_LOGOUT = 0l;// 非注销

	}

	class SourcesState {
		/** 数据是录入的 */
		public final static Long INPUT = 0L;
		/** 数据是认领的 */
		public final static Long CLAIM = 1L;
	}

}
