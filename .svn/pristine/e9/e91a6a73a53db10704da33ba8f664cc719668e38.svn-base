package com.tianque.baseInfo.base.dao;

import java.util.List;
import java.util.Map;

import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.base.domain.DuplicatePeople;
import com.tianque.baseInfo.base.domain.PoepleDuplicateRemovalLog;

/**
 * @Description:删除重复数据记录日志dao
 * @author zhangyouwei@hztian.com
 * @date: 2014-8-6 上午09:12:00
 */
@DynamicIbatisDAO(value = "PoepleDuplicateRemovalLogDAO", sqlMapClientTemplate = "sqlMapClientTemplate")
@Repository("PoepleDuplicateRemovalLogDAO")
public interface PoepleDuplicateRemovalLogDAO {

	/**
	 * 新增去重操作日志
	 * 
	 * @param poepleDuplicateRemovalLog
	 * @return
	 */
	public Long addPoepleDuplicateRemovalLog(
			PoepleDuplicateRemovalLog poepleDuplicateRemovalLog);

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public PoepleDuplicateRemovalLog getPoepleDuplicateRemovalLogById(Long id);

	/**
	 * 根据baseInfoId分组查询重复数据
	 * 
	 * @param map
	 *            【baseInfoId、tableName】
	 * @return
	 */
	public List<DuplicatePeople> queryDuplicatePeopleBaseInfoForList(
			Map<String, Object> map);

	/**
	 * 根据baseInfoId、tableName、orgId查询同一网格下重复数据（业务人员）
	 * 
	 * @param map
	 *            【baseInfoId、tableName、orgId】
	 * @return
	 */
	public List<DuplicatePeople> queryDuplicatePeopleAllForList(
			Map<String, Object> map);

	/**
	 * 统一修改所有的baseInfoId根据原来的baseInfoId（全部的业务信息）
	 * 
	 * @param map
	 *            【baseInfoId、tableName、oldBaseInfoId】
	 */
	public void updateAllBusinessPopulationDuplicate(Map<String, Object> map);

	/**
	 * 根据id、baseInfoId、tableName、orgId修改原来的baseInfoId（单类业务人员信息）
	 * 
	 * @param map
	 *            【id、baseInfoId、tableName、orgId】
	 */
	public void updateBusinessPopulationDuplicate(Map<String, Object> map);

	/**
	 * 根据baseInfoId、tableName、orgId查询同一网格下重复数据(户籍、流口)
	 * 
	 * @param map
	 *            【baseInfoId、tableName、orgId】
	 * @return
	 */
	public List<DuplicatePeople> queryDuplicatePeopleAllActualForList(
			Map<String, Object> map);

	/**
	 * 查询单张表的同网格同baseInfoId的重复数据的总数
	 * 
	 * @param tableName
	 * @return
	 */
	public Integer getAlonePopulationDuplicateTotal(Map<String, Object> map);

	/**
	 * 分页查询数据自定义分页
	 * 
	 * @param map
	 * @return
	 */
	public List<DuplicatePeople> queryDuplicatePeopleByGroupByPagingForList(
			Map<String, Object> map);

	/**
	 * 统计出baseInfo的重复数据的条数
	 * 
	 * @return
	 */
	public Integer getBaseIbfoPopulationDuplicateTotal(Map<String, Object> map);

	/**
	 * 统计流动、户籍所有的业务的条数
	 * 
	 * @param map
	 * @return
	 */
	public Integer getActualPopulationHasBusinessTotal(Map<String, Object> map);

	/**
	 * 根据表和老的baseInfoId修改表的baseInfoId
	 * 
	 * @param map
	 * @return
	 */
	public int updateBaseInfoIdByTableNameAndOldBaseInfoId(
			Map<String, Object> map);

	/**
	 * 根据baseInfoId去查询所有的数据
	 * 
	 * @param map
	 * @return
	 */
	public List<DuplicatePeople> queryDuplicatePeopleToLogForList(
			Map<String, Object> map);

	public List<DuplicatePeople> queryAllHouseholdStaffAndFloatingDuplicatePeopleByPagingForList(
			Map<String, Object> map);

	public List<DuplicatePeople> queryAllBusinessPopulationDuplicatePeopleByPagingForList(
			Map<String, Object> map);

}
