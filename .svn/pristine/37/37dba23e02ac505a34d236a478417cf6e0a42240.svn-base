package com.tianque.working.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.working.domain.WorkingRecord;
import com.tianque.working.vo.SearchWorkingRecordVo;

public interface WorkingRecordDao {
	/**
	 * 根据orgInternalCode及dailyDirectoryId查询分页数据
	 * 
	 * @param orgInternalCode
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @param dailyDirectoryId
	 * @param displayLevel
	 * @return
	 */
	PageInfo<WorkingRecord> findWorkingRecordForPageByOrgCodeAndDailyDirectoryId(
			String orgInternalCode, Integer pageNum, Integer pageSize, String sidx, String sord,
			String dailyDirectoryId, boolean displayLevel);

	/**
	 * 添加台帐
	 * 
	 * @param workingRecord
	 * @return 返回新增的台帐
	 */
	public WorkingRecord addWorkingRecord(WorkingRecord workingRecord);

	/**
	 * 使用searchVo查询
	 * 
	 * @param searchWorkingRecordVo
	 * @param orgInternalCode
	 * @param allDailyDirectoryId
	 * @param pageNum
	 * @param pageSize
	 * @param sortField
	 * @param order
	 * @return
	 */
	public PageInfo findPagerBySearchVo(SearchWorkingRecordVo searchWorkingRecordVo,
			String orgInternalCode, String allDailyDirectoryId, Integer pageNum, Integer pageSize,
			String sortField, String order);

	/**
	 * 删除台账
	 * 
	 * @param id
	 */
	public void deleteWorkingRecordById(Long id);

	/**
	 * 修改工作台账
	 * 
	 * @param workingRecord
	 * @return
	 */
	public WorkingRecord update(WorkingRecord workingRecord);

	/**
	 * 新增工作台账
	 * 
	 * @param workingRecord
	 * @return workingRecord
	 */
	public WorkingRecord add(WorkingRecord workingRecord);

	public WorkingRecord getWorkingRecordById(Long id);

	public Long countSubTablesValue(Long directoryId);

	public List<WorkingRecord> findWorkingRecordByDailyDirectoryId(Long directoryId,
			Long organizationId);

	// int countWorkingRecordsByDailyDirectoryId(Long id);

	int countWorkingRecordsByDailyDirectoryId(String orgCode, Long id);

	List<WorkingRecord> findWorkingRecordByIds(List<Long> listIds);
}
