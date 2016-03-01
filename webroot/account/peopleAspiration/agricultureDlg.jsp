<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
       
       <tr>
			<td ><div class='innerTxt cf'><strong>工程名称：</strong>${agriculture.projectName}</div></td>
		</tr>
       	<tr>
			<td ><div class='innerTxt cf'><strong>受益人口：</strong>${agriculture.beneficiaryNumber}</div></td>
		</tr>
		<c:if test="${agriculture.buildType.id != null}">
       	<tr>
        	<td ><div class='innerTxt cf'><strong>建设性质：</strong>
        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@AGRICULTURE_PROJECT_BUILD_TYPE" defaultValue="${agriculture.buildType.id}" /></div></td>
       	</tr>
       	</c:if>
       	<c:if test="${agriculture.projectCategory.id != null}">
       	<tr>
        	<td ><div class='innerTxt cf'><strong>项目类别：</strong>
        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@AGRICULTURE_PROJECT" defaultValue="${agriculture.projectCategory.id}" /></div></td>
       	</tr>
       	</c:if>
       	<c:if test="${agriculture.projectSubCategory.id != null}">
       	<tr>
        	<td ><div class='innerTxt cf'><strong>项目类型：</strong>
        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@AGRICULTURE_PROJECT_SUB" defaultValue="${agriculture.projectSubCategory.id}" /></div></td>
       	</tr>
       	</c:if>

		<tr>
			<td ><div class='innerTxt cf'><strong>规模：</strong>${agriculture.scopeNumber}</div></td>
		</tr>
		
		<tr>
			<td ><div class='innerTxt cf'><strong>建设起点：</strong>${agriculture.fromAddress}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>建设讫点：</strong>${agriculture.toAddress}</div></td>
		</tr>
       	<tr>
			<td ><div class='innerTxt cf'><strong>计划资金(万元)：</strong>${agriculture.plannedInvestment}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>自筹资金(万元)：</strong>${agriculture.selfFund}</div></td>
		</tr>
			<tr>
			<td ><div class='innerTxt cf'><strong>缺口资金(万元)：</strong>${agriculture.gapFund}</div></td>
		</tr>
			  