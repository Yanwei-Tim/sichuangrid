package com.tianque.task.domain;

import com.tianque.core.base.BaseDomain;

/**
 * 任务
 * 
 * @ClassName: Task
 * @Description: TODO(描述)
 * @author 王乐
 * @date 2013-06-07 16:04:00
 * */
public class Task extends BaseDomain {
	private String name;
	private String taskGroup;
	private String description;
	private TaskPloy taskPloy;
	private String config;
	private Integer closed;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTaskGroup() {
		return taskGroup;
	}

	public void setTaskGroup(String taskGroup) {
		this.taskGroup = taskGroup;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TaskPloy getTaskPloy() {
		return taskPloy;
	}

	public void setTaskPloy(TaskPloy taskPloy) {
		this.taskPloy = taskPloy;
	}

	public String getConfig() {
		return config;
	}

	public void setConfig(String config) {
		this.config = config;
	}

	public Integer getClosed() {
		return closed;
	}

	public void setClosed(Integer closed) {
		this.closed = closed;
	}

}
