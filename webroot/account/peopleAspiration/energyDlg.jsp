<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
       <tr>
			<td ><div class='innerTxt cf'><strong>工程名称：</strong>${energy.projectName}</div></td>
		</tr>
       	<tr>
			<td ><div class='innerTxt cf'><strong>受益人口：</strong>${energy.beneficiaryNumber}</div></td>
		</tr>
		<c:if test="${energy.buildType.id != null}">
       	<tr>
        	<td ><div class='innerTxt cf'><strong>建设性质：</strong>
        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@ENERGY_BUILD_TYPE" defaultValue="${energy.buildType.id}" /></div></td>
       	</tr>
       	</c:if>
       
       	<c:if test="${energy.projectCategory.id != null}">
       	<tr>
        	<td ><div class='innerTxt cf'><strong>项目类别：</strong>
        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@ENERGY_PROJECT" defaultValue="${energy.projectCategory.id}" /></div></td>
       	</tr>
       	</c:if>
       	<c:if test="${energy.projectSubCategory.id != null}">
       	<tr>
        	<td ><div class='innerTxt cf'><strong>工程类型：</strong>
        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@ENERGY_PROJECT_SUB_TYPE" defaultValue="${energy.projectSubCategory.id}" /></div></td>
       	</tr>
       	</c:if>
       	<c:if test="${energy.lineCategory.id != null}">
       	<tr>
        	<td ><div class='innerTxt cf'><strong>线路类型：</strong>
        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@ENERGY_LINE_CATEGORY" defaultValue="${energy.lineCategory.id}" /></div></td>
       	</tr>
       	</c:if>
       	<c:if test="${energy.pipeLineCategory.id != null}">
       	<tr>
        	<td ><div class='innerTxt cf'><strong>管道类型：</strong>
        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@ENERGY_PIPELINE_CATEGORY" defaultValue="${energy.pipeLineCategory.id}" /></div></td>
       	</tr>
       	</c:if>
		<c:if test="${energy.pipeMaterialCategory.id != null}">
       	<tr>
        	<td ><div class='innerTxt cf'><strong>管道材质：</strong>
        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@ENERGY_PIPE_MATERIAL_CATEGORY" defaultValue="${energy.pipeMaterialCategory.id}" /></div></td>
       	</tr>
       	</c:if>
       	<c:if test="${energy.securityCategory.id != null}">
       	<tr>
        	<td ><div class='innerTxt cf'><strong>安全设施类型:</strong>
        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@ENERGY_SECURITY_CATEGORY" defaultValue="${energy.securityCategory.id}" /></div></td>
       	</tr>
       	</c:if>
       	
    	<tr>
			<td ><div class='innerTxt cf'><strong>容积(立方米):</strong>${energy.capacity}</div></td>
		</tr>
		
		<tr>
			<td ><div class='innerTxt cf'><strong>长度（公里）:</strong>${energy.mileage}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>填埋深度（米）:</strong>${energy.depth}</div></td>
		</tr>
		
		<tr>
			<td ><div class='innerTxt cf'><strong>数量:</strong>${energy.energyNumber}${energy.securityNum}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>建设起点:</strong>${energy.fromAddress}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>建设讫点:</strong>${energy.toAddress}</div></td>
		</tr>
       	<tr>
			<td ><div class='innerTxt cf'><strong>计划资金(万元):</strong>${energy.plannedInvestment}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>自筹资金(万元):</strong>${energy.selfFund}</div></td>
		</tr>
			<tr>
			<td ><div class='innerTxt cf'><strong>缺口资金(万元):</strong>${energy.gapFund}</div></td>
		</tr>
       	
