<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%
if(ThreadVariable.getUser()!=null){
	request.setAttribute("NAME",ThreadVariable.getUser().getName());
}
%>


<div id="dialog-form" title="民生诉求表" class="container container_24"  style="overflow:hidden;">
	
	<form id="maintainForm" method="post" action="">
		<pop:token />
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<input type="hidden" id="organizationId" name="peopleAspirations.organization.id" value="${peopleAspirations.organization.id}"/>
		<input type="hidden" id="orgType" name="peopleAspirations.organization.orgType.id" value="${peopleAspirations.organization.orgType.id}"/>
		<input type="hidden" id="id" name="peopleAspirations.id" value="${peopleAspirations.id}"/>
		<input type="hidden" id="orgInternalCode" name="peopleAspirations.orgInternalCode" value="${peopleAspirations.orgInternalCode}"/>
		<input type="hidden" id="occurOrgId" name="peopleAspirations.occurOrg.id" value="${peopleAspirations.occurOrg.id}" />
		<input type="hidden" id="occurOrgInternalCode" name="peopleAspirations.occurOrgInternalCode" value="${peopleAspirations.occurOrgInternalCode}" />
		
	 	
 		<div class="grid_4 lable-right">
				<em class="form-req">*</em>
			<label class="form-lbl">编号：</label>
	 	</div>
		<div class="grid_7">
			<input type="text"  name="peopleAspirations.serialNumber" id="serialNumber"  maxlength="30" value="${peopleAspirations.serialNumber}" 
				readonly="readonly" class='form-txt' />
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">建表类型：</label>
	 	</div>
		<div class="grid_7">
			<select name="peopleAspirations.createTableType.id" id="createTableType" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@CREATE_TABLE_TYPE"  defaultValue="${peopleAspirations.createTableType.id}" />
			</select>
		</div>
 		<div class='clearLine'>&nbsp;</div>
 		<div class="grid_4 lable-right">
			<label class="form-lbl">网格号：</label>
	 	</div>
		<div class="grid_7">
			<input type="text"  name="peopleAspirations.gridNo" id="gridNo"  maxlength="20" value="${peopleAspirations.gridNo}" 
				class='form-txt' />
		</div>
 		<div class="grid_4 lable-right">
				<em class="form-req">*</em>
			<label class="form-lbl">发生网格：</label>
  		</div>
   		<div class="grid_7 form-left">
			<input type="text"   id="peopleOccurOrgSelector" name="selectOrgName"  value=""  class="form-txt" />
   		</div>
   		<div class='clearLine'></div>
 		<div class="grid_4 lable-right">
			<label class="form-lbl">登记单位：</label>
	 	</div>
		<div <s:if test='!"view".equals(mode)'>class="grid_7"</s:if><s:else>class="grid_4"</s:else>>
			<input type="text"  id="peopleAspirationsOrgName"  name="peopleAspirations.bookingUnit"  readonly  value="${peopleAspirations.bookingUnit}"
				class='form-txt' />
		</div>
		<div class="grid_4 lable-right">
			<s:if test='!"view".equals(mode)'>
				<em class="form-req">*</em>
			</s:if>
			<label class="form-lbl">登记人：</label>
	 	</div>
		<div class="grid_7">
			<input type="text"  name="peopleAspirations.registrant" id="registrant"  maxlength="20" value="${peopleAspirations.registrant}" 
				class='form-txt' />
		</div>
		
		<s:if test='"view".equals(mode)'>
			<div class="grid_3 lable-right">
				<label class="form-lbl">登记时间：</label>
		 	</div>
			<div class="grid_5">
				<input type="text"  name="peopleAspirations.registrationTime" id="registrationTime"  maxlength="20"   value='<s:date name="peopleAspirations.registrationTime" format="yyyy-MM-dd HH:mm:ss" />' 
					class='form-txt' />
			</div>
		</s:if>
		
		<div class='clearLine'>&nbsp;</div>
 	
		 <div class="grid_4 lable-right">
		 	<em class="form-req">*</em>
			<label class="form-lbl">姓名：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="peopleAspirations.name" id="name"  maxlength="20"  value="${peopleAspirations.name}"
				class="form-txt {validatorNativePlaceAddress:true, messages:{validatorNativePlaceAddress:'姓名不能输入特殊字符'}}"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
			
	 	<div class="grid_4 lable-right">
	 		<em class="form-req">*</em>
			<label class="form-lbl">身份证：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="peopleAspirations.idCardNo" id="idCardNo"  maxlength="18"  value="${peopleAspirations.idCardNo}"
					class="form-txt {required:true,idCard:true,messages:{required:'请输入身份证',idCard:'请输入一个合法的身份证号码'}}"/>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">出生年月：</label>
	 	</div>
		<div class="grid_7" id="birthdayDiv">
			<input type="text" name="peopleAspirations.birthDay" id="birthDay"  maxlength="32" readonly  value='<s:date name="peopleAspirations.birthDay" format="yyyy-MM-dd" />' class="form-txt" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">性别：</label>
	 	</div>
		<div class="grid_7">
			<select name="peopleAspirations.gender.id" id="gender" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="${peopleAspirations.gender.id}" />
			</select>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">联系手机：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="peopleAspirations.mobileNumber" id="mobileNumber"  maxlength="11"  value="${peopleAspirations.mobileNumber}" title="请输入11位以1开头的联系手机  例如：13988888888" 
				class='form-txt {mobile:true,messages:{mobile:"手机号码输入只能是以1开头的11位数字"}}' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lbl">常住地址：</label>
	 	</div>
		<div class="grid_18">
			<input type="text" name="peopleAspirations.permanentAddress" id="permanentAddress"  maxlength="50"  value="${peopleAspirations.permanentAddress}"
				class="form-txt {validatorNativePlaceAddress:true, messages:{validatorNativePlaceAddress:'常住地址不能输入特殊字符'}}"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">是否党员：</label>
	 	</div>
		<div class="grid_7">
			<select id="isPartyMember" name="peopleAspirations.isPartyMember" class="form-txt" <s:if test='"view".equals(mode)'>disabled</s:if>>
	 				<option value="true" <s:if test="peopleAspirations.isPartyMember">selected</s:if>>是</option>
	 				<option value="false" <s:if test="!peopleAspirations.isPartyMember">selected</s:if>>否</option>
	 		</select>
		</div>
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">职业或身份：</label>
	 	</div>
		<div class="grid_7">
			<select name="peopleAspirations.position.id" id="position" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@POSITION_OR_STATUS" defaultValue="${peopleAspirations.position.id}" />
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lbl">反应群体人数：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="peopleAspirations.responseGroupNo" id="responseGroupNo"  maxlength="6"  value="${peopleAspirations.responseGroupNo}"
				class='form-txt'/>
		</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lbl">诉求人类别：</label>
	 	</div>
		<div class="grid_7 ">
			<select name="peopleAspirations.appealHumanType.id" id="appealHumanType" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@APPEAL_HUMAN_TYPE" defaultValue="${peopleAspirations.appealHumanType.id}" />
			</select>
		</div>
	 	
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
				<em class="form-req">*</em>
			<label class="form-lbl">主要愿望或诉求：</label>
	 	</div>
		<div class="grid_18 heightAuto">
			<textarea  id="appealContent" name="peopleAspirations.appealContent"  maxlength="50" class='form-txt' >${peopleAspirations.appealContent}</textarea>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
				<em class="form-req">*</em>
			<label class="form-lbl">项目类别：</label>
	 	</div>
		<div class="grid_7">
			  <select name="peopleAspirations.itemCategory.id" id="itemCategory" class="form-txt" <s:if test='"view".equals(mode)'>disabled</s:if>>
		   		<pop:PropertyDictReleationSelectTag name="@com.tianque.domain.property.PropertyTypes@ITEM_CATEGORY" defaultValue="${peopleAspirations.itemCategory.id}" 
		   			reletionId="itemCategorySub" reletionName="@com.tianque.domain.property.PropertyTypes@ITEM_CATEGORY_SUB" id="itemCategory"/>
			</select>
		</div>
		<div class="grid_7">
			  <select name="peopleAspirations.itemCategorySub.id" id="itemCategorySub" class="form-txt" <s:if test='"view".equals(mode)'>disabled</s:if>>
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ITEM_CATEGORY_SUB" defaultValue="${peopleAspirations.itemCategorySub.id}" />
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lbl">服务联系人：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="peopleAspirations.serverContractor" id="serverContractor"  maxlength="20" class="form-txt" value="${peopleAspirations.serverContractor}"/>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">服务联系电话：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="peopleAspirations.serverTelephone" id="serverTelephone"  maxlength="13"  value="${peopleAspirations.serverTelephone}"
				class='form-txt {telephone:true,messages:{telephone:$.format("固定电话不合法，只能输数字和横杠(-)")}}' title="请输入由数字和-组成的联系电话,例如：0577-88888888" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lbl">服务职务：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="peopleAspirations.serverJob" id="serverJob"  maxlength="20" class="form-txt" value="${peopleAspirations.serverJob}"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
 		<div class="grid_4 lable-right">
			<label class="form-lbl">服务联系人单位：</label>
	 	</div>
		<div class="grid_18">
			<input type="text" name="peopleAspirations.serverUnit" id="serverUnit"  maxlength="50" class="form-txt" value="${peopleAspirations.serverUnit}"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
 		
	</form>
	
</div>
<script type="text/javascript">
var peopleTree;

$(document).ready(function(){
	
	// 初始发生化网格 
	initOccurOrgSelector();
	
	$("#idCardNo").blur(function(){
    	idCardChanged();
	});
	
	jQuery.validator.addMethod("validatorNativePlaceAddress", validatorSpecialWord);
	function validatorSpecialWord(value,element){
		if(value==null||value==undefined||value==""){return true}
		var pattern = new RegExp("[`~!@%#$^&*()=|{}':;',　\\[\\]<>/? \\.；：%……+￥（）【】‘”“'。，、？ ！]");
		return this.optional(element)||!pattern.test(value) ; 
	}
	
	var idCardNoData;
	jQuery.validator.addMethod("exsistedIdCard", function(value, element){
		var value=$('#idCardNo').val();
		if(value==null||value==undefined||value==""){return true}
		$.ajax({
			async: false ,
			url:"${path}/account/peopleAspirationManage/exsistedIdCard.action",
		   	data:{
				"peopleAspirations.idCardNo":$('#idCardNo').val(),
				"peopleAspirations.organization.id":$('#organizationId').val(),
				"peopleAspirations.id":$('#id').val()
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
		var value=$('#serialNumber').val();
		if(value==null||value==undefined||value==""){return true}
		$.ajax({
			async: false ,
			url:"${path}/account/peopleAspirationManage/hasDuplicateSerialNumber.action",
		   	data:{
				"peopleAspirations.serialNumber":$('#serialNumber').val(),
				"peopleAspirations.organization.id":$('#organizationId').val(),
				"peopleAspirations.id":$('#id').val()
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
	
	function idCardChanged(){
		var text=$('#idCardNo').val();
		text=getBirthDayTextFromIdCard(text);
		resetBirthdayField(text);
		text=$('#idCardNo').val();
	}
	
	resetBirthdayField($("#birthDay").val());
	
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
	        $(form).ajaxSubmit({
				success: function(data){
		        	if(!data.id){
						$.messageBox({
							message:data,
							level: "error"
						});
						return;
					}
		        	if(data.organization){
						data["organization.orgName"]=data.organization.orgName;
					}
					<s:if test='"edit".equals(mode)'>
// 						$("#peopleAspirationsList").setRowData(data.id,data);
				    	$.messageBox({message:"民生诉求表修改成功!"});
				    	$("#peopleaspirationsDialog").dialog("close");
				    	//onOrgChanged(userOrgId,isGrid());
				    	$("#reload").click();
					</s:if>
					<s:if test='"add".equals(mode)'>
// 						$("#peopleAspirationsList").addRowData(data.id,data,"first");
						$.messageBox({message:"民生诉求表新增成功!"});
						$("#peopleaspirationsDialog").dialog("close");
						onOrgChanged(userOrgId,isGrid());
					</s:if>
	      	   	},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	    	alert("提交数据时发生错误");
	      	   	}
	      	});
		},
		rules:{
			"peopleAspirations.serialNumber":{
				required:true,
				exsistedSerialNumber:true
			},
			"peopleAspirations.name":{
				required:true,
				exculdeParticalChar:true,
				minlength:2,
				maxlength:20
			},
			"peopleAspirations.createTableType.id":{
				required:true
			},
			"peopleAspirations.responseGroupNo":{
				digits:true,
				range:[1,999999]
			},
			"peopleAspirations.idCardNo":{
				idCard:true
			},
			"peopleAspirations.itemCategory.id":{
				required:true
			},
			"peopleAspirations.serverContractor":{
				exculdeParticalChar:true,
				minlength:2,
				maxlength:20
			},
			"peopleAspirations.serverJob":{
				exculdeParticalChar:true,
				minlength:2,
				maxlength:20
			},
			"peopleAspirations.serverUnit":{
				exculdeParticalChar:true,
				minlength:2,
				maxlength:20
			},
			"peopleAspirations.appealContent":{
				required:true,
				minlength:2,
				maxlength:50
			},
			"selectOrgName":{
				required:true,
				orgLevelLessEqual:function(){
						return [getOccurOrgId(),<s:property value="@com.tianque.domain.property.OrganizationLevel@GRID"/>];
					}
			},
			"peopleAspirations.registrant":{
				required:true,
				exculdeParticalChar:true,
				minlength:2,
				maxlength:20
			}
			
		},
		messages:{
			"peopleAspirations.serialNumber":{
				required:"请输入编号",
				exsistedSerialNumber:function(){
					return serialNumberData;
				}
			},
			"peopleAspirations.name":{
				required:"请输入姓名",
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("姓名至少需要输入{0}个字符"),
				maxlength:$.format("姓名最多需要输入{0}个字符")
			},
			"peopleAspirations.createTableType.id":{
				required:"请选择建表类型"
			},
			"peopleAspirations.responseGroupNo":{
				digits:"反应群体人数只能输入1到999999之间的整数",
				range:$.format("反应群体人数只能输入{0}到{1}之间的整数")
			},
			"peopleAspirations.idCardNo":{
				idCard:'请输入一个合法的身份证号码'
			},
			"peopleAspirations.itemCategory.id":{
				required:"请选择项目类别"
			},
			"peopleAspirations.serverContractor":{
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("服务联系人至少需要输入{0}个字符"),
				maxlength:$.format("服务联系人最多需要输入{0}个字符")
			},
			"peopleAspirations.serverJob":{
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("服务职务至少需要输入{0}个字符"),
				maxlength:$.format("服务职务最多需要输入{0}个字符")
			},
			"peopleAspirations.serverUnit":{
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("服务联系人单位至少需要输入{0}个字符"),
				maxlength:$.format("服务联系人单位最多需要输入{0}个字符")
			},
			"peopleAspirations.appealContent":{
				required:"请输入主要愿望或诉求",
				minlength:$.format("诉求至少需要输入{0}个字符"),
				maxlength:$.format("诉求最多需要输入{0}个字符")
			},
			"selectOrgName":{
				required:"请选择发生网格",
				orgLevelLessEqual:"发生网格只能选择网格级别"
			},
			"peopleAspirations.registrant":{
				required:"请输入登记人姓名",
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("登记人姓名至少需要输入{0}个字符"),
				maxlength:$.format("登记人姓名最多需要输入{0}个字符")
			}
			
		},
		ignore:':hidden'
	});
<s:if test='"add".equals(mode)'>
    $("#maintainForm").attr("action","${path}/account/peopleAspirationManage/addPeopleAspiration.action");
</s:if>
<s:if test='"edit".equals(mode)'>
    $("#maintainForm").attr("action","${path}/account/peopleAspirationManage/updatePeopleAspiration.action");
</s:if>
<s:if test='"add".equals(mode)'>
$("#registrant").val('${NAME}');
$("#createTableType option").eq(1).attr("selected", true);
	$.ajax({
		async: false,
		url: "${path}/sysadmin/orgManage/getOrgRelativePath.action",
		data:{
			"organization.id":$("#organizationId").val()
		},
		success:function(responseData){
			$("#peopleAspirationsOrgName").val(responseData);
			
		}
	});
</s:if>
});

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
		$("#birthdayDiv").html("<input type='text' name='peopleAspirations.birthDay' id='birthDay' class='form-txt' value='"+text+"' readonly/>");
	}else{
		$('#birthDay').datePicker({
			yearRange: '1900:2030',
    		dateFormat: 'yy-mm-dd',
            maxDate:'+0d'});
	}
}

function isNull(){
	if($("#peopleOccurOrgSelector").val()==null||$("#peopleOccurOrgSelector").val()==""){
		$.notice({
			title:"提示",
			message:"请先选择所属网格！",
			width: 300
		});
		return false;
		}
	return true;
}

function initOccurOrgSelector(){
		var tree=$("#peopleOccurOrgSelector").treeSelect({
			inputName:"peopleAspirations.occurOrg.id",
			loadCom:function(){
				if(peopleEditing()){
					$.setTreeValue(getDefaultOccurOrg(),tree); 
				}
			}
		});
		peopleTree=tree;
}

function peopleEditing(){
	return <s:property value='"edit".equals(mode)'/>;
}

function getDefaultOccurOrg(){
	<s:if test="null!=peopleAspirations.occurOrg && null!=peopleAspirations.occurOrg.id">
		return "${peopleAspirations.occurOrg.id}";
	</s:if>
	<s:else>
		return -1;
	</s:else>
}

function  getOccurOrgId(){
	return $("#occurOrgId").val();
} 

</script>


