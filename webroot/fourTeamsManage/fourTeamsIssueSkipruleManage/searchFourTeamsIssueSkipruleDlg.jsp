<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div class="container container_24">
	<form id="searchFourTeamsIssueSkiprule_form" method="post" action="" >
		<input id="searchFourTeamsIssueSkipruleVoOrgId" name="searchFourTeamsIssueSkipruleVo.orgId" type="hidden" value="<s:property value='@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId()'/>">
		
		<div class="grid_4 lable-right">
			<label class="form-lbl">事件类型：</label>
		</div>
		<div class="grid_20 form-left">
			<select id="searchIssueTypeDomainId" name="searchFourTeamsIssueSkipruleVo.issueTypeDomainId" class="form-select">
		   		<pop:IssueTypeReleationSelectTag name=
		   		"resolveTheContradictions,securityPrecautions,emergencies,otherManage" 
		   		reletionId="searchIssueTypeId" isOperateDiv="0" id="searchIssueTypeDomainId" />
			</select>
		</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lbl">事件子类：</label>
		</div>
		<div class="grid_20 form-left">
			<select id="searchIssueTypeId" name="searchFourTeamsIssueSkipruleVo.issueTypeId" class="form-select" disabled></select>
		</div>
		<div class="grid_4 lable-right">
		</div>
		<div id="issueUrgentLevelsDiv" class="grid_18 form-left">
		<s:iterator value="propertyDicts" >
			<input type="checkbox" name="searchFourTeamsIssueSkipruleVo.issueUrgentLevels" value="<s:property value='id'/>" />重大紧急等级-<s:property value='displayName'/>
		</s:iterator>
		
		</div>
   	</form>
</div>

<script type="text/javascript">


</script>