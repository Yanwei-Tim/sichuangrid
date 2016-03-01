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
	 	
	 	<td class="title"><label>概算总投资</label></td>
	 	<td class="title"><label>财政资金总投入</label></td>
	 	
	 	<td class="title"><label>中央/省份投入(万元)</label></td>
	 	<td class="title"><label>市投入资金(万元)</label></td>
	 	<td class="title"><label>县资金投入(万元)</label></td>
	 	
	 	<td class="title"><label>社会资金总投入</label></td>
	 	
	 	<td class="title"><label>金融投入资金(万元)</label></td>
	 	<td class="title"><label>工商投入资金(万元)</label></td>
	 	
	 	<td class="title"><label></label>其他</td>
	 	
	 	<td class="title"><label>农民自筹资金(万元)</label></td>
	</tr>
	<c:forEach items="${list }" var="vo">
		<tr>
		<td class=""><label>${vo.reportOrg.orgName }</label></td>
		<td class=""><label><fmt:formatNumber value='${vo.capitalInvested.allInvestmentCount }' pattern='#0.00'/></label></td>
		<td class=""><label><fmt:formatNumber value='${vo.capitalInvested.capitalInvestmentCount }' pattern='#0.00'/></label></td>
	 	<td class=""><label><fmt:formatNumber value='${vo.capitalInvested.centralProvinceInvested }' pattern='#0.00'/></label></td>
	 	<td class=""><label><fmt:formatNumber value='${vo.capitalInvested.municipalityInvested }' pattern='#0.00'/></label></td>
	 	<td class=""><label><fmt:formatNumber value='${vo.capitalInvested.countyInvested }' pattern='#0.00'/></label></td>
	 	<td class=""><label><fmt:formatNumber value='${vo.capitalInvested.sociologyInvested }' pattern='#0.00'/></label></td>
	 	<td class=""><label><fmt:formatNumber value='${vo.capitalInvested.financialInvested }' pattern='#0.00'/></label></td>
	 	<td class=""><label><fmt:formatNumber value='${vo.capitalInvested.industryAndCommerceInvested }' pattern='#0.00'/></label></td>
	 	<td class=""><label><fmt:formatNumber value='${vo.capitalInvested.otherInvested }' pattern='#0.00'/></label></td>
	 	<td class=""><label><fmt:formatNumber value='${vo.capitalInvested.farmerInvested }' pattern='#0.00'/></label></td>
		</tr>
	</c:forEach>
</table>


