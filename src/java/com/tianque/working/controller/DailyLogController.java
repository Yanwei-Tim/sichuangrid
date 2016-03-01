package com.tianque.working.controller;

import java.io.File;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.controller.vo.ExtTreeStringIdData;
import com.tianque.controller.vo.WorkingRecordTreeData;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.StoredFile;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.IssueInspect;
import com.tianque.domain.KeyAreasOfInvestigationInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PlantFormMessageConfig;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.SignificantIssuedeal;
import com.tianque.domain.User;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.StatementsReportedType;
import com.tianque.domain.vo.IssueSubmitInfoVo;
import com.tianque.platformMessage.service.PlatformMessageService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.util.DateFormat;
import com.tianque.working.domain.DailyDirectory;
import com.tianque.working.domain.DailyLog;
import com.tianque.working.domain.DailyLogAttachFile;
import com.tianque.working.domain.WorkingRecord;
import com.tianque.working.service.DailyDirectoryService;
import com.tianque.working.service.DailyLogAttachFileService;
import com.tianque.working.service.DailyLogService;
import com.tianque.working.service.KeyAreasOfInvestigationInfoService;
import com.tianque.working.service.SignificantIssuedealService;
import com.tianque.working.service.WorkingRecordService;

@SuppressWarnings("serial")
@Controller("dailyLogController")
@Scope("prototype")
@Transactional
public class DailyLogController extends BaseAction {

	private static Logger logger = LoggerFactory
			.getLogger(DailyLogController.class);
	@Autowired
	private DailyLogService dailyLogService;
	@Autowired
	private WorkingRecordService workingRecordService;
	@Autowired
	private DailyDirectoryService dailyDirectoryService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private DailyLogAttachFileService dailyLogAttachFileService;
	@Autowired
	private PlatformMessageService platformaMessageService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private KeyAreasOfInvestigationInfoService areasOfInvestigationInfoService;
	@Autowired
	private SignificantIssuedealService issSignificantIssuedealService;
	private DailyLog dailyLog;
	private DailyDirectory dailyDirectory;
	private Long dailyYearId;
	private Long parentId;
	private int internalId;
	private Organization organization;
	private Map<String, String> issueDetailSumData = new HashMap<String, String>();
	private IssueInspect issueInspect;
	private Set<String> attachFiles;
	private DailyLogAttachFile dailyLogAttachFile;
	private final static String tmpUploadFilePath = "tmp";
	private final static Long MAX_2M_FILESIZE = 2097152L;
	private String issueDetails;
	private Date dealFrom;
	private Date dealTo;
	private Date serverDate;
	private Long parentIndate;

	private String orgid;
	private String selectMonth;
	private PlantFormMessageConfig plantFormMessageConfig;

	List<DailyDirectory> dailyTrunkDirectorys = new ArrayList<DailyDirectory>();
	private int existedCount;

	private List<ExtTreeStringIdData> extTreeDatas;

	public String findExtTreeDataByDirectoryId() throws Exception {
		extTreeDatas = new ArrayList<ExtTreeStringIdData>();
		dailyDirectory = dailyDirectoryService
				.getFullDailyDirectoryById(parentId);
		internalId = dailyDirectory.getType().getInternalId();
		if (internalId == StatementsReportedType.INVESTIGATION) {
			List<KeyAreasOfInvestigationInfo> areasList = areasOfInvestigationInfoService
					.findKeyAreasOfInvestigationInfos(ThreadVariable.getUser()
							.getOrganization().getId(), parentId);
			for (KeyAreasOfInvestigationInfo areas : areasList) {
				extTreeDatas.add(new WorkingRecordTreeData(areas));
			}
		} else if (internalId == StatementsReportedType.SIGNIFICANT_ISSUEDEAL) {
			List<SignificantIssuedeal> signsList = issSignificantIssuedealService
					.findSignificantIssuedeals(ThreadVariable.getUser()
							.getOrganization().getId(), parentId);
			for (SignificantIssuedeal signs : signsList) {
				extTreeDatas.add(new WorkingRecordTreeData(signs));
			}
		} else {
			List<WorkingRecord> workingRecords = workingRecordService
					.findWorkingRecordByDailyDirectoryId(parentId,
							ThreadVariable.getUser().getOrganization().getId());
			for (WorkingRecord workingRecord : workingRecords) {
				extTreeDatas.add(new WorkingRecordTreeData(workingRecord));
			}
		}

		return SUCCESS;
	}

	public String dispatch() throws Exception {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			return prepareAddDailyLog();
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
			return prepareUpdateDailyLog();
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			return viewDailyLog();
		} else if (DialogMode.PRINT_MODE.equalsIgnoreCase(mode)) {
			viewDailyLog();
			return "print";
		}
		return SUCCESS;
	}

	public String issueDispatch() throws Exception {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			return prepareAddissueInspect();
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
			return prepareUpdateissueInspect();
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			return viewIssue();
		} else if (DialogMode.PRINT_MODE.equalsIgnoreCase(mode)) {
			return viewIssuePrint();
		}
		return SUCCESS;
	}

	private String viewIssuePrint() throws Exception {
		String fow = "";
		organization = organizationDubboService.getSimpleOrgById(organization
				.getId());
		PropertyDict propertyDict = propertyDictService
				.getPropertyDictById(organization.getOrgLevel().getId());
		if (propertyDict == null) {
			this.errorMessage = "请配置数据库字典!";
			return ERROR;
		}
		if (propertyDict.getInternalId() == OrganizationLevel.COUNTRY) {
			fow = "print";
		} else if (propertyDict.getInternalId() == OrganizationLevel.PROVINCE) {
			fow = "print";
		} else if (propertyDict.getInternalId() == OrganizationLevel.CITY) {
			fow = "print";
		} else if (propertyDict.getInternalId() == OrganizationLevel.DISTRICT) {
			fow = "districtPrint";
		} else if (propertyDict.getInternalId() == OrganizationLevel.TOWN) {
			fow = "townPrint";
		} else if (propertyDict.getInternalId() == OrganizationLevel.VILLAGE) {
			fow = "villagePrint";
		} else {
			this.errorMessage = "您没有查询权限!";
			return ERROR;
		}
		dailyDirectory = dailyDirectoryService
				.getSimpleDailyDirectoryById(dailyDirectory.getId());
		if ("月报".equals(dailyDirectory.getShortName())) {
			issueInspect.setIssueLevel(1L);
		} else if ("季报".equals(dailyDirectory.getShortName())) {
			issueInspect.setIssueLevel(2);
		}
		if (issueInspect == null || "".equals(issueInspect.getRepdate())) {
			this.errorMessage = "参数错误!";
			return ERROR;
		}
		if (issueInspect.getSubmitState().equals("未上报")) {
			String begindate = "";
			String enddate = "";
			if (issueInspect.getIssueLevel() == 1) {
				begindate = issueInspect.getRepdate();
				enddate = issueInspect.getRepdate();
			} else if (issueInspect.getIssueLevel() == 2) {
				begindate = getMonthByQuarter(issueInspect.getRepdate(),
						getQuarterByMonth(issueInspect.getRepdate()), "s");
				enddate = getMonthByQuarter(issueInspect.getRepdate(),
						getQuarterByMonth(issueInspect.getRepdate()), "e");
			} else {
				this.errorMessage = "参数错误!";
				return ERROR;
			}
			findDetailIssueInspects(begindate, enddate,
					issueInspect.getRemark());
		} else {
			issueInspect.setIssueLevel(1L);
			findDetailIssueInspects(issueInspect);
		}
		return fow;
	}

	private String viewIssue() throws ParseException {
		String fow = "";
		organization = organizationDubboService.getSimpleOrgById(organization
				.getId());
		PropertyDict propertyDict = propertyDictService
				.getPropertyDictById(organization.getOrgLevel().getId());
		if (propertyDict == null) {
			this.errorMessage = "请配置数据库字典!";
			return ERROR;
		}
		if (propertyDict.getInternalId() == OrganizationLevel.COUNTRY) {
			fow = "success";
		} else if (propertyDict.getInternalId() == OrganizationLevel.PROVINCE) {
			fow = "success";
		} else if (propertyDict.getInternalId() == OrganizationLevel.CITY) {
			fow = "success";
		} else if (propertyDict.getInternalId() == OrganizationLevel.DISTRICT) {
			fow = "district";
		} else if (propertyDict.getInternalId() == OrganizationLevel.TOWN) {
			fow = "town";
		} else if (propertyDict.getInternalId() == OrganizationLevel.VILLAGE) {
			fow = "village";
		} else {
			this.errorMessage = "您没有查询权限!";
			return ERROR;
		}
		dailyDirectory = dailyDirectoryService
				.getSimpleDailyDirectoryById(dailyDirectory.getId());
		if ("月报".equals(dailyDirectory.getShortName())) {
			issueInspect.setIssueLevel(1L);
		} else if ("季报".equals(dailyDirectory.getShortName())) {
			issueInspect.setIssueLevel(2);
		}
		if (issueInspect == null || "".equals(issueInspect.getRepdate())) {
			this.errorMessage = "参数错误!";
			return ERROR;
		}
		if (issueInspect.getSubmitState().equals("未上报")) {
			String begindate = "";
			String enddate = "";
			if (issueInspect.getIssueLevel() == 1) {
				begindate = issueInspect.getRepdate();
				enddate = issueInspect.getRepdate();
			} else if (issueInspect.getIssueLevel() == 2) {
				begindate = getMonthByQuarter(issueInspect.getRepdate(),
						getQuarterByMonth(issueInspect.getRepdate()), "s");
				enddate = getMonthByQuarter(issueInspect.getRepdate(),
						getQuarterByMonth(issueInspect.getRepdate()), "e");
			} else {
				this.errorMessage = "参数错误!";
				return ERROR;
			}
			findDetailIssueInspects(begindate, enddate,
					issueInspect.getRemark());
		} else {
			issueInspect.setIssueLevel(1L);
			findDetailIssueInspects(issueInspect);
		}
		return fow;
	}

	public String getDailyLogById() throws Exception {
		if (dailyLog == null || dailyLog.getId() == null) {
			this.errorMessage = "参数错误!";
			return ERROR;
		}

		getDailyLogAndFillAttachFileById(dailyLog.getId());
		return SUCCESS;
	}

	private void getDailyLogAndFillAttachFileById(Long id) {
		dailyLog = dailyLogService.getSimpleDailyLogById(id);
		dailyLog.setDailyLogAttachFile(dailyLogAttachFileService
				.getSimpleDailyLogAttachFileByDailyLogId(id));
	}

	private String viewDailyLog() {
		if (dailyLog == null || dailyLog.getId() == null) {
			this.errorMessage = "参数错误!";
			return ERROR;
		}
		if (dailyLog.getId() != null) {

			getDailyLogAndFillAttachFileById(dailyLog.getId());
		}
		if (dailyLog != null && dailyLog.getDailyDirectory() != null
				&& dailyLog.getDailyDirectory().getId() != null) {
			dailyDirectory = dailyDirectoryService
					.getSimpleDailyDirectoryById(dailyLog.getDailyDirectory()
							.getId());
			dailyLog.setDailyDirectory(dailyDirectory);
			return prepareSubItem(dailyDirectory);
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "updateDailyLog")
	private String prepareUpdateDailyLog() throws Exception {
		serverDate = new Date();
		if (dailyLog == null || dailyLog.getId() == null) {
			this.errorMessage = "参数错误!";
			return ERROR;
		}
		dailyLog = dailyLogService.getSimpleDailyLogById(dailyLog.getId());

		if (dailyLog != null && dailyLog.getDailyDirectory() != null
				&& dailyLog.getDailyDirectory().getId() != null) {
			dailyDirectory = dailyDirectoryService
					.getSimpleDailyDirectoryById(dailyLog.getDailyDirectory()
							.getId());
			dailyLog.setDailyDirectory(dailyDirectory);
			dailyLog.setDailyLogAttachFile(dailyLogAttachFileService
					.getSimpleDailyLogAttachFileByDailyLogId(dailyLog.getId()));
			if (dailyDirectory.getParentDailyDirectory() != null) {
				DailyDirectory parentDailyDirectory = dailyDirectoryService
						.getSimpleDailyDirectoryById(dailyDirectory
								.getParentDailyDirectory().getId());
				parentIndate = parentDailyDirectory.getIndate();
			}
			return prepareSubItem(dailyDirectory);
		}
		return SUCCESS;
	}

	public String saveIssueDetails() throws Exception {
		long id = issueInspect.getId();
		if (id == 0) {
			this.errorMessage = "参数错误!";
			return ERROR;
		}
		long substate = 0;
		if ("未上报".equals(issueInspect.getSubmitState())) {
			substate = 0;
		} else if ("已编制".equals(issueInspect.getSubmitState())) {
			substate = 1;
		} else {
			substate = 2;
		}
		dailyLogService.saveIssueDetail(generateDetaislMap(issueDetails, id),
				id, substate, issueInspect.getAuditPerson(),
				issueInspect.getCreaterepPerson());
		return SUCCESS;
	}

	private int getQuarterByMonth(String d) throws ParseException {
		int quarter = 0;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
		Date date = df.parse(d);
		int month = Integer.valueOf(new SimpleDateFormat("MM").format(date));
		if (month <= 3) {
			quarter = 1;
		} else if (month <= 6) {
			quarter = 2;
		} else if (month <= 9) {
			quarter = 3;
		} else {
			quarter = 4;
		}
		return quarter;
	}

	private String getMonthByQuarter(String date, int quarter, String type) {
		String genmonth = "";
		String year = date.substring(0, 4);
		if (quarter == 1 && "s".equals(type)) {
			genmonth = year + "-01";
		} else if (quarter == 1 && "e".equals(type)) {
			genmonth = year + "-03";
		} else if (quarter == 2 && "s".equals(type)) {
			genmonth = year + "-04";
		} else if (quarter == 2 && "e".equals(type)) {
			genmonth = year + "-06";
		} else if (quarter == 3 && "s".equals(type)) {
			genmonth = year + "-07";
		} else if (quarter == 3 && "e".equals(type)) {
			genmonth = year + "-09";
		} else if (quarter == 4 && "s".equals(type)) {
			genmonth = year + "-10";
		} else if (quarter == 4 && "e".equals(type)) {
			genmonth = year + "-12";
		}
		return genmonth;
	}

	public String addIssueInspect() throws Exception {
		organization = organizationDubboService.getSimpleOrgById(organization
				.getId());

		PropertyDict orgLevelDict = propertyDictService
				.getPropertyDictById(organization.getOrgLevel().getId());

		if ((orgLevelDict.getInternalId() != OrganizationLevel.TOWN && orgLevelDict
				.getInternalId() != OrganizationLevel.VILLAGE)
				&& issueInspect.getIssueLevel() == 1) {
			if (countUnderLineAreaNotSubmit(issueInspect) > 0) {
				this.errorMessage = "还有"
						+ countUnderLineAreaNotSubmit(issueInspect)
						+ "个辖区单位没有上报，具体单位请参考辖区上报情况！";
				return ERROR;
			}
		}
		issueInspect.setDailyDirectoryId(dailyDirectory.getId());
		issueInspect = dailyLogService.addIssueInspect(issueInspect);
		return SUCCESS;
	}

	private Integer countUnderLineAreaNotSubmit(IssueInspect issueInspect) {
		issueInspect.setCreaterepUnion(ThreadVariable.getUser()
				.getOrgInternalCode());
		return dailyLogService.countUnderLineAreaNotSubmit(issueInspect);
	}

	public String updateIssueInspect() throws Exception {
		dailyLogService.updateIssueInspect(issueInspect);
		return SUCCESS;
	}

	@SuppressWarnings("deprecation")
	public String issueInspectBack() throws Exception {
		if (dailyLog == null) {
			this.errorMessage = "参数错误!";
			return ERROR;
		}
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
		dailyLogService.updateIssueStateByID(dailyLog.getId(), 1);
		List<User> users = permissionService
				.findChildUsersByEnameAndInternalCode("addDailyLog",
						organization.getOrgInternalCode());
		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);
			platformaMessageService.sendPlatformMessageToUser(user.getId(),
					plantFormMessageConfig.getMsgContent(),
					plantFormMessageConfig.getMsgTitle());
		}
		return SUCCESS;
	}

	@SuppressWarnings("deprecation")
	public String significantIssueBack() throws Exception {
		if (dailyLog == null) {
			this.errorMessage = "参数错误!";
			return ERROR;
		}
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
		dailyLogService.updateIssueStateByID(dailyLog.getId(), 1);
		List<User> users = permissionService
				.findChildUsersByEnameAndInternalCode("addDailyLog",
						organization.getOrgInternalCode());
		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);
			platformaMessageService.sendPlatformMessageToUser(user.getId(),
					plantFormMessageConfig.getMsgContent(),
					plantFormMessageConfig.getMsgTitle());
		}
		return SUCCESS;
	}

	private List generateDetaislMap(String issueDetails, long id) {
		List<Map> ls = new ArrayList<Map>();
		String[] details = issueDetails.split("&");
		for (int i = 0; i < details.length; i++) {
			String detail = details[i];
			JSONObject jsonObject = JSONObject.fromObject(detail);
			Set jset = jsonObject.entrySet();
			Map<String, String> map = new HashMap<String, String>();
			for (Iterator it = jset.iterator(); it.hasNext();) {
				Map.Entry<String, String> ent = (Map.Entry<String, String>) it
						.next();
				String fkey = ent.getKey();
				String typeValue = fkey.substring(2, fkey.indexOf("_", 2));
				String key = "c_" + fkey.substring(fkey.indexOf("_", 2) + 1);
				map.put("issueid", String.valueOf(id));
				map.put("issuetype", typeValue);
				map.put(key, ent.getValue());
			}
			ls.add(map);
		}
		return ls;
	}

	private String prepareAddissueInspect() throws Exception {
		dailyDirectory = dailyDirectoryService
				.getSimpleDailyDirectoryById(dailyDirectory.getId());
		issueInspect = new IssueInspect();
		if ("月报".equals(dailyDirectory.getShortName())) {
			issueInspect.setIssueLevel(1L);
		} else if ("季报".equals(dailyDirectory.getShortName())) {
			issueInspect.setIssueLevel(2);
		}
		return "issuesuccess";
	}

	private String prepareUpdateissueInspect() throws Exception {
		return "issueeditsuccess";
	}

	@PermissionFilter(ename = "addDailyLog")
	private String prepareAddDailyLog() throws Exception {
		serverDate = new Date();
		if (dailyLog == null) {
			dailyLog = new DailyLog();
		}
		if (dailyDirectory != null && dailyDirectory.getId() != null) {
			dailyDirectory = dailyDirectoryService
					.getSimpleDailyDirectoryById(dailyDirectory.getId());

			dailyLog.setDailyYear(dailyDirectory.getDailyYear());
			dailyLog.setDailyDirectory(dailyDirectory);
			if (dailyDirectory.getParentDailyDirectory() != null) {
				DailyDirectory parentDailyDirectory = dailyDirectoryService
						.getSimpleDailyDirectoryById(dailyDirectory
								.getParentDailyDirectory().getId());
				parentIndate = parentDailyDirectory.getIndate();
			}
			return prepareSubItem(dailyDirectory);
		}
		return SUCCESS;
	}

	private String prepareSubItem(DailyDirectory dailyDirectory) {
		if (dailyDirectory.getType() != null) {
			PropertyDict propertyDict = propertyDictService
					.getPropertyDictById(dailyDirectory.getType().getId());
			return FileType.valueOf(propertyDict.getDisplayName()).getEName();
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "dailyLog")
	public String findDailyLogs() throws Exception {
		if (dailyDirectory == null || dailyDirectory.getId() == null) {
			gridPage = new GridPage(new PageInfo<DailyLog>());
			return SUCCESS;
		}
		PageInfo<DailyLog> pageInfo = dailyLogService
				.findDailyLogByDailyDirectoryId(dailyDirectory.getId(),
						organization.getId(), page, rows, sidx, sord);
		appendAttachFile(pageInfo);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	@PermissionFilter(ename = "dailyLog")
	public String findDailyLogsByParentId() throws Exception {
		if (dailyDirectory == null || dailyDirectory.getId() == null) {
			gridPage = new GridPage(new PageInfo<DailyLog>());
			return SUCCESS;
		}

		PageInfo<DailyLog> pageInfo = dailyLogService
				.findDailyLogByParentDailyDirectoryId(dailyDirectory.getId(),
						organization.getId(), page, rows, sidx, sord);
		appendAttachFile(pageInfo);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	public String findIssueInspectsByParentId() throws Exception {
		if (dailyDirectory == null || dailyDirectory.getId() == null) {
			gridPage = new GridPage(new PageInfo<IssueInspect>());
			return SUCCESS;
		}
		PageInfo<IssueInspect> pageInfo = dailyLogService
				.findIssueInspectsByParentId(dailyDirectory.getId(),
						organization.getId(), page, rows);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	public String findIssueInspects() throws Exception {
		dailyDirectory = dailyDirectoryService
				.getSimpleDailyDirectoryById(dailyDirectory.getId());
		issueInspect = new IssueInspect();
		if ("月报".equals(dailyDirectory.getShortName())) {
			issueInspect.setIssueLevel(1L);
		} else if ("季报".equals(dailyDirectory.getShortName())) {
			issueInspect.setIssueLevel(2);
		}
		issueInspect.setDailyDirectoryId(dailyDirectory.getId());
		issueInspect.setCreaterepUnion(organization.getId().toString());
		if (!"".equals(issueInspect.getRepdate())) {
			PageInfo<IssueInspect> pageInfo = dailyLogService.findIssueInspect(
					page, rows, issueInspect);
			gridPage = new GridPage(pageInfo);
		}
		return SUCCESS;
	}

	public String findIssue() throws Exception {
		dailyDirectory = dailyDirectoryService
				.getSimpleDailyDirectoryById(dailyDirectory.getId());
		issueInspect = new IssueInspect();
		if ("月报".equals(dailyDirectory.getShortName())) {
			issueInspect.setIssueLevel(1L);
		} else if ("季报".equals(dailyDirectory.getShortName())) {
			issueInspect.setIssueLevel(2);
		}
		issueInspect.setDailyDirectoryId(dailyDirectory.getId());
		issueInspect.setCreaterepUnion(organization.getId().toString());
		if (!"".equals(issueInspect.getRepdate())) {
			PageInfo<IssueInspect> pageInfo = dailyLogService.findIssueInspect(
					page, rows, issueInspect);
			gridPage = new GridPage(pageInfo);
		}
		return SUCCESS;
	}

	public String findIssueSubmitInfo() throws Exception {
		String orgcode = ThreadVariable.getUser().getOrgInternalCode();
		PageInfo<IssueSubmitInfoVo> pageInfo = dailyLogService
				.findIssueSubmitInfo(page, rows, orgcode, dailyLog
						.getDailyYear().getName());
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	public String findIssueInspectByDate() throws Exception {
		dailyDirectory = dailyDirectoryService
				.getSimpleDailyDirectoryById(dailyDirectory.getId());
		issueInspect = new IssueInspect();
		if (dailyDirectory.getParentDailyDirectory() != null) {
			if ("月报".equals(dailyDirectory.getShortName())) {
				issueInspect.setIssueLevel(1L);
			} else if ("季报".equals(dailyDirectory.getShortName())) {
				issueInspect.setIssueLevel(2L);
			}
		}
		if (dealFrom != null && null != dealTo) {
			String from = DateFormat.toString(dealFrom, "yyyy-MM");
			String to = DateFormat.toString(dealTo, "yyyy-MM");
			PageInfo<IssueInspect> pageInfo = dailyLogService
					.findIssueInspectByDate(page, rows, issueInspect,
							organization, from, to);
			gridPage = new GridPage(pageInfo);
		}
		return SUCCESS;
	}

	public Map<String, String> findDetailIssueInspects(String begindate,
			String enddate, String unioncode) {
		List<IssueInspect> issues = null;
		if (issueInspect.getIssueLevel() == 1) {
			issues = dailyLogService.findDetailIssueInspect(begindate, enddate,
					unioncode);
		} else if (issueInspect.getIssueLevel() == 2) {
			issues = dailyLogService.findDetailIssueInspectForQuarter(
					begindate, enddate, unioncode);
		}
		issueDetailSumData.putAll(generateSumById(issues));
		issueDetailSumData
				.putAll(generateIssueSum(issueDetailSumData, "bydate"));
		issueDetailSumData.putAll(generateSumByIssueType(issueDetailSumData));
		issueDetailSumData.putAll(genrateSumRate(issueDetailSumData));
		return issueDetailSumData;
	}

	public Map<String, String> findDetailIssueInspects(IssueInspect issueInspect) {
		try {
			List<Map> issues = dailyLogService
					.findDetailIssueInspectByID(issueInspect);
			issueDetailSumData.putAll(generateSumById(issues));
			issueDetailSumData.putAll(generateIssueSum(issueDetailSumData,
					"byid"));
			issueDetailSumData
					.putAll(generateSumByIssueType(issueDetailSumData));
			issueDetailSumData.putAll(genrateSumRate(issueDetailSumData));
		} catch (Exception e) {
			logger.error("findDetailIssueInspects 错误", e);
		}
		return null;
	}

	public Map<String, String> generateSumById(List ls) {
		Map<String, String> data = new HashMap<String, String>();
		for (Iterator it = ls.iterator(); it.hasNext();) {
			Map map = (Map) it.next();
			Set dataSet = map.entrySet();
			for (Iterator sit = dataSet.iterator(); sit.hasNext();) {
				Map.Entry<String, String> en = (Map.Entry<String, String>) sit
						.next();
				String fkey = en.getKey();
				if (fkey.indexOf("c_") > -1) {
					String key = fkey.substring(0, fkey.indexOf("_")) + "_"
							+ map.get("issuetype")
							+ fkey.substring(fkey.indexOf("_"));
					data.put(key,
							String.valueOf(en.getValue()).equals("0") ? ""
									: String.valueOf(en.getValue()));
				}
			}
		}
		return data;
	}

	private Map<String, String> generateSumByIssueType(
			Map<String, String> issDetail) {
		Set sumSet = issDetail.entrySet();
		Map<String, String> map = new HashMap<String, String>();
		for (Iterator it = sumSet.iterator(); it.hasNext();) {
			Map.Entry<String, String> ent = (Map.Entry<String, String>) it
					.next();
			if (ent.getKey().indexOf("_100_") > -1)
				continue;
			String subKey = ent.getKey().substring(0,
					ent.getKey().indexOf("_", 2));
			String key = subKey + "_99_"
					+ ent.getKey().substring(ent.getKey().lastIndexOf("_") + 1);
			int value = "".equals(ent.getValue()) ? 0 : Integer.parseInt(ent
					.getValue());
			int filValue = map.get(key) == null || map.get(key).equals("") ? 0
					: Integer.parseInt(map.get(key));
			if (map.containsKey(key)) {
				map.put(key, (value + filValue) == 0 ? "" : (value + filValue)
						+ "");
			} else {
				map.put(key, value == 0 ? "" : value + "");
			}
		}
		return map;
	}

	private Map<String, String> generateIssueSum(Map<String, String> issDetail,
			String type) {
		Map<String, String> issuedata = new HashMap<String, String>();
		Set issSet = issDetail.entrySet();
		for (Iterator it = issSet.iterator(); it.hasNext();) {
			Map.Entry<String, String> ent = (Map.Entry<String, String>) it
					.next();
			if (ent.getKey().indexOf("_0_") == -1
					&& ent.getKey().indexOf("finish") > -1)
				continue;
			String key = "c_0"
					+ ent.getKey().substring(ent.getKey().indexOf("_", 2));
			int value = "".equals(ent.getValue()) ? 0 : Integer.parseInt(ent
					.getValue());
			boolean s = issuedata.get(key) == null
					|| issuedata.get(key).equals("");
			int filValue = s ? 0 : Integer.parseInt(issuedata.get(key));

			if (issuedata.containsKey(key)) {

				issuedata.put(key, (value + filValue) == 0 ? ""
						: (value + filValue) + "");
			} else {
				issuedata.put(key, value == 0 ? "" : value + "");
			}
		}
		return issuedata;
	}

	private Map<String, String> genrateSumRate(Map<String, String> sumdata) {
		Map<String, String> rateData = new HashMap<String, String>();
		rateData.putAll(getSumRateData(sumdata, "c_0_12_issuecount",
				"c_0_12_dealcount", "c_0_12_dealrate"));
		rateData.putAll(getSumRateData(sumdata, "c_0_10_issuecount",
				"c_0_10_dealcount", "c_0_10_dealrate"));
		rateData.putAll(getSumRateData(sumdata, "c_0_8_issuecount",
				"c_0_8_dealcount", "c_0_8_dealrate"));
		rateData.putAll(getSumRateData(sumdata, "c_0_6_issuecount",
				"c_0_6_dealcount", "c_0_6_dealrate"));
		rateData.putAll(getSumRateData(sumdata, "c_0_12_issuecount",
				"c_0_12_finishcount", "c_0_12_finishrate"));
		rateData.putAll(getSumRateData(sumdata, "c_0_10_issuecount",
				"c_0_10_finishcount", "c_0_10_finishrate"));
		rateData.putAll(getSumRateData(sumdata, "c_0_8_issuecount",
				"c_0_8_finishcount", "c_0_8_finishrate"));
		rateData.putAll(getSumRateData(sumdata, "c_0_6_issuecount",
				"c_0_6_finishcount", "c_0_6_finishrate"));
		rateData.putAll(getSumRateData(sumdata, "c_0_99_issuecount",
				"c_0_99_finishcount", "c_0_99_finishrate"));
		rateData.putAll(getSumRateData(sumdata, "c_0_99_issuecount",
				"c_0_99_dealcount", "c_0_99_dealrate"));
		rateData.putAll(getSumRateData(sumdata, "c_0_100_issuecount",
				"c_0_100_dealcount", "c_0_100_dealrate"));
		rateData.putAll(getSumRateData(sumdata, "c_0_100_issuecount",
				"c_0_100_finishcount", "c_0_100_finishrate"));
		rateData.putAll(getSumRateData(sumdata, "c_429_100_issuecount",
				"c_429_100_finishcount", "c_429_100_finishrate"));
		rateData.putAll(getSumRateData(sumdata, "c_429_99_issuecount",
				"c_429_99_finishcount", "c_429_99_finishrate"));
		rateData.putAll(getSumRateData(sumdata, "c_429_6_issuecount",
				"c_429_6_finishcount", "c_429_6_finishrate"));
		rateData.putAll(getSumRateData(sumdata, "c_429_8_issuecount",
				"c_429_8_finishcount", "c_429_8_finishrate"));
		rateData.putAll(getSumRateData(sumdata, "c_429_10_issuecount",
				"c_429_10_finishcount", "c_429_10_finishrate"));
		rateData.putAll(getSumRateData(sumdata, "c_429_12_issuecount",
				"c_429_12_finishcount", "c_429_12_finishrate"));
		return rateData;
	}

	private Map<String, String> getSumRateData(Map<String, String> sumdata,
			String issuekey, String ratekey, String key) {
		Map<String, String> map = new HashMap<String, String>();
		DecimalFormat df = new DecimalFormat("#.00");
		if (getSumCount(sumdata, issuekey) != 0) {
			double dealvRate = getSumCount(sumdata, ratekey) == 0 ? 0
					: getSumCount(sumdata, ratekey)
							/ getSumCount(sumdata, issuekey) * 100;
			String fFealRate = df.format(dealvRate);
			if (dealvRate != 0)
				map.put(key, fFealRate.replace(".00", "") + "%");
		}
		return map;
	}

	private double getSumCount(Map<String, String> sumdata, String key) {
		double value = sumdata.get(key) == null || sumdata.get(key).equals("") ? 0
				: Integer.parseInt(sumdata.get(key));
		return value;
	}

	private void appendAttachFile(PageInfo<DailyLog> pageInfo) {
		for (DailyLog dailyLog : pageInfo.getResult()) {
			dailyLog.setDailyLogAttachFile(dailyLogAttachFileService
					.getSimpleDailyLogAttachFileByDailyLogId(dailyLog.getId()));
		}
	}

	public String addDailyLogWarmMessage() throws Exception {
		String[] subInfos = orgid.split("@");
		for (String subInfo : subInfos) {
			if ("".equals(subInfo))
				continue;
			JSONObject jsonObject = JSONObject.fromObject(subInfo);
			Object orgid = jsonObject.get("id");
			Object month = jsonObject.get("month");
			if (orgid == null || month == null) {
				return ERROR;
			}
			List<User> users = permissionService
					.findChildUsersByEnameAndOrgCode("addDailyLog",
							orgid.toString());
			addWarnMessageByUsers(month, users);
		}
		return SUCCESS;
	}

	@SuppressWarnings("deprecation")
	private void addWarnMessageByUsers(Object month, List<User> users) {
		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);
			String warnTitle = month + "月份矛盾纠纷排查消息提醒";
			String warnMessage = "您还没有上报矛盾纠纷排查月报，请您及时完成相关矛盾纠纷排查月报工作";
			if (dailyDirectory != null
					&& dailyDirectory.getType().getInternalId() == StatementsReportedType.CHECK) {
				warnTitle = month + "月份治安重点整治消息提醒";
				warnMessage = "您还没有上报治安重点整治月报，请您及时完成相关治安重点整治月报工作";
			}
			platformaMessageService.sendPlatformMessageToUser(user.getId(),
					warnMessage, warnTitle);
		}
	}

	private void loadTypeDailyDirectory(DailyDirectory dailyDirectory) {
		if (dailyDirectory != null)
			dailyDirectory.setType(propertyDictService
					.getPropertyDictById(dailyDirectory.getType().getId()));
	}

	public String deleteDailyLogAttachFile() throws Exception {
		if (dailyLogAttachFile == null || dailyLogAttachFile.getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		dailyLogAttachFileService
				.deleteDailyLogAttachFileById(dailyLogAttachFile.getId());
		return SUCCESS;
	}

	public String deleteIssueInspect() throws Exception {
		if (issueInspect == null || issueInspect.getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		try {
			dailyLogService.deleteIssueInspect(issueInspect.getId());
		} catch (Exception e) {
			logger.error("deleteIssueInspect 错误", e);
			return ERROR;
		}
		return SUCCESS;
	}

	public String downloadDailyLogAttachFile() throws Exception {
		if (dailyLogAttachFile == null || dailyLogAttachFile.getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		dailyLogAttachFile = dailyLogAttachFileService
				.getSimpleDailyLogAttachFileById(dailyLogAttachFile.getId());
		if (dailyLogAttachFile == null) {
			this.errorMessage = "未找到对应的附件";
			return ERROR;
		}
		inputStream = new java.io.FileInputStream(FileUtil.getWebRoot()
				+ File.separator + dailyLogAttachFile.getFileActualUrl());
		downloadFileName = new String(dailyLogAttachFile.getFileName()
				.getBytes("gbk"), "ISO8859-1");
		return SUCCESS;
	}

	@PermissionFilter(ename = "addDailyLog")
	public String addDailyLog() throws Exception {
		dailyLog.setOrganization(ThreadVariable.getUser().getOrganization());
		dailyLog.setOrgInternalCode(ThreadVariable.getUser()
				.getOrgInternalCode());
		DailyDirectory directory = dailyDirectoryService
				.getFullDailyDirectoryById(dailyLog.getDailyDirectory().getId());
		if (directory.getType().getInternalId() == StatementsReportedType.CHECK) {
			if (countUnderLineAreaNotSubmit(dailyLog) > 0) {
				this.errorMessage = "还有"
						+ countUnderLineAreaNotSubmit(dailyLog)
						+ "个辖区单位没有上报，具体单位请参考辖区上报情况！";
				return ERROR;
			}
		}

		if (!validateDailyLog()) {
			return ERROR;
		}

		dailyLog = dailyLogService.addDailyLog(dailyLog);
		if (!addDailyLogAttachFile(dailyLog)) {
			return ERROR;
		}
		return SUCCESS;
	}

	public Integer countUnderLineAreaNotSubmit(DailyLog dailyLog) {
		Integer areaCount = 0;
		if (dailyLog.getDailyDirectory().getShortName().equals("季报"))
			return areaCount;
		organization = organizationDubboService.getSimpleOrgById(organization
				.getId());
		PropertyDict propertyDict = propertyDictService
				.getPropertyDictById(organization.getOrgLevel().getId());
		if ((propertyDict.getInternalId() != OrganizationLevel.TOWN && propertyDict
				.getInternalId() != OrganizationLevel.VILLAGE)) {
			areaCount = dailyLogService.countUnderLineAreaNotSubmit(dailyLog);
		}
		return areaCount;
	}

	private boolean addDailyLogAttachFile(DailyLog dailyLog) {
		if (attachFiles == null || attachFiles.size() == 0) {
			return true;
		}
		for (String fileName : attachFiles) {
			if (!addAttachFile(dailyLog, fileName))
				return false;
		}
		return true;
	}

	private boolean addAttachFile(DailyLog dailyLog, String fileName) {
		DailyLogAttachFile dailyLogAttachFile = new DailyLogAttachFile();
		dailyLogAttachFile.setDailyLog(dailyLog);
		StoredFile storedFile = null;
		try {
			storedFile = FileUtil.copyTmpFileToStoredFile(fileName,
					GridProperties.DAILYLOG_PATH);
		} catch (Exception e) {
			logger.error("addAttachFile 错误", e);
		}
		dailyLogAttachFile.setFileSize(storedFile.getFileSize());
		dailyLogAttachFile.setFileActualUrl(storedFile.getStoredFilePath()
				+ File.separator + storedFile.getStoredFileName());
		dailyLogAttachFile.setFileName(fileName);
		dailyLogAttachFile.setDailyYear(dailyLog.getDailyYear());
		dailyLog.getDailyLogAttachFile().add(
				dailyLogAttachFileService
						.addDailyLogAttachFile(dailyLogAttachFile));
		return true;
	}

	private Long getTmpUploadFileSize() {
		Long result = 0L;
		if (attachFiles == null)
			return result;
		File file = null;
		for (String fileName : attachFiles) {
			file = getUploadFile(fileName);
			if (file != null)
				result = result + file.length();
		}
		return result;
	}

	private boolean validateFileSize(Long dailyLogId, Long curFileSize) {
		if (dailyLogId == null)
			return true;
		Long totalSize = dailyLogAttachFileService
				.sumAllFileSizeByDailyLogId(dailyLogId);

		if (totalSize != null && MAX_2M_FILESIZE < totalSize + curFileSize) {
			this.errorMessage = "总共文件不能超过2M!";
			return false;
		}

		return true;
	}

	private File getUploadFile(String uploadFileName) {
		String storedFilePath = tmpUploadFilePath + File.separator
				+ ThreadVariable.getUser().getId();
		File storedFile = new File(FileUtil.getWebRoot() + File.separator
				+ storedFilePath + File.separator + uploadFileName);
		if (storedFile.exists())
			return storedFile;
		else
			return null;
	}

	@PermissionFilter(ename = "updateDailyLog")
	public String updateDailyLog() throws Exception {
		if (!validateDailyLog())
			return ERROR;
		if (!validateFileSize(dailyLog.getId(), getTmpUploadFileSize()))
			return ERROR;
		dailyLog = dailyLogService.updateDailyLog(dailyLog);
		if (!margeAttachFiles(dailyLog))
			return ERROR;
		dailyLog.setDailyLogAttachFile(dailyLogAttachFileService
				.getSimpleDailyLogAttachFileByDailyLogId(dailyLog.getId()));
		return SUCCESS;
	}

	private boolean margeAttachFiles(DailyLog dailyLog) {
		if (attachFiles == null || attachFiles.size() == 0)
			return true;
		List<DailyLogAttachFile> dailyLogAttachFiles = dailyLogAttachFileService
				.getSimpleDailyLogAttachFileByDailyLogId(dailyLog.getId());
		List<String> dailyLogAttachFileName = new ArrayList<String>();
		for (DailyLogAttachFile dailyLogAttachFile : dailyLogAttachFiles) {
			dailyLogAttachFileName.add(dailyLogAttachFile.getFileName());
		}
		for (String fileName : attachFiles) {
			if (!dailyLogAttachFileName.contains(fileName))
				if (!addAttachFile(dailyLog, fileName))
					return false;
		}
		return true;
	}

	@PermissionFilter(ename = "deleteDailyLog")
	public String deleteDailyLog() throws Exception {
		if (dailyLog == null || dailyLog.getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		dailyLogAttachFileService.deleteDailyLogAttachFileByDailyLogId(dailyLog
				.getId());
		dailyLogService.deleteDailyLogById(dailyLog.getId());
		return SUCCESS;
	}

	public String filterDailyLog() throws Exception {
		if (dailyDirectory == null
				|| (dailyDirectory.getId() == null && dailyDirectory
						.getParentDailyDirectory() == null)) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		PageInfo<DailyLog> pageInfo = new PageInfo<DailyLog>();
		if (dailyDirectory.getId() != null) {
			pageInfo = dailyLogService.filterDailyLogByDate(
					dailyDirectory.getId(), organization.getId(), dealFrom,
					dealTo, page, rows, sidx, sord);
		} else if (dailyDirectory.getParentDailyDirectory().getId() != null) {
			pageInfo = dailyLogService
					.filterDailyLogByDateAndDailyDirectoryParentId(
							dailyDirectory.getParentDailyDirectory().getId(),
							organization.getId(), dealFrom, dealTo, page, rows,
							sidx, sord);
		}

		appendAttachFile(pageInfo);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	public String dailyLogMenu() throws Exception {
		dailyTrunkDirectorys = dailyDirectoryService
				.findDailyAllTrunkDirectory();

		return SUCCESS;
	}

	public String dailyLogForPage() throws Exception {
		organization = ThreadVariable.getUser().getOrganization();
		if (dailyYearId != null)
			dailyTrunkDirectorys = dailyDirectoryService
					.findDailyDirectoryByDailyYearId(dailyYearId);
		for (DailyDirectory dailyDirectory : dailyTrunkDirectorys) {
			loadTypeDailyDirectory(dailyDirectory);
		}
		return SUCCESS;
	}

	public String countExsistedDailyLogByDirectoryId() throws Exception {
		if (dailyDirectory == null || dailyDirectory.getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		existedCount = dailyLogService
				.countExsistedDailyLogByDirectoryId(dailyDirectory.getId());

		return SUCCESS;
	}

	private boolean validateDailyLog() throws Exception {
		if (dailyLog == null) {
			this.errorMessage = "参数错误";
			return false;
		}
		if (dailyLog.getName() == null || "".equals(dailyLog.getName())) {
			this.errorMessage = "台帐名称不能为空！";
			return false;
		}
		if (dailyLog.getTheme() == null || "".equals(dailyLog.getTheme())) {
			this.errorMessage = "台帐主题不能为空！";
			return false;
		}
		if (dailyLog.getDailyDirectory() == null) {
			this.errorMessage = "台帐目录不能为空！";
			return false;
		}
		if (dailyLog.getDealDate() == null) {
			this.errorMessage = "台帐时间不能为空！";
			return false;
		}
		if (dailyLog.getDailyYear() == null) {
			this.errorMessage = "年度台帐目录不能为空！";
			return false;
		}
		if (getTmpUploadFileSize() > MAX_2M_FILESIZE) {
			this.errorMessage = "总共文件不能超过2M!";
			return false;
		}

		return true;
	}

	public DailyLog getDailyLog() {
		return dailyLog;
	}

	public void setDailyLog(DailyLog dailyLog) {
		this.dailyLog = dailyLog;
	}

	public DailyDirectory getDailyDirectory() {
		return dailyDirectory;
	}

	public void setDailyDirectory(DailyDirectory dailyDirectory) {
		this.dailyDirectory = dailyDirectory;
	}

	public List<DailyDirectory> getDailyTrunkDirectorys() {
		return dailyTrunkDirectorys;
	}

	public void setDailyTrunkDirectorys(
			List<DailyDirectory> dailyTrunkDirectorys) {
		this.dailyTrunkDirectorys = dailyTrunkDirectorys;
	}

	public Long getDailyYearId() {
		return dailyYearId;
	}

	public void setDailyYearId(Long dailyYearId) {
		this.dailyYearId = dailyYearId;
	}

	public Set<String> getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(Set<String> attachFiles) {
		this.attachFiles = attachFiles;
	}

	public DailyLogAttachFile getDailyLogAttachFile() {
		return dailyLogAttachFile;
	}

	public void setDailyLogAttachFile(DailyLogAttachFile dailyLogAttachFile) {
		this.dailyLogAttachFile = dailyLogAttachFile;
	}

	public Date getDealFrom() {
		return dealFrom;
	}

	public void setDealFrom(Date dealFrom) {
		this.dealFrom = dealFrom;
	}

	public Date getDealTo() {
		return dealTo;
	}

	public void setDealTo(Date dealTo) {
		this.dealTo = dealTo;
	}

	public Map<String, String> getIssueDetailSumData() {
		return issueDetailSumData;
	}

	public void setIssueDetailSumData(Map<String, String> issueDetailSumData) {
		this.issueDetailSumData = issueDetailSumData;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	private enum FileType {
		文件类("file"), 会议类("meet"), 活动类("activity"), 治安重点排查整治类("check");// 报表类("report"),材料类("stuff");
		private String eName;

		private FileType(String eName) {
			this.eName = eName;
		}

		public String getEName() {
			return eName;
		}
	}

	public String getIssueDetails() {
		return issueDetails;
	}

	public void setIssueDetails(String issueDetails) {
		this.issueDetails = issueDetails;
	}

	public IssueInspect getIssueInspect() {
		return issueInspect;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public void setIssueInspect(IssueInspect issueInspect) {
		this.issueInspect = issueInspect;
	}

	public int getExistedCount() {
		return existedCount;
	}

	public void setExistedCount(int existedCount) {
		this.existedCount = existedCount;
	}

	public List<ExtTreeStringIdData> getExtTreeDatas() {
		return extTreeDatas;
	}

	public void setExtTreeDatas(List<ExtTreeStringIdData> extTreeDatas) {
		this.extTreeDatas = extTreeDatas;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public OrganizationDubboService getOrganizationDubboService() {
		return organizationDubboService;
	}

	public void setOrganizationDubboService(OrganizationDubboService organizationDubboService) {
		this.organizationDubboService = organizationDubboService;
	}

	public String getSelectMonth() {
		return selectMonth;
	}

	public void setSelectMonth(String selectMonth) {
		this.selectMonth = selectMonth;
	}

	public PlantFormMessageConfig getPlantFormMessageConfig() {
		return plantFormMessageConfig;
	}

	public void setPlantFormMessageConfig(
			PlantFormMessageConfig plantFormMessageConfig) {
		this.plantFormMessageConfig = plantFormMessageConfig;
	}

	public Date getServerDate() {
		return serverDate;
	}

	public void setServerDate(Date serverDate) {
		this.serverDate = serverDate;
	}

	public Long getParentIndate() {
		return parentIndate;
	}

	public void setParentIndate(Long parentIndate) {
		this.parentIndate = parentIndate;
	}

	public int getInternalId() {
		return internalId;
	}

	public void setInternalId(int internalId) {
		this.internalId = internalId;
	}

}
