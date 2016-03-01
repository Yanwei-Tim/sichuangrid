<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />
<div id="eventHandlingProcess" style="overflow-y:auto;">
     <img src="${resource_path}/resource/system/images/issue/<s:text name="project.issue.processimage.url"/>lc.jpg"/>
</div>
<script>
	$(function(){
		$("#eventHandlingProcess").height($(".ui-layout-center").height()-$(".submenu").height());
		$.loadingComp("close");
	});
</script>