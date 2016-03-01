package com.tianque.resourcePool.vo;

import com.tianque.core.vo.ExtTreeData;
import com.tianque.resourcePool.domain.DirectorySetting;

public class ResourcePoolTreeData extends ExtTreeData {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourcePoolTreeData(DirectorySetting directorySetting) {
		setText(directorySetting.getName());
		setId(directorySetting.getId());

		setLeaf(true);

		setExpanded(true);
		setLevel(2);
		setChecked(false);

	}

}
