package com.tianque.plugin.taskList.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.taskList.domain.PropagandaAndVerification;
import com.tianque.plugin.taskList.domain.PropagandaAndVerificationVo;

/**
 * 发现隐患模块service层接口
 * 
 * @author GAOHU
 *
 */
public interface PropagandaAndVerificationService {

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public PropagandaAndVerification getPropagandaAndVerificationById(Long id);

	/**
	 * 新增
	 * 
	 * @param propagandaAndVerification
	 * @return
	 */
	public PropagandaAndVerification addPropagandaAndVerification(
			PropagandaAndVerification propagandaAndVerification);

	/**
	 * 批量删除
	 * 
	 * @param ids
	 */
	public void deletePropagandaAndVerification(List<Long> ids);

	/**
	 * 更新
	 * 
	 * @param propagandaAndVerification
	 * @return
	 */
	public PropagandaAndVerification updatePropagandaAndVerification(
			PropagandaAndVerification propagandaAndVerification);

	/**
	 * 查询
	 * 
	 * @param propagandaAndVerificationVo
	 * @param pageNum
	 * @param pageSize
	 * @param sortField
	 * @param order
	 * @return
	 */
	public PageInfo<PropagandaAndVerification> searchPropagandaAndVerification(
			PropagandaAndVerificationVo propagandaAndVerificationVo,
			Integer pageNum, Integer pageSize, String sortField, String order);

	/**
	 * 更新签发状态
	 * 
	 * @param propagandaAndVerification
	 * @return
	 */
	public PropagandaAndVerification updatePropagandaAndVerificationSignDetail(
			PropagandaAndVerification propagandaAndVerification);

	
	/**
	 * 根据姓名获取记录信息
	 * 
	 * @param propagandaAndVerificationVo 查询domain
	 * @return List
	 */
	public List<PropagandaAndVerification> findPropagandaAndVerificationByName(PropagandaAndVerificationVo propagandaAndVerificationVo);

}
