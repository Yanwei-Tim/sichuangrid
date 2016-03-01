package com.tianque.baseInfo.familyInfo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.familyInfo.dao.GroupFamilyDao;
import com.tianque.baseInfo.familyInfo.domain.GroupFamily;
import com.tianque.baseInfo.familyInfo.domain.GroupFamilyHasPopulation;
import com.tianque.baseInfo.familyInfo.domain.SavePeople;
import com.tianque.baseInfo.familyInfo.domain.SearchGroupFamilyVo;
import com.tianque.cache.PageInfoCacheThreadLocal;
import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.cache.constant.MemCacheConstant;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.property.RelationShipWithHead;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.shard.util.ShardConversion;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Transactional
@Service("groupFamilyService")
public class GroupFamilyServiceImpl extends AbstractBaseService implements
		GroupFamilyService {
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private GroupFamilyDao groupFamilyDao;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private ShardConversion shardConversion;

	@Override
	public void practiseGroupFamilyForSynchro(GroupFamily groupFamily,
			Long populationId, String populationType) {
		if (null == groupFamily)
			return;
		if (null == groupFamily.getId()) {
			if (StringUtil.isStringAvaliable(groupFamily.getFamilyAccount())) {
				GroupFamily queryGroupFamily = findGroupFamilyByFamilyAccountAndOrgInternalCode(
						groupFamily.getFamilyAccount(),
						groupFamily.getOrgInternalCode());
				if (null != queryGroupFamily) {
					groupFamily.setId(queryGroupFamily.getId());
					updateGroupFamily(groupFamily);
				} else {
					autoFillChinesePinyin(groupFamily);
					groupFamilyDao.addGroupFamily(groupFamily);
				}
				addGroupPopulationHasPopulation(groupFamily, populationId,
						populationType);
			}
		} else {
			GroupFamily primaryGroupFamily = groupFamilyDao
					.getGroupFamilyById(groupFamily.getId());
			if (null == primaryGroupFamily)
				return;
			if (StringUtil.isStringAvaliable(groupFamily.getFamilyAccount())) {
				if (groupFamily.getFamilyAccount().equals(
						primaryGroupFamily.getFamilyAccount())) {
					updateGroupFamily(groupFamily);
					addGroupPopulationHasPopulation(groupFamily, populationId,
							populationType);
				} else {
					GroupFamily queryGroupFamily = findGroupFamilyByFamilyAccountAndOrgInternalCode(
							groupFamily.getFamilyAccount(),
							groupFamily.getOrgInternalCode());
					if (null == queryGroupFamily) {
						updateGroupFamily(groupFamily);
					} else {
						deleteGroupFamilyHasPopulationByFamilyIdAndPopulationIdAndPopulationType(
								groupFamily.getId(), populationId,
								populationType);
						deleteGroupFamily(groupFamily.getId());
						groupFamily.setId(queryGroupFamily.getId());
						updateGroupFamily(groupFamily);
						addGroupPopulationHasPopulation(groupFamily,
								populationId, populationType);
					}
				}
			} else {
				deleteGroupFamilyHasPopulationByFamilyIdAndPopulationIdAndPopulationType(
						groupFamily.getId(), populationId, populationType);
			}
		}
	}

	private void autoFillChinesePinyin(GroupFamily groupFamily) {
		if (null != groupFamily.getFamilyMaster()) {
			Map<String, String> pinyin = Chinese2pinyin
					.changeChinese2Pinyin(groupFamily.getFamilyMaster());
			groupFamily.setSimplePinyin(pinyin.get("simplePinyin"));
			groupFamily.setFullPinyin(pinyin.get("fullPinyin"));
		}
	}

	private void deleteGroupFamily(Long familyId) {
		if (familyId == null) {
			throw new BusinessValidationException("文件id没有获得");
		}
		GroupFamily groupFamily = groupFamilyDao.getGroupFamilyById(familyId);
		List<GroupFamilyHasPopulation> familyMembers = groupFamilyDao
				.getFamilyMembersByFamilyId(familyId);
		if (null == familyMembers || familyMembers.size() < 1) {
			groupFamilyDao.deleteGroupFamilyById(familyId);
			// 缓存计数器
			groupFamily.setOrganization(organizationDubboService
					.getOrganizationByOrganizationCode(groupFamily
							.getOrgInternalCode()));
			PageInfoCacheThreadLocal.decrease(MemCacheConstant
					.getCachePageKey(GroupFamily.class.getSimpleName()),
					groupFamily, 1);
		}
	}

	private void addGroupPopulationHasPopulation(GroupFamily groupFamily,
			Long populationId, String populationType) {
		GroupFamilyHasPopulation groupFamilyHasPopulation = groupFamilyDao
				.getGroupFamilyHasPopulationByFamilyIdAndPopulationIdAndPopulationType(
						groupFamily.getId(), populationId, populationType);
		if (null != groupFamilyHasPopulation) {
			deleteGroupFamilyHasPopulationByFamilyIdAndPopulationIdAndPopulationType(
					groupFamily.getId(), populationId, populationType);
		}
		groupFamilyHasPopulation = new GroupFamilyHasPopulation(
				groupFamily.getId(), groupFamily.getFamilyRelation(),
				populationId, populationType);
		addGroupFamilyHasPopulation(groupFamilyHasPopulation);
		// 缓存计数器
		groupFamily.setOrganization(organizationDubboService
				.getOrganizationByOrganizationCode(groupFamily
						.getOrgInternalCode()));
		PageInfoCacheThreadLocal.increment(MemCacheConstant
				.getCachePageKey(GroupFamily.class.getSimpleName()),
				groupFamily, 1);
	}

	@Override
	public PageInfo<GroupFamily> pageGroupFamiliesByOrgId(Long orgId,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		if (null == orgId) {
			return new PageInfo<GroupFamily>();
		}
		PropertyDict dict = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.RELATION_SHIP_WITH_MASTER,
						RelationShipWithHead.HEADER).get(0);
		PageInfo<GroupFamily> pageGroupFamilys = setGroupFamilyDefaultOrgId(
				groupFamilyDao
						.pageGroupFamiliesByOrgInternalCode(organizationDubboService
								.getSimpleOrgById(orgId).getOrgInternalCode(),
								pageNum, pageSize, sidx, sord, dict.getId(),
								shardConversion.getShardCode(orgId)),
				orgId);
		List<GroupFamily> groupFamilys = pageGroupFamilys.getResult();
		if (groupFamilys != null) {
			for (GroupFamily groupFamily : groupFamilys) {
				Organization organization = organizationDubboService
						.getOrganizationByOrgInternalCode(groupFamily
								.getOrgInternalCode());
				groupFamily.setOrganization(organization);
			}
		}
		pageGroupFamilys.setResult(groupFamilys);
		return pageGroupFamilys;
	}

	@Override
	public PageInfo<GroupFamily> pageGroupFamiliesBySearchGroupFamilyVoAndOrgId(
			SearchGroupFamilyVo searchGroupFamilyVo, Long orgId,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		if (null == orgId || null == searchGroupFamilyVo) {
			return new PageInfo<GroupFamily>();
		}
		searchGroupFamilyVo.setOrgInternalCode(organizationDubboService
				.getSimpleOrgById(orgId).getOrgInternalCode());
		String shardCode = shardConversion.getShardCode(orgId);
		return setGroupFamilyDefaultOrgId(
				groupFamilyDao.pageGroupFamiliesBySearchGroupFamilyVo(
						shardCode, searchGroupFamilyVo, pageNum, pageSize,
						sidx, sord), orgId);
	}

	@Override
	public PageInfo<GroupFamily> pageGroupFamiliesByFullSearchGroupFamilyVoAndOrgId(
			SearchGroupFamilyVo searchGroupFamilyVo, Long orgId,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		if (null == orgId) {
			return new PageInfo<GroupFamily>();
		}
		if (null == searchGroupFamilyVo) {
			return pageGroupFamiliesByOrgId(orgId, pageNum, pageSize, sidx,
					sord);
		}
		searchGroupFamilyVo.setOrgInternalCode(organizationDubboService
				.getSimpleOrgById(orgId).getOrgInternalCode());
		PropertyDict dict = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.RELATION_SHIP_WITH_MASTER,
						RelationShipWithHead.HEADER).get(0);
		searchGroupFamilyVo.setDictId(dict.getId());
		return setGroupFamilyDefaultOrgId(
				groupFamilyDao.pageGroupFamiliesByFullSearchGroupFamilyVo(
						searchGroupFamilyVo, pageNum, pageSize, sidx, sord,
						shardConversion.getShardCode(orgId)), orgId);
	}

	private PageInfo<GroupFamily> setGroupFamilyDefaultOrgId(
			PageInfo<GroupFamily> groupFamilies, Long orgId) {
		if (null != groupFamilies.getResult()
				&& groupFamilies.getResult().size() > 0) {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			for (GroupFamily groupFamily : groupFamilies.getResult()) {
				if (null == groupFamily.getOrganization()) {
					groupFamily.setOrganization(org);
				}
			}
		}
		return groupFamilies;
	}

	@Override
	public GroupFamily getGroupFamilyByPopulation(Countrymen population) {
		GroupFamilyHasPopulation gfhp = groupFamilyDao
				.getGroupFamilyHasPopulationByFamilyIdAndPopulationIdAndPopulationType(
						null, population.getId(),
						population.getActualPopulationType());
		if (null != gfhp) {
			GroupFamily groupFamily = groupFamilyDao.getGroupFamilyById(gfhp
					.getFamilyId());
			if (whetherFamilyMaster(gfhp.getFamilyRelation().getId()))
				autoFetchInfoToGroupFamilyFromCountrymen(groupFamily,
						population);
			groupFamily.setFamilyRelation(gfhp.getFamilyRelation());
			return groupFamily;
		}
		return new GroupFamily();
	}

	private void autoFetchInfoToGroupFamilyFromCountrymen(
			GroupFamily groupFamily, Countrymen population) {
		if (population != null
				&& BaseInfoTables.OVERSEAPERSONNEL_KEY.equals(population
						.getActualPopulationType())) {
			groupFamily.setFamilyMaster(population.getUsedName());
		} else {
			groupFamily.setFamilyMaster(population.getName());
		}
		groupFamily.setMasterMobileNumber(population.getMobileNumber());
		groupFamily.setMasterTelephone(population.getTelephone());
	}

	@Override
	public GroupFamily findGroupFamilyByFamilyAccountAndOrgInternalCode(
			String familyAccount, String orgInternalCode) {
		return groupFamilyDao.findGroupFamilyByFamilyAccountAndOrgInternalCode(
				familyAccount, orgInternalCode);
	}

	@Override
	public GroupFamily findGroupFamilyByOrgIdAndFamilyAccount(Long orgId,
			String familyAccount) {
		return groupFamilyDao.findGroupFamilyByFamilyAccountAndOrgInternalCode(
				familyAccount, organizationDubboService.getSimpleOrgById(orgId)
						.getOrgInternalCode());
	}

	@Override
	public GroupFamily getGroupFamilyById(Long familyId) {
		GroupFamily groupFamily = groupFamilyDao.getGroupFamilyById(familyId);
		groupFamily.setOrganization(organizationDubboService
				.getOrganizationByOrgInternalCode(groupFamily
						.getOrgInternalCode()));
		return groupFamily;
	}

	@Override
	public PageInfo<GroupFamilyHasPopulation> pageFamilyMembersByFamilyId(
			Long familyId, Integer pageNum, Integer pageSize, String sidx,
			String sord) {
		GroupFamily groupFamily = groupFamilyDao.getGroupFamilyById(familyId);
		PageInfo<GroupFamilyHasPopulation> pageMembers = groupFamilyDao
				.pageFamilyMembersByFamilyIdAndOrgInternalCode(familyId,
						groupFamily.getOrgInternalCode(), pageNum, pageSize,
						sidx, sord, shardConversion
								.getShardCodeByOrgCode(groupFamily
										.getOrgInternalCode()));

		return setFamilyRelationToDisplayName(pageMembers);

	}

	private PageInfo<GroupFamilyHasPopulation> setFamilyRelationToDisplayName(
			PageInfo<GroupFamilyHasPopulation> pageMembers) {
		List<GroupFamilyHasPopulation> groupFamilyHasPopulations = null;
		if (pageMembers != null && pageMembers.getResult() != null) {
			groupFamilyHasPopulations = pageMembers.getResult();
			for (GroupFamilyHasPopulation groupFamilyHasPopulation : groupFamilyHasPopulations) {
				groupFamilyHasPopulation.getFamilyRelation().setDisplayName(
						propertyDictService.getPropertyDictById(
								groupFamilyHasPopulation.getFamilyRelation()
										.getId()).getDisplayName());
			}
			pageMembers.setResult(groupFamilyHasPopulations);
		}

		return pageMembers;
	}

	@Override
	public Boolean whetherFamilyMaster(Long pid) {
		return getPropertyDictById(pid).getInternalId() == RelationShipWithHead.HEADER;
	}

	private PropertyDict getPropertyDictById(Long id) {
		PropertyDict propertyDict = propertyDictService.getPropertyDictById(id);
		if (propertyDict == null) {
			throw new BusinessValidationException("PropertyDict不能为空!");
		}
		return propertyDict;
	}

	@Override
	public int getFamilyMembersByFamilyId(String ids) {
		if (ids == null || "".equals(ids)) {
			throw new BusinessValidationException("家庭ID不能为空!");
		}
		String[] st = ids.split(",");
		int num = 0;
		for (String id : st) {
			if (id != null && !"".equals(id)) {
				List<GroupFamilyHasPopulation> groupFamilyHasPopulation = findFamilyMembersByFamilyId(Long
						.valueOf(id));
				if (groupFamilyHasPopulation.size() > 0) {
					num++;
				}
			}
		}

		return num;
	}

	@Override
	public void deleteGroupFamily(String ids) {
		if (ids == null || "".equals(ids)) {
			throw new BusinessValidationException("家庭ID不能为空!");
		}
		String[] st = ids.split(",");
		for (String id : st) {

			deleteGroupFamilyHasPopulationByFamilyId(Long.valueOf(id));
			deleteGroupFamily(Long.valueOf(id));

		}
	}

	@Override
	public List<GroupFamilyHasPopulation> findFamilyMembersByFamilyId(
			Long familyId) {
		GroupFamily groupFamily = groupFamilyDao.getGroupFamilyById(familyId);
		return groupFamilyDao.findFamilyMembersByFamilyIdAndOrgCode(familyId,
				groupFamily.getOrgInternalCode(),
				shardConversion.getShardCodeByOrgCode(groupFamily
						.getOrgInternalCode()));
	}

	@Override
	public int deleteGroupFamilyHasPopulationByFamilyId(Long familyId) {

		return groupFamilyDao
				.deleteGroupFamilyHasPopulationByFamilyId(familyId);
	}

	@Override
	public void resetFamilyAccount(Long id, String previousFamilyAccount,
			String newFamilyAccount) {
		if (id == null) {
			throw new BusinessValidationException("id不能为空!");
		}
		if (newFamilyAccount == null || "".equals(newFamilyAccount)) {
			throw new BusinessValidationException("新家庭编号不能为空!");
		}
		GroupFamily groupFamily = groupFamilyDao.getGroupFamilyById(id);
		GroupFamily groupFam = new GroupFamily();
		if (groupFamily != null) {
			groupFam = findGroupFamilyByFamilyAccountAndOrgInternalCode(
					newFamilyAccount, groupFamily.getOrgInternalCode());
		}
		if (groupFam != null && !previousFamilyAccount.equals(newFamilyAccount)) {
			throw new BusinessValidationException("新家庭编号已存在，请重新输入！");
		} else {
			updateGroupFamilyAccount(id, newFamilyAccount);
		}

	}

	@Override
	public void updateGroupFamilyAccount(Long id, String newFamilyAccount) {
		groupFamilyDao.updateGroupFamilyAccount(id, newFamilyAccount);
	}

	@Override
	public GroupFamilyHasPopulation getGroupFamilyHasPopulationByPopulationIdAndPopulationType(
			Long populationId, String populationType) {
		return groupFamilyDao
				.getGroupFamilyHasPopulationByPopulationIdAndPopulationType(
						populationId, populationType);
	}

	@Override
	public void updateGroupFamilyMasterToNull(Long id) {
		groupFamilyDao.updateGroupFamilyMasterToNull(id);
	}

	@Override
	public void deleteGroupFamilyByPopulationIdAndPopulationTypeAndIdCardNo(
			Long populationId, String populationType, String idCardNo) {
		GroupFamilyHasPopulation groupFamilyHasPopulation = getGroupFamilyHasPopulationByPopulationIdAndPopulationType(
				populationId, populationType);
		GroupFamily groupFamily = null;
		if (groupFamilyHasPopulation != null) {
			groupFamily = getGroupFamilyById(groupFamilyHasPopulation
					.getFamilyId());
			if (groupFamily != null
					&& getFamilyRelationToMasterId().equals(
							groupFamilyHasPopulation.getFamilyRelation()
									.getId())) {
				updateGroupFamilyMasterToNull(groupFamily.getId());
			}
			if (groupFamily != null) {
				if (idCardNo == null) {
					updateGroupFamilyMemberCountByGroupFamilyId(
							groupFamily.getId(), "add");
				}
				deleteGroupFamilyHasPopulationByFamilyIdAndPopulationIdAndPopulationType(
						groupFamily.getId(), populationId, populationType);
			}
		}

	}

	@Override
	public void updateGroupFamily(GroupFamily groupFamily) {
		autoFillChinesePinyin(groupFamily);
		groupFamilyDao.updateGroupFamily(groupFamily);

	}

	@Override
	public void updateGroupFamilyByObj(GroupFamily groupFamily,
			Long populationId, String populationType, boolean isDeath) {
		GroupFamilyHasPopulation groupFamilyHasPopulation = getGroupFamilyHasPopulationByPopulationIdAndPopulationType(
				populationId, populationType);
		GroupFamily groupFa = null;
		if (groupFamilyHasPopulation != null) {
			groupFa = getGroupFamilyById(groupFamilyHasPopulation.getFamilyId());
			if (isDeath == true) {
				updateGroupFamilyToNoMasterforLogOut(populationId,
						populationType);
			} else {
				if (groupFa != null
						&& propertyDictService.getPropertyDictById(
								groupFamilyHasPopulation.getFamilyRelation()
										.getId()).getInternalId() == RelationShipWithHead.HEADER) {
					groupFamily.setId(groupFa.getId());
					groupFamily
							.setOrgInternalCode(groupFa.getOrgInternalCode());
					groupFamily.setFamilyAccount(groupFa.getFamilyAccount());
					updateGroupFamily(groupFamily);
				}
			}

		}
	}

	@Override
	public List<GroupFamilyHasPopulation> findFamilyMembersByFamilyIdNoMaster(
			Long familyId) {
		if (familyId == null) {
			throw new BusinessValidationException("id不能为空!");
		}
		List<GroupFamilyHasPopulation> gfps = new ArrayList<GroupFamilyHasPopulation>();
		List<GroupFamilyHasPopulation> groupFamilyHasPopulations = findFamilyMembersByFamilyId(familyId);
		for (GroupFamilyHasPopulation groupFamilyHasPopulation : groupFamilyHasPopulations) {
			if (groupFamilyHasPopulation != null
					&& !whetherFamilyMaster(groupFamilyHasPopulation
							.getFamilyRelation().getId())) {
				gfps.add(groupFamilyHasPopulation);
			}
		}
		return gfps;
	}

	private Long getFamilyRelationToMasterId() {
		return propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.RELATION_SHIP_WITH_MASTER,
						RelationShipWithHead.HEADER).get(0).getId();
	}

	@Override
	public void changeFamilyMaster(Long groupFamilyId, Long familyRelationId,
			String newFamilyMaster) {
		String[] st = newFamilyMaster.split(",");
		Long populationId = Long.valueOf(st[0]);
		String populationType = st[1];

		Long familyrelationToMaster = getFamilyRelationToMasterId();
		if (familyrelationToMaster.equals(familyRelationId)) {
			throw new BusinessValidationException("与原家长的关系不能是家长!");
		}
		updateGroupFamilyHasPopulationByFamilyIdAndFamilyrelation(
				groupFamilyId, familyRelationId, familyrelationToMaster);
		GroupFamilyHasPopulation groupFamilyHasPopulation = getGroupFamilyHasPopulationByPopulationIdAndPopulationType(
				populationId, populationType);
		if (groupFamilyHasPopulation != null) {
			updateGroupFamilyHasPopulationRelation(populationId,
					populationType, familyrelationToMaster);
		}
		GroupFamily groupFamily = getGroupFamilyById(groupFamilyId);
		GroupFamilyHasPopulation groupPopulation = groupFamilyDao
				.getFamilyMembersByFamilyIdAndOrgCodeAndFamilyrelation(
						groupFamilyId, familyrelationToMaster, groupFamily
								.getOrgInternalCode(), shardConversion
								.getShardCodeByOrgCode(groupFamily
										.getOrgInternalCode()));

		updateGroupFamily(setGroupFamily(groupFamily, groupPopulation));
	}

	private GroupFamily setGroupFamily(GroupFamily groupFamily,
			GroupFamilyHasPopulation groupPopulation) {
		groupFamily.setFamilyMaster(groupPopulation.getPopulation().getName());
		groupFamily.setMasterCardNo(groupPopulation.getPopulation()
				.getIdCardNo());
		groupFamily.setMasterMobileNumber(groupPopulation.getPopulation()
				.getMobileNumber());
		groupFamily.setMasterTelephone(groupPopulation.getPopulation()
				.getTelephone());
		return groupFamily;
	}

	@Override
	public void updateGroupFamilyHasPopulationRelation(Long populationId,
			String populationType, Long familyRelationId) {
		groupFamilyDao.updateGroupFamilyHasPopulationRelation(populationId,
				populationType, familyRelationId);
	}

	@Override
	public void updateGroupFamilyHasPopulationByFamilyIdAndFamilyrelation(
			Long familyId, Long familyRelationId, Long previousFamilyrelation) {
		groupFamilyDao
				.updateGroupFamilyHasPopulationByFamilyIdAndFamilyrelation(
						familyId, familyRelationId, previousFamilyrelation);
	}

	@Override
	public PageInfo<GroupFamilyHasPopulation> findFamilyMembersByFamilyIdNoMasterAndIncludeRelation(
			Long familyId, Integer pageNum, Integer pageSize, String sidx,
			String sord) {
		if (familyId == null) {
			throw new BusinessValidationException("id不能为空!");
		}
		GroupFamily groupFamily = getGroupFamilyById(familyId);
		Long familyrelationToMaster = getFamilyRelationToMasterId();
		PageInfo<GroupFamilyHasPopulation> pageMembers = groupFamilyDao
				.pageFamilyMembersByFamilyIdNoMaster(familyId,
						familyrelationToMaster, groupFamily
								.getOrgInternalCode(), pageNum, pageSize, sidx,
						sord, shardConversion.getShardCodeByOrgCode(groupFamily
								.getOrgInternalCode()));

		return setFamilyRelationToDisplayName(pageMembers);
	}

	@Override
	public GroupFamilyHasPopulation addFamilyMember(Long groupFamilyId,
			Long familyRelationId, SavePeople savePeople,
			boolean whetherExistOtherFamily) {
		if (groupFamilyId == null) {
			throw new BusinessValidationException("id不能为空!");
		}
		GroupFamilyHasPopulation groupFamilyHasPopulation = new GroupFamilyHasPopulation();
		if (whetherExistOtherFamily) {
			GroupFamilyHasPopulation newGFHasPopulation = getGroupFamilyHasPopulationByPopulationIdAndPopulationType(
					savePeople.getPopulationId(),
					savePeople.getPopulationType());
			if (newGFHasPopulation != null
					&& getFamilyRelationToMasterId().equals(
							newGFHasPopulation.getFamilyRelation().getId())) {
				updateGroupFamilyMasterToNull(newGFHasPopulation.getFamilyId());
			}
			if (newGFHasPopulation != null) {
				deleteGroupFamilyHasPopulationByFamilyIdAndPopulationIdAndPopulationType(
						newGFHasPopulation.getFamilyId(),
						newGFHasPopulation.getPopulationId(),
						newGFHasPopulation.getPopulationType());
			}

		}
		groupFamilyHasPopulation = autoGroupFamilyHasPopulation(groupFamilyId,
				familyRelationId, savePeople, groupFamilyHasPopulation);
		addGroupFamilyHasPopulation(groupFamilyHasPopulation);
		return groupFamilyHasPopulation;
	}

	private GroupFamilyHasPopulation autoGroupFamilyHasPopulation(
			Long groupFamilyId, Long familyRelationId, SavePeople savePeople,
			GroupFamilyHasPopulation groupFamilyHasPopulation) {
		groupFamilyHasPopulation.setFamilyId(groupFamilyId);
		PropertyDict familyRelation = new PropertyDict();
		familyRelation.setId(familyRelationId);
		groupFamilyHasPopulation.setFamilyRelation(familyRelation);
		groupFamilyHasPopulation.setPopulationId(savePeople.getPopulationId());
		groupFamilyHasPopulation.setPopulationType(savePeople
				.getPopulationType());
		return groupFamilyHasPopulation;
	}

	@Override
	public SavePeople getPeopleByIdcardNoAndOrgCode(String idCardNo,
			String orgCode, Long orgId) {
		return groupFamilyDao.getPeopleByIdcardNoAndOrgCode(idCardNo, orgCode,
				shardConversion.getShardCode(orgId));
	}

	@Override
	public void deleteGroupFamilyMember(Long groupFamilyId, Long populationId,
			String populationType) {
		if (groupFamilyId == null) {
			throw new BusinessValidationException("id不能为空!");
		}

		deleteGroupFamilyHasPopulationByFamilyIdAndPopulationIdAndPopulationType(
				groupFamilyId, populationId, populationType);

	}

	private boolean judgeExistAtOrgCodeOfFamily(SavePeople savePeople,
			GroupFamily groupFamily) {
		GroupFamilyHasPopulation groupFamilyHasPopulation = getGroupFamilyHasPopulationByPopulationIdAndPopulationType(
				savePeople.getPopulationId(), savePeople.getPopulationType());
		if (groupFamilyHasPopulation == null) {
		} else {
			GroupFamily newGroupFamily = getGroupFamilyById(groupFamilyHasPopulation
					.getFamilyId());
			if (newGroupFamily != null
					&& newGroupFamily.getOrgInternalCode().equals(
							groupFamily.getOrgInternalCode())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public SavePeople judgeExistAtOrgCodeOfFamily(Long groupFamilyId,
			String idCardNo, Long familyRelationId) {
		if (groupFamilyId == null) {
			throw new BusinessValidationException("id不能为空!");
		}
		GroupFamily groupFamily = getGroupFamilyById(groupFamilyId);
		SavePeople savePeople = null;
		if (groupFamily != null) {
			savePeople = getPeopleByIdcardNoAndOrgCode(idCardNo,
					groupFamily.getOrgInternalCode(), groupFamily
							.getOrganization().getId());
			if (savePeople == null) {
				throw new BusinessValidationException("该网格下不存在此人!");
			} else {
				if (judgeExistAtOrgCodeOfFamily(savePeople, groupFamily)) {
					return savePeople;
				} else {
					savePeople.setName(null);
					return savePeople;
				}

			}
		}
		return null;
	}

	@Override
	public void addGroupFamilyHasPopulation(
			GroupFamilyHasPopulation groupFamilyHasPopulation) {
		groupFamilyDao.addGroupFamilyHasPopulation(groupFamilyHasPopulation);
		GroupFamilyHasPopulation groupFamilyHas = groupFamilyDao
				.getGroupFamilyHasPopulationByFamilyIdAndPopulationIdAndPopulationType(
						groupFamilyHasPopulation.getFamilyId(),
						groupFamilyHasPopulation.getPopulationId(),
						groupFamilyHasPopulation.getPopulationType());
		if (groupFamilyHas != null) {
			updateGroupFamilyMemberCountByGroupFamilyId(
					groupFamilyHasPopulation.getFamilyId(), "add");
		}
	}

	private void updateGroupFamilyMemberCountByGroupFamilyId(
			Long groupFamilyId, String tag) {
		int membersCount = 0;
		if (groupFamilyId != null) {
			GroupFamily groupFamily = getGroupFamilyById(groupFamilyId);
			if (groupFamily != null && "add".equals(tag)) {
				membersCount = groupFamily.getMembersCount() + 1;
			}
			if (groupFamily != null && "del".equals(tag)) {
				membersCount = groupFamily.getMembersCount() - 1;
			}
		}
		groupFamilyDao.updateGroupFamilyMemberCountByGroupFamilyId(
				groupFamilyId, membersCount);
	}

	@Override
	public void deleteGroupFamilyHasPopulationByFamilyIdAndPopulationIdAndPopulationType(
			Long familyId, Long populationId, String populationType) {
		groupFamilyDao
				.deleteGroupFamilyHasPopulationByFamilyIdAndPopulationIdAndPopulationType(
						familyId, populationId, populationType);
		updateGroupFamilyMemberCountByGroupFamilyId(familyId, "del");
	}

	@Override
	public void updateGroupFamilyToNoMasterforLogOut(Long populationId,
			String populationType) {
		GroupFamilyHasPopulation groupFamilyHasPopulation = getGroupFamilyHasPopulationByPopulationIdAndPopulationType(
				populationId, populationType);
		if (groupFamilyHasPopulation != null
				&& getFamilyRelationToMasterId().equals(
						groupFamilyHasPopulation.getFamilyRelation().getId())) {
			updateGroupFamilyMasterToNull(groupFamilyHasPopulation
					.getFamilyId());
		}
		if (groupFamilyHasPopulation != null) {
			updateGroupFamilyMemberCountByGroupFamilyId(
					groupFamilyHasPopulation.getFamilyId(), "del");

		}
	}

	@Override
	public GroupFamily getGroupFamilyByOrgCodeAndFamilyAccount(
			String orgInternalCode, String familyAccount) {
		return groupFamilyDao.getGroupFamilyByOrgCodeAndFamilyAccount(
				orgInternalCode, familyAccount);
	}

	@Override
	public void updateGroupFamilyIdcardById(GroupFamily groupFamily) {
		if (groupFamily == null || groupFamily.getId() == null
				|| groupFamily.getMasterCardNo() == null
				|| "".equals(groupFamily.getMasterCardNo())) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			groupFamilyDao.updateGroupFamilyIdcardById(groupFamily);
		} catch (Exception e) {
			throw new ServiceValidationException("修改户籍人口家庭信息家长身份证号错误", e);
		}
	}

}
