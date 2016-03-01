package com.tianque.plugin.transfer.handler;

import java.lang.reflect.Method;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.SpringBeanUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PopulationTypeBean;
import com.tianque.plugin.serviceTeam.serviceGuardersWithObject.service.ServiceGuardersWithObjectService;
import com.tianque.plugin.serviceTeam.serviceMemberWithObject.dao.ServiceMemberWithObjectDao;
import com.tianque.plugin.serviceTeam.serviceMemberWithObject.service.ServiceMemberWithObjectService;
import com.tianque.plugin.serviceTeam.serviceRecord.dao.ServiceRecordRelyObjectDao;
import com.tianque.plugin.serviceTeam.serviceRecord.domain.ServiceRecordRelyObject;
import com.tianque.plugin.serviceTeam.serviceRecord.service.ServiceRecordService;
import com.tianque.plugin.transfer.util.Constants;
import com.tianque.plugin.transfer.util.Context;
import com.tianque.plugin.transfer.util.TransferUtil;
import com.tianque.service.PopulationTypeService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("serviceTeamHandler")
public class ServiceTeamHandler extends Handler {

	@Autowired
	private ServiceRecordService serviceRecordService;
	@Autowired
	private ServiceRecordRelyObjectDao serviceRecordRelyObjectDao;
	@Autowired
	private ServiceMemberWithObjectDao serviceMemberWithObjectDao;
	@Autowired
	public PopulationTypeService populationTypeService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private ServiceMemberWithObjectService serviceMemberWithObjectService;
	@Autowired
	private ServiceGuardersWithObjectService serviceGuardersWithObjectService;

	@Override
	public void invoke(String type, Long id, Long toOrgId, Context context) {
		Organization org = organizationDubboService.getSimpleOrgById(toOrgId);
		Countrymen newCountrymen = (Countrymen) context.getNewMap().get(type);
		/** 若为实口，查出业务人员，并对关联业务人员进行处理 */
		if (TransferUtil.isActualPopulation(type)) {
			List<PopulationTypeBean> list = populationTypeService
					.getPopulationTypeByActualIdAndType(id, type);
			if (context.isExistedToOrgId()) {
				combineServiceObject(type, context, toOrgId);
			} else {
				for (PopulationTypeBean populationType : list) {
					serviceRecordService.updateServiceRecordsOrg(org.getId(),
							org.getOrgInternalCode(),
							populationType.getPopulationType(),
							populationType.getPopulationId());
				}
			}
		} else if (TransferUtil.isAllAttentionPopulation(type)) {
		} else {
			handle(type, id, toOrgId);
		}

	}

	private void combineServiceObject(String type, Context context, Long toOrgId) {
		Organization org = organizationDubboService.getSimpleOrgById(toOrgId);
		Countrymen oldCountrymen = (Countrymen) context.getOldMap().get(type);
		Countrymen newCountrymen = (Countrymen) context.getNewMap().get(type);
		List<PopulationTypeBean> oldPopulationTypeList = populationTypeService
				.getPopulationTypeByActualIdAndType(oldCountrymen.getId(), type);
		List<PopulationTypeBean> newPopulationTypeList = populationTypeService
				.getPopulationTypeByActualIdAndType(newCountrymen.getId(), type);
		for (PopulationTypeBean oldPopulationTypeBean : oldPopulationTypeList) {
			boolean isSamePopulation = false;
			boolean isMutexPopulation = false;
			PopulationTypeBean temp = new PopulationTypeBean();
			for (PopulationTypeBean newPopulationTypeBean : newPopulationTypeList) {
				if (oldPopulationTypeBean.getPopulationType().equals(
						BaseInfoTables.POSITIVEINFO_KEY)
						&& newPopulationTypeBean.getPopulationType().equals(
								BaseInfoTables.RECTIFICATIVEPERSON_KEY)) {
					temp = newPopulationTypeBean;
					isMutexPopulation = true;
					break;
				}
				if (oldPopulationTypeBean.getPopulationType().equals(
						BaseInfoTables.RECTIFICATIVEPERSON_KEY)
						&& newPopulationTypeBean.getPopulationType().equals(
								BaseInfoTables.POSITIVEINFO_KEY)) {
					temp = newPopulationTypeBean;
					isMutexPopulation = true;
					break;
				}
				if (oldPopulationTypeBean.getPopulationType().equals(
						newPopulationTypeBean.getPopulationType())) {
					temp = newPopulationTypeBean;
					isSamePopulation = true;
				}
			}
			if (isMutexPopulation) {
				updateServiceObject(temp.getPopulationType(),
						temp.getPopulationId(),
						oldPopulationTypeBean.getPopulationId(), org.getId(),
						org.getOrgInternalCode(),
						oldPopulationTypeBean.getPopulationType());
			} else if (isSamePopulation) {
				updateServiceObject(oldPopulationTypeBean.getPopulationType(),
						oldPopulationTypeBean.getPopulationId(),
						temp.getPopulationId(), org.getId(),
						org.getOrgInternalCode());
			} else {
				serviceRecordService.updateServiceRecordsOrg(org.getId(),
						org.getOrgInternalCode(),
						oldPopulationTypeBean.getPopulationType(),
						oldPopulationTypeBean.getPopulationId());
			}
		}
	}

	private void updateServiceObject(String type, Long oldId, Long newId,
			Long toOrgId, String toOrgCode) {
		serviceRecordService.updateServiceRecordByTransfer(toOrgId, toOrgCode,
				type, oldId, newId);
		serviceMemberWithObjectService
				.updateServiceMemberAndTeamHasObjectForMove(type, oldId, newId);
		serviceGuardersWithObjectService.updateServiceGuardersHasObject(type,
				oldId, newId);
	}

	private void updateServiceObject(String type, Long oldId, Long newId,
			Long toOrgId, String toOrgCode, String newType) {
		serviceRecordService.updateServiceRecordByTransfer(toOrgId, toOrgCode,
				type, oldId, newId, newType);
		serviceMemberWithObjectService
				.updateServiceMemberAndTeamHasObjectForMove(type, oldId, newId,
						newType);
		serviceGuardersWithObjectService.updateServiceGuardersHasObject(type,
				oldId, newId, newType);
	}

	private void handle(String type, Long id, Long toOrgId) {
		Object target = SpringBeanUtil
				.getBeanFromSpringByBeanName(getServiceBeanName(type));
		/** 调用数据认领之前撤销认领存在的删除方法 */
		String getByIdMethod = "get" + getClassName(type) + "ById";
		String searchArg = null;
		try {
			/** 根据id获取对象 */
			Method method = target.getClass().getMethod(getByIdMethod,
					Long.class);
			target = method.invoke(target, id);
			try {
				method = target.getClass().getMethod("getIdCardNo");
			} catch (Exception e) {
				try {
					method = target.getClass().getMethod("getPlaceName");
				} catch (Exception e1) {
					try {
						method = target.getClass().getMethod("getCompanyName");
					} catch (Exception e2) {
						try {
							method = target.getClass().getMethod("getUnitName");
						} catch (Exception e3) {
							method = target.getClass().getMethod("getName");
						}
					}
				}
			}
			searchArg = (String) method.invoke(target);
			handleServiceRecordRelationship(type, toOrgId, searchArg, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 处理服务记录关联关系 */
	private void handleServiceRecordRelationship(String type, Long orgId,
			String searchArg, Long id) {
		ServiceRecordRelyObject serviceRecordRelyObject = new ServiceRecordRelyObject();
		serviceRecordRelyObject.setObjectType(type);
		serviceRecordRelyObject.setOrgId(orgId);
		serviceRecordRelyObject.setCardNoOrName(searchArg);
		serviceRecordRelyObject.setObjectId(id);
		serviceRecordRelyObjectDao
				.updateServiceRecordRelyObject(serviceRecordRelyObject);

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
