package com.tianque.plugin.serviceTeam.serviceObject.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.serviceTeam.serviceObject.vo.ServiceObjectVo;

public interface ServiceObjectService {

	/**
	 * 获取服务对象
	 * 
	 * @param ServiceObjectVo
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return PageInfo
	 */
	public PageInfo findObjects(ServiceObjectVo serviceObjectVo,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	/**
	 * 获取团队成员的服务对象
	 * 
	 * @param ServiceObjectVo
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return PageInfo
	 */
	public PageInfo findObjectsForServiceTeamMember(
			ServiceObjectVo serviceObjectVo, Integer pageNum, Integer pageSize,
			String sidx, String sord);
	/**
	 * 根据服务团队Id查找相应的服务对象
	 * 
	 * @param teamId
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	/*
	 * public PageInfo findServiceObjectByTeamId(Long teamId, Integer page,
	 * Integer rows, String sidx, String sord);
	 *//**
	 * 新增服务对象
	 * 
	 * @param serviceObject
	 * @param analyzePopulationIds
	 * @return
	 */
	/*
	 * public List<Long> addServiceObject(ServiceObject serviceObject, Long[]
	 * analyzePopulationIds);
	 *//**
	 * 根据业务人员类型和Id查找服务对象的详细信息
	 * 
	 * @param serviceTeamId
	 * @param populationId
	 * @param populationType
	 * @return
	 */
	/*
	 * public ServiceObject findServiceObjectByPopulationIdAndType( Long
	 * serviceTeamId, Long populationId, String populationType);
	 *//**
	 * 根据服务团队Id和所选择的人员Id移除服务对象
	 * 
	 * @param serviceTeamId
	 * @param analyzeSelectedIds
	 * @return
	 */
	/*
	 * public Long deleteServiceObject(Long serviceTeamId, List<ServiceObject>
	 * analyzeSelectedIds);
	 *//**
	 * 服务对象添加服务成员
	 * 
	 * @param serviceTeamId
	 * @param serviceObjects
	 * @param analyzeServers
	 */
	/*
	 * public void addServers(Long serviceTeamId, List<ServiceObject>
	 * serviceObjects, Long[] analyzeServers); public void
	 * addServiceTeamHasObject(ServiceObject serviceObject); public int
	 * countServiceTeamHasObject(ServiceObject serviceObject);
	 *//**
	 * 根据成员Id和团队Id查找服务成员
	 * 
	 * @param serviceTeamMemberId
	 * @param serviceTeamId
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	/*
	 * public PageInfo<ServiceObject>
	 * findServiceObjectForExistedByServiceMemberIdAndTeamId( Long
	 * serviceTeamMemberId, Long serviceTeamId, Integer page, Integer rows,
	 * String sidx, String sord);
	 *//**
	 * 服务成员添加服务对象
	 * 
	 * @param serviceObject
	 * @param analyzePopulationIds
	 * @param analyzeSelectedIds
	 */
	/*
	 * public void addServiceObjectForMember(ServiceObject serviceObject, Long[]
	 * analyzePopulationIds, Long[] analyzeSelectedIds); boolean
	 * countServiceObjectsByMemberId(final Long memberId);
	 *//**
	 * 根据orgInternalCode统计辖区内服务对象人数
	 * 
	 * @param orgInternalCode
	 * @return
	 */
	/*
	 * public Integer countServiceObjectByOrgInternalCode(String
	 * orgInternalCode);
	 */
}
