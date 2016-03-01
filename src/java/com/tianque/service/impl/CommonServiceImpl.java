package com.tianque.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tianque.core.util.GridProperties;
import com.tianque.service.CommonService;

@Service("commonService")
public class CommonServiceImpl implements CommonService {

	@Override
	public List<Integer> getUsableYear() {
		List<Integer> availableYear = new ArrayList<Integer>();
		Integer beginYear = getSysBeginUseYear();
		Integer doYear = getCurYear() + 1;
		for (Integer i = beginYear; i <= doYear; i++) {
			availableYear.add(i);
		}
		return availableYear;
	}

	@Override
	public Integer getSysBeginUseYear() {
		String SysBeginUseTime = GridProperties.SYS_BEGIN_USE_YEAR;
		Integer year = Integer.parseInt(SysBeginUseTime.substring(0, 4));
		return year;
	}

	@Override
	public Integer getSysBeginUseMonth() {
		String SysBeginUseTime = GridProperties.SYS_BEGIN_USE_YEAR;
		Integer month = Integer.parseInt(SysBeginUseTime.substring(4, 6));
		return month;
	}

	private Integer getCurYear() {
		Calendar nowYear = Calendar.getInstance();
		Integer curYear = nowYear.get(Calendar.YEAR);
		return curYear;
	}
}