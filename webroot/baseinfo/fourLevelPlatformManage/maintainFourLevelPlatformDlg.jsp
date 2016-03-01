<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="四级平台" class="container container_24">
	
	<form id="maintainForm" method="post" action="">
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<input type="hidden" id="id" name="fourLevelPlatform.id" value="${fourLevelPlatform.id}"/>
		<input type="hidden" id="organizationId" name="fourLevelPlatform.organization.id" value="${fourLevelPlatform.organization.id}"/>
		<input type="hidden" id="orgInternalCode" name="fourLevelPlatform.orgInternalCode" value="${fourLevelPlatform.orgInternalCode}"/>
		<input type="hidden" name="fourLevelPlatform.teamType" id="teamType" value=""/>
		<s:if test='"edit".equals(mode)'>
			<input type="hidden" name="fourLevelPlatform.teamTypeName" id="teamTypeName" value="${fourLevelPlatform.teamTypeName}"/>
		</s:if>
		
	 	
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
 			<label class="form-lb1">所属网格：</label>
 		</div>
 		<div class="grid_18">
 			<input type="text"  id="fourLevelPlatformOrgName"  name="fourLevelPlatform.organization.orgName"  readonly  value="${fourLevelPlatform.organization.orgName}" style="width: 99%"	class="form-txt" />
 		</div>
 		<div class='clearLine'>&nbsp;</div>
	 	
	 	<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">名称：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="fourLevelPlatform.name" id="name"  maxlength="30" class="form-txt" value="${fourLevelPlatform.name}"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
	 	<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">组织类型：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="fourLevelPlatform.teamTypeName" id="teamTypeName" readonly class="form-txt" value="${fourLevelPlatform.teamTypeName}"/>
		</div>
		<s:if test="fourLevelPlatform.organization.orgLevel.internalId == @com.tianque.domain.property.OrganizationLevel@DISTRICT">
		<div class='clearLine'>&nbsp;</div>
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">管理部门：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="fourLevelPlatform.supervisorydepartment" id="supervisorydepartment"  maxlength="30" class="form-txt" value="${fourLevelPlatform.supervisorydepartment}"/>
		</div>
		</s:if>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">备注：</label>
		</div>
		<div class="grid_20 heightAuto">
			<textarea id="remark" style="width:440px;height:100px" name="fourLevelPlatform.remark" title="请输入备注" maxlength="100"
			class="form-txt {maxlength:200,messages:{maxlength:$.format('备注最多需要输入{0}个字符')}}">${fourLevelPlatform.remark}</textarea>
		</div>
		<div class='clearLine'>&nbsp;</div>
	</form>
	
</div>
<script type="text/javascript">

$(document).ready(function(){
	<s:if test='"add".equals(mode)'>
		$("#teamType").val($("#currentOrgId").attr("orgLevelInternalId"));
	</s:if>
	if(isDistrict()){
		$("#teamTypeName").val("县监管中心");
	}else if(isTown()){
		$("#teamTypeName").val("镇监管分中心");
	}else if(isVillage()){
		$("#teamTypeName").val("社区工作站");
	}else if(isGrid()){
		$("#teamTypeName").val("网格");
	}
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
						onOrgChanged(getCurrentOrgId(),isGrid());
				    	$.messageBox({message:"四级平台修改成功!"});
				    	$("#fourlevelplatformDialog").dialog("close");
					</s:if>
					<s:if test='"add".equals(mode)'>
						onOrgChanged(getCurrentOrgId(),isGrid());
						$.messageBox({message:"四级平台新增成功!"});
						$("#fourlevelplatformDialog").dialog("close");
					</s:if>
	      	   	},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	    	alert("提交数据时发生错误");
	      	   	}
	      	});
		},
		rules:{
			"fourLevelPlatform.name":{
				required:true,
				minlength:2,
				maxlength:30
			},
			"fourLevelPlatform.teamTypeName":{
				required:true
			},
			"fourLevelPlatform.supervisorydepartment":{
				minlength:2,
				maxlength:30
			}
		},
		messages:{
			"fourLevelPlatform.name":{
				required:"请输入名称",
				minlength:$.format("名称不能少于{0}个字符"),
				maxlength:$.format("名称不能多于{0}个字符")
			},
			"fourLevelPlatform.teamTypeName":{
				required:"组织类型不能为空"
			},
			"fourLevelPlatform.supervisorydepartment":{
				minlength:$.format("管理部门不能少于{0}个字符"),
				maxlength:$.format("管理部门不能多于{0}个字符")
			}
		},
		ignore:':hidden'
	});
<s:if test='"add".equals(mode)'>
    $("#maintainForm").attr("action","${path}/fourLevelPlatformManage/addFourLevelPlatform.action");
</s:if>
<s:if test='"edit".equals(mode)'>
    $("#maintainForm").attr("action","${path}/fourLevelPlatformManage/updateFourLevelPlatform.action");
</s:if>
<s:if test='"add".equals(mode)'>
	$.ajax({
		async: false,
		url: "${path}/sysadmin/orgManage/getOrgRelativePath.action",
		data:{
			"organization.id":$("#organizationId").val()
		},
		success:function(responseData){
			$("#fourLevelPlatformOrgName").val(responseData);
		}
	});
</s:if>
});

</script>


