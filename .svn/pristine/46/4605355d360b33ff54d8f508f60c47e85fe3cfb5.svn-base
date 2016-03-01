package com.tianque.plugin.transfer.handler;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.util.SpringBeanUtil;
import com.tianque.domain.PopulationTypeBean;
import com.tianque.plugin.transfer.util.Constants;
import com.tianque.plugin.transfer.util.Context;
import com.tianque.plugin.transfer.util.TransferUtil;
import com.tianque.service.IssueTypeService;
import com.tianque.service.PopulationTypeService;
import com.tianque.service.util.PopulationType;

@Component("issueHandler")
public class IssueHandler extends Handler {
	@Autowired
	private IssueTypeService issueTypeService;
	@Autowired
	public PopulationTypeService populationTypeService;

	@Override
	public void invoke(String type, Long id, Long toOrgId, Context context) {
		/** 若为实口，查出业务人员，并对关联业务人员进行处理 */
		if (TransferUtil.isActualPopulation(type)) {
			List<PopulationTypeBean> list = populationTypeService
					.getPopulationTypeByActualIdAndType(id, type);
			for (PopulationTypeBean populationType : list) {
				handle(populationType.getPopulationType(),
						populationType.getPopulationId(), toOrgId);
			}
		} else {
			handle(type, id, toOrgId);
		}
	}

	private void handle(String type, Long id, Long toOrgId) {
		/** 调用数据认领之前撤销认领存在的删除方法 */
		Object target = SpringBeanUtil
				.getBeanFromSpringByBeanName(getServiceBeanName(type));
		String getByIdMethod = "get" + getClassName(type) + "ById";
		String searchArg = null;

		try {
			Method method = target.getClass().getMethod(getByIdMethod,
					Long.class);
			target = method.invoke(target, id);
			if (target != null) {
				try {
					method = target.getClass().getMethod("getIdCardNo");
				} catch (Exception e) {
					try {
						method = target.getClass().getMethod("getPlaceName");
					} catch (Exception e1) {
						try {
							method = target.getClass().getMethod(
									"getCompanyName");
						} catch (Exception e2) {
							try {
								method = target.getClass().getMethod(
										"getUnitName");
							} catch (Exception e3) {
								method = target.getClass().getMethod("getName");
							}
						}
					}
				}
				searchArg = (String) method.invoke(target);
				handlePersonRelationship(type, toOrgId, searchArg, id);
				handlePlaceRelationship(type, toOrgId, searchArg, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 处理事件与人关联关系 */
	private void handlePersonRelationship(String type, Long orgId,
			String searchArg, Long id) {
		Map infoMap = new HashMap();
		infoMap.put("personType", type);
		infoMap.put("orgId", orgId);
		infoMap.put("cardNoOrName", searchArg);
		infoMap.put("personId", id);
		issueTypeService.updateRelatePersonsForOrgChange(infoMap);
	}

	/** 处理事件与场所关联关系 */
	private void handlePlaceRelationship(String type, Long orgId,
			String searchArg, Long id) {
		Map infoMap = new HashMap();
		infoMap.put("placeType", type);
		infoMap.put("orgId", orgId);
		infoMap.put("cardNoOrName", searchArg);
		infoMap.put("placeId", id);
		issueTypeService.updateRelatePlacesForOrgChange(infoMap);
	}

	/** 根据传入type获取service名 */
	private String getServiceBeanName(String objetType) {
		if (Constants.SAFETYPRODUCTIONKEY_KEY.equals(objetType)
				|| Constants.SECURITYKEY_KEY.equals(objetType)
				|| Constants.ENTERPRISEKEY_KEY.equals(objetType)
				|| Constants.ENTERPRISEDOWNKEY_KEY.equals(objetType)) {
			return "enterpriseService";
		} else if (Constants.OVERSEAPERSONNEL_KEY.equals(objetType)) {
			return "overseaStaffService";
		} else if (Constants.AIDSPOPULATIONS_KEY.equals(objetType)) {
			return PopulationType.AIDSPOPULATIONS + "Service";
		} else {
			return objetType + "Service";
		}
	}

	/** 根据传入type获取类名（用于拼接getById方法名） */
	private String getClassName(String objetType) {
		if (Constants.SAFETYPRODUCTIONKEY_KEY.equals(objetType)
				|| Constants.SECURITYKEY_KEY.equals(objetType)
				|| Constants.ENTERPRISEKEY_KEY.equals(objetType)
				|| Constants.ENTERPRISEDOWNKEY_KEY.equals(objetType)) {
			return "Enterprise";
		} else {
			return objetType.substring(0, 1).toUpperCase()
					+ objetType.substring(1);
		}
	}

	@Override
	public void validate(String type, Long id, Long toOrgId, Context context) {
	}

	@Override
	public void validate(String type, Long id, Long toOrgId, Long fromOrgId,
			Context context) {
	}

	@Override
	public void invoke(String type, Long id, Long toOrgId, Long fromOrgId,
			Context context) {
		invoke(type, id, toOrgId, context);
	}
}
