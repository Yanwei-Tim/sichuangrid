<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>

<table  border="1" class="tablelist">

	<tr>
    	<td class="title"><label>所属网格</label></td>
    	<td colspan="3" class="content" id="serviceProjectOrgName">${serviceProject.organization.orgName}</td>
    	
	</tr>
	<tr>
		<td class="title"><label>项目名称</label></td>
    	<td class="content"><span>${serviceProject.projectName}</span></td>
    	<td class="title"><label>拟认领数</label></td>
    	<td class="content"><span>${serviceProject.claimPlans}</span></td>
    	
	</tr>
	<tr>
    	<td class="title"><label>项目内容</label></td>
    	<td  colspan="3" class="content"><span>${serviceProject.projectContent}</span></td>
	</tr>
	<tr>
    	<td class="title"><label>联系人</label></td>
    	<td class="content"><span>${serviceProject.contractor}</span></td>
    	<td class="title"><label>联系电话</label></td>
    	<td class="content"><span>${serviceProject.telephone}</span></td>
    	
	</tr>
	<tr>
    	<td class="title"><label>备注</label></td>
    	<td  colspan="3" class="content"><span>${serviceProject.remark}</span></td>
	</tr>

</table>