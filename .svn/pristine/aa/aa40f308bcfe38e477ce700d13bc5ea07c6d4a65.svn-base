package com.tianque.partyBuilding.systemPartys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.CollectionUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.core.vo.Pager;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.partyBuilding.members.service.MemberAssociatePartyOrgService;
import com.tianque.partyBuilding.systemPartys.dao.SystemPartyDao;
import com.tianque.partyBuilding.systemPartys.domain.SystemParty;

@Transactional
@Service("systemPartyService")
public class SystemPartyServiceImpl implements SystemPartyService {
	@Autowired
	private SystemPartyDao systemPartyDao;
	@Autowired
	private MemberAssociatePartyOrgService associatePartyOrgService;

	@Override
	public SystemParty addSystemParty(SystemParty systemParty) {
		if (systemParty == null) {
			throw new BusinessValidationException("党组织为空，新增党组织失败");
		}
		autoFillOrg(systemParty);
		systemParty = systemPartyDao.addSystemParty(systemParty);
		return systemParty;
	}

	private void autoFillOrg(SystemParty systemParty) {
		systemParty.setOrganization(ThreadVariable.getOrganization());
		systemParty.setOrgInternalCode(ThreadVariable.getOrganization()
				.getOrgInternalCode());
	}

	@Override
	public SystemParty updateSystemParty(SystemParty systemParty) {
		if (systemParty == null) {
			throw new BusinessValidationException("党组织为空，新增党组织失败");
		}
		systemParty = systemPartyDao.updateSystemParty(systemParty);
		return systemParty;
	}

	@Override
	public SystemParty getSystemPartyById(SystemParty systemParty) {
		if (systemParty == null || systemParty.getId() == null) {
			throw new BusinessValidationException("获取党组织失败");
		}
		return systemPartyDao.getSystemPartyById(systemParty.getId());
	}

	@Override
	public boolean deleteSystemPartyByIds(Integer partyOrgType, List<Long> ids) {
		if (!CollectionUtil.isAvaliable(ids)) {
			throw new BusinessValidationException("删除党组织失败");
		}
		return systemPartyDao.deleteSystemPartyByIds(partyOrgType, ids);
	}

	@Override
	public PageInfo<SystemParty> findSystemPartysForPage(
			SystemParty systemParty, Pager pager) {
		if (systemParty.getPartyOrgType() == null) {
			throw new BusinessValidationException("查询党组织失败");
		}
		PageInfo<SystemParty> pageInfo = systemPartyDao
				.findSystemPartysForPage(systemParty, pager);

		for (SystemParty party : pageInfo.getResult()) {
			party.setPartyMemeberSum(associatePartyOrgService
					.countByPartyOrgTypeAndPartyOrgAndOrgId(systemParty
							.getPartyOrgType(), ThreadVariable
							.getOrganization().getId(), party.getPartyName()));
		}
		return pageInfo;
	}

	@Override
	public boolean isExsistedSystemPartyOrg(String partyOrgName,
			Integer partyOrgType) {
		if (!StringUtil.isStringAvaliable(partyOrgName) || partyOrgType == null
				|| partyOrgType < 0) {
			throw new BusinessValidationException("查询参数错误");
		}
		return systemPartyDao.isExsistedSystemPartyOrg(partyOrgName,
				partyOrgType);
	}
}
