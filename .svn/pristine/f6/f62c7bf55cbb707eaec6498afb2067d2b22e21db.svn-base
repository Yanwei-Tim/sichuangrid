<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%
if(ThreadVariable.getUser()!=null){
	request.setAttribute("NAME",ThreadVariable.getUser().getName());
}
%>

	<s:if test="#request.type=='convert'">
		<s:action name="dispatch" namespace="/threeRecords/ledgerConvert" executeResult="true">
			<s:param name="id" value="#request.keyId"></s:param>
			<s:param name="mode" >view</s:param>
		</s:action>
	</s:if>

	<div id="tabs-option">
		<ul>
			<li><a id="basicTab" href="#basicInfo" onclick="changeForm('maintainForm')">基本信息</a></li>
			<li id="itemTab" <s:if test='"add".equals(mode)'>style="display:none"</s:if>><a href="#itemInfo" onclick="changeForm('maintainForm1')">研究整理</a></li>
		</ul>
		<div id="basicInfo" class="container container_24"  style="overflow:hidden;">
			<jsp:include page="${path}/account/peopleAspiration/basic.jsp"/>
		</div>
		
		<div id="itemInfo" class="container container_24"  style="overflow:hidden;">
			<jsp:include page="${path}/account/peopleAspiration/science.jsp"/>
		</div>
		
		<input type="hidden" id="_id" value='maintainForm'/>
		
	</div>


<script type="text/javascript">
var peopleTree;

$(document).ready(function(){
	$("#tabs-option").tabs();
})	
function  getOccurOrgId(){
	return $("#occurOrgId").val();
} 
function changeForm(form){
	$('#_id').val(form);
	if(form=="maintainForm"){
		$(".ui-dialog-buttonset  button").eq(0).hide();
		$(".ui-dialog-buttonset  .ui-button-text").eq(1).text('下一步');
	}else{
		$(".ui-dialog-buttonset  button").eq(0).show();
		$(".ui-dialog-buttonset  .ui-button-text").eq(1).text('保存');
	}
}
function submit(){
	$("#"+$('#_id').val()).submit();
}

</script>


