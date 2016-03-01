package com.tianque.domain.constants;

import java.util.ArrayList;
import java.util.List;

public class TeamType {

	public static final String VOLUNTEER_TEAM = "志愿者团队";
	public static final String SERVICE_TEAM = "服务团队";

	public static final List<String> TEAM_TYPES = new ArrayList<String>();
	static {
		TEAM_TYPES.add(VOLUNTEER_TEAM);
		TEAM_TYPES.add(SERVICE_TEAM);
	}

}
