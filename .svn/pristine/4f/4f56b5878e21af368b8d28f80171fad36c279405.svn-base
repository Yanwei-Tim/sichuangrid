<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<jsp:include page="/common/orgSelectedComponent.jsp"/>
<div class="container container_24">
	<input type="hidden" name="orgId" id="orgIdForParty" value="${orgId}" class="form-txt" />
	<s:action name="findPartyOrgInfo" namespace="/baseinfo/partyOrgInfoManage" executeResult="true"></s:action>
</div>
<div id="tabs" class="party_info">
	<ul>
		<li><a id="partyInfo" href="${path}/baseinfo/partyConstruction/currentParty/partyMemberInfoList.jsp?orgId=${orgId}">党员信息</a> </li>
		<li><a id="partyOrganizationActiveRecord" href="${path}/baseinfo/partyConstruction/currentParty/partyOrgActivityList.jsp?orgId=${orgId}">区域联建情况</a></li>
		<li><a id="partyOrganizationSource" href="${path}/">区域内主要党组织资源</a></li>
	</ul>
</div>
<script>
$(function() {
	$("#orgIdForParty").val(getCurrentOrgId());
	$("#partyInfo").attr("href","${path}/baseinfo/partyConstruction/currentParty/partyMemberInfoList.jsp?orgId="+getCurrentOrgId());
	$("#partyOrganizationActiveRecord").attr("href","${path}/baseinfo/partyConstruction/currentParty/partyOrgActivityList.jsp?orgId="+getCurrentOrgId());
	$( "#tabs" ).tabs({cache:true});
});
</script>