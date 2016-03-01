<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />
<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />
<s:action name="getFullOrgById" var="getFullOrgById" executeResult="false" namespace="/sysadmin/orgManage" >
	<s:param name="organization.id" value="#loginAction.user.organization.id"></s:param>
</s:action>
<style>
.select_ {width: 150px;}
._select {width: 70px;}
.input_ {width: 77px;}
</style>

<div id="tabList">
	<ul>
		<li><a href="${path}/account/query/comprehensivePeopleQuery.jsp?orgLevel=<s:property value='#getFullOrgById.organization.orgLevel.internalId'/>&keyId=<s:property value='#getFullOrgById.organization.id'/>&functionalOrgType=<s:property value='#getFullOrgById.organization.functionalOrgType.id'/>">民生台账&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
		<li><a href="${path}/account/query/comprehensivePoorPeopleQuery.jsp?orgLevel=<s:property value='#getFullOrgById.organization.orgLevel.internalId'/>&keyId=<s:property value='#getFullOrgById.organization.id'/>&functionalOrgType=<s:property value='#getFullOrgById.organization.functionalOrgType.id'/>">困难台账&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
		<li><a href="${path}/account/query/comprehensiveSteadyWorkQuery.jsp?orgLevel=<s:property value='#getFullOrgById.organization.orgLevel.internalId'/>&keyId=<s:property value='#getFullOrgById.organization.id'/>&functionalOrgType=<s:property value='#getFullOrgById.organization.functionalOrgType.id'/>">稳定台账&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
	</ul>
 </div>
<script type="text/javascript">

$(function() {
	var isAuditDelay= '<s:property value="#parameters.isAuditDelay"/>';
	if(isAuditDelay!='' && isAuditDelay!=null){
		$( "#tabList" ).tabs({ selected: isAuditDelay ,beforeLoad:function(){
			$( "#tabList" ).find(".ui-tabs-panel").empty();
		}});
	}else{
		$( "#tabList" ).tabs({ selected: 0 ,beforeLoad:function(){
			$( "#tabList" ).find(".ui-tabs-panel").empty();
		}});
	}
});
</script>