package com.tianque.partyBuilding.organizations.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseServiceImpl;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.OrganizationVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.partyBuilding.organizations.dao.RegionalPartyMemberDao;
import com.tianque.partyBuilding.organizations.domain.RegionalPartyMember;
import com.tianque.partyBuilding.organizations.domain.vo.RegionalPartyMemberVo;
import com.tianque.partyBuilding.organizations.service.RegionalPartyMemberService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 区域党委成员：业务逻辑
 * */
@Repository("regionalPartyMemberService")
public class RegionalPartyMemberServiceImpl extends
		BaseServiceImpl<RegionalPartyMember, RegionalPartyMemberVo> implements
		RegionalPartyMemberService {
	@Autowired
	@Qualifier("regionalPartyMemberValidator")
	private DomainValidator<RegionalPartyMember> domainValidator;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private RegionalPartyMemberDao regionalPartyMemberDao;

	@Resource(name = "regionalPartyMemberDao")
	public void setBaseDao(RegionalPartyMemberDao baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public RegionalPartyMember add(RegionalPartyMember regionalPartyMember) {
		regionalPartyMemberValidator(regionalPartyMember);
		try {
			regionalPartyMember = getBaseDao().add(regionalPartyMember);
			return regionalPartyMember;
		} catch (Exception e) {
			return dealException(this, "add", "新增区域党委成员表信息出现错误", e);
		}
	}

	@Override
	public RegionalPartyMember update(RegionalPartyMember regionalPartyMember) {
		regionalPartyMemberValidator(regionalPartyMember);
		try {
			regionalPartyMember = getBaseDao().update(regionalPartyMember);
			return regionalPartyMember;
		} catch (Exception e) {
			return dealException(this, "update", "更新区域党委成员表信息出现错误", e);
		}
	}

	private void regionalPartyMemberValidator(
			RegionalPartyMember regionalPartyMember) {
		ValidateResult baseDataValidator = domainValidator
				.validate(regionalPartyMember);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}

	}

	@Override
	public PageInfo<RegionalPartyMember> findRegionalPartyMemberPagerBySearchVo(
			RegionalPartyMemberVo regionalPartyMemberVo, Integer pageNum,
			Integer pageSize, String sortField, String sord) {
		try {
			if (regionalPartyMemberVo.getOrganization().getId() == null) {
				throw new BusinessValidationException("参数错误");
			} else {
				regionalPartyMemberVo.setSortField(sortField);
				regionalPartyMemberVo.setOrder(sord);
				Organization organization = organizationDubboService
						.getSimpleOrgById(regionalPartyMemberVo
								.getOrganization().getId());
				if (null == organization) {
					return constructEmptyPageInfo();
				} else {
					PageInfo<RegionalPartyMember> regionalPartyMembers = this.regionalPartyMemberDao
							.findRegionalPartyMemberPagerBySearchVo(
									regionalPartyMemberVo, pageNum, pageSize,
									sortField, sord);

					return regionalPartyMembers;
				}
			}
		} catch (Exception e) {
			return dealException(this,
					"findRegionalPartyMemberPagerBySearchVo", "分页查询区域党委成员出现错误",
					e);
		}
	}

	private PageInfo<RegionalPartyMember> constructEmptyPageInfo() {
		PageInfo<RegionalPartyMember> regionalPartyMemberPageInfo = new PageInfo<RegionalPartyMember>();
		regionalPartyMemberPageInfo
				.setResult(new ArrayList<RegionalPartyMember>());
		return regionalPartyMemberPageInfo;
	}

	@Override
	public Integer countRegionalPartyMemberByOrgIdOrOrgInternalCode(
			Long internalId, String orgInternalCode) {

		try {
			OrganizationVo organizationVo = new OrganizationVo();
			organizationVo.setOrgInternalCode(orgInternalCode);
			organizationVo.setOrgLevelId(internalId);
			List<Long> orgIdsList = organizationDubboService
					.findOrgIdsBySearchVo(organizationVo);
			return regionalPartyMemberDao
					.countRegionalPartyMemberByOrgIdOrOrgInternalCode(orgIdsList);
		} catch (Exception e) {
			return dealException(this,
					"countRegionalPartyMemberByOrgIdOrOrgInternalCode",
					"统计区域党委成员出现错误", e);
		}
	}
}
