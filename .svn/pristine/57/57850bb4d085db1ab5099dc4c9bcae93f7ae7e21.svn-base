<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%
if(ThreadVariable.getUser()!=null){
	request.setAttribute("NAME",ThreadVariable.getUser().getName());
}
%>
<s:action name="getLoginInfo" var="loginAction" executeResult="false"
	namespace="/sessionManage" />
<s:action name="getFullOrgById" var="getFullOrgById"
	executeResult="false" namespace="/sysadmin/orgManage">
	<s:param name="organization.id"
		value="#loginAction.user.organization.id"></s:param>
</s:action>

<s:if test="#request.type=='convert'">
		<s:action name="dispatch" namespace="/threeRecords/ledgerConvert" executeResult="true">
			<s:param name="id" value="#request.keyId"></s:param>
			<s:param name="mode" >view</s:param>
		</s:action>
	</s:if>
<div id="dialog-form" title="困难群众台账维护" class="container container_24" style="overflow:hidden;">
	<form id="maintainForm" method="post" 
	    action="<s:if test='"edit".equals(mode)'>/threeRecordsIssue/ledgerSteadyWorkManage/updateLedgerSteadyWork.action</s:if><s:elseif test='"add".equals(mode)'>/threeRecordsIssue/ledgerSteadyWorkManage/addLedgerSteadyWork.action</s:elseif>" >
	<pop:token/>
	<input type="hidden" id="organizationId" name="ledgerSteadyWork.organization.id" value="${ledgerSteadyWork.organization.id}"/>
	<input type="hidden" id="occurOrgId" name="ledgerSteadyWork.occurOrg.id" value="${ledgerSteadyWork.occurOrg.id}" />
	<input type="hidden" name="ledgerSteadyWork.id" id="id" value="${ledgerSteadyWork.id}" />
	<input type="hidden" name="ledgerSteadyWork.birthDay" id="birthDay" value="${ledgerSteadyWork.birthDay}" />
	<s:if test="#request.type=='convert'">
			<input type="hidden" id="oldIssueId" name="ledgerSteadyWork.oldIssueId" value="${param.oldIssueId}"/>
			<input type="hidden" id="convertId" name="ledgerSteadyWork.convertId" value="${param.keyId}"/>
		</s:if>
 		<div class="grid_4 lable-right">
 			<em class="form-req">*</em>
	    	<label class="form-lbl">反映群体：</label>
		</div>
		<div class="grid_7">
   			<select id="involvingSteadyType" onchange="setResponseGroupNo()" name="ledgerSteadyWork.involvingSteadyType.id" class="form-txt {required:true,messages:{required:'反映群体必须选择'}}">
	   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@STEADY_RECORD_WORK_INVOLVING_STEADY_TYPE" defaultValue="${ledgerSteadyWork.involvingSteadyType.id}"/></select>
		</div>
 		<div class="grid_4 lable-right">
   			<em class="form-req">*</em>
   		<label class="form-lbl">反映人数：</label></div>
    	<div class="grid_7">
   			<input type="text" id="involvingSteadyNum" name="ledgerSteadyWork.involvingSteadyNum" value="${ledgerSteadyWork.involvingSteadyNum }" maxlength="20" class='form-txt {required:true,positiveInteger:true,messages:{required:"反映群体人数必须输入",positiveInteger:$.format("反映群体人数输入不合法，只能输入正整数")}}' />
    	</div>
    	<div class='clearLine'>&nbsp;</div>
    	
		<div  class="grid_4 lable-right" >
			<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
			<label class="form-lbl">编号： </label>
		</div>
		<div class="grid_7" id="userDiv">
   			<input type="text" name="ledgerSteadyWork.serialNumber" value="${ledgerSteadyWork.serialNumber}" readonly="readonly" maxlength="15" class="form-txt {required:true,messages:{required:'编号必须输入'}}" />
		</div>
		
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">建卡类型：</label>
	 	</div>
		<div class="grid_7">
			<select name="ledgerSteadyWork.createTableType.id" id="createTableType" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
		   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@LEDGER_PEOPLEASPIRATION_CREATE_TABLE_TYPE"  defaultValue="${ledgerSteadyWork.createTableType.id}" />
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
 			<em class="form-req">*</em>
			<label class="form-lbl">登记单位：</label>
	 	</div>
		<div <s:if test='!"view".equals(mode)'>class="grid_7"</s:if><s:else>class="grid_4"</s:else>>
			<input type="text"  id="ledgerSteadyWorkOrgName"  name="ledgerSteadyWork.bookingUnit"  readonly  value="${ledgerSteadyWork.bookingUnit}"
				class='form-txt' />
		</div>
   		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">服务联系人：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="ledgerSteadyWork.serverContractor" id="serverContractor"  maxlength="20" class="form-txt" value="${ledgerSteadyWork.serverContractor}"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
    	<div class="grid_4 lable-right">
    		<em class="form-req">*</em>
			<label class="form-lbl">发生网格：</label>
		</div>
		<div class="grid_8">
			<input type="text" id="occurOrg" class="form-txt {isNOTNULL:true,messages:{isNOTNULL:'请选择发生网格'}}" style="color:red"/>
   		</div>
   		
   		<div class="grid_3 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">单位及职务：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="ledgerSteadyWork.serverJob" id="serverJob"  maxlength="20" class="form-txt {required:true,messages:{required:'单位及职务必须填写'}}" value="${ledgerSteadyWork.serverJob}"/>
		</div>
   		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
	   		 <label class="form-lbl">身份证号：</label>
		</div>
		<div class="grid_7"><!-- ,exsistedIdCard:false -->
   			<input type="text" name="ledgerSteadyWork.idCardNo" id="idCardNo" value="${ledgerSteadyWork.idCardNo }" maxlength="18" class="  form-txt {idCard:true,messages:{idCard:$.format('请输入一个合法的身份证号码'),exsistedIdCard:function(){return idCardNoData;}}}" />
		</div>
		<div class="grid_4 lable-right">
   			<em class="form-req ">*</em>
   			<label class="form-lbl">姓名：</label></div>
   	    <div class="grid_7">
   			<input type="text" id="name" name="ledgerSteadyWork.name" value="${ledgerSteadyWork.name }" maxlength="20" class=" form-txt {exculdeParticalChar:true,maxlength:20,minlength:2,messages:{exculdeParticalChar:'不能输入非法字符',minlength:$.format('姓名至少需要输入{0}个字符'),maxlength:$.format('姓名最多需要输入{0}个字符')}}" />
   	    </div>
   	    <div class='clearLine'>&nbsp;</div>
   	    
		<div class="grid_4 lable-right">
	    	<label class="form-lbl">性别：</label>
		</div>
		<div class="grid_7">
   			<select id="gender" name="ledgerSteadyWork.gender.id" class="form-txt">
	   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@GENDER" defaultValue="${ledgerSteadyWork.gender.id}"/></select>
	   		<input type="hidden" id="populationGender" value="${ledgerSteadyWork.gender.id}"/>
		</div>
		
		<div class="grid_4 lable-right">
	 		<label class="form-lbl">是否党员：</label>
		</div>
    	<div class="grid_7">
   			<select name="ledgerSteadyWork.isPartyMember" class="form-txt">
   				<option value="false" <s:if test="false == ledgerSteadyWork.isPartyMember">selected="selected"</s:if>>否</option>
   				<option value="true" <s:if test="true == ledgerSteadyWork.isPartyMember">selected="selected"</s:if>>是</option>
   			</select>
   	    </div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
	   	 	<label class="form-lbl">职业或身份：</label>
		</div>
		<div class="grid_7">
   			<select id="position" name="ledgerSteadyWork.position.id" class="form-txt ">
	   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@POSITION_OR_STATUS" defaultValue="${ledgerSteadyWork.position.id}"/></select>
		</div>
		<div class="grid_4 lable-right">
	    	 <label class="form-lbl">常住地址：</label>
		</div>
    	<div class="grid_7">
   			<input type="text" id='permanentAddress' name="ledgerSteadyWork.permanentAddress" value="${ledgerSteadyWork.permanentAddress }" maxlength="50" class="form-txt {validatorNativePlaceAddress:true, messages:{validatorNativePlaceAddress:'常住地址不能输入特殊字符'}}" style="width: 99%;" />
   		</div>
   		<div class='clearLine'>&nbsp;</div>
   		
		<div class="grid_4 lable-right">
	    	<label class="form-lbl">联系电话：</label>
		</div>
		<div class="grid_7">
   			<input type="text" name="ledgerSteadyWork.mobileNumber" id="serverTelephone" maxlength="13"  value="${ledgerSteadyWork.mobileNumber }"
   				class='form-txt {telephone:true,messages:{telephone:$.format("固定电话不合法，只能输数字和横杠(-)")}}' title="请输入由数字和-组成的联系电话,例如：0577-88888888"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
	   <div class="grid_4 lable-right">
			<label class="form-lbl">登记人：</label>
	 	</div>
		<div class="grid_7">
			<input type="text"  name="ledgerSteadyWork.registrant" id="registrant"  maxlength="20" value="${ledgerSteadyWork.registrant}" 
				class='form-txt' />
		</div>	
	<div class="grid_4 lable-right">
	    <em class="form-req">*</em><label class="form-lbl">登记时间：</label>
	</div>
	<div class="grid_7" id="birthdayDiv">
   		<input type="text" name="ledgerSteadyWork.registrationTime" id="registrationTime" value="<s:date name="ledgerSteadyWork.registrationTime" format="yyyy-MM-dd"/>" class="form-txt" readonly/>
	</div>
	<div class='clearLine'>&nbsp;</div>
	
    <div class="grid_4 lable-right">
   		<em class="form-req">*</em>
   		<label class="form-lbl">涉稳类型 ：</label></div>
    <div class="grid_7">
   		<select name="ledgerSteadyWork.steadyWorkType.id" onchange="change()" id="_poorBigInfo" class="form-txt {required:true,messages:{required:'涉稳类型 (大类)必须选择'}}">
   			<pop:PropertyDictReleationSelectTag name="@com.tianque.plugin.account.property.PropertyTypes@STEADY_RECORD_WORK_TYPE" defaultValue="${ledgerSteadyWork.steadyWorkType.id}" 
		   			reletionId="_poorInfo" reletionName="@com.tianque.plugin.account.property.PropertyTypes@STEADY_RECORD_WORK_PROBLEM_TYPE" id="_poorBigInfo"/></select>
    </div>
    
    <div id="problemType">
    	<div class="grid_4 lable-right">
   			<em class="form-req">*</em>
   			<label class="form-lbl">涉稳问题类型 ：</label>
   		</div>
   		<div class="grid_7">
   			<select name="ledgerSteadyWork.steadyWorkProblemType.id" id="_poorInfo" class="form-txt {required:true,messages:{required:'涉稳问题类型（子类)必须选择'}}">
	   			<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@STEADY_RECORD_WORK_PROBLEM_TYPE" defaultValue="${ledgerSteadyWork.steadyWorkProblemType.id}"/></select>
    	</div>
    </div>
    <div id="remark">
    	<div class="grid_4 lable-right">
   			<em class="form-req"></em>
   			<label class="form-lbl">备注 ：</label>
   		</div>
    	<div class="grid_7">
   			<input type="text" name="ledgerSteadyWork.otherRemark" value="${ledgerSteadyWork.otherRemark }" maxlength="60" class="form-txt {maxlength:60,messages:{exculdeParticalChar:'不能输入非法字符',maxlength:$.format('备注最多输入{0}个字符')}}" />
    	</div>
    </div>
    
    <div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			 <em class="form-req">*</em>
	   		 <label class="form-lbl">稳控责任单位：</label>
		</div>
		<div class="grid_7">
   			<input type="text" name="ledgerSteadyWork.steadyUnit" value="${ledgerSteadyWork.steadyUnit }" maxlength="60" class="form-txt {required:true,exculdeParticalChar:true,maxlength:60,minlength:1,messages:{required:'稳控责任单位必须填写',exculdeParticalChar:'不能输入非法字符',minlength:$.format('稳控责任单位至少需要输入{0}个字符'),maxlength:$.format('稳控责任单位最多需要输入{0}个字符')}}" />
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
   			<label class="form-lbl">稳控责任人：</label></div>
   	    <div class="grid_7">
   			<input type="text" name="ledgerSteadyWork.steadyUser" value="${ledgerSteadyWork.steadyUser }" maxlength="10" class="form-txt {required:true,exculdeParticalChar:true,maxlength:10,minlength:1,messages:{required:'稳控稳控责任人必须填写',exculdeParticalChar:'不能输入非法字符',minlength:$.format('稳控责任人至少需要输入{0}个字符'),maxlength:$.format('稳控责任人最多需要输入{0}个字符')}}" />
   	    </div>
   	    
    <div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			 <em class="form-req">*</em>
	   		 <label class="form-lbl">化解责任部门：</label>
		</div>
		<div class="grid_7"><!-- ,exsistedIdCard:false -->
   			<input type="text" name="ledgerSteadyWork.resolveUnit" value="${ledgerSteadyWork.resolveUnit }"  maxlength="60" class="form-txt {required:true,exculdeParticalChar:true,maxlength:60,minlength:1,messages:{required:'化解责任部门必须填写',exculdeParticalChar:'不能输入非法字符',minlength:$.format('化解责任部门至少需要输入{0}个字符'),maxlength:$.format('化解责任部门最多需要输入{0}个字符')}}" />
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
   			<label class="form-lbl">化解责任人：</label></div>
   	    <div class="grid_7">
   			<input type="text" name="ledgerSteadyWork.resolveUser" value="${ledgerSteadyWork.resolveUser }" maxlength="10" class="form-txt {required:true,exculdeParticalChar:true,maxlength:10,minlength:1,messages:{required:'化解责任人必须填写',exculdeParticalChar:'不能输入非法字符',minlength:$.format('化解责任人至少需要输入{0}个字符'),maxlength:$.format('化解责任人最多需要输入{0}个字符')}}" />
   	    </div>
    
    <div class='clearLine'>&nbsp;</div>
    
    <div class="grid_4 lable-right">
    	<em class="form-req">*</em>
	   	<label class="form-lbl">稳定预警级别：</label>
	</div>
	<div class="grid_7">
	    <select name="ledgerSteadyWork.steadyWorkWarnLevel.id" id="steadyWorkWarnLevel" class="form-txt {required:true,messages:{required:'预警级别类型必须选择'}}">
		   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@STEADY_RECORD_WORK_WARN_LEVEL"  defaultValue="${ledgerSteadyWork.steadyWorkWarnLevel.id}" />
		</select>
	</div>
	<div class="grid_4 lable-right">
		<em class="form-req">*</em>
   		<label class="form-lbl">预警日期：</label></div>
   	<div class="grid_7">
   	    <input type="text" name="ledgerSteadyWork.steadyWorkWarnLevelDate" id="steadyWorkWarnLevelDate" value="<s:date name="ledgerSteadyWork.steadyWorkWarnLevelDate" format="yyyy-MM-dd"/>" class="form-txt {required:true,messages:{required:'预警时间必须填写'}}" readonly/>
    </div>
    <div class='clearLine'>&nbsp;</div>
     
	<div class="grid_4 lable-right">
		<em class="form-req">*</em>
		<label class="form-lbl">涉稳事项：</label>
	</div>
	<div class="grid_20 heightAuto">
	   <textarea name="ledgerSteadyWork.involvingSteadyInfo" id="involvingSteadyInfo" class="form-txt">${ledgerSteadyWork.involvingSteadyInfo}</textarea>
	</div>
    <div class='clearLine'>&nbsp;</div>
    
    <div class="grid_4 lable-right">
    	<em class="form-req">*</em>
		<label class="form-lbl">涉稳人员或群体稳定现状：</label>
	</div>
	<div class="grid_20 heightAuto">
	   <textarea name="ledgerSteadyWork.steadyInfo" id="steadyInfo" class="form-txt ">${ledgerSteadyWork.steadyInfo}</textarea>
	</div>
    <div class='clearLine'>&nbsp;</div>
    
    	<div >
    		<div class="grid_11 lable-left">
    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input id="isAnonymity" name="ledgerSteadyWork.anonymity" type="checkbox" value="${ledgerSteadyWork.anonymity}" 
				<s:if test="ledgerSteadyWork.anonymity">checked="checked"</s:if>
				<s:if test='"view".equals(mode)'>disabled</s:if> />
				<label class="form-check-text" for="isAnonymity">是否匿名</label>
				<input id="_isAnonymity" name="ledgerSteadyWork.anonymity" type="hidden" value="${ledgerSteadyWork.anonymity}" disabled="disabled"/>
			</div>
		</div>
	 
	<!--  <div class="grid_22 lable-center"></div>-->
	<div class='clearLine'>&nbsp;</div>
    <div style=" overflow:scroll;border-collapse: collapse;border: solid #000 1px;height:120;width:690;" >
  		<div class="grid_6 lable-right">
		    <label class="form-lbl">涉稳人员或涉稳群体代表：</label>
	    </div>
	    <div class="grid_3"></div>
	    <div class="grid_4 lable-right">
	        <button type="button" id="addFamilyMembers">增加</button>
	    </div>
	    <div class="grid_1"></div>
	    <div class="grid_5">
	     	<button type="button" id="delFamilyMembers">删除</button>
	    </div>
	    <div class="grid_5"></div>
	    
	<div id="copyAddFamilyMembersParent">
	  	<c:if test="${fn:length(ledgerSteadyWork.ledgerSteadyWorkPeopleInfos)<1}">
	  	<div id="copyAddFamilyMembers">
	  	<div class="grid_3 lable-right">
	  		<em class="form-req emchar">*</em>
   			<label class="form-lbl">姓名：</label>
   		</div>
    	<div class="grid_3">
   			<input type="text" id="ledgerSteadyWorkPeopleInfosName" name='_name'  maxlength="20" class="form-txt isAnonymity  {exculdeParticalChar:true,maxlength:20,minlength:2,messages:{exculdeParticalChar:'不能输入非法字符',minlength:$.format('姓名至少需要输入{0}个字符'),maxlength:$.format('姓名最多需要输入{0}个字符')}}" />
    	</div>
    	<div class="grid_3 lable-right">
    		<em class="form-req emchar">*</em>
	    	<label class="form-lbl">身份证号：</label>
		</div>
		<div class="grid_3"><!-- ,exsistedIdCard:false -->
   			<input type="text" id="ledgerSteadyWorkPeopleInfosIdCardNo" name="ledgerSteadyWorkPeopleInfosIdCardNo" maxlength="18" class="form-txt isAnonymity {idCard:true,messages:{idCard:$.format('请输入一个合法的身份证号码'),exsistedIdCard:function(){return idCardNoData;}}}" />
		</div>
		<div class="grid_2 lable-right">
			<em class="form-req emchar">*</em>
	    	<label class="form-lbl">性别：</label>
		</div>
		<div class="grid_3">
   			<select id="ledgerSteadyWorkPeopleInfosGender"  name='_gender'  class="form-txt isAnonymity">
	   			<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@GENDER"/></select>
		</div>
	   <div class="grid_3 lable-right">
	   		<em class="form-req emchar">*</em>
	 		<label class="form-lbl">是否党员：</label>
		</div>
    	<div class="grid_3">
   			<select id="ledgerSteadyWorkPeopleInfosIsPartyMember" name='_isPartyMember' class="form-txt isAnonymity">
   			    <option value="" <c:if test="${ledgerSteadyWorkPeopleInfo.partyMember == ''}">selected="selected"</c:if>>请选择</option>
   				<option value="false" <c:if test="${ledgerSteadyWorkPeopleInfo.partyMember == false}">selected="selected"</c:if>>否</option>
   				<option value="true" <c:if test="${ledgerSteadyWorkPeopleInfo.partyMember == true}">selected="selected"</c:if>>是</option>
   			</select>
   	    </div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_3 lable-right">
			<em class="form-req emchar">*</em>
	    	<label class="form-lbl">联系电话：</label>
		</div>
		<div class="grid_3">
   			<input type="text" id="ledgerSteadyWorkPeopleInfosMobileNumber" name='_mobile' maxlength="13" 
   				class='form-txt isAnonymity {phoneAndMobile:true,messages:{phoneAndMobile:$.format("请输入以固定电话：028-87653333或者手机：15102888888为格式的号码")}}' title="请输入以固定电话：028-87653333或者手机：15102888888为格式的号码"/>
		</div>
		<div class="grid_3 lable-right">
			<em class="form-req emchar">*</em>
	    	<label class="form-lbl">出生年月：</label>
		</div>
		<div class="grid_3" id="birthdayDiv">
   			<input type="text" id="birthDayTimeMembers" name="birthDayTimeMembers" class="form-txt isAnonymity" readonly/>
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req emchar">*</em>
	   	 	<label class="form-lbl">职业或身份：</label>
		</div>
		<div class="grid_2">
   			<select id="ledgerSteadyWorkPeopleInfosPositionId" name='_position' class="form-txt isAnonymity">
	   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@POSITION_OR_STATUS"/></select>
		</div>
	   <div class="grid_3 lable-right">
	  		 <em class="form-req emchar">*</em>
	    	 <label class="form-lbl">常住地址：</label>
		</div>
    	<div class="grid_3">
   			<input type="text" id="ledgerSteadyWorkPeopleInfosPermanentAddress" name='_permanentAddress' maxlength="50" class="form-txt isAnonymity {validatorNativePlaceAddress:true, messages:{validatorNativePlaceAddress:'常住地址不能输入特殊字符'}}" style="width: 99%;" />
   		</div>
	    <div class='clearLine'>&nbsp;</div>
	    <hr style="height:1px;border:none;border-top:1px dashed black;" />
	  </div>
	  </c:if>
	  
	   <c:forEach items="${ledgerSteadyWork.ledgerSteadyWorkPeopleInfos}" var="ledgerSteadyWorkPeopleInfos" varStatus="status">
	   <div id="copyAddFamilyMembers" >
	   <div class="grid_3 lable-right">
	   		<em class="form-req emchar">*</em>
   			<label class="form-lbl">姓名：</label>
   		</div>
    	<div class="grid_3">
   			<input type="text" id="ledgerSteadyWorkPeopleInfosName" name="_name${fn:length(ledgerSteadyWork.ledgerSteadyWorkPeopleInfos)-status.index-1}" maxlength="20" value="${ledgerSteadyWorkPeopleInfos.name}" class="form-txt isAnonymity {required:true,exculdeParticalChar:true,maxlength:20,minlength:2,messages:{required:'请输入姓名 ',exculdeParticalChar:'不能输入非法字符',minlength:$.format('姓名至少需要输入{0}个字符'),maxlength:$.format('姓名最多需要输入{0}个字符')}}" />
    	</div>
    	<div class="grid_3 lable-right">
    		<em class="form-req emchar">*</em>
	    	<label class="form-lbl">身份证号：</label>
		</div>
		<div class="grid_3"><!-- ,exsistedIdCard:false -->
   			<input type="text" id="ledgerSteadyWorkPeopleInfosIdCardNo" name="ledgerSteadyWorkPeopleInfosIdCardNo" maxlength="18" value="${ledgerSteadyWorkPeopleInfos.idCardNo}" class="form-txt isAnonymity {idCard:true,messages:{idCard:$.format('请输入一个合法的身份证号码'),exsistedIdCard:function(){return idCardNoData;}}}" />
		</div>
		<div class="grid_2 lable-right">
			<em class="form-req emchar">*</em>
	    	<label class="form-lbl">性别：</label>
		</div>
		<div class="grid_3">
   			<select id="ledgerSteadyWorkPeopleInfosGender" name='_gender' class="form-txt isAnonymity">
	   			<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@GENDER" defaultValue="${ledgerSteadyWorkPeopleInfos.gender.id}"/></select>
		</div>
	   <div class="grid_3 lable-right">
	   		<em class="form-req emchar">*</em>
	 		<label class="form-lbl">是否党员：</label>
		</div>
    	<div class="grid_3">
   			<select id="ledgerSteadyWorkPeopleInfosIsPartyMember" name='_isPartyMember' class="form-txt isAnonymity">
   			    <option value="" <c:if test="${ledgerSteadyWorkPeopleInfos.partyMember == ''}">selected="selected"</c:if>>请选择</option>
   			    <option value="false" <c:if test="${ledgerSteadyWorkPeopleInfos.partyMember == false}">selected="selected"</c:if>>否</option>
   			    <option value="true" <c:if test="${ledgerSteadyWorkPeopleInfos.partyMember == true}">selected="selected"</c:if>>是</option>
   			</select>
   	    </div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_3 lable-right">
			<em class="form-req emchar">*</em>
	    	<label class="form-lbl">联系电话：</label>
		</div>
		<div class="grid_3">
   			<input type="text" id="ledgerSteadyWorkPeopleInfosMobileNumber"  name='_mobile' maxlength="13" value="${ledgerSteadyWorkPeopleInfos.mobileNumber}"
   				class='form-txt isAnonymity {phoneAndMobile:true,messages:{phoneAndMobile:$.format("请输入以固定电话：028-87653333或者手机：15102888888为格式的号码")}}' title="请输入以固定电话：028-87653333或者手机：15102888888为格式的号码"/>
		</div>
		<div class="grid_3 lable-right">
			<em class="form-req emchar">*</em>
	    	<label class="form-lbl">出生年月：</label>
		</div>
		<div class="grid_3" id="birthdayDiv">
   			<input type="text" id="birthDayTimeMembers" name="birthDayTimeMembers" value="<fmt:formatDate value="${ledgerSteadyWorkPeopleInfos.birthDay }" pattern='yyyy-MM-dd'/>" class="form-txt isAnonymity" readonly/>
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req emchar">*</em>
	   	 	<label class="form-lbl">职业或身份：</label>
		</div>
		<div class="grid_2">
   			<select id="ledgerSteadyWorkPeopleInfosPositionId" name='_position' class="form-txt isAnonymity">
	   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@POSITION_OR_STATUS" defaultValue="${ledgerSteadyWorkPeopleInfos.position.id}"/></select>
		</div>
	   <div class="grid_3 lable-right">
	  		 <em class="form-req emchar">*</em>
	    	 <label class="form-lbl">常住地址：</label>
		</div>
    	<div class="grid_3">
   			<input type="text" id="ledgerSteadyWorkPeopleInfosPermanentAddress" name='_permanentAddress' maxlength="50" value="${ledgerSteadyWorkPeopleInfos.permanentAddress}" class="form-txt isAnonymity {validatorNativePlaceAddress:true, messages:{validatorNativePlaceAddress:'常住地址不能输入特殊字符'}}" style="width: 99%;" />
   		</div>
	    <div class='clearLine'>&nbsp;</div>
	    <hr style="height:1px;border:none;border-top:1px dashed black;" />
	  </div>
	  </c:forEach>
	  </div>
  	</div>
    <div class='clearLine'>&nbsp;</div>
	</form>
</div>
<script type="text/javascript">
function change(){
	if($("#_poorBigInfo").find("option:selected").text() == '其他'){
		$("#problemType").hide()
		$("#remark").show();
	}else{
		$("#problemType").show()
		$("#remark").hide();
	}
}
$('#delFamilyMembers').click(function(){
	var childrens = $("#copyAddFamilyMembersParent").children();
	if(childrens.length > 1){
		$(childrens[0]).remove();
	}else if(childrens.length == 1){
		var objs = $(childrens[0]);
		initFamilyMembersValue(objs);
	}
});

function initOccurOrgSelector(){
	var tree=$("#occurOrg").treeSelect({
		inputName:"ledgerSteadyWork.occurOrg.id",
		loadCom:function(){
			if(<s:property value='!"add".equals(mode)'/>){
				$.setTreeValue(getDefaultOccurOrg(),tree); 
			}
			if($('#id').val()=="")
				$("#occurOrg").val("请选择");
			
		}
	});
}
function setResponseGroupNo(){
	if($("#involvingSteadyType").find("option:selected").text()=="个人"){
		$("#involvingSteadyNum").val(1);
	}
}
function getDefaultOccurOrg(){
	<s:if test="null!=ledgerSteadyWork.occurOrg && null!=ledgerSteadyWork.occurOrg.id">
		return "${ledgerSteadyWork.occurOrg.id}";
	</s:if>
	<s:else>
		return -1;
	</s:else>
}
//电话号码
jQuery.validator.addMethod("phoneAndMobile", function(value, element){
	if(value==null||value==undefined||value=="" ){return true};
	var mobile = /^(1[3|4|5|7|8][0-9])+\d{8}$/;
	var length = value.length;
	if(length == 11 && mobile.test(value)){return true;}
	var phone = /^([0-9]{3,4}-)+[0-9]{7,8}$/;	
	if (value.match(phone)==null) {return false;}
	return true
});

jQuery.validator.addMethod("validatorNativePlaceAddress", function (value,element){
	if(value==null||value==undefined||value==""){return true}
	var pattern = new RegExp("[`~!@%#$^&*()=|{}':;',　\\[\\]<>/? \\.；：%……+￥（）【】‘”“'。，、？ ！]");
	return this.optional(element)||!pattern.test(value) ; 
});
jQuery.validator.addMethod("isNOTNULL", function (value,element){
	if(value==null||value==undefined||value==""||value=="请选择"){return false}
	return true ; 
});
jQuery.validator.addMethod("validatorLastYearMemberIncome", function (value,element){
	if(value==null||value==undefined||value==""){return true}
	var pattern = /^\d{1,6}(\.\d{1,2})?$/;
	return pattern.test(value) ; 
});
var idCardNoData;
jQuery.validator.addMethod("exsistedIdCard", function(value, element){
	var value=$('#idCardNo').val();
	if(value==null||value==undefined||value==""){return true}
	$.ajax({
		async: false ,
		url:"${path}/threeRecordsIssue/ledgerSteadyWorkManage/existedPoorPeopleByIdCardNo.action",
	   	data:{
			"idCardNo": $('#idCardNo').val(),
        },
		success:function(responseData){
			idCardNoData = responseData;
		}
	});
	if(!(idCardNoData==null||idCardNoData=="")){
		return false;
	}
	return true;
});


function initFamilyMembersValue(objs){
	if(null != objs){
		objs.find("#ledgerSteadyWorkPeopleInfosName").val(null);
		objs.find("#ledgerSteadyWorkPeopleInfosIdCardNo").val(null);
		objs.find("#ledgerSteadyWorkPeopleInfosGender").val(0);
		objs.find("#ledgerSteadyWorkPeopleInfosIsPartyMember").val(null);
		objs.find("#ledgerSteadyWorkPeopleInfosMobileNumber").val(null);
		objs.find("#ledgerSteadyWorkPeopleInfosPositionId").val(0);
		objs.find("#ledgerSteadyWorkPeopleInfosPermanentAddress").val(null);
		objs.find("#birthDayTimeMembers").val(null);
		var i = $("#copyAddFamilyMembersParent").children().length;
		objs.find("#ledgerSteadyWorkPeopleInfosName").attr('name','_name'+i);
		objs.find("#ledgerSteadyWorkPeopleInfosIdCardNo").attr('name','ledgerSteadyWorkPeopleInfosIdCardNo'+i);
		objs.find("#ledgerSteadyWorkPeopleInfosGender").attr('name','_gender'+i);
		objs.find("#ledgerSteadyWorkPeopleInfosIsPartyMember").attr('name','_isPartyMember'+i);
		objs.find("#ledgerSteadyWorkPeopleInfosMobileNumber").attr('name','_mobile'+i);
		objs.find("#ledgerSteadyWorkPeopleInfosPositionId").attr('name','_position'+i);
		objs.find("#ledgerSteadyWorkPeopleInfosPermanentAddress").attr('name','_permanentAddress'+i);
		objs.find("#birthDayTimeMembers").attr('name','birthDayTimeMembers'+i);
	}
}

function idCardChanged(idCardNoObj){
	var info = getBirthDayTextFromIdCard(idCardNoObj.val());
	resetBirthdayField(info, $($(idCardNoObj.parent()).parent()).find("#birthDayTimeMembers"));
	_fillGenderByIdCardNo(idCardNoObj);
	/*
	var text = idCardNoObj.val();
	$.ajax({
		async: false ,
		url:"${path}/threeRecordsIssue/ledgerPoorPeopleManage/getCountrymenByIdCardNoAndOrgInternalCode.action",
	   	data:{
			"householdStaffVo.idCardNo": text,
        },
		success:function(responseData){
			if(null == responseData ||　"" == responseData || responseData.idCardNo == null){
				$.messageBox({message:"该人员未录入人口模块的人口基本信息！",level: "error"});
				return;
			}else{
				resetBirthdayField(responseData.name, $($(idCardNoObj.parent()).parent()).find("#ledgerSteadyWorkPeopleInfosName"));
				resetBirthdayField(responseData.gender.id, $($(idCardNoObj.parent()).parent()).find("#ledgerSteadyWorkPeopleInfosGender"));
				resetBirthdayField(responseData.mobileNumber, $($(idCardNoObj.parent()).parent()).find("#ledgerSteadyWorkPeopleInfosMobileNumber"));
				resetBirthdayField(responseData.currentAddress, $($(idCardNoObj.parent()).parent()).find("#ledgerSteadyWorkPeopleInfosPermanentAddress"));career
				resetBirthdayField(responseData.career.id, $($(idCardNoObj.parent()).parent()).find("#ledgerSteadyWorkPeopleInfosPositionId"));
			}
		}
	});*/
}

function _fillGenderByIdCardNo(select){
    var sex;
    var idCardNo = select.val();
    if(idCardNo==null||idCardNo=="" || typeof(idCardNo)=="undefined"){
        return;
    }
    if(idCardNo.length!=15 && idCardNo.length!=18){
        return;
    }

    if (15 == idCardNo.length) { //15位身份证号码
        if (parseInt(idCardNo.charAt(14) / 2) * 2 != idCardNo.charAt(14))
            sex = '男';
        else
            sex = '女';
     }
    if (18 == idCardNo.length) { //18位身份证号码
        if (parseInt(idCardNo.charAt(16) / 2) * 2 != idCardNo.charAt(16))
           sex = '男';
        else
          sex = '女';
    }
    for(var i = 0; i < $($(select.parent()).parent()).find("#ledgerSteadyWorkPeopleInfosGender").find('option').length; i++){
    	if($($(select.parent()).parent()).find("#ledgerSteadyWorkPeopleInfosGender").find('option')[i].text == sex){
			$($(select.parent()).parent()).find("#ledgerSteadyWorkPeopleInfosGender option").eq(i).attr("selected",true);
			break;
    	}
    }
}

function resetBirthdayField(text, objDiv){
	if (text != "" && objDiv != undefined && objDiv != null){
		objDiv.val(text);
	}
}
//根据身份证得到出生日期
function getBirthDayTextFromIdCard(idCard){
	if(idCard!=null&&idCard.length==18){
		idCard=idCard.substring(6,14);
		if(idCard.substring(4,6)<=0||idCard.substring(4,6)>12){
			return "";
		}else if(idCard.substring(6,8)<=0||idCard.substring(6,8)>31){
			return "";
		}else{
			return idCard.substring(0,4)+"-"+idCard.substring(4,6)+"-"+idCard.substring(6,8);
		}
	}else if(idCard!=null&&idCard.length==15){
		idCard=idCard.substring(6,12);
		if(idCard.substring(2,4)<=0||idCard.substring(2,4)>12){
			return "";
		}else if(idCard.substring(4,6)<=0||idCard.substring(4,6)>31){
			return "";
		}else{
			return "19"+idCard.substring(0,2)+"-"+idCard.substring(2,4)+"-"+idCard.substring(4,6);
		}
	}
	return "";
}

$(document).ready(function(){
	if($("#_poorBigInfo").find("option:selected").text() == '其他'){
		$("#problemType").hide()
		$("#remark").show();
	}else{
		$("#problemType").show()
		$("#remark").hide();
	}
	if($("#id").val().length == 0){
		$("#problemType").show()
		$("#remark").hide();
	}
	
	
	if($("#convertId").val()!=undefined){
		$("#name").val($('#convert_name').text());
		$("#serverTelephone").val($('#convert_mobile').text());
		$("#involvingSteadyInfo").val($('#convert_description').text());					
	}
	$("#registrant").val('${NAME}');
	
	$('#addFamilyMembers').click(function(){
		var childrens = $("#copyAddFamilyMembersParent").children();
		var objs = $(childrens[childrens.length - 1]).clone();
		initFamilyMembersValue(objs);
		objs.prependTo($("#copyAddFamilyMembersParent"));
		
		objs.find("#ledgerSteadyWorkPeopleInfosIdCardNo").bind("blur",function(){
			  var idCard = objs.find("#ledgerSteadyWorkPeopleInfosIdCardNo").val();
			  if(null != idCard && "" != idCard){
				  idCardChanged(objs.find("#ledgerSteadyWorkPeopleInfosIdCardNo"));
			  }
		});
		if($("#isAnonymity").attr("checked")!="checked"){
			$(objs).find(".isAnonymity").each(function(){
				$(this).rules('add',{required: true,  messages:{  required: '内容不能为空！', }}); 
			})
		}
		
		//如果选择了匿名选项，新增时默认为匿名
		if($("#isAnonymity").attr("checked")=="checked"){
			var i = $("#copyAddFamilyMembersParent").children().length;
			$("input[name='_name" + (i-1) +"']").val('匿名');
		}
		
	});
	
	$("input[name='ledgerSteadyWorkPeopleInfosIdCardNo']").blur(function(){
		var idCard = $(this).val();
		if(null != idCard && "" != idCard){
			 idCardChanged($(this));
		}
	 });
	
	
	$('#registrationTime').datePicker({
		yearRange:'1930:2060',
		dateFormat:'yy-mm-dd',
		maxDate:'%y-%M-#{%d}',
		minDate:'-'+(new Date().getDate()-1)+'d'
	});
	
	$('#steadyWorkWarnLevelDate').datePicker({
		yearRange: '1900:2030',
		dateFormat: 'yy-mm-dd',
        maxDate:'+0d'
        });
	
	initOccurOrgSelector();
	
	function getParm(){
		    var parm = "";
			var childers = $("#copyAddFamilyMembersParent").children();
			if(null != childers && childers.length > 0){
				for(var i = 0; i < childers.length; i++){
					var childer = $(childers[i]);
					var name = childer.find("#ledgerSteadyWorkPeopleInfosName").val();
					var idCardNo = childer.find("#ledgerSteadyWorkPeopleInfosIdCardNo").val();
					var gender = childer.find("#ledgerSteadyWorkPeopleInfosGender").val();
					var isPartyMember = childer.find("#ledgerSteadyWorkPeopleInfosIsPartyMember").val();
					var mobileNumber = childer.find("#ledgerSteadyWorkPeopleInfosMobileNumber").val();
					var position = childer.find("#ledgerSteadyWorkPeopleInfosPositionId").val();
					var permanentAddress = childer.find("#ledgerSteadyWorkPeopleInfosPermanentAddress").val();
					var birthDay = childer.find("#birthDayTimeMembers").val();
					if(null != name && '' != name){
						parm += "name:"+name+",";
					}
					if(null != gender && '' != gender){
						parm += "gender:"+gender+",";
					}
					if(null != idCardNo && '' != idCardNo){
						parm += "idCardNo:"+idCardNo+",";
					}
					if(null != isPartyMember && '' != isPartyMember){
						parm += "partyMember:"+isPartyMember+",";
					}
					if(null != mobileNumber && '' != mobileNumber){
						parm += "mobileNumber:"+mobileNumber+",";
					}
					if(null != position && '' != position){
						parm += "position:"+position+",";
					}
					if(null != permanentAddress && '' != permanentAddress){
						parm += "permanentAddress:"+permanentAddress+",";
					}
					if(null != birthDay && '' != birthDay){
						parm += "birthDay:"+birthDay+",";
					}
					if(null != parm && '' != parm){
						parm += "},";
					}
				}
			}
			return parm;
	}
	
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
			$(form).ajaxSubmit({
				data : {
					"ledgerSteadyWorkPeopleInfosParm" : getParm(),
				},
	             success: function(data){
                     if(data==null || !data.id){
                    	 $.messageBox({ message:data, level: "error" });
                     	return;
                     }
        	   		 <s:if test='"add".equals(mode) || "copy".equals(mode) '>
				    	//$("#ledgerSteadyWorkList").addRowData(data.id, data, "first");
				    	$.messageBox({message:"新增成功!"});
				    	//if(isNeedMaintainMember){
				    //		maintainMember(data.id);
				    //		isNeedMaintainMember = false;
				    //	}
				     </s:if>
				     <s:if test='"edit".equals(mode)'>
				        //$("#ledgerSteadyWorkList").setRowData(data.id, data);
				    	$.messageBox({message:"修改成功!"});
				     </s:if>
				     <s:if test="#request.type=='convert'">	
						$("#ledgerConvertDialog").dialog("close");
			        	$("#ledgerConvertList").trigger("reloadGrid");
				     </s:if>
				     <s:else>
						 $("#ledgerSteadyWorkList").trigger("reloadGrid");
					     $("#steadyWorkDialog").dialog("close");
					     $("#ledgerSteadyWorkList").setSelection(data.id);
				     </s:else>
	      	   },
	      	   error: function(XMLHttpRequest, textStatus, errorThrown){
	      	      alert("提交错误");
	      	   }
			});
		},
		rules:{
			"ledgerSteadyWork.registrant":{
				required:true,
				exculdeParticalChar:true,
				minlength:2,
				maxlength:20
			},
			"ledgerSteadyWork.position.id":{
				required:false
			},
			"ledgerSteadyWork.createTableType.id":{
				required:true
			},
			"ledgerSteadyWork.gender.id":{
				required:false
			},
			"ledgerSteadyWork.involvingSteadyInfo":{
				required:true,
				minlength:2,
				maxlength:500
			},"ledgerSteadyWork.mobileNumber":{
				phoneAndMobile:true
			},
			"ledgerSteadyWork.serverContractor":{
				required:true,
				exculdeParticalChar:true,
				minlength:2,
				maxlength:20
			},"ledgerSteadyWork.steadyInfo":{
				required:true,
				minlength:2,
				maxlength:300
			},
			"ledgerSteadyWork.registrationTime":{
				required:true
			}
		},
		messages:{
			"ledgerSteadyWork.registrant":{
				required:"请输入登记人姓名",
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("登记人姓名至少需要输入{0}个字符"),
				maxlength:$.format("登记人姓名最多需要输入{0}个字符")
			},
			"ledgerSteadyWork.createTableType.id":{
				required:"请选择建卡类型"
			},
			"ledgerSteadyWork.involvingSteadyInfo":{
				required:"请输入涉稳事项",
				minlength:$.format("涉稳事项至少需要输入{0}个字符"),
				maxlength:$.format("涉稳事项最多需要输入{0}个字符")
			},"ledgerSteadyWork.mobileNumber":{
				phoneAndMobile:"请输入以固定电话：028-87653333或者手机：15102888888为格式的号码"	
			},"ledgerSteadyWork.serverContractor":{
				required:"请填写服务联系人",
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("服务联系人至少需要输入{0}个字符"),
				maxlength:$.format("服务联系人最多需要输入{0}个字符")
			},"ledgerSteadyWork.steadyInfo":{
				required:"请输入涉稳人员或群体稳定现状",
				minlength:$.format("涉稳事项至少需要输入{0}个字符"),
				maxlength:$.format("涉稳事项最多需要输入{0}个字符")
			},
			"ledgerSteadyWork.registrationTime":{
				required:"请选择登记时间"
			}
		},
		ignore:':hidden'
	});
	<s:if test='"add".equals(mode)'>
		$("#createTableType option").eq(1).attr("selected", true);
		$.ajax({
			async: false,
			url: "${path}/sysadmin/orgManage/getOrgRelativePath.action",
			data:{
				"organization.id":$("#organizationId").val()
			},
			success:function(responseData){
				$("#ledgerSteadyWorkOrgName").val(responseData);
				
			}
		});
	</s:if>

	$("#isAnonymity").change(function(event){
		if($("#isAnonymity").attr("checked")=="checked"){
			$("#isAnonymity").val(true);
			//$("#name").val('匿名');
			
			//涉稳人员匿名设置
			var j = $("#copyAddFamilyMembersParent").children().length;
			$("input[name='_name']").val('匿名');
			for(var i = 1; i < j; i++){
				$("input[name='_name" + i +"']").val('匿名');
			}
			
			$(".isAnonymity").each(function(){
				$(this).rules('remove');  
				$(this).removeClass("errorInput");
			})
			$(".emchar").each(function(){
				$(this).hide();  
			})

		}else{
			$("#isAnonymity").val(false);
			$(".isAnonymity").each(function(){
				$(this).rules('add',{required: true,  messages:{  required: '内容不能为空！', } }); 
			}) 
			$(".emchar").each(function(){
				$(this).show();  
			})
		}
	});
	if($("#isAnonymity").attr("checked")=="checked"){
		$(".isAnonymity").each(function(){
			$(this).rules('remove');  
		})
		$(".emchar").each(function(){
			$(this).hide();  
		})
	}else{
		$(".isAnonymity").each(function(){
			$(this).rules('add',{required: true,  messages:{  required: '内容不能为空！', }}); 
		})
	}
	setUnEdit();
});

function  getOccurOrgId(){
	return $("#occurOrgId").val();
} 
$("#createTableType option").each(function(){
	if($(this).text().indexOf("上年接转")>-1){
		$(this).hide();
	}
	<s:if test="#getFullOrgById.organization.orgLevel.internalId == @com.tianque.domain.property.OrganizationLevel@DISTRICT">
		if($(this).text().indexOf("县委县政府")>-1){
			$(this).hide();
		}
	</s:if>
	<s:if test="#getFullOrgById.organization.orgLevel.internalId == @com.tianque.domain.property.OrganizationLevel@TOWN">
		if($(this).text().indexOf("上级主管部门")>-1){
			$(this).hide();
		}
	</s:if>
	<s:if test="#getFullOrgById.organization.orgLevel.internalId < @com.tianque.domain.property.OrganizationLevel@TOWN">
		if($(this).text().indexOf("上级主管部门")>-1){
			$(this).hide();
		}
		if($(this).text().indexOf("县")>-1){
			$(this).hide();
		}
	</s:if>
})
var projectSubCategory='${ledgerSteadyWork.steadyWorkProblemType.id}';
$("#_poorInfo").children('option').each(function(){
	if($(this).attr("id")>0){
		$(this).remove();
	}
	$("#_poorInfo").val(projectSubCategory); 
})


function setUnEdit(){
	if($("#id").val()!=''){
		$("#name").attr("readOnly","readOnly");
		$("#isAnonymity").attr("disabled","disabled");
		$("#_isAnonymity").removeAttr("disabled");
		
	}
}

$("#idCardNo").blur(function(){
	var idCard = $("#idCardNo").val();
	fillGenderByIdCardNo(idCard,"gender","populationGender",true);
	/*if(null != idCard && "" != idCard){
		$.ajax({
			async: false ,
			url:"${path}/threeRecordsIssue/ledgerPoorPeopleManage/getCountrymenByIdCardNoAndOrgInternalCode.action",
		   	data:{
				"householdStaffVo.idCardNo": idCard,
	        },
			success:function(responseData){
				if(null == responseData ||　"" == responseData || responseData.idCardNo == null){
					var text = getBirthDayTextFromIdCard(idCard);
					resetBirthdayField(text, $("#birthDayTime"));
					return;
				}else{
					$("#name").val(responseData.name);
					$("#gender").val(responseData.gender.id);
					$("#birthDayTime").val(responseData.birthDay);
					$("#position").val(responseData.career);
					$("#permanentAddress").val(responseData.currentAddress);
					$("#conditionNation").val(responseData.nation.id);
					$("#serverTelephone").val(responseData.mobileNumber);
				}
			}
		});
	  }*/
  });
</script>