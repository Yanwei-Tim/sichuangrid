package com.tianque.partyBuilding.doubleRegActivities.dao;

import com.tianque.core.base.BaseDao;
import com.tianque.partyBuilding.doubleRegActivities.domain.VolunteerJobs;
import com.tianque.partyBuilding.doubleRegActivities.domain.Vo.SearchVolunteerJobsVo;

/**
 * 志愿者岗位表:数据操作层接口
 * 
 * @author
 * @date 2014-02-27 10:06:23
 */
public interface VolunteerJobsDao extends
		BaseDao<VolunteerJobs, SearchVolunteerJobsVo> {

	/**
	 * 根据name和orgId得到志愿者岗位信息
	 * 
	 * @param name
	 * @param orgId
	 * @return
	 */
	VolunteerJobs getVolunteerJobsByNameAndOrgId(String name, Long orgId);

	/**
	 * 根据志愿者岗位id和组织机构id统计已认领数
	 * 
	 * @param id
	 * @param orgId
	 * @return
	 */
	Integer countClaimedByIdAndOrgId(Long id, Long orgId);

	/***
	 * 批量删除
	 * 
	 * @param ids
	 */
	void deleteVolunteerJobsByIds(Long[] ids);

}
