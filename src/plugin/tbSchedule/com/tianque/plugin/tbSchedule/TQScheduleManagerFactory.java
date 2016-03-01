/**
 * 
 */
package com.tianque.plugin.tbSchedule;

import com.taobao.pamirs.schedule.strategy.TBScheduleManagerFactory;
import com.tianque.core.util.GridProperties;

/**
 * @since
 * @author 曾利民
 * @version 1.0.0[2015年1月13日]
 */
public class TQScheduleManagerFactory extends TBScheduleManagerFactory {

	@Override
	public void init() throws Exception {
		if (GridProperties.ISSCHEDULE) {
			super.init();
		}
	}

}
