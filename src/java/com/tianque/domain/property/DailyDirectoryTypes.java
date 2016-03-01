package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

public class DailyDirectoryTypes extends BaseProperty {
	public final static int MEET = 0;
	public final static int FILE = 1;
	public final static int ACTIVITIES = 2;
	public final static int CHECK = 3;
	public final static int ISSUEDEAL = 4;
	public final static int INVESTIGATION = 5;
	public final static int SIGNIFICANT_ISSUEDEAL = 6;
	public final static int INVESTIGATION_REMEDIATION = 7;
	public final static int STATEMENTS_REPORTED = 16;

	public final static int SOCIETY_SECURITY = 8;
	public final static int SECURITY_PROPAGANDA = 9;
	public final static int SERIES_SECURITY = 10;
	public final static int SERVICE_MANAGEMENT = 11;
	public final static int SERVICE_MANAGEMENT_CITY = 12;
	public final static int SERVICE_MANAGEMENT_TOWN = 13;
	public final static int SERVICE_MANAGEMENT_VILLAGE = 14;
	public final static int GRID_MANAGEMENT_NORMAL = 15;

	public final static String MEET_NAME = "会议类";
	public final static String FILE_NAME = "文件类";
	public final static String ACTIVITIES_NAME = "活动类";
	public final static String STATEMENTS_REPORTED_NAME = "报表上报";

	public final static String CHECK_NAME = "治安重点排查整治类";
	public final static String ISSUEDEAL_NAME = "矛盾纠纷排查调处类";
	public final static String SIGNIFICANT_ISSUEDEAL_NAME = "重大矛盾纠纷排查调处情况";
	public final static String INVESTIGATION_NAME = "治安重点排查情况";
	public final static String INVESTIGATION_REMEDIATION_NAME = "排查整治强基促稳情况";

	public final static String SOCIETY_SECURITY_NAME = "社会治安防控体系建设";
	public final static String SECURITY_PROPAGANDA_NAME = "平安综治宣传";
	public final static String SERIES_SECURITY_NAME = "系列平安创建";
	public final static String SERVICE_MANAGEMENT_NAME = "社会服务管理";
	public final static String SERVICE_MANAGEMENT_CITY_NAME = "社会服务管理中心建设(县及以上使用)";
	public final static String SERVICE_MANAGEMENT_TOWN_NAME = "社会服务管理中心建设(乡镇使用)";
	public final static String SERVICE_MANAGEMENT_VILLAGE_NAME = "社会服务管理室（站）建设(村社区使用)";
	public final static String GRID_MANAGEMENT_NORMAL_NAME = "平时开展工作情况(网格化管理、组团式服务)";

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(MEET, MEET_NAME));
		properties.add(getInternalProperty(FILE, FILE_NAME));
		properties.add(getInternalProperty(ACTIVITIES, ACTIVITIES_NAME));
		properties.add(getInternalProperty(CHECK, CHECK_NAME));
		properties.add(getInternalProperty(ISSUEDEAL, ISSUEDEAL_NAME));

		properties.add(getInternalProperty(INVESTIGATION, INVESTIGATION_NAME));
		properties.add(getInternalProperty(SIGNIFICANT_ISSUEDEAL, SIGNIFICANT_ISSUEDEAL_NAME));
		properties.add(getInternalProperty(INVESTIGATION_REMEDIATION,
				INVESTIGATION_REMEDIATION_NAME));

		properties.add(getInternalProperty(STATEMENTS_REPORTED, STATEMENTS_REPORTED_NAME));
		properties.add(getInternalProperty(SOCIETY_SECURITY, SOCIETY_SECURITY_NAME));

		properties.add(getInternalProperty(SOCIETY_SECURITY, SOCIETY_SECURITY_NAME));
		properties.add(getInternalProperty(SECURITY_PROPAGANDA, SECURITY_PROPAGANDA_NAME));
		properties.add(getInternalProperty(SERIES_SECURITY, SERIES_SECURITY_NAME));
		properties.add(getInternalProperty(SERVICE_MANAGEMENT, SERVICE_MANAGEMENT_NAME));
		properties.add(getInternalProperty(SERVICE_MANAGEMENT_CITY, SERVICE_MANAGEMENT_CITY_NAME));
		properties.add(getInternalProperty(SERVICE_MANAGEMENT_TOWN, SERVICE_MANAGEMENT_TOWN_NAME));
		properties.add(getInternalProperty(SERVICE_MANAGEMENT_VILLAGE,
				SERVICE_MANAGEMENT_VILLAGE_NAME));
		properties.add(getInternalProperty(GRID_MANAGEMENT_NORMAL, GRID_MANAGEMENT_NORMAL_NAME));
		return properties;
	}

	public final static String DAILY_DIRECTORY_TYPES_KEY = PropertyTypes.DAILYDIRECTORY_TYPE;
}
