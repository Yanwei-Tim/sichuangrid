package com.tianque.resourcePool.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.ExtTreeData;
import com.tianque.core.vo.GridPage;
import com.tianque.resourcePool.domain.DirectorySetting;
import com.tianque.resourcePool.service.DirectorySettingService;
import com.tianque.resourcePool.vo.DirectorySettingVo;
import com.tianque.resourcePool.vo.MyProfileTreeData;
import com.tianque.resourcePool.vo.ResourcePoolTreeData;

@Namespace("/resourcePool/directorySettingManage")
@Transactional
@Scope("request")
@Controller("directorySettingController")
public class DirectorySettingController extends BaseAction {
	private List<ExtTreeData> extTreeDatas = new ArrayList<ExtTreeData>();
	private DirectorySetting directorySetting;
	private DirectorySettingVo directorySettingVo;
	private DirectorySetting parentDirect;
	DirectorySetting previousDirect = null;
	private String moveMode;
	private String parentId;
	List<DirectorySettingVo> result = null;
	List<DirectorySetting> directorySettings;
	@Autowired
	private DirectorySettingService directorySettingService;

	@Action(value = "findDirectorySettingForList", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findDirectorySettingForList() throws Exception {
		gridPage = new GridPage();
		gridPage.setRows(getDailyDirectoryList());

		return SUCCESS;
	}

	@Action(value = "dispatchdirectoryTrunk", results = {
			@Result(name = "maintainDirectorySettingTrunk", location = "/knowledgeSharing/directorySetting/maintainDirectorySettingTrunkDlg.jsp"),
			@Result(name = "SUCCESS", location = "/knowledgeSharing/directorySetting/directorySettingList.jsp") })
	public String dispatchdirectoryTrunk() throws Exception {

		if ("edit".equals(mode) || "view".equals(mode)) {
			directorySetting = directorySettingService
					.findDirectorySettingById(directorySetting.getId());
			return "maintainDirectorySettingTrunk";
		}
		if ("add".equals(mode)) {
			return "maintainDirectorySettingTrunk";
		}

		return SUCCESS;

	}

	@PermissionFilter(ename = "adddirectorySetting")
	@Action(value = "addDirectorySetting", results = {
			@Result(name = "success", type = "json", params = { "root",
					"directorySetting", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addDirectorySetting() throws Exception {
		if (directorySetting == null) {
			errorMessage = "无效的操作";
			return ERROR;
		}

		if (StringUtils.isEmpty(directorySetting.getName())) {
			errorMessage = "请输入目录名";
			return ERROR;
		}

		if (directorySetting.getName().getBytes().length > 30) {
			errorMessage = "目录名太长，不能超过30个字节，（一个汉字2个字节）";
			return ERROR;
		}

		directorySetting = directorySettingService.add(directorySetting);
		return SUCCESS;

	}

	@PermissionFilter(ename = "deletedirectorySetting")
	@Action(value = "deleteDirectorySetting", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteDirectorySetting() throws Exception {
		if (directorySetting == null || directorySetting.getId() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		if (!validateDelete())
			return ERROR;
		directorySettingService.delete(directorySetting.getId());

		return SUCCESS;
	}

	@PermissionFilter(ename = "updatedirectorySetting")
	@Action(value = "updateDirectorySetting", results = {
			@Result(name = "success", type = "json", params = { "root",
					"directorySettingVo", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateDirectorySetting() throws Exception {
		if (directorySetting.getId() != null)
			directorySetting = directorySettingService.update(directorySetting);

		directorySettingVo = new DirectorySettingVo(
				updateDirectorySetting(directorySetting));
		return SUCCESS;
	}

	/**
	 * 上移
	 * 
	 * @return
	 */
	@Action(value = "upDirectorySetting", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String upDirectorySetting() throws Exception {
		directorySettingService.updateMove(directorySetting, moveMode);

		return SUCCESS;

	}

	/**
	 * 下移
	 * 
	 * @return
	 */
	@Action(value = "downDirectorySetting", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String downDirectorySetting() throws Exception {
		directorySettingService.updateMove(directorySetting, moveMode);

		return SUCCESS;

	}

	public boolean validateDelete() {
		int childDirectCount = directorySettingService
				.findDirectorySettingByParentId(directorySetting.getId())
				.size();
		if (childDirectCount > 0) {
			errorMessage = "该目录下含有子目录，无法删除<br>请先将子目录删除后，再重新删除该目录。";
			return false;
		}
		int fileCount = directorySettingService
				.getCountByResourcePoolType(directorySetting.getId());
		if (fileCount > 0) {
			errorMessage = "该目录下已经存在文件，无法删除！";
			return false;
		}
		return true;

	}

	private List<DirectorySettingVo> getDailyDirectoryList() {
		result = new ArrayList<DirectorySettingVo>();
		List<DirectorySetting> privatedirectory = directorySettingService
				.findchildByparentId(999L);// 999L指个人目录parentId为999

		DirectorySettingVo privateVo = new DirectorySettingVo();
		privateVo.setName("个人目录");
		privateVo.setId(999L);
		privateVo.setIndexid(1);
		privateVo.setDirectoryType(DirectorySetting.privateDirectory);
		autoDirectorySettingVo(privatedirectory, privateVo);

		List<DirectorySetting> publicdirectory = directorySettingService
				.findDirectorySettingByDirectoryType(DirectorySetting.publicDirectory);
		DirectorySettingVo publicVo = new DirectorySettingVo();
		publicVo.setName("本级目录");
		publicVo.setId(-1L);// 100L指设定公共目录Id为100
		publicVo.setIndexid(2);
		publicVo.setDirectoryType(DirectorySetting.publicDirectory);
		autoDirectorySettingVo(publicdirectory, publicVo);
		return result;
	}

	private List<DirectorySettingVo> autoDirectorySettingVo(
			List<DirectorySetting> directorySettings,
			DirectorySettingVo directorySettingOne) {
		directorySettingOne.setLevel(1);
		directorySettingOne.setCount(directorySettings.size());
		if (directorySettings.size() > 0) {
			directorySettingOne.setLeaf(false);
		}
		result.add(directorySettingOne);
		addResult(directorySettings);

		return result;
	}

	private DirectorySetting updateDirectorySetting(
			DirectorySetting directorySetting) {
		DirectorySetting daDirectory = new DirectorySetting();
		if (directorySetting.getParentPersonalDirectory() == null) {
			daDirectory.setId(-1L);
			directorySetting.setParentPersonalDirectory(daDirectory);
		}
		int childDirectCount = directorySettingService
				.findDirectorySettingByParentId(directorySetting.getId())
				.size();
		directorySetting.setCount(childDirectCount);
		return directorySetting;
	}

	private List<DirectorySettingVo> exchangeToDirectorySettingVo(
			List<DirectorySetting> directorySettings) {
		if (directorySettings == null)
			return null;
		List<DirectorySettingVo> result = new ArrayList<DirectorySettingVo>();
		for (DirectorySetting directorySetting : directorySettings) {
			result.add(new DirectorySettingVo(
					updateDirectorySetting(directorySetting)));
			result.addAll(exchangeToDirectorySettingVo(directorySettingService
					.findDirectorySettingByParentId(directorySetting.getId())));

		}
		return result;
	}

	@Action(value = "firstLoadDirectory", results = { @Result(name = "success", type = "json", params = {
			"root", "extTreeDatas", "excludeNullProperties", "true",
			"ignoreHierarchy", "false" }) })
	public String firstLoadDirectory() throws Exception {
		ajaxDirectorySettingForExtTree();
		return SUCCESS;
	}

	/**
	 * 公共目录树（不带框）
	 * 
	 * @return
	 */
	@Action(value = "firstPublicDirectory", results = { @Result(name = "success", type = "json", params = {
			"root", "extTreeDatas", "excludeNullProperties", "true",
			"ignoreHierarchy", "false" }) })
	public String firstPublicDirectory() throws Exception {
		ajaxPublicDirectoryForExtTree();
		return SUCCESS;
	}

	/**
	 * 共享时存放的公共目录树（带框）
	 * 
	 * @return
	 */
	@Action(value = "shareToStorePublicDirectory", results = { @Result(name = "success", type = "json", params = {
			"root", "extTreeDatas", "excludeNullProperties", "true",
			"ignoreHierarchy", "false" }) })
	public String shareToStorePublicDirectory() throws Exception {
		ajaxResoursePoolDirectoryForExtTree();
		return SUCCESS;
	}

	public void ajaxResoursePoolDirectoryForExtTree() {
		List<DirectorySetting> propertyDicts = directorySettingService
				.findDirectorySettingByDirectoryType(DirectorySetting.publicDirectory);
		for (DirectorySetting pd : propertyDicts) {
			extTreeDatas.add(new ResourcePoolTreeData(pd));
		}
	}

	private List<ExtTreeData> ajaxPublicDirectoryForExtTree() {
		directorySettings = new ArrayList<DirectorySetting>();
		directorySettings
				.addAll(directorySettingService
						.findDirectorySettingByDirectoryType(DirectorySetting.publicDirectory));
		for (DirectorySetting ds : directorySettings) {
			MyProfileTreeData myProfileTreeData = new MyProfileTreeData(
					new DirectorySettingVo(updateDirectorySetting(ds)));
			myProfileTreeData.setText(ds.getName());
			extTreeDatas.add(myProfileTreeData);
		}
		return extTreeDatas;
	}

	/**
	 * 我的资料树
	 */
	public List<ExtTreeData> ajaxDirectorySettingForExtTree() {

		getMyDirectorySettings();

		exchangeDirectorySettingsToExtTreeData();

		return extTreeDatas;
	}

	private void getMyDirectorySettings() {
		if (null != parentId) {
			directorySettings = directorySettingService
					.findchildByparentId(Long.valueOf(parentId));
		} else {
			directorySettings = new ArrayList<DirectorySetting>();
			directorySettings
					.addAll(directorySettingService
							.findDirectorySettingByDirectoryType(DirectorySetting.publicDirectory));
			directorySettings.addAll(directorySettingService
					.findchildByparentId(0L));
		}
	}

	private void exchangeDirectorySettingsToExtTreeData() {
		/*
		 * for (DirectorySetting ds : directorySettings) { extTreeDatas.add(new
		 * MyProfileTreeData(new DirectorySettingVo(
		 * updateDirectorySetting(ds)))); }
		 */

		DirectorySettingVo publicVo = new DirectorySettingVo();
		List<DirectorySetting> publicdirectory = directorySettingService
				.findDirectorySettingByDirectoryType(DirectorySetting.publicDirectory);
		publicVo.setName("本级目录");
		publicVo.setId(-1L);// -1L指设定公共目录Id为-1
		publicVo.setIndexid(2);
		publicVo.setDirectoryType(DirectorySetting.publicDirectory);
		publicVo.setLevel(1);
		publicVo.setCount(publicdirectory.size());
		ExtTreeData benjimulu = new MyProfileTreeData(publicVo);
		List<ExtTreeData> list = new ArrayList<ExtTreeData>();
		for (DirectorySetting benji : publicdirectory) {
			list.add(new MyProfileTreeData(new DirectorySettingVo(
					updateDirectorySetting(benji))));
		}
		benjimulu.setChildren(list);
		extTreeDatas.add(benjimulu);

		List<DirectorySetting> privatedirectory = directorySettingService
				.findchildByparentId(999L);// 0L指个人目录parentId为0
		DirectorySettingVo privateVo = new DirectorySettingVo();
		privateVo.setName("个人目录");
		privateVo.setId(999L);
		privateVo.setIndexid(1);
		privateVo.setDirectoryType(DirectorySetting.privateDirectory);
		privateVo.setLevel(1);
		privateVo.setCount(privatedirectory.size());
		if (privatedirectory.size() > 0) {
			privateVo.setLeaf(false);
		}
		ExtTreeData gerenmulu = new MyProfileTreeData(privateVo);
		List<ExtTreeData> l = new ArrayList<ExtTreeData>();
		for (DirectorySetting ds : privatedirectory) {
			l.add(getPrivateProfile(ds));
		}
		gerenmulu.setChildren(l);
		extTreeDatas.add(gerenmulu);
	}

	private ExtTreeData getPrivateProfile(DirectorySetting ds) {
		ExtTreeData gerenmulu = new MyProfileTreeData(new DirectorySettingVo(
				updateDirectorySetting(ds)));
		if (ds.getId() != null) {
			List<DirectorySetting> privatedirectory = directorySettingService
					.findchildByparentId(ds.getId());
			List<ExtTreeData> list = new ArrayList<ExtTreeData>();
			for (DirectorySetting dss : privatedirectory) {
				list.add(getPrivateProfile(dss));
			}
			gerenmulu.setChildren(list);
		}
		return gerenmulu;
	}

	private void addResult(List<DirectorySetting> directorySettings) {
		if (directorySettings != null) {
			for (DirectorySetting directorySetting : directorySettings) {
				result.add(new DirectorySettingVo(
						updateDirectorySetting(directorySetting)));
				result.addAll(exchangeToDirectorySettingVo(directorySettingService
						.findDirectorySettingByParentId(directorySetting
								.getId())));
			}
		}
	}

	public DirectorySetting getDirectorySetting() {
		return directorySetting;
	}

	public void setDirectorySetting(DirectorySetting directorySetting) {
		this.directorySetting = directorySetting;
	}

	public DirectorySettingVo getDirectorySettingVo() {
		return directorySettingVo;
	}

	public void setDirectorySettingVo(DirectorySettingVo directorySettingVo) {
		this.directorySettingVo = directorySettingVo;
	}

	public DirectorySetting getParentDirect() {
		return parentDirect;
	}

	public void setParentDirect(DirectorySetting parentDirect) {
		this.parentDirect = parentDirect;
	}

	public String getMoveMode() {
		return moveMode;
	}

	public void setMoveMode(String moveMode) {
		this.moveMode = moveMode;
	}

	public List<ExtTreeData> getExtTreeDatas() {
		return extTreeDatas;
	}

	public void setExtTreeDatas(List<ExtTreeData> extTreeDatas) {
		this.extTreeDatas = extTreeDatas;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public List<DirectorySetting> getDirectorySettings() {
		return directorySettings;
	}

	public void setDirectorySettings(List<DirectorySetting> directorySettings) {
		this.directorySettings = directorySettings;
	}

}
