package com.tianque.plugin.serviceTeam.router;

import java.util.Map;

import com.tianque.domain.Organization;

public interface RouterService {

	public Map<String, Integer> getServiceTeamDataForWorkBench(Organization org);

	/**
	 * 删除服务对象和服务团队的关联记录 删除服务对象和服务人员的关联记录
	 * 
	 * @param ObjectType对象类型
	 *        ，ObejectId 对象id
	 * @return
	 */
	public void deleteServiceTeamHasObjectsAndServiceMemberHasObject(String ObjectType,
			Long ObjectId);

	/**
	 * 修改对象和服务团队的关联记录 修改服务对象和服务人员的关联记录（注销状态修改）
	 * 
	 * @param ObjectType对象类型
	 *        ，ObejectId 对象id,logout注销状态
	 * @return
	 */
	public void updateServiceTeamHasObjectsAndServiceMemberHasObject(String ObjectType,
			Long ObjectId, Long logout);

	/**删除业务人员或者场所时保存删除对象的orgId和身份证（场所名）*/
	public void setOrgIdAndCardNoOrName(Long orgId, String cardNoOrName, String objectType,
			Long objectId);
}
