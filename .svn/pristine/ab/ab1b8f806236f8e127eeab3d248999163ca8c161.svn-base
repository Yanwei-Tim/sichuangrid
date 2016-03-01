package com.tianque.fourTeams.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tianque.core.cache.service.CacheService;
import com.tianque.core.cache.util.CacheKeyGenerator;
import com.tianque.core.util.GlobalValue;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.vo.TrampResidentHasInsuranceCaseVo;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueLogNew;
import com.tianque.state.IssueDealType;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

public class FourTeamsControllerHelper {
	private static Logger logger = LoggerFactory
			.getLogger(FourTeamsControllerHelper.class);
	private static ApplicationContext context;

	public static PageInfo processAllOrgRelativeName(PageInfo pageInfo,
			OrganizationDubboService organizationDubboService, String[] orgPropertyNames,
			Long organizationId) {
		if (pageInfo == null || pageInfo.getResult() == null
				|| pageInfo.getResult().size() == 0)
			return pageInfo;
		processAllOrgRelativeName(pageInfo.getResult(), organizationDubboService,
				orgPropertyNames, organizationId);
		return pageInfo;
	}

	public static List processAllOrgRelativeName(List list,
			OrganizationDubboService organizationDubboService, String[] orgPropertyNames,
			Long organizationId) {
		if (list == null || list.size() == 0)
			return list;
		try {
			Map<Long, String> cacheValues = new HashMap();
			List<Method> orgMethods = getOrganizationMethods(list.get(0)
					.getClass(), orgPropertyNames);
			for (Object object : list) {
				for (Method readOrgMethod : orgMethods) {
					processSingleOrganizationRelativeName(readOrgMethod,
							object, cacheValues, organizationDubboService,
							organizationId);
				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException("processAllOrgRelativeName错误",
					e);
		}
		return list;
	}

	public static List processAllOrgName(List list,
			OrganizationDubboService organizationDubboService, String[] orgPropertyNames) {
		if (list == null || list.size() == 0)
			return list;
		try {
			Map<Long, String> cacheValues = new HashMap();
			List<Method> orgMethods = getOrganizationMethods(list.get(0)
					.getClass(), orgPropertyNames);
			for (Object object : list) {
				for (Method readOrgMethod : orgMethods) {
					processSingleOrganizationName(readOrgMethod, object,
							cacheValues, organizationDubboService);
				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException("processAllOrgName错误", e);
		}
		return list;
	}

	public static Organization proccessRelativeOrgNameByOrg(
			Organization organization, OrganizationDubboService organizationDubboService) {
		organization = organizationDubboService.getSimpleOrgById(organization
				.getId());
		if (organization.getParentOrg() != null) {
			organization.setOrgName(getOrganizationRelativeName(
					organization.getId(), organizationDubboService));
		}
		return organization;
	}

	public static Organization proccessRelativeOrgNameByOrgId(Long orgId,
			OrganizationDubboService organizationDubboService) {
		Organization organization = organizationDubboService.getSimpleOrgById(orgId);
		if (organization.getParentOrg() != null) {
			organization.setOrgName(getOrganizationRelativeName(
					organization.getId(), organizationDubboService));
		}
		return organization;
	}

	private static void processSingleOrganizationRelativeName(
			Method readOrgMethod, Object object, Map<Long, String> cacheValues,
			OrganizationDubboService organizationDubboService, Long organizationId)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		Organization organization = (Organization) readOrgMethod.invoke(object);
		if (organization != null) {
			String orgRelativeName = cacheValues.get(organization.getId());
			if (orgRelativeName == null) {
				Organization org = organizationDubboService
						.getSimpleOrgById(organization.getId());
				if (org != null && org.getParentOrg() == null) {
					orgRelativeName = org.getOrgName();
					// if (org.getParentOrg() == null){
					// orgRelativeName=org.getOrgName();
					// }else{
					// orgRelativeName =
					// ControllerHelper.getRelativeOrgNameByOrgId(organization.getId(),
					// organizationDubboService);
					// }
					// cacheValues.put(organization.getId(),orgRelativeName);
				}
				if (org != null && org.getParentOrg() != null) {
					orgRelativeName = FourTeamsControllerHelper
							.getRelativeOrgNameListByOrgId(
									organization.getId(), organizationDubboService,
									organizationId);
				}
				cacheValues.put(organization.getId(), orgRelativeName);
			}
			organization.setOrgName(orgRelativeName);
		}

	}

	private static void processSingleOrganizationName(Method readOrgMethod,
			Object object, Map<Long, String> cacheValues,
			OrganizationDubboService organizationDubboService)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		Organization organization = (Organization) readOrgMethod.invoke(object);
		try {
			if (organization == null)
				return;
			String orgName = cacheValues.get(organization.getId());
			if (orgName == null) {
				Organization org = organizationDubboService
						.getSimpleOrgById(organization.getId());
				orgName = org.getOrgName();

				cacheValues.put(organization.getId(), orgName);
			}
			organization.setOrgName(orgName);
		} catch (Exception e) {
			logger.error("没有找到组织机构，orgid=" + organization.getId());

		}
	}

	private static List getOrganizationMethods(Class clazz,
			String[] orgPropertyNames) {
		List<Method> result = new ArrayList<Method>();
		for (String propertyName : orgPropertyNames) {
			Method fieldReadMethod = getFieldReadMethod(clazz, propertyName,
					Organization.class);
			if (fieldReadMethod != null && isAccessible(fieldReadMethod)) {
				result.add(fieldReadMethod);
			}
		}
		return result;
	}

	private static boolean isAccessible(Method method) {
		return Modifier.isPublic(method.getModifiers())
				&& Modifier.isPublic(method.getDeclaringClass().getModifiers());
	}

	private static Method getFieldReadMethod(Class clazz, String propertyName,
			Class expireReturnClass) {
		Method result = null;
		try {
			result = clazz.getMethod("get"
					+ propertyName.substring(0, 1).toUpperCase()
					+ propertyName.substring(1));
		} catch (SecurityException e) {
		} catch (NoSuchMethodException e) {
			try {
				result = clazz.getMethod("is"
						+ propertyName.substring(0, 1).toUpperCase()
						+ propertyName.substring(1));
			} catch (SecurityException e1) {
			} catch (NoSuchMethodException e1) {
			}
		}
		if (result != null
				&& !expireReturnClass.isAssignableFrom(result.getReturnType())) {
			result = null;
		}
		return result;
	}

	public static List<FourTeamsIssueLogNew> processAllIssueLogDealDescription(
			List<FourTeamsIssueLogNew> list) {
		if (list == null || list.size() == 0)
			return list;
		try {
			for (FourTeamsIssueLogNew issueLog : list) {
				if (issueLog.getDealType() == null) {
					continue;
				}
				if (IssueDealType.COMMAND.equals(issueLog.getDealType())) {
					issueLog.setDealDescription("对                "
							+ issueLog.getTargeOrg().getOrgName()
							+ "                批示");
				} else if (issueLog.getDealType() >= IssueDealType.SEND_BACK
						&& issueLog.getDealType() <= IssueDealType.TELL
						&& !IssueDealType.COMMENT
								.equals(issueLog.getDealType())) {
					issueLog.setDealDescription(issueLog.getDealDescription()
							+ "                给                "
							+ issueLog.getTargeOrg().getOrgName());
				} else if (IssueDealType.HISTORICALISSUE.equals(issueLog
						.getDealType())
						|| IssueDealType.PUBLICLTYCASS.equals(issueLog
								.getDealType())) {
					issueLog.setDealDescription("将其设为                "
							+ issueLog.getTargeOrg().getOrgName()
							+ "               的                "
							+ issueLog.getDealDescription());
				} else if (!IssueDealType.HISTORICALISSUE.equals(issueLog
						.getDealType())
						&& !IssueDealType.READ.equals(issueLog.getDealType())
						&& issueLog.getDealType() >= IssueDealType.NORMAL
						&& issueLog.getDealType() <= IssueDealType.CANCELSUPERVISE) {
					issueLog.setDealDescription("对                "
							+ issueLog.getTargeOrg().getOrgName()
							+ "                "
							+ issueLog.getDealDescription());
				} else if (IssueDealType.CANCELHISTORICALISSUE.equals(issueLog
						.getDealType())) {
					issueLog.setDealDescription("取消                "
							+ issueLog.getTargeOrg().getOrgName()
							+ "                的历史遗留");
				} else if (IssueDealType.CANCELPUBLICLTYCASS.equals(issueLog
						.getDealType())) {
					issueLog.setDealDescription("取消                "
							+ issueLog.getTargeOrg().getOrgName()
							+ "                的宣传案例");
				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"processAllIssueLogDealDescription错误", e);
		}
		return list;
	}

	public synchronized static PageInfo proccessRelativeOrgNameByPageInfo(
			PageInfo pageInfo, OrganizationDubboService organizationDubboService) {
		if (pageInfo == null || pageInfo.getResult() == null
				|| pageInfo.getResult().size() == 0)
			return pageInfo;
		try {
			Map<Long, String> tmpRelativeInfoStorate = new HashMap();
			Method method = pageInfo.getResult().get(0).getClass()
					.getMethod("getOrganization");
			for (Object object : pageInfo.getResult()) {
				Organization organization = (Organization) method
						.invoke(object);
				if (organization == null) {
					continue;
				}
				String orgName = tmpRelativeInfoStorate.get(organization
						.getId());
				if (orgName == null) {
					orgName = getOrganizationRelativeName(organization.getId(),
							organizationDubboService);
					tmpRelativeInfoStorate.put(organization.getId(), orgName);
				}
				organization.setOrgName(orgName);
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"processAllIssueLogDealDescription错误", e);
		}
		return pageInfo;

	}

	public static Map<Long, List<PropertyDict>> proccessTrampResidentHasInsuranceMap(
			List<TrampResidentHasInsuranceCaseVo> trampResidentHasInsurancesCases,
			PropertyDictService propertyDictService) {
		Map<Long, List<PropertyDict>> insuranceMap = new HashMap<Long, List<PropertyDict>>();
		for (TrampResidentHasInsuranceCaseVo vo : trampResidentHasInsurancesCases) {
			if (insuranceMap.get(vo.getTrampResidentId()) != null) {
				insuranceMap.get(vo.getTrampResidentId()).add(
						propertyDictService.getPropertyDictById(vo
								.getPropertyDictId()));
			} else {
				List<PropertyDict> tempList = new ArrayList<PropertyDict>();
				tempList.add(propertyDictService.getPropertyDictById(vo
						.getPropertyDictId()));
				insuranceMap.put(vo.getTrampResidentId(), tempList);
			}
		}
		return insuranceMap;
	}

	public static String getRelativeOrgNameByOrgId(Long orgId,
			OrganizationDubboService organizationDubboService) {
		Organization organization = organizationDubboService.getSimpleOrgById(orgId);
		if (organization.getParentOrg() != null) {
			return getOrganizationRelativeName(orgId, organizationDubboService);
		}
		return organization.getOrgName();
	}

	public static String getRelativeOrgNameByOrgIdTemp(Long orgId,
			OrganizationDubboService organizationDubboService, ApplicationContext context) {
		FourTeamsControllerHelper.context = context;
		Organization organization = organizationDubboService.getSimpleOrgById(orgId);
		if (organization.getParentOrg() != null) {
			return getOrganizationRelativeName(orgId, organizationDubboService);
		}
		return organization.getOrgName();
	}

	/**
	 * 列表查询方法
	 */
	public static String getRelativeOrgNameListByOrgId(Long orgId,
			OrganizationDubboService organizationDubboService, Long organizationId) {
		Organization organization = organizationDubboService.getSimpleOrgById(orgId);
		if (organization.getParentOrg() != null) {
			return getOrganizationRelativeNameList(orgId, organizationDubboService,
					organizationId);
		}
		return organization.getOrgName();
	}

	/**
	 * 列表查询方法
	 */
	public static String getOrganizationRelativeNameList(Long orgId,
			OrganizationDubboService organizationDubboService, Long organizationId) {
		String path = "";
		Long actualRoot = ThreadVariable.getUser().getOrganization().getId();
		if ("test".equals(GlobalValue.environment)) {
			path = getRelativeNameList(orgId, organizationDubboService, actualRoot,
					organizationId);
		} else {
			if (context == null) {
				context = WebApplicationContextUtils
						.getWebApplicationContext(ServletActionContext
								.getServletContext());
			}
			CacheService cacheService = (CacheService) context
					.getBean("cacheService");
			if (orgId != null) {
				if (cacheService.get(CacheKeyGenerator
						.generateCacheKeyFromString(
								FourTeamsControllerHelper.class, actualRoot
										+ "_" + orgId + "_" + organizationId)) != null) {
					path = cacheService.get(
							CacheKeyGenerator.generateCacheKeyFromString(
									FourTeamsControllerHelper.class, actualRoot
											+ "_" + orgId + "_"
											+ organizationId)).toString();
				} else {
					path = getRelativeNameList(orgId, organizationDubboService,
							actualRoot, organizationId);
					cacheService.set(CacheKeyGenerator
							.generateCacheKeyFromString(
									FourTeamsControllerHelper.class, actualRoot
											+ "_" + orgId + "_"
											+ organizationId), path);
				}
			}
		}
		return path;
	}

	/**
	 * 获取orgid在指定rootOrgid下的相对路径名，->分割
	 * 
	 * @return
	 */
	public static String getOrganizationRelativeName(Long orgId,
			OrganizationDubboService organizationDubboService) {
		String path = "";
		Long actualRoot = ThreadVariable.getUser().getOrganization().getId();
		if ("test".equals(GlobalValue.environment)) {
			path = getRelativeName(orgId, organizationDubboService, actualRoot);
		} else {
			if (context == null) {
				context = WebApplicationContextUtils
						.getWebApplicationContext(ServletActionContext
								.getServletContext());
			}
			CacheService cacheService = (CacheService) context
					.getBean("cacheService");
			if (orgId != null) {
				if (cacheService.get(CacheKeyGenerator
						.generateCacheKeyFromString(
								FourTeamsControllerHelper.class, actualRoot
										+ "_" + orgId)) != null) {
					path = cacheService.get(
							CacheKeyGenerator.generateCacheKeyFromString(
									FourTeamsControllerHelper.class, actualRoot
											+ "_" + orgId)).toString();
				} else {
					path = getRelativeName(orgId, organizationDubboService,
							actualRoot);
					cacheService.set(CacheKeyGenerator
							.generateCacheKeyFromString(
									FourTeamsControllerHelper.class, actualRoot
											+ "_" + orgId), path);
				}
			}
		}
		return path;
	}

	private static String getRelativeName(Long orgId,
			OrganizationDubboService organizationDubboService, Long actualRoot) {
		String path;
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		path = org.getOrgName();
		if (!orgId.equals(actualRoot)) {
			while (org.getParentOrg() != null
					&& !org.getParentOrg().getId().equals(actualRoot)) {
				org = organizationDubboService.getSimpleOrgById(org.getParentOrg()
						.getId());
				path = org.getOrgName() + "->" + path;
			}
		}
		return path;
	}

	/**
	 * 列表查询方法
	 */
	private static String getRelativeNameList(Long orgId,
			OrganizationDubboService organizationDubboService, Long actualRoot,
			Long organizationId) {
		String path;
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		path = org.getOrgName();
		if (!orgId.equals(actualRoot)) {
			while (org.getParentOrg() != null
					&& !org.getParentOrg().getId().equals(actualRoot)
					&& !org.getId().equals(organizationId)) {
				org = organizationDubboService.getSimpleOrgById(org.getParentOrg()
						.getId());
				path = org.getOrgName() + "->" + path;
				if (org.getId().equals(organizationId))
					break;
			}
		}
		if (organizationId != null) {
			StringBuffer strbuf = new StringBuffer("");
			String[] str = path.split("->");
			if (str.length > 3) {
				for (int i = 0; i < 3; i++) {
					strbuf.append(str[i]);
					if (i != 2) {
						strbuf.append("->");
					}
				}
				path = strbuf.toString();
			}
		}
		return path;
	}

}
