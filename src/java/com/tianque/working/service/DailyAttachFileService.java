package com.tianque.working.service;

import java.util.List;

import com.tianque.working.domain.DailyAttachFiles;

public interface DailyAttachFileService {

	public DailyAttachFiles addDailyAttachFiles(DailyAttachFiles dailyAttachFiles);

	public DailyAttachFiles getSimpleDailyAttachFilesById(Long id);

	public List<DailyAttachFiles> getSimpleDailyAttachFilesByDailyIdAndDailyDirectoryType(
			Long dailyId, Long dailyDirectoryType);

	public void deleteDailyAttachFilesById(Long id);

	public void deleteDailyAttachFilesByByDailyIdAndDailyDirectoryType(Long dailyId,
			Long dailyDirectoryType);
}
