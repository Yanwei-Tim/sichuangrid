package com.tianque.plugin.serviceTeam.serviceTeamManage.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.serviceTeam.serviceTeamManage.domain.ServiceTeam;
import com.tianque.plugin.serviceTeam.serviceTeamManage.vo.ServiceTeamVo;
import com.tianque.plugin.serviceTeam.serviceTeamMember.vo.ServiceTeamMemberVo;

/**
 * 服务团队业务处理接口
 * 
 * @author yangpengdian
 */
public interface ServiceTeamService {
	/**
	 * 新增服务团队业务
	 * 
	 * @param ServiceTeamVo
	 * @return ServiceTeamVo 服务团队vo
	 */
	public ServiceTeamVo addServiceTeam(ServiceTeam serviceTeam);

	/**
	 * 获取服务团队页面
	 * 
	 * @param ServiceTeamVo
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return PageInfo
	 */
	public PageInfo<ServiceTeam> findServiceTeams(ServiceTeamVo serviceTeamVo,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	/**
	 * 根据id获取单个服务团队信息
	 * 
	 * @param id
	 * @return ServiceTeamVo @
	 */
	public ServiceTeamVo getServiceTeamById(Long id);

	/**
	 * 修改服务团队信息
	 * 
	 * @param ServiceTeam
	 * @return ServiceTeamVo @
	 */
	public ServiceTeamVo updateServiceTeam(ServiceTeam serviceTeam);

	/**
	 * 根据ID删除服务团队
	 * 
	 * @param selectedIds
	 *            准备删除的记录ID
	 * @return int 删除条数
	 */
	public int deleteServiceTeam(String selectedIds);

	/**
	 * 解散团队
	 * 
	 * @param ServiceTeam
	 */
	public void logOutServiceTeam(ServiceTeam serviceTeam);

	/**
	 * 查找团队下对象数量
	 * 
	 * @param ServiceTeamVo
	 */
	public int[] countServiceObjectsForTeam(ServiceTeamVo serviceTeamVo);

	/**
	 * 离职成员时查找有无对象
	 * 
	 * @param serviceTeamVo
	 * @return
	 */
	public Integer findServiceTeamMemberHasObjects(
			ServiceTeamMemberVo serviceTeamMemberVo);

	/**
	 * 查询服务团队，供下载
	 * 
	 * @param serviceTeamVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public List<ServiceTeam> searchServiceTeamsForExport(
			ServiceTeamVo serviceTeamVo, Integer page, Integer rows,
			String sidx, String sord);

	public String[][] getExportPopertyArray();
}