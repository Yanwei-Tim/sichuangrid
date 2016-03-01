<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="schoolPrint" >
<style type="text/css">
* {
	padding: 0;
	margin: 0;
	font-size: 12px;
	font-family: Arial;
}

.mod_print {
	margin: 0 auto;
	width: 190mm;
	height: 97mm;
}

.mod_print h1 {
	font-size: 34px;
	text-align: center;
	line-height: 3em;
}

.mod_print .b table {
	width: 100%;
	line-height: 2em;
	border-collapse: collapse;
}

.mod_print .b table td,.mod_print .b table th {
	padding: 0.3em 0.5em;
	border: 1px solid #333;
}

.mod_print .b table th {
	text-align: right;
}

.mod_print .b table .th {
	text-align: center;
	font-weight: bold;
}
</style>
<input id="orgId" type="hidden" name="orgId" value="${school.organization.id}">
<div class="mod_print">
<h1>${school.chineseName}信息</h1>
<div class="b">
<table>
	<tr>
		<th width="17%" class="th">所属网格</th>
		<td colspan="5" class="heightAuto"><label id="orgName"></label></td>
	</tr>
	<tr>
		<th class="th">学校名称</th>
		<td colspan="5">${school.chineseName}</td>
	</tr>
	<tr>
		<th class="th">英文名称</th>
		<td colspan="3">${school.englishName}</td>

		<td class="th">学校性质</td>
		<td><input name="school.kind.id" id="schoolKindId"
			value="${school.kind.id}" type="hidden"> <label
			id="schoolKind"></label></td>
	</tr>
	<tr>
		<th class="th">学校地址</th>
		<td colspan="3">${school.address}</td>

		<td class="th">学校类型</td>
		<td><input name="school.type.id" id="schoolTypeId"
			value="${school.type.id}" type="hidden"><label
			id="schoolType"></label></td>
	</tr>

	<tr>
		<th class="th">学校网址</th>
		<td colspan="3">${school.webSite}</td>

		<td class="th">在校人数</td>
		<td>${school.atSchoolHeadcount}</td>
	</tr>

	<tr>
		<th class="th">校长</th>
		<td width="19%">${school.personLiable}</td>
		<td width="12%" class="th">传真</td>
		<td width="23%">${school.fax}</td>
		<td width="13%" class="th">电子邮箱</td>
		<td width="16%">${school.email}</td>
	</tr>
	<tr>
		<th class="th">法制副校长</th>
		<td width="19%">${school.president}</td>
		<td width="12%" class="th">联系电话</td>
		<td width="23%">${school.personLiableTelephone}</td>
		<td width="13%" class="th">联系手机</td>
		<td width="16%">${school.personLiableMobileNumber}</td>
	</tr>
	<tr>
		<th class="th">备 注</th>
		<td colspan="5">
		<p>${school.remark}</p>
		</td>
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
			"organization.id":$("#orgId").val()
		},
		success:function(responseData){
			$("#orgName").html(responseData);
		}
	});

	if($("#schoolKindId").val()!=null&&$("#schoolKindId").val()!=""){
		$.ajax({
			async: false,
			url: "${path }/sysadmin/propertyManage/getPropertyDictById.action",
			data:{
				"propertyDomain.domainName":"学校性质",
				"propertyDict.id":$("#schoolKindId").val()
			},
			success:function(responseData){
				$("#schoolKind").html(responseData.displayName);
			}
		});
	}
	if($("#schoolTypeId").val()!=null&&$("#schoolTypeId").val()!=""){
	$.ajax({
		async: false,
		url: "${path }/sysadmin/propertyManage/getPropertyDictById.action",
		data:{
			"propertyDomain.domainName":"学校类型",
			"propertyDict.id":$("#schoolTypeId").val()
		},
		success:function(responseData){
			$("#schoolType").html(responseData.displayName);
		}
	});
	}
});
</script>