package com.tianque.plugin.serviceTeam.serviceGuardersWithObject.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.plugin.serviceTeam.serviceGuardersWithObject.domain.ServiceGuardersWithObject;
import com.tianque.plugin.serviceTeam.serviceGuardersWithObject.vo.ServiceGuardersWithObjectVo;

/**
 * @ClassName: ServiceGuardersWithObjectDaoImpl
 * @Description: 监护人员数据访问层实现类
 */
@Repository("ServiceGuardersWithObjectDao")
public class ServiceGuardersWithObjectDaoImpl extends AbstractBaseDao implements
		ServiceGuardersWithObjectDao {

	@Override
	public ServiceGuardersWithObjectVo addObjectAndGuardersRelation(
			ServiceGuardersWithObject serviceGuardersWithObject) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"serviceGuardersWithObject.addObjectAndGuardersRelation",
				serviceGuardersWithObject);
		return getServiceGuardersWithObjectById(id);
	}

	@Override
	public ServiceGuardersWithObjectVo getServiceGuardersWithObjectById(Long id) {
		return (ServiceGuardersWithObjectVo) getSqlMapClientTemplate()
				.queryForObject(
						"serviceGuardersWithObject.getServiceGuardersWithObjectById",
						id);
	}

	@Override
	public int deleteServiceGuardersWithObject(Long id) {
		return getSqlMapClientTemplate()
				.delete("serviceGuardersWithObject.deleteServiceGuardersWithObject",
						id);
	}

	@Override
	public void updateServiceGuardersHasObject(String objectType,
			Long oldObjectId, Long newObjectId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("objectType", objectType);
		map.put("oldObjectId", oldObjectId);
		map.put("newObjectId", newObjectId);
		getSqlMapClientTemplate()
				.update("serviceGuardersWithObject.updateServiceGuardersHasObject",
						map);

	}

	@Override
	public void updateServiceGuardersHasObject(String objectType,
			Long oldObjectId, Long newObjectId, String newObjectType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("objectType", objectType);
		map.put("oldObjectId", oldObjectId);
		map.put("newObjectId", newObjectId);
		map.put("newObjectType", newObjectType);
		getSqlMapClientTemplate()
				.update("serviceGuardersWithObject.updateServiceGuardersHasObjectForPosAndRec",
						map);

	}

	@Override
	public int deleteServiceGuardersWithObjectByIds(String[] ids) {
		if (ids == null || ids.length == 0) {
			return 0;
		}
		return getSqlMapClientTemplate()
				.delete("serviceGuardersWithObject.deleteServiceGuardersWithObjectByIds",
						ids);
	}

}
