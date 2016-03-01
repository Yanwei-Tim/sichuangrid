package com.tianque.baseInfo.base.service;

import java.util.List;

import com.tianque.baseInfo.base.domain.DuplicatePeople;
import com.tianque.baseInfo.base.domain.PoepleDuplicateRemovalLog;

/**
 * @Description:删除重复数据记录日志service接口
 * @author zhangyouwei@hztian.com
 * @date: 2014-8-6 上午08:50:02
 */
public interface PoepleDuplicateRemovalLogService {

	/**
	 * 新增日志记录
	 * 
	 * @param poepleDuplicateRemovalLog
	 * @return
	 */
	public PoepleDuplicateRemovalLog addPoepleDuplicateRemovalLog(
			PoepleDuplicateRemovalLog poepleDuplicateRemovalLog);

	/**
	 * 根据id获取去重日志记录
	 * 
	 * @param id
	 * @return
	 */
	public PoepleDuplicateRemovalLog getPoepleDuplicateRemovalLogById(Long id);

	/**
	 * 根据baseId去查询对应的业务表的的重复数据
	 * 
	 * @param baseInfoId
	 *            基础表的id
	 * @param tableName
	 *            业务表表名称
	 * @return
	 */
	public List<DuplicatePeople> getDuplicatePeople(Long baseInfoId,
			String tableName);

	/**
	 * 根据基础的重复数据的标准去获取全部对应网格对应baseId的多余的数据（业务人员）
	 * 
	 * @param baseInfoId
	 * @param orgId
	 * @param tableName
	 * @return
	 */
	public List<DuplicatePeople> getBusinessPopulationByOrgIdAndBaseInfoId(
			Long baseInfoId, Long orgId, String tableName);

	/**
	 * 根据基础的重复数据的标准去获取全部对应网格对应baseId的多余的数据(户籍、流动)
	 * 
	 * @param baseInfoId
	 * @param orgId
	 * @param tableName
	 * @return
	 */
	public List<DuplicatePeople> getActualPopulationByOrgIdAndBaseInfoId(
			Long baseInfoId, Long orgId, String tableName);

	/**
	 * 查询单张表的同网格同baseInfoId的重复数据的总数
	 * 
	 * @param tableName
	 * @return
	 */
	public Integer getAlonePopulationDuplicateTotal(String tableName);

	/**
	 * 查询根据分组的重复数据（ordId和baseInfoId）并且分页
	 * 
	 * @param startRow
	 * @param endRow
	 * @return
	 */
	public List<DuplicatePeople> queryDuplicatePeopleByGroupByPagingForList(
			String tableName, int startRow, int endRow);

	/**
	 * 统计出baseInfo表的重复数据有多少条
	 * 
	 * @return
	 */
	public Integer getBaseIbfoPopulationDuplicateTotal();

	/**
	 * 根据户籍和流动去统计所有业务信息的个数
	 * 
	 * @param id
	 * @param tableName
	 * @param actualtype
	 * @return
	 */
	public Integer getActualPopulationHasBusinessTotal(Long actualId,
			String tableName, String actualType);

	/**
	 * 根据表和老的baseInfoId修改表的baseInfoId
	 * 
	 * @param oldBaseInfoId
	 * @param tableName
	 * @param newBaseInfoId
	 * @return
	 */
	public Integer updateBaseInfoIdByTableNameAndOldBaseInfoId(
			Long oldBaseInfoId, String tableName, Long newBaseInfoId);

	/**
	 * 在修改basInfoId之前记录日志是信息（根据baseId查询数据）
	 * 
	 * @param baseInfoId
	 * @param tableName
	 * @return
	 */
	public List<DuplicatePeople> queryDuplicatePeopleToLogForList(
			Long baseInfoId, String tableName);

	public List<DuplicatePeople> getAllBusinessPopulationDuplicatePeopleByPaging(
			int startRow, int endRow, String tableName);

	public List<DuplicatePeople> getAllHouseholdStaffAndFloatingDuplicatePeopleByPaging(
			int startRow, int endRow, String tableName);
}
