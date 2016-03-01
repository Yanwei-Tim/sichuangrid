package com.tianque.working.vo;

import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.tianque.core.util.DateUtil;
import com.tianque.core.vo.ExtTreeData;
import com.tianque.working.domain.DailyDirectory;
import com.tianque.working.domain.TimeLimitHelper;
import com.tianque.working.service.DailyLogService;

public class DailyDirectoryTreeData extends ExtTreeData {
	private static final long serialVersionUID = 1L;
	private long typeId;
	private int internalId;
	private long reportTypeId;
	private int reportTypeInternalId;
	private int require;

	private static int ZERO = 0;// 有附件有数据
	private static int ONE = 1;// 有附件无数据

	public DailyDirectoryTreeData(DailyDirectory dailyDirectory, DailyLogService dailyLogService,
			boolean leaf, boolean noRecords) {
		setText(dailyDirectory.getFullName());
		setId(dailyDirectory.getId());
		setLeaf(leaf);
		String cautionIcon = ServletActionContext.getRequest().getContextPath()
				+ "/resource/js/ext/images/default/tree/leaf_caution.gif";
		String attachIcon = ServletActionContext.getRequest().getContextPath()
				+ "/resource/js/ext/images/default/tree/attach.gif";
		String clockIcon = ServletActionContext.getRequest().getContextPath()
				+ "/resource/system/images/evaluateManagement/clock.gif";
		String noRecordsText = "<span style='color:red;'>" + dailyDirectory.getFullName()
				+ "</span>";

		Object[] obj = setEffectiveDaysAndDateType(dailyDirectory);

		String hasFiles = setHasFilesOrNoRecordsTextAndHasFiles(dailyDirectory, clockIcon,
				String.valueOf(obj[0]), Boolean.valueOf(String.valueOf(obj[1])), attachIcon, ZERO);

		String noRecordsTextAndHasFiles = setHasFilesOrNoRecordsTextAndHasFiles(dailyDirectory,
				clockIcon, String.valueOf(obj[0]), Boolean.valueOf(String.valueOf(obj[1])),
				attachIcon, ONE);

		if (!"".equals(hasFiles)) {
			setText(hasFiles);
		}
		if (leaf) {
			if (dailyDirectory.getRequire() != null) {
				setIcon(cautionIcon);
				setRequire(1);
				if (noRecords) {
					setText(noRecordsText);
					if (!"".equals(noRecordsTextAndHasFiles)) {
						setText(noRecordsTextAndHasFiles);
					}
				}
			} else {
				setRequire(0);
			}
		}

		if (dailyDirectory.getDirectoryReportType() != null) {
			if (dailyDirectory.getDirectoryReportType().getId() != null) {
				reportTypeId = dailyDirectory.getDirectoryReportType().getId();
				reportTypeInternalId = dailyDirectory.getDirectoryReportType().getInternalId();
			}
		}
		if (dailyDirectory.getType() != null) {
			if (dailyDirectory.getType().getId() != null) {
				typeId = dailyDirectory.getType().getId();
				internalId = dailyDirectory.getType().getInternalId();
			}
		}
	}

	private String setHasFilesOrNoRecordsTextAndHasFiles(DailyDirectory dailyDirectory,
			String clockIcon, String effectiveDays, boolean dateType, String attachIcon,
			int dataType) {
		String hasFiles = "";
		String color = "";
		if (0 == dataType) {
			color = "<span style='color:black;'>";
		} else {
			color = "<span style='color:red;'>";
		}
		if (!dailyDirectory.getDailyDirectoryAttachFiles().isEmpty()
				&& null != dailyDirectory.getTimeLimit()) {
			hasFiles = "<img id='dailyDirectory_" + dailyDirectory.getId()
					+ "' class='popUpMoreX' dailyDirectoryId='" + dailyDirectory.getId()
					+ "' src='" + clockIcon + "' onmouseover='over(event,&quot;" + effectiveDays
					+ "&quot;," + dateType + ")' onmouseout='out(event)'></img>" + color
					+ dailyDirectory.getFullName() + "<img id='dailyDirectory_"
					+ dailyDirectory.getId() + "' class='popUpMoreX' dailyDirectoryId='"
					+ dailyDirectory.getId() + "' src='" + attachIcon + "'></img>" + "</span>";
		} else if (!dailyDirectory.getDailyDirectoryAttachFiles().isEmpty()) {
			hasFiles = color + dailyDirectory.getFullName() + "<img id='dailyDirectory_"
					+ dailyDirectory.getId() + "' class='popUpMoreX' dailyDirectoryId='"
					+ dailyDirectory.getId() + "' src='" + attachIcon + "'></img>" + "</span>";
		} else if (null != dailyDirectory.getTimeLimit()) {
			hasFiles = "<img id='dailyDirectory_" + dailyDirectory.getId()
					+ "' class='popUpMoreX' dailyDirectoryId='" + dailyDirectory.getId()
					+ "' src='" + clockIcon + "' onmouseover='over(event,&quot;" + effectiveDays
					+ "&quot;," + dateType + ")' onmouseout='out(event)'></img>" + color
					+ dailyDirectory.getFullName() + "</span>";
		}
		return hasFiles;
	}

	private Object[] setEffectiveDaysAndDateType(DailyDirectory dailyDirectory) {
		Object[] obj = null;
		boolean dateType = false;
		String effectiveDays = "";
		if (null != dailyDirectory.getTimeLimit() && null != dailyDirectory.getEffectiveDays()) {
			effectiveDays = String.valueOf(dailyDirectory.getEffectiveDays());
		} else if (null != dailyDirectory.getTimeLimit()
				&& null != dailyDirectory.getDeadlineType()
				&& null != dailyDirectory.getDeadlineDate()) {
			effectiveDays = DateUtil.toString(TimeLimitHelper.getEndDate(dailyDirectory),
					"yyyy年MM月dd日");
			dateType = true;
		} else if (null != dailyDirectory.getTimeLimit()
				&& null != dailyDirectory.getDeadlineType()
				&& null != dailyDirectory.getDeadlineStart()
				&& null != dailyDirectory.getDeadlineEnd()) {
			dateType = true;
			Map<Integer, String> timeLimitMap = TimeLimitHelper.getDeadlineStartTypeMap();
			effectiveDays = timeLimitMap.get(dailyDirectory.getDeadlineStart()).trim()
					+ dailyDirectory.getDeadlineEnd() + "日";
		}
		obj = new Object[] { effectiveDays, dateType };
		return obj;
	}

	public int getInternalId() {
		return internalId;
	}

	public void setInternalId(int internalId) {
		this.internalId = internalId;
	}

	public int getReportTypeInternalId() {
		return reportTypeInternalId;
	}

	public void setReportTypeInternalId(int reportTypeInternalId) {
		this.reportTypeInternalId = reportTypeInternalId;
	}

	public long getTypeId() {
		return typeId;
	}

	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}

	public long getReportTypeId() {
		return reportTypeId;
	}

	public void setReportTypeId(long reportTypeId) {
		this.reportTypeId = reportTypeId;
	}

	public int getRequire() {
		return require;
	}

	public void setRequire(int require) {
		this.require = require;
	}

}
