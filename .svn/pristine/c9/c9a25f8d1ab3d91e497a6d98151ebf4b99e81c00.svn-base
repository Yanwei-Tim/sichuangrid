package com.tianque.partyBuilding.organsParty.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseServiceImpl;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.partyBuilding.members.constant.MemberType;
import com.tianque.partyBuilding.members.service.MemberAssociatePartyOrgService;
import com.tianque.partyBuilding.organsParty.dao.OrgansPartyDao;
import com.tianque.partyBuilding.organsParty.domain.OrgansParty;
import com.tianque.partyBuilding.organsParty.domain.vo.SearchOrgansPartyVo;
import com.tianque.partyBuilding.organsParty.service.OrgansPartyService;
import com.tianque.sysadmin.service.PropertyDictService;

/**
 * 机关党组织表:业务逻辑层
 * 
 * @author
 * @date 2014-02-10 16:00:06
 */
@Repository("organsPartyService")
public class OrgansPartyServiceImpl extends
		BaseServiceImpl<OrgansParty, SearchOrgansPartyVo> implements
		OrgansPartyService {

	@Autowired
	@Qualifier("organsPartyValidator")
	private DomainValidator<OrgansParty> domainValidator;
	@Autowired
	private OrgansPartyDao organsPartyDao;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private MemberAssociatePartyOrgService memberAssociatePartyOrgService;

	@Resource(name = "organsPartyDao")
	public void setBaseDao(OrgansPartyDao baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public OrgansParty add(OrgansParty organsParty) {
		organsPartyValidator(organsParty);
		try {
			organsParty.setOrgid(ThreadVariable.getOrganization().getId());
			organsParty.setOrginternalcode(ThreadVariable.getOrganization()
					.getOrgInternalCode());
			if (!checkOrgansParty(organsParty)) {
				throw new BusinessValidationException("已有相同名称的党政机关存在");
			}
			organsParty = getBaseDao().add(organsParty);
			return organsParty;
		} catch (Exception e) {
			return dealException(this, "add", "新增机关党组织表信息出现错误", e);
		}
	}

	@Override
	public OrgansParty update(OrgansParty organsParty) {
		organsPartyValidator(organsParty);
		if (!checkOrgansParty(organsParty)) {
			throw new BusinessValidationException("已有相同名称的党政机关存在");
		}
		try {
			organsParty.setOrgid(ThreadVariable.getOrganization().getId());
			organsParty.setOrginternalcode(ThreadVariable.getOrganization()
					.getOrgInternalCode());
			organsParty = getBaseDao().update(organsParty);
			return organsParty;
		} catch (Exception e) {
			return dealException(this, "update", "更新机关党组织表信息出现错误", e);
		}
	}

	// 验证机关党组织是否唯一
	@Override
	public boolean checkOrgansParty(OrgansParty organsParty) {
		if (organsParty == null) {
			throw new BusinessValidationException("操作失败，请于管理员联系");
		}
		Long id = organsPartyDao.getOrgansPartyByName(organsParty.getName(),
				ThreadVariable.getOrganization().getId());
		if (id == null) {
			return true;
		}
		if (id != null && organsParty.getId() == null) {
			return false;
		}
		if (organsParty.getId() != null && !organsParty.getId().equals(id)) {
			return false;
		} else {
			return true;
		}
	}

	private void organsPartyValidator(OrgansParty organsParty) {
		ValidateResult baseDataValidator = domainValidator
				.validate(organsParty);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

	@Override
	public PageInfo<OrgansParty> findPagerBySearchVo(
			SearchOrgansPartyVo searchVo, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		PageInfo<OrgansParty> pageInfo = organsPartyDao.findPagerBySearchVo(
				searchVo, pageNum, pageSize, sidx, sord);
		List<PropertyDict> dicts = propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.PARTYORGTYPE);
		PropertyDict partyCommitteeDict = null;// 党委字典项
		PropertyDict generalPartyDict = null;// 党总支字典项
		PropertyDict partyBranchDict = null;// 党支部 字典项
		for (PropertyDict dict : dicts) {
			if (dict.getDisplayName().equals("党委")) {
				partyCommitteeDict = dict;
			} else if (dict.getDisplayName().equals("党总支")) {
				generalPartyDict = dict;
			} else if (dict.getDisplayName().equals("党支部")) {
				partyBranchDict = dict;
			}
		}
		OrgansParty organsParty;
		for (int i = 0; pageInfo != null && i < pageInfo.getResult().size(); i++) {
			organsParty = pageInfo.getResult().get(i);
			if (organsParty.getSuperior() != null) {
				organsParty.setSuperior(organsPartyDao.get(organsParty
						.getSuperior().getId()));
			}
			organsParty.setMemberCount(memberAssociatePartyOrgService
					.countByPartyOrgTypeAndPartyOrgAndOrgId(
							MemberType.OFFICE_PARTY_ORG,
							organsParty.getOrgid(), organsParty.getName()));
			if (organsParty.getType().getId()
					.equals(partyCommitteeDict.getId())) {
				Integer generalPartyCount = organsPartyDao
						.getOrgansPartyCountByOrgIdAndSuperiorAndType(
								ThreadVariable.getOrganization().getId(),
								organsParty.getId(), generalPartyDict);
				Integer partyBranchCount = organsPartyDao
						.getOrgansPartyCountByOrgIdAndSuperiorAndType(
								ThreadVariable.getOrganization().getId(),
								organsParty.getId(), partyBranchDict);
				organsParty.setJuniorParty("党总支：" + generalPartyCount + "个；"
						+ "党支部：" + partyBranchCount + "个");
			} else if (organsParty.getType().getId()
					.equals(generalPartyDict.getId())) {
				Integer partyBranchCount = organsPartyDao
						.getOrgansPartyCountByOrgIdAndSuperiorAndType(
								ThreadVariable.getOrganization().getId(),
								organsParty.getId(), partyBranchDict);
				organsParty.setJuniorParty("党支部：" + partyBranchCount + "个");
			}
		}
		return pageInfo;
	}
}
