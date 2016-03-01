<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="dialog-form" title="重大紧急等级" class="container container_24">
	<input type="hidden" value="false" id="isSkip">
	<c:forEach items="${urgentLevels}" var="urgentLevel">
		<label><input type="radio" value="${urgentLevel.id}" name="issueStep.emergencyLevel.id"/>${urgentLevel.displayName}</label>
		<div class='clearLine'>&nbsp;</div>
	</c:forEach>
</div>

<script type="text/javascript">
$(document).ready(function(){
	if($("input:radio").length>0){
		$("div.loadPage").height(27*$("input:radio").length);
		$("div.loadPage").width(60);
	}	
	$("input:radio").click(function(){
		$.ajax({
			url:'${path}/issues/issueManage/updateIssueStepUrgentLevelForCache.action',
			data:{
				"issueStep.id":getSelectedId(),
				"issueStep.emergencyLevel.id":$(this).val()
			},
			success:function(data){
				if("true"==data||true==data){
					$("#isSkip").val(true);
					$("#dealTop").click();
				}	
			}
		});
	});
});
</script>
