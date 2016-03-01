package com.tianque.plugin.serviceTeam.serviceTeamMember.service;

import java.util.List;
import java.util.Map;

import com.tianque.core.vo.AutoCompleteData;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.serviceTeam.router.vo.ServiceMemberVo;
import com.tianque.plugin.serviceTeam.serviceTeamHasObjects.domain.ServiceTeamHasObjects;
import com.tianque.plugin.serviceTeam.serviceTeamMember.domain.ServiceTeamMember;
import com.tianque.plugin.serviceTeam.serviceTeamMember.domain.ServiceTeamMemberBase;
import com.tianque.plugin.serviceTeam.serviceTeamMember.vo.ServiceMemberInTeamVo;
import com.tianque.plugin.serviceTeam.serviceTeamMember.vo.ServiceTeamMemberVo;

/**
 * 服务成员业务处理接口
 * 
 * @author tengjia
 */
public interface ServiceTeamMemberService {
	/**
	 * 获取服务成员页面
	 * 
	 * @param serviceTeamMemberVo
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 */
	PageInfo<ServiceTeamMemberVo> findServiceTeamMemberBases(
			ServiceTeamMemberVo serviceTeamMemberVo, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	/**
	 * 根据电话和名字获得重复数据列表
	 * 
	 * @param ServiceTeamMemberVo
	 *            serviceTeamMemberVo, Integer page, Integer rows, String sidx,
	 *            String sord
	 * @return PageInfo<ServiceTeamMemberVo> @
	 */
	public PageInfo<ServiceTeamMemberVo> findSameMembersByNameAndMobile(
			ServiceTeamMemberVo serviceTeamMemberVo, Integer page,
			Integer rows, String sidx, String sord);

	/** 合并重复服务成员 */
	public void combineServiceTeamMembers(
			ServiceTeamMemberBase serviceTeamMemberBase,
			ServiceTeamMemberVo serviceTeamMemberVo, String selectedIds);

	/** 成员层级转移 */
	public void changeOrg(Long orgId, String selectedIds);

	/**
	 * 新增服务成员业
	 * 
	 * @param serviceTeamMemberBase
	 * @return serviceTeamMemberVo 服务成员vo
	 */
	ServiceTeamMemberVo addServiceTeamMemberBase(
			ServiceTeamMemberBase serviceTeamMemberBase);

	/**
	 * 把一个成员添加到团队当中
	 * 
	 * @param serviceTeamMember
	 * @return serviceTeamMemberVo 服务成员vo
	 */
	ServiceTeamMemberVo addTeamAndMemberRelation(
			ServiceTeamMember serviceTeamMember);

	/**
	 * 从团队中移除队员
	 * 
	 * @param serviceTeamMemberVo
	 *            服务成员vo @
	 */
	public int reMoveTeamAndMemberRelation(
			ServiceTeamMemberVo serviceTeamMemberVo);

	/**
	 * 修改服务成员信息
	 * 
	 * @param serviceTeamMemberBase
	 * @return ServiceTeamVo @
	 */
	public ServiceTeamMemberVo updateServiceTeamMemberBase(
			ServiceTeamMemberBase serviceTeamMemberBase);

	/**
	 * 根据ID删除服务成员
	 * 
	 * @param deleteIds
	 *            准备删除的记录ID
	 * @return int 删除条数
	 */
	public int deleteServiceTeamMemberBase(String deleteIds);

	/**
	 * 根据id获取单个服务成员信息
	 * 
	 * @param id
	 * @return serviceTeamMemberVo @
	 */
	public ServiceTeamMemberVo getTeamMemberBaseById(Long id);

	/**
	 * 根据id获取单个服务成员(detial)信息
	 * 
	 * @param id
	 * @return serviceTeamMemberVo @
	 */
	public ServiceTeamMemberVo getServiceTeamMemberDetailsById(Long id);

	/**
	 * 高级搜索
	 * 
	 * @param serviceTeamMemberVo
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return PageInfo
	 */
	PageInfo<ServiceTeamMemberVo> searchServiceTeamMemberBases(
			ServiceTeamMemberVo serviceTeamMemberVo, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	/**
	 * 获取维护队员数据，现有成员
	 * 
	 * @param serviceTeamMemberVo
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 */
	PageInfo<ServiceTeamMemberVo> findServiceTeamMemberVoPageInTeam(
			ServiceTeamMemberVo serviceTeamMemberVo, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	/**
	 * 获取维护队员，可选成员
	 * 
	 * @param serviceTeamMemberVo
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 */
	PageInfo<ServiceTeamMemberVo> findServiceTeamMemberVoPageNotInTeam(
			ServiceTeamMemberVo serviceTeamMemberVo, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	/**
	 * 服务记录处查询服务记录所属人
	 * 
	 * @param serviceTeamMemberVo
	 * @return List<AutoCompleteData>
	 */
	public List<AutoCompleteData> findServiceMembersForServiceRecord(
			ServiceTeamMemberVo serviceTeamMemberVo);

	/**
	 * 团队员成离职或重新担任
	 * 
	 * @param serviceTeamMemberVo
	 * @param count
	 *            将离职成员是否有对象
	 * @return int
	 */
	public int updateServiceTeamMemberOnDuty(
			ServiceTeamMemberVo serviceTeamMemberVo, Integer count);

	/**
	 * 从业务人员角度 查询团队成员列表
	 * 
	 * @param serviceTeamMemberVo
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 */
	PageInfo<ServiceTeamMemberVo> findServiceMemberFromTeams(
			ServiceTeamMemberVo serviceTeamMemberVo, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	PageInfo<ServiceMemberVo> findServiceMembersByServiceMemberVo(
			ServiceMemberVo serviceMemberVo, Integer pageNum, Integer pageSize,
			String sidx, String sord);

	/**
	 * 根据是否含有成员判断是否可删除团队
	 */
	public boolean isDeleteAbleForTeam(Long id);

	/**
	 * 根据是否含有服务记录,判断是否可删除成员
	 */
	public boolean isDeleteAbleForTeamMember(Long id);

	/**
	 * @Description:服务成员列表page页
	 * @author tengjia
	 * @date 2013-7-3 下午8:35:20
	 * @return PageInfo
	 * @param serviceTeamMemberVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	PageInfo<ServiceTeamMemberVo> findServiceTeamMemberBasesPageList(
			ServiceTeamMemberVo serviceTeamMemberVo, Integer page,
			Integer rows, String sidx, String sord);

	/**
	 * 导出数据查询
	 */
	public List<ServiceTeamMemberVo> searchServiceTeamMemberForExport(
			ServiceTeamMemberVo serviceTeamMemberCondition, Integer page,
			Integer rows, String sidx, String sord);

	/**
	 * 获取导出属性的数组
	 */
	public String[][] getExportPopertyArray();

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

	/********************** 组织机构迁移合并方法 *********************/
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

	/**
	 * 用于批量添加成员时，查询团队成员列表
	 * 
	 * @param serviceTeamMemberVo
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<ServiceTeamMemberVo> findServiceMembersFromTeams(
			ServiceTeamMemberVo serviceTeamMemberVo, Integer pageNum,
			Integer pageSize, String sidx, String sord);

}