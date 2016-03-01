package com.tianque.plugin.serviceTeam.serviceTeamHasObjects.dao;

import java.util.List;
import java.util.Map;

import com.tianque.plugin.serviceTeam.serviceTeamHasObjects.domain.ServiceTeamHasObjects;
import com.tianque.plugin.serviceTeam.serviceTeamMember.vo.ServiceTeamMemberVo;

/**
 * 服务团队与对象DAO层
 * 
 * @author yangpengdian
 */
public interface ServiceTeamHasObjectsDao {
	/**
	 * 查找团队成员关联的对象是否与团队关联
	 * 
	 * @param serviceTeamMemberVo
	 * @return
	 */
	public int findServiceObjectInServiceTeam(
			ServiceTeamMemberVo serviceTeamMemberVo);

	/**
	 * 为服务团队和服务对象添加关联关系
	 * 
	 * @param serviceObjectMember
	 * @return ServiceMemberWithObjectVo 服务成员vo
	 */
	public void addObjectAndTeamRelation(
			ServiceTeamHasObjects serviceTeamHasObjects);

	/**
	 * 删除服务对象和服务团队的关联记录
	 * 
	 * @param id
	 * @return
	 */
	public void deleteServiceTeamHasObjects(
			ServiceTeamHasObjects serviceTeamHasObjects);

	/**
	 * 删除服务对象和服务团队的关联记录
	 * 
	 * @param ObjectType对象类型
	 *            ，ObejectId 对象id
	 * @return
	 */
	public void deleteServiceTeamWithObjects(
			ServiceTeamHasObjects serviceTeamHasObjects);

	/**
	 * 卸任，重新担任 （服务对象和服务团队关联表修改onDuty值）
	 */
	public void updateServiceTeamHasObjects(
			ServiceTeamHasObjects serviceTeamHasObjects);

	/**
	 * 修改对象和服务团队的关联记录 （注销状态修改）
	 * 
	 * @param ObjectType对象类型
	 *            ，ObejectId 对象id,logout注销状态
	 * @return
	 */
	public void updateServiceTeamWithObjects(
			ServiceTeamHasObjects serviceTeamHasObjects);

	/**
	 * 转移时，修改关联关系
	 * */
	public void updateServiceMemberHasObjectForMove(String objectType,
			Long oldObjectId, Long newObjectId);

	public void updateServiceTeamHasObjectForMove(String objectType,
			Long oldObjectId, Long newObjectId);

	/**
	 * 转移时，修改关联关系（用于社区矫正和刑释解教）
	 * */
	public void updateServiceMemberHasObjectForMove(String objectType,
			Long oldObjectId, Long newObjectId, String newObjectType);

	public void updateServiceTeamHasObjectForMove(String objectType,
			Long oldObjectId, Long newObjectId, String newObjectType);

	/************************ 迁移合并方法 *****************************/
	/**
	 * 
	 * @Title: queryServiceTeamHasObjectsForList
	 * @Description: TODO查询重复服务团队关系
	 * @param @param map
	 * @param @return 设定文件
	 * @return List<ServiceTeamHasObjects> 返回类型
	 * @author wanggz
	 * @date 2014-10-28 上午10:31:28
	 */
	public List<ServiceTeamHasObjects> queryServiceTeamHasObjectsForList(Map map);

}
