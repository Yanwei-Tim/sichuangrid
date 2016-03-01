<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

       <tr>
			<td ><div class='innerTxt cf'><strong>工程名称:</strong>${waterResource.projectName}</div></td>
		</tr>
       	<tr>
			<td ><div class='innerTxt cf'><strong>受益人口:</strong>${waterResource.beneficiaryNumber}</div></td>
		</tr>
		
		<c:if test="${waterResource.buildType.id != null}">
       	<tr>
        	<td ><div class='innerTxt cf'><strong>建设性质:</strong>
        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@LEDGER_WATEER_BUILD_TYPE" defaultValue="${waterResource.buildType.id}" /></div></td>
       	</tr>
       	</c:if>
		<c:if test="${waterResource.projectCategory.id != null}">
       	<tr>
        	<td ><div class='innerTxt cf'><strong>项目类别:</strong>
        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@LEDGER_WATEER_PROJECT" defaultValue="${waterResource.projectCategory.id}" /></div></td>
       	</tr>
       	</c:if>
		<c:if test="${waterResource.projectSubCategory.id != null}">
       	<tr>
        	<td ><div class='innerTxt cf'><strong>工程类别:</strong>
        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@LEDGER_WATEER_PROJECT_SUB_TYPE" defaultValue="${waterResource.projectSubCategory.id}" /></div></td>
       	</tr>
       	</c:if>
		
		<tr>
			<td ><div class='innerTxt cf'><strong>数量(口、节):</strong>${waterResource.num}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>集中供水(处):</strong>${waterResource.centralized}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>分散供水(日供水量:方):</strong>${waterResource.scatter}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>蓄水量:</strong>${waterResource.impoundage}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>里程(公里):</strong>${waterResource.mileage}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>建设起点:</strong>${waterResource.fromAddress}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>建设讫点:</strong>${waterResource.toAddress}</div></td>
		</tr>
		
       	<tr>
			<td ><div class='innerTxt cf'><strong>计划资金(万元):</strong>${waterResource.plannedInvestment}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>自筹资金(万元):</strong>${waterResource.selfFund}</div></td>
		</tr>
			<tr>
			<td ><div class='innerTxt cf'><strong>缺口资金(万元):</strong>${waterResource.gapFund}</div></td>
		</tr>
   