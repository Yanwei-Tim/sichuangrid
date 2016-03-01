package com.tianque.partyBuilding.organizations.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseServiceImpl;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.vo.OrganizationVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.partyBuilding.activityRecords.constant.OrganizationType;
import com.tianque.partyBuilding.activityRecords.service.ActivityRecordsService;
import com.tianque.partyBuilding.members.constant.MemberType;
import com.tianque.partyBuilding.members.service.MemberAssociatePartyOrgService;
import com.tianque.partyBuilding.organizations.dao.NewPartyOrgDao;
import com.tianque.partyBuilding.organizations.domain.NewPartyOrg;
import com.tianque.partyBuilding.organizations.domain.StatisticParty;
import com.tianque.partyBuilding.organizations.domain.vo.SearchNewPartyOrgVo;
import com.tianque.partyBuilding.organizations.service.NewPartyOrgService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

/**
 * 两新组织党组织表:业务逻辑层
 * 
 * @author
 * @date 2014-01-14 15:44:08
 */
@Repository("newPartyOrgService")
public class NewPartyOrgServiceImpl extends
		BaseServiceImpl<NewPartyOrg, SearchNewPartyOrgVo> implements
		NewPartyOrgService {

	@Autowired
	@Qualifier("newPartyOrgValidator")
	private DomainValidator<NewPartyOrg> domainValidator;
	@Autowired
	private NewPartyOrgDao newPartyOrgDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private MemberAssociatePartyOrgService memberAssociatePartyOrgService;
	@Autowired
	private ActivityRecordsService activityRecordsService;
	@Autowired
	PropertyDictService propertyDictService;

	@Resource(name = "newPartyOrgDao")
	public void setBaseDao(NewPartyOrgDao baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public NewPartyOrg add(NewPartyOrg newPartyOrg) {
		newPartyOrgValidator(newPartyOrg);
		try {
			newPartyOrg = getBaseDao().add(newPartyOrg);
			return newPartyOrg;
		} catch (Exception e) {
			return dealException(this.getClass().getSimpleName(), "add",
					"新增两新组织党组织表信息出现错误", e);
		}
	}

	@Override
	public NewPartyOrg update(NewPartyOrg newPartyOrg) {
		newPartyOrgValidator(newPartyOrg);
		try {
			newPartyOrg = getBaseDao().update(newPartyOrg);
			return newPartyOrg;
		} catch (Exception e) {
			return dealException(this.getClass().getSimpleName(), "update",
					"更新两新组织党组织表信息出现错误", e);
		}
	}

	@Override
	public PageInfo<NewPartyOrg> findPagerBySearchVo(
			SearchNewPartyOrgVo searchVo, Integer pageNum, Integer pageSize,
			String sidx, String sord) {

		PageInfo<NewPartyOrg> pageInfo = newPartyOrgDao.findPagerBySearchVo(
				searchVo, pageNum, pageSize, sidx, sord);
		NewPartyOrg newPartyOrg;
		for (int i = 0; pageInfo != null && i < pageInfo.getResult().size(); i++) {
			newPartyOrg = pageInfo.getResult().get(i);
			pageInfo.getResult()
					.get(i)
					.setMemberNum(
							memberAssociatePartyOrgService
									.countByPartyOrgTypeAndPartyOrgAndOrgId(
											MemberType.TWO_NEW_ORGNIZATION_PARTY_ORG,
											newPartyOrg.getOrganization()
													.getId(), newPartyOrg
													.getName()));
		}
		return pageInfo;
	}

	private void newPartyOrgValidator(NewPartyOrg newPartyOrg) {
		ValidateResult baseDataValidator = domainValidator
				.validate(newPartyOrg);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		} else {
			SearchNewPartyOrgVo searchVo = new SearchNewPartyOrgVo();
			searchVo.setOrgId(newPartyOrg.getOrganization().getId());
			searchVo.setName(newPartyOrg.getName());
			searchVo.setId(newPartyOrg.getId());
			if (hasDuplicate(searchVo)) {
				throw new BusinessValidationException("此名称在系统中已经存在");
			}
		}
	}

	@Override
	public PageInfo<StatisticParty> statisticBySearchVo(
			SearchNewPartyOrgVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		try {
			List<StatisticParty> statistics = new ArrayList<StatisticParty>();
			/** 网格分级 */
			List<PropertyDict> orgLevels = propertyDictService
					.findPropertyDictByDomainName(PropertyTypes.ORGANIZATION_LEVEL);
			Map<Integer, Long> orgLevelMap = new HashMap<Integer, Long>();
			if (orgLevels != null && orgLevels.size() > 0) {
				for (PropertyDict orgLevel : orgLevels) {
					orgLevelMap.put(orgLevel.getInternalId(), orgLevel.getId());
				}
			}
			StatisticParty statisticParty;
			Map<String, Integer> map;
			List<Organization> orgList = organizationDubboService
					.findAdminOrgsByParentId(searchVo.getOrgId());
			for (Organization org : orgList) {
				statisticParty = new StatisticParty();
				statisticParty.setOrganization(org);
				map = statisticParty(
						orgLevelMap.get(OrganizationLevel.VILLAGE),
						org.getOrgInternalCode());
				statisticParty.setVillageOrgNum(map.get("org"));
				statisticParty.setVillageMemberNum(map.get("member"));
				statisticParty.setVillageRecordNum(map.get("record"));
				statistics.add(statisticParty);
			}
			return new PageInfo<StatisticParty>(page, rows, statistics.size(),
					statistics);
		} catch (Exception e) {
			return dealException(this.getClass().getSimpleName(),
					"statisticBySearchVo", "统计两新组织党组织信息出现错误", e);
		}
	}

	/**
	 * 根据组织机构的分级和组织机构code【没有排除职能部门】
	 * 
	 * @param InternalId
	 * @param orgInternalCode
	 * @return
	 */
	private Map<String, Integer> statisticParty(Long internalId,
			String orgInternalCode) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		OrganizationVo orgVo = new OrganizationVo();
		orgVo.setOrgInternalCode(orgInternalCode);
		orgVo.setOrgLevelId(internalId);
		List<Long> orgIdList = organizationDubboService.findOrgIdsBySearchVo(orgVo);
		map.put("org", newPartyOrgDao.countByOrg(orgIdList));
		map.put("member", memberAssociatePartyOrgService
				.getMemberNumByPartyOrgTypeAndOrgIdOrOrgCode(
						MemberType.TWO_NEW_ORGNIZATION_PARTY_ORG, internalId,
						orgInternalCode));
		map.put("record", activityRecordsService
				.countActivityRecordByOrgIdOrgInternalCode(internalId,
						orgInternalCode,
						OrganizationType.NEW_PARTY_ORGANIZATION));
		return map;
	}

	@Override
	public Boolean hasDuplicate(SearchNewPartyOrgVo searchVo) {
		try {
			NewPartyOrg newPartyOrg = newPartyOrgDao.getByOrgIdAndName(
					searchVo.getOrgId(), searchVo.getName());
			return (newPartyOrg == null) ? false : (newPartyOrg.getId().equals(
					searchVo.getId()) ? false : true);
		} catch (Exception e) {
			return dealException(this.getClass().getSimpleName(),
					"hasDuplicate", "唯一性校验出现错误", e);

		}
	}

}
