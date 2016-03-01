<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div style="display: none;">
mode:<input id="mode" type="text" name="mode" value="${mode }" /> 
orgId:<input id="orgId" type="text"	name="serviceManageTeam.organization.id" value="<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().id"/>" />
teamId:<input type="text" name="serviceManageTeam.id" value="${serviceManageTeam.id }" /> 
</div>
<table width="200" class="tablelist">
	<tr>
	    <td class="title"><label>团队名称：</label></td>
	    <td class="content"><span>${serviceManageTeam.name}</span></td>
	</tr>
   	<tr>
	    <td class="title"><label>成立时间：</label></td>
	    <td class="content"><span><s:date name="serviceManageTeam.establishDate" format="yyyy-MM-dd" /></span></td>
	</tr>
	<tr>
	    <td class="title"><label>团队简介：</label></td>
	    <td class="content"><span>${serviceManageTeam.remark}</span></td>
	</tr>
</table>
