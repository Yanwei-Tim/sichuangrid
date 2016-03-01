package com.tianque.task.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.task.dao.TaskDao;
import com.tianque.task.domain.Task;

/**
 * 任务数据库操作实现类
 * 
 * @ClassName: TaskDaoImpl
 * @Description: TODO(描述)
 * @author 王乐
 * @date 2013-06-07 16:04:00
 * */
@Repository("taskDao")
public class TaskDaoImpl extends AbstractBaseDao implements TaskDao {

	@Override
	public Task getTaskById(Task task) {
		return (Task) getSqlMapClientTemplate().queryForObject(
				"task.getTaskById", task);
	}

	@Override
	public Task addTask(Task task) {
		Long id = (Long) getSqlMapClientTemplate().insert("task.addTask", task);
		task.setId(id);
		return getTaskById(task);
	}

	@Override
	public Task updateTaskById(Task task) {
		getSqlMapClientTemplate().update("task.updateTaskById", task);
		return getTaskById(task);
	}

	@Override
	public Task changeTaskById(Task task) {
		// System.err.println(task.getId());
		getSqlMapClientTemplate().update("task.changeTaskById", task);
		return getTaskById(task);
	}

	@Override
	public boolean getTaskByPloyId(Task task) {
		Long count = (Long) getSqlMapClientTemplate().queryForObject(
				"task.getTaskByPloyId", task);
		return count > 0;
	}

	@Override
	public void deleteTaskById(Task task) {
		getSqlMapClientTemplate().delete("task.deleteTaskById", task);
	}

	@Override
	public void deleteTaskByIds(List<Task> tasks) {
		getSqlMapClientTemplate().delete("task.deleteTaskByIds", tasks);
	}

	@Override
	public PageInfo<Task> findTaskPage(Task task, Integer page, Integer rows,
			String sidx, String sord) {
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"task.selectPageListCount", task);
		List<Task> list = getSqlMapClientTemplate().queryForList(
				"task.selectPageList", task, (page - 1) * rows, rows);
		return new PageInfo(page, rows, countNum, list);
	}

	@Override
	public List getAllTask() {
		List<Task> list = getSqlMapClientTemplate().queryForList(
				"task.selectPageList");
		return list;
	}

}
