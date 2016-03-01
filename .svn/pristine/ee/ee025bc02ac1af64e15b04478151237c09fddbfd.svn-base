<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form" title="困难群众台账维护" class="container container_24" style="overflow:hidden;">
	<form id="maintainForm" method="post" action="<s:if test='"edit".equals(mode)'>/account/poorPeopleManage/updatePoorPeople.action</s:if><s:elseif test='"add".equals(mode)'>/account/poorPeopleManage/addPoorPeople.action</s:elseif>" >
	<pop:token/>
	<input type="hidden" id="organizationId" name="poorPeople.organization.id" value="${poorPeople.organization.id}"/>
	<input type="hidden" id="occurOrgId" name="poorPeople.occurOrg.id" value="${poorPeople.occurOrg.id}" />
	<input type="hidden" name="poorPeople.id" id="id" value="${poorPeople.id}" />
	<div  class="grid_4 lable-right" >
		<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
		<label class="form-lbl">编号： </label>
	</div>
	<div class="grid_7" id="userDiv">
   		<input type="text" name="poorPeople.serialNumber" value="${poorPeople.serialNumber}" readonly="readonly" maxlength="15" class="form-txt {required:true,exsistedSerialNumber: true,messages:{required:'编号必须输入', exsistedSerialNumber: function (){return serialNumberData;}}}" />
	</div>
	<div class="grid_4 lable-right">
		<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
		<label class="form-lbl">建表类型：</label>
	</div>
	<div class="grid_7">
	   <select name="poorPeople.createTableType.id" class="form-txt" id="createTableType">
	   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@CREATE_TABLE_TYPE"  defaultValue="${poorPeople.createTableType.id}" />
	   </select>
	</div>
	<div class='clearLine'>&nbsp;</div>
    <div class="grid_4 lable-right"><label class="form-lbl">网格号：</label></div>
	<div class="grid_7 heightAuto">
   		<input type="text" name="poorPeople.gridNo" value="${poorPeople.gridNo }" maxlength="20" class="form-txt" />
    </div>
    <div class="grid_4 lable-right"><label class="form-lbl">发生网格：</label></div>
	<div class="grid_7">
		<input type="text" id="peopleOccurOrgSelector" class="form-txt {orgLevelLessEqual:function(){return [getOccurOrgId(),<s:property value="@com.tianque.domain.property.OrganizationLevel@GRID"/>];},messages:{orgLevelLessEqual:'发生网格只能选择网格级别'}}" />
    </div>
	<div class='clearLine'></div>
	<div class="grid_4 lable-right">
   		<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
	    <label class="form-lbl">登记人：</label>
	</div>
	<div class="grid_7">
   		<input type="text" name="poorPeople.registrant" value="${poorPeople.registrant }" maxlength="16" class="form-txt {required:true,messages:{required:'登记人必须输入'}}" />
	</div>
    <div class="grid_4 lable-right">
   		<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if><label class="form-lbl">姓名：</label></div>
    <div class="grid_7">
   		<input type="text" name="poorPeople.name" value="${poorPeople.name }" maxlength="20" class="form-txt {required:true,exculdeParticalChar:true,maxlength:20,minlength:2,validatorNativePlaceAddress:true,messages:{required:'姓名必须输入',exculdeParticalChar:'不能输入非法字符',minlength:$.format('姓名至少需要输入{0}个字符'),maxlength:$.format('姓名最多需要输入{0}个字符'),validatorNativePlaceAddress:'姓名不能输入特殊字符'}}" />
    </div>
	<div class='clearLine'></div>
	<div class="grid_4 lable-right">
   		<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
	    <label class="form-lbl">身份证号：</label>
	</div>
	<div class="grid_7"><!-- ,exsistedIdCard:false -->
   		<input type="text" name="poorPeople.idCardNo" id="idCardNo" value="${poorPeople.idCardNo }" maxlength="18" class="form-txt {required:true,idCard:true,messages:{required:'身份证号必须输入',idCard:$.format('请输入一个合法的身份证号码'),exsistedIdCard:function(){return idCardNoData;}}}" />
	</div>
    <div class="grid_4 lable-right"><label class="form-lbl">联系手机：</label></div>
    <div class="grid_7">
   		<input type="text" name="poorPeople.mobileNumber" value="${poorPeople.mobileNumber }" maxlength="11" class="form-txt {mobile:true,messages:{mobile:'手机号码输入只能是以1开头的11位数字'}}" />
    </div>
	<div class='clearLine'></div>
	<div class="grid_4 lable-right">
	    <label class="form-lbl">性别：</label>
	</div>
	<div class="grid_7">
   		<select name="poorPeople.gender.id" class="form-txt">
	   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="${poorPeople.gender.id}"/></select>
	</div>
	<div class="grid_4 lable-right">
	    <label class="form-lbl">出生年月：</label>
	</div>
	<div class="grid_7" id="birthdayDiv">
   		<input type="text" name="poorPeople.birthDay" id="birthDay" value="<s:date name="poorPeople.birthDay" format="yyyy-MM-dd"/>" readonly="readonly" class="form-txt" />
	</div>
	<div class='clearLine'></div>
    <div class="grid_4 lable-right"><label class="form-lbl">常住地址：</label></div>
    <div class="grid_18">
   		<input type="text" name="poorPeople.permanentAddress" value="${poorPeople.permanentAddress }" maxlength="50" class="form-txt {validatorNativePlaceAddress:true, messages:{validatorNativePlaceAddress:'常住地址不能输入特殊字符'}}" style="width: 99%;" />
    </div>
	<div class='clearLine'></div>
    <div class="grid_4 lable-right"><label class="form-lbl">是否党员：</label></div>
    <div class="grid_7">
   		<select name="poorPeople.isPartyMember" class="form-txt">
   			<option value="false" <s:if test="false == poorPeople.isPartyMember">selected="selected"</s:if>>否</option>
   			<option value="true" <s:if test="true == poorPeople.isPartyMember">selected="selected"</s:if>>是</option>
   		</select>
    </div>
	<div class="grid_4 lable-right">
	    <label class="form-lbl">职业或身份：</label>
	</div>
	<div class="grid_7">
   		<select name="poorPeople.position.id" class="form-txt">
	   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@POSITIONORIDENTITY" defaultValue="${poorPeople.position.id}"/></select>
	</div>
	<div class='clearLine'></div>
	<div class="grid_4 lable-right">
	    <label class="form-lbl">学历：</label>
	</div>
	<div class="grid_7">
   		<select name="poorPeople.schooling.id" class="form-txt">
	   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SCHOOLING" defaultValue="${poorPeople.schooling.id}"/></select>
	</div>
    <div class="grid_4 lable-right"><label class="form-lbl">纳入保险：</label></div>
    <div class="grid_7">
   		<select name="poorPeople.insuranceType.id" class="form-txt">
	   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@INSURANCETYPE" defaultValue="${poorPeople.insuranceType.id}"/></select>
    </div>
	<div class='clearLine'></div>
	<div class="grid_4 lable-right">
	    <label class="form-lbl">家庭人口：</label>
	</div>
	<div class="grid_7">
   		<input type="text" name="poorPeople.memberNum" value="${poorPeople.memberNum }" maxlength="2" class="form-txt" />
	</div>
	<div class="grid_4 lable-right">
	    <label class="form-lbl">上年度人均年收入：</label>
	</div>
	<div class="grid_7">
   		<input type="text" name="poorPeople.lastYearMemberIncome" value="${poorPeople.lastYearMemberIncome }" maxlength="8" class="form-txt" />
	</div>
	<div class='clearLine'></div>
    <div class="grid_4 lable-right">
   		<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if><label class="form-lbl">困难原因：</label></div>
    <div class="grid_7">
   		<select name="poorPeople.poorBigInfo.id" id="_poorBigInfo" class="form-txt {required:true,messages:{required:'困难原因(大类)必须选择'}}">
   			<pop:PropertyDictReleationSelectTag name="@com.tianque.domain.property.PropertyTypes@POORBIGINFO" defaultValue="${poorPeople.poorBigInfo.id}" 
		   			reletionId="_poorInfo" reletionName="@com.tianque.domain.property.PropertyTypes@POORINFO" id="_poorBigInfo"/></select>
    </div>
    <div class="grid_1">
    </div>
    <div class="grid_7">
   		<select name="poorPeople.poorInfo.id" id="_poorInfo" class="form-txt {required:true,messages:{required:'困难原因(子类)必须选择'}}">
	   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@POORINFO" defaultValue="${poorPeople.poorInfo.id}"/></select>
    </div>
	<div class='clearLine'></div>
    <div class="grid_4 lable-right">
   		<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if><label class="form-lbl">帮扶需求：</label></div>
    <div class="grid_18">
   		<textarea name="poorPeople.helpInfo" maxlength="50" class="form-txt {required:true,maxlength:50,messages:{required:'帮扶需求必须输入',maxlength:$.format('帮扶需求最多需要输入{0}个字符')}}" style="width: 99%;">${poorPeople.helpInfo }</textarea>
    </div>
	<div class='clearLine' style="height: 25px;"></div>
    <div class="grid_4 lable-right"><label class="form-lbl">年度帮扶项目：</label></div>
    <div class="grid_18">
   		<textarea name="poorPeople.yearHelpInfo" maxlength="200" class="form-txt" style="width: 99%;">${poorPeople.yearHelpInfo}</textarea>
    </div>
	<div class='clearLine' style="height: 25px;"></div>
    <div class="grid_4 lable-right"><label class="form-lbl">服务联系人：</label></div>
    <div class="grid_7">
   		<input type="text" name="poorPeople.serverContractor" value="${poorPeople.serverContractor }" maxlength="20" class="form-txt" />
    </div>
	<div class="grid_4 lable-right">
	    <label class="form-lbl">联系电话：</label>
	</div>
	<div class="grid_7">
   		<input type="text" name="poorPeople.serverTelephone" id="serverTelephone" maxlength="13"  value="${poorPeople.serverTelephone }"
   			class='form-txt {telephone:true,messages:{telephone:$.format("固定电话不合法，只能输数字和横杠(-)")}}' title="请输入由数字和-组成的联系电话,例如：0577-88888888"/>
	</div>
	<div class='clearLine'></div>
	<div class="grid_4 lable-right">
	    <label class="form-lbl">职务：</label>
	</div>
	<div class="grid_7">
   		<input type="text" name="poorPeople.serverJob" value="${poorPeople.serverJob }" maxlength="20" class="form-txt" />
	</div>
    <div class="grid_4 lable-right"><label class="form-lbl">服务联系人单位：</label></div>
    <div class="grid_7">
   		<input type="text" name="poorPeople.serverUnit" value="${poorPeople.serverUnit }" maxlength="50" class="form-txt" />
    </div>
	</form>
</div>
<script type="text/javascript">
function initOccurOrgSelector(){
	var tree=$("#peopleOccurOrgSelector").treeSelect({
		inputName:"poorPeople.occurOrg.id",
		loadCom:function(){
			if(<s:property value='!"add".equals(mode)'/>){
				$.setTreeValue(getDefaultOccurOrg(),tree); 
			}
		}
	});
}

function getDefaultOccurOrg(){
	<s:if test="null!=poorPeople.occurOrg && null!=poorPeople.occurOrg.id">
		return "${poorPeople.occurOrg.id}";
	</s:if>
	<s:else>
		return -1;
	</s:else>
}

jQuery.validator.addMethod("validatorNativePlaceAddress", function (value,element){
	if(value==null||value==undefined||value==""){return true}
	var pattern = new RegExp("[`~!@%#$^&*()=|{}':;',　\\[\\]<>/? \\.；：%……+￥（）【】‘”“'。，、？ ！]");
	return this.optional(element)||!pattern.test(value) ; 
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
		url:"${path}/account/poorPeopleManage/exsistedIdCard.action",
	   	data:{
			"poorPeople.idCardNo": $('#idCardNo').val(),
			"poorPeople.id": $('#id').val()
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

var serialNumberData;
jQuery.validator.addMethod("exsistedSerialNumber", function(value, element){
	if(value==null||value==undefined||value==""){return true}
	$.ajax({
		async: false ,
		url:"${path}/account/poorPeopleManage/hasDuplicateSerialNumber.action",
	   	data:{
			"poorPeople.serialNumber": value,
			"poorPeople.id":$('#id').val()
        },
		success:function(responseData){
			serialNumberData = responseData;
		}
	});
	if(!(serialNumberData==null||serialNumberData=="")){
		return false;
	}
	return true;
});

$(document).ready(function(){

	initOccurOrgSelector();
	
	$("#idCardNo").blur(function(){
    	idCardChanged();
	});
	
	resetBirthdayField($("#birthDay").val());
	
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
			$(form).ajaxSubmit({
	             success: function(data){
                     if(data==null || !data.id){
                    	 $.messageBox({ message:data, level: "error" });
                     	return;
                     }
        	   		 <s:if test='"add".equals(mode) || "copy".equals(mode) '>
				    	//$("#poorPeopleList").addRowData(data.id, data, "first");
				    	$.messageBox({message:"新增成功!"});
				    	if(isNeedMaintainMember){
				    		maintainMember(data.id);
				    		isNeedMaintainMember = false;
				    	}
				     </s:if>
				     <s:if test='"edit".equals(mode)'>
				        //$("#poorPeopleList").setRowData(data.id, data);
				    	$.messageBox({message:"修改成功!"});
				     </s:if>
					$("#poorPeopleList").trigger("reloadGrid");
				     $("#poorPeopleDialog").dialog("close");
				     $("#poorPeopleList").setSelection(data.id);
	      	   },
	      	   error: function(XMLHttpRequest, textStatus, errorThrown){
	      	      alert("提交错误");
	      	   }
			});
		},
		rules:{
			"poorPeople.registrant":{
				required:true,
				exculdeParticalChar:true,
				minlength:2,
				maxlength:20
			},
			"poorPeople.createTableType.id":{
				required:true
			},
			"poorPeople.memberNum":{
				digits:true,
				range:[1,99]
			},
			"poorPeople.lastYearMemberIncome":{
				number:true,
				validatorLastYearMemberIncome:true
			},
			"poorPeople.serverContractor":{
				exculdeParticalChar:true,
				minlength:2,
				maxlength:20
			},
			"poorPeople.serverJob":{
				exculdeParticalChar:true,
				minlength:2,
				maxlength:20
			},
			"poorPeople.serverUnit":{
				exculdeParticalChar:true,
				minlength:2,
				maxlength:20
			}
		},
		messages:{
			"poorPeople.registrant":{
				required:"请输入登记人姓名",
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("登记人姓名至少需要输入{0}个字符"),
				maxlength:$.format("登记人姓名最多需要输入{0}个字符")
			},
			"poorPeople.createTableType.id":{
				required:"请选择建表类型"
			},
			"poorPeople.memberNum":{
				digits:'家庭人口只能输入1到99之间的整数',
				range:$.format('家庭人口只能输入{0}到{1}之间的整数')
			},
			"poorPeople.lastYearMemberIncome":{
				number:"上年度人均年收入只能输入1到999999之间的数",
				validatorLastYearMemberIncome:"上年度人均年收入只能输入1到999999之间的数,小数保留2位有效数"
			},
			"poorPeople.serverContractor":{
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("服务联系人至少需要输入{0}个字符"),
				maxlength:$.format("服务联系人最多需要输入{0}个字符")
			},
			"poorPeople.serverJob":{
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("职务至少需要输入{0}个字符"),
				maxlength:$.format("职务最多需要输入{0}个字符")
			},
			"poorPeople.serverUnit":{
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("服务联系人单位至少需要输入{0}个字符"),
				maxlength:$.format("服务联系人单位最多需要输入{0}个字符")
			}
		},
		ignore:':hidden'
	});
	<s:if test='"add".equals(mode)'>
		$("#createTableType option").eq(1).attr("selected", true);
	</s:if>
});

function idCardChanged(){
	var text=$('#idCardNo').val();
	text=getBirthDayTextFromIdCard(text);
	resetBirthdayField(text);
	text=$('#idCardNo').val();
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

function resetBirthdayField(text){
	if (text!="" && $('#idCardNo').val()!=""){
		$("#birthdayDiv").html("<input type='text' name='poorPeople.birthDay' id='birthDay' class='form-txt' value='"+text+"' readonly/>");
	}else{
		$('#birthDay').datePicker({
			yearRange: '1900:2030',
    		dateFormat: 'yy-mm-dd',
            maxDate:'+0d'});
	}
}

function  getOccurOrgId(){
	return $("#occurOrgId").val();
} 
</script>