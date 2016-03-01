package com.tianque.workBench.myVisitRecord.dao;

import java.util.List;

import com.tianque.workBench.myVisitRecord.domain.MyVisitRecord;

public interface MyVisitRecordDao {

	public List<MyVisitRecord> findMyVisitRecordForBigTypeByOrgInternalCode(
			String orgInternalCode, String shardCode);

	// 没有任何方法调用该方法
	/*
	 * public List<MyVisitRecord> findMyVisitRecordByPersonType( String
	 * orgInternalCode, String personType);
	 */

	public List<MyVisitRecord> findMyVisitRecordInElder(String orgInternalCode,
			List<MyVisitRecord> myVisitRecords, List<Long> dictIds,
			String shardCode);

	public List<MyVisitRecord> findMyVisitRecordInOptimalObject(
			String orgInternalCode, List<MyVisitRecord> myVisitRecords,
			List<Long> dictIds);

	public List<MyVisitRecord> findMyVisitRecordInAidNeedPopulation(
			String orgInternalCode, List<MyVisitRecord> myVisitRecords,
			List<Long> dictIds);

	public List<MyVisitRecord> findMyVisitRecordInUnemployedPeople(
			String orgInternalCode, List<MyVisitRecord> myVisitRecords,
			List<Long> dictIds);

	public List<MyVisitRecord> findMyVisitRecordInHandicapped(
			String orgInternalCode, List<MyVisitRecord> myVisitRecords,
			List<Long> dictIds);

}
