package com.tianque.baseInfo.permanentAddress.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.baseInfo.permanentAddress.domain.PermanentAddress;
import com.tianque.baseInfo.permanentAddress.domain.PermanentAddressLog;
import com.tianque.baseInfo.permanentAddress.job.service.SyncPermanentAddressJobService;
import com.tianque.baseInfo.permanentAddress.service.PermanentAddressLogService;
import com.tianque.baseInfo.permanentAddress.service.PermanentAddressService;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.sysadmin.service.JobMonitorService;

@Scope("prototype")
@Controller("permanentAddressManageController")
@Namespace("/baseinfo/permanentAddressManage")
public class PermanentAddressController extends BaseAction {
	@Autowired
	private PermanentAddressService permanentAddressService;
	@Autowired
	private PermanentAddressLogService permanentAddressLogService;
	@Autowired
	private SyncPermanentAddressJobService syncPermanentAddressJobService;
	@Autowired
	private JobMonitorService jobMonitorService;

	private PermanentAddress permanentAddress;
	private String permanentAddressStr = "";
	private String parentName;
	private String addressNo;
	private String mode;
	private String searchText;
	private PermanentAddressLog permanentAddressLog;
	private String ids;

	@Action(value = "getPermanentAddress", results = { @Result(name = "success", type = "json", params = {
			"root", "permanentAddressStr", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String getPermanentAddressByL() throws Exception {
		List<PermanentAddress> addressList = permanentAddressService
				.getPermanentAddressByLevel("1");
		for (PermanentAddress idCard : addressList) {
			permanentAddressStr += idCard.getAddressName() + ",";
		}
		return "success";
	}

	@Action(value = "getPermanentAddressByParentName", results = { @Result(name = "success", type = "json", params = {
			"root", "permanentAddressStr", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String getPermanentAddressByParentName() throws Exception {
		List<PermanentAddress> addressList = permanentAddressService
				.getPermanentAddressByParentName(parentName);
		for (PermanentAddress idCard : addressList) {
			permanentAddressStr += idCard.getAddressName() + ",";
		}
		return "success";
	}

	@Action(value = "getPermanentAddressByParentNameAndPId", results = { @Result(name = "success", type = "json", params = {
			"root", "permanentAddressStr", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String getPermanentAddressByParentNameAndPId() throws Exception {
		List<PermanentAddress> addressList = permanentAddressService
				.getPermanentAddressByParentNameAndPId(parentName);
		for (PermanentAddress idCard : addressList) {
			permanentAddressStr += idCard.getAddressName() + ",";
		}
		return "success";
	}

	@Action(value = "findPermanentAddress", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findPermanentAddress() throws Exception {
		gridPage = new GridPage(permanentAddressService.findPermanentAddress(
				page, rows, sidx, sord));
		return SUCCESS;
	}

	@PermissionFilter(ename = "searchPermanentAddr")
	@Action(value = "searchPermanentAddress", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String searchPermanentAddress() throws Exception {
		gridPage = new GridPage(permanentAddressService.searchPermanentAddress(
				searchText, page, rows, sidx, sord));
		return SUCCESS;
	}

	@PermissionFilter(ename = "updatePermanentAddr")
	@Action(value = "updatePermanentAddress", results = {
			@Result(name = "success", type = "json", params = { "root",
					"permanentAddress", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updatePermanentAddress() throws Exception {
		permanentAddress = permanentAddressService
				.updatePermanentAddress(permanentAddress);
		return SUCCESS;
	}

	@PermissionFilter(ename = "addPermanentAddr")
	@Action(value = "addPermanentAddress", results = {
			@Result(name = "success", type = "json", params = { "root",
					"permanentAddress", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addPermanentAddress() throws Exception {
		permanentAddress = permanentAddressService
				.addPermanentAddress(permanentAddress);
		return SUCCESS;
	}

	@Action(value = "permanentAddressClean", results = {
			@Result(name = "success", type = "json", params = { "root",
					"permanentAddressLog", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String permanentAddressClean() throws Exception {
		if (permanentAddress == null) {
			errorMessage = "操作失败，请重试";
			return ERROR;
		}

		if (permanentAddress.getAddressLevel() == null
				|| permanentAddress.getAddressLevel().trim().length() == 0) {
			errorMessage = "户籍地操作层级不存在，请重试";
			return ERROR;
		}
		permanentAddressLog = permanentAddressService
				.addPermanentAddressClean(permanentAddress);
		return SUCCESS;

	}

	/***
	 * 删除户籍地操作日志
	 * 
	 * @return
	 */
	@Action(value = "deleteAddressLogByIds", results = { @Result(name = "success", type = "json", params = {
			"root", "true" }) })
	public String deleteAddressLogByIds() throws Exception {
		if (ids == null) {
			errorMessage = "数据不存在，请重试";
			return ERROR;
		}
		permanentAddressLogService.deleteLogByIds(ids.split(","));
		return SUCCESS;
	}

	/***
	 * 手动执行户籍地清洗job
	 * 
	 * @return
	 */
	@Action(value = "executionJobByIds", results = { @Result(name = "success", type = "json", params = {
			"root", "true" }) })
	public String executionJobByIds() throws Exception {
		if (ids == null) {
			errorMessage = "所选数据ID未得到，请重试";
			return ERROR;
		}
		id = jobMonitorService.addJobMonitor("户籍地清洗job");
		try {
			long startTime = System.currentTimeMillis();
			syncPermanentAddressJobService.syncSelectPermanentAddress(ids
					.split(","));
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime) + "手动执行"
							+ "户籍地清洗的job", true);
		} catch (Exception e) {
			jobMonitorService.updateJobMonitor(id, "手动执行" + "户籍地清洗的job出现异常："
					+ e.getMessage(), false);
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "deletePermanentAddr")
	@Action(value = "deletePermanentAddress", results = { @Result(name = "success", type = "json", params = {
			"root", "true" }) })
	public String deletePermanentAddress() throws Exception {
		if (permanentAddress.getAddressNo() == null) {
			throw new BusinessValidationException("参数错误！");
		}
		permanentAddressService.deletePermanentAddress(permanentAddress
				.getAddressNo());
		return SUCCESS;
	}

	@PermissionFilter(ename = "searchPermanentAddr")
	@Action(value = "getPermanentAddressByLevel", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getPermanentAddressByLevel() throws Exception {
		gridPage = new GridPage(
				permanentAddressService.getPermanentAddressByLevel(
						permanentAddress.getAddressLevel(), page, rows, sidx,
						sord));
		return SUCCESS;
	}

	@Action(value = "doPermanentAddress", results = {
			@Result(name = "edit", location = "/sysadmin/permanentAddressManage/updatePermanentaddress.jsp"),
			@Result(name = "add", location = "/sysadmin/permanentAddressManage/addPermanentaddress.jsp"),
			@Result(name = "addressCleanLog", location = "/sysadmin/permanentAddressManage/permanentAddressCleanList.jsp"),
			@Result(name = "addressClean", location = "/sysadmin/permanentAddressManage/permanentAddressClean.jsp") })
	public String doPermanentAddress() throws Exception {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(this.mode)
				|| DialogMode.ADDRESS_CLEAN.equals(mode)
				|| DialogMode.ADDRESS_CLEANLOG.equals(mode)) {
			return this.mode;
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(this.mode)) {
			permanentAddress = permanentAddressService
					.getPermanentAddressByAddressNo(addressNo);
			return this.mode;
		}
		return SUCCESS;
	}

	@Action(value = "findPermanentAddressCleanLog", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findPermanentAddressCleanLog() throws Exception {
		PageInfo<PermanentAddressLog> pageInfo = permanentAddressLogService
				.findAllPermanentAddressLog(page, rows, sidx, sord);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	public String getPermanentAddressStr() {
		return permanentAddressStr;
	}

	public void setPermanentAddressStr(String permanentAddressStr) {
		this.permanentAddressStr = permanentAddressStr;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getAddressNo() {
		return addressNo;
	}

	public void setAddressNo(String addressNo) {
		this.addressNo = addressNo;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public PermanentAddress getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(PermanentAddress permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public PermanentAddressLog getPermanentAddressLog() {
		return permanentAddressLog;
	}

	public void setPermanentAddressLog(PermanentAddressLog permanentAddressLog) {
		this.permanentAddressLog = permanentAddressLog;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}
