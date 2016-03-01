package com.tianque.plugin.serviceTeam.serviceTeamGuarders.dao;

import com.tianque.plugin.serviceTeam.serviceTeamGuarders.domain.ServiceTeamGuarders;
import com.tianque.plugin.serviceTeam.serviceTeamGuarders.vo.ServiceTeamGuardersVo;

/**
 * @ClassName: ServiceTeamGuardersDao
 * @Description: 服务监护人员数据访问层接口
 * @author tengjia
 * @date 2013-6-28 上午10:18:35
 */
public interface ServiceTeamGuardersDao {

	/**
	 * 新增监护人信息
	 * 
	 * @param serviceTeamGuarders
	 * @return ServiceTeamGuardersVo 监护人员vo
	 */
	public ServiceTeamGuardersVo addServiceTeamGuarders(ServiceTeamGuarders serviceTeamGuarders);

	/**
	 * 更新监护人员
	 * 
	 * @param serviceTeamGuarders
	 * @return ServiceTeamGuardersVo
	 */
	public ServiceTeamGuardersVo updateServiceTeamGuarders(ServiceTeamGuarders serviceTeamGuarders);

	/**
	 * 根据id获取监护人员
	 * 
	 * @param id
	 * @return ServiceTeamGuardersVo 监护人员vo
	 */

	public ServiceTeamGuardersVo getServiceTeamGuardersById(Long id);
}
