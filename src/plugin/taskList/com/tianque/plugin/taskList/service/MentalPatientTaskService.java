package com.tianque.plugin.taskList.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.taskList.domain.MentalPatientTask;

public interface MentalPatientTaskService {
	/**
	 * 修改精神病患者走访记录
	 * @param mentalPatientTask
	 */
	public MentalPatientTask updateMentalPatientTask(MentalPatientTask mentalPatientTask);

	/**
	 * 新增精神病患者走访记录
	 * @param mentalPatientTask
	 * @param attachFileNames 
	 * @param attachFiles 
	 * @param attachFile 
	 * @return
	 */
	public MentalPatientTask addMentalPatientTask(MentalPatientTask mentalPatientTask);

	/**
	 * 删除某次精神病患者走访记录
	 * @param ids
	 */
	public void deleteMentalPatientTaskByIds(List<Long> ids);

	/**
	 * 获取精神病患者走访记录list
	 * @param sord 
	 * @param sidx 
	 * @param rows 
	 * @param page 
	 * @param mentalPatientTask 
	 * @return
	 */
	public PageInfo getMentalPatientTaskList(MentalPatientTask mentalPatientTask, Integer page,
			Integer rows, String sidx, String sord);

	/**
	 * 根据id查询某次精神病患者走访记录
	 * @param id
	 * @return
	 */
	public MentalPatientTask getMentalPatientTaskById(Long id);

	/**
	 * 搜索 高级搜索
	 * @param mentalPatientTask
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo searchMentalPatient(MentalPatientTask mentalPatientTask, Integer page,
			Integer rows, String sidx, String sord);

}
