<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="mentalPatientPrint">
	<style type="text/css">
		*{
			padding:0;
			margin:0;
			font-size:12px;
			font-family: Arial;
		}
		.mod_print{
			margin:0 auto;
			width:190mm;
			height:97mm;
		}
		.mod_print h1{
			font-size:34px;
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
		<input id="organizationId" type="hidden" name="orgId" value="${orgId}" />
		<div class="mod_print">
		<h1>${mentalPatient.name}信息</h1>
    	<div class="b">
    	<table>
          <tr>
            <th width="17%" class="th">所属网格</th>
            <td colspan="5" class="heightAuto"><label id="orgName"></label></td>
          </tr>
          <tr>
            <th class="th">姓名</th>
            <td width="19%" >${mentalPatient.name}</td>
            <td width="12%" class="th">身份证号码</td>
            <td width="23%" >${mentalPatient.idCardNo}</td>
            <td width="13%" class="th">性 别</td>
            <td width="16%" ><input id="mentalPatient.gender.id" type="hidden" value="${mentalPatient.gender.id}"><label id="gender"></label></td>
          </tr>
          
           <tr>
            <th class="th">户籍地</th>
            <td colspan="5">${mentalPatient.nativeProvince}${mentalPatient.nativeCity}${mentalPatient.nativeDistrict}</td>
          </tr>
          
           <tr>
            <th class="th">户籍详址</th>
            <td colspan="3">${mentalPatient.nativePlaceAddress}</td>
            <td class="th">出生日期</td>
            <td>
           	<label><fmt:formatDate value="${mentalPatient.birthday}" type="date" pattern="yyyy-MM-dd" /></label>
            </td>
          </tr>
          
            <tr>
            <th class="th">常住地址</th>
            <td colspan="3">${mentalPatient.familyAddress}</td>
            
            <td class="th">联系电话</td>
            <td>
           	<label>${mentalPatient.telephone}</label>
            </td>
          </tr>
          
           <tr>
            <th class="th">工作单位或就读学校</th>
            <td colspan="3">${mentalPatient.workUnit}</td>
            <td class="th">联系手机</td>
            <td>
           	<label>${mentalPatient.mobileNumber}</label>
            </td>
          </tr>
          
           <tr>
            <th class="th">患病名称</th>
            <td colspan="3">${mentalPatient.psychosisName}</td>
            <td class="th">危险程度</td>
            <td>
           	<input id="mentalPatient.dangerLevel.id" value="${mentalPatient.dangerLevel.id}" type="hidden">
			<label id="dangerLevel"></label>
            </td>
          </tr>
          
          <tr>
            <th class="th">是否接受过治疗</th>
            <td colspan="3">${mentalPatient.treat==true?'是':'否'}</td>
            <td class="th">康复机构</td>
            <td>
           	${mentalPatient.cureDepartment}
            </td>
          </tr>
          
           <tr>
            <th class="th">备 注</th>
            <td colspan="5"><p>${mentalPatient.remark}</p>
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

	$.ajax({
		async: false,
		url: "${path }/sysadmin/propertyManage/getPropertyDictById.action",
		data:{
			"propertyDomain.domainName":"性别",
			"propertyDict.id":$("#mentalPatient\\.gender\\.id").val()
		},
		success:function(responseData){
			if(responseData!=null){
				$("#gender").html(responseData.displayName);
			}
		}
	});

	$.ajax({
		async: false,
		url: "${path }/sysadmin/propertyManage/getPropertyDictById.action",
		data:{
			"propertyDomain.domainName":"危险程度",
			"propertyDict.id":$("#mentalPatient\\.dangerLevel\\.id").val()==""?'-1':$("#mentalPatient\\.dangerLevel\\.id").val()
		},
		success:function(responseData){
			if(responseData!=null){
				$("#dangerLevel").html(responseData.displayName);
			}
		}
	});
});
</script>
