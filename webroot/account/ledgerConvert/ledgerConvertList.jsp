<%@	page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />
<s:action name="getFullOrgById" var="getFullOrgById" executeResult="false" namespace="/sysadmin/orgManage" >
	<s:param name="organization.id" value="#loginAction.user.organization.id"></s:param>
</s:action>

<style>
.issueCount {border-radius: 5px;cursor: pointer;position: absolute;color: #fff;font: bold 11px/1.5em '';top: 6px;right: 9px;background: #F68300;padding: 0 5px;z-index: 1;
}
</style>

<div id="tabList">
	<ul>
		<li><a href="${path}/account/ledgerConvert/needLedgerConvertList.jsp">待转事件&nbsp;&nbsp;&nbsp;&nbsp;<label id="needCount" class="issueCount"></label></a> </li>
		<li><a href="${path}/account/ledgerConvert/historyLedgerConvertList.jsp?viewType=history&orgLevel=<s:property value='#getFullOrgById.organization.orgLevel.internalId'/>&keyId=<s:property value='#getFullOrgById.organization.id'/>&functionalOrgType=<s:property value='#getFullOrgById.organization.functionalOrgType.id'/>">已转事件&nbsp;&nbsp;&nbsp;&nbsp;<label id="historyCount" class="issueCount"></label></a></li>
	</ul>
 	</div>

<script>
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