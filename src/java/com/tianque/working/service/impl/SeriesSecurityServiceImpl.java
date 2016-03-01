package com.tianque.working.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.property.DailyDirectoryTypes;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.working.dao.SeriesSecurityDao;
import com.tianque.working.domain.DailyDirectory;
import com.tianque.working.domain.SeriesSecurity;
import com.tianque.working.service.DailyAttachFileService;
import com.tianque.working.service.DailyDirectoryService;
import com.tianque.working.service.SeriesSecurityService;

@Service("seriesSecurityService")
public class SeriesSecurityServiceImpl implements SeriesSecurityService {

	@Autowired
	private SeriesSecurityDao seriesSecurityDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private DailyAttachFileService dailyAttachFileService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private DailyDirectoryService dailyDirectoryService;

	@Override
	public SeriesSecurity getSeriesSecurityById(Long id) {
		if (validateId(id)) {
			throw new BusinessValidationException("参数错误");
		}
		return seriesSecurityDao.getSeriesSecurityById(id);
	}

	@Override
	public SeriesSecurity addSeriesSecurity(SeriesSecurity seriesSecurity) {
		if (seriesSecurity == null) {
			throw new BusinessValidationException("参数错误");
		}
		seriesSecurity.setOrgInternalCode(organizationDubboService.getSimpleOrgById(
				seriesSecurity.getOrganization().getId()).getOrgInternalCode());
		return seriesSecurityDao.addSeriesSecurity(seriesSecurity);
	}

	@Override
	public void deleteSeriesSecurityById(Long id) {
		if (validateId(id)) {
			throw new BusinessValidationException("参数错误");
		}
		seriesSecurityDao.deleteSeriesSecurityById(id);
	}

	@Override
	public SeriesSecurity updateSeriesSecurity(SeriesSecurity seriesSecurity) {
		if (seriesSecurity == null || seriesSecurity.getId() == null
				|| seriesSecurity.getId().intValue() == 0) {
			throw new BusinessValidationException("参数错误");
		}
		return seriesSecurityDao.updateSeriesSecurity(seriesSecurity);
	}

	@Override
	public PageInfo<SeriesSecurity> findSeriesSecuritysForPageByOrgIdAndDailyDirectoryId(
			Long dailyDirectoryId, Long orgId, Integer page, Integer rows,
			String sidx, String sord) {
		if (orgId == null || orgId.intValue() == 0) {
			throw new BusinessValidationException("参数错误");
		}
		return appendAttachFile(seriesSecurityDao
				.findSeriesSecuritysForPageByOrgIdAndDailyDirectoryId(
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
						DailyDirectoryTypes.SERIES_SECURITY_NAME).getId();
	}

	private PageInfo<SeriesSecurity> appendAttachFile(
			PageInfo<SeriesSecurity> pageInfo) {
		for (SeriesSecurity seriesSecurity : pageInfo.getResult()) {
			seriesSecurity.setDailyAttachFiles(dailyAttachFileService
					.getSimpleDailyAttachFilesByDailyIdAndDailyDirectoryType(
							seriesSecurity.getId(), getDailyDirectoryType()));
		}
		return pageInfo;

	}

	private boolean validateId(Long id) {
		return id == null || id.intValue() == 0;
	}

}
