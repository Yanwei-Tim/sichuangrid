<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
      <tr>
		<td ><div class='innerTxt cf'><strong>工程名称:</strong>${other.projectName}</div></td>
	</tr>
	<c:if test="${other.buildType.id != null}">
      	<tr>
       	<td ><div class='innerTxt cf'><strong>建设性质:</strong>
       	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@LEDGER_OTHER_BUILD_TYPE" defaultValue="${other.buildType.id}" /></div></td>
      	</tr>
     </c:if>
      	
    <c:if test="${other.projectCategory.id != null}">
      	<tr>
       	<td ><div class='innerTxt cf'><strong>项目类别:</strong>
       	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@OTHER_PROJECT" defaultValue="${other.projectCategory.id}" /></div></td>
      	</tr>
    </c:if>
	<tr>
		<td ><div class='innerTxt cf'><strong>工程规模及内容:</strong>${other.scopeContent}</div></td>
	</tr>
	<tr>
		<td ><div class='innerTxt cf'><strong>其它:</strong>${other.otherContent}</div></td>
	</tr>
	<tr>
			<td ><div class='innerTxt cf'><strong>建设起点:</strong>${other.fromAddress}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>建设讫点:</strong>${other.toAddress}</div></td>
		</tr>
     <tr>
		<td ><div class='innerTxt cf'><strong>计划资金(万元):</strong>${other.plannedInvestment}</div></td>
	</tr>
	<tr>
		<td ><div class='innerTxt cf'><strong>自筹资金(万元):</strong>${other.selfFund}</div></td>
	</tr>
		<tr>
		<td ><div class='innerTxt cf'><strong>缺口资金(万元):</strong>${other.gapFund}</div></td>
	</tr>
		