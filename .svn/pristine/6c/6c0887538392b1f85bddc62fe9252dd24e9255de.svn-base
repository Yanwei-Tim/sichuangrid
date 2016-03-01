<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="rectificativePerson">
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
	<input id="organizationId" type="hidden" name="orgId" value="${orgId}">
	<div class="mod_print" >
		<h1>${rectificativePerson.name}信息</h1>
    	<div class="b">
    	<table>
          <tr>
            <th width="17%" class="th">所属网格</th>
            <td colspan="5" class="heightAuto"><label id="orgName"></label></td>
          </tr>
          <tr>
            <th class="th">姓名</th>
            <td width="19%" >${rectificativePerson.name}</td>
            <td width="12%" class="th">身份证号码</td>
            <td width="23%" >${rectificativePerson.idCardNo}</td>
            <td width="13%" class="th">性 别</td>
            <td width="16%" ><input id="rectificativePerson.gender.id" type="hidden" value="${rectificativePerson.gender.id}"><label id="gender"></label></td>
          </tr>

		 <tr>
            <th class="th">户籍地</th>
            <td colspan="5">${rectificativePerson.province}${rectificativePerson.city}${rectificativePerson.district}</td>
          </tr>	
		  <tr>
            <th class="th">户籍详址</th>
            <td colspan="3" >${rectificativePerson.nativePlaceAddress}</td>
            <td class="th">出生日期</td>
            <td><fmt:formatDate value="${rectificativePerson.birthday}" type="date" pattern="yyyy-MM-dd" /></td>
          </tr>
           <tr>
            <th class="th">户籍派出所</th>
            <td colspan="3" >${rectificativePerson.nativePoliceStation}</td>
            <td class="th">联系电话</td>
            <td>${rectificativePerson.telephone}</td>
          </tr>
          <tr>
          <th class="th">常住地址</th>
            <td colspan="3" >${rectificativePerson.currentlyAddress}</td>
            <td class="th">联系手机</td>
            <td>${rectificativePerson.mobileNumber}</td>
          </tr>
          
          <tr>
            <th class="th">刑法执行类别</th>
            <td width="19%" ><input name="" id="rectificativePerson.executeType.id" type="hidden" value="${rectificativePerson.executeType.id}"><label id="executeType"></label> </td>
            <td width="12%" class="th">原判刑期</td>
            <td width="23%" colspan="3">${rectificativePerson.penaltyTerm}</td>
          </tr>
          
               <tr>
            <th class="th">社区矫正时间</th>
            <td width="19%" ><fmt:formatDate value="${rectificativePerson.rectifyStartDate}" type="date" pattern="yyyy-MM-dd" /></td>
            <td width="12%" class="th"> 至 </td>
            <td width="23%" colspan="3"><fmt:formatDate value="${rectificativePerson.rectifyEndDate}" type="date" pattern="yyyy-MM-dd" /></td>
          </tr>
          <tr>
          <th class="th">近况描述</th>
            <td colspan="5" >${rectificativePerson.recentSituation}</td>
          </tr>
           <tr>
		  <th class="th">备 注</th>
            <td colspan="5" >${rectificativePerson.remark}</td>
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
			"propertyDict.id":$("#rectificativePerson\\.gender\\.id").val()
		},
		success:function(responseData){
			$("#gender").html(responseData.displayName);
		}
	});

	$.ajax({
		async: false,
		url: "${path }/sysadmin/propertyManage/getPropertyDictById.action",
		data:{
			"propertyDomain.domainName":"刑法类别",
			"propertyDict.id":$("#rectificativePerson\\.executeType\\.id").val()==""?'-1':$("#rectificativePerson\\.executeType\\.id").val()
		},
		success:function(responseData){
			$("#executeType").html(responseData.displayName);
		}
	});

});

</script>