<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="/includes/baseInclude.jsp" />
<%request.setAttribute("list",request.getParameter("list"));%>
<table class="tablelist">
	<tr>
	 	<td class="title"><label>机构名字</label></td>
	 	<td class="title"><label>幅员面积</label></td>
	 	<td class="title"><label>耕地面积</label></td>
	 	<td class="title"><label>林地面积</label></td>
	 	<td class="title"><label>荒地面积</label></td>
	 	<td class="title"><label>流转土地面积</label></td>
	 	<td class="title"><label>总户数</label></td>
	 	<td class="title"><label>总人数</label></td>
	 	<td class="title"><label>外出务工人数</label></td>
	 	<td class="title"><label>集体资金(万元)</label></td>
	 	<td class="title"><label>集体资产折资(万元)</label></td>
	</tr>
	<c:forEach items="${list }" var="vo">
		<tr>
		<td class=""><label>${vo.reportOrg.orgName }</label></td>
	 	<td class=""><label><fmt:formatNumber value='${vo.basicYearInfo.totalArea }' pattern='#0.00'/></label></td>
	 	<td class=""><label><fmt:formatNumber value='${vo.basicYearInfo.cultivatedLandArea }' pattern='#0.00'/></label></td>
	 	<td class=""><label><fmt:formatNumber value='${vo.basicYearInfo.woodlandArea }' pattern='#0.00'/></label></td>
	 	<td class=""><label><fmt:formatNumber value='${vo.basicYearInfo.wastelandArea }' pattern='#0.00'/></label></td>
	 	<td class=""><label><fmt:formatNumber value='${vo.basicYearInfo.landTransfer }' pattern='#0.00'/></label></td>
	 	<td class=""><label>${vo.basicYearInfo.totalHouseholdsNum }</label></td>
	 	<td class=""><label>${vo.basicYearInfo.totalPeopleNum }</label></td>
	 	<td class=""><label>${vo.basicYearInfo.outWorkNum }</label></td>
	 	<td class=""><label><fmt:formatNumber value='${vo.basicYearInfo.collectiveFunds }' pattern='#0.00'/></label></td>
	 	<td class=""><label><fmt:formatNumber value='${vo.basicYearInfo.collectiveAssets }' pattern='#0.00'/></label></td>
		</tr>
	</c:forEach>
</table>


