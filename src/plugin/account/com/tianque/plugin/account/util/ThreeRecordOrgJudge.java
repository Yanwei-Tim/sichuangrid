package com.tianque.plugin.account.util;

import com.tianque.domain.Organization;
import com.tianque.plugin.account.constants.ThreeRecordsIssueConstants;

public class ThreeRecordOrgJudge {
	/**
	 * 检测部门是否是县台账办
	 * @return
	 */
	public static boolean isJg(Organization organization){
		return organization.getDepartmentNo().endsWith(ThreeRecordsIssueConstants.COUNTY_LEDGER);
	}
	
	/**
	 * 检测部门是否是县联席会议
	 * @return
	 */
	public static boolean isLx(Organization organization){
		return organization.getDepartmentNo().endsWith(ThreeRecordsIssueConstants.COUNTY_JOINT);
	}
	
	/**
	 * 检测部门是否是县委县政府
	 * @return
	 */
	public static boolean isXw(Organization organization){
		return organization.getDepartmentNo().endsWith(ThreeRecordsIssueConstants.COUNTY_COMMITTEE);
	}
	
	/**
	 * 检测部门是否是县台账办或则县联席会议或则县委县政府
	 * @return
	 */
	public static boolean isJgOrLxOrxW(Organization organization){
		return isJg(organization) || isLx(organization) || isXw(organization);
	}
}
