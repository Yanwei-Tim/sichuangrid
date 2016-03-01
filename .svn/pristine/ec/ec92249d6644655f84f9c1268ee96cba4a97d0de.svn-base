<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="housePropertyInfoPrint">
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
<input type="hidden" name="estateInformation.id" id="estateInformation-id" value="${estateInformation.id}" />
<input type="hidden" name="ownerOrg.id" id="organizationId" value="${ownerOrg.id}" />
<div class="mod_print">
<div class="b">
<h1 align="center"><font size="6" style="font-weight:700;">房产信息</font></h1>
<table >
 <tr height=28 style='mso-height-source:userset;height:21.0pt'>
  <td colspan=6 class="th"><font size="4" style="font-weight:600;">房产信息</font></td>
 </tr>
 <tr>
  <th class="th"><span>房产证号</span></th>
  <td width="23%">${estateInformation.proprietaryNo}　</td>
  <td class="th">土地证号</td>
  <td width="19%">${estateInformation.landPermit}　</td>
  <td class="th">住宅产权</td>
  <td width="19%"><input id="estateInformation.houseEquity.id" type="hidden" value="${estateInformation.houseEquity.id}"><label id="houseEquity"></label>　</td>
 </tr>
 <tr>
  <th class="th">建筑结构</th>
  <td><input id="estateInformation.buildingStructure.id" type="hidden" value="${estateInformation.buildingStructure.id}"><label id="buildingStructure"></label>　</td>
  <td class="th">建筑面积</td>
  <td>${estateInformation.coveredArea} (㎡)</td>
  <td class="th">占地面积</td>
  <td>${estateInformation.floorArea}　(㎡)</td>
 </tr>
 <tr>
  <th class="th">建构年代</th>
  <td><input id="estateInformation.structureYear.id" type="hidden" value="${estateInformation.structureYear.id}"><label id="structureYear"></label></td>
  <td class="th">房产地址</td>
  <td colspan=3>${estateInformation.housePropertyPlace}　</td>
 </tr>
 <tr>
  <th height=19 class="th" style='height:14.25pt;border-top:none'>所属网格</th>
  <td class=xl27 width=205 style='border-top:none;border-left:none;width:154pt'><label id="orgName"></label>　</td>
  <td class="th">有出租房</td>
  <td><input id="estateInformation.Hire" type="hidden" value="${estateInformation.hire}"><label id="hire"></label></td>
  <td class="th">危房</td>
  <td><input id="estateInformation.DangerousBuilding" type="hidden" value="${estateInformation.dangerousBuilding}"><label id="dangerousBuilding"></label></td>
 </tr>
 <tr>
  <th class="th">自来水已入户</th>
  <td><input id="estateInformation.HasTapWater" type="hidden" value="${estateInformation.hasTapWater}"><label id="hasTapWater"></label></td>
  <td class="th">互联网已入户</td>
  <td><input id="estateInformation.HasNet" type="hidden" value="${estateInformation.hasNet}"><label id="hasNet"></label></td>
  <td class="th">有卫生间</td>
  <td ><input id="estateInformation.HasToilet" type="hidden" value="${estateInformation.hasToilet}"><label id="hasToilet"></label></td>
 </tr>
 <tr>
  <th class="th">出租房地址</th>
  <td colspan=3>${estateInformation.occupiedAddress}　</td>
  <td class="th">出租房房产证号</td>
  <td>${estateInformation.occupiedNo}　</td>
 </tr>
 <tr>
  <td colspan=6 class="th">房主信息</td>
 </tr>
 <s:iterator value="inhabitants" var="inhabitantConnect">
	 <tr>
	  <th class="th">姓名</th>
	  <td>${inhabitantConnect.name}　</td>
	  <td class="th">身份证号码</td>
	  <td colspan=3 class=xl26>${inhabitantConnect.idCardNo}　</td>
	 </tr>
 </s:iterator>
</table>
</div>
</div>
</div>
<script>
$(document).ready(function(){

	var houseEquityId = $("#estateInformation\\.houseEquity\\.id").val();
	if(null!=houseEquityId && ""!=houseEquityId && undefined!=houseEquityId){
		$.ajax({
			async: false,
			url: "${path }/sysadmin/propertyManage/getPropertyDictById.action",
			data:{
				"propertyDomain.domainName":"住宅产权",
				"propertyDict.id":houseEquityId
			},
			success:function(responseData){
				$("#houseEquity").html(responseData.displayName);
			}
		});
	}
	
	var buildingStructureId = $("#estateInformation\\.buildingStructure\\.id").val();
	if(null!=buildingStructureId && ""!=buildingStructureId && undefined!=buildingStructureId){
		$.ajax({
			async: false,
			url: "${path }/sysadmin/propertyManage/getPropertyDictById.action",
			data:{
				"propertyDomain.domainName":"建筑结构",
				"propertyDict.id":buildingStructureId
			},
			success:function(responseData){
				$("#buildingStructure").html(responseData.displayName);
			}
		});
	}

	var structureYearId = $("#estateInformation\\.structureYear\\.id").val();
	if(null!=structureYearId && ""!=structureYearId && undefined!=structureYearId){
		$.ajax({
			async: false,
			url: "${path }/sysadmin/propertyManage/getPropertyDictById.action",
			data:{
				"propertyDomain.domainName":"建构年代",
				"propertyDict.id":structureYearId
			},
			success:function(responseData){
				$("#structureYear").html(responseData.displayName);
			}
		});
	}
	var orgIdNew = $("#organizationId").val();
	if(null!=orgIdNew && ""!=orgIdNew && undefined!=orgIdNew){
		$.ajax({
			async: false,
			url: "${path}/sysadmin/orgManage/getOrgRelativePath.action",
			data:{
				"organization.id":orgIdNew
			},
			success:function(responseData){
				$("#orgName").html(responseData);
			}
		});
	}
	

	
	var hire = $("#estateInformation\\.Hire").val();
	if(hire=='false'){
		$("#hire").html("否");
	}else{
		$("#hire").html("是");
	}
	var dangerousBuilding = $("#estateInformation\\.DangerousBuilding").val();
	if(dangerousBuilding=='false'){
		$("#dangerousBuilding").html("否");
	}else{
		$("#dangerousBuilding").html("是");
	}
	
	var hasTapWater = $("#estateInformation\\.HasTapWater").val();
	if(hasTapWater=='false'){
		$("#hasTapWater").html("否");
	}else{
		$("#hasTapWater").html("是");
	}

	var hasNet = $("#estateInformation\\.HasNet").val();
	if(hasNet=='false'){
		$("#hasNet").html("否");
	}else{
		$("#hasNet").html("是");
	}

	var hasToilet = $("#estateInformation\\.HasToilet").val();
	if(hasToilet=='false'){
		$("#hasToilet").html("否");
	}else{
		$("#hasToilet").html("是");
	}

});
</script>
