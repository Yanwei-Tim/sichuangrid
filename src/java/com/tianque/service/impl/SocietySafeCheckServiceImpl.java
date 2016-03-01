package com.tianque.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.dao.SocietySafeCheckDao;
import com.tianque.domain.SocietySafeCheck;
import com.tianque.service.SocietySafeCheckService;

@Service("societySafeCheckService")
public class SocietySafeCheckServiceImpl implements SocietySafeCheckService {

	@Autowired
	private SocietySafeCheckDao societySafeCheckDao;

	@Override
	public void addSocietySafeCheck(SocietySafeCheck societySafeCheck) {
		societySafeCheckDao.addSocietySafeCheck(societySafeCheck);
	}

	@Override
	public void deleteSocietySafeCheckById(Long id) {
		societySafeCheckDao.deleteSocietySafeCheckById(id);
	}

	@Override
	public void updateSocietySafeCheck(SocietySafeCheck societySafeCheck) {
		societySafeCheckDao.updateSocietySafeCheck(societySafeCheck);
	}

	@Override
	public SocietySafeCheck getSocietySafeCheckByDailyLogId(Long dailyLogId) {
		return societySafeCheckDao.getSocietySafeCheckByDailyLogId(dailyLogId);
	}

	@Override
	public List<SocietySafeCheck> findCollectionData(Integer reportState,
			String reportYear, Integer reportMonth, String orgCode,
			Integer reportType) {
		return societySafeCheckDao.findCollectionData(reportState, reportYear,
				reportMonth, orgCode, reportType);
	}

	@Override
	public List<SocietySafeCheck> findQuarterData(Integer reportState,
			String reportYear, Integer quarter, String orgCode) {
		return societySafeCheckDao.findQuarterData(reportState, reportYear,
				quarter, orgCode);
	}

	@Override
	public SocietySafeCheck getSocietySafeCheckBySignCode(String signCode) {
		return societySafeCheckDao.getSocietySafeCheckBySignCode(signCode);
	}

	public void backReport(Long id) {
		societySafeCheckDao.backReport(id);
	}
	// 方法没有显式调用
	// @Override
	// public PageInfo<IssueSubmitInfoVo> findIssueSubmitInfo(Integer page,
	// Integer rows, Long orgId,
	// String reportYear, String directoryNameSign) {
	// return societySafeCheckDao.findIssueSubmitInfo(page, rows, orgId,
	// reportYear,
	// directoryNameSign);
	// }
}
