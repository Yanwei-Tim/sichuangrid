package com.tianque.plugin.taskList.daoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.plugin.taskList.dao.TaskListAttachFileDao;
import com.tianque.plugin.taskList.domain.TaskListAttachFile;

/**
 * 任务清单附件实现Dao
 * @author GAOHU
 *
 */
@Repository("taskListAttachFileDao")
public class TaskListAttachFileDaoImpl extends AbstractBaseDao implements TaskListAttachFileDao {

	@Override
	public TaskListAttachFile addTaskListAttachFile(TaskListAttachFile taskListAttachFile) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"taskListAttachFile.addTaskListAttachFile", taskListAttachFile);
		return getTaskListAttachFileById(id);
	}

	@Override
	public TaskListAttachFile getTaskListAttachFileById(Long id) {
		return (TaskListAttachFile) getSqlMapClientTemplate().queryForObject(
				"taskListAttachFile.getTaskListAttachFileById", id);
	}

	@Override
	public void deleteTaskListAttachFileById(Long id) {
		getSqlMapClientTemplate().delete("taskListAttachFile.deleteTaskListAttachFileById", id);
	}

	@Override
	public void deleteTaskListAttachFilesByBusinessId(Map<String, Object> data) {
		getSqlMapClientTemplate().delete("taskListAttachFile.deleteTaskListAttachFileByBusinessId",
				data);
	}

	@Override
	public void deleteTaskListAttachFilesByIds(List<Long> ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		getSqlMapClientTemplate().delete("taskListAttachFile.deleteTaskListAttachFileByIds", map);
	}

	@Override
	public void deleteTaskListAttachFilesByBusinessIds(Map<String, Object> data) {
		getSqlMapClientTemplate().delete(
				"taskListAttachFile.deleteTaskListAttachFileByBusinessIds", data);
	}

	@Override
	public List<TaskListAttachFile> queryTaskListAttachFilesByBusinessId(Map<String, Object> data) {
		return getSqlMapClientTemplate().queryForList(
				"taskListAttachFile.queryTaskListAttachFilesByBusinessId", data);

	}

}
