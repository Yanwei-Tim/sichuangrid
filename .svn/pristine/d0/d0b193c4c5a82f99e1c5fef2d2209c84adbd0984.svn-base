<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>

<table class="tablelist" style="width:795px">
	<tr>
		<td class="title"><label>所属区域：</label></td>
		<td class="content" colspan="3">${primaryOrgVo.org.orgName}</td>
	</tr>
	<tr>
	<c:choose>
	<c:when test="${teamTypeName=='MASSEDUTY_TYPE'&&primaryOrgVo.isSynchronize>0}">
		<td class="title"><label>组织类别：</label></td>
		<td class="content"><span>平安志愿者</span></td>
	</c:when>
	<c:when test="${teamTypeName!='' }">
		<td class="title"><label>组织类别：</label></td>
		<td class="content"><span id="teamtypeId"><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@${teamTypeName}" defaultValue="${primaryOrgVo.teamType.id}" /></span></td>
		<s:if test='primaryOrgVo.departmentType.id!=null'>
		<td class="content">
		<span id="leaderId"><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@LEADERGROUP_TYPE" defaultValue="${primaryOrgVo.departmentType.id}" /></span>
		<span id="manageId"><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@MANAGEMENT_TYPE" defaultValue="${primaryOrgVo.departmentType.id}" /></span>
		</td>
		</s:if>
	</c:when>
	</c:choose>
	</tr>
	<tr>
		<td class="title"><label>组织名称：</label></td>
		<td class="content" colspan="3" <c:if test="${teamTypeName=='' }"></c:if>><span>${primaryOrgVo.detailName}</span></td>
	</tr>
	<!-- 
	<tr>
		<td class="title"><label>详细名称：</label></td>
		<td class="content" colspan="3"><span>${primaryOrgVo.detailName}</span></td>
	</tr>
	 -->
	 <c:if test="${teamTypeName=='MASSEDUTY_TYPE'}">
	 	<tr>
			<td class="title"><label>业务指导部门：</label></td>
			<td class="content" colspan="3"><span>${primaryOrgVo.guidanceDepartment}</span></td>
		</tr>
	 	<tr>
			<td class="title"><label>主要职能：</label></td>
			<td class="content" colspan="3"><span>${primaryOrgVo.mainFunction}</span></td>
		</tr>
	 </c:if>
	<tr>
		<td class="title"><label>备注：</label></td>
		<td class="content" colspan="3"><span>${primaryOrgVo.remark}</span></td>
	</tr>
</table>


<div id="viewPrimaryOrg" style="width:800px">
	<div id=tabs>
		<ul>
		<%--
			<li><a href='${path}/baseinfo/primaryOrgManage/viewPrimaryOrg.action?dailogName=primaryOrgDialog&primaryOrg.id=${primaryOrg.id}'>组织信息</a></li>
   --%>`
   
   <!-- 
			<s:if test="teamTypeName=='MASSEDUTY_TYPE'&&primaryOrgVo.isSynchronize>0">
				<li><a href='${path}/baseinfo/primaryOrgMemberManage/dispatchByEncrypt.action?mode=view&dailogName=primaryOrgDialog&internalId=<s:property value="@com.tianque.domain.property.BasicOrgType@VOLUNTARY_TEAM"/>&primaryOrgMemberVo.primaryOrgId=${primaryOrgVo.encryptId}&primaryOrgMemberVo.isHaveJob=0'>现有成员</a></li>
				<li><a href='$${path}/baseinfo/primaryOrgMemberManage/dispatchByEncrypt.action?mode=view&dailogName=primaryOrgDialog&internalId=<s:property value="@com.tianque.domain.property.BasicOrgType@VOLUNTARY_TEAM"/>&primaryOrgMemberVo.primaryOrgId=${primaryOrgVo.encryptId}&primaryOrgMemberVo.isHaveJob=1'>卸任成员</a></li>
			</s:if>
			<s:else>         
				<li><a href='${path}/primaryOrg/primaryMembersManage/dispatch.action?mode=primaryOrgMemberList&dailogName=primaryOrgDialog&internalId=${param.internalId}&primaryMemberVo.primaryOrgId=${primaryOrgVo.encryptId}&primaryMemberVo.isHaveJob=0'>现有成员</a></li>
				<li><a href='${path}/primaryOrg/primaryMembersManage/dispatch.action?mode=primaryOrgMemberList&dailogName=primaryOrgDialog&internalId=${param.internalId}&primaryMemberVo.primaryOrgId=${primaryOrgVo.encryptId}&primaryMemberVo.isHaveJob=1'>卸任成员</a></li>
				
			</s:else> -->
				<li id="liHava"><a id="hava"  href='${path}/primaryOrg/primaryMembersManage/dispatch.action?mode=primaryOrgMemberList&primaryMemberVo.primaryOrgId=${primaryOrg.id}&internalId=${param.internalId}&primaryMemberVo.org.id=${primaryOrg.org.id}&primaryMemberVo.isHaveJob=0&name=${param.name}&primaryMemberVo.isFourLevelPlatform=0&primaryMemberVo.isOperator=${mode}'>现有成员</a></li>
				<li><a id="nothava" href='${path}/primaryOrg/primaryMembersManage/dispatch.action?mode=primaryOrgMemberList&primaryMemberVo.primaryOrgId=${primaryOrg.id}&internalId=${param.internalId}&primaryMemberVo.org.id=${primaryOrg.org.id}&primaryMemberVo.isHaveJob=1&name=${param.name}&primaryMemberVo.isFourLevelPlatform=0&primaryMemberVo.isOperator=${mode}'>卸任成员</a></li>
		</ul>
	</div>
</div>
<script type="text/javascript">
	$(function() {
		$( "#tabs").tabs({cache:false});
	});
	change();
	function change(){
		var checkText=$("#teamtypeId").text();
		if(checkText=="专项工作领导小组"){
			$("#manageId").hide();
			$("#leaderId").show();
		}else if(checkText=="综治办"){
			$("#manageId").show();
			$("#leaderId").hide();
		}else{
			$("#manageId").hide();
			$("#leaderId").hide();
		}
	}
</script>