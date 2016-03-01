package com.tianque.plugin.taskList.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.validate.ValidateResult;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.judgmentAnalysis.util.StringUtil;
import com.tianque.plugin.taskList.dao.TaskListAttachFileDao;
import com.tianque.plugin.taskList.domain.TaskListAttachFile;
import com.tianque.plugin.taskList.service.TaskListAttachFileService;
import com.tianque.plugin.taskList.validate.TaskListAttachFileValidatorImpl;

@Service("taskListAttachFileService")
@Transactional
public class TaskListAttachFileServiceImpl extends AbstractBaseService implements
		TaskListAttachFileService {
	@Autowired
	private TaskListAttachFileDao taskListAttachFileDao;
	@Autowired
	private TaskListAttachFileValidatorImpl taskListAttachFileValidator;

	@Override
	public TaskListAttachFile addTaskListAttachFile(TaskListAttachFile taskListAttachFile) {
		if (taskListAttachFile == null) {
			throw new BusinessValidationException("任务清单附件信息为空，新增附件失败！");
		}
		validatorTaskListAttachFile(taskListAttachFile);
		try {
			return taskListAttachFileDao.addTaskListAttachFile(taskListAttachFile);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类TaskListAttachFileServiceImpl的addTaskListAttachFile方法出现异常，原因：",
					"新增任务清单附件出现错误", e);
		}
	}

	@Override
	public TaskListAttachFile getTaskListAttachFileById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("任务清单附件参数为空，获取附件失败！");
		}
		try {
			return taskListAttachFileDao.getTaskListAttachFileById(id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类TaskListAttachFileServiceImpl的getTaskListAttachFileById方法出现异常，原因：",
					"获取任务清单附件信息出现错误", e);
		}
	}

	@Override
	public void deleteTaskListAttachFileById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("任务清单附件参数为空，删除附件失败！");
		}
		try {
			taskListAttachFileDao.deleteTaskListAttachFileById(id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类TaskListAttachFileServiceImpl的deleteTaskListAttachFileById方法出现异常，原因：",
					"删除任务清单附件信息出现错误", e);
		}
	}

	@Override
	public void deleteTaskListAttachFilesByBusinessId(Long businessId, String moduleKey) {
		if (businessId == null || StringUtil.isEmpty(moduleKey)) {
			throw new BusinessValidationException("任务清单附件参数为空，删除附件失败！");
		}
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("businessId", businessId);
		data.put("moduleKey", moduleKey);
		try {
			taskListAttachFileDao.deleteTaskListAttachFilesByBusinessId(data);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类TaskListAttachFileServiceImpl的deleteTaskListAttachFilesByBusinessId方法出现异常，原因：",
					"删除任务清单附件信息出现错误", e);
		}
	}

	@Override
	public void deleteTaskListAttachFilesByBusinessIds(List<Long> businessIds, String moduleKey) {
		if (businessIds == null || businessIds.size() < 1 || StringUtil.isEmpty(moduleKey)) {
			throw new BusinessValidationException("任务清单附件参数为空，删除附件失败！");
		}
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("businessIds", businessIds);
		data.put("moduleKey", moduleKey);
		try {
			taskListAttachFileDao.deleteTaskListAttachFilesByBusinessIds(data);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类TaskListAttachFileServiceImpl的deleteTaskListAttachFilesByBusinessId方法出现异常，原因：",
					"删除任务清单附件信息出现错误", e);
		}
	}

	@Override
	public void deleteTaskListAttachFilesByIds(List<Long> ids) {
		if (ids == null || ids.size() < 1) {
			throw new BusinessValidationException("任务清单附件参数为空，删除附件失败！");
		}
		try {
			taskListAttachFileDao.deleteTaskListAttachFilesByIds(ids);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类TaskListAttachFileServiceImpl的deleteTaskListAttachFilesByIds方法出现异常，原因：",
					"删除任务清单附件信息出现错误", e);
		}
	}

	@Override
	public List<TaskListAttachFile> queryTaskListAttachFilesByBusinessId(Long businessId,
			String moduleKey) {
		if (businessId == null || StringUtil.isEmpty(moduleKey)) {
			throw new BusinessValidationException("任务清单附件参数为空，查询附件信息失败！");
		}
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("businessId", businessId);
		data.put("moduleKey", moduleKey);
		try {
			return taskListAttachFileDao.queryTaskListAttachFilesByBusinessId(data);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类TaskListAttachFileServiceImpl的queryTaskListAttachFilesByBusinessId方法出现异常，原因：",
					"查询任务清单附件信息出现错误", e);
		}
	}

	private void validatorTaskListAttachFile(TaskListAttachFile taskListAttachFile) {
		ValidateResult dataValidateResult = taskListAttachFileValidator
				.validate(taskListAttachFile);
		if (dataValidateResult.hasError()) {
			throw new BusinessValidationException(dataValidateResult.getErrorMessages());
		}
	}
}
