package com.tianque.working.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.property.DailyDirectoryTypes;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.working.dao.GridManagementNormalDao;
import com.tianque.working.domain.DailyDirectory;
import com.tianque.working.domain.GridManagementNormal;
import com.tianque.working.service.DailyAttachFileService;
import com.tianque.working.service.DailyDirectoryService;
import com.tianque.working.service.GridManagementNormalService;

@Service("gridManagementNormalService")
public class GridManagementNormalServiceImpl implements
		GridManagementNormalService {

	@Autowired
	private GridManagementNormalDao gridManagementNormalDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private DailyAttachFileService dailyAttachFileService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private DailyDirectoryService dailyDirectoryService;

	@Override
	public GridManagementNormal getGridManagementNormalById(Long id) {
		if (validateId(id)) {
			throw new BusinessValidationException("参数错误");
		}
		return gridManagementNormalDao.getGridManagementNormalById(id);
	}

	@Override
	public GridManagementNormal addGridManagementNormal(
			GridManagementNormal gridManagementNormal) {
		if (gridManagementNormal == null) {
			throw new BusinessValidationException("参数错误");
		}
		gridManagementNormal = validateWhenAdd(gridManagementNormal);
		gridManagementNormal.setOrgInternalCode(organizationDubboService
				.getSimpleOrgById(
						gridManagementNormal.getOrganization().getId())
				.getOrgInternalCode());
		return gridManagementNormalDao
				.addGridManagementNormal(gridManagementNormal);
	}

	private GridManagementNormal validateWhenAdd(
			GridManagementNormal gridManagementNormal) {
		if (gridManagementNormal != null
				&& gridManagementNormal.getDailyDirectoryId() != null) {
			DailyDirectory dailyDirectory = dailyDirectoryService
					.getFullDailyDirectoryById(gridManagementNormal
							.getDailyDirectoryId());
			if (dailyDirectory.getEffectiveDays() != null) {
				Date dateNow = new Date();
				Date activityDate = gridManagementNormal.getActivityDate();
				if (activityDate != null) {
					if (getQuot(dateNow, activityDate) > dailyDirectory
							.getEffectiveDays()) {
						gridManagementNormal.setExpiredEntering(1L);// 1超期录入
					} else {
						gridManagementNormal.setExpiredEntering(0L);
					}
				}
			}
		}
		return gridManagementNormal;
	}

	private int getQuot(Date date1, Date date2) {
		long quotTime = 0;
		quotTime = date1.getTime() - date2.getTime();
		quotTime = quotTime / 1000 / 60 / 60 / 24;
		return (int) quotTime;
	}

	@Override
	public void deleteGridManagementNormalById(Long id) {
		if (validateId(id)) {
			throw new BusinessValidationException("参数错误");
		}
		gridManagementNormalDao.deleteGridManagementNormalById(id);
	}

	@Override
	public GridManagementNormal updateGridManagementNormal(
			GridManagementNormal gridManagementNormal) {
		if (gridManagementNormal == null
				|| gridManagementNormal.getId() == null
				|| gridManagementNormal.getId().intValue() == 0) {
			throw new BusinessValidationException("参数错误");
		}
		gridManagementNormal = validateWhenAdd(gridManagementNormal);
		return gridManagementNormalDao
				.updateGridManagementNormal(gridManagementNormal);
	}

	@Override
	public PageInfo<GridManagementNormal> findGridManagementNormalsForPageByOrgIdAndDailyDirectoryId(
			Long dailyDirectoryId, Long orgId, Integer page, Integer rows,
			String sidx, String sord) {
		if (orgId == null || orgId.intValue() == 0) {
			throw new BusinessValidationException("参数错误");
		}
		return appendAttachFile(gridManagementNormalDao
				.findGridManagementNormalsForPageByOrgIdAndDailyDirectoryId(
						buildDailyDirectoryIds(dailyDirectoryId), orgId, page,
						rows, sidx, sord));
	}

	private String buildDailyDirectoryIds(Long dailyDirectoryId) {
		String allDailyDirectoryId = "";
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
		return allDailyDirectoryId;
	}

	@Override
	public Long getDailyDirectoryType() {
		return propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						PropertyTypes.DAILYDIRECTORY_TYPE,
						DailyDirectoryTypes.GRID_MANAGEMENT_NORMAL_NAME)
				.getId();
	}

	private PageInfo<GridManagementNormal> appendAttachFile(
			PageInfo<GridManagementNormal> pageInfo) {
		for (GridManagementNormal gridManagementNormal : pageInfo.getResult()) {
			gridManagementNormal.setDailyAttachFiles(dailyAttachFileService
					.getSimpleDailyAttachFilesByDailyIdAndDailyDirectoryType(
							gridManagementNormal.getId(),
							getDailyDirectoryType()));
		}
		return pageInfo;

	}

	private boolean validateId(Long id) {
		return id == null || id.intValue() == 0;
	}

}
