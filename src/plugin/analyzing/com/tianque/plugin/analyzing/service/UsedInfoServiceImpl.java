package com.tianque.plugin.analyzing.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.leaderViewCache.domain.LeaderViewCache;
import com.tianque.baseInfo.leaderViewCache.service.LeaderViewCacheService;
import com.tianque.core.cache.constant.MemCacheConstant;
import com.tianque.core.util.CalendarUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.UsedInfo;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.analyzing.dao.UsedInfoDao;
import com.tianque.plugin.analyzing.util.LingRateUtil;
import com.tianque.plugin.serviceTeam.serviceRecord.service.ServiceRecordService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.usedInfoOptmize.domain.UsedInfoData;
import com.tianque.usedInfoOptmize.service.UsedInfoDataService;

/**
 * @Description:网格化服务管理信息系统使用情况service
 * @author zhangyouwei@hztianque.com
 * @date: 2015-3-27 下午05:17:52
 */
@Service("usedInfoService")
public class UsedInfoServiceImpl implements UsedInfoService {

	@Autowired
	private LeaderViewCacheService<UsedInfo> leaderViewCacheService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private ServiceRecordService serviceRecordService;
	@Autowired
	private UsedInfoDao usedInfoDao;
	@Autowired
	private UsedInfoDataService usedInfoDataService;

	@Override
	public List<UsedInfo> findUsedInfoData(Long orgId) {
		if (orgId == null) {
			throw new BusinessValidationException("请输入正确的组织机构");
		}
		try {
			List<UsedInfo> datas = null;
			String catchKey = MemCacheConstant.USEDINFO_KEY + orgId;
			datas = leaderViewCacheService.getDatasByCacheKeyForJob(
					MemCacheConstant.USEDINFO_NAMESPACE, catchKey,
					UsedInfo.class);

			if (datas == null) {
				int year = CalendarUtil.getNowYear();
				int month = CalendarUtil.getNowMonth();
				/** 上月开始 */
				Date monthStartDate = CalendarUtil
						.getBeforMonthFirstDayByMonthAndYear(year, month);
				/** 上月结束 */
				Date monthEndDate = CalendarUtil
						.getBeforMonthLastDayByMonthAndYear(year, month);
				/** 昨天开始 */
				Date beforDayStartDate = CalendarUtil
						.getBeforDayStartDate(new Date());
				/** 昨天结束 */
				Date beforDayEndDate = CalendarUtil
						.getBeforDayEndDate(new Date());
				/** 上周一 */
				Date beforWeekMonday = CalendarUtil
						.getBeforWeekMonday(new Date());
				/** 上周日 */
				Date beforWeekSunday = CalendarUtil
						.getBeforWeekSunday(new Date());
				PropertyDict adminOrgType = propertyDictService
						.findPropertyDictByDomainNameAndInternalId(
								PropertyTypes.ORGANIZATION_TYPE,
								OrganizationType.ADMINISTRATIVE_REGION).get(0);
				datas = createUsedInfoDataCacheByOrgId(beforDayStartDate,
						beforDayEndDate, beforWeekMonday, beforWeekSunday,
						monthStartDate, monthEndDate, orgId,
						adminOrgType.getId());

			}
			return datas;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"UsedInfoServiceImpl的findUsedInfoData方法错误:", e);
		}
	}

	@Override
	public void createUsedInfoData() {
		try {
			int year = CalendarUtil.getNowYear();
			int month = CalendarUtil.getNowMonth();
			/** 上月开始 */
			Date monthStartDate = CalendarUtil
					.getBeforMonthFirstDayByMonthAndYear(year, month);
			/** 上月结束 */
			Date monthEndDate = CalendarUtil
					.getBeforMonthLastDayByMonthAndYear(year, month);
			/** 昨天开始 */
			Date beforDayStartDate = CalendarUtil
					.getBeforDayStartDate(new Date());
			/** 昨天结束 */
			Date beforDayEndDate = CalendarUtil.getBeforDayEndDate(new Date());
			/** 上周一 */
			Date beforWeekMonday = CalendarUtil.getBeforWeekMonday(new Date());
			/** 上周日 */
			Date beforWeekSunday = CalendarUtil.getBeforWeekSunday(new Date());
			List<Long> orgIdList = getGridOrgUpOrgIdList();
			PropertyDict adminOrgType = propertyDictService
					.findPropertyDictByDomainNameAndInternalId(
							PropertyTypes.ORGANIZATION_TYPE,
							OrganizationType.ADMINISTRATIVE_REGION).get(0);

			for (Long orgId : orgIdList) {
				createUsedInfoDataCacheByOrgId(beforDayStartDate,
						beforDayEndDate, beforWeekMonday, beforWeekSunday,
						monthStartDate, monthEndDate, orgId,
						adminOrgType.getId());
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"UsedInfoServiceImpl的createUsedInfoData方法错误:", e);
		}
	}

	/**
	 * 根据orgId统计出下辖的网格化服务管理信息系统使用情况并且放入缓存表
	 * 
	 * @param beforDayStartDate
	 * @param beforDayEndDate
	 * @param beforWeekMonday
	 * @param beforWeekSunday
	 * @param monthStartDate
	 * @param monthEndDate
	 * @param orgId
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public List<UsedInfo> createUsedInfoDataCacheByOrgId(
			Date beforDayStartDate, Date beforDayEndDate, Date beforWeekMonday,
			Date beforWeekSunday, Date monthStartDate, Date monthEndDate,
			Long orgId, Long orgTypeId) {
		try {
			/** 活跃度情况 */
			List<UsedInfo> resultUserActiveRateList = usedInfoDao
					.getUsedLoginInfoForUsedInfo(beforDayStartDate,
							beforDayEndDate, beforWeekMonday, beforWeekSunday,
							monthStartDate, monthEndDate, orgId, orgTypeId);
			/** 走访记录情况 */
			List<UsedInfo> resultSpecialCrowdCountList = serviceRecordService
					.getSpecialCrowdCountForUsedInfo(beforDayStartDate,
							beforDayEndDate, beforWeekMonday, beforWeekSunday,
							monthStartDate, monthEndDate, orgId, orgTypeId);
			int dayActiveSum = 0;
			int weekActiveSum = 0;
			int monthActiveSum = 0;
			int dayAccountSum = 0;
			int weekAccountSum = 0;
			int monthAccountSum = 0;
			int daySpecialCrowdCountSum = 0;
			int monthSpecialCrowdCountSum = 0;
			int weekSpecialCrowdCountSum = 0;
			for (UsedInfo userActiveRate : resultUserActiveRateList) {
				fillActiveRate(userActiveRate);
				dayActiveSum += userActiveRate.getDayActive();
				dayAccountSum += userActiveRate.getDayAccountSum();
				weekActiveSum += userActiveRate.getWeekActive();
				weekAccountSum += userActiveRate.getWeekAccountSum();
				monthActiveSum += userActiveRate.getMonthActive();
				monthAccountSum += userActiveRate.getMonthAccountSum();

				for (UsedInfo specialCrowdCount : resultSpecialCrowdCountList) {
					/** 填充走访记录匹配上后跳出 */
					if (userActiveRate.getOrg().getId()
							.equals(specialCrowdCount.getOrg().getId())) {
						userActiveRate
								.setDaySpecialCrowdCount(specialCrowdCount
										.getDaySpecialCrowdCount());
						userActiveRate
								.setWeekSpecialCrowdCount(specialCrowdCount
										.getWeekSpecialCrowdCount());
						userActiveRate
								.setMonthSpecialCrowdCount(specialCrowdCount
										.getMonthSpecialCrowdCount());
						daySpecialCrowdCountSum += specialCrowdCount
								.getDaySpecialCrowdCount();
						weekSpecialCrowdCountSum += specialCrowdCount
								.getWeekSpecialCrowdCount();
						monthSpecialCrowdCountSum += specialCrowdCount
								.getMonthSpecialCrowdCount();
						resultSpecialCrowdCountList.remove(specialCrowdCount);
						break;
					}
				}
			}

			UsedInfo totalUsedInfo = new UsedInfo(dayActiveSum, weekActiveSum,
					monthActiveSum, dayAccountSum, weekAccountSum,
					monthAccountSum, daySpecialCrowdCountSum,
					weekSpecialCrowdCountSum, monthSpecialCrowdCountSum);
			Organization org = new Organization();
			org.setOrgName("合计");
			totalUsedInfo.setOrg(org);
			fillActiveRate(totalUsedInfo);
			resultUserActiveRateList.add(totalUsedInfo);
			String catchKey = MemCacheConstant.USEDINFO_KEY + orgId;
			leaderViewCacheService.addOrUpdateCacheByKey(
					MemCacheConstant.USEDINFO_NAMESPACE,
					new LeaderViewCache<UsedInfo>(catchKey,
							resultUserActiveRateList), UsedInfo.class);
			return resultUserActiveRateList;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"UsedInfoServiceImpl的createUsedInfoDataCacheByOrgId方法错误:",
					e);
		}
	}

	/**
	 * 填充活跃度
	 * 
	 * @param usedInfo
	 */
	private void fillActiveRate(UsedInfo usedInfo) {
		usedInfo.setDayActiveRate(LingRateUtil.getLingRate(
				usedInfo.getDayActive(), usedInfo.getDayAccountSum()));
		usedInfo.setWeekActiveRate(LingRateUtil.getLingRate(
				usedInfo.getWeekActive(), usedInfo.getWeekAccountSum()));
		usedInfo.setMonthActiveRate(LingRateUtil.getLingRate(
				usedInfo.getMonthActive(), usedInfo.getMonthAccountSum()));
	}

	/**
	 * 获取所有网格以上的职能部门
	 * 
	 * @return
	 */
	private List<Long> getGridOrgUpOrgIdList() {
		PropertyDict gridOrgLevel = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.ORGANIZATION_LEVEL,
						OrganizationLevel.GRID).get(0);

		PropertyDict adminOrgType = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.ORGANIZATION_TYPE,
						OrganizationType.ADMINISTRATIVE_REGION).get(0);
		List<Long> orgIdList = organizationDubboService
				.getOrganizationByOrgLevelAndOrgType(gridOrgLevel.getId(),
						adminOrgType.getId());
		return orgIdList;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public List<UsedInfo> createUsedInfoDataCacheByOrgIdToOptmize(
			Date beforDayStartDate, Date beforDayEndDate, Long orgId,
			Long orgTypeId) {
		try {
			List<UsedInfo> resultUserActiveRateList = usedInfoDao
					.getUsedInfoDayData(beforDayStartDate, beforDayEndDate,
							orgId, orgTypeId);
			List<UsedInfoData> weekUsedInfoDatas = usedInfoDataService
					.findUsedInfoWeekDataByParentOrgId(orgId);
			List<UsedInfoData> monthUsedInfoDatas = usedInfoDataService
					.findUsedInfoMonthDataByParentOrgId(orgId);

			int dayActiveSum = 0;
			int weekActiveSum = 0;
			int monthActiveSum = 0;
			int dayAccountSum = 0;
			int weekAccountSum = 0;
			int monthAccountSum = 0;
			int daySpecialCrowdCountSum = 0;
			int monthSpecialCrowdCountSum = 0;
			int weekSpecialCrowdCountSum = 0;
			for (UsedInfo userActiveRate : resultUserActiveRateList) {
				for (UsedInfoData weekUsedInfoData : weekUsedInfoDatas) {
					if (weekUsedInfoData.getOrg().getId()
							.equals(userActiveRate.getOrg().getId())) {
						userActiveRate.setWeekActive(weekUsedInfoData
								.getActiveSum());
						userActiveRate.setWeekAccountSum(weekUsedInfoData
								.getAccountSum());
						userActiveRate
								.setWeekSpecialCrowdCount(weekUsedInfoData
										.getSpecialCrowdCount());
						weekUsedInfoDatas.remove(weekUsedInfoData);
						break;
					}
				}
				for (UsedInfoData monthUsedInfoData : monthUsedInfoDatas) {
					if (monthUsedInfoData.getOrg().getId()
							.equals(userActiveRate.getOrg().getId())) {

						userActiveRate.setMonthActive(monthUsedInfoData
								.getActiveSum());
						userActiveRate.setMonthAccountSum(monthUsedInfoData
								.getAccountSum());
						userActiveRate
								.setMonthSpecialCrowdCount(monthUsedInfoData
										.getSpecialCrowdCount());
						monthUsedInfoDatas.remove(monthUsedInfoData);
						break;
					}
				}
				fillActiveRate(userActiveRate);
				dayActiveSum += userActiveRate.getDayActive();
				dayAccountSum += userActiveRate.getDayAccountSum();
				weekActiveSum += userActiveRate.getWeekActive();
				weekAccountSum += userActiveRate.getWeekAccountSum();
				monthActiveSum += userActiveRate.getMonthActive();
				monthAccountSum += userActiveRate.getMonthAccountSum();
				daySpecialCrowdCountSum += userActiveRate
						.getDaySpecialCrowdCount();
				weekSpecialCrowdCountSum += userActiveRate
						.getWeekSpecialCrowdCount();
				monthSpecialCrowdCountSum += userActiveRate
						.getMonthSpecialCrowdCount();

			}
			UsedInfo totalUsedInfo = new UsedInfo(dayActiveSum, weekActiveSum,
					monthActiveSum, dayAccountSum, weekAccountSum,
					monthAccountSum, daySpecialCrowdCountSum,
					weekSpecialCrowdCountSum, monthSpecialCrowdCountSum);
			Organization org = new Organization();
			org.setOrgName("合计");
			totalUsedInfo.setOrg(org);
			fillActiveRate(totalUsedInfo);
			resultUserActiveRateList.add(totalUsedInfo);
			String catchKey = MemCacheConstant.USEDINFO_KEY + orgId;
			leaderViewCacheService.addOrUpdateCacheByKey(
					MemCacheConstant.USEDINFO_NAMESPACE,
					new LeaderViewCache<UsedInfo>(catchKey,
							resultUserActiveRateList), UsedInfo.class);
			return resultUserActiveRateList;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"UsedInfoServiceImpl的createUsedInfoDataCacheByOrgId方法错误:",
					e);
		}
	}
	
	@Override
	public void createUserSignEveryDay(){
		/** 昨天开始 */
		Date beforDayStartDate = CalendarUtil.getBeforDayStartDate(new Date());
		/** 昨天结束 */
		Date beforDayEndDate = CalendarUtil.getBeforDayEndDate(new Date());
		try {
			usedInfoDao.dropUserSignEveryDay();
		} catch (Exception e) {
		}
		usedInfoDao.createUserSignEveryDay(beforDayStartDate, beforDayEndDate);
		usedInfoDao.createUserSignEveryDayIndex();
	}
}
