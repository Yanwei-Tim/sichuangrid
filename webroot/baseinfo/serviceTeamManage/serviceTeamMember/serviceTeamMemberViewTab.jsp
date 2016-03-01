<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<table class="tablelist">
	<tr>
		<td class="title"><label>所属网格：</label></td>
		<td class="content" colspan="3">${serviceTeamMemberBase.organization.orgName}</td>
	</tr>
	<tr>
		<td class="title"><label>姓名：</label></td>
		<td class="content"><span>${serviceTeamMemberBase.name}</span></td>
		<td class="title"><label>性别：</label></td>
		<td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="${serviceTeamMemberBase.gender.id}" /></span></td>
	</tr>
	<tr>
		<td class="title"><label>身份证号码：</label></td>
		<td class="content" colspan="3" ><span>${serviceTeamMemberBase.idCardNo}</span></td>
	</tr>
	<tr>
		<td class="title"><label>联系手机：</label></td>
		<td class="content"><span>${serviceTeamMemberBase.mobile}</span></td>
		<td class="title"><label>固定电话：</label></td>
		<td class="content"><span>${serviceTeamMemberBase.homePhone}</span></td>
	</tr>
	<tr>
		<td class="title"><label>备注：</label></td>
		<td class="content" colspan="3" rowspan="3"><span>${serviceTeamMemberBase.remark}</span></td>
	</tr>
</table>
