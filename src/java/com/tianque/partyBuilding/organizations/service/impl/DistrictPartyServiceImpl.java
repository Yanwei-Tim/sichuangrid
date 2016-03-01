package com.tianque.partyBuilding.organizations.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.partyBuilding.activityRecords.constant.OrganizationType;
import com.tianque.partyBuilding.activityRecords.service.ActivityRecordsService;
import com.tianque.partyBuilding.organizations.domain.DistrictPartyStatistics;
import com.tianque.partyBuilding.organizations.service.DistrictPartyService;
import com.tianque.partyBuilding.organizations.service.PartyresourceService;
import com.tianque.partyBuilding.organizations.service.RegionalBuildInfoService;
import com.tianque.partyBuilding.organizations.service.RegionalPartyMemberService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Transactional
@Service("districtPartyService")
public class DistrictPartyServiceImpl implements DistrictPartyService {

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PartyresourceService partyresourceService;
	/*
	 * @Autowired private MemberAssociatePartyOrgService
	 * memberAssociatePartyOrgService;
	 */
	@Autowired
	private ActivityRecordsService activityRecordsService;
	@Autowired
	private RegionalPartyMemberService regionalPartyMemberService;
	@Autowired
	private RegionalBuildInfoService regionalBuildInfoService;
	@Autowired
	private PropertyDictService propertyDictService;

	@Override
	public PageInfo<DistrictPartyStatistics> findDistrictPartyStatistics(
			Long organizationId, Integer rows, Integer page) {
		List<Organization> orgs = organizationDubboService
				.findAdminOrgsByParentId(organizationId);
		if (null == orgs || orgs.size() == 0) {
			return new PageInfo<DistrictPartyStatistics>(0, 0, 0,
					new ArrayList<DistrictPartyStatistics>());
		} else {
			int startIndex = (page - 1) * rows;
			int endIndex = page * rows;
			if (endIndex > orgs.size()) {
				endIndex = orgs.size();
			}
			List<Organization> tempOrgs = orgs.subList(startIndex, endIndex);
			List<DistrictPartyStatistics> list = new ArrayList<DistrictPartyStatistics>();
			/** 网格分级 */
			List<PropertyDict> orgLevels = propertyDictService
					.findPropertyDictByDomainName(PropertyTypes.ORGANIZATION_LEVEL);
			Map<Integer, Long> orgLevelMap = new HashMap<Integer, Long>();
			if (orgLevels != null && orgLevels.size() > 0) {
				for (PropertyDict orgLevel : orgLevels) {
					orgLevelMap.put(orgLevel.getInternalId(), orgLevel.getId());
				}
			}
			for (Organization org : tempOrgs) {
				DistrictPartyStatistics districtPartyStatistics = new DistrictPartyStatistics();
				districtPartyStatistics.setOrganization(org);
				/** 乡镇工作动态数 */
				districtPartyStatistics
						.setTownActivityRecordNum(activityRecordsService
								.countActivityRecordByOrgIdOrgInternalCode(
										orgLevelMap.get(OrganizationLevel.TOWN), org.getOrgInternalCode(),
										OrganizationType.PARTY_ORGANIZATION));
				/** 村社区工作动态数 */
				districtPartyStatistics
						.setVillageActivityRecordNum(activityRecordsService
								.countActivityRecordByOrgIdOrgInternalCode(
										orgLevelMap.get(OrganizationLevel.VILLAGE), org.getOrgInternalCode(),
										OrganizationType.PARTY_ORGANIZATION));
				/** 乡镇主要党组织资源数 */
				districtPartyStatistics
						.setTownPartyResourceNum(partyresourceService
								.getTownAndVillagePartyResourceNum(orgLevelMap.get(OrganizationLevel.TOWN), org.getOrgInternalCode()));
				/** 村社区主要党组织资源数 */
				districtPartyStatistics
						.setVillagePartyResourceNum(partyresourceService
								.getTownAndVillagePartyResourceNum(orgLevelMap.get(OrganizationLevel.VILLAGE), org.getOrgInternalCode()));
				/** 乡镇区域党委成员数 */
				districtPartyStatistics
						.setTownMemberNum(regionalPartyMemberService
								.countRegionalPartyMemberByOrgIdOrOrgInternalCode(
										orgLevelMap.get(OrganizationLevel.TOWN), org.getOrgInternalCode()));
				/** 村社区区域党委成员数 */
				districtPartyStatistics
						.setVillageMemberNum(regionalPartyMemberService
								.countRegionalPartyMemberByOrgIdOrOrgInternalCode(orgLevelMap.get(OrganizationLevel.VILLAGE), org.getOrgInternalCode()));
				/** 乡镇区域联建数 */
				districtPartyStatistics
						.setTownRegionalBuildInfoNum(regionalBuildInfoService
								.countRegionalBuildInfoByOrgIdOrOrgInternalCode(
										orgLevelMap.get(OrganizationLevel.TOWN), org.getOrgInternalCode()));
				/** 村社区区域联建数 */
				districtPartyStatistics
						.setVillageRegionalBuildInfoNum(regionalBuildInfoService
								.countRegionalBuildInfoByOrgIdOrOrgInternalCode(
										orgLevelMap.get(OrganizationLevel.VILLAGE), org.getOrgInternalCode()));
				list.add(districtPartyStatistics);
			}
			PageInfo pageInfo = new PageInfo<DistrictPartyStatistics>(page,
					rows, orgs.size(), list);
			return pageInfo;
		}
	}
}
