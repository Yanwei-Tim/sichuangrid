<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="idleYouthPrint">
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
<h1>${idleYouth.name}</h1>
<div class="b">
<table>
	<tr>
		<th width="17%" class="th">所属网格</th>
		<td colspan="5"><label id="idleYouthOrgName"></label></td>
	</tr>
	<tr>
		<th class="th">姓名</th>
		<td width="19%">${idleYouth.name}</td>
		<td width="12%" class="th">身份证号码</td>
		<td width="23%">${idleYouth.idCardNo}</td>
		<td width="13%" class="th">性 别</td>
		<td width="16%"><label id="gender"></label><input id="idleYouth.gender.id"
			type="hidden" value="${idleYouth.gender.id}"></input></td>
	</tr>

	<tr>
		<th class="th">户籍地</th>
		<td colspan="5">${idleYouth.province}${idleYouth.city}${idleYouth.district}</td>
	</tr>
	<tr>
		<th class="th">户籍详址</th>
		<td colspan="3">${idleYouth.nativePlaceAddress}</td>
		<td class="th">出生日期</td>
		<td><fmt:formatDate value="${idleYouth.birthday}" type="date" pattern="yyyy-MM-dd" /></td>
	</tr>
	<tr>
		<th class="th">户籍派出所</th>
		<td colspan="3">${idleYouth.nativePoliceStation}</td>
		<td class="th">联系电话</td>
		<td>${idleYouth.telephone}</td>
	</tr>
	<tr>
		<th class="th">常住地址</th>
		<td colspan="3">${idleYouth.familyAddress}</td>
		<td class="th">联系手机</td>
		<td>${idleYouth.mobileNumber}</td>
	</tr>
	<tr>
		<th class="th">工作情况</th>
		<td colspan="3">${idleYouth.workCondition}</td>
		<td class="th">人员类型</td>
		<td><input id="idleYouth.staffType.id" type="hidden"
			value="${idleYouth.staffType.id}"> <label id="staffType"></label>
		</td>
	</tr>
	<tr >
		<th class="th">备注</th>
		<td colspan="5">${idleYouth.remark}</td>
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
			"propertyDomain.domainName":"闲散青少年人员类型",
			"propertyDict.id":$("#idleYouth\\.staffType\\.id").val()==""?'-1':$("#idleYouth\\.staffType\\.id").val()
		},
		success:function(responseData){
			$("#staffType").html(responseData.displayName);
		}
	});

});
</script>