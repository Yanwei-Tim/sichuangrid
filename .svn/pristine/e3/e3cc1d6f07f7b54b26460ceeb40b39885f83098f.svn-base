<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div style="display: none;">
mode:<input id="mode" type="hidden" name="mode" value="${mode }" /> 
orgId:<input id="primaryOrgOrgId" type="hidden" name="primaryOrg.org.id" value="<s:property value="@com.tianque.util.ThreadVariable@getOrganization().id"/>" />
teamId<input type="hidden" name="primaryOrg.id" value="${primaryOrg.id }" /> 
</div>
<table class="tablelist">
	<tr>
		<td class="title"><label>所属区域：</label></td>
		<td class="content" colspan="3">${primaryOrgVo.org.orgName}</td>
	</tr>
	<tr>
		<td class="title"><label>组织名称：</label></td>
		<td class="content" colspan="3"><span>${primaryOrgVo.teamName}</span></td>
	</tr>
	<tr>
		<td class="title"><label>详细名称：</label></td>
		<td class="content" colspan="3" ><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@PERMARY_ORGANIZATION_TYPE" defaultValue="${primaryOrgVo.teamType.id}" /></span></td>
	</tr>
	<tr>
		<td class="title"><label>简介：</label></td>
		<td class="content" colspan="3"><span>${primaryOrgVo.remark}</span></td>
	</tr>
</table>
