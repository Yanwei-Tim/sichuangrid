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
import com.tianque.working.dao.SecurityPropagandaDao;
import com.tianque.working.domain.DailyDirectory;
import com.tianque.working.domain.SecurityPropaganda;
import com.tianque.working.service.DailyAttachFileService;
import com.tianque.working.service.DailyDirectoryService;
import com.tianque.working.service.SecurityPropagandaService;

@Service("securityPropagandaService")
public class SecurityPropagandaServiceImpl implements SecurityPropagandaService {

	@Autowired
	private SecurityPropagandaDao securityPropagandaDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private DailyAttachFileService dailyAttachFileService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private DailyDirectoryService dailyDirectoryService;

	@Override
	public SecurityPropaganda getSecurityPropagandaById(Long id) {
		if (validateId(id)) {
			throw new BusinessValidationException("参数错误");
		}
		return securityPropagandaDao.getSecurityPropagandaById(id);
	}

	@Override
	public SecurityPropaganda addSecurityPropaganda(
			SecurityPropaganda securityPropaganda) {
		if (securityPropaganda == null) {
			throw new BusinessValidationException("参数错误");
		}
		securityPropaganda.setOrgInternalCode(organizationDubboService
				.getSimpleOrgById(securityPropaganda.getOrganization().getId())
				.getOrgInternalCode());
		return securityPropagandaDao.addSecurityPropaganda(securityPropaganda);
	}

	@Override
	public void deleteSecurityPropagandaById(Long id) {
		if (validateId(id)) {
			throw new BusinessValidationException("参数错误");
		}
		securityPropagandaDao.deleteSecurityPropagandaById(id);
	}

	@Override
	public SecurityPropaganda updateSecurityPropaganda(
			SecurityPropaganda securityPropaganda) {
		if (securityPropaganda == null || securityPropaganda.getId() == null
				|| securityPropaganda.getId().intValue() == 0) {
			throw new BusinessValidationException("参数错误");
		}
		return securityPropagandaDao
				.updateSecurityPropaganda(securityPropaganda);
	}

	@Override
	public PageInfo<SecurityPropaganda> findSecurityPropagandasForPageByOrgIdAndDailyDirectoryId(
			Long dailyDirectoryId, Long orgId, Integer page, Integer rows,
			String sidx, String sord) {
		if (orgId == null || orgId.intValue() == 0) {
			throw new BusinessValidationException("参数错误");
		}
		return appendAttachFile(securityPropagandaDao
				.findSecurityPropagandasForPageByOrgIdAndDailyDirectoryId(
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
						DailyDirectoryTypes.SECURITY_PROPAGANDA_NAME).getId();
	}

	private PageInfo<SecurityPropaganda> appendAttachFile(
			PageInfo<SecurityPropaganda> pageInfo) {
		for (SecurityPropaganda securityPropaganda : pageInfo.getResult()) {
			securityPropaganda
					.setDailyAttachFiles(dailyAttachFileService
							.getSimpleDailyAttachFilesByDailyIdAndDailyDirectoryType(
									securityPropaganda.getId(),
									getDailyDirectoryType()));
		}
		return pageInfo;

	}

	private boolean validateId(Long id) {
		return id == null || id.intValue() == 0;
	}

}
