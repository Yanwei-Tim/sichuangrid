<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="otherLocalePrint"  class="container container_24">
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
	height: 7mm;
}

.mod_print h1 {
	font-size: 30px;
	text-align: center;
	line-height: 2em;
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
<input id="organizationId" name="orgId" type="hidden" value="${organization.id}" />
<div class="mod_print" >
<h1>${otherLocale.name}信息</h1>
<div class="b">
<table>
	<tr>
		<th width="17%" class="th">所属网格</th>
		<td colspan="5" class="heightAuto"><label id="orgName"></label></td>
	</tr>
	<tr>
		<th width="17%" class="th">名称</th>
		<td colspan="5" class="heightAuto">${otherLocale.name}</td>
	</tr>
	
	<tr>
		<th class="th">地址</th>
		<td colspan="3">${otherLocale.address}</td>

		<td class="th">场所类型</td>
		<td><input id="otherLocaleTypeId"name="otherLocale.type.id" type="hidden" value="${otherLocale.type.id}" />
 			<label id="otherLocaleType"></label>
 		</td>
	</tr>
	
	<tr>
		<th class="th">联系人</th>
		<td width="19%">${otherLocale.contactPerson}</td>
		<td width="12%" class="th">联系电话</td>
		<td width="23%">${otherLocale.telephone}</td>
		<td width="13%" class="th">联系手机</td>
		<td width="16%">${otherLocale.mobileNumber}</td>
	</tr>
	
	<tr>
		<th class="th">备 注</th>
		<td colspan="5">
		<p>${otherLocale.remark}</p>
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
			"organization.id":$("#organizationId").val()
		},
		success:function(responseData){
			$("#orgName").html(responseData);
		}
	});

	if($("#otherLocaleTypeId").val()!=null&&$("#enterpriseTypeId").val()!=""){
		$.ajax({
			async: false,
			url: "${path }/sysadmin/propertyManage/getPropertyDictById.action",
			data:{
				"propertyDomain.domainName":"其他场所类型",
				"propertyDict.id":$("#otherLocaleTypeId").val()
			},
			success:function(responseData){
				$("#otherLocaleType").html(responseData.displayName);
			}
		});
	}

});
</script>