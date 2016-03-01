<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />
<s:action name="getFullOrgById" var="getFullOrgById" executeResult="false" namespace="/sysadmin/orgManage" >
	<s:param name="organization.id" value="#loginAction.user.organization.id"></s:param>
</s:action>
<style>
#myAuditDelayCount {border-radius: 5px;cursor: pointer;position: absolute;color: #fff;font: bold 11px/1.5em '';top: 6px;right: 9px;background: #F68300;padding: 0 5px;z-index: 1;
}
</style>
<div id="tabList">
	<ul>
		<li><a href="${path}/fourTeamsManage/fourTeamsIssueManage/gridServiceTeam/gridFourTeamsIssueForViewList.jsp?viewType=need&orgLevel=<s:property value='#getFullOrgById.organization.orgLevel.internalId'/>&keyId=<s:property value='#getFullOrgById.organization.id'/>&functionalOrgType=<s:property value='#getFullOrgById.organization.functionalOrgType.id'/>">待办</a> </li>
		<!--<li id="checkPending"><a href="${path}/issue/issueManage/issueForViewList.jsp?viewType=checkPending&orgLevel=<s:property value='#getFullOrgById.organization.orgLevel.internalId'/>&keyId=<s:property value='#getFullOrgById.organization.id'/>&functionalOrgType=<s:property value='#getFullOrgById.organization.functionalOrgType.id'/>">待审核&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label id="myAuditDelayCount"></label></a></li>
		-->
		<li><a href="${path}/fourTeamsManage/fourTeamsIssueManage/gridServiceTeam/gridFourTeamsIssueForViewList.jsp?viewType=done&orgLevel=<s:property value='#getFullOrgById.organization.orgLevel.internalId'/>&keyId=<s:property value='#getFullOrgById.organization.id'/>&functionalOrgType=<s:property value='#getFullOrgById.organization.functionalOrgType.id'/>">已办</a></li>
		<li><a href="${path}/fourTeamsManage/fourTeamsIssueManage/gridServiceTeam/gridFourTeamsIssueForViewList.jsp?viewType=completed&orgLevel=<s:property value='#getFullOrgById.organization.orgLevel.internalId'/>&keyId=<s:property value='#getFullOrgById.organization.id'/>&functionalOrgType=<s:property value='#getFullOrgById.organization.functionalOrgType.id'/>">已办结</a></li>
		<!--<li><a href="${path}/issue/issueManage/issueForViewList.jsp?viewType=submit&orgLevel=<s:property value='#getFullOrgById.organization.orgLevel.internalId'/>&keyId=<s:property value='#getFullOrgById.organization.id'/>&functionalOrgType=<s:property value='#getFullOrgById.organization.functionalOrgType.id'/>">上报</a></li>
		<li><a href="${path}/issue/issueManage/issueForViewList.jsp?viewType=assign&orgLevel=<s:property value='#getFullOrgById.organization.orgLevel.internalId'/>&keyId=<s:property value='#getFullOrgById.organization.id'/>&functionalOrgType=<s:property value='#getFullOrgById.organization.functionalOrgType.id'/>">上级交办</a></li>
		<li><a href="${path}/issue/issueManage/issueForViewList.jsp?viewType=skip&orgLevel=<s:property value='#getFullOrgById.organization.orgLevel.internalId'/>&keyId=<s:property value='#getFullOrgById.organization.id'/>&functionalOrgType=<s:property value='#getFullOrgById.organization.functionalOrgType.id'/>">越级</a>--></li>
	</ul>
 	</div>
<script>
$(function() {
	$( "#tabList" ).tabs({selected: 0 ,beforeLoad:function(){
		$( "#tabList" ).find(".ui-tabs-panel").empty()
	}});
});
</script>