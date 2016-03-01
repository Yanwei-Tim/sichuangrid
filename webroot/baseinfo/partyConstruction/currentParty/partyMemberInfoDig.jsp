<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<div id="commonPopulation" class="container container_24">
	<form id="maintainForm" method="post" action="<s:property value="@com.tianque.controller.BaseInfoActionDefine@getAction(actionName)"/>" >
		<input id="mode" type="hidden" name="mode" value="${mode}" />
		<input id="populationId" type="hidden" name="population.id" value="${population.id}" />
		<input id="ajaxUrl" type="hidden" name="ajaxUrl" value="<s:property value="@com.tianque.controller.BaseInfoActionDefine@getAction(ajaxUrl)"/>" />
		<input id="populationOrganizationId" type="hidden" name="population.organization.id" value="${population.organization.id }" />
		<input id="_imgUrl" name="population.imgUrl" type="hidden" value="${population.imgUrl}"/>
		<input id="provinceValue"	type="hidden" name="" value="${population.province}" />
		<input id="cityValue"	type="hidden" name="" value="${population.city}" />
		<input id="districtValue"	type="hidden" name="" value="${population.district}" />
		<input type="hidden" id="actualPopulationType" name="population.actualPopulationType" />
		<input type="hidden" id="attentionPopulationType" name="population.attentionPopulationType" />
		<input id="" type="hidden" name="contextId" value="${contextId}" />
		
		<div class="grid_4 lable-right">
		   	<em class="form-req">*</em>
   			<label class="form-lb1">所属网格：</label>
   		</div>
   		<div class="grid_18">
   			<input type="text"  id="commonPopulationOrgName"  name="population.organization.orgName"  readonly value="${population.organization.orgName}" style="width: 99%"	class="form-txt" />
   		</div>
   		<div class='clearLine'>&nbsp;</div>
   			<div class="grid_4 lable-right">
   			<em class="form-req">*</em>
   			<label class="form-lb1">身份证号码：</label>
   		</div>
   		<div class="grid_7">
   			<input type="text" name="population.idCardNo" id="idCardNo" maxlength="18" value="${population.idCardNo}" style="width: 99%"
   				class="form-txt {required:true,exsistedIdCard:true,idCard:true,messages:{required:'请输入身份证号码',exsistedIdCard:'该身份证号码已经存在，请重新输入',idCard:'请输入一个合法的身份证号码'}}" />
   		</div>
   		<div class="grid_4 lable-right">
   			<em class="form-req">*</em>
   			<label class="form-lb1">姓名：</label>
   		</div>
   		<div class="grid_7">
   			<input type="text" name="population.name" id="name" maxlength="20" value="${population.name}" 
   			class='form-txt {required:true,exculdeParticalChar:true,minlength:2,maxlength:20,messages:{required:"请输入姓名",exculdeParticalChar:"不能输入非法字符",minlength:$.format("姓名至少需要输入{0}个字符"),maxlength:$.format("姓名最多需要输入{0}个字符")}}'
   			/>
   		</div>

   		<div class='clearLine'>&nbsp;</div>
   		
   		<div class="grid_4 lable-right">
   			<em class="form-req">*</em>
   			<label class="form-lb1">性别：</label>
  		</div>
		<div class="grid_7">
		    <select name="population.gender.id" id="gender" class='form-txt {required:true,messages:{required:"请选择性别"}}' 
	    	>
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="${population.gender.id}" />
			</select>
   		</div>
   		<div class="grid_4 lable-right">
   			<label class="form-lb1">曾用名/别名：</label>
   		</div>
   		<div class="grid_7">
   			<input type="text" name="population.usedName" id="usedName" maxlength="20" value="${population.usedName}" 
   				class='form-txt {exculdeParticalChar:true,minlength:2,maxlength:20,messages:{exculdeParticalChar:"不能输入非法字符",minlength:$.format("曾用名/别名至少需要输入{0}个字符"),maxlength:$.format("曾用名/别名最多需要输入{0}个字符")}}' 
   			/>
   		</div>
   		<div class="clearLine">&nbsp;</div>
   		<div  class="grid_4 lable-right">
  			<label class="form-lbl">联系手机：</label>
  		</div>
		<div class="grid_7">
		    <input type="text" name="population.mobileNumber" id="mobileNumber" maxlength="11" value="${population.mobileNumber }" title="请输入11位以1开头的联系手机  例如：13988888888" 
		    	class='form-txt {mobile:true,messages:{mobile:"手机号码输入只能是以1开头的11位数字"}}' 
		    />
   		</div>
   		<div class="grid_4 lable-right">
   			<label class="form-lb1">固定电话：</label>
   		</div>
   		<div class="grid_7">
   			<input type="text" name="population.telephone" id="telephone" maxlength="20"  value="${population.telephone }" title="请输入由数字和-组成的联系电话,例如：0577-88888888" 
   				class='form-txt {telephone:true,messages:{telephone:$.format("固定电话不合法，只能输数字和横杠(-)")}}'
   			/>
   		</div>
   		<div class="clearLine">&nbsp;</div>
   		<div class="grid_4 lable-right">
   			<label class="form-lb1">出生日期：</label>
   		</div>
   		<div class="grid_7" id="birthdayDiv">
   			<input type="text" name="population.birthday" id="birthday" maxlength="32" readonly  value='<s:date name="population.birthday" format="yyyy-MM-dd" />'class="form-txt" 
   			/>
   		</div>
		<div  class="grid_4 lable-right">
  			<label class="form-lbl">民族：</label>
  		</div>
		<div class="grid_7">
		   <select name="population.nation.id" id="nation" class="form-txt" 
		   >
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@NATION" defaultValue="${population.nation.id}" />
			</select>
   		</div>
		<div class='clearLine'>&nbsp;</div>
		<div  class="grid_4 lable-right">
  			<label class="form-lbl">政治面貌：</label>
  		</div>
		<div class="grid_7">
		   <select name="population.politicalBackground.id" id="politicalBackground" class="form-txt" 
		   >
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" defaultValue="${population.politicalBackground.id}" />
			</select>
   		</div>
   		<div  class="grid_4 lable-right">
  			<label class="form-lbl">文化程度：</label>
  		</div>
		<div class="grid_7">
		   <select name="population.schooling.id" id="schooling" class="form-txt" 
		   >
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SCHOOLING" defaultValue="${population.schooling.id}" />
			</select>
   		</div>
   		 <div class="clearLine">&nbsp;</div>
   		 <div  class="grid_4 lable-right">
  			<label class="form-lbl">职业：</label>
  		</div>
		<div class="grid_7">
		   <select name="population.career.id" id="career" class="form-txt" 
		   >
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@CAREER" defaultValue="${population.career.id}" />
			</select>
   		</div>
   		<div class="grid_4 lable-right">
   			<label class="form-lb1">工作单位或就读学校：</label>
   		</div>
   		<div class="grid_7">
   			 <input type="text" name="population.workUnit" id="workUnit" maxlength="50" value="${population.workUnit }"  
   			 class="form-txt " 
   			 />
   		</div>
   		<div class="clearLine">&nbsp;</div>
   		<div class="grid_4 lable-right">
   			<label class="form-lb1">婚姻状况：</label>
   		</div>
   		<div class="grid_7">
   			 <select name="population.maritalState.id" id="maritalState" class="form-txt" 
   			 >
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS" defaultValue="${population.maritalState.id}" />
			</select>
   		</div>
   		<s:if test='!"add".equals(mode)'>
	   		<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" condition="notEq" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">
		   		<div class="grid_4 lable-right">
				</div>
				<div class="grid_7">
					<input type="checkbox" id="isDead" name="population.death" value="true"   <s:if test='true==population.death'>checked="checked"</s:if> 
					/>
					<label class="form-check-text">是否死亡 </label>
				</div>
			</pop:GlobalSettingTag>
		</s:if>
   		<div class="clearLine">&nbsp;</div>
   		<div class="grid_4 lable-right">
   			<label class="form-lb1">身高：</label>
   		</div>
   		<div class="grid_6">
   			<input type="text" name="population.stature" id="stature" maxlength="3"  value="${population.stature }" title="请输入不大于300的正整数，如175"  
   				class='form-txt {stature:true,messages:{stature:"请输入不大于300的正整数"}}' 
   			/>
   		</div>
   		<div class="grid_1">cm</div>
   		<div  class="grid_4 lable-right">
  			<label class="form-lbl">血型：</label>
  		</div>
		<div class="grid_7">
		   <select name="population.bloodType.id" id="bloodType" class="form-txt" 
		   >
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@BLOOD_TYPE" defaultValue="${population.bloodType.id}" />
			</select>
   		</div>
   		<div class="clearLine">&nbsp;</div>

   		<div class="grid_4 lable-right">
   			<label class="form-lb1">宗教信仰：</label>
   		</div>
   		<div class="grid_7">
   			<select name="population.faith.id" id="faith" class="form-txt" 
   			>
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@FAITH" defaultValue="${population.faith.id}" />
			</select>
   		</div>
   		<div class="grid_4 lable-right">
   			<label class="form-lb1">电子邮箱：</label>
   		</div>
   		<div class="grid_7">
   			<input type="text" name="population.email" id="email" maxlength="50"  value="${population.email}"  
   				class='form-txt {email:true,messages:{email:"请输入一个合法的电子邮箱"}}' 
   			/>
   		</div>
   		<div class="clearLine">&nbsp;</div>
   		<s:if test='"householdStaffPopulationDialog".equals(#parameters.dailogName[0])'>
   		<div class="grid_4 lable-right">
   			<label class="form-lb1">户口类别：</label>
   		</div>
   		<div class="grid_7">
   			<select name="population.residenceType.id" id="residenceType" class="form-txt" 
   			>
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@RESIDENCE_TYPE" defaultValue="${population.residenceType.id}" />
			</select>
   		</div>
   		<div class="clearLine">&nbsp;</div>
   	    </s:if>
   		<div class="grid_4 lable-right">
   			<em class="form-req">*</em>
   			<label class="form-lb1">户籍地：</label>
   		</div>
   		<div class="grid_5">
			<select name="population.province" id="province" 
				class='form-txt {required:true,messages:{required:"请选择户籍地省"}}' 
			>
			</select>
  		</div>
   		<div class="grid_1">
   		  <label class="form-lbl">省</label>
   		</div>
   		<div class="grid_5">
			<select name="population.city" id="city" class='form-txt {required:true,messages:{required:"请选择户籍地市"}}' 
			>
			</select>
 		</div>
   		<div class="grid_1">
   		  <label class="form-lbl">市</label>
   		</div>
   		<div class="grid_5">
			<select name="population.district" id="district" class='form-txt {required:true,messages:{required:"请选择户籍地县"}}' 
			>
			</select>
  		</div>
   		<div class="grid_1">
   		  <label class="form-lbl">县</label>
   		</div>
   		<div class="clearLine">&nbsp;</div>
   		<div class="grid_4 lable-right">
   			<label class="form-lb1">户籍地详址：</label>
   		</div>
   		<div class="grid_18">
   			<input type="text" name="population.nativePlaceAddress" id="nativePlaceAddress" maxlength="50" value="${population.nativePlaceAddress }" class="form-txt" style="width: 99%"
			/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lb1">常住地址：</label>
		</div>
		<div class="grid_18">
			<input type="text" id="currentAddress" name="population.currentAddress"  maxlength="50"  value="${population.currentAddress }" style="width: 99%" class="form-txt {required:true,messages:{required:'请输入常住地址'}}"/>
		</div>
	
	
   		<div class='clearLine'>&nbsp;</div>
   		<div class='clearLine'>&nbsp;</div>
   		<div class="grid_4 lable-right">
   			<label class="form-lb1">备注：</label>
   		</div>
   		<div class="grid_18">
   			<textarea rows="4" name="population.remark" id="remark" style="width: 99%"
   				class='form-txt {maxlength:300,messages:{maxlength:"备注最多需要输入300个字符"}}'
   			>${population.remark }</textarea>
   		</div>
   		<input name="isSubmit" id="isSubmit" type="hidden" value="" />
	</form>
	<div id="populationDialogJsp"></div>
</div>

<script type="text/javascript">
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
	text=getBirthDayTextFromIdCard(text);
	resetBirthdayField(text);
	text=$('#idCardNo').val();
	getCommonPopulation(text);
}



function searchAndSynData(idCardNo, orgId) {
	$.ajax({
		url:"${path}/commonPopulation/commonPopulationManage/getActualPopulationByOrgIdAndIdCardNo.action",
		data:{
			"orgId":orgId,
			"idCardNo":idCardNo
        },
        success:function(data){
    
        	$("#actualPersonTypeDiv").hide();
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
		}
	})
}

$(document).ready(function(){
	if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){
		$("#headerImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
		$(".shadow").show();
	}
	
	threeSelect({province:'province',
		city:'city',
		district:'district',
		provinceValue:$('#provinceValue').val(),
		cityValue:$('#cityValue').val(),
		districtValue:$('#districtValue').val()
	});

	jQuery.validator.addMethod("currentAddress", function(value, element){
		if($("#currentAddressType").find("option:selected").attr("internalId")==<s:property value="@com.tianque.domain.property.CurrentAddressType@OTHER"/>){
			var livingHouse=$("#currentAddress").val();
			return (livingHouse==null||livingHouse==undefined||livingHouse=="")?false:true;
		}
	  	return true;
	});

	jQuery.validator.addMethod("community", function(value, element){
		if($("#currentAddressType").find("option:selected").attr("internalId")==<s:property value="@com.tianque.domain.property.CurrentAddressType@BUSINESS_HOUSE"/>) {
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
				"population.idCardNo":$('#idCardNo').val(),
				"organizationId":$('#populationOrganizationId').val(),
				"mode":$('#mode').val(),
				"population.id":$('#populationId').val()
	        },
			success:function(responseData){
				flag = !eval(responseData);
			}
		});
		return flag;
	});

	
	$("#maintainForm").formValidate({
		submitHandler: function(form) {
			$("select",$("#maintainForm")).removeAttr("disabled");
         	$(form).ajaxSubmit({
         		success: function(data){
					if(!data.id){
	               	 	$.messageBox({
							message:data,
							level: "error"
					 	});
                		return;
                	}
                	doAction("<s:property value='#parameters.dailogName[0]'/>",data.id,data);
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
		idCardChanged();
	});
	



});
</script>