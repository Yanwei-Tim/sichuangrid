/**
 * 
 */
package com.tianque.serviceList.service;

import java.util.List;

import com.tianque.serviceList.domain.ServiceListAttch;
import com.tianque.serviceList.domain.ServiceListReport;

/**
 * @作者:彭乐
 * @功能: 
 * @时间:2015-11-27 上午10:55:54
 * @邮箱:pengle@hztianque.com
 */
public interface ServiceListReportService {
	public List<ServiceListReport> getAllKindServiceList(Long orgId,Integer searchType,Integer year,Integer month,Integer week);
}
