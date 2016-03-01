package com.tianque.working.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.working.dao.DailyAttachFileDao;
import com.tianque.working.domain.DailyAttachFiles;

@Repository("dailyAttachFileDao")
public class DailyAttachFileDaoImpl extends AbstractBaseDao implements DailyAttachFileDao {

	@Override
	public DailyAttachFiles addDailyAttachFiles(DailyAttachFiles dailyAttachFiles) {
		Long id = (Long) getSqlMapClientTemplate().insert("dailyAttachFiles.addDailyAttachFiles",
				dailyAttachFiles);
		return getSimpleDailyAttachFilesById(id);
	}

	@Override
	public DailyAttachFiles getSimpleDailyAttachFilesById(Long id) {
		return (DailyAttachFiles) getSqlMapClientTemplate().queryForObject(
				"dailyAttachFiles.getSimpleDailyAttachFilesById", id);
	}

	@Override
	public List<DailyAttachFiles> getSimpleDailyAttachFilesByDailyIdAndDailyDirectoryType(
			Long dailyId, Long dailyDirectoryType) {
		List<DailyAttachFiles> dailyAttachFiles = getSqlMapClientTemplate().queryForList(
				"dailyAttachFiles.getSimpleDailyAttachFilesByDailyIdAndDailyDirectoryType",
				createQueryMap(dailyId, dailyDirectoryType));
		if (dailyAttachFiles == null) {
			dailyAttachFiles = new ArrayList<DailyAttachFiles>();
		}
		return dailyAttachFiles;
	}

	@Override
	public void deleteDailyAttachFilesById(Long id) {
		getSqlMapClientTemplate().delete("dailyAttachFiles.deleteDailyAttachFilesById", id);
	}

	@Override
	public void deleteDailyAttachFilesByByDailyIdAndDailyDirectoryType(Long dailyId,
			Long dailyDirectoryType) {
		getSqlMapClientTemplate().delete(
				"dailyAttachFiles.deleteDailyAttachFilesByByDailyIdAndDailyDirectoryType",
				createQueryMap(dailyId, dailyDirectoryType));

	}

	private Map<String, Object> createQueryMap(Long dailyId, Long dailyDirectoryType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dailyId", dailyId);
		map.put("dailyDirectoryType", dailyDirectoryType);
		return map;
	}
}
