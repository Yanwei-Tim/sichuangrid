package com.tianque.working.strategy;

import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.working.strategy.impl.WorkingRecordForHalfYearAlgorithm;
import com.tianque.working.strategy.impl.WorkingRecordForMonthAlgorithm;
import com.tianque.working.strategy.impl.WorkingRecordForQuarterAlgorithm;
import com.tianque.working.strategy.impl.WorkingRecordForYearAlgorithm;

public class WorkingRecordAlgorithm {

	private WorkingRecordAlgorithm() {

	}

	public static WorkingRecordStrategy getWorkingRecordForMonthAlgorithm(
			PropertyDictService propertyDictService) {
		return new WorkingRecordForMonthAlgorithm(propertyDictService);
	}

	public static WorkingRecordStrategy getWorkingRecordForQuarterAlgorithm(
			PropertyDictService propertyDictService) {
		return new WorkingRecordForQuarterAlgorithm(propertyDictService);
	}

	public static WorkingRecordStrategy getWorkingRecordForHalfYearAlgorithm(
			PropertyDictService propertyDictService) {
		return new WorkingRecordForHalfYearAlgorithm(propertyDictService);
	}

	public static WorkingRecordStrategy getWorkingRecordForYearAlgorithm(
			PropertyDictService propertyDictService) {
		return new WorkingRecordForYearAlgorithm(propertyDictService);
	}
}
