package com.tianque.plugin.serviceTeam.serviceMemberWithObject.dao;

import java.util.List;
import java.util.Map;

import com.tianque.plugin.serviceTeam.serviceMemberWithObject.domain.ServiceMemberWithObject;
import com.tianque.plugin.serviceTeam.serviceMemberWithObject.vo.ServiceMemberWithObjectVo;

/**
 * 服务成员和服务对象数据访问层接口
 * 
 * @author tengjia
 */
public interface ServiceMemberWithObjectDao {
	/**
	 * 为一个服务对象添加服务人员
	 * 
	 * @param serviceObjectMember
	 * @return ServiceMemberWithObjectVo 服务成员vo
	 */
	ServiceMemberWithObjectVo addObjectAndMemberRelation(
			ServiceMemberWithObject serviceObjectMember);

	/**
	 * 根据id获取服务成员和服务对象的关联信息
	 * 
	 * @param id
	 * @return ServiceMemberWithObjectVo 服务成员vo
	 */
	public ServiceMemberWithObjectVo getServiceMemberWithObjectById(Long id);

	/**
	 * 根据ID删除服务成员和服务对象的关联信息
	 * 
	 * @param id
	 * @return int 删除条数
	 */

	public int deleteServiceMemberWithObject(Long id);

	/**
	 * 删除服务对象和服务人员的关联记录
	 * 
	 * @param ObjectType对象类型
	 *            ，ObejectId 对象id
	 * @return
	 */
	public void deleteServiceMemberHasObject(
			ServiceMemberWithObject serviceMemberWithObject);

	/**
	 * 更新服务成员
	 * 
	 * @param ServiceTeamMemberBase
	 * @return ServiceTeamMemberVo
	 */
	public int updateServiceMemberWithObject(
			ServiceMemberWithObject serviceObjectMember);

	/**
	 * 修改服务对象和服务人员的关联记录（注销状态修改）
	 * 
	 * @param ObjectType对象类型
	 *            ，ObejectId 对象id,logout注销状态
	 * @return
	 */
	public void updateServiceMemberHasObject(
			ServiceMemberWithObject serviceMemberWithObject);

	/**
	 * 通过成员id，服务对象类型和id获取成员和服务对象的关联关系
	 * 
	 * @param serviceMemberWithObjectVo
	 * @return
	 */
	public ServiceMemberWithObjectVo getServiceMemberWithObjectVoByMemberIdAndObjectTypeAndId(
			ServiceMemberWithObjectVo serviceMemberWithObjectVo);

	/**
	 * 根据memberId查出关联关系列表
	 * 
	 * @param memberId
	 * @return List<ServiceMemberWithObjectVo>
	 */
	public List<ServiceMemberWithObjectVo> findServiceMemberWithObjectsByMemberId(
			Long memberId);

	/** 将传入对象的全部重复成员memberId修改为标准memberId */
	public void updateRepeatData(
			ServiceMemberWithObjectVo serviceMemberWithObjectVo);

	/**
	 * 根据objectType和objectId查出关联关系列表
	 * 
	 * @param objectType
	 *            、objectId
	 * @return List<ServiceMemberWithObjectVo>
	 */
	public List<ServiceMemberWithObjectVo> findServiceMemberWithObjectsByTypeAndId(
			String objectType, Long objectId);

	/** 修改重复数据合并后的在职情况（对象处） */
	public void updateOnDuty(ServiceMemberWithObjectVo serviceMemberWithObjectVo);

	/** 修改与服务成员的关联关系（对象转移时用） */
	public void updateServiceMemberWithObject(
			ServiceMemberWithObjectVo serviceMemberWithObjectVo);

	/**************** 组织机构迁移合并 *********************/
	/**
	 * 
	 * @Title: queryServiceTeamHasObjectsForList
	 * @Description: TODO查询重复的服务人员
	 * @param @param map
	 * @param @return 设定文件
	 * @return List<ServiceMemberWithObject> 返回类型
	 * @author wanggz
	 * @date 2014-10-28 上午10:04:56
	 */
	public List<ServiceMemberWithObject> queryServiceMemberWithObjectForList(
			Map map);

	/**
	 * map 存放 的是List<Long> objectId ,objectType 根据objectType和objectId查出关联关系列表
	 * 
	 * @param map
	 * @return
	 */
	public List<ServiceMemberWithObjectVo> findServiceMembersWithObjectsByTypeAndId(
			Map map);
}