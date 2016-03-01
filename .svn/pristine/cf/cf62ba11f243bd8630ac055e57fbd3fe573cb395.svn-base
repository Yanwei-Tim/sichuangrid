/**
 * 
 */
package com.tianque.serviceList.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.objectweb.carol.jndi.enc.java.javaURLContextFactory;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseDaoImpl;
import com.tianque.serviceList.dao.ServiceListAttachDao;
import com.tianque.serviceList.domain.ServiceListAttch;

/**
 * @作者:彭乐
 * @功能: 
 * @时间:2015-11-27 上午10:55:54
 * @邮箱:pengle@hztianque.com
 */
@Repository("serviceListAttachDaoImpl")
public class ServiceListAttachDaoImpl extends BaseDaoImpl<ServiceListAttch, ServiceListAttch> implements ServiceListAttachDao{

	@Override
	public void deleteServiceListAttchByIds(Long id, Integer type) {
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("id", id);
		map.put("type", type);
		getSqlMapClientTemplate().delete("serviceListAttch.deleteServiceListAttch", map);
	}

	@Override
	public ArrayList<ServiceListAttch> getServiceListAttchByIdAndType(Long id,
			Integer type) {
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("id", id);
		map.put("type", type);
		return (ArrayList<ServiceListAttch>)getSqlMapClientTemplate().queryForList("serviceListAttch.getServiceListAttchByIdAndType", map);
	}

}
