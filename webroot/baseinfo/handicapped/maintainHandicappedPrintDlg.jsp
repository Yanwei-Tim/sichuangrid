<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="handicappedPrint">
 <style type="text/css">
	.mod_print{
			margin:0 auto;
		}
		.mod_print h1{
			font-size:30px;
			text-align:center;
			line-height:2em;
		}
		.mod_print .b table{
			width:100%;
			line-height:2em;
			border-collapse:collapse;
		}
		.mod_print .b table td,
		.mod_print .b table th{
			padding:0.2em 0.3em;
			border:1px solid #333;
		}
		.mod_print .b table th{
			text-align:right;
		}
		.mod_print .b table .th{
			text-align:center;
			font-weight:bold;
		}
	</style>
<input id="organizationId" type="hidden" name="organizationId"
	value="${organizationId}" />
<div class="mod_print">
<h1>${handicapped.name}</h1>
<div class="b">
<table>
	<tr>
		<th width="17%" class="th">所属网格</th>
		<td colspan="5"><label id="idleYouthOrgName"></label></td>
	</tr>
	<tr>
		<th class="th">姓名</th>
		<td width="19%">${handicapped.name}</td>
		<td width="12%" class="th">身份证号码</td>
		<td width="23%">${handicapped.idCardNo}</td>
		<td width="13%" class="th">性 别</td>
		<td width="16%"><label id="gender"></label><input id="idleYouth.gender.id"
			type="hidden" value="${handicapped.gender.id}"></input></td>
	</tr>

	
	<tr>
		<td class="th">出生日期</td>
		<td><fmt:formatDate value="${handicapped.birthday}" type="date" pattern="yyyy-MM-dd" /></td>	
		<th class="th">联系手机</th>
		<td width="19%">${handicapped.mobileNumber}</td>
		<td width="12%" class="th">联系电话</td>
		<td width="23%">${handicapped.telephone}</td>
	</tr>
	<tr>
		<th class="th">残疾证号</th>
		<td width="19%">${handicapped.disabilityCardNo}</td>
		<td width="12%" class="th">残疾状况</td>
		<td width="23%"><label id="disability"></label><input id="handicapped.disability.id"
			type="hidden" value="${handicapped.disability.id}"></input></td>
		<td class="th">劳动能力</td>
		<td><label id="workProfile"></label><input id="handicapped.workProfile.id"
			type="hidden" value="${handicapped.workProfile.id}"></input></td>
	</tr>
	<tr>
		<th class="th">常住地址</th>
		<td colspan="3">${handicapped.presentAddress}</td>
		<td class="th">技能特长</td>
		<td><label id="skillProfile"></label><input id="handicapped.skillProfile.id"
			type="hidden" value="${handicapped.skillProfile.id}"></input></td>
	</tr>
	<tr >
		<th class="th">备注</th>
		<td colspan="5">${handicapped.remark}</td>
	</tr>
</table>
</div>
</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$.ajax({
		async: false,
		url: "${path }/sysadmin/orgManage/getOrgRelativePath.action",
		data:{
			"organization.id":$("#organizationId").val()
		},
		success:function(responseData){
			$("#idleYouthOrgName").html(responseData);
		}
	});

	$.ajax({
		async: false,
		url: "${path }/sysadmin/propertyManage/getPropertyDictById.action",
		data:{
			"propertyDomain.domainName":"性别",
			"propertyDict.id":$("#idleYouth\\.gender\\.id").val()
		},
		success:function(responseData){
			$("#gender").html(responseData.displayName);
		}
	});

	$.ajax({
		async: false,
		url: "${path }/sysadmin/propertyManage/getPropertyDictById.action",
		data:{
			"propertyDomain.domainName":"残疾状况",
			"propertyDict.id":$("#handicapped\\.disability\\.id").val()==""?'-1':$("#handicapped\\.disability\\.id").val()
		},
		success:function(responseData){
			$("#disability").html(responseData.displayName);
		}
	});

	$.ajax({
		async: false,
		url: "${path }/sysadmin/propertyManage/getPropertyDictById.action",
		data:{
			"propertyDomain.domainName":"劳动能力",
			"propertyDict.id":$("#handicapped\\.workProfile\\.id").val()==""?'-1':$("#handicapped\\.workProfile\\.id").val()
		},
		success:function(responseData){
			$("#workProfile").html(responseData.displayName);
		}
	});

	$.ajax({
		async: false,
		url: "${path }/sysadmin/propertyManage/getPropertyDictById.action",
		data:{
			"propertyDomain.domainName":"技能特长",
			"propertyDict.id":$("#handicapped\\.skillProfile\\.id").val()==""?'-1':$("#handicapped\\.skillProfile\\.id").val()
		},
		success:function(responseData){
			$("#skillProfile").html(responseData.displayName);
		}
	});

});
</script>