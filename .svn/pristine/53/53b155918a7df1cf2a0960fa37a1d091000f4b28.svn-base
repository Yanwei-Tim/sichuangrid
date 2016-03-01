package com.tianque.task.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.tianque.task.domain.Task;
import com.tianque.task.service.TaskService;

@Transactional
@Scope("prototype")
@Namespace("/task/taskManage")
@Controller("taskController")
public class TaskController extends BaseAction {
	@Autowired
	private TaskService taskService;
	private Task task;
	private List<Task> tasks;

	@Action(value = "dispatch", results = {
			@Result(name = "edit", location = "/task/taskDlg.jsp"),
			@Result(name = "add", location = "/task/taskDlg.jsp"),
			@Result(name = "view", location = "/task/viewTask.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatch() throws Exception {
		if (DialogMode.EDIT_MODE.equals(mode)
				|| DialogMode.VIEW_MODE.equals(mode)) {
			task = taskService.getTaskById(task);
		}
		return mode;
	}

	@Action(value = "taskList", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String taskList() throws Exception {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("task", task);
		PageInfo<Task> pageInfo;
		pageInfo = taskService.findTaskPage(task, page, rows, sidx, sord);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	@Action(value = "addTask", results = { @Result(name = "success", type = "json", params = {
			"root", "task", "ignoreHierarchy", "false" }) })
	public String addTask() throws Exception {
		task = taskService.addTask(task);
		return SUCCESS;
	}

	@Action(value = "updateTask", results = { @Result(name = "success", type = "json", params = {
			"root", "task", "ignoreHierarchy", "false" }) })
	public String updateTask() throws Exception {
		task = taskService.updateTaskById(task);
		return SUCCESS;
	}

	@Action(value = "changeTask", results = { @Result(name = "success", type = "json", params = {
			"root", "task", "ignoreHierarchy", "false" }) })
	public String changeTask() throws Exception {
		task = taskService.changeTaskById(task);
		return SUCCESS;
	}

	@Action(value = "deleteTask", results = { @Result(name = "success", type = "json", params = {
			"root", "true" }) })
	public String deleteTask() throws Exception {
		taskService.deleteTaskById(task);
		return SUCCESS;
	}

	@Action(value = "deleteTasks", results = { @Result(name = "success", type = "json", params = {
			"root", "true" }) })
	public String deleteTasks() throws Exception {
		taskService.deleteTaskByIds(tasks);
		return SUCCESS;
	}

	@Action(value = "reloadJobs", results = { @Result(name = "success", type = "json", params = {
			"root", "true" }) })
	public String reloadJobs() throws Exception {
		taskService.afterStartRunTask();
		return SUCCESS;
	}

	/**
	 * 错误验证处理
	 * 
	 * @param e
	 *            Exception 异常信息
	 * */
	private String validateError(Exception e) {
		String msg = e.getMessage();
		e.printStackTrace();// 测试
		if (msg != null && msg.toLowerCase().indexOf("sql") != -1) {
			msg = "数据操作错误了，请联系管理员";
		}
		return msg;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public List<Task> getTasks() {
		if (tasks == null) {
			tasks = new ArrayList<Task>();
		}
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

}
