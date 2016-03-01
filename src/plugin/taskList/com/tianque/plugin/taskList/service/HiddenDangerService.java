package com.tianque.plugin.taskList.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.taskList.domain.HiddenDanger;
import com.tianque.plugin.taskList.domain.HiddenDangerVo;
import com.tianque.plugin.taskList.domain.TaskListAttachFile;

/**
 * 发现隐患模块service层接口
 * 
 * @author GAOHU
 *
 */
public interface HiddenDangerService {

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public HiddenDanger getHiddenDangerById(Long id);

	/**
	 * 新增
	 * 
	 * @param hiddenDanger
	 * @return
	 */
	public void addHiddenDanger(HiddenDanger hiddenDanger,String[] attachFile,String[] attachFiles,String[] attachFileNames);

	/**
	 * 批量删除
	 * 
	 * @param ids
	 */
	public void deleteHiddenDanger(List<Long> ids);

	/**
	 * 更新
	 * 
	 * @param hiddenDanger
	 * @return
	 */
	public HiddenDanger updateHiddenDanger(HiddenDanger hiddenDanger);

	/**
	 * 更新签发状态
	 * 
	 * @param hiddenDanger
	 * @return
	 */
	public HiddenDanger updateHiddenDangerSignDetail(HiddenDanger hiddenDanger);

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
	public PageInfo<HiddenDanger> searchHiddenDanger(
			HiddenDangerVo hiddenDangerVo, String type,Integer pageNum, Integer pageSize,
			String sortField, String order);
	
	/**
	 * 获取附件
	 * @param id
	 */
	public TaskListAttachFile getHiddenDangerAnenx(Long id);

}
