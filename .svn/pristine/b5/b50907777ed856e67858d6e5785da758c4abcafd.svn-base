package com.tianque.fourTeams.fourTeamsManage.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.fourTeams.fourTeamsManage.dao.FourTeamsOrgDAO;
import com.tianque.fourTeams.fourTeamsManage.domain.FourTeamsOrg;
import com.tianque.fourTeams.fourTeamsManage.service.FourTeamsOrgService;
import com.tianque.fourTeams.fourTeamsManage.util.FourteamsUitl;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Service("fourTeamsOrgService")
@Transactional
public class FourTeamsOrgServiceImpl extends AbstractBaseService implements
		FourTeamsOrgService {
	@Autowired
	private FourTeamsOrgDAO fourTeamsOrgDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Qualifier("fourTeamsOrgValidator")
	@Autowired
	private DomainValidator<FourTeamsOrg> domainValidator;

	@Override
	public Long addFourTeamsOrg(FourTeamsOrg fourTeamsOrg) {
		return fourTeamsOrgDao.addFourTeamsOrg(fourTeamsOrg);
	}

	@Override
	public void deleteFourTeamsOrgByPrimaryKey(Long id) {
		fourTeamsOrgDao.deleteFourTeamsOrgByPrimaryKey(id);
	}

	@Override
	public FourTeamsOrg getFourTeamsOrgByPrimaryKey(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误！");
		}
		FourTeamsOrg fourTeamsOrg = null;
		try {
			fourTeamsOrg = fourTeamsOrgDao.getFourTeamsOrgByPrimaryKey(id);
		} catch (Exception e) {
			throw new ServiceValidationException("查询出错！", e);
		}
		return fourTeamsOrg;
	}

	@Override
	public List<FourTeamsOrg> queryFourTeamsOrgForList(FourTeamsOrg fourTeamsOrg) {
		if (fourTeamsOrg == null || fourTeamsOrg.getOrganization() == null
				|| fourTeamsOrg.getOrganization().getId() == null) {
			throw new BusinessValidationException("参数错误");
		}
		// 获取到县级别
		Organization org = organizationDubboService
				.getDistrictOrganizationId(fourTeamsOrg.getOrganization()
						.getId());
		fourTeamsOrg.setOrganization(org);
		List<FourTeamsOrg> fourTeamsOrgList = null;
		try {
			fourTeamsOrgList = fourTeamsOrgDao
					.queryFourTeamsOrgForList(fourTeamsOrg);
		} catch (Exception e) {
			throw new ServiceValidationException("查询出错！", e);
		}
		return fourTeamsOrgList;
	}

	@Override
	public void updateFourTeamsOrgByPrimaryKey(FourTeamsOrg fourTeamsOrg) {
		if (fourTeamsOrg == null) {
			throw new BusinessValidationException("参数错误！");
		}
		fourTeamsOrg.setTeamTitle("");
		fourTeamsOrg.setHeadImage("");
		fourTeamsOrg.setUserName("");
		fourTeamsOrg.setPhoneNumber("");
		fourTeamsOrg.setPosition("");
		try {
			fourTeamsOrgDao.updateFourTeamsOrgByPrimaryKey(fourTeamsOrg);
		} catch (Exception e) {
			throw new ServiceValidationException("清空操作出错！", e);
		}
	}

	@Override
	public void updateFourTeamsOrgByPrimaryKeySelective(
			FourTeamsOrg fourTeamsOrg) {
		if (fourTeamsOrg == null) {
			throw new BusinessValidationException("参数错误！");
		}
		ValidateResult validateResult = domainValidator.validate(fourTeamsOrg);
		if (validateResult.hasError()) {
			throw new BusinessValidationException(
					validateResult.getErrorMessages());
		}
		try {
			fourTeamsOrgDao
					.updateFourTeamsOrgByPrimaryKeySelective(fourTeamsOrg);
		} catch (Exception e) {
			throw new ServiceValidationException("编辑出错！", e);
		}
	}

	@Override
	public List<FourTeamsOrg> initFourTeamsOrg(FourTeamsOrg fourTeamsOrg) {
		if (fourTeamsOrg == null || fourTeamsOrg.getOrganization() == null
				|| fourTeamsOrg.getOrganization().getId() == null) {
			throw new BusinessValidationException("参数错误");
		}
		// 获取到县级别
		Organization org = organizationDubboService
				.getDistrictOrganizationId(fourTeamsOrg.getOrganization()
						.getId());
		fourTeamsOrg.setOrganization(org);
		// init the first level
		fourTeamsOrg.setParentId(null);
		fourTeamsOrg.setLeve(FourteamsUitl.LEVEL_TOP);
		fourTeamsOrg.setLeveSort(FourteamsUitl.LEVESORT_ONE);
		Long parentId = addFourTeamsOrg(fourTeamsOrg);
		if (parentId == null) {
			throw new BusinessValidationException("初始化四支队伍架构图出错");
		}
		// init the scend level
		List<Long> sParentIds = new ArrayList<Long>();
		for (int i = 1; i <= FourteamsUitl.LEVESORT_FOUR; i++) {
			fourTeamsOrg.setParentId(parentId);
			fourTeamsOrg.setLeve(FourteamsUitl.LEVEL_MID);
			fourTeamsOrg.setLeveSort(i);
			Long sParentId = addFourTeamsOrg(fourTeamsOrg);
			if (sParentId == null) {
				throw new BusinessValidationException("初始化四支队伍架构图出错");
			}
			sParentIds.add(sParentId);
		}
		// init the last level
		if (sParentIds == null || sParentIds.size() != 4) {
			throw new BusinessValidationException("初始化四支队伍架构图出错");
		}
		for (int i = 1; i <= FourteamsUitl.LEVESORT_FOUR; i++) {
			fourTeamsOrg.setParentId(sParentIds.get(i - 1));
			fourTeamsOrg.setLeve(FourteamsUitl.LEVEL_END);
			fourTeamsOrg.setLeveSort(i);
			Long eParentId = addFourTeamsOrg(fourTeamsOrg);
			if (eParentId == null) {
				throw new BusinessValidationException("初始化四支队伍架构图出错");
			}
		}
		return fourTeamsOrgDao.queryFourTeamsOrgForList(fourTeamsOrg);
	}
}
