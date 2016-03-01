package com.tianque.statRegrad.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.dao.StatRegradedPointsDao;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.StatRegradedPoint;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.job.SessionHelper;
import com.tianque.statRegrad.domain.StatRegradedPointsSearchVo;
import com.tianque.statRegrad.util.RegradedPointUtil;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Service("defalutstatRegradedPointsServiceImpl")
public class DefalutStatRegradedPointsServiceImpl implements
		DefalutStatRegradedPointsService {
	
	public final static Logger logger = LoggerFactory
			.getLogger(DefalutStatRegradedPointsServiceImpl.class);

	@Autowired
	private StatRegradedPointsDao statRegradedPointsDao;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private List<StatRegradedPoint> getSimpleStatRegradedPoints(Date startDate,
			Date endDate, Long targeOrgId, Long orgType, String sortField,
			String order) {
		return statRegradedPointsDao.getSimpleStatRegradedPoints(startDate,
				endDate, targeOrgId, orgType, sortField, order);
	}
	
	private List<StatRegradedPoint> getSimpleStatRegradedPointsNew(
			List<StatRegradedPointsSearchVo> statRegradedPointsSearchVos) {
		if(statRegradedPointsSearchVos == null || statRegradedPointsSearchVos.size() < 1){
			return new ArrayList<StatRegradedPoint>();
		}
		return statRegradedPointsDao
				.getSimpleStatRegradedPointsNew(statRegradedPointsSearchVos);
	}

	@Override
	public List<StatRegradedPoint> findStatRegradedPoints(
			List<StatRegradedPoint> points, Integer year, Integer month,
			PropertyDict reportDateType, Long targeOrgId, int internalId,
			String sidx, String sord) {
		PropertyDict dict = propertyDictService
				.getPropertyDictById(reportDateType.getId());
		if(dict == null){
			return new ArrayList<StatRegradedPoint>();
		}
		List<StatRegradedPointsSearchVo> statRegradedPointsSearchVos = RegradedPointUtil
				.getStatRegradedPointsSearchVo(year, month, dict
						.getDisplayName(), targeOrgId, RegradedPointUtil
						.getOrganizationTypeIdByInternalId(propertyDictService,
								internalId));
		return getSimpleStatRegradedPointsNew(statRegradedPointsSearchVos);
	}
	
	@Override
	public void createRegradedPointStatTable(final Integer year, final Integer month){
		if(year == null || year == 0 || month == null || month == 0){
			logger.error("生成考核评估统计表失败，年:" +year+"月:"+month);
			return;
		}
		try {
			statRegradedPointsDao.dropRegradedPointStatTable(year, month);
		} catch (Exception e) {
			logger.error("删除" + year + "年" + month + "月的考核评估统计表失败，原因:" + e.getMessage());
		}
		final Map<String, Date> date = RegradedPointUtil.getStartDateAndEndDate(year,
				month, null);
		new Thread(new Runnable() {
			@Override
			public void run() {
				logger.error("生成" + year + "年" + month + "月的考核评估统计表开始。");
				SessionHelper.createMockAdminSession();
				PropertyDict propertyDict = null;
				do {
					try {
						propertyDict = propertyDictService
								.findPropertyDictByDomainNameAndDictDisplayName(
										PropertyTypes.ORGANIZATION_LEVEL,
										OrganizationLevel.PROVINCE_KEY);
					} catch (Exception e) {
						propertyDict = null;
					}
				} while (propertyDict == null);
				statRegradedPointsDao.createRegradedPointStatTable(year, month,
						date.get(RegradedPointUtil.START_DATE),
						date.get(RegradedPointUtil.END_TDATE),
						propertyDict.getId());
				List<Organization> orgs = organizationDubboService
						.findOrgsByOrgTypeAndOrgLevelAndParentId(
								OrganizationType.ADMINISTRATIVE_REGION,
								OrganizationLevel.CITY, 1L);
				for(Organization org : orgs){
					logger.error("生成" + year + "年" + month + "月的考核评估统计表,统计:"+org.getOrgName());
					statRegradedPointsDao.insertRegradedPointStatTableByOrgCode(
							year, month, org.getOrgInternalCode(),
							date.get(RegradedPointUtil.START_DATE),
					date.get(RegradedPointUtil.END_TDATE));
				}
				logger.error("生成" + year + "年" + month + "月的考核评估统计表成功。");
			}
		}).start();
		
	}
	
	@Override
	public void refreshRegradedPointStatTableByOrgId(Date applicationDate,
			Long targeOrgId) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(applicationDate);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		Map<String, Date> date = RegradedPointUtil.getStartDateAndEndDate(year,
				month, null);
		statRegradedPointsDao.deleteRegradedPointStatTableByOrgId(year, month,
				targeOrgId);
		statRegradedPointsDao.insertRegradedPointStatTableByOrgId(year, month,
				targeOrgId, date.get(RegradedPointUtil.START_DATE),
				date.get(RegradedPointUtil.END_TDATE));
	}
	
}
