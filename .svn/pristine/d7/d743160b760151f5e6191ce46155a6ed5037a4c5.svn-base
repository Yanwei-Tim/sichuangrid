package com.tianque.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.domain.PlantFormMessageConfig;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.SocietySafeCheck;
import com.tianque.domain.User;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.platformMessage.service.PlatformMessageService;
import com.tianque.service.SocietySafeCheckService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.working.domain.DailyDirectory;
import com.tianque.working.domain.DailyLog;
import com.tianque.working.domain.DailyYear;
import com.tianque.working.service.DailyDirectoryService;
import com.tianque.working.service.DailyLogService;
import com.tianque.working.service.DailyYearService;

@SuppressWarnings("serial")
@Controller("societySafeCheckController")
@Scope("prototype")
public class SocietySafeCheckController extends BaseAction {

	@Autowired
	private DailyLogService dailyLogService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SocietySafeCheckService societySafeCheckService;
	@Autowired
	private DailyDirectoryService dailyDirectoryService;
	@Autowired
	private DailyYearService dailyYearService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private PlatformMessageService platformaMessageService;

	private DailyDirectory dailyDirectory;
	private DailyYear dailyYear;
	private SocietySafeCheck societySafeCheck;
	private String dataCollectionArray;// ajax提交所收集的表格的数据
	private String dailyLogId;
	private String resultData[];// ajax将后台的结果集返回到页面
	private DailyLog dailyLog;
	private Integer orgLevle;
	private String checkUser;// 签发人
	private int reportType;// 报表类型
	private Organization organization;
	private String message;// 向页面返回信息
	private String signCode;// 标识码
	private String directoryNameSign;// 上下级台账目录名称的对应名

	private PlantFormMessageConfig plantFormMessageConfig;

	public String dispatch() throws Exception {
		this.prepareExcute();
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			dailyYear = dailyYearService.getSimpleDailyYearById(dailyLog
					.getDailyYear().getId());
			return "add";
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
			dailyLogId = dailyLog.getId().toString();
			return "success";
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			dailyLogId = dailyLog.getId().toString();
			return findSocietySafeCheck();
		}
		return "success";

	}

	public void prepareExcute() throws Exception {
		organization = organizationDubboService.getSimpleOrgById(ThreadVariable
				.getSession().getOrganization().getId());
		PropertyDict propertyDict = propertyDictService
				.getPropertyDictById(organization.getOrgLevel().getId());
		if (propertyDict == null) {
			this.errorMessage = "请配置数据库字典!";
			orgLevle = 20;
			return;
		}
		if (propertyDict.getInternalId() == OrganizationLevel.COUNTRY) {
			orgLevle = 6;
		} else if (propertyDict.getInternalId() == OrganizationLevel.PROVINCE) {
			orgLevle = 5;
		} else if (propertyDict.getInternalId() == OrganizationLevel.CITY) {
			orgLevle = 4;
		} else if (propertyDict.getInternalId() == OrganizationLevel.DISTRICT) {
			orgLevle = 3;
		} else if (propertyDict.getInternalId() == OrganizationLevel.TOWN) {
			orgLevle = 2;
		} else if (propertyDict.getInternalId() == OrganizationLevel.VILLAGE) {
			orgLevle = 1;
		} else if (propertyDict.getInternalId() == OrganizationLevel.GRID) {
			orgLevle = 0;
		} else
			orgLevle = 10;
	}

	public void prepareMatch() throws Exception {
		dailyDirectory = dailyDirectoryService
				.getSimpleDailyDirectoryById(dailyLogService
						.getSimpleDailyLogById(Long.parseLong(dailyLogId))
						.getDailyDirectory().getId());
		String parentDirectoryName = dailyDirectoryService
				.getSimpleDailyDirectoryById(
						dailyDirectory.getParentDailyDirectory().getId())
				.getShortName();
		directoryNameSign = "p" + parentDirectoryName + "s"
				+ dailyDirectory.getShortName();
	}

	/**
	 * 判断上下级的台账目录名称是否对应
	 * 
	 * @param directoryName
	 * @return
	 */
	public boolean directoryNameSignMatch(String directoryName) {
		if (StringUtil.isStringAvaliable(directoryNameSign)) {
			if (directoryNameSign.equals(directoryName))
				return true;
		}
		return false;
	}

	public String addSocietySafeCheck() throws Exception {
		if (societySafeCheckService.getSocietySafeCheckByDailyLogId(Long
				.parseLong(dailyLogId)) == null)
			return saveSocietySafeCheck();
		return updateSocietySafeCheck();
	}

	public String updateSocietySafeCheck() throws Exception {
		societySafeCheck = societySafeCheckService
				.getSocietySafeCheckByDailyLogId(Long.parseLong(dailyLogId));
		societySafeCheck = stringToSocietySafeCheck(societySafeCheck);
		societySafeCheckService.updateSocietySafeCheck(societySafeCheck);
		return "success";
	}

	public String deleteSocietySafeCheck() throws Exception {
		societySafeCheckService.deleteSocietySafeCheckById(Long
				.parseLong(dailyLogId));
		return "success";
	}

	/**
	 * 判断报表是否已经存在
	 * 
	 * @return
	 */
	public String checkExist() throws Exception {
		societySafeCheck = societySafeCheckService
				.getSocietySafeCheckBySignCode(signCode);
		if (societySafeCheck != null) {
			throw new Exception("");
		} else
			message = "success";
		return message;
	}

	/**
	 * 数据上报
	 * 
	 * @return
	 */
	public String reportUp() throws Exception {
		if (checkUser == null || "".equals(checkUser))
			return "fail";
		societySafeCheck = societySafeCheckService
				.getSocietySafeCheckByDailyLogId(Long.parseLong(dailyLogId));
		societySafeCheck.setReportState(1);
		societySafeCheck.setReportDate(getReportUpDate());
		societySafeCheck.setCheckUser(checkUser);
		societySafeCheckService.updateSocietySafeCheck(societySafeCheck);
		return "success";
	}

	/**
	 * 报表回退
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String reportBack() throws Exception {
		if (plantFormMessageConfig == null) {
			this.errorMessage = "参数错误!";
			return ERROR;
		}
		if (StringUtils.isBlank(plantFormMessageConfig.getMsgTitle())) {
			this.errorMessage = "参数错误!";
			return ERROR;
		}
		if (StringUtils.isBlank(plantFormMessageConfig.getMsgContent())) {
			this.errorMessage = "参数错误!";
			return ERROR;
		}
		Long dailyLogId = dailyLog.getId();
		societySafeCheck = societySafeCheckService
				.getSocietySafeCheckByDailyLogId(dailyLogId);
		societySafeCheck.setReportState(0);
		societySafeCheck.setReportDate(null);
		societySafeCheckService.updateSocietySafeCheck(societySafeCheck);

		DailyLog dailyLog = dailyLogService.getSimpleDailyLogById(dailyLogId);
		dailyLog.setRemark("未上报");
		dailyLog.setUpdateDate(null);
		dailyLogService.updateDailyLog(dailyLog);
		societySafeCheckService.backReport(dailyLogId);
		dailyLogService.updateIssueStateByID(dailyLog.getId(), 1);
		List<User> users = permissionService
				.findChildUsersByEnameAndInternalCode("addDailyLog",
						dailyLog.getOrgInternalCode());
		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);
			platformaMessageService.sendPlatformMessageToUser(user.getId(),
					plantFormMessageConfig.getMsgContent(),
					plantFormMessageConfig.getMsgTitle());
		}
		return "success";
	}

	// 方法没有显式调用
	// public String findReportInfo() throws Exception {
	// this.prepareExcute();
	// if (dailyDirectory == null)
	// return "error";
	// dailyDirectory = dailyDirectoryService
	// .getSimpleDailyDirectoryById(dailyDirectory.getId());
	// String parentDirectoryName = dailyDirectoryService
	// .getSimpleDailyDirectoryById(
	// dailyDirectory.getParentDailyDirectory().getId())
	// .getShortName();
	// directoryNameSign = "p" + parentDirectoryName + "s"
	// + dailyDirectory.getShortName();
	// PageInfo<IssueSubmitInfoVo> pageInfo = societySafeCheckService
	// .findIssueSubmitInfo(page, rows, organization.getId(), dailyLog
	// .getDailyYear().getName(), directoryNameSign);
	// gridPage = new GridPage(pageInfo);
	// return "success";
	// }

	/**
	 * 查找汇总基础上修改并保存的数据
	 * 
	 * @return
	 */
	public String findSocietySafeCheck() throws Exception {
		societySafeCheck = societySafeCheckService
				.getSocietySafeCheckByDailyLogId(Long.parseLong(dailyLogId));
		resultData = longArrayToStringArray(ObjectToArray(societySafeCheck));
		return "success";

	}

	/**
	 * 查找所有子地区上报后汇总的数据
	 * 
	 * @return
	 */
	public String findCollectionData() throws Exception {
		if (societySafeCheckService.getSocietySafeCheckByDailyLogId(Long
				.parseLong(dailyLogId)) != null)
			return findSocietySafeCheck();
		return collectionData();

	}

	/**
	 * 重新生成报表
	 * 
	 * @return
	 */
	public String reloadCollectionData() throws Exception {
		return collectionData();
	}

	/**
	 * 对子地区的数据进行汇总,其中季报分为两种：市级直接对县级的月报进行汇总， 省级以上的是对市级的季报进行汇总
	 * 
	 * @return
	 */
	public String collectionData() throws Exception {
		this.prepareExcute();
		this.prepareMatch();
		DailyLog dailyLog = dailyLogService.getSimpleDailyLogById(Long
				.parseLong(dailyLogId));

		String createData = new SimpleDateFormat("yyyy-MM-dd").format(dailyLog
				.getDealDate());
		String reportYear = createData.substring(0, 4);
		String orgCode = organization.getId() + "";
		Integer reportMonth = Integer.parseInt(createData.substring(5, 7));
		if (reportType == 2) {// 季报
			Long dataArray[] = new Long[52];
			int quarter = getQuarter(reportMonth);
			if (orgLevle >= 5) {// 省级以上部门
				dataArray = collectQuarterDataToArray(reportYear, quarter,
						orgCode);
			} else {
				dataArray = monthDatatoQuarterData(reportYear, quarter, orgCode);
			}
			resultData = longArrayToStringArray(dataArray);
		} else if (reportType == 1) {// 月报
			resultData = longArrayToStringArray(collectDataToArray(reportYear,
					reportMonth, orgCode, 1));
		} else {
			resultData = longArrayToStringArray(collectDataToArray(reportYear,
					12, orgCode, 0));
		}

		return "success";
	}

	/**
	 * 市一级的季报是对县的月报的汇总
	 * 
	 * @param reportYear
	 * @param quarter
	 * @param orgCode
	 * @return
	 */

	public Long[] monthDatatoQuarterData(String reportYear, int quarter,
			String orgCode) throws Exception {
		Long dataArray[] = new Long[52];
		for (int month = (quarter - 1) * 3 + 1; month < quarter * 3 + 1; month++) {
			Long tempArray[] = collectDataToArray(reportYear, month, orgCode, 1);
			if (tempArray[1] == null)
				continue;
			for (int i = 0; i < 52; i++) {
				if (dataArray[i] == null)
					dataArray[i] = tempArray[i];
				else
					dataArray[i] = dataArray[i] + tempArray[i];
			}
		}
		return dataArray;
	}

	/**
	 * 将从数据库里查出来的月报中的上报数据进行汇总
	 * 
	 * @param reportYear
	 * @param reportMonth
	 * @param orgCode
	 * @return
	 */

	public Long[] collectDataToArray(String reportYear, Integer reportMonth,
			String orgCode, int type) throws Exception {
		List<SocietySafeCheck> dataList = societySafeCheckService
				.findCollectionData(1, reportYear, reportMonth, orgCode, type);
		Long dataArray[] = new Long[52];
		Iterator<SocietySafeCheck> iterator = dataList.iterator();
		while (iterator.hasNext()) {
			SocietySafeCheck societySafeCheck = iterator.next();
			String directoryName = societySafeCheck.getDirectoryNameSign();
			if (reportType == 2) {// 季報
				if (societySafeCheck.getReportType() == 1)// 月報
					directoryName = directoryName.substring(0,
							directoryName.indexOf("s") + 1)
							+ directoryNameSign.substring(directoryName
									.indexOf("s") + 1);
			}
			if (this.directoryNameSignMatch(directoryName) == false)
				continue;
			Long dataTemp[] = ObjectToArray(societySafeCheck);
			for (int i = 0; i < dataArray.length; i++) {
				if (dataArray[i] == null)
					dataArray[i] = dataTemp[i];
				else
					dataArray[i] = dataArray[i] + dataTemp[i];
			}
		}
		return dataArray;
	}

	/**
	 * 将从数据库里查出来的季报中的上报数据进行汇总
	 * 
	 * @param reportYear
	 * @param quarter
	 * @param orgCode
	 * @return
	 */

	public Long[] collectQuarterDataToArray(String reportYear, Integer quarter,
			String orgCode) throws Exception {
		List<SocietySafeCheck> dataList = societySafeCheckService
				.findQuarterData(1, reportYear, quarter, orgCode);
		Long dataArray[] = new Long[52];
		this.prepareMatch();
		Iterator<SocietySafeCheck> iterator = dataList.iterator();
		while (iterator.hasNext()) {
			SocietySafeCheck societySafeCheck = iterator.next();
			if (this.directoryNameSignMatch(societySafeCheck
					.getDirectoryNameSign()) == false)
				continue;
			Long dataTemp[] = ObjectToArray(societySafeCheck);
			for (int i = 0; i < dataArray.length; i++) {
				if (dataArray[i] == null)
					dataArray[i] = dataTemp[i];
				else
					dataArray[i] = dataArray[i] + dataTemp[i];
			}
		}
		return dataArray;
	}

	/**
	 * 将数据封装并保存一个对象
	 * 
	 * @return
	 */
	public String saveSocietySafeCheck() throws Exception {
		this.prepareExcute();
		societySafeCheck = new SocietySafeCheck();
		societySafeCheck = stringToSocietySafeCheck(societySafeCheck);
		DailyLog dailyLog = dailyLogService.getSimpleDailyLogById(Long
				.parseLong(dailyLogId));
		String createData = new SimpleDateFormat("yyyy-MM-dd").format(dailyLog
				.getDealDate());
		String reportYear = createData.substring(0, 4);
		Integer reportMonth = Integer.parseInt(createData.substring(5, 7));
		Long orgCode = -1L;
		if (null != organization.getParentOrg()) {
			orgCode = organization.getParentOrg().getId();// 存父地区编码
		}
		String reportName = dailyLog.getName();
		Long dailyDirectoryId = dailyLog.getDailyDirectory().getId();
		societySafeCheck.setOrgId(organization.getId());
		societySafeCheck.setReportName(reportName);
		societySafeCheck.setReportYear(reportYear);
		societySafeCheck.setReportMonth(reportMonth);
		societySafeCheck.setOrgCode("" + orgCode);
		societySafeCheck.setReportState(0);
		this.prepareMatch();
		societySafeCheck.setDirectoryNameSign(directoryNameSign);
		if (reportType == 2) {// 季报
			int quarter = getQuarter(reportMonth);
			societySafeCheck.setQuarter(quarter);
			societySafeCheck.setReportType(2);
			signCode = dailyDirectoryId + "direct" + reportYear + "year"
					+ quarter + "quarter" + organization.getId() + "org";
		} else if (reportType == 1) {// 月报
			societySafeCheck.setReportType(1);
			signCode = dailyDirectoryId + "direct" + reportYear + "year"
					+ createData.substring(5, 7) + "month"
					+ organization.getId() + "org";
		} else {
			societySafeCheck.setReportType(0);
			societySafeCheck.setReportMonth(12);
			signCode = dailyDirectoryId + "direct" + reportYear + "year"
					+ organization.getId() + "org";
		}
		societySafeCheck.setSignCode(signCode);
		societySafeCheckService.addSocietySafeCheck(societySafeCheck);
		return "success";
	}

	/**
	 * 根据月份返回季
	 * 
	 * @param reportMonth
	 * @return
	 */
	public int getQuarter(int reportMonth) {
		if (reportMonth < 4)
			return 1;
		else if (reportMonth > 3 && reportMonth < 7)
			return 2;
		else if (reportMonth > 6 && reportMonth < 10)
			return 3;
		else
			return 4;
	}

	/**
	 * 将ajax传回的字符串组装成对象
	 * 
	 * @return SocietySafeCheck对象
	 */
	public SocietySafeCheck stringToSocietySafeCheck(
			SocietySafeCheck societySafeCheck) throws Exception {
		if (dataCollectionArray != null) {
			String dataArray[] = dataCollectionArray.split(",");
			societySafeCheck.setDailyLogId(Long.parseLong(dailyLogId));
			societySafeCheck.setCheckEngineSum(Long.parseLong(dataArray[0]));
			societySafeCheck.setCheckEngineCadre(Long.parseLong(dataArray[1]));
			societySafeCheck.setCheckEnginePeople(Long.parseLong(dataArray[2]));
			societySafeCheck.setCheckEngineWork(Long.parseLong(dataArray[3]));
			societySafeCheck.setCheckOther(Long.parseLong(dataArray[4]));

			societySafeCheck.setPublicitySum(Long.parseLong(dataArray[5]));
			societySafeCheck.setPublicityIssueReport(Long
					.parseLong(dataArray[6]));
			societySafeCheck.setPublicityMeeting(Long.parseLong(dataArray[7]));
			societySafeCheck.setPublicityPeopleAccuse(Long
					.parseLong(dataArray[8]));
			societySafeCheck.setPublicityFerretPenal(Long
					.parseLong(dataArray[9]));
			societySafeCheck.setPublicityArrestOffender(Long
					.parseLong(dataArray[10]));

			societySafeCheck.setFindCounty(Long.parseLong(dataArray[12]));
			societySafeCheck.setFindTown(Long.parseLong(dataArray[13]));
			societySafeCheck.setFindVillage(Long.parseLong(dataArray[14]));
			societySafeCheck.setFindOther(Long.parseLong(dataArray[15]));
			societySafeCheck.setFindBlackArea(Long.parseLong(dataArray[16]));
			societySafeCheck.setFindDangerCrime(Long.parseLong(dataArray[17]));
			societySafeCheck.setFindRobAndSteal(Long.parseLong(dataArray[18]));
			societySafeCheck
					.setFindYellowBetBane(Long.parseLong(dataArray[19]));
			societySafeCheck.setFindHeresy(Long.parseLong(dataArray[20]));

			societySafeCheck.setFinishCounty(Long.parseLong(dataArray[22]));
			societySafeCheck.setFinishTown(Long.parseLong(dataArray[23]));
			societySafeCheck.setFinishVillage(Long.parseLong(dataArray[24]));
			societySafeCheck.setFinishOther(Long.parseLong(dataArray[25]));

			societySafeCheck.setJustCounty(Long.parseLong(dataArray[27]));
			societySafeCheck.setJustTown(Long.parseLong(dataArray[28]));
			societySafeCheck.setJustVillage(Long.parseLong(dataArray[29]));
			societySafeCheck.setJustOher(Long.parseLong(dataArray[30]));

			societySafeCheck.setHitFerretSum(Long.parseLong(dataArray[31]));
			societySafeCheck.setHitFerretDangerCrime(Long
					.parseLong(dataArray[32]));
			societySafeCheck.setHitFerretRobAndSteal(Long
					.parseLong(dataArray[33]));
			societySafeCheck.setHitFerretYellowBetBane(Long
					.parseLong(dataArray[34]));

			societySafeCheck.setHitArrestSum(Long.parseLong(dataArray[35]));
			societySafeCheck.setHitArrestFlowCrime(Long
					.parseLong(dataArray[36]));
			societySafeCheck.setHitArrestExternalPeople(Long
					.parseLong(dataArray[37]));
			societySafeCheck.setHitArrestNoWork(Long.parseLong(dataArray[38]));
			societySafeCheck
					.setHitArrestLiberate(Long.parseLong(dataArray[39]));
			societySafeCheck.setHitArrestYouth(Long.parseLong(dataArray[40]));

			societySafeCheck.setHitDestroySum(Long.parseLong(dataArray[41]));
			societySafeCheck.setHitDestroyOrganization(Long
					.parseLong(dataArray[42]));
			societySafeCheck.setHitDestroyForce(Long.parseLong(dataArray[43]));

			societySafeCheck.setCautionProvince(Long.parseLong(dataArray[45]));
			societySafeCheck.setCautionCity(Long.parseLong(dataArray[46]));
			societySafeCheck.setCautionCounty(Long.parseLong(dataArray[47]));

			societySafeCheck.setUrgeProvince(Long.parseLong(dataArray[49]));
			societySafeCheck.setUrgeCity(Long.parseLong(dataArray[50]));
			societySafeCheck.setUrgeCounty(Long.parseLong(dataArray[51]));

			societySafeCheck.setCreateTabUser(dataArray[52]);
		}
		return societySafeCheck;
	}

	/**
	 * 将从数据库查出的对象数据转成long类型的数组
	 * 
	 * @param societySafeCheck
	 * @return
	 */
	public Long[] ObjectToArray(SocietySafeCheck societySafeCheck)
			throws Exception {
		Long dataQuery[] = new Long[52];
		dataQuery[1] = societySafeCheck.getCheckEngineCadre();
		dataQuery[2] = societySafeCheck.getCheckEnginePeople();
		dataQuery[3] = societySafeCheck.getCheckEngineWork();
		dataQuery[4] = societySafeCheck.getCheckOther();
		dataQuery[0] = societySafeCheck.getCheckEngineSum();
		// dataQuery[0] = dataQuery[1] + dataQuery[2] + dataQuery[3]+
		// dataQuery[4];

		dataQuery[6] = societySafeCheck.getPublicityIssueReport();
		dataQuery[7] = societySafeCheck.getPublicityMeeting();
		dataQuery[8] = societySafeCheck.getPublicityPeopleAccuse();
		dataQuery[9] = societySafeCheck.getPublicityFerretPenal();
		dataQuery[10] = societySafeCheck.getPublicityArrestOffender();
		dataQuery[5] = societySafeCheck.getPublicitySum();
		// dataQuery[5] = dataQuery[6] + dataQuery[7] + dataQuery[8]+
		// dataQuery[9] + dataQuery[10];

		dataQuery[12] = societySafeCheck.getFindCounty();
		dataQuery[13] = societySafeCheck.getFindTown();
		dataQuery[14] = societySafeCheck.getFindVillage();
		dataQuery[15] = societySafeCheck.getFindOther();
		dataQuery[11] = dataQuery[12] + dataQuery[13] + dataQuery[14]
				+ dataQuery[15];
		dataQuery[16] = societySafeCheck.getFindBlackArea();
		dataQuery[17] = societySafeCheck.getFindDangerCrime();
		dataQuery[18] = societySafeCheck.getFindRobAndSteal();
		dataQuery[19] = societySafeCheck.getFindYellowBetBane();
		dataQuery[20] = societySafeCheck.getFindHeresy();

		dataQuery[22] = societySafeCheck.getFinishCounty();
		dataQuery[23] = societySafeCheck.getFinishTown();
		dataQuery[24] = societySafeCheck.getFinishVillage();
		dataQuery[25] = societySafeCheck.getFinishOther();
		dataQuery[21] = dataQuery[22] + dataQuery[23] + dataQuery[24]
				+ dataQuery[25];

		dataQuery[27] = societySafeCheck.getJustCounty();
		dataQuery[28] = societySafeCheck.getJustTown();
		dataQuery[29] = societySafeCheck.getJustVillage();
		dataQuery[30] = societySafeCheck.getJustOher();
		dataQuery[26] = dataQuery[27] + dataQuery[28] + dataQuery[29]
				+ dataQuery[30];

		dataQuery[32] = societySafeCheck.getHitFerretDangerCrime();
		dataQuery[33] = societySafeCheck.getHitFerretRobAndSteal();
		dataQuery[34] = societySafeCheck.getHitFerretYellowBetBane();
		dataQuery[31] = societySafeCheck.getHitFerretSum();
		// dataQuery[31] = dataQuery[32] + dataQuery[33] + dataQuery[34];

		dataQuery[36] = societySafeCheck.getHitArrestFlowCrime();
		dataQuery[37] = societySafeCheck.getHitArrestExternalPeople();
		dataQuery[38] = societySafeCheck.getHitArrestNoWork();
		dataQuery[39] = societySafeCheck.getHitArrestLiberate();
		dataQuery[40] = societySafeCheck.getHitArrestYouth();
		dataQuery[35] = societySafeCheck.getHitArrestSum();
		// dataQuery[35] = dataQuery[36] + dataQuery[37] + dataQuery[38]+
		// dataQuery[39] + dataQuery[40];

		dataQuery[42] = societySafeCheck.getHitDestroyOrganization();
		dataQuery[43] = societySafeCheck.getHitDestroyForce();
		dataQuery[41] = societySafeCheck.getHitDestroySum();
		// dataQuery[41] = dataQuery[42] + dataQuery[43];

		dataQuery[45] = societySafeCheck.getCautionProvince();
		dataQuery[46] = societySafeCheck.getCautionCity();
		dataQuery[47] = societySafeCheck.getCautionCounty();
		dataQuery[44] = dataQuery[45] + dataQuery[46] + dataQuery[47];

		dataQuery[49] = societySafeCheck.getUrgeProvince();
		dataQuery[50] = societySafeCheck.getUrgeCity();
		dataQuery[51] = societySafeCheck.getUrgeCounty();
		dataQuery[48] = dataQuery[49] + dataQuery[50] + dataQuery[51];
		return dataQuery;
	}

	/**
	 * 将long类型的数据转成String类型的数据
	 * 
	 * @param longArray
	 * @return
	 */
	public String[] longArrayToStringArray(Long longArray[]) throws Exception {
		String stringArray[] = new String[longArray.length];
		for (int i = 0; i < longArray.length; i++) {
			stringArray[i] = longArray[i].toString();
		}
		return stringArray;
	}

	public SocietySafeCheck getSocietySafeCheck() {

		return societySafeCheck;
	}

	public void setSocietySafeCheck(SocietySafeCheck societySafeCheck) {
		this.societySafeCheck = societySafeCheck;
	}

	public String getDailyLogId() {
		return dailyLogId;
	}

	public void setDailyLogId(String dailyLogId) {
		this.dailyLogId = dailyLogId;
	}

	public String getDataCollectionArray() {
		return dataCollectionArray;
	}

	public void setDataCollectionArray(String dataCollectionArray) {
		this.dataCollectionArray = dataCollectionArray;
	}

	public String getReportUpDate() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}

	public DailyLog getDailyLog() {
		return dailyLog;
	}

	public void setDailyLog(DailyLog dailyLog) {
		this.dailyLog = dailyLog;
	}

	public String[] getResultData() {
		return resultData;
	}

	public void setResultData(String[] resultData) {
		this.resultData = resultData;
	}

	public String getCheckUser() {
		return checkUser;
	}

	public void setCheckUser(String checkUser) {
		this.checkUser = checkUser;
	}

	public int getReportType() {
		return reportType;
	}

	public void setReportType(int reportType) {
		this.reportType = reportType;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSignCode() {
		return signCode;
	}

	public void setSignCode(String signCode) {
		this.signCode = signCode;
	}

	public Integer getOrgLevle() {
		return orgLevle;
	}

	public void setOrgLevle(Integer orgLevle) {
		this.orgLevle = orgLevle;
	}

	public DailyYear getDailyYear() {
		return dailyYear;
	}

	public void setDailyYear(DailyYear dailyYear) {
		this.dailyYear = dailyYear;
	}

	public DailyDirectory getDailyDirectory() {
		return dailyDirectory;
	}

	public void setDailyDirectory(DailyDirectory dailyDirectory) {
		this.dailyDirectory = dailyDirectory;
	}

	public PlantFormMessageConfig getPlantFormMessageConfig() {
		return plantFormMessageConfig;
	}

	public void setPlantFormMessageConfig(
			PlantFormMessageConfig plantFormMessageConfig) {
		this.plantFormMessageConfig = plantFormMessageConfig;
	}
}
