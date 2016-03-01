<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="dialog-form" title="区域内主要党组织资源" class="container container_24">
	
	<form id="maintainForm" method="post" action="">
		<s:if test="@com.tianque.core.util.DialogMode@ADD_MODE.equalsIgnoreCase(mode)">
			<pop:token />
		</s:if>
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<input type="hidden" id="organizationId" name="partyresource.organization.id" value="${partyresource.organization.id}"/>
		<input type="hidden" id="id" name="partyresource.id" value="${partyresource.id}"/>
		
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">组织名称：</label>
	 	</div>
		<div class="grid_20">
			<input type="text" name="partyresource.name" id="name"  maxlength="50" class='form-txt {required:true,maxlength:50,messages:{required:"请输入组织名称",maxlength:$.format("姓名不能多于{0}个字符")}}' value="${partyresource.name}"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">负责人：</label>
	 	</div>
		<div class="grid_8">
			<input type="text" name="partyresource.manager" id="manager"  maxlength="20" class='form-txt {required:true,maxlength:20,minlength:2,exculdeParticalChar:true,messages:{required:"请输入负责人",maxlength:$.format("负责人姓名不能多于{0}个字符"),minlength:$.format("负责人姓名不能少于{0}个字符"),exculdeParticalChar:"不能输入非法字符"}}' value="${partyresource.manager}"/>
		</div>
		
	 	<div class="grid_4 lable-right">
	 		<em class="form-req">*</em>
			<label class="form-lbl">联系电话：</label>
	 	</div>
		<div class="grid_8">
			<input type="text" name="partyresource.telephone" id="telephone"  maxlength="15" style="width: 93%" class='form-txt {required:true,maxlength:15,telephone:true,messages:{required:"请输入联系电话",maxlength:$.format("联系电话不能多于{0}个字符"),telephone:"请填写正确的联系电话"}}' value="${partyresource.telephone}"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">地点：</label>
	 	</div>
		<div class="grid_20">
			<input type="text" name="partyresource.address" id="address"  maxlength="50" class="form-txt" value="${partyresource.address}"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">备注：</label>
	 	</div>
		<div class="grid_20">
			<textarea id="issueContent" name="partyresource.remark" class='form-txt {maxlength:300,messages:{maxlength:$.format("备注不能多于{0}个字符")}}' rows="4">${partyresource.remark}</textarea>
		</div>
	</form>
	
</div>
<script type="text/javascript">

$(document).ready(function(){
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
		        	<c:if test='${mode=="edit"}'>
						$("#partyresourceList").setRowData(data.id,data);
				    	$.messageBox({message:"区域内主要党组织资源修改成功!"});
				    	$("#partyresourceDialog").dialog("close");
					</c:if>
					<c:if test='${mode=="add"}'>
						$("#partyresourceList").addRowData(data.id,data,"first");
						$.messageBox({message:"区域内主要党组织资源新增成功!"});
						$("#partyresourceList").trigger("reloadGrid");
						$("#partyresourceDialog").dialog("close");
					</c:if>
	      	   	},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	    	alert("提交数据时发生错误");
	      	   	}
	      	});
		}
	});
	<c:if test='${mode=="add"}'>
	    $("#maintainForm").attr("action","${path}/partyresourceManage/addPartyresource.action");
	</c:if>
	<c:if test='${mode=="edit"}'>
	    $("#maintainForm").attr("action","${path}/partyresourceManage/updatePartyresource.action");
	</c:if>
});

</script>


