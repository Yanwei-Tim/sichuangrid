<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="overseaPersonnelPrint" title="境外人员维护"
	class="container container_24"><input id="organizationId"
	type="hidden" name="orgId" value="${orgId}" />
<style type="text/css">
.mod_print {
	margin: 0 auto;
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

<div class="mod_print">
<h1 class="th">${overseaPersonnel.name}信息</h1>
<div class="b">
<table>
	<tr>
		<th width="17%" class="th">所属网格</th>
		<td colspan="5" class="heightAuto"><label
			id="overseaPersonnelOrgName"></label></td>
	</tr>
	<tr>
		<th class="th">姓名</th>
		<td width="19%">${overseaPersonnel.name}</td>
		<td width="12%" class="th">护照号码</td>
		<td width="23%">${overseaPersonnel.idCardNo}</td>
		<td width="13%" class="th">性 别</td>
		<td width="16%"><input id="overseaPersonnel.gender.id"
			type="hidden" value="${overseaPersonnel.gender.id}"><label
			id="gender"></label></td>
	</tr>

	<tr>
		<th class="th">国籍</th>
		<td colspan="3">${overseaPersonnel.nationality}</td>
		<td class="th">证件种类</td>
		<td><input id="overseaPersonnel.certificateType.id" type="hidden"
			value="${overseaPersonnel.certificateType.id}"><label
			id="certificateType"></label></td>
	</tr>

	<tr>
		<th class="th">户籍详址</th>
		<td colspan="3">${overseaPersonnel.nativePlaceAddress}</td>
		<td class="th">出生日期</td>
		<td><s:date name="overseaPersonnel.birthday" format="yyyy-MM-dd" /></td>
	</tr>
	<tr>
		<th class="th">工作单位或就读学校</th>
		<td colspan="3">${overseaPersonnel.workUnit}</td>
		<td class="th">联系手机</td>
		<td>${overseaPersonnel.mobileNumber}</td>
	</tr>
	<tr>
		<th class="th">常住地址</th>
		<td colspan="3">${overseaPersonnel.currentlyAddress}</td>
		<td class="th">邮箱</td>
		<td>${overseaPersonnel.mail}</td>
	</tr>

	<tr>
		<th class="th">抵达时间</th>
		<td colspan="3"><s:date name="overseaPersonnel.arrivalTime" format="yyyy-MM-dd" /></td>
		<td class="th">联系电话</td>
		<td>${overseaPersonnel.telephone}</td>
	</tr>

	<tr>
		<th class="th">离开时间</th>
		<td colspan="5"><s:date name="overseaPersonnel.leaveTime" format="yyyy-MM-dd" /></td>
	</tr>

	<tr>
		<th class="th">关注原因</th>
		<td colspan="5">${overseaPersonnel.attentionReason}</td>
	</tr>
	<tr>
		<th class="th">备 注</th>
		<td colspan="5">${overseaPersonnel.remark}</td>
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
			$("#overseaPersonnelOrgName").html(responseData);
		}
	});
	$.ajax({
		async: false,
		url: "${path }/sysadmin/propertyManage/getPropertyDictById.action",
		data:{
			"propertyDomain.domainName":"性别",
			"propertyDict.id":$("#overseaPersonnel\\.gender\\.id").val()
		},
		success:function(responseData){
			$("#gender").html(responseData.displayName);
		}
	});

	$.ajax({
		async: false,
		url: "${path }/sysadmin/propertyManage/getPropertyDictById.action",
		data:{
			"propertyDomain.domainName":"证件种类",
			"propertyDict.id":$("#overseaPersonnel\\.certificateType\\.id").val()
		},
		success:function(responseData){
			$("#certificateType").html(responseData.displayName);
		}
	});

});
</script>
