<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>

<table  border="1" class="tablelist">

	<tr>
    	<td class="title"><label>所属网格</label></td>
    	<td class="content" colspan="3"><span>${fourLevelPlatform.organization.orgName}</span></td>
	</tr>
	<tr>
    	<td class="title"><label>平台名称</label></td>
    	<td class="content"><span>${fourLevelPlatform.name}</span></td>
    	<td class="title"><label>组织类型</label></td>
    	<td class="content"><span>${fourLevelPlatform.teamTypeName}</span></td>
	</tr>
	<s:if test="fourLevelPlatform.organization.orgLevel.internalId == @com.tianque.domain.property.OrganizationLevel@DISTRICT">
	<tr>
    	<td class="title"><label>管理部门</label></td>
    	<td class="content" colspan="3"><span>${fourLevelPlatform.supervisorydepartment}</span></td>
	</tr>
	</s:if>
	<tr>
    	<td class="title"><label>备注</label></td>
    	<td class="content" colspan="3">${fourLevelPlatform.remark}</td>
	</tr>

</table>
<div id="viewPrimaryOrg" style="width:800px">
	<div id=tabs>
		<ul>
			<li id="liHava"><a id="hava"  href='${path}/primaryOrg/primaryMembersManage/dispatch.action?mode=primaryOrgMemberList&primaryMemberVo.primaryOrgId=${fourLevelPlatform.id}&primaryMemberVo.org.id=${fourlevelplatformDialog.organization.id}&primaryMemberVo.isHaveJob=0&name=${param.name}&primaryMemberVo.isFourLevelPlatform=1&primaryMemberVo.isOperator=${mode}'>现有成员</a></li>
			<li><a id="nothava" href='${path}/primaryOrg/primaryMembersManage/dispatch.action?mode=primaryOrgMemberList&primaryMemberVo.primaryOrgId=${fourLevelPlatform.id}&primaryMemberVo.org.id=${fourlevelplatformDialog.organization.id}&primaryMemberVo.isHaveJob=1&name=${param.name}&primaryMemberVo.isFourLevelPlatform=1&primaryMemberVo.isOperator=${mode}'>卸任成员</a></li>
		</ul>
	</div>
</div>
<script type="text/javascript">
	$(function() {
		$( "#tabs").tabs({cache:false});
	});
</script>

