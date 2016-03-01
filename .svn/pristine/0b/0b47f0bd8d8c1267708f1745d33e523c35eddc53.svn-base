<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="container container_24">
	<form id="changeAccNoForm" action="${path}/baseinfo/houseFamily/changeAccountNumber.action">
	<pop:token />
		<div class="grid_8 lable-right"><label class="form-lbl">原家庭户口号：</label>
		</div>
		<div class="grid_16"><input type="text" id="houseFamilyAccountNumber" value="${houseFamily.censusRegisterFamily.accountNumber}" maxlength="20" class="form-txt" disabled="disabled"/>
		</div>
		<div class="grid_8 lable-right"><label class="form-lbl">户主身份证号码：</label>
		</div>
		<div class="grid_16"><input type="text" id="houseFamilyIdCardNo" value="${houseFamily.censusRegisterFamily.idCardNo}" maxlength="20" class="form-txt" disabled="disabled"/>
		</div>
		<div class="grid_8 lable-right"><label class="form-lbl">户主姓名：</label>
		</div>
		<div class="grid_16"><input type="text" id="houseFamilyHouseHoldName" value="${houseFamily.censusRegisterFamily.householdName}" maxlength="20" class="form-txt" disabled="disabled"/>
		</div>
		<div class="grid_8 lable-right"><em class="form-req">*</em><label class="form-lbl">新家庭户口号：</label>
		</div>
		<div class="grid_16"><input type="text" name="newAccNo" id="newAccNo" maxlength="20" class="form-txt {required:true,notExistAccountNumber:true,messages:{required:'请输入新家庭户口号',notExistAccountNumber:'家庭户口号已存在！'}}" />
		</div>
		<input type="hidden" name="houseFamily.id" value="${houseFamily.censusRegisterFamily.id}">
		<input type="hidden" id="orgId" name="orgId" value="${orgId}"/>
		<input type="hidden" id="shardOrgId" name="shardOrgId" value="${houseFamily.organization.id}"/>
	</form>
</div>
<script type="text/javascript">
$(document).ready(function(){
	jQuery.validator.addMethod("notExistAccountNumber", function(value, element){
		var flag = false;
		if($('#newAccNo').val() == $('#houseFamilyAccountNumber').val()){
			flag = true;
		}else{
			$.ajax({
				async:false,
				url:'${path}/baseinfo/houseFamily/existAccountNumber.action',
			   	data:{
					"newAccNo":$('#newAccNo').val(),
					"orgId":$('#orgId').val()
		        },
				success:function(data){
					flag = !data;
				}
			});
		}
		return flag;
	});
	$("#changeAccNoForm").formValidate({
		submitHandler: function(form) {
			$(form).ajaxSubmit({
	     		success: function(data){
	     			$("#houseFamilyList").trigger("reloadGrid");
					$.messageBox({message:"成功重置户口号!"});
	     			$("#houseFamilyDialog").dialog("close");
	  	   		},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	  	   		     alert("提交错误");
	      	   	}
	  	  	});
		},
		rules:{
		},
		messages:{
		},
		ignore:':hidden'
	});
});
</script>