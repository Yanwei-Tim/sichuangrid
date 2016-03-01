/**
 * 
 */
package com.tianque.serviceList.dao;

import java.util.List;
import java.util.Map;

import com.tianque.serviceList.domain.ServiceListReport;

/**
 * @作者:彭乐
 * @功能: 
 * @时间:2015-11-27 上午10:55:54
 * @邮箱:pengle@hztianque.com
 */
public interface ServiceListReportDao{
	public List<ServiceListReport> getAllKindServiceList(Map<String, Object>map);
}
