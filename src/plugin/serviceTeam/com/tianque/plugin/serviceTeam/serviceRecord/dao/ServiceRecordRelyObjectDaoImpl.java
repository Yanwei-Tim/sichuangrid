package com.tianque.plugin.serviceTeam.serviceRecord.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.plugin.serviceTeam.serviceRecord.domain.ServiceRecordRelyObject;

@Repository("ServiceRecordRelyObjectDao")
public class ServiceRecordRelyObjectDaoImpl extends AbstractBaseDao implements
		ServiceRecordRelyObjectDao {
	@Override
	public Long addServiceRecordRelyObject(ServiceRecordRelyObject serviceRecordRelyObject) {

		return (Long) getSqlMapClientTemplate().insert(
				"serviceRecordRelyObject.addServiceRecordRelyObject", serviceRecordRelyObject);
	}

	@Override
	public List<ServiceRecordRelyObject> findServiceObjects(Long id) {
		return getSqlMapClientTemplate().queryForList("serviceRecordRelyObject.findServiceObjects",
				id);
	}

	@Override
	public int deleteServiceRecordRelyObjects(Long id,Long serviceObjectId) {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("id", id);
		map.put("serviceObjectId", serviceObjectId);
		int i = getSqlMapClientTemplate().delete(
				"serviceRecordRelyObject.deleteServiceRecordRelyObjects", map);
		return i;
	}

	@Override
	public void updateServiceRecordRelyObject(ServiceRecordRelyObject serviceRecordRelyObject) {
		getSqlMapClientTemplate().update("serviceRecordRelyObject.updateServiceRecordRelyObject",
				serviceRecordRelyObject);
	}

	@Override
	public List<ServiceRecordRelyObject> findServiceRecordRelyObjectsByInfo(
			ServiceRecordRelyObject serviceRecordRelyObject) {
		return getSqlMapClientTemplate().queryForList(
				"serviceRecordRelyObject.findServiceRecordRelyObjectsByInfo",
				serviceRecordRelyObject);
	}

	@Override
	public void setOrgIdAndCardNoOrName(Map map) {
		getSqlMapClientTemplate().update("serviceRecordRelyObject.setOrgIdAndCardNoOrName", map);
	}

}
