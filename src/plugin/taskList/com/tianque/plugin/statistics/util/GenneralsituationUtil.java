package com.tianque.plugin.statistics.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.tianque.core.cache.constant.MemCacheConstant;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.plugin.statistics.constants.GeneralStituationConstants;
import com.tianque.plugin.statistics.vo.GeneralStituationSearchVo;
import com.tianque.plugin.statistics.vo.TaskListStatisticsVo;

public class GenneralsituationUtil {
	
	/**
	 * 根据任务类别定义key值
	 * @param selectTypes 任务类别
	 * @return
	 */
	public static final String getSelectTypesForKey(String selectTypes){
		if("pandect".equals(selectTypes)){
			return "pandect";
		}
		String reg = "^[0-9]*[1-9][0-9]*$";
		String[] types = selectTypes.split(",");
		String returnTypes = "";
		for(String type : types){
			if(type.matches(reg)){
				returnTypes += type;
			}else{
				returnTypes += type.substring(0,3);
			}
		}
		return returnTypes;
	}
	
	/**
	 * 根据条件返回缓存nameSpace、key值
	 * @param taskListStatisticsVo
	 * @param graphicType 0是柱状图  1饼状图  2趋势图  3地图分析地图  4地图分析饼图 5地图分析同比环比   6地图分析趋势图
	 * @param isSubType 0为字典项类别  1 为字符串类别    2为地图类型
	 * @return
	 */
	public static final String[] getCachKeyByTypes(TaskListStatisticsVo taskListStatisticsVo,Integer graphicType,Integer isSubType){
		String key = "";
		String nameSpace = "";
		if(isSubType == 0){
			String domainName = taskListStatisticsVo.getPropertyDomainName();
			//判断选中的菜单项
			if(domainName.equals(PropertyTypes.WORKING_CONTENT_TYPE )){//民警带领下开展工作
				if(graphicType == 0){
					key = MemCacheConstant.POLICELIST_COLUMN;
				}else if(graphicType == 1){
					key = MemCacheConstant.POLICELIST_PIE;
				}else if(graphicType == 2){
					key = MemCacheConstant.POLICELIST_TREND;
				}
				nameSpace = MemCacheConstant.POLICELIST_NAMESPACE;
			}else if(domainName.equals(PropertyTypes.EXCEPTION_SITUATION_TYPE)){//异常情况报告
				if(graphicType == 0){
					key = MemCacheConstant.EXCEPTIONLIST_COLUMN;
				}else if(graphicType == 1){
					key = MemCacheConstant.EXCEPTIONLIST_PIE;
				}else if(graphicType == 2){
					key = MemCacheConstant.EXCEPTIONLIST_TREND;
				}
				nameSpace = MemCacheConstant.EXCEPTIONLIST_NAMESPACE;
			}else if(domainName.equals(PropertyTypes.DANGER_EXCEPTION_TYPE)){//发现治安隐患
				if(graphicType == 0){
					key = MemCacheConstant.HIDDENGERLIST_COLUMN;
				}else if(graphicType == 1){
					key = MemCacheConstant.HIDDENGERLIST_PIE;
				}else if(graphicType == 2){
					key = MemCacheConstant.HIDDENGERLIST_TREND;
				}
				nameSpace = MemCacheConstant.HIDDENGERLIST_NAMESPACE;
			}
		}else if(isSubType == 1){
			Integer situationType = taskListStatisticsVo.getSituationType();
			//判断选中的菜单项
			if(situationType == 0){//任务清单总览
				if(graphicType == 0){
					key = MemCacheConstant.TASKLIST_COLUMN;
				}else if(graphicType == 1){
					key = MemCacheConstant.TASKLIST_PIE;
				}else if(graphicType == 2){
					key = MemCacheConstant.TASKLIST_TREND;
				}
				nameSpace = MemCacheConstant.TASKLIST_NAMESPACE;
			}else if(situationType == 1){//流动人口
				if(graphicType == 0){
					key = MemCacheConstant.FLOATINGLIST_COLUMN;
				}else if(graphicType == 1){
					key = MemCacheConstant.FLOATINGLIST_PIE;
				}else if(graphicType == 2){
					key = MemCacheConstant.FLOATINGLIST_TREND;
				}
				nameSpace = MemCacheConstant.FLOATINGLIST_NAMESPACE;
			}else if(situationType == 3){//特殊人群
				if(graphicType == 0){
					key = MemCacheConstant.SPECIALLIST_COLUMN;
				}else if(graphicType == 1){
					key = MemCacheConstant.SPECIALLIST_PIE;
				}else if(graphicType == 2){
					key = MemCacheConstant.SPECIALLIST_TREND;
				}
				nameSpace = MemCacheConstant.SPECIALLIST_NAMESPACE;
			}
		}else{
			String baseType = taskListStatisticsVo.getBasesicType();
			if(StringUtil.isStringAvaliable(baseType)){
				String detailType = taskListStatisticsVo.getDetailType();
				if(StringUtil.isStringAvaliable(detailType)){
					baseType += "," + detailType;
					String subType = taskListStatisticsVo.getSubType();
					if(StringUtil.isStringAvaliable(subType)){
						baseType += "," + subType;
					}
				}
			}
			if(graphicType == 3){//地图
				key = MemCacheConstant.TASKLIST_MAP;
				baseType += ",00"+taskListStatisticsVo.getMapTypeByOrg();
			}else if(graphicType == 4){//地图饼图
				key = MemCacheConstant.TASKLIST_MAPPIE;
			}else{//地图同比环比
				key = MemCacheConstant.TASKLIST_MAPANALYSIS;
			}
			nameSpace = MemCacheConstant.TASKLISTMAP_NAMESPACE;
			taskListStatisticsVo.setSelectTypes(baseType);
		}
		Integer searchDateType = taskListStatisticsVo.getSearchDateType();
		Integer dateValue = 0 ;
		if(searchDateType == 0){
			dateValue = taskListStatisticsVo.getMonth();
		}else if(searchDateType == 1){
			dateValue = taskListStatisticsVo.getQuarter();
		}else{
			dateValue = taskListStatisticsVo.getYearType();
		}
		key = MemCacheConstant.getTaskListCachKey(key, GenneralsituationUtil.getSelectTypesForKey(taskListStatisticsVo.getSelectTypes()), taskListStatisticsVo.getIsSegin(), searchDateType, taskListStatisticsVo.getYear(), dateValue, taskListStatisticsVo.getOrgId());
		String[] returnString = {nameSpace,key};
		return returnString;
	}

	public static final List<Long> dealLongToArray(Long[] ids){
		List<Long> idList = new ArrayList<Long>();
		if(ids!=null && ids.length!=0){
			for(Long id:ids){
				idList.add(id);
			}
		}
		return idList;
	}
	

	public static final List<Long> dealOrgIds(String [] orgIds){
		List<Long> list = new ArrayList<Long>();
		for(String id:orgIds){
			list.add(Long.parseLong(id));
		}
		return list;
	}
	
	public static final String getSubTypeIds(List<PropertyDict> list){
		StringBuffer subType = new StringBuffer();
		int i = 0;
		for(PropertyDict propertyDict:list){
			if(i==list.size()-1){
				subType.append(propertyDict.getId()+"");
			}else{
				subType.append(propertyDict.getId()+",");
			}
		}
		return subType.toString();
	}
	
	public static final String[] getTime(Integer year,Integer month,String[] time) {
		Calendar nowCalendar = Calendar.getInstance();
		nowCalendar.setTime(CalendarUtil.getDateByYearAndMonth(year, month));
		nowCalendar.add(Calendar.MONTH, -11);

		for (int i = 0; i < 12; i++) {
			SimpleDateFormat timePattern = new SimpleDateFormat("yyyy-MM");
			time[i] = timePattern.format(nowCalendar.getTime());
			nowCalendar.add(Calendar.MONTH, 1);
		}

		return time;
	}
}
