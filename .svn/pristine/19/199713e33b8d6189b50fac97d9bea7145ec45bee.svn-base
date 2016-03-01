<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="container container_24" >
	<form action="" method="post" id="maintainForm">
		<input name="orgId" id="searchOrgId" value="${orgId}" type="hidden">
		<div class="grid_5 lable-right">
			<label class="form-lbl">所属网格：</label>
		</div>
		<div class="grid_7">
			<input id="orgName" value="" type="text"/>
		</div>
		<div class="grid_1"><em class="form-req">*</em></div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">IMSI号：</label>
		</div>
		<div class="grid_6">
			<input type="text" id="imsiNo" name="mobileInfo.imsiNo" class="form-txt" value="${mobileInfo.imsiNo}" maxlength="15"/>
		</div>
		<div class='clearLine'></div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">户主姓名：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="name" name="mobileInfo.name" class="form-txt" value="${mobileInfo.name}" maxlength="20"/>
		</div>
		<div class="grid_1"></div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">手机号：</label>
		</div>
		<div class="grid_6">
			<input type="text" id="mobileNumber" name="mobileInfo.mobileNumber" class="form-txt" value="${mobileInfo.mobileNumber}" maxlength="11"/>
		</div>
	</form>
</div>
<script>
var orgSelector;
$(document).ready(function(){
	orgSelector=$("#orgName").treeSelect({
		inputName:"orgId"
	});
	$.setTreeValue(${orgId},orgSelector); 
});
</script>