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
import com.tianque.partyBuilding.organizations.dao.TownPartyOrgDao;
import com.tianque.partyBuilding.organizations.domain.StatisticParty;
import com.tianque.partyBuilding.organizations.domain.TownPartyOrg;
import com.tianque.partyBuilding.organizations.domain.vo.SearchTownPartyOrgVo;
import com.tianque.partyBuilding.organizations.service.PartyOrgMemberService;
import com.tianque.partyBuilding.organizations.service.TownPartyOrgService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

/**
 * 街道社区党组织表:业务逻辑层
 * 
 * @author
 * @date 2014-01-14 14:35:25
 */
@Repository("townPartyOrgService")
public class TownPartyOrgServiceImpl extends
		BaseServiceImpl<TownPartyOrg, SearchTownPartyOrgVo> implements
		TownPartyOrgService {

	@Autowired
	@Qualifier("townPartyOrgValidator")
	private DomainValidator<TownPartyOrg> domainValidator;
	@Autowired
	private TownPartyOrgDao townPartyOrgDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PartyOrgMemberService partyOrgMemberService;
	@Autowired
	private MemberAssociatePartyOrgService memberAssociatePartyOrgService;
	@Autowired
	private ActivityRecordsService activityRecordsService;
	@Autowired
	PropertyDictService propertyDictService;

	@Resource(name = "townPartyOrgDao")
	public void setBaseDao(TownPartyOrgDao baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public TownPartyOrg add(TownPartyOrg townPartyOrg) {
		townPartyOrgValidator(townPartyOrg);
		try {
			TownPartyOrg result = getBaseDao().add(townPartyOrg);
			partyOrgMemberService.editPartyOrgMembers(result.getId(),
					townPartyOrg.getMembers());
			result.setMembers(partyOrgMemberService.getByPartyOrgId(result
					.getId()));
			return result;
		} catch (Exception e) {
			return dealException(this, "add", "新增街道社区党组织表信息出现错误", e);
		}
	}

	@Override
	public TownPartyOrg update(TownPartyOrg townPartyOrg) {
		townPartyOrgValidator(townPartyOrg);
		try {
			TownPartyOrg result = getBaseDao().update(townPartyOrg);
			partyOrgMemberService.editPartyOrgMembers(result.getId(),
					townPartyOrg.getMembers());
			result.setMembers(partyOrgMemberService.getByPartyOrgId(result
					.getId()));
			return result;
		} catch (Exception e) {
			return dealException(this, "update", "更新街道社区党组织表信息出现错误", e);
		}
	}

	@Override
	public TownPartyOrg get(Long id) {
		if (id == null) {
			return null;
		}
		TownPartyOrg townPartyOrg = getBaseDao().get(id);
		townPartyOrg.setMembers(partyOrgMemberService.getByPartyOrgId(id));
		return townPartyOrg;
	}

	@Override
	public PageInfo<TownPartyOrg> findPagerBySearchVo(
			SearchTownPartyOrgVo searchVo, Integer pageNum, Integer pageSize,
			String sidx, String sord) {

		PageInfo<TownPartyOrg> pageInfo = getBaseDao().findPagerBySearchVo(
				searchVo, pageNum, pageSize, sidx, sord);
		for (int i = 0; i < pageInfo.getResult().size(); i++) {
			TownPartyOrg townPartyOrg = pageInfo.getResult().get(i);
			pageInfo.getResult()
					.get(i)
					.setMembers(
							partyOrgMemberService.getByPartyOrgId(townPartyOrg
									.getId()));
			pageInfo.getResult()
					.get(i)
					.setMemberNum(
							memberAssociatePartyOrgService
									.countByPartyOrgTypeAndPartyOrgAndOrgId(
											MemberType.TOWN_PARTY_ORG,
											townPartyOrg.getOrganization()
													.getId(), townPartyOrg
													.getName()));
		}
		return pageInfo;
	}

	private void townPartyOrgValidator(TownPartyOrg townPartyOrg) {
		ValidateResult baseDataValidator = domainValidator
				.validate(townPartyOrg);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		} else {
			SearchTownPartyOrgVo searchVo = new SearchTownPartyOrgVo();
			searchVo.setOrgId(townPartyOrg.getOrganization().getId());
			searchVo.setName(townPartyOrg.getName());
			searchVo.setId(townPartyOrg.getId());
			if (hasDuplicate(searchVo)) {
				throw new BusinessValidationException("此名称在系统中已经存在");
			}
		}
	}

	@Override
	public PageInfo statisticBySearchVo(SearchTownPartyOrgVo searchVo,
			Integer page, Integer rows, String sidx, String sord) {
		try {
			List<StatisticParty> statistics = new ArrayList<StatisticParty>();
			StatisticParty statisticParty;
			List<Organization> orgList = organizationDubboService
					.findAdminOrgsByParentId(searchVo.getOrgId());
			Organization organization = organizationDubboService
					.getFullOrgById(searchVo.getOrgId());
			/** 网格分级 */
			List<PropertyDict> orgLevels = propertyDictService
					.findPropertyDictByDomainName(PropertyTypes.ORGANIZATION_LEVEL);
			Map<Integer, Long> orgLevelMap = new HashMap<Integer, Long>();
			if (orgLevels != null && orgLevels.size() > 0) {
				for (PropertyDict orgLevel : orgLevels) {
					orgLevelMap.put(orgLevel.getInternalId(), orgLevel.getId());
				}
			}
			Map<String, Integer> map;
			for (Organization org : orgList) {
				statisticParty = new StatisticParty();
				statisticParty.setOrganization(org);
				if (organization.getOrgLevel().getInternalId() > OrganizationLevel.TOWN) {
					map = statisticParty(
							orgLevelMap.get(OrganizationLevel.TOWN),
							org.getOrgInternalCode());
					statisticParty.setTownOrgNum(map.get("org"));
					statisticParty.setTownMemberNum(map.get("member"));
					statisticParty.setTownRecordNum(map.get("record"));
					map = statisticParty(
							orgLevelMap.get(OrganizationLevel.VILLAGE),
							org.getOrgInternalCode());
				} else {
					map = statisticParty(
							orgLevelMap.get(OrganizationLevel.VILLAGE),
							org.getOrgInternalCode());
				}
				statisticParty.setVillageOrgNum(map.get("org"));
				statisticParty.setVillageMemberNum(map.get("member"));
				statisticParty.setVillageRecordNum(map.get("record"));
				statistics.add(statisticParty);
			}
			return new PageInfo<StatisticParty>(page, rows, statistics.size(),
					statistics);
		} catch (Exception e) {
			return dealException(this, "statisticByOrgForVillage",
					"统计街道社区党组织表信息出现错误", e);
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
		OrganizationVo organizationVo = new OrganizationVo();
		organizationVo.setOrgInternalCode(orgInternalCode);
		organizationVo.setOrgLevelId(internalId);
		List<Long> orgIdsList = organizationDubboService
				.findOrgIdsBySearchVo(organizationVo);
		map.put("org", townPartyOrgDao.countByOrg(orgIdsList));
		map.put("member", memberAssociatePartyOrgService
				.getMemberNumByPartyOrgTypeAndOrgIdOrOrgCode(
						MemberType.TOWN_PARTY_ORG, internalId, orgInternalCode));
		map.put("record", activityRecordsService
				.countActivityRecordByOrgIdOrgInternalCode(internalId,
						orgInternalCode,
						OrganizationType.TOWN_PARTY_ORGANIZATION));
		return map;
	}

	@Override
	public Boolean hasDuplicate(SearchTownPartyOrgVo searchVo) {
		try {
			TownPartyOrg townPartyOrg = townPartyOrgDao.getByOrgIdAndName(
					searchVo.getOrgId(), searchVo.getName());
			return (townPartyOrg == null) ? false : (townPartyOrg.getId()
					.equals(searchVo.getId()) ? false : true);
		} catch (Exception e) {
			return dealException(this, "hasDuplicate", "街道社区党组织唯一性校验出现错误", e);
		}
	}
}
