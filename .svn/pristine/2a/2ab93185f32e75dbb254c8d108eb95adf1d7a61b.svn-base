package com.tianque.working.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.exception.base.BusinessValidationException;
import com.tianque.working.dao.DailyAttachFileDao;
import com.tianque.working.domain.DailyAttachFiles;
import com.tianque.working.service.DailyAttachFileService;

@Service("dailyAttachFileService")
public class DailyAttachFileServiceImpl implements DailyAttachFileService {

	@Autowired
	private DailyAttachFileDao dailyAttachFileDao;

	@Override
	public DailyAttachFiles addDailyAttachFiles(
			DailyAttachFiles dailyAttachFiles) {
		if (dailyAttachFiles == null) {
			throw new BusinessValidationException("保存对象为空");
		}
		return dailyAttachFileDao.addDailyAttachFiles(dailyAttachFiles);
	}

	@Override
	public DailyAttachFiles getSimpleDailyAttachFilesById(Long id) {
		if (validateId(id)) {
			throw new BusinessValidationException("参数错误");
		}
		return dailyAttachFileDao.getSimpleDailyAttachFilesById(id);
	}

	@Override
	public List<DailyAttachFiles> getSimpleDailyAttachFilesByDailyIdAndDailyDirectoryType(
			Long dailyId, Long dailyDirectoryType) {
		if (validateDailyIdAndDailyDirectoryType(dailyId, dailyDirectoryType)) {
			throw new BusinessValidationException("参数错误");
		}
		return dailyAttachFileDao
				.getSimpleDailyAttachFilesByDailyIdAndDailyDirectoryType(
						dailyId, dailyDirectoryType);
	}

	@Override
	public void deleteDailyAttachFilesById(Long id) {
		if (validateId(id)) {
			throw new BusinessValidationException("参数错误");
		}
		dailyAttachFileDao.deleteDailyAttachFilesById(id);
	}

	@Override
	public void deleteDailyAttachFilesByByDailyIdAndDailyDirectoryType(
			Long dailyId, Long dailyDirectoryType) {
		if (validateDailyIdAndDailyDirectoryType(dailyId, dailyDirectoryType)) {
			throw new BusinessValidationException("参数错误");
		}
		dailyAttachFileDao
				.deleteDailyAttachFilesByByDailyIdAndDailyDirectoryType(
						dailyId, dailyDirectoryType);
	}

	private boolean validateId(Long id) {
		return id == null || id.intValue() == 0;
	}

	private boolean validateDailyIdAndDailyDirectoryType(Long dailyId,
			Long dailyDirectoryType) {
		return dailyId == null || dailyId.intValue() == 0
				|| dailyDirectoryType == null
				|| dailyDirectoryType.intValue() == 0;
	}
}
