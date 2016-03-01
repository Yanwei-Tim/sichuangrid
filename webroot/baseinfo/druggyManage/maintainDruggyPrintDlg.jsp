<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="container container_24" id="druggyPrint" >
<style type="text/css">
		*{
			padding:0;
			margin:0;
			font-size:12px;
			font-family: Arial;
		}
		.mod_print{
			margin:0 auto;
			width:200mm;
			height:97mm;
		}
		.mod_print h1{
			font-size:30px;
			text-align:center;
			line-height:3em;
		}
		.mod_print .b table{
			width:100%;
			line-height:2em;
			border-collapse:collapse;
		}
		.mod_print .b table td,
		.mod_print .b table th{
			padding:0.3em 0.5em;
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
		<input type="hidden" name="ownerOrg.id" id="organizationId" value="${ownerOrg.id}" />
		<div class="mod_print">
		<h1>${druggy.name}信息</h1>
    	<div class="b">
    	<table>
          <tr>
            <th width="17%" class="th">所属网格</th>
            <td colspan="5" class="heightAuto"><label id="orgName"></label></td>
          </tr>
          <tr>
            <th class="th">姓名</th>
            <td width="19%" >${druggy.name}</td>
            <td width="12%" class="th">身份证号码</td>
            <td width="23%" >${druggy.idCardNo}</td>
            <td width="13%" class="th">性 别</td>
            <td width="16%" ><input id="druggy.gender.id" type="hidden" value="${druggy.gender.id}"><label id="gender"></label></td>
          </tr>
          
           <tr>
            <th class="th">户籍地</th>
            <td colspan="3">${druggy.province}${druggy.city}${druggy.district}</td>
            <td class="th">吸毒状态</td>
            <td>
            <input id="druggy.detoxicateCondition.id" type="hidden" value="${druggy.detoxicateCondition.id}">
			<label id="detoxicateCondition"></label>
            </td>
          </tr>
          
           <tr>
            <th class="th">户籍详址</th>
            <td colspan="3">${druggy.nativePlaceAddress}</td>
            <td class="th">出生日期</td>
            <td>
           	<label><s:date name="druggy.birthday" format="yyyy-MM-dd"/></label>
            </td>
          </tr>
          
             <tr>
            <th class="th">户籍派出所</th>
            <td colspan="3">${druggy.nativePoliceStation}</td>
            <td class="th">联系电话</td>
            <td>
           	<label>${druggy.telephone}</label>
            </td>
          </tr>
          
            <tr>
            <th class="th">常住地址</th>
            <td colspan="3">${druggy.currentAddress}</td>
            <td class="th">联系手机</td>
            <td>
           	<label>${druggy.mobileNumber}</label>
            </td>
          </tr>
          
           <tr>
            <th class="th">工作单位或就读学校</th>
            <td colspan="3">${druggy.workUnit}</td>
            <td class="th">首吸时间</td>
            <td>
           <label><s:date name="druggy.drugFristDate" format="yyyy-MM-dd"/></label>
            </td>
          </tr>
          
           <tr>
            <th class="th">管控现状</th>
            <td colspan="3">${druggy.controlActuality}</td>
            <td class="th">查获日期</td>
            <td>
           <label><s:date name="druggy.ferretOutDate" format="yyyy-MM-dd"/></label>
            </td>
          </tr>
          
           <tr>
            <th class="th">吸毒原因</th>
            <td colspan="3">
            	<input id="druggy.drugReason.id" type="hidden" value="${druggy.drugReason.id}">
			<label id="drugReason"></label>
            </td>
            <td class="th">毒品来源</td>
            <td>
           		<input id="druggy.drugSource.id" type="hidden" value="${druggy.drugSource.id}">
				<label id="drugSource"></label>
            </td>
          </tr>
          
            <tr>
            <th class="th">戒毒情况</th>
            <td colspan="3">
            	<input id="druggy.detoxicateCase.id" type="hidden" value="${druggy.detoxicateCase.id}">
			<label id="detoxicateCase"></label>
            </td>
            <td class="th">是否服美沙酮戒毒</td>
            <td>
           		${druggy.adanon==true?'是':'否'}
            </td>
          </tr>
          
          <tr>
            <th class="th">备 注</th>
            <td colspan="5"><p>${druggy.remark} </p></td>
          </tr>
          
		</table>
		</div>
		</div>
		</div>

<script>

$(document).ready(function(){
	$.ajax({
		async: false,
		url: "${path}/sysadmin/orgManage/getOrgRelativePath.action",
		data:{
			"organization.id":$("#organizationId").val()
		},
		success:function(responseData){
			$("#orgName").html(responseData);
		}
	});

	$.ajax({
		async: false,
		url: "${path }/sysadmin/propertyManage/getPropertyDictById.action",
		data:{
			"propertyDomain.domainName":"性别",
			"propertyDict.id":$("#druggy\\.gender\\.id").val()
		},
		success:function(responseData){
			$("#gender").html(responseData.displayName);
		}
	});

	$.ajax({
		async: false,
		url: "${path }/sysadmin/propertyManage/getPropertyDictById.action",
		data:{
			"propertyDomain.domainName":"吸毒原因",
			"propertyDict.id":$("#druggy\\.drugReason\\.id").val()==""?'-1':$("#druggy\\.drugReason\\.id").val()
		},
		success:function(responseData){
			if(responseData!=null){
				$("#drugReason").html(responseData.displayName);
			}
		}
	});

	$.ajax({
		async: false,
		url: "${path }/sysadmin/propertyManage/getPropertyDictById.action",
		data:{
			"propertyDomain.domainName":"毒品来源",
			"propertyDict.id":$("#druggy\\.drugSource\\.id").val()==""?'-1':$("#druggy\\.drugSource\\.id").val()
		},
		success:function(responseData){
			if(responseData!=null){
				$("#drugSource").html(responseData.displayName);
			}
		}
	});

	$.ajax({
		async: false,
		url: "${path }/sysadmin/propertyManage/getPropertyDictById.action",
		data:{
			"propertyDomain.domainName":"戒毒情况",
			"propertyDict.id":$("#druggy\\.detoxicateCase\\.id").val()==""?'-1':$("#druggy\\.detoxicateCase\\.id").val()
		},
		success:function(responseData){
			if(responseData!=null){
				$("#detoxicateCase").html(responseData.displayName);
			}
		}
	});

	$.ajax({
		async: false,
		url: "${path }/sysadmin/propertyManage/getPropertyDictById.action",
		data:{
			"propertyDomain.domainName":"吸毒状态",
			"propertyDict.id":$("#druggy\\.detoxicateCondition\\.id").val()==""?'-1':$("#druggy\\.detoxicateCondition\\.id").val()
		},
		success:function(responseData){
			if(responseData!=null){
				$("#detoxicateCondition").html(responseData.displayName);
			}
		}
	});


});
</script>
