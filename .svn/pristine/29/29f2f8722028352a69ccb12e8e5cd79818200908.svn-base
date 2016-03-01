package com.tianque.mobile.baseInfo.impl;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.base.domain.LogoutDetail;
import com.tianque.baseInfo.base.service.BaseInfoPopulationTemplateService;
import com.tianque.baseInfo.floatingPopulation.service.FloatingPopulationService;
import com.tianque.baseInfo.householdStaff.service.HouseholdStaffService;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.SpringBeanUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.mobile.baseInfo.CommonActualPopulationMobileService;
import com.tianque.mobile.baseInfo.constants.CommonActualPopulationType;

/**
 * @Description:人口手机端取消、重新关注
 * @author zhangyouwei@hztianque.com
 * @date: 2015-4-17 上午10:40:04
 */
@Transactional
@Service("commonActualPopulationMobileService")
public class CommonActualPopulationMobileServiceImpl implements
		CommonActualPopulationMobileService {
	private static Logger logger = LoggerFactory
			.getLogger(CommonActualPopulationMobileServiceImpl.class);
	@Autowired
	private Map<String, BaseInfoPopulationTemplateService> baseInfoPopulationTemplateServiceMap;

	@Override
	public List<Long> updateActualPopulationEmphasiseForMobile(Long orgId,
			LogoutDetail logoutDetail, String populationType, Long[] ids) {
		if (orgId == null || ids == null || ids.length == 0) {
			throw new BusinessValidationException("参数错误");
		}
		if (!StringUtil.isStringAvaliable(populationType)
				|| !CommonActualPopulationType.PopulationTypeMap
						.containsKey(populationType)) {
			throw new BusinessValidationException("人口类型错误");
		}
		try {
			String type = CommonActualPopulationType.PopulationTypeMap
					.get(populationType);
			List<Long> resultList = null;
			if (BaseInfoTables.FLOATINGPOPULATION_KEY.equals(type)
					|| BaseInfoTables.HOUSEHOLDSTAFF_KEY.equals(type)) {
				resultList = updateActualPopulationEmphasiseAndSyncSolr(orgId,
						logoutDetail, type, ids);
			} else {
				BaseInfoPopulationTemplateService bIPopulationTemplateService = baseInfoPopulationTemplateServiceMap
						.get(CommonActualPopulationType.PopulationTypeServiceMap
								.get(populationType));
				resultList = (List<Long>) bIPopulationTemplateService
						.updateLogOutDetailAndCountByPopulationTypeAndIds(
								orgId, logoutDetail, type, ids);
			}
			return resultList;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"CommonActualPopulationMobileServiceImpl人口手机端取消、重新关注错误", e);
		}
	}

	/**
	 * 户籍流口等需要同步solr的单独处理
	 * 
	 * @param orgId
	 * @param logoutDetail
	 * @param type
	 * @param ids
	 * @return
	 */
	private List<Long> updateActualPopulationEmphasiseAndSyncSolr(Long orgId,
			LogoutDetail logoutDetail, String populationType, Long[] ids) {

		List<Long> idList = null;
		String methodName = "updateLogOutOf"
				+ StringUtil.firstCharUpperCase(populationType) + "ByIds";
		try {
			Method[] methods = null;
			HouseholdStaffService householdStaffService = null;
			FloatingPopulationService floatingPopulationService = null;

			if (BaseInfoTables.HOUSEHOLDSTAFF_KEY.equals(populationType)) {
				householdStaffService = (HouseholdStaffService) SpringBeanUtil
						.getBeanFromSpringByBeanName(populationType + "Service");
				methods = householdStaffService.getClass().getMethods();
			} else if (BaseInfoTables.FLOATINGPOPULATION_KEY
					.equals(populationType)) {
				floatingPopulationService = (FloatingPopulationService) SpringBeanUtil
						.getBeanFromSpringByBeanName(populationType + "Service");
				methods = floatingPopulationService.getClass().getMethods();
			}

			boolean matchMethod = false;
			for (Method method : methods) {
				Class[] paramClasses = method.getParameterTypes();
				if (methodName.toUpperCase().equals(
						method.getName().toUpperCase())
						&& paramClasses != null && paramClasses.length == 4) {
					if (householdStaffService != null) {
						idList = (List<Long>) method.invoke(
								householdStaffService, orgId, logoutDetail,
								populationType, ids);
					} else if (floatingPopulationService != null) {
						idList = (List<Long>) method.invoke(
								floatingPopulationService, orgId, logoutDetail,
								populationType, ids);
					}
					matchMethod = true;
					break;
				}

			}
			if (!matchMethod) {
				throw new RuntimeException("列表缓存或CONT缓存中查询对应的人口，未找到对应方法["
						+ methodName + "]！");
			}
		} catch (Exception e) {
			logger.error("列表缓存或CONT缓存中查询对应的人口，未找到对应方法[" + methodName + "]！", e);
		}
		return idList;
	}
}
