package com.tianque.plugin.serviceTeam.serviceTeamGuarders.dao;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.plugin.serviceTeam.serviceTeamGuarders.domain.ServiceTeamGuarders;
import com.tianque.plugin.serviceTeam.serviceTeamGuarders.vo.ServiceTeamGuardersVo;

/**
 * @ClassName: ServiceTeamGuardersDaoImpl
 * @Description: 服务监护人数据访问层实现类
 * @author tengjia
 * @date 2013-6-28 上午10:22:07
 */
@Repository("serviceTeamGuardersDao")
public class ServiceTeamGuardersDaoImpl extends AbstractBaseDao implements ServiceTeamGuardersDao {

	@Override
	public ServiceTeamGuardersVo addServiceTeamGuarders(ServiceTeamGuarders serviceTeamGuarders) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"serviceTeamGuarders.addServiceTeamGuarders", serviceTeamGuarders);
		return getServiceTeamGuardersById(id);
	}

	@Override
	public ServiceTeamGuardersVo getServiceTeamGuardersById(Long id) {
		return (ServiceTeamGuardersVo) getSqlMapClientTemplate().queryForObject(
				"serviceTeamGuarders.getServiceTeamGuardersById", id);
	}

	@Override
	public ServiceTeamGuardersVo updateServiceTeamGuarders(ServiceTeamGuarders serviceTeamGuarders) {
		getSqlMapClientTemplate().update("serviceTeamGuarders.updateServiceTeamGuarders",
				serviceTeamGuarders);
		return getServiceTeamGuardersById(serviceTeamGuarders.getId());
	}

}
