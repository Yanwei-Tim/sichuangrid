package com.tianque.plugin.judgmentAnalysis.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.analysis.api.BusinessModelDubboService;
import com.tianque.analysis.api.DimensionCombinationDubboService;
import com.tianque.analysis.api.ScheduleJobDubboService;
import com.tianque.core.base.BaseAction;
import com.tianque.core.exception.ServiceException;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.judgmentAnalysis.domain.BusinessModel;
import com.tianque.plugin.judgmentAnalysis.domain.DimensionCombination;
import com.tianque.plugin.judgmentAnalysis.domain.ScheduleJob;

@Scope("prototype")
@Namespace("/judgmentAnalysis/scheduleJob")
@Controller("scheduleJobController")
public class ScheduleJobController extends BaseAction {
	private static Logger logger = LoggerFactory
			.getLogger(ScheduleJobController.class);

	@Autowired
	private ScheduleJobDubboService scheduleJobService;
	@Autowired
	private BusinessModelDubboService businessModelService;
	@Autowired
	private DimensionCombinationDubboService dimensionCombinationService;

	private String modelIds;
	private String modelIdStr;
	private List<BusinessModel> businessModelList;

	private String combinationIds;
	private String combinationIdStr;
	private List<DimensionCombination> combinations;

	private ScheduleJob scheduleJob;

	@Action(value = "findScheduleJobForPage", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "excludeNullProperties", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findScheduleJobForPage() {
		try {
			PageInfo<ScheduleJob> pageInfo = scheduleJobService
					.findScheduleJobForPage(page, rows, sidx, sord, scheduleJob);
			gridPage = new GridPage(pageInfo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "操作失败，请联系管理员";
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * @return 跳转页面
	 */
	@Actions(value = {
			@Action(value = "updateScheduleJobView", results = {
					@Result(name = "success", location = "/template/judgmentAnalysis/scheduleJob/maintainScheduleJob.ftl"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "addScheduleJobView", results = {
					@Result(name = "success", location = "/template/judgmentAnalysis/scheduleJob/maintainScheduleJob.ftl"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "viewScheduleJob", results = {
					@Result(name = "success", location = "/template/judgmentAnalysis/scheduleJob/maintainScheduleJob.ftl"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String dispatch() {
		try {
			if (DialogMode.EDIT_MODE.equalsIgnoreCase(getMode())
					|| DialogMode.VIEW_MODE.equalsIgnoreCase(getMode())) {
				if (scheduleJob == null || scheduleJob.getId() == null) {
					errorMessage = "请填写数据！";
					return ERROR;
				}
				scheduleJob = this.scheduleJobService
						.getScheduleJobById(scheduleJob.getId());
				return SUCCESS;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "操作失败，请联系管理员";
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 添加
	 * 
	 * @return
	 */
	@Actions(value = {
			@Action(value = "addScheduleJob", results = {
					@Result(name = "success", type = "json", params = { "root",
							"scheduleJob", "excludeNullProperties", "true",
							"ignoreHierarchy", "false" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "updateScheduleJob", results = {
					@Result(name = "success", type = "json", params = { "root",
							"scheduleJob", "excludeNullProperties", "true",
							"ignoreHierarchy", "false" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String operatorScheduleJob() {
		try {
			if (scheduleJob == null) {
				errorMessage = "请填写数据！";
				return ERROR;
			}
			if (DialogMode.EDIT_MODE.equalsIgnoreCase(getMode())) {
				if (scheduleJob.getId() == null) {
					errorMessage = "请填写数据！";
					return ERROR;
				}
				scheduleJob = scheduleJobService.updateScheduleJob(scheduleJob);
			} else if (DialogMode.ADD_MODE.equalsIgnoreCase(getMode())) {
				scheduleJob = scheduleJobService.addScheduleJob(scheduleJob);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "操作失败，请联系管理员";
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@Action(value = "deleteScheduleJobById", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteScheduleJobById() {
		try {
			if (id == null) {
				errorMessage = "请选择要删除的数据！";
				return null;
			}
			this.scheduleJobService.deleteScheduleJobById(id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "操作失败，请联系管理员";
			return ERROR;
		}
		return SUCCESS;
	}

	@Action(value = "findBusinessModelNames", results = {
			@Result(name = "success", type = "json", params = { "root",
					"businessModelList" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findBusinessModelNames() {
		try {
			if (null == businessModelList) {
				businessModelList = new ArrayList<BusinessModel>();
			}
			String[] expertIdsStr = modelIds.split(",");
			if (expertIdsStr != null && expertIdsStr.length != 0) {
				for (int i = 0; i < expertIdsStr.length; i++) {
					businessModelList.add(businessModelService
							.getBusinessModelById(Long
									.parseLong(expertIdsStr[i])));
				}
			}
			return SUCCESS;
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "操作失败，请联系管理员";
			return ERROR;
		}
	}

	@Action(value = "scheduleJobStart", results = { @Result(name = "success", type = "json", params = {
			"root", "true" }) })
	public String scheduleJobStart() throws Exception {
		scheduleJobService.scheduleJobStart(analyze(combinationIds));
		return SUCCESS;
	}

	@Action(value = "scheduleJobStop", results = { @Result(name = "success", type = "json", params = {
			"root", "true" }) })
	public String scheduleJobStop() throws Exception {
		scheduleJobService.scheduleJobStop(analyze(combinationIds));
		return SUCCESS;
	}

	@Action(value = "scheduleJobIsStart", results = { @Result(name = "success", type = "json", params = {
			"root", "combinationIds" }) })
	public String scheduleJobIsStart() throws Exception {
		combinationIds = scheduleJobService
				.scheduleJobIsStart(analyze(combinationIds));
		return SUCCESS;
	}

	@Action(value = "scheduleJobSync", results = { @Result(name = "success", type = "json", params = {
			"root", "true" }) })
	public String scheduleJobSync() throws Exception {
		scheduleJobService.scheduleJobSync();
		return SUCCESS;
	}

	/**
	 * 查询维度组合
	 * 
	 * @return
	 */
	@Action(value = "findCombinationNames", results = {
			@Result(name = "success", type = "json", params = { "root",
					"combinations" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findCombinationNames() {
		try {
			if (null == combinations) {
				combinations = new ArrayList<DimensionCombination>();
			}
			String[] combinationIdStr = combinationIds.split(",");
			if (combinationIdStr != null && combinationIdStr.length != 0) {
				for (int i = 0; i < combinationIdStr.length; i++) {
					combinations.add(dimensionCombinationService
							.getDimensionCombinationById(Long
									.parseLong(combinationIdStr[i])));
				}
			}
			return SUCCESS;
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "操作失败，请联系管理员";
			return ERROR;
		}
	}

	public ScheduleJob getScheduleJob() {
		return scheduleJob;
	}

	public void setScheduleJob(ScheduleJob scheduleJob) {
		this.scheduleJob = scheduleJob;
	}

	public String getModelIds() {
		return modelIds;
	}

	public void setModelIds(String modelIds) {
		this.modelIds = modelIds;
	}

	public List<BusinessModel> getBusinessModelList() {
		return businessModelList;
	}

	public void setBusinessModelList(List<BusinessModel> businessModelList) {
		this.businessModelList = businessModelList;
	}

	public String getCombinationIds() {
		return combinationIds;
	}

	public void setCombinationIds(String combinationIds) {
		this.combinationIds = combinationIds;
	}

	public List<DimensionCombination> getCombinations() {
		return combinations;
	}

	public void setCombinations(List<DimensionCombination> combinations) {
		this.combinations = combinations;
	}

	public String getModelIdStr() {
		return modelIdStr;
	}

	public void setModelIdStr(String modelIdStr) {
		this.modelIdStr = modelIdStr;
	}

	public String getCombinationIdStr() {
		return combinationIdStr;
	}

	public void setCombinationIdStr(String combinationIdStr) {
		this.combinationIdStr = combinationIdStr;
	}

}
