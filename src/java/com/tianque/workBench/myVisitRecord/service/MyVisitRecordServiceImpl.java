package com.tianque.workBench.myVisitRecord.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.utils.Utils;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.AidReasonType;
import com.tianque.domain.property.ElderPersonType;
import com.tianque.domain.property.HandicappedType;
import com.tianque.domain.property.OptimalObjectType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.property.UnemploymentReasonType;
import com.tianque.shard.util.ShardConversion;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.workBench.myVisitRecord.dao.MyVisitRecordDao;
import com.tianque.workBench.myVisitRecord.domain.MyVisitRecord;

@Service("myVisitRecordService")
@Transactional
public class MyVisitRecordServiceImpl implements MyVisitRecordService {

	@Autowired
	private MyVisitRecordDao myVisitRecordDao;

	@Autowired
	private PermissionService permissionService;

	@Autowired
	private PropertyDictService propertyDictService;

	@Autowired
	private ShardConversion shardConversion;

	@Override
	public List<MyVisitRecord> findMyVisitRecordForBigTypeByOrgInternalCode(
			String orgInternalCode) {
		String shardCode = shardConversion
				.getShardCodeByOrgCode(orgInternalCode);
		List<MyVisitRecord> myVisitRecords = myVisitRecordDao
				.findMyVisitRecordForBigTypeByOrgInternalCode(orgInternalCode,
						shardCode);
		boolean isHasElderlyPeopleManagement = permissionService
				.isUserHasPermission(ThreadVariable.getUser().getId(),
						"elderlyPeopleManagement");
		boolean isHasOptimalObjectManagement = permissionService
				.isUserHasPermission(ThreadVariable.getUser().getId(),
						"optimalObjectManagement");
		boolean isHasAidNeedPopulationManagement = permissionService
				.isUserHasPermission(ThreadVariable.getUser().getId(),
						"aidNeedPopulationManagement");
		boolean isHasUnemployedPeopleManagement = permissionService
				.isUserHasPermission(ThreadVariable.getUser().getId(),
						"unemployedPeopleManagement");
		boolean isHasHandicappedManagement = permissionService
				.isUserHasPermission(ThreadVariable.getUser().getId(),
						"handicappedManagement");
		List<MyVisitRecord> newMyVisitRecords = new ArrayList<MyVisitRecord>();
		for (MyVisitRecord myVisitRecord : myVisitRecords) {
			if (isHasElderlyPeopleManagement
					&& myVisitRecord.getType().contains("elderlyPeople_")) {
				myVisitRecord.seteName("elderlyPeopleManagement");
				myVisitRecord.setcName(BaseInfoTables
						.getTypeDisplayNames("ELDERLYPEOPLE_KEY"));
				newMyVisitRecords.add(myVisitRecord);
			} else if (isHasOptimalObjectManagement
					&& "optimalobjects".equals(myVisitRecord.getType())) {
				myVisitRecord.seteName("optimalObjectManagement");
				myVisitRecord.setcName(BaseInfoTables
						.getTypeDisplayNames("OPTIMALOBJECT_KEY"));
				newMyVisitRecords.add(myVisitRecord);
			} else if (isHasAidNeedPopulationManagement
					&& "aidNeedPopulation".equals(myVisitRecord.getType())) {
				myVisitRecord.seteName("aidNeedPopulationManagement");
				myVisitRecord.setcName(BaseInfoTables
						.getTypeDisplayNames("AIDNEEDPOPULATION_KEY"));
				newMyVisitRecords.add(myVisitRecord);
			} else if (isHasUnemployedPeopleManagement
					&& "unemployedpeople".equals(myVisitRecord.getType())) {
				myVisitRecord.seteName("unemployedPeopleManagement");
				myVisitRecord.setcName(BaseInfoTables
						.getTypeDisplayNames("UNEMPLOYEDPEOPLE_KEY"));
				newMyVisitRecords.add(myVisitRecord);
			} else if (isHasHandicappedManagement
					&& "handicappeds".equals(myVisitRecord.getType())) {
				myVisitRecord.seteName("handicappedManagement");
				myVisitRecord.setcName(BaseInfoTables
						.getTypeDisplayNames("HANDICAPPED_KEY"));
				newMyVisitRecords.add(myVisitRecord);
			}
		}
		return newMyVisitRecords;
	}

	@Override
	public List<MyVisitRecord> findMyVisitRecordByPersonType(
			String orgInternalCode, String personType) {
		List<MyVisitRecord> myVisitRecords = new ArrayList<MyVisitRecord>();
		if ("elderlyPeopleManagement".equals(personType)) {
			List<Long> internalIds = new ArrayList<Long>();
			internalIds.add(Long.valueOf(ElderPersonType.NOFAMILY_ELDER));
			internalIds.add(Long.valueOf(ElderPersonType.LOWERINCOME_ELDER));
			internalIds.add(Long.valueOf(ElderPersonType.TERRIBLYILL_ELDER));
			internalIds.add(Long.valueOf(ElderPersonType.PROVERTY_ELDER));
			List<Long> dictIds = getDictIdListByDictList(
					PropertyTypes.SPECIAL_PERSON, internalIds, Boolean.FALSE);
			String shardCode = shardConversion
					.getShardCodeByOrgCode(orgInternalCode);
			myVisitRecords = myVisitRecordDao.findMyVisitRecordInElder(
					orgInternalCode, myVisitRecords, dictIds, shardCode);
		} else if ("optimalObjectManagement".equals(personType)) {
			List<Long> internalIds = new ArrayList<Long>();
			internalIds.add(Long.valueOf(OptimalObjectType.Disable_Armyman));
			internalIds.add(Long.valueOf(OptimalObjectType.Veteran_WithIll));
			internalIds.add(Long
					.valueOf(OptimalObjectType.DiedOnduty_Armyman_Family));
			internalIds.add(Long
					.valueOf(OptimalObjectType.DiedOnill_Armyman_Family));
			List<Long> dictIds = getDictIdListByDictList(
					PropertyTypes.SPECIAL_CARE_TYPE, internalIds, Boolean.FALSE);
			myVisitRecords = myVisitRecordDao.findMyVisitRecordInOptimalObject(
					orgInternalCode, myVisitRecords, dictIds);
		} else if ("aidNeedPopulationManagement".equals(personType)) {
			List<Long> internalIds = new ArrayList<Long>();
			internalIds.add(Long.valueOf(AidReasonType.Single_Child_NoLabor));
			internalIds.add(Long.valueOf(AidReasonType.Medical_Spending_Much));
			internalIds.add(Long.valueOf(AidReasonType.EMERGENCY_INCIDENT));
			internalIds
					.add(Long.valueOf(AidReasonType.Education_Spending_Much));
			List<Long> dictIds = getDictIdListByDictList(
					PropertyTypes.AIDRREASON, internalIds, Boolean.FALSE);
			myVisitRecords = myVisitRecordDao
					.findMyVisitRecordInAidNeedPopulation(orgInternalCode,
							myVisitRecords, dictIds);
		} else if ("unemployedPeopleManagement".equals(personType)) {
			List<Long> internalIds = new ArrayList<Long>();
			internalIds.add(Long.valueOf(UnemploymentReasonType.Ex_serviceman));
			internalIds.add(Long
					.valueOf(UnemploymentReasonType.Release_After_Serving));
			internalIds.add(Long
					.valueOf(UnemploymentReasonType.Reeducation_Through_Labor));
			internalIds.add(Long.valueOf(UnemploymentReasonType.Leave_School));
			List<Long> dictIds = getDictIdListByDictList(
					PropertyTypes.UNEMPLOYMENTREASON, internalIds,
					Boolean.FALSE);
			myVisitRecords = myVisitRecordDao
					.findMyVisitRecordInUnemployedPeople(orgInternalCode,
							myVisitRecords, dictIds);
		} else if ("handicappedManagement".equals(personType)) {
			List<Long> displayseqs = new ArrayList<Long>();
			displayseqs.add(Long.valueOf(HandicappedType.EYESIGHT_DEFORMITY));
			displayseqs.add(Long.valueOf(HandicappedType.WIT_DEFORMITY));
			displayseqs.add(Long.valueOf(HandicappedType.LIMB_DEFORMITY));
			displayseqs.add(Long.valueOf(HandicappedType.MULTIPLE_DEFORMITY));
			List<Long> dictIds = getDictIdListByDictList(
					PropertyTypes.DISABILITY_TYPE, displayseqs, Boolean.TRUE);
			myVisitRecords = myVisitRecordDao.findMyVisitRecordInHandicapped(
					orgInternalCode, myVisitRecords, dictIds);
		}

		return myVisitRecords;
	}

	private List<Long> getDictIdListByDictList(String domainName,
			List<Long> displayseqsOrInternalIds, boolean isSeq) {
		List<Long> dictIds = null;
		List<PropertyDict> dictList = null;
		if (isSeq) {
			dictList = propertyDictService
					.findPropertyDictByDomainNameAndDisplayseqs(domainName,
							Utils.analyticalIds2Int(displayseqsOrInternalIds));
		} else {
			dictList = propertyDictService
					.findPropertyDictByDomainNameAndInternalIds(domainName,
							Utils.analyticalIds2Int(displayseqsOrInternalIds));
		}
		if (dictList != null && dictList.size() > 0) {
			dictIds = new ArrayList<Long>();
			for (PropertyDict propertyDict : dictList) {
				if (propertyDict != null && propertyDict.getId() != null) {
					dictIds.add(propertyDict.getId());
				}
			}
		}
		return dictIds;
	}
}
