package com.tianque.plugin.orgchange.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.orgchange.domain.ModuleTable;

@Component("moduleTableValidateImpl")
public class ModuleTableValidateImpl implements DomainValidator<ModuleTable>{

	@Autowired
	private ValidateHelper validateHelper;
	
	/**表名长度*/
	private final int TABLENAME_LENGTH = 30; 
	
	/**beanName长度*/
	private final int BEANNAME_LENGTH=30;
	/**网格字段长度*/
	private final int ORGID_LENGTH=30;
	/**ORGCOUDE字段长度*/
	private final int ORGCODE_LENGTH=30;
	/**统计语句*/
	private final int COUNT_SCRIPT_LENGTH=1000;
	/**更新语句*/
	private final int UPDATE_SCRIPT_LENGTH=1000;
	
	@Override
	public ValidateResult validate(ModuleTable domain) {
		ValidateResult result = new ValidateResult();
		/**表名验证*/
		if(validateHelper.emptyString(domain.getTableName())){
			result.addErrorMessage("表名必须填写");
		}else if(validateHelper.illegalStringLength(0, TABLENAME_LENGTH, domain.getTableName())){
			result.addErrorMessage("表名长度不能大于"+TABLENAME_LENGTH+"个字符");
		}
		
		/**beanName字段验证*/
		if(!validateHelper.emptyString(domain.getBeanName()) && validateHelper.illegalStringLength(0, BEANNAME_LENGTH, domain.getBeanName())){
			result.addErrorMessage("beanName点长度不能大于"+BEANNAME_LENGTH+"个字符");
		}
		
		/**网格字段验证*/
		if(!validateHelper.emptyString(domain.getOrgIdColumn()) && validateHelper.illegalStringLength(0, ORGID_LENGTH, domain.getOrgIdColumn())){
			result.addErrorMessage("网格字段长度不能大于"+ORGID_LENGTH+"个字符");
		}
		
		/**ORGCODE字段验证*/
		if(!validateHelper.emptyString(domain.getOrgCodeColumn()) && validateHelper.illegalStringLength(0, ORGCODE_LENGTH, domain.getOrgCodeColumn())){
			result.addErrorMessage("ORGCODE字段长度不能大于"+ORGCODE_LENGTH+"个字符");
		}
		
		/**统计语句*/
		if(!validateHelper.emptyString(domain.getCountScript()) && validateHelper.illegalStringLength(0, COUNT_SCRIPT_LENGTH, domain.getCountScript())){
			result.addErrorMessage("统计脚本长度不能大于"+COUNT_SCRIPT_LENGTH+"个字符");
			
		}
		
		/**更新语句*/
		if(!validateHelper.emptyString(domain.getUpdateScript()) && validateHelper.illegalStringLength(0, UPDATE_SCRIPT_LENGTH, domain.getUpdateScript())){
			result.addErrorMessage("更新脚本长度不能大于"+UPDATE_SCRIPT_LENGTH+"个字符");
			
		}
		return result;
	}

}
