<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="container container_24">
		<form id="maintainForm" action="" method="post" enctype="application/x-www-form-urlencoded">
			<input id="mode" type="hidden" name="mode" value="${mode}" />
			<input type="hidden" name="servicePerson.id" value="${servicePerson.id}" />
			<input type="hidden" name="servicePerson.organization.id" value="${organization.id}" />
			<div class="grid_8 lable-right">
				<label class="form-lbl">姓名：</label>
			</div>
			<div class="grid_9">
				<input id="servicePerson.name" class="form-txt" name="servicePerson.name" type="text" value="${servicePerson.name}"/>
			</div>
			<div class="grid_1">
    			<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
    		</div>
			<div class="clearLine">&nbsp;</div>
			<div class="grid_8 lable-right">
				<label class="form-lbl">职位：</label>
			</div>
			<div class="grid_9">
				<input id="servicePerson.position" class="form-txt" name="servicePerson.position" type="text" value="${servicePerson.position}"/>
			</div>
			<div class="clearLine">&nbsp;</div>
			<div class="grid_8 lable-right">
				<label class="form-lbl">手机号码：</label>
			</div>
			<div class="grid_9">
				<input id="servicePerson.phone" class="form-txt" name="servicePerson.phone" type="text" value="${servicePerson.phone}" maxlength="11"/>
			</div>
			<div class="grid_1">
    			<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
    		</div>
		</form>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$("#maintainForm").formValidate({
			promptPosition: "bottomLeft",
			
			submitHandler: function(form) {
				doAjaxSubmit();
			},
			
			rules: {
				"servicePerson.name":{
					required:true,
					exculdeParticalChar:true,
					minlength:2,
					maxlength:10
				},
				"servicePerson.position":{
					maxlength:20
				},
				"servicePerson.phone":{
					required:true,
					mobile:true
				}
			},

			messages: {
				"servicePerson.name":{
					required:"请输入服务人员姓名",
					exculdeParticalChar:"服务人员姓名不能有特殊字符",
					minlength:$.format("服务人员姓名最少需要输入{0}个字符"),
					maxlength:$.format("服务人员姓名最多只能输入{0}个字符")
				},
				"servicePerson.position":{
					maxlength:$.format("服务人员职务最多只能输入{0}个字符")
				},
				"servicePerson.phone":{
					required:"请输入服务人员手机号码",
					mobile:"手机号码只能是1开头的11位数字"
				}
			}
		});
		<s:if test='"add".equals(mode)'>
			$("#maintainForm").attr("action","${path}/baseinfo/servicePerson/addServicePerson.action");
		</s:if>
		<s:if test='"edit".equals(mode)'>
			$("#maintainForm").attr("action","${path}/baseinfo/servicePerson/updateServicePerson.action");
		</s:if>
	})
	
	function doAjaxSubmit() {
		$("#maintainForm").ajaxSubmit({
			success: function(data) {
				if(!data.id){
	           	 $.messageBox({
						message:data,
						level: "error"							
					 });
	            	return;
	            }
				<s:if test='"add".equals(mode)'>
					$("#servicePersonList").addRowData(data.id,data,"first");
			    	$.messageBox({message:"成功保存服务人员信息!"});
			    	$("#servicePersonDialog").dialog("close");
			    </s:if>	
			    <s:if test='"edit".equals(mode)'>
				    $("#servicePersonList").setRowData(data.id,data);
				    $.messageBox({message:"成功修改服务人员信息!"});
				    $("#servicePersonDialog").dialog("close");
			    </s:if>
			    $("#servicePersonList").setSelection(data.id);
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
		 	      alert("提交错误");
		 	}
		});
	}
</script>

