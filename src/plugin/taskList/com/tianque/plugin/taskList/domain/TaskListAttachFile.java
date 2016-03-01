package com.tianque.plugin.taskList.domain;

import com.tianque.core.base.BaseDomain;

/**
 * 任务清单附件domain
 * @author lanhaifeng
 *
 */
public class TaskListAttachFile extends BaseDomain {
	/** 使用到该文件的功能模块对象ID */
	private Long businessId;
	/** 附件名称 */
	private String fileName;
	/** 附件地址 */
	private String physicsFullFileName;
	/** 使用到该文件的功能模块 **/
	private String moduleKey;
	
	public Long getBusinessId() {
		return businessId;
	}
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getPhysicsFullFileName() {
		return physicsFullFileName;
	}
	public void setPhysicsFullFileName(String physicsFullFileName) {
		this.physicsFullFileName = physicsFullFileName;
	}
	public String getModuleKey() {
		return moduleKey;
	}
	public void setModuleKey(String moduleKey) {
		this.moduleKey = moduleKey;
	}
	
}