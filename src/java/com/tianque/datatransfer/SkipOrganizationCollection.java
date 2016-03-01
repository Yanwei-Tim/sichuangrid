package com.tianque.datatransfer;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.util.BaseInfoTables;

public class SkipOrganizationCollection {

	/** 用于跳过填报单位的验证 */
	public static final List<String> SKIP_ORGANIZATION_VALIDATE = new ArrayList<String>();

	static {
		SKIP_ORGANIZATION_VALIDATE.add(BaseInfoTables.MEMBER_KEY);
		SKIP_ORGANIZATION_VALIDATE.add(DataTransferConstants.MOBILEUSER);
		SKIP_ORGANIZATION_VALIDATE.add(DataTransferConstants.FOUR_TEAM_MEMBERS);
		SKIP_ORGANIZATION_VALIDATE.add(DataTransferConstants.RED_CUFF_TEAM);
	}
}
