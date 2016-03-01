/**
 * 
 */
package com.tianque.serviceList.dao.impl;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.dao.AbstractBaseDao;
import com.tianque.serviceList.dao.ServiceListReportDao;
import com.tianque.serviceList.domain.ServiceListReport;

/**
 * @作者:彭乐
 * @功能: 
 * @时间:2015-11-27 上午10:55:54
 * @邮箱:pengle@hztianque.com
 */
@Repository("serviceListReportDaoImpl")
public class ServiceListReportDaoImpl extends AbstractBaseDao implements ServiceListReportDao{

	@Override
	public List<ServiceListReport> getAllKindServiceList(Map<String, Object>map) {
		return getSqlMapClientTemplate().queryForList("serviceListCommon.getServieSumAndVisitByOrgId",
				map);
	}
	
}
