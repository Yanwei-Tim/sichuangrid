package com.tianque.mobile.baseInfo.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.mobile.base.BaseMobileAction;
import com.tianque.mobile.baseInfo.GlobalSettingMobileAdapter;

/**
 * @ClassName: GlobalSettingMobileAdapterImpl
 * @Description: GPS定位时间间隔接口实现类
 * @author wangxiaohu wsmalltiger@163.com
 * @date Nov 6, 2013 11:00:14 AM
 */
@Controller("globalSettingMobileAdapter")
@Scope("prototype")
@Transactional
public class GlobalSettingMobileAdapterImpl extends BaseMobileAction
		implements
			GlobalSettingMobileAdapter {
	@Autowired
	private GlobalSettingService globalSettingService;

	protected Map<String, Object> globalSettingMap = new HashMap<String, Object>();

	@Override
	public String getMobileGPSPositionTimeInterval() throws Exception {
		Map<String, String> map = globalSettingService.getGlobalSetting();
		Object obj = map.get(GlobalSetting.MOBILE_GPS_POSITION_TIME_INTERVAL);
		Integer timeInterval = Integer
				.parseInt(GlobalSetting.MOBILE_GPS_POSITION_TIME_INTERVAL_DEFAULT_VAL);
		try {
			timeInterval = Integer
					.parseInt(obj == null ? GlobalSetting.MOBILE_GPS_POSITION_TIME_INTERVAL_DEFAULT_VAL
							: obj.toString());
		} catch (Exception e) {
		}
		globalSettingMap.put("timeInterval", timeInterval);
		globalSettingMap.put("endDateTime",
				map.get(GlobalSetting.MOBILE_GPS_ENDDATETIME));
		globalSettingMap.put("startDateTime",
				map.get(GlobalSetting.MOBILE_GPS_STARTDATETIME));
		globalSettingMap
				.put("weekDay",
						map.get(GlobalSetting.MOBILE_GPS_WEEKDAY) == null ? ""
								: map.get(GlobalSetting.MOBILE_GPS_WEEKDAY)
										.replace(" ", ""));
		globalSettingMap.put("allow", "1".equals(map
				.get(GlobalSetting.MOBILE_GPS_ALLOW)) ? true : false);
		globalSettingMap.put("separator", ",");
		return SUCCESS;
	}

	public Map<String, Object> getGlobalSettingMap() {
		return globalSettingMap;
	}

	public void setGlobalSettingMap(Map<String, Object> globalSettingMap) {
		this.globalSettingMap = globalSettingMap;
	}

}
