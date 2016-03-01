package com.tianque.working.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.controller.mode.MoveMode;
import com.tianque.controller.vo.DailyDirectoryTreeVo;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.ExtTreeData;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.PropertyDomain;
import com.tianque.domain.property.StatementsReportedType;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.sysadmin.service.PropertyDomainService;
import com.tianque.working.domain.DailyDirectory;
import com.tianque.working.domain.DailyDirectoryAttachFile;
import com.tianque.working.domain.DailyYear;
import com.tianque.working.domain.TimeLimitHelper;
import com.tianque.working.service.DailyDirectoryService;
import com.tianque.working.service.DailyYearService;
import com.tianque.working.vo.DailyDirectoryVo;

@Controller("dailyDirectoryController")
@Namespace("/daily/dailyDirectoryManage")
@Scope("prototype")
@Transactional
public class DailyDirectoryController extends BaseAction {

	@Autowired
	private DailyDirectoryService dailyDirectoryService;
	@Autowired
	private DailyYearService dailyYearService;
	@Autowired
	private PropertyDictService propertyDictService;

	@Autowired
	private PropertyDomainService propertyDomainService;

	private List<DailyDirectory> dailyDirectorys;
	private DailyDirectory dailyDirectory;
	private Long parentId;
	private Long directoryId;
	private DailyDirectoryTreeVo dailyDirectoryTreeVo;

	private List<DailyYear> dailyYears;
	private Long dailyYearId;
	private long existedCount;
	private String initTree;
	private Long typeId;
	private boolean show;
	private int isYear;
	private String dailyDirectorysFilterType;
	private List<ExtTreeData> extTreeDatas = new ArrayList<ExtTreeData>();

	private Map timeLimitMap;
	private List deadlineStartList;
	private List deadlineEndList;
	private boolean flg;
	private String[] attachFiles;
	private Long keyId;
	private DailyDirectoryVo dailyDirectoryVo;

	private List domainList;

	private static final int ZREO = 0;// 标识台账:报表上报(治安重点排查情况、重大矛盾纠纷排查调处情况、排查整治强基促稳情况)子目录设置有效时(无、截止时间)
	private static final int TWO = 2;// 标识台账:报表上报(治安重点整治、矛盾纠纷排查)子目录设置有效时(无、截止时间)
	private static final int FOUR = 4;// 标识台账:平时开展工作情况(网格化管理、组团式服务)子目录设置有效时(无、有效期)

	/**
	 * 页面请求分发
	 */
	@Action(value = "dispatchOperate", results = {
			@Result(name = "success", location = "/daily/dailyDirectory/maintainDirectoryDlg.jsp"),
			@Result(name = "view", location = "/daily/dailyDirectory/dailyDirectoryView.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }),
			@Result(name = "expDate", location = "/daily/dailyDirectory/expirationDate.jsp") })
	public String dispatchOperate() throws Exception {
		timeLimitMap = TimeLimitHelper.getTimeLimitMap();
		if ("add".equals(mode)) {
			prepareAddDailyDirectory();
			return SUCCESS;
		} else if ("edit".equals(mode)) {
			prepareDailyDirectory();
			return SUCCESS;
		} else if ("view".equals(mode)) {
			prepareDailyDirectory();
			return "view";
		} else if ("expDate".equals(mode)) {
			prepareDailyDirectory();
			return "expDate";
		}
		return ERROR;
	}

	private void prepareAddDailyDirectory() {
		timeLimitMap.remove(TimeLimitHelper.DEADLINE_TYPE);

		dailyDirectory = new DailyDirectory();
		DailyYear dailyYear = new DailyYear();
		dailyYear.setId(dailyYearId);
		dailyDirectory.setDailyYear(dailyYear);

		DailyDirectory parentDirectory = new DailyDirectory();
		parentDirectory.setId(parentId);
		dailyDirectory.setParentDailyDirectory(parentDirectory);

		PropertyDict propertyDict = null;
		if (null != typeId) {
			propertyDict = new PropertyDict();
			propertyDict = propertyDictService.getPropertyDictById(typeId);
			dailyDirectory.setType(propertyDict);

			if (null != propertyDict && null != propertyDict.getDisplayName()) {
				dailyDirectory.setTypeName(propertyDict.getDisplayName());
				PropertyDomain propertyDomain = new PropertyDomain();
				propertyDomain = propertyDomainService
						.getPropertyDomainByDomainName(propertyDict
								.getDisplayName());
				if (null != propertyDomain) {
					domainList = propertyDictService
							.findPropertyDictByPropertyDomainId(propertyDomain
									.getId());
				} else {
					domainList = propertyDictService
							.findPropertyDictByPropertyDomainId(propertyDict
									.getPropertyDomain().getId());
				}
			}
		}
	}

	private void prepareDailyDirectory() {
		dailyDirectory = dailyDirectoryService
				.getSimpleDailyDirectoryById(directoryId);
		if (dailyDirectory.getType() != null
				&& dailyDirectory.getType().getId() != null
				&& null != dailyDirectory.getWhetherAdd()
				&& !dailyDirectory.getWhetherAdd().equals(FOUR)) {
			flg = true;
			createDeadlineEndList();
			if (null != dailyDirectory.getWhetherAdd()
					&& (dailyDirectory.getWhetherAdd().equals(TWO) || dailyDirectory
							.getWhetherAdd().equals(ZREO))) {
				timeLimitMap.remove(TimeLimitHelper.EFFECTIVE_DAYS);
			}
		} else {
			timeLimitMap.remove(TimeLimitHelper.DEADLINE_TYPE);
		}
		List<DailyDirectory> subDailyDirectorys = dailyDirectoryService
				.findDailySubDirectoryByParentId(directoryId);
		if (null == subDailyDirectorys || subDailyDirectorys.size() <= 0) {
			show = true;
		}
	}

	private void createDeadlineEndList() {
		deadlineStartList = new ArrayList();
		Map map = TimeLimitHelper.getDeadlineStartTypeMap();
		for (int i = 1; i <= map.size(); i++) {
			Map m = new HashMap();
			Object sss = map.get(i);
			m.put(i, sss);
			deadlineStartList.add(m);
		}
		deadlineEndList = new ArrayList();
		for (int i = 1; i <= 31; i++) {
			deadlineEndList.add(i);
		}
	}

	public String dispatchDailyTrunkDirectory() throws Exception {
		if (dailyDirectory != null && dailyDirectory.getId() != null)
			dailyDirectory = dailyDirectoryService
					.getFullDailyDirectoryById(dailyDirectory.getId());
		if ("add".equals(mode) || "edit".equals(mode) || "view".equals(mode))
			return "maintainDailyTrunkDirectory";

		return SUCCESS;
	}

	public String dispatchDailySubDirectory() throws Exception {
		if ("add".equals(mode) || "edit".equals(mode) || "view".equals(mode)) {
			if (dailyDirectory != null && dailyDirectory.getId() != null)
				dailyDirectory = dailyDirectoryService
						.getSimpleDailyDirectoryById(dailyDirectory.getId());
			DailyDirectory dailyDirectoryOne = dailyDirectoryService
					.getSimpleDailyDirectoryById(dailyDirectory
							.getParentDailyDirectory().getId());
			typeId = dailyDirectoryOne.getType().getId();
			PropertyDict propertyDict = propertyDictService
					.getPropertyDictById(typeId);
			int internalId = propertyDict.getInternalId();
			if (StatementsReportedType.CHECK == internalId
					|| StatementsReportedType.ISSUEDEAL == internalId) {
				show = true;
			}
			return "maintainDailySubDirectory";
		}
		return SUCCESS;
	}

	public String findDailyAllTrunkDirectory() throws Exception {
		if (dailyYearId != null)
			generateUnderDeptSubInfo(dailyDirectoryService
					.findDailyTrunkDirectoryByDailyYearId(dailyYearId));
		return SUCCESS;
	}

	public String findDailySubDirectoryListByParentId() throws Exception {
		if (parentId == null)
			return ERROR;
		generateUnderDeptSubInfo(dailyDirectoryService
				.findDailySubDirectoryByParentId(parentId));
		return SUCCESS;
	}

	private void generateUnderDeptSubInfo(List<DailyDirectory> Directorys) {
		dailyDirectorys = new ArrayList<DailyDirectory>();
		if ("underDeptSubInfo".equals(dailyDirectorysFilterType)) {
			for (DailyDirectory dailyDirectior : Directorys) {
				PropertyDict propertyDict = propertyDictService
						.getPropertyDictById(dailyDirectior.getType().getId());
				if ("季报".equals(dailyDirectior.getShortName()))
					continue;
				if (propertyDict.getInternalId() == StatementsReportedType.ISSUEDEAL
						|| propertyDict.getInternalId() == StatementsReportedType.CHECK) {
					dailyDirectorys.add(dailyDirectior);
				}
			}
		} else {
			dailyDirectorys.addAll(Directorys);
		}
	}

	public String findDailyTrunkDirectoryByDailyYearId() throws Exception {
		if (dailyYearId != null)
			dailyDirectorys = dailyDirectoryService
					.findDailyTrunkDirectoryByDailyYearId(dailyYearId);
		return SUCCESS;
	}

	public String findDailySubDirectoryByParentId() throws Exception {
		if (parentId == null)
			return ERROR;
		PageInfo<DailyDirectory> pageInfo = dailyDirectoryService
				.findDailySubDirectoryByParentIdForPage(parentId, page, rows,
						"indexId", "asc");
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	public String findDailySubDirectoryForSelectedByParentId() throws Exception {
		if (parentId == null)
			return ERROR;
		dailyDirectorys = dailyDirectoryService
				.findDailySubDirectoryByParentId(parentId);
		return SUCCESS;
	}

	public String findDailyDirectorysByDailyYearId() throws Exception {
		if (dailyYearId == null) {
			return ERROR;
		}
		gridPage = new GridPage();
		gridPage.setRows(dailyDirectoryService
				.findDailyDirectoryByDailyYearId(dailyYearId));
		return SUCCESS;
	}

	@PermissionFilter(ename = "addDailyDirectory")
	@Action(value = "addDailyDirectory", results = {
			@Result(name = "success", type = "json", params = { "root",
					"dailyDirectoryVo", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addDailyDirectory() throws Exception {
		if (!validateByAdd(dailyDirectory))
			return ERROR;
		dailyDirectory
				.setDailyDirectoryAttachFiles(convertToFileInfos(attachFiles));
		if (null != dailyDirectory.getParentDailyDirectory()
				&& null != dailyDirectory.getParentDailyDirectory().getId()) {
			DailyDirectory directory = dailyDirectoryService
					.getFullDailyDirectoryById(dailyDirectory
							.getParentDailyDirectory().getId());
			if (null != directory && null != directory.getWhetherAdd()
					&& directory.getWhetherAdd().equals(1)) {
				dailyDirectory.setWhetherAdd(2);
			}

			DailyDirectory subDailyDirectorys = dailyDirectoryService
					.getSimpleDailyDirectoryById(dailyDirectory
							.getParentDailyDirectory().getId());
			if (null != subDailyDirectorys
					&& null != subDailyDirectorys.getEffectiveDays()) {
				dailyDirectory.setEffectiveDays(subDailyDirectorys
						.getEffectiveDays());
				subDailyDirectorys.setEffectiveDays(null);
				dailyDirectoryService.updateDailyDirectory(subDailyDirectorys);
			}
		}

		dailyDirectory = dailyDirectoryService
				.addDailyDirectory(dailyDirectory);
		dailyDirectoryVo = new DailyDirectoryVo(dailyDirectory, 1, true);

		return SUCCESS;
	}

	private List<DailyDirectoryAttachFile> convertToFileInfos(String[] fileInfos) {
		if (fileInfos == null || fileInfos.length == 0) {
			return null;
		} else {
			List<DailyDirectoryAttachFile> result = new ArrayList<DailyDirectoryAttachFile>();
			for (int index = 0; index < fileInfos.length; index++) {
				result.add(convertToFileInfo(fileInfos[index]));
			}
			return result;
		}
	}

	private DailyDirectoryAttachFile convertToFileInfo(String file) {
		DailyDirectoryAttachFile info = new DailyDirectoryAttachFile();
		// id是否存在，存在则获取
		String idStr = file.substring(0, file.indexOf(","));
		if (StringUtil.isStringAvaliable(idStr)) {
			info.setFileId(Long.valueOf(idStr));
		}
		info.setFileName(file.substring(file.indexOf(",") + 1));
		return info;
	}

	private boolean validateByAdd(DailyDirectory dailyDirectory) {
		if (dailyDirectory == null) {
			errorMessage = "操作失败！";
			return false;
		}
		if (dailyDirectory.getFullName() == null
				|| "".equals(dailyDirectory.getFullName())) {
			errorMessage = "台账目录名称不能为空！";
			return false;
		}
		if (dailyDirectory.getDailyYear() == null
				|| dailyDirectory.getDailyYear().getId() == null) {
			errorMessage = "操作失败！";
			return false;
		}
		return true;
	}

	@PermissionFilter(ename = "updateDailyDirectory")
	@Action(value = "updateDailyDirectory", results = {
			@Result(name = "success", type = "json", params = { "root",
					"dailyDirectory", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateDailyDirectory() throws Exception {
		if (!validateByUpdate(dailyDirectory))
			return ERROR;
		if (null != dailyDirectory && null != dailyDirectory.getId()) {
			DailyDirectory directory = dailyDirectoryService
					.getSimpleDailyDirectoryById(dailyDirectory.getId());
			if (null != directory && null != directory.getWhetherAdd()) {
				dailyDirectory.setWhetherAdd(directory.getWhetherAdd());
			}
			if (null != dailyDirectory.getTimeLimit()
					&& dailyDirectory.getTimeLimit() == 1) {
				dailyDirectory.setEffectiveDays(null);
			}
		}
		if (null != dailyDirectory.getTimeLimit()
				&& null == dailyDirectory.getDeadlineType()
				&& null == dailyDirectory.getDeadlineStart()
				&& null == dailyDirectory.getDeadlineEnd()
				&& null == dailyDirectory.getDeadlineDate()
				&& null == dailyDirectory.getEffectiveDays()) {
			dailyDirectory.setTimeLimit(null);
		}
		dailyDirectory
				.setDailyDirectoryAttachFiles(convertToFileInfos(attachFiles));
		dailyDirectory = dailyDirectoryService
				.updateDailyDirectory(dailyDirectory);
		return SUCCESS;
	}

	private boolean validateByUpdate(DailyDirectory dailyDirectory) {
		if (dailyDirectory == null || dailyDirectory.getId() == null) {
			errorMessage = "操作失败！";
			return false;
		}
		if (dailyDirectory.getFullName() == null
				|| "".equals(dailyDirectory.getFullName())) {
			errorMessage = "台账目录名称不能为空！";
			return false;
		}
		return true;
	}

	@Action(value = "downLoadAttachFile", results = { @Result(name = "error", type = "json", params = {
			"root", "errorMessage" }) })
	public String downLoadActualFile() throws Exception {
		if (keyId == null) {
			this.errorMessage = "下载失败";
			return ERROR;
		}
		DailyDirectoryAttachFile attachFile = dailyDirectoryService
				.getDailyDirectoryAttachFilesByFileId(keyId);
		if (attachFile != null) {
			inputStream = new java.io.FileInputStream(createStoreFile(
					attachFile.getFileActualUrl(), attachFile.getFileName()));
			return STREAM_SUCCESS;
		}
		return ERROR;
	}

	private File createStoreFile(String path, String fileName)
			throws IOException {
		String downFilePath = FileUtil.getWebRoot() + File.separator + path;
		downloadFileName = new String(fileName.getBytes("gbk"), "ISO8859-1");
		File storedFile = new File(downFilePath);
		if (!storedFile.exists()) {
			storedFile.createNewFile();
		}
		return storedFile;
	}

	@PermissionFilter(ename = "deleteDailyDirectory")
	@Action(value = "deleteDailyDirectory", results = {
			@Result(name = "success", type = "json", params = { "root", "flg" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteDailyDirectory() throws Exception {
		flg = false;
		if (dailyDirectory == null || dailyDirectory.getId() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		this.errorMessage = dailyDirectoryService
				.deleteDailyDirectoryById(dailyDirectory.getId());
		if ("true".equals(this.errorMessage)) {
			flg = true;
			return SUCCESS;
		}
		return ERROR;
	}

	@Action(value = "validateDelete", results = {
			@Result(name = "success", type = "json", params = { "root", "flg" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String validateDelete() throws Exception {
		flg = false;
		if (dailyDirectory == null || dailyDirectory.getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		this.errorMessage = dailyDirectoryService
				.validateDeleteDailyDirectoryById(dailyDirectory.getId());
		if ("true".equals(this.errorMessage)) {
			flg = true;
			return SUCCESS;
		}
		return ERROR;
	}

	public String countExsistedTrunkDirectory() throws Exception {
		if (dailyYearId == null) {
			this.errorMessage = "请选择年度台帐!";
			return ERROR;
		} else {
			existedCount = dailyDirectoryService.countExsistedTrunkDirectory(
					dailyDirectory.getShortName(), dailyYearId,
					dailyDirectory.getId());
			return SUCCESS;
		}
	}

	@Action(value = "countExsistedSubDirectory", results = {
			@Result(name = "success", type = "json", params = { "root",
					"existedCount" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String countExsistedSubDirectory() throws Exception {
		existedCount = dailyDirectoryService.countExsistedSubDirectory(
				dailyDirectory.getFullName(), parentId, dailyYearId);
		return SUCCESS;
	}

	public String countExsistedSubDirectoryByParentId() throws Exception {
		if (parentId == null) {
			this.errorMessage = "请选择主台帐目录!";
			return ERROR;
		} else {
			existedCount = dailyDirectoryService.countExsistedSubDirectory(
					null, parentId, null);
			return SUCCESS;
		}
	}

	public String findDailyYearList() throws Exception {
		dailyYears = dailyYearService.findDailyYearList();
		return SUCCESS;
	}

	public String findDailyDirectoryByDailyYear() throws Exception {
		if (dailyYearId != null)
			dailyDirectorys = dailyDirectoryService
					.findDailyDirectoryByDailyYearId(dailyYearId);

		return SUCCESS;
	}

	@Action(value = "searchDirectorys", results = { @Result(name = "success", type = "json", params = {
			"root", "dailyDirectorys", "ignoreHierarchy", "false" }) })
	public String searchDirectorys() throws Exception {
		if (dailyDirectory != null && dailyDirectory.getFullName() != null
				&& dailyDirectory.getDailyYear() != null
				&& dailyDirectory.getDailyYear().getId() != null) {
			dailyDirectorys = dailyDirectoryService.searchDirectorys(
					dailyDirectory.getFullName(), dailyDirectory.getDailyYear()
							.getId());
		}
		return SUCCESS;
	}

	@Action(value = "moveDailyDirectory", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String moveDailyDirectory() throws Exception {
		if (null == moveMode || "".equals(moveMode)) {
			this.errorMessage = "请选择移动方式!";
			return ERROR;
		}
		dailyDirectory = dailyDirectoryService
				.getSimpleDailyDirectoryById(dailyDirectory.getId());
		if (MoveMode.TO_FIRST.equalsIgnoreCase(moveMode)) {
			dailyDirectoryService.moveDailySubDirectoryToFirst(dailyDirectory
					.getId(), dailyDirectory.getParentDailyDirectory().getId(),
					dailyDirectory.getIndexId());
		} else if (MoveMode.TO_END.equalsIgnoreCase(moveMode)) {
			dailyDirectoryService.moveDailySubDirectoryToEnd(dailyDirectory
					.getId(), dailyDirectory.getParentDailyDirectory().getId(),
					dailyDirectory.getIndexId());
		} else if (MoveMode.TO_PREVIOUS.equalsIgnoreCase(moveMode)) {
			dailyDirectoryService.moveDailySubDirectoryToPrevious(
					dailyDirectory.getId(), dailyDirectory
							.getParentDailyDirectory().getId(), dailyDirectory
							.getIndexId());
		} else if (MoveMode.TO_NEXT.equalsIgnoreCase(moveMode)) {
			dailyDirectoryService.moveDailySubDirectoryToNext(dailyDirectory
					.getId(), dailyDirectory.getParentDailyDirectory().getId(),
					dailyDirectory.getIndexId());
		}
		return SUCCESS;
	}

	@Action(value = "moveMainDailyDirectory", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String moveMainDailyDirectory() throws Exception {
		if (null == moveMode || "".equals(moveMode)) {
			this.errorMessage = "请选择移动方式!";
			return ERROR;
		}
		dailyDirectory = dailyDirectoryService
				.getSimpleDailyDirectoryById(dailyDirectory.getId());
		if (MoveMode.TO_FIRST.equalsIgnoreCase(moveMode)) {
			dailyDirectoryService.moveDailyTrunkDirectoryToFirst(dailyDirectory
					.getId(), dailyDirectory.getDailyYear().getId(),
					dailyDirectory.getIndexId());
		} else if (MoveMode.TO_END.equalsIgnoreCase(moveMode)) {
			dailyDirectoryService.moveDailyTrunkDirectoryToEnd(dailyDirectory
					.getId(), dailyDirectory.getDailyYear().getId(),
					dailyDirectory.getIndexId());
		} else if (MoveMode.TO_PREVIOUS.equalsIgnoreCase(moveMode)) {
			dailyDirectoryService.moveDailyTrunkDirectoryToPrevious(
					dailyDirectory.getId(), dailyDirectory.getDailyYear()
							.getId(), dailyDirectory.getIndexId(),
					dailyDirectory.getIndexId() - 1);
		} else if (MoveMode.TO_NEXT.equalsIgnoreCase(moveMode)) {
			dailyDirectoryService.moveDailyTrunkDirectoryToNext(dailyDirectory
					.getId(), dailyDirectory.getDailyYear().getId(),
					dailyDirectory.getIndexId(),
					dailyDirectory.getIndexId() + 1);
		}
		return SUCCESS;
	}

	@Action(value = "firstLoadDailydirectory", results = {
			@Result(name = "success", type = "json", params = { "root",
					"extTreeDatas", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String firstLoadDailydirectory() throws Exception {
		if (this.dailyYearId == null) {
			return SUCCESS;
		}
		this.extTreeDatas = dailyDirectoryService
				.findExtTreeDataByTreeTypeAndParentIdAndDailyYearId(initTree,
						parentId, dailyYearId);
		return SUCCESS;
	}

	public String isYesrsCount() throws Exception {
		isYear = dailyDirectoryService.isYesrsCount(dailyYearId);
		return SUCCESS;
	}

	public String getSimpleDailyDirectoryById() throws Exception {
		if (dailyDirectory == null || dailyDirectory.getId() == null) {
			this.errorMessage = "参数不正确";
			return ERROR;
		}
		this.dailyDirectory = this.dailyDirectoryService
				.getSimpleDailyDirectoryById(dailyDirectory.getId());
		return SUCCESS;
	}

	@Action(value = "getFullDailyDirectoryById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"dailyDirectory", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getFullDailyDirectoryById() throws Exception {
		if (dailyDirectory == null || dailyDirectory.getId() == null) {
			this.errorMessage = "参数不正确";
			return ERROR;
		}
		this.dailyDirectory = this.dailyDirectoryService
				.getSimpleDailyDirectoryById(dailyDirectory.getId());
		return SUCCESS;
	}

	@Action(value = "loadDailyDirectoryTreeForSelfEvaluate", results = {
			@Result(name = "success", type = "json", params = { "root",
					"extTreeDatas", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String loadDailyDirectoryTreeForSelfEvaluate() throws Exception {
		if (this.dailyYearId == null) {
			return SUCCESS;
		}
		this.extTreeDatas = dailyDirectoryService
				.findExtTreeDataByTreeTypeAndParentIdAndDailyYearId(initTree,
						parentId, dailyYearId);
		return SUCCESS;
	}

	private Map<String, Integer> map;

	@Action(value = "staticsReport", results = {
			@Result(name = "success", type = "json", params = { "root", "map",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String staticsReport() throws Exception {
		map = dailyDirectoryService.statisticsReport(ThreadVariable.getUser()
				.getOrganization().getId());
		return SUCCESS;
	}

	private String moveMode = "";
	private Long referDailyDirectoryId;

	public String getMoveMode() {
		return moveMode;
	}

	public void setMoveMode(String moveMode) {
		this.moveMode = moveMode;
	}

	public Long getReferDailyDirectoryId() {
		return referDailyDirectoryId;
	}

	public void setReferDailyDirectoryId(Long referDailyDirectoryId) {
		this.referDailyDirectoryId = referDailyDirectoryId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public DailyDirectory getDailyDirectory() {
		return dailyDirectory;
	}

	public void setDailyDirectory(DailyDirectory dailyDirectory) {
		this.dailyDirectory = dailyDirectory;
	}

	public List<DailyDirectory> getDailyDirectorys() {
		return dailyDirectorys;
	}

	public void setDailyDirectorys(List<DailyDirectory> dailyDirectorys) {
		this.dailyDirectorys = dailyDirectorys;
	}

	public List<DailyYear> getDailyYears() {
		return dailyYears;
	}

	public void setDailyYears(List<DailyYear> dailyYears) {
		this.dailyYears = dailyYears;
	}

	public Long getDailyYearId() {
		return dailyYearId;
	}

	public void setDailyYearId(Long dailyYearId) {
		this.dailyYearId = dailyYearId;
	}

	public long getExistedCount() {
		return existedCount;
	}

	public void setExistedCount(long existedCount) {
		this.existedCount = existedCount;
	}

	public List<ExtTreeData> getExtTreeDatas() {
		return extTreeDatas;
	}

	public void setExtTreeDatas(List<ExtTreeData> extTreeDatas) {
		this.extTreeDatas = extTreeDatas;
	}

	public DailyDirectoryTreeVo getDailyDirectoryTreeVo() {
		return dailyDirectoryTreeVo;
	}

	public void setDailyDirectoryTreeVo(
			DailyDirectoryTreeVo dailyDirectoryTreeVo) {
		this.dailyDirectoryTreeVo = dailyDirectoryTreeVo;
	}

	public String getInitTree() {
		return initTree;
	}

	public void setInitTree(String initTree) {
		this.initTree = initTree;
	}

	public String getDailyDirectorysFilterType() {
		return dailyDirectorysFilterType;
	}

	public void setDailyDirectorysFilterType(String dailyDirectorysFilterType) {
		this.dailyDirectorysFilterType = dailyDirectorysFilterType;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public int getIsYear() {
		return isYear;
	}

	public void setIsYear(int isYear) {
		this.isYear = isYear;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public Long getDirectoryId() {
		return directoryId;
	}

	public void setDirectoryId(Long directoryId) {
		this.directoryId = directoryId;
	}

	public Map getTimeLimitMap() {
		return timeLimitMap;
	}

	public List getDeadlineStartList() {
		return deadlineStartList;
	}

	public List getDeadlineEndList() {
		return deadlineEndList;
	}

	public boolean isFlg() {
		return flg;
	}

	public String[] getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(String[] attachFiles) {
		this.attachFiles = attachFiles;
	}

	public Long getKeyId() {
		return keyId;
	}

	public void setKeyId(Long keyId) {
		this.keyId = keyId;
	}

	public DailyDirectoryVo getDailyDirectoryVo() {
		return dailyDirectoryVo;
	}

	public void setDailyDirectoryVo(DailyDirectoryVo dailyDirectoryVo) {
		this.dailyDirectoryVo = dailyDirectoryVo;
	}

	public Map<String, Integer> getMap() {
		return map;
	}

	public void setMap(Map<String, Integer> map) {
		this.map = map;
	}

	public List getDomainList() {
		return domainList;
	}

	public void setDomainList(List domainList) {
		this.domainList = domainList;
	}
}
