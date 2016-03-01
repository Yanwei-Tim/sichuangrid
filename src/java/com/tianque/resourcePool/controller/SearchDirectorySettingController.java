package com.tianque.resourcePool.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.resourcePool.domain.DirectorySetting;
import com.tianque.resourcePool.service.DirectorySettingService;

@Transactional
@Scope("protoType")
@Controller("searchDirectorySettingController")
@Namespace("/resourcePool/searchDirectorySetting")
public class SearchDirectorySettingController extends BaseAction {
	private DirectorySetting directorySetting;
	@Autowired
	private DirectorySettingService directorySettingService;
	private List<DirectorySetting> directorySettings;
	private List<Long> ids;
	private String parentNodeIdsForSearch = "";

	@Action(value = "searchDirectorySetting", results = { @Result(name = "success", type = "json", params = {
			"root", "directorySettings", "ignoreHierarchy", "false" }) })
	public String searchDirectorySetting() {
		if (directorySetting != null && directorySetting.getName() != null) {
			directorySettings = directorySettingService.SearchDirectorySetting(directorySetting
					.getName());
		}

		return SUCCESS;

	}

	/**
	 * jqTreeGrid
	 * 
	 * @return
	 */
	@Action(value = "searchListParentNodeIds", results = { @Result(name = "success", type = "json", params = {
			"root", "parentNodeIdsForSearch", "ignoreHierarchy", "false" }) })
	public String searchListParentNodeIds() {
		if (directorySetting != null && directorySetting.getId() != null) {
			ids = directorySettingService.searchListParentNodeIds(directorySetting.getId());
		}
		fillSearchListParentNodeIds(ids);
		return SUCCESS;

	}

	private void fillSearchListParentNodeIds(List<Long> ids) {
		directorySetting = directorySettingService.findDirectorySettingById(directorySetting
				.getId());
		if (directorySetting != null
				&& directorySetting.getDirectoryType() == DirectorySetting.privateDirectory) {
			parentNodeIdsForSearch = "0";
		} else {
			parentNodeIdsForSearch = "-1";
		}

		for (int i = 0; i < ids.size(); i++) {

			parentNodeIdsForSearch = parentNodeIdsForSearch + "/" + ids.get(i);
		}
	}

	/**
	 * tree
	 * 
	 * @return
	 */
	@Action(value = "searchParentNodeIds", results = { @Result(name = "success", type = "json", params = {
			"root", "parentNodeIdsForSearch", "ignoreHierarchy", "false" }) })
	public String searchParentNodeIds() {
		if (directorySetting != null && directorySetting.getId() != null) {
			ids = directorySettingService.searchParentNodeIds(directorySetting.getId());
		}
		fillSearchParentNodeIds(ids);
		return SUCCESS;

	}

	private void fillSearchParentNodeIds(List<Long> ids) {
		for (int i = 0; i < ids.size(); i++) {

			parentNodeIdsForSearch = ids.get(i) + "/" + parentNodeIdsForSearch;
		}
		parentNodeIdsForSearch = parentNodeIdsForSearch.substring(0,
				parentNodeIdsForSearch.length() - 1);
	}

	public DirectorySetting getDirectorySetting() {
		return directorySetting;
	}

	public void setDirectorySetting(DirectorySetting directorySetting) {
		this.directorySetting = directorySetting;
	}

	public List<DirectorySetting> getDirectorySettings() {
		return directorySettings;
	}

	public void setDirectorySettings(List<DirectorySetting> directorySettings) {
		this.directorySettings = directorySettings;
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	public String getParentNodeIdsForSearch() {
		return parentNodeIdsForSearch;
	}

	public void setParentNodeIdsForSearch(String parentNodeIdsForSearch) {
		this.parentNodeIdsForSearch = parentNodeIdsForSearch;
	}

}
