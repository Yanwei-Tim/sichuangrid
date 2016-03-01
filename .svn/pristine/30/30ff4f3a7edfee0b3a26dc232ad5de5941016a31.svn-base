package com.tianque.issue.migrateData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.domain.IssueType;
import com.tianque.domain.IssueTypeDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.init.ContextType;
import com.tianque.issue.constants.IssueTypes;
import com.tianque.service.IssueTypeService;
import com.tianque.statAnalyse.issueManage.listManage.constant.QueryType;
import com.tianque.statAnalyse.issueManage.listManage.domain.IssueAreaStat;
import com.tianque.statAnalyse.issueManage.listManage.service.IssueAreaStatService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.util.ApplicationContextFactory;

public class HandleIssueData {

	private static Logger logger = LoggerFactory
			.getLogger(HandleIssueData.class);
	private MigrationIssueData migrationIssueData;
	private IssueTypeService issueTypeService;
	private Map<Long, IssueType> issueTypeMap;// 老事件类型Id 与新事件类型的映射
	private Map<String, String> contradictionOldToNewTypeMap;
	private Map<String, String> securitytroubleOldToNewTypeMap;
	private PropertyDictService propertyDictService;
	private OrganizationDubboService organizationDubboService;
	private IssueAreaStatService issueAreaStatService;

	public static void main(String[] args) {
		if (args.length > 0) {
			HandleIssueData handleIssueData = new HandleIssueData();
			handleIssueData.init(args[0]);
			handleIssueData.initMap();
			handleIssueData.doMigrationIssueData();
		}

	}

	private void doMigrationIssueData() {
		logger.error("开始执行事件数据迁移");
		updateIssuestepEmergencyLevel();
		migrationIssueTypes();
		// statIssueReport();
		logger.error("迁移完成");
		System.exit(0);
	}

	private void migrationIssueTypes() {
		// 矛盾纠纷和治安、安全隐患
		List<Long> issueIds = migrationIssueData
				.getIssueIdsFromIssuehastypeByDomainid(
						issueTypeService.getIssueTypeDoaminByDomainName(
								IssueTypes.CONTRADICTION).getId(),
						issueTypeService.getIssueTypeDoaminByDomainName(
								IssueTypes.SECURITYTROUBLE).getId());
		for (Long issueId : issueIds) {
			proccessOldIssueTypes(issueId);
		}
		logger.error("处理矛盾纠纷事件和治安、安全隐患" + issueIds.size() + "条");
	}

	private void proccessOldIssueTypes(Long issueId) {
		List<IssueHasType> ihts = migrationIssueData
				.getIssueHasTypeByIssueId(issueId);
		if (ihts != null && ihts.size() > 0) {
			IssueType issueType = issueTypeMap
					.get(ihts.get(0).getIssueTypeId());
			IssueHasType issueHasType = new IssueHasType();
			issueHasType.setIssueId(issueId);
			issueHasType.setIssuetypedomainId(issueType.getIssueTypeDomain()
					.getId());
			issueHasType.setIssueTypeId(issueType.getId());
			migrationIssueData.deleteIssueHasTypeByIssueId(issueId);
			migrationIssueData.addIssuehastypes(issueHasType);
		}
	}

	private void initMap() {
		logger.error("开始初始化映射关系");
		issueTypeMap = new HashMap<Long, IssueType>();
		contradictionOldToNewTypeMap = new HashMap<String, String>();
		securitytroubleOldToNewTypeMap = new HashMap<String, String>();
		contradictionOldToNewTypeMap.put("民族宗教", "民族宗教");
		contradictionOldToNewTypeMap.put("军转干部、复员退伍军人安置", "军人安置");
		contradictionOldToNewTypeMap.put("征地拆迁安置", "征地拆迁");
		contradictionOldToNewTypeMap.put("建筑工程质量、物业管理", "建筑工程");
		contradictionOldToNewTypeMap.put("土地矿产山林水利界线权属", "山林土地");
		contradictionOldToNewTypeMap.put("经济活动", "经济活动");
		contradictionOldToNewTypeMap.put("劳资纠纷", "劳资纠纷");
		contradictionOldToNewTypeMap.put("企业改制", "企业改制");
		contradictionOldToNewTypeMap.put("环境污染、生态破坏", "环境生态");
		contradictionOldToNewTypeMap.put("司法活动", "司法活动");
		contradictionOldToNewTypeMap.put("行政执法活动", "行政执法活动");
		contradictionOldToNewTypeMap.put("大中专院校、中小学校", "院校问题");
		contradictionOldToNewTypeMap.put("海事渔事", "海事渔事");
		contradictionOldToNewTypeMap.put("干部作风", "干部作风");
		contradictionOldToNewTypeMap.put("村（社区、居）务管理", "村（社区）务管理");
		contradictionOldToNewTypeMap.put("农村土地承包、农村集体“三资”及农民负担", "农村“三资”");
		contradictionOldToNewTypeMap.put("婚姻家庭邻里", "婚姻家庭");
		contradictionOldToNewTypeMap.put("医患纠纷", "医患纠纷");
		contradictionOldToNewTypeMap.put("其他", "其他");

		securitytroubleOldToNewTypeMap.put("抢劫", "其他");
		securitytroubleOldToNewTypeMap.put("抢夺", "其他");
		securitytroubleOldToNewTypeMap.put("盗窃", "其他");
		securitytroubleOldToNewTypeMap.put("赌博", "其他");
		securitytroubleOldToNewTypeMap.put("卖淫嫖娼", "其他");
		securitytroubleOldToNewTypeMap.put("毒品", "其他");
		securitytroubleOldToNewTypeMap.put("黑恶势力", "其他");
		securitytroubleOldToNewTypeMap.put("消防安全", "其他");
		securitytroubleOldToNewTypeMap.put("安全生产", "其他");
		securitytroubleOldToNewTypeMap.put("食品卫生", "其他");
		securitytroubleOldToNewTypeMap.put("环境污染", "其他");
		securitytroubleOldToNewTypeMap.put("公共安全", "其他");
		securitytroubleOldToNewTypeMap.put("其他", "其他");

		List<IssueType> contradictionList = issueTypeService
				.findIssueTypesByDomainId(issueTypeService
						.getIssueTypeDoaminByDomainName(
								IssueTypes.CONTRADICTION).getId());
		List<IssueType> securitytroubleList = issueTypeService
				.findIssueTypesByDomainId(issueTypeService
						.getIssueTypeDoaminByDomainName(
								IssueTypes.SECURITYTROUBLE).getId());

		IssueTypeDomain resolvethecontradictionsDomain = issueTypeService
				.getIssueTypeDoaminByDomainName(IssueTypes.RESOLVETHECONTRADICTIONS);// 矛盾劝解
		IssueTypeDomain securityprecautionsDomain = issueTypeService
				.getIssueTypeDoaminByDomainName(IssueTypes.SECURITYPRECAUTIONS);// 参与治安防控
		for (IssueType it : contradictionList) {
			IssueType issueType = issueTypeService.getIssueTypeByIssueTypeName(
					contradictionOldToNewTypeMap.get(it.getIssueTypeName()),
					resolvethecontradictionsDomain.getId());
			issueTypeMap.put(it.getId(), issueType);
		}
		for (IssueType it : securitytroubleList) {
			IssueType issueType = issueTypeService.getIssueTypeByIssueTypeName(
					securitytroubleOldToNewTypeMap.get(it.getIssueTypeName()),
					securityprecautionsDomain.getId());
			issueTypeMap.put(it.getId(), issueType);
		}
		logger.error(issueTypeMap.size() + "");
	}

	private void updateIssuestepEmergencyLevel() {
		List<IssueMigrationVo> needUpdateIssueMigrationVos = migrationIssueData
				.getHaveEmergencyLevelData();
		if (needUpdateIssueMigrationVos != null
				&& needUpdateIssueMigrationVos.size() > 0) {
			for (IssueMigrationVo vo : needUpdateIssueMigrationVos) {
				migrationIssueData.updateIssuestepEmergencyLevel(vo);
			}
			logger.error("issueSteps中重大紧急程度更新完成，一共更新"
					+ needUpdateIssueMigrationVos.size() + "条");
		}
	}

	private void init(String contextType) {
		logger.error("开始初始化Spring环境");
		if (contextType.equals("development")) {
			migrationIssueData = (MigrationIssueData) ApplicationContextFactory
					.getInstance().getBean(ContextType.development,
							"migrationIssueData");
			issueTypeService = (IssueTypeService) ApplicationContextFactory
					.getInstance().getBean(ContextType.development,
							"issueTypeService");
			propertyDictService = (PropertyDictService) ApplicationContextFactory
					.getInstance().getBean(ContextType.development,
							"propertyDictService");
			organizationDubboService = (OrganizationDubboService) ApplicationContextFactory
					.getInstance().getBean(ContextType.development,
							"organizationDubboService");
			issueAreaStatService = (IssueAreaStatService) ApplicationContextFactory
					.getInstance().getBean(ContextType.development,
							"issueAreaStatService");

		} else if (contextType.equals("production")) {
			migrationIssueData = (MigrationIssueData) ApplicationContextFactory
					.getInstance().getBean(ContextType.production,
							"migrationIssueData");
			issueTypeService = (IssueTypeService) ApplicationContextFactory
					.getInstance().getBean(ContextType.production,
							"issueTypeService");
			propertyDictService = (PropertyDictService) ApplicationContextFactory
					.getInstance().getBean(ContextType.production,
							"propertyDictService");
			organizationDubboService = (OrganizationDubboService) ApplicationContextFactory
					.getInstance().getBean(ContextType.production,
							"organizationDubboService");
			issueAreaStatService = (IssueAreaStatService) ApplicationContextFactory
					.getInstance().getBean(ContextType.production,
							"issueAreaStatService");
		}
	}

	private void statIssueReport() {
		logger.error("开始");
		List<Organization> organizations = findAllOrganizationExcludeGrid();
		for (Organization organization : organizations) {
			// 九月
			addIssueHandleStats(getDate("2013-09-01 00:00:00"),
					getDate("2013-09-30 23:59:00"), organization.getId());
			addIssueClassificationStats(getDate("2013-09-01 00:00:00"),
					getDate("2013-09-30 23:59:00"), organization.getId());

			addIssueStepStats(getDate("2013-09-01 00:00:00"),
					getDate("2013-09-30 23:59:00"), organization.getId());
			// 十月
			addIssueHandleStats(getDate("2013-10-01 00:00:00"),
					getDate("2013-10-31 23:59:00"), organization.getId());
			addIssueClassificationStats(getDate("2013-10-01 00:00:00"),
					getDate("2013-10-31 23:59:00"), organization.getId());

			addIssueStepStats(getDate("2013-10-01 00:00:00"),
					getDate("2013-10-31 23:59:00"), organization.getId());

			// 十一月
			addIssueHandleStats(getDate("2013-11-01 00:00:00"),
					getDate("2013-11-30 23:59:00"), organization.getId());
			addIssueClassificationStats(getDate("2013-11-01 00:00:00"),
					getDate("2013-11-30 23:59:00"), organization.getId());

			addIssueStepStats(getDate("2013-11-01 00:00:00"),
					getDate("2013-11-30 23:59:00"), organization.getId());
			// 十二月
			addIssueHandleStats(getDate("2013-12-01 00:00:00"),
					getDate("2013-12-31 23:59:00"), organization.getId());
			addIssueClassificationStats(getDate("2013-12-01 00:00:00"),
					getDate("2013-12-31 23:59:00"), organization.getId());

			addIssueStepStats(getDate("2013-12-01 00:00:00"),
					getDate("2013-12-31 23:59:00"), organization.getId());
		}
	}

	private Date getDate(String dateStr) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return dateFormat.parse(dateStr);
		} catch (Exception e) {
			logger.error("格式化日期错误", e);
		}
		return null;
	}

	private void addIssueHandleStats(Date startDate, Date endDate, Long orgId) {
		List<IssueAreaStat> handleStats = issueAreaStatService
				.findIssueAreaStatsByYearAndMonthAndParentOrgId(startDate,
						endDate, orgId, QueryType.AREA_DEAL_HISTORY,null);
		for (IssueAreaStat issueAreaStat : handleStats) {
			issueAreaStat.setFindBackDate(endDate);
			migrationIssueData.addIssueHandleStat(issueAreaStat);
		}

	}

	private void addIssueClassificationStats(Date startDate, Date endDate,
			Long orgId) {
		List<IssueAreaStat> classificationStats = issueAreaStatService
				.findIssueAreaStatsByYearAndMonthAndParentOrgId(startDate,
						endDate, orgId, QueryType.AREA_CLASSIFICATION_HISTORY,null);
		for (IssueAreaStat issueAreaStat : classificationStats) {
			issueAreaStat.setFindBackDate(endDate);
			migrationIssueData.addIssueClassificationStat(issueAreaStat);
		}

	}

	private void addIssueStepStats(Date startDate, Date endDate, Long orgId) {
		List<IssueAreaStat> stepStats = issueAreaStatService
				.findIssueAreaStatsByYearAndMonthAndParentOrgId(startDate,
						endDate, orgId, QueryType.STEP_HISTORY,null);
		for (IssueAreaStat issueAreaStat : stepStats) {
			issueAreaStat.setFindBackDate(endDate);
			migrationIssueData.addIssueStepStat(issueAreaStat);
		}
	}

	private List<Organization> findAllOrganizationExcludeGrid() {
		List<PropertyDict> orgLevels = propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.ORGANIZATION_LEVEL);
		List<Organization> organizations = new ArrayList<Organization>();
		for (PropertyDict orgLevel : orgLevels) {
			if (orgLevel.getInternalId() != OrganizationLevel.GRID) {
				organizations.addAll(organizationDubboService
						.fingOrganizationforLevel(orgLevel.getId()));
			}
		}
		return organizations;
	}
}
