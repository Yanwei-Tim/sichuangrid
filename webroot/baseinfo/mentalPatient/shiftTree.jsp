<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div  title="请选择部门">
		<div id="organizationTree" style="clear: both;"></div>
	</div>
<form id="maintainShiftForm" method="post" action="${path}/baseinfo/mentalPatient/shiftMentalPatient.action">
<pop:token />
	<input name="orgId" id="orgId" value="${mentalPatient.organization.id}" type="hidden">
	<input name="orgPangtId" id="orgPangtId" value="${mentalPatient.organization.parentOrg.id}" type="hidden">
	<input name="mentalPatient.organization.id" id="shiftOrgId" value="" type="text" style="width:0px;border:0px;height:0px;overflow:hidden;position:absolute;top:25px;left:70px;">
	<input name="mentalPatientIds" id="mentalPatientIds" value="${mentalPatientIds}" type="hidden">
</form >
<script type="text/javascript" >
var orgPangtId = $("#orgPangtId").val();
var orgId = $("#orgId").val();
var orgSelector;
function closeDialog(){
	var selectOrgId=$.getSelectedNode(orgSelector).attributes.id;
	if (selectOrgId!=null){
		$("#shiftOrgId").val(selectOrgId);
	}
}
$(document).ready(function(){
	orgSelector=$("#organizationTree").initTree({rootId:orgPangtId,
		loadCom:function(){
			$.disableNode(orgSelector,orgId);
			$.disableNode(orgSelector,orgPangtId);
	}});
	$.addClick(orgSelector,closeDialog);
	jQuery.validator.addMethod("isGridOrganization", function(value, element){
		if(value==null||value==undefined||value==""){return true}
		$.ajax({
			async:false,
			url:"${path}/sysadmin/orgManage/isGridOrganization.action",
			data:{
				"organization.id":$("#shiftOrgId").val()
			},
			success:function(responseData){
				bol = responseData;
			}
		});
		return bol;
	});

	jQuery.validator.addMethod("isModernOrg", function(value, element){
		if($("#orgId").val()==$("#shiftOrgId").val()){
			return false;
		}
		return true;
	});
	
	$("#maintainShiftForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
         $(form).ajaxSubmit({
             success: function(data){
                     if(data){
                    	 $.messageBox({message:"数据转移成功!"});
                    	 $("#mentalPatientList").trigger("reloadGrid");
     					$("#mentalPatientDialog").dialog("close");
                     }
      	   },
	      error: function(data){
      		 $.messageBox({
					message:data,
					level: "error"
				 });
	      	   }
	       });
		},
		rules:{"mentalPatient.organization.id":{isGridOrganization:true,isModernOrg:true}},
		messages:{"mentalPatient.organization.id":{isGridOrganization:"数据只能转移得到网格",isModernOrg:"数据不能转移到当前网格"}}
	});
});
</script>