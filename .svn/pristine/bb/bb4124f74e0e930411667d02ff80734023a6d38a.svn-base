<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="dialog-form" title="服务项目" class="container container_24">
	
	<form id="maintainForm" method="post" action="">
		<pop:token />
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<input type="hidden" id="id" name="serviceProject.id" value="${serviceProject.id}"/>
		<input type="hidden" id="organizationId" name="serviceProject.organization.id" value="${serviceProject.organization.id}"/>
		<input type="hidden" id="orgInternalCode" name="serviceProject.orgInternalCode" value="${serviceProject.orgInternalCode}"/>
		
	 	
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
 			<label class="form-lb1">所属网格：</label>
 		</div>
 		<div class="grid_18">
 			<input type="text"  id="serviceProjectOrgName"  name="serviceProject.organization.orgName"  readonly  value="${serviceProject.organization.orgName}" style="width: 99%"	class="form-txt" />
 		</div>
 		<div class='clearLine'>&nbsp;</div>
	 	
	 	<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">项目名称：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="serviceProject.projectName" id="projectName"  maxlength="20" class="form-txt" value="${serviceProject.projectName}"/>
		</div>
	 	
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">拟认领数：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="serviceProject.claimPlans" id="claimPlans"  maxlength="6" class="form-txt" value="${serviceProject.claimPlans}"/>
		</div>
	 	
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">项目内容：</label>
	 	</div>
		<div class="grid_18 heightAuto">
			<textarea rows="4"  style="width: 99%;" name="serviceProject.projectContent" id="projectContent"  maxlength="200" class="form-txt">${serviceProject.projectContent}</textarea>
		</div>
		<div class='clearLine'>&nbsp;</div>
	 	
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">联系人：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="serviceProject.contractor" id="contractor"  maxlength="20" class="form-txt" value="${serviceProject.contractor}"/>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">联系电话：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="serviceProject.telephone" id="telephone"  maxlength="13" class="form-txt" value="${serviceProject.telephone}" title="请输入由数字和-组成的联系电话 例如：0577-88888888"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">备注：</label>
	 	</div>
		<div class="grid_18 heightAuto">
			<textarea rows="4" style="width: 99%;" name="serviceProject.remark" id="remark"  maxlength="200" class="form-txt" >${serviceProject.remark}</textarea>
		</div>
	 	
		<div class='clearLine'>&nbsp;</div>
		
	</form>
	
</div>
<script type="text/javascript">
var projectNameData;
$(document).ready(function(){
	
	jQuery.validator.addMethod("exsistedProjectName", function(value, element){
		var value=$('#projectName').val();
		if(value==null||value==undefined||value==""){return true}
		$.ajax({
			async: false ,
			url:"${path}/serviceprojectManage/hasDuplicateProjectName.action",
		   	data:{
				"serviceProject.projectName":$('#projectName').val(),
				"serviceProject.organization.id":$('#organizationId').val(),
				"serviceProject.id":$('#id').val()
	        },
			success:function(responseData){
				projectNameData = responseData;
			}
		});
		if(!(projectNameData==null||projectNameData=="")){
			return false;
		}
		return true;
	});
	
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
					<c:if test='${mode=="edit"}'>
						onOrgChanged(getCurrentOrgId(),isGrid());
				    	$.messageBox({message:"服务项目修改成功!"});
				    	$("#serviceprojectDialog").dialog("close");
					</c:if>
					<c:if test='${mode=="add"}'>
						onOrgChanged(getCurrentOrgId(),isGrid());
						$.messageBox({message:"服务项目新增成功!"});
						$("#serviceprojectDialog").dialog("close");
					</c:if>
	      	   	},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	    	alert("提交数据时发生错误");
	      	   	}
	      	});
		},
		rules:{
			"serviceProject.projectName":{
				required:true,
				exsistedProjectName:true
			},
			"serviceProject.claimPlans":{
				digits:true,
				range:[1,999999]
			},
			"serviceProject.contractor":{
				minlength:2,
				maxlength:20
			},
			"serviceProject.telephone":{
				telephone:true
			}
		},
		messages:{
			"serviceProject.projectName":{
				required:"请输入项目名称",
				exsistedProjectName:function(){
					return projectNameData;
				}
			},
			"serviceProject.claimPlans":{
				digits:"拟认领数只能输入1到999999之间的整数",
				range:$.format("拟认领数只能输入{0}到{1}之间的整数")
			},
			"serviceProject.contractor":{
				minlength:$.format("联系人至少需要输入{0}个字符"),
				maxlength:$.format("联系人最多需要输入{0}个字符")
			},
			"serviceProject.telephone":{
				telephone:"联系电话不合法，只能输数字和横杠(-)"
			}
		},
		ignore:':hidden'
	});
	<c:if test='${mode=="add"}'>
    $("#maintainForm").attr("action","${path}/serviceprojectManage/addServiceProject.action");
</c:if>
<c:if test='${mode=="edit"}'>
    $("#maintainForm").attr("action","${path}/serviceprojectManage/updateServiceProject.action");
</c:if>
<c:if test='${mode=="add"}'>
	$.ajax({
		async: false,
		url: "${path}/sysadmin/orgManage/getOrgRelativePath.action",
		data:{
			"organization.id":$("#organizationId").val()
		},
		success:function(responseData){
			$("#serviceProjectOrgName").val(responseData);
		}
	});
</c:if>
});

</script>


