package com.tianque.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tianque.service.TimeService;

@Service("timeService")
public class TimeServiceImpl implements TimeService {

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
		Calendar can = Calendar.getInstance();
		Integer year = can.get(Calendar.YEAR) - 1;
		return year;
	}

	@Override
	public Integer getSysBeginUseMonth() {
		Calendar calendar = Calendar.getInstance();
		Integer month = calendar.get(Calendar.MONTH) + 2;
		return month;

	}

	private Integer getCurYear() {
		Calendar nowYear = Calendar.getInstance();
		Integer curYear = nowYear.get(Calendar.YEAR);
		return curYear;
	}
}
