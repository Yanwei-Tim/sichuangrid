package com.tianque.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.working.dao.DailyLogAttachFileDao;
import com.tianque.working.domain.DailyLogAttachFile;

@Repository("dailyLogAttachFileDao")
public class DailyLogAttachFileDaoImpl extends AbstractBaseDao implements
		DailyLogAttachFileDao {

	@Override
	public DailyLogAttachFile addDailyLogAttachFile(
			DailyLogAttachFile dailyLogAttachFile) {
		validate(dailyLogAttachFile);
		Long id = (Long) getSqlMapClientTemplate().insert(
				"dailyLogAttachFile.addDailyLogAttachFile", dailyLogAttachFile);
		return this.getSimpleDailyLogAttachFileById(id);
	}

	@Override
	public void deleteDailyLogAttachFileByDailyLogId(Long id) {
		getSqlMapClientTemplate().delete(
				"dailyLogAttachFile.deleteDailyLogAttachFileByDailyLogId", id);
	}

	@Override
	public void deleteDailyLogAttachFileById(Long id) {
		getSqlMapClientTemplate().delete(
				"dailyLogAttachFile.deleteDailyLogAttachFileById", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DailyLogAttachFile> getSimpleDailyLogAttachFileByDailyLogId(
			Long id) {
		return getSqlMapClientTemplate().queryForList(
				"dailyLogAttachFile.getSimpleDailyLogAttachFileByDailyLogId",
				id);
	}

	@Override
	public DailyLogAttachFile getSimpleDailyLogAttachFileById(Long id) {
		return (DailyLogAttachFile) getSqlMapClientTemplate().queryForObject(
				"dailyLogAttachFile.getSimpleDailyLogAttachFileById", id);
	}

	private void validate(DailyLogAttachFile dailyLogAttachFile) {
		if (dailyLogAttachFile == null)
			throw new BusinessValidationException("台帐附件不能为空");
		if (dailyLogAttachFile.getFileName() == null
				|| "".equals(dailyLogAttachFile.getFileName().trim()))
			throw new BusinessValidationException("台帐名称不能为空");
		if (dailyLogAttachFile.getFileActualUrl() == null
				|| "".equals(dailyLogAttachFile.getFileActualUrl().trim()))
			throw new BusinessValidationException("台帐存储路径不能为空");
		// if (dailyLogAttachFile.getDailyLog() == null
		// || dailyLogAttachFile.getDailyYear() == null)
		// return false;
	}

	@Override
	public Long sumAllFileSizeByDailyLogId(Long id) {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"dailyLogAttachFile.sumAllFileSizeByDailyLogId", id);
	}

	@Override
	public void deleteDailyLogAttachFileByYearId(Long yearId) {
		getSqlMapClientTemplate().delete(
				"dailyLogAttachFile.deleteDailyLogAttachFileByYearId", yearId);
	}

}
