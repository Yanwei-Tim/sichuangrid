<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>    
<div class="center-left">
	<div id="emergencyDisposalTabs" class="incidentSidebar">
	<ul>
		<li><a href="/incident/emergencyDisposal/findHandleIncidentsList.action?incidents.status=<s:property value="@com.tianque.incident.vo.IncidentConstants@HANDLING"/>">处置中</a></li>
	</ul>
</div>
</div>

<div class="center-right">
</div>
<script>
	var centerHeight=$(".ui-layout-center").outerHeight();
	$(".center-left").height(centerHeight-25);
	$(function(){
		$("#emergencyDisposalTabs").tabs();
	});
</script>