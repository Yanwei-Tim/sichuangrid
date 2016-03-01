package com.tianque.resourcePool.vo;

import com.tianque.resourcePool.domain.DirectorySetting;

public class DirectorySettingVo extends DirectorySetting {
	private static final long serialVersionUID = 1L;
	private boolean leaf = true;

	private boolean expanded = false;
	private Long parentId;
	private boolean check;
	private boolean enable = true;
	private int indexid;

	public DirectorySettingVo(DirectorySetting directorySetting) {
		this.indexid = directorySetting.getIndexId();
		this.setId(directorySetting.getId());
		this.setSimplePinyin(directorySetting.getSimplePinyin());
		this.setName(directorySetting.getName());

		this.setParentPersonalDirectory(directorySetting.getParentPersonalDirectory());
		this.setDirectoryType(directorySetting.getDirectoryType());
		this.setLevel(directorySetting.getLevel());
		int count = directorySetting.getCount();
		this.setCount(count);
		if (count == 0) {
			leaf = true;
			expanded = false;
		} else {
			leaf = false;
		}
		if (directorySetting.getParentPersonalDirectory() != null
				&& directorySetting.getParentPersonalDirectory().getId() != null)
			parentId = directorySetting.getParentPersonalDirectory().getId();
	}

	public DirectorySettingVo() {
	};

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
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

	public int getIndexid() {
		return indexid;
	}

	public void setIndexid(int indexid) {
		this.indexid = indexid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
