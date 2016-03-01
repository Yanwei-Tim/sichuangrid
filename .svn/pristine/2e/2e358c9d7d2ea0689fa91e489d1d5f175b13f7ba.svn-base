<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
       <tr>
			<td ><div class='innerTxt cf'><strong>工程名称：</strong>${education.projectName}</div></td>
		</tr>
       	<tr>
			<td ><div class='innerTxt cf'><strong>受益人口：</strong>${education.beneficiaryNumber}</div></td>
		</tr>
		<c:if test="${education.buildType.id != null}">
       	<tr>
        	<td ><div class='innerTxt cf'><strong>建设性质：</strong>
        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@EDUCATION_BUILD_TYPE" defaultValue="${education.buildType.id}" /></div></td>
       	</tr>
       	</c:if>
       
       	<c:if test="${education.projectCategory.id != null}">
       	<tr>
        	<td ><div class='innerTxt cf'><strong>项目类别：</strong>
        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@EDUCATION_PROJECT" defaultValue="${education.projectCategory.id}" /></div></td>
       	</tr>
       	</c:if>
       	<c:if test="${education.projectSubCategory.id != null}">
       	<tr>
        	<td ><div class='innerTxt cf'><strong>项目内容：</strong>
        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@EDUCATION_PROJECT_SUB" defaultValue="${education.projectSubCategory.id}" /></div></td>
       	</tr>
       	</c:if>
     
		<tr>
			<td ><div class='innerTxt cf'><strong>建设起点：</strong>${education.fromAddress}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>建设讫点：</strong>${education.toAddress}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>建设单位名称：</strong>${education.buildCompanyName}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>建设面积：</strong>${education.buildArea}</div></td>
		</tr>
       	<tr>
			<td ><div class='innerTxt cf'><strong>计划资金(万元)：</strong>${education.plannedInvestment}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>自筹资金(万元)：</strong>${education.selfFund}</div></td>
		</tr>
			<tr>
			<td ><div class='innerTxt cf'><strong>缺口资金(万元)：</strong>${education.gapFund}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>学生姓名：</strong>${education.studentName}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>学生就读学校：</strong>${education.schoolName}</div></td>
		</tr>
		<c:if test="${education.degreeCategory.id != null}">
	       	<tr>
	        	<td ><div class='innerTxt cf'><strong>就学类型：</strong>
	        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@EDUCATION_DEGREE_TYPE" defaultValue="${education.degreeCategory.id}" /></div></td>
	       	</tr>
       	</c:if>
		<tr>
			<td ><div class='innerTxt cf'><strong>时间：</strong>${education.studyDate}</div></td>
		</tr>
		<c:if test="${education.modeCategory.id != null}">
	       	<tr>
	        	<td ><div class='innerTxt cf'><strong>方式：</strong>
	        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@EDUCATION_MODE_TYPE" defaultValue="${education.modeCategory.id}" /></div></td>
	       	</tr>
       	</c:if>
		<c:if test="${education.itemCategory.id != null}">
	       	<tr>
	        	<td ><div class='innerTxt cf'><strong>项目名称：</strong>
	        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@EDUCATION_ITEM_TYPE" defaultValue="${education.itemCategory.id}" /></div></td>
	       	</tr>
       	</c:if>
		<c:if test="${education.distanceCategory.id != null}">
	       	<tr>
	        	<td ><div class='innerTxt cf'><strong>路程：</strong>
	        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@EDUCATION_DISTANCE_TYPE" defaultValue="${education.distanceCategory.id}" /></div></td>
	       	</tr>
       	</c:if>
		<c:if test="${education.roadConditionCategory.id != null}">
	       	<tr>
	        	<td ><div class='innerTxt cf'><strong>路程：</strong>
	        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@EDUCATION_ROAD_CONDITION_TYPE" defaultValue="${education.roadConditionCategory.id}" /></div></td>
	       	</tr>
       	</c:if>
