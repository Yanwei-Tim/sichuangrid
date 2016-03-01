package com.tianque.baseInfo.base.service;

import java.util.List;

import com.tianque.baseInfo.base.domain.DuplicatePeople;

/**
 * @Description:删除人口重复数据业务接口（包含baseinfo、householdstaffs、floatingpopulations和相关的业务表）
 * @author zhangyouwei@hztian.com
 * @date: 2014-8-5 下午02:21:34
 */
public interface PeopleDuplicateRemovalService {

	/**
	 * 获取baseInfo表的重复数据的总数
	 * 
	 * @return
	 */
	public Integer getBaseInfoPopulationDuplicateTotal();

	/**
	 * 根据baseInfo的重复数据去修改所有表的baseInfoId为统一的baseInfoId
	 * 
	 * @param duplicatePeoples
	 */
	public void chanageAllPeopleBaseInfoIdByBaseInfo(
			List<DuplicatePeople> duplicatePeopleBaseInfos);

	/**
	 * 根据表名称去统计该表的全部同网格同basInfoId的重复的总数
	 * 
	 * @param tableName
	 * @return
	 */
	public Integer getAlonePopulationDuplicateTotal(String tableName);

	/**
	 * 根据分页后的后去去重业务的重复数据
	 * 
	 * @param startRow
	 * @param endRow
	 * @param tableName
	 */
	public void removalBusinessPopulationDuplicateByPaging(int startRow,
			int endRow, String tableName);

	/**
	 * 根据分页后的后去去重户籍和流动的重复数据
	 * 
	 * @param startRow
	 * @param endRow
	 * @param tableName
	 */
	public void removalHouseholdStaffAndFloatingPopulationByPaging(
			int startRow, int endRow, String tableName);

	/**
	 * 获取所有的baseInfo重复数据的必要的全部信息
	 * 
	 * @return
	 */
	public List<DuplicatePeople> getAllBaseInfoDuplicatePeople();

	/**
	 * 根据分页获取全部的业务信息的的重复的业务人员
	 * 
	 * @param startRow
	 * @param endRow
	 * @param tableName
	 * @return
	 */
	public List<DuplicatePeople> getAllBusinessPopulationDuplicatePeopleByPaging(
			int startRow, int endRow, String tableName);

	/**
	 * 根据分页获取全部的户籍、流口信息的的重复的业务人员
	 * 
	 * @param startRow
	 * @param endRow
	 * @param tableName
	 * @return
	 */
	public List<DuplicatePeople> getAllHouseholdStaffAndFloatingDuplicatePeopleByPaging(
			int startRow, int endRow, String tableName);

	public void removalHouseholdStaffAndFloatingPopulationByPagingByNew(
			List<DuplicatePeople> list, String tableName);

	public void removalBusinessPopulationDuplicateByPagingByNew(
			List<DuplicatePeople> list, String tableName);
}
