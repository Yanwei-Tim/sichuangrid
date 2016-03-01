package com.tianque.plugin.analyzing.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.baseInfo.companyPlace.constacts.ModulTypes;
import com.tianque.core.cache.constant.MemCacheConstant;
import com.tianque.core.cache.service.CacheService;
import com.tianque.domain.Organization;
import com.tianque.domain.property.BasicOrgType;
import com.tianque.plugin.analyzing.dao.SearchPrimaryOrganizationDataColumnDao;
import com.tianque.plugin.analyzing.domain.PrimaryOrganizationDataColumnTwoVo;
import com.tianque.plugin.analyzing.domain.PrimaryOrganizationDataColumnVo;
import com.tianque.plugin.analyzing.domain.SearchPrimaryOrganizationDataColumnVo;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

/**
 * 统计队伍信息
 */
@Service("searchPrimaryOrganizationDataColumnService")
public class SearchPrimaryOrganizationDataColumnServiceImpl implements
		SearchPrimaryOrganizationDataColumnService {
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SearchPrimaryOrganizationDataColumnDao searchPrimaryOrganizationDataColumnDao;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private CacheService cacheService;

	/**
	 * 处理数据（合计）
	 * 
	 * @param parentOrgId
	 * @return
	 */
	private List<PrimaryOrganizationDataColumnTwoVo> dealDataColumnVoListForHj(
			List<PrimaryOrganizationDataColumnTwoVo> list) {
		int teamCount = 0;
		int teamMemberCount = 0;
		int partyCommitteeCount = 0;// 党委部门【综治组织】
		int partyCommitteeMemberCount = 0;// 党委部门人数【综治组织】
		int partyTeamCount = 0;// 党组织【基层党组织】
		int partyTeamMemberCount = 0;
		int autonomyTeamCount = 0;// 自治组织【基层自治组织】
		int autonmoyTeamMemberCount = 0;
		int masseDutyCount = 0;// 群团组织【群防群治队伍】
		int masseDutyMemberCount = 0;// 群团组织人数
		int postulantTeamCount = 0;// 社工志愿者队伍【社会志愿者队伍】
		int postulantTeamMemberCount = 0;

		PrimaryOrganizationDataColumnTwoVo primaryOrganizationDataColumnVo = new PrimaryOrganizationDataColumnTwoVo();
		if (list != null && list.size() > 0) {
			for (PrimaryOrganizationDataColumnTwoVo columnVo : list) {
				partyCommitteeCount += columnVo.getPartyCommitteeCount();
				partyCommitteeMemberCount += columnVo
						.getPartyCommitteeMemberCount();
				partyTeamCount += columnVo.getPartyTeamCount();
				partyTeamMemberCount += columnVo.getPartyTeamMemberCount();
				autonomyTeamCount += columnVo.getAutonomyTeamCount();
				autonmoyTeamMemberCount += columnVo
						.getAutonmoyTeamMemberCount();
				masseDutyCount += columnVo.getMasseDutyCount();
				masseDutyMemberCount += columnVo.getMasseDutyMemberCount();
				postulantTeamCount += columnVo.getPostulantTeamCount();
				postulantTeamMemberCount += columnVo
						.getPostulantTeamMemberCount();
				teamCount += columnVo.getTeamCount();
				teamMemberCount += columnVo.getTeamMemberCount();
			}
			primaryOrganizationDataColumnVo
					.setPartyCommitteeCount(partyCommitteeCount);
			primaryOrganizationDataColumnVo
					.setPartyCommitteeMemberCount(partyCommitteeMemberCount);
			primaryOrganizationDataColumnVo.setPartyTeamCount(partyTeamCount);
			primaryOrganizationDataColumnVo
					.setPartyTeamMemberCount(partyTeamMemberCount);
			primaryOrganizationDataColumnVo
					.setAutonomyTeamCount(autonomyTeamCount);
			primaryOrganizationDataColumnVo
					.setAutonmoyTeamMemberCount(autonmoyTeamMemberCount);
			primaryOrganizationDataColumnVo.setMasseDutyCount(masseDutyCount);
			primaryOrganizationDataColumnVo
					.setMasseDutyMemberCount(masseDutyMemberCount);
			primaryOrganizationDataColumnVo
					.setPostulantTeamCount(postulantTeamCount);
			primaryOrganizationDataColumnVo
					.setPostulantTeamMemberCount(postulantTeamMemberCount);
			primaryOrganizationDataColumnVo.setTeamCount(teamCount);
			primaryOrganizationDataColumnVo.setTeamMemberCount(teamMemberCount);
			Organization organization = new Organization();
			organization.setOrgName("合计");
			primaryOrganizationDataColumnVo.setOrg(organization);
			list.add(primaryOrganizationDataColumnVo);
		}
		return list;
	}

	/**
	 * 处理数据（总况）
	 * 
	 * @param parentOrgId
	 * @return
	 */
	private List<PrimaryOrganizationDataColumnTwoVo> dealDataColumnVoListForZk(
			List<PrimaryOrganizationDataColumnTwoVo> list) {
		int teamCount = 0;
		int teamMemberCount = 0;
		if (list != null && list.size() > 0) {
			for (PrimaryOrganizationDataColumnTwoVo columnVo : list) {
				teamCount = columnVo.getPartyCommitteeCount()
						+ columnVo.getPartyTeamCount()
						+ columnVo.getAutonomyTeamCount()
						+ columnVo.getMasseDutyCount()
						+ columnVo.getPostulantTeamCount();
				teamMemberCount = columnVo.getPartyCommitteeMemberCount()
						+ columnVo.getPartyTeamMemberCount()
						+ columnVo.getAutonmoyTeamMemberCount()
						+ columnVo.getMasseDutyMemberCount()
						+ columnVo.getPostulantTeamMemberCount();

				columnVo.setTeamCount(teamCount);
				columnVo.setTeamMemberCount(teamMemberCount);
			}
		}
		return list;
	}

	/**
	 * 统计各类队伍信息
	 * 
	 * @param parentOrgId
	 * @return
	 */

	public List<PrimaryOrganizationDataColumnVo> dealPrimaryOrganizationDataColumnVoList(
			List<Organization> organizationsList,
			List<SearchPrimaryOrganizationDataColumnVo> columnVoList) {
		List<PrimaryOrganizationDataColumnVo> list = new ArrayList<PrimaryOrganizationDataColumnVo>();
		PrimaryOrganizationDataColumnVo dataColumnVo = null;
		for (Organization organization : organizationsList) {
			int partyTeamCount = 0;// 党组织
			int partyTeamMemberCount = 0;
			int autonomyTeamCount = 0;// 自治组织
			int autonmoyTeamMemberCount = 0;
			int compositeTeamCount = 0;// 综治组织
			int compositeTeamMemberCount = 0;
			int massesTeamCount = 0;// 群防群治队伍
			int massesTeamMemberCount = 0;
			int postulantTeamCount = 0;// 社工志愿者队伍
			int postulantTeamMemberCount = 0;
			int leaderGroupTeamCount = 0;// 专项工作领导小组
			int leaderGroupTeamMemberCount = 0;
			String orgInternalCode = organization.getOrgInternalCode();
			for (SearchPrimaryOrganizationDataColumnVo searchDataColumnVo : columnVoList) {
				dataColumnVo = new PrimaryOrganizationDataColumnVo();
				if (searchDataColumnVo.getOrgInternalCode().startsWith(
						orgInternalCode)) {
					// 判断是否是同一机构
					int basicOrgType = propertyDictService.getPropertyDictById(
							new Long(searchDataColumnVo.getTeamClazz()))
							.getInternalId();
					if (basicOrgType == BasicOrgType.BASICLEVEL_PARTY) {
						// 党组织
						partyTeamCount += searchDataColumnVo.getTeamNum();
						partyTeamMemberCount += searchDataColumnVo
								.getMemberNum();
					} else if (basicOrgType == BasicOrgType.PERMARY_ORGANIZATION) {
						// 综治组织
						compositeTeamCount += searchDataColumnVo.getTeamNum();
						compositeTeamMemberCount += searchDataColumnVo
								.getMemberNum();
					} else if (basicOrgType == BasicOrgType.AUTONOMY_ORG) {
						// 自治组织
						autonomyTeamCount += searchDataColumnVo.getTeamNum();
						autonmoyTeamMemberCount += searchDataColumnVo
								.getMemberNum();
					} else if (basicOrgType == BasicOrgType.MASS_TREAT_TEAM) {
						// 群防群治队伍
						massesTeamCount += searchDataColumnVo.getTeamNum();
						massesTeamMemberCount += searchDataColumnVo
								.getMemberNum();
					} else if (basicOrgType == BasicOrgType.VOLUNTARY_TEAM) {
						// 社工志愿者队伍
						postulantTeamCount += searchDataColumnVo.getTeamNum();
						postulantTeamMemberCount += searchDataColumnVo
								.getMemberNum();
					} else if (basicOrgType == BasicOrgType.LEADER_GROUP) {
						// 专项工作领导小组
						leaderGroupTeamCount += searchDataColumnVo.getTeamNum();
						leaderGroupTeamMemberCount += searchDataColumnVo
								.getMemberNum();
					}
				}
				dataColumnVo
						.setAutonmoyTeamMemberCount(autonmoyTeamMemberCount);
				dataColumnVo.setAutonomyTeamCount(autonomyTeamCount);
				dataColumnVo.setCompositeTeamCount(compositeTeamCount);
				dataColumnVo
						.setCompositeTeamMemberCount(compositeTeamMemberCount);
				dataColumnVo.setMassesTeamCount(massesTeamCount);
				dataColumnVo.setMassesTeamMemberCount(massesTeamMemberCount);
				dataColumnVo.setOrg(organization);
				dataColumnVo.setPartyTeamCount(partyTeamCount);
				dataColumnVo.setPartyTeamMemberCount(partyTeamMemberCount);
				dataColumnVo.setPostulantTeamCount(postulantTeamCount);
				dataColumnVo
						.setPostulantTeamMemberCount(postulantTeamMemberCount);
				dataColumnVo.setLeaderGroupTeamCount(leaderGroupTeamCount);
				dataColumnVo
						.setLeaderGroupTeamMemberCount(leaderGroupTeamMemberCount);

			}
			list.add(dataColumnVo);

		}

		return list;
	}

	// /**
	// * 处理数据（合计）
	// *
	// * @param parentOrgId
	// * @return
	// */
	// private List<PrimaryOrganizationDataColumnVo> dealDataColumnVoListForHj(
	// List<PrimaryOrganizationDataColumnVo> list) {
	// int teamCount = 0;
	// int teamMemberCount = 0;
	// int partyTeamCount = 0;// 党组织
	// int partyTeamMemberCount = 0;
	// int autonomyTeamCount = 0;// 自治组织
	// int autonmoyTeamMemberCount = 0;
	// int compositeTeamCount = 0;// 综治组织
	// int compositeTeamMemberCount = 0;
	// int massesTeamCount = 0;// 群防群治队伍
	// int massesTeamMemberCount = 0;
	// int postulantTeamCount = 0;// 社工志愿者队伍
	// int postulantTeamMemberCount = 0;
	// int leaderGroupTeamCount = 0;// 专项工作领导小组
	// int leaderGroupTeamMemberCount = 0;
	// PrimaryOrganizationDataColumnVo primaryOrganizationDataColumnVo = new
	// PrimaryOrganizationDataColumnVo();
	// for (PrimaryOrganizationDataColumnVo columnVo : list) {
	// partyTeamCount += columnVo.getPartyTeamCount();
	// partyTeamMemberCount += columnVo.getPartyTeamMemberCount();
	// compositeTeamCount += columnVo.getCompositeTeamCount();
	// compositeTeamMemberCount += columnVo.getCompositeTeamMemberCount();
	// autonomyTeamCount += columnVo.getAutonomyTeamCount();
	// autonmoyTeamMemberCount += columnVo.getAutonmoyTeamMemberCount();
	// massesTeamCount += columnVo.getMassesTeamCount();
	// massesTeamMemberCount += columnVo.getMassesTeamMemberCount();
	// postulantTeamCount += columnVo.getPostulantTeamCount();
	// postulantTeamMemberCount += columnVo.getPostulantTeamMemberCount();
	// leaderGroupTeamCount += columnVo.getLeaderGroupTeamCount();
	// leaderGroupTeamMemberCount += columnVo
	// .getLeaderGroupTeamMemberCount();
	// teamCount += columnVo.getTeamCount();
	// teamMemberCount += columnVo.getTeamMemberCount();
	// }
	// primaryOrganizationDataColumnVo
	// .setAutonmoyTeamMemberCount(autonmoyTeamMemberCount);
	// primaryOrganizationDataColumnVo.setAutonomyTeamCount(autonomyTeamCount);
	// primaryOrganizationDataColumnVo
	// .setCompositeTeamCount(compositeTeamCount);
	// primaryOrganizationDataColumnVo
	// .setCompositeTeamMemberCount(compositeTeamMemberCount);
	// primaryOrganizationDataColumnVo.setMassesTeamCount(massesTeamCount);
	// primaryOrganizationDataColumnVo
	// .setMassesTeamMemberCount(massesTeamMemberCount);
	// primaryOrganizationDataColumnVo.setPartyTeamCount(partyTeamCount);
	// primaryOrganizationDataColumnVo
	// .setPartyTeamMemberCount(partyTeamMemberCount);
	// primaryOrganizationDataColumnVo
	// .setPostulantTeamCount(postulantTeamCount);
	// primaryOrganizationDataColumnVo
	// .setPostulantTeamMemberCount(postulantTeamMemberCount);
	// primaryOrganizationDataColumnVo
	// .setLeaderGroupTeamCount(leaderGroupTeamCount);
	// primaryOrganizationDataColumnVo
	// .setLeaderGroupTeamMemberCount(leaderGroupTeamMemberCount);
	// primaryOrganizationDataColumnVo.setTeamCount(teamCount);
	// primaryOrganizationDataColumnVo.setTeamMemberCount(teamMemberCount);
	// Organization organization = new Organization();
	// organization.setOrgName("合计");
	// primaryOrganizationDataColumnVo.setOrg(organization);
	// list.add(primaryOrganizationDataColumnVo);
	//
	// return list;
	// }
	//
	// /**
	// * 处理数据（总况）
	// *
	// * @param parentOrgId
	// * @return
	// */
	// private List<PrimaryOrganizationDataColumnVo> dealDataColumnVoListForZk(
	// List<PrimaryOrganizationDataColumnVo> list) {
	// int teamCount = 0;
	// int teamMemberCount = 0;
	// for (PrimaryOrganizationDataColumnVo columnVo : list) {
	// teamMemberCount = columnVo.getAutonmoyTeamMemberCount()
	// + columnVo.getCompositeTeamMemberCount()
	// + columnVo.getMassesTeamMemberCount()
	// + columnVo.getPartyTeamMemberCount()
	// + columnVo.getPostulantTeamMemberCount()
	// + columnVo.getLeaderGroupTeamMemberCount();
	// teamCount = columnVo.getAutonomyTeamCount()
	// + columnVo.getCompositeTeamCount()
	// + columnVo.getMassesTeamCount()
	// + columnVo.getPartyTeamCount()
	// + columnVo.getPostulantTeamCount()
	// + columnVo.getLeaderGroupTeamCount();
	// columnVo.setTeamCount(teamCount);
	// columnVo.setTeamMemberCount(teamMemberCount);
	// }
	// return list;
	// }

	// /**
	// * 统计各类队伍信息
	// *
	// * @param parentOrgId
	// * @return
	// */
	//
	// public List<PrimaryOrganizationDataColumnVo>
	// dealPrimaryOrganizationDataColumnVoList(
	// List<Organization> organizationsList,
	// List<SearchPrimaryOrganizationDataColumnVo> columnVoList) {
	// List<PrimaryOrganizationDataColumnVo> list = new
	// ArrayList<PrimaryOrganizationDataColumnVo>();
	// PrimaryOrganizationDataColumnVo dataColumnVo = null;
	// for (Organization organization : organizationsList) {
	// int partyTeamCount = 0;// 党组织
	// int partyTeamMemberCount = 0;
	// int autonomyTeamCount = 0;// 自治组织
	// int autonmoyTeamMemberCount = 0;
	// int compositeTeamCount = 0;// 综治组织
	// int compositeTeamMemberCount = 0;
	// int massesTeamCount = 0;// 群防群治队伍
	// int massesTeamMemberCount = 0;
	// int postulantTeamCount = 0;// 社工志愿者队伍
	// int postulantTeamMemberCount = 0;
	// int leaderGroupTeamCount = 0;// 专项工作领导小组
	// int leaderGroupTeamMemberCount = 0;
	// String orgInternalCode = organization.getOrgInternalCode();
	// for (SearchPrimaryOrganizationDataColumnVo searchDataColumnVo :
	// columnVoList) {
	// dataColumnVo = new PrimaryOrganizationDataColumnVo();
	// if (searchDataColumnVo.getOrgInternalCode().startsWith(
	// orgInternalCode)) {
	// // 判断是否是同一机构
	// int basicOrgType = propertyDictService.getPropertyDictById(
	// new Long(searchDataColumnVo.getTeamClazz()))
	// .getInternalId();
	// if (basicOrgType == BasicOrgType.BASICLEVEL_PARTY) {
	// // 党组织
	// partyTeamCount += searchDataColumnVo.getTeamNum();
	// partyTeamMemberCount += searchDataColumnVo
	// .getMemberNum();
	// } else if (basicOrgType == BasicOrgType.PERMARY_ORGANIZATION) {
	// // 综治组织
	// compositeTeamCount += searchDataColumnVo.getTeamNum();
	// compositeTeamMemberCount += searchDataColumnVo
	// .getMemberNum();
	// } else if (basicOrgType == BasicOrgType.AUTONOMY_ORG) {
	// // 自治组织
	// autonomyTeamCount += searchDataColumnVo.getTeamNum();
	// autonmoyTeamMemberCount += searchDataColumnVo
	// .getMemberNum();
	// } else if (basicOrgType == BasicOrgType.MASS_TREAT_TEAM) {
	// // 群防群治队伍
	// massesTeamCount += searchDataColumnVo.getTeamNum();
	// massesTeamMemberCount += searchDataColumnVo
	// .getMemberNum();
	// } else if (basicOrgType == BasicOrgType.VOLUNTARY_TEAM) {
	// // 社工志愿者队伍
	// postulantTeamCount += searchDataColumnVo.getTeamNum();
	// postulantTeamMemberCount += searchDataColumnVo
	// .getMemberNum();
	// } else if (basicOrgType == BasicOrgType.LEADER_GROUP) {
	// // 专项工作领导小组
	// leaderGroupTeamCount += searchDataColumnVo.getTeamNum();
	// leaderGroupTeamMemberCount += searchDataColumnVo
	// .getMemberNum();
	// }
	// }
	// dataColumnVo
	// .setAutonmoyTeamMemberCount(autonmoyTeamMemberCount);
	// dataColumnVo.setAutonomyTeamCount(autonomyTeamCount);
	// dataColumnVo.setCompositeTeamCount(compositeTeamCount);
	// dataColumnVo
	// .setCompositeTeamMemberCount(compositeTeamMemberCount);
	// dataColumnVo.setMassesTeamCount(massesTeamCount);
	// dataColumnVo.setMassesTeamMemberCount(massesTeamMemberCount);
	// dataColumnVo.setOrg(organization);
	// dataColumnVo.setPartyTeamCount(partyTeamCount);
	// dataColumnVo.setPartyTeamMemberCount(partyTeamMemberCount);
	// dataColumnVo.setPostulantTeamCount(postulantTeamCount);
	// dataColumnVo
	// .setPostulantTeamMemberCount(postulantTeamMemberCount);
	// dataColumnVo.setLeaderGroupTeamCount(leaderGroupTeamCount);
	// dataColumnVo
	// .setLeaderGroupTeamMemberCount(leaderGroupTeamMemberCount);
	//
	// }
	// list.add(dataColumnVo);
	//
	// }
	//
	// return list;
	// }

	@Override
	public List<PrimaryOrganizationDataColumnTwoVo> getPrimaryOrganizationDataColumnVoList(
			Long parentOrgId) {
		String key = MemCacheConstant.getCachKeyForThreeData(
				MemCacheConstant.GRASSROOTS_CACHKEY, parentOrgId,
				ModulTypes.STATISTICSTABLELIST);
		List<PrimaryOrganizationDataColumnTwoVo> listData = null;
		if (key != null) {
			listData = (List<PrimaryOrganizationDataColumnTwoVo>) cacheService
					.get(MemCacheConstant.ANALYSE_GRASSROOTS_NAMESPACE, key);
			if (listData != null) {
				return listData;
			}
		}
		List<PrimaryOrganizationDataColumnTwoVo> list = searchPrimaryOrganizationDataColumnDao
				.searchPrimaryOrganizationDataColumnByOrgInternalCode(parentOrgId);

		List<PrimaryOrganizationDataColumnTwoVo> dealListForZk = dealDataColumnVoListForZk(list);
		List<PrimaryOrganizationDataColumnTwoVo> dealFinal = dealDataColumnVoListForHj(dealListForZk);
		if (key != null) {
			cacheService.set(MemCacheConstant.ANALYSE_GRASSROOTS_NAMESPACE,
					key, ModulTypes.CACHETIME, dealFinal);
		}
		return dealFinal;
	}

	// @Override
	// public List<PrimaryOrganizationDataColumnVo>
	// getPrimaryOrganizationDataColumnVoList(
	// Long parentOrgId) {
	// String key = MemCacheConstant.getCachKeyForThreeData(
	// MemCacheConstant.GRASSROOTS_CACHKEY, parentOrgId,
	// ModulTypes.STATISTICSTABLELIST);
	// List<PrimaryOrganizationDataColumnVo> listData = null;
	// if (key != null) {
	// listData = (List<PrimaryOrganizationDataColumnVo>) cacheService
	// .get(MemCacheConstant.ANALYSE_GRASSROOTS_NAMESPACE, key);
	// if (listData != null) {
	// return listData;
	// }
	// }
	// List<Organization> organizationsList = organizationDubboService
	// .findAdminOrgsByParentId(parentOrgId);
	// Organization organization = organizationDubboService
	// .getFullOrgById(parentOrgId);
	// organizationsList.add(organization);
	//
	// List<SearchPrimaryOrganizationDataColumnVo> columnVoList =
	// searchPrimaryOrganizationDataColumnDao
	// .searchPrimaryOrganizationDataColumnByOrgInternalCode(organization
	// .getOrgInternalCode());
	// if (organizationsList != null && columnVoList.size() != 0
	// && organizationsList.size() != 0) {
	// List<PrimaryOrganizationDataColumnVo> dealList =
	// dealPrimaryOrganizationDataColumnVoList(
	// organizationsList, columnVoList);
	// if (dealList.size() == 1) {
	// dealList.get(0).getOrg().setOrgName("本级");
	// }
	// for (PrimaryOrganizationDataColumnVo primaryOrganizationDataColumnVo :
	// dealList) {
	// if (primaryOrganizationDataColumnVo.getOrg()
	// .getOrgInternalCode()
	// .equals(organization.getOrgInternalCode())) {
	// for (PrimaryOrganizationDataColumnVo primaryOrganizationDataColumnVoTwo :
	// dealList) {
	// if (!(primaryOrganizationDataColumnVo.getOrg()
	// .getOrgInternalCode()
	// .equals(primaryOrganizationDataColumnVoTwo
	// .getOrg().getOrgInternalCode()))) {
	// primaryOrganizationDataColumnVo
	// .setAutonmoyTeamMemberCount(primaryOrganizationDataColumnVo
	// .getAutonmoyTeamMemberCount()
	// - primaryOrganizationDataColumnVoTwo
	// .getAutonmoyTeamMemberCount());
	// primaryOrganizationDataColumnVo
	// .setAutonomyTeamCount(primaryOrganizationDataColumnVo
	// .getAutonomyTeamCount()
	// - primaryOrganizationDataColumnVoTwo
	// .getAutonomyTeamCount());
	// primaryOrganizationDataColumnVo
	// .setCompositeTeamCount(primaryOrganizationDataColumnVo
	// .getCompositeTeamCount()
	// - primaryOrganizationDataColumnVoTwo
	// .getCompositeTeamCount());
	// primaryOrganizationDataColumnVo
	// .setCompositeTeamMemberCount(primaryOrganizationDataColumnVo
	// .getCompositeTeamMemberCount()
	// - primaryOrganizationDataColumnVoTwo
	// .getCompositeTeamMemberCount());
	// primaryOrganizationDataColumnVo
	// .setMassesTeamCount(primaryOrganizationDataColumnVo
	// .getMassesTeamCount()
	// - primaryOrganizationDataColumnVoTwo
	// .getMassesTeamCount());
	// primaryOrganizationDataColumnVo
	// .setMassesTeamMemberCount(primaryOrganizationDataColumnVo
	// .getMassesTeamMemberCount()
	// - primaryOrganizationDataColumnVoTwo
	// .getMassesTeamMemberCount());
	// primaryOrganizationDataColumnVo
	// .setPostulantTeamCount(primaryOrganizationDataColumnVo
	// .getPostulantTeamCount()
	// - primaryOrganizationDataColumnVoTwo
	// .getPostulantTeamCount());
	// primaryOrganizationDataColumnVo
	// .setPostulantTeamMemberCount(primaryOrganizationDataColumnVo
	// .getPostulantTeamMemberCount()
	// - primaryOrganizationDataColumnVoTwo
	// .getPostulantTeamMemberCount());
	// primaryOrganizationDataColumnVo
	// .setPartyTeamCount(primaryOrganizationDataColumnVo
	// .getPartyTeamCount()
	// - primaryOrganizationDataColumnVoTwo
	// .getPartyTeamCount());
	// primaryOrganizationDataColumnVo
	// .setPartyTeamMemberCount(primaryOrganizationDataColumnVo
	// .getPartyTeamMemberCount()
	// - primaryOrganizationDataColumnVoTwo
	// .getPartyTeamMemberCount());
	// primaryOrganizationDataColumnVo
	// .setLeaderGroupTeamCount(primaryOrganizationDataColumnVo
	// .getLeaderGroupTeamCount()
	// - primaryOrganizationDataColumnVoTwo
	// .getLeaderGroupTeamCount());
	// primaryOrganizationDataColumnVo
	// .setLeaderGroupTeamMemberCount(primaryOrganizationDataColumnVo
	// .getLeaderGroupTeamMemberCount()
	// - primaryOrganizationDataColumnVoTwo
	// .getLeaderGroupTeamMemberCount());
	// primaryOrganizationDataColumnVo.getOrg()
	// .setOrgName("本级");
	// }
	// }
	// break;
	// }
	// }
	// List<PrimaryOrganizationDataColumnVo> dealListForZk =
	// dealDataColumnVoListForZk(dealList);
	// List<PrimaryOrganizationDataColumnVo> dealFinal =
	// dealDataColumnVoListForHj(dealListForZk);
	// if (key != null) {
	// cacheService.set(MemCacheConstant.ANALYSE_GRASSROOTS_NAMESPACE,
	// key, ModulTypes.CACHETIME, dealFinal);
	// }
	// return dealFinal;
	// } else {
	// List<PrimaryOrganizationDataColumnVo> list = new
	// ArrayList<PrimaryOrganizationDataColumnVo>();
	// for (Organization org : organizationsList) {
	// PrimaryOrganizationDataColumnVo columnVo = new
	// PrimaryOrganizationDataColumnVo();
	// columnVo.setOrg(org);
	// list.add(columnVo);
	// }
	// if (list.size() == 1) {
	// list.get(0).getOrg().setOrgName("本级");
	// }
	// for (PrimaryOrganizationDataColumnVo primaryOrganizationDataColumnVo :
	// list) {
	// if (primaryOrganizationDataColumnVo.getOrg()
	// .getOrgInternalCode()
	// .equals(organization.getOrgInternalCode())) {
	// for (PrimaryOrganizationDataColumnVo primaryOrganizationDataColumnVoTwo :
	// list) {
	// if (!(primaryOrganizationDataColumnVo.getOrg()
	// .getOrgInternalCode()
	// .equals(primaryOrganizationDataColumnVoTwo
	// .getOrg().getOrgInternalCode()))) {
	// primaryOrganizationDataColumnVo
	// .setAutonmoyTeamMemberCount(primaryOrganizationDataColumnVo
	// .getAutonmoyTeamMemberCount()
	// - primaryOrganizationDataColumnVoTwo
	// .getAutonmoyTeamMemberCount());
	// primaryOrganizationDataColumnVo
	// .setAutonomyTeamCount(primaryOrganizationDataColumnVo
	// .getAutonomyTeamCount()
	// - primaryOrganizationDataColumnVoTwo
	// .getAutonomyTeamCount());
	// primaryOrganizationDataColumnVo
	// .setCompositeTeamCount(primaryOrganizationDataColumnVo
	// .getCompositeTeamCount()
	// - primaryOrganizationDataColumnVoTwo
	// .getCompositeTeamCount());
	// primaryOrganizationDataColumnVo
	// .setCompositeTeamMemberCount(primaryOrganizationDataColumnVo
	// .getCompositeTeamMemberCount()
	// - primaryOrganizationDataColumnVoTwo
	// .getCompositeTeamMemberCount());
	// primaryOrganizationDataColumnVo
	// .setMassesTeamCount(primaryOrganizationDataColumnVo
	// .getMassesTeamCount()
	// - primaryOrganizationDataColumnVoTwo
	// .getMassesTeamCount());
	// primaryOrganizationDataColumnVo
	// .setMassesTeamMemberCount(primaryOrganizationDataColumnVo
	// .getMassesTeamMemberCount()
	// - primaryOrganizationDataColumnVoTwo
	// .getMassesTeamMemberCount());
	// primaryOrganizationDataColumnVo
	// .setPostulantTeamCount(primaryOrganizationDataColumnVo
	// .getPostulantTeamCount()
	// - primaryOrganizationDataColumnVoTwo
	// .getPostulantTeamCount());
	// primaryOrganizationDataColumnVo
	// .setPostulantTeamMemberCount(primaryOrganizationDataColumnVo
	// .getPostulantTeamMemberCount()
	// - primaryOrganizationDataColumnVoTwo
	// .getPostulantTeamMemberCount());
	// primaryOrganizationDataColumnVo
	// .setPartyTeamCount(primaryOrganizationDataColumnVo
	// .getPartyTeamCount()
	// - primaryOrganizationDataColumnVoTwo
	// .getPartyTeamCount());
	// primaryOrganizationDataColumnVo
	// .setPartyTeamMemberCount(primaryOrganizationDataColumnVo
	// .getPartyTeamMemberCount()
	// - primaryOrganizationDataColumnVoTwo
	// .getPartyTeamMemberCount());
	// primaryOrganizationDataColumnVo
	// .setLeaderGroupTeamCount(primaryOrganizationDataColumnVo
	// .getLeaderGroupTeamCount()
	// - primaryOrganizationDataColumnVoTwo
	// .getLeaderGroupTeamCount());
	// primaryOrganizationDataColumnVo
	// .setLeaderGroupTeamMemberCount(primaryOrganizationDataColumnVo
	// .getLeaderGroupTeamMemberCount()
	// - primaryOrganizationDataColumnVoTwo
	// .getLeaderGroupTeamMemberCount());
	// primaryOrganizationDataColumnVo.getOrg()
	// .setOrgName("本级");
	// }
	// }
	// break;
	// }
	// }
	// Organization org = new Organization();
	// org.setOrgName("合计");
	// PrimaryOrganizationDataColumnVo columnVo = new
	// PrimaryOrganizationDataColumnVo();
	// columnVo.setOrg(org);
	// list.add(columnVo);
	// if (key != null) {
	// cacheService.set(MemCacheConstant.ANALYSE_GRASSROOTS_NAMESPACE,
	// key, ModulTypes.CACHETIME, list);
	// }
	// return list;
	//
	// }
	//
	// }

}
