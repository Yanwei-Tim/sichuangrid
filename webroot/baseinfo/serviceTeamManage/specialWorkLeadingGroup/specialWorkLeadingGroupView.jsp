<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div style="display: none;">
mode:<input id="mode" type="text" name="mode" value="${mode }" /> 
orgId:<input id="autonomyTeamOrgId" type="text"	name="serviceManageTeam.organization.id" value="<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().id"/>" />
teamId<input type="text" name="serviceManageTeam.id" value="${serviceManageTeam.id }" /> 
</div>
<table width="200" class="tablelist">
	<tr>
	    <td class="title"><label>所属网格：</label></td>
	    <td class="content"><span>${serviceManageTeam.organization.orgName}<!--<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().orgName"/>--></span></td>
	    <td class="title"><label>小组名称：</label></td>
	    <td class="content"><span>${serviceManageTeam.name}</span></td>
	</tr>
   	<tr>
	    <td class="title"><label>小组类别：</label></td>
	    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@LEADERGROUP_TYPE" defaultValue="${serviceManageTeam.teamType.id}" /></span></td>
	    <td class="title"><label>成立时间：</label></td>
	    <td class="content"><span><s:date name="serviceManageTeam.establishDate" format="yyyy-MM-dd" /></span></td>
	</tr>
	<tr>
	    <td class="title"><label>简介：</label></td>
	    <td class="content"><span>${serviceManageTeam.remark}</span></td>
	</tr>
</table>
