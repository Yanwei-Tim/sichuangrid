package com.tianque.plugin.serviceTeam.serviceTeamGuarders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.serviceTeam.serviceGuardersWithObject.domain.ServiceGuardersWithObject;
import com.tianque.plugin.serviceTeam.serviceGuardersWithObject.service.ServiceGuardersWithObjectService;
import com.tianque.plugin.serviceTeam.serviceTeamGuarders.dao.ServiceTeamGuardersDao;
import com.tianque.plugin.serviceTeam.serviceTeamGuarders.domain.ServiceTeamGuarders;
import com.tianque.plugin.serviceTeam.serviceTeamGuarders.vo.ServiceTeamGuardersVo;

/**
 * @ClassName: ServiceTeamGuardersServiceImpl
 * @Description: 服务监护人服务层实现类
 * @author tengjia
 * @date 2013-6-28 上午10:16:25
 */
@Service("serviceTeamGuardersService")
@Transactional
public class ServiceTeamGuardersServiceImpl extends AbstractBaseService implements
		ServiceTeamGuardersService {

	@Autowired
	private ServiceTeamGuardersDao serviceTeamGuardersDao;
	@Autowired
	private ServiceGuardersWithObjectService serviceGuardersWithObjectService;

	@Override
	public ServiceTeamGuardersVo addServiceTeamGuarders(ServiceTeamGuarders serviceTeamGuarders) {
		try {
			ServiceGuardersWithObject guarderWithObject = serviceTeamGuarders
					.getServiceGuardersWithObject();

			ServiceTeamGuardersVo resultVo = serviceTeamGuardersDao
					.addServiceTeamGuarders(serviceTeamGuarders);

			guarderWithObject.setGuardersId(resultVo.getGuardersId());
			serviceGuardersWithObjectService.addObjectAndGuardersRelation(guarderWithObject);

			return resultVo;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ServiceTeamGuardersServiceImpl的addServiceTeamGuarders方法出现异常，原因：",
					"新增监护人出现错误", e);
		}
	}

	@Override
	public ServiceTeamGuardersVo getServiceTeamGuardersById(Long id) {
		try {
			return serviceTeamGuardersDao.getServiceTeamGuardersById(id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ServiceTeamGuardersServiceImpl的getServiceTeamGuardersById方法出现异常，原因：",
					"根据ID获取监护人员出现错误", e);
		}
	}

	@Override
	public ServiceTeamGuardersVo updateServiceTeamGuarders(ServiceTeamGuarders serviceTeamGuarders) {
		try {
			return serviceTeamGuardersDao.updateServiceTeamGuarders(serviceTeamGuarders);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ServiceTeamGuardersServiceImpl的updateServiceTeamGuarders方法出现异常，原因：",
					"修改监护人员出现错误", e);
		}
	}

}
