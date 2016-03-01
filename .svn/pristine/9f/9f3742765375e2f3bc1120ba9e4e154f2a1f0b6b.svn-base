<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="lettingHousePrint" class="container container_24">
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
    <input type="hidden" name="lettingHouse.orgInternalCode" id="orgInternalCode"
    		value="${lettingHouse.orgInternalCode}">
	<input id="modeType" type="hidden" name="modeType" value="${modeType}" /> 
	<input id="organizationId"	type="hidden" name="organizationId" value="${lettingHouse.organization.id}" /> 
	<input id="lettingHouseId"	type="hidden" name="lettingHouse.id" value="${lettingHouse.id}" /> 
  <div class="mod_print" >
    <h1>出租房信息</h1>
    <div class="b">
      <table>
        <tr>
			<th width="17%" class="th">所属网格</th>
			<td colspan="5" class="heightAuto"><label id="orgName"></label></td>
	    </tr>
	    <tr>
	        <th class="th">房东姓名</th>
            <td width="17%" >${lettingHouse.name}</td>
            <td width="12%" class="th">身份证号码</td>
            <td width="23%" >${lettingHouse.idCardNo}</td>
            <td width="13%" class="th">登记日期</td>
			<td><label><s:date name="lettingHouse.registDate" format="yyyy-MM-dd"></s:date></label></td>
	    </tr>
	    <tr>
			<th class="th">编号</th>
			<td width="17%" >${lettingHouse.lettingHouseNo}</td>
			<td width="12%" class="th">联系电话</td>
			<td width="23%">${lettingHouse.telephone}</td>
			<td width="13%" class="th">联系手机</td>
			<td width="14%">${lettingHouse.mobileNumber}</td>
	    </tr>
	    <tr>
			<th width="17%" class="th">房东地址</th>
			<td colspan="5" class="heightAuto">${lettingHouse.landlordAddr}</td>
	    </tr>
	    <tr>
			<th width="17%" class="th">出租房地址</th>
			<td colspan="5" class="heightAuto">${lettingHouse.lettingHouseAddr}</td>
	    </tr>
	    <tr>
            <th class="th">房间数</th>
            <td width="17%" >${lettingHouse.roomNumbers}</td>
            <td width="12%" class="th">出租房面积</td>
            <td width="23%" >${lettingHouse.lettingHouseAreas}M<sup>2</sup></td>
            <td width="13%" class="th">限住人数</td>
            <td width="14%" >${lettingHouse.limitPersons}</td>
         </tr>
         <tr>
            <th class="th">实住人数</th>
            <td width="17%" >${lettingHouse.realityPersons}</td>
            <td width="12%" class="th">出租房结构</td>
            <td width="23%" >
               <input id="resident.lettingHouseStruts.id" type="hidden" value="${lettingHouse.lettingHouseStruts.id}"><label id="lettingHouseStruts"></label>
            </td>
            <td width="13%" class="th">出租房类别</td>
            <td width="14%" >
               <input id="lettingHouse.type.id" type="hidden" value="${lettingHouse.type.id}"><label id="houseType"></label>
            </td>
          </tr>
          <tr>
            <th class="th">出租房性质</th>
            <td width="17%" >
               <input id="lettingHouse.lettingHouseProperty.id" type="hidden" value="${lettingHouse.lettingHouseProperty.id}"><label id="lettingHouseProperty"></label>
            </td>
            <td width="12%" class="th">出租用途</td>
            <td width="23%" >
               <input id="lettingHouse.usage.id" type="hidden" value="${lettingHouse.usage.id}"><label id="usage"></label>
            </td>
            <td width="13%" class="th">隐患程度</td>
            <td width="14%" >
               <input id="lettingHouse.hiddenTroubleLevel.id" type="hidden" value="${lettingHouse.hiddenTroubleLevel.id}"><label id="hiddenTroubleLevel"></label>
            </td>
          </tr>
          <tr>
			   <th class="th">隐患情况</th>
			   <td colspan="5">
			     <p>${lettingHouse.hiddenRectification}</p>
			   </td>
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
			"propertyDomain.domainName":"出租房结构",
			"propertyDict.id":$("#resident\\.lettingHouseStruts\\.id").val()
		},
		success:function(responseData){
			$("#lettingHouseStruts").html(responseData.displayName);
		}
	});
	$.ajax({
		async: false,
		url: "${path }/sysadmin/propertyManage/getPropertyDictById.action",
		data:{
			"propertyDomain.domainName":"出租房类别",
			"propertyDict.id":$("#lettingHouse\\.type\\.id").val()
		},
		success:function(responseData){
			$("#houseType").html(responseData.displayName);
		}
	});
	$.ajax({
		async: false,
		url: "${path }/sysadmin/propertyManage/getPropertyDictById.action",
		data:{
			"propertyDomain.domainName":"出租房性质",
			"propertyDict.id":$("#lettingHouse\\.lettingHouseProperty\\.id").val()
		},
		success:function(responseData){
			$("#lettingHouseProperty").html(responseData.displayName);
		}
	});
	$.ajax({
		async: false,
		url: "${path }/sysadmin/propertyManage/getPropertyDictById.action",
		data:{
			"propertyDomain.domainName":"出租用途",
			"propertyDict.id":$("#lettingHouse\\.usage\\.id").val()
		},
		success:function(responseData){
			$("#usage").html(responseData.displayName);
		}
	});
	$.ajax({
		async: false,
		url: "${path }/sysadmin/propertyManage/getPropertyDictById.action",
		data:{
			"propertyDomain.domainName":"隐患程度",
			"propertyDict.id":$("#lettingHouse\\.hiddenTroubleLevel\\.id").val()
		},
		success:function(responseData){
			$("#hiddenTroubleLevel").html(responseData.displayName);
		}
	});
});
</script>
