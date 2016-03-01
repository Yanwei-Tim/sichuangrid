package com.tianque.plugin.serviceTeam.serviceRecord.dao;

import java.util.List;
import java.util.Map;

import com.tianque.plugin.serviceTeam.serviceRecord.domain.ServiceRecordRelyObject;

public interface ServiceRecordRelyObjectDao {
	/**
	 * 根据id获取对象依赖关系队列
	 * 
	 * @param id
	 * @return List<ServiceRecordRelyObject> @
	 */
	public List<ServiceRecordRelyObject> findServiceObjects(Long id);

	/**
	 * 新增服务对象关联
	 * 
	 * @param ServiceRecordRelyObject
	 * @return ServiceRecordRelyObject
	 */
	public Long addServiceRecordRelyObject(
			ServiceRecordRelyObject serviceRecordRelyObject);

	/**
	 * 删除与服务对象的关联关系
	 * 
	 * @param id
	 * @return int 删除条数
	 */
	public int deleteServiceRecordRelyObjects(Long id,Long serviceObjectId);

	/** 修改与服务对象的关联关系 */
	public void updateServiceRecordRelyObject(
			ServiceRecordRelyObject serviceRecordRelyObject);

	/** 通过两个条件（对象id、对象类型）搜索关联关系对象（对象转移时用） */
	public List<ServiceRecordRelyObject> findServiceRecordRelyObjectsByInfo(
			ServiceRecordRelyObject serviceRecordRelyObject);

	/** 删除业务人员或者场所时保存删除对象的orgId和身份证（场所名） */
	public void setOrgIdAndCardNoOrName(Map map);
}
