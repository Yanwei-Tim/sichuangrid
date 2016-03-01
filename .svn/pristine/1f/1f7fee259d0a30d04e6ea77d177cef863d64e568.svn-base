<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<div class="container container_24">
	<form id="moveHouseMemberForm" action="${path}/baseinfo/houseFamily/moveHouseMember.action">
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
		<div class="grid_16"><input type="text" name="newFamilyMember.accountNumber" id="newAccNo" maxlength="20" class="form-txt {required:true,messages:{required:'请输入新家庭户口号'}}" />
		</div>
		<div class="grid_8 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">与户主关系：</label>
		</div>
		<div class="grid_16">
			<select id="relationshipWithHeadId" name="newFamilyMember.relationShipWithHead.id" class="form-select {required:true,messages:{required:'请选择与户主关系'}}">
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@RELATION_SHIP_WITH_HEAD" exceptInternalIds="@com.tianque.domain.property.RelationShipWithHead@HEADER" defaultValue="${population.relationShipWithHead.id}" />
			</select>
		</div>
		<input type="hidden" id="houseFamilyId" name="houseFamily.id" value="${houseFamily.censusRegisterFamily.id}">
		<input type="hidden" id="orgId" name="orgId" value="${orgId}"/>
		<input type="hidden" id="idCardNo" name="newFamilyMember.idCardNo" value="${newFamilyMember.idCardNo}">
	</form>
</div>
<script type="text/javascript">
function accountNumberHasHouseHold(){
    var isAccountNumberHasHouseHold = false;
	$.ajax({
		async : false,
		url : "${path }/baseinfo/householdStaff/getCensusRegisterFamilyByOrgIdAndAccountNumber.action?orgId="+$("#orgId").val()+"&householdStaffVo.accountNumber="+$("#newAccNo").val(),
		success : function(data) {
			if(data != null){
				//if(data.idCardNo != 'null' && data.idCardNo != ''){
					isAccountNumberHasHouseHold = true;
				//}
			}
		}
	});
	return isAccountNumberHasHouseHold;
}
$(document).ready(function(){
	
	$("#moveHouseMemberForm").formValidate({
		submitHandler: function(form) {
			if(accountNumberHasHouseHold()){
				$(form).ajaxSubmit({
		     		success: function(data){
		     			$("#houseFamilyMemberList").trigger("reloadGrid");
		     			$("#houseFamilyList").trigger("reloadGrid");
						$.messageBox({message:"成功移除成员!"});
		     			$("#houseFamilyMemberDialog").dialog("close");
		  	   		},
		      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
		  	   		     alert("提交错误");
		      	   	}
		  	  	});
			}else{
				$.confirm({
					title:"确认",
					message:"该户口号不存在，是否新增户口号并继续移除该人员？",
					okFunc: function() {
						$(form).ajaxSubmit({
				     		success: function(data){
				     			$("#houseFamilyMemberList").trigger("reloadGrid");
				     			$("#houseFamilyList").trigger("reloadGrid");
								$.messageBox({message:"成功移除成员!"});
				     			$("#houseFamilyMemberDialog").dialog("close");
				  	   		},
				      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
				  	   		     alert("提交错误");
				      	   	}
				  	  	});
					}
				})
			}
		},
		rules:{
		},
		messages:{
		},
		ignore:':hidden'
	});
});
</script>