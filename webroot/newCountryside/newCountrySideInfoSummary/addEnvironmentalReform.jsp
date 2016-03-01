<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<%request.setAttribute("list",request.getParameter("list"));%>
<table class="tablelist">
	<tr>
	 	<td class="title"><label>上报单位</label></td>
	 	<td class="title"><label>垃圾池(个)</label></td>
	 	<td class="title"><label>公厕</label></td>
	 	<td class="title"><label>污水处理设施</label></td>
	 	<s:if test="0==isPlaning">
	 	<td class="title"><label>生活污水处理户数</label></td>
	 	<td class="title"><label>垃圾处理全覆盖</label></td>
	 	<td class="title"><label>面源污染有效治理</label></td>
	 	</s:if>
	</tr>
	<c:forEach items="${list }" var="vo">
		<tr>
		
		<td class=""><label>${vo.reportOrg.orgName }</label></td>
		<td class=""><label>${vo.environmentalReform.garbageTank }</label></td>
	 	<td class=""><label>${vo.environmentalReform.toilets }</label></td>
	 	<td class=""><label>${vo.environmentalReform.treatmentFacilities }</label></td>
	 	<s:if test="0==isPlaning">
	 	<td class=""><label>${vo.environmentalReform.sanitarySewage }</label></td>
	 	<td class=""><label>${vo.environmentalReform.garbageDisposal }</label></td>
	 	<td class=""><label>${vo.environmentalReform.treatmentPollution }</label></td>
	 	</s:if>
		</tr>
	</c:forEach>
</table>


