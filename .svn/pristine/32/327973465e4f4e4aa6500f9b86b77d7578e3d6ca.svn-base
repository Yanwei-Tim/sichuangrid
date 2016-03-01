package com.tianque.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.vo.PageInfo;
import com.tianque.dao.KeyAreasOfInvestigationInfoWorkingRecordDao;
import com.tianque.domain.Organization;
import com.tianque.domain.workingRecord.KeyAreasOfInvestigationInfoWorkingRecord;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.service.KeyAreasOfInvestigationInfoWorkingRecordService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.working.domain.DailyDirectory;
import com.tianque.working.service.DailyDirectoryService;

@Service("keyAreasOfInvestigationInfoWorkingRecordSerivce")
@Transactional
public class KeyAreasOfInvestigationInfoWorkingRecordSerivceImpl extends
		LogableService implements
		KeyAreasOfInvestigationInfoWorkingRecordService {

	@Autowired
	private KeyAreasOfInvestigationInfoWorkingRecordDao keyAreasOfInvestigationInfoWorkingRecordDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private DailyDirectoryService dailyDirectoryService;

	@Override
	public KeyAreasOfInvestigationInfoWorkingRecord addKeyAreasOfInvestigationInfoWorkingRecord(
			KeyAreasOfInvestigationInfoWorkingRecord keyAreasOfInvestigationInfoWorkingRecord) {
		validate(keyAreasOfInvestigationInfoWorkingRecord);
		autoFillOrganizationInternalCode(keyAreasOfInvestigationInfoWorkingRecord);
		return keyAreasOfInvestigationInfoWorkingRecordDao
				.addKeyAreasOfInvestigationInfoWorkingRecord(keyAreasOfInvestigationInfoWorkingRecord);
	}

	@Override
	public void deleteKeyAreasOfInvestigationInfoWorkingRecord(Long id) {
		keyAreasOfInvestigationInfoWorkingRecordDao
				.deleteKeyAreasOfInvestigationInfoWorkingRecord(id);
	}

	@Override
	public PageInfo<KeyAreasOfInvestigationInfoWorkingRecord> findKeyAreasOfInvestigationInfoWorkingRecordForPageByOrgInternalCode(
			Long orgId, Integer pageNum, Integer pageSize, String sidx,
			String sord, Long dailyDirectoryId) {
		String allDailyDirectoryId = "";
		if (dailyDirectoryId == null) {
			throw new BusinessValidationException("找不到指定的目录");
		}
		List<DailyDirectory> dailyDirectories = dailyDirectoryService
				.findDailySubDirectoryByParentId(dailyDirectoryId);
		if (dailyDirectories.size() == 0) {
			allDailyDirectoryId = "'" + dailyDirectoryId + "'";
		} else {
			for (int i = 0; i < dailyDirectories.size(); i++) {
				if (i == 0) {
					allDailyDirectoryId = "'" + dailyDirectories.get(i).getId()
							+ "'";
				} else {
					allDailyDirectoryId = allDailyDirectoryId + ",'"
							+ dailyDirectories.get(i).getId() + "'";
				}
			}
		}
		return keyAreasOfInvestigationInfoWorkingRecordDao
				.findKeyAreasOfInvestigationInfoWorkingRecordForPageByOrgInternalCode(
						orgId, pageNum, pageSize, sidx, sord,
						allDailyDirectoryId);
	}

	@Override
	public KeyAreasOfInvestigationInfoWorkingRecord getKeyAreasOfInvestigationInfoWorkingRecordById(
			Long id) {
		return keyAreasOfInvestigationInfoWorkingRecordDao
				.getKeyAreasOfInvestigationInfoWorkingRecordById(id);
	}

	@Override
	public KeyAreasOfInvestigationInfoWorkingRecord updateKeyAreasOfInvestigationInfoWorkingRecord(
			KeyAreasOfInvestigationInfoWorkingRecord keyAreasOfInvestigationInfoWorkingRecord) {
		validate(keyAreasOfInvestigationInfoWorkingRecord);
		autoFillOrganizationInternalCode(keyAreasOfInvestigationInfoWorkingRecord);
		return keyAreasOfInvestigationInfoWorkingRecordDao
				.updateKeyAreasOfInvestigationInfoWorkingRecord(keyAreasOfInvestigationInfoWorkingRecord);
	}

	private void autoFillOrganizationInternalCode(
			KeyAreasOfInvestigationInfoWorkingRecord keyAreasOfInvestigationInfoWorkingRecord) {
		Organization org = organizationDubboService
				.getSimpleOrgById(keyAreasOfInvestigationInfoWorkingRecord
						.getOrganization().getId());
		if (org == null) {
			throw new BusinessValidationException("找不到指定的网格");
		}
		keyAreasOfInvestigationInfoWorkingRecord.setOrgInternalCode(org
				.getOrgInternalCode());
	}

	private void validate(
			KeyAreasOfInvestigationInfoWorkingRecord keyAreasOfInvestigationInfoWorkingRecord) {
		if (keyAreasOfInvestigationInfoWorkingRecord == null)
			throw new BusinessValidationException("找不到治安重点排查信息");
		if (keyAreasOfInvestigationInfoWorkingRecord.getMainProblem() == null
				|| "".equals(keyAreasOfInvestigationInfoWorkingRecord
						.getMainProblem().trim()))
			throw new BusinessValidationException("找不到主要问题");
		if (keyAreasOfInvestigationInfoWorkingRecord.getPoliciesAndMeasures() == null
				|| "".equals(keyAreasOfInvestigationInfoWorkingRecord
						.getPoliciesAndMeasures().trim()))
			throw new BusinessValidationException("找不到对策与措施");
		if (keyAreasOfInvestigationInfoWorkingRecord.getAreaName() == null
				|| "".equals(keyAreasOfInvestigationInfoWorkingRecord
						.getAreaName().trim()))
			throw new BusinessValidationException("找不到地区名称");
		if (keyAreasOfInvestigationInfoWorkingRecord.getDealDate() == null)
			throw new BusinessValidationException("找不到排查时间");
		if (keyAreasOfInvestigationInfoWorkingRecord.getOrganization() == null)
			throw new BusinessValidationException("找不到指定的网格");
	}
}
