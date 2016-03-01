<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container container_24">
	<form id="searchIssueSkiprule_form" method="post" action="" >
		<input id="searchIssueSkipruleVoOrgId" name="searchIssueSkipruleVo.orgId" type="hidden" value="<s:property value='@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId()'/>">
		
		<div class="grid_4 lable-right">
			<label class="form-lbl">事件类型：</label>
		</div>
		<div class="grid_20 form-left">
			<select id="searchIssueTypeDomainId" name="searchIssueSkipruleVo.issueTypeDomainId" class="form-select">
		   		<pop:IssueTypeReleationSelectTag name=
		   		"contradiction,resolveTheContradictions,securityPrecautions,specialPopulations,socialConditions,policiesAndLaws,emergencies,otherManage" 
		   		reletionId="searchIssueTypeId" isOperateDiv="0" id="searchIssueTypeDomainId" />
			</select>
		</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lbl">事件子类：</label>
		</div>
		<div class="grid_20 form-left">
			<select id="searchIssueTypeId" name="searchIssueSkipruleVo.issueTypeId" class="form-select" disabled></select>
		</div>
		<div class="grid_4 lable-right">
		</div>
		<div id="issueUrgentLevelsDiv" class="grid_18 form-left">
		<c:forEach items="${propertyDicts}" var="propertyDict">
			<input type="checkbox" name="searchIssueSkipruleVo.issueUrgentLevels" value="${propertyDict.id}" />重大紧急等级-${propertyDict.displayName}
		</c:forEach>
		
		</div>
   	</form>
</div>

<script type="text/javascript">


</script>