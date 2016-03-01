package com.tianque.task.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.task.dao.TaskPloyDao;
import com.tianque.task.domain.TaskPloy;
import com.tianque.task.service.TaskPloyService;
import com.tianque.task.service.TaskService;

@Service("taskPloyService")
@Transactional
public class TaskPloyServiceImpl implements TaskPloyService {
	@Autowired
	private TaskPloyDao taskPloyDao;
	@Autowired
	private TaskService taskService;
	@Autowired
	private PropertyDictService propertyDictService;

	@Override
	public TaskPloy getTaskPloyById(TaskPloy taskPloy) {
		validateTaskPloy(GET, taskPloy);
		try {
			taskPloy = taskPloyDao.getTaskPloyById(taskPloy);
			setType(taskPloy);
			return taskPloy;
		} catch (Exception e) {
			throw new ServiceValidationException("获取策略类型失败", e);
		}
	}

	@Override
	public TaskPloy addTaskPloy(TaskPloy taskPloy) {
		validateTaskPloy(ADD, taskPloy);
		try {
			taskPloy = taskPloyDao.addTaskPloy(taskPloy);
			setType(taskPloy);
			return taskPloy;
		} catch (Exception e) {
			throw new ServiceValidationException("新增策略类型失败", e);
		}
	}

	@Override
	public TaskPloy updateTaskPloyById(TaskPloy taskPloy) {
		validateTaskPloy(UPDATE, taskPloy);
		try {
			taskPloy = taskPloyDao.updateTaskPloyById(taskPloy);
			setType(taskPloy);
			return taskPloy;
		} catch (Exception e) {
			throw new ServiceValidationException("修改策略类型失败", e);
		}
	}

	@Override
	public void deleteTaskPloyById(TaskPloy taskPloy) {
		validateTaskPloy(DELETE, taskPloy);
		try {
			taskPloyDao.deleteTaskPloyById(taskPloy);
		} catch (Exception e) {
			throw new ServiceValidationException("删除策略类型失败", e);
		}
	}

	@Override
	public void deleteTaskPloyByIds(List<TaskPloy> taskPloys) {
		if (taskPloys == null || taskPloys.size() <= 0) {
			throw new BusinessValidationException(ERROR_ID);
		}
		try {
			taskPloyDao.deleteTaskPloyByIds(taskPloys);
		} catch (Exception e) {
			throw new ServiceValidationException("删除策略类型失败", e);
		}
	}

	@Override
	public PageInfo<TaskPloy> findTaskPloyPage(TaskPloy taskPloy, Integer page,
			Integer rows, String sidx, String sord) {
		try {
			PageInfo<TaskPloy> pageInfo = taskPloyDao.findTaskPloyPage(
					taskPloy, page, rows, sidx, sord);
			for (TaskPloy tp : pageInfo.getResult()) {
				setType(tp);
			}
			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException("获取策略类型列表失败", e);
		}
	}

	/**
	 * 验证domain的一些属性值
	 * 
	 * @param task
	 *            Task domain实例
	 * @return void
	 * */
	public void validateTaskPloy(Integer type, TaskPloy taskPloy) {

	}

	/**
	 * 设置策略类型属性
	 * 
	 * @parama taskPloy TaskPloy 要设置的策略
	 * */
	private void setType(TaskPloy taskPloy) {
		if (taskPloy != null && taskPloy.getType() != null) {
			taskPloy.setType(propertyDictService.getPropertyDictById(taskPloy
					.getType().getId()));
		}
	}

}
