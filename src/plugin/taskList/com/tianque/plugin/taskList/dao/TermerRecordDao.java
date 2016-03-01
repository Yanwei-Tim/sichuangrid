package com.tianque.plugin.taskList.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.taskList.domain.TermerRecord;
import com.tianque.plugin.taskList.domain.TermerRecordVo;

/**
 * 社区服刑人员记录
 * 任务清单使用
 * @author lanhaifeng
 *
 */
public interface TermerRecordDao{
	/**
	 * 新增记录信息
	 * 
	 * @param TermerRecord
	 *            记录信息domain
	 * @return TermerRecord
	 */
	public TermerRecord addTermerRecord(TermerRecord termerRecord);

	/**
	 * 返回单个记录
	 * 
	 * @param id  记录id
	 * @return TermerRecord
	 */
	public TermerRecord getTermerRecordById(Long id);

	/**
	 * 获取记录分页数
	 * 
	 * @param termerRecordVo 查询domain
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return PageInfo
	 */
	public PageInfo<TermerRecord> findTermerRecords(
			TermerRecordVo termerRecordVo, Integer pageNum, Integer pageSize,String sidx, String sord);

	/**
	 * 获取记录条数
	 * @param termerRecord  查询domain
	 * @return
	 */
	public Integer countTermerRecords(TermerRecord termerRecord);

	/**
	 * 更新记录信息
	 * 
	 * @param termerRecord   记录信息domain
	 * @return TermerRecord
	 */
	public TermerRecord updateTermerRecord(TermerRecord termerRecord);
	
	/**
	 * 根据姓名获取记录信息
	 * 
	 * @param termerRecordVo 查询domain
	 * @return List
	 */
	public List<TermerRecord> findTermerRecordsByName(TermerRecordVo termerRecordVo);

	/**
	 * 删除记录
	 * 
	 * @param ids  记录id数组
	 * @return Integer 删除条数
	 */
	public Integer deleteTermerRecords(List<Long> ids);
}
