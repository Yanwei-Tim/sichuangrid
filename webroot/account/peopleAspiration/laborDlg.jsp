<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
     	<tr>
			<td ><div class='innerTxt cf'><strong>工程名称:</strong>${labor.projectName}</div></td>
		</tr>
		<c:if test="${labor.projectCategory.id != null}">
		    <tr>
		       	<td ><div class='innerTxt cf'><strong>项目类别:</strong>
		       	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@LABOR_PROJECT" defaultValue="${labor.projectCategory.id}" /></div></td>
		    </tr>
    	 </c:if>
     	<c:if test="${labor.projectSubCategory.id != null}">
		    <tr>
		       	<td ><div class='innerTxt cf'><strong>项目类型:</strong>
		       	<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@LABOR_PROJECT_SUB" defaultValue="${labor.projectSubCategory.id}" /></div></td>
		   </tr>
      	</c:if>
	  	<c:if test="${labor.projectSubContentCategory.id != null}">
	   		<tr>
	      	 	<td ><div class='innerTxt cf'><strong>项目内容:</strong>
	       		<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@LABOR_PROJECT_CONTENT" defaultValue="${labor.projectSubContentCategory.id}" /></div></td>
	   	    </tr>
      	</c:if>
		<c:if test="${labor.degree.id != null}">
	   		<tr>
	      	 	<td ><div class='innerTxt cf'><strong>本人学历:</strong>
	       		<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@LEDGER_DEGREE" defaultValue="${labor.degree.id}" /></div></td>
	   	    </tr>
      	</c:if>
      	
      	<tr>
			<td ><div class='innerTxt cf'><strong>本人技能特长:</strong>${labor.skill}</div></td>
		</tr>
		
		<c:if test="${labor.ageLevel.id != null}">
	   		<tr>
	      	 	<td ><div class='innerTxt cf'><strong>年龄段:</strong>
	       		<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@LABOR_AGE" defaultValue="${labor.ageLevel.id}" /></div></td>
	   	    </tr>
      	</c:if>
      	<c:if test="${labor.crippleLevel.id != null}">
	   		<tr>
	      	 	<td ><div class='innerTxt cf'><strong>残疾等级:</strong>
	       		<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@LABOR_CRIPPLE" defaultValue="${labor.crippleLevel.id}" /></div></td>
	   	    </tr>
      	</c:if>
      	<c:if test="${labor.dignity.id != null}">
	   		<tr>
	      	 	<td ><div class='innerTxt cf'><strong>身份性质:</strong>
	       		<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@LABOR_DIGNITY" defaultValue="${labor.dignity.id}" /></div></td>
	   	    </tr>
      	</c:if>
      	<tr>
			<td ><div class='innerTxt cf'><strong>工资拖欠工程项目名称:</strong>${labor.projectName}</div></td>
		</tr>
      	<tr>
			<td ><div class='innerTxt cf'><strong>工资拖欠施工单位:</strong>${labor.company}</div></td>
		</tr>
      	<tr>
			<td ><div class='innerTxt cf'><strong>工资拖欠施工单位地址:</strong>${labor.yawnAddr}</div></td>
		</tr>
      	<tr>
			<td ><div class='innerTxt cf'><strong>工资拖欠施工单位联系人姓名:</strong>${labor.yawnContactor}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>联系电话:</strong>${labor.yawnMobile}</div></td>
		</tr>
		
      	<tr>
			<td ><div class='innerTxt cf'><strong>涉及人数:</strong>${labor.relationNumber}</div></td>
		</tr>
		<tr>
			<td ><div class='innerTxt cf'><strong>涉及金额（万元）:</strong>${labor.money}</div></td>
		</tr>
