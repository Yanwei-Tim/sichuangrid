package com.tianque.fourTeams.util.keyGenerator;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.CalendarUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.SystemKeyInfo;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsIssueSerialNumberGenerator;
import com.tianque.service.SystemKeyInfoService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Service("fourTeamsIssueSerialKeyGenerator")
@Transactional
public class FourTeamsIssueSerialKeyGenerator implements FourTeamsKeyGenerator,
		FourTeamsIssueSerialNumberGenerator {

	private static final String DEFAULT_PREFIX_FORMAT = "yyMMdd";
	private static final String DEFAULT_FIX_CONTEXT = "0000";
	private static final String ISSUESERIAL = "issueSerial";
	@Autowired
	private SystemKeyInfoService systemKeyInfoService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public String getNextKey(String prefix) {
		// String prefix=getCurrentIssuePrefix();
		SystemKeyInfo systemKeyInfo = systemKeyInfoService
				.getSimpleSystemKeyInfoByName(prefix);
		if (systemKeyInfo == null)
			systemKeyInfo = addNewSystemKeyInfo(prefix);
		systemKeyInfo.setCurNum(systemKeyInfo.getCurNum()
				+ systemKeyInfo.getStep());
		systemKeyInfo = systemKeyInfoService.updateSystemKeyInfo(systemKeyInfo);
		String curNum = String.valueOf(systemKeyInfo.getCurNum());
		if (curNum.length() > 4)
			curNum = curNum.substring(0, 4);
		else
			curNum = DEFAULT_FIX_CONTEXT.substring(0, 4 - curNum.length())
					+ curNum;
		return prefix + curNum;
	}

	@Override
	public String getNextKey() {
		// String prefix=getCurrentIssuePrefix();
		SystemKeyInfo systemKeyInfo = systemKeyInfoService
				.getSimpleSystemKeyInfoByName(ISSUESERIAL);
		if (systemKeyInfo == null)
			systemKeyInfo = addNewSystemKeyInfo();
		systemKeyInfo.setCurNum(systemKeyInfo.getCurNum()
				+ systemKeyInfo.getStep());
		systemKeyInfo = systemKeyInfoService.updateSystemKeyInfo(systemKeyInfo);
		String curNum = String.valueOf(systemKeyInfo.getCurNum());
		if (curNum.length() > 4)
			curNum = curNum.substring(0, 4);
		else
			curNum = DEFAULT_FIX_CONTEXT.substring(0, 4 - curNum.length())
					+ curNum;
		return getPrefixDate() + curNum;
	}

	private String getPrefixDate() {
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_PREFIX_FORMAT);
		return sdf.format(Calendar.getInstance().getTime());
	}

	private SystemKeyInfo addNewSystemKeyInfo(String prefix) {
		return systemKeyInfoService
				.addSystemKeyInfo(createSystemKeyInfoInstance(prefix));
	}

	private SystemKeyInfo addNewSystemKeyInfo() {
		return systemKeyInfoService
				.addSystemKeyInfo(createSystemKeyInfoInstance());
	}

	private SystemKeyInfo createSystemKeyInfoInstance(String prefix) {
		SystemKeyInfo systemKeyInfo = new SystemKeyInfo();
		systemKeyInfo.setName(prefix);
		systemKeyInfo.setCurNum(0L);
		systemKeyInfo.setStep(1);
		return systemKeyInfo;
	}

	private SystemKeyInfo createSystemKeyInfoInstance() {
		SystemKeyInfo systemKeyInfo = new SystemKeyInfo();
		systemKeyInfo.setName(ISSUESERIAL);
		systemKeyInfo.setCurNum(0L);
		systemKeyInfo.setStep(1);
		return systemKeyInfo;
	}

	@Override
	public String nextValue(FourTeamsIssueNew issue) {
		String prefix = getCurrentIssuePrefix(issue);
		return getNextKey(prefix);
	}

	private String getCurrentIssuePrefix(FourTeamsIssueNew issue) {
		Organization district = organizationDubboService.findSomeLevelParentOrg(
				issue.getOccurOrg().getId(), OrganizationLevel.DISTRICT);
		if (district == null) {
			district = organizationDubboService.findOrganizationsByParentId(null)
					.get(0);
		}
		return CalendarUtil.format("yyMMdd", new Date())
				+ district.getDepartmentNo();
	}
}
