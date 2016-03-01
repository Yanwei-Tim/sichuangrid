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
	 	<td class="title"><label>聚居点数量</label></td>
	 	<td class="title"><label>新建数</label></td>
	 	<td class="title"><label>改造数</label></td>
	 	<td class="title"><label>保护数</label></td>
	 	<td class="title"><label>涉及农户</label></td>
	 	<s:if test="0==isPlaning">
	 	<td class="title"><label>新村建设全覆盖</label></td>
	 	<td class="title"><label>房屋总数</label></td>
	 	<td class="title"><label>无房户数</label></td>
	 	<td class="title"><label>危房户数</label></td>
	 	<td class="title"><label>住房困难户数</label></td>
	 	</s:if>
	 	<td class="title"><label>人均安全住房面积</label></td>
	 	<td class="title"><label>廉租房数</label></td>
	 	<td class="title"><label>入住廉租房数</label></td>
	 	<td class="title"><label>涉及人数</label></td>
	</tr>
	<c:forEach items="${list }" var="vo">
		<tr>
		<td class=""><label>${vo.reportOrg.orgName }</label></td>
	 	<td class=""><label>${vo.newVillage.settlementsNumber }</label></td>
	 	<td class=""><label>${vo.newVillage.settlementsBuild }</label></td>
	 	<td class=""><label>${vo.newVillage.settlementsReform }</label></td>
	 	<td class=""><label>${vo.newVillage.settlementsProtect }</label></td>
	 	<td class=""><label>${vo.newVillage.involvingFarmers }</label></td>
	 	<s:if test="0==isPlaning">
	 	<td class=""><label>${vo.newVillage.isAllStanding }</label></td>
	 	<td class=""><label>${vo.newVillage.houseCount }</label></td>
	 	<td class=""><label>${vo.newVillage.noHouseCount }</label></td>
	 	<td class=""><label>${vo.newVillage.dangerousHouseCount }</label></td>
	 	<td class=""><label>${vo.newVillage.housingDifficultCount }</label></td>
	 	</s:if>
	 	<td class=""><label><fmt:formatNumber value='${vo.newVillage.capitaHousingArea }' pattern='#0.00'/></label></td>
	 	<td class=""><label>${vo.newVillage.lowRentHousing }</label></td>
	 	<td class=""><label>${vo.newVillage.inLowRentHousing }</label></td>
	 	<td class=""><label>${vo.newVillage.numberInvolved }</label></td>
		</tr>
	</c:forEach>
</table>


