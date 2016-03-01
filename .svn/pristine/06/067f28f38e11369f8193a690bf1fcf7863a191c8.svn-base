package com.tianque.workBench.myVisitRecord.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.domain.property.AidReasonType;
import com.tianque.domain.property.ElderPersonType;
import com.tianque.domain.property.HandicappedType;
import com.tianque.domain.property.OptimalObjectType;
import com.tianque.domain.property.UnemploymentReasonType;
import com.tianque.workBench.myVisitRecord.domain.MyVisitRecord;

@Repository("myVisitRecordDao")
public class MyVisitRecordDaoImpl extends AbstractBaseDao implements
		MyVisitRecordDao {

	@Override
	public List<MyVisitRecord> findMyVisitRecordInElder(String orgInternalCode,
			List<MyVisitRecord> myVisitRecords, List<Long> dictIds,
			String shardCode) {
		// 通过service传参数将字典项idList传入
		// List<Integer> personType = new ArrayList<Integer>();
		// personType.add(ElderPersonType.NOFAMILY_ELDER);
		// personType.add(ElderPersonType.LOWERINCOME_ELDER);
		// personType.add(ElderPersonType.TERRIBLYILL_ELDER);
		// personType.add(ElderPersonType.PROVERTY_ELDER);
		Map<String, Object> map = new HashMap<String, Object>();
		// map.put("domainname", PropertyTypes.SPECIAL_PERSON);
		// map.put("internalIds", personType);
		map.put("dictIds", dictIds);
		map.put("isEmphasis", 0L);
		map.put("orgInternalCode", orgInternalCode);
		map.put("shardCode", shardCode);
		myVisitRecords = getSqlMapClientTemplate().queryForList(
				"myVisitRecord.countEachElderlyPeopleVisitRecordByType", map);
		if (myVisitRecords != null) {
			Map<String, String> myVisitRecordAidReasonType = ElderPersonType
					.myVisitRecordAidReasonType();
			for (MyVisitRecord myVisitRecord : myVisitRecords) {
				String displayName = myVisitRecord.getTypeName()
						.getDisplayName();
				String type = myVisitRecordAidReasonType.get(displayName);
				myVisitRecord.setType(type);
			}

		} else {

		}
		return myVisitRecords;
	}

	@Override
	public List<MyVisitRecord> findMyVisitRecordInOptimalObject(
			String orgInternalCode, List<MyVisitRecord> myVisitRecords,
			List<Long> dictIds) {
		// List<Integer> personType = new ArrayList<Integer>();
		// personType.add(OptimalObjectType.Disable_Armyman);
		// personType.add(OptimalObjectType.Veteran_WithIll);
		// personType.add(OptimalObjectType.DiedOnduty_Armyman_Family);
		// personType.add(OptimalObjectType.DiedOnill_Armyman_Family);
		Map<String, Object> map = new HashMap<String, Object>();
		// map.put("domainname", PropertyTypes.SPECIAL_CARE_TYPE);
		// map.put("internalIds", personType);
		map.put("dictIds", dictIds);
		map.put("isEmphasis", 0L);
		map.put("orgInternalCode", orgInternalCode);
		myVisitRecords = getSqlMapClientTemplate().queryForList(
				"myVisitRecord.countEachOptimalObjectVisitRecordByType", map);
		if (myVisitRecords != null) {
			Map<String, String> myVisitRecordAidReasonType = OptimalObjectType
					.myVisitRecordAidReasonType();
			for (MyVisitRecord myVisitRecord : myVisitRecords) {
				myVisitRecord.setType(myVisitRecordAidReasonType
						.get(myVisitRecord.getTypeName().getDisplayName()));
			}
		}
		return myVisitRecords;
	}

	@Override
	public List<MyVisitRecord> findMyVisitRecordInAidNeedPopulation(
			String orgInternalCode, List<MyVisitRecord> myVisitRecords,
			List<Long> dictIds) {

		// List<Integer> personType = new ArrayList<Integer>();
		// personType.add(AidReasonType.Single_Child_NoLabor);
		// personType.add(AidReasonType.Medical_Spending_Much);
		// personType.add(AidReasonType.EMERGENCY_INCIDENT);
		// personType.add(AidReasonType.Education_Spending_Much);
		Map<String, Object> map = new HashMap<String, Object>();
		// map.put("domainname", PropertyTypes.AIDRREASON);
		// map.put("internalIds", personType);
		map.put("dictIds", dictIds);
		map.put("isEmphasis", 0L);
		map.put("orgInternalCode", orgInternalCode);
		myVisitRecords = getSqlMapClientTemplate().queryForList(
				"myVisitRecord.countEachAidNeedPopulationVisitRecordByType",
				map);
		if (myVisitRecords != null) {
			Map<String, String> myVisitRecordAidReasonType = AidReasonType
					.myVisitRecordAidReasonType();
			for (MyVisitRecord myVisitRecord : myVisitRecords) {
				myVisitRecord.setType(myVisitRecordAidReasonType
						.get(myVisitRecord.getTypeName().getDisplayName()));
			}
		}
		return myVisitRecords;
	}

	@Override
	public List<MyVisitRecord> findMyVisitRecordInUnemployedPeople(
			String orgInternalCode, List<MyVisitRecord> myVisitRecords,
			List<Long> dictIds) {
		// List<Integer> personType = new ArrayList<Integer>();
		// personType.add(UnemploymentReasonType.Ex_serviceman);
		// personType.add(UnemploymentReasonType.Release_After_Serving);
		// personType.add(UnemploymentReasonType.Reeducation_Through_Labor);
		// personType.add(UnemploymentReasonType.Leave_School);
		Map<String, Object> map = new HashMap<String, Object>();
		// map.put("domainname", PropertyTypes.UNEMPLOYMENTREASON);
		// map.put("internalIds", personType);
		map.put("dictIds", dictIds);
		map.put("isEmphasis", 0L);
		map.put("orgInternalCode", orgInternalCode);
		myVisitRecords = getSqlMapClientTemplate()
				.queryForList(
						"myVisitRecord.countEachUnemployedPeopleVisitRecordByType",
						map);
		if (myVisitRecords != null) {
			Map<String, String> myVisitRecordAidReasonType = UnemploymentReasonType
					.myVisitRecordAidReasonType();
			for (MyVisitRecord myVisitRecord : myVisitRecords) {
				myVisitRecord.setType(myVisitRecordAidReasonType
						.get(myVisitRecord.getTypeName().getDisplayName()));
			}
		}
		return myVisitRecords;
	}

	@Override
	public List<MyVisitRecord> findMyVisitRecordForBigTypeByOrgInternalCode(
			String orgInternalCode, String shardCode) {
		List<String> tables = new ArrayList<String>();
		if (shardCode != null) {
			tables.add("elderlyPeople" + "_" + shardCode);
		}
		tables.add("optimalobjects");
		tables.add("aidNeedPopulation");
		tables.add("unemployedpeople");
		tables.add("handicappeds");
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("tables", tables);
		query.put("isEmphasis", 0L);
		query.put("orgInternalCode", orgInternalCode);
		List<MyVisitRecord> bigType = getSqlMapClientTemplate().queryForList(
				"myVisitRecord.countEachAttentionPopulation", query);
		return bigType;
	}

	// 没有任何方法调用该方法
	// @Override
	// public List<MyVisitRecord> findMyVisitRecordByPersonType(
	// String orgInternalCode, String personType) {
	// List<MyVisitRecord> myVisitRecords = new ArrayList<MyVisitRecord>();
	// if ("elderlyPeopleManagement".equals(personType)) {
	//
	// myVisitRecords = findMyVisitRecordInElder(orgInternalCode,
	// myVisitRecords);
	// } else if ("optimalObjectManagement".equals(personType)) {
	// myVisitRecords = findMyVisitRecordInOptimalObject(orgInternalCode,
	// myVisitRecords);
	//
	// } else if ("aidNeedPopulationManagement".equals(personType)) {
	// myVisitRecords = findMyVisitRecordInAidNeedPopulation(
	// orgInternalCode, myVisitRecords);
	//
	// } else if ("unemployedPeopleManagement".equals(personType)) {
	// myVisitRecords = findMyVisitRecordInUnemployedPeople(
	// orgInternalCode, myVisitRecords);
	//
	// }
	// return myVisitRecords;
	// }

	@Override
	public List<MyVisitRecord> findMyVisitRecordInHandicapped(
			String orgInternalCode, List<MyVisitRecord> myVisitRecords,
			List<Long> dictIds) {
		// List<Integer> personType = new ArrayList<Integer>();
		// personType.add(HandicappedType.EYESIGHT_DEFORMITY);
		// personType.add(HandicappedType.WIT_DEFORMITY);
		// personType.add(HandicappedType.LIMB_DEFORMITY);
		// personType.add(HandicappedType.MULTIPLE_DEFORMITY);
		Map<String, Object> map = new HashMap<String, Object>();
		// map.put("domainname", PropertyTypes.DISABILITY_TYPE);
		// map.put("internalIds", personType);
		map.put("dictIds", dictIds);
		map.put("isEmphasis", 0L);
		map.put("orgInternalCode", orgInternalCode);
		myVisitRecords = getSqlMapClientTemplate().queryForList(
				"myVisitRecord.countEachHandicappedVisitRecordByType", map);
		if (myVisitRecords != null) {
			Map<String, String> myVisitRecordAidReasonType = HandicappedType
					.myVisitRecordAidReasonType();
			for (MyVisitRecord myVisitRecord : myVisitRecords) {
				myVisitRecord.setType(myVisitRecordAidReasonType
						.get(myVisitRecord.getTypeName().getDisplayName()));
			}
		}
		return myVisitRecords;
	}
}
