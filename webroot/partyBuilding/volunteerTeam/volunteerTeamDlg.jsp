<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="dialog-form" title="党员志愿者队伍表" class="container container_24">
	<form id="maintainForm" method="post" action="">
		<pop:token />
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<input type="hidden" id="teamId" name="volunteerTeam.id" value="${volunteerTeam.id}"/>
		<input type="hidden" name="volunteerTeam.orgInternalCode" value="${volunteerTeam.organization.orgInternalCode}"/>
		<input type="hidden" id="organizationId" name="volunteerTeam.organization.id" value="${volunteerTeam.organization.id}"/>
	 	
		<div class="grid_4 lable-right">
 			<label class="form-lb1">所属区域：</label>
 		</div>
 		<div class="grid_18">
 			<input type="text"  id="volunteerTeamOrgName"  name="volunteerTeam.organization.orgName"  readonly  value="${volunteerTeam.organization.orgName}" class="form-txt" />
 		</div>
 		<div class='clearLine'>&nbsp;</div>
 		
 		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">组织名称：</label>
	 	</div>
		<div class="grid_18">
			<input type="text" name="volunteerTeam.name" id="name"  maxlength="30" class="form-txt {required:true,exsistedTeamName:true,messages:{required:'请输入组织名称',exsistedTeamName:'此名称在系统中已经存在'}}" value="${volunteerTeam.name}"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div id="belongOrgDiv" class="hidden">
			<div class="grid_4 lable-right">
				<label class="form-lbl">所属部门：</label>
		 	</div>
			<div class="grid_7">
				 <select name="volunteerTeam.belongOrg.id" id="belongOrg" class='form-txt'>
				   	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@BELONG_ORG" defaultValue="${volunteerTeam.belongOrg.id}" />
				</select>
			</div>
			<div class='clearLine'>&nbsp;</div>
		</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lbl">服务方向：</label>
	 	</div>
		<div class="grid_18" style="height: 80px;">
			<textarea rows="3" name="volunteerTeam.serviceDirection" class="form-txt" maxlength="150">${volunteerTeam.serviceDirection}</textarea>
		</div>
	 	<div class='clearLine'>&nbsp;</div>
	 	<div class='clearLine'>&nbsp;</div>
	 	
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">备注：</label>
	 	</div>
		<div class="grid_18">
			<textarea rows="3" name="volunteerTeam.remark" class="form-txt" maxlength="150">${volunteerTeam.remark}</textarea>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
	</form>
	
</div>
<script type="text/javascript">
jQuery.validator.addMethod("exsistedTeamName", function(value, element){
	var result = true;
	if(value==null||value==undefined||value==""){return true}
	$.ajax({
		async: false ,
		url:"${path}/volunteerTeamManage/hasDuplicate.action",
	   	data:{
			"volunteerTeam.orgId":$("#organizationId").val(),
			"volunteerTeam.id":$("#teamId").val(),
			"volunteerTeam.name":value
        },
		success:function(data){
        	if(data!=null && data==true){
        		result = false;
        	}
		}
	});
	return result;
});
$(document).ready(function(){
	$("#maintainForm").formValidate({
		submitHandler: function(form) {
	        $(form).ajaxSubmit({
				success: function(data){
		        	if(!data.id){
						$.messageBox({level: "error",message:data});
						return;
					}
					if("edit"==$("#mode").val()){
						//$("#volunteerTeamList").setRowData(data.id,data);
				    	$.messageBox({message:"党员志愿者队伍表修改成功!"});
					}else if("add"==$("#mode").val()){
						//$("#volunteerTeamList").addRowData(data.id,data,"first");
						$.messageBox({message:"党员志愿者队伍表新增成功!"});
					}
					$("#volunteerTeamList").trigger("reloadGrid");
					$("#volunteerTeamDialog").dialog("close");
	      	   	},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	    	alert("提交数据时发生错误");
	      	   	}
	      	});
		},
		rules:{
		},
		messages:{
		}
	});
	if(isDistrict()){
		$("#belongOrgDiv").show();
	}
<c:if test='${mode=="add"}'>
    $("#maintainForm").attr("action","${path}/volunteerTeamManage/addVolunteerTeam.action");
</c:if>
<c:if test='${mode=="edit"}'>
    $("#maintainForm").attr("action","${path}/volunteerTeamManage/updateVolunteerTeam.action");
</c:if>
<c:if test='${mode=="view"}'>
	$("#maintainForm").find("input,select,textarea").attr("disabled","disabled");
</c:if>

	$.ajax({
		async: false,
		url: "${path}/sysadmin/orgManage/getOrgRelativePath.action",
		data:{
			"organization.id":$("#organizationId").val()
		},
		success:function(responseData){
			$("#volunteerTeamOrgName").val(responseData);
		}
	});

});

</script>


