package com.tianque.plugin.taskList.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.taskList.domain.TaskListAttachFile;

/**
 * 任务清单附件验证类
 * @author lanhaifeng
 *
 */
@Component("taskListAttachFileValidator")
public class TaskListAttachFileValidatorImpl implements DomainValidator<TaskListAttachFile> {
	@Autowired
	public ValidateHelper validateHelper;
	
	@Override
	public ValidateResult validate(TaskListAttachFile domain) {
		ValidateResult result = new ValidateResult();
		if(validateHelper.emptyString(domain.getFileName())){
			result.addErrorMessage("文件名不能为空!");
		}
		if(validateHelper.emptyString(domain.getPhysicsFullFileName())){
			result.addErrorMessage("文件物理名不能为空!");
		}
		if(validateHelper.emptyString(domain.getModuleKey())){
			result.addErrorMessage("该附件的功能模块名不能为空!");
		}
		return result;
	}

}
