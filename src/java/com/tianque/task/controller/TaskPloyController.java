package com.tianque.task.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.task.domain.TaskPloy;
import com.tianque.task.service.TaskPloyService;

@Transactional
@Scope("prototype")
@Namespace("/task/taskPloyManage")
@Controller("taskPloyController")
public class TaskPloyController extends BaseAction {
	@Autowired
	private TaskPloyService taskPloyService;
	private TaskPloy taskPloy;
	private List<TaskPloy> taskPloys;
	public static String introduction;

	@Action(value = "dispatch", results = {
			@Result(name = "add", location = "/task/taskPloyDlg.jsp"),
			@Result(name = "edit", location = "/task/taskPloyDlg.jsp"),
			@Result(name = "view", location = "/task/viewTaskPloy.jsp"),
			@Result(name = "read", location = "/task/viewIntroduction.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatch() throws Exception {
		if (DialogMode.EDIT_MODE.equals(mode)
				|| DialogMode.VIEW_MODE.equals(mode)) {
			taskPloy = taskPloyService.getTaskPloyById(taskPloy);
		}
		if ("read".equals(mode)) {
			readWord();
		}
		return mode;
	}

	// 读取任务的使用简介
	private void readWord() {
		try {
			FileInputStream fis = new FileInputStream(new File(
					"document/jobIntroduction.doc"));
			WordExtractor we = new WordExtractor(fis);
			// introduction = we.getText();
			// introduction = introduction.replace("    ", " <br>");
			introduction = "";
			String[] paragraph = we.getParagraphText();
			for (int i = 0; i < paragraph.length; i++) {
				introduction = introduction + paragraph[i] + "<br>";
			}
		} catch (Exception e) {
			throw new ServiceValidationException(e.getMessage(), e);
		}
	}

	@Action(value = "taskPloyList", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String taskPloyList() throws Exception {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("taskPloy", taskPloy);
		PageInfo<TaskPloy> pageInfo;
		pageInfo = taskPloyService.findTaskPloyPage(taskPloy, page, rows, sidx,
				sord);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	@Action(value = "addTaskPloy", results = { @Result(name = "success", type = "json", params = {
			"root", "taskPloy", "ignoreHierarchy", "false" }) })
	public String addTask() throws Exception {
		taskPloy = taskPloyService.addTaskPloy(taskPloy);
		return SUCCESS;
	}

	@Action(value = "updateTaskPloy", results = { @Result(name = "success", type = "json", params = {
			"root", "taskPloy", "ignoreHierarchy", "false" }) })
	public String updateTaskPloy() throws Exception {
		taskPloy = taskPloyService.updateTaskPloyById(taskPloy);
		return SUCCESS;
	}

	@Action(value = "deleteTaskPloy", results = { @Result(name = "success", type = "json", params = {
			"root", "true" }) })
	public String deleteTask() throws Exception {
		taskPloyService.deleteTaskPloyById(taskPloy);
		return SUCCESS;
	}

	@Action(value = "deleteTaskPloys", results = { @Result(name = "success", type = "json", params = {
			"root", "true" }) })
	public String deleteTaskPloys() throws Exception {
		taskPloyService.deleteTaskPloyByIds(taskPloys);
		return SUCCESS;
	}

	/**
	 * 错误验证
	 * 
	 * @param e
	 *            Exception 错误信息
	 * */
	private String validateError(Exception e) {
		String msg = e.getMessage();
		e.printStackTrace();// 测试
		if (msg != null && msg.toLowerCase().indexOf("sql") != -1) {
			msg = "数据操作错误了，请联系管理员";
		}
		return msg;
	}

	public TaskPloy getTaskPloy() {
		return taskPloy;
	}

	public void setTaskPloy(TaskPloy taskPloy) {
		this.taskPloy = taskPloy;
	}

	public List<TaskPloy> getTaskPloys() {
		if (null == taskPloys) {
			taskPloys = new ArrayList<TaskPloy>();
		}
		return taskPloys;
	}

	public void setTaskPloys(List<TaskPloy> taskPloys) {
		this.taskPloys = taskPloys;
	}

	public static String getIntroduction() {
		return introduction;
	}

	public static void setIntroduction(String introduction) {
		TaskPloyController.introduction = introduction;
	}

}
