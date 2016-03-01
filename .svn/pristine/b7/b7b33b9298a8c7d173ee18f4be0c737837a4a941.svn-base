package com.tianque.statRegrad.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.core.util.StringUtil;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.ReportDateType;
import com.tianque.exception.base.SystemUtilException;
import com.tianque.statRegrad.domain.StatRegradedPointsSearchVo;
import com.tianque.sysadmin.service.PropertyDictService;

public class RegradedPointUtil {
	private final static Logger logger = LoggerFactory
			.getLogger(RegradedPointUtil.class);
	private static Properties properties = new Properties();
	public static final String START_DATE = "startDate";
	public static final String END_TDATE = "endDate";

	static {
		try {
			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			InputStream is = cl.getResourceAsStream("regradedPoint.properties");
			properties.load(is);
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	public static List<Integer> getRankScale() throws Exception {
		if (!StringUtil.isStringAvaliable(get("regradedPointScale"))) {
			return null;
		}
		String[] scales = get("regradedPointScale").split(":");
		List<Integer> sc = new ArrayList<Integer>();
		for (String scale : scales) {
			if (StringUtils.isNumeric(scale)) {
				sc.add(Integer.parseInt(scale));
			} else {
				throw new SystemUtilException("配置有误，只能是以冒号隔开的正整数");
			}
		}
		if (sc.size() != 3) {
			throw new SystemUtilException("配置有误，值必须是“数字:数字:数字”的形式");
		}
		return sc;
	}

	public static List<Integer> getScale() throws Exception {
		if (!StringUtil.isStringAvaliable(get("scale"))) {
			return null;
		}
		String[] scales = get("scale").split(":");
		List<Integer> sc = new ArrayList<Integer>();
		for (String scale : scales) {
			if (StringUtils.isNumeric(scale)) {
				sc.add(Integer.parseInt(scale));
			} else {
				throw new SystemUtilException("配置有误，只能是以冒号隔开的正整数");
			}
		}
		if (sc.size() != 2) {
			throw new SystemUtilException("配置有误，值必须是“数字:数字”的形式");
		}
		return sc;
	}

	public static String[] getAlgorithmService() throws Exception {
		return StringUtil.isStringAvaliable(get("algorith")) ? get("algorith")
				.split(";") : null;
	}

	public static String get(String key) {
		return properties.getProperty(key);
	}

	public static Long getOrganizationTypeIdByInternalId(
			PropertyDictService propertyDictService, int internalId) {
		List<PropertyDict> orgTypes = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						OrganizationType.ORG_TYPE_KEY, internalId);
		if (orgTypes == null || orgTypes.size() == 0 || orgTypes.get(0) == null) {
			return null;
		}
		return orgTypes.get(0).getId();
	}

	public static Map<String, Date> getStartDateAndEndDate(int year, int month,
			String reportDateTypeName) {
		Date startDate = null;
		Date endDate = null;
		Calendar defaultDate = ReportDateType.getDefaultCalendar();
		defaultDate.set(Calendar.YEAR, year);
		defaultDate.set(Calendar.MONTH, month - 1);
		startDate = defaultDate.getTime();
		if (ReportDateType.GROUPBYYEAR_KEY.equals(reportDateTypeName)) {
			defaultDate.add(Calendar.MARCH, 12);
			endDate = defaultDate.getTime();
		} else if (ReportDateType.GROUPBYQUARTER_KEY.equals(reportDateTypeName)) {
			defaultDate.add(Calendar.MARCH, 3);
			endDate = defaultDate.getTime();
		} else {
			defaultDate.add(Calendar.MARCH, 1);
			endDate = defaultDate.getTime();
		}

		Map<String, Date> date = new HashMap<String, Date>();
		date.put(START_DATE, startDate);
		date.put(END_TDATE, endDate);
		return date;
	}
	
	public static List<StatRegradedPointsSearchVo> getStatRegradedPointsSearchVo(
			int year, int month, String reportDateTypeName, Long targeOrgId,
			Long orgType) {
		List<StatRegradedPointsSearchVo> statRegradedPointsSearchVos = new ArrayList<StatRegradedPointsSearchVo>();
		StatRegradedPointsSearchVo statRegradedPointsSearchVo; 
		if (ReportDateType.GROUPBYYEAR_KEY.equals(reportDateTypeName)) {
			do {
				statRegradedPointsSearchVo = new StatRegradedPointsSearchVo();
				statRegradedPointsSearchVo.setMonth(month);
				statRegradedPointsSearchVo.setYear(year);
				statRegradedPointsSearchVo.setOrgType(orgType);
				statRegradedPointsSearchVo.setTargeOrgId(targeOrgId);
				statRegradedPointsSearchVos.add(statRegradedPointsSearchVo);
				month++;
				if (month > 12) {
					break;
				}
				if (Calendar.getInstance().get(Calendar.YEAR) == year
						&& Calendar.getInstance().get(Calendar.MONTH) < month) {
					break;
				}
			} while (true);
			
		} else if (ReportDateType.GROUPBYQUARTER_KEY.equals(reportDateTypeName)) {
			int i = 1;
			do {
				statRegradedPointsSearchVo = new StatRegradedPointsSearchVo();
				statRegradedPointsSearchVo.setMonth(month);
				statRegradedPointsSearchVo.setYear(year);
				statRegradedPointsSearchVo.setOrgType(orgType);
				statRegradedPointsSearchVo.setTargeOrgId(targeOrgId);
				statRegradedPointsSearchVos.add(statRegradedPointsSearchVo);
				month++;
				i++;
				if (i > 3) {
					break;
				}
				if (Calendar.getInstance().get(Calendar.YEAR) == year
						&& Calendar.getInstance().get(Calendar.MONTH) < month) {
					break;
				}
			} while (true);
		} else {
			statRegradedPointsSearchVo = new StatRegradedPointsSearchVo();
			statRegradedPointsSearchVo.setMonth(month);
			statRegradedPointsSearchVo.setYear(year);
			statRegradedPointsSearchVo.setOrgType(orgType);
			statRegradedPointsSearchVo.setTargeOrgId(targeOrgId);
			statRegradedPointsSearchVos.add(statRegradedPointsSearchVo);
		}
		return statRegradedPointsSearchVos;
	}
	
}
