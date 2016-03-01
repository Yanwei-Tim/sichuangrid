package com.tianque.partyBuilding.doubleRegActivities.service;

import com.tianque.core.base.BaseService;
import com.tianque.partyBuilding.doubleRegActivities.domain.VolunteerJobs;
import com.tianque.partyBuilding.doubleRegActivities.domain.Vo.SearchVolunteerJobsVo;

/**
 * 志愿者岗位表:业务逻辑层接口
 * 
 * @author
 * @date 2014-02-27 10:06:23
 */
public interface VolunteerJobsService extends
		BaseService<VolunteerJobs, SearchVolunteerJobsVo> {

	/**
	 * 验证是否存在相同的志愿者岗位
	 * 
	 * @param id
	 * @param orgId
	 * @param name
	 * @return
	 */
	String hasDuplicateName(Long id, Long orgId, String name);

	/**
	 * 根据志愿者岗位id和组织机构id统计已认领数
	 * 
	 * @param id
	 * @param orgId
	 * @return
	 */
	Integer countClaimedByIdAndOrgId(Long id, Long orgId);

	/**
	 * 根据ids删除志愿者岗位信息
	 * 
	 * @param ids
	 */
	void deleteVolunteerJobsByIds(Long[] ids);

}
