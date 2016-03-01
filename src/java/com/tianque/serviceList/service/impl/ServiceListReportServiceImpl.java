/**
 * 
 */
package com.tianque.serviceList.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.companyPlace.constacts.ModulTypes;
import com.tianque.core.cache.constant.MemCacheConstant;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.util.CalendarUtil;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.judgmentAnalysis.util.StringUtil;
import com.tianque.serviceList.dao.ServiceListReportDao;
import com.tianque.serviceList.domain.ServiceListReport;
import com.tianque.serviceList.service.ServiceListReportService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.vladium.emma.report.reportTask;

@Service("serviceListReportServiceImpl")
@Transactional
public class ServiceListReportServiceImpl implements ServiceListReportService{
	@Autowired 
	private ServiceListReportDao serviceListReportDao;
	@Autowired
	private CacheService cacheService;
	@Autowired
	private PropertyDictService propertyDictService;

	/* (non-Javadoc)
	 * @see com.tianque.serviceList.service.ServiceListReportService#getAllKindServiceList(java.lang.Long, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<ServiceListReport> getAllKindServiceList(Long orgId,
			Integer searchType, Integer year, Integer month, Integer week) {
		if(orgId==null || searchType==null){
			throw new BusinessValidationException("查询失败，查询参数未获得");
		}
		List<ServiceListReport> serviceListReports  = null;
		String key = MemCacheConstant.getTaskListReportCachKey(MemCacheConstant.SERVICE_LIST_ALL_KEY, orgId,searchType,year,month,week,null);
		if(StringUtil.isStringAvaliable(key)){
			serviceListReports = (List<ServiceListReport>) cacheService.get(MemCacheConstant.SERVICE_LIST_ALL_NAMESPACE, key);
			if(serviceListReports!=null && serviceListReports.size()!=0){
//				return serviceListReports;
			}
		}
		try {
			Map<String,Object> map = getDateByCondition(searchType, year, month, week);
			Long orgType = propertyDictService.findPropertyDictByDomainNameAndDictDisplayName(
					PropertyTypes.ORGANIZATION_TYPE, OrganizationType.ADMINISTRATIVE_KEY).getId();
			map.put("orgType", orgType);
			map.put("parentOrgId", orgId);
			
			serviceListReports = serviceListReportDao.getAllKindServiceList(map);
			for (int i = 0; i < serviceListReports.size(); i++) {
				//计算百分比和发送量签收量
				serviceListReports.set(i, countPercentAndNum(serviceListReports.get(i)));
			}
			for (int i = 0; i < serviceListReports.size(); i++) {
				if (serviceListReports.get(i).getId().equals(orgId)) {
					ServiceListReport temp = serviceListReports.get(i);
					temp.setOrgname("合计");
					serviceListReports.remove(i);
					serviceListReports.add(temp);
				}
			}
			if(StringUtil.isStringAvaliable(key)){
				cacheService.set(MemCacheConstant.SERVICE_LIST_ALL_NAMESPACE, key,ModulTypes.CACHETIME, serviceListReports);
			}
			return serviceListReports;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceValidationException("获取走访报表出错", e);
		}
	}
	//计算百分比和发送量签收量
	private ServiceListReport countPercentAndNum(ServiceListReport report){
		//计算百分比
		report.setPolicyPropagandaSignPercent
			(countPercent(report.getPolicyPropagandaVisit(), report.getPolicyPropagandaSum()));
		report.setFoodSaftySignPercent
			(countPercent(report.getFoodSaftyVisit(), report.getFoodSaftySum()));
		report.setDrugsSaftySignPercent
			(countPercent(report.getDrugsSaftyVisit(), report.getDrugsSaftySum()));
		report.setBusinessManageSignPercent
			(countPercent(report.getBusinessManageVisit(), report.getBusinessManageSum()));
		report.setPyramidSalesManageSignPercent
			(countPercent(report.getPyramidSalesManageVisit(), report.getPyramidSalesManageSum()));
		report.setUnlicensedManageSignPercent
			(countPercent(report.getUnlicensedManageVisit(), report.getUnlicensedManageSum()));
		report.setOtherSituationManageSignPercent
			(countPercent(report.getOtherSituationManageVisit(), report.getOtherSituationManageSum()));
		//计算总量和百分比
		Long totalSum=report.getPolicyPropagandaSum()+report.getFoodSaftySum()+report.getDrugsSaftySum()+
				report.getBusinessManageSum()+report.getPyramidSalesManageSum()+report.getUnlicensedManageSum()+
				report.getOtherSituationManageSum();
		Long signNum=report.getPolicyPropagandaVisit()+report.getFoodSaftyVisit()+report.getDrugsSaftyVisit()+
				report.getBusinessManageVisit()+report.getPyramidSalesManageVisit()+report.getUnlicensedManageVisit()+
				report.getOtherSituationManageVisit();
		report.setTotalSum(totalSum);
		report.setSignSum(signNum);
		report.setSignPercent(countPercent(signNum, totalSum));
		return report;
	}
	//计算百分比
	private String countPercent(Long signNum,Long totalSum){
		if(totalSum==0){
			return "0.0%";
		}else{
			return Math.round((signNum*1.0/totalSum)*10000)/100.0+"%";
		}
	}
	private Map<String,Object> getDateByCondition(Integer searchType,Integer year,Integer month,Integer week){
		 Map<String,Object> map = new HashMap<String,Object>();
		 Date startDate = null;
		 Date endDate = null;
		 if(searchType==0){//根据年月查询
				if(CalendarUtil.compareYearAndMonth(year, month)){//当前年月则查询本月数据
					startDate = CalendarUtil.getMonthStart(year, month);
				}
				endDate = CalendarUtil.getNextMonthStart(year, month);
			}else{//根据周查询
				if(week==0){//本周
					startDate = CalendarUtil.getWeekMonday();
					endDate = CalendarUtil.getNextWeekMonday();
				}else{//上周
					startDate = CalendarUtil.getBeforWeekMonday(CalendarUtil.now());//根据当前时间获取上周周一
					endDate = CalendarUtil.getWeekMonday();//根据当前时间获取本周周一
				}
			}
		 if(startDate!=null){
			 map.put("startDate",startDate);
		 }
		 map.put("endDate",endDate);
		 return map;
	}
}
