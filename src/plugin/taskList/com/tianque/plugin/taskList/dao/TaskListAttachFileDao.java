package com.tianque.plugin.taskList.dao;

import java.util.List;
import java.util.Map;

import com.tianque.plugin.taskList.domain.TaskListAttachFile;

/**
 * 任务清单附件dao层
 * @author lanhaifeng
 *
 */
public interface TaskListAttachFileDao {
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
	 * @param id 附件id
	 */
	public void deleteTaskListAttachFileById(Long id);

	/**
	 * 删除附件
	 * @param data 该附件的功能模块对象id和功能模块对象名
	 */
	public void deleteTaskListAttachFilesByBusinessId(Map<String, Object> data);

	/**
	 * 删除附件
	 * @param ids 附件id集合
	 */
	public void deleteTaskListAttachFilesByIds(List<Long> ids);

	/**
	 * 删除附件
	 * @param ids 附件的功能模块对象id集合
	 */
	public void deleteTaskListAttachFilesByBusinessIds(Map<String, Object> data);

	/**
	 * 查询附件集合
	 * @param data 该附件的功能模块对象id和功能模块对象名
	 * @return
	 */
	public List<TaskListAttachFile> queryTaskListAttachFilesByBusinessId(Map<String, Object> data);
}
