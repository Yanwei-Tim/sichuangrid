package com.tianque.util.keyGenerator;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.domain.SystemKeyInfo;
import com.tianque.service.SystemKeyInfoService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Transactional
@Service("workingSerialKeyGenerator")
public class WorkingSerialKeyGenerator {

	private static final String DEFAULT_PREFIX_FORMAT = "yyMMdd";
	private static final String DEFAULT_FIX_CONTEXT = "0000000";
	@Autowired
	private SystemKeyInfoService systemKeyInfoService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	public synchronized String getNextKey(String prefix) {
		prefix = getCurrentPrefix(prefix);
		SystemKeyInfo systemKeyInfo = systemKeyInfoService
				.getSimpleSystemKeyInfoByName(prefix);
		if (systemKeyInfo == null)
			systemKeyInfo = addNewSystemKeyInfo(prefix);
			systemKeyInfo.setCurNum(systemKeyInfo.getCurNum()
					+ systemKeyInfo.getStep());
			systemKeyInfo = systemKeyInfoService.updateSystemKeyInfo(systemKeyInfo);
		String curNum = String.valueOf(systemKeyInfo.getCurNum());
		if (curNum.length() > 7)
			curNum = curNum.substring(0, 7);
		else
			curNum = DEFAULT_FIX_CONTEXT.substring(0, 7 - curNum.length())
					+ curNum;
		return prefix + curNum;
	}

	private String getPrefixDate() {
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_PREFIX_FORMAT);
		return sdf.format(Calendar.getInstance().getTime());
	}

	private String getCurrentPrefix(String prefix) {
		String dateSerial = getPrefixDate();
		return dateSerial + prefix;
	}

	private SystemKeyInfo addNewSystemKeyInfo(String prefix) {
		return systemKeyInfoService
				.addSystemKeyInfo(createSystemKeyInfoInstance(prefix));
	}

	private SystemKeyInfo createSystemKeyInfoInstance(String prefix) {
		SystemKeyInfo systemKeyInfo = new SystemKeyInfo();
		systemKeyInfo.setName(prefix);
		systemKeyInfo.setCurNum(0L);
		systemKeyInfo.setStep(1);
		return systemKeyInfo;
	}

}
