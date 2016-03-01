<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="newSocietyFederationPrint">
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
	border:1px;
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
	<input id="organizationId" name="organization.id" type="hidden" value="${organization.id}" />
	<div class="mod_print" style="width:98%">
		<h1>${newSocietyFederation.name}信息</h1>
		<div class="b">
			<table>
				<tr>
	            	<th width="17%" class="th">所属区域</th>
	            	<td colspan="4" class="heightAuto"><label id="orgName"></label></td>
	          	</tr>
	          	<tr>
	          		<th class="th">名称</th>
	          		<td class="heightAuto">${newSocietyFederation.name}</td>
	          		<th class="th">负责人</th>
	          		<td colspan="1" class="heightAuto">${newSocietyFederation.legalPerson }</td>
	          	</tr>
				<tr>
					<th class="th">地址</th>
					<td class="heightAuto">${newSocietyFederation.address }</td>
					<th class="th">类别</th>
					<td class="heightAuto"><input name="newSocietyFederation.type.id" id="newSocietyFederationTypeId" value="${newSocietyFederation.type.id}" type="hidden"/><label id="newSocietyFederationType"></label></td>
				</tr>
				<tr>
					<th class="th">联系电话</th>
					<td>${newSocietyFederation.legalPersonTelephone}</td>
					<th class="th">联系手机</th>
					<td>${newSocietyFederation.legalPersonMobileNumber}</td>
				</tr>
				<tr>
					<th class="th">主要职责</th>
					<td colspan="3">${newSocietyFederation.majorDuty}</td>
				</tr>
				<tr>
					<th class="th">备注</th>
					<td colspan="3">${newSocietyFederation.remark}</td>
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

	
	if($("#newSocietyFederationTypeId").val()!=null && $("#newSocietyFederationTypeId").val()!=""){
		$.ajax({
			async: false,
			url: "${path }/sysadmin/propertyManage/getPropertyDictById.action",
			data:{
				"propertyDomain.domainName":"社会组织类型",
				"propertyDict.id":$("#newSocietyFederationTypeId").val()
			},
			success:function(responseData){
				$("#newSocietyFederationType").html(responseData.displayName);
			}
		});
	}
	
	

	
});
</script>