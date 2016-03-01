package com.tianque.serviceList.validater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.serviceList.domain.OtherSituationManage;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("otherSituationManageImportValidator")
public class OtherSituationManageValidatorImpl implements
		DomainValidator<OtherSituationManage> {
	@Autowired
	private ValidateHelper validateHelper;

	@Autowired
	private OrganizationDubboService organizationDubboService;

	public void setValidateHelper(ValidateHelper validateHelper) {
		this.validateHelper = validateHelper;
	}

	public void setOrganizationDubboService(OrganizationDubboService organizationDubboService) {
		this.organizationDubboService = organizationDubboService;
	}

	/** 最小长度 */
	private final int MIN_LENGTH = 1;
	/** 地址长度长度 */
	private final int ADDRESS_LENGTH = 40;
	/** 详细情况长度 */
	private final int DETAILS_LENGTH = 50;
	/** 备注 */
	private final int REMAKE_LENGTH = 300;
	/**回复人*/
	private final int REPLY_PELOPLE_LENGTH=10;
	/**回复内容*/
	private final int REPLY_CONTENT_LENGTH=300;
	
	@Override
	public ValidateResult validate(OtherSituationManage domain) {
		ValidateResult result = new ValidateResult();
		Organization domainOrganization = domain.getOrganization();
		boolean isGrid = false;
		if (domainOrganization == null) {
			result.addErrorMessage(getColumNo("organization") + "所属网格未填写或填写错误");
		}
		if (null != domainOrganization && null != domainOrganization.getId()) {
			isGrid = organizationDubboService.isGridOrganization(domainOrganization
					.getId());
			if (!isGrid) {
				result.addErrorMessage(getColumNo("organization") + "所属网格必填且必须为片组片格");
			}
		}
		// 地址验证
		if (validateHelper.emptyString(domain.getAddress())) {
			result.addErrorMessage(getColumNo("address") + "地址必须输入");
		} else {
			// 非法脚本验证
			if (validateHelper.illegalScript(domain.getAddress())) {
				result.addErrorMessage(getColumNo("address") + "地址不能输入非法脚本");
			}
			// 长度验证
			if (validateHelper.illegalStringLength(MIN_LENGTH, ADDRESS_LENGTH,
					domain.getAddress())) {
				result.addErrorMessage(getColumNo("address") + "地址不能小于"
						+ MIN_LENGTH + "个字符" + "不能大于" + ADDRESS_LENGTH + "个字符");
			}
		}
		// 备注验证
		if (!validateHelper.emptyString(domain.getRemake())) {
			// 非法脚本验证
			if (validateHelper.illegalScript(domain.getRemake())) {
				result.addErrorMessage(getColumNo("remake") + "备注不能输入非法脚本");
			}
			// 长度验证
			if (validateHelper.illegalStringLength(MIN_LENGTH, REMAKE_LENGTH,
					domain.getRemake())) {
				result.addErrorMessage(getColumNo("remake") + "备注不能小于"
						+ MIN_LENGTH + "个字符" + "不能大于" + REMAKE_LENGTH + "个字符");
			}
		}
		//时间验证
		if(!validateHelper.dateIsnotNull(domain.getInputTime())){
			result.addErrorMessage(getColumNo("inputTime")
					+ "时间必须输入或填写错误，正确示例：2015-8-8");
		}
		//详细情况验证
		if(validateHelper.emptyString(domain.getDetails())){
			result.addErrorMessage(getColumNo("details")
					+ "详细情况必须输入");
		}else if (validateHelper.illegalScript(domain.getDetails())) {
			// 非法脚本验证
			result.addErrorMessage(getColumNo("details") + "详细情况不能输入非法脚本");
		}else{
			// 长度验证
			if (validateHelper.illegalStringLength(MIN_LENGTH, DETAILS_LENGTH,
					domain.getDetails())) {
				result.addErrorMessage(getColumNo("details") + "详细情况不能小于"
						+ MIN_LENGTH + "个字符" + "不能大于" + DETAILS_LENGTH + "个字符");
			}
		}
		//是否签收验证
		if(domain.getIsSign()==null){
			result.addErrorMessage(getColumNo("isSign")
					+ "是否签收必须输入");
		}
		if (ExcelImportHelper.isImport.get()) {
		//是否回复验证
		if(domain.getIsReply()==null){
			result.addErrorMessage(getColumNo("isReply")
					+ "是否回复必须输入");
		}else if(domain.getIsReply()==1){
			if(domain.getReplyDate()==null){
				result.addErrorMessage(getColumNo("replyDate")
						+ "回复选择是时回复时间必须输入");
			}
			
			if(domain.getReplyPeople()==null){
				result.addErrorMessage(getColumNo("replyPeople")
						+ "回复选择是时回复人必须输入");
			}else if(validateHelper.illegalScript(domain.getReplyPeople())){
				// 非法脚本验证
				result.addErrorMessage(getColumNo("replyPeople") + "回复人不能输入非法脚本");
			} else if (validateHelper.illegalStringLength(MIN_LENGTH, REMAKE_LENGTH,
					domain.getReplyPeople())) {
				// 长度验证
				result.addErrorMessage(getColumNo("replyPeople") + "回复人不能小于"
						+ MIN_LENGTH + "个字符" + "不能大于" + REPLY_PELOPLE_LENGTH + "个字符");
			}
			
			if(domain.getReplyContent()==null){
				result.addErrorMessage(getColumNo("replyContent")
						+ "回复选择是时回复内容必须输入");
			}else if(validateHelper.illegalScript(domain.getReplyContent())){
				// 非法脚本验证
				result.addErrorMessage(getColumNo("replyContent") + "回复内容不能输入非法脚本");
			} else if (validateHelper.illegalStringLength(MIN_LENGTH, REMAKE_LENGTH,
					domain.getReplyContent())) {
				// 长度验证
				result.addErrorMessage(getColumNo("replyContent") + "回复内容不能小于"
						+ MIN_LENGTH + "个字符" + "不能大于" + REPLY_CONTENT_LENGTH + "个字符");
			}
		}
		}
		return result;
	}

	public String getColumNo(String key) {
		return ExcelImportHelper.getColumNo(key);
	}

	public boolean typeIsNotNull(PropertyDict p) {
		if (p == null || p.getId() == null) {
			return false;
		}
		return true;
	}
	
	//pc新增修改验证
	public void validatorOtherSituationManage(OtherSituationManage domain) {
		ValidateResult result = validate(domain);
		if (result.hasError()) {
			throw new BusinessValidationException("政策法规宣传pc新增出错："+result.getErrorMessages());
		}
	}
}