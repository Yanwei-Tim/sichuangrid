package com.tianque.baseInfo.youths.constants;

import java.util.HashMap;
import java.util.Map;

import com.tianque.baseInfo.youths.vo.SearchYouthsVo;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.sysadmin.service.PropertyDictService;

public class YouthsType {

	public final static String YOUNGSTERS = "youngsters";// 青少年
	public final static String YOUNGPIONEER = "youngpioneer";// 少先队员
	public final static String LEAGUEMEMBER = "leaguemember";// 共青团员

	public final static String YOUNGSTERS_NAME = "青少年";
	public final static String YOUNGPIONEER_NAME = "少先队员";
	public final static String LEAGUEMEMBER_NAME = "共青团员";

	public static final int THREADE_AMOUNT = 3;

	//青少年缓存key前缀
	public static final String YOUTHSKEY = "statisticsYouthsCount";
	//户籍人口
	public static final String YOUTHS_HOUSEHOLDSTAFFS = "householdStaffs";
	//流动人口
	public static final String YOUTHS_FLOATINGPOPULATIONS = "floatingPopulations";
	//未落户人口
	public static final String YOUTHS_UNSETTLEDPOPULATIONS = "unsettledPopulations";

	public final static Map<String, String> youthsTypes = new HashMap<String, String>();
	static {
		youthsTypes.put(YOUNGSTERS, YOUNGSTERS_NAME);
		youthsTypes.put(YOUNGPIONEER, YOUNGPIONEER_NAME);
		youthsTypes.put(LEAGUEMEMBER, LEAGUEMEMBER_NAME);
	}

	public static String getTypeName(String key) {
		return youthsTypes.get(key);
	}

	/**
	 * 
	 * 填充vo边界值
	 * 
	 * 青少年:35周岁以下的实有人口
	 * 
	 * 少先队员：6周岁到14周岁
	 * 
	 * 共青团员：14周岁到28周岁
	 */
	public static void fillBeginAgeAndEndAge(SearchYouthsVo searchYouthsVo,
			PropertyDictService propertyDictService) {
		if (YouthsType.YOUNGSTERS.equals(searchYouthsVo.getKeyType())) {
			if (null == searchYouthsVo.getBeginAge()) {
				searchYouthsVo.setBeginAge(0);
			} else if (searchYouthsVo.getBeginAge() < 0) {
				searchYouthsVo.setBeginAge(0);
			}
			if (null == searchYouthsVo.getEndAge()) {
				searchYouthsVo.setEndAge(35);
			} else if (searchYouthsVo.getEndAge() > 35) {
				searchYouthsVo.setEndAge(35);
			}
		} else if (YouthsType.YOUNGPIONEER.equals(searchYouthsVo.getKeyType())) {
			if (null == searchYouthsVo.getBeginAge()) {
				searchYouthsVo.setBeginAge(6);
			} else if (searchYouthsVo.getBeginAge() < 6) {
				searchYouthsVo.setBeginAge(6);
			}
			if (null == searchYouthsVo.getEndAge()) {
				searchYouthsVo.setEndAge(14);
			} else if (searchYouthsVo.getEndAge() > 14) {
				searchYouthsVo.setEndAge(14);
			}
			searchYouthsVo.setPoliticalBackground(propertyDictService
					.findPropertyDictByDomainNameAndDictDisplayName(
							PropertyTypes.POLITICAL_BACKGROUND, "中国少年先锋队"));
		} else if (YouthsType.LEAGUEMEMBER.equals(searchYouthsVo.getKeyType())) {
			if (null == searchYouthsVo.getBeginAge()) {
				searchYouthsVo.setBeginAge(14);
			} else if (searchYouthsVo.getBeginAge() < 14) {
				searchYouthsVo.setBeginAge(14);
			}
			if (null == searchYouthsVo.getEndAge()) {
				searchYouthsVo.setEndAge(28);
			} else if (searchYouthsVo.getEndAge() > 28) {
				searchYouthsVo.setEndAge(28);
			}
			searchYouthsVo.setPoliticalBackground(propertyDictService
					.findPropertyDictByDomainNameAndDictDisplayName(
							PropertyTypes.POLITICAL_BACKGROUND, "中国共产主义青年团团员"));
		}
	}
}
