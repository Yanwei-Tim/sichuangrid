package com.tianque.plugin.transfer.handler;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.familyInfo.dao.GroupFamilyDao;
import com.tianque.baseInfo.familyInfo.domain.GroupFamily;
import com.tianque.baseInfo.familyInfo.domain.GroupFamilyHasPopulation;
import com.tianque.baseInfo.familyInfo.service.GroupFamilyService;
import com.tianque.baseInfo.floatingPopulation.domain.FloatingPopulation;
import com.tianque.baseInfo.householdStaff.domain.HouseholdStaff;
import com.tianque.baseInfo.overseaPersonnel.domain.OverseaPersonnel;
import com.tianque.baseInfo.unsettledPopulation.domain.UnsettledPopulation;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.property.RelationShipWithHead;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.plugin.transfer.util.Constants;
import com.tianque.plugin.transfer.util.Context;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Component("groupFamilyHandler")
public class GroupFamilyHandler extends Handler {

	@Autowired
	private GroupFamilyService groupFamilyService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private GroupFamilyDao groupFamilyDao;
	@Autowired
	private PropertyDictService propertyDictService;

	@Override
	public void invoke(String type, Long id, Long toOrgId, Context context) {
		if (!checkInvoke(type, id, toOrgId, context)) {
			return;
		}
		if (context.isExistedToOrgId()) {
			return;
		}
		Map<String, Object> oldMap = context.getOldMap();
		Map<String, Object> newMap = context.getNewMap();
		Organization toOrganization = organizationDubboService
				.getFullOrgById(toOrgId);
		// 获取原有实有人口类型及id
		Countrymen oldCountrymen = getCountrymen(oldMap);
		Countrymen newCountrymen = getCountrymen(newMap);
		String oldPopulationType = oldCountrymen.getActualPopulationType();
		Long oldPopulationId = oldCountrymen.getId();
		String newPopulationType = newCountrymen.getActualPopulationType();
		Long newPopulationId = newCountrymen.getId();
		// 当原有家庭及人与家庭关联存在时，业务继续
		GroupFamilyHasPopulation oldGroupFamilyHasPopulation = groupFamilyService
				.getGroupFamilyHasPopulationByPopulationIdAndPopulationType(
						oldPopulationId, oldPopulationType);
		GroupFamily oldGroupFamily = groupFamilyService
				.getGroupFamilyByPopulation(oldCountrymen);
		if (oldGroupFamilyHasPopulation == null || oldGroupFamily == null) {
			return;
		}

		GroupFamily newGroupFamily = groupFamilyService
				.getGroupFamilyByOrgCodeAndFamilyAccount(
						toOrganization.getOrgInternalCode(),
						oldGroupFamily.getFamilyAccount());

		// 删除人与原家庭关系
		groupFamilyService
				.deleteGroupFamilyHasPopulationByFamilyIdAndPopulationIdAndPopulationType(
						oldGroupFamily.getId(),
						oldGroupFamilyHasPopulation.getPopulationId(),
						oldGroupFamilyHasPopulation.getPopulationType());
		if (propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.RELATION_SHIP_WITH_MASTER,
						RelationShipWithHead.HEADER).get(0).getId()
				.equals(oldGroupFamily.getFamilyRelation().getId())) {
			groupFamilyService.updateGroupFamilyMasterToNull(oldGroupFamily
					.getId());
		}

		if (oldGroupFamily.getId() == null) {
			throw new BusinessValidationException("文件id没有获得");
		}
		List<GroupFamilyHasPopulation> familyMembers = groupFamilyDao
				.getFamilyMembersByFamilyId(oldGroupFamily.getId());
		if (null == familyMembers || familyMembers.size() < 1) {
			groupFamilyDao.deleteGroupFamilyById(oldGroupFamily.getId());
		}

		// 组织新家庭的信息
		if (newGroupFamily != null) {
			newGroupFamily.setOrganization(toOrganization);
			newGroupFamily.setOrgInternalCode(newCountrymen
					.getOrgInternalCode());
			newGroupFamily.setFamilyRelation(oldGroupFamilyHasPopulation
					.getFamilyRelation());
			if (isMaster(oldGroupFamilyHasPopulation.getFamilyRelation()
					.getId())) {
				if (newGroupFamily.getMasterCardNo() != null
						&& newCountrymen.getIdCardNo() != null) {
					// 如果新户是户主，并且老户有户主，设置新户户主为其他
					if (hasMaster(newGroupFamily.getId())) {
						List<PropertyDict> list = propertyDictService
								.findPropertyDictByDomainNameAndInternalId(
										PropertyTypes.RELATION_SHIP_WITH_MASTER,
										RelationShipWithHead.OTHER);
						if (!list.isEmpty()) {
							PropertyDict propertyDictOther = list.get(0);
							// newCountrymen.getGroupFamily().setFamilyRelation(propertyDictOther);
							newGroupFamily.setFamilyRelation(propertyDictOther);
						}
					}

				} else {

					newGroupFamily.setFamilyMaster(newCountrymen.getName());
					newGroupFamily.setMasterCardNo(newCountrymen.getIdCardNo());
					newGroupFamily.setMasterMobileNumber(newCountrymen
							.getMobileNumber());
					newGroupFamily.setMasterTelephone(newCountrymen
							.getTelephone());
					autoFillChinesePinyin(newGroupFamily);
				}
				groupFamilyService.updateGroupFamilyMasterToNull(oldGroupFamily
						.getId());
			}
			newCountrymen.setGroupFamily(newGroupFamily);
		} else {
			newGroupFamily = oldGroupFamily;
			newGroupFamily.setId(null);
			newGroupFamily.setOrganization(toOrganization);
			newGroupFamily.setOrgInternalCode(toOrganization
					.getOrgInternalCode());
			newGroupFamily.setFamilyRelation(oldGroupFamilyHasPopulation
					.getFamilyRelation());
			if (isMaster(oldGroupFamilyHasPopulation.getFamilyRelation()
					.getId())) {
				newGroupFamily.setFamilyMaster(newCountrymen.getName());
				newGroupFamily.setMasterCardNo(newCountrymen.getIdCardNo());
				newGroupFamily.setMasterMobileNumber(newCountrymen
						.getMobileNumber());
				newGroupFamily.setMasterTelephone(newCountrymen.getTelephone());
				autoFillChinesePinyin(newGroupFamily);
				newCountrymen.setGroupFamily(newGroupFamily);
				groupFamilyService.updateGroupFamilyMasterToNull(oldGroupFamily
						.getId());
			}
		}

		groupFamilyService.practiseGroupFamilyForSynchro(newGroupFamily,
				newCountrymen.getId(), newCountrymen.getActualPopulationType());

		int newCount = groupFamilyDao
				.countFamilyMembersByFamilyId(newGroupFamily.getId());
		groupFamilyDao.updateGroupFamilyMemberCountByGroupFamilyId(
				newGroupFamily.getId(), newCount);
		int oldCount = groupFamilyDao
				.countFamilyMembersByFamilyId(oldGroupFamily.getId());
		groupFamilyDao.updateGroupFamilyMemberCountByGroupFamilyId(
				oldGroupFamily.getId(), oldCount);

	}

	private Countrymen getCountrymen(Map<String, Object> map) {
		Countrymen countrymen = null;
		if (map.get(Constants.HOUSEHOLDSTAFF_KEY) != null) {
			countrymen = (HouseholdStaff) map.get(Constants.HOUSEHOLDSTAFF_KEY);
		}
		if (map.get(Constants.FLOATINGPOPULATION_KEY) != null) {
			countrymen = (FloatingPopulation) map
					.get(Constants.FLOATINGPOPULATION_KEY);
		}
		if (map.get(Constants.UNSETTLEDPOPULATION_KEY) != null) {
			countrymen = (UnsettledPopulation) map
					.get(Constants.UNSETTLEDPOPULATION_KEY);
		}
		if (map.get(Constants.OVERSEAPERSONNEL_KEY) != null) {
			countrymen = (OverseaPersonnel) map
					.get(Constants.OVERSEAPERSONNEL_KEY);
		}
		return countrymen;
	}

	private boolean checkInvoke(String type, Long id, Long toOrgId,
			Context context) {
		Map<String, Object> oldMap = context.getOldMap();
		Map<String, Object> newMap = context.getNewMap();
		if (oldMap.get(Constants.HOUSEHOLDSTAFF_KEY) == null
				&& oldMap.get(Constants.FLOATINGPOPULATION_KEY) == null
				&& oldMap.get(Constants.UNSETTLEDPOPULATION_KEY) == null
				&& oldMap.get(Constants.OVERSEAPERSONNEL_KEY) == null) {
			return false;
		}
		// 当目标网格实口存在时，业务继续
		if (newMap.get(Constants.HOUSEHOLDSTAFF_KEY) == null
				&& newMap.get(Constants.FLOATINGPOPULATION_KEY) == null
				&& newMap.get(Constants.UNSETTLEDPOPULATION_KEY) == null
				&& newMap.get(Constants.OVERSEAPERSONNEL_KEY) == null) {
			return false;
		}
		// 当目标组织机构存在时，业务继续
		Organization toOrganization = organizationDubboService
				.getFullOrgById(toOrgId);
		if (toOrganization == null) {
			return false;
		}
		return true;
	}

	private void autoFillChinesePinyin(GroupFamily groupFamily) {
		if (null != groupFamily.getFamilyMaster()) {
			Map<String, String> pinyin = Chinese2pinyin
					.changeChinese2Pinyin(groupFamily.getFamilyMaster());
			groupFamily.setSimplePinyin(pinyin.get("simplePinyin"));
			groupFamily.setFullPinyin(pinyin.get("fullPinyin"));
		}
	}

	private boolean isMaster(Long familyRelationId) {
		PropertyDict propertyDict = propertyDictService
				.getPropertyDictById(familyRelationId);
		return propertyDict.getInternalId() == RelationShipWithHead.HEADER;
	}

	private boolean hasMaster(Long id) {
		List<GroupFamilyHasPopulation> groupFamilyHasPopulations = groupFamilyService
				.findFamilyMembersByFamilyId(id);
		for (GroupFamilyHasPopulation groupFamilyHasPopulation : groupFamilyHasPopulations) {
			PropertyDict propertyDict = propertyDictService
					.getPropertyDictById(groupFamilyHasPopulation
							.getFamilyRelation().getId());
			if (propertyDict.getInternalId() == RelationShipWithHead.HEADER) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void validate(String type, Long id, Long toOrgId, Context context) {
	}

	@Override
	public void validate(String type, Long id, Long toOrgId, Long fromOrgId,
			Context context) {
	}

	@Override
	public void invoke(String type, Long id, Long toOrgId, Long fromOrgId,
			Context context) {
		invoke(type, id, toOrgId, context);
	}

}
