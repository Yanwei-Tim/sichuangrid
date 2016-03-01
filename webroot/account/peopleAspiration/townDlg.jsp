<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
       <tr>
			<td ><div class='innerTxt cf'><strong>工程名称:</strong>${town.projectName}</div></td>
		</tr>
       	<tr>
			<td ><div class='innerTxt cf'><strong>受益人口:</strong>${town.beneficiaryNumber}</div></td>
		</tr>
		<c:if test="${town.buildType.id != null}">
       	<tr>
        	<td ><div class='innerTxt cf'><strong>建设性质:</strong>
        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@TOWN_PROJECT_BUILD_TYPE" defaultValue="${town.buildType.id}" /></div></td>
       	</tr>
       	</c:if>
       	<c:if test="${town.projectCategory.id != null}">
       	<tr>
        	<td ><div class='innerTxt cf'><strong>项目类别:</strong>
        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@TOWN_PROJECT" defaultValue="${town.projectCategory.id}" /></div></td>
       	</tr>
       	</c:if>
       	<c:if test="${town.projectSubCategory.id != null}">
       	<tr>
        	<td ><div class='innerTxt cf'><strong>建设类型:</strong>
        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@TOWN_PROJECT_SUB" defaultValue="${town.projectSubCategory.id}" /></div></td>
       	</tr>
       	</c:if>
     
       	<tr>
			<td ><div class='innerTxt cf'><strong>项目库编号:</strong>${town.projectNumber}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>数量:</strong>${town.scopeNumber}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>面积:</strong>${town.area}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>里程(公里):</strong>${town.mileage}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>建设起点:</strong>${town.fromAddress}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>建设讫点:</strong>${town.toAddress}</div></td>
		</tr>
       	<tr>
			<td ><div class='innerTxt cf'><strong>计划资金(万元):</strong>${town.plannedInvestment}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>自筹资金(万元):</strong>${town.selfFund}</div></td>
		</tr>
			<tr>
			<td ><div class='innerTxt cf'><strong>缺口资金(万元):</strong>${town.gapFund}</div></td>
		</tr>
