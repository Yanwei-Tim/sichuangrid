package com.tianque.partyBuilding.doubleRegActivities.dao;

import com.tianque.core.base.BaseDao;
import com.tianque.partyBuilding.doubleRegActivities.domain.ServiceProject;
import com.tianque.partyBuilding.doubleRegActivities.domain.Vo.SearchServiceProjectVo;

/**
 * 服务项目表:数据操作层接口
 * 
 * @author
 * @date 2014-02-26 10:02:48
 */
public interface ServiceProjectDao extends
		BaseDao<ServiceProject, SearchServiceProjectVo> {

	/**
	 * 根据projectName和组织机构id得到服务项目信息
	 * 
	 * @param projectName
	 * @param orgId
	 * @return
	 */
	ServiceProject getServiceProjectByProjectNameAndOrgId(String projectName,
			Long orgId);

	/**
	 * 根据服务项目和组织机构id统计已认领数
	 * 
	 * @param id
	 * @param orgId
	 * @return
	 */
	Integer countClaimedByIdAndOrgId(Long id, Long orgId);

	/**
	 * 批量删除
	 * 
	 * @param ids
	 */
	void deleteServiceProjectByIds(Long[] ids);

}
