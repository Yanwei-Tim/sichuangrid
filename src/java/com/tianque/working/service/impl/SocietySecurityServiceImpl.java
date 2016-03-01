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
import com.tianque.working.dao.SocietySecurityDao;
import com.tianque.working.domain.DailyDirectory;
import com.tianque.working.domain.SocietySecurity;
import com.tianque.working.service.DailyAttachFileService;
import com.tianque.working.service.DailyDirectoryService;
import com.tianque.working.service.SocietySecurityService;

@Service("societySecurityService")
public class SocietySecurityServiceImpl implements SocietySecurityService {

	@Autowired
	private SocietySecurityDao societySecurityDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private DailyAttachFileService dailyAttachFileService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private DailyDirectoryService dailyDirectoryService;

	@Override
	public SocietySecurity getSocietySecurityById(Long id) {
		if (validateId(id)) {
			throw new BusinessValidationException("参数错误");
		}
		return societySecurityDao.getSocietySecurityById(id);
	}

	@Override
	public SocietySecurity addSocietySecurity(SocietySecurity societySecurity) {
		if (societySecurity == null) {
			throw new BusinessValidationException("参数错误");
		}
		societySecurity.setOrgInternalCode(organizationDubboService
				.getSimpleOrgById(societySecurity.getOrganization().getId())
				.getOrgInternalCode());
		return societySecurityDao.addSocietySecurity(societySecurity);
	}

	@Override
	public void deleteSocietySecurityById(Long id) {
		if (validateId(id)) {
			throw new BusinessValidationException("参数错误");
		}
		societySecurityDao.deleteSocietySecurityById(id);
	}

	@Override
	public SocietySecurity updateSocietySecurity(SocietySecurity societySecurity) {
		if (societySecurity == null || societySecurity.getId() == null
				|| societySecurity.getId().intValue() == 0) {
			throw new BusinessValidationException("参数错误");
		}
		return societySecurityDao.updateSocietySecurity(societySecurity);
	}

	@Override
	public PageInfo<SocietySecurity> findSocietySecuritysForPageByOrgIdAndDailyDirectoryId(
			Long dailyDirectoryId, Long orgId, Integer page, Integer rows,
			String sidx, String sord) {
		if (orgId == null || orgId.intValue() == 0) {
			throw new BusinessValidationException("参数错误");
		}
		return appendAttachFile(societySecurityDao
				.findSocietySecuritysForPageByOrgIdAndDailyDirectoryId(
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
						DailyDirectoryTypes.SOCIETY_SECURITY_NAME).getId();
	}

	private PageInfo<SocietySecurity> appendAttachFile(
			PageInfo<SocietySecurity> pageInfo) {
		for (SocietySecurity societySecurity : pageInfo.getResult()) {
			societySecurity.setDailyAttachFiles(dailyAttachFileService
					.getSimpleDailyAttachFilesByDailyIdAndDailyDirectoryType(
							societySecurity.getId(), getDailyDirectoryType()));
		}
		return pageInfo;

	}

	private boolean validateId(Long id) {
		return id == null || id.intValue() == 0;
	}

}
