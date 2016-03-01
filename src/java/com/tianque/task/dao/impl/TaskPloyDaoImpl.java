package com.tianque.task.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.task.dao.TaskPloyDao;
import com.tianque.task.domain.TaskPloy;

/**
 * 任务数据库操作实现类
 * 
 * @ClassName: TaskDaoImpl
 * @Description: TODO(描述)
 * @author 王乐
 * @date 2013-06-07 16:04:00
 * */
@Repository("taskPloyDao")
public class TaskPloyDaoImpl extends AbstractBaseDao implements TaskPloyDao {

	@Override
	public TaskPloy getTaskPloyById(TaskPloy taskPloy) {
		return (TaskPloy) getSqlMapClientTemplate().queryForObject(
				"taskPloy.getTaskPloyById", taskPloy);
	}

	@Override
	public TaskPloy addTaskPloy(TaskPloy taskPloy) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"taskPloy.addTaskPloy", taskPloy);
		taskPloy.setId(id);
		return getTaskPloyById(taskPloy);
	}

	@Override
	public TaskPloy updateTaskPloyById(TaskPloy taskPloy) {
		getSqlMapClientTemplate().update("taskPloy.updateTaskPloyById",
				taskPloy);
		return getTaskPloyById(taskPloy);
	}

	@Override
	public void deleteTaskPloyById(TaskPloy taskPloy) {
		getSqlMapClientTemplate().delete("taskPloy.deleteTaskPloyById",
				taskPloy);
	}

	@Override
	public void deleteTaskPloyByIds(List<TaskPloy> taskPloys) {
		getSqlMapClientTemplate().delete("taskPloy.deleteTaskPloyByIds",
				taskPloys);
	}

	@Override
	public PageInfo<TaskPloy> findTaskPloyPage(TaskPloy taskPloy, Integer page,
			Integer rows, String sidx, String sord) {
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"taskPloy.selectPageListCount", taskPloy);
		List<TaskPloy> list = getSqlMapClientTemplate().queryForList(
				"taskPloy.selectPageList", taskPloy, (page - 1) * rows, rows);
		return new PageInfo(page, rows, countNum, list);
	}

}
