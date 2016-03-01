package com.tianque.plugin.serviceTeam.serviceGuardersWithObject.dao;

import com.tianque.plugin.serviceTeam.serviceGuardersWithObject.domain.ServiceGuardersWithObject;
import com.tianque.plugin.serviceTeam.serviceGuardersWithObject.vo.ServiceGuardersWithObjectVo;

/**
 * @ClassName: ServiceGuardersWithObjectDao
 * @Description: 监护人员和服务对象数据访问层接口
 */
public interface ServiceGuardersWithObjectDao {
	/**
	 * 为一个服务对象添加监护人员
	 * 
	 * @param serviceGuardersWithObject
	 * @return ServiceGuardersWithObjectVo 监护人员vo
	 */
	ServiceGuardersWithObjectVo addObjectAndGuardersRelation(
			ServiceGuardersWithObject serviceGuardersWithObject);

	/**
	 * 根据id获取监护人员和服务对象的关联信息
	 * 
	 * @param id
	 * @return ServiceGuardersWithObjectVo 监护人员vo
	 */
	public ServiceGuardersWithObjectVo getServiceGuardersWithObjectById(Long id);

	/**
	 * 根据ID删除监护人员和服务对象的关联信息
	 * 
	 * @param id
	 * @return int 删除条数
	 */
	public int deleteServiceGuardersWithObject(Long id);

	/**
	 * 根据ID批量删除监护人员和服务对象的关联信息
	 * 
	 * @param id
	 * @return int 删除条数
	 */
	public int deleteServiceGuardersWithObjectByIds(String[] ids);

	/**
	 * 转移时，修改监护人员关联
	 * */
	public void updateServiceGuardersHasObject(String objectType,Long oldObjectId,Long newObjectId);
	/**
	 * 转移时，修改监护人员关联（用户社区矫正和刑释解教）
	 * */
	public void updateServiceGuardersHasObject(String objectType,Long oldObjectId,Long newObjectId,String newObjectType);
}
