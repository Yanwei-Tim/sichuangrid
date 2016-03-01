package com.tianque.plugin.serviceTeam.serviceGuardersWithObject.service;

import com.tianque.plugin.serviceTeam.serviceGuardersWithObject.domain.ServiceGuardersWithObject;
import com.tianque.plugin.serviceTeam.serviceGuardersWithObject.vo.ServiceGuardersWithObjectVo;

/**
 * @ClassName: ServiceGuardersWithObjectService
 * @Description: 监护人员和服务对象的业务处理接口
 */
public interface ServiceGuardersWithObjectService {
	/**
	 * 为一个服务对象添加监护人员
	 * 
	 * @param serviceGuardersWithObject
	 * @return ServiceGuardersWithObjectVo 监护成员vo
	 */
	ServiceGuardersWithObjectVo addObjectAndGuardersRelation(
			ServiceGuardersWithObject serviceGuardersWithObject);

	/**
	 * 根据id获取监护人员和服务对象的关联信息
	 * 
	 * @param id
	 * @return ServiceGuardersWithObjectVo @
	 */
	public ServiceGuardersWithObjectVo getServiceGuardersWithObjectById(Long id);

	/**
	 * 根据ID删除监护人和服务对象的关联信息
	 * 
	 * @param selectedIds
	 *            准备删除的记录ID
	 * @return int 删除条数
	 */
	public int deleteServiceGuardersWithObject(String selectedIds);

	/**
	 * 转移时，修改监护人员关联
	 * */
	public void updateServiceGuardersHasObject(String objectType,
			Long oldObjectId, Long newObjectId);

	/**
	 * 转移时，修改监护人员关联（用户社区矫正和刑释解教）
	 * */
	public void updateServiceGuardersHasObject(String objectType,
			Long oldObjectId, Long newObjectId, String newObjectType);
}
