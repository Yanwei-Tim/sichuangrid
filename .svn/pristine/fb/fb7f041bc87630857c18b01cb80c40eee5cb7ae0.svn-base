<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
			       
       <tr>
			<td ><div class='innerTxt cf'><strong>工程名称:</strong>${medical.projectName}</div></td>
		</tr>
       	<tr>
			<td ><div class='innerTxt cf'><strong>受益人口:</strong>${medical.beneficiaryNumber}</div></td>
		</tr>
		<c:if test="${medical.buildType.id != null}">
       	<tr>
        	<td ><div class='innerTxt cf'><strong>建设性质:</strong>
        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@MEDICAL_BUILD_TYPE" defaultValue="${medical.buildType.id}" /></div></td>
       	</tr>
       	</c:if>
       
       	<c:if test="${medical.projectCategory.id != null}">
       	<tr>
        	<td ><div class='innerTxt cf'><strong>项目类型:</strong>
        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@MEDICAL_PROJECT" defaultValue="${medical.projectCategory.id}" /></div></td>
       	</tr>
       	</c:if>
     	<c:if test="${medical.projectSubCategory.id != null}">
       	<tr>
        	<td ><div class='innerTxt cf'><strong>项目内容:</strong>
        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@MEDICAL_PROJECT_SUB" defaultValue="${medical.projectSubCategory.id}" /></div></td>
       	</tr>
       	</c:if>
		<tr>
			<td ><div class='innerTxt cf'><strong>建设工程量:</strong>${medical.buildArea}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>设备:</strong>${medical.equipment}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>建设起点:</strong>${medical.fromAddress}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>建设讫点:</strong>${medical.toAddress}</div></td>
		</tr>
       	<tr>
			<td ><div class='innerTxt cf'><strong>计划资金(万元):</strong>${medical.plannedInvestment}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>自筹资金(万元):</strong>${medical.selfFund}</div></td>
		</tr>
			<tr>
			<td ><div class='innerTxt cf'><strong>缺口资金(万元):</strong>${medical.gapFund}</div></td>
		</tr>
