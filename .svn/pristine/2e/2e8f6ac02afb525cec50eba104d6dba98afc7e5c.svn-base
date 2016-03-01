<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="emergencyDisposalTabs" class="incidentSidebar">
	<ul>
		<li><a href="/incident/emergencyDisposal/findWarningIncidentsList.action?incidents.status=<s:property value="@com.tianque.incident.vo.IncidentConstants@EARLY_WARNING"/>">预警</a></li>
		<li><a href="/incident/emergencyDisposal/findHandleIncidentsList.action?incidents.status=<s:property value="@com.tianque.incident.vo.IncidentConstants@HANDLING"/>">处置中</a></li>
		<li><a href="/incident/caselibIndex.jsp">案例库</a></li>
	</ul>
</div>
<script>
$(function(){
	$("#emergencyDisposalTabs").tabs({
		cache:false,
		beforeLoad:function(){
			$.dialogLoading("open");
		},
		load:function(){
			$.dialogLoading("close");
		}
	});
});

</script>
