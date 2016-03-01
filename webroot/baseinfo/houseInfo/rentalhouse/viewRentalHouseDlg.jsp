﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<s:action name="dispatchOperate" namespace="/baseinfo/rentalHouseManage" var="getActualHouse" executeResult="false">
	<s:param name="houseInfo.id" value="#parameters['houseId'][0]"></s:param>
</s:action>
<%
String houseInfoId = request.getParameter("houseInfoId");
request.setAttribute("houseInfoId",houseInfoId);
%>

<div id="actualHouse" class="container container_24">
	<div id=tabs>
		<ul>
			<li><a href="${path}/baseinfo/actualHouseManage/dispatchHouseInfoByHouseIdByEncrypt.action?houseInfo.organization.id=<s:property value="#getActualHouse.houseInfo.organization.id"/>&houseInfo.id=<s:property value="#getActualHouse.houseInfo.houseInfoEncryptId"/>&houseType=rentalHouse">基础信息</a> </li>
			<li><a href="${path}/baseinfo/actualHouseManage/dispatchactualHousePopulationByOrgIdAndHouseIdByEncrypt.action?houseInfo.organization.id=<s:property value="#getActualHouse.houseInfo.organization.id"/>&houseInfo.id=<s:property value="#getActualHouse.houseInfo.houseInfoEncryptId"/>">人员信息</a></li>
			<li><a href="${path }/baseinfo/rentalHouseManage/dispatchRentalHouseByHouseIdByEncrypt.action?houseInfo.organization.id=<s:property value="#getActualHouse.houseInfo.organization.id"/>&houseInfo.id=<s:property value="#getActualHouse.houseInfo.houseInfoEncryptId"/>">出租信息</a></li>
			<pop:JugePermissionTag ename="serviceTeamMemberManagement">
			<li><a href="/plugin/serviceTeam/router/routerManage/maintainServiceMemberForHouse.action?mode=view1&id=<s:property value="#getActualHouse.houseInfo.id"/>&populationType=RENTALHOUSE">管理治安负责人</a></li>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="serviceRecordManagement">
			<li><a href="/plugin/serviceTeam/router/routerManage/maintainServiceRecordForPopulation.action?mode=page&fromSource=population&id=<s:property value="#getActualHouse.houseInfo.id"/>&populationType=RENTALHOUSE">巡场情况</a></li>
			</pop:JugePermissionTag>
			
			<li><a href="${path }/baseinfo/houseInfo/actualHouse/houseInfoTrackInfos.jsp?houseId=<%=houseInfoId %>&width=750&height=370&hsType=house&hsAddress=<s:property value="#getActualHouse.houseInfo.address"/>&houseOpertyType=rentalHouse&houseAddress="+encodeURI(encodeURI("${houseInfo.address}"))>轨迹信息</a></li>
		</ul>
	</div>
</div>
<script>
$(function() {
	$( "#tabs" ).tabs();
});
</script>