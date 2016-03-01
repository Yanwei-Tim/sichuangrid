package com.tianque.plugin.account.util;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.plugin.account.domain.BaseWorking;

public class LedgerSerialnumberCheck {
	/**
	 * 根据台账编号判断台账是否是本年台账
	 * @param ledger
	 * @return
	 */
	public static boolean isCurrentYearLedger(BaseWorking ledger){
		String serial = ledger.getSerialNumber();
		if(!StringUtil.isStringAvaliable(serial)){
			return false;
		}
		if(!serial.substring(0, 2).equals(String.valueOf(CalendarUtil.getNowYear()).substring(2, 4))){
			return false;
		}
		return true;
	}
}
