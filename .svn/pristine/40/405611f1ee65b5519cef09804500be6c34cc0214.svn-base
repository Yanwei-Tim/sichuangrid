<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>

<div id="tabs">
	<ul>
		<li><a href="${path}/baseinfo/serviceTeamMemberManage/serviceTeamMemberDispatch.action?mode=view&serviceTeamMemberBase.id=<s:property value="#parameters.baseId[0]"/>">成员基本信息</a></li>
		<li><a href="${path}/baseinfo/serviceTeamManage/serviceTeam/currentServiceTeamsViewTab.jsp?baseId=<s:property value="#parameters.baseId[0]"/>">现有团队信息</a></li>
		<li><a href="${path}/baseinfo/serviceTeamManage/serviceTeam/priorServiceTeamsViewTab.jsp?baseId=<s:property value="#parameters.baseId[0]"/>">曾经团队信息</a></li>
	</ul>
</div>
<script type="text/javascript">
	$(function() {
		$( "#tabs").tabs({cache:false});
	});
</script>
