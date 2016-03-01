package com.tianque.working.vo;

import java.util.Map;

import com.tianque.core.util.DateUtil;
import com.tianque.working.domain.DailyDirectory;
import com.tianque.working.domain.TimeLimitHelper;

/**
 * @author Administrator
 */
public class DailyDirectoryVo extends DailyDirectory {
	private static final long serialVersionUID = 1L;
	private boolean leaf;
	private Integer level = 1;
	private boolean expanded = true;
	private Long parentId;
	private boolean check;
	private boolean enable = true;
	private Long directoryTypeId;
	private boolean loaded = true;
	private String parentFlag;

	/**
	 * @param dailyDirectory
	 * @param level
	 *        层级数
	 * @param leaf
	 *        是否节点
	 */
	public DailyDirectoryVo(DailyDirectory dailyDirectory, Integer level, boolean leaf) {
		this.setIndexId(dailyDirectory.getIndexId());
		this.setId(dailyDirectory.getId());
		this.setShortName(dailyDirectory.getShortName());
		this.setFullName(dailyDirectory.getFullName());
		this.setRemark(dailyDirectory.getRemark());
		this.setParentDailyDirectory(dailyDirectory.getParentDailyDirectory());
		this.setType(dailyDirectory.getType());
		this.setDailyDirectoryAttachFiles(dailyDirectory.getDailyDirectoryAttachFiles());
		this.setWhetherAdd(dailyDirectory.getWhetherAdd());
		this.level = level;
		this.leaf = leaf;
		if (level >= 3) {
			expanded = false;
		}
		if (dailyDirectory.getParentDailyDirectory() != null
				&& dailyDirectory.getParentDailyDirectory().getId() != null) {
			this.parentId = dailyDirectory.getParentDailyDirectory().getId();
		} else {
			this.parentId = 0L;
		}
		if (dailyDirectory.getType() != null && dailyDirectory.getType().getId() != null) {
			this.directoryTypeId = dailyDirectory.getType().getId();
		}

		if (null != dailyDirectory.getTimeLimit() && null != dailyDirectory.getEffectiveDays()) {
			this.setFullName(dailyDirectory.getFullName() + "(<font color='#CC0000'>有效时间为"
					+ dailyDirectory.getEffectiveDays() + "天</font>)");
		} else if (null != dailyDirectory.getTimeLimit()
				&& null != dailyDirectory.getDeadlineType()
				&& null != dailyDirectory.getDeadlineDate()) {
			this.setFullName(dailyDirectory.getFullName() + "(<font color='#CC0000'>截止时间为"
					+ DateUtil.toString(TimeLimitHelper.getEndDate(dailyDirectory), "yyyy年MM月dd日")
					+ "</font>)");
		} else if (null != dailyDirectory.getTimeLimit()
				&& null != dailyDirectory.getDeadlineType()
				&& null != dailyDirectory.getDeadlineStart()
				&& null != dailyDirectory.getDeadlineEnd()) {
			Map<Integer, String> timeLimitMap = TimeLimitHelper.getDeadlineStartTypeMap();
			this.setFullName(dailyDirectory.getFullName() + "(<font color='#CC0000'>截止时间为："
					+ timeLimitMap.get(dailyDirectory.getDeadlineStart()).trim()
					+ dailyDirectory.getDeadlineEnd() + "日" + "</font>)");
		} else {
			this.setFullName(dailyDirectory.getFullName());
		}
	}

	public DailyDirectoryVo() {
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public Long getDirectoryTypeId() {
		return directoryTypeId;
	}

	public void setDirectoryTypeId(Long directoryTypeId) {
		this.directoryTypeId = directoryTypeId;
	}

	public String getParentFlag() {
		return parentFlag;
	}

	public void setParentFlag(String parentFlag) {
		this.parentFlag = parentFlag;
	}

	public boolean isLoaded() {
		return loaded;
	}

	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}

}
