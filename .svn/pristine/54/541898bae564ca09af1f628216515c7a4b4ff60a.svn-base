<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
       <tr>
			<td ><div class='innerTxt cf'><strong>工程名称:</strong>${science.projectName}</div></td>
		</tr>
       	<tr>
			<td ><div class='innerTxt cf'><strong>受益人口:</strong>${science.beneficiaryNumber}</div></td>
		</tr>
		<c:if test="${science.buildType.id != null}">
       	<tr>
        	<td ><div class='innerTxt cf'><strong>建设性质:</strong>
        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@SCIENCE_BUILD_TYPE" defaultValue="${science.buildType.id}" /></div></td>
       	</tr>
       	</c:if>
       
       	<c:if test="${science.projectCategory.id != null}">
       	<tr>
        	<td ><div class='innerTxt cf'><strong>类别:</strong>
        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@SCIENCE_PROJECT" defaultValue="${science.projectCategory.id}" /></div></td>
       	</tr>
       	</c:if>
       	<c:if test="${science.projectSubCategory.id != null}">
       	<tr>
        	<td ><div class='innerTxt cf'><strong>项目类型:</strong>
        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@SCIENCE_PROJECT_SUB" defaultValue="${science.projectSubCategory.id}" /></div></td>
       	</tr>
       	</c:if>
     	<c:if test="${science.projectLevelCategory.id != null}">
       	<tr>
        	<td ><div class='innerTxt cf'><strong>项目级别:</strong>
        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@SCIENCE_PROJECT_LEVEL" defaultValue="${science.projectLevelCategory.id}" /></div></td>
       	</tr>
       	</c:if>
		<tr>
			<td ><div class='innerTxt cf'><strong>项目名称:</strong>${science.itemName}</div></td>
		</tr>
		<c:if test="${science.contentCategory.id != null}">
       	<tr>
        	<td ><div class='innerTxt cf'><strong>广播电视播放方式:</strong>
        	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@SCIENCE_BROADCAST" defaultValue="${science.contentCategory.id}" /></div></td>
       	</tr>
       	</c:if>
		
		<tr>
			<td ><div class='innerTxt cf'><strong>数量（户、个、场）:</strong>${science.scienceScope}</div></td>
		</tr>
		
		<tr>
			<td ><div class='innerTxt cf'><strong>宣传次数:</strong>${science.publicizeNum}</div></td>
		</tr>
		
		<tr>
			<td ><div class='innerTxt cf'><strong>主题内容:</strong>${science.content}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>建设起点:</strong>${science.fromAddress}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>建设讫点:</strong>${science.toAddress}</div></td>
		</tr>
       	<tr>
			<td ><div class='innerTxt cf'><strong>计划资金(万元):</strong>${science.plannedInvestment}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>自筹资金(万元):</strong>${science.selfFund}</div></td>
		</tr>
			<tr>
			<td ><div class='innerTxt cf'><strong>缺口资金(万元):</strong>${science.gapFund}</div></td>
		</tr>
