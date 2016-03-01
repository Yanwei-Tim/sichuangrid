<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="优抚对象" class="container container_24">
	<div id="specialCareGroups">
	<form id="maintainForm" method="post" action="" >
		<pop:token />
		<input name="mode" id="mode" value="${mode}" type="hidden"> 
		<input name="isSubmit" id="isSubmit" type="hidden" value="">
		<input id="specialCareGroupsId" type="hidden" name="specialCareGroups.id" value="${specialCareGroups.id}" /> 
		<input id="orgId" name="specialCareGroups.organization.id" value="${orgId}" type="hidden">
	
		<div class="grid_3 lable-right"><label class="form-lbl">所属网格：</label></div>
		<div class="grid_13"><input id="specialCareGroupsOrgName" type="text" name="specialCareGroups.organization.orgName" readonly
			<s:if test='"view".equals(mode)'>disabled='true'</s:if> value="" style="width: 99%" class="form-txt" />
		</div>
		<div class="grid_1"><s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if></div>
		<div class='clearLine'>&nbsp;</div>
			
		<div  class="grid_3 lable-right"><label class="form-lbl">姓名：</label></div>
		<div class="grid_4"><input type="text" name="specialCareGroups.name" id="name" maxlength="18"
			<s:if test='"view".equals(mode)'>disabled='true'</s:if>
			value="${specialCareGroups.name}" class="form-txt" />
		</div>
		<div class="grid_1"><s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if></div>
	
		<div class="grid_3 lable-right"><label class="form-lbl">身份证号码：</label></div>
		<div class="grid_5"><input type="text" class="form-txt  dialogtip" name="specialCareGroups.idCardNo" id="idCardNo" maxlength="18"
			<s:if test='"view".equals(mode)'>disabled='true'</s:if>
			value="${specialCareGroups.idCardNo}" />
		</div>
		<div class="grid_1"><s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if></div>

		<div class="grid_2 lable-right"><label class="form-lbl">性别：</label></div>
		<div class="grid_4">
			<select name="specialCareGroups.gender.id" id="gender" class="form-txt"
				<s:if test='"view".equals(mode)'>disabled='true'</s:if>>
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="${specialCareGroups.gender.id}" />
			</select>
		</div>		
		<div class="grid_1"><s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if></div>

		<div class='clearLine'>&nbsp;</div>
   		<hr width="90%" />	
		<div class='clearLine'>&nbsp;</div>
								
		<div class="grid_3 lable-right"><label class="form-lbl">出生日期：</label></div>
		<div class="grid_4" id="birthdayDiv">
		<input type="text" name="specialCareGroups.birthday" id="birthday" class="form-txt" readonly
			<s:if test='"view".equals(mode)'>disabled='true'</s:if>
			value='<s:date name="specialCareGroups.birthday" format="yyyy-MM-dd" />' />
		</div>
		<div class="grid_1"></div>
			
		<div class="grid_3 lable-right"><label class="form-lbl">联系电话：</label></div>
		<div class="grid_5"><input id="telephone" type="text" name="specialCareGroups.telephone" class="form-txt  dialogtip"
			<s:if test='"view".equals(mode)'>disabled='true'</s:if>
			title="0577-88888888" value='${specialCareGroups.telephone}' maxlength="15"/>
		</div>
		
		<div class="grid_3 lable-right"><label class="form-lbl">联系手机：</label></div>
		<div class="grid_4"><input id="mobileNumber" type="text" name="specialCareGroups.mobileNumber" class="form-txt  dialogtip"
			title="请输入11位以1开头的联系手机  例如：13988888888"
			<s:if test='"view".equals(mode)'>disabled='true'</s:if>
			value='${specialCareGroups.mobileNumber}' maxlength="11" />
		</div>
		<div class="grid_1"></div>
		<div class='clearLine'>&nbsp;</div>
	
		<div class="grid_3 lable-right"><label class="form-lbl">优抚证号：</label></div>
		<div class="grid_4"><input type="text" class="form-txt  dialogtip" name="specialCareGroups.specialCareNo" id="specialCareNo" maxlength="25"
			<s:if test='"view".equals(mode)'>disabled='true'</s:if> 
			value="${specialCareGroups.specialCareNo}" />
		</div>
		<div class="grid_1"><s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if></div>
	
		<div class="grid_3 lable-right"><label class="form-lbl">优抚类型：</label></div>
		<div class="grid_5">
		<select name="specialCareGroups.specialCareType.id" id="specialCareType"
			<s:if test='"view".equals(mode)'>disabled='true'</s:if>
			class="form-txt">
			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SPECIAL_CARE_TYPE" defaultValue="${specialCareGroups.specialCareType.id}" />
		</select>
		</div>
	
		<div class="grid_3 lable-right"><label class="form-lbl">劳动能力：</label></div>
		<div class="grid_4">
		<select name="specialCareGroups.labourCapacity.id" id="labourCapacity" class="form-txt" 
			<s:if test='"view".equals(mode)'>disabled='true'</s:if>>
			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@LABOUR_CAPACITY" defaultValue="${specialCareGroups.labourCapacity.id}" />
		</select>
		</div>
		<div class="grid_1"></div>
		
		<div class="grid_3 lable-right"><label class="form-lbl">生活能力：</label></div>
		<div class="grid_4"><select name="specialCareGroups.viability.id" id="viability" class="form-txt" 
			<s:if test='"view".equals(mode)'>disabled='true'</s:if>>
			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@VIABILITY" defaultValue="${specialCareGroups.viability.id}" />
		</select>
		</div>
		<div class="grid_1"></div>
		
		<div class="grid_3 lable-right"><label class="form-lbl">就业情况：</label></div>
		<div class="grid_5"><select name="specialCareGroups.employmentStatus.id" id="employmentStatus" class="form-txt" 
			<s:if test='"view".equals(mode)'>disabled='true'</s:if>>
			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@EMPLOYMENT_STATUS" defaultValue="${specialCareGroups.employmentStatus.id}" />
		</select>
		</div>
		
		<div class="grid_3 lable-right"><label class="form-lbl">供养情况：</label></div>
		<div class="grid_4"><select name="specialCareGroups.supportStatus.id" id="supportStatus" class="form-txt" 
			<s:if test='"view".equals(mode)'>disabled='true'</s:if>>
			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SUPPORT_STATUS" defaultValue="${specialCareGroups.supportStatus.id}" />
		</select>
		</div>
		<div class="grid_1"></div>
		<div class='clearLine'>&nbsp;</div>

		<div class="grid_3 lable-right"><label class="form-lbl">常住地址：</label></div>
		<div class="grid_13"><input id="currentAddress" type="text" name="specialCareGroups.currentAddress " class="form-txt"
			<s:if test='"view".equals(mode)'>disabled='true'</s:if>
			value='${specialCareGroups.currentAddress}' maxlength="50" />
		</div>		
		
		<div class="grid_3 lable-right"><label class="form-lbl">月生活费：</label></div>
		<div class="grid_4">
		<input id="monthsExpenses" type="text" name="specialCareGroups.monthsExpenses" class="form-txt  dialogtip" style="text-align:right;"
			<s:if test='"view".equals(mode)'>disabled='true'</s:if> title="月生活费" maxlength="9"
			value='${specialCareGroups.monthsExpensesStr}' />
		</div>
		<div class="grid_1">(元)</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_3 lable-right"><label class="form-lbl ">备注：</label></div>
		<div class="grid_20">
		<textarea rows="5" class="form-txt" name="specialCareGroups.remark" id="remark" style="width: 99%"
			<s:if test='"view".equals(mode)'>disabled='true'</s:if>>${specialCareGroups.remark}</textarea>
		</div>
		
	</form>
	</div>
</div>
<script type="text/javascript">

$(document).ready(function(){
	$('#idCardNo').blur(idCardChanged);
	jQuery.validator.addMethod("exsistedIdCard", function(value, element){
		if(value==null||value==undefined||value==""){return true}
		$.ajax({
			async:false,
			url:"${path}/baseinfo/specialCareGroupsManage/hasDuplicateSpecialCareGroups.action",
		   	data:{
			"specialCareGroups.idCardNo":$('#idCardNo').val(),
			"orgId":$('#orgId').val(),
			"mode":$('#mode').val(),
			"specialCareGroups.id":$("#mode").val() == "add"?"-1":$('#specialCareGroupsId').val()
	        },
			success:function(responseData){
	        		if(!responseData){
	        			$.confirm({
							title:"确认",
							message:"该身份证号码已存在,是否继续操作?",
							width: 300,
							okFunc:getSpecialCareGroups
						});
		        }
			}
		});
		return true;
	});
	<s:if test='!"view".equals(mode)'>
	resetBirthdayField($("#birthday").val());
	$("#maintainForm").formValidate({
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
	                 data["organization.orgName"]=data.organization.orgName;
	                 if("add"==$("#mode").val()){
	                	    $("#specialCareGroupsList").addRowData(data.id,data,"first");
							$.messageBox({message:"保存新优抚对象信息成功!"});
	        				if($("#isSubmit").val()=="true"){
								$("#specialCareGroupsDialog").dialog("close");
							}else{
						 		emptyObject();
							}
						    $("#specialCareGroupsList").setSelection(data.id);
						    checkExport();
	                 }
                     if("edit"==$("#mode").val()){
		        			$.messageBox({message:"优抚对象修改成功!"});
					        $("#specialCareGroupsList").setRowData(data.id,data);
						    $("#specialCareGroupsDialog").dialog("close");
						    $("#specialCareGroupsList").setSelection(data.id);
                     }
	  	   		}
	  	  	});
		},
		rules:{
			"specialCareGroups.name":{
				required:true,
				exculdeParticalChar:true,
				minlength:2,
				maxlength:20
			},
			"specialCareGroups.gender.id":{
				required:true
			},
			"specialCareGroups.idCardNo":{
				required:true,
				idCard:true,
				exsistedIdCard:true
			},
			"specialCareGroups.specialCareNo":{
				required:true,
				minlength:2,
				maxlength:25
			},
			"specialCareGroups.telephone":{
				telephone:true
			},
			"specialCareGroups.mobileNumber":{
				mobile:true
			},
			"specialCareGroups.monthsExpenses":{
				number:true,
				min:0,
				max:999999999
			},
			"specialCareGroups.remark":{
				maxlength:200
			}
		},
		messages:{
			"specialCareGroups.name":{
				required:"请输入姓名",
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("姓名至少需要输入{0}个字符"),
				maxlength:$.format("姓名最多需要输入{0}个字符")
				},
				"specialCareGroups.gender.id":{
					required:"请选择性别"
				},
				"specialCareGroups.idCardNo":{
					required:"请输入身份证号码",
					idCard:$.format("请输入一个合法的身份证号码"),
					exsistedIdCard:"该身份证号码已存在"
				},
				"specialCareGroups.telephone":{
					telephone:$.format("固定电话不合法，只能输数字和横杠(-)")
				},
				"specialCareGroups.mobileNumber":{
					mobile:"手机号码输入只能是以1开头的11位数字"
				},
				"specialCareGroups.specialCareNo":{
					required:"请输入优抚证号",
					minlength:$.format("优抚证号至少需要输入{0}个字符"),
					maxlength:$.format("优抚证号最多需要输入{0}个字符")
				},
				"specialCareGroups.monthsExpenses":{
					number: "月生活费需要输入正数",
					min: "月生活费需要输入正数",
					max: "月生活费最大输入999999999"						
				},
				"specialCareGroups.remark":{
					maxlength:"备注最多需要输入200个字符"
				},
				"specialCareGroups.organization.orgName":{
					isGridOrganization:"网格只能属于片组片格"
				}
		}
	});
	</s:if>
	<s:if test='"add".equals(mode)'>
		$("#maintainForm").attr("action","${path}/baseinfo/specialCareGroupsManage/addSpecialCareGroups.action");
	</s:if>
	<s:if test='"edit".equals(mode)'>
		$("#maintainForm").attr("action","${path}/baseinfo/specialCareGroupsManage/updateSpecialCareGroups.action");
	</s:if>
	$.ajax({
		async: false,
		url: "${path }/sysadmin/orgManage/getOrgRelativePath.action",
		data:{
			"organization.id":${orgId}
		},
		success:function(responseData){
			$("#specialCareGroupsOrgName").val(responseData);
		}
	});
	<s:if test='#parameters.dialog != null'>
	var tree = $("#specialCareGroupsOrgName").treeSelect({
		inputName:"specialCareGroups.organization.id",
		url:"/sysadmin/orgManage/loadTownForExtTree.action"
	});
	</s:if>
});
function idCardChanged(){
	var text=$('#idCardNo').val();
	text=getBirthDayTextFromIdCard(text);
	resetBirthdayField(text);
	text=$('#idCardNo').val();
	getCommonPopulation(text);
}

function getCommonPopulation(idCardNo){
	if( idCardNo.length != null && (idCardNo.length == 18 || idCardNo.length == 15)){
		ajaxForCommonPopulation(idCardNo);
	}
}
function ajaxForCommonPopulation(idCardNo){
	$.ajax({
		async:false,
		url: "${path}/baseinfo/commonPopulationManage/getCommonPopulationByIdCardNo.action",
		data:{
			"commonPopulation.idCardNo":idCardNo
		},
		success:function(responseData){
			manageCommonPopulation(responseData);
		}
	});
}
function manageCommonPopulation(responseData){
	if(null != responseData.gender)
	    $("#gender").val(responseData.gender)
	if(null != responseData.name)
		$("#name").val(responseData.name);
	if(null != responseData.address)
	    $("#currentAddress").val(responseData.address);
	if(null != responseData.telephone)
		$("#telephone").val(responseData.telephone)
    if(null != responseData.mobileNumber)
	    $("#mobileNumber").val(responseData.mobileNumber)
}

function resetBirthdayField(text){
	if (text!="" && $('#idCardNo').val()!=""){
		$("#birthdayDiv").html("<input type='text' name='specialCareGroups.birthday' id='birthday' class='form-txt' value='"+text+"' readonly/>");
	}else{
		$('#birthday').datePicker({
			yearRange: '1900:2030',
    		dateFormat: 'yy-mm-dd',
            maxDate:'+0d'});
	}
}

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
function getSpecialCareGroups(){
	$.ajax({
		async: false,
		url: "${path }/baseinfo/specialCareGroupsManage/getSpecialCareGroupsByIdCardNo.action",
		data:{
			"specialCareGroups.organization.id":$("#orgId").val(),
			"specialCareGroups.idCardNo":$("#idCardNo").val()
		},
		success:function(responseData){
			if(responseData.id){
				$("#mode").val("edit");
				$("#maintainForm").attr("action","${path}/baseinfo/specialCareGroupsManage/updateSpecialCareGroups.action");
				$("#specialCareGroupsId").val(responseData.id);
				$("#name").val(responseData.name);
				$("#idCardNo").val(responseData.idCardNo);
				$("#gender").val(responseData.gender.id);
				$("#birthday").val(responseData.birthday!=null?responseData.birthday:"");
				$("#telephone").val(responseData.telephone!=null?responseData.telephone:"");
				$("#mobileNumber").val(responseData.mobileNumber!=null?responseData.mobileNumber:"");				
				$("#currentAddress").val(responseData.currentAddress!=null?responseData.currentAddress:"");
				$("#specialCareNo").val(responseData.specialCareNo);
				$("#specialCareType").val(responseData.specialCareType!=null?responseData.specialCareType.id:"");
				$("#labourCapacity").val(responseData.labourCapacity!=null?responseData.labourCapacity.id:"");
				$("#viability").val(responseData.viability!=null?responseData.viability.id:"");
				$("#employmentStatus").val(responseData.employmentStatus!=null?responseData.employmentStatus.id:"");
				$("#supportStatus").val(responseData.supportStatus!=null?responseData.supportStatus.id:"");
				$("#monthsExpenses").val(responseData.monthsExpenses!=null?responseData.monthsExpenses:"");
				$("#remark").val(responseData.remark!=null?responseData.remark:"");
			}
		}
	});
}
function emptyObject(){
	$("#specialCareGroupsId").val("");
	$("#name").val("");
	$("#idCardNo").val("");
	$("#gender").val("");
	$("#birthday").val("");
	$("#telephone").val("");
	$("#mobileNumber").val("");
	$("#currentAddress").val("");
	$("#specialCareNo").val("");
	$("#specialCareType").val("");
	$("#labourCapacity").val("");
	$("#viability").val("");
	$("#employmentStatus").val("");
	$("#specialPerson").val("");
	$("#monthsExpenses").val("");
	$("#remark").val("");
}
</script>


