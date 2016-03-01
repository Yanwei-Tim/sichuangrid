package com.tianque.plugin.serviceTeam.serviceTeamManage.dao;

import java.util.List;
import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.serviceTeam.serviceTeamManage.domain.ServiceTeam;
import com.tianque.plugin.serviceTeam.serviceTeamManage.vo.ServiceTeamVo;
import com.tianque.plugin.serviceTeam.serviceTeamMember.vo.ServiceTeamMemberVo;

/**
 * 服务团队数据访问层接口
 * 
 * @author yangpengdian
 */
public interface ServiceTeamDao {

	/**
	 * 新增服务团队信息
	 * 
	 * @param ServiceTeam
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
			Integer pageNum, Integer pageSize);

	/**
	 * 查找团队数量
	 * 
	 * @param serviceTeamVo
	 * @return
	 */
	public Integer countServiceTeams(ServiceTeamVo serviceTeamVo);

	/**
	 * 根据id获取服务团队
	 * 
	 * @param id
	 * @return ServiceTeamVo 服务团队vo
	 */

	public ServiceTeamVo getServiceTeamById(Long id);

	/**
	 * 更新服务团队
	 * 
	 * @param serviceTeam
	 * @return ServiceTeamVo
	 */
	public ServiceTeamVo updateServiceTeam(ServiceTeam serviceTeam);

	/**
	 * 删除服务团队
	 * 
	 * @param id
	 * @return int 删除条数
	 */
	public int deleteServiceTeam(Long id);

	/**
	 * 批量删除服务团队
	 * 
	 * @param ids
	 * @return int 删除条数
	 */
	public int deleteServiceTeamByIds(String[] ids);

	/**
	 * 解散服务团队
	 * 
	 * @param serviceTeam
	 */

	public void logOutServiceTeam(ServiceTeam serviceTeam);

	/**
	 * 查找团队下对象数量
	 * 
	 * @param id
	 */
	public List<Map> countServiceObjectsForTeam(Long id);

	/**
	 * 查找所团队下所有对象数量
	 */
	public Integer countAllServiceTeamObjects(ServiceTeamVo serviceTeamVo);

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
}