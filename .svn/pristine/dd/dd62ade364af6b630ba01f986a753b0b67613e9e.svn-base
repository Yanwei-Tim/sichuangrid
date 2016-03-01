<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<s:action name="dispatchOperate" namespace="/baseinfo/actualHouseManage" var="getActualHouse" executeResult="false">
	<s:param name="houseInfo.id" value="#parameters['houseId'][0]"></s:param>
</s:action>
<%
String houseId = request.getParameter("houseId");
request.setAttribute("houseId",houseId);
// String houseInfoId = request.getParameter("houseInfoId");
%>
<div id="actualHouse" class="container container_24">
<div id=tabs>
	<ul>
		<li><a href="${path}/baseinfo/actualHouseManage/dispatchHouseInfoByHouseIdByEncrypt.action?houseInfo.id=${houseId}">基础信息</a></li>
		<li><a href="${path}/baseinfo/actualHouseManage/dispatchactualHousePopulationByOrgIdAndHouseIdByEncrypt.action?houseInfo.organization.id=<s:property value="#getActualHouse.houseInfo.organization.id"/>&houseInfo.id=${houseId}">人员信息</a></li>
		<c:if test="${getActualHouse.houseInfo.isRentalHouse }">
		<li><a href="${path}/baseinfo/rentalHouseManage/dispatchRentalHouseByHouseIdByEncrypt.action?houseInfo.organization.id=<s:property value="#getActualHouse.houseInfo.organization.id"/>&houseInfo.id=${houseId}">出租信息</a></li>
		</c:if>
		<!-- 没有根据id进行的操作，不会暴露id或越权不需要验证？ -->
		<li><a href="${path }/baseinfo/houseInfo/actualHouse/houseInfoTrackInfos.jsp?houseId=${houseId}&width=750&height=370&hsType=house&hsAddress=<s:property value="#getActualHouse.houseInfo.address"/>&houseOpertyType=actualHouse&houseAddress="+encodeURI(encodeURI("${houseInfo.address}"))>轨迹信息</a></li>
		
	</ul>
</div>
</div>
<script>
$(function() {
	$( "#tabs" ).tabs();
});
</script>