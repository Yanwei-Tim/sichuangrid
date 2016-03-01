package com.tianque.baseInfo.permanentAddress.job.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.permanentAddress.domain.JobStatusType;
import com.tianque.baseInfo.permanentAddress.domain.PermanentAddressLog;
import com.tianque.baseInfo.permanentAddress.domain.TableMapColumn;
import com.tianque.baseInfo.permanentAddress.job.constants.TableMapColumns;
import com.tianque.baseInfo.permanentAddress.job.dao.SyncPermanentAddressDao;
import com.tianque.baseInfo.permanentAddress.service.PermanentAddressLogService;

@Service("syncPermanentAddressJobService")
@Transactional
public class SyncPermanentAddressJobServiceImpl implements
		SyncPermanentAddressJobService {

	@Autowired
	private PermanentAddressLogService permanentAddressLogService;

	@Autowired
	private SyncPermanentAddressDao syncPermanentAddressDao;

	@Override
	public void syncPermanentAddress() {
		Calendar calendar = Calendar.getInstance();
		Date updateDate = calendar.getTime();
		List<PermanentAddressLog> permanentAddressLogs = permanentAddressLogService
				.findPermanentAddressLogByJobstatus(JobStatusType.NEED_STATE);
		if (null != permanentAddressLogs && permanentAddressLogs.size() > 0) {
			for (PermanentAddressLog permanentAddressLog : permanentAddressLogs) {
				switch (permanentAddressLog.getAddslevel()) {
				case 1:
					syncProvince(permanentAddressLog, updateDate);
					break;
				case 2:
					syncCity(permanentAddressLog, updateDate);
					break;
				case 3:
					syncDistrict(permanentAddressLog, updateDate);
					break;
				default:
					break;
				}
			}
		}
	}

	@Override
	public void syncSelectPermanentAddress(String[] ids) {
		Calendar calendar = Calendar.getInstance();
		Date updateDate = calendar.getTime();
		List<PermanentAddressLog> permanentAddressLogs = permanentAddressLogService
				.findPermanentAddressLogByJobstatusAndIds(ids);
		if (null != permanentAddressLogs && permanentAddressLogs.size() > 0) {
			for (PermanentAddressLog permanentAddressLog : permanentAddressLogs) {
				switch (permanentAddressLog.getAddslevel()) {
				case 1:
					syncProvince(permanentAddressLog, updateDate);
					break;
				case 2:
					syncCity(permanentAddressLog, updateDate);
					break;
				case 3:
					syncDistrict(permanentAddressLog, updateDate);
					break;
				default:
					break;
				}
			}
		}
	}

	private void syncProvince(PermanentAddressLog permanentAddressLog,
			Date updateDate) {
		for (TableMapColumn tableMapColumn : TableMapColumns
				.getTableMapColumns()) {
			syncPermanentAddressDao.syncProvince(tableMapColumn,
					permanentAddressLog, updateDate);
		}
		permanentAddressLogService.updatePermanentAddressLogJobstatus(
				permanentAddressLog.getId(), JobStatusType.COMPLETE_STATE);
	}

	private void syncCity(PermanentAddressLog permanentAddressLog,
			Date updateDate) {
		for (TableMapColumn tableMapColumn : TableMapColumns
				.getTableMapColumns()) {
			syncPermanentAddressDao.syncCity(tableMapColumn,
					permanentAddressLog, updateDate);
		}
		permanentAddressLogService.updatePermanentAddressLogJobstatus(
				permanentAddressLog.getId(), JobStatusType.COMPLETE_STATE);
	}

	private void syncDistrict(PermanentAddressLog permanentAddressLog,
			Date updateDate) {
		for (TableMapColumn tableMapColumn : TableMapColumns
				.getTableMapColumns()) {
			syncPermanentAddressDao.syncDistrict(tableMapColumn,
					permanentAddressLog, updateDate);
		}
		permanentAddressLogService.updatePermanentAddressLogJobstatus(
				permanentAddressLog.getId(), JobStatusType.COMPLETE_STATE);
	}
}
