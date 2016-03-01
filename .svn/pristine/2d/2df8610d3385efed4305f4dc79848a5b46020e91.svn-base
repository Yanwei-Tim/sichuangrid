<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="/includes/baseInclude.jsp" />
<%request.setAttribute("list",request.getParameter("list"));%>
<style>
td {
	text-align:center;
}
</style>
<table class="tablelist">
	<tr>
	 	<td class="title"><label>上报单位</label></td>
	 	<td class="title"><div>村社道路</div><div>(公里)</div></td>
	 	<td class="title"><div>硬化路</div><div>(公里)</div></td>
	 	<td class="title"><div>泥结路</div><div>(公里)</div></td>
	 	<td class="title"><label>塘库堰池(个)</label></td>
	 	
	 	<td class="title"><label>塘(个)</label></td>
	 	<td class="title"><label>库(个)</label></td>
	 	<td class="title"><label>堰(个)</label></td>
	 	<td class="title"><label>池(个)</label></td>
	 	
	 	<td class="title"><div>水渠长度</div><div>(公里)</div></td>
	 	<td class="title"><label>生活饮用水户数</label></td>
	 	<td class="title"><div>电网改造</div><div>(单位:村)</div></td>
	 	
	 	<td class="title"><label>户用沼气池</label></td>
	 	<td class="title"><label>太阳能</label></td>
	 	<td class="title"><label>天燃气</label></td>
	 	
	 	<td class="title"><label>三建四改涉及户数</label></td>
	 	<td class="title"><div>宽带乡村</div><div>(单位:村)</div></td>
	 	<td class="title"><label>有线电视</label></td>
	 	<td class="title"><label>宽带(户)</label></td>
	</tr>
	<c:forEach items="${list }" var="vo">
		<tr>
		<td class=""><label>${vo.reportOrg.orgName }</label></td>
	 	<td class=""><label><fmt:formatNumber value='${vo.infrastructure.villageRoad }' pattern='#0.00'/></label></td>
	 	<td class=""><label><fmt:formatNumber value='${vo.infrastructure.villageHardenedRoad }' pattern='#0.00'/></label></td>
	 	<td class=""><label><fmt:formatNumber value='${vo.infrastructure.villageMudKnotRoad }' pattern='#0.00'/></label></td>
	 	<td class=""><label>${vo.infrastructure.weirPoolNum }</label></td>
	 	
	 	<td class=""><label>${vo.infrastructure.pond }</label></td>
	 	<td class=""><label>${vo.infrastructure.library }</label></td>
	 	<td class=""><label>${vo.infrastructure.weir }</label></td>
	 	<td class=""><label>${vo.infrastructure.pool }</label></td>
	 	
	 	<td class=""><label><fmt:formatNumber value='${vo.infrastructure.canalLength }' pattern='#0.00'/></label></td>
	 	<td class=""><label>${vo.infrastructure.drinkingWaterNum }</label></td>
	 	<td class=""><label>${vo.infrastructure.isPowerGrid }</label></td>
	 	
	 	<td class=""><label>${vo.infrastructure.biogasNum }</label></td>
	 	<td class=""><label>${vo.infrastructure.solarEnergy }</label></td>
	 	<td class=""><label>${vo.infrastructure.naturalGas }</label></td>
	 	
	 	<td class=""><label>${vo.infrastructure.involveHouseCount }</label></td>
		<td class=""><label>${vo.infrastructure.isBroadbandVillage }</label></td>
		<td class=""><label>${vo.infrastructure.cableTvCount }</label></td>
		<td class=""><label>${vo.infrastructure.broadbandCount }</label></td>
		</tr>
	</c:forEach>
</table>


