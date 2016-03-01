<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="container container_24" >
	<form action="${path}/sysadmin/mobileInfo/addMobileInfo.action" method="post" id="maintainForm">
		<div class="grid_5 lable-right">
			<label class="form-lbl">所属网格：</label>
		</div>
		<div class="grid_18">
			<input name="orgId" id="mobileInfoOrgId" value="${orgId}" type="hidden">
			<input type="text" id="orgName" class="form-txt" name="mobileInfo.organization.orgName"
				<s:if test='"view".equals(mode)'>disabled</s:if> value="${mobileInfo.organization.orgName }"/>
		</div>
		<div class="grid_1"><s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if></div>
		<div class='clearLine'></div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">IMSI号：</label>
		</div>
		<div class="grid_6">
			<input type="text" id="imsiNo" name="mobileInfo.imsiNo" class="form-txt" value="${mobileInfo.imsiNo}" maxlength="15"
			<s:if test='"view".equals(mode)'>disabled</s:if>/>
		</div>
		<div class="grid_1"><s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if></div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">户主姓名：</label>
		</div>
		<div class="grid_6">
			<input type="text" id="name" name="mobileInfo.name" class="form-txt" value="${mobileInfo.name}" maxlength="20"
			<s:if test='"view".equals(mode)'>disabled</s:if>/>
		</div>
		<div class='clearLine'></div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">手机号：</label>
		</div>
		<div class="grid_6">
			<input type="text" id="mobileNumber" name="mobileInfo.mobileNumber" class="form-txt" value="${mobileInfo.mobileNumber}" maxlength="11"
			<s:if test='"view".equals(mode)'>disabled</s:if>/>
		</div>
		<div class="grid_1"><s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if></div>
		<div class='clearLine'></div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">受理时间：</label>
		</div>
		<div class="grid_6">
			<input type="text" id="acceptTime" name="mobileInfo.acceptTime"  class="form-txt" readonly
				value="<s:date name="mobileInfo.acceptTime" format="yyyy-MM-dd"/>" maxlength="20"
			<s:if test='"view".equals(mode)'>disabled</s:if>/>
		</div>
		<div class="grid_1"><s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if></div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">有效时间：</label>
		</div>
		<div class="grid_6">
			<input type="text" id="effectivelyTime" name="mobileInfo.effectivelyTime" class="form-txt" readonly
				value="<s:date name="mobileInfo.effectivelyTime" format="yyyy-MM-dd"/>" maxlength="20"
			<s:if test='"view".equals(mode)'>disabled</s:if>/>
		</div>
		<div class="grid_1"><s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if></div>
		<div class='clearLine'></div>
	</form>
</div>
<script>
var orgSelector;
$(document).ready(function(){
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
			$(form).ajaxSubmit({
		            success: function(data){
                    if(data==null || !data.id){
                   	 	$.messageBox({
							message:data,
							level: "error"
						});
                   	 //$("#mobileInfoDialog").dialog("close");
                    	return;
                  	}
				     $("#mobileInfoList").addRowData(data.id,data,"first");
				     $.messageBox({message:"成功保存新用户信息!"});
				     $("#mobileInfoDialog").dialog("close");
				     $("#mobileInfoList").setSelection(data.id);
		     		},
		     	   	error: function(XMLHttpRequest, textStatus, errorThrown){
		     	      		alert("提交错误");
		     	   	}
		     	});
		}
	});

	$('#acceptTime').datePicker({
		yearRange: '1900:2030',
		dateFormat: 'yy-mm-dd',
        maxDate:'+0d'});

	$('#effectivelyTime').datePicker({
		yearRange: '1900:2030',
		dateFormat: 'yy-mm-dd',
        maxDate:'+2y'});
});
</script>