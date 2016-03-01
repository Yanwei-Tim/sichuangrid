<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
		<tr>
			<td ><div class='innerTxt cf'><strong>工程名称:</strong>${traffic.projectName}</div></td>
		</tr>
       	<tr>
			<td ><div class='innerTxt cf'><strong>受益人口:</strong>${traffic.beneficiaryNumber}</div></td>
		</tr>
		<c:if test="${traffic.buildType.id != null}">
       	<tr>
        	<td ><div class='innerTxt cf'><strong>建设性质:</strong>
        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@TRAFFIC_BUILD_TYPE" defaultValue="${traffic.buildType.id}" /></div></td>
       	</tr>
       	</c:if>
       	<c:if test="${traffic.projectCategory.id != null}">
       	<tr>
        	<td ><div class='innerTxt cf'><strong>项目类别:</strong>
        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@TRAFFIC_PROJECT" defaultValue="${traffic.projectCategory.id}" /></div></td>
       	</tr>
       	</c:if>
       	<c:if test="${traffic.roadCategory.id != null}">
       	<tr>
        	<td ><div class='innerTxt cf'><strong>道路类别:</strong>
        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@TRAFFIC_ROAD" defaultValue="${traffic.roadCategory.id}" /></div></td>
       	</tr>
       	</c:if>
       	<c:if test="${traffic.roadSurfaceCategory.id != null}">
       	<tr>
        	<td ><div class='innerTxt cf'><strong>路面类型:</strong>
        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@TRAFFIC_ROADSURFACE" defaultValue="${traffic.roadSurfaceCategory.id}" /></div></td>
       	</tr>
       	</c:if>
       	<c:if test="${traffic.securityCategory.id != null}">
       	<tr>
        	<td ><div class='innerTxt cf'><strong>安保设施类型:</strong>
        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@TRAFFIC_SECURITY" defaultValue="${traffic.securityCategory.id}" /></div></td>
       	</tr>
       	</c:if>
       	<c:if test="${traffic.passengerCategory.id != null}">
       	<tr>
        	<td ><div class='innerTxt cf'><strong>班线客运:</strong>
        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@TRAFFIC_PASSENGER" defaultValue="${traffic.passengerCategory.id}" /></div></td>
       	</tr>
       	</c:if>
       	<c:if test="${traffic.publicTransportCategory.id != null}">
       	<tr>
        	<td ><div class='innerTxt cf'><strong>城市公共交通:</strong>
        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@TRAFFIC_PUBLIC_TRANSPORT" defaultValue="${traffic.publicTransportCategory.id}" /></div></td>
       	</tr>
       	</c:if>
		<c:if test="${traffic.passengerManageCategory.id != null}">
       	<tr>
        	<td ><div class='innerTxt cf'><strong>客运站管理:</strong>
        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@TRAFFIC_PASSENGER_MANAGE" defaultValue="${traffic.passengerManageCategory.id}" /></div></td>
       	</tr>
       	</c:if>
       	<c:if test="${traffic.passengerBuildCategory.id != null}">
       	<tr>
        	<td ><div class='innerTxt cf'><strong>客运站建设:</strong>
        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@TRAFFIC_PASSENGER_BUILD" defaultValue="${traffic.passengerBuildCategory.id}" /></div></td>
       	</tr>
       	</c:if>
       	<tr>
			<td ><div class='innerTxt cf'><strong>建设起点:</strong>${traffic.fromAddress}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>建设讫点:</strong>${traffic.toAddress}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>桥宽:</strong>${traffic.rodeWide}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>桥长:</strong>${traffic.rodeLength}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>里程(公里):</strong>${traffic.mileage}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>宽:</strong>${traffic.wide}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>备注:</strong>${traffic.remark}</div></td>
		</tr>
       	<tr>
			<td ><div class='innerTxt cf'><strong>计划资金(万元):</strong>${traffic.plannedInvestment}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>自筹资金(万元):</strong>${traffic.selfFund}</div></td>
		</tr>
			<tr>
			<td ><div class='innerTxt cf'><strong>缺口资金(万元):</strong>${traffic.gapFund}</div></td>
		</tr>

