package com.tianque.working.service;

import java.util.List;

import com.tianque.working.domain.DailyLogAttachFile;
import com.tianque.working.domain.WorkingRecord;

public interface DailyLogAttachFileService {

	public void updateDailyLogAttachFile(WorkingRecord workingRecord, String[] attachFiles);

	public DailyLogAttachFile addDailyLogAttachFile(DailyLogAttachFile dailyLogAttachFile);

	public DailyLogAttachFile getSimpleDailyLogAttachFileById(Long id);

	public List<DailyLogAttachFile> getSimpleDailyLogAttachFileByDailyLogId(Long id);

	public void deleteDailyLogAttachFileById(Long id);

	public void deleteDailyLogAttachFileByDailyLogId(Long id);

	public Long sumAllFileSizeByDailyLogId(Long id);

}
