package com.tianque.openLayersMap.positioningTrajectory.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.openLayersMap.positioningTrajectory.domian.PositioningTrajectory;
import com.tianque.openLayersMap.positioningTrajectory.domian.vo.SearchPositioningTrajectoryVo;
import com.tianque.openLayersMap.positioningTrajectory.service.PositioningTrajectoryService;

@SuppressWarnings("serial")
@Controller("positioningTrajectoryController")
@Scope("prototype")
@Namespace("/baseinfo/positioningTrajectoryManage")
public class PositioningTrajectoryController extends BaseAction {
	@Autowired
	private PositioningTrajectoryService positioningTrajectoryService;
	private PositioningTrajectory positioningTrajectory;
	private String deviceNumber;
	private SearchPositioningTrajectoryVo searchPositioningTrajectoryVo;
	private String positionId;
	private String dialogName;
	private String userName;

	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/openLayersMap/gps/maintainGpsViewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String dispatch() throws Exception {
		if (positionId != null && !positionId.trim().equals("")) {
			positioningTrajectory = positioningTrajectoryService.getPositioningTrajectoryById(Long
					.parseLong(positionId));
		}
		return SUCCESS;
	}

	@Action(value = "addPositioningTrajectory", results = {
			@Result(name = "success", type = "json", params = { "root", "positioningTrajectory",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String addPositioningTrajectory() throws Exception {
		if (userName != null) {
			positioningTrajectory.setUserName(userName);
		}
		positioningTrajectory = positioningTrajectoryService
				.addPositioningTrajectory(positioningTrajectory);
		return SUCCESS;
	}

	@Action(value = "deletePositioningTrajectoryByDeviceNumber", results = {
			@Result(name = "success", type = "json", params = { "root", "deviceNumber" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String deleteByDeviceNumber() throws Exception {
		positioningTrajectoryService.deletePositioningTrajectoryByDeviceNumber(deviceNumber);
		return SUCCESS;
	}

	@Action(value = "viewPositioningTrajectoryById", results = {
			@Result(name = "success", type = "json", params = { "root", "positioningTrajectory",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String viewPositioningTrajectory() throws Exception {
		positioningTrajectory = positioningTrajectoryService
				.getPositioningTrajectoryByDeviceNumber(positioningTrajectory.getId());
		return SUCCESS;
	}

	@Action(value = "positioningTrajectoryList", results = {
			@Result(type = "json", name = "success", params = { "root", "gridPage",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String findGridPageByDeviceNumberAndLocateDate() throws Exception {
		if (searchPositioningTrajectoryVo == null) {
			searchPositioningTrajectoryVo = new SearchPositioningTrajectoryVo();
		}
		gridPage = new GridPage(
				positioningTrajectoryService.findPositioningTrajectoryByDeviceNumberAndLocateDate(
						searchPositioningTrajectoryVo, page, rows, sidx, sord));
		return SUCCESS;
	}

	public PositioningTrajectory getPositioningTrajectory() {
		return positioningTrajectory;
	}

	public void setPositioningTrajectory(PositioningTrajectory positioningTrajectory) {
		this.positioningTrajectory = positioningTrajectory;
	}

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	public SearchPositioningTrajectoryVo getSearchPositioningTrajectoryVo() {
		return searchPositioningTrajectoryVo;
	}

	public void setSearchPositioningTrajectoryVo(
			SearchPositioningTrajectoryVo searchPositioningTrajectoryVo) {
		this.searchPositioningTrajectoryVo = searchPositioningTrajectoryVo;
	}

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	public String getDialogName() {
		return dialogName;
	}

	public void setDialogName(String dialogName) {
		this.dialogName = dialogName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
