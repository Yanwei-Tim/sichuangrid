<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="dialog-form" title="两新组织党组织" class="container container_24">
	
	<form id="maintainForm" method="post" action="">
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<input type="hidden" id="partyOrgId" name="newPartyOrg.id" value="${newPartyOrg.id}"/>
		<input type="hidden" id="organizationId" name="newPartyOrg.organization.id" value="${newPartyOrg.organization.id}"/>
		<input type="hidden" name="newPartyOrg.orgInternalCode" value="${newPartyOrg.organization.orgInternalCode}"/>
		
	 	
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
 			<label class="form-lb1">所属网格：</label>
 		</div>
 		<div class="grid_18">
 			<input type="text"  id="newPartyOrgOrgName"  name="newPartyOrg.organization.orgName"  readonly  value="${newPartyOrg.organization.orgName}" style="width: 99%"	class="form-txt" />
 		</div>
 		<div class='clearLine'>&nbsp;</div>
	 	
	 	<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">党组织类别：</label>
	 	</div>
		<div class="grid_7">
			<select name="newPartyOrg.type.id" id="partyOrgType" class='form-txt {required:true,messages:{required:"请选择党组织类别"}}'>
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@PARTYORGTYPE"  defaultValue="${newPartyOrg.type.id}" />
			</select>
		</div>
	 	<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">组织名称：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="newPartyOrg.name" id="name"  maxlength="30" value="${newPartyOrg.name}" 
				class='form-txt {required:true,exsistedNewPartyOrg:true,messages:{required:"请输入组织名称",exsistedNewPartyOrg:"此名称在系统中已经存在"}}'/>
			<input type="hidden" name="oldPartyOrg" id="oldPartyOrg"  maxlength="30" class="form-txt" value="${newPartyOrg.name}"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">地址：</label>
	 	</div>
		<div class="grid_18">
			<input type="text" name="newPartyOrg.address" id="address"  maxlength="30" class="form-txt" value="${newPartyOrg.address}"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">备注：</label>
	 	</div>
		<div class="grid_18">
			<textarea rows="5" name="newPartyOrg.remark" style="width: 99%" class='form-txt {maxlength:300,messages:{maxlength:"备注最多只能输入300个字符"}}' >${newPartyOrg.remark}</textarea>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
	</form>
	
</div>
<script type="text/javascript">
jQuery.validator.addMethod("exsistedNewPartyOrg", function(value, element){
	var result = true;
	if(value==null||value==undefined||value==""){return true}
	$.ajax({
		async: false ,
		url:"${path}/newPartyOrgManage/hasDuplicateNewPartyOrg.action",
	   	data:{
			"searchVo.orgId":$("#organizationId").val(),
			"searchVo.name":value,
			"searchVo.id":$("#partyOrgId").val()
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
						//$("#newPartyOrgList").setRowData(data.id,data);
				    	$.messageBox({message:"两新组织党组织表修改成功!"});
					}else if("add"==$("#mode").val()){
						//$("#newPartyOrgList").addRowData(data.id,data,"first");
						$.messageBox({message:"两新组织党组织表新增成功!"});
					}
					$("#newPartyOrgList").trigger("reloadGrid");
					$("#newPartyOrgDialog").dialog("close");
	      	   	},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	    	alert("提交数据时发生错误");
	      	   	}
	      	});
		},
		rules:{
		},
		messages:{
		},
		ignore:':hidden'
	});
<c:if test='${mode=="add"}'>
    $("#maintainForm").attr("action","${path}/newPartyOrgManage/addNewPartyOrg.action");
</c:if>
<c:if test='${mode=="edit"}'>
    $("#maintainForm").attr("action","${path}/newPartyOrgManage/updateNewPartyOrg.action");
</c:if>
<c:if test='${mode=="view"}'>
	$("#maintainForm").find("input,select,textarea").attr("disabled","disabled")
</c:if>
	$.ajax({
		async: false,
		url: "${path}/sysadmin/orgManage/getOrgRelativePath.action",
		data:{
			"organization.id":$("#organizationId").val()
		},
		success:function(responseData){
			$("#newPartyOrgOrgName").val(responseData);
		}
	});
});

</script>


