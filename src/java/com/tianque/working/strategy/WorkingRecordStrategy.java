package com.tianque.working.strategy;

import java.util.Date;
import java.util.List;

import com.tianque.working.domain.ReportWorkingRecord;

public interface WorkingRecordStrategy {

	public String[][] transform(List<ReportWorkingRecord> reportWorking,
			String[][] reportWorkingRecord, int iteration, Date dealDate);

	public String[][] createReportWorkingRecord(String[][] reportWorkingRecord, int iteration,
			Date dealDate);
}
