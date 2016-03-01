package com.tianque.working.domain;

import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.StringUtil;
import com.tianque.domain.PropertyDict;

/**
 * 工作台帐目录
 * 
 * @author drools
 */
public class DailyDirectory extends BaseDomain {
	/** 上级台帐目录 */
	private DailyDirectory parentDailyDirectory;
	/** 台帐目录简称 */
	private String shortName;
	/** 台帐目录全称 */
	private String fullName;
	private PropertyDict type;
	private PropertyDict directoryReportType;
	/** 台帐目录描述 */
	private String remark;
	/** 年度工作台帐目录 */
	private DailyYear dailyYear;
	private Long indate;
	private boolean effectiveDate;// 是否使用有效日期

	/** 名称是否必填 */
	private Integer require;
	/** 时限 */
	private Integer timeLimit;
	/** 有效天数 */
	private Integer effectiveDays;
	/** 截止时间类型 */
	private Integer deadlineType;
	/** 截止时间类型的普通台账类型截止日期 */
	private Date deadlineDate;
	/** 截止时间起始 */
	private Integer deadlineStart;
	/** 截止时间结束 */
	private Integer deadlineEnd;
	/** 台帐目录名称简拼 */
	private String simplePinyin;
	/** 台帐目录名称全拼 */
	private String fullPinyin;

	/** 是否新增子目录 */
	private Integer whetherAdd;

	private List<DailyDirectory> subDailyDirectorys;

	private List<DailyDirectoryAttachFile> dailyDirectoryAttachFiles;

	private Integer indexId;

	private String typeName;

	private boolean showClock;

	public Integer getIndexId() {
		return indexId;
	}

	public void setIndexId(Integer indexId) {
		this.indexId = indexId;
	}

	public DailyDirectory getParentDailyDirectory() {
		return parentDailyDirectory;
	}

	public void setParentDailyDirectory(DailyDirectory parentDailyDirectory) {
		this.parentDailyDirectory = parentDailyDirectory;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public DailyYear getDailyYear() {
		return dailyYear;
	}

	public void setDailyYear(DailyYear dailyYear) {
		this.dailyYear = dailyYear;
	}

	public List<DailyDirectory> getSubDailyDirectorys() {
		return subDailyDirectorys;
	}

	public void setSubDailyDirectorys(List<DailyDirectory> subDailyDirectorys) {
		this.subDailyDirectorys = subDailyDirectorys;
	}

	public PropertyDict getType() {
		return type;
	}

	public void setType(PropertyDict type) {
		this.type = type;
	}

	public Long getIndate() {
		return indate;
	}

	public void setIndate(Long indate) {
		this.indate = indate;
	}

	public PropertyDict getDirectoryReportType() {
		return directoryReportType;
	}

	public void setDirectoryReportType(PropertyDict directoryReportType) {
		this.directoryReportType = directoryReportType;
	}

	public Integer getRequire() {
		return require;
	}

	public void setRequire(Integer require) {
		this.require = require;
	}

	public Integer getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}

	public Integer getEffectiveDays() {
		return effectiveDays;
	}

	public void setEffectiveDays(Integer effectiveDays) {
		this.effectiveDays = effectiveDays;
	}

	public Integer getDeadlineType() {
		return deadlineType;
	}

	public void setDeadlineType(Integer deadlineType) {
		this.deadlineType = deadlineType;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getDeadlineDate() {
		return deadlineDate;
	}

	public void setDeadlineDate(Date deadlineDate) {
		this.deadlineDate = deadlineDate;
	}

	public Integer getDeadlineStart() {
		return deadlineStart;
	}

	public void setDeadlineStart(Integer deadlineStart) {
		this.deadlineStart = deadlineStart;
	}

	public Integer getDeadlineEnd() {
		return deadlineEnd;
	}

	public void setDeadlineEnd(Integer deadlineEnd) {
		this.deadlineEnd = deadlineEnd;
	}

	public String getSimplePinyin() {
		return simplePinyin;
	}

	public void setSimplePinyin(String simplePinyin) {
		if (!StringUtil.isStringAvaliable(simplePinyin) && simplePinyin.length() > 29) {
			simplePinyin = simplePinyin.substring(0, 30);
		}
		this.simplePinyin = simplePinyin;
	}

	public String getFullPinyin() {
		return fullPinyin;
	}

	public void setFullPinyin(String fullPinyin) {
		if (!StringUtil.isStringAvaliable(fullPinyin) && fullPinyin.length() > 128) {
			fullPinyin = fullPinyin.substring(0, 127);
		}
		this.fullPinyin = fullPinyin;
	}

	public List<DailyDirectoryAttachFile> getDailyDirectoryAttachFiles() {
		return dailyDirectoryAttachFiles;
	}

	public void setDailyDirectoryAttachFiles(
			List<DailyDirectoryAttachFile> dailyDirectoryAttachFiles) {
		this.dailyDirectoryAttachFiles = dailyDirectoryAttachFiles;
	}

	public boolean isEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(boolean effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Integer getWhetherAdd() {
		return whetherAdd;
	}

	public void setWhetherAdd(Integer whetherAdd) {
		this.whetherAdd = whetherAdd;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public boolean getShowClock() {
		return showClock;
	}

	public void setShowClock(boolean showClock) {
		this.showClock = showClock;
	}
}
