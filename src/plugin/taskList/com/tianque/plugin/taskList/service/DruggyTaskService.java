package com.tianque.plugin.taskList.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.taskList.domain.DruggyTask;

public interface DruggyTaskService {
	/**
	 * 修改吸毒人员走访记录
	 * @param druggyTask
	 */
	public void updateDruggyTask(DruggyTask druggyTask);

	/**
	 * 新增吸毒人员走访记录
	 * @param druggyTask
	 * @param attachFileNames 
	 * @param attachFiles 
	 * @param attachFile 
	 * @return
	 */
	public DruggyTask addDruggyTask(DruggyTask druggyTask);

	/**
	 * 删除吸毒人员走访记录
	 * @param ids
	 */
	public void deleteDruggyTaskByIds(List<Long> ids);

	/**
	 * 获取吸毒人员走访记录list
	 * @param sord 
	 * @param sidx 
	 * @param rows 
	 * @param page 
	 * @param druggyTask 
	 * @return
	 */
	public PageInfo getDruggyTaskList(DruggyTask druggyTask, Integer page, Integer rows,
			String sidx, String sord);

	/**
	 * 根据id获取某次走访记录
	 * @param id
	 * @return
	 */
	public DruggyTask getDruggyTaskById(Long id);

	/**
	 * 搜索 高级搜索
	 * @param druggyTask
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo searchDruggyTask(DruggyTask druggyTask, Integer page, Integer rows,
			String sidx, String sord);

	/**
	 * 根据姓名检索
	 * @param druggyTask
	 * @return
	 */
	public List<DruggyTask> searchDruggyTaskByName(DruggyTask druggyTask);

}
