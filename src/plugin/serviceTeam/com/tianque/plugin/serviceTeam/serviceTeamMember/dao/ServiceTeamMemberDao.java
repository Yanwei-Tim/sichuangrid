package com.tianque.plugin.serviceTeam.serviceTeamMember.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.serviceTeam.router.vo.ServiceMemberVo;
import com.tianque.plugin.serviceTeam.serviceTeamMember.domain.ServiceTeamMember;
import com.tianque.plugin.serviceTeam.serviceTeamMember.domain.ServiceTeamMemberBase;
import com.tianque.plugin.serviceTeam.serviceTeamMember.vo.ServiceMemberInTeamVo;
import com.tianque.plugin.serviceTeam.serviceTeamMember.vo.ServiceTeamMemberVo;

/**
 * 服务成员数据访问层接口
 * 
 * @author tengjia
 */
public interface ServiceTeamMemberDao {

	/**
	 * 新增服务成员信息
	 * 
	 * @param serviceTeamMemberBase
	 * @return ServiceTeamMemberVo 服务成员vo
	 */
	ServiceTeamMemberVo addServiceTeamMemberBase(
			ServiceTeamMemberBase serviceTeamMemberBase);

	/**
	 * 维护成员信息可选成员添加业务
	 * 
	 * @param serviceTeamMemberDetails
	 * @return ServiceTeamMemberVo 服务成员vo
	 */
	ServiceTeamMemberVo addTeamAndMemberRelation(
			ServiceTeamMember serviceTeamMemberDetails);

	/**
	 * 从团队中移除队员
	 * 
	 * @param memberId
	 *            服务成员vo
	 * @return 删除数量
	 */
	public int reMoveTeamAndMemberRelation(Long memberId);

	/**
	 * 更新服务成员
	 * 
	 * @param ServiceTeamMemberBase
	 * @return ServiceTeamMemberVo
	 */
	public ServiceTeamMemberVo updateServiceTeamMemberBase(
			ServiceTeamMemberBase serviceTeamMemberBase);

	/**
	 * 删除服务成员
	 * 
	 * @param id
	 * @return int 删除条数
	 */
	public int deleteServiceTeamMemberBase(Long id);

	/**
	 * 批量删除服务成员
	 * 
	 * @param ids
	 *            传入的参数
	 * @return int 删除条数
	 */
	public int deleteServiceTeamMemberBaseByIds(String[] ids);

	/**
	 * 根据id获取服务成员
	 * 
	 * @param id
	 * @return ServiceTeamMemberVo 服务成员vo
	 */
	public ServiceTeamMemberVo getServiceTeamMemberBaseById(Long id);

	/**
	 * 根据id获取服务成员(Details)
	 * 
	 * @param id
	 * @return ServiceTeamMemberVo 服务成员vo
	 */
	public ServiceTeamMemberVo getServiceTeamMemberDetailsById(Long id);

	/**
	 * 服务记录处查询服务记录所属人
	 * 
	 * @param serviceTeamMemberVo
	 * @return List<ServiceTeamMemberVo>
	 */
	public List<ServiceTeamMemberVo> findServiceMembersForServiceRecord(
			ServiceTeamMemberVo serviceTeamMemberVo);

	/**
	 * 查询服务人员Page
	 * 
	 * @param serviceTeamMemberVo
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 */
	PageInfo<ServiceTeamMemberVo> findServiceTeamMemberVoPage(
			ServiceTeamMemberVo serviceTeamMemberVo, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	PageInfo<ServiceTeamMemberVo> findServiceTeamMemberVoPageNotInTeam(
			ServiceTeamMemberVo serviceTeamMemberVo, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	/**
	 * 从业务人员角度 查询团队成员列表
	 */
	PageInfo<ServiceTeamMemberVo> findServiceMemberFromTeams(
			ServiceTeamMemberVo serviceTeamMemberVo, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	PageInfo<ServiceMemberVo> findServiceMembersByServiceMemberVo(
			ServiceMemberVo serviceMemberVo, Integer pageNum, Integer pageSize,
			String sidx, String sord);

	/**
	 * 团队员成离职或重新担任
	 * 
	 * @param serviceTeamMemberVo
	 * @return int
	 */
	public int updateServiceTeamMemberOnDuty(
			ServiceTeamMemberVo serviceTeamMemberVo);

	/**
	 * 根据是否含有成员判断是否可删除团队
	 */
	public boolean isDeleteAbleForTeam(Long id);

	/**
	 * 根据是否属于团队,判断是否可删除成员
	 */
	public boolean isDeleteAbleForTeamMember(Long id);

	/**
	 * 团队成员显示列表
	 */
	PageInfo<ServiceTeamMemberVo> findServiceTeamMemberBasesPageList(
			ServiceTeamMemberVo serviceTeamMemberVo, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	/**
	 * 获得所有成员数量
	 */
	public Integer getAllServiceTeamMembers(String orgCode);

	/**
	 * 导出数据查询
	 */
	public List<ServiceTeamMemberVo> searchServiceTeamMemberForExport(
			ServiceTeamMemberVo serviceTeamMemberCondition, Integer page,
			Integer rows, String sidx, String sord);

	/**
	 * 通过成员的基本信息，取得成员的详细信息
	 * 
	 * @param baseId
	 * @return
	 */
	List<ServiceTeamMemberVo> getServiceTeamMemberDetailsByBaseId(Long baseId);

	/**
	 * 通过服务成员在团队中的id查询该成员所在的所有团队的信息
	 * 
	 * @param memberId
	 * @return
	 */
	List<ServiceMemberInTeamVo> getServiceMemberInTeamVoByMemberId(Long memberId);

	/**
	 * 根据电话和名字获得重复数据列表
	 * 
	 * @param ServiceTeamMemberVo
	 *            serviceTeamMemberVo, Integer page, Integer rows
	 * @return PageInfo<ServiceTeamMemberVo> @
	 */
	public PageInfo<ServiceTeamMemberVo> findSameMembersByNameAndMobile(
			ServiceTeamMemberVo serviceTeamMemberVo, Integer page, Integer rows);

	/** 根据一个baseId查出所有detail（团队中的成员信息） */
	public List<ServiceTeamMemberVo> findServiceMembersByBaseId(Long baseId);

	/** 合并操作，实际操作为修改成员表中的baseId字段，使各个成员信息的baseId统一为标准的baseId */
	public void combine(ServiceTeamMemberVo combineVo);

	/** 将合并后的成员修改为需要的在职情况和职位 */
	public void updateServiceTeamMemberOnDutyAndPosition(
			ServiceTeamMemberVo serviceTeamMemberVo);

	/** 层级转移 */
	public void changeOrg(ServiceTeamMemberBase serviceTeamMemberBase);

	public Integer findServiceMembersNumByServiceMemberVo(
			ServiceMemberVo serviceMemberVo);

	/*
	 * 用于批量添加成员时，查询团队成员列表
	 */
	public PageInfo<ServiceTeamMemberVo> findServiceMembersFromTeams(
			ServiceTeamMemberVo serviceTeamMemberVo, Integer pageNum,
			Integer pageSize, String sidx, String sord);
}