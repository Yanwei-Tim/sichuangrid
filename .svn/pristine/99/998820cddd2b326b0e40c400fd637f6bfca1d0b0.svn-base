<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div style="display: none;">
mode:<input id="mode" type="hidden" name="mode" value="${mode }" /> 
orgId:<input id="serviceTeamOrgId" type="hidden" name="serviceManageTeam.organization.id" value="<s:property value="@com.tianque.util.ThreadVariable@getOrganization().id"/>" />
teamId<input type="hidden" name="serviceManageTeam.id" value="${serviceManageTeam.id }" /> 
</div>
<table class="tablelist">
	<tr>
		<td class="title"><label>所属网格：</label></td>
		<td class="content" colspan="3">${serviceManageTeam.organization.orgName}</td>
	</tr>
	<tr>
		<td class="title"><label>团队名称：</label></td>
		<td class="content" colspan="3"><span>${serviceManageTeam.name}</span></td>
	</tr>
	<tr>
		<td class="title"><label>所属团队：</label></td>
		<td class="content" colspan="3" ><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@TEAMCLAZZ" defaultValue="${serviceManageTeam.teamClazz.id}" /></span></td>
	</tr>
	<tr>
		<td class="title"><label>团队类别：</label></td>
		<td class="content" colspan="3" ><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@BASICORGTYPE" defaultValue="${serviceManageTeam.teamType.id}" /></span></td>
	</tr>
	<tr>
		<td class="title"><label>成立时间：</label></td>
		<td class="content" colspan="3"><span><s:date name="serviceManageTeam.establishDate" format="yyyy-MM-dd" /></span></td>
	</tr>
	<tr>
		<td class="title"><label>简介：</label></td>
		<td class="content" colspan="3"><span>${serviceManageTeam.remark}</span></td>
	</tr>
</table>
