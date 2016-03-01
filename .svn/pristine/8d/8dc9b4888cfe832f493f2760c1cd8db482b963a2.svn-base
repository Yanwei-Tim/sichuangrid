package com.tianque.task.service.impl;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.task.dao.TaskDao;
import com.tianque.task.domain.Task;
import com.tianque.task.schedule.SchedulerMain;
import com.tianque.task.service.TaskPloyService;
import com.tianque.task.service.TaskService;

/**
 * 任务服务层实现
 * */
@Service("taskService")
@Transactional
public class TaskServiceImpl implements TaskService, ApplicationContextAware {
	@Autowired
	private TaskDao taskDao;
	@Autowired
	private TaskPloyService taskPloyService;
	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public Task getTaskById(Task task) {
		validateTask(GET, task);
		try {
			task = taskDao.getTaskById(task);
			setFrenquency(task);
			setTaskPloy(task);
			return task;
		} catch (Exception e) {
			throw new ServiceValidationException("获取Task任务失败", e);
		}
	}

	@Override
	public Task addTask(Task task) {
		validateTask(ADD, task);
		try {
			task = taskDao.addTask(task);
			setFrenquency(task);
			setTaskPloy(task);
			return task;
		} catch (Exception e) {
			throw new ServiceValidationException("新增Task任务失败", e);
		}
	}

	@Override
	public Task updateTaskById(Task task) {
		validateTask(UPDATE, task);
		try {
			task = taskDao.updateTaskById(task);
			setFrenquency(task);
			setTaskPloy(task);
			return task;
		} catch (Exception e) {
			throw new ServiceValidationException("更新Task任务失败", e);
		}
	}

	@Override
	public Task changeTaskById(Task task) {
		validateTask(CHANGE, task);
		try {
			task = taskDao.changeTaskById(task);
			// setFrenquency(task);
			setTaskPloy(task);
			doTask(task);
			return task;
		} catch (Exception e) {
			throw new ServiceValidationException("变更Task任务失败", e);
		}
	}

	@Override
	public void deleteTaskById(Task task) {
		validateTask(DELETE, task);
		try {
			// 删除前先获取任务
			task = getTaskById(task);
			taskDao.deleteTaskById(task);
			deleteTask(task);
		} catch (Exception e) {
			throw new ServiceValidationException("删除Task任务失败", e);
		}
	}

	@Override
	public void deleteTaskByIds(List<Task> tasks) {
		if (null == tasks || tasks.size() == 0) {
			throw new BusinessValidationException(ERROR_ID);
		}
		try {
			taskDao.deleteTaskByIds(tasks);
		} catch (Exception e) {
			throw new ServiceValidationException("删除Task任务失败", e);
		}
	}

	@Override
	public PageInfo<Task> findTaskPage(Task task, Integer page, Integer rows,
			String sidx, String sord) {
		try {
			PageInfo<Task> pageInfo = taskDao.findTaskPage(task, page, rows,
					sidx, sord);
			for (Task t : pageInfo.getResult()) {
				setTaskPloy(t);
			}
			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException("获取Task任务列表失败", e);
		}
	}

	public void afterStartRunTask() {
		try {
			List<Task> list = taskDao.getAllTask();
			for (Task t : list) {
				// setFrenquency(t);
				setTaskPloy(t);
				doTask(t);
			}
		} catch (Exception e) {
			throw new ServiceValidationException(e.getMessage(), e);
		}
	}

	/**
	 * 设置间隔属性
	 * 
	 * @param task
	 *            Task 要设置的任务
	 * */
	private void setFrenquency(Task task) {
		// if (task.getFrequency() != null) {
		// task.setFrequency(propertyDictService.getPropertyDictById(task
		// .getFrequency().getId()));
		// }
	}

	/**
	 * 设置策略属性
	 * 
	 * @param task
	 *            Task 要设置的任务
	 * */
	private void setTaskPloy(Task task) {
		if (task.getTaskPloy() != null) {
			task.setTaskPloy(taskPloyService.getTaskPloyById(task.getTaskPloy()));
		}
	}

	/**
	 * 执行任务
	 * 
	 * @param task
	 *            Task 要执行的任务
	 * */
	private void doTask(Task task) {
		try {
			if (task.getClosed() == 1) {
				SchedulerMain
						.getInstance()
						.run()
						.taskStart(
								task,
								taskPloyService.getTaskPloyById(task
										.getTaskPloy()));
			} else {
				SchedulerMain.getInstance().run().taskStop(task);
			}
		} catch (Exception e) {
			throw new ServiceValidationException("执行Task任务失败", e);
		}
	}

	/**
	 * 删除任务
	 * 
	 * @param task
	 *            Task 要删除的任务
	 * */
	private void deleteTask(Task task) {
		if (task != null) {
			try {
				SchedulerMain.getInstance().run().taskClean(task);
			} catch (Exception e) {
				throw new ServiceValidationException("删除Task任务失败", e);
			}
		}
	}

	/**
	 * 验证domain的一些属性值
	 * 
	 * @param task
	 *            Task domain实例
	 * @return void
	 * */
	private void validateTask(Integer type, Task task) {
		if ((ADD == type && task == null)
				|| (ADD != type && (task == null || task.getId() <= 0))) {
			throw new BusinessValidationException(ERROT_ARGUMENT);
		}
		if (UPDATE == type && task.getClosed() == 1) {
			throw new BusinessValidationException(ERROT_STATUS);
		}
		if (DELETE == type
				&& (task.getId() == null || taskDao.getTaskById(task) == null)) {
			throw new BusinessValidationException(NO_RESULT);
		}
		if (ADD == type || UPDATE == type) {
			if (task.getName() == null || "".equals(task.getName().trim())
					|| task.getName().trim().length() > 50) {
				throw new BusinessValidationException(ERROR_NAME);
			}
			// if (task.getStartDate() == null) {
			// task.setStartDate(new Date());
			// }
			// if (task.getEndDate() != null
			// && task.getStartDate().after(task.getEndDate())) {
			// throw new BusinessValidationException(STARTDATE_GRANT_ENDDATE);
			// }
		}
		// if (CHANGE == type) {
		// if (task.getEndDate() != null
		// && task.getEndDate().before(new Date())) {
		// throw new BusinessValidationException(HAS_ENDED);
		// }
		// }
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
}
