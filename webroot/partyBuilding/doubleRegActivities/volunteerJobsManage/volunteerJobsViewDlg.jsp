<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>

<table  border="1" class="tablelist">

	<tr>
		<td class="title"><label>所属网格</label></td>
    	<td colspan="3" class="content" id="volunteerJobsOrgName">${volunteerJobs.organization.orgName}</td>
	</tr>
	<tr>
		<td class="title"><label>志愿者岗位</label></td>
    	<td class="content"><span>${volunteerJobs.name}</span></td>
    	<td class="title"><label>拟认领数</label></td>
    	<td class="content"><span>${volunteerJobs.claimPlans}</span></td>
	</tr>
	<tr>
		<td class="title"><label>专长要求</label></td>
    	<td colspan="3"  class="content"><span>${volunteerJobs.content}</span></td>
	</tr>
	<tr>
    	<td class="title"><label>联系人</label></td>
    	<td class="content"><span>${volunteerJobs.contractor}</span></td>
    	<td class="title"><label>联系电话</label></td>
    	<td class="content"><span>${volunteerJobs.telephone}</span></td>
	</tr>
	<tr>
    	<td class="title"><label>备注</label></td>
    	<td colspan="3"  class="content"><span>${volunteerJobs.remark}</span></td>
	</tr>

</table>