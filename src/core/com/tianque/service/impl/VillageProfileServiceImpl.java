package com.tianque.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.dao.VillageProfileDao;
import com.tianque.domain.Organization;
import com.tianque.domain.VillageProfile;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ParameterIllegalException;
import com.tianque.service.VillageProfileService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Service("villageProfileService")
public class VillageProfileServiceImpl extends AbstractBaseService implements
		VillageProfileService {

	@Autowired
	private VillageProfileDao villageProfileDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public VillageProfile addVillageProfile(VillageProfile villageProfile) {
		autoFillOrganizationInternalCode(villageProfile);
		VillageProfile newVillageProfile = villageProfileDao
				.addVillageProfile(villageProfile);
		if (newVillageProfile != null
				&& newVillageProfile.getOrganization() != null
				&& newVillageProfile.getOrganization().getId() != null) {
			newVillageProfile.setOrganization(organizationDubboService
					.getSimpleOrgById(newVillageProfile.getOrganization()
							.getId()));
		}

		return newVillageProfile;
	}

	@Override
	public VillageProfile findVillageProfileById(Long id, Long orgId) {
		VillageProfile newVillageProfile = villageProfileDao
				.findVillageProfileById(id, orgId);
		if (newVillageProfile != null
				&& newVillageProfile.getOrganization() != null
				&& newVillageProfile.getOrganization().getId() != null) {
			newVillageProfile.setOrganization(organizationDubboService
					.getSimpleOrgById(newVillageProfile.getOrganization()
							.getId()));
		}
		return newVillageProfile;
	}

	@Override
	public VillageProfile deletePhotosAndOrgById(String imgUrl, Long id,
			Long orgId) {
		if (imgUrl == null || id == null || orgId == null) {
			throw new ParameterIllegalException("参数错误");
		}
		VillageProfile villageProfile = villageProfileDao
				.findVillageProfileById(id, orgId);
		if (villageProfile != null && villageProfile.getOrganization() != null
				&& villageProfile.getOrganization().getId() != null) {
			villageProfile
					.setOrganization(organizationDubboService
							.getSimpleOrgById(villageProfile.getOrganization()
									.getId()));

		}
		if (("/" + villageProfile.getImgUrl()).equals(imgUrl)) {
			villageProfile.setImgUrl(null);
			villageProfile = villageProfileDao
					.updateVillageProfile(villageProfile);
			if (villageProfile != null
					&& villageProfile.getOrganization() != null
					&& villageProfile.getOrganization().getId() != null) {
				villageProfile.setOrganization(organizationDubboService
						.getSimpleOrgById(villageProfile.getOrganization()
								.getId()));
			}
		}
		return villageProfile;
	}

	@Override
	public VillageProfile updateVillageProfile(VillageProfile villageProfile) {
		VillageProfile newVillageProfile = villageProfileDao
				.updateVillageProfile(villageProfile);
		if (newVillageProfile != null
				&& newVillageProfile.getOrganization() != null
				&& newVillageProfile.getOrganization().getId() != null) {
			newVillageProfile.setOrganization(organizationDubboService
					.getSimpleOrgById(newVillageProfile.getOrganization()
							.getId()));
		}
		return newVillageProfile;
	}

	public void autoFillOrganizationInternalCode(VillageProfile villageProfile) {
		if (villageProfile.getOrganization() == null) {
			throw new BusinessValidationException("找不到指定网格");
		} else {
			Organization organization = organizationDubboService
					.getSimpleOrgById(villageProfile.getOrganization().getId());
			villageProfile
					.setOrgInternalCode(organization.getOrgInternalCode());
		}
	}

	// 没有显式的调用
	// @Override
	// public VillageProfile getVillageProfileByFullPinYin(String fullPinYin) {
	// return villageProfileDao.getVillageProfileByFullPinYin(fullPinYin);
	// }

	@Override
	public void deleteVillageProfile(Long orgId) {
		if (orgId == null) {
			throw new BusinessValidationException("组织机构ID不能为空");
		}
		villageProfileDao.deleteVillageProfile(orgId);
	}

	@Override
	public VillageProfile getVillageProfileByOrgId(Long orgId) {
		if (orgId == null) {
			throw new BusinessValidationException("组织机构ID不能为空");
		}
		VillageProfile newVillageProfile = villageProfileDao
				.getVillageProfileByOrgId(orgId);
		if (newVillageProfile != null) {
			newVillageProfile.setOrganization(organizationDubboService
					.getSimpleOrgById(orgId));
		}
		return newVillageProfile;
	}
}
