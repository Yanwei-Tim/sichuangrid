package com.tianque.plugin.taskList.service;

import java.util.List;

import com.tianque.plugin.taskList.domain.TaskListAttachFile;

public interface TaskListAttachFileService {
	/**
	 * 新增附件
	 * @param taskListAttachFile  附件domain
	 * @return
	 */
	public TaskListAttachFile addTaskListAttachFile(TaskListAttachFile taskListAttachFile);

	/**
	 * 获取附件信息
	 * @param id 附件id
	 * @return
	 */
	public TaskListAttachFile getTaskListAttachFileById(Long id);

	/**
	 * 删除附件
	 * @param ids 附件的功能模块对象id集合
	 */
	public void deleteTaskListAttachFilesByBusinessIds(List<Long> ids, String moduleKey);

	/**
	 * 删除附件
	 * @param id 附件id
	 */
	public void deleteTaskListAttachFileById(Long id);

	/**
	 * 删除附件
	 * @param bussinessId 该附件的功能模块对象id
	 * @param moduleKey 功能模块名
	 */
	public void deleteTaskListAttachFilesByBusinessId(Long bussinessId, String moduleKey);

	/**
	 * 删除附件
	 * @param ids 附件id集合
	 */
	public void deleteTaskListAttachFilesByIds(List<Long> ids);

	/**
	 * 查询附件集合
	 * @param bussinessId 该附件的功能模块对象id
	 * @param moduleKey 功能模块名
	 * @return
	 */
	public List<TaskListAttachFile> queryTaskListAttachFilesByBusinessId(Long bussinessId,
			String moduleKey);
}
