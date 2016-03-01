package com.tianque.resourcePool.vo;

import org.apache.struts2.ServletActionContext;

import com.tianque.core.vo.ExtTreeData;
import com.tianque.resourcePool.domain.DirectorySetting;

public class MyProfileTreeData extends ExtTreeData {
	public MyProfileTreeData(DirectorySettingVo directorySettingVo) {

		setId(directorySettingVo.getId());
		convertMyProfileNode(directorySettingVo);
		String publicIcon = ServletActionContext.getRequest().getContextPath()
				+ "/resource/external/ext/images/default/tree/public.gif";
		String privateIcon = ServletActionContext.getRequest().getContextPath()
				+ "/resource/external/ext/images/default/tree/private.gif";
		if (DirectorySetting.publicDirectory == directorySettingVo
				.getDirectoryType()) {
			setText(directorySettingVo.getName());
			setIcon(publicIcon);
		} else if (DirectorySetting.privateDirectory == directorySettingVo
				.getDirectoryType() && directorySettingVo.getCount() == 0) {
			setText(directorySettingVo.getName());
			setIcon(privateIcon);
		} else {
			setText(directorySettingVo.getName());
		}

		setExpanded(false);
		setLevel(directorySettingVo.getLevel());

	}

	private void convertMyProfileNode(DirectorySettingVo directorySettingVo) {
		if (directorySettingVo.getCount() != 0) {
			setLeaf(false);
			cls = "folder";
		} else {
			setLeaf(true);
			cls = "file";
			leaf = true;
			expanded = false;
			icon = ServletActionContext.getRequest().getContextPath() + LEAF;
		}
	}

}
