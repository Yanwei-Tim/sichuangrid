package com.tianque.plugin.serviceTeam.serviceMemberWithObject.service;

import java.util.List;
import java.util.Map;

import com.tianque.plugin.serviceTeam.serviceMemberWithObject.domain.ServiceMemberWithObject;
import com.tianque.plugin.serviceTeam.serviceMemberWithObject.vo.ServiceMemberWithObjectVo;

/**
 * 服务成员和服务对象的业务处理接口
 * 
 * @author tengjia
 */
public interface ServiceMemberWithObjectService {
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
	 * @return ServiceMemberWithObjectVo @
	 */
	public ServiceMemberWithObjectVo getServiceMemberWithObjectById(Long id);

	/**
	 * 根据ID删除服务成员和服务对象的关联信息
	 * 
	 * @param selectedIds
	 *            准备删除的记录ID
	 * @return int 删除条数
	 */
	public int deleteServiceMemberWithObjectById(String selectedIds);

	/**
	 * 修改服务成员信息
	 * 
	 * @param serviceObjectMember
	 * @return ServiceMemberWithObjectVo @
	 */
	public int updateServiceMemberWithObject(
			ServiceMemberWithObject serviceObjectMember);

	public void updateServiceTeamHasObjects(Long teamId, Long memberId,
			Long onDuty);

	/**
	 * 通过成员id，服务对象类型和id获取成员和服务对象的关联关系
	 * 
	 * @param memberId
	 * @param objectId
	 * @param objectType
	 * @return
	 */
	public ServiceMemberWithObjectVo getServiceMemberWithObjectVoByMemberIdAndObjectTypeAndId(
			Long memberId, Long objectId, String objectType);

	/**
	 * 服务成员处删除对象（的关联关系）
	 * 
	 * @param memberIds
	 * @param serviceObjectVo
	 * @return
	 */
	public int deleteServiceObjectFromMember(List<Long> memberIds,
			ServiceMemberWithObject serviceMemberWithObject);

	/**
	 * 根据memberId查出关联关系列表
	 * 
	 * @param memberId
	 * @return List<ServiceMemberWithObjectVo>
	 */
	public List<ServiceMemberWithObjectVo> findServiceMemberWithObjectsByMemberId(
			Long memberId);

	/**
	 * 转移时，修改关联关系
	 * */
	public void updateServiceMemberAndTeamHasObjectForMove(String objectType,
			Long oldObjectId, Long newObjectId);

	/**
	 * 转移时，修改关联关系（用于社区矫正和刑释解教）
	 * */
	public void updateServiceMemberAndTeamHasObjectForMove(String objectType,
			Long oldObjectId, Long newObjectId, String newObjectType);

	/************************ 组织机构迁移合并方法 ************************/
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
	 * 为一个或多个服务对象添加一个或多个服务人员
	 * 
	 * @param serviceObjectMembers
	 * @param isFilter
	 *            是否过滤
	 */
	public void addObjectAndMembersRelation(
			List<ServiceMemberWithObject> serviceObjectMembers, boolean isFilter);

}