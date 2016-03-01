package com.tianque.partyBuilding.doubleRegActivities.service;

import com.tianque.core.base.BaseService;
import com.tianque.partyBuilding.doubleRegActivities.domain.ServiceProject;
import com.tianque.partyBuilding.doubleRegActivities.domain.Vo.SearchServiceProjectVo;

/**
 * 服务项目表:业务逻辑层接口
 * 
 * @author
 * @date 2014-02-26 10:02:48
 */
public interface ServicePojectService extends
		BaseService<ServiceProject, SearchServiceProjectVo> {

	/**
	 * 验证是否存在相同的项目名称
	 * 
	 * @param id
	 * @param orgId
	 * @param projectName
	 * @return
	 */
	String hasDuplicateProjectName(Long id, Long orgId, String projectName);

	/**
	 * 根据服务项目和组织机构id统计已认领数
	 * 
	 * @param id
	 * @param orgId
	 * @return
	 */
	Integer countClaimedByIdAndOrgId(Long id, Long orgId);

	/**
	 * 根据ids删除服务项目
	 * 
	 * @param idsLong
	 */
	void deleteServiceProjectByIds(Long[] ids);

}
