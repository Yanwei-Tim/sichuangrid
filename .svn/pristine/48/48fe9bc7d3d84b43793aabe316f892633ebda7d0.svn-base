<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="enterprisePrint" >
<input id="organizationId" type="hidden" name="ownerOrg.id"
	value="${ownerOrg.id}" />
<style type="text/css">
* {
	padding: 0;
	margin: 0;
	font-size: 12px;
	font-family: Arial;
}

.mod_print {
	margin: 0 auto;
	width: 210mm;
	height: 97mm;
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
<div class="mod_print" >
<h1>${enterprise.name}信息</h1>
<div class="b">
<table>
	<tr>
		<th class="th">所属网格</th>
		<td colspan="5" class="heightAuto"><label id="orgName"></label></td>
	</tr>

	<tr>
		<th class="th">企业名称</th>
		<td colspan="5">${enterprise.name}</td>
	</tr>

	<tr>
		<th class="th" >企业类型</th>
		<td width="19%" colspan="3"><input name="enterprise.type.id"
			id="enterpriseTypeId" value="${enterprise.type.id}" type="hidden">
		<label id="enterpriseType"></label></td>
		<td width="12%" class="th">工商执照号码</td>
		<td width="23%">${enterprise.businessLicense}</td>
	</tr>

	<tr>
		<th class="th">法人代表</th>
		<td width="19%">${enterprise.legalPerson}</td>
		<td width="12%" class="th">注册资金</td>
		<td width="23%">${enterprise.registeredCapital}(万元)</td>
		<td width="13%" class="th">所属行业</td>
		<td width="16%"><input name="enterprise.industry.id"
			id="enterpriseIndustryId" value="${enterprise.industry.id}"
			type="hidden"> <label id="enterpriseIndustry"></label></td>
	</tr>

	<tr>
		<th class="th">面积</th>
		<td width="19%">${enterprise.area}<font size="1">(M</font><font size="1"><sup>2</sup>)</font></td>
		<td width="12%" class="th">员工数</td>
		<td width="23%">${enterprise.employeeAmount}</td>
		<td width="13%" class="th">党员数</td>
		<td width="16%">${enterprise.partyMemberAmount}</td>
	</tr>

	<tr>
		<th class="th">企业联系电话</th>
		<td width="19%">${enterprise.enterpriseTelephone}</td>
		<td width="12%" class="th">企业传真号码</td>
		<td width="23%">${enterprise.fax}</td>
		<td width="13%" class="th">是否危化企业</td>
		<td width="16%">${enterprise.riskEnterprise==true?'是':'否'}</td>
	</tr>

	<tr>
		<th class="th" >法人联系电话</th>
		<td width="19%" colspan="3">${enterprise.telephone}</td>
		<td width="12%" class="th">法人手机号码</td>
		<td width="23%">${enterprise.mobileNumber}</td>
	</tr>

	<tr>
		<th class="th">企业地址</th>
		<td colspan="5">${enterprise.address}</td>
	</tr>

	<tr>
		<th class="th">隐患情况</th>
		<td colspan="5">${enterprise.hiddenCases}</td>
	</tr>

	<tr>
		<th class="th">隐患整改情况</th>
		<td colspan="5">${enterprise.hiddenRectification}</td>
	</tr>

	<tr>
		<th class="th">备注</th>
		<td colspan="5">${enterprise.remark}</td>
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

	if($("#enterpriseTypeId").val()!=null&&$("#enterpriseTypeId").val()!=""){
		$.ajax({
			async: false,
			url: "${path }/sysadmin/propertyManage/getPropertyDictById.action",
			data:{
				"propertyDomain.domainName":"企业类型",
				"propertyDict.id":$("#enterpriseTypeId").val()
			},
			success:function(responseData){
				$("#enterpriseType").html(responseData.displayName);
			}
		});
	}
	if($("#enterpriseIndustryId").val()!=null&&$("#enterpriseIndustryId").val()!=""){
		$.ajax({
			async: false,
			url: "${path }/sysadmin/propertyManage/getPropertyDictById.action",
			data:{
				"propertyDomain.domainName":"职业",
				"propertyDict.id":$("#enterpriseIndustryId").val()
			},
			success:function(responseData){
				$("#enterpriseIndustry").html(responseData.displayName);
			}
		});
	}
});
</script>