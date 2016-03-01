package com.tianque.plugin.transfer.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.actualHouse.dao.ActualHouseFromMongodbDao;
import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.base.domain.People;
import com.tianque.baseInfo.idleYouth.domain.IdleYouthsHasPropertyDicts;
import com.tianque.baseInfo.idleYouth.service.IdleYouthsHasPropertyDictsService;
import com.tianque.baseInfo.overseaPersonnel.domain.OverseaPersonnel;
import com.tianque.cache.PageInfoCacheThreadLocal;
import com.tianque.core.cache.constant.MemCacheConstant;
import com.tianque.core.redis.utils.RedisDefaultPageUtils;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.domain.Organization;
import com.tianque.domain.PopulationTypeBean;
import com.tianque.jms.OperateMode;
import com.tianque.mongodb.dao.HouseholdStaffMongoDao;
import com.tianque.plugin.tqSearch.syncSolrIndex.SyncPopulationSolrIndexForMQ;
import com.tianque.plugin.transfer.dao.TransferOtherInfoDao;
import com.tianque.plugin.transfer.util.Constants;
import com.tianque.plugin.transfer.util.Context;
import com.tianque.plugin.transfer.util.TransferUtil;
import com.tianque.service.PopulationTypeService;
import com.tianque.service.util.PopulationType;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.systemOperateLog.service.SystemOperateLogService;
import com.tianque.systemOperateLog.util.SystemOperateType;

@Component("finalHandler")
public class FinalHandler extends Handler {

	@Autowired
	private PopulationTypeService populationTypeService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private TransferOtherInfoDao transferOtherInfoDao;
	@Autowired
	private SystemOperateLogService systemOperateLogService;
	@Autowired
	private SyncPopulationSolrIndexForMQ syncPopulationSolrIndexForMQ;
	@Autowired
	private ActualHouseFromMongodbDao actualHouseFromMongodbDao;
	@Autowired
	private HouseholdStaffMongoDao householdStaffMongoDao;
	@Autowired
	private IdleYouthsHasPropertyDictsService idleYouthsHasPropertyDictsService;

	@Override
	public void invoke(String type, Long id, Long toOrgId, Context context) {
		Organization org = organizationDubboService.getSimpleOrgById(toOrgId);
		// 没有重复，修改ORG信息
		Map<String, Object> map = new HashMap();
		map.put("orgId", org.getId());
		map.put("orgCode", org.getOrgInternalCode());
		Long oldOrgId = null;
		Countrymen newCountrymen = null;
		Countrymen oldCountrymen = null;
		if (!Constants.ACTUALHOUSE_KEY.equals(type)) {
			newCountrymen = (Countrymen) context.getNewMap().get(type);
			oldCountrymen = (Countrymen) context.getOldMap().get(type);
			if (oldCountrymen != null
					&& oldCountrymen.getOrganization() != null) {
				oldOrgId = oldCountrymen.getOrganization().getId();
			}
		}
		if (Constants.ACTUALHOUSE_KEY.equals(type)) {
			// 如果目标网格没有重复数据，则对该数据增加日志信息.
			boolean haveSameHouseInfo = context.isExistedToOrgId();
			HouseInfo oldHouseInfo = (HouseInfo) context.getOldMap().get(type);
			HouseInfo newHouseInfo = (HouseInfo) context.getNewMap().get(type);
			Long houseOldOrgId = null;
			if (oldHouseInfo != null && oldHouseInfo.getOrganization() != null) {
				houseOldOrgId = oldHouseInfo.getOrganization().getId();
			}
			if (!haveSameHouseInfo) {// 没有重复数据
				systemOperateLogService.addSystemOperateLog(type, newHouseInfo
						.getId().toString(), org, org.getOrgInternalCode(),
						type, SystemOperateType.TRANSFER, newHouseInfo.getId(),
						houseOldOrgId);
			}

			return;
		} else if (TransferUtil
				.isActualPopulation(type.equals(Constants.OVERSEAPERSONNEL_KEY) ? PopulationType.OVERSEA_STAFF
						: type)) {
			if (Constants.UNSETTLEDPOPULATION_KEY.equals(type)) {

				systemOperateLogService.addSystemOperateLog(type,
						oldCountrymen.getIdCardNo(), org,
						org.getOrgInternalCode(), type,
						SystemOperateType.TRANSFER, oldCountrymen.getId(),
						oldOrgId);
				return;
			}
			if (Constants.OVERSEAPERSONNEL_KEY.equals(type)) {
				OverseaPersonnel oerseaPersonnel = (OverseaPersonnel) context
						.getOldMap().get(type);
				systemOperateLogService.addSystemOperateLog(type,
						oerseaPersonnel.getCertificateNo(), org,
						org.getOrgInternalCode(), type,
						SystemOperateType.TRANSFER, oerseaPersonnel.getId(),
						oldOrgId);
				return;
			}
			deleteOldPopulationType(type, context, toOrgId,
					context.isExistedToOrgId());
		} else if (TransferUtil.isAllAttentionPopulation(type)) {
			if (PopulationType.AIDSPOPULATIONS_KEY.equals(type)) {
				type = PopulationType.AIDSPOPULATIONS;
			}
			systemOperateLogService.addSystemOperateLog(type,
					oldCountrymen.getIdCardNo(), org, org.getOrgInternalCode(),
					type, SystemOperateType.TRANSFER, null, oldOrgId);
			if (context.isExistedToOrgId()) {
				List<PopulationTypeBean> list = populationTypeService
						.getPopulationTypeByActualIdAndType(
								newCountrymen.getId(),
								newCountrymen.getActualPopulationType());
				for (PopulationTypeBean populationType : list) {
					if (type.equals(Constants.RECTIFICATIVEPERSON_KEY)
							&& populationType.getPopulationType().equals(
									Constants.POSITIVEINFO_KEY)) {
						populationTypeService
								.deletePopulationTypeByPopulationIdAndType(
										populationType.getPopulationId(),
										populationType.getPopulationType());
						transferOtherInfoDao.deleteAttentionPopulationExisted(
								BaseInfoTables.getKeytables().get(
										populationType.getPopulationType()),
								populationType.getPopulationId());

						// 转移的时候 有重复类型的 计数器-1
						transferDecreasePageInfoCacheThreadLocal(
								populationType.getPopulationType(),
								populationType.getPopulationId(), toOrgId);

					} else if (type.equals(Constants.POSITIVEINFO_KEY)
							&& populationType.getPopulationType().equals(
									Constants.RECTIFICATIVEPERSON_KEY)) {
						populationTypeService
								.deletePopulationTypeByPopulationIdAndType(
										populationType.getPopulationId(),
										populationType.getPopulationType());
						transferOtherInfoDao.deleteAttentionPopulationExisted(
								BaseInfoTables.getKeytables().get(
										populationType.getPopulationType()),
								populationType.getPopulationId());

						// 转移的时候 有重复类型的 计数器-1
						transferDecreasePageInfoCacheThreadLocal(
								populationType.getPopulationType(),
								populationType.getPopulationId(), toOrgId);

					} else if (populationType.getPopulationType().equals(type)) {
						List<IdleYouthsHasPropertyDicts> dicts = null;
						// 重点青少年 特殊处理
						if (BaseInfoTables.IDLEYOUTH_KEY.equals(type)) {
							idleYouthsHasPropertyDictsService
									.deleteIdleYouthsHasPropertyDictsByIdleYouthId(populationType
											.getPopulationId());
							dicts = idleYouthsHasPropertyDictsService
									.queryIdleYouthsHasPropertyDictsByIdleYouthIdForList(id);
							idleYouthsHasPropertyDictsService
									.deleteIdleYouthsHasPropertyDictsByIdleYouthId(id);
						}

						// 在修改之前得到传过来的业务信息 只限人口
						People fromPeople = null;
						if (Constants.getClassNameByType(type) != null) {
							fromPeople = TransferUtil
									.getPeopleByPopulationTypeAndId(type, id);
						}

						populationTypeService
								.deletePopulationTypeByActualIdAndTypeAndPopulationType(
										oldCountrymen.getId(),
										oldCountrymen.getActualPopulationType(),
										type);
						transferOtherInfoDao.deleteAttentionPopulationExisted(
								BaseInfoTables.getKeytables().get(type),
								populationType.getPopulationId());
						map.put("id", id);

						transferOtherInfoDao.updateAttentionPopulationExisted(
								BaseInfoTables.getKeytables().get(type),
								populationType.getPopulationId(), map);

						// 重点青少年 重新新增关系
						if (dicts != null && dicts.size() > 0) {
							IdleYouthsHasPropertyDicts idleYouthsHasPropertyDicts = null;
							for (int i = 0; i < dicts.size(); i++) {
								idleYouthsHasPropertyDicts = dicts.get(i);
								idleYouthsHasPropertyDicts
										.setIdleYouthsid(populationType
												.getPopulationId());
								idleYouthsHasPropertyDictsService
										.addIdleYouthsHasPropertyDicts(idleYouthsHasPropertyDicts);
							}
						}
						// 转移的时候 有重复类型的 计数器-1
						transferDecreasePageInfoCacheThreadLocal(fromPeople,
								populationType.getPopulationType());
					}
				}
			}
		}

	}

	private void deleteOldPopulationType(String type, Context context,
			Long toOrgId, boolean isExisted) {
		Organization org = organizationDubboService.getSimpleOrgById(toOrgId);
		// 没有重复，修改ORG信息
		Map<String, Object> map = new HashMap();
		map.put("orgId", org.getId());
		map.put("orgCode", org.getOrgInternalCode());
		Countrymen oldCountrymen = (Countrymen) context.getOldMap().get(type);
		Countrymen newCountrymen = (Countrymen) context.getNewMap().get(type);
		syncPopulationSolrIndexForMQ.sendPopulationSolrIndexForMQ(
				newCountrymen, type, OperateMode.EDIT);
		if (oldCountrymen == null || newCountrymen == null) {
			return;
		}
		List<PopulationTypeBean> oldPopulationTypeList = populationTypeService
				.getPopulationTypeByActualIdAndType(oldCountrymen.getId(), type);
		List<PopulationTypeBean> newPopulationTypeList = populationTypeService
				.getPopulationTypeByActualIdAndType(newCountrymen.getId(), type);
		Long oldOrgId = null;
		if (oldCountrymen.getOrganization() != null) {
			oldOrgId = oldCountrymen.getOrganization().getId();
		}

		systemOperateLogService.addSystemOperateLog(type,
				oldCountrymen.getIdCardNo(), org, org.getOrgInternalCode(),
				type, SystemOperateType.TRANSFER, null, oldOrgId);
		for (PopulationTypeBean oldPopulationTypeBean : oldPopulationTypeList) {
			// 如果目标网格不存在仅仅记录转移日志，不删除关系
			systemOperateLogService.addSystemOperateLog(
					oldPopulationTypeBean.getPopulationType(),
					oldCountrymen.getIdCardNo(), org, org.getOrgInternalCode(),
					type, SystemOperateType.TRANSFER, null, oldOrgId);
			if (!isExisted) {
				continue;
			}
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
				// 缓存计数器
				String modelName = null;
				People people = null;
				if (Constants.getClassNameByType(temp.getPopulationType()) != null) {
					String objectType = PopulationType
							.getFirstCharUpperCaseTypeByType(temp
									.getPopulationType());
					modelName = MemCacheConstant.getCachePageKey(objectType);
					people = TransferUtil.getPeopleByPopulationTypeAndId(
							temp.getPopulationType(), temp.getPopulationId());
				}

				transferOtherInfoDao.deleteAttentionPopulationExisted(
						BaseInfoTables.getKeytables().get(
								temp.getPopulationType()),
						temp.getPopulationId());
				populationTypeService
						.deletePopulationTypeByPopulationIdAndType(
								temp.getPopulationId(),
								temp.getPopulationType());
				populationTypeService
						.deletePopulationTypeByActualIdAndTypeAndPopulationType(
								oldPopulationTypeBean.getActualId(),
								oldPopulationTypeBean.getActualType(),
								oldPopulationTypeBean.getPopulationType());

				if (Constants.getClassNameByType(temp.getPopulationType()) != null) {
					PageInfoCacheThreadLocal.decrease(modelName, people, 1);
				}

			} else if (isSamePopulation) {
				populationTypeService
						.deletePopulationTypeByActualIdAndTypeAndPopulationType(
								oldPopulationTypeBean.getActualId(),
								oldPopulationTypeBean.getActualType(),
								oldPopulationTypeBean.getPopulationType());
			}
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

	// 缓存技术器增减
	private void transferDecreasePageInfoCacheThreadLocal(
			String populationType, Long populationId, Long toOrgId) {
		if (Constants.getClassNameByType(populationType) != null) {
			String objectType = PopulationType
					.getFirstCharUpperCaseTypeByType(populationType);
			String modelName = MemCacheConstant.getCachePageKey(objectType);
			People people = RedisDefaultPageUtils
					.getPeopleByPopulationTypeAndId(objectType, populationId,
							modelName, toOrgId);
			PageInfoCacheThreadLocal.decrease(modelName, people, 1);
		}
	}

	// 缓存技术器增减
	private void transferDecreasePageInfoCacheThreadLocal(People fromPeople,
			String populationType) {
		if (Constants.getClassNameByType(populationType) != null) {
			String objectType = PopulationType
					.getFirstCharUpperCaseTypeByType(populationType);
			String modelName = MemCacheConstant.getCachePageKey(objectType);
			PageInfoCacheThreadLocal.decrease(modelName, fromPeople, 1);

		}
	}
}
