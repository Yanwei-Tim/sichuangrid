<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="specialCareGroupsPrint">
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
		.autoNewline{
		  	word-break: break-all;
		}
	</style>
<input id="organizationId" type="hidden" name="organizationId" value="${orgId}"/>
<div class="mod_print">
<h1>${specialCareGroups.name}信息</h1>
<div class="b">
<table>
	<tr>
		<th width="17%" class="th">所属网格</th>
		<td colspan="5"><label id="specialCareGroupsOrgName"></label></td>
	</tr>
	<tr>
		<th class="th">姓名</th>
		<td width="19%">${specialCareGroups.name}</td>
		<td width="12%" class="th">身份证号码</td>
		<td width="23%">${specialCareGroups.idCardNo}</td>
		<td width="13%" class="th">性 别</td>
		<td width="16%"><label id="gender"></label><input id="specialCareGroups.gender.id" type="hidden" value="${specialCareGroups.gender.id}"></input></td>
	</tr>

	<tr>
		<td class="th">出生日期</td>
		<td><s:date name="specialCareGroups.birthday" format="yyyy-MM-dd" /></td>		
		<th class="th">常住地址</th>
		<td colspan="3">${specialCareGroups.currentAddress}</td>		
	</tr>
	
	<tr>
		<td class="th">联系电话</td>
		<td>${specialCareGroups.telephone}</td>
		<td class="th">联系手机</td>
		<td>${specialCareGroups.mobileNumber}</td>
		<th class="th">优抚证号</th>
		<td>${specialCareGroups.specialCareNo}</td>	
	</tr>

	<tr>
		<td class="th">优抚类型</td>
		<td><input id="specialCareGroups.specialCareType.id" type="hidden"
			value="${specialCareGroups.specialCareType.id}"> <label id="specialCareType"></label>
		</td>	
		<td class="th">劳动能力</td>
		<td><input id="specialCareGroups.labourCapacity.id" type="hidden"
			value="${specialCareGroups.labourCapacity.id}"> <label id="labourCapacity"></label>
		</td>
		<td class="th">生活能力</td>
		<td><input id="specialCareGroups.viability.id" type="hidden"
			value="${specialCareGroups.viability.id}"> <label id="viability"></label>
		</td>	
	</tr>
	<tr>	
		<td class="th">就业情况</td>
		<td><input id="specialCareGroups.employmentStatus.id" type="hidden"
			value="${specialCareGroups.employmentStatus.id}"> <label id="employmentStatus"></label>
		</td>		
		<td class="th">供养情况</td>
		<td><input id="specialCareGroups.supportStatus.id" type="hidden"
			value="${specialCareGroups.supportStatus.id}"> <label id="supportStatus"></label>
		</td>		
		<th class="th">月生活费</th>
		<td>${specialCareGroups.monthsExpensesStr}</td>	
					
	</tr>

	<tr >	
		<th class="th">备注</th>
		<td colspan="5" class="autoNewline">${specialCareGroups.remark}</td>
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
			$("#specialCareGroupsOrgName").html(responseData);
		}
	});

	$.ajax({
		async: false,
		url: "${path }/sysadmin/propertyManage/getPropertyDictById.action",
		data:{
			"propertyDomain.domainName":"性别",
			"propertyDict.id":$("#specialCareGroups\\.gender\\.id").val()
		},
		success:function(responseData){
			$("#gender").html(responseData.displayName);
		}
	});

	$.ajax({
		async: false,
		url: "${path }/sysadmin/propertyManage/getPropertyDictById.action",
		data:{
			"propertyDomain.domainName":"优抚类型",
			"propertyDict.id":$("#specialCareGroups\\.specialCareType\\.id").val()==""?'-1':$("#specialCareGroups\\.specialCareType\\.id").val()
		},
		success:function(responseData){
			if(responseData!=null){
				$("#specialCareType").html(responseData.displayName);
			}
		}
	});
	$.ajax({
		async: false,
		url: "${path }/sysadmin/propertyManage/getPropertyDictById.action",
		data:{
			"propertyDomain.domainName":"劳动能力",
			"propertyDict.id":$("#specialCareGroups\\.labourCapacity\\.id").val()==""?'-1':$("#specialCareGroups\\.labourCapacity\\.id").val()
		},
		success:function(responseData){
			if(responseData!=null){
				$("#labourCapacity").html(responseData.displayName);
			}
		}
	});
	$.ajax({
		async: false,
		url: "${path }/sysadmin/propertyManage/getPropertyDictById.action",
		data:{
			"propertyDomain.domainName":"生活能力",
			"propertyDict.id":$("#specialCareGroups\\.viability\\.id").val()==""?'-1':$("#specialCareGroups\\.viability\\.id").val()
		},
		success:function(responseData){
			if(responseData!=null){
				$("#viability").html(responseData.displayName);
			}
		}
	});
	$.ajax({
		async: false,
		url: "${path }/sysadmin/propertyManage/getPropertyDictById.action",
		data:{
			"propertyDomain.domainName":"就业情况",
			"propertyDict.id":$("#specialCareGroups\\.employmentStatus\\.id").val()==""?'-1':$("#specialCareGroups\\.employmentStatus\\.id").val()
		},
		success:function(responseData){
			if(responseData!=null){
				$("#employmentStatus").html(responseData.displayName);
			}
		}
	});
	$.ajax({
		async: false,
		url: "${path }/sysadmin/propertyManage/getPropertyDictById.action",
		data:{
			"propertyDomain.domainName":"供养情况",
			"propertyDict.id":$("#specialCareGroups\\.supportStatus\\.id").val()==""?'-1':$("#specialCareGroups\\.supportStatus\\.id").val()
		},
		success:function(responseData){
			if(responseData!=null){
				$("#supportStatus").html(responseData.displayName);
			}
		}
	});

});
</script>