package com.tianque.plugin.serviceTeam.serviceGuardersWithObject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.serviceTeam.serviceGuardersWithObject.dao.ServiceGuardersWithObjectDao;
import com.tianque.plugin.serviceTeam.serviceGuardersWithObject.domain.ServiceGuardersWithObject;
import com.tianque.plugin.serviceTeam.serviceGuardersWithObject.vo.ServiceGuardersWithObjectVo;
import com.tianque.plugin.serviceTeam.serviceTeamGuarders.service.ServiceTeamGuardersService;

/**
 * @ClassName: ServiceGuardersWithObjectServiceImpl
 * @Description: 监护人员业务处理实现类
 */
@Service("serviceGuardersWithObjectService")
@Transactional
public class ServiceGuardersWithObjectServiceImpl extends AbstractBaseService
		implements ServiceGuardersWithObjectService {

	@Autowired
	private ServiceGuardersWithObjectDao serviceGuardersWithObjectDao;
	@Autowired
	private ServiceTeamGuardersService serviceTeamGuardersService;

	@Override
	public ServiceGuardersWithObjectVo addObjectAndGuardersRelation(
			ServiceGuardersWithObject serviceGuardersWithObject) {
		try {

			return serviceGuardersWithObjectDao
					.addObjectAndGuardersRelation(serviceGuardersWithObject);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ServiceGuardersWithObjectServiceImpl的addObjectAndGuardersRelation方法出现异常，原因：",
					"新增监护人员出现错误", e);
		}
	}

	@Override
	public ServiceGuardersWithObjectVo getServiceGuardersWithObjectById(Long id) {
		try {
			return serviceGuardersWithObjectDao
					.getServiceGuardersWithObjectById(id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"根据ID获取监护人员和服务对象的信息出现错误",
					"类ServiceGuardersWithObjectServiceImpl的getServiceGuardersWithObjectById方法出现异常，原因：",
					e);
		}
	}

	@Override
	public int deleteServiceGuardersWithObject(String selectedIds) {
		if (selectedIds == null || selectedIds.equals("")) {
			throw new BusinessValidationException("无法定位需要操作的数据！！");
		}
		// List<Long> deleteIds = getDeleteIds(selectedIds);
		int count = 0;
		// for (Long deleteId : deleteIds) {
		try{
			count = serviceGuardersWithObjectDao
					.deleteServiceGuardersWithObjectByIds(selectedIds.split(","));
		}catch(Exception e){
			throw new ServiceValidationException("删除失败，请重试",e);
		}
		// if (null != getServiceGuardersWithObjectById(deleteId)) {
		// int deleteCount = serviceGuardersWithObjectDao
		// .deleteServiceGuardersWithObject(deleteId);
		// count += deleteCount;
		// }
		// }
		return count;
	}

	/**
	 * 获得要进行删除操作的id组
	 * 
	 * @return
	 */
	private List<Long> getDeleteIds(String selectedIds) {
		String[] deleteIdStrs = selectedIds.split(",");
		List<Long> deleteIds = new ArrayList<Long>();
		for (String deleteIdStr : deleteIdStrs) {
			Long deleteId = Long.parseLong(deleteIdStr);
			deleteIds.add(deleteId);
		}
		return deleteIds;
	}

	@Override
	public void updateServiceGuardersHasObject(String objectType,
			Long oldObjectId, Long newObjectId) {
		if (objectType == null || "".equals(objectType) || newObjectId == null
				|| newObjectId == null) {
			throw new BusinessValidationException("转移人口时修改监护人员关联关系参数错误");
		}
		try {
			serviceGuardersWithObjectDao.updateServiceGuardersHasObject(
					objectType, oldObjectId, newObjectId);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ServiceGuardersWithObjectServiceImpl的updateServiceGuardersHasObject方法出现异常，原因：",
					"转移人口时修改监护人员关联关系出现错误", e);
		}

	}

	@Override
	public void updateServiceGuardersHasObject(String objectType,
			Long oldObjectId, Long newObjectId, String newObjectType) {
		if (objectType == null || "".equals(objectType) || newObjectId == null
				|| newObjectId == null || newObjectType == null
				|| "".equals(newObjectType)) {
			throw new BusinessValidationException("转移人口时修改监护人员关联关系参数错误");
		}
		try {
			serviceGuardersWithObjectDao.updateServiceGuardersHasObject(
					objectType, oldObjectId, newObjectId, newObjectType);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ServiceGuardersWithObjectServiceImpl的updateServiceGuardersHasObject方法出现异常，原因：",
					"转移人口时修改监护人员关联关系出现错误", e);
		}

	}

}
