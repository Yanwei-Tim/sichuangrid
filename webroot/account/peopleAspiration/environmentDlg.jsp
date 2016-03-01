<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
       <tr>
			<td ><div class='innerTxt cf'><strong>工程名称：</strong>${environment.projectName}</div></td>
		</tr>
       	<tr>
			<td ><div class='innerTxt cf'><strong>受益人口：</strong>${environment.beneficiaryNumber}</div></td>
		</tr>
		<c:if test="${environment.buildType.id != null}">
       	<tr>
        	<td ><div class='innerTxt cf'><strong>建设性质：</strong>
        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@ENVIRONMENT_BUILD_TYPE" defaultValue="${environment.buildType.id}" /></div></td>
       	</tr>
       	</c:if>
       
       	<c:if test="${environment.projectCategory.id != null}">
       	<tr>
        	<td ><div class='innerTxt cf'><strong>项目类别：</strong>
        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@ENVIRONMENT_PROJECT" defaultValue="${environment.projectCategory.id}" /></div></td>
       	</tr>
       	</c:if>
     	<c:if test="${environment.projectSubCategory.id != null}">
       	<tr>
        	<td ><div class='innerTxt cf'><strong>治理类型：</strong>
        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@ENVIRONMENT_PROJECT_SUB" defaultValue="${environment.projectSubCategory.id}" /></div></td>
       	</tr>
       	</c:if>
       	<tr>
			<td ><div class='innerTxt cf'><strong>建设起点：</strong>${environment.fromAddress}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>建设讫点：</strong>${environment.toAddress}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>建设规模及建设内容：</strong>${environment.content}</div></td>
		</tr>
		
		
		<tr>
			<td ><div class='innerTxt cf'><strong>治理数量：</strong>${environment.governNumber}</div></td>
		</tr>
       	<tr>
			<td ><div class='innerTxt cf'><strong>计划资金(万元)：</strong>${environment.plannedInvestment}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>自筹资金(万元)：</strong>${environment.selfFund}</div></td>
		</tr>
			<tr>
			<td ><div class='innerTxt cf'><strong>缺口资金(万元)：</strong>${environment.gapFund}</div></td>
		</tr>
       	
