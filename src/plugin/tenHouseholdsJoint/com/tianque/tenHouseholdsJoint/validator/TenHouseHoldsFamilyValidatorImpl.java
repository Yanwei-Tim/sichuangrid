package com.tianque.tenHouseholdsJoint.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.ValidateResult;
import com.tianque.fourTeams.fourTeamsManage.domain.FourTeamMembers;
import com.tianque.plugin.dataManage.validate.AbstactDataManageValidator;
import com.tianque.tenHouseholdsJoint.domain.FamilyInfo;
import com.tianque.tenHouseholdsJoint.service.impl.FamilyInfoServiceImpl;
import com.tianque.tenHouseholdsJoint.service.impl.FamilyTeamServiceImpl;

@Component("tenHouseHoldsFamilyValidatorImpl")
public class TenHouseHoldsFamilyValidatorImpl extends AbstactDataManageValidator<FamilyInfo>{
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private FamilyTeamServiceImpl familyTeamService;
	@Autowired
	private FamilyInfoServiceImpl familyInfoService;
	@Override
	public ValidateResult validate(FamilyInfo domain) {
		ValidateResult result = new ValidateResult();
		boolean orgIsNotNull = validateOrgIsNotNull(domain.getOrganization());
		if (!orgIsNotNull) {
			result.addErrorMessage(getColumNo("org")
					+ "所属网格为空或网格名称填写错误！");
		}

		if (orgIsNotNull && !validateOrgIsGrid(domain.getOrganization())) {
			result.addErrorMessage(getColumNo("org") + "所属网格必须为片组片格");
		}
		
		/**所属分组*/
		if(ExcelImportHelper.isImport.get()){
			if(!StringUtil.isStringAvaliable(domain.getTeamName())){
				result.addErrorMessage(getColumNo("teamName") + "所属分组不能为空");
			}else if(!isHasTheTeam(domain.getTeamName(),domain.getOrganization().getId())){
				result.addErrorMessage(getColumNo("teamName") + "所属分组不存在，请重新确认后输入");
			}
		}else{
			if(domain.getTeamId()==null){
				result.addErrorMessage("所属分组不能为空");
			}
		}
		/**用户姓名*/
		if(!StringUtil.isStringAvaliable(domain.getName())){
			result.addErrorMessage(getColumNo("name") + "用户姓名不能为空");
		}else if(validateHelper.illegalStringLength(2, 50,domain.getName())){
			result.addErrorMessage(getColumNo("name") + "用户姓名只能输入2-50个字符");
		}
		
		/**求助电话*/
		if(!StringUtil.isStringAvaliable(domain.getHelpLine())){
			result.addErrorMessage(getColumNo("helpLine") + "求助电话必须输入");
		}else if(validateHelper.illegalMobilePhone(domain.getHelpLine())){
			result.addErrorMessage(getColumNo("helpLine") + "求助电话只能输入1开头的11位数字");
		}else if(!familyInfoService.checkHelpLine(domain)){
			result.addErrorMessage(getColumNo("helpLine") + "求助电话已经存在，请重新输入");
		}
		/**用户证件号码*/
		if(!StringUtil.isStringAvaliable(domain.getCertificateNumber())){
			result.addErrorMessage(getColumNo("certificateNumber") + "用户证件号码必须输入");
		}else if(validateHelper.illegalStringLength(2, 20,domain.getCertificateNumber())){
			result.addErrorMessage(getColumNo("certificateNumber") + "用户证件号码只能输入2-20个字符");
		}
		/**用户地址*/
		if(!StringUtil.isStringAvaliable(domain.getFamilyAddress())){
			result.addErrorMessage(getColumNo("familyAddress") + "用户证件号码必须输入");
		}else if(validateHelper.illegalStringLength(2, 30,domain.getFamilyAddress())){
			result.addErrorMessage(getColumNo("familyAddress") + "用户证件号码只能输入2-30个字符");
		}
		/**短信接收号码*/
		if(validateHelper.illegalStringLength(0, 200,domain.getSMSNumber())){
				result.addErrorMessage(getColumNo("SMSNumber") + "短信接收号码最多输入200个字符");
		}
		/**语音接收号码*/
		if(validateHelper.illegalStringLength(0, 200,domain.getVoiceNumber())){
			result.addErrorMessage(getColumNo("voiceNumber") + "语音接收号码最多输入200个字符");
		}
		if(ExcelImportHelper.isImport.get()){
			/**是否通知同组其他用户*/
			if(domain.getIsInformOther()==null){
				result.addErrorMessage(getColumNo("isInformOther") + "是否通知同组其他用户 必须选择");
			}
			/**是否接收同组其他用户告警*/
			if(domain.getIsReceiveOtherAlarm()==null){
				result.addErrorMessage(getColumNo("isReceiveOtherAlarm") + "是否接收同组其他用户告警 必须选择");
			}
			/**呼叫同组其他用户*/
			if(domain.getIsCallOther()==null){
				result.addErrorMessage(getColumNo("isCallOther") + "是否呼叫同组其他用户 必须选择");
			}
			/**接收同组其他用户呼叫*/
			if(domain.getIsReceiveOtherCall()==null){
				result.addErrorMessage(getColumNo("isReceiveOtherCall") + "是否接收同组其他用户呼叫 必须选择");
			}
		}
		
		
		return result;
	}
	
	/**验证输入的所属队伍是否有数据*/
	private boolean isHasTheTeam(String teamName,Long orgId){
		return familyTeamService.getFamilyTeamByName(teamName,orgId)==null?false:true;
	}

	@Override
	public ValidateResult validateSpecializedInfo(FamilyInfo domain) {
		return null;
	}
}
