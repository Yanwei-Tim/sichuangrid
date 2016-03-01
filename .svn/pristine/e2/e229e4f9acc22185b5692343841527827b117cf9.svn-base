<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="/includes/baseInclude.jsp" />
<%request.setAttribute("list",request.getParameter("list"));%>
<table class="tablelist">
	<tr>
	 	<td class="title"><label>上报单位</label></td>
	 	<td class="title"><label>农业主导产业收入(元)</label></td>
	 	<td class="title"><label>农村家庭经营性收入(元)</label></td>
	 	<td class="title"><label>农民人均可支配收入(元)</label></td>
	 	<td class="title"><label>村集体经济收入(元)</label></td>
	</tr>
	<c:forEach items="${list }" var="vo">
		<tr>
		<td class=""><label>${vo.reportOrg.orgName }</label></td>
		<td class=""><label><fmt:formatNumber value='${vo.farmerPerIncomeInfo.agriculturalIncome }' pattern='#0.00'/></label></td>
		<td class=""><label><fmt:formatNumber value='${vo.farmerPerIncomeInfo.householdIncome }' pattern='#0.00'/></label></td>
	 	<td class=""><label><fmt:formatNumber value='${vo.farmerPerIncomeInfo.farmerPerIncome }' pattern='#0.00'/></label></td>
	 	<td class=""><label><fmt:formatNumber value='${vo.farmerPerIncomeInfo.villageCollectiveIncome }' pattern='#0.00'/></label></td>
		</tr>
	</c:forEach>
</table>


