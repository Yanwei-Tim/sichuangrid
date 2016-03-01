<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/commonPopulation/jsFunctionInclude.jsp" />

<div id="commonPopulation" class="container container_24">
	<form id="maintainForm" method="post" action="${path}/plugin/dataManage/${tempClassName}Manage/updateTempBase.action" >
		<@pop.token />
		<input id="ajaxUrl" type="hidden" name="ajaxUrl" value="${path}/plugin/dataManage/${tempClassName}Manage/hasDuplicate.action" />
		<input id="mode" type="hidden" name="mode" value="${(mode)!}" />
		<input id="from" type="hidden" name="from" value="${(from)!}" />
		<input id="populationId" type="hidden" name="population.id" value="${(population.id)!}" />
		<input id="populationOrganizationId" type="hidden" name="population.organization.id" value="${(population.organization.id)! }" />
		<input id="provinceValue"	type="hidden" name="" value="${(population.province)!}" />
		<input id="_imgUrl"	type="hidden" name="population.imgUrl" value="${(population.imgUrl)!}" />
		<input id="cityValue"	type="hidden" name="" value="${(population.city)!}" />
		<input id="districtValue"	type="hidden" name="" value="${(population.district)!}" />
		<input type="hidden" id="actualPopulationType" name="" value="${(population.actualPopulationType)!}"/>
		<input type="hidden" id="attentionPopulationType" name="population.attentionPopulationType" value="${(population.attentionPopulationType)!}"/>
		<input id="districtOrgId" type="hidden"  value="${(population.claimDetail.districtOrgId)! }" />
		
		<@s.if test='"toPositiveInfoDialog".equals(#attr.dailogName)'>
			<input type="hidden" id="caseReason" name="population.caseReason" value="${(population.caseReason)!}"/>
		</@s.if>
		
		<div class="grid_5 lable-right">
		   	<em class="form-req">*</em>
   			<label class="form-lb1">所属网格：</label>
   		</div>
   		<div class="grid_19">
   			<input type="text"  id="commonPopulationOrgName"  name="population.organization.orgName"  readonly value="${(population.organization.orgName)!}" style="width: 99%"	class="form-txt" />
   		</div>
   		<div class='clearLine'>&nbsp;</div>
   			<div class="grid_5 lable-right">
   			<#if "unsettledPopulationTemp"!=tempClassName>
   				<em class="form-req">*</em>
   			</#if>
   			<label class="form-lb1">身份证号码：</label>
   		</div>
   		<div class="grid_7">
   			<input type="text" name="population.idCardNo" id="idCardNo" maxlength="18" value="${(population.idCardNo)!}" style="width: 99%"
   				class="form-txt { <#if "unsettledPopulationTemp"!=tempClassName> required:true,</#if>exsistedIdCard:true,idCard:true,<@s.if test="'idleYouthTempDialog'.equals(#parameters.dailogName[0])">isYouth:true,</@s.if><@s.if test="'elderlyPeopleTempDialog'.equals(#parameters.dailogName[0])">isElderly:true,</@s.if> messages:{<#if "unsettledPopulationTemp"!=tempClassName> required:'请输入身份证号码',</#if>exsistedIdCard:'该身份证号码已经存在，请重新输入',idCard:'请输入一个合法的身份证号码'<@s.if test="'idleYouthTempDialog'.equals(#parameters.dailogName[0])">,isYouth:'青少年年龄应在0~35之间'</@s.if><@s.if test="'elderlyPeopleTempDialog'.equals(#parameters.dailogName[0])">,isElderly:'老年人年龄应在60岁以上'</@s.if>}}"
   				/>
   		</div>
   		<div class="grid_4 lable-right">
   			<em class="form-req">*</em>
   			<label class="form-lb1">姓名：</label>
   		</div>
   		<div class="grid_8">
   			<input type="text" name="population.name" id="name" maxlength="20" value="${(population.name)!}"
   			class='form-txt {required:true,exculdeParticalChar:true,minlength:2,maxlength:20,messages:{required:"请输入姓名",exculdeParticalChar:"不能输入非法字符",minlength:$.format("姓名至少需要输入{0}个字符"),maxlength:$.format("姓名最多需要输入{0}个字符")}}'
   				 
   			/>
   		</div>

   		<div class='clearLine'>&nbsp;</div>
   			<#if  "unsettledPopulationTemp"==tempClassName || "householdStaffTemp"==tempClassName ||
   			"overseaPersonnelTemp"==tempClassName || "floatingPopulationTemp" == tempClassName >
   			<#else>
	   		<div id="actualPersonTypeDiv" <#if from=="FROM_LIST">style="display:none;"</#if>>
		   		<div class="grid_5 lable-right">
		   			<em class="form-req">*</em>
		   			<label class="form-lb1">实口类型：</label>
		   		</div>
		   		<div class="grid_7">
		   			<input type="radio" onclick="householdType()" class="form-btn  {actualPersonTypeNotNull:true,messages:{actualPersonTypeNotNull:'人员类型必须至少勾选一种类型'}}" name="population.actualPopulationType" value='<@s.property value="@com.tianque.service.util.PopulationType@HOUSEHOLD_STAFF"/>' 
		   					<#if stack.findValue('@com.tianque.service.util.PopulationType@HOUSEHOLD_STAFF')==(population.actualPopulationType)!>checked</#if>  />&nbsp;&nbsp;户籍人口&nbsp;&nbsp;&nbsp;&nbsp;
		   			<input type="radio" onclick="floatType()" class="form-btn {actualPersonTypeNotNull:true,messages:{actualPersonTypeNotNull:'人员类型必须至少勾选一种类型'}}" name="population.actualPopulationType" value='<@s.property value="@com.tianque.service.util.PopulationType@FLOATING_POPULATION"/>'
		   				<#if stack.findValue('@com.tianque.service.util.PopulationType@FLOATING_POPULATION')==(population.actualPopulationType)!>checked</#if>  />&nbsp;&nbsp;流动人口
		   		</div>
		   		<div class='grid_12'>&nbsp;</div>
	   		</div>
	   		</#if>
		<div class="clearLine">&nbsp;</div>
   		<div class="grid_5 lable-right">
   			<em class="form-req">*</em>
   			<label class="form-lb1">性别：</label>
  		</div>
		<div class="grid_7">
		    <select name="population.gender.id" id="gender" class='form-txt {required:true,messages:{required:"请选择性别"}}'
   				<@pop.GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">disabled</@pop.GlobalSettingTag>
	    	>
		   		<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="${(population.gender.id)!}" />
			</select>
   		</div>
   		<div class="grid_4 lable-right">
   			<label class="form-lb1">曾用名/别名：</label>
   		</div>
   		<div class="grid_8">
   			<input type="text" name="population.usedName" id="usedName" maxlength="20" value="${(population.usedName)!}"
   				class='form-txt {exculdeParticalChar:true,minlength:2,maxlength:20,messages:{exculdeParticalChar:"不能输入非法字符",minlength:$.format("曾用名/别名至少需要输入{0}个字符"),maxlength:$.format("曾用名/别名最多需要输入{0}个字符")}}'
   				 
   			/>
   		</div>
   		<div class="clearLine">&nbsp;</div>
   		<div  class="grid_5 lable-right">
  			<label class="form-lbl">联系手机：</label>
  		</div>
		<div class="grid_7">
		    <input type="text" name="population.mobileNumber" id="mobileNumber" maxlength="11" value="${(population.mobileNumber)! }" title="请输入11位以1开头的联系手机  例如：13988888888"
		    	class='form-txt {mobile:true,messages:{mobile:"手机号码输入只能是以1开头的11位数字"}}'
   				 
		    />
   		</div>
   		<div class="grid_4 lable-right">
   			<label class="form-lb1">固定电话：</label>
   		</div>
   		<div class="grid_8">
   			<input type="text" name="population.telephone" id="telephone" maxlength="20"  value="${(population.telephone)! }" title="请输入由数字和-组成的联系电话,例如：0577-88888888"
   				class='form-txt {telephone:true,messages:{telephone:$.format("固定电话不合法，只能输数字和横杠(-)")}}'
   				 
   			/>
   		</div>
   		<div class="clearLine">&nbsp;</div>
   		<div class="grid_5 lable-right">
   			<label class="form-lb1">出生日期：</label>
   		</div>
   		<div class="grid_7" id="birthdayDiv">
   			<input type="text" name="population.birthday" id="birthday" maxlength="32" readonly  value='<@s.date name="population.birthday" format="yyyy-MM-dd" />'
   					class='form-txt'
   			/>
   		</div>

   		<div  class="grid_4 lable-right">
   			<em class="form-req">*</em>
  			<label class="form-lbl">民族：</label>
  		</div>
		<div class="grid_8">
		   <select name="population.nation.id" id="nation" class='form-txt {required:true,messages:{required:"请选择民族"}}'>
			  <@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@NATION" defaultValue="${(population.nation.id)!}" />
			</select>
   		</div>
		<div class='clearLine'>&nbsp;</div>
		<div  class="grid_5 lable-right">
			<em class="form-req">*</em>
  			<label class="form-lbl">政治面貌：</label>
  		</div>
		<div class="grid_7">
		   <select name="population.politicalBackground.id" id="politicalBackground" class='form-txt {required:true,messages:{required:"请选择政治面貌"}}'>
		   		<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" defaultValue="${(population.politicalBackground.id)!}" />
			</select>
   		</div>
   		<div  class="grid_4 lable-right">
   			<em class="form-req">*</em>
  			<label class="form-lbl">文化程度：</label>
  		</div>
		<div class="grid_8">
		   <select name="population.schooling.id" id="schooling" class='form-txt {required:true,messages:{required:"请选择文化程度"}}'>
		   		<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@SCHOOLING" defaultValue="${(population.schooling.id)!}" />
			</select>
   		</div>
   		<div class="grid_5 lable-right">
   			<em class="form-req">*</em>
   			<label class="form-lb1">婚姻状况：</label>
   		</div>
   		<div class="grid_7">
   			 <select name="population.maritalState.id" id="maritalState" class='form-txt {required:true,messages:{required:"请选择婚姻状况"}}'>
		   		<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS" defaultValue="${(population.maritalState.id)!}" />
			</select>
   		</div>
   		
   		<div  class="grid_4 lable-right">
  			<label class="form-lbl">职业：</label>
  		</div>
		<div class="grid_8">
		   <select name="population.career.id" id="career" class="form-txt"
   				<@pop.GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">disabled</@pop.GlobalSettingTag>
		   >
		   		<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@CAREER" defaultValue="${(population.career.id)!}" />
			</select>
   		</div>
		<div class='clearLine' >&nbsp;</div>
		
		<div id="beforeInfo">
			
		</div>
		
   		<div class="grid_5 lable-right">
   			<label class="form-lb1">工作单位或就读学校：</label>
   		</div>
   		<div class="grid_19">
   			 <input type="text" name="population.workUnit" id="workUnit" maxlength="50" value="${(population.workUnit)! }"
   			 class="form-txt "  />
   		</div>
   		<@s.if test='!"add".equals(mode)'>
	   		<@pop.GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" condition="notEq" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">
		   		<div class="grid_4 lable-right">
				</div>
				<div class="grid_8">
					<input type="checkbox" id="isDead" name="population.death" value="true"   <@s.if test='true==population.death'>checked="checked"</@s.if>
		   				 
					/>
					<label class="form-check-text">是否死亡 </label>
				</div>
			</@pop.GlobalSettingTag>
		</@s.if>
   		
   		<div class="clearLine">&nbsp;</div>
   		<@s.if test='"householdStaffPopulationDialog".equals(#parameters.dailogName[0])'>
   		<div class="grid_4 lable-right">
   			<label class="form-lb1">户口类别：</label>
   		</div>
   		<div class="grid_8">
   			<select name="population.residenceType.id" id="residenceType" class="form-txt"
   				<@pop.GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">disabled</@pop.GlobalSettingTag>
   			>
		   		<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@RESIDENCE_TYPE" defaultValue="${(population.residenceType.id)!}" />
			</select>
   		</div>
   		<div class="clearLine">&nbsp;</div>
   	    </@s.if>
   		<div class="grid_5 lable-right">
   			<em class="form-req">*</em>
   			<label class="form-lb1">户籍地：</label>
   		</div>
   		<div class="grid_6">
			<select name="population.province" id="province"
				class='form-txt {required:true,messages:{required:"请选择户籍地省"}}' style="width:125px;"
   				<@pop.GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">disabled</@pop.GlobalSettingTag>
			>
			</select>
			省
  		</div>
   		<div class="grid_7">
			<select name="population.city" id="city" class='form-txt {required:true,messages:{required:"请选择户籍地市"}}' style="width:125px;"
   				<@pop.GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">disabled</@pop.GlobalSettingTag>
			>
			</select>
			市
 		</div>
   		<div class="grid_6">
			<select name="population.district" id="district" class='form-txt {required:true,messages:{required:"请选择户籍地县"}}' style="width:125px;"
   				<@pop.GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">disabled</@pop.GlobalSettingTag>
			>
			</select>
			县
  		</div>
   		<div class="clearLine">&nbsp;</div>
   		<div class="grid_5 lable-right">
   			<label class="form-lb1">户籍地详址：</label>
   		</div>
   		<div class="grid_19">
   			<input type="text" name="population.nativePlaceAddress" id="nativePlaceAddress" maxlength="50" value="${(population.nativePlaceAddress)! }" class="form-txt" style="width: 99%"
   				 
			/>
		</div>
		<div class='clearLine'>&nbsp;</div>
   		<div class="grid_5 lable-right">
   			<label class="form-lb1">临时居所：</label>
   		</div>
   		<div class="grid_19">
   			<input type="text" name="population.otherAddress" id="populationOtherAddress"  maxlength="50" value="${(population.otherAddress)! }" class="form-txt" style="width: 99%"
   				 
   			/>
   		</div>
   		<div class='clearLine'>&nbsp;</div>
   		<div class="grid_5 lable-right">
   			<label class="form-lb1">备注：</label>
   		</div>
   		<div class="grid_19" style="height: 120px">
   			<textarea rows="4" name="population.remark" id="remark" style="width: 99%"
   				class='form-txt {maxlength:300,messages:{maxlength:"备注最多需要输入300个字符"}}'
   			>${(population.remark)! }</textarea>
   		</div>
   		<div class='clearLine'>&nbsp;</div>
   		<input name="isSubmit" id="isSubmit" type="hidden" value="" />
	</form>
	<div id="populationDialogJsp"></div>
</div>
<script type="text/javascript">
	$.ajax({
		async: false,
		url: "${path }/sysadmin/orgManage/getOrgRelativePath.action",
		data:{
			"organization.id" : $("#populationOrganizationId").val()
		},
		success:function(responseData){
			$("#commonPopulationOrgName").val(responseData);
		}
	});



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

function resetBirthdayField(text){
	if (text!="" && $('#idCardNo').val()!=""){
		$("#birthdayDiv").html("<input type='text' name='population.birthday' id='birthday' class='form-txt' value='"+text+"' readonly/>");
	}else{
		$('#birthday').datePicker({
			yearRange: '1900:2030',
    		dateFormat: 'yy-mm-dd',
            maxDate:'+0d'});
	}
}

function manageCommonPopulation(responseData){
	//省市县的选中
	threeSelect({
		province:'province',
		city:'city',
		district:'district',
		provinceValue:responseData.province,
		cityValue:responseData.city,
		districtValue:responseData.district
	});
}

function ajaxForCommonPopulation(idCardNo){
	$.ajax({
		async:false,
		url: "${path}/commonPopulation/commonPopulationManage/getCommonPopulationByIdCardNo.action",
		data:{
			"commonPopulation.idCardNo":idCardNo
		},
		success:function(responseData){
			manageCommonPopulation(responseData);
		}
	});
}

function getCommonPopulation(idCardNo){
	if( idCardNo.length != null && idCardNo.length == 18){
		ajaxForCommonPopulation(idCardNo);
	}else if( idCardNo.length != null && idCardNo.length == 15){
		ajaxForCommonPopulation(idCardNo);
	}
}

function idCardChanged(){
	var text=$('#idCardNo').val();
	if(text==undefined){
		return;
	}
	if(isUnsettledPopulation(text)){
		$('#idCardNo').val("");
		return;
	}
	
	text=getBirthDayTextFromIdCard(text);
	resetBirthdayField(text);
	text=$('#idCardNo').val();
	getCommonPopulation(text);
}

function isUnsettledPopulation(idCardNo){
	var isTure;
	$.ajax({
		async: false,
		url: "${path }/baseinfo/unsettledPopulationManage/getUnsettledPopulationByIdCardNo.action",
		data:{
			"unsettledPopulation.organization.id" : $("#currentOrgId").val(),
			"unsettledPopulation.idCardNo":idCardNo
		},
		success:function(responseData){
			if(responseData!=null){
				isTure = true;
				$.messageBox({
					message:"未落户人口不能新增为特殊人群！",
					level: "error"
			 	});
			}else{
				isTure = false;
			}
		}
	});
	return isTure;
}

function proccessAddressBySyncData(data){
	if(data.isHaveHouse){
		$("input[name='population.houseId']", $("#maintainForm")).val(data.houseId);
		$("input[name='population.houseCode']", $("#maintainForm")).val(data.houseCode);
		$("input[name='population.noHouseReason']", $("#maintainForm")).val("");
		$("select[name='population.isHaveHouse']", $("#maintainForm")).val("true").change();
		$("#currentAddress").val(data.currentAddress);
	}else{
		$("#community,#block,#unit,#room").val("");
		$("input[name='population.houseId']", $("#maintainForm")).val("");
		$("input[name='population.houseCode']", $("#maintainForm")).val("");
		$("input[name='population.noHouseReason']", $("#maintainForm")).val(data.noHouseReason);
		$("select[name='population.isHaveHouse']", $("#maintainForm")).val("false").change();
	}
}

function searchAndSynData(idCardNo, orgId) {
	$.ajax({
		url:"${path}/commonPopulation/commonPopulationManage/getActualPopulationByOrgIdAndIdCardNo.action",
		data:{
			"orgId":orgId,
			"idCardNo":idCardNo
        },
        success:function(data){
        	if(data == null) {
        		<@pop.GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_DEPENDENT" condition="notEq">
        		notFindPopulationByIdCardNoAndOrgId();
        		</@pop.GlobalSettingTag>
        		idCardChanged();

        		<@pop.GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@SYNC_ACTUAL_POPULATION">
        		clearSearchActualData();
   				</@pop.GlobalSettingTag>
        		return;
        	}
			$.each(data,function(i,n){
				if(n.id){
					$("select[name='population."+i+".id']:visible").val(n.id);
				}else{
					$("input[name='population."+i+"']:visible").val(n);
				}
			})

			$("select[name='population.province']:visible").val(data.province).change();
			$("select[name='population.city']:visible").val(data.city).change();
			$("select[name='population.district']:visible").val(data.district);
			$("#remark").val(data.remark);

			proccessAddressBySyncData(data);

			<@pop.GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@SYNC_ACTUAL_POPULATION">
    			afterSearchActualData(data);
   			</@pop.GlobalSettingTag>
		}
	})
}

function householdType(){
	//$("#beforeInfo").load("${path}/dataManage/population/common/householdTypeDiv.ftl");
}
function floatType(){
	//$("#beforeInfo").load("${path}/dataManage/population/common/FloatingPersonTypeDiv.ftl");
}

$(document).ready(function(){
	$("#floatTypeInfo").remove();
	if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){
		$("#headerImg").attr("src",$("#_imgUrl").val());
		$(".shadow").show();
	}
	idCardChanged();
	
	<@s.if test='"toPositiveInfoDialog".equals(#attr.dailogName)'>
		$('#idCardNo').attr("readonly","readonly");
	</@s.if>

	threeSelect({
		province:'province',
		city:'city',
		district:'district',
		provinceValue:$('#provinceValue').val(),
		cityValue:$('#cityValue').val(),
		districtValue:$('#districtValue').val()
	});

	jQuery.validator.addMethod("currentAddress", function(value, element){
		if($("#currentAddressType").find("option:selected").attr("internalId")==<@s.property value="@com.tianque.domain.property.CurrentAddressType@OTHER"/>){
			var livingHouse=$("#currentAddress").val();
			return (livingHouse==null||livingHouse==undefined||livingHouse=="")?false:true;
		}
	  	return true;
	});

	jQuery.validator.addMethod("community", function(value, element){
		if($("#currentAddressType").find("option:selected").attr("internalId")==<@s.property value="@com.tianque.domain.property.CurrentAddressType@BUSINESS_HOUSE"/>) {
			var livingHouse=$("#community").val();
			return (livingHouse==null||livingHouse==undefined||livingHouse=="")?false:true;
		}
		return true;
	});

	jQuery.validator.addMethod("exsistedIdCard", function(value, element){
		var flag =true;
		if(value==null||value==undefined||value==""){return true}
		$.ajax({
			async: false ,
			url:$('#ajaxUrl').val(),
		   	data:{
				"uniqueValue":$('#idCardNo').val(),
				"districtOrgId":$('#districtOrgId').val(),
				"tempId":$('#populationId').val()
	        },
			success:function(responseData){
				flag = !eval(responseData);
			}
		});
		return flag;
	});
	
	jQuery.validator.addMethod("isElderly", function(value, element){
		if(value==null||value==undefined||value=="" ){return true};
		var d = new Date();
		if(value!=null&&value.length==18){
			value=value.substring(6,14);
			var year = value.substring(0,4);
			var month = value.substring(4,6);
			var day = value.substring(6,8);
		}else if(value!=null&&value.length==15){
			value=value.substring(6,12);
			var temp = value.substring(0,2);
			var year = parseInt(temp)+1900;
			var month = value.substring(2,4);
			var day = value.substring(4,6);
		}
		var elementDate = year;
		var dat1 = d.getFullYear()-60;//当前年份
		var nowMon = d.getMonth();
		var nowDay = d.getDate();
		if (dat1<year) {
			return false;
		}
		 if(dat1 == year){
			if((nowMon+1) < month){
				return false;
			}
			if((nowMon+1) == month){
				if(nowDay<day){
					return false;
				}
				if(nowDay==day){
					return false;
				}
			}
		}
		return true;
	});
	jQuery.validator.addMethod("isYouth", function(value, element){
		if(value==null||value==undefined||value=="" ){return true};
		var d = new Date();
		if(value!=null&&value.length==18){
			value=value.substring(6,14);
			var year = value.substring(0,4);
			var month = value.substring(4,6);
			var day = value.substring(6,8);
		}else if(value!=null&&value.length==15){
			value=value.substring(6,12);
			var temp = value.substring(0,2);
			var year = parseInt(temp)+1900;
			var month = value.substring(2,4);
			var day = value.substring(4,6);
		}
		var dat1 = d.getFullYear()-25;//当前年份
		var dat2 = d.getFullYear()-6;
		var nowMon = d.getMonth();
		var nowDay = d.getDate();
		if (dat1>year || dat2<year){
			 return false;
		}
		if(dat1==year){
			if((nowMon+1) > month){
				return false;
			}
			if(eval(nowMon+1) == month){
				if(nowDay>day){
					return false;
				}
				if(nowDay==day){
					return false;
				}
			}
		}
		if(dat2 == year){
			if(eval(nowMon+1) < month){
				return false;
			}
			if(eval(nowMon+1) == month){
				if(nowDay<day){
					return false;
				}
				if(nowDay==day){
					return false;
				}
			}
		}
		return true;
	});

	jQuery.validator.addMethod("actualPersonTypeNotNull", function(value, element){
		var flag=false;
		$("input[name='population\.actualPopulationType']").each(function(i){
			 if ($(this).attr("checked")=="checked") {
				 flag=true;
			 }
		});
		return flag;
	});
	$("#maintainForm").formValidate({
		submitHandler: function(form) {
         	$(form).ajaxSubmit({
         		async : false,
         		success : function(data){
					if(!data.id){
	               	 	$.messageBox({
							message:data,
							level: "error"
					 	});
                		return;
                	}
                
	  	   		},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	      	alert("提交错误");
	      	   	}
      	  	});
		},
		ignore:':hidden',
		rules:{
		},
		messages:{
		}
		
		
		
	});

	$("#idCardNo").blur(function(){
		if($(this).attr("createMsg")=="false") {
			searchAndSynData($(this).val(), getCurrentOrgId());
		}
	});

	<@s.if test='"add".equals(mode)'>
    $("#populationOrganizationId").val($("#currentOrgId").val());
	$.ajax({
		async: false,
		url: "${path }/sysadmin/orgManage/getOrgRelativePath.action",
		data:{
			"organization.id" : $("#currentOrgId").val()
		},
		success:function(responseData){
			$("#commonPopulationOrgName").val(responseData);
		}
	});
	</@s.if>

	<@s.if test='"nurturesWomenTempDialog".equals(#parameters.dailogName[0])'>
	 $("#gender option").each(function() {
		if($(this).text() == '女') {
	    	$(this).attr("selected", "selected");
	    }else{
			$(this).remove();
	    }
	});
	</@s.if>
});
</script>