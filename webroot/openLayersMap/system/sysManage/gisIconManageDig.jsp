<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>	
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/openLayersMap/includes/jsInclude.jsp" />

<div id="iconSelect"  class="container container_24">
	<div class="grid_6 lable-right">
		<img alt="" src="${resource_path}/resource/openLayersMap/images/blueBubble/default.png">
		<input type="radio" name="icon" value="/resource/openLayersMap/images/blueBubble" style="margin-right:7px;" />
	</div>
	
	<div class="grid_6 lable-right">
		<img alt="" src="${resource_path}/resource/openLayersMap/images/greenBubble/default.png">
		<input type="radio" name="icon" value="/resource/openLayersMap/images/greenBubble" style="margin-right:7px;" />
	</div>
	
	<div class="grid_6 lable-right">
		<img alt="" src="${resource_path}/resource/openLayersMap/images/redBubble/default.png">
		<input type="radio" name="icon" value="/resource/openLayersMap/images/redBubble" style="margin-right:7px;" />
	</div>
	
	<div class="grid_6 lable-right">
		<img alt="" src="${resource_path}/resource/openLayersMap/images/yellowBubble/default.png">
		<input type="radio" name="icon" value="/resource/openLayersMap/images/yellowBubble" style="margin-right:7px;" />
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		$("#iconSelect input").each(function(){
			$(this).click(function(){
				$("#function-iconUrl").val($(this).val());
		   		$("#function-iconUrlHidden").attr("value",$(this).val());
				$("#iconDialog").dialog("close");
			})
		})
	})
</script>