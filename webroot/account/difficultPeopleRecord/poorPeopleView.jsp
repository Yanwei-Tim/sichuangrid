<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 

<div id="dialog-form" title="困难群众台账维护" class="container container_24" style="overflow:hidden;">
	<input type="hidden" id="organizationId" name="ledgerPoorPeople.organization.id" value="${ledgerPoorPeople.organization.id}"/>
	<input type="hidden" id="occurOrgId" name="ledgerPoorPeople.occurOrg.id" value="${ledgerPoorPeople.occurOrg.id}" />
	<input type="hidden" name="ledgerPoorPeople.id" id="id" value="${ledgerPoorPeople.id}" />
	<input type="hidden" name="ledgerPoorPeople.birthDay" id="birthDay" value="${ledgerPoorPeople.birthDay}" />
	
	<div class="grid_4 lable-right">
   		<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
	    <label class="form-lbl">身份证号：</label>
	</div>
	<div class="grid_7"><!-- ,exsistedIdCard:false -->
   		<input type="text" name="ledgerPoorPeople.idCardNo" id="idCardNo" value="${ledgerPoorPeople.idCardNo }" readonly="readonly" class="form-txt" />
	</div>
	<div class="grid_4 lable-right">
   		<em class="form-req">*</em>
   		<label class="form-lbl">姓名：</label></div>
    <div class="grid_7">
   		<input type="text" id="name" name="ledgerPoorPeople.name" value="${ledgerPoorPeople.name }" readonly="readonly" class="form-txt" />
    </div>
    <div class='clearLine'>&nbsp;</div>
    
	<div class="grid_4 lable-right">
	    <label class="form-lbl">性别：</label>
	</div>
	<div class="grid_7">
   		<select id="gender" name="ledgerPoorPeople.gender.id" class="form-txt" disabled>
	   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@GENDER" defaultValue="${ledgerPoorPeople.gender.id}"/></select>
	</div>
	<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">建卡类型：</label>
	 	</div>
		<div class="grid_7">
			<select name="ledgerPoorPeople.createTableType.id" id="createTableType"  class='form-txt' disabled>
		   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@LEDGER_PEOPLEASPIRATION_CREATE_TABLE_TYPE"  defaultValue="${ledgerPoorPeople.createTableType.id}" />
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
	<div  class="grid_4 lable-right" >
		<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
		<label class="form-lbl">编号： </label>
	</div>
	<div class="grid_7" id="userDiv">
   		<input type="text" name="ledgerPoorPeople.serialNumber" value="${ledgerPoorPeople.serialNumber}" readonly="readonly" class="form-txt" />
	</div>
	<div class="grid_4 lable-right">
		<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
	    <label class="form-lbl">职业或身份：</label>
	</div>
	<div class="grid_7">
   		<select id="position" name="ledgerPoorPeople.position.id" class="form-txt" disabled>
	   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@POSITION_OR_STATUS" defaultValue="${ledgerPoorPeople.position.id}"/></select>
	</div>
	<div class="grid_4 lable-right">
	     <label class="form-lbl">常住地址：</label>
	</div>
    <div class="grid_7">
   		<input type="text" name="ledgerPoorPeople.permanentAddress" value="${ledgerPoorPeople.permanentAddress }" class="form-txt" style="width: 99%;" />
    </div>
    <div class="grid_4 lable-right">
		<label class="form-lbl">发生网格：</label>
	</div>
	<div class="grid_7">
		<input type="text" id="occurOrg" class="form-txt" readonly="readonly" disabled/>
    </div>
    <div class='clearLine'>&nbsp;</div>
    
	<div class="grid_4 lable-right">
	    <label class="form-lbl">联系电话：</label>
	</div>
	<div class="grid_7">
   		<input type="text" name="ledgerPoorPeople.mobileNumber" id="serverTelephone" maxlength="13"  value="${ledgerPoorPeople.mobileNumber }"
   			class="form-txt" readonly="readonly"/>
	</div>
	<div class="grid_4 lable-right">
	 	<label class="form-lbl">是否户主：</label>
	</div>
    <div class="grid_7">
   		<select name="ledgerPoorPeople.owner" class="form-txt" disabled>
   			<option value="false" <s:if test="false == ledgerPoorPeople.owner">selected="selected"</s:if>>否</option>
   			<option value="true" <s:if test="true == ledgerPoorPeople.owner">selected="selected"</s:if>>是</option>
   		</select>
    </div>
    <div class='clearLine'>&nbsp;</div>
    
    <div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">户口号：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="ledgerPoorPeople.accountNo" id="accountNo" maxlength="20"
			value="${ledgerPoorPeople.accountNo}" class="form-txt dialogtip" readonly="readonly"/>
	</div>
	<div class="grid_4 lable-right">
	    <label class="form-lbl">家庭人口：</label>
	</div>
	<div class="grid_7">
   		<input type="text" name="ledgerPoorPeople.memberNo" readonly="readonly" value="${ledgerPoorPeople.memberNo }" class="form-txt" />
	</div>
	<div class='clearLine'>&nbsp;</div>
	
	<div class="grid_4 lable-right">
		<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
		<label class="form-lbl">保障类型：</label>
	</div>
	<div class="grid_7">
	   <select name="ledgerPoorPeople.securityType.id" class="form-txt" id="securityType" disabled>
	   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@LEDGER_POOR_PEOPLE_SECURITY_TYPE"  defaultValue="${ledgerPoorPeople.securityType.id}" />
	   </select>
	</div>
	<div class="grid_4 lable-right">
	    <label class="form-lbl">人均年收入：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="ledgerPoorPeople.annualPerCapitaIncome" value="${ledgerPoorPeople.annualPerCapitaIncome}" class="form-txt" readonly="readonly"/>
	</div>
	<div class='clearLine'>&nbsp;</div>
	
	<div class="grid_4 lable-right">
		<label class="form-lbl">登记人：</label>
	</div>
	<div class="grid_7">
		<input type="text"  name="ledgerPoorPeople.registrant" id="registrant"  maxlength="20" value="${ledgerPoorPeople.registrant}" 
			class='form-txt' readonly="readonly"/>
	</div>	
	
	<div class="grid_4 lable-right">
	    <label class="form-lbl">登记时间：</label>
	</div>
	<div class="grid_7" id="birthdayDiv">
   		<input type="text" name="ledgerPoorPeople.registrationTime" id="registrationTime" value="<s:date name="ledgerPoorPeople.registrationTime" format="yyyy-MM-dd"/>" class="form-txt" readonly="readonly" disabled/>
	</div>
	<div class='clearLine'>&nbsp;</div>
	
	<div class="grid_4 lable-right">
	    <label class="form-lbl">出生年月：</label>
	</div>
	<div class="grid_7" id="birthdayDiv">
   		<input type="text" name="ledgerPoorPeople.birthDay" id="birthDayTime" value="<s:date name="ledgerPoorPeople.birthDay" format="yyyy-MM-dd"/>" class="form-txt" readonly="readonly"/>
	</div>
	<div class="grid_4 lable-right">
	    <label class="form-lbl">困难程度：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="ledgerPoorPeople.difficultyDegree" value="${ledgerPoorPeople.difficultyDegree }" class="form-txt" readonly="readonly"/>
	</div>
	<div class='clearLine'>&nbsp;</div>
	
	<div class="grid_4 lable-right">
	    <label class="form-lbl">关注程度：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="ledgerPoorPeople.attentionDegree" value="${ledgerPoorPeople.attentionDegree}"  class="form-txt" readonly="readonly"/>
	</div>
	<div class="grid_4 lable-right">
	    <label class="form-lbl">户籍地址：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="ledgerPoorPeople.censusRegisterAddress" value="${ledgerPoorPeople.censusRegisterAddress}" class="form-txt" readonly="readonly"/>
	</div>
	<div class='clearLine'>&nbsp;</div>
	
	<div class="grid_4 lable-right">
	    <label class="form-lbl">户籍性质：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="ledgerPoorPeople.censusRegisterNature" value="${ledgerPoorPeople.censusRegisterNature}" class="form-txt" readonly="readonly" />
	</div>
	<div class="grid_4 lable-right">
	 	<label class="form-lbl">是否党员：</label>
	</div>
    <div class="grid_7">
   		<select name="ledgerPoorPeople.isPartyMember" class="form-txt" disabled>
   			<option value="false" <s:if test="false == ledgerPoorPeople.isPartyMember">selected="selected"</s:if>>否</option>
   			<option value="true" <s:if test="true == ledgerPoorPeople.isPartyMember">selected="selected"</s:if>>是</option>
   		</select>
    </div>
    <div class='clearLine'>&nbsp;</div>
    
    <div class="grid_4 lable-right">
	 	<label class="form-lbl">民族：</label>
	</div>
    <div class="grid_7">
   		<select  class="form-txt" id="conditionNation" name="ledgerPoorPeople.national.id" disabled>
			   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@NATION" defaultValue="${ledgerPoorPeople.national.id}" />
		</select>
    </div>
    <div class="grid_4 lable-right">
		<label class="form-lbl">文化程度： </label>
	</div>
	<div class="grid_7">
		<select  class="form-txt" id="conditionSchooling" name="ledgerPoorPeople.levelEducation.id" disabled>
			   	<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@SCHOOLING" defaultValue="${ledgerPoorPeople.levelEducation.id}" />
		</select>
	</div>
    <div class='clearLine'>&nbsp;</div>
    
    <div class="grid_4 lable-right">
	   <label class="form-lbl">婚姻状况： </label>
	</div>
	<div class="grid_7">
		<select class="form-txt" id="conditionMaritalState" name="ledgerPoorPeople.maritalStatus.id" disabled>
			<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@MARITAL_STATUS" defaultValue="${ledgerPoorPeople.maritalStatus.id}"  />
		</select>
	</div>
	<div class="grid_4 lable-right">
	    <label class="form-lbl">健康状况：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="ledgerPoorPeople.healthCondition" value="${ledgerPoorPeople.healthCondition}" class="form-txt" readonly="readonly" />
	</div>
	<div class='clearLine'>&nbsp;</div>
		
	<div class="grid_4 lable-right">
	    <label class="form-lbl">外出及外出原因：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="ledgerPoorPeople.goOutReason" value="${ledgerPoorPeople.goOutReason}" class="form-txt" readonly="readonly" />
	</div>
	<div class="grid_4 lable-right">
		<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
		<label class="form-lbl">具体需求：</label>
	</div>
	<div class="grid_7">
	   <select name="ledgerPoorPeople.requiredType.id" class="form-txt" id="createTableType" disabled>
	   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@LEDGER_POOR_PEOPLE_SPECIFIC_NEED"  defaultValue="${ledgerPoorPeople.requiredType.id}" />
	   </select>
	</div>
	<div class='clearLine'>&nbsp;</div>
    
	<div class="grid_4 lable-right">
   		<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if><label class="form-lbl">困难类型：</label></div>
    <div class="grid_7">
   		<select name="ledgerPoorPeople.poorType.id" id="_poorBigInfo" class="form-txt" disabled>
   			<pop:PropertyDictReleationSelectTag name="@com.tianque.plugin.account.property.PropertyTypes@LEDGER_POOR_PEOPLE_DIFFICULT_TYPE" defaultValue="${ledgerPoorPeople.poorType.id}" 
		   			reletionId="_poorInfo" reletionName="@com.tianque.plugin.account.property.PropertyTypes@LEDGER_POOR_PEOPLE_DIFFICULT_CAUSE" id="_poorBigInfo"/></select>
    </div>
    <div class="grid_4 lable-right">
   		<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if><label class="form-lbl">困难原因：</label>
   	</div>
    <div class="grid_7">
   		<select name="ledgerPoorPeople.poorSource.id" id="_poorInfo" class="form-txt" disabled>
	   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@LEDGER_POOR_PEOPLE_DIFFICULT_CAUSE" defaultValue="${ledgerPoorPeople.poorSource.id}" /></select>
    </div>
    <div class='clearLine'>&nbsp;</div>
    <div id ="dynamicAddModule" ></div>
	<div class='clearLine'>&nbsp;</div>
	
	<div class='grid_1 lable-right'></div>
    <div class="grid_22 lable-center" style=" overflow:scroll;border-collapse: collapse;border: solid #000 1px;height:110;width:640;" >
  		<div class="grid_4 lable-right">
		    <label class="form-lbl">家庭成员：</label>
	    </div>
	    <div class="grid_5"></div>
	    <div class="grid_4 lable-right">
	    </div>
	    <div class="grid_11"></div>
	    
	  <div id="copyAddFamilyMembersParent">
	  <div id="copyAddFamilyMembers">
	      <c:forEach items="${ledgerPoorPeople.ledgerPoorPeopleMembers}" var="ledgerPoorPeopleMembers">   
          		<div class="grid_3 lable-right">
   					<label class="form-lbl">姓名：</label>
   				</div>
    			<div class="grid_3">
   					<input type="text" name=".name" value="${ledgerPoorPeopleMembers.name }" readonly="readonly" class="form-txt" />
    			</div>
				<div class="grid_2 lable-right">
	    			<label class="form-lbl">性别：</label>
				</div>
				<div class="grid_3">
   					<select name="ledgerPoorPeople.ledgerPoorPeopleMembers.gender.id" class="form-txt" disabled>
	   					<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@GENDER" defaultValue="${ledgerPoorPeopleMembers.gender.id}" /></select>
				</div>
	   			<div class="grid_3 lable-right">
	    		<label class="form-lbl">身份证号：</label>
				</div>
				<div class="grid_3">
   					<input type="text" name="ledgerPoorPeople.ledgerPoorPeopleMembers.idCardNo" value="${ledgerPoorPeopleMembers.idCardNo }" readonly="readonly" class="form-txt" />
				</div>
	    
	    		<div class="grid_3 lable-right">
					<label class="form-lbl">保障类型：</label>
				</div>
				<div class="grid_3">
	  	 			<select name="ledgerPoorPeople.ledgerPoorPeopleMembers.securityType.id" class="form-txt" disabled>
	   					<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@LEDGER_POOR_PEOPLE_SECURITY_TYPE" defaultValue="${ledgerPoorPeopleMembers.securityType.id}" />
	  				 </select>
				</div>
				<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_3 lable-right" >
	    	<label class="form-lbl">出生年月：</label>
		</div>
		<div class="grid_3" id="birthdayDiv">
   			<input type="text" name="ledgerPoorPeopleMembers.birthday" id="birthDayTimeMembers" value="<fmt:formatDate value="${ledgerPoorPeopleMembers.birthday }" pattern='yyyy-MM-dd'/>" class="form-txt" readonly="readonly"/>
		</div>
		<div class="grid_3 lable-right">
	    	<label class="form-lbl">与户主关系：</label>
		</div>
		<div class="grid_3">
   			<input type="text" name="ledgerPoorPeopleMembers.headHouseholdRelation" id="headHouseholdRelationMembers" value="${ledgerPoorPeopleMembers.headHouseholdRelation }" class="form-txt" readonly="readonly"/>
		</div>
		<div class="grid_3 lable-right">
	 	   <label class="form-lbl">是否失业：</label>
	    </div>
        <div class="grid_3">
   		    <select name="ledgerPoorPeopleMembers.unemployment" id="unemploymentMembers" class="form-txt" disabled>
   		       <option value="" <c:if test="${ledgerPoorPeopleMembers.unemployment == ''}">selected="selected"</c:if>>请选择</option>
   			   <option value="false" <c:if test="${ledgerPoorPeopleMembers.unemployment == false}">selected="selected"</c:if>>否</option>
   			   <option value="true" <c:if test="${ledgerPoorPeopleMembers.unemployment == true}">selected="selected"</c:if>>是</option>
   		    </select>
        </div>
	    <div class="grid_3 lable-right">
	    	<label class="form-lbl">健康状况：</label>
	    </div>
	    <div class="grid_3">
   			<input type="text" name="ledgerPoorPeopleMembers.healthCondition" id="healthConditionMembers" value="${ledgerPoorPeopleMembers.healthCondition }" class="form-txt" readonly="readonly"/>
	    </div>
          </c:forEach>   
	  </div>
	  </div>
  	</div>
    
</div>
<script type="text/javascript">

function initOccurOrgSelector(){
	var tree=$("#occurOrg").treeSelect({
		inputName:"ledgerPoorPeople.occurOrg.id",
		loadCom:function(){
			if(<s:property value='!"add".equals(mode)'/>){
				$.setTreeValue(getDefaultOccurOrg(),tree); 
			}
		}
	});
}

function getDefaultOccurOrg(){
	<s:if test="null!=ledgerPoorPeople.occurOrg && null!=ledgerPoorPeople.occurOrg.id">
		return "${ledgerPoorPeople.occurOrg.id}";
	</s:if>
	<s:else>
		return -1;
	</s:else>
}

$(document).ready(function(){
	initPoorBigInfoChange();
	$('#registrationTime').datePicker({
		yearRange: '1900:2030',
		dateFormat: 'yy-mm-dd',
        maxDate:'+0d'
        });
	
	initOccurOrgSelector();
});

function  getOccurOrgId(){
	return $("#occurOrgId").val();
} 

function initPoorBigInfoChange(){
	 var poorBigInfo = $("#_poorBigInfo").find("option:selected").text();
    if(undefined != poorBigInfo && null != poorBigInfo){
    	var obj = $("#dynamicAddModule").find("#dynamicAddModuleChildren");
    	if(undefined != obj && null != obj){
    		obj.remove();
    	}
    	$("#dynamicAddModule").append("<div id = 'dynamicAddModuleChildren'></div>");
    	obj = $("#dynamicAddModule").find("#dynamicAddModuleChildren");
    	if("请选择" == poorBigInfo){
    		return;
    	}
    	if(poorBigInfo == "生活"){
    		obj.append(addOrphan());
    		obj.append(addLonelinessOld());
    		obj.append(addClearLine());
    		obj.append(addSkillsSpeciality());
    	}
		if(poorBigInfo == "医疗"){
				obj.append(addOtherInfo());
    	}
		if(poorBigInfo == "住房"){
				obj.append(addHousing());
    		obj.append(addHousingStructure());
    		obj.append(addClearLine());
    		obj.append(addHousingArea());
    		obj.append(addBuildHouseDate());
    		obj.append(addClearLine());
		}
		if(poorBigInfo == "就学"){
				obj.append(addOrphan());
		}
		if(poorBigInfo == "就业"){
				obj.append(addUnemploymentDate());
    		obj.append(addUnemploymentReason());
    		obj.append(addClearLine());
    		obj.append(addRegistrationCardNumber());
    		obj.append(addSkillsSpeciality());
    		obj.append(addClearLine());
		}
    }
}

function  getOccurOrgId(){
	return $("#occurOrgId").val();
} 

function addLonelinessOld(){
	var oldHtml = "<div id='lonelinessOldDiv'>"
                 +"<div class='grid_4 lable-right'>"
	   					+"<label class='form-lbl'>是否孤老：</label>"
				  +"</div>"
				  +"<div class='grid_7'>"
	    		  		+"<select name='ledgerPoorPeople.lonelinessOld' id='lonelinessOld' class='form-txt' disabled>"
		   					+"<option value='false' <s:if test='false == ledgerPoorPeople.lonelinessOld'>selected='selected'</s:if>>否</option>"
		   					+"<option value='true' <s:if test='true == ledgerPoorPeople.lonelinessOld'>selected='selected'</s:if>>是</option>"
	    		 		 +"</select>"
				  +"</div>"
				 +"</div>";
	return oldHtml;
}

function addOrphan(){
	var oldHtml = "<div id='orphanDiv'>"
                 +"<div class='grid_4 lable-right'>"
	   					+"<label class='form-lbl'>是否孤儿：</label>"
				  +"</div>"
				  +"<div class='grid_7'>"
	    		  		+"<select name='ledgerPoorPeople.orphan' id='orphan' class='form-txt' disabled>"
		   					+"<option value='false' <s:if test='false == ledgerPoorPeople.orphan'>selected='selected'</s:if>>否</option>"
		   					+"<option value='true' <s:if test='true == ledgerPoorPeople.orphan'>selected='selected'</s:if>>是</option>"
	    		 		 +"</select>"
				  +"</div>"
				 +"</div>";
	return oldHtml;
}

function addSkillsSpeciality(){
	var oldHtml = "<div id='skillsSpecialityDiv'>"
		          +"<div class='grid_4 lable-right'>"
					+"<label class='form-lbl'>技能特长：</label>"
	 			  +"</div>"
	  			  +"<div class='grid_7'>"
 					+"<input type='text' name='ledgerPoorPeople.skillsSpeciality' id='skillsSpeciality' value='${ledgerPoorPeople.skillsSpeciality}'  class='form-txt' readonly='readonly'/>"
	 			  +"</div>"
	 			 +"</div>";
	return oldHtml;
}

function addHousing(){
	var oldHtml = "<div id='housingDiv'>"
				  +"<div class='grid_4 lable-right'>"
	   					+"<label class='form-lbl'>有无住房：</label>"
				  +"</div>"
				  +"<div class='grid_7'>"
	    		  		+"<select name='ledgerPoorPeople.housing' id='housing' class='form-txt' disabled>"
		   					+"<option value='false' <s:if test='false == ledgerPoorPeople.housing'>selected='selected'</s:if>>无</option>"
		   					+"<option value='true' <s:if test='true == ledgerPoorPeople.housing'>selected='selected'</s:if>>有</option>"
	    		 		 +"</select>"
				  +"</div>";
				  +"</div>"
	return oldHtml;
}

function addHousingStructure(){
	var oldHtml = "<div id='housingStructureDiv'>"
		          +"<div class='grid_4 lable-right'>"
					+"<label class='form-lbl'>住房结构：</label>"
	 			  +"</div>"
	  			  +"<div class='grid_7'>"
 					+"<input type='text' name='ledgerPoorPeople.housingStructure' id='housingStructure' value='${ledgerPoorPeople.housingStructure}' class='form-txt' readonly='readonly'/>"
	 			  +"</div>"
	 			 +"</div>";
	return oldHtml;
}

function addHousingArea(){
	var oldHtml = "<div id='housingAreaDiv'>"
		          +"<div class='grid_4 lable-right'>"
					+"<label class='form-lbl'>住房面积：</label>"
	 			  +"</div>"
	  			  +"<div class='grid_7'>"
 					+"<input type='text' name='ledgerPoorPeople.housingArea' id='housingArea' value='${ledgerPoorPeople.housingArea}'  class='form-txt' readonly='readonly'/>"
	 			  +"</div>"
	 			 +"</div>";
	return oldHtml;
}

function addBuildHouseDate(){
	var oldHtml = "<div id='buildHouseDateDiv'>"
		          +"<div class='grid_4 lable-right'>"
					+"<label class='form-lbl'>建房年月：</label>"
	 			  +"</div>"
	  			  +"<div class='grid_7'>"
 					+"<input type='text' name='ledgerPoorPeople.buildHouseDate' id='buildHouseDate' value='<s:date name='ledgerPoorPeople.buildHouseDate' format='yyyy-MM-dd'/>' class='form-txt' readonly='readonly'/>"
	 			  +"</div>"
	 			 +"</div>";
	return oldHtml;
}

function addUnemploymentDate(){
	var oldHtml = "<div id='unemploymentDateDiv'>"
		          +"<div class='grid_4 lable-right'>"
					+"<label class='form-lbl'>失业时间：</label>"
	 			  +"</div>"
	  			  +"<div class='grid_7'>"
 					+"<input type='text' name='ledgerPoorPeople.unemploymentDate' id='unemploymentDate' value='<s:date name='ledgerPoorPeople.unemploymentDate' format='yyyy-MM-dd'/>' class='form-txt' readonly='readonly'/>"
	 			  +"</div>"
	 			 +"</div>";
	return oldHtml;
}

function addUnemploymentReason(){
	var oldHtml = "<div id='unemploymentReasonDiv'>"
		          +"<div class='grid_4 lable-right'>"
					+"<label class='form-lbl'>失业原因：</label>"
	 			  +"</div>"
	  			  +"<div class='grid_7'>"
 					+"<input type='text' name='ledgerPoorPeople.unemploymentReason' id='unemploymentReason' value='${ledgerPoorPeople.unemploymentReason}'  class='form-txt' readonly='readonly'/>"
	 			  +"</div>"
	 			 +"</div>";
	return oldHtml;
}

function addRegistrationCardNumber(){
	var oldHtml = "<div id='registrationCardNumberDiv'>"
		          +"<div class='grid_4 lable-right'>"
					+"<label class='form-lbl'>登记证号：</label>"
	 			  +"</div>"
	  			  +"<div class='grid_7'>"
 					+"<input type='text' name='ledgerPoorPeople.registrationCardNumber' id='registrationCardNumber' value='${ledgerPoorPeople.registrationCardNumber}'  class='form-txt' readonly='readonly'/>"
	 			  +"</div>"
	 			 +"</div>";
	return oldHtml;
}

function addOtherInfo(){
	var oldHtml = "<div id='otherInfoDiv'>"
		          +"<div class='grid_4 lable-right'>"
					+"<label class='form-lbl'>其他：</label>"
	 			  +"</div>"
	  			  +"<div class='grid_7'>"
 					+"<input type='text' name='ledgerPoorPeople.otherInfo' id='otherInfo' value='${ledgerPoorPeople.registrationCardNumber}'  class='form-txt' readonly='readonly'/>"
	 			  +"</div>"
	 			 +"</div>";
	return oldHtml;
}
function addClearLine(){
	return "<div class='clearLine'>&nbsp;</div>";
}
</script>