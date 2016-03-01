package com.tianque.plugin.taskList.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.taskList.domain.WorkingSituation;
import com.tianque.plugin.taskList.domain.WorkingSituationVo;

/**
 * 民警带领下开展工作情况dao层
 * 
 * @author GAOHU
 *
 */
public interface WorkingSituationDao {

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public WorkingSituation getWorkingSituationById(Long id);

	/**
	 * 新增
	 * 
	 * @param hiddenDanger
	 * @return
	 */
	public WorkingSituation addWorkingSituation(
			WorkingSituation workingSituation);

	/**
	 * 批量删除
	 * 
	 * @param ids
	 */
	public void deleteWorkingSituation(List<Long> ids);

	/**
	 * 更新
	 * 
	 * @param hiddenDanger
	 * @return
	 */
	public WorkingSituation updateWorkingSituation(
			WorkingSituation workingSituation);

	/**
	 * 查询
	 * 
	 * @param hiddenDangerVo
	 * @param pageNum
	 * @param pageSize
	 * @param sortField
	 * @param order
	 * @return
	 */
	public PageInfo<WorkingSituation> searchWorkingSituation(
			WorkingSituationVo workingSituationVo, Integer pageNum,
			Integer pageSize, String sortField, String order);
	
	
	/**
	 * 更新签收状态
	 * 
	 * @param workingSituation
	 * @return
	 */
	public WorkingSituation updateWorkingSituationSignDetail(
			WorkingSituation workingSituation);

}
