package com.tianque.working.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.ThreadVariable;
import com.tianque.service.impl.LogableService;
import com.tianque.working.domain.DailyDirectory;
import com.tianque.working.domain.WorkingRecord;

public abstract class Logable4WorkingRecordService extends LogableService {
	@Autowired
	private DailyDirectoryService dailyDirectoryService;

	public void log(WorkingRecord WorkingRecord, String operation,
			Integer operationType) {
		DailyDirectory dailyDirectory = dailyDirectoryService
				.getFullDailyDirectoryById(WorkingRecord.getDailyDirectory()
						.getId());
		DailyDirectory parentDailyDirectory = dailyDirectoryService
				.getSimpleDailyDirectoryById(dailyDirectory
						.getParentDailyDirectory().getId());
		super.log(
				WARN,
				WORKINGRECORD + parentDailyDirectory.getShortName() + "->"
						+ dailyDirectory.getShortName(),
				ThreadVariable.getSession().getUserName() + operation + "名称为"
						+ WorkingRecord.getName() + "的"
						+ dailyDirectory.getShortName(), operationType,
				ObjectToJSON.convertJSON(WorkingRecord));
	}

}
